package model.library;

import model.objects.ObjectCard;
import model.library.LibrarySpace;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Library extends LibraryGrid {
    /**
     *
     * @param objectCard
     */
        public void addObject(ObjectCard objectCard, LibrarySpace librarySpace){
            librarySpace.putObject(objectCard);
    }
    public LibrarySpace getLibrarySpace(int x, int y){
            return libraryGrid[x][y];
    }
}
