package model.commonobjective;

import enumerations.ObjectColour;
import model.library.Library;

import junit.framework.TestCase;
import model.library.LibrarySpace;
import model.objects.ObjectCard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.library.Library;

import static org.junit.jupiter.api.Assertions.*;
public class TwoEqualsInColumnTest  {
    TwoEqualsInColumn  twoEqualsInColumn;
    Library testLibrary;
    @BeforeEach
    public void setUp() {

        testLibrary=new Library();
        twoEqualsInColumn= new TwoEqualsInColumn();


    }

    @AfterEach
    public void tearDown() {
        twoEqualsInColumn=null;
    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void isTwoEqualsInColumn(){
        int x = 0;
        int y = 0;

        testLibrary.addObject(new ObjectCard(ObjectColour.GREEN3), testLibrary.getLibrarySpace(0, 0));
        testLibrary.addObject(new ObjectCard(ObjectColour.GREEN3), testLibrary.getLibrarySpace(1, 0));

        assertTrue(twoEqualsInColumn.applyObjectiveRules(testLibrary,x,y));
        System.out.println(twoEqualsInColumn.getDescription());
    }

    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void isNotTwoEqualsInColumn(){
        int x = 0;
        int y = 0;

        testLibrary.addObject(new ObjectCard(ObjectColour.GREEN3), testLibrary.getLibrarySpace(0, 0));
        testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), testLibrary.getLibrarySpace(1, 0));

        assertFalse(twoEqualsInColumn.applyObjectiveRules(testLibrary,x,y));
    }
    /**
     * Test to check if the objective correctly applies its rules.
     */
    @Test
    public void isNotTwoEqualsInColumnEmpty(){
        int x = 0;
        int y = 0;

        assertFalse(twoEqualsInColumn.applyObjectiveRules(testLibrary,x,y));
    }
}
