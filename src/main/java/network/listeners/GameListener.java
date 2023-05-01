package network.listeners;

import model.board.BoardSpace;
import model.library.LibrarySpace;

import java.rmi.RemoteException;
import java.util.List;

public interface GameListener {

    /**
     * method that asks, using the UI, the nickname to the user and sends it to the server
     * @throws RemoteException when the message is null
     */
    void requestNickname() throws RemoteException;

    /**
     * method that asks, using the UI, the number of players in the game
     * @throws RemoteException when is not possible to send the message
     */
    void requestMaxPlayer() throws RemoteException;

    /**
     * method for asking the user which move will do in his turn on the board
     * @throws RemoteException when is not possible to send the message
     */
    void requestBoardMove(BoardSpace[][] board) throws RemoteException;

    /**
     * method for asking the user which move will do in his turn on his library and sends message to the server
     * @throws RemoteException when is not possible to send the message
     */
    void requestLibraryMove(LibrarySpace[][] library) throws RemoteException;

    /**
     * tells the user that he has to wait his turn and sends message to the server
     * @throws RemoteException when is not possible to send the message
     */

    void waitTurn() throws RemoteException;

    /**
     * tells the user who is the winner and sends the message to the server
     * @throws RemoteException when is not possible to send the message
     */
    void whoIsWinner(String winner) throws RemoteException;

    /**
     * Forward a message to the user
     * @param mesage It is the message that has to be forwarded
     * @throws RemoteException Threw when the client is unreachable
     */
    void generalMessage(String mesage) throws RemoteException;

    /**
     * Tells the user the current general information of the game
     * @param players It is a list of the players' nickname
     * @param board It is a representation of the game board
     * @param playersLibrary It is  a list of the players' library
     * @throws RemoteException Threw when the client isn't unreachable
     */
    void showMatchInfo(List<String> players, BoardSpace[][] board, List<LibrarySpace[][]> playersLibrary) throws RemoteException;
}
