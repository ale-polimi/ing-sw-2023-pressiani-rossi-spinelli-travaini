package model.library;

import model.objects.ObjectCard;

public class LibrarySpace {
    private ObjectCard objectContained;

    public LibrarySpace(){
        this.objectContained = null;
    }

    /**
     * Getter method that returns the object contained in this library space.
     * @return the object in this library space.
     */
    public ObjectCard getObject(){
        return objectContained;
    }

    /**
     *
     * @param objectCard is the object that will be put in this library space.
     */
    public void putObject(ObjectCard objectCard){
        objectContained = objectCard;
    }

}
