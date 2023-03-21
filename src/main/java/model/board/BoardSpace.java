package model.board;

import enumerations.TypeSpace;
import model.objects.ObjectCard;

public class BoardSpace {
    private TypeSpace typeSpace;
    private ObjectCard objectContained;

    /**
     *
     * @param typespace
     */
    public BoardSpace(TypeSpace typespace){
        this.typeSpace = typespace;
        this.objectContained = null;
    }

    /**
     *
     * @return
     */
    public TypeSpace getTypeSpace() {
        return typeSpace;
    }

    /**
     *
     * @return
     */
    public ObjectCard getObject(){
        return objectContained;
    }

    /**
     *
     */
    public void removeObject(){
        objectContained = null;
    }

    /**
     *
     * @param objectCard
     */
    public void insertObject(ObjectCard objectCard){
        objectContained = objectCard;
    }
}
