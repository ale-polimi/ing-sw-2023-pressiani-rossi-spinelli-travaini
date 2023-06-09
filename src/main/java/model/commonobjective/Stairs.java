package model.commonobjective;

import enumerations.ObjectColour;
import model.library.Library;
import view.cli.Colours;

public class Stairs extends CommonObjective {
    /**
     *this method will check that the objects are in a position which forms a stair
     * @param library is the personal library of the players
     * @param x is the row coordinate
     * @param y is the column coordinate
     */

    private final String description =" " + Colours.UNDERLINED + " " + Colours.RESET + "\n" +
            "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + " " + Colours.RESET + "         Five columns of increasing or decreasing\n" +
            "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + " " + Colours.RESET + "       height. Starting from the first column on\n" +
            "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET  + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + " " + Colours.RESET + "     the left or on the right, each next column\n" +
            "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET  + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + " " + Colours.RESET + "   must be made of exactly one more tile.\n" +
            "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET  + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|  Tiles can be of any type.";

    @Override
    public boolean applyObjectiveRules(Library library, int x, int y) {
        boolean returnValue = true;
        int firstTryRow = 1;
        int secondTryRow = 0;

        if(!isInDescendingOrder(library, firstTryRow)){
            if(!isInDescendingOrder(library, secondTryRow)){
                if(!isInAscendingOrder(library, firstTryRow)){
                    if(!isInAscendingOrder(library, secondTryRow)){
                        returnValue = false;
                    }
                }
            }
        }

        return returnValue;
    }

    /**
     * This method checks if the stairs are present in ascending order.
     * @param library is the library of the player.
     * @param startingRow is the starting row (0 or 1).
     * @return {@code true} if the stairs are in ascending order, {@code false} otherwise.
     */
    private boolean isInAscendingOrder(Library library, int startingRow) {
        boolean retValue = true;

        for(int row = startingRow; row < 5 + startingRow; row++){
            for(int col = 4; col >= 0; col--){
                if(4 - col + startingRow <= row){
                    if(library.getLibrarySpace(row,col).getObject().getObjectColour().equals(ObjectColour.EMPTY)){
                        retValue = false;
                        break;
                    }
                } else {
                    if(!library.getLibrarySpace(row,col).getObject().getObjectColour().equals(ObjectColour.EMPTY)){
                        retValue = false;
                        break;
                    }
                }
            }
        }

        return retValue;
    }

    /**
     * This method checks if the stairs are present in descending order.
     * @param library is the library of the player.
     * @param startingRow is the starting row (0 or 1).
     * @return {@code true} if the stairs are in descending order, {@code false} otherwise.
     */
    private boolean isInDescendingOrder(Library library, int startingRow){
        boolean retValue = true;

        for(int row = startingRow; row < 5 + startingRow; row++){
            for(int col = 0; col < 5; col++){
                if(col + startingRow <= row){
                    if(library.getLibrarySpace(row,col).getObject().getObjectColour().equals(ObjectColour.EMPTY)){
                        retValue = false;
                        break;
                    }
                } else {
                    if(!library.getLibrarySpace(row,col).getObject().getObjectColour().equals(ObjectColour.EMPTY)){
                        retValue = false;
                        break;
                    }
                }
            }
        }

        return retValue;
    }

    public String getDescription() {
        return description;
    }
}
