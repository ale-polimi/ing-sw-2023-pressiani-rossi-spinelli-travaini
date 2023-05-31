package model.board;

import enumerations.TypeSpace;
import model.objects.ObjectCard;
import observer.Observable;

import java.io.Serializable;

public class BoardSpace extends Observable implements Serializable {
    private TypeSpace typeSpace;
    private ObjectCard objectContained;

    /**
     * Constructor method. The tile will have the required typespace and will be empty.
     * @param typespace is the type of the board space. There are multiple {@link TypeSpace typespaces}.
     */
    public BoardSpace(TypeSpace typespace){
        this.typeSpace = typespace;
        this.objectContained = null;
    }

    /**
     *
     * @return the type of the tile.
     */
    public TypeSpace getTypeSpace() {
        return typeSpace;
    }

    /**
     *
     * @return the object card contained in the tile.
     */
    public ObjectCard getObject(){
        return objectContained;
    }

    /**
     *Set to null the parameter objectContained. Useful for {@link Board#pickupObjectFrom(BoardSpace) pickupObjectFrom} method
     */
    public void removeObject(){
        objectContained = null;
        /* notifyObserver(new GenericModelChangeMessage()); */
    }

    /**
     * This method allows you to put an object in this tile. Useful for {@link Board#putObjectIn(BoardSpace, ObjectCard) putObjectIn} method.
     * @param objectCard is the object card that needs to be put in the tile.
     */
    public void insertObject(ObjectCard objectCard){
        objectContained = objectCard;
    }
}
