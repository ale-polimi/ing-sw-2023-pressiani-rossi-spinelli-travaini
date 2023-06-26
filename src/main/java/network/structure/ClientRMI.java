package network.structure;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
import network.messages.*;
import observer.Observable;
import view.cli.Colours;

/**
 * this class represents an RMI client.
 */

public class ClientRMI extends Observable implements Client,Runnable,ClientHandler {

    private transient Server server;
    private String nickname;
    //default RMI port
    private final int port;
    private final String address;
    boolean getConnected =false;
    private final ArrayList<Message> messages =new ArrayList<>();
    transient ScheduledExecutorService timer;
    /**
     * Default constructor for the RMI client with the connection to the server.
     * @param address is the IP of the server.
     * @param port is the port for the connection.
     * @param clientController is the controller for the client.
     * @throws RemoteException when the server is not reachable.
     */
    public ClientRMI(String address, int port, ClientController clientController) throws RemoteException {
        try {
            System.setProperty("java.rmi.server.hostname", InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        this.address=address;
        this.port=port;
        //clientController.addObserver(this);
    }

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

    @Override
    public void closeConnection() throws RemoteException {
        server.disconnect(this);
        disconnect();
    }

    @Override
    public void sendMessage(Message message) {
        if(message.getType().equals(MessageType.USER_INFO))nickname = message.getSender();
        try {server.receiveMessage(message);}
        catch (RemoteException e) {
            notifyObserver(new ServerDisconnectedMessage());
        }
    }

    @Override
    public void receivedMessage(Message message)throws RemoteException{
        if(!message.getType().equals(MessageType.PING)) messages.add(message);
    }

    /**
     * This method registers an RMI client to a server.
     * @param server is the server that this client is connected to.
     * @throws RemoteException if the server is not reachable.
     */
    private void initialize(Server server) throws RemoteException {
        try {
            server.registry( (ClientHandler) UnicastRemoteObject.exportObject(this,0));
        } catch (RemoteException e) {
            e.printStackTrace();
            System.err.println("Unable to connect!");
            getConnected = false;
            System.exit(1);
        }
    }

    @Override
    public boolean isConnected() {return getConnected;}

    @Override
    public void disconnect() {
       getConnected = false;
        try {
            server.disconnect(this);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void ping() {
        timer.scheduleAtFixedRate(() -> {
            sendMessage(new PingMessage(nickname, MessageType.PING));
        }, 0, 5000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            if(messages.size()>0) notifyObserver(messages.remove(0));;
        }
    }
}
