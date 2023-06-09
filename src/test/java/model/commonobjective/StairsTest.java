package model.commonobjective;


import enumerations.ObjectColour;
import model.library.Library;
import model.library.LibrarySpace;
import model.objects.ObjectCard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


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
}