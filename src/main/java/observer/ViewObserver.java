package observer;

import view.View;

import java.util.ArrayList;
import java.util.Map;

/**
 * Observer interface used only for the {@link view.View view}.
 */
public interface ViewObserver {
    /**
     * This method will send the necessary info to open a socket with the server.
     * @param serverInfo is a {@link Map} that has the address as key and the IP address as value.
     */
    void onUpdateServerInfo(Map<String, String> serverInfo);

    /**
     * This method will send the username on the network.
     * @param nickname is the nickname passed by the {@link View}.
     */
    void onUpdateNickname(String nickname);

    /**
     * This method will send the maximum number of players for the game.
     * @param numOfPlayers is the number of players for the game.
     */
    void onMaxPlayers(int numOfPlayers);

    /**
     * This method will send the coordinates of the object cards the player wants to pick.
     * @param coordinatesToSend are the coordinates, stored as an {@link ArrayList}.
     */
    void onUpdateBoardMove(ArrayList<Integer> coordinatesToSend);

    /**
     * This method will send the order in which the picked cards will be put in the library.
     * It will send the column where the cards will be put as well.
     * @param orderAndColumnToSend are the order and the column, stored as an {@link ArrayList}.
     */
    void onUpdateLibraryMove(ArrayList<Integer> orderAndColumnToSend);

    /**
     * This method will be used by the {@link View} to show the common objectives on screen.
     * This method is particularly useful in the {@link view.cli.Cli CLI}.
     */
    void onRequestCommonObjectives();

    /**
     * This method will be used by the {@link View} to show the personal objective on screen.
     * This method is particularly useful in the {@link view.cli.Cli CLI}.
     */
    void onRequestPersonalObjective();
}
