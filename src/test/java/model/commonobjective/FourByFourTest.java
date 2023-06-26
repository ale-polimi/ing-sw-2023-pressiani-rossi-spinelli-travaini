package model.commonobjective;


import enumerations.ObjectColour;
import model.library.Library;

import model.library.LibrarySpace;
import model.objects.ObjectCard;
import org.junit.*;
import view.cli.Colours;

import static org.junit.jupiter.api.Assertions.*;
public class FourByFourTest {
    Library testLibrary;
    FourByFourNew  fourByFour;

    @Before
    public void setUp() {
        testLibrary =new Library();
        fourByFour= new FourByFourNew();
    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void isFourByFour(){
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

                testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int row = 0; row < 2; row++){
            for(int col = 0; col < 2; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.YELLOW2), testLibrary.getLibrarySpace(row,col));

            }
        }



        assertTrue(fourByFour.applyObjectiveRules(testLibrary,x,y));
        System.out.println(fourByFour.groupCount);
        System.out.println(fourByFour.getDescription());
    }

    /**
     * Test to check if the objective works with "complex" layouts of  groups
     */
    @Test
    public void isFourByFourL(){
        int x = 0;
        int y = 0;
        for(int row = 3; row < 4; row++){
            for(int col = 0; col < 2; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int col = 1; col < 2; col++){
           for(int row = 3; row < 6; row++){


                testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int col = 0; col < 1; col++){
            for(int row = 4; row < 6; row++){


                testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int col = 2; col < 3; col++){
            for(int row = 3; row < 6; row++){


                testLibrary.addObject(new ObjectCard(ObjectColour.GREEN3), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int col = 3; col < 4; col++){
            for(int row = 5; row < 6; row++){


                testLibrary.addObject(new ObjectCard(ObjectColour.GREEN2), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int col = 3; col < 4; col++){
            for(int row = 3; row < 5; row++){


                testLibrary.addObject(new ObjectCard(ObjectColour.YELLOW3), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int col = 4; col < 5; col++){
            for(int row = 4; row < 6; row++){


                testLibrary.addObject(new ObjectCard(ObjectColour.YELLOW3), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int col = 4; col < 5; col++){
            for(int row = 1; row < 4; row++){


                testLibrary.addObject(new ObjectCard(ObjectColour.PINK2), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int col = 3; col < 4; col++){
            for(int row = 2; row < 3; row++){


                testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(row,col));

            }
        }
        assertTrue(fourByFour.applyObjectiveRules(testLibrary,x,y));
        System.out.println(fourByFour.groupCount);
    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void notFourByFourEmpty(){
        assertFalse(fourByFour.applyObjectiveRules(testLibrary,0,0));
    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void isNotFourByFourFullLibrary(){
        int x = 0;
        int y = 0;
        for(int row = 0; row < 6; row++){
            for(int col = 0; col < 5; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.YELLOW2), testLibrary.getLibrarySpace(row,col));

            }
        }

        assertFalse(fourByFour.applyObjectiveRules(testLibrary,x,y));
        System.out.println(fourByFour.groupCount);
    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void isNotFourByFour(){
        int x = 0;
        int y = 0;
        for(int row = 3; row < 6; row++){
            for(int col = 0; col < 5; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int row = 1; row < 3; row++){
            for(int col = 0; col < 2; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.GREEN1), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int row = 1; row < 3; row++){
            for(int col = 3; col < 5; col++){

                testLibrary.addObject(new ObjectCard(ObjectColour.BLUE3), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int col = 2; col < 3; col++){
            for(int row = 0; row < 2; row++){

                testLibrary.addObject(new ObjectCard(ObjectColour.YELLOW2), testLibrary.getLibrarySpace(row,col));

            }
        }
        for(int row = 0; row < 1; row++){
           for(int col = 0; col < 5; col++){


                testLibrary.addObject(new ObjectCard(ObjectColour.YELLOW2), testLibrary.getLibrarySpace(row,col));

            }
        }


        assertFalse(fourByFour.applyObjectiveRules(testLibrary,x,y));
        System.out.println(fourByFour.groupCount);
    }

    @Test
    public void getDescription(){
        String description = " " + Colours.UNDERLINED + " " + Colours.RESET + "\n" +
                "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|  Four groups each containing at least\n" +
                "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|  4 tiles of the same type (not necessarily\n" +
                "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|  in the depicted shape).\n" +
                "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|  The tiles of one group can be different\n" +
                " x4  from those of another group.";

        assertEquals(description, fourByFour.getDescription());
    }
}
