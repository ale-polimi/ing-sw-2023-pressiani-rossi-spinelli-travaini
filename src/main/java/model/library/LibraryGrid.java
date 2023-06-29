package model.library;

import model.library.LibrarySpace;
import model.objects.ObjectCard;

import java.io.Serializable;

/**
 * This class represents the grid inside the {@link Library library}.
 */
public class LibraryGrid implements Serializable {
    protected LibrarySpace[][] libraryGrid = new LibrarySpace[6][5];

    public LibraryGrid(){
        for(int i =0; i< 6; i++){
            for(int j = 0; j < 5; j++){
                libraryGrid[i][j] = new LibrarySpace();
            }
        }
    }
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
