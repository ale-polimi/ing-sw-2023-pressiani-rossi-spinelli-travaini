package model.commonobjective;


import enumerations.ObjectColour;
import model.library.Library;

import junit.framework.TestCase;
import model.library.LibrarySpace;
import model.objects.ObjectCard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ThreeColumnsMaxThreeDifferentTest {
    ThreeColumnsMaxThreeDifferent  threeColumnsMaxThreeDifferent;
    Library testLibrary;
    @BeforeEach
    public void setUp() {
        testLibrary=new Library();
        threeColumnsMaxThreeDifferent= new ThreeColumnsMaxThreeDifferent();


    }

    @AfterEach
    public void tearDown() {
        threeColumnsMaxThreeDifferent=null;
    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void isThreeColumnsMaxThreeDifferent(){
        int x = 0;
        int y = 0;


        for(int col = 0; col < 1; col++){
            for(int row = 0; row < 6; row++){

                testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int col = 1; col < 2; col++){
            for(int row = 0; row < 4; row++){

                testLibrary.addObject(new ObjectCard(ObjectColour.GREEN3), testLibrary.getLibrarySpace(row,col));

            }
        }
        testLibrary.addObject(new ObjectCard(ObjectColour.YELLOW2), testLibrary.getLibrarySpace(4,1));
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(5,1));

        for(int col = 2; col < 3; col++){
            for(int row = 0; row < 4; row++){

                testLibrary.addObject(new ObjectCard(ObjectColour.WHITE1), testLibrary.getLibrarySpace(row,col));

            }
        }
        testLibrary.addObject(new ObjectCard(ObjectColour.LIGHT_BLUE3), testLibrary.getLibrarySpace(4,2));
        testLibrary.addObject(new ObjectCard(ObjectColour.GREEN3), testLibrary.getLibrarySpace(5,2));


        assertTrue(threeColumnsMaxThreeDifferent.applyObjectiveRules(testLibrary,x,y));
        System.out.println(threeColumnsMaxThreeDifferent.getCountObj());
        System.out.println(threeColumnsMaxThreeDifferent.getDescription());
    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void isNotThreeColumnsMaxThreeDifferent(){
        int x = 0;
        int y = 0;


        for(int col = 0; col < 1; col++){
            for(int row = 0; row < 6; row++){

                testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int col = 1; col < 2; col++){
            for(int row = 0; row < 4; row++){

                testLibrary.addObject(new ObjectCard(ObjectColour.GREEN3), testLibrary.getLibrarySpace(row,col));

            }
        }
        testLibrary.addObject(new ObjectCard(ObjectColour.YELLOW2), testLibrary.getLibrarySpace(4,1));
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(5,1));

        for(int col = 2; col < 3; col++){
            for(int row = 0; row < 3; row++){

                testLibrary.addObject(new ObjectCard(ObjectColour.WHITE1), testLibrary.getLibrarySpace(row,col));

            }
        }
        testLibrary.addObject(new ObjectCard(ObjectColour.BLUE3), testLibrary.getLibrarySpace(3,2));
        testLibrary.addObject(new ObjectCard(ObjectColour.LIGHT_BLUE3), testLibrary.getLibrarySpace(4,2));
        testLibrary.addObject(new ObjectCard(ObjectColour.GREEN3), testLibrary.getLibrarySpace(5,2));


        assertFalse(threeColumnsMaxThreeDifferent.applyObjectiveRules(testLibrary,x,y));
        System.out.println(threeColumnsMaxThreeDifferent.getCountObj());
        System.out.println(threeColumnsMaxThreeDifferent.getDescription());
    }
    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void isNotThreeColumnsMaxThreeDifferentEmpty(){
        int x = 0;
        int y = 0;

        assertFalse(threeColumnsMaxThreeDifferent.applyObjectiveRules(testLibrary,x,y));
    }
}
