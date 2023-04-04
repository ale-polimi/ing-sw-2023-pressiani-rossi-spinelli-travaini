package model.commonobjective;

import enumerations.ObjectColour;
import model.library.Library;

public class Diagonal implements CommonObjective{
    /**
     *this method will check the presence of at least one diagonal with all equal objects (they have the same colour)
     * @param library is the personal library of the players
     * @param x is the row coordinate
     * @param y is the column coordinate
     */

    private final String description= "Five tiles of the same type forming a diagonal";
    @Override
    public boolean applyObjectiveRules(Library library, int x, int y) {
        ObjectColour colour = library.getLibrarySpace(x, y).getObject().getObjectColour();

        if (y == 0 && x <=1) {
            int k=0;
            for (int i = x + 1, j = y + 1; k < 4; k++, j++, i++) {
                if (!library.getLibrarySpace(i, j).getObject().getObjectColour().equals(colour))
                    break;
            }
            if (k==4)
                return true;
        }
        else if (y==4 && x<=1){
            int k=0;
                for (int i = x + 1, j = y - 1; k < 4; k++, j--, i++) {
                    if (!library.getLibrarySpace(i, j).getObject().getObjectColour().equals(colour))
                        break;
                }
            if (k==4)
                return true;
            }
        return false;
    }

    public String getDescription() {
        return description;
    }
}
