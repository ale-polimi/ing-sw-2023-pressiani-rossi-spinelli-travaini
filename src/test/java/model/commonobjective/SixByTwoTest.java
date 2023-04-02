package model.commonobjective;

import junit.framework.TestCase;
import enumerations.ObjectColour;
import model.library.Library;

import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.library.Library;

import static org.junit.jupiter.api.Assertions.*;
public class SixByTwoTest extends TestCase {

    SixByTwo  sixByTwo;
    Library instance;
    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
        sixByTwo=null;
    }
    @Test
    public void isSixByTwo(){
        int x = 0;
        int y = 0;
        sixByTwo.applyObjectiveRules(instance,x,y);
        if(sixByTwo.getCount()==6)
            assertTrue(sixByTwo.applyObjectiveRules(instance,x,y));
    }

    @Test
    public void isNotSixByTwo(){
        int x = 0;
        int y = 0;
        sixByTwo.applyObjectiveRules(instance,x,y);
        if(sixByTwo.getCount()<6)
            assertFalse(sixByTwo.applyObjectiveRules(instance,x,y));
    }
}
