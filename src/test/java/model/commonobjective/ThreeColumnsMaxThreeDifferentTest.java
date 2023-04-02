package model.commonobjective;


import model.library.Library;

import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enumerations.ObjectColour;
import model.library.Library;

import static org.junit.jupiter.api.Assertions.*;
public class ThreeColumnsMaxThreeDifferentTest extends TestCase{
    ThreeColumnsMaxThreeDifferent  threeColumnsMaxThreeDifferent;
    Library instance;
    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
        threeColumnsMaxThreeDifferent=null;
    }

    @Test
    public void isThreeColumnsMaxThreeDifferent(){
        int x = 0;
        int y = 0;
        threeColumnsMaxThreeDifferent.applyObjectiveRules(instance,x,y);
        if(threeColumnsMaxThreeDifferent.getCountObj()==3)
            assertTrue(threeColumnsMaxThreeDifferent.applyObjectiveRules(instance,x,y));
    }
    @Test
    public void isNotThreeColumnsMaxThreeDifferent(){
        int x = 0;
        int y = 0;
        threeColumnsMaxThreeDifferent.applyObjectiveRules(instance,x,y);
        if(threeColumnsMaxThreeDifferent.getCountObj()<3)
            assertFalse(threeColumnsMaxThreeDifferent.applyObjectiveRules(instance,x,y));
    }
}
