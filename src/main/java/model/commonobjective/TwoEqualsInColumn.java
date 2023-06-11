package model.commonobjective;

import model.library.Library;

public class TwoEqualsInColumn extends CommonObjective {

    /**
     * This method will check the presence of two spaces in column containing objects of the same colour
     * @param library is the personal library of the players
     * @param x is the row coordinate
     * @param y is the column coordinate
     */
    @Override
    public  boolean applyObjectiveRules(Library library, int x, int y) {

        return library.getLibrarySpace(x, y).getObject().getObjectColour().isEquals(library.getLibrarySpace(x+1, y ).getObject().getObjectColour());
    }


    @Override
    public String getDescription(){
        return description;
    }
}
