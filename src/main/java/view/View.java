package view;

import model.board.Board;
import model.board.BoardSpace;
import model.commonobjective.CommonObjective;
import model.library.Library;
import model.library.LibrarySpace;
import model.library.PersonalObjective;
import model.objects.ObjectCard;

import java.util.ArrayList;
import java.util.HashMap;
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

    void showGenericError(String player, String payload);

    void showTurn(String player, Board gameBoard, Library playerLibrary, ArrayList<ObjectCard> playerObjInHand);

    void showCommonObjectives(String player, CommonObjective commonObjective1, CommonObjective commonObjective2);

    void showPersonalObjective(String player, PersonalObjective personalObjective);
    void showLobby(ArrayList<String> players);
    void showNotMyTurn(Board gameBoard);
    void showWinner(String winner, HashMap<String, Integer> leaderboard);
}
