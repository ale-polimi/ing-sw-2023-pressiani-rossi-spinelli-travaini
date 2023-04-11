package network;

public class MaxPlayersMessage extends Message{
    public MaxPlayersMessage(int maxPlayers){
        super("",String.valueOf(maxPlayers), MessageType.MAX_PLAYERS_FOR_GAME);
    }
}
