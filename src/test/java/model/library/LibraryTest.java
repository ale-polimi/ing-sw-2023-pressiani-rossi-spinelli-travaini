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
}