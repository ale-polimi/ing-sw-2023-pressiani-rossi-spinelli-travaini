package network;

public class AskNicknameMessage extends Message{
    /**
     * Constructor for class  AskNicknameMessage.
     * @param sender      is the sender of the message.
     */
    public AskNicknameMessage(String sender) {
        super(sender, MessageType.ASK_NICKNAME);
    }
}
