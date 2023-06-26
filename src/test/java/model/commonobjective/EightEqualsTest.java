package model.commonobjective;

import enumerations.ObjectColour;
import model.library.Library;
import model.objects.ObjectCard;
import org.junit.*;
import view.cli.Colours;

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
    public void doesNotHaveEightEqualsEmptyLibrary(){
        assertFalse(eightEquals.applyObjectiveRules(testLibrary,0,0));
    }

    @Test
    public void hasEightEquals() {
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
        for(int row = 0; row < 6; row++){
            testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(row,0));
        }
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(0,1));
        testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), testLibrary.getLibrarySpace(1,1));

        assertFalse(eightEquals.applyObjectiveRules(testLibrary,0,0));
    }

    @Test
    public void getDescription(){
        String description = "   " + Colours.UNDERLINED + " " + Colours.RESET + "   "  + Colours.UNDERLINED + " " + Colours.RESET + " \n" +
                " " + Colours.UNDERLINED + " " + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + " "+ Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + " " + Colours.RESET + "   Eight tiles of the same type. There’s no\n" +
                "|" + Colours.UNDERLINED + "■" + Colours.RESET + "| |" + Colours.UNDERLINED + "■" + Colours.RESET + "| |" + Colours.UNDERLINED + "■" + Colours.RESET + "|  restriction about the position of these\n" +
                "|" + Colours.UNDERLINED + "■" + Colours.RESET + "| |" + Colours.UNDERLINED + "■" + Colours.RESET + "| |" + Colours.UNDERLINED + "■" + Colours.RESET + "|  tiles.";

        assertEquals(description, eightEquals.getDescription());
    }
}

