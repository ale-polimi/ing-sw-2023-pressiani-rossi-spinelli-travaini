package model.library;

import model.objects.ObjectCard;

public class LibrarySpace {
    private ObjectCard objectContained;

    public LibrarySpace(){
        this.objectContained = null;
    }
    public ObjectCard getObject(){
        return objectContained;
    }

    public void putObject(ObjectCard objectCard){
        objectContained = objectCard;
    }

}
