package network.structure;

import network.Message;
import observer.Observer;

import java.io.IOException;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

/**
 * this interface represents a generic client
 */

public interface Client extends Remote, ClientHandler{

    void connection() throws IOException;

    void closeConnection() throws IOException;

    void sendMessage(Message message);

    void ping();
}
