package model.commonobjective;

import enumerations.ObjectColour;
import model.library.Library;

import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.library.Library;

import static org.junit.jupiter.api.Assertions.*;
public class TwoEqualsInColumnTest extends TestCase {
    TwoEqualsInColumn  twoEqualsInColumn;
    Library instance;
    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
        twoEqualsInColumn=null;
    }

    @Test
    public void isTwoEqualsInColumn(){
        int x = 0;
        int y = 0;
        twoEqualsInColumn.applyObjectiveRules(instance,x,y);
        if(instance.getLibrarySpace(x, y).getObject().getObjectColour().equals(instance.getLibrarySpace(x+1, y ).getObject().getObjectColour()))
            assertTrue(twoEqualsInColumn.applyObjectiveRules(instance,x,y));
    }

    @Test
    public void isNotTwoEqualsInColumn(){
        int x = 0;
        int y = 0;
        twoEqualsInColumn.applyObjectiveRules(instance,x,y);
        if(!instance.getLibrarySpace(x, y).getObject().getObjectColour().equals(instance.getLibrarySpace(x+1, y ).getObject().getObjectColour()))
           assertFalse(twoEqualsInColumn.applyObjectiveRules(instance,x,y));
    }
}
