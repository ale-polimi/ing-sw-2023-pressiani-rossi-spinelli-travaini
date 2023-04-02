package model.commonobjective;


import model.library.Library;

import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enumerations.ObjectColour;
import model.library.Library;

import static org.junit.jupiter.api.Assertions.*;
public class FourByFourTest extends TestCase{
    FourByFour  fourByFour;
    Library instance;
    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
        fourByFour=null;
    }

    @Test
    public void isFourByFour(){
        int x = 0;
        int y = 0;
        fourByFour.applyObjectiveRules(instance,x,y);
        if(fourByFour.getCount()==4)
            assertTrue(fourByFour.applyObjectiveRules(instance,x,y));
    }

    @Test
    public void isNotFourByFour(){
        int x = 0;
        int y = 0;
        fourByFour.applyObjectiveRules(instance,x,y);
        if(fourByFour.getCount()<4)
            assertFalse(fourByFour.applyObjectiveRules(instance,x,y));
    }

}
