package model.library;

import model.objects.ObjectCard;

public class LibrarySpace {
    private ObjectCard objectContained;
    private int group = 0;
    private boolean visited = false;

    /**
     * Constructor of the LibrarySpace class
     */
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
