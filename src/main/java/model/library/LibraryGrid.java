package model.library;

import model.library.LibrarySpace;

public class LibraryGrid {
    protected LibrarySpace[][] libraryGrid;

    /**
     *
     */
    public LibraryGrid(){
        libraryGrid = new LibrarySpace[6][5];
    }

    public LibrarySpace[][] getLibraryGrid() {
        return libraryGrid;
    }
}
