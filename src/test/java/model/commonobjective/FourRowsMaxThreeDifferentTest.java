package model.commonobjective;

import model.library.Library;

import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enumerations.ObjectColour;
import model.library.Library;

import static org.junit.jupiter.api.Assertions.*;
public class FourRowsMaxThreeDifferentTest extends TestCase{
    FourRowsMaxThreeDifferent  fourRowsMaxThreeDifferent;
    Library instance;
    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {fourRowsMaxThreeDifferent=null;
    }

    @Test
    public void isFourRowsMaxThreeDifferent(){
        int x = 0;
        int y = 0;
        fourRowsMaxThreeDifferent.applyObjectiveRules(instance,x,y);
        if(fourRowsMaxThreeDifferent.getCountObj()==4)
            assertTrue(fourRowsMaxThreeDifferent.applyObjectiveRules(instance,x,y));
    }
    @Test
    public void isNotFourRowsMaxThreeDifferent(){
        int x = 0;
        int y = 0;
        fourRowsMaxThreeDifferent.applyObjectiveRules(instance,x,y);
        if(fourRowsMaxThreeDifferent.getCountObj()<4)
            assertFalse(fourRowsMaxThreeDifferent.applyObjectiveRules(instance,x,y));
    }
}
