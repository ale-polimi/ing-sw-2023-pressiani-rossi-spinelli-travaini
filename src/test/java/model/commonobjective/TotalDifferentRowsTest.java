package model.commonobjective;

import model.library.LibrarySpace;
import model.objects.ObjectCard;
import org.junit.*;

import static org.junit.Assert.*;

import model.library.Library;

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


        assertFalse(totalDifferentRows.applyObjectiveRules(testTotDiffRows, 3, 4));

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

        assertTrue(totalDifferentRows.applyObjectiveRules(testTotDiffRows, 3, 4));
    }
}