import enumerations.GameState;
import enumerations.TypeSpace;
import exceptions.game.TooManyPlayersException;
import model.Game;
import model.board.Board;
import model.player.Player;
import org.junit.*;

import static org.junit.Assert.*;

public class GameTest {
    private Game instance;

    @Before
    public void setUp(){
        this.instance = new Game();
        instance.setMaxPlayers(2);
        Player p1 = new Player("Alice","1,1,PINK1,2,0,GREEN1,2,2,YELLOW1,3,4,WHITE1,4,3,LIGHT_BLUE1,5,4,BLUE1");
        Player p2 = new Player("Bob","1,0,BLUE1,1,3,YELLOW1,2,2,PINK1,3,1,GREEN1,3,4,LIGHT_BLUE1,5,0,WHITE1");
        instance.addToGame(p1);
        instance.addToGame(p2);
    }

    /**
     * Test a correct insertion of a player to the game
     */
    @Test
    public void addToGame_OK(){
        instance.setMaxPlayers(3);
        instance.addToGame(new Player("Carlo","0,0,PINK1,0,2,BLUE1,1,4,GREEN1,2,3,WHITE1,3,1,YELLOW1,5,2,LIGHT_BLUE1"));
        assertEquals(3, instance.numOfPlayers());
    }

    /**
     * Testing that a null player cannot be added to the game
     */
    @Test
    public void addNullPlayer(){
        instance.setMaxPlayers(3);
        assertThrows(NullPointerException.class,()->instance.addToGame(new Player(null,null)));
        assertEquals(2, instance.getPlayers().size());
    }

    /**
     * Testing the correct throwing of TooManyPlayersException
     */
    @Test
    public void addToGameTooManyPlayersException(){
        assertThrows(TooManyPlayersException.class,()->instance.addToGame(new Player("Carlo","0,0,PINK1,0,2,BLUE1,1,4,GREEN1,2,3,WHITE1,3,1,YELLOW1,5,2,LIGHT_BLUE1")));
    }

    /**
     * Testing that setMaxPlayer set the maximum number of players correspond to the input
     */
    @Test
    public void setMaxPlayers_inRange() {
        assertTrue(instance.setMaxPlayers(3));
        assertEquals(3, instance.numOfPlayers());
    }

    /**
     * Test that setMaxPlayer doesn't accept a number of players lesser than 2
     */
    @Test
    public void setMaxPlayerLesserBoundOut(){
        assertFalse(instance.setMaxPlayers(1));
    }

    /**
     * Test that setMAxPlayer doesn't accept a number of players greater tha 4
     */
    @Test
    public void setMaxPlayersUpperBoundOut() {
        assertFalse(instance.setMaxPlayers(5));
    }

    /**
     * Test that setMaxPlayer behavior on the lesser bound
     */
    @Test
    public void setMaxPlayerLesserBoundIn(){
        assertTrue(instance.setMaxPlayers(2));
    }

    /**
     * Test that setMaxPlayer behavior on the greater bound
     */
    @Test
    public void setMaxPlayerUpperBoundIn(){
        assertTrue(instance.setMaxPlayers(4));
    }

    /**
     * Test that if a nickname is taken isNicknameTaken return true
     */
    @Test
    public void isNicknameTaken_True(){
        assertTrue(instance.isNicknameTaken("Alice"));
    }

    /**
     * Test that if a nickname is taken isNicknameTaken return false
     */
    @Test
    public void isNicknameTaken_False(){
        assertFalse(instance.isNicknameTaken("Anna"));
    }

    /**
     * Test that setFirstPlayer set the first player the one who is the first in List players
     */
    @Test
    public void setFirstPlayer(){
        instance.setFirstPlayer();
        assertTrue(instance.getPlayers().get(0).isFirstPlayer() && !instance.getPlayers().get(1).isFirstPlayer());
    }

    /**
     * Test that getTurn doesn't return null
     */
    @Test
    public void getTurnNotNull(){
        assertNotNull(instance.getTurn());
    }

    /**
     * Test that getMaxPlayer set the number of players to 2 when 2 is in input
     */
    @Test
    public void getMaxPlayer2(){
        instance.setMaxPlayers(2);
        assertEquals(2,instance.getMaxPlayers());
    }

    /**
     * Test that getMaxPlayer set the number of players to 3 when 3 is in input
     */
    @Test
    public void getMaxPlayer3(){
        instance.setMaxPlayers(3);
        assertEquals(3,instance.getMaxPlayers());
    }

    /**
     * Test that getMaxPlayer set the number of players to 4 when 4 is in input
     */
    @Test
    public void getMaxPlayer4(){
        instance.setMaxPlayers(4);
        assertEquals(4,instance.getMaxPlayers());
    }

    /**
     * Test that setGameState set gameState to LOGIN when the input is LOGIN
     */
    @Test
    public void setGameStateLOGIN(){
        instance.setGameState(GameState.LOGIN);
        assertEquals(GameState.LOGIN,instance.getGameState());
    }

    /**
     * Test that setGameState set gameState to START when the input is START
     */
    @Test
    public void setGameStateSTART(){
        instance.setGameState(GameState.START);
        assertEquals(GameState.START,instance.getGameState());
    }

    /**
     * Test that setGameState set gameState to IN_GAME when the input is IN_GAME
     */
    @Test
    public void setGameStateIN_GAME(){
        instance.setGameState(GameState.IN_GAME);
        assertEquals(GameState.IN_GAME,instance.getGameState());
    }

    /**
     * Test that setGameState set gameState to END when the input is END
     */
    @Test
    public void setGameStateEND(){
        instance.setGameState(GameState.END);
        assertEquals(GameState.END,instance.getGameState());
    }

    /**
     * Test that getNextPlayer returns the player in the next position in the players ArrayList when the current player isn't the last one
     */
    @Test
    public void getNextPlayerNormal(){
        instance.setPlayerInTurn(instance.getPlayers().get(0));
        assertEquals(-1,instance.getPlayers().indexOf(instance.getPlayerInTurn()) - instance.getPlayers().indexOf(instance.getNextPlayer()));
    }

    /**
     * Test that getNextPlayer returns the first player when the current player is the last in the players ArrayList
     */
    @Test
    public void getNextPlayerLastPosition(){
        instance.setPlayerInTurn(instance.getPlayers().get(instance.numOfPlayers()-1));
        assertEquals(0,instance.getPlayers().indexOf(instance.getNextPlayer()));
    }

    /**
     *Test that setNextPlayer set currentPlayer to the next in the ArrayList
     */
    @Test
    public void setNextPlayerNormal(){
        instance.setPlayerInTurn(instance.getPlayers().get(0));
        instance.setNextPlayer();
        assertEquals(0,instance.getPlayers().indexOf(instance.getPlayerInTurn())-1);
    }

    /**
     * Test that setNextPlayer set currentPlayer to the first player in the players ArrayList when currentPlayer is the last in the ArrayList
     */
    @Test
    public void setNextPlayerLastPlayer(){
        instance.setPlayerInTurn(instance.getPlayers().get(instance.getMaxPlayers()-1));
        instance.setNextPlayer();
        assertEquals(0,instance.getPlayers().indexOf(instance.getPlayerInTurn()));
    }


    /**
     * Test the restoring of the board when the number of players is 2
     */
    @Test
    public void restoreEmptyBoardTwoPlayer(){
        instance.setMaxPlayers(2);
        instance.restoreBoard(instance.getBoard());
        for(int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (instance.getBoard().getSpace(i,j).getTypeSpace().equals(TypeSpace.FOR_TWO_PLAYERS)){
                    assertTrue((instance.getBoard().getSpace(i,j).getObject() != null) && (instance.getObjectsDeck().getAvailableObjects() >= 132 - 29));
                }
            }
        }
    }


    /**
     *  Test the restoring of the board when the number of players is 3
     */
    @Test
    public void restoreEmptyBoardThreePlayer(){
        instance.setMaxPlayers(3);
        instance.addToGame(new Player("Carlo","0,0,PINK1,0,2,BLUE1,1,4,GREEN1,2,3,WHITE1,3,1,YELLOW1,5,2,LIGHT_BLUE1"));
        instance.restoreBoard(instance.getBoard());
        for(int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (instance.getBoard().getSpace(i,j).getTypeSpace().equals(TypeSpace.FOR_TWO_PLAYERS)||
                    instance.getBoard().getSpace(i,j).getTypeSpace().equals(TypeSpace.FOR_THREE_PLAYERS)){
                    assertTrue((instance.getBoard().getSpace(i,j).getObject() != null)&&(132 - 37 <= instance.getObjectsDeck().getAvailableObjects()));
                }
            }
        }
    }

    /**
     *  Test the restoring of the board when the number of players is 4
     */
    @Test
    public void restoreEmptyBoardFourPlayer(){
        instance.setMaxPlayers(4);
        instance.addToGame(new Player("Carlo","0,0,PINK1,0,2,BLUE1,1,4,GREEN1,2,3,WHITE1,3,1,YELLOW1,5,2,LIGHT_BLUE1"));
        instance.addToGame(new Player("Pietro","1,0,PINK1,2,2,BLUE1,1,4,GREEN1,2,3,WHITE1,3,2,YELLOW1,5,4,LIGHT_BLUE1"));
        instance.restoreBoard(instance.getBoard());
        for(int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (instance.getBoard().getSpace(i,j).getTypeSpace().equals(TypeSpace.FOR_TWO_PLAYERS)||
                    instance.getBoard().getSpace(i,j).getTypeSpace().equals(TypeSpace.FOR_THREE_PLAYERS)||
                    instance.getBoard().getSpace(i,j).getTypeSpace().equals(TypeSpace.FOR_FOUR_PLAYERS)){
                    assertTrue((instance.getBoard().getSpace(i,j).getObject() != null)&&(132 - 45 <= instance.getObjectsDeck().getAvailableObjects()));
                }
            }
        }
    }

    /**
     * Test the correctly setting of GameState to END
     */
    @Test
    public void endGameEND(){
        instance.endGame();
        assertEquals(GameState.END,instance.getGameState());
    }
}
