import network.structure.Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StartServer extends Remote {
    Server connect() throws RemoteException;
}
