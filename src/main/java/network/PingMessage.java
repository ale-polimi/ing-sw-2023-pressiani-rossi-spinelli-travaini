package network;

/**
 * Message used to ping the server and keep the connection alive.
 */
public class PingMessage  extends Message{
    /**
     *Custom constructor for class PingMessage
     * @param sender      is the sender of the message.
     * @param messageType is the type of the message.
     */
    public PingMessage(String sender, MessageType messageType) {
        super(sender, messageType);
    }
}

