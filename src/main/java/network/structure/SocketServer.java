package network.structure;

import network.messages.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * This class represents an instance for the socket server.
 */
public class SocketServer implements Runnable, Server{
    private final StartServerImpl server;
    private final int port;

    private ArrayList<SocketHandler> clients;
    ServerSocket serverSocket;

    /**
     * Custom constructor for the socket server.
     * @param server is the main of the server.
     * @param port is the port of the server.
     */
    public SocketServer(StartServerImpl server, int port){
        this.server = server;
        this.port = port;
        this.clients = new ArrayList<>();
        try {serverSocket = new ServerSocket(port);}
        catch ( IOException e) {System.err.println("Server could not start!");}
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket socket = serverSocket.accept();
                SocketHandler clientHandler = new SocketHandler(this, socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
                registry(clientHandler);
                System.out.println("Client connected");
            } catch (IOException e) {
                System.err.println("Server socket unreachable ");
            }
        }
    }

    @Override
   public void receiveMessage(Message message){
        if(message.getType().equals(MessageType.PING)) server.getServerRMI().receiveMessage(message);
        else server.receiveMessage(message);
    }

    @Override
    public void sendMessage(Message message){for(SocketHandler s: clients)s.receivedMessage(message);}

    @Override
    public void disconnect(ClientHandler clientHandler) {
        try {
            clientHandler.disconnect();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        clients.remove((SocketHandler) clientHandler);
       server.disconnect();
    }

    /**
     * This method disconnects all clients from this server.
     */
    public void disconnect(){
        for(ClientHandler c: clients){
            try {
                c.receivedMessage(new GameClosedMessage("Controller", MessageType.GAME_CLOSED));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
        clients = new ArrayList<>();
    }

    @Override
    public void ping() throws RemoteException {
        for (ClientHandler c : clients) {
            if(!c.isConnected())disconnect(c);
        }
    }

    @Override
    public void registry(ClientHandler clientHandler) throws RemoteException {
        if(!clients.contains((SocketHandler) clientHandler)){
            clients.add((SocketHandler) clientHandler);
            clientHandler.receivedMessage(new AskNicknameMessage("Controller"));
        }
    }

    /**
     * Getter method for the startServer parameter.
     * @return the start server instance.
     */
    public StartServerImpl getServer() {return server;}
}
