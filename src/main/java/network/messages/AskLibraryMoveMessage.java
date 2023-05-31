package network.messages;

/**
 * Message used to ask the player for a move in the library.
 */
public class AskLibraryMoveMessage extends Message{
    /**
     * Constructor for class AskLibraryMoveMessage.
     * @param sender is the sender of the message.
     */
    public AskLibraryMoveMessage(String sender) {
        super(sender, MessageType.ASK_LIBRARY_MOVE);
    }
}
