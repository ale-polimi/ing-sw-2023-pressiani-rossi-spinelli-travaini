package model.commonobjective;

import junit.framework.TestCase;
import enumerations.ObjectColour;
import model.library.Library;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.library.Library;

import static org.junit.jupiter.api.Assertions.*;
public class TwoByFourTest extends TestCase {
    TwoByFour  twoByFour;
    Library instance;
    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
        twoByFour=null;
    }

    @Test
    public void isTwoByFour(){
        int x = 0;
        int y = 0;
        twoByFour.applyObjectiveRules(instance,x,y);
        if(twoByFour.getCountBlue()==2||twoByFour.getCountLightBlue()==2||twoByFour.getCountGreen()==2
        ||twoByFour.getCountPink()==2||twoByFour.getCountWhite()==2||twoByFour.getCountYellow()==2)
            assertTrue(twoByFour.applyObjectiveRules(instance,x,y));
    }

    @Test
    public void isNotTwoByFour(){
        int x = 0;
        int y = 0;
        twoByFour.applyObjectiveRules(instance,x,y);
        if(twoByFour.getCountBlue()!=2&&twoByFour.getCountLightBlue()!=2&&twoByFour.getCountGreen()!=2
                &&twoByFour.getCountPink()!=2&&twoByFour.getCountWhite()!=2&&twoByFour.getCountYellow()!=2)
            assertFalse(twoByFour.applyObjectiveRules(instance,x,y));
    }
}
