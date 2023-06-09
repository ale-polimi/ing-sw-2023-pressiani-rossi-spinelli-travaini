package network.structure;

import network.messages.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote{
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
     * Disconnect the player from the server
     * @param clientHandler The clientHandler which is unreachable
     * @throws RemoteException
     */
    void disconnect(ClientHandler clientHandler)throws RemoteException;

    void ping() throws RemoteException;
}
