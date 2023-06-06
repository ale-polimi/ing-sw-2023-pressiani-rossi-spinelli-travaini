package network.structure;

import network.messages.Message;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * this interface represents a generic client
 */

public interface Client extends Remote, ClientHandler{

    /**
     * makes the connection between client and server
     * @throws IOException when the server is unreachable
     */
    void connection() throws IOException;

    /**
     * closes the connection between client and server
     * @throws IOException when the connection is already closed
     */
    void closeConnection() throws IOException;

    /**
     * forwards a message
     * @param message is the sending message
     */
    void sendMessage(Message message)throws RemoteException;

    void receivedMessage(Message message)throws RemoteException;

    /**
     * check the presence of problems in the connection between client and server
     */
    void ping() throws RemoteException;
}
