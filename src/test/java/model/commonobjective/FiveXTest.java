package model.commonobjective;

import enumerations.ObjectColour;
import model.library.Library;

import junit.framework.TestCase;
import org.junit.*;


public class FiveXTest extends TestCase {
       private Library instance;
       private FiveX fiveX;
    @Before
    public void setUp() {
        fiveX = new FiveX();

        instance = new Library();

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
