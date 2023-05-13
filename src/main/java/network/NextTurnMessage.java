package network;

public class NextTurnMessage extends Message {
    public NextTurnMessage() {
        super("Model", MessageType.NEXT_TURN);
    }
}
