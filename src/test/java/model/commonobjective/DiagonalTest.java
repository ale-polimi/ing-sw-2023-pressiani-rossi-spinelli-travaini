package model.commonobjective;

import enumerations.ObjectColour;
import model.library.Library;

import model.library.LibrarySpace;
import model.objects.ObjectCard;
import org.junit.*;
import view.cli.Colours;

import static org.junit.jupiter.api.Assertions.*;

public class DiagonalTest {
    private Library testLibrary;
    private CommonObjective diagonal;

    @Before
    public void setUp() {
        testLibrary = new Library();
        diagonal = new Diagonal();
    }

    /**
     * This test checks if the library has a diagonal starting from row = 1 col =0;
     */
    @Test
    public void hasDiagonalFirstTry() {
        int row = 1;
        for (int col = 0; col < 5; col++){
            testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(row + col,col));
        }

        assertTrue(diagonal.applyObjectiveRules(testLibrary, 0,0));
    }

    /**
     * This test checks if the library has a diagonal starting from row = 0 col =0;
     */
    @Test
    public void hasDiagonalSecondTry(){
        int row = 1;
        testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), testLibrary.getLibrarySpace(row,0));
        for (int col = 1; col < 5; col++){
            testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(row + col,col));
        }

        row = 0;
        for (int col = 0; col < 5; col++){
            testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(row + col,col));
        }

        assertTrue(diagonal.applyObjectiveRules(testLibrary, 0,0));
    }

    /**
     * Test to check if the objective is not applied with an empty library.
     */
    @Test
    public void doesNotHaveDiagonalEmptyLibrary(){
        assertFalse(diagonal.applyObjectiveRules(testLibrary,0,0));
    }

    /**
     * Test to check if a non-empty library does not meet the objective requirements.
     */
    @Test
    public void doesNotHaveDiagonal(){
        int row = 1;
        testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), testLibrary.getLibrarySpace(row,0));
        for (int col = 1; col < 5; col++){
            testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(row + col,col));
        }

        row = 0;
        testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), testLibrary.getLibrarySpace(row,0));
        for (int col = 1; col < 5; col++){
            testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(row + col,col));
        }

        assertFalse(diagonal.applyObjectiveRules(testLibrary,0,0));
    }

    /**
     * Test to check if a non-empty library does not meet the objective requirements.
     */
    @Test
    public void doesNotHaveDiagonalFirstRowEmpty(){
        int row = 1;
        testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), testLibrary.getLibrarySpace(row,0));
        for (int col = 1; col < 5; col++){
            testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(row + col,col));
        }

        assertFalse(diagonal.applyObjectiveRules(testLibrary,0,0));
    }

    @Test
    public void getDescription(){
        String description = " " + Colours.UNDERLINED + " " + Colours.RESET + " \n" +
                "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + " " + Colours.RESET + " \n" +
                "  |" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + " " + Colours.RESET + " \n" +
                "    |" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + " " + Colours.RESET + "     Five tiles of the same type forming a diagonal.\n" +
                "      |" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + " " + Colours.RESET + " \n" +
                "        |" + Colours.UNDERLINED + "■" + Colours.RESET + "|";

        assertEquals(description, diagonal.getDescription());
    }

}