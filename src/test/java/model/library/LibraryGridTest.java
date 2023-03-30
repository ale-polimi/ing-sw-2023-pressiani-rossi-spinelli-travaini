package model.library;

import model.objects.ObjectCard;
import org.junit.Test;

import static org.junit.Assert.*;

public class LibraryGridTest {

  /**
   * Test the correct insertion of a card in a library space
   */
  @Test
  public void addObjectCorrectly() {
    LibraryGrid libraryGrid = new LibraryGrid();
    LibrarySpace tmp = new LibrarySpace();
    libraryGrid.getLibraryGrid()[0][0] = tmp;
    libraryGrid.addObject(new ObjectCard("PINK1"),tmp);
    assertEquals(tmp.getObject().getObjectColour(), libraryGrid.getLibraryGrid()[0][0].getObject().getObjectColour());
  }

  /**
   * Test the correct returning of the library grid
   */
  @Test
  public void getLibraryGridCorrectly() {
    LibraryGrid libraryGrid = new LibraryGrid();
    assertNotNull(libraryGrid.getLibraryGrid());
  }
}