package model.library;

import com.google.gson.Gson;
import enumerations.ObjectColour;
import model.board.Board;
import model.objects.ObjectCard;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Random;

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

    public PersonalObjective(){
        Random random = new Random();
        String[] possiblePatterns = {};
        Gson gson = new Gson();
        String json = possiblePatterns[random.nextInt(possiblePatterns.length)];
        this.librarygrid = gson.fromJson(json,LibrarySpace[][].class);
    }
}
