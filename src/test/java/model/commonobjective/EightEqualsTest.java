package model.commonobjective;

import model.library.Library;
import model.objects.ObjectCard;
import org.junit.*;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

public class EightEqualsTest {

    private EightEquals  eightEquals;
    private Library testLibrary;
    @Before
    public void setUp() {
        eightEquals = new EightEquals();
        testLibrary = new Library();

    }

    @After
    public void tearDown() {
        eightEquals=null;
    }

    @Test
    public void isEightEquals() {
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(1,2));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(0,0));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(4,3));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(2,2));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(1,1));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(0,1));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(3,3));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(3,2));

        assertTrue(eightEquals.applyObjectiveRules(testLibrary, 0, 0 ));
    }
    @Test
    public void notEightEquals() {
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(0,0));
        testLibrary.addObject(new ObjectCard("GREEN2"),testLibrary.getLibrarySpace(0,1));
        testLibrary.addObject(new ObjectCard("GREEN3"),testLibrary.getLibrarySpace(0,2));
        testLibrary.addObject(new ObjectCard("PINK1"),testLibrary.getLibrarySpace(0,3));
        testLibrary.addObject(new ObjectCard("PINK2"),testLibrary.getLibrarySpace(0,4));
        testLibrary.addObject(new ObjectCard("PINK3"),testLibrary.getLibrarySpace(1,0));
        testLibrary.addObject(new ObjectCard("BLUE1"),testLibrary.getLibrarySpace(1,1));
        testLibrary.addObject(new ObjectCard("BLUE2"),testLibrary.getLibrarySpace(1,2));
        testLibrary.addObject(new ObjectCard("BLUE3"),testLibrary.getLibrarySpace(1,3));
        testLibrary.addObject(new ObjectCard("WHITE1"),testLibrary.getLibrarySpace(1,4));
        testLibrary.addObject(new ObjectCard("WHITE2"),testLibrary.getLibrarySpace(2,0));
        testLibrary.addObject(new ObjectCard("WHITE3"),testLibrary.getLibrarySpace(2,1));
        testLibrary.addObject(new ObjectCard("LIGHT_BLUE1"),testLibrary.getLibrarySpace(2,2));
        testLibrary.addObject(new ObjectCard("LIGHT_BLUE2"),testLibrary.getLibrarySpace(2,3));
        testLibrary.addObject(new ObjectCard("LIGHT_BLUE3"),testLibrary.getLibrarySpace(2,4));
        testLibrary.addObject(new ObjectCard("YELLOW1"),testLibrary.getLibrarySpace(3,0));
        testLibrary.addObject(new ObjectCard("YELLOW2"),testLibrary.getLibrarySpace(3,1));
        testLibrary.addObject(new ObjectCard("YELLOW3"),testLibrary.getLibrarySpace(3,2));
        testLibrary.addObject(new ObjectCard("GREEN1"),testLibrary.getLibrarySpace(3,3));
        testLibrary.addObject(new ObjectCard("GREEN2"),testLibrary.getLibrarySpace(3,4));
        testLibrary.addObject(new ObjectCard("GREEN3"),testLibrary.getLibrarySpace(4,0));
        testLibrary.addObject(new ObjectCard("BLUE1"),testLibrary.getLibrarySpace(4,1));
        testLibrary.addObject(new ObjectCard("BLUE2"),testLibrary.getLibrarySpace(4,2));
        testLibrary.addObject(new ObjectCard("BLUE3"),testLibrary.getLibrarySpace(4,3));
        testLibrary.addObject(new ObjectCard("PINK1"),testLibrary.getLibrarySpace(4,4));
        testLibrary.addObject(new ObjectCard("PINK2"),testLibrary.getLibrarySpace(5,0));
        testLibrary.addObject(new ObjectCard("PINK3"),testLibrary.getLibrarySpace(5,1));
        testLibrary.addObject(new ObjectCard("WHITE1"),testLibrary.getLibrarySpace(5,2));
        testLibrary.addObject(new ObjectCard("WHITE2"),testLibrary.getLibrarySpace(5,3));
        testLibrary.addObject(new ObjectCard("WHITE3"),testLibrary.getLibrarySpace(5,4));

        assertFalse(eightEquals.applyObjectiveRules(testLibrary, 0, 0 ));
    }

}

