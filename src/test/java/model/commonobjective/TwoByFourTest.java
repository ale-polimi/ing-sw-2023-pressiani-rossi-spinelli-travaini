package model.commonobjective;

import enumerations.ObjectColour;
import junit.framework.TestCase;
import model.library.Library;

import model.objects.ObjectCard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TwoByFourTest {
    TwoByFour  twoByFour;
    Library testLibrary;
    @BeforeEach
    public void setUp() {
        testLibrary=new Library();
        twoByFour= new TwoByFour();



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
        twoByFour=null;
    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void isTwoByFour(){
        int x = 0;
        int y = 0;

        for(int row = 4; row < 6; row++){
            for(int col = 3; col < 5; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.LIGHT_BLUE3), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int row = 4; row < 6; row++){
            for(int col = 0; col < 2; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.LIGHT_BLUE3), testLibrary.getLibrarySpace(row,col));

            }
        }


        assertTrue(twoByFour.applyObjectiveRules(testLibrary,x,y));
        System.out.println(twoByFour.getCountGreen());
        System.out.println(twoByFour.getCountBlue());
        System.out.println(twoByFour.getCountYellow());
        System.out.println(twoByFour.getCountPink());
        System.out.println(twoByFour.getCountLightBlue());
        System.out.println(twoByFour.getCountWhite());
        System.out.println(twoByFour.getDescription());


    }
    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void isTwoByFourOdd(){
        int x = 0;
        int y = 0;

        for(int row = 3; row < 5; row++){
            for(int col = 3; col < 5; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.YELLOW2), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int row = 1; row < 3; row++){
            for(int col = 0; col < 2; col++){


                testLibrary.addObject(new ObjectCard(ObjectColour.YELLOW1), testLibrary.getLibrarySpace(row,col));

            }
        }


        assertTrue(twoByFour.applyObjectiveRules(testLibrary,x,y));
        System.out.println(twoByFour.getCountGreen());
        System.out.println(twoByFour.getCountYellow());
        System.out.println(twoByFour.getCountPink());
        System.out.println(twoByFour.getCountLightBlue());
        System.out.println(twoByFour.getCountWhite());


    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void isNotTwoByFour() {
        int x = 0;
        int y = 0;

        for(int row = 4; row < 6; row++){
            for(int col = 3; col < 5; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.GREEN3), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int row = 4; row < 6; row++){
            for(int col = 0; col < 2; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), testLibrary.getLibrarySpace(row,col));

            }
        }


        assertFalse(twoByFour.applyObjectiveRules(testLibrary, x, y));
        System.out.println(twoByFour.getCountGreen());
        System.out.println(twoByFour.getCountBlue());
    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void isNotTwoByFourOneTileMissing() {
        int x = 0;
        int y = 0;

        for (int row = 4; row < 5; row++) {
            for (int col = 3; col < 5; col++) {

                testLibrary.addObject(new ObjectCard(ObjectColour.GREEN3), testLibrary.getLibrarySpace(row, col));

            }
        }

        testLibrary.addObject(new ObjectCard(ObjectColour.GREEN3), testLibrary.getLibrarySpace(5, 0));
        for (int row = 4; row < 6; row++) {
            for (int col = 0; col < 2; col++) {

                testLibrary.addObject(new ObjectCard(ObjectColour.GREEN2), testLibrary.getLibrarySpace(row, col));

            }
        }
        assertFalse(twoByFour.applyObjectiveRules(testLibrary, x, y));
        System.out.println(twoByFour.getCountGreen());

    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void isNotTwoByFourEmpty() {
        int x = 0;
        int y = 0;

        assertFalse(twoByFour.applyObjectiveRules(testLibrary, x, y));
    }
}
