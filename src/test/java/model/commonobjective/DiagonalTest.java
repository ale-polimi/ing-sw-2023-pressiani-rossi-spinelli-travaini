package model.commonobjective;

import enumerations.ObjectColour;
import model.library.Library;

import model.library.LibrarySpace;
import model.objects.ObjectCard;
import org.junit.*;

import enumerations.ObjectColour;
import model.library.Library;

import static org.junit.jupiter.api.Assertions.*;

public class DiagonalTest {
    private Library instance;
    private Diagonal diagonal;
    @Before
    public void setUp() {
        diagonal = new Diagonal();

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
        diagonal=null;
    }

    @Test
    public void isDiagonal() {
        int x=0;
        int y=0;
        diagonal.applyObjectiveRules(instance, 0, 0);
        for (int i = 0; i < 4; i++) {
            if (!instance.getLibrarySpace(x+i,y+i).getObject().getObjectColour().equals(instance.getLibrarySpace(x+i+1,y+i+1).getObject().getObjectColour()))
                assertFalse(diagonal.applyObjectiveRules(instance, 0, 0));
        }
    }

    @Test
    public void isNotDiagonal() {
        int x=0;
        int y=0;
        diagonal.applyObjectiveRules(instance, x, y);
        for (int i = 0; i < 4; i++) {
            if (!instance.getLibrarySpace(x+i,y+i).getObject().getObjectColour().equals(instance.getLibrarySpace(x+i+1,y+i+1).getObject().getObjectColour()))
                assertFalse(diagonal.applyObjectiveRules(instance, 0, 0));
        }
    }

    @Test
    public void coordinateControl(){
        int x=2;
        int y=3;
        if (x>1 && (y!=0 || y!=4))
            assertFalse(diagonal.applyObjectiveRules(instance, x, y));
    }

}