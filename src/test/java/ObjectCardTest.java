import enumerations.ObjectColour;
import model.objects.ObjectCard;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;



public class ObjectCardTest {
  @Test
  public void objectColourNotNull(){
    assertNotNull(new ObjectCard().getObjectColour());
  }
  @Test
  public void isGreen(){
    assertEquals(new ObjectCard(ObjectColour.GREEN).getObjectColour(),ObjectColour.GREEN);
  }
  @Test
  public void isWhite(){
    assertEquals(new ObjectCard(ObjectColour.WHITE).getObjectColour(),ObjectColour.WHITE);
  }
  @Test
  public void isYellow(){
    assertEquals(new ObjectCard(ObjectColour.YELLOW).getObjectColour(),ObjectColour.YELLOW);
  }
  @Test
  public void isBlue(){
    assertEquals(new ObjectCard(ObjectColour.BLUE).getObjectColour(),ObjectColour.BLUE);
  }
  @Test
  public void isLightBlue(){
    assertEquals(new ObjectCard(ObjectColour.LIGHT_BLUE).getObjectColour(),ObjectColour.LIGHT_BLUE);
  }
  @Test
  public void isPink(){
    assertEquals(new ObjectCard(ObjectColour.PINK).getObjectColour(),ObjectColour.PINK);
  }
}