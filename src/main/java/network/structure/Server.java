package network.structure;

import network.Message;
import java.rmi.RemoteException;

public interface Server {
    /**
     * Register a new client that has established a connection to the server
     * @param clientHandler the clientHandler of the client that tries to connect to the server
     * @throws RemoteException the server is unreachable
     */
    void registry(ClientHandler clientHandler) throws RemoteException;

    /**
     * when called, reads the message
     * @param message is the message that has to be read
     * @throws RemoteException when the server is unreachable
     */
    void receiveMessage(Message message) throws RemoteException;

    /**
     * Send a message to the clients
     * @param message The message that has to be forwarded
     */
    void sendMessage(Message message) throws RemoteException;

    /**
     * disconnects the client from the server
     */
    void disconnect();
}
