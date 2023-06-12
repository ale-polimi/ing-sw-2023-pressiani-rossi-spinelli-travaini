package network.messages;

public class ClientDisconnectedMessage extends Message {
    String disconnectionError = "Connection closed, terminating the application...";

    public ClientDisconnectedMessage() {
        super("Client", MessageType.CLIENT_DISCONNECT);
    }

    public String getDisconnectionError() {
        return disconnectionError;
    }
}
