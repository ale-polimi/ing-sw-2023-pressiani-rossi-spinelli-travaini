package model.commonobjective;

import model.library.LibrarySpace;
import model.objects.ObjectCard;
import org.junit.*;

import static org.junit.Assert.*;

import model.library.Library;
import view.cli.Colours;

public class TotalDifferentRowsTest {
    private TotalDifferentRows totalDifferentRows;
    private Library testTotDiffRows;
    @Before
    public void setUp() {
        totalDifferentRows = new TotalDifferentRows();
        testTotDiffRows = new Library();
    }

    @Test
    public void oneRowHasEmptyCell(){
        testTotDiffRows.addObject(new ObjectCard("GREEN1"),testTotDiffRows.getLibrarySpace(0,0));
        testTotDiffRows.addObject(new ObjectCard("PINK1"),testTotDiffRows.getLibrarySpace(0,1));
        testTotDiffRows.addObject(new ObjectCard("BLUE1"),testTotDiffRows.getLibrarySpace(0,2));
        testTotDiffRows.addObject(new ObjectCard("WHITE1"),testTotDiffRows.getLibrarySpace(0,3));
        testTotDiffRows.addObject(new ObjectCard("YELLOW1"),testTotDiffRows.getLibrarySpace(0,4));
        testTotDiffRows.addObject(new ObjectCard("GREEN1"),testTotDiffRows.getLibrarySpace(1,0));
        testTotDiffRows.addObject(new ObjectCard("LIGHT_BLUE1"),testTotDiffRows.getLibrarySpace(1,1));
        testTotDiffRows.addObject(new ObjectCard("PINK1"),testTotDiffRows.getLibrarySpace(1,2));
        testTotDiffRows.addObject(new ObjectCard("BLUE1"),testTotDiffRows.getLibrarySpace(1,3));

        assertFalse(totalDifferentRows.applyObjectiveRules(testTotDiffRows,0,0));
    }

    @Test
    public void NoDifferentRows(){
        testTotDiffRows.addObject(new ObjectCard("GREEN1"),testTotDiffRows.getLibrarySpace(2,0));
        testTotDiffRows.addObject(new ObjectCard("GREEN1"),testTotDiffRows.getLibrarySpace(2,1));
        testTotDiffRows.addObject(new ObjectCard("BLUE1"),testTotDiffRows.getLibrarySpace(2,2));
        testTotDiffRows.addObject(new ObjectCard("WHITE1"),testTotDiffRows.getLibrarySpace(2,3));
        testTotDiffRows.addObject(new ObjectCard("YELLOW1"),testTotDiffRows.getLibrarySpace(2,4));
        testTotDiffRows.addObject(new ObjectCard("GREEN1"),testTotDiffRows.getLibrarySpace(1,0));
        testTotDiffRows.addObject(new ObjectCard("GREEN1"),testTotDiffRows.getLibrarySpace(1,1));
        testTotDiffRows.addObject(new ObjectCard("PINK1"),testTotDiffRows.getLibrarySpace(1,2));
        testTotDiffRows.addObject(new ObjectCard("BLUE1"),testTotDiffRows.getLibrarySpace(1,3));
        testTotDiffRows.addObject(new ObjectCard("WHITE1"),testTotDiffRows.getLibrarySpace(1,4));


        assertFalse(totalDifferentRows.applyObjectiveRules(testTotDiffRows, 0, 0));

    }

    @Test
    public void NoDifferentRowsFromGame(){
        testTotDiffRows.addObject(new ObjectCard("PINK1"),testTotDiffRows.getLibrarySpace(0,0));
        testTotDiffRows.addObject(new ObjectCard("LIGHT_BLUE1"),testTotDiffRows.getLibrarySpace(0,1));
        testTotDiffRows.addObject(new ObjectCard("BLUE2"),testTotDiffRows.getLibrarySpace(0,2));
        testTotDiffRows.addObject(new ObjectCard("BLUE2"),testTotDiffRows.getLibrarySpace(1,0));
        testTotDiffRows.addObject(new ObjectCard("BLUE3"),testTotDiffRows.getLibrarySpace(1,1));
        testTotDiffRows.addObject(new ObjectCard("GREEN1"),testTotDiffRows.getLibrarySpace(1,2));
        testTotDiffRows.addObject(new ObjectCard("LIGHT_BLUE1"),testTotDiffRows.getLibrarySpace(2,0));
        testTotDiffRows.addObject(new ObjectCard("YELLOW1"),testTotDiffRows.getLibrarySpace(2,1));
        testTotDiffRows.addObject(new ObjectCard("LIGHT_BLUE3"),testTotDiffRows.getLibrarySpace(2,2));
        testTotDiffRows.addObject(new ObjectCard("GREEN2"),testTotDiffRows.getLibrarySpace(3,0));
        testTotDiffRows.addObject(new ObjectCard("BLUE1"),testTotDiffRows.getLibrarySpace(3,1));
        testTotDiffRows.addObject(new ObjectCard("WHITE2"),testTotDiffRows.getLibrarySpace(3,2));
        testTotDiffRows.addObject(new ObjectCard("LIGHT_BLUE3"),testTotDiffRows.getLibrarySpace(3,3));
        testTotDiffRows.addObject(new ObjectCard("WHITE2"),testTotDiffRows.getLibrarySpace(4,0));
        testTotDiffRows.addObject(new ObjectCard("LIGHT_BLUE1"),testTotDiffRows.getLibrarySpace(4,1));
        testTotDiffRows.addObject(new ObjectCard("PINK1"),testTotDiffRows.getLibrarySpace(4,2));
        testTotDiffRows.addObject(new ObjectCard("PINK1"),testTotDiffRows.getLibrarySpace(4,3));
        testTotDiffRows.addObject(new ObjectCard("LIGHT_BLUE3"),testTotDiffRows.getLibrarySpace(5,0));
        testTotDiffRows.addObject(new ObjectCard("YELLOW2"),testTotDiffRows.getLibrarySpace(5,1));
        testTotDiffRows.addObject(new ObjectCard("BLUE1"),testTotDiffRows.getLibrarySpace(5,2));
        testTotDiffRows.addObject(new ObjectCard("WHITE3"),testTotDiffRows.getLibrarySpace(5,3));

        assertFalse(totalDifferentRows.applyObjectiveRules(testTotDiffRows, 0, 0));

    }

    @Test
    public void NoDifferentRowsFromGame2(){
        testTotDiffRows.addObject(new ObjectCard("BLUE1"),testTotDiffRows.getLibrarySpace(0,1));
        testTotDiffRows.addObject(new ObjectCard("BLUE1"),testTotDiffRows.getLibrarySpace(0,2));
        testTotDiffRows.addObject(new ObjectCard("PINK1"),testTotDiffRows.getLibrarySpace(0,3));
        testTotDiffRows.addObject(new ObjectCard("WHITE1"),testTotDiffRows.getLibrarySpace(0,4));
        testTotDiffRows.addObject(new ObjectCard("BLUE3"),testTotDiffRows.getLibrarySpace(1,1));
        testTotDiffRows.addObject(new ObjectCard("LIGHT_BLUE1"),testTotDiffRows.getLibrarySpace(1,2));
        testTotDiffRows.addObject(new ObjectCard("GREEN1"),testTotDiffRows.getLibrarySpace(1,3));
        testTotDiffRows.addObject(new ObjectCard("LIGHT_BLUE2"),testTotDiffRows.getLibrarySpace(1,4));
        testTotDiffRows.addObject(new ObjectCard("GREEN3"),testTotDiffRows.getLibrarySpace(2,1));
        testTotDiffRows.addObject(new ObjectCard("PINK2"),testTotDiffRows.getLibrarySpace(2,2));
        testTotDiffRows.addObject(new ObjectCard("GREEN1"),testTotDiffRows.getLibrarySpace(2,3));
        testTotDiffRows.addObject(new ObjectCard("GREEN3"),testTotDiffRows.getLibrarySpace(2,4));
        testTotDiffRows.addObject(new ObjectCard("GREEN2"),testTotDiffRows.getLibrarySpace(3,1));
        testTotDiffRows.addObject(new ObjectCard("GREEN1"),testTotDiffRows.getLibrarySpace(3,2));
        testTotDiffRows.addObject(new ObjectCard("GREEN2"),testTotDiffRows.getLibrarySpace(3,3));
        testTotDiffRows.addObject(new ObjectCard("WHITE1"),testTotDiffRows.getLibrarySpace(3,4));
        testTotDiffRows.addObject(new ObjectCard("GREEN3"),testTotDiffRows.getLibrarySpace(4,0));
        testTotDiffRows.addObject(new ObjectCard("BLUE2"),testTotDiffRows.getLibrarySpace(4,1));
        testTotDiffRows.addObject(new ObjectCard("YELLOW2"),testTotDiffRows.getLibrarySpace(4,2));
        testTotDiffRows.addObject(new ObjectCard("LIGHT_BLUE2"),testTotDiffRows.getLibrarySpace(4,3));
        testTotDiffRows.addObject(new ObjectCard("WHITE1"),testTotDiffRows.getLibrarySpace(4,4));
        testTotDiffRows.addObject(new ObjectCard("BLUE1"),testTotDiffRows.getLibrarySpace(5,0));
        testTotDiffRows.addObject(new ObjectCard("YELLOW1"),testTotDiffRows.getLibrarySpace(5,1));
        testTotDiffRows.addObject(new ObjectCard("GREEN3"),testTotDiffRows.getLibrarySpace(5,2));
        testTotDiffRows.addObject(new ObjectCard("WHITE1"),testTotDiffRows.getLibrarySpace(5,3));
        testTotDiffRows.addObject(new ObjectCard("BLUE1"),testTotDiffRows.getLibrarySpace(5,4));

        assertFalse(totalDifferentRows.applyObjectiveRules(testTotDiffRows, 0, 0));

    }

    @Test
    public void NoDifferentRowsSameType(){
        testTotDiffRows.addObject(new ObjectCard("GREEN1"),testTotDiffRows.getLibrarySpace(0,0));
        testTotDiffRows.addObject(new ObjectCard("GREEN2"),testTotDiffRows.getLibrarySpace(0,1));
        testTotDiffRows.addObject(new ObjectCard("BLUE1"),testTotDiffRows.getLibrarySpace(0,2));
        testTotDiffRows.addObject(new ObjectCard("WHITE1"),testTotDiffRows.getLibrarySpace(0,3));
        testTotDiffRows.addObject(new ObjectCard("YELLOW1"),testTotDiffRows.getLibrarySpace(0,4));
        testTotDiffRows.addObject(new ObjectCard("GREEN1"),testTotDiffRows.getLibrarySpace(1,0));
        testTotDiffRows.addObject(new ObjectCard("LIGHT_BLUE1"),testTotDiffRows.getLibrarySpace(1,1));
        testTotDiffRows.addObject(new ObjectCard("PINK1"),testTotDiffRows.getLibrarySpace(1,2));
        testTotDiffRows.addObject(new ObjectCard("BLUE1"),testTotDiffRows.getLibrarySpace(1,3));
        testTotDiffRows.addObject(new ObjectCard("WHITE1"),testTotDiffRows.getLibrarySpace(1,4));

        assertFalse(totalDifferentRows.applyObjectiveRules(testTotDiffRows, 0, 0));
    }

    @Test
    public void DifferentRows(){
        testTotDiffRows.addObject(new ObjectCard("GREEN1"),testTotDiffRows.getLibrarySpace(0,0));
        testTotDiffRows.addObject(new ObjectCard("PINK1"),testTotDiffRows.getLibrarySpace(0,1));
        testTotDiffRows.addObject(new ObjectCard("BLUE1"),testTotDiffRows.getLibrarySpace(0,2));
        testTotDiffRows.addObject(new ObjectCard("WHITE1"),testTotDiffRows.getLibrarySpace(0,3));
        testTotDiffRows.addObject(new ObjectCard("YELLOW1"),testTotDiffRows.getLibrarySpace(0,4));
        testTotDiffRows.addObject(new ObjectCard("GREEN1"),testTotDiffRows.getLibrarySpace(1,0));
        testTotDiffRows.addObject(new ObjectCard("LIGHT_BLUE1"),testTotDiffRows.getLibrarySpace(1,1));
        testTotDiffRows.addObject(new ObjectCard("PINK1"),testTotDiffRows.getLibrarySpace(1,2));
        testTotDiffRows.addObject(new ObjectCard("BLUE1"),testTotDiffRows.getLibrarySpace(1,3));
        testTotDiffRows.addObject(new ObjectCard("WHITE1"),testTotDiffRows.getLibrarySpace(1,4));

        assertTrue(totalDifferentRows.applyObjectiveRules(testTotDiffRows, 0, 0));
    }
    @Test
    public void tooManyDifferentRows(){
        testTotDiffRows.addObject(new ObjectCard("GREEN1"),testTotDiffRows.getLibrarySpace(0,0));
        testTotDiffRows.addObject(new ObjectCard("PINK1"),testTotDiffRows.getLibrarySpace(0,1));
        testTotDiffRows.addObject(new ObjectCard("BLUE1"),testTotDiffRows.getLibrarySpace(0,2));
        testTotDiffRows.addObject(new ObjectCard("WHITE1"),testTotDiffRows.getLibrarySpace(0,3));
        testTotDiffRows.addObject(new ObjectCard("YELLOW1"),testTotDiffRows.getLibrarySpace(0,4));
        testTotDiffRows.addObject(new ObjectCard("GREEN1"),testTotDiffRows.getLibrarySpace(1,0));
        testTotDiffRows.addObject(new ObjectCard("LIGHT_BLUE1"),testTotDiffRows.getLibrarySpace(1,1));
        testTotDiffRows.addObject(new ObjectCard("PINK1"),testTotDiffRows.getLibrarySpace(1,2));
        testTotDiffRows.addObject(new ObjectCard("BLUE1"),testTotDiffRows.getLibrarySpace(1,3));
        testTotDiffRows.addObject(new ObjectCard("WHITE1"),testTotDiffRows.getLibrarySpace(1,4));
        testTotDiffRows.addObject(new ObjectCard("GREEN1"),testTotDiffRows.getLibrarySpace(2,0));
        testTotDiffRows.addObject(new ObjectCard("LIGHT_BLUE1"),testTotDiffRows.getLibrarySpace(2,1));
        testTotDiffRows.addObject(new ObjectCard("PINK1"),testTotDiffRows.getLibrarySpace(2,2));
        testTotDiffRows.addObject(new ObjectCard("BLUE1"),testTotDiffRows.getLibrarySpace(2,3));
        testTotDiffRows.addObject(new ObjectCard("WHITE1"),testTotDiffRows.getLibrarySpace(2,4));

        assertFalse(totalDifferentRows.applyObjectiveRules(testTotDiffRows, 0, 0));
    }

    @Test
    public void getDescription(){
        String description = " " + Colours.UNDERLINED + " " + Colours.RESET + " " + Colours.UNDERLINED + " " + Colours.RESET + " " + Colours.UNDERLINED + " " + Colours.RESET + " " + Colours.UNDERLINED + " " + Colours.RESET + " " + Colours.UNDERLINED + " " + Colours.RESET + "   Two lines each formed by 5 different\n" +
                "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|  types of tiles. One line can show the\n" +
                "     x2      same or a different combination of the\n" +
                "             other line.";

        assertEquals(description, totalDifferentRows.getDescription());
    }
}