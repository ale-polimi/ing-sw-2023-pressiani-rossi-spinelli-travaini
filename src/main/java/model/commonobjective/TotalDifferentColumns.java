package model.commonobjective;

import enumerations.ObjectColour;
import model.library.Library;
import view.cli.Colours;

import java.util.HashSet;

public class TotalDifferentColumns extends CommonObjective {

    private int count=0;

    private final String description = " " + Colours.UNDERLINED + " " + Colours.RESET + "\n" +
            "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|\n" +
            "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|\n" +
            "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|  Two columns each formed by 6\n" +
            "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|  different types of tiles.\n" +
            "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|\n" +
            "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|\n" +
            " x2";

    /**
     *this method will check the presence of at least two columns with all different objects
     * @param library is the personal library of the players
     * @param x is the row coordinate
     * @param y is the column coordinate
     */
    @Override
    public boolean applyObjectiveRules(Library library, int x, int y) {

        boolean different=true;

        for (int col = 0; col < 5; col++) {
            different = true;
            for (int row = 0; row < 5 && different; row++) {
                for(int otherrow = row + 1; otherrow < 6 && different; otherrow++){
                    if(library.getLibrarySpace(row, col).getObject().getObjectColour().isEquals(library.getLibrarySpace(otherrow, col).getObject().getObjectColour()) || library.getLibrarySpace(otherrow, col).getObject().getObjectColour().equals(ObjectColour.EMPTY)){
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
