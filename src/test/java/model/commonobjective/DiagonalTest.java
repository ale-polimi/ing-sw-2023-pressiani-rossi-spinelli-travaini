package model.commonobjective;

import enumerations.ObjectColour;
import model.library.Library;

import junit.framework.TestCase;
import org.junit.*;

import enumerations.ObjectColour;
import model.library.Library;

import static junit.framework.Assert.*;

public class DiagonalTest extends TestCase {
    Library instance;
    Diagonal diagonal;
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        diagonal=null;
    }

    @Test
    public void isDiagonal() {
        int x=0;
        int y=0;
        diagonal.applyObjectiveRules(instance, 0, 0);
        for (int i = 0; i < 4; i++) {
            if (instance.getLibrarySpace(x+i,y+i).getObject().getObjectColour().equals(instance.getLibrarySpace(x+i+1,y+i+1).getObject().getObjectColour()))
                assertFalse(diagonal.applyObjectiveRules(instance, 0, 0));
        }
    }

    @Test
    public void isNotDiagonal() {
        int x=0;
        int y=0;
        diagonal.applyObjectiveRules(instance, x, y);
        for (int i = 0; i < 4; i++) {
            if (!instance.getLibrarySpace(x+i,y+i).getObject().getObjectColour().equals(instance.getLibrarySpace(x+i+1,y+i+1).getObject().getObjectColour()))
                assertFalse(diagonal.applyObjectiveRules(instance, 0, 0));
        }
    }

    @Test
    public void coordinateControl(){
        int x=2;
        int y=3;
        if (x>1 && (y!=0 || y!=4))
            assertFalse(diagonal.applyObjectiveRules(instance, x, y));
    }

}