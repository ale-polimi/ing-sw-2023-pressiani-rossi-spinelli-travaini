package model.board;

import enumerations.TypeSpace;
import model.Game;
import model.objects.ObjectCard;
import model.player.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

  private Game instance;

  @Before
  public void setUp(){
    instance = new Game();
  }

  /**
   * Test that getSpace returns a not null BoardSpace
   */
  @Test
  public void getSpaceNotNull() {
    instance.setMaxPlayers(2);
    instance.restoreBoard(instance.getBoard());
    for(int i =0; i< 9; i++){
      for(int j = 0;j < 9;j++){assertNotNull(instance.getBoard().getSpace(i,j));}
    }
  }

  /**
   *Test that pickup objectFrom returns null when boarSpace is UNUSABLE
   */
  @Test
  public void pickupObjectFromUNUSABLE() {
    instance.setMaxPlayers(2);
    instance.restoreBoard(instance.getBoard());
    for(int i =0; i< 9; i++){
      for(int j = 0;j < 9;j++){
        if(instance.getBoard().getSpace(i,j).getTypeSpace().equals(TypeSpace.UNUSABLE)) {
          assertNull(instance.getBoard().pickupObjectFrom(instance.getBoard().getSpace(i,j)));
        }
      }
    }
  }

  /**
   *Test that pickup objectFrom returns an object only when the boardSpace is FOR_TWO_PLAYERS
   */
  @Test
  public void pickupObjectFromTwoPlayers() {
    instance.setMaxPlayers(2);
    instance.addToGame( new Player("Alice","1,1,PINK1,2,0,GREEN1,2,2,YELLOW1,3,4,WHITE1,4,3,LIGHT_BLUE1,5,4,BLUE1"));
    instance.addToGame( new Player("Bob","1,0,BLUE1,1,3,YELLOW1,2,2,PINK1,3,1,GREEN1,3,4,LIGHT_BLUE1,5,0,WHITE1"));
    instance.restoreBoard(instance.getBoard());
    for(int i =0; i< 9; i++){
      for(int j = 0;j < 9;j++){
        if(instance.getBoard().getSpace(i,j).getTypeSpace().equals(TypeSpace.FOR_TWO_PLAYERS)) {
          assertNotNull(instance.getBoard().pickupObjectFrom(instance.getBoard().getSpace(i,j)));
        }else{
          assertNull(instance.getBoard().pickupObjectFrom(instance.getBoard().getSpace(i,j)));
        }
      }
    }
  }

  /**
   *Test that pickup objectFrom returns an object only when the boardSpace is FOR_THREE_PLAYERS
   */
  @Test
  public void pickupObjectFromThreePlayers() {
    instance.setMaxPlayers(3);
    instance.addToGame( new Player("Alice","1,1,PINK1,2,0,GREEN1,2,2,YELLOW1,3,4,WHITE1,4,3,LIGHT_BLUE1,5,4,BLUE1"));
    instance.addToGame( new Player("Bob","1,0,BLUE1,1,3,YELLOW1,2,2,PINK1,3,1,GREEN1,3,4,LIGHT_BLUE1,5,0,WHITE1"));
    instance.addToGame(new Player("Elisa","1,0,PINK1,2,0,GREEN1,2,2,YELLOW1,3,4,WHITE1,4,3,LIGHT_BLUE1,5,4,BLUE1"));
    instance.restoreBoard(instance.getBoard());
    for(int i =0; i< 9; i++){
      for(int j = 0;j < 9;j++){
        if(instance.getBoard().getSpace(i,j).getTypeSpace().equals(TypeSpace.FOR_TWO_PLAYERS)||
            instance.getBoard().getSpace(i,j).getTypeSpace().equals(TypeSpace.FOR_THREE_PLAYERS)) {
          assertNotNull(instance.getBoard().pickupObjectFrom(instance.getBoard().getSpace(i,j)));
        }else{
          assertNull(instance.getBoard().pickupObjectFrom(instance.getBoard().getSpace(i,j)));
        }
      }
    }
  }

  /**
   *Test that pickup objectFrom returns an object only when the boardSpace is FOR_FOUR_PLAYERS
   */
  @Test
  public void pickupObjectFromFourPlayers() {
    instance.setMaxPlayers(4);
    instance.addToGame( new Player("Alice","1,1,PINK1,2,0,GREEN1,2,2,YELLOW1,3,4,WHITE1,4,3,LIGHT_BLUE1,5,4,BLUE1"));
    instance.addToGame( new Player("Bob","1,0,BLUE1,1,3,YELLOW1,2,2,PINK1,3,1,GREEN1,3,4,LIGHT_BLUE1,5,0,WHITE1"));
    instance.addToGame(new Player("Elisa","1,0,PINK1,2,0,GREEN1,2,2,YELLOW1,3,4,WHITE1,4,3,LIGHT_BLUE1,5,4,BLUE1"));
    instance.addToGame(new Player("Alisa","1,0,PINK1,2,0,GREEN1,2,2,YELLOW1,3,4,WHITE1,4,2,LIGHT_BLUE1,5,2,BLUE1"));
    instance.restoreBoard(instance.getBoard());
    for(int i =0; i< 9; i++){
      for(int j = 0;j < 9;j++){
        if(instance.getBoard().getSpace(i,j).getTypeSpace().equals(TypeSpace.FOR_TWO_PLAYERS)||
            instance.getBoard().getSpace(i,j).getTypeSpace().equals(TypeSpace.FOR_THREE_PLAYERS)||
            instance.getBoard().getSpace(i,j).getTypeSpace().equals(TypeSpace.FOR_FOUR_PLAYERS)) {
          assertNotNull(instance.getBoard().pickupObjectFrom(instance.getBoard().getSpace(i,j)));
        }else{assertNull(instance.getBoard().pickupObjectFrom(instance.getBoard().getSpace(i,j)));}
      }
    }
  }

  /**
   * Test that putObjectIn inserts an object in the corresponding space
   */
  @Test
  public void putObjectInCorrectly() {
    instance.setMaxPlayers(2);
    ObjectCard tmp = new ObjectCard();
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(1,3),tmp);
    assertEquals(tmp.getObjectColour(),instance.getBoard().getSpace(1,3).getObject().getObjectColour());
  }

  /**
   * Test putObjectIn when there are two players
   */
  @Test
  public void putObjectInTwoPlayers(){
    instance.setMaxPlayers(2);
    instance.addToGame( new Player("Alice","1,1,PINK1,2,0,GREEN1,2,2,YELLOW1,3,4,WHITE1,4,3,LIGHT_BLUE1,5,4,BLUE1"));
    instance.addToGame( new Player("Bob","1,0,BLUE1,1,3,YELLOW1,2,2,PINK1,3,1,GREEN1,3,4,LIGHT_BLUE1,5,0,WHITE1"));
    instance.restoreBoard(instance.getBoard());
    for(int i =0; i< 9; i++){
      for(int j = 0;j < 9;j++){
        if(instance.getBoard().getSpace(i,j).getTypeSpace().equals(TypeSpace.UNUSABLE)||
            instance.getBoard().getSpace(i,j).getTypeSpace().equals(TypeSpace.FOR_THREE_PLAYERS)||
            instance.getBoard().getSpace(i,j).getTypeSpace().equals(TypeSpace.FOR_FOUR_PLAYERS)) {
          assertNull(instance.getBoard().pickupObjectFrom(instance.getBoard().getSpace(i,j)));
        }else{assertNotNull(instance.getBoard().pickupObjectFrom(instance.getBoard().getSpace(i,j)));}
      }
    }
  }

  /**
   * Test putObjectIn when there are three players
   */
  @Test
  public void putObjectInThreePlayers(){
    instance.setMaxPlayers(3);
    instance.addToGame( new Player("Alice","1,1,PINK1,2,0,GREEN1,2,2,YELLOW1,3,4,WHITE1,4,3,LIGHT_BLUE1,5,4,BLUE1"));
    instance.addToGame( new Player("Bob","1,0,BLUE1,1,3,YELLOW1,2,2,PINK1,3,1,GREEN1,3,4,LIGHT_BLUE1,5,0,WHITE1"));
    instance.addToGame(new Player("Elisa","1,0,PINK1,2,0,GREEN1,2,2,YELLOW1,3,4,WHITE1,4,3,LIGHT_BLUE1,5,4,BLUE1"));
    instance.restoreBoard(instance.getBoard());
    for(int i =0; i< 9; i++){
      for(int j = 0;j < 9;j++){
        if(instance.getBoard().getSpace(i,j).getTypeSpace().equals(TypeSpace.UNUSABLE)||
            instance.getBoard().getSpace(i,j).getTypeSpace().equals(TypeSpace.FOR_FOUR_PLAYERS)) {
          assertNull(instance.getBoard().pickupObjectFrom(instance.getBoard().getSpace(i,j)));
        }else{assertNotNull(instance.getBoard().pickupObjectFrom(instance.getBoard().getSpace(i,j)));}
      }
    }
  }

  /**
   * Test putObjectIn when there are four players
   */
  @Test
  public void putObjectInFourPlayers(){
    instance.setMaxPlayers(4);
    instance.addToGame( new Player("Alice","1,1,PINK1,2,0,GREEN1,2,2,YELLOW1,3,4,WHITE1,4,3,LIGHT_BLUE1,5,4,BLUE1"));
    instance.addToGame( new Player("Bob","1,0,BLUE1,1,3,YELLOW1,2,2,PINK1,3,1,GREEN1,3,4,LIGHT_BLUE1,5,0,WHITE1"));
    instance.addToGame(new Player("Elisa","1,0,PINK1,2,0,GREEN1,2,2,YELLOW1,3,4,WHITE1,4,3,LIGHT_BLUE1,5,4,BLUE1"));
    instance.addToGame(new Player("Alisa","1,0,PINK1,2,0,GREEN1,2,2,YELLOW1,3,4,WHITE1,4,2,LIGHT_BLUE1,5,2,BLUE1"));
    instance.restoreBoard(instance.getBoard());
    for(int i =0; i< 9; i++){
      for(int j = 0;j < 9;j++){
        if(instance.getBoard().getSpace(i,j).getTypeSpace().equals(TypeSpace.UNUSABLE)) {
          assertNull(instance.getBoard().pickupObjectFrom(instance.getBoard().getSpace(i,j)));
        }else{assertNotNull(instance.getBoard().pickupObjectFrom(instance.getBoard().getSpace(i,j)));}
      }
    }
  }


  /**
   * Test that isSpaceSurrounded returns true when the board is full
   */
  @Test
  public void isSpaceSurroundedTotalTrue() {
    instance.setMaxPlayers(2);
    instance.addToGame( new Player("Alice","1,1,PINK1,2,0,GREEN1,2,2,YELLOW1,3,4,WHITE1,4,3,LIGHT_BLUE1,5,4,BLUE1"));
    instance.addToGame( new Player("Bob","1,0,BLUE1,1,3,YELLOW1,2,2,PINK1,3,1,GREEN1,3,4,LIGHT_BLUE1,5,0,WHITE1"));
    instance.restoreBoard(instance.getBoard());
    for(int i = 0;i < 9;i++){
      for(int j = 0; j < 9; j++){if(!instance.getBoard().getSpace(i,j).getTypeSpace().equals(TypeSpace.UNUSABLE))
        assertTrue(instance.getBoard().isSpaceSurrounded(i,j));}
    }
  }

  /**
   * Test that isSpaceSurrounded returns false when the board is empty
   */
  @Test
  public void isSpaceSurroundedTotalFalse(){
    instance.setMaxPlayers(2);
    instance.addToGame( new Player("Alice","1,1,PINK1,2,0,GREEN1,2,2,YELLOW1,3,4,WHITE1,4,3,LIGHT_BLUE1,5,4,BLUE1"));
    instance.addToGame( new Player("Bob","1,0,BLUE1,1,3,YELLOW1,2,2,PINK1,3,1,GREEN1,3,4,LIGHT_BLUE1,5,0,WHITE1"));
    instance.restoreBoard(instance.getBoard());
    for(int i =0;i<9;i++){for(int j=0;j<9;j++){instance.getBoard().getSpace(i,j).removeObject();}}
    for(int i = 0;i < 9;i++){
      for(int j = 0; j < 9; j++){if(!instance.getBoard().getSpace(i,j).getTypeSpace().equals(TypeSpace.UNUSABLE))
        assertFalse(instance.getBoard().isSpaceSurrounded(i,j));}
    }
  }

  /**
   * Test that isSpaceSurrounded returns true for an objectCard that is surrounded on all sides
   */
  @Test
  public void isSpaceSurroundedPlusShaped(){
    instance.setMaxPlayers(2);
    instance.addToGame( new Player("Alice","1,1,PINK1,2,0,GREEN1,2,2,YELLOW1,3,4,WHITE1,4,3,LIGHT_BLUE1,5,4,BLUE1"));
    instance.addToGame( new Player("Bob","1,0,BLUE1,1,3,YELLOW1,2,2,PINK1,3,1,GREEN1,3,4,LIGHT_BLUE1,5,0,WHITE1"));
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,4),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,3),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,5),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(3,4),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(5,4),new ObjectCard());
    assertTrue(instance.getBoard().isSpaceSurrounded(4,4));
  }

  /**
   * Test that isSpaceSurrounded returns true for an objectCard that is surrounded on the left, right and up
   */
  @Test
  public void isSpaceSurroundedReverseTShaped(){
    instance.setMaxPlayers(2);
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,4),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,3),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,5),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(3,4),new ObjectCard());
    assertTrue(instance.getBoard().isSpaceSurrounded(4,4));
  }

  /**
   * Test that isSpaceSurrounded returns true for an objectCard that is surrounded on the left, right and down
   */
  @Test
  public void isSpaceSurroundedTShaped(){
    instance.setMaxPlayers(2);
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,4),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,3),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,5),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(5,4),new ObjectCard());
    assertTrue(instance.getBoard().isSpaceSurrounded(4,4));
  }

  /**
   * Test that isSpaceSurrounded returns true for an objectCard that is surrounded on the left, up and down
   */
  @Test
  public void isSpaceSurroundedLTShaped(){
    instance.setMaxPlayers(2);
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,4),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,3),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(3,4),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(5,4),new ObjectCard());
    assertTrue(instance.getBoard().isSpaceSurrounded(4,4));
  }
  /**
   * Test that isSpaceSurrounded returns true for an objectCard that is surrounded on the right, up and down
   */
  @Test
  public void isSpaceSurroundedRTShaped(){
    instance.setMaxPlayers(2);
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,4),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,5),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(3,4),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(5,4),new ObjectCard());
    assertTrue(instance.getBoard().isSpaceSurrounded(4,4));
  }

  /**
   * Test that isSpaceSurrounded returns true for an objectCard that is surrounded on the left and up
   */
  @Test
  public void isSpaceSurroundedLUShaped(){
    instance.setMaxPlayers(2);
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,4),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,3),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(3,4),new ObjectCard());
    assertTrue(instance.getBoard().isSpaceSurrounded(4,4));
  }
  /**
   * Test that isSpaceSurrounded returns true for an objectCard that is surrounded on the left and down
   */
  @Test
  public void isSpaceSurroundedLDShaped(){
    instance.setMaxPlayers(2);
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,4),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,3),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(5,4),new ObjectCard());
    assertTrue(instance.getBoard().isSpaceSurrounded(4,4));
  }

  /**
   * Test that isSpaceSurrounded returns true for an objectCard that is surrounded on the right and up
   */
  @Test
  public void isSpaceSurroundedRUShaped(){
    instance.setMaxPlayers(2);
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,4),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,5),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(3,4),new ObjectCard());
    assertTrue(instance.getBoard().isSpaceSurrounded(4,4));
  }
  /**
   * Test that isSpaceSurrounded returns true for an objectCard that is surrounded on the right and down
   */
  @Test
  public void isSpaceSurroundedRDShaped(){
    instance.setMaxPlayers(2);
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,4),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,5),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(5,4),new ObjectCard());
    assertTrue(instance.getBoard().isSpaceSurrounded(4,4));
  }

  /**
   * Test that isSpaceSurrounded returns true for an objectCard that is surrounded on the right and left
   */
  @Test
  public void isSpaceSurroundedIHShaped(){
    instance.setMaxPlayers(2);
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,4),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,5),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,3),new ObjectCard());
    assertTrue(instance.getBoard().isSpaceSurrounded(4,4));
  }

  /**
   * Test that isSpaceSurrounded returns true for an objectCard that is surrounded up and down
   */
  @Test
  public void isSpaceSurroundedIVShaped(){
    instance.setMaxPlayers(2);
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,4),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(5,4),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(3,4),new ObjectCard());
    assertTrue(instance.getBoard().isSpaceSurrounded(4,4));
  }

  /**
   * Test that isSpaceSurrounded returns true for an objectCard that is surrounded on the left
   */
  @Test
  public void isSpaceSurroundedLeft(){
    instance.setMaxPlayers(2);
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,4),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,3),new ObjectCard());
    assertTrue(instance.getBoard().isSpaceSurrounded(4,4));
  }

  /**
   * Test that isSpaceSurrounded returns true for an objectCard that is surrounded on up
   */
  @Test
  public void isSpaceSurroundedUp(){
    instance.setMaxPlayers(2);
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,4),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(3,4),new ObjectCard());
    assertTrue(instance.getBoard().isSpaceSurrounded(4,4));
  }
  /**
   * Test that isSpaceSurrounded returns true for an objectCard that is surrounded on the right
   */
  @Test
  public void isSpaceSurroundedRight(){
    instance.setMaxPlayers(2);
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,4),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,5),new ObjectCard());
    assertTrue(instance.getBoard().isSpaceSurrounded(4,4));
  }
  /**
   * Test that isSpaceSurrounded returns true for an objectCard that is surrounded on down
   */
  @Test
  public void isSpaceSurroundedDown(){
    instance.setMaxPlayers(2);
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,4),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(5,4),new ObjectCard());
    assertTrue(instance.getBoard().isSpaceSurrounded(4,4));
  }

  /**
   * Test isSpaceSurrounded when row==0 and the space is surrounded
   */
  @Test
  public void isSpaceSurroundedRowZeroTrue(){
    instance.setMaxPlayers(4);
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(0,3),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(1,3),new ObjectCard());
    assertTrue(instance.getBoard().isSpaceSurrounded(0,3));
  }

  /**
   * Test isSpaceSurrounded when row==0 and the space isn't surrounded
   */
  @Test
  public void isSpaceSurroundedRowZeroFalse(){
    instance.setMaxPlayers(4);
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(0,3),new ObjectCard());
    assertFalse(instance.getBoard().isSpaceSurrounded(0,3));
  }

  /**
   * Test isSpaceSurrounded when row==8 and the space is surrounded
   */
  @Test
  public void isSpaceSurroundedRowEightTrue(){
    instance.setMaxPlayers(4);
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(8,4),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(7,4),new ObjectCard());
    assertTrue(instance.getBoard().isSpaceSurrounded(8,4));
  }

  /**
   * Test isSpaceSurrounded when row==8 and the space isn't surrounded
   */
  @Test
  public void isSpaceSurroundedRowEightFalse(){
    instance.setMaxPlayers(4);
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(8,4),new ObjectCard());
    assertFalse(instance.getBoard().isSpaceSurrounded(8,4));
  }

  /**
   * Test isSpaceSurrounded when column==0 and the space is surrounded
   */
  @Test
  public void isSpaceSurroundedColumnZeroTrue(){
    instance.setMaxPlayers(4);
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,0),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(5,0),new ObjectCard());
    assertTrue(instance.getBoard().isSpaceSurrounded(4,0));
  }

  /**
   * Test isSpaceSurrounded when column==0 and the space isn't surrounded
   */
  @Test
  public void isSpaceSurroundedColumnZeroFalse(){
    instance.setMaxPlayers(4);
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,0),new ObjectCard());
    assertFalse(instance.getBoard().isSpaceSurrounded(4,0));
  }

  /**
   * Test isSpaceSurrounded when column==8 and the space is surrounded
   */
  @Test
  public void isSpaceSurroundedColumnEightTrue(){
    instance.setMaxPlayers(4);
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(3,8),new ObjectCard());
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(4,8),new ObjectCard());
    assertTrue(instance.getBoard().isSpaceSurrounded(3,8));
  }

  /**
   * Test isSpaceSurrounded when column==8 and the space isn't surrounded
   */
  @Test
  public void isSpaceSurroundedColumnEightFalse(){
    instance.setMaxPlayers(4);
    instance.getBoard().putObjectIn(instance.getBoard().getSpace(3,8),new ObjectCard());
    assertFalse(instance.getBoard().isSpaceSurrounded(3,8));
  }
}
