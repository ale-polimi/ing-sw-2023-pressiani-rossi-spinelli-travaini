package network.structure;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import controller.ClientController;
import network.Message;
import network.MessageType;
import network.PingMessage;
import observer.Observable;

/**
 * this class represents an RMI client.
 */

public class ClientRMI extends Observable implements Client{

    Server server;

    //default RMI port
    private int port;
    String address;
    boolean getConnected =false;

    ClientController clientController;

    ScheduledExecutorService timer;

    /**
     * is the constructor with the connection with the server
     * @param address is the IP client
     * @param port is the port where the connection is
     */
    public ClientRMI(String address, int port) throws RemoteException {
        this.address=address;
        this.port=port;

    }

    /**
     *  Connection with the server
     *  @throws RemoteException when the registry is not found
     */
    @Override
    public void connection() throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(port);
            server = (ServerRMI) registry.lookup("server");
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
        server.disconnect();
        disconnect();
    }

    /**
     * Forwards a message
     * @param message is the message to forward
     * @throws RemoteException when the server is unreachable
     */
    @Override
    public void sendMessage(Message message) throws RemoteException{

        server.receiveMessage(message);
        //notifyObserver(new Message("client", message.getType()));

    }


    /**
     * Receive a message
     * @param message is the sent message
     */
    public void receivedMessage(Message message) {
        clientController.update(message);
    }

    /**
     * method for register a client to a game
     * @param server is the server which the client connects to for starting the game
     * @throws RemoteException if the connection is not possible
     */

    private void initialize(Server server) throws RemoteException {
            try {
                server.registry( this);
            } catch (RemoteException e) {
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
    }
    @Override
    public void ping() {

        timer.scheduleAtFixedRate(() -> {
            try {
                sendMessage(new PingMessage("client", MessageType.PING));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);

    }
}
