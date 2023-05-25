package model.board;

import enumerations.ObjectColour;
import enumerations.TypeSpace;
import model.Game;
import model.objects.ObjectCard;
import network.GenericModelChangeMessage;
import network.Message;
import observer.Observable;
import observer.Observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;

public class Board extends Observable implements Serializable, Observer {
    private BoardSpace[][] boardSpaces;
    PropertyChangeListener listener;

    /**
     *Board class custom constructor
     */
    public Board(){
        boardSpaces = new BoardSpace[9][9];
        for(int i = 0; i <= 8; i++){
            for(int j = 0; j <= 8; j++){
                if((i == 0 && j == 4) || (i == 1 && j == 5) || (i == 3 && j == 1) || (i == 4 && j == 0) || (i == 4 && j == 8) || (i == 5 && j == 7) || (i == 7 && j == 3) || (i == 8 && j == 4)){
                    boardSpaces[i][j] = new BoardSpace(TypeSpace.FOR_FOUR_PLAYERS);
                }else if((i == 0 && j == 3) || (i == 2 && j == 2) || (i == 2 && j == 6) || (i == 3 && j == 8) || (i == 5 && j == 0) || (i == 6 && j == 2) || (i == 6 && j == 6) || (i == 8 && j == 5)){
                    boardSpaces[i][j] = new BoardSpace(TypeSpace.FOR_THREE_PLAYERS);
                } else if((i == 1 && (j >= 3 && j <= 4)) || (i == 2 && (j >= 3 && j <= 5)) || (i == 3 && (j >= 2 && j <= 7)) || (i == 4 && (j >= 1 && j <= 7)) || (i == 5 && (j >= 1 && j <= 6)) || (i == 6 && (j >= 3 && j <= 5)) || (i == 7 && (j >= 4 && j <= 5))){
                    boardSpaces[i][j] = new BoardSpace(TypeSpace.FOR_TWO_PLAYERS);
                } else {
                    boardSpaces[i][j] = new BoardSpace(TypeSpace.UNUSABLE);
                }
                /* Subscribing to the observable boardSpaces */
                //boardSpaces[i][j].addObserver(this);
            }
        }
    }

    /**
     *Returns the specified space in the board
     * @param x is the X coordinate of the tile in the board.
     * @param y is the Y coordinate of the tile in the board.
     * @return the tile at the requested (X,Y) coordinates.
     */
    public BoardSpace getSpace(int x, int y){
        return boardSpaces[x][y];
    }

    /**
     * This method allows players to pick up an object from a tile of the board.
     * @param boardSpace is the tile where the player wants to pick up an object card from.
     * @return the object contained in the tile.
     */
    public ObjectCard pickupObjectFrom(BoardSpace boardSpace){
        ObjectCard objectPicked = boardSpace.getObject();
        boardSpace.removeObject();
        return objectPicked;
    }

    /**
     * This method allows you to put an object in the specified tile.
     * @param boardSpace is the tile the server wants to put the object card in.
     * @param objectCard is the object card that will be in the tile.
     */
    public void putObjectIn(BoardSpace boardSpace, ObjectCard objectCard) {
        boardSpace.insertObject(objectCard);
        //notifyObserver(new GenericModelChangeMessage());
    }

    /**
     * This method is a checker used in {@link controller.Controller#pickObjectFromBoard(int, int) the pick object method} in the controller.
     * @param row is the row of the object.
     * @param column is the column of the object.
     * @return {@code true} if the space is surrounded by other objects, {@code false} otherwise.
     */
    public boolean isSpaceSurrounded(int row, int column) {
        if(row == 0){
            if((getSpace(row + 1, column).getObject() != null) && (getSpace(row, column + 1).getObject() != null) && (getSpace(row, column - 1).getObject() != null)){
                return true;
            } else {
                return false;
            }
        } else  if (row == 8){
            if((getSpace(row - 1, column).getObject() != null) && (getSpace(row, column + 1).getObject() != null) && (getSpace(row, column - 1).getObject() != null)){
                return true;
            } else {
                return false;
            }
        } else  if (column == 0){
            if((getSpace(row - 1, column).getObject() != null) && (getSpace(row + 1, column).getObject() != null) && (getSpace(row, column + 1).getObject() != null)){
                return true;
            } else {
                return false;
            }
        } else  if (column == 8){
            if((getSpace(row - 1, column).getObject() != null) && (getSpace(row + 1, column).getObject() != null) && (getSpace(row, column - 1).getObject() != null)){
                return true;
            } else {
                return false;
            }
        } else {
            if((getSpace(row - 1, column).getObject() != null) && (getSpace(row + 1, column).getObject() != null) && (getSpace(row, column - 1).getObject() != null) && (getSpace(row, column + 1).getObject() != null)){
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public void update(Message message) {
        System.out.println(this.getClass().toString() + ": I have been notified!");
        notifyObserver(message);
    }
}
