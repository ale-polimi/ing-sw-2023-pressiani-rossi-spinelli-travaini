package view;

import model.board.BoardSpace;
import model.library.LibrarySpace;

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
     * @param board It is the current game board
     */
    public void askBoardMove(BoardSpace[][] board);

    /**
     * Ask to insert a move regarding the library
     * @param library It is the current player's library
     */
    public void askLibraryMove(LibrarySpace[][] library);

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
     * @param playersLibrary It is a list of the current players' library
     */
    public void showMatchInfo(List<String> players, BoardSpace[][] board, List<LibrarySpace[][]> playersLibrary);

    /**
     * Tells the player it isn't his/her turn
     */
    public void waitTurn();
}
