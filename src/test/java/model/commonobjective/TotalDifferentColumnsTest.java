package model.commonobjective;

import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import model.library.*;
import org.junit.jupiter.api.Test;

public class TotalDifferentColumnsTest extends TestCase {

    TotalDifferentColumns totalDifferentColumns;

    Library instance;


    @BeforeEach
    public void setUp() {
    }

    @AfterEach
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