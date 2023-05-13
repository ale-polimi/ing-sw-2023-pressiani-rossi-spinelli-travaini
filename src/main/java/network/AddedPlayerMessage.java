package network;

public class AddedPlayerMessage extends Message {
    public AddedPlayerMessage() {
        super("Model", MessageType.ADDED_PLAYER);
    }
}
