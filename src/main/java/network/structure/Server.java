package network.structure;

import network.messages.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This class represents the server.
 */
public interface Server extends Remote{

    /**
     * This method registers a new client that has established a connection to the server.
     * @param clientHandler is the {@link ClientHandler} of the client that tries to connect to the server.
     * @throws RemoteException the server is unreachable
     */
    void registry(ClientHandler clientHandler) throws RemoteException;

    /**
     * This method receives a message from the network.
     * @param message is the received message.
     * @throws RemoteException if the server is not reachable.
     */
    void receiveMessage(Message message) throws RemoteException;

    /**
     * This method sends a {@link Message message} on the network.
     * @param message is the message to send.
     * @throws RemoteException when the server is not reachable.
     */
    void sendMessage(Message message) throws RemoteException;

    /**
     * This method disconnects a {@link ClientHandler client} from this server.
     * @param clientHandler is the client that's disconnected.
     * @throws RemoteException when the client already disconnected from this server.
     */
    void disconnect(ClientHandler clientHandler)throws RemoteException;

    /**
     * This method pings the clients. It's used to constantly check the status of the connection.
     * @throws RemoteException if the client is not reachable.
     */
    void ping() throws RemoteException;
}
