package model.library;

import enumerations.ObjectColour;
import model.objects.ObjectCard;
import org.junit.*;
import static org.junit.Assert.*;

public class PersonalObjectiveTest {

  private Library testLibrary;
  private final PersonalObjective pO = new PersonalObjective("1,0,BLUE1,1,3,YELLOW1,2,2,PINK1,3,1,GREEN1,3,4,LIGHT_BLUE1,5,0,WHITE1");

  /**
   * Setting up the testing library
   */
  @Before
  public void setUp() {
    this.testLibrary = new Library();
  }

  /**
   * Test that compareTo returns 0 if the json in input is null
   */
  @Test
  public void compareToNull(){
    assertEquals(0, pO.compareTo(testLibrary));
  }

  /**
   * Test the correct execution of the compareTo method when the library has zero matches
   */
  @Test
  public void compareNoMatches() {
    /* Filling the library with exactly 0 matches */
    for(int row = 0; row < 6; row++){
      for(int col = 0; col < 5; col++){
        if(row == 1 && col == 0) {
          this.testLibrary.addObject(new ObjectCard(ObjectColour.YELLOW1), this.testLibrary.getLibrarySpace(row, col));
        } else {
          this.testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), this.testLibrary.getLibrarySpace(row,col));
        }
      }
    }
    assertEquals(0, pO.compareTo(testLibrary));
  }

  /**
   * Test the correct execution of the compareTo method when the library has one matche
   */
  @Test
  public void compareOneMatch() {
    /* Filling the library with exactly 1 matches */
    for(int row = 0; row < 6; row++){
      for(int col = 0; col < 5; col++){
        if(row == 1 && col == 0) {
          this.testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), this.testLibrary.getLibrarySpace(row, col));
        } else {
          this.testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), this.testLibrary.getLibrarySpace(row,col));
        }
      }
    }
    assertEquals(1, pO.compareTo(testLibrary));
  }

  /**
   * Test the correct execution of the compareTo method when the library has three matches
   */
  @Test
  public void compareTwoMatches() {
    /* Filling the library with exactly 1 matches */
    for(int row = 0; row < 6; row++){
      for(int col = 0; col < 5; col++){
        if(row == 1 && col == 0) {
          this.testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), this.testLibrary.getLibrarySpace(row, col));
        } else if (row == 1 && col == 3){
          this.testLibrary.addObject(new ObjectCard(ObjectColour.YELLOW1), this.testLibrary.getLibrarySpace(row,col));
        } else {
          this.testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), this.testLibrary.getLibrarySpace(row,col));
        }
      }
    }
    assertEquals(2, pO.compareTo(testLibrary));
  }

  /**
   * Test the correct execution of the compareTo method when the library has three matches
   */
  @Test
  public void compareThreeMatches() {
    /* Filling the library with exactly 3 matches */
    for(int row = 0; row < 6; row++){
      for(int col = 0; col < 5; col++){
        if(row == 1 && col == 0) {
          this.testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), this.testLibrary.getLibrarySpace(row, col));
        } else if (row == 1 && col == 3){
          this.testLibrary.addObject(new ObjectCard(ObjectColour.YELLOW1), this.testLibrary.getLibrarySpace(row,col));
        } else if(row == 2 && col == 2){
          this.testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), this.testLibrary.getLibrarySpace(row,col));
        } else {
          this.testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), this.testLibrary.getLibrarySpace(row,col));
        }
      }
    }
    assertEquals(4, pO.compareTo(testLibrary));
  }

  /**
   * Test the correct execution of the compareTo method when the library has four matches
   */
  @Test
  public void compareFourMatches() {
    /* Filling the library with exactly 4 matches */
    for(int row = 0; row < 6; row++){
      for(int col = 0; col < 5; col++){
        if(row == 1 && col == 0) {
          this.testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), this.testLibrary.getLibrarySpace(row, col));
        } else if (row == 1 && col == 3){
          this.testLibrary.addObject(new ObjectCard(ObjectColour.YELLOW1), this.testLibrary.getLibrarySpace(row,col));
        } else if (row == 2 && col == 2){
          this.testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), this.testLibrary.getLibrarySpace(row,col));
        } else if (row == 3 && col == 1) {
          this.testLibrary.addObject(new ObjectCard(ObjectColour.GREEN1), this.testLibrary.getLibrarySpace(row,col));
        } else {
          this.testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), this.testLibrary.getLibrarySpace(row,col));
        }
      }
    }
    assertEquals(6, pO.compareTo(testLibrary));
  }

  /**
   * Test the correct execution of the compareTo method when the library has five matches
   */
  @Test
  public void compareFiveMatches() {
    /* Filling the library with exactly 5 matches */
    for(int row = 0; row < 6; row++){
      for(int col = 0; col < 5; col++){
        if(row == 1 && col == 0) {
          this.testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), this.testLibrary.getLibrarySpace(row, col));
        } else if (row == 1 && col == 3){
          this.testLibrary.addObject(new ObjectCard(ObjectColour.YELLOW1), this.testLibrary.getLibrarySpace(row,col));
        } else if (row == 2 && col == 2){
          this.testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), this.testLibrary.getLibrarySpace(row,col));
        } else if (row == 3 && col == 1) {
          this.testLibrary.addObject(new ObjectCard(ObjectColour.GREEN1), this.testLibrary.getLibrarySpace(row,col));
        } else if (row == 3 && col == 4) {
          this.testLibrary.addObject(new ObjectCard(ObjectColour.LIGHT_BLUE1), this.testLibrary.getLibrarySpace(row,col));
        } else {
          this.testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), this.testLibrary.getLibrarySpace(row,col));
        }
      }
    }
    assertEquals(9, pO.compareTo(testLibrary));
  }

  /**
   * Test the correct execution of the compareTo method when the library has six matches
   */
  @Test
  public void compareSixMatches() {
    /* Filling the library with exactly 6 matches */
    for(int row = 0; row < 6; row++){
      for(int col = 0; col < 5; col++){
        if(row == 1 && col == 0) {
          this.testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), this.testLibrary.getLibrarySpace(row, col));
        } else if (row == 1 && col == 3){
          this.testLibrary.addObject(new ObjectCard(ObjectColour.YELLOW1), this.testLibrary.getLibrarySpace(row,col));
        } else if (row == 2 && col == 2){
          this.testLibrary.addObject(new ObjectCard(ObjectColour.PINK1), this.testLibrary.getLibrarySpace(row,col));
        } else if (row == 3 && col == 1) {
          this.testLibrary.addObject(new ObjectCard(ObjectColour.GREEN1), this.testLibrary.getLibrarySpace(row,col));
        } else if (row == 3 && col == 4) {
          this.testLibrary.addObject(new ObjectCard(ObjectColour.LIGHT_BLUE1), this.testLibrary.getLibrarySpace(row,col));
        } else if (row == 5 && col == 0) {
          this.testLibrary.addObject(new ObjectCard(ObjectColour.WHITE1), this.testLibrary.getLibrarySpace(row,col));
        } else {
          this.testLibrary.addObject(new ObjectCard(ObjectColour.BLUE1), this.testLibrary.getLibrarySpace(row,col));
        }
      }
    }
    assertEquals(12, pO.compareTo(testLibrary));
  }
}