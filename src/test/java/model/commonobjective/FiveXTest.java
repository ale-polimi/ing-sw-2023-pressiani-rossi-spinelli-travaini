package model.commonobjective;

import com.sun.source.tree.AssertTree;
import enumerations.ObjectColour;
import model.library.Library;


import model.objects.ObjectCard;
import org.junit.*;
import static org.junit.Assert.*;


public class FiveXTest  {
       private Library testLibrary;
       private CommonObjective fiveX;

    @Before
    public void setUp() {
        testLibrary = new Library();
        fiveX = new FiveX();
    }

    /**
     * This test checks if there is an X.
     */
    @Test
    public void thereIsX() {
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(5, 0));
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(3, 0));
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(4, 1));
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(5, 2));
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(3, 2));

        assertTrue(this.fiveX.applyObjectiveRules(this.testLibrary, 0,0));
    }

    /**
     * If the library is empty there must be no X.
     */
    @Test
    public void thereIsNoXEmptyLibrary(){
        assertFalse(this.fiveX.applyObjectiveRules(this.testLibrary, 0,0));
    }

    /**
     * This test checks if there is no X with a non-empty library.
     */
    @Test
    public void thereIsNoX (){
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(5, 0));
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(3, 0));
        testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), testLibrary.getLibrarySpace(4, 1));

        assertFalse(this.fiveX.applyObjectiveRules(this.testLibrary, 0,0));
    }
}
