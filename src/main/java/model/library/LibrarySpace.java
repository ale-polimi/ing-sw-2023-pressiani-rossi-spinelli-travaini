package model.library;

import model.objects.ObjectCard;

public class LibrarySpace {
    private ObjectCard objectContained;

    public LibrarySpace(){
        this.objectContained = null;
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
     * @param objectCard
     */
    public void putObject(ObjectCard objectCard){
        objectContained = objectCard;
    }

}
