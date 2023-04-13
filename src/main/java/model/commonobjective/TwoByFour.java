package model.commonobjective;

import model.library.Library;
import model.objects.ObjectCard;

public class TwoByFour extends TwoEqualsInColumn {

    private final String description = "Two groups each containing 4 tiles of the same type in a 2x2 square. The tiles of one square can be different from those of the other square.";
    @Override
    public boolean applyObjectiveRules(Library library, int x, int y) {
        return super.applyObjectiveRules(library, x, y);
    }

    private int countBlue = 0;
    private int countLightBlue = 0;
    private int countGreen = 0;
    private int countWhite = 0;
    private int countPink = 0;
    private int countYellow = 0;

    /**
     * This method will check the presence of two squares composed by four spaces containing objects of the same colour(the two squares must be the same colour)
     * @param library is the personal library of the players
     */
    public boolean controlObjective(Library library) {
         countBlue = 0;
         countLightBlue = 0;
         countGreen = 0;
         countWhite = 0;
         countPink = 0;
         countYellow = 0;
        ObjectCard tempCard = null;
        for (int x = 0; x < 5; x = x+2) {
            for (int y = 0; y < 5; y++) {
                if (applyObjectiveRules(library, x, y)) {
                    if (library.getLibrarySpace(x, y).getObject().getObjectColour().isEquals(library.getLibrarySpace(x , y+1).getObject().getObjectColour()) && library.getLibrarySpace(x, y).getObject().getObjectColour().isEquals(library.getLibrarySpace(x + 1, y + 1).getObject().getObjectColour())) {
                        tempCard = library.getLibrarySpace(x, y).getObject();
                        switch (tempCard.getObjectColour()) {
                            case BLUE1, BLUE2, BLUE3 -> {
                                countBlue++;
                                if (countBlue == 2) {
                                    return true;
                                }
                            }
                            case PINK1, PINK2, PINK3 -> {
                                countPink++;
                                if (countPink == 2) {
                                    return true;
                                }
                            }
                            case GREEN1, GREEN2, GREEN3 -> {
                                countGreen++;
                                if (countGreen == 2){
                                    return true;
                                }

                            }
                            case WHITE1, WHITE2, WHITE3 -> {
                                countWhite ++;
                                if(countWhite == 2){
                                    return true;
                                }
                            }
                            case YELLOW1,YELLOW2,YELLOW3 -> {
                                countYellow ++;
                                if (countYellow == 2){
                                    return true;
                                }
                            }
                            case LIGHT_BLUE1, LIGHT_BLUE2, LIGHT_BLUE3 -> {
                                countLightBlue ++;
                                if (countLightBlue == 2){
                                    return true;
                                }
                            }
                        }
                        y++;
                    }
                }

            }


        }
        for (int x = 1; x <5; x = x+2) {
            for (int y = 0; y < 5; y++) {
                if (applyObjectiveRules(library, x, y)) {
                    if (library.getLibrarySpace(x, y).getObject().getObjectColour().isEquals(library.getLibrarySpace(x , y+1).getObject().getObjectColour()) && library.getLibrarySpace(x, y).getObject().getObjectColour().isEquals(library.getLibrarySpace(x + 1, y + 1).getObject().getObjectColour())) {
                        if((library.getLibrarySpace(x, y).getObject().getObjectColour().isEquals(library.getLibrarySpace(x-1, y).getObject().getObjectColour()) && library.getLibrarySpace(x, y).getObject().getObjectColour().isEquals(library.getLibrarySpace(x -1, y+1).getObject().getObjectColour()))||
                                (library.getLibrarySpace(x, y).getObject().getObjectColour().isEquals(library.getLibrarySpace(x+2, y).getObject().getObjectColour()) && library.getLibrarySpace(x, y).getObject().getObjectColour().isEquals(library.getLibrarySpace(x +2, y+1).getObject().getObjectColour()))){

                        }else {
                        tempCard = library.getLibrarySpace(x, y).getObject();
                        switch (tempCard.getObjectColour()) {
                            case BLUE1, BLUE2, BLUE3 -> {
                                countBlue++;
                                if (countBlue == 2) {
                                    return true;
                                }
                            }
                            case PINK1, PINK2, PINK3 -> {
                                countPink++;
                                if (countPink == 2) {
                                    return true;
                                }
                            }
                            case GREEN1, GREEN2, GREEN3 -> {
                                countGreen++;
                                if (countGreen == 2){
                                    return true;
                                }

                            }
                            case WHITE1, WHITE2, WHITE3 -> {
                                countWhite ++;
                                if(countWhite == 2){
                                    return true;
                                }
                            }
                            case YELLOW1,YELLOW2,YELLOW3 -> {
                                countYellow ++;
                                if (countYellow == 2){
                                    return true;
                                }
                            }
                            case LIGHT_BLUE1, LIGHT_BLUE2, LIGHT_BLUE3 -> {
                                countLightBlue ++;
                                if (countLightBlue == 2){
                                    return true;
                                }
                            }
                        }
                            y++;
                     }

                    }
                }

            }
        }
        return false;
    }

    public int getCountBlue() {
        return countBlue;
    }

    public int getCountGreen() {
        return countGreen;
    }

    public int getCountLightBlue() {
        return countLightBlue;
    }

    public int getCountPink() {
        return countPink;
    }

    public int getCountWhite() {
        return countWhite;
    }

    public int getCountYellow() {
        return countYellow;
    }

    public String getDescription() {
        return description;
    }
}