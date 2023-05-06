package model.commonobjective;

import model.library.Library;
import model.objects.ObjectCard;
import view.cli.Colours;

public class FourRowsMaxThreeDifferent extends CommonObjective {
    private int countObj;
    private final String description = " " + Colours.UNDERLINED + " " + Colours.RESET + " " + Colours.UNDERLINED + " " + Colours.RESET + " " + Colours.UNDERLINED + " " + Colours.RESET + " " + Colours.UNDERLINED + " " + Colours.RESET + "   Four lines each formed by 5 tiles of\n" +
            "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|  maximum three different types. One\n" +
            "    x4     line can show the same or a different\n" +
            " Max 3 =   combination of another line.";

    /**
     * This method will check the presence of four rows where each row can have a maximum of three different colours
     * @param library is the personal library of the players
     * @param x is the row coordinate
     * @param y is the column coordinate
     */
    @Override
    public boolean applyObjectiveRules(Library library, int x, int y) {
         countObj = 0;

        for ( x = 0; x < 6; x++) {
            int countColours = 0;
            int checkGreen = 0;
            int checkWhite = 0;
            int checkYellow = 0;
            int checkBlue = 0;
            int checkLightBlue = 0;
            int checkPink = 0;

            for ( y = 0; y <5 ; y++) {
                ObjectCard tempCard = library.getLibrarySpace(x,y).getObject();
                switch (tempCard.getObjectColour()){
                    case BLUE1,BLUE2,BLUE3 -> {
                        checkBlue = 1;
                    }
                    case PINK1,PINK2,PINK3 -> {
                        checkPink = 1;
                    }
                    case GREEN1,GREEN2,GREEN3 -> {
                        checkGreen = 1;
                    }
                    case WHITE1,WHITE2,WHITE3 -> {
                        checkWhite = 1;
                    }
                    case YELLOW1,YELLOW2,YELLOW3 -> {
                        checkYellow = 1;
                    }
                    case LIGHT_BLUE1,LIGHT_BLUE2,LIGHT_BLUE3 -> {
                        checkLightBlue = 1;
                    }
                }
                if(y==4){
                    countColours = checkPink+checkBlue+checkGreen+checkLightBlue+checkWhite+checkYellow;
                    if(countColours<4){
                        countObj++;
                        if(countObj==4){
                            return true;
                        }
                    }

                }
            }

        }
        return false;
    }

    public int getCountObj() {
        return countObj;
    }

    public String getDescription() {
        return description;
    }
}
