package view;

import model.board.Board;
import model.commonobjective.CommonObjective;
import model.library.Library;
import model.library.PersonalObjective;
import model.objects.ObjectCard;
import network.messages.Message;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This interface will be implemented by both the {@link view.cli.Cli CLI} and the {@link view.gui.Gui GUI}.
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
     *
     * @param player                         is the player currently playing.
     * @param gameBoard                      is the board of the game.
     * @param playerLibrary                  is the player's library.
     * @param playerObjInHand                are the objects the player currently has in hand.
     * @param completedCommonObjectives      is the array of points for each completed common objective.
     * @param availableCommonObjectivePoints is the array of available points for each common objective.
     */
    void showTurn(String player, Board gameBoard, Library playerLibrary, ArrayList<ObjectCard> playerObjInHand, int[] completedCommonObjectives, int[] availableCommonObjectivePoints);

    /**
     * This method will show the {@link CommonObjective common objectives} to the player.
     *
     * @param player                         is the player currently playing.
     * @param commonObjective1               is the first common objective.
     * @param commonObjective2               is the second common objective.
     * @param completedCommonObjectives
     * @param availableCommonObjectivePoints
     */
    void showCommonObjectives(String player, CommonObjective commonObjective1, CommonObjective commonObjective2, int[] completedCommonObjectives, int[] availableCommonObjectivePoints);

    /**
     * This method will show the {@link PersonalObjective personal objective} to the player.
     * @param player is the player currently playing.
     * @param personalObjective is the player's personal objective.
     */
    void showPersonalObjective(String player, PersonalObjective personalObjective);

    /**
     * This method will show the lobby while all the players are connecting to the game.
     * @param players is an {@link ArrayList} containing the usernames of the currently connected players.
     */
    void showLobby(ArrayList<String> players);

    /**
     * This method will show the winner and the leaderboard when the game ends.
     * @param winner is the username of the winner.
     * @param leaderboard is a {@link HashMap} containing the pairs (username, points).
     */
    void showWinner(String winner, HashMap<String, Integer> leaderboard);

    /**
     * This method will show the board when the player currently in its turn.
     * @param gameBoard is the board of the game.
     */
    void showNotMyTurn(Board gameBoard);

    /**
     * This method will show the other players' library to the player in turn.
     * @param sender is the sender of the message.
     * @param librariesOfPlayers is the {@link HashMap} containing the other players' usernames as keys and libraries as values.
     */
    void showOthersLibrary(String sender, HashMap<String, Library> librariesOfPlayers);

    /**
     * This method will show the chat message
     * @param sender the sender of the message
     * @param isPrivate indicate if the message is private
     * @param message is the message text
     */
    void showChat(String sender,boolean isPrivate,String message);

    /**
     * Return if the player is the player in turn
     * @return true if the player is the player in turn
     */
    boolean getMyTurn();

    /**
     * Set the myTurn parameter
     * @param turn is the new value of the myTurn parameter
     */
    void setMyTurn(boolean turn);

    /**
     * Handle the chat service when the player is not in turn
     */
    void askChat();

    /**
     * Return the value of the chatAbilitator parameter
     * @return true if the chat service is still enabled
     */
    boolean getChatAbilitator();

    /**
     * Set the value of the chatAbilitator Parameter
     */
    void setChatAbilitator(boolean value);
}
