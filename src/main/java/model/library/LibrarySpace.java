package model.library;

import enumerations.ObjectColour;
import model.objects.ObjectCard;

import java.io.Serial;
import java.io.Serializable;

/**
 * This class represents the single space (called library space) inside the {@link Library}. The library space will contain one {@link ObjectCard object}.
 */
public class LibrarySpace implements Serializable {
    private ObjectCard objectContained;
    private int group = 0;
    private boolean visited = false;

    /**
     * Constructor of the LibrarySpace class
     */
    public LibrarySpace(){
        this.objectContained = new ObjectCard(ObjectColour.EMPTY);
    }

    /**
     * Getter method that returns the object contained in this library space.
     * @return the object in this library space.
     */
    public ObjectCard getObject(){
        return objectContained;
    }

    /**
     *Put an objectCard in the librarySpace
     * @param objectCard is the object that will be put in this library space.
     */
    public void putObject(ObjectCard objectCard){
        objectContained = objectCard;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getGroup() {
        return group;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
