package model.board;

import enumerations.TypeSpace;
import model.objects.ObjectCard;

public class BoardSpace {
    private TypeSpace typeSpace;
    private ObjectCard objectContained;

    public BoardSpace(TypeSpace typespace){
        this.typeSpace = typespace;
        this.objectContained = null;
    }

    public TypeSpace getTypeSpace() {
        return typeSpace;
    }
    public ObjectCard getObject(){
        return objectContained;
    }
    public void removeObject(){
        objectContained = null;
    }
    public void insertObject(ObjectCard objectCard){
        objectContained = objectCard;
    }
}
