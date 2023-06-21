package model.library;

import enumerations.ObjectColour;
import model.objects.ObjectCard;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class LibraryTest {

  /**
   * Test the correct insert of a card in a library
   */
  @Test
  public void addObjectCorrectly() {
    LibrarySpace tmp = new LibrarySpace();
    Library library = new Library();
    library.addObject(new ObjectCard("PINK1"),tmp);
    assertEquals(ObjectColour.PINK1,library.getObject(tmp).getObjectColour());
  }

  /**
   * Test that the return value for a defined object isn't null
   */
  @Test
  public void getObjectNotNull() {
    LibrarySpace tmp = new LibrarySpace();
    Library library = new Library();
    library.addObject(new ObjectCard("PINK1"),tmp);
    assertNotNull(library.getObject(tmp));
  }

  /**
   * Test that the method returns the correct object
   */
  @Test
  public void getObjectOfTheSameColour(){
    LibrarySpace tmp = new LibrarySpace();
    Random rand = new Random();
    Library library = new Library();
    ObjectCard obj = new ObjectCard(ObjectColour.values()[rand.nextInt(18)]);
    library.addObject(obj,tmp);
    assertEquals(obj.getObjectColour(),library.getObject(tmp).getObjectColour());
  }

  /**
   * Test that the return value of getLibrarySpace isn't Null
   */
  @Test
  public void getLibrarySpaceNotNull() {
    Library library = new Library();
    LibrarySpace tmp = new LibrarySpace();
    library.getLibraryGrid()[0][0] = tmp;
    assertNotNull(library.getLibrarySpace(0,0));
  }

  /**
   * Test that the value returned by getLibrarySpace is equals to the ones in the library
   */
  @Test
  public void getLibrarySpaceCorrectly() {
    Library library = new Library();
    LibrarySpace tmp = new LibrarySpace();
    library.getLibraryGrid()[0][0] = tmp;
    assertEquals(library.getLibraryGrid()[0][0],library.getLibrarySpace(0,0));
  }

  /**
   * Test that an empty library return 0 points
   */
  @Test
  public void getPointsEmptyLibrary(){
    Library library = new Library();
    assertEquals(0,library.getLibraryPoints());
  }

  /**
   * Test that the library points are assigned correctly when there are 0 cards with the same colour
   */
  @Test
  public void getPointsCorrectly0(){
    Library library = new Library();
    library.getLibrarySpace(5,0).putObject(new ObjectCard("PINK2"));
    library.getLibrarySpace(5,1).putObject(new ObjectCard("PINK1"));
    library.getLibrarySpace(4,1).putObject(new ObjectCard("BLUE1"));
    library.getLibrarySpace(4,2).putObject(new ObjectCard("BLUE1"));
    assertEquals(0,library.getLibraryPoints());
  }
  /**
   * Test that the library points are assigned correctly when there are 3 cards with the same colour
   */
  @Test
  public void getPointsCorrectly2(){
    Library library = new Library();
    library.getLibrarySpace(5,0).putObject(new ObjectCard("PINK2"));
    library.getLibrarySpace(5,1).putObject(new ObjectCard("PINK1"));
    library.getLibrarySpace(5,2).putObject(new ObjectCard("PINK1"));
    library.getLibrarySpace(4,1).putObject(new ObjectCard("BLUE1"));
    library.getLibrarySpace(4,2).putObject(new ObjectCard("BLUE1"));
    assertEquals(2,library.getLibraryPoints());
  }

  /**
   * Test that the library points are assigned correctly when there are 4 cards with the same colour
   */
  @Test
  public void getPointsCorrectly3(){
    Library library = new Library();
    library.getLibrarySpace(5,0).putObject(new ObjectCard("PINK2"));
    library.getLibrarySpace(5,1).putObject(new ObjectCard("PINK1"));
    library.getLibrarySpace(5,2).putObject(new ObjectCard("PINK1"));
    library.getLibrarySpace(4,0).putObject(new ObjectCard("PINK1"));
    library.getLibrarySpace(4,1).putObject(new ObjectCard("BLUE1"));
    library.getLibrarySpace(4,2).putObject(new ObjectCard("BLUE1"));
    assertEquals(3,library.getLibraryPoints());
  }

  /**
   * Test that the library points are assigned correctly when there are 5 cards with the same colour
   */
  @Test
  public void getPointsCorrectly5(){
    Library library = new Library();
    library.getLibrarySpace(5,0).putObject(new ObjectCard("PINK2"));
    library.getLibrarySpace(5,1).putObject(new ObjectCard("PINK1"));
    library.getLibrarySpace(5,2).putObject(new ObjectCard("PINK1"));
    library.getLibrarySpace(4,2).putObject(new ObjectCard("PINK1"));
    library.getLibrarySpace(4,0).putObject(new ObjectCard("PINK1"));
    library.getLibrarySpace(4,1).putObject(new ObjectCard("BLUE1"));
    assertEquals(5,library.getLibraryPoints());
  }

  /**
   * Test that the library points are assigned correctly when there are 6 or more cards with the same colour
   */
  @Test
  public void getPointsCorrectly8(){
    Library library = new Library();
    library.getLibrarySpace(5,0).putObject(new ObjectCard("PINK2"));
    library.getLibrarySpace(5,1).putObject(new ObjectCard("PINK1"));
    library.getLibrarySpace(5,2).putObject(new ObjectCard("PINK1"));
    library.getLibrarySpace(4,2).putObject(new ObjectCard("PINK3"));
    library.getLibrarySpace(3,2).putObject(new ObjectCard("PINK1"));
    library.getLibrarySpace(2,2).putObject(new ObjectCard("PINK1"));
    library.getLibrarySpace(2,1).putObject(new ObjectCard("PINK1"));
    assertEquals(8,library.getLibraryPoints());
  }

  /**
   * Test the points calculation for a custom formation for the tiles in the library
   */
  @Test
  public void strangeFormPoints(){
    Library library = new Library();
    library.getLibrarySpace(5,0).putObject(new ObjectCard("PINK2"));
    library.getLibrarySpace(5,1).putObject(new ObjectCard("PINK1"));
    library.getLibrarySpace(5,2).putObject(new ObjectCard("PINK1"));
    library.getLibrarySpace(4,1).putObject(new ObjectCard("PINK1"));
    library.getLibrarySpace(0,4).putObject(new ObjectCard("PINK3"));
    library.getLibrarySpace(0,3).putObject(new ObjectCard("PINK1"));
    library.getLibrarySpace(0,2).putObject(new ObjectCard("PINK1"));
    library.getLibrarySpace(2,1).putObject(new ObjectCard("YELLOW1"));
    library.getLibrarySpace(1,4).putObject(new ObjectCard("PINK1"));
    assertEquals(6,library.getLibraryPoints());
  }
}