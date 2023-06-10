package model.commonobjective;

import enumerations.ObjectColour;
import model.library.*;

import model.library.Library;
import model.objects.ObjectCard;
import org.junit.*;

import static org.junit.jupiter.api.Assertions.*;

public class EightEqualsTest {
    private Library testLibrary;
    private EightEquals  eightEquals;

    @Before
    public void setUp() {
        testLibrary = new Library();
        eightEquals = new EightEquals();
    }

    @Test
    public void doesNotHaveEightEqualsEmptyLibrary(){
        assertFalse(eightEquals.applyObjectiveRules(testLibrary,0,0));
    }

    @Test
    public void hasEightEquals() {
        for(int row = 0; row < 6; row++){
            for(int col = 0; col < 2; col++){
                testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(row,col));
            }
        }

        assertTrue(eightEquals.applyObjectiveRules(testLibrary,0,0));
    }
    @Test
    public void notEightEquals() {
        for(int row = 0; row < 6; row++){
            testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(row,0));
        }
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(0,1));
        testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), testLibrary.getLibrarySpace(1,1));

        assertFalse(eightEquals.applyObjectiveRules(testLibrary,0,0));
    }
}

