package network.structure;

import network.listeners.PlayerListener;
import java.rmi.RemoteException;

public interface Server extends PlayerListener {
    /**
     * Register a new client that has established a connection to the server
     * @param client the client that tries to connect to the server
     * @throws RemoteException the server is unreachable3
     */
    void registry(Client client) throws RemoteException;
}
