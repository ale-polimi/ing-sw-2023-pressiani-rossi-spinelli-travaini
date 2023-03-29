package network;

public abstract class Message {

    private final String sender;
    private final String payload;
    private final MessageType messageType;

    /**
     * Constructor for abstract class Message.
     * @param payload is the payload of the message.
     * @param messageType is the type of the message.
     */
    public Message(String sender, String payload, MessageType messageType){
        this.sender = sender;
        this.payload = payload;
        this.messageType = messageType;
    }

    /**
     * Getter method to return the sender of the message.
     * @return the sender as a String object.
     */
    public String getSender(){
        return this.sender;
    }

    /**
     * Getter method to return the payload of the message.
     * @return the payload as a String object.
     */
    public String getPayload(){
        return this.payload;
    }

    /**
     * Getter method to return the type of the message.
     * @return the type as a MessageType object.
     */
    public MessageType getType(){
        return this.messageType;
    }
}
