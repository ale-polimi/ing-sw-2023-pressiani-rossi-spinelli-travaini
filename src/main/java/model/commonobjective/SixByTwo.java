package model.commonobjective;

import model.library.Library;


public class SixByTwo extends TwoEqualsInColumn{

    @Override
    public boolean applyObjectiveRules(Library library, int x, int y) {
        return super.applyObjectiveRules(library, x, y);
    }

    private int count = 0;

    /**
     * This method will check the presence of six couples of elements of the same colour(couples can be of different colours from each other)
     * @param library is the personal library of the players
     */
    public boolean controlObjective(Library library){
        count = 0;


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

    /**
     * This method will check the presence of three objects of the same colour forming a L in the library to avoid counting it as multiple couples
     * @param library is the personal library of the players
     * @param x is the row coordinate
     * @param y is the column coordinate
     */
    private boolean checkLDown(Library library,int x, int y){
        return library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x+1, y+1 ).getObject().getObjectColour());
    }

    /**
     * This method will check the presence of three objects of the same colour forming a reverseLup in the library to avoid counting it as multiple couples
     * @param library is the personal library of the players
     * @param x is the row coordinate
     * @param y is the column coordinate
     */
    private boolean checkReverseLUp(Library library, int x, int y){
        return library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x, y-1 ).getObject().getObjectColour());
    }

    /**
     * This method will check the presence of three objects of the same colour forming a reverseLdown in the library to avoid counting it as multiple couples
     * @param library is the personal library of the players
     * @param x is the row coordinate
     * @param y is the column coordinate
     */
    private boolean checkReverseLDown(Library library, int x, int y){
        return library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x+1, y-1 ).getObject().getObjectColour());
    }

    /**
     * This method will check the presence of three objects of the same colour forming a Lup in the library to avoid counting it as multiple couples
     * @param library is the personal library of the players
     * @param x is the row coordinate
     * @param y is the column coordinate
     */
    private boolean checkLUp(Library library, int x, int y){
        return library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x, y+1 ).getObject().getObjectColour());
    }

    public int getCount(){
        return count;
    }
}
