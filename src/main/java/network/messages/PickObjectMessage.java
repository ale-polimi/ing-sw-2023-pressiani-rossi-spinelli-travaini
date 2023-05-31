package network.messages;

import java.util.ArrayList;

/**
 * Message which contains the coordinates of the object picked from the board.
 */
public class PickObjectMessage extends Message{
    private final ArrayList<Integer> coordinates;

    /**
     * Default contructor.
     * @param sender is the sender of the message.
     * @param coordinates are the coordinates sent as an {@link ArrayList}
     */
    public PickObjectMessage(String sender, ArrayList<Integer> coordinates){
        super(sender, MessageType.PICK_OBJECT);
        this.coordinates = coordinates;
    }

    public ArrayList<Integer> getCoordinates() {
        return coordinates;
    }
}
