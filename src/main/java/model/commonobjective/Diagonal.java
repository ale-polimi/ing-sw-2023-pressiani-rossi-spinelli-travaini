package model.commonobjective;

import enumerations.ObjectColour;
import model.library.Library;
import view.cli.Colours;

public class Diagonal extends CommonObjective {

    private final String description= " " + Colours.UNDERLINED + " " + Colours.RESET + " \n" +
            "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + " " + Colours.RESET + " \n" +
            "  |" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + " " + Colours.RESET + " \n" +
            "    |" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + " " + Colours.RESET + "     Five tiles of the same type forming a diagonal.\n" +
            "      |" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + " " + Colours.RESET + " \n" +
            "        |" + Colours.UNDERLINED + "■" + Colours.RESET + "|";

    /**
     * This method will check the presence of at least one diagonal with all equal objects (they have the same colour)
     * @param library is the personal library of the players
     * @param x is the row coordinate
     * @param y is the column coordinate
     */
    @Override
    public boolean applyObjectiveRules(Library library, int x, int y) {
        boolean returnValue = true;
        boolean foundDiagonal = true;

        int rowFirstTry = 1;
        int rowSecondTry = 0;

        ObjectColour firstTryColour = library.getLibrarySpace(rowFirstTry,0).getObject().getObjectColour();
        ObjectColour secondTryColour = library.getLibrarySpace(rowSecondTry,0).getObject().getObjectColour();

        if(firstTryColour.equals(ObjectColour.EMPTY)){
            returnValue = false;
        } else {

            for(int col = 1; col < 5; col++){
                if(!library.getLibrarySpace(rowFirstTry + col,col).getObject().getObjectColour().isEquals(firstTryColour)){
                    foundDiagonal = false;
                }
            }

            if (foundDiagonal == false) {
                if (secondTryColour.equals(ObjectColour.EMPTY)) {
                    returnValue = false;
                } else {
                    for (int col = 1; col < 5; col++) {
                        if (!library.getLibrarySpace(rowSecondTry + col, col).getObject().getObjectColour().isEquals(secondTryColour)) {
                            returnValue = false;
                        }
                    }
                }
            }
        }

        return returnValue;
    }

    public String getDescription() {
        return description;
    }
}
