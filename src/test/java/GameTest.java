import enumerations.TypeSpace;
import model.Game;
import model.board.Board;
import model.player.Player;
import org.junit.*;

import static junit.framework.Assert.*;

public class GameTest {
    private Game instance;

    @Before
    public void setUp(){
        this.instance = new Game();
        Player p1 = new Player("Alice","1,1,PINK1,2,0,GREEN1,2,2,YELLOW1,3,4,WHITE1,4,3,LIGHT_BLUE1,5,4,BLUE1");
        Player p2 = new Player("Bob","1,0,BLUE1,1,3,YELLOW1,2,2,PINK1,3,1,GREEN1,3,4,LIGHT_BLUE1,5,0,WHITE1");

        instance.addToGame(p1);
        instance.addToGame(p2);
    }

    @Test
    public void addToGame_OK(){
        instance.addToGame(new Player("Carlo","0,0,PINK1,0,2,BLUE1,1,4,GREEN1,2,3,WHITE1,3,1,YELLOW1,5,2,LIGHT_BLUE1"));

        assertEquals(3, instance.numOfPlayers());
    }

    @Test
    public void addNullPlayer(){
        instance.addToGame(new Player(null,null));

        assertEquals(2, instance.numOfPlayers());
    }

    @Test
    public void setMaxPlayers_inRange() {
        assertTrue(instance.setMaxPlayers(3));
        assertEquals(3, instance.numOfPlayers());
    }

    @Test
    public void setMaxPlayers_outOfBound() {
        assertFalse(instance.setMaxPlayers(6));
    }

    @Test
    public void isNicknameTaken_True(){
        assertTrue(instance.isNicknameTaken("Alice"));
    }

    @Test
    public void isNicknameTaken_False(){
        assertFalse(instance.isNicknameTaken("Anna"));
    }

    @Test
    public void setFirstPlayer(){
        instance.setFirstPlayer();
        assertTrue(instance.getPlayers().get(0).isFirstPlayer() && !instance.getPlayers().get(1).isFirstPlayer());
    }

    @Test
    public void restoreEmptyBoard(){
        instance.restoreBoard(instance.getBoard());
        for(int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if (instance.getBoard().getSpace(i,j).getTypeSpace().equals(TypeSpace.FOR_TWO_PLAYERS)){
                    assertTrue((instance.getBoard().getSpace(i,j).getObject() != null) && (instance.getObjectsDeck().getAvailableObjects() == 132 - 29));
                }
            }
        }
    }
}
