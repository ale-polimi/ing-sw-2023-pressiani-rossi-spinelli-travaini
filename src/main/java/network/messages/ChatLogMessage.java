package network.messages;

import java.util.ArrayList;

public class ChatLogMessage extends Message {
    private final ArrayList<ChatMessage> chatlog;
    private final boolean turnState;
    /**
     * Constructor for abstract class Message.
     *
     * @param sender  Is the sender of the message.
     * @param chatLog Is the payload of the message
     * @param turnState Indicates if the player was the player in turn when he sent the message
     */
    public ChatLogMessage(String sender, ArrayList<ChatMessage> chatLog,boolean turnState) {
        super(sender, MessageType.CHATLOG);
        this.chatlog = chatLog;
        this.turnState = turnState;
    }

    /**
     * Getter for the chatLog parameter
     * @return the chatLog parameter
     */
    public ArrayList<ChatMessage> getChatlog() {return chatlog;}

    /**
     * Getter method for the turnState parameter
     * @return the turnState parameter
     */
    public boolean getTurnState() {return turnState;}
}
