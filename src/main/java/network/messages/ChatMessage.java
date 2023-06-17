package network.messages;

public class ChatMessage  extends Message{

    private final String text;
    private final String receiver;
    /**
     *Custom constructor for ChatMessage class
     * @param sender      is the sender of the message.
     * @param text is the message the player wants to send in the chat
     */
    public ChatMessage(String sender, String receiver,String text) {
        super(sender, MessageType.CHAT);
        this.text=text;
        this.receiver = receiver;
    }

    /**
     * Getter method for the text parameter
     * @return the String object in text parameter
     */
    public String getText(){return text;}

    /**
     * Getter method for the chat message receiver
     * @return the String object in receiver parameter
     */
    public String getDest() {return receiver;}
}
