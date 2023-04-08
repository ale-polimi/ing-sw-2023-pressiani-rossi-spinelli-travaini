package network;

public class PickObjectMessage extends Message{
    public PickObjectMessage(int x, int y){
        super("","".concat(String.valueOf(x)).concat(String.valueOf(y)), MessageType.PICK_OBJECT);
    }
}
