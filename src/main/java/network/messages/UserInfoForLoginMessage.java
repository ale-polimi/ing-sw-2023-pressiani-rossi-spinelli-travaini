package network.messages;

/**
 * Message that contains the information of the player (username) for the login.
 */
public class UserInfoForLoginMessage extends Message{
    private final String username;
    public UserInfoForLoginMessage(String sender, String username){
        super(sender, MessageType.USER_INFO);
        this.username = username;
    }

    /**
     * Getter method to return the payload of the message.
     * @return the payload as a String object.
     */
    public String getUsername(){
        return this.username;
    }
}
