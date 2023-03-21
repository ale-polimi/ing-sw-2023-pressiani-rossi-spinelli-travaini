package model.library;

import model.objects.ObjectCard;

public class LibrarySpace {
    private ObjectCard objectContained;

    public LibrarySpace(){
        objectContained = null;
    }
    /**
     *
     * @param objectContained
     */
    public void setObjectContained(ObjectCard objectContained) {
        this.objectContained = objectContained;
    }

    /**
     *
     * @return
     */
    public ObjectCard getObjectContained() {
        return objectContained;
    }
}
