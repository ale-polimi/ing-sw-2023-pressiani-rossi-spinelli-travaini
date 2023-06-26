package network.structure;

import network.messages.Message;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface used for the server to represent a client.
 */
public interface ClientHandler extends Remote,Serializable{

    /**
     * This method checks the connection between the client and the server.
     * @return {@code true} if the connection is alive, {@code false} otherwise.
     */
    boolean isConnected()throws RemoteException;

    /**
     * This method disconnects the client.
     */
    void disconnect()throws RemoteException;

    /**
     * This method receives a message from the network.
     * @param message is the received message.
     * @throws RemoteException if the server is not reachable.
     */
    void receivedMessage(Message message)throws RemoteException;
}
