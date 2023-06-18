package model.player;

import enumerations.ObjectColour;
import enumerations.PlayerState;
import exceptions.player.EmptyDeckException;
import exceptions.player.TooManyObjectsInHandException;
import model.library.LibrarySpace;
import model.objects.ObjectCard;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

  /**
   * Test that the player's nickname isn't null
   */
  @Test
  public void getNicknameNotNull() {
    Player player = new Player("Gino", "0,0,PINK1,0,2,BLUE1,1,4,GREEN1,2,3,WHITE1,3,1,YELLOW1,5,2,LIGHT_BLUE1");
    assertNotNull(player.getNickname());
  }

  /**
   * Test that the player's nickname is setted as the one in input
   */
  @Test
  public void getNicknameEquals() {
    Player player = new Player("Gino", "0,0,PINK1,0,2,BLUE1,1,4,GREEN1,2,3,WHITE1,3,1,YELLOW1,5,2,LIGHT_BLUE1");
    assertEquals("Gino",player.getNickname());
  }

  /**
   * Test the correct functioning of the setAsFirst method
   */
  @Test
  public void setAsFirstTrue() {
    Player player = new Player("Gino", "0,0,PINK1,0,2,BLUE1,1,4,GREEN1,2,3,WHITE1,3,1,YELLOW1,5,2,LIGHT_BLUE1");
    player.setAsFirst();
    assertTrue(player.isFirstPlayer());
  }

  /**
   * Test the correct functioning of the isFirstPlayer method
   */
  @Test
  public void isFirstPlayerCorrectExecution() {
      Player player = new Player("Gino", "0,0,PINK1,0,2,BLUE1,1,4,GREEN1,2,3,WHITE1,3,1,YELLOW1,5,2,LIGHT_BLUE1");
      assertFalse(player.isFirstPlayer());
      player.setAsFirst();
      assertTrue(player.isFirstPlayer());
  }

  /**
   *Test the correct functioning of the isFirstToEnd method when the return value is true
   */
  @Test
  public void isFirstToEndTrue() {
    Player player = new Player("Gino", "0,0,PINK1,0,2,BLUE1,1,4,GREEN1,2,3,WHITE1,3,1,YELLOW1,5,2,LIGHT_BLUE1");
    player.setFirstToEnd(true);
    assertTrue(player.isFirstToEnd());
  }
  /**
   *Test the correct functioning of the isFirstToEnd method when the return value is false
   */
  @Test
  public void isFirstToEndFalse() {
    Player player = new Player("Gino", "0,0,PINK1,0,2,BLUE1,1,4,GREEN1,2,3,WHITE1,3,1,YELLOW1,5,2,LIGHT_BLUE1");
    player.setFirstToEnd(false);
    assertFalse(player.isFirstToEnd());
  }

  /**
   * Test the correct execution of setFirstToEnd when the input value is True
   */
  @Test
  public void setFirstToEndCorrectExecutionTrue() {
    Player player = new Player("Gino", "0,0,PINK1,0,2,BLUE1,1,4,GREEN1,2,3,WHITE1,3,1,YELLOW1,5,2,LIGHT_BLUE1");
    player.setFirstToEnd(true);
    assertTrue(player.isFirstToEnd());
  }

  /**
   * Test the correct execution of setFirstToEnd when the input value is False
   */
  @Test
  public void setFirstToEndCorrectExecutionFalse() {
    Player player = new Player("Gino", "0,0,PINK1,0,2,BLUE1,1,4,GREEN1,2,3,WHITE1,3,1,YELLOW1,5,2,LIGHT_BLUE1");
    player.setFirstToEnd(false);
    assertFalse(player.isFirstToEnd());
  }

  /**
   * Test the correct returning of the player's personal objective points from when they are 0
   */
  @Test
  public void getPointsPersonalObjectiveDefault() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    assertEquals(0,player.getPersonalObjective().compareTo(player.getLibrary()));
  }

  /**
   * Test the correct returning of the player's personal objective points when they are 1
   */
  @Test
  public void getPointsPersonalObjectiveOne() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.getLibrary().getLibraryGrid()[5][4] = new LibrarySpace();
    player.getLibrary().addObject(new ObjectCard("PINK1"),player.getLibrary().getLibraryGrid()[5][4]);
    assertEquals(1,player.getPersonalObjective().compareTo(player.getLibrary()));
  }

  /**
   * Test the correct returning of the player's personal objective points when they are 2
   */
  @Test
  public void getPointsPersonalObjectiveTwo() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.getLibrary().getLibraryGrid()[5][4] = new LibrarySpace();
    player.getLibrary().addObject(new ObjectCard("PINK1"),player.getLibrary().getLibraryGrid()[5][4]);
    player.getLibrary().getLibraryGrid()[5][3] = new LibrarySpace();
    player.getLibrary().addObject(new ObjectCard("BLUE1"),player.getLibrary().getLibraryGrid()[5][3]);
    assertEquals(2,player.getPersonalObjective().compareTo(player.getLibrary()));
  }

  /**
   * Test the correct returning of the player's personal objective points when they are 4
   */
  @Test
  public void getPointsPersonalObjectiveFour() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.getLibrary().getLibraryGrid()[5][4] = new LibrarySpace();
    player.getLibrary().addObject(new ObjectCard("PINK1"),player.getLibrary().getLibraryGrid()[5][4]);
    player.getLibrary().getLibraryGrid()[5][3] = new LibrarySpace();
    player.getLibrary().addObject(new ObjectCard("BLUE1"),player.getLibrary().getLibraryGrid()[5][3]);
    player.getLibrary().getLibraryGrid()[5][2] = new LibrarySpace();
    player.getLibrary().addObject(new ObjectCard("GREEN1"),player.getLibrary().getLibraryGrid()[5][2]);
    assertEquals(4,player.getPersonalObjective().compareTo(player.getLibrary()));
  }

  /**
   * Test the correct returning of the player's personal objective points when they are 6
   */
  @Test
  public void getPointsPersonalObjectiveSix() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.getLibrary().getLibraryGrid()[5][4] = new LibrarySpace();
    player.getLibrary().addObject(new ObjectCard("PINK1"),player.getLibrary().getLibraryGrid()[5][4]);
    player.getLibrary().getLibraryGrid()[5][3] = new LibrarySpace();
    player.getLibrary().addObject(new ObjectCard("BLUE1"),player.getLibrary().getLibraryGrid()[5][3]);
    player.getLibrary().getLibraryGrid()[5][2] = new LibrarySpace();
    player.getLibrary().addObject(new ObjectCard("GREEN1"),player.getLibrary().getLibraryGrid()[5][2]);
    player.getLibrary().getLibraryGrid()[5][1] = new LibrarySpace();
    player.getLibrary().addObject(new ObjectCard("WHITE1"),player.getLibrary().getLibraryGrid()[5][1]);
    assertEquals(6,player.getPersonalObjective().compareTo(player.getLibrary()));
  }

  /**
   * Test the correct returning of the player's personal objective points when they are 9
   */
  @Test
  public void getPointsPersonalObjectiveNine() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.getLibrary().getLibraryGrid()[5][4] = new LibrarySpace();
    player.getLibrary().addObject(new ObjectCard("PINK1"),player.getLibrary().getLibraryGrid()[5][4]);
    player.getLibrary().getLibraryGrid()[5][3] = new LibrarySpace();
    player.getLibrary().addObject(new ObjectCard("BLUE1"),player.getLibrary().getLibraryGrid()[5][3]);
    player.getLibrary().getLibraryGrid()[5][2] = new LibrarySpace();
    player.getLibrary().addObject(new ObjectCard("GREEN1"),player.getLibrary().getLibraryGrid()[5][2]);
    player.getLibrary().getLibraryGrid()[5][1] = new LibrarySpace();
    player.getLibrary().addObject(new ObjectCard("WHITE1"),player.getLibrary().getLibraryGrid()[5][1]);
    player.getLibrary().getLibraryGrid()[5][0] = new LibrarySpace();
    player.getLibrary().addObject(new ObjectCard("YELLOW1"),player.getLibrary().getLibraryGrid()[5][0]);
    assertEquals(9,player.getPersonalObjective().compareTo(player.getLibrary()));
  }

  /**
   * Test the correct returning of the player's personal objective points when they are 12
   */
  @Test
  public void getPointsPersonalObjectiveTwelve() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.getLibrary().getLibraryGrid()[5][4] = new LibrarySpace();
    player.getLibrary().addObject(new ObjectCard("PINK1"),player.getLibrary().getLibraryGrid()[5][4]);
    player.getLibrary().getLibraryGrid()[5][3] = new LibrarySpace();
    player.getLibrary().addObject(new ObjectCard("BLUE1"),player.getLibrary().getLibraryGrid()[5][3]);
    player.getLibrary().getLibraryGrid()[5][2] = new LibrarySpace();
    player.getLibrary().addObject(new ObjectCard("GREEN1"),player.getLibrary().getLibraryGrid()[5][2]);
    player.getLibrary().getLibraryGrid()[5][1] = new LibrarySpace();
    player.getLibrary().addObject(new ObjectCard("WHITE1"),player.getLibrary().getLibraryGrid()[5][1]);
    player.getLibrary().getLibraryGrid()[5][0] = new LibrarySpace();
    player.getLibrary().addObject(new ObjectCard("YELLOW1"),player.getLibrary().getLibraryGrid()[5][0]);
    player.getLibrary().getLibraryGrid()[4][0] = new LibrarySpace();
    player.getLibrary().addObject(new ObjectCard("LIGHT_BLUE1"),player.getLibrary().getLibraryGrid()[4][0]);
    assertEquals(12,player.getPersonalObjective().compareTo(player.getLibrary()));
  }

  /**
   * Test that gatLibrary doesn't return a null object
   */
  @Test
  public void getLibraryNotNull() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    assertNotNull(player.getLibrary());
  }

  /**
   * Test that getLibrary returns the correct library
   */
  @Test
  public void getLibraryCorrect(){
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    LibrarySpace tmp = new LibrarySpace();
    player.getLibrary().getLibraryGrid()[5][0] = tmp;
    player.getLibrary().addObject(new ObjectCard("PINK1"),tmp);
    assertEquals(tmp.getObject().getObjectColour(),player.getLibrary().getLibraryGrid()[5][0].getObject().getObjectColour());
  }

  /**
   * Test that initObjectsInHands initialize an empty list
   */
  @Test
  public void initObjectsInHandCreatedEmptyList() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    assertEquals(0,player.getObjectsInHandSize());
  }

  /**
   * Test that resetObjectsInHands set objectsInHand to null
   */
  @Test
  public void resetObjectsInHandIsNull() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.resetObjectsInHand();
    assertThrows(NullPointerException.class, player::getObjectsInHandSize);
  }

  /**
   * Test that hasObjectsInHand return true when you have an empty hand
   */
  @Test
  public void hasObjectsInHandTrueWithoutObjectsInHands() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    assertTrue(player.hasObjectsInHand());
  }

  /**
   * Test that hasObjectsInHand return true when you have objects in hand
   */
  @Test
  public void hasObjectsInHandTrueWithObjectsInHAnd() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("PINK1"));
    assertTrue(player.hasObjectsInHand());
  }

  /**
   * Test that hasObjectsInHand return false when you have a null hand
   */
  @Test
  public void hasObjectsInHandFalse() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.resetObjectsInHand();
    assertFalse(player.hasObjectsInHand());
  }

  /**
   * Test that addToObjectsInHand will insert PINK1 when received PINK1
   */
  @Test
  public void addToObjectsInHandPINK1() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("PINK1"));
    assertEquals(1,player.getObjectsInHandSize());
    assertEquals(ObjectColour.PINK1,player.getObjectInHand(0).getObjectColour());
  }
  /**
   * Test that addToObjectsInHand will insert PINK2 when received PINK2
   */
  @Test
  public void addToObjectsInHandPINK2() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("PINK2"));
    assertEquals(1,player.getObjectsInHandSize());
    assertEquals(ObjectColour.PINK2,player.getObjectInHand(0).getObjectColour());
  }
  /**
   * Test that addToObjectsInHand will insert PINK3 when received PINK3
   */
  @Test
  public void addToObjectsInHandPINK3() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("PINK3"));
    assertEquals(1,player.getObjectsInHandSize());
    assertEquals(ObjectColour.PINK3,player.getObjectInHand(0).getObjectColour());
  }
  /**
   * Test that addToObjectsInHand will insert LIGHT_BLUE1 when received LIGHT_BLUE1
   */
  @Test
  public void addToObjectsInHandLIGHT_BLUE1() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("LIGHT_BLUE1"));
    assertEquals(1,player.getObjectsInHandSize());
    assertEquals(ObjectColour.LIGHT_BLUE1,player.getObjectInHand(0).getObjectColour());
  }
  /**
   * Test that addToObjectsInHand will insert LIGHT_BLUE2 when received LIGHT_BLUE2
   */
  @Test
  public void addToObjectsInHandLIGHT_BLUE2() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("LIGHT_BLUE2"));
    assertEquals(1,player.getObjectsInHandSize());
    assertEquals(ObjectColour.LIGHT_BLUE2,player.getObjectInHand(0).getObjectColour());
  }
  /**
   * Test that addToObjectsInHand will insert LIGHT_BLUE3 when received LIGHT_BLUE3
   */
  @Test
  public void addToObjectsInHandLIGHT_BLUE3() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("LIGHT_BLUE3"));
    assertEquals(1,player.getObjectsInHandSize());
    assertEquals(ObjectColour.LIGHT_BLUE3,player.getObjectInHand(0).getObjectColour());
  }
  /**
   * Test that addToObjectsInHand will insert BLUE1 when received BLUE1
   */
  @Test
  public void addToObjectsInHandBLUE1() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("BLUE1"));
    assertEquals(1,player.getObjectsInHandSize());
    assertEquals(ObjectColour.BLUE1,player.getObjectInHand(0).getObjectColour());
  }
  /**
   * Test that addToObjectsInHand will insert BLUE2 when received BLUE2
   */
  @Test
  public void addToObjectsInHandBLUE2() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("BLUE2"));
    assertEquals(1,player.getObjectsInHandSize());
    assertEquals(ObjectColour.BLUE2,player.getObjectInHand(0).getObjectColour());
  }
  /**
   * Test that addToObjectsInHand will insert BLUE3 when received BLUE3
   */
  @Test
  public void addToObjectsInHandBLUE3() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("BLUE3"));
    assertEquals(1,player.getObjectsInHandSize());
    assertEquals(ObjectColour.BLUE3,player.getObjectInHand(0).getObjectColour());
  }
  /**
   * Test that addToObjectsInHand will insert YELLOW1 when received YELLOW1
   */
  @Test
  public void addToObjectsInHandYELLOW1() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("YELLOW1"));
    assertEquals(1,player.getObjectsInHandSize());
    assertEquals(ObjectColour.YELLOW1,player.getObjectInHand(0).getObjectColour());
  }
  /**
   * Test that addToObjectsInHand will insert YELLOW2 when received YELLOW2
   */
  @Test
  public void addToObjectsInHandYELLOW2() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("YELLOW2"));
    assertEquals(1,player.getObjectsInHandSize());
    assertEquals(ObjectColour.YELLOW2,player.getObjectInHand(0).getObjectColour());
  }
  /**
   * Test that addToObjectsInHand will insert YELLOW3 when received YELLOW3
   */
  @Test
  public void addToObjectsInHandYELLOW3() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("YELLOW3"));
    assertEquals(1,player.getObjectsInHandSize());
    assertEquals(ObjectColour.YELLOW3,player.getObjectInHand(0).getObjectColour());
  }
  /**
   * Test that addToObjectsInHand will insert WHITE1 when received WHITE1
   */
  @Test
  public void addToObjectsInHandWHITE1() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("WHITE1"));
    assertEquals(1,player.getObjectsInHandSize());
    assertEquals(ObjectColour.WHITE1,player.getObjectInHand(0).getObjectColour());
  }
  /**
   * Test that addToObjectsInHand will insert WHITE2 when received WHITE2
   */
  @Test
  public void addToObjectsInHandWHITE2() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("WHITE2"));
    assertEquals(1,player.getObjectsInHandSize());
    assertEquals(ObjectColour.WHITE2,player.getObjectInHand(0).getObjectColour());
  }
  /**
   * Test that addToObjectsInHand will insert WHITE3 when received WHITE3
   */
  @Test
  public void addToObjectsInHandWHITE3() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("WHITE3"));
    assertEquals(1,player.getObjectsInHandSize());
    assertEquals(ObjectColour.WHITE3,player.getObjectInHand(0).getObjectColour());
  }
  /**
   * Test that addToObjectsInHand will insert GREEN1 when received GREEN1
   */
  @Test
  public void addToObjectsInHandGREEN1() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("GREEN1"));
    assertEquals(1,player.getObjectsInHandSize());
    assertEquals(ObjectColour.GREEN1,player.getObjectInHand(0).getObjectColour());
  }
  /**
   * Test that addToObjectsInHand will insert GREEN2 when received GREEN2
   */
  @Test
  public void addToObjectsInHandGREEN2() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("GREEN2"));
    assertEquals(1,player.getObjectsInHandSize());
    assertEquals(ObjectColour.GREEN2,player.getObjectInHand(0).getObjectColour());
  }
  /**
   * Test that addToObjectsInHand will insert GREEN3 when received GREEN3
   */
  @Test
  public void addToObjectsInHandGREEN3() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("GREEN3"));
    assertEquals(1,player.getObjectsInHandSize());
    assertEquals(ObjectColour.GREEN3,player.getObjectInHand(0).getObjectColour());
  }

  /**
   * Test the throwing of the TooManyObjectsInHandException exception
   */
  @Test
  public void addToObjectsInHandTooManyObjectsInHandException(){
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("PINK1"));
    player.addToObjectsInHand(new ObjectCard("YELLOW1"));
    player.addToObjectsInHand(new ObjectCard("WHITE1"));
    assertThrows(TooManyObjectsInHandException.class,()->player.addToObjectsInHand(new ObjectCard("BLUE1")));
  }

  /**
   * Test the correct returning of the first object in hand
   */
  @Test
  public void getObjectInHandFirstObject() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("PINK1"));
    player.addToObjectsInHand(new ObjectCard("YELLOW1"));
    player.addToObjectsInHand(new ObjectCard("WHITE1"));
    assertEquals(ObjectColour.PINK1,player.getObjectInHand(0).getObjectColour());
  }

  /**
   *Test the correct returning of the second object in hand
   */
  @Test
  public void getObjectInHandSecondObject() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("PINK1"));
    player.addToObjectsInHand(new ObjectCard("YELLOW1"));
    player.addToObjectsInHand(new ObjectCard("WHITE1"));
    assertEquals(ObjectColour.YELLOW1,player.getObjectInHand(1).getObjectColour());
  }
  /**
   *Test the correct returning of the third object in hand
   */
  @Test
  public void getObjectInHandThirdObject() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("PINK1"));
    player.addToObjectsInHand(new ObjectCard("YELLOW1"));
    player.addToObjectsInHand(new ObjectCard("WHITE1"));
    assertEquals(ObjectColour.WHITE1,player.getObjectInHand(2).getObjectColour());
  }

  /**
   *Test the throwing of the IndexOutOfBoundException exception
   */
  @Test
  public void getObjectInHandIndexOutOfBoundException() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("PINK1"));
    player.addToObjectsInHand(new ObjectCard("YELLOW1"));
    player.addToObjectsInHand(new ObjectCard("WHITE1"));
    assertThrows(IndexOutOfBoundsException.class,()->player.getObjectInHand(3));
  }
  /**
   *Test the throwing of the EmptyDeckException exception
   */
  @Test
  public void getObjectInHandEmptyDeckException() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    assertThrows(EmptyDeckException.class,()->player.getObjectInHand(0));
  }

  /**
   * Test that getObjectInHandSize returns 0 when objectsInHand is empty
   */
  @Test
  public void getObjectsInHandSizeZero() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    assertEquals(0,player.getObjectsInHandSize());
  }

  /**
   * Test that getObjectInHandSize returns 1 when objectsInHand has one object
   */
  @Test
  public void getObjectsInHandSizeOne(){
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("PINK1"));
    assertEquals(1,player.getObjectsInHandSize());
  }
  /**
   * Test that getObjectInHandSize returns 2 when objectsInHand has two object
   */
  @Test
  public void getObjectsInHandSizeTwo(){
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("PINK1"));
    player.addToObjectsInHand(new ObjectCard("YELLOW1"));
    assertEquals(2,player.getObjectsInHandSize());
  }

  /**
   * Test that getObjectInHandSize returns 3 when objectsInHand has three object
   */
  @Test
  public void getObjectsInHandSizeThree(){
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.initObjectsInHand();
    player.addToObjectsInHand(new ObjectCard("PINK1"));
    player.addToObjectsInHand(new ObjectCard("YELLOW1"));
    player.addToObjectsInHand(new ObjectCard("WHITE1"));
    assertEquals(3,player.getObjectsInHandSize());
  }

  /**
   * Test that setPlayerState set playerState to IDLE when received IDLE
   */
  @Test
  public void setPlayerStateIdle() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.setPlayerState(PlayerState.IDLE);
    assertEquals(PlayerState.IDLE,player.getPlayerState());
  }

  /**
   * Test that setPlayerState set playerState to PICKUP when received PICKUP
   */
  @Test
  public void setPlayerStatePICKUP() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.setPlayerState(PlayerState.PICKUP);
    assertEquals(PlayerState.PICKUP,player.getPlayerState());
  }

  /**
   * Test that setPlayerState set playerState to IN_LIBRARY when received IN_LIBRARY
   */
  @Test
  public void setPlayerStateIN_LIBRARY() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.setPlayerState(PlayerState.IN_LIBRARY);
    assertEquals(PlayerState.IN_LIBRARY,player.getPlayerState());
  }

  /**
   * Test that the getPlayerState doesn't return a null value
   */
  @Test
  public void getPlayerStateNotNull() {
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    assertNull(player.getPlayerState());
  }

  /**
   * Test that getPlayerState return IDLE  when it playerState is IDLE
   */
  @Test
  public void getPlayerStateIDLE(){
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.setPlayerState(PlayerState.IDLE);
    assertEquals(PlayerState.IDLE,player.getPlayerState());
  }
  /**
   * Test that getPlayerState return PICKUP  when it playerState is PICKUP
   */
  @Test
  public void getPlayerStatePICKUP(){
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.setPlayerState(PlayerState.PICKUP);
    assertEquals(PlayerState.PICKUP,player.getPlayerState());
  }
  /**
   * Test that getPlayerState return IN_LIBRARY  when it playerState is IN_LIBRARY
   */
  @Test
  public void getPlayerStateIN_LIBRARY(){
    Player player = new Player("Gino", "5,4,PINK1,5,3,BLUE1,5,2,GREEN1,5,1,WHITE1,5,0,YELLOW1,4,0,LIGHT_BLUE1");
    player.setPlayerState(PlayerState.IN_LIBRARY);
    assertEquals(PlayerState.IN_LIBRARY,player.getPlayerState());
  }
}