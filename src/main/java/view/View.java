package view;

import model.board.Board;
import model.board.BoardSpace;
import model.commonobjective.CommonObjective;
import model.library.Library;
import model.library.LibrarySpace;
import model.library.PersonalObjective;
import model.objects.ObjectCard;

import java.util.ArrayList;
import java.util.List;

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
     * Show a message from the game
     * @param message It is the message that has to be showed
     */
    public void generalMessage(String message);

    /**
     * Show the winner of the game
     * @param winner It is the nicname of the winner
     */
    public void endGame(String winner);

    /**
     * Show the current game information
     * @param players It is a list of the players' nicknames
     * @param board It is the current board
     * @param playerLibrary It is the player's library
     */
    public void showMatchInfo(List<String> players, Board board, Library playerLibrary, ArrayList<CommonObjective> commonObjective, PersonalObjective personalObjective);

    /**
     * Tells the player it isn't his/her turn
     */
    public void waitTurn();

    void showGenericError(String payload);

    void showTurn(String player, Board gameBoard, Library playerLibrary, ArrayList<ObjectCard> playerObjInHand);

    void showCommonObjectives(CommonObjective commonObjective1, CommonObjective commonObjective2);

    void showPersonalObjective(PersonalObjective personalObjective);
    void showLobby(ArrayList<String> players);
}
