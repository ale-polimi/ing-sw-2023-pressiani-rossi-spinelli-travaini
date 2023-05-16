package network.structure;

import network.Message;
import observer.Observable;
import observer.Observer;

import java.io.IOException;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

/**
 * this interface represents a generic client
 */

public interface Client extends Remote, ClientHandler{

    final List<Observer> observers = new ArrayList<>();
    public default void addObserver(Observer obs) {
        observers.add(obs);
    }
    void connection() throws IOException;

    void closeConnection() throws IOException;

    void sendMessage(Message message) throws IOException;
}
