package model.commonobjective;

import enumerations.ObjectColour;
import model.library.Library;
import view.cli.Colours;

public class FourCorners extends CommonObjective {
    private final String description = " " + Colours.UNDERLINED + " " + Colours.RESET + " " + Colours.UNDERLINED + " " + Colours.RESET + " " + Colours.UNDERLINED + " " + Colours.RESET + " " + Colours.UNDERLINED + " " + Colours.RESET + " " + Colours.UNDERLINED + " " + Colours.RESET + " \n" +
            "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|     |" + Colours.UNDERLINED + "■" + Colours.RESET + "|\n" +
            "|         |\n" +
            "|         |  Four tiles of the same type in the four\n" +
            "|         |  corners of the bookshelf.\n" +
            "|" + Colours.UNDERLINED + " " + Colours.RESET + "       " + Colours.UNDERLINED + " " + Colours.RESET + "|\n" +
            "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + " " + Colours.RESET + " " + Colours.UNDERLINED + " " + Colours.RESET + " " + Colours.UNDERLINED + " " + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|";

    /**
     * This method will check if the four spaces placed in the four corners of the library are the same colour
     * @param library is the personal library of the players
     * @param x is the row coordinate
     * @param y is the column coordinate
     */
    @Override
    public boolean applyObjectiveRules(Library library, int x, int y) {
        x = 0;
        y = 0;
        if(library.getLibrarySpace(x, y).getObject().getObjectColour().equals(ObjectColour.EMPTY) || library.getLibrarySpace(x, y + 4).getObject().getObjectColour().equals(ObjectColour.EMPTY) || library.getLibrarySpace(x + 5, y).getObject().getObjectColour().equals(ObjectColour.EMPTY) || library.getLibrarySpace(x + 5, y + 4).getObject().getObjectColour().equals(ObjectColour.EMPTY)){
            return false;
        } else {
            return library.getLibrarySpace(x, y).getObject().getObjectColour().isEquals(library.getLibrarySpace(x, y + 4).getObject().getObjectColour()) &&
                    library.getLibrarySpace(x, y).getObject().getObjectColour().isEquals(library.getLibrarySpace(x + 5, y).getObject().getObjectColour()) &&
                    library.getLibrarySpace(x, y).getObject().getObjectColour().isEquals(library.getLibrarySpace(x + 5, y + 4).getObject().getObjectColour());
        }
    }

    public String getDescription() {
        return description;
    }
}
