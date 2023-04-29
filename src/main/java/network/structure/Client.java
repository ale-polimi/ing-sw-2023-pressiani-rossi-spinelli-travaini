package network.structure;

import network.listeners.GameListener;
import network.listeners.PlayerListener;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * this interface represents a generic client
 */

public interface Client extends Remote, GameListener {
    PlayerListener listener = null;

    /**
     * notify the client a change
     * @throws RemoteException
     */
    void update(/*TypeView typeViews, Type types*/) throws RemoteException;
}
