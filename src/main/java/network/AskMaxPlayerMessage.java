package network;

public class AskMaxPlayerMessage extends Message{

    /**
     * Constructor for class AskMaxPlayerMessage.
     * @param sender      is the sender of the message.
     */
    public AskMaxPlayerMessage(String sender) {
        super(sender, MessageType.ASK_MAX_PLAYER);
    }
}
