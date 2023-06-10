package model.commonobjective;

import model.library.*;
import model.objects.ObjectCard;
import org.junit.*;

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
    public void noDifferentColumns(){

        testLibrary.addObject(new ObjectCard("GREEN1"),testTotDiffCol.getLibrarySpace(0,1));
        testLibrary.addObject(new ObjectCard("GREEN1"),testTotDiffCol.getLibrarySpace(1,1));
        testLibrary.addObject(new ObjectCard("BLUE1"),testTotDiffCol.getLibrarySpace(2,1));
        testLibrary.addObject(new ObjectCard("WHITE1"),testTotDiffCol.getLibrarySpace(3,1));
        testLibrary.addObject(new ObjectCard("YELLOW1"),testTotDiffCol.getLibrarySpace(4,1));
        testLibrary.addObject(new ObjectCard("GREEN1"),testTotDiffCol.getLibrarySpace(5,1));
        testLibrary.addObject(new ObjectCard("GREEN1"),testTotDiffCol.getLibrarySpace(0,2));
        testLibrary.addObject(new ObjectCard("PINK1"),testTotDiffCol.getLibrarySpace(1,2));
        testLibrary.addObject(new ObjectCard("BLUE1"),testTotDiffCol.getLibrarySpace(2,2));
        testLibrary.addObject(new ObjectCard("WHITE1"),testTotDiffCol.getLibrarySpace(3,2));
        testLibrary.addObject(new ObjectCard("WHITE1"),testTotDiffCol.getLibrarySpace(4,2));
        testLibrary.addObject(new ObjectCard("WHITE1"),testTotDiffCol.getLibrarySpace(5,2));

        assertFalse(totalDifferentColumns.applyObjectiveRules(testTotDiffCol, 2, 2));

    }

    @Test
    public void hsDifferentColumns(){
        testLibrary.addObject(new ObjectCard("GREEN1"),testTotDiffCol.getLibrarySpace(0,1));
        testLibrary.addObject(new ObjectCard("PINK1"),testTotDiffCol.getLibrarySpace(1,1));
        testLibrary.addObject(new ObjectCard("BLUE1"),testTotDiffCol.getLibrarySpace(2,1));
        testLibrary.addObject(new ObjectCard("LIGHT_BLUE1"),testTotDiffCol.getLibrarySpace(3,1));
        testLibrary.addObject(new ObjectCard("YELLOW1"),testTotDiffCol.getLibrarySpace(4,1));
        testLibrary.addObject(new ObjectCard("WHITE1"),testTotDiffCol.getLibrarySpace(5,1));
        testLibrary.addObject(new ObjectCard("GREEN1"),testTotDiffCol.getLibrarySpace(0,2));
        testLibrary.addObject(new ObjectCard("LIGHT_BLUE1"),testTotDiffCol.getLibrarySpace(1,2));
        testLibrary.addObject(new ObjectCard("PINK1"),testTotDiffCol.getLibrarySpace(2,2));
        testLibrary.addObject(new ObjectCard("BLUE1"),testTotDiffCol.getLibrarySpace(3,2));
        testLibrary.addObject(new ObjectCard("YELLOW1"),testTotDiffCol.getLibrarySpace(4,2));
        testLibrary.addObject(new ObjectCard("WHITE1"),testTotDiffCol.getLibrarySpace(5,2));

        assertTrue(totalDifferentColumns.applyObjectiveRules(testTotDiffCol, 0, 0));
    }

}