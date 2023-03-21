package model.board;

import enumerations.TypeSpace;
import model.Game;
import model.objects.ObjectCard;

import java.util.ArrayList;

public class Board {
    private model.board.BoardSpace[][] boardSpaces;

    /**
     *
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
            }
        }
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public BoardSpace getSpace(int x, int y){
        return boardSpaces[x][y];
    }

    /**
     *
     * @param boardSpace
     * @return
     */
    public ObjectCard pickupObjectFrom(BoardSpace boardSpace){
        ObjectCard objectPicked = boardSpace.getObject();
        boardSpace.removeObject();
        return objectPicked;
    }

    /**
     *
     * @param boardSpace
     * @param objectCard
     */
    public void putObjectIn(BoardSpace boardSpace, ObjectCard objectCard){
        boardSpace.insertObject(objectCard);
    }
}
