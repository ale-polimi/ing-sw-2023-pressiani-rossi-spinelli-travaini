package model.commonobjective;

import enumerations.ObjectColour;
import model.library.Library;

import model.library.LibrarySpace;
import model.objects.ObjectCard;
import org.junit.*;

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
            }
        }

        //instance initialized with known elements

        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(0,0));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(0,1));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(0,2));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(0,3));
        instance.addObject(new ObjectCard("WHITE1"),instance.getLibrarySpace(0,4));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(1,0));
        instance.addObject(new ObjectCard("BLUE1"),instance.getLibrarySpace(1,1));
        instance.addObject(new ObjectCard("BLUE1"),instance.getLibrarySpace(1,2));
        instance.addObject(new ObjectCard("BLUE1"),instance.getLibrarySpace(1,3));
        instance.addObject(new ObjectCard("BLUE1"),instance.getLibrarySpace(1,4));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(2,0));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(2,1));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(2,2));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(2,3));
        instance.addObject(new ObjectCard("YELLOW1"),instance.getLibrarySpace(2,4));
        instance.addObject(new ObjectCard("LIGHT_BLUE1"),instance.getLibrarySpace(3,0));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(3,1));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(3,2));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(3,3));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(3,4));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(4,0));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(4,1));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(4,2));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(4,3));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(4,4));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(5,0));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(5,1));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(5,2));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(5,3));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(5,4));

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
        int x=0;
        int y=0;
        if (x>1 && (y!=0 || y!=4))
            assertFalse(diagonal.applyObjectiveRules(instance, x, y));
    }

}