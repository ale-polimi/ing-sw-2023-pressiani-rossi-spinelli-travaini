package network;

import java.util.ArrayList;

/**
 * This message shows the lobby while the players are connecting.
 */
public class ShowLobbyMessage extends Message {
    ArrayList<String> players;

    /**
     * Default constructor.
     * @param players are the players currently connected to the game.
     */
    public ShowLobbyMessage(ArrayList<String> players) {
        super("Controller", MessageType.SHOW_LOBBY);
        this.players = players;
    }

    /**
     * Getter method to return the currently connected players.
     * @return the players.
     */
    public ArrayList<String> getLobbyPlayers() {
        return players;
    }
}
