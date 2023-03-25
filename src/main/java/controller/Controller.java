package controller;

import enumerations.GameState;
import exceptions.controller.IncompatibleStateException;
import exceptions.controller.NotEnoughSpaceException;
import exceptions.player.TooManyObjectsInHandException;
import model.Game;
import model.objects.ObjectCard;
import model.player.Player;
import network.Message;

import static enumerations.PlayerState.*;
import static enumerations.GameState.*;
import static java.lang.System.out;
import static network.MessageType.*;

public class Controller {

    private Game game;

    private Player player;


    /**
     * Constructor for the controller.
     */
    public Controller(){
        this.game = new Game();
        game.setGameState(LOGIN);
    }

    public void onMessageReceived(Message receivedMessage){
        switch (game.getGameState()){
            case LOGIN ->
                if(receivedMessage.getType().equals(MAX_PLAYERS_FOR_GAME)){
                    game.setMaxPlayers(Integer.parseInt(receivedMessage.getPayload()));
                } else if(receivedMessage.getType().equals(USER_INFO)){
                    game.addToGame(new Player(receivedMessage.getPayload()));
                }
                break;
            case START ->
                ;
                break;
            case IN_GAME ->
                if(receivedMessage.getType().equals(PICK_OBJECT)){
                    pickObjectFromBoard(Character.getNumericValue(receivedMessage.getPayload().charAt(0)), Character.getNumericValue(receivedMessage.getPayload().charAt(1)));
                } else if(receivedMessage.getType().equals(PUT_OBJECT)){
                    addObjectToLibrary(receivedMessage.getPayload());
                }
                break;
            case END ->
                ;
                break;
            default ->
                out.println("Invalid game state.");
        }
    }


    /**
     * The player will select the card to pick from the board one by one
     * @param coordX is the X coordinate of the card on the board.
     * @param coordY is the Y coordinate of the card on the board.
     * @throws IncompatibleStateException if the player is not in PICKUP state.
     */
    private void pickObjectFromBoard(int coordX, int coordY) throws IncompatibleStateException{
        if(player.getPlayerState().equals(PICKUP)) {
            if (game.getBoard().getSpace(coordX, coordY).getObject() != null) {
                try {
                    player.addToObjectsInHand(game.getBoard().getSpace(coordX, coordY).getObject());
                } catch (TooManyObjectsInHandException e) {
                    /* Not implemented yet */
                }
                game.getBoard().getSpace(coordX, coordY).removeObject();
            }
        } else {
            throw new IncompatibleStateException(PICKUP, player.getPlayerState());
        }
    }

    /**
     * The player can select in which order the object cards have to be put in the specified column
     * @param cardID1 is the first card to be put in the specified column.
     * @param cardID2 is the second card to be put in the specified column.
     * @param cardID3 is the third card to be put in the specified column.
     * @param column is the player specified column.
     * @return true if the cards are correctly put in the library.
     * @throws IncompatibleStateException if the player is not in IN_LIBRARY state.
     * @throws NotEnoughSpaceException if the chosen column does not have enough space for the desired cards.
     */
    private boolean addObjectToLibrary(int cardID1, int cardID2, int cardID3, int column) throws IncompatibleStateException, NotEnoughSpaceException{
        if(player.getPlayerState().equals(IN_LIBRARY)) {
            ObjectCard objectCard1 = player.getObjectInHand(cardID1);
            ObjectCard objectCard2 = player.getObjectInHand(cardID2);
            ObjectCard objectCard3 = player.getObjectInHand(cardID3);
            int firstEmpty = 0;

            for (int i = 5; i >= 0; i--) {
                if (player.getLibrary().getObject(player.getLibrary().getLibrarySpace(i, column)) == null) {
                    firstEmpty = i;
                    break;
                }
            }
            if (!((firstEmpty - 1 < 0) || (firstEmpty - 2 < 0))) {
                player.getLibrary().addObject(objectCard1, player.getLibrary().getLibrarySpace(firstEmpty, column));
                player.getLibrary().addObject(objectCard2, player.getLibrary().getLibrarySpace(firstEmpty - 1, column));
                player.getLibrary().addObject(objectCard3, player.getLibrary().getLibrarySpace(firstEmpty - 2 , column));
                return true;
            } else {
                throw new NotEnoughSpaceException(column);
            }
        } else {
            throw new IncompatibleStateException(IN_LIBRARY, player.getPlayerState());
        }
    }
}
