package model.commonobjective;



import model.library.*;
import model.objects.ObjectCard;
import org.junit.*;

import static org.junit.Assert.*;

public class TotalDifferentColumnsTest  {

    private TotalDifferentColumns totalDifferentColumns;

    private Library instance;


    @Before
    public void setUp() {
        totalDifferentColumns = new TotalDifferentColumns();

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
        totalDifferentColumns=null;
    }

    @Test
    public void NoDifferentRows(){
        totalDifferentColumns.applyObjectiveRules(instance, 3, 4);
        if (totalDifferentColumns.getCount() < 2)
            assertFalse(totalDifferentColumns.applyObjectiveRules(instance, 3, 4));

    }

    @Test
    public void DifferentRows(){
        totalDifferentColumns.applyObjectiveRules(instance, 3, 4);
        if (totalDifferentColumns.getCount()>= 2)
            assertTrue(totalDifferentColumns.applyObjectiveRules(instance, 3, 4));
    }

}