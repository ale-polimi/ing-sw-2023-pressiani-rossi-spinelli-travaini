package view;

import model.board.Board;
import model.board.BoardSpace;
import model.commonobjective.CommonObjective;
import model.library.Library;
import model.library.LibrarySpace;
import model.library.PersonalObjective;
import model.objects.ObjectCard;
import network.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This interface will be implemented by both the {@link view.cli.Cli CLI} and the {@link GUI}.
 * It's also implemented in the {@link network.structure.NetworkView networkview}.
 */
public interface View {

    /**
     * Ask to insert the player's nickname
     */
    public void askNickname();

    /**
     * Ask to insert the maximum number of players for the match
     */
    public void askMaxPlayer();

    /**
     * Ask to insert a move regarding the board
     */
    public void askBoardMove();

    /**
     * Ask to insert a move regarding the library
     */
    public void askLibraryMove();

    /**
     * This method will show a generic error.
     * @param player is the recipient of the error.
     * @param payload is the error's description.
     */
    void showGenericError(String player, String payload);

    /**
     * This method will show the current turn of the game, whether it's for the player in turn or not. It will be up to
     * the client to {@link controller.ClientController#update(Message) check if it's for him}.
     * @param player is the player currently playing.
     * @param gameBoard is the board of the game.
     * @param playerLibrary is the player's library.
     * @param playerObjInHand are the objects the player currently has in hand.
     */
    void showTurn(String player, Board gameBoard, Library playerLibrary, ArrayList<ObjectCard> playerObjInHand);

    /**
     * This method will send the {@link CommonObjective common objectives} to the player.
     * @param player is the
     * @param commonObjective1
     * @param commonObjective2
     */
    void showCommonObjectives(String player, CommonObjective commonObjective1, CommonObjective commonObjective2);

    void showPersonalObjective(String player, PersonalObjective personalObjective);
    void showLobby(ArrayList<String> players);
    void showWinner(String winner, HashMap<String, Integer> leaderboard);
    void showNotMyTurn(Board gameBoard);
}
