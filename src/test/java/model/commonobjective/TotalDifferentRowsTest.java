package model.commonobjective;

import model.library.LibrarySpace;
import model.objects.ObjectCard;
import org.junit.*;

import static org.junit.Assert.*;

import model.library.Library;

public class TotalDifferentRowsTest {

    private TotalDifferentRows totalDifferentRows;

    private Library instance;
    @Before
    public void setUp() {
        totalDifferentRows = new TotalDifferentRows();

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
        totalDifferentRows = null;
    }

    @Test
    public void NoDifferentRows(){
        totalDifferentRows.applyObjectiveRules(instance, 3, 4);
        if (totalDifferentRows.getCount() < 2)
            assertFalse(totalDifferentRows.applyObjectiveRules(instance, 3, 4));

    }

    @Test
    public void DifferentRows(){
        totalDifferentRows.applyObjectiveRules(instance, 3, 4);
        if (totalDifferentRows.getCount()>= 2)
            assertTrue(totalDifferentRows.applyObjectiveRules(instance, 3, 4));
    }



}