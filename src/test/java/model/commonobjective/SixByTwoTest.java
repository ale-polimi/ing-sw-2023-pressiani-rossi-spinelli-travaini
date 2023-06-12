package model.commonobjective;

import enumerations.ObjectColour;
import junit.framework.TestCase;
import model.library.Library;

import model.library.LibrarySpace;
import model.objects.ObjectCard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SixByTwoTest  {

    SixByTwoNew  sixByTwo;
    Library testLibrary;
    @BeforeEach
    public void setUp() {
        testLibrary=new Library();
       sixByTwo= new SixByTwoNew();



        /*
        testLibrary.addObject(new ObjectCard("GREEN2"),testLibrary.getLibrarySpace(0,0));
        testLibrary.addObject(new ObjectCard("GREEN3"),testLibrary.getLibrarySpace(0,1));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(0,2));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(0,3));
        testLibrary.addObject(new ObjectCard("WHITE1"),testLibrary.getLibrarySpace(0,4));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(1,0));
        testLibrary.addObject(new ObjectCard("BLUE1"),testLibrary.getLibrarySpace(1,1));
        testLibrary.addObject(new ObjectCard("BLUE3"),testLibrary.getLibrarySpace(1,2));
        testLibrary.addObject(new ObjectCard("BLUE1"),testLibrary.getLibrarySpace(1,3));
        testLibrary.addObject(new ObjectCard("BLUE1"),testLibrary.getLibrarySpace(1,4));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(2,0));
        testLibrary.addObject(new ObjectCard("YELLOW1"),testLibrary.getLibrarySpace(2,1));
        testLibrary.addObject(new ObjectCard("WHITE1"),testLibrary.getLibrarySpace(2,2));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(2,3));
        testLibrary.addObject(new ObjectCard("YELLOW1"),testLibrary.getLibrarySpace(2,4));
        testLibrary.addObject(new ObjectCard("LIGHT_BLUE1"),testLibrary.getLibrarySpace(3,0));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(3,1));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(3,2));
        testLibrary.addObject(new ObjectCard("WHITE1"),testLibrary.getLibrarySpace(3,3));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(3,4));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(4,0));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(4,1));
        testLibrary.addObject(new ObjectCard("GREEN3"),testLibrary.getLibrarySpace(4,2));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(4,3));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(4,4));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(5,0));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(5,1));
        testLibrary.addObject(new ObjectCard("GREEN2"),testLibrary.getLibrarySpace(5,2));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(5,3));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(5,4));
        */

    }

    @AfterEach
    public void tearDown() {
        sixByTwo=null;
    }
    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void isSixByTwo(){
        int x = 0;
        int y = 0;

        for(int row = 4; row < 6; row++){
            for(int col = 0; col < 2; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int row = 2; row < 4; row++){
            for(int col = 0; col < 2; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.YELLOW3), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int row = 4; row < 6; row++){
            for(int col = 3; col < 5; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.GREEN3), testLibrary.getLibrarySpace(row,col));

            }
        }

        for(int row = 2; row < 4; row++){
            for(int col = 3; col < 5; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.PINK2), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int col = 2; col < 3; col++){
            for(int row = 4; row < 6; row++){

                testLibrary.addObject(new ObjectCard(ObjectColour.LIGHT_BLUE1), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int col = 2; col < 3; col++){
            for(int row = 2; row < 4; row++){

                testLibrary.addObject(new ObjectCard(ObjectColour.GREEN2), testLibrary.getLibrarySpace(row,col));

            }
        }

            assertTrue(sixByTwo.applyObjectiveRules(testLibrary,x,y));
        System.out.println(sixByTwo.groupCount);
        System.out.println(sixByTwo.getDescription());
    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void isSixByTwoExactly(){
        int x = 0;
        int y = 0;

        testLibrary.addObject(new ObjectCard(ObjectColour.PINK2), testLibrary.getLibrarySpace(5,0));
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK2), testLibrary.getLibrarySpace(5,1));
        testLibrary.addObject(new ObjectCard(ObjectColour.GREEN1), testLibrary.getLibrarySpace(5,2));
        testLibrary.addObject(new ObjectCard(ObjectColour.GREEN2), testLibrary.getLibrarySpace(5,3));
        testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), testLibrary.getLibrarySpace(4,0));
        testLibrary.addObject(new ObjectCard(ObjectColour.BLUE2), testLibrary.getLibrarySpace(4,1));
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK2), testLibrary.getLibrarySpace(5,4));
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK2), testLibrary.getLibrarySpace(4,4));
        testLibrary.addObject(new ObjectCard(ObjectColour.YELLOW3), testLibrary.getLibrarySpace(4,2));
        testLibrary.addObject(new ObjectCard(ObjectColour.YELLOW1), testLibrary.getLibrarySpace(4,3));
        testLibrary.addObject(new ObjectCard(ObjectColour.LIGHT_BLUE1), testLibrary.getLibrarySpace(3,0));
        testLibrary.addObject(new ObjectCard(ObjectColour.LIGHT_BLUE1), testLibrary.getLibrarySpace(3,1));




        assertTrue(sixByTwo.applyObjectiveRules(testLibrary,x,y));
        System.out.println(sixByTwo.groupCount);

    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void isNotSixByTwoFull(){
        int x = 0;
        int y = 0;
        for(int row = 0; row < 6; row++){
            for(int col = 0; col < 5; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.PINK2), testLibrary.getLibrarySpace(row,col));

            }
        }


        assertFalse(sixByTwo.applyObjectiveRules(testLibrary,x,y));
        System.out.println(sixByTwo.groupCount);

    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void isNotSixByTwoEmpty(){
        int x = 0;
        int y = 0;


        assertFalse(sixByTwo.applyObjectiveRules(testLibrary,x,y));
        System.out.println(sixByTwo.groupCount);

    }
}
