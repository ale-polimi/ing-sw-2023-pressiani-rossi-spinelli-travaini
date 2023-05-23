package network;

import java.util.HashMap;

/**
 * Message containing the winner of the game and the leaderboard.
 */
public class EndGameMessage extends Message{
    private String winner;
    HashMap<String, Integer> playersPoints;

    /**
     * Constructor for class EndGameMessage
     * @param winner is the winner of the game.
     */
    public EndGameMessage(String winner, HashMap<String, Integer> playersPoints) {
        super("Controller", MessageType.END_GAME);
        this.winner = winner;
        this.playersPoints = playersPoints;
    }

    /**
     * Getter for the EndGameMessage
     * @return The winner of the game
     */
    public String getWinner() {return winner;}

    /**
     * Getter method to return the leaderboard.
     * @return the leaderboard as a {@link HashMap} with usernames as keys and the points as values.
     */
    public HashMap<String, Integer> getPlayersPoints() {
        return playersPoints;
    }
}

