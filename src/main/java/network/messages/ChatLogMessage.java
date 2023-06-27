package network.messages;

import java.util.ArrayList;

public class ChatLogMessage extends Message {
    private final ArrayList<ChatMessage> chatlog;
    private final boolean turnState;
    /**
     * Constructor for abstract class Message.
     *
     * @param sender  is the sender of the message.
     * @param chatLog is the payload of the message
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
