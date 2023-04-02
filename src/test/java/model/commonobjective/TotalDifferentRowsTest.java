package model.commonobjective;

import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import model.library.Library;
import org.junit.jupiter.api.Test;

public class TotalDifferentRowsTest extends TestCase {

    TotalDifferentRows totalDifferentRows;



    Library instance;
    @BeforeEach
    public void setUp() {
    }

    @AfterEach
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