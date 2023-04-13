package model.commonobjective;

import model.library.Library;

public class FourCorners extends CommonObjective {
    private final String description = "Four tiles of the same type in the four corners of the bookshelf. ";

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
        return library.getLibrarySpace(x, y).getObject().getObjectColour().isEquals(library.getLibrarySpace(x, y + 4).getObject().getObjectColour()) &&
                library.getLibrarySpace(x, y).getObject().getObjectColour().isEquals(library.getLibrarySpace(x + 5, y).getObject().getObjectColour()) &&
                library.getLibrarySpace(x, y).getObject().getObjectColour().isEquals(library.getLibrarySpace(x + 5, y + 4).getObject().getObjectColour());
    }

    public String getDescription() {
        return description;
    }
}
