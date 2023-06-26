package model.commonobjective;

import model.library.*;
import model.objects.ObjectCard;
import org.junit.*;
import view.cli.Colours;

import static org.junit.Assert.*;

public class TotalDifferentColumnsTest  {
    private Library testLibrary;
    private TotalDifferentColumns totalDifferentColumns;

    @Before
    public void setUp() {
        testLibrary = new Library();
        totalDifferentColumns = new TotalDifferentColumns();
    }

    @Test
    public void oneColumnHasEmptyCell(){
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(0,1));
        testLibrary.addObject(new ObjectCard("PINK1"),testLibrary.getLibrarySpace(1,1));
        testLibrary.addObject(new ObjectCard("BLUE1"),testLibrary.getLibrarySpace(2,1));
        testLibrary.addObject(new ObjectCard("LIGHT_BLUE1"),testLibrary.getLibrarySpace(3,1));
        testLibrary.addObject(new ObjectCard("YELLOW1"),testLibrary.getLibrarySpace(4,1));
        testLibrary.addObject(new ObjectCard("WHITE1"),testLibrary.getLibrarySpace(5,1));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(0,2));
        testLibrary.addObject(new ObjectCard("LIGHT_BLUE1"),testLibrary.getLibrarySpace(1,2));
        testLibrary.addObject(new ObjectCard("PINK1"),testLibrary.getLibrarySpace(2,2));
        testLibrary.addObject(new ObjectCard("BLUE1"),testLibrary.getLibrarySpace(3,2));
        testLibrary.addObject(new ObjectCard("YELLOW1"),testLibrary.getLibrarySpace(4,2));

        assertFalse(totalDifferentColumns.applyObjectiveRules(testLibrary, 0, 0));
    }


    @Test
    public void noDifferentColumns(){

        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(0,1));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(1,1));
        testLibrary.addObject(new ObjectCard("BLUE1"),testLibrary.getLibrarySpace(2,1));
        testLibrary.addObject(new ObjectCard("WHITE1"),testLibrary.getLibrarySpace(3,1));
        testLibrary.addObject(new ObjectCard("YELLOW1"),testLibrary.getLibrarySpace(4,1));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(5,1));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(0,2));
        testLibrary.addObject(new ObjectCard("PINK1"),testLibrary.getLibrarySpace(1,2));
        testLibrary.addObject(new ObjectCard("BLUE1"),testLibrary.getLibrarySpace(2,2));
        testLibrary.addObject(new ObjectCard("WHITE1"),testLibrary.getLibrarySpace(3,2));
        testLibrary.addObject(new ObjectCard("WHITE1"),testLibrary.getLibrarySpace(4,2));
        testLibrary.addObject(new ObjectCard("WHITE1"),testLibrary.getLibrarySpace(5,2));

        assertFalse(totalDifferentColumns.applyObjectiveRules(testLibrary, 0, 0));

    }

    @Test
    public void hasDifferentColumns(){
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(0,1));
        testLibrary.addObject(new ObjectCard("PINK1"),testLibrary.getLibrarySpace(1,1));
        testLibrary.addObject(new ObjectCard("BLUE1"),testLibrary.getLibrarySpace(2,1));
        testLibrary.addObject(new ObjectCard("LIGHT_BLUE1"),testLibrary.getLibrarySpace(3,1));
        testLibrary.addObject(new ObjectCard("YELLOW1"),testLibrary.getLibrarySpace(4,1));
        testLibrary.addObject(new ObjectCard("WHITE1"),testLibrary.getLibrarySpace(5,1));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(0,2));
        testLibrary.addObject(new ObjectCard("LIGHT_BLUE1"),testLibrary.getLibrarySpace(1,2));
        testLibrary.addObject(new ObjectCard("PINK1"),testLibrary.getLibrarySpace(2,2));
        testLibrary.addObject(new ObjectCard("BLUE1"),testLibrary.getLibrarySpace(3,2));
        testLibrary.addObject(new ObjectCard("YELLOW1"),testLibrary.getLibrarySpace(4,2));
        testLibrary.addObject(new ObjectCard("WHITE1"),testLibrary.getLibrarySpace(5,2));

        assertTrue(totalDifferentColumns.applyObjectiveRules(testLibrary, 0, 0));
    }

    @Test
    public void tooManyDifferentColumns(){
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(0,1));
        testLibrary.addObject(new ObjectCard("PINK1"),testLibrary.getLibrarySpace(1,1));
        testLibrary.addObject(new ObjectCard("BLUE1"),testLibrary.getLibrarySpace(2,1));
        testLibrary.addObject(new ObjectCard("LIGHT_BLUE1"),testLibrary.getLibrarySpace(3,1));
        testLibrary.addObject(new ObjectCard("YELLOW1"),testLibrary.getLibrarySpace(4,1));
        testLibrary.addObject(new ObjectCard("WHITE1"),testLibrary.getLibrarySpace(5,1));

        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(0,2));
        testLibrary.addObject(new ObjectCard("LIGHT_BLUE1"),testLibrary.getLibrarySpace(1,2));
        testLibrary.addObject(new ObjectCard("PINK1"),testLibrary.getLibrarySpace(2,2));
        testLibrary.addObject(new ObjectCard("BLUE1"),testLibrary.getLibrarySpace(3,2));
        testLibrary.addObject(new ObjectCard("YELLOW1"),testLibrary.getLibrarySpace(4,2));
        testLibrary.addObject(new ObjectCard("WHITE1"),testLibrary.getLibrarySpace(5,2));

        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(0,3));
        testLibrary.addObject(new ObjectCard("LIGHT_BLUE1"),testLibrary.getLibrarySpace(1,3));
        testLibrary.addObject(new ObjectCard("PINK1"),testLibrary.getLibrarySpace(2,3));
        testLibrary.addObject(new ObjectCard("BLUE1"),testLibrary.getLibrarySpace(3,3));
        testLibrary.addObject(new ObjectCard("YELLOW1"),testLibrary.getLibrarySpace(4,3));
        testLibrary.addObject(new ObjectCard("WHITE1"),testLibrary.getLibrarySpace(5,3));

        assertFalse(totalDifferentColumns.applyObjectiveRules(testLibrary, 0, 0));
    }

    @Test
    public void getDescription(){
        String description = " " + Colours.UNDERLINED + " " + Colours.RESET + "\n" +
                "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|\n" +
                "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|\n" +
                "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|  Two columns each formed by 6\n" +
                "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|  different types of tiles.\n" +
                "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|\n" +
                "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|\n" +
                " x2";

        assertEquals(description, totalDifferentColumns.getDescription());
    }

}