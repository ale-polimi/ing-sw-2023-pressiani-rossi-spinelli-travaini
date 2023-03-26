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
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");

        instance.addToGame(p1);
        instance.addToGame(p2);
    }

    @Test
    public void addToGame_OK(){
        instance.addToGame(new Player("Carlo"));

        assertEquals(3, instance.numOfPlayers());
    }

    @Test
    public void addNullPlayer(){
        instance.addToGame(new Player(null));

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
