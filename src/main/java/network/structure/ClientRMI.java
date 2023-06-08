package network.structure;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import controller.ClientController;
import network.messages.Message;
import network.messages.MessageType;
import network.messages.PingMessage;
import observer.Observable;
import observer.Observer;

/**
 * this class represents an RMI client.
 */

public class ClientRMI extends Observable implements Client,Runnable {

    private transient Server server;

    //default RMI port
    private final int port;
    private final String address;
    boolean getConnected =false;
    private ArrayList<Message> messages =new ArrayList<>();
    transient ScheduledExecutorService timer;

    /**
     * is the constructor with the connection with the server
     *
     * @param address         is the IP client
     * @param port            is the port where the connection is
     */
    public ClientRMI(String address, int port, ClientController clientController) throws RemoteException {
        this.address=address;
        this.port=port;
        //clientController.addObserver(this);
    }

    /**
     *  Connection with the server
     *  @throws RemoteException when the registry is not found
     */
    @Override
    public void connection() throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(address,port);
            server = (Server) registry.lookup("server");
            initialize(server);
            this.timer = Executors.newSingleThreadScheduledExecutor();
            getConnected=true;
        }
        catch (RemoteException e){
            System.err.println("RMI registry not found");
        }
        catch (NotBoundException e){
            System.err.println("Error in RMI lookup");
        }
    }


    /**
     * Close connection with the server
     * @throws RemoteException when the client is already disconnected
     */
    @Override
    public void closeConnection() throws RemoteException {
        server.disconnect(this);
        disconnect();
    }

    /**
     * Forwards a message
     * @param message is the message to forward
     */
    @Override
    public void sendMessage(Message message) {

        try{
            server.receiveMessage(message);
        } catch (RemoteException e){
           disconnect();
        }
        //notifyObserver(new Message("client", message.getType()));

    }


    /**
     * Receive a message
     * @param message is the sent message
     */
    public void receivedMessage(Message message)throws RemoteException{messages.add(message);}

    /**
     * method for register a client to a game
     * @param server is the server which the client connects to for starting the game
     * @throws RemoteException if the connection is not possible
     */

    private void initialize(Server server) throws RemoteException {
            try {
                server.registry( (ClientHandler) UnicastRemoteObject.exportObject(this,0));
            } catch (RemoteException e) {
                e.printStackTrace();
                System.err.println("connection unable");
                getConnected = false;
            }
        }


    @Override
    public boolean isConnected() {
        return getConnected;
    }

    /**
     * Disconnect the RMI client
     */
    @Override
    public void disconnect() {
        server=null;
        System.out.println("Connection interrupted, terminating application...");
        System.exit(1);
    }

    /**
     * check the presence of problems in the connection between client and server
     */
    @Override
    public void ping() {
        timer.scheduleAtFixedRate(() -> {
            sendMessage(new PingMessage("client", MessageType.PING));
        }, 0, 5000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            if(messages.size()>0) notifyObserver(messages.remove(0));
        }
    }
}
