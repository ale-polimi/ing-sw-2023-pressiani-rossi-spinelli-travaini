package network.structure;

import network.messages.Message;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * This interface represents a generic client.
 */
public interface Client extends Remote{

    /**
     * This method makes the connection between client and server.
     * @throws IOException when the server is unreachable.
     */
    void connection() throws IOException;

    /**
     * This method closes the connection between a client and a server.
     * @throws IOException when the connection is already closed.
     */
    void closeConnection() throws IOException;

    /**
     * This method sends a {@link Message message} on the network.
     * @param message is the message to send.
     * @throws RemoteException when the server is not reachable.
     */
    void sendMessage(Message message)throws RemoteException;

    /**
     * This method receives a message from the network.
     * @param message is the received message.
     * @throws RemoteException if the server is not reachable.
     */
    void receivedMessage(Message message)throws RemoteException;

    /**
     * This method pings the server. It's used to constantly check the status of the connection.
     * @throws RemoteException if the server is not reachable.
     */
    void ping() throws RemoteException;

    /**
     * This method shows if the connection is still alive.
     * @return {@code true} if the connection is alive, {@code false} otherwise.
     * @throws RemoteException if the server is not reachable.
     */
    boolean isConnected() throws RemoteException;

    /**
     * This method disconnects the client.
     */
    void disconnect() throws RemoteException;


}
