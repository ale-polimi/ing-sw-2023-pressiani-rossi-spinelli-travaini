package network.messages;

import java.util.ArrayList;

/**
 * Message that contains the order in which the cards the player currently has will be put in the library.
 */
public class PutObjectInLibraryMessage extends Message {
    private final ArrayList<Integer> orderArray;

    /**
     * Default constructor.
     * @param sender is the sender of the message.
     * @param orderArray is the order of the cards as an {@link ArrayList ArrayList}.
     */
    public PutObjectInLibraryMessage(String sender, ArrayList<Integer> orderArray){
        super(sender, MessageType.PUT_OBJECT);
        this.orderArray = orderArray;
    }

    /**
     * Getter method to return the order in which the cards will be put in player's library.
     * @return the order of the cards as an {@link ArrayList ArrayList}.
     */
    public ArrayList<Integer> getOrderArray(){
        return this.orderArray;
    }
}
