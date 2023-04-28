package network;

import java.util.ArrayList;

/**
 * Message which contains the coordinates of the object picked from the board.
 */
public class PickObjectMessage extends Message{
    private final ArrayList<Integer> coordinates;

    /**
     * Default contructor.
     * @param sender is the sender of the message.
     * @param x is the X coordinate of the object.
     * @param y is the Y coordinate of the object.
     */
    public PickObjectMessage(String sender, ArrayList<Integer> coordinates){
        super(sender, MessageType.PICK_OBJECT);
        this.coordinates = coordinates;
    }

    public ArrayList<Integer> getCoordinates() {
        return coordinates;
    }
}
