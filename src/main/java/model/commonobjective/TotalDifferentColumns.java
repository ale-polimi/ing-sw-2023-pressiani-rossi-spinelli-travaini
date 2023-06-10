package model.commonobjective;

import model.library.Library;
import view.cli.Colours;

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

        for (int k = 0; k < 5; k++) {
            for (int i = 0; i < 4 && different; i++) {
                for (int j = i+1; j < 5; j++) {
                    if (library.getLibrarySpace(i, k).getObject().getObjectColour().isEquals(library.getLibrarySpace(j, k).getObject().getObjectColour())) {
                        different = false;
                        break;
                    }
                }
            }
            if (different)
                count++;
            if (count == 2)
                return true;

        }
        return false;
    }
     /** this method return the result of the count in the method applyObjectiveRules*/
    public int getCount() {
        return count;
    }

    public String getDescription() {
        return description;
    }
}
