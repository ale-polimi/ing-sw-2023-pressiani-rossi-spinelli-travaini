package model.library;

import model.objects.ObjectCard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PersonalObjectiveTest {


  private final PersonalObjective pO = new PersonalObjective("1,0,BLUE1,1,3,YELLOW1,2,2,PINK1,3,1,GREEN1,3,4,LIGHT_BLUE1,5,0,WHITE1");
  Library[] librarySetup = new Library[7];

  /**
   * Setting up the testing library
   */
  @Before
  public void setUp() {
    Library l1 = new Library();
    l1.getLibraryGrid()[1][0] = new LibrarySpace();
    l1.addObject(new ObjectCard("BLUE1"),l1.getLibrarySpace(1,0));
    Library l2 = new Library();
    l2.getLibraryGrid()[1][0] = new LibrarySpace();
    l2.addObject(new ObjectCard("BLUE1"),l2.getLibrarySpace(1,0));
    l2.getLibraryGrid()[1][3] = new LibrarySpace();
    l2.addObject(new ObjectCard("YELLOW1"),l2.getLibrarySpace(1,3));
    Library l3 = new Library();
    l3.getLibraryGrid()[1][0] = new LibrarySpace();
    l3.addObject(new ObjectCard("BLUE1"),l3.getLibrarySpace(1,0));
    l3.getLibraryGrid()[1][3] = new LibrarySpace();
    l3.addObject(new ObjectCard("YELLOW1"),l3.getLibrarySpace(1,3));
    l3.getLibraryGrid()[2][2] = new LibrarySpace();
    l3.addObject(new ObjectCard("PINK1"),l3.getLibrarySpace(2,2));
    Library l4 = new Library();
    l4.getLibraryGrid()[1][0] = new LibrarySpace();
    l4.addObject(new ObjectCard("BLUE1"),l4.getLibrarySpace(1,0));
    l4.getLibraryGrid()[1][3] = new LibrarySpace();
    l4.addObject(new ObjectCard("YELLOW1"),l4.getLibrarySpace(1,3));
    l4.getLibraryGrid()[2][2] = new LibrarySpace();
    l4.addObject(new ObjectCard("PINK1"),l4.getLibrarySpace(2,2));
    l4.getLibraryGrid()[3][1] = new LibrarySpace();
    l4.addObject(new ObjectCard("GREEN1"),l4.getLibrarySpace(3,1));
    Library l5 = new Library();
    l5.getLibraryGrid()[1][0] = new LibrarySpace();
    l5.addObject(new ObjectCard("BLUE1"),l5.getLibrarySpace(1,0));
    l5.getLibraryGrid()[1][3] = new LibrarySpace();
    l5.addObject(new ObjectCard("YELLOW1"),l5.getLibrarySpace(1,3));
    l5.getLibraryGrid()[2][2] = new LibrarySpace();
    l5.addObject(new ObjectCard("PINK1"),l5.getLibrarySpace(2,2));
    l5.getLibraryGrid()[3][1] = new LibrarySpace();
    l5.addObject(new ObjectCard("GREEN1"),l5.getLibrarySpace(3,1));
    l5.getLibraryGrid()[3][4] = new LibrarySpace();
    l5.addObject(new ObjectCard("LIGHT_BLUE1"),l5.getLibrarySpace(3,4));
    Library l6 = new Library();
    l6.getLibraryGrid()[1][0] = new LibrarySpace();
    l6.addObject(new ObjectCard("BLUE1"),l6.getLibrarySpace(1,0));
    l6.getLibraryGrid()[1][3] = new LibrarySpace();
    l6.addObject(new ObjectCard("YELLOW1"),l6.getLibrarySpace(1,3));
    l6.getLibraryGrid()[2][2] = new LibrarySpace();
    l6.addObject(new ObjectCard("PINK1"),l6.getLibrarySpace(2,2));
    l6.getLibraryGrid()[3][1] = new LibrarySpace();
    l6.addObject(new ObjectCard("GREEN1"),l6.getLibrarySpace(3,1));
    l6.getLibraryGrid()[3][4] = new LibrarySpace();
    l6.addObject(new ObjectCard("LIGHT_BLUE1"),l6.getLibrarySpace(3,4));
    l6.getLibraryGrid()[5][0] = new LibrarySpace();
    l6.addObject(new ObjectCard("WHITE1"),l6.getLibrarySpace(5,0));
    Library l0 = new Library();
    librarySetup[0] = l0;
    librarySetup[1] = l1;
    librarySetup[2] = l2;
    librarySetup[3] = l3;
    librarySetup[4] = l4;
    librarySetup[5] = l5;
    librarySetup[6] = l6;


  }

  /**
   * Test that compareTo returns 0 if the json in input is null
   */
  @Test
  public void compareToNull(){
    Assert.assertEquals(0,pO.compareTo(librarySetup[0]));
  }
  /**
   * Test the correct execution of the compareTo method when the library has one match
   */
  @Test
  public void testCompareToResult0() {
    Assert.assertEquals(0, pO.compareTo(librarySetup[0]));
  }

  /**
   * Test the correct execution of the compareTo method when the library has two matches
   */
  @Test
  public void testCompareToResult1() {
    Assert.assertEquals(1, pO.compareTo(librarySetup[1]));
  }

  /**
   * Test the correct execution of the compareTo method when the library has three matches
   */
  @Test
  public void testCompareToResult2() {
    Assert.assertEquals(2, pO.compareTo(librarySetup[2]));
  }

  /**
   * Test the correct execution of the compareTo method when the library has four matches
   */
  @Test
  public void testCompareToResult3() {
    Assert.assertEquals(4, pO.compareTo(librarySetup[3]));
  }

  /**
   * Test the correct execution of the compareTo method when the library has five matches
   */
  @Test
  public void testCompareToResult4() {
    Assert.assertEquals(6, pO.compareTo(librarySetup[4]));
  }

  /**
   * Test the correct execution of the compareTo method when the library has six matches
   */
  @Test
  public void testCompareToResult5() {
    Assert.assertEquals(9, pO.compareTo(librarySetup[5]));
  }

  /**
   * Test the correct execution of the compareTo method
   * when the library doesn't match at all the personal objective
   */
  @Test
  public void testCompareToResult6() {
    Assert.assertEquals(12, pO.compareTo(librarySetup[6]));
  }
}