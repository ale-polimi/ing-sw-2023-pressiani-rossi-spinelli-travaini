package controller;

import exceptions.controller.NotEnoughSpaceException;
import exceptions.player.TooManyObjectsInHandException;
import model.Game;
import model.objects.ObjectCard;
import model.player.Player;

public class Controller {

    private Game game;
    private Player player;


    public Controller(){
        this.game = new Game();
        this.player = new Player(username);
    }

    /**
     * The player will select the card to pick from the board one by one
     * @param coordX is the X coordinate of the card on the board.
     * @param coordY is the Y coordinate of the card on the board.
     * @return will let the player know the cards are correctly picked up from the board.
     */
    public boolean pickObjectFromBoard(int coordX, int coordY){
        if(game.getBoard().getSpace(coordX, coordY).getObject() != null){
            try {
                player.addToObjectsInHand(game.getBoard().getSpace(coordX, coordY).getObject());
            } catch (TooManyObjectsInHandException e){
                /* Not implemented yet */
            }
            game.getBoard().getSpace(coordX, coordY).removeObject();
            return true;
        }

        return false;
    }

    /**
     * The player can select in which order the object cards have to be put in the specified column
     * @param cardID1 is the first card to be put in the specified column.
     * @param cardID2 is the second card to be put in the specified column.
     * @param cardID3 is the third card to be put in the specified column.
     * @param column is the player specified column.
     * @return true if the cards are correctly put in the library.
     * @throws NotEnoughSpaceException if the chosen column does not have enough space for the desired cards.
     */
    public boolean addObjectToLibrary(int cardID1, int cardID2, int cardID3, int column) throws NotEnoughSpaceException{
        ObjectCard objectCard1 = player.getObjectInHand(cardID1);
        ObjectCard objectCard2 = player.getObjectInHand(cardID2);
        ObjectCard objectCard3 = player.getObjectInHand(cardID3);
        int firstEmpty = 0;

        for(int i = 5; i >= 0; i--){
            if(player.getLibrary().getLibraryGrid()[i][column].getObject() == null){
                firstEmpty = i;
                break;
            }
        }
        if(!((firstEmpty - 1 < 0) || (firstEmpty - 2 < 0))){
            player.getLibrary().getLibraryGrid()[firstEmpty][column].putObject(objectCard1);
            player.getLibrary().getLibraryGrid()[firstEmpty - 1][column].putObject(objectCard2);
            player.getLibrary().getLibraryGrid()[firstEmpty - 2][column].putObject(objectCard3);
            return true;
        } else {

            throw new NotEnoughSpaceException(column);
        }
    }
}
