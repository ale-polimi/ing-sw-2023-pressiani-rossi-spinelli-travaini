package network.messages;

import model.board.Board;
import model.library.Library;
import model.objects.ObjectCard;

import java.util.ArrayList;

/**
 * Message used each turn to send to the clients the state of the game.
 */
public class ShowTurnMessage extends Message{

    private final Board gameBoard;
    private final Library playerLibrary;
    private final ArrayList<ObjectCard> playerObjInHand;
    private final boolean[] completedCommonObjectives;

    /**
     * Constructor for abstract class Message.
     *
     * @param sender is the sender of the message.
     * @param gameBoard is the {@link Board board} of the game.
     * @param playerLibrary is the {@link Library library} of the player.
     * @param playerObjInHand is the array of ObjectsInHand of the player.
     * @param completedCommonObjectives is the array containing for each common objective if the player has completed it.
     */
    public ShowTurnMessage(String sender, Board gameBoard, Library playerLibrary, ArrayList<ObjectCard> playerObjInHand, boolean[] completedCommonObjectives) {
        super(sender, MessageType.SHOW_TURN);
        this.gameBoard = gameBoard;
        this.playerLibrary = playerLibrary;
        this.playerObjInHand = playerObjInHand;
        this.completedCommonObjectives = completedCommonObjectives;
    }

    /**
     * Getter method for the game board.
     * @return the game {@link Board board}.
     */
    public Board getGameBoard() {
        return gameBoard;
    }

    /**
     * Getter method for the player's library.
     * @return the {@link model.player.Player player}'s {@link Library}.
     */
    public Library getPlayerLibrary() {
        return playerLibrary;
    }

    /**
     * Getter method for the player's object in hand.
     * @return the {@link model.player.Player player}'s objects in hand.
     */
    public ArrayList<ObjectCard> getPlayerObjInHand() {
        return playerObjInHand;
    }

    /**
     * Getter method for the player's completed common objectives.
     * @return a boolean {@link java.lang.reflect.Array array} containing for each {@link model.commonobjective.CommonObjective common objective} if the player has completed it.
     */
    public boolean[] getCompletedCommonObjectives() {
        return completedCommonObjectives;
    }
}
