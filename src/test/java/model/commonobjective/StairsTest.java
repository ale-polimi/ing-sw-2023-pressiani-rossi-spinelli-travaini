package model.commonobjective;

import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import model.library.Library;
import org.junit.jupiter.api.Test;


public class StairsTest extends TestCase {

    Library instance;

    Stairs stairs;

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
        stairs = null;
    }

    @Test
    public void isNotStair(){
        stairs.applyObjectiveRules(instance, 0, 0);
        if (instance.getLibrarySpace(3,5).getObject() != null)
            assertFalse(stairs.applyObjectiveRules(instance, 0, 0));
    }

    @Test
    public void isStair(){
        stairs.applyObjectiveRules(instance, 0, 0);
        assertTrue(stairs.applyObjectiveRules(instance, 0, 0));
    }
}