package network;

import model.board.Board;
import model.library.Library;
import model.objects.ObjectCard;

import java.util.ArrayList;

public class ShowTurnMessage extends Message{

    private final Board gameBoard;
    private final Library playerLibrary;
    private final ArrayList<ObjectCard> playerObjInHand;

    /**
     * Constructor for abstract class Message.
     *
     * @param sender      is the sender of the message.
     * @param messageType is the type of the message.
     */
    public ShowTurnMessage(String sender, Board gameBoard, Library playerLibrary, ArrayList<ObjectCard> playerObjInHand) {
        super(sender, MessageType.SHOW_TURN);
        this.gameBoard = gameBoard;
        this.playerLibrary = playerLibrary;
        this.playerObjInHand = playerObjInHand;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public Library getPlayerLibrary() {
        return playerLibrary;
    }

    public ArrayList<ObjectCard> getPlayerObjInHand() {
        return playerObjInHand;
    }
}
