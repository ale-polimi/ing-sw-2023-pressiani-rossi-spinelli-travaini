package model.commonobjective;

import model.library.Library;


public class SixByTwo extends TwoEqualsInColumn{
    @Override
    public boolean applyObjectiveRules(Library library, int x, int y) {
        return super.applyObjectiveRules(library, x, y);
    }

    public boolean controlObjective(Library library){

        int count = 0;
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 4; x++) {
                if(library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x+1, y ).getObject().getObjectColour())){
                    count++;
                    x++;
                    if(count==6){
                        return true;
                    }
                }

            }

        }
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if(applyObjectiveRules(library,x,y) && (x==4 ||!library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x+1, y ).getObject().getObjectColour()))){
                    count++;
                    y++;
                    if(count == 6){
                        return true;
                    }

                }
            }

        }
        return false;

    }
}
