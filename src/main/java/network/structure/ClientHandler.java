package network.structure;

import network.Message;

public interface ClientHandler {
    /**
     * check the connection between client and server
     * @return
     */
    boolean isConnected();

    /**
     * disconnect the client from a server
     */
    void disconnect();

    /**
     * when called, it reads the message in input
     * @param message is the message that has to be read
     */
    void receivedMessage(Message message);
}
