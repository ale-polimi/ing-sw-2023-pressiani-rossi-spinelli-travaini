package model.commonobjective;

import enumerations.ObjectColour;
import model.library.Library;
import view.cli.Colours;

public class FiveX extends CommonObjective {
    /**
     *this method will check the presence of at least five objects of the same colour, that form an x
     * @param library is the personal library of the players
     * @param x is the row coordinate
     * @param y is the column coordinate
     */

    private final String description= " " + Colours.UNDERLINED + " " + Colours.RESET + "   "  + Colours.UNDERLINED + " " + Colours.RESET + " \n" +
            "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + " "+ Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|\n" +
            " " + Colours.UNDERLINED + " " + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + " " + Colours.RESET + "    Five tiles of the same type forming an X.\n" +
            "|" + Colours.UNDERLINED + "■" + Colours.RESET + "| |" + Colours.UNDERLINED + "■" + Colours.RESET + "|";
    @Override
    public boolean applyObjectiveRules(Library library, int x, int y) {


        for ( x = 1; x < 5; x++) {
            for ( y = 1; y < 4; y++) {

                ObjectColour colour= library.getLibrarySpace(x,y).getObject().getObjectColour();
                if(!colour.equals(ObjectColour.EMPTY)){
                    if (library.getLibrarySpace(x-1,y-1).getObject().getObjectColour().isEquals(colour) &&
                            library.getLibrarySpace(x-1,y+1).getObject().getObjectColour().isEquals(colour)&&
                            library.getLibrarySpace(x+1,y-1).getObject().getObjectColour().isEquals(colour)&&
                            library.getLibrarySpace(x+1,y+1).getObject().getObjectColour().isEquals(colour)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public String getDescription() {
        return description;
    }
}
