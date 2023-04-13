package model.commonobjective;


import model.library.Library;

import junit.framework.TestCase;
import model.library.LibrarySpace;
import model.objects.ObjectCard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enumerations.ObjectColour;
import model.library.Library;

import static org.junit.jupiter.api.Assertions.*;
public class ThreeColumnsMaxThreeDifferentTest extends TestCase{
    ThreeColumnsMaxThreeDifferent  threeColumnsMaxThreeDifferent;
    Library instance;
    @BeforeEach
    public void setUp() {
        instance=new Library();
        threeColumnsMaxThreeDifferent= new ThreeColumnsMaxThreeDifferent();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                instance.getLibraryGrid()[i][j] = new LibrarySpace();
            }
        }

        instance.addObject(new ObjectCard("GREEN2"),instance.getLibrarySpace(0,0));
        instance.addObject(new ObjectCard("GREEN3"),instance.getLibrarySpace(0,1));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(0,2));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(0,3));
        instance.addObject(new ObjectCard("WHITE1"),instance.getLibrarySpace(0,4));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(1,0));
        instance.addObject(new ObjectCard("BLUE1"),instance.getLibrarySpace(1,1));
        instance.addObject(new ObjectCard("BLUE3"),instance.getLibrarySpace(1,2));
        instance.addObject(new ObjectCard("BLUE1"),instance.getLibrarySpace(1,3));
        instance.addObject(new ObjectCard("BLUE1"),instance.getLibrarySpace(1,4));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(2,0));
        instance.addObject(new ObjectCard("YELLOW1"),instance.getLibrarySpace(2,1));
        instance.addObject(new ObjectCard("WHITE1"),instance.getLibrarySpace(2,2));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(2,3));
        instance.addObject(new ObjectCard("YELLOW1"),instance.getLibrarySpace(2,4));
        instance.addObject(new ObjectCard("LIGHT_BLUE1"),instance.getLibrarySpace(3,0));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(3,1));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(3,2));
        instance.addObject(new ObjectCard("WHITE1"),instance.getLibrarySpace(3,3));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(3,4));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(4,0));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(4,1));
        instance.addObject(new ObjectCard("GREEN3"),instance.getLibrarySpace(4,2));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(4,3));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(4,4));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(5,0));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(5,1));
        instance.addObject(new ObjectCard("GREEN2"),instance.getLibrarySpace(5,2));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(5,3));
        instance.addObject(new ObjectCard("GREEN1"),instance.getLibrarySpace(5,4));
    }

    @AfterEach
    public void tearDown() {
        threeColumnsMaxThreeDifferent=null;
    }

    @Test
    public void isThreeColumnsMaxThreeDifferent(){
        int x = 0;
        int y = 0;
        threeColumnsMaxThreeDifferent.applyObjectiveRules(instance,x,y);
        if(threeColumnsMaxThreeDifferent.getCountObj()==3)
            assertTrue(threeColumnsMaxThreeDifferent.applyObjectiveRules(instance,x,y));
    }
    @Test
    public void isNotThreeColumnsMaxThreeDifferent(){
        int x = 0;
        int y = 0;
        threeColumnsMaxThreeDifferent.applyObjectiveRules(instance,x,y);
        if(threeColumnsMaxThreeDifferent.getCountObj()<3)
            assertFalse(threeColumnsMaxThreeDifferent.applyObjectiveRules(instance,x,y));
    }
}
