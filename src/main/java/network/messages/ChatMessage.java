package network.messages;

public class ChatMessage  extends Message{

    private final String text;
    private final String receiver;
    private final boolean turnState;
    /**
     *Custom constructor for ChatMessage class
     * @param sender      Is the sender of the message.
     * @param receiver    Is the receiver of the message
     * @param text is the message the player wants to send in the chat
     * @param turnState Indicates if the player was the player in turn when he sent the message
     */
    public ChatMessage(String sender, String receiver,String text,boolean turnState) {
        super(sender, MessageType.CHAT);
        this.text=text;
        this.receiver = receiver;
        this.turnState= turnState;
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

    /**
     * Getter method for the turnState parameter
     * @return the turnState parameter
     */
    public boolean getTurnState() {return turnState;}
}
