package network;

import controller.Controller;

/**
 * Abstract message class which must be extended by each message type.
 * Both server and clients will communicate using this generic type of message.
 * This avoids the usage of the "instance of" primitive.
 */
public abstract class Message {

    private final String sender;
    private final MessageType messageType;

    /**
     * Constructor for abstract class Message.
     * @param sender is the sender of the message.
     * @param messageType is the type of the message.
     */
    public Message(String sender, MessageType messageType){
        this.sender = sender;
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
     * Getter method to return the type of the message.
     * @return the type as a MessageType object.
     */
    public MessageType getType(){
        return this.messageType;
    }
}
