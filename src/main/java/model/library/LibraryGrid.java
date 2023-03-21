package model.library;

import model.library.LibrarySpace;
import model.objects.ObjectCard;

public class LibraryGrid {
    protected LibrarySpace[][] libraryGrid = new LibrarySpace[6][5];

    /**
     *
     */
    public void addObject(ObjectCard objectCard, LibrarySpace librarySpace){
        librarySpace.putObject(objectCard);
    }

    public LibrarySpace[][] getLibraryGrid() {
        return libraryGrid;
    }
}
