package model.commonobjective;

import enumerations.ObjectColour;
import model.library.Library;
import view.cli.Colours;

public class EightEquals extends CommonObjective {

   private int count=0;

    private final String description = "   " + Colours.UNDERLINED + " " + Colours.RESET + "   "  + Colours.UNDERLINED + " " + Colours.RESET + " \n" +
            " " + Colours.UNDERLINED + " " + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + " "+ Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + " " + Colours.RESET + "   Eight tiles of the same type. There’s no\n" +
            "|" + Colours.UNDERLINED + "■" + Colours.RESET + "| |" + Colours.UNDERLINED + "■" + Colours.RESET + "| |" + Colours.UNDERLINED + "■" + Colours.RESET + "|  restriction about the position of these\n" +
            "|" + Colours.UNDERLINED + "■" + Colours.RESET + "| |" + Colours.UNDERLINED + "■" + Colours.RESET + "| |" + Colours.UNDERLINED + "■" + Colours.RESET + "|  tiles.";


    /**
     * This method will check the presence of at least eight equal objects (they have the same colour)
     * @param library is the personal library of the players
     * @param x is the row coordinate
     * @param y is the column coordinate
     */
    @Override
    public boolean applyObjectiveRules(Library library, int x, int y) {
        x=0;
        y=0;

        for (ObjectColour o: ObjectColour.values()) {
            if(!o.equals(ObjectColour.EMPTY)) {
                count = 0;
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (library.getLibrarySpace(i, j) != null) {
                            if (library.getLibrarySpace(i, j).getObject().getObjectColour().isEquals(o)) {
                                count++;
                            }
                            if (count == 8)
                                return true;
                        }
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
