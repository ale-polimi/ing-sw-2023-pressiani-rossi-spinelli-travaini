package model.library;

import com.google.gson.Gson;
import enumerations.ObjectColour;
import model.board.Board;
import model.objects.ObjectCard;

import java.util.ArrayList;
import java.util.Random;

import static enumerations.ObjectColour.*;

public final class PersonalObjective extends LibraryGrid {

    /**
     * Constructor of the PersonalObjectivew class
     * @param json is the string containing the parameters of the personal objective
     */
    public PersonalObjective(String json){
        this.libraryGrid = new LibrarySpace[6][5];
        for(int i = 0; i < 6; i++){
            String[] tmp = json.split(",");
            libraryGrid[Integer.parseInt(tmp[3*i])][Integer.parseInt(tmp[3*i+1])]= new LibrarySpace();
            libraryGrid[Integer.parseInt(tmp[3*i])][Integer.parseInt(tmp[3*i+1])].putObject(new ObjectCard(tmp[3*i+2]));
        }
    }

    /**
     *Counts the completion status of the personal objective
     * @param library is the library to compare with the personal objective
     * @return the points assigned by the personal objective
     */
    public int compareTo(Library library){
        int count = 0;
        for(int i = 0; i < 6; i++){
            for(int j =  0; j < 5; j++){
                if(library.getLibraryGrid()[i][j]!= null && libraryGrid[i][j]!=null){
                    if(libraryGrid[i][j].getObject().getObjectColour().equals(library.getLibraryGrid()[i][j].getObject().getObjectColour())){count++;}
                }
            }
        }
        return switch(count){
            case 1 -> 1;
            case 2 -> 2;
            case 3 ->4;
            case 4 ->6;
            case 5 -> 9;
            case 6 -> 12;
            default -> 0;
        };
    }
}
