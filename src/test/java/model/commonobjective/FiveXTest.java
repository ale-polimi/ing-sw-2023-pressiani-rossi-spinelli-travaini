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
