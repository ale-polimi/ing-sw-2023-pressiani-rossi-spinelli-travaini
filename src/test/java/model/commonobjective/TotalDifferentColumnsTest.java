package model.commonobjective;

import junit.framework.TestCase;

import model.library.*;
import org.junit.*;

public class TotalDifferentColumnsTest extends TestCase {

    private TotalDifferentColumns totalDifferentColumns;

    private Library instance;


    @Before
    public void setUp() {
        totalDifferentColumns = new TotalDifferentColumns();

        instance = new Library();
    }

    @After
    public void tearDown() {
        totalDifferentColumns=null;
    }

    @Test
    public void NoDifferentRows(){
        totalDifferentColumns.applyObjectiveRules(instance, 3, 4);
        if (totalDifferentColumns.getCount() < 2)
            assertFalse(totalDifferentColumns.applyObjectiveRules(instance, 3, 4));

    }

    @Test
    public void DifferentRows(){
        totalDifferentColumns.applyObjectiveRules(instance, 3, 4);
        if (totalDifferentColumns.getCount()>= 2)
            assertTrue(totalDifferentColumns.applyObjectiveRules(instance, 3, 4));
    }

}