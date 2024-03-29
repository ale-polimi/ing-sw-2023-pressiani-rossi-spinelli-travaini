package model.commonobjective;

import enumerations.ObjectColour;
import model.library.Library;
import view.cli.Colours;

import java.util.ArrayList;
import java.util.HashSet;

public class TotalDifferentRows extends CommonObjective {


    private final String description = " " + Colours.UNDERLINED + " " + Colours.RESET + " " + Colours.UNDERLINED + " " + Colours.RESET + " " + Colours.UNDERLINED + " " + Colours.RESET + " " + Colours.UNDERLINED + " " + Colours.RESET + " " + Colours.UNDERLINED + " " + Colours.RESET + "   Two lines each formed by 5 different\n" +
            "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|  types of tiles. One line can show the\n" +
            "     x2      same or a different combination of the\n" +
            "             other line.";

   private int count=0;
    /**
     *this method will check the presence of at least two rows with all different objects
     * @param library is the personal library of the players
     * @param x is the row coordinate
     * @param y is the column coordinate
     */
    @Override
    public boolean applyObjectiveRules(Library library, int x, int y) {
        x=0;
        y=0;

        count = 0;
        ArrayList<ObjectColour> firstSet;
        boolean different=true;

        for (int row = 0; row < 6; row++) {
            different = true;
            firstSet = new ArrayList<>();
            for (int col = 0; col < 5 && different; col++) {
                if(library.getLibrarySpace(row,col).getObject().getObjectColour().equals(ObjectColour.EMPTY)){
                    different = false;
                } else {
                    firstSet.add(library.getLibrarySpace(row,col).getObject().getObjectColour());
                }
            }

            for(int i = 0; i < firstSet.size() - 1 && different; i++){
                for(int j = i + 1; j < firstSet.size() && different; j++){
                    if(firstSet.get(i).isEquals(firstSet.get(j))){
                        different = false;
                    }
                }
            }

            if(different){
                count++;
            }
        }

        if(count == 2){
            return true;
        }
        return false;
    }

    public String getDescription() {
        return description;
    }
}
