package model.commonobjective;

import model.library.Library;


public class SixByTwo extends TwoEqualsInColumn{
    @Override
    public boolean applyObjectiveRules(Library library, int x, int y) {
        return super.applyObjectiveRules(library, x, y);
    }

    public boolean controlObjective(Library library){

        int count = 0;
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 4; y++) {
                if(library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x, y+1 ).getObject().getObjectColour())){
                    count++;
                    y++;
                    if(count==6){
                        return true;
                    }
                }

            }

        }
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if(applyObjectiveRules(library,x,y)){
                    switch (y){
                        case 0 ->{
                            if(!checkLUp(library,x,y)&&!checkLDown(library,x,y)){
                                count++;
                                x++;
                                if(count == 6){
                                    return true;
                                }
                            }
                        }
                        case 1,2,3 ->{
                            if(!checkLUp(library,x,y)&&!checkLDown(library,x,y)&&!checkReverseLDown(library,x,y)&&checkReverseLUp(library,x,y)){
                                count++;
                                x++;
                                if(count == 6){
                                    return true;
                                }
                            }
                        }
                        case 4 ->{
                            if(!checkReverseLUp(library,x,y)&&!checkReverseLDown(library,x,y)){
                                count++;
                                x++;
                                if(count == 6){
                                    return true;
                                }
                            }
                        }
                    }


                }
            }

        }
        return false;

    }
    private boolean checkLDown(Library library,int x, int y){
        return library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x+1, y+1 ).getObject().getObjectColour());
    }
    private boolean checkReverseLUp(Library library, int x, int y){
        return library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x, y-1 ).getObject().getObjectColour());
    }
    private boolean checkReverseLDown(Library library, int x, int y){
        return library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x+1, y-1 ).getObject().getObjectColour());
    }
    private boolean checkLUp(Library library, int x, int y){
        return library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x, y+1 ).getObject().getObjectColour());
    }
}
