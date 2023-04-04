package model.commonobjective;


import model.library.Library;
import model.library.LibrarySpace;
import model.objects.ObjectCard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class StairsTest {
    Library instance;

    Stairs stairs;

    @Before
    public void setUp() {
        stairs = new Stairs();

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
        stairs = null;
    }

    @Test
    public void isNotStair(){
        stairs.applyObjectiveRules(instance, 0, 0);
        if (instance.getLibrarySpace(3,4).getObject() != null)
            assertFalse(stairs.applyObjectiveRules(instance, 0, 0));
    }

    @Test
    public void isStair(){
        /**/
    }
}