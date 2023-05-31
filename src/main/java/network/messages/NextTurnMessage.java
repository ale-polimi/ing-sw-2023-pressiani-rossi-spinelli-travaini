package network.messages;

/**
 * Message used for an update from the model.
 */
public class NextTurnMessage extends Message {
    public NextTurnMessage() {
        super("Model", MessageType.NEXT_TURN);
    }
}
