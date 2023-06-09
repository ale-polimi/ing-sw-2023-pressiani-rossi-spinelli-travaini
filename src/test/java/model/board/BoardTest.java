package model.board;

import enumerations.ObjectColour;
import enumerations.TypeSpace;
import model.Game;
import model.objects.ObjectCard;
import model.player.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

  private Board testBoard;

  @Before
  public void setUp() {
    testBoard = new Board();
  }

  /**
   * After the creation of the board, if an object is put in a board space, it must be there.
   */
  @Test
  public void putObjectInBoard(){
    ObjectCard objectCard = new ObjectCard(ObjectColour.PINK1);
    testBoard.putObjectIn(testBoard.getSpace(4,4), objectCard);

    assertEquals(objectCard.getObjectColour(), testBoard.getSpace(4,4).getObject().getObjectColour());
  }

  /**
   * After the creation of the board, all the board spaces must exist.
   */
  @Test
  public void getSpace() {
    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {
        assertNotNull(testBoard.getSpace(row, col));
      }
    }
  }

  /**
   * This tests checks if the pickup correctly removes an object from the selected board space.
   * After the pickup I must have the same object that was in the selected board space.
   * After the pickup the selected board space must be empty.
   */
  @Test
  public void pickupEmptyTile(){
    ObjectCard putObject = new ObjectCard(ObjectColour.PINK1);
    testBoard.putObjectIn(testBoard.getSpace(4,4), putObject);
    ObjectCard pickedObject = testBoard.pickupObjectFrom(testBoard.getSpace(4,4));

    assertEquals(pickedObject.getObjectColour(), putObject.getObjectColour());
    assertNull(testBoard.getSpace(4,4).getObject());
  }

  /**
   * Test to check if isSpaceSurrounded works for tiles in row = 0
   */
  @Test
  public void spaceSurroundedInRow0(){
    testBoard.putObjectIn(testBoard.getSpace(0,0), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(0,1), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(0,2), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(1,1), new ObjectCard(ObjectColour.PINK1));

    assertTrue(testBoard.isSpaceSurrounded(0,1));
  }

  /**
   * Test to check if isSpaceSurrounded works for tiles in row = 0
   */
  @Test
  public void spaceNotSurroundedInRow0(){
    testBoard.putObjectIn(testBoard.getSpace(0,1), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(0,2), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(1,1), new ObjectCard(ObjectColour.PINK1));

    assertFalse(testBoard.isSpaceSurrounded(0,1));
  }

  /**
   * Test to check if isSpaceSurrounded works for tiles in col = 0
   */
  @Test
  public void spaceSurroundedInCol0(){
    testBoard.putObjectIn(testBoard.getSpace(0,0), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(1,0), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(2,0), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(1,1), new ObjectCard(ObjectColour.PINK1));

    assertTrue(testBoard.isSpaceSurrounded(1,0));
  }

  /**
   * Test to check if isSpaceSurrounded works for tiles in col = 0
   */
  @Test
  public void spaceNotSurroundedInCol0(){
    testBoard.putObjectIn(testBoard.getSpace(1,0), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(2,0), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(1,1), new ObjectCard(ObjectColour.PINK1));

    assertFalse(testBoard.isSpaceSurrounded(1,0));
  }

  /**
   * Test to check if isSpaceSurrounded works for tiles in row = 8
   */
  @Test
  public void spaceSurroundedInRow8(){
    testBoard.putObjectIn(testBoard.getSpace(8,0), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(8,1), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(8,2), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(7,1), new ObjectCard(ObjectColour.PINK1));

    assertTrue(testBoard.isSpaceSurrounded(8,1));
  }

  /**
   * Test to check if isSpaceSurrounded works for tiles in row = 8
   */
  @Test
  public void spaceNotSurroundedInRow8(){
    testBoard.putObjectIn(testBoard.getSpace(8,1), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(8,2), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(7,1), new ObjectCard(ObjectColour.PINK1));

    assertFalse(testBoard.isSpaceSurrounded(8,1));
  }

  /**
   * Test to check if isSpaceSurrounded works for tiles in col = 8
   */
  @Test
  public void spaceSurroundedInCol8(){
    testBoard.putObjectIn(testBoard.getSpace(0,8), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(1,8), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(2,8), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(1,7), new ObjectCard(ObjectColour.PINK1));

    assertTrue(testBoard.isSpaceSurrounded(1,8));
  }

  /**
   * Test to check if isSpaceSurrounded works for tiles in col = 8
   */
  @Test
  public void spaceNotSurroundedInCol8(){
    testBoard.putObjectIn(testBoard.getSpace(1,0), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(2,0), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(1,1), new ObjectCard(ObjectColour.PINK1));

    assertFalse(testBoard.isSpaceSurrounded(1,0));
  }

  /**
   * Test to check if isSpaceSurrounded works generally
   */
  @Test
  public void spaceSurrounded(){
    testBoard.putObjectIn(testBoard.getSpace(3,4), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(4,4), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(5,4), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(4,3), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(4,5), new ObjectCard(ObjectColour.PINK1));

    assertTrue(testBoard.isSpaceSurrounded(4,4));
  }

  /**
   * Test to check if isSpaceSurrounded works generally
   */
  @Test
  public void spaceNotSurrounded(){
    testBoard.putObjectIn(testBoard.getSpace(4,4), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(5,4), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(4,3), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(4,5), new ObjectCard(ObjectColour.PINK1));

    assertFalse(testBoard.isSpaceSurrounded(4,4));
  }

  /**
   * Test to check if isSpaceSurroundedByNull works for tiles in row = 0
   */
  @Test
  public void spaceSurroundedByNullInRow0(){
    testBoard.putObjectIn(testBoard.getSpace(0,1), new ObjectCard(ObjectColour.PINK1));

    assertTrue(testBoard.isSpaceSurroundedByNull(0,1));
  }

  /**
   * Test to check if isSpaceSurroundedByNull works for tiles in row = 0
   */
  @Test
  public void spaceNotSurroundedByNullInRow0(){
    testBoard.putObjectIn(testBoard.getSpace(0,1), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(0,2), new ObjectCard(ObjectColour.PINK1));

    assertFalse(testBoard.isSpaceSurroundedByNull(0,1));
  }

  /**
   * Test to check if isSpaceSurroundedByNull works for tiles in col = 0
   */
  @Test
  public void spaceSurroundedByNullInCol0(){
    testBoard.putObjectIn(testBoard.getSpace(1,0), new ObjectCard(ObjectColour.PINK1));

    assertTrue(testBoard.isSpaceSurroundedByNull(1,0));
  }

  /**
   * Test to check if isSpaceSurroundedByNull works for tiles in col = 0
   */
  @Test
  public void spaceNotSurroundedByNullInCol0(){
    testBoard.putObjectIn(testBoard.getSpace(1,0), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(2,0), new ObjectCard(ObjectColour.PINK1));

    assertFalse(testBoard.isSpaceSurroundedByNull(1,0));
  }

  /**
   * Test to check if isSpaceSurroundedByNull works for tiles in row = 8
   */
  @Test
  public void spaceSurroundedByNullInRow8(){
    testBoard.putObjectIn(testBoard.getSpace(8,1), new ObjectCard(ObjectColour.PINK1));

    assertTrue(testBoard.isSpaceSurroundedByNull(8,1));
  }

  /**
   * Test to check if isSpaceSurroundedByNull works for tiles in row = 8
   */
  @Test
  public void spaceNotSurroundedByNullInRow8(){
    testBoard.putObjectIn(testBoard.getSpace(8,1), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(8,2), new ObjectCard(ObjectColour.PINK1));

    assertFalse(testBoard.isSpaceSurroundedByNull(8,1));
  }

  /**
   * Test to check if isSpaceSurroundedByNull works for tiles in col = 8
   */
  @Test
  public void spaceSurroundedByNullInCol8(){
    testBoard.putObjectIn(testBoard.getSpace(1,8), new ObjectCard(ObjectColour.PINK1));

    assertTrue(testBoard.isSpaceSurroundedByNull(1,8));
  }

  /**
   * Test to check if isSpaceSurroundedByNull works for tiles in col = 8
   */
  @Test
  public void spaceNotSurroundedByNullInCol8(){
    testBoard.putObjectIn(testBoard.getSpace(1,8), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(2,8), new ObjectCard(ObjectColour.PINK1));

    assertFalse(testBoard.isSpaceSurroundedByNull(1,8));
  }

  /**
   * Test to check if isSpaceSurroundedByNull works generally
   */
  @Test
  public void spaceSurroundedByNull(){
    testBoard.putObjectIn(testBoard.getSpace(4,4), new ObjectCard(ObjectColour.PINK1));

    assertTrue(testBoard.isSpaceSurroundedByNull(4,4));
  }

  /**
   * Test to check if isSpaceSurroundedByNull works generally
   */
  @Test
  public void spaceNotSurroundedByNull(){
    testBoard.putObjectIn(testBoard.getSpace(3,4), new ObjectCard(ObjectColour.PINK1));
    testBoard.putObjectIn(testBoard.getSpace(4,4), new ObjectCard(ObjectColour.PINK1));

    assertFalse(testBoard.isSpaceSurroundedByNull(4,4));
  }

}