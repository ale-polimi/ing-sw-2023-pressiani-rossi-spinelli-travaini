package network.structure;

import network.Message;

public interface ClientHandler {
    boolean isConnected();
    void disconnect();

    void receivedMessage(Message message);
}
