package network;

public class GenericErrorMessage extends Message{
    public GenericErrorMessage(String errorMessage){
        super("Controller", errorMessage, MessageType.GENERIC_ERROR);
    }
}
