package network;

/**
 * Message containing the info for the game closure.
 */
public class GameClosedMessage extends Message{

    /**
     * Constructor for  class GameClosedMessage.
     * @param sender      is the sender of the message.
     * @param messageType is the type of the message.
     */
    public GameClosedMessage(String sender, MessageType messageType) {
        super(sender, messageType);
    }
}

