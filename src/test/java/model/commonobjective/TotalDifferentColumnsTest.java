package model.commonobjective;



import enumerations.ObjectColour;
import model.library.*;
import model.objects.ObjectCard;
import org.junit.*;

import static org.junit.Assert.*;

public class TotalDifferentColumnsTest  {
    private Library testLibrary;
    private TotalDifferentColumns totalDifferentColumns;

    @Before
    public void setUp() {
        testLibrary = new Library();
        totalDifferentColumns = new TotalDifferentColumns();
    }

    @Test
    public void noDifferentColumns(){
        for(int row = 0; row < 6; row++){
            for(int col = 0; col < 5; col++){
                testLibrary.addObject(new ObjectCard(ObjectColour.));
            }
        }
    }

    @Test
    public void DifferentRows(){
        totalDifferentColumns.applyObjectiveRules(testLibrary, 3, 4);
        if (totalDifferentColumns.getCount()>= 2)
            assertTrue(totalDifferentColumns.applyObjectiveRules(testLibrary, 3, 4));
    }

}