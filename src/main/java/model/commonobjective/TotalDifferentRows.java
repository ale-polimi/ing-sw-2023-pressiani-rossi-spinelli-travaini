package model.commonobjective;

import model.library.Library;

public class TotalDifferentRows extends CommonObjective {
    /**
     *this method will check the presence of at least two rows with all different objects
     * @param library is the personal library of the players
     * @param x is the row coordinate
     * @param y is the column coordinate
     */

    private final String description = "Two lines each formed by 5 different types of tiles. One line can show the same or a different combination of the other line.";

   private int count=0;
    @Override
    public boolean applyObjectiveRules(Library library, int x, int y) {
        x=0;
        y=0;

        boolean different=true;

        for (int k = 0; k < 5; k++) {
            different=true;
            for (int i = 0; i < 5 && different; i++) {
                for (int j = i+1; j < 5; j++) {
                    if (library.getLibrarySpace(k, i).getObject().getObjectColour().isEquals(library.getLibrarySpace(k, i+1).getObject().getObjectColour())) {
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
