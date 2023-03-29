package model.commonobjective;

import model.library.Library;

public class FourByFour implements CommonObjective {

    @Override
    public boolean applyObjectiveRules(Library library, int x, int y) {
        int count = 0;

        for ( x = 0; x < 6; x++) {
            for ( y = 0; y < 2; y++) {
                if(FourInLine(library,x,y)){
                    count++;
                    y=2;
                    if(count==4){
                        return true;
                    }
                }
            }

        }
        for ( y = 0; y < 5; y++) {
            for ( x = 0; x < 3; x++) {
                switch (y){
                    case 0  ->{if(FourInColumn(library,x,y)&&!FourInLine(library,x,y)&&
                            !FourInLine(library,x+1,y)&&
                            !FourInLine(library,x+2,y)&&
                            !FourInLine(library,x+3,y)){
                        count ++;
                        x=3;
                        if(count == 4)return true;}
                    }
                    case 1 ->{if(FourInColumn(library, x, y)&&!FourInLine(library,x,y)&&
                            !FourInLine(library,x+1,y)&&
                            !FourInLine(library,x+2,y)&&
                            !FourInLine(library,x+3,y)&& !SecondFourLine(library, x, y)&&
                            !SecondFourLine(library, x+1, y)&&
                            !SecondFourLine(library, x+2, y)&&
                            !SecondFourLine(library, x+3, y)){
                        count++;
                        x=3;
                        if(count == 4)return true;
                    }

                    }
                    case 2 ->{if(FourInColumn(library, x, y)&&!ThirdFourLine(library,x,y)&&
                            !ThirdFourLine(library,x+1,y)&&
                            !ThirdFourLine(library,x+2,y)&&
                            !ThirdFourLine(library,x+3,y)&& !SecondFourLine(library, x, y)&&
                            !SecondFourLine(library, x+1, y)&&
                            !SecondFourLine(library, x+2, y)&&
                            !SecondFourLine(library, x+3, y)){
                        count++;
                        x=3;
                        if(count==4)return true;

                    }
                    }
                    case 3 ->{if(FourInColumn(library, x, y)&&!FourInLine(library,x,y-3)&&
                            !FourInLine(library,x+1,y-3)&&
                            !FourInLine(library,x+2,y-3)&&
                            !FourInLine(library,x+3,y-3)&& !ThirdFourLine(library, x, y)&&
                            !ThirdFourLine(library, x+1, y)&&
                            !ThirdFourLine(library, x+2, y)&&
                            !ThirdFourLine(library, x+3, y)){
                        count++;
                        x=3;
                        if(count==4)return true;

                    }
                    }
                    case 4 ->{if(FourInColumn(library,x,y)&&!FourInLine(library,x,y-3)&&
                            !FourInLine(library,x+1,y-3)&&
                            !FourInLine(library,x+2,y-3)&&
                            !FourInLine(library,x+3,y-3)){
                        count++;
                        x=3;
                        if(count==4)return true;

                    }
                    }
                }

            }

        }
        return false;
    }

    public boolean FourInLine(Library library, int x, int y){
        return library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x, y+1 ).getObject().getObjectColour()) &&
                library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x , y+2).getObject().getObjectColour()) &&
                library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x , y+3 ).getObject().getObjectColour());
    }
    public boolean FourInColumn(Library library,int x, int y){
        return library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x+1, y).getObject().getObjectColour()) &&
                library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x+2 , y).getObject().getObjectColour()) &&
                library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x+3, y).getObject().getObjectColour());
    }
    public boolean SecondFourLine(Library library,int x ,int y){
        return library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x, y-1 ).getObject().getObjectColour()) &&
                library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x , y+1).getObject().getObjectColour()) &&
                library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x , y+2).getObject().getObjectColour());
    }
    public boolean ThirdFourLine(Library library, int x, int y){
        return library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x, y-2 ).getObject().getObjectColour()) &&
                library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x , y-1).getObject().getObjectColour()) &&
                library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x , y+1 ).getObject().getObjectColour());
    }
}
