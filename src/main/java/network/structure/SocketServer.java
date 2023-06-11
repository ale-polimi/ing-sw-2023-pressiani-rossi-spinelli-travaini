package network.structure;

import network.messages.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class SocketServer implements Runnable, Server{
    private final StartServerImpl server;
    private final int port;

    private ArrayList<SocketHandler> clients;
    ServerSocket serverSocket;

    /**
     * Custom constructor of class SocketServer
     * @param server The main server
     * @param port The port where the message will be sent
     */
    public SocketServer(StartServerImpl server, int port){
        this.server = server;
        this.port = port;
        this.clients = new ArrayList<>();
        try {serverSocket = new ServerSocket(port);}
        catch ( IOException e) {System.err.println("Server could not start!");}
    }


    /**
     * The run method that the server thread execute
     */
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

    /**
     * Receive a message from the client and send it to the RMI server
     * @param message the message that has to be dispatched
     */
    @Override
   public void receiveMessage(Message message){
        if(message.getType().equals(MessageType.PING)) server.getServerRMI().receiveMessage(message);
        else server.receiveMessage(message);
    }

    /**
     * Send a message to the client
     * @param message the message that has to be forwarded
     */
    @Override
    public void sendMessage(Message message){for(SocketHandler s: clients)s.receivedMessage(message);}

    /**
     * Handles the disconnection request from the players
     */
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
    /**
     * Register a new client to the server
     * @param clientHandler the clientHandler of the client that tries to connect to the server
     * @throws RemoteException Threw when the server is unreachable
     */
    @Override
    public void registry(ClientHandler clientHandler) throws RemoteException {
        if(!clients.contains((SocketHandler) clientHandler)){
            clients.add((SocketHandler) clientHandler);
            clientHandler.receivedMessage(new AskNicknameMessage("Controller"));
        }
    }

    public StartServerImpl getServer() {return server;}
}
