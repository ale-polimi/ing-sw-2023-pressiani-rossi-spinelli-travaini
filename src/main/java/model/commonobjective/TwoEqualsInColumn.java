package model.commonobjective;

import model.library.Library;

public class TwoEqualsInColumn implements CommonObjective {

    @Override
    public boolean applyObjectiveRules(Library library,int x, int y) {

        return library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x+1, y ).getObject().getObjectColour());
    }
}
