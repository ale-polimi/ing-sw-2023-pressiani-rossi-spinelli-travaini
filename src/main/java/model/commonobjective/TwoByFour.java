package model.commonobjective;

import model.library.Library;
import model.objects.ObjectCard;

public class TwoByFour extends TwoEqualsInColumn {
    @Override
    public boolean applyObjectiveRules(Library library, int x, int y) {
        return super.applyObjectiveRules(library, x, y);
    }

    public boolean controlObjective(Library library) {
        int countBlue = 0;
        int countLightBlue = 0;
        int countGreen = 0;
        int countWhite = 0;
        int countPink = 0;
        int countYellow = 0;
        ObjectCard tempCard = null;
        for (int x = 0; x < 5; x = x+2) {
            for (int y = 0; y < 5; y++) {
                if (applyObjectiveRules(library, x, y)) {
                    if (library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x , y+1).getObject().getObjectColour()) && library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x + 1, y + 1).getObject().getObjectColour())) {
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
                    if (library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x , y+1).getObject().getObjectColour()) && library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x + 1, y + 1).getObject().getObjectColour())) {
                        if((library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x-1, y).getObject().getObjectColour()) && library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x +1, y-1).getObject().getObjectColour()))||
                                (library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x+2, y).getObject().getObjectColour()) && library.getLibrarySpace(x, y).getObject().getObjectColour().equals(library.getLibrarySpace(x +2, y+1).getObject().getObjectColour()))){

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
}