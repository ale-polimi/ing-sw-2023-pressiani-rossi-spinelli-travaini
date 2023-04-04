package model.commonobjective;

import enumerations.ObjectColour;
import model.library.Library;


import model.library.LibrarySpace;
import model.objects.ObjectCard;
import org.junit.*;

import static org.junit.Assert.*;


public class FiveXTest  {
       private Library instance;
       private FiveX fiveX;
    @Before
    public void setUp() {
        fiveX = new FiveX();

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
        fiveX=null;
    }

    @Test
    public void thereIsX() {
        ObjectColour colour = ObjectColour.PINK3;
        int x=3;
        int y=3;

        if (instance.getLibrarySpace(x-1,y-1).getObject().getObjectColour().equals(colour) &&
                instance.getLibrarySpace(x-1,y+1).getObject().getObjectColour().equals(colour)&&
                instance.getLibrarySpace(x+1,y-1).getObject().getObjectColour().equals(colour)&&
                instance.getLibrarySpace(x+1,y+1).getObject().getObjectColour().equals(colour))
            assertTrue(fiveX.applyObjectiveRules(instance, 3, 3));
    }


    @Test
    public void thereIsNoX (){
        ObjectColour colour = ObjectColour.PINK3;
        int x=3;
        int y=3;

        if ((!instance.getLibrarySpace(x-1,y-1).getObject().getObjectColour().equals(colour) &&
                instance.getLibrarySpace(x-1,y+1).getObject().getObjectColour().equals(colour)&&
                instance.getLibrarySpace(x+1,y-1).getObject().getObjectColour().equals(colour)&&
                instance.getLibrarySpace(x+1,y+1).getObject().getObjectColour().equals(colour)) ||
                (instance.getLibrarySpace(x-1,y-1).getObject().getObjectColour().equals(colour) &&
                        !instance.getLibrarySpace(x-1,y+1).getObject().getObjectColour().equals(colour)&&
                        instance.getLibrarySpace(x+1,y-1).getObject().getObjectColour().equals(colour)&&
                        instance.getLibrarySpace(x+1,y+1).getObject().getObjectColour().equals(colour)) ||
                (instance.getLibrarySpace(x-1,y-1).getObject().getObjectColour().equals(colour) &&
                        instance.getLibrarySpace(x-1,y+1).getObject().getObjectColour().equals(colour)&&
                        !instance.getLibrarySpace(x+1,y-1).getObject().getObjectColour().equals(colour)&&
                        instance.getLibrarySpace(x+1,y+1).getObject().getObjectColour().equals(colour)) ||
                (instance.getLibrarySpace(x-1,y-1).getObject().getObjectColour().equals(colour) &&
                        instance.getLibrarySpace(x-1,y+1).getObject().getObjectColour().equals(colour)&&
                        instance.getLibrarySpace(x+1,y-1).getObject().getObjectColour().equals(colour)&&
                        !instance.getLibrarySpace(x+1,y+1).getObject().getObjectColour().equals(colour)))
            assertFalse(fiveX.applyObjectiveRules(instance, 3, 3));
    }
}
