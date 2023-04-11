package model.objects;

import enumerations.ObjectColour;
import model.objects.ObjectCard;
import org.junit.Test;

import static org.junit.Assert.*;


public class ObjectCardTest {
  @Test
  public void objectColourNotNull(){
    assertNotNull(new ObjectCard().getObjectColour());
  }
  @Test
  public void isGreen(){
    assertEquals(new ObjectCard(ObjectColour.GREEN1).getObjectColour(),ObjectColour.GREEN1);
  }
  @Test
  public void isWhite(){
    assertEquals(new ObjectCard(ObjectColour.WHITE1).getObjectColour(),ObjectColour.WHITE1);
  }
  @Test
  public void isYellow(){
    assertEquals(new ObjectCard(ObjectColour.YELLOW1).getObjectColour(),ObjectColour.YELLOW1);
  }
  @Test
  public void isBlue(){
    assertEquals(new ObjectCard(ObjectColour.BLUE1).getObjectColour(),ObjectColour.BLUE1);
  }
  @Test
  public void isLightBlue(){
    assertEquals(new ObjectCard(ObjectColour.LIGHT_BLUE1).getObjectColour(),ObjectColour.LIGHT_BLUE1);
  }
  @Test
  public void isPink(){
    assertEquals(new ObjectCard(ObjectColour.PINK1).getObjectColour(),ObjectColour.PINK1);
  }
}