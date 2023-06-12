package network.messages;

public class ChatMessage  extends Message{

    private String text;
    /**
     *Custom constructor for ChatMessage class
     * @param sender      is the sender of the message.
     * @param text is the message the player wants to send in the chat
     */
    public ChatMessage(String sender, String text) {
        super(sender, MessageType.CHAT);
        this.text=text;
    }

    /**
     * Getter method for the text parameter
     * @return the String object in text parameter
     */
    public String getText(){return text;}
}
