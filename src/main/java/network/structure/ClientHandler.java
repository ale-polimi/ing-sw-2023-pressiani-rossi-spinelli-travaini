package network.structure;

import network.messages.Message;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientHandler extends Remote,Serializable{
    /**
     * check the connection between client and server
     * @return true if the connection is up
     */
    boolean isConnected()throws RemoteException;

    /**
     * disconnect the client from a server
     */
    void disconnect()throws RemoteException;

    /**
     * when called, it reads the message in input
     * @param message is the message that has to be read
     * @throws RemoteException if it is not possible to communicate with the client
     */
    void receivedMessage(Message message)throws RemoteException;
}
