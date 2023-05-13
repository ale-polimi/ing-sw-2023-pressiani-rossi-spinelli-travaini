package network.structure;

import network.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;

public class SocketServer implements Runnable, Server{
    private final ServerRMI server;
    private final int port;
    ServerSocket serverSocket;

    /**
     * Custom constructor of class SocketServer
     * @param server The main server
     * @param port The port where the message will be sent
     */
    public SocketServer(ServerRMI server, int port){
        this.server = server;
        this.port = port;
    }


    /**
     * The run method that the server thread execute
     */
    @Override
    public void run() {
        try {serverSocket = new ServerSocket(port);}
        catch ( IOException e) {System.err.println("Server could not start!");}
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket socket = serverSocket.accept();
                socket.setSoTimeout(5000);
                SocketHandler clientHandler = new SocketHandler(this, socket);
                Thread thread = new Thread(clientHandler, "ss_handler" + socket.getInetAddress());
                thread.start();
                registry(clientHandler);
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
   public void receiveMessage(Message message){server.receiveMessage(message);}

    /**
     * Send a message to the client
     * @param message the message that has to be forwarded
     */
    @Override
    public void sendMessage(Message message){server.sendMessage(message);}

    /**
     * Handles the disconnection request from the players
     * @param clientHandler The clientHandler related to the player
     */
    @Override
    public void disconnect(ClientHandler clientHandler) {
        server.disconnect(clientHandler);
    }

    /**
     * Register a new client to the server
     * @param clientHandler the clientHandler of the client that tries to connect to the server
     * @throws RemoteException Threw when the server is unreachable
     */
    @Override
    public void registry(ClientHandler clientHandler) throws RemoteException {
        server.registry(clientHandler);
    }
}
