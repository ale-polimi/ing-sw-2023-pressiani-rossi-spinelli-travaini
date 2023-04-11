package model.commonobjective;

import model.library.Library;

import junit.framework.TestCase;
import model.library.LibrarySpace;
import model.objects.ObjectCard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import enumerations.ObjectColour;
import model.library.Library;

import static org.junit.jupiter.api.Assertions.*;
public class FourRowsMaxThreeDifferentTest{
    private FourRowsMaxThreeDifferent  fourRowsMaxThreeDifferent= new FourRowsMaxThreeDifferent() ;
    private Library instance = new Library();


    @Before
    public void setUp() {

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                instance.getLibraryGrid()[i][j] = new LibrarySpace();
            }
        }

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 5; j++) {
                    instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(i,j));
                }
        }
    }

    @After
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
