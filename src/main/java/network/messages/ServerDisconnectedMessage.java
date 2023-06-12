package network.messages;

public class ServerDisconnectedMessage extends Message {
    String disconnectionError = "Server not reachable, terminating the application...";

    public ServerDisconnectedMessage() {
        super("Client", MessageType.SERVER_DISCONNECT);
    }

    public String getDisconnectionError() {
        return disconnectionError;
    }
}
