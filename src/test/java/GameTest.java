import model.Game;
import model.board.Board;
import model.player.Player;
import org.junit.Before;
import org.junit.Test;


import static junit.framework.Assert.*;

public class GameTest {
    private Game instance;

    @Before
    public void setUp(){
        this.instance = new Game();
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        Board board = new Board();

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
        assertEquals(3, instance.getChosenPlayers());
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
}
