package model.commonobjective;

import enumerations.ObjectColour;
import model.library.*;

import model.library.Library;
import model.objects.ObjectCard;
import org.junit.*;

import static org.junit.jupiter.api.Assertions.*;

public class EightEqualsTest {

    private EightEquals  eightEquals;
    private Library instance;
    @Before
    public void setUp() {
        eightEquals = new EightEquals();

        instance = new Library();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                instance.getLibraryGrid()[i][j] = new LibrarySpace();
                instance.addObject(new ObjectCard("BLUE1"),instance.getLibrarySpace(i,j));
            }
        }

    }

    @After
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

