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
    }

    @AfterEach
    public void tearDown() {
        twoByFour=null;
    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void isTwoByFourLB(){
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
    public void isTwoByFourB(){
        int x = 0;
        int y = 0;

        for(int row = 4; row < 6; row++){
            for(int col = 3; col < 5; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.BLUE2), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int row = 4; row < 6; row++){
            for(int col = 0; col < 2; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.BLUE3), testLibrary.getLibrarySpace(row,col));

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
    public void isTwoByFourP(){
        int x = 0;
        int y = 0;

        for(int row = 4; row < 6; row++){
            for(int col = 3; col < 5; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.PINK2), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int row = 4; row < 6; row++){
            for(int col = 0; col < 2; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.PINK3), testLibrary.getLibrarySpace(row,col));

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
    public void isTwoByFourG(){
        int x = 0;
        int y = 0;

        for(int row = 4; row < 6; row++){
            for(int col = 3; col < 5; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.GREEN2), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int row = 4; row < 6; row++){
            for(int col = 0; col < 2; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.GREEN3), testLibrary.getLibrarySpace(row,col));

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
    public void isTwoByFourW(){
        int x = 0;
        int y = 0;

        for(int row = 4; row < 6; row++){
            for(int col = 3; col < 5; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.WHITE2), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int row = 4; row < 6; row++){
            for(int col = 0; col < 2; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.WHITE3), testLibrary.getLibrarySpace(row,col));

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
    public void isTwoByFourY(){
        int x = 0;
        int y = 0;

        for(int row = 4; row < 6; row++){
            for(int col = 3; col < 5; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.YELLOW2), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int row = 4; row < 6; row++){
            for(int col = 0; col < 2; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.YELLOW3), testLibrary.getLibrarySpace(row,col));

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
    public void isTwoByFourOddB(){
        int x = 0;
        int y = 0;

        for(int row = 3; row < 5; row++){
            for(int col = 3; col < 5; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.BLUE2), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int row = 1; row < 3; row++){
            for(int col = 0; col < 2; col++){


                testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), testLibrary.getLibrarySpace(row,col));

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
    public void isTwoByFourOddP(){
        int x = 0;
        int y = 0;

        for(int row = 3; row < 5; row++){
            for(int col = 3; col < 5; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.PINK2), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int row = 1; row < 3; row++){
            for(int col = 0; col < 2; col++){


                testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(row,col));

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
    public void isTwoByFourOddG(){
        int x = 0;
        int y = 0;

        for(int row = 3; row < 5; row++){
            for(int col = 3; col < 5; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.GREEN2), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int row = 1; row < 3; row++){
            for(int col = 0; col < 2; col++){


                testLibrary.addObject(new ObjectCard(ObjectColour.GREEN1), testLibrary.getLibrarySpace(row,col));

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
    public void isTwoByFourOddW(){
        int x = 0;
        int y = 0;

        for(int row = 3; row < 5; row++){
            for(int col = 3; col < 5; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.WHITE2), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int row = 1; row < 3; row++){
            for(int col = 0; col < 2; col++){


                testLibrary.addObject(new ObjectCard(ObjectColour.WHITE1), testLibrary.getLibrarySpace(row,col));

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
    public void isTwoByFourOddLB(){
        int x = 0;
        int y = 0;

        for(int row = 3; row < 5; row++){
            for(int col = 3; col < 5; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.LIGHT_BLUE2), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int row = 1; row < 3; row++){
            for(int col = 0; col < 2; col++){


                testLibrary.addObject(new ObjectCard(ObjectColour.LIGHT_BLUE1), testLibrary.getLibrarySpace(row,col));

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
    public void isTwoByFourOddY(){
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
     * Test to check if the objective is not applied when the groups have tiles in common.
     */
    @Test
    public void isNotTwoByFourOneTileShared(){
        /* Square in the bottom left corner */
        testLibrary.addObject(new ObjectCard(ObjectColour.WHITE1), testLibrary.getLibrarySpace(4,0));
        testLibrary.addObject(new ObjectCard(ObjectColour.WHITE1), testLibrary.getLibrarySpace(5,0));
        testLibrary.addObject(new ObjectCard(ObjectColour.WHITE1), testLibrary.getLibrarySpace(4,1));
        testLibrary.addObject(new ObjectCard(ObjectColour.WHITE1), testLibrary.getLibrarySpace(5,1));

        testLibrary.addObject(new ObjectCard(ObjectColour.WHITE1), testLibrary.getLibrarySpace(3,1));
        testLibrary.addObject(new ObjectCard(ObjectColour.WHITE1), testLibrary.getLibrarySpace(3,2));
        testLibrary.addObject(new ObjectCard(ObjectColour.WHITE1), testLibrary.getLibrarySpace(4,2));

        assertFalse(twoByFour.applyObjectiveRules(testLibrary,0,0));
        System.out.println(twoByFour.getCountWhite());
    }

    /**
     * Test to check if the objective is not applied when the groups have tiles in common.
     */
    @Test
    public void isNotTwoByFourTwoTilesShared(){
        /* Square in the bottom left corner */
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(4,0));
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(5,0));
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(4,1));
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(5,1));

        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(3,0));
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(3,1));

        assertFalse(twoByFour.applyObjectiveRules(testLibrary,0,0));
        System.out.println(twoByFour.getCountPink());
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
