package network;

/**
 * Message used to ask the player for a move on the board.
 */
public class AskBoardMoveMessage extends Message{
    /**
     * Constructor for class AskBoardMoveMessage.
     * @param sender is the sender of the message.
     */
    public AskBoardMoveMessage(String sender) {
        super(sender, MessageType.ASK_BOARD_MOVE);
    }
}
