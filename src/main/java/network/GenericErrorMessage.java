package network;

/**
 * Message that contains an error message.
 */
public class GenericErrorMessage extends Message{

    private final String errorMessage;

    /**
     * Default constructor.
     * @param sender is the sender of the message.
     * @param errorMessage is the error message.
     */
    public GenericErrorMessage(String sender, String errorMessage){
        super(sender, MessageType.GENERIC_ERROR);
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
