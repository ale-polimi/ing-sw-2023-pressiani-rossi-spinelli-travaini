package model.board;

import enumerations.TypeSpace;
import model.objects.ObjectCard;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardSpaceTest {

  /**
   * Test that getTypeSpace returns UNUSABLE when typeSpace is UNUSABLE
   */
  @Test
  public void getTypeSpaceUNUSABLE() {
    BoardSpace boardSpace = new BoardSpace(TypeSpace.UNUSABLE);
    assertEquals(TypeSpace.UNUSABLE,boardSpace.getTypeSpace());
  }

  /**
   * Test that getTypeSpace returns FOR_TWO_PLAYERS when typeSpace is FOR_TWO_PLAYERS
   */
  @Test
  public void getTypeSpaceFOR_TWO_PLAYERS() {
    BoardSpace boardSpace = new BoardSpace(TypeSpace.FOR_TWO_PLAYERS);
    assertEquals(TypeSpace.FOR_TWO_PLAYERS,boardSpace.getTypeSpace());
  }

  /**
   * Test that getTypeSpace returns FOR_THREE_PLAYERS when typeSpace is FOR_THREE_PLAYERS
   */
  @Test
  public void getTypeSpaceFOR_THREE_PLAYERS() {
    BoardSpace boardSpace = new BoardSpace(TypeSpace.FOR_THREE_PLAYERS);
    assertEquals(TypeSpace.FOR_THREE_PLAYERS,boardSpace.getTypeSpace());
  }

  /**
   * Test that getTypeSpace returns FOR_FOUR_PLAYERS when typeSpace is FOR_FOUR_PLAYERS
   */
  @Test
  public void getTypeSpaceFOR_FOUR_PLAYERS() {
    BoardSpace boardSpace = new BoardSpace(TypeSpace.FOR_FOUR_PLAYERS);
    assertEquals(TypeSpace.FOR_FOUR_PLAYERS,boardSpace.getTypeSpace());
  }

  /**
   * Test that getObject returns null when typeSpace is UNUSABLE
   */
  @Test
  public void getObjectUNUSABLE() {
    BoardSpace boardSpace = new BoardSpace(TypeSpace.UNUSABLE);
    assertNull(boardSpace.getObject());
  }

  /**
   * Test that getObject returns the objectContained when typeSpace is FOR_TWO_PLAYERS
   */
  @Test
  public void getObjectFOR_TWO_PLAYERS() {
    BoardSpace boardSpace = new BoardSpace(TypeSpace.FOR_TWO_PLAYERS);
    boardSpace.insertObject(new ObjectCard());
    assertNotNull(boardSpace.getObject());
  }

  /**
   * Test that getObject returns the objectContained when typeSpace is FOR_THREE_PLAYERS
   */
  @Test
  public void getObjectFOR_THREE_PLAYERS() {
    BoardSpace boardSpace = new BoardSpace(TypeSpace.FOR_THREE_PLAYERS);
    boardSpace.insertObject(new ObjectCard());
    assertNotNull(boardSpace.getObject());
  }

  /**
   * Test that getObject returns the objectContained when typeSpace is FOR_FOUR_PLAYERS
   */
  @Test
  public void getObjectFOR_FOUR_PLAYERS() {
    BoardSpace boardSpace = new BoardSpace(TypeSpace.FOR_FOUR_PLAYERS);
    boardSpace.insertObject(new ObjectCard());
    assertNotNull(boardSpace.getObject());
  }

  /**
   * Test that removeObject method set objectContained to null
   */
  @Test
  public void removeObjectNull() {
    BoardSpace boardSpace = new BoardSpace(TypeSpace.FOR_TWO_PLAYERS);
    boardSpace.insertObject(new ObjectCard("PINK1"));
    boardSpace.removeObject();
    assertNull(boardSpace.getObject());
  }

  /**
   * Test the insertObject method with a FOR_TWO_PLAYERS space
   */
  @Test
  public void insertObjectFOR_TWO_PLAYERS() {
    BoardSpace boardSpace = new BoardSpace(TypeSpace.FOR_TWO_PLAYERS);
    ObjectCard objectCard = new ObjectCard();
    boardSpace.insertObject(objectCard);
    assertEquals(objectCard.getObjectColour(),boardSpace.getObject().getObjectColour());
  }

  /**
   * Test the insertObject method with a FOR_THREE_PLAYERS space
   */
  @Test
  public void insertObjectFOR_THREE_PLAYERS() {
    BoardSpace boardSpace = new BoardSpace(TypeSpace.FOR_THREE_PLAYERS);
    ObjectCard objectCard = new ObjectCard();
    boardSpace.insertObject(objectCard);
    assertEquals(objectCard.getObjectColour(),boardSpace.getObject().getObjectColour());
  }

  /**
   * Test the insertObject method with a FOR_FOUR_PLAYERS space
   */
  @Test
  public void insertObjectFOR_FOUR_PLAYERS() {
    BoardSpace boardSpace = new BoardSpace(TypeSpace.FOR_FOUR_PLAYERS);
    ObjectCard objectCard = new ObjectCard();
    boardSpace.insertObject(objectCard);
    assertEquals(objectCard.getObjectColour(),boardSpace.getObject().getObjectColour());
  }
}