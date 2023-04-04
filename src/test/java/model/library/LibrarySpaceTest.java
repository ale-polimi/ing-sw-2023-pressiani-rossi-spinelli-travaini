package model.library;

import model.objects.ObjectCard;
import org.junit.Test;

import static org.junit.Assert.*;

public class LibrarySpaceTest {

  /**
   * Test that getObject returns objectContained card when it exists
   */
  @Test
  public void getObjectNotNull() {
    LibrarySpace librarySpace = new LibrarySpace();
    librarySpace.putObject(new ObjectCard());
    assertNotNull(librarySpace.getObject());
  }

  /**
   * Test that getObject returns the corresponding object contained in the librarySpace
   */
  @Test
  public void getObjectContained() {
    LibrarySpace librarySpace = new LibrarySpace();
    ObjectCard objectCard = new ObjectCard();
    librarySpace.putObject(objectCard);
    assertEquals(objectCard.getObjectColour(),librarySpace.getObject().getObjectColour());
  }

  /**
   * Test that getObject returns null when librarySpace doesn't contain a card
   */
  @Test
  public void getObjectNull() {
    LibrarySpace librarySpace = new LibrarySpace();
    assertNull(librarySpace.getObject());
  }

  /**
   *Test putObject behaviour with a null input
   */
  @Test
  public void putObjectNull() {
    LibrarySpace librarySpace = new LibrarySpace();
    librarySpace.putObject(null);
    assertNull(librarySpace.getObject());
  }
  /**
   *Test putObject behaviour with a normal input
   */
  @Test
  public void putObjectNormal() {
    LibrarySpace librarySpace = new LibrarySpace();
    ObjectCard objectCard = new ObjectCard();
    librarySpace.putObject(objectCard);
    assertEquals(objectCard.getObjectColour(),librarySpace.getObject().getObjectColour());
  }
}