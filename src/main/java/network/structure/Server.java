package network.structure;

import network.Message;
import network.listeners.PlayerListener;
import java.rmi.RemoteException;

public interface Server {
    /**
     * Register a new client that has established a connection to the server
     * @param clientHandler the clientHandler of the client that tries to connect to the server
     * @throws RemoteException the server is unreachable3
     */
    void registry(ClientHandler clientHandler) throws RemoteException;
    void receiveMessage(Message message) throws RemoteException;
    void sendMessage(Message message) throws RemoteException;
    void disconnect();
}
