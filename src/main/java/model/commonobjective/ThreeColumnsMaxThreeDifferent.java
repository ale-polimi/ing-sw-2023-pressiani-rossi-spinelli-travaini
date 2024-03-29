package model.commonobjective;

import model.library.Library;
import model.objects.ObjectCard;
import view.cli.Colours;

public class ThreeColumnsMaxThreeDifferent extends CommonObjective {
    private int countObj;
    private final String description = "  " + Colours.UNDERLINED + " " + Colours.RESET + "\n" +
            " |" + Colours.UNDERLINED + "■" + Colours.RESET + "|\n" +
            " |" + Colours.UNDERLINED + "■" + Colours.RESET + "|     Three columns each formed by 6 tiles\n" +
            " |" + Colours.UNDERLINED + "■" + Colours.RESET + "|     of maximum three different types. One\n" +
            " |" + Colours.UNDERLINED + "■" + Colours.RESET + "|     column can show the same or a different\n" +
            " |" + Colours.UNDERLINED + "■" + Colours.RESET + "|     combination of another column\n" +
            " |" + Colours.UNDERLINED + "■" + Colours.RESET + "|\n" +
            "  x3\n" +
            "Max 3 =";

    /**
     * This method will check the presence of three columns where each column can have a maximum of three different colour
     * @param library is the personal library of the players
     * @param x is the row coordinate
     * @param y is the column coordinate
     */
    @Override
    public boolean applyObjectiveRules(Library library, int x, int y) {
         countObj = 0;

        for ( y = 0; y < 5; y++) {
            int countColours = 0;
            int checkGreen = 0;
            int checkWhite = 0;
            int checkYellow = 0;
            int checkBlue = 0;
            int checkLightBlue = 0;
            int checkPink = 0;

            for ( x = 0; x <6 ; x++) {
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
                    case EMPTY -> {
                        x=6;
                    }
                }
                if(x==5){
                    countColours = checkPink+checkBlue+checkGreen+checkLightBlue+checkWhite+checkYellow;
                    if(countColours<4 && countColours > 0){
                        countObj++;
                        if(countObj==3){
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
