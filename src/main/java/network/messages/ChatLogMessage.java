package network.messages;

import java.util.ArrayList;

public class ChatLogMessage extends Message {
    private final ArrayList<ChatMessage> chatlog;
    /**
     * Constructor for abstract class Message.
     *
     * @param sender  is the sender of the message.
     * @param chatLog is the payload of the message
     */
    public ChatLogMessage(String sender, ArrayList<ChatMessage> chatLog) {
        super(sender, MessageType.CHATLOG);
        this.chatlog = chatLog;
    }

    /**
     * Getter for the chatLog parameter
     * @return the chatLog parameter
     */
    public ArrayList<ChatMessage> getChatlog() {return chatlog;}
}
