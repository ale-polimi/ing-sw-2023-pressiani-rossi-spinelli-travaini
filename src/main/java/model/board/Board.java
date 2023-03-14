package model.board;

import model.objects.ObjectCard;

import java.util.ArrayList;

public class Board {
    private BoardSpace[][] boardSpaces;

    public BoardSpace getSpace(int x, int y){
        return boardSpaces[x][y];
    }

    public ObjectCard pickupObjectFrom(BoardSpace boardSpace){
        ObjectCard objectPicked = boardSpace.getObject();
        boardSpace.removeObject();
        return objectPicked;
    }

    public void putObjectIn(BoardSpace boardSpace, ObjectCard objectCard){
        boardSpace.insertObject(objectCard);
    }
}
