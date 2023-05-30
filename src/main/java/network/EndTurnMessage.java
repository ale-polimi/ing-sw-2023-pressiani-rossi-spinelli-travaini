package network;

public class EndTurnMessage extends Message {
    public EndTurnMessage() {
        super("Model", MessageType.END_TURN);
    }
}
