package network.messages;

/**
 * Message that contains an error message.
 */
public class BoardErrorMessage extends Message{
    private final String errorMessage;

    /**
     * Default constructor.
     * @param sender is the sender of the message.
     * @param errorMessage is the error message.
     */
    public BoardErrorMessage(String sender, String errorMessage){
        super(sender, MessageType.BOARD_ERROR);
        this.errorMessage = errorMessage;
    }

    /**
     * Getter method to return the payload of the message.
     * @return the payload as a String object.
     */
    public String getPayload(){
        return this.errorMessage;
    }
}