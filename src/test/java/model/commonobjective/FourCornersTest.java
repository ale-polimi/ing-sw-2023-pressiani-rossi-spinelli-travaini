package model.commonobjective;


import enumerations.ObjectColour;
import model.library.Library;
import model.objects.ObjectCard;
import org.junit.*;
import view.cli.Colours;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FourCornersTest {
    Library testLibrary;
    FourCorners  fourCorners;

    @Before
    public void setUp() {
        testLibrary =new Library();
        fourCorners= new FourCorners();
    }

    /**
     * Test to check if the objective is not applied when the library is empty.
     */
    @Test
    public void hasEmptyFourCorners(){
        assertFalse(fourCorners.applyObjectiveRules(testLibrary,0,0));
    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void hasFourCorners(){
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(0,0));
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(0,4));
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(5,0));
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(5,4));

        assertTrue(fourCorners.applyObjectiveRules(testLibrary,0,0));
        System.out.println(fourCorners.getDescription());
    }

    /**
     * Test to check if the objective is not applied when the objects are of different colours.
     */
    @Test
    public void doesNotHaveFourCorners(){
        testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), testLibrary.getLibrarySpace(0,0));
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(0,4));
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(5,0));
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(5,4));

        assertFalse(fourCorners.applyObjectiveRules(testLibrary,0,0));
    }

    @Test
    public void getDescription(){
        String description = " " + Colours.UNDERLINED + " " + Colours.RESET + " " + Colours.UNDERLINED + " " + Colours.RESET + " " + Colours.UNDERLINED + " " + Colours.RESET + " " + Colours.UNDERLINED + " " + Colours.RESET + " " + Colours.UNDERLINED + " " + Colours.RESET + " \n" +
                "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|     |" + Colours.UNDERLINED + "■" + Colours.RESET + "|\n" +
                "|         |\n" +
                "|         |  Four tiles of the same type in the four\n" +
                "|         |  corners of the bookshelf.\n" +
                "|" + Colours.UNDERLINED + " " + Colours.RESET + "       " + Colours.UNDERLINED + " " + Colours.RESET + "|\n" +
                "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|" + Colours.UNDERLINED + " " + Colours.RESET + " " + Colours.UNDERLINED + " " + Colours.RESET + " " + Colours.UNDERLINED + " " + Colours.RESET + "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|";

        assertEquals(description, fourCorners.getDescription());
    }
}
