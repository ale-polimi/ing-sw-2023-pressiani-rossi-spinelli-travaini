package network.messages;

public class GenericModelChangeMessage extends Message {
    public GenericModelChangeMessage() {
        super("Model", MessageType.GENERIC_MODEL_CHANGE);
    }
}
