package model.commonobjective;

import junit.framework.TestCase;
import org.junit.*;

import model.library.Library;



public class StairsTest extends TestCase {

    Library instance;

    Stairs stairs;

    @Before
    public void setUp() {
        stairs = new Stairs();

        instance = new Library();
    }

    @After
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