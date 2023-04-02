package model.commonobjective;


import model.library.Library;

import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enumerations.ObjectColour;
import model.library.Library;

import static org.junit.jupiter.api.Assertions.*;
public class FourCornersTest extends TestCase{
    FourCorners  fourCorners;
    Library instance;
    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
        fourCorners=null;
    }
    @Test
    public void isFourCorners(){
        int x = 0;
        int y = 0;
        fourCorners.applyObjectiveRules(instance,x,y);
        if(instance.getLibrarySpace(x, y).getObject().getObjectColour().equals(instance.getLibrarySpace(x, y + 4).getObject().getObjectColour()) &&
                instance.getLibrarySpace(x, y).getObject().getObjectColour().equals(instance.getLibrarySpace(x + 5, y).getObject().getObjectColour()) &&
                instance.getLibrarySpace(x, y).getObject().getObjectColour().equals(instance.getLibrarySpace(x + 5, y + 4).getObject().getObjectColour()))
            assertTrue(fourCorners.applyObjectiveRules(instance,x,y));
    }
    @Test
    public void isNotFourCorners(){
        int x = 0;
        int y = 0;
        fourCorners.applyObjectiveRules(instance,x,y);
        if(!instance.getLibrarySpace(x, y).getObject().getObjectColour().equals(instance.getLibrarySpace(x, y + 4).getObject().getObjectColour()) ||
                !instance.getLibrarySpace(x, y).getObject().getObjectColour().equals(instance.getLibrarySpace(x + 5, y).getObject().getObjectColour()) ||
                !instance.getLibrarySpace(x, y).getObject().getObjectColour().equals(instance.getLibrarySpace(x + 5, y + 4).getObject().getObjectColour()))
            assertFalse(fourCorners.applyObjectiveRules(instance,x,y));
    }
}
