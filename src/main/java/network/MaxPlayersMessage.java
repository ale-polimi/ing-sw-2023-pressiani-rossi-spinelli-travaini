package network;

/**
 * Message which contains the maximum number of players for the game.
 */
public class MaxPlayersMessage extends Message{
    private final int players;

    /**
     * Default constructor.
     * @param sender is the sender of the message.
     * @param maxPlayers is the number of players for the game.
     */
    public MaxPlayersMessage(String sender, int maxPlayers){
        super(sender, MessageType.MAX_PLAYERS_FOR_GAME);
        this.players = maxPlayers;
    }

    /**
     * Getter method to return the number of players for the game.
     * @return the number of players for the game.
     */
    public int getPlayers() {
        return players;
    }
}
