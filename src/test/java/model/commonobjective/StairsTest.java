package model.commonobjective;


import enumerations.ObjectColour;
import model.library.Library;
import model.library.LibrarySpace;
import model.objects.ObjectCard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import view.cli.Colours;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class StairsTest {
    Library testLibrary;
    Stairs stairs;

    @Before
    public void setUp() {
        testLibrary = new Library();
        stairs = new Stairs();
    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void hasStairsDescendingOrderFirstTry(){
        for(int row = 1; row < 6; row++){
            for(int col = 0; col < 5; col++){
                if(col + 1 <= row){
                    testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(row,col));
                }
            }
        }
        assertTrue(stairs.applyObjectiveRules(testLibrary,0,0));
    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void hasStairsDescendingOrderSecondTry(){
        for(int row = 0; row < 6; row++){
            for(int col = 0; col < 5; col++){
                if(col <= row){
                    testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(row,col));
                }
            }
        }
        assertTrue(stairs.applyObjectiveRules(testLibrary,0,0));
    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void hasStairsAscendingOrderFirstTry(){
        for(int row = 1; row < 6; row++){
            for(int col = 0; col < 5; col++){
                if(4 - col + 1 <= row){
                    testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(row,col));
                }
            }
        }
        assertTrue(stairs.applyObjectiveRules(testLibrary,0,0));
    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void hasStairsAscendingOrderSecondTry(){
        for(int row = 0; row < 6; row++){
            for(int col = 0; col < 5; col++){
                if(4 - col <= row){
                    testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(row,col));
                }
            }
        }
        assertTrue(stairs.applyObjectiveRules(testLibrary,0,0));
    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void doesNotHaveStairsDescendingOrderFirstTry(){
        for(int row = 1; row < 6; row++){
            for(int col = 0; col < 5; col++){
                testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(row,col));
            }
        }
        assertFalse(stairs.applyObjectiveRules(testLibrary,0,0));
    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void doesNotHaveStairsDescendingOrderSecondTry(){
        for(int row = 0; row < 6; row++){
            for(int col = 0; col < 5; col++){
                testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(row,col));
            }
        }
        assertFalse(stairs.applyObjectiveRules(testLibrary,0,0));
    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void doesNotHaveStairsAscendingOrderFirstTry(){
        for(int row = 1; row < 6; row++){
            for(int col = 0; col < 5; col++){
                testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(row,col));
            }
        }
        assertFalse(stairs.applyObjectiveRules(testLibrary,0,0));
    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void doesNotHaveStairsAscendingOrderSecondTry(){
        for(int row = 1; row < 6; row++){
            for(int col = 0; col < 5; col++){
                testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(row,col));
            }
        }
        assertFalse(stairs.applyObjectiveRules(testLibrary,0,0));
    }

    @Test
    public void getDescription(){
        String description =" " + Colours.UNDERLINED + " " + Colours.RESET + "\n" +
                "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + " " + Colours.RESET + "         Five columns of increasing or decreasing\n" +
                "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + " " + Colours.RESET + "       height. Starting from the first column on\n" +
                "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET  + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + " " + Colours.RESET + "     the left or on the right, each next column\n" +
                "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET  + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + " " + Colours.RESET + "   must be made of exactly one more tile.\n" +
                "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET  + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|  Tiles can be of any type.";

        assertEquals(description, stairs.getDescription());
    }
}