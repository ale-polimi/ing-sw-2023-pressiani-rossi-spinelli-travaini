package network.messages;

/**
 * Message used for an update from the model.
 */
public class AddedPlayerMessage extends Message {
    public AddedPlayerMessage() {
        super("Controller", MessageType.ADDED_PLAYER);
    }
}
