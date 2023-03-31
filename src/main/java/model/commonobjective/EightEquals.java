package model.commonobjective;

import enumerations.ObjectColour;
import model.library.Library;

public class EightEquals implements CommonObjective{

    int count=0;
    /**
     *this method will check the presence of at least eight equal objects (they have the same colour)
     * @param library is the personal library of the players
     * @param x is the row coordinate
     * @param y is the column coordinate
     */
    @Override
    public boolean applyObjectiveRules(Library library, int x, int y) {
        x=0;
        y=0;

        for (ObjectColour o: ObjectColour.values()) {
            count=0;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 5; j++) {
                    if (library.getLibrarySpace(i,j)!= null){
                        if (library.getLibrarySpace(i,j).getObject().getObjectColour().equals(o)){
                            count++;
                        }
                        if (count==8)
                            return true;
                    }
                }

            }
        }
        return false;
    }

    public int getCount(){
        return count;
    }
}
