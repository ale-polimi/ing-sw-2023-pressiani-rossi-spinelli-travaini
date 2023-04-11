package model.objects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ObjectsDeckTest {

  private ObjectsDeck objectsDeck;
  @Before
  public void setUp(){
    this.objectsDeck= new ObjectsDeck();
  }

  /**
   * Test that the removed cards are not null
   */
  @Test
  public void removeFromDeckNotNull(){
    assertNotNull(objectsDeck.removeFromDeck());
  }

  /**
   * Test that getAvailableObjects returns 132 when the deck is full
   */
  @Test
  public void getAvailableObjectsFull() {
    assertEquals(objectsDeck.getAvailableObjects(),132);
  }

  /**
   * Test that getAvailableObjects returns 0 when the deck is empty
   */
  @Test
  public void getAvailableObjectsEmpty() {
    for(int i = 0; i < 132; i++){objectsDeck.removeFromDeck();}
    assertEquals(objectsDeck.getAvailableObjects(),0);
  }

  /**
   * Test that getAvailableObjects returns the number of cards in the deck
   */
  @Test
  public void getAvailableObjectsAfterRemoves() {
    objectsDeck.removeFromDeck();
    assertEquals(objectsDeck.getAvailableObjects(),131);
  }
}