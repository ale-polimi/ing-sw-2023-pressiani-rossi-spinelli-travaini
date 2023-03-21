package model.library;

import model.objects.ObjectCard;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Library extends LibraryGrid {

        public void addObject(ObjectCard objectCard, LibrarySpace librarySpace){
            librarySpace.putObject(objectCard);
    }
}
