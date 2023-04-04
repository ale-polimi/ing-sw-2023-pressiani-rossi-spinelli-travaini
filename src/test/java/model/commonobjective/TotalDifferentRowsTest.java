package model.commonobjective;

import junit.framework.TestCase;
import org.junit.*;

import model.library.Library;

public class TotalDifferentRowsTest extends TestCase {

    private TotalDifferentRows totalDifferentRows;

    private Library instance;
    @Before
    public void setUp() {
        totalDifferentRows = new TotalDifferentRows();

        instance = new Library();
    }

    @After
    public void tearDown() {
        totalDifferentRows = null;
    }

    @Test
    public void NoDifferentRows(){
        totalDifferentRows.applyObjectiveRules(instance, 3, 4);
        if (totalDifferentRows.getCount() < 2)
            assertFalse(totalDifferentRows.applyObjectiveRules(instance, 3, 4));

    }

    @Test
    public void DifferentRows(){
        totalDifferentRows.applyObjectiveRules(instance, 3, 4);
        if (totalDifferentRows.getCount()>= 2)
            assertTrue(totalDifferentRows.applyObjectiveRules(instance, 3, 4));
    }



}