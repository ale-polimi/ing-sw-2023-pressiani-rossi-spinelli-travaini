package model.library;

import model.objects.ObjectCard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PersonalObjectiveTest {


  private final PersonalObjective pO = new PersonalObjective("1,0,BLUE1,1,3,YELLOW1,2,2,PINK1,3,1,GREEN1,3,4,LIGHT_BLUE1,5,0,WHITE1");
  Library[] librarySetup = new Library[7];
  @Before
  public void setUp() {
    Library l1 = new Library();
    l1.addObject(new ObjectCard("BLUE1"),l1.getLibrarySpace(1,0));
    Library l2 = new Library();
    l2.addObject(new ObjectCard("BLUE1"),l2.getLibrarySpace(1,0));
    l2.addObject(new ObjectCard("YELLOW1"),l2.getLibrarySpace(1,3));
    Library l3 = new Library();
    l3.addObject(new ObjectCard("BLUE1"),l3.getLibrarySpace(1,0));
    l3.addObject(new ObjectCard("YELLOW1"),l3.getLibrarySpace(1,3));
    l3.addObject(new ObjectCard("PINK1"),l3.getLibrarySpace(2,2));
    Library l4 = new Library();
    l4.addObject(new ObjectCard("BLUE1"),l4.getLibrarySpace(1,0));
    l4.addObject(new ObjectCard("YELLOW1"),l4.getLibrarySpace(1,3));
    l4.addObject(new ObjectCard("PINK1"),l4.getLibrarySpace(2,2));
    l4.addObject(new ObjectCard("GREEN1"),l4.getLibrarySpace(3,1));
    Library l5 = new Library();
    l5.addObject(new ObjectCard("BLUE1"),l5.getLibrarySpace(1,0));
    l5.addObject(new ObjectCard("YELLOW1"),l5.getLibrarySpace(1,3));
    l5.addObject(new ObjectCard("PINK1"),l5.getLibrarySpace(2,2));
    l5.addObject(new ObjectCard("GREEN1"),l5.getLibrarySpace(3,1));
    l5.addObject(new ObjectCard("LIGHT_BLUE1"),l5.getLibrarySpace(3,4));
    Library l6 = new Library();
    l6.addObject(new ObjectCard("BLUE1"),l6.getLibrarySpace(1,0));
    l6.addObject(new ObjectCard("YELLOW1"),l6.getLibrarySpace(1,3));
    l6.addObject(new ObjectCard("PINK1"),l6.getLibrarySpace(2,2));
    l6.addObject(new ObjectCard("GREEN1"),l6.getLibrarySpace(3,1));
    l6.addObject(new ObjectCard("LIGHT_BLUE1"),l6.getLibrarySpace(3,4));
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
  @Test
  public void testCompareToResult0() {
    Assert.assertEquals(0, pO.compareTo(librarySetup[0]));
  }
  @Test
  public void testCompareToResult1() {
    Assert.assertEquals(1, pO.compareTo(librarySetup[1]));
  }
  @Test
  public void testCompareToResult2() {
    Assert.assertEquals(2, pO.compareTo(librarySetup[2]));
  }
  @Test
  public void testCompareToResult3() {
    Assert.assertEquals(4, pO.compareTo(librarySetup[4]));
  }
  @Test
  public void testCompareToResult4() {
    Assert.assertEquals(6, pO.compareTo(librarySetup[4]));
  }
  @Test
  public void testCompareToResult5() {
    Assert.assertEquals(9, pO.compareTo(librarySetup[5]));
  }
  @Test
  public void testCompareToResult6() {
    Assert.assertEquals(12, pO.compareTo(librarySetup[6]));
  }
}