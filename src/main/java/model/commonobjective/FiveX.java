package model.commonobjective;

import enumerations.ObjectColour;
import model.library.Library;

public class FiveX implements CommonObjective{
    /**
     *this method will check the presence of at least five objects of the same colour, that form an x
     * @param library is the personal library of the players
     * @param x is the row coordinate
     * @param y is the column coordinate
     */
    @Override
    public boolean applyObjectiveRules(Library library, int x, int y) {

        ObjectColour colour= library.getLibrarySpace(x,y).getObject().getObjectColour();

        if (library.getLibrarySpace(x-1,y-1).getObject().getObjectColour().equals(colour) &&
                library.getLibrarySpace(x-1,y+1).getObject().getObjectColour().equals(colour)&&
                library.getLibrarySpace(x+1,y-1).getObject().getObjectColour().equals(colour)&&
                library.getLibrarySpace(x+1,y+1).getObject().getObjectColour().equals(colour))
            return true;
        else
            return false;

    }
}
