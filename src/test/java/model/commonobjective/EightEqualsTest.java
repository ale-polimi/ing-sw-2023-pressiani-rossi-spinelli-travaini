package model.commonobjective;

import enumerations.ObjectColour;
import model.library.*;
import junit.framework.TestCase;
import model.library.Library;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EightEqualsTest extends TestCase {

    EightEquals  eightEquals;
    Library instance;
    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
        eightEquals=null;
    }

    @Test
    public void isEightEquals() {
        eightEquals.applyObjectiveRules(instance, 0, 0 );
        if (eightEquals.getCount() == 8)
            assertTrue(eightEquals.applyObjectiveRules(instance, 0, 0 ));
    }
    @Test
    public void notEightEquals() {
        eightEquals.applyObjectiveRules(instance, 0, 0 );
        if (eightEquals.getCount() < 8)
            assertFalse(eightEquals.applyObjectiveRules(instance, 0, 0 ));
    }
}

