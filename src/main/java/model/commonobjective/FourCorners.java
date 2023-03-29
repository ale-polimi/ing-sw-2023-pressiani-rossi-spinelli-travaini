package model.commonobjective;

import model.library.Library;

public class FourCorners implements CommonObjective{
    @Override
    public boolean applyObjectiveRules(Library library, int x, int y) {
        x = 0;
        y = 0;
        return library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x, y + 4).getObject().getObjectColour()) &&
                library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x + 5, y).getObject().getObjectColour()) &&
                library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x + 5, y + 4).getObject().getObjectColour());
    }
}
