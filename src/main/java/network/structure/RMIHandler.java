package network.structure;

import network.messages.Message;
import network.messages.MessageType;
import network.messages.PingMessage;

import java.rmi.RemoteException;

public class RMIHandler implements ClientHandler,Runnable{
    private final Client client;
    private final ServerRMI server;

    /**
     * Custom constructor of RMIHandler class
     * @param client It is the client to handle
     * @param server It is the server who is handling the connection
     */
    public RMIHandler(Client client, ServerRMI server){
        this.client = client;
        this.server = server;
    }

    /**
     * Check that a client is still connected on the network
     * @return true if the client is reachable, false otherwise
     */
    @Override
    public boolean isConnected() {
        try {
            client.receivedMessage(new PingMessage("Server", MessageType.PING));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return true;
    }


    /**
     * disconnect the server
     */
    @Override
    public void disconnect() {server.disconnect();}

    /**
     * Send the message from the server to the client
     * @param message It is the message that has to be forwarded
     */
    @Override
    public void receivedMessage(Message message) {
        try {
            client.receivedMessage(message);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (!Thread.interrupted()){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
