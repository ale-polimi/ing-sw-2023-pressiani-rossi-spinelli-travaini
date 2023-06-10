package model.commonobjective;

import model.library.Library;
import view.cli.Colours;

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

        boolean different=true;

        for (int k = 0; k < 6; k++) {
            for (int i = 0; i < 4 && different; i++) {
                for (int j = i+1; j < 5; j++) {
                    if (library.getLibrarySpace(k, i).getObject().getObjectColour().isEquals(library.getLibrarySpace(k, j).getObject().getObjectColour())) {
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

    public int getCount(){
        return count;
    }
    public String getDescription() {
        return description;
    }



}
