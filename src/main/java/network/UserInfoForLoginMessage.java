package network;

public class UserInfoForLoginMessage extends Message{
    public UserInfoForLoginMessage(String username){
        super("",username, MessageType.USER_INFO);
    }
}
