package model.commonobjective;

import model.library.*;
import model.objects.ObjectCard;
import org.junit.*;

import static org.junit.Assert.*;

public class TotalDifferentColumnsTest  {
    private TotalDifferentColumns totalDifferentColumns;
    private Library testTotDiffCol;


    @Before
    public void setUp() {
        totalDifferentColumns = new TotalDifferentColumns();
        testTotDiffCol = new Library();
    }


    @After
    public void tearDown() {
        totalDifferentColumns=null;
    }

    @Test
    public void NoDifferentCols(){

        testTotDiffCol.addObject(new ObjectCard("GREEN1"),testTotDiffCol.getLibrarySpace(0,1));
        testTotDiffCol.addObject(new ObjectCard("GREEN1"),testTotDiffCol.getLibrarySpace(1,1));
        testTotDiffCol.addObject(new ObjectCard("BLUE1"),testTotDiffCol.getLibrarySpace(2,1));
        testTotDiffCol.addObject(new ObjectCard("WHITE1"),testTotDiffCol.getLibrarySpace(3,1));
        testTotDiffCol.addObject(new ObjectCard("YELLOW1"),testTotDiffCol.getLibrarySpace(4,1));
        testTotDiffCol.addObject(new ObjectCard("GREEN1"),testTotDiffCol.getLibrarySpace(5,1));
        testTotDiffCol.addObject(new ObjectCard("GREEN1"),testTotDiffCol.getLibrarySpace(0,2));
        testTotDiffCol.addObject(new ObjectCard("PINK1"),testTotDiffCol.getLibrarySpace(1,2));
        testTotDiffCol.addObject(new ObjectCard("BLUE1"),testTotDiffCol.getLibrarySpace(2,2));
        testTotDiffCol.addObject(new ObjectCard("WHITE1"),testTotDiffCol.getLibrarySpace(3,2));
        testTotDiffCol.addObject(new ObjectCard("WHITE1"),testTotDiffCol.getLibrarySpace(4,2));
        testTotDiffCol.addObject(new ObjectCard("WHITE1"),testTotDiffCol.getLibrarySpace(5,2));

        assertFalse(totalDifferentColumns.applyObjectiveRules(testTotDiffCol, 2, 2));

    }

    @Test
    public void DifferentCols(){
        testTotDiffCol.addObject(new ObjectCard("GREEN1"),testTotDiffCol.getLibrarySpace(0,1));
        testTotDiffCol.addObject(new ObjectCard("PINK1"),testTotDiffCol.getLibrarySpace(1,1));
        testTotDiffCol.addObject(new ObjectCard("BLUE1"),testTotDiffCol.getLibrarySpace(2,1));
        testTotDiffCol.addObject(new ObjectCard("LIGHT_BLUE1"),testTotDiffCol.getLibrarySpace(3,1));
        testTotDiffCol.addObject(new ObjectCard("YELLOW1"),testTotDiffCol.getLibrarySpace(4,1));
        testTotDiffCol.addObject(new ObjectCard("WHITE1"),testTotDiffCol.getLibrarySpace(5,1));
        testTotDiffCol.addObject(new ObjectCard("GREEN1"),testTotDiffCol.getLibrarySpace(0,2));
        testTotDiffCol.addObject(new ObjectCard("LIGHT_BLUE1"),testTotDiffCol.getLibrarySpace(1,2));
        testTotDiffCol.addObject(new ObjectCard("PINK1"),testTotDiffCol.getLibrarySpace(2,2));
        testTotDiffCol.addObject(new ObjectCard("BLUE1"),testTotDiffCol.getLibrarySpace(3,2));
        testTotDiffCol.addObject(new ObjectCard("YELLOW1"),testTotDiffCol.getLibrarySpace(4,2));
        testTotDiffCol.addObject(new ObjectCard("WHITE1"),testTotDiffCol.getLibrarySpace(5,2));

        assertTrue(totalDifferentColumns.applyObjectiveRules(testTotDiffCol, 0, 0));
    }

}