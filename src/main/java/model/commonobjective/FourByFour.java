package model.commonobjective;

import model.library.Library;

public class FourByFour implements CommonObjective {

    @Override
    public boolean applyObjectiveRules(Library library, int x, int y) {
        int count = 0;

        for ( x = 0; x < 2; x++) {
            for ( y = 0; y < 6; y++) {
                if(FourInLine(library,x,y)){
                    count++;
                    if(count==4){
                        return true;
                    }
                }
            }

        }
        for ( y = 0; y < 3; y++) {
            for ( x = 0; x < 5; x++) {
                switch (x){
                    case 0  ->{if(FourInColumn(library,x,y)&&!FourInLine(library,x,y)&&
                            !FourInLine(library,x,y+1)&&
                            !FourInLine(library,x,y+2)&&
                            !FourInLine(library,x,y+3)){
                        count ++;
                        if(count == 4)return true;}
                    }
                    case 1 ->{if(FourInColumn(library, x, y)&&!FourInLine(library,x,y)&&
                            !FourInLine(library,x,y+1)&&
                            !FourInLine(library,x,y+2)&&
                            !FourInLine(library,x,y+3)&& !SecondFourLine(library, x, y)&&
                            !SecondFourLine(library, x, y+1)&&
                            !SecondFourLine(library, x, y+2)&&
                            !SecondFourLine(library, x, y+3)){
                        count++;
                        if(count == 4)return true;
                    }

                    }
                    case 2 ->{if(FourInColumn(library, x, y)&&!ThirdFourLine(library,x,y)&&
                            !ThirdFourLine(library,x,y+1)&&
                            !ThirdFourLine(library,x,y+2)&&
                            !ThirdFourLine(library,x,y+3)&& !SecondFourLine(library, x, y)&&
                            !SecondFourLine(library, x, y+1)&&
                            !SecondFourLine(library, x, y+2)&&
                            !SecondFourLine(library, x, y+3)){
                        count++;
                        if(count==4)return true;

                    }
                    }
                    case 3 ->{if(FourInColumn(library, x, y)&&!FourInLine(library,x-3,y)&&
                            !FourInLine(library,x-3,y+1)&&
                            !FourInLine(library,x-3,y+2)&&
                            !FourInLine(library,x-3,y+3)&& !ThirdFourLine(library, x, y)&&
                            !ThirdFourLine(library, x, y+1)&&
                            !ThirdFourLine(library, x, y+2)&&
                            !ThirdFourLine(library, x, y+3)){
                        count++;
                        if(count==4)return true;

                    }
                    }
                    case 4 ->{if(FourInColumn(library,x,y)&&!FourInLine(library,x-3,y)&&
                            !FourInLine(library,x-3,y+1)&&
                            !FourInLine(library,x-3,y+2)&&
                            !FourInLine(library,x-3,y+3)){
                        count++;
                        if(count==4)return true;

                    }
                    }
                }

            }

        }
        return false;
    }

    public boolean FourInLine(Library library, int x, int y){
        return library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x+1, y ).getObject().getObjectColour()) &&
                library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x + 2, y).getObject().getObjectColour()) &&
                library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x + 3, y ).getObject().getObjectColour());
    }
    public boolean FourInColumn(Library library,int x, int y){
        return library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x, y+1).getObject().getObjectColour()) &&
                library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x , y+2).getObject().getObjectColour()) &&
                library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x , y+3).getObject().getObjectColour());
    }
    public boolean SecondFourLine(Library library,int x ,int y){
        return library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x-1, y ).getObject().getObjectColour()) &&
                library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x + 1, y).getObject().getObjectColour()) &&
                library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x + 2, y ).getObject().getObjectColour());
    }
    public boolean ThirdFourLine(Library library, int x, int y){
        return library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x-2, y ).getObject().getObjectColour()) &&
                library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x -1, y).getObject().getObjectColour()) &&
                library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x + 1, y ).getObject().getObjectColour());
    }
}
