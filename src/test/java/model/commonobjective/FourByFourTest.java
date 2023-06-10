package model.commonobjective;


import model.library.Library;

import model.library.LibrarySpace;
import model.objects.ObjectCard;
import org.junit.*;

import static org.junit.jupiter.api.Assertions.*;
public class FourByFourTest {
    Library testLibrary;
    FourByFourNew  fourByFour;

    @Before
    public void setUp() {
        testLibrary =new Library();
        fourByFour= new FourByFourNew();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                testLibrary.getLibraryGrid()[i][j] = new LibrarySpace();
            }
        }

        testLibrary.addObject(new ObjectCard("GREEN2"), testLibrary.getLibrarySpace(0,0));
        testLibrary.addObject(new ObjectCard("GREEN3"), testLibrary.getLibrarySpace(0,1));
        testLibrary.addObject(new ObjectCard("GREEN1"), testLibrary.getLibrarySpace(0,2));
        testLibrary.addObject(new ObjectCard("GREEN1"), testLibrary.getLibrarySpace(0,3));
        testLibrary.addObject(new ObjectCard("WHITE1"), testLibrary.getLibrarySpace(0,4));
        testLibrary.addObject(new ObjectCard("GREEN1"), testLibrary.getLibrarySpace(1,0));
        testLibrary.addObject(new ObjectCard("BLUE1"), testLibrary.getLibrarySpace(1,1));
        testLibrary.addObject(new ObjectCard("BLUE3"), testLibrary.getLibrarySpace(1,2));
        testLibrary.addObject(new ObjectCard("BLUE1"), testLibrary.getLibrarySpace(1,3));
        testLibrary.addObject(new ObjectCard("BLUE1"), testLibrary.getLibrarySpace(1,4));
        testLibrary.addObject(new ObjectCard("GREEN1"), testLibrary.getLibrarySpace(2,0));
        testLibrary.addObject(new ObjectCard("YELLOW1"), testLibrary.getLibrarySpace(2,1));
        testLibrary.addObject(new ObjectCard("WHITE1"), testLibrary.getLibrarySpace(2,2));
        testLibrary.addObject(new ObjectCard("GREEN1"), testLibrary.getLibrarySpace(2,3));
        testLibrary.addObject(new ObjectCard("YELLOW1"), testLibrary.getLibrarySpace(2,4));
        testLibrary.addObject(new ObjectCard("LIGHT_BLUE1"), testLibrary.getLibrarySpace(3,0));
        testLibrary.addObject(new ObjectCard("GREEN1"), testLibrary.getLibrarySpace(3,1));
        testLibrary.addObject(new ObjectCard("GREEN1"), testLibrary.getLibrarySpace(3,2));
        testLibrary.addObject(new ObjectCard("WHITE1"), testLibrary.getLibrarySpace(3,3));
        testLibrary.addObject(new ObjectCard("GREEN1"), testLibrary.getLibrarySpace(3,4));
        testLibrary.addObject(new ObjectCard("GREEN1"), testLibrary.getLibrarySpace(4,0));
        testLibrary.addObject(new ObjectCard("GREEN1"), testLibrary.getLibrarySpace(4,1));
        testLibrary.addObject(new ObjectCard("BLUE3"), testLibrary.getLibrarySpace(4,2));
        testLibrary.addObject(new ObjectCard("GREEN1"), testLibrary.getLibrarySpace(4,3));
        testLibrary.addObject(new ObjectCard("GREEN1"), testLibrary.getLibrarySpace(4,4));
        testLibrary.addObject(new ObjectCard("BLUE1"), testLibrary.getLibrarySpace(5,0));
        testLibrary.addObject(new ObjectCard("BLUE1"), testLibrary.getLibrarySpace(5,1));
        testLibrary.addObject(new ObjectCard("BLUE1"), testLibrary.getLibrarySpace(5,2));
        testLibrary.addObject(new ObjectCard("GREEN1"), testLibrary.getLibrarySpace(5,3));
        testLibrary.addObject(new ObjectCard("GREEN1"), testLibrary.getLibrarySpace(5,4));



    }

    @After
    public void tearDown() {
        fourByFour=null;
    }

    @Test
    public void isFourByFour(){
        int x = 0;
        int y = 0;
        fourByFour.applyObjectiveRules(testLibrary,x,y);
        System.out.println(fourByFour.groupCount);
        if(fourByFour.groupCount >=5){
            System.out.print("fourbyfour");
            assertTrue(fourByFour.applyObjectiveRules(testLibrary,x,y));}
    }

    @Test
    public void isNotFourByFour(){
        int x = 0;
        int y = 0;
        fourByFour.applyObjectiveRules(testLibrary,x,y);
        System.out.println(fourByFour.groupCount);
        if(fourByFour.groupCount<5){
            System.out.print("notfourbyfour");
            assertFalse(fourByFour.applyObjectiveRules(testLibrary,x,y));}
    }

}
