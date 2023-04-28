package network;

import model.board.Board;

public class ShowBoardMessage extends Message{
    private final Board gameBoard;

    public ShowBoardMessage(String sender, Board gameBoard) {
        super(sender, MessageType.SHOW_BOARD);
        this.gameBoard = gameBoard;
    }

    /**
     * Getter method to return the board of the game.
     * @return the board of the game.
     */
    public Board getGameBoard() {
        return gameBoard;
    }
}
