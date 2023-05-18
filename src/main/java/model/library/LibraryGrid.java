package model.library;

import model.library.LibrarySpace;
import model.objects.ObjectCard;

import java.io.Serializable;

public class LibraryGrid implements Serializable {
    protected LibrarySpace[][] libraryGrid = new LibrarySpace[6][5];

    /**
     * Adds a card in a specified space
     * @param objectCard is the card we want to add to the library
     * @param librarySpace is the space where we want to aadd the card
     */
    public void addObject(ObjectCard objectCard, LibrarySpace librarySpace){
        librarySpace.putObject(objectCard);
    }

    /**
     *
     * @return the library grid of the caller
     */
    public LibrarySpace[][] getLibraryGrid() {
        return libraryGrid;
    }
}
