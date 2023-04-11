package model.commonobjective;

import model.library.Library;

public class TotalDifferentColumns implements CommonObjective{
    /**
     *this method will check the presence of at least two columns with all different objects
     * @param library is the personal library of the players
     * @param x is the row coordinate
     * @param y is the column coordinate
     */

    private int count=0;

    private final String description = "two columns each formed by 6 different types of tiles";
    @Override
    public boolean applyObjectiveRules(Library library, int x, int y) {
        x=0;
        y=0;

        boolean different=true;


        for (int k = 0; k < 4; k++) {
            different=true;
            for (int i = 0; i < 4 && different; i++) {
                for (int j = i+1; j < 4; j++) {
                    if (library.getLibrarySpace(i, k).getObject().getObjectColour().isEquals(library.getLibrarySpace(i + 1, k).getObject().getObjectColour())) {
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
