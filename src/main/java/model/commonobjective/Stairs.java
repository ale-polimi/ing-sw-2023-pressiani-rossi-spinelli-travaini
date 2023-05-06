package model.commonobjective;

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
        for (int i = x; i < x+5 ; i++) {
            for (int j = 0; j < i+1; j++) {
                if (library.getLibrarySpace(i,j).getObject() == null)
                    return false;
            }
        }

        if ((x==1 && library.getLibrarySpace(0,0).getObject()==null) || x==0 ) {
            for (int i = x, j = y; i < 4 + x; j++, i++) {
                if (library.getLibrarySpace(i, j + 1).getObject()!= null)
                    return false;
            }
        }

        return true;
    }

    public String getDescription() {
        return description;
    }
}
