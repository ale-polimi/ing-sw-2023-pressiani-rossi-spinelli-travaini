package model.commonobjective;

import enumerations.ObjectColour;
import model.library.Library;

import model.objects.ObjectCard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
public class FourRowsMaxThreeDifferentTest{
    private FourRowsMaxThreeDifferent  fourRowsMaxThreeDifferent;
    private Library testLibrary;


    @Before
    public void setUp() {

        fourRowsMaxThreeDifferent= new FourRowsMaxThreeDifferent() ;
        testLibrary = new Library();


    }


    @After
    public void tearDown() {fourRowsMaxThreeDifferent=null;
    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void isFourRowsMaxThreeDifferent(){
        int x = 0;
        int y = 0;

        for(int row = 5; row < 6; row++){
            for(int col = 0; col < 5; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int row = 4; row < 5; row++){
            for(int col = 0; col < 3; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.GREEN2), testLibrary.getLibrarySpace(row,col));

            }
        }
        testLibrary.addObject(new ObjectCard(ObjectColour.YELLOW2), testLibrary.getLibrarySpace(4,3));
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(4,4));

        for(int row = 3; row < 4; row++){
            for(int col = 0; col < 3; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.WHITE1), testLibrary.getLibrarySpace(row,col));

            }
        }
        testLibrary.addObject(new ObjectCard(ObjectColour.LIGHT_BLUE3), testLibrary.getLibrarySpace(3,3));
        testLibrary.addObject(new ObjectCard(ObjectColour.GREEN3), testLibrary.getLibrarySpace(3,4));

        for(int row = 2; row < 3; row++){
            for(int col = 0; col < 5; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), testLibrary.getLibrarySpace(row,col));

            }
        }
        assertTrue(fourRowsMaxThreeDifferent.applyObjectiveRules(testLibrary,x,y));
        System.out.println(fourRowsMaxThreeDifferent.getCountObj());
        System.out.println(fourRowsMaxThreeDifferent.getDescription());
    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void isNotFourRowsMaxThreeDifferent() {
        int x = 0;
        int y = 0;

        for (int row = 5; row < 6; row++) {
            for (int col = 0; col < 5; col++) {

                testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), testLibrary.getLibrarySpace(row, col));

            }
        }
        for (int row = 4; row < 5; row++) {
            for (int col = 0; col < 3; col++) {

                testLibrary.addObject(new ObjectCard(ObjectColour.GREEN2), testLibrary.getLibrarySpace(row, col));

            }
        }
        testLibrary.addObject(new ObjectCard(ObjectColour.YELLOW2), testLibrary.getLibrarySpace(4, 3));
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(4, 4));

        for (int row = 3; row < 4; row++) {
            for (int col = 0; col < 2; col++) {

                testLibrary.addObject(new ObjectCard(ObjectColour.WHITE1), testLibrary.getLibrarySpace(row, col));

            }
        }
        testLibrary.addObject(new ObjectCard(ObjectColour.BLUE3), testLibrary.getLibrarySpace(3, 2));
        testLibrary.addObject(new ObjectCard(ObjectColour.LIGHT_BLUE3), testLibrary.getLibrarySpace(3, 3));
        testLibrary.addObject(new ObjectCard(ObjectColour.GREEN3), testLibrary.getLibrarySpace(3, 4));

        for (int row = 2; row < 3; row++) {
            for (int col = 0; col < 5; col++) {

                testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), testLibrary.getLibrarySpace(row, col));

            }
        }
        assertFalse(fourRowsMaxThreeDifferent.applyObjectiveRules(testLibrary, x, y));
        System.out.println(fourRowsMaxThreeDifferent.getCountObj());
        System.out.println(fourRowsMaxThreeDifferent.getDescription());
    }
    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void isNotFourRowsMaxThreeDifferentEmpty(){
        int x = 0;
        int y = 0;

        assertFalse(fourRowsMaxThreeDifferent.applyObjectiveRules(testLibrary,x,y));
        System.out.println(fourRowsMaxThreeDifferent.getCountObj());
    }
}
