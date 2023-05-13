package model.library;

import enumerations.ObjectColour;
import model.objects.ObjectCard;

public class Library extends LibraryGrid {

    /**
     * Custom constructor to initialize an empty library. Needed for the {@link view.cli.Cli CLI}.
     */
    public Library() {
        for(int row = 0; row < 6; row++){
            for(int col = 0; col < 5; col++){
                this.getLibrarySpace(row,col).putObject(new ObjectCard(ObjectColour.EMPTY));
            }
        }
    }

    /**
     * This method will add an object in the selected library space.
     * @param objectCard is the object card that will be added to the library.
     * @param librarySpace is the library space that will contain the object card.
     */
    public void addObject(ObjectCard objectCard, LibrarySpace librarySpace){
        librarySpace.putObject(objectCard);
    }

    /**
     * Getter method to return the object contained in a library space of this library.
     * @param librarySpace is the desired library space of this library.
     * @return the object contained in this library space using the {@link LibrarySpace#getObject() getObject() method} of the library space.
     */
    public ObjectCard getObject(LibrarySpace librarySpace){
        return librarySpace.getObject();
    }

    /**
     * This method will return the library space at the desired coordinates.
     * @param x is the X coordinate of the library.
     * @param y is the Y coordinate of the library.
     * @return the selected library space of this library.
     */
    public LibrarySpace getLibrarySpace(int x, int y){
            return libraryGrid[x][y];
    }
}
