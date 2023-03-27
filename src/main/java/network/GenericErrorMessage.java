package network;

public class GenericErrorMessage extends Message{
    public GenericErrorMessage(String errorMessage){
        super(errorMessage, MessageType.GENERIC_ERROR);
    }
}
