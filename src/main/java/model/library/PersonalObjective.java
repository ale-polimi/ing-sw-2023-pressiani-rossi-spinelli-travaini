package model.library;

import enumerations.ObjectColour;
import model.objects.ObjectCard;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

import static enumerations.ObjectColour.GREEN;

public final class PersonalObjective extends LibraryGrid {
    public PersonalObjective(){
        librarygrid = new LibrarySpace[6][5];
        ArrayList<ObjectCard> randomObjects = new ArrayList<ObjectCard>();

        /* Creates an array of one type of object in each space */
        for (ObjectColour objectColour: ObjectColour.values()) {
            randomObjects.add(new ObjectCard(objectColour));
        }

        for(int k = 0; k < 6; k++){
            /* Create random i, create random j */
            /* Create 6 objects of different types */

            librarygrid[x][y].addObject(randomObjects[i]);
        }

    }
    public void addObject(ObjectCard objectCard){
        throw new NotImplementedException();
    }

}
