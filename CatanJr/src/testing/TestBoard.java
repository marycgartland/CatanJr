package testing;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.*;
import board.Board;
import player.Player;
import setup.BoardSetup;

public class TestBoard {

	private ArrayList<Player> testPlayerList;
	private BoardSetup testboardSetup;
	private Board testBoard;
	
	@Before
	public void setUp() throws Exception{
		testPlayerList=new ArrayList<Player>();
		testPlayerList.add(new Player("testPlayer1", "Blue"));
		testPlayerList.add(new Player("testPlayer2", "Red"));
		testPlayerList.add(new Player("testPlayer3", "White"));
		testboardSetup = new BoardSetup(testPlayerList);
		testBoard = Board.getInstance();
		testBoard = testboardSetup.getBoard();
	}

	@After
	public void tearDown() {
		testPlayerList.clear();
		testboardSetup = null;
		testBoard = null;
	}

	// ---------------------------------------------------------------------------------------------------------
	// --------- Testing mostCocotiles method ------------------------------------------------------------------
	// These tests cover testing the lair replacement at spooky island. If the ghost captain is in the centre of
	// the board, no lair can be placed here. Otherwise, the player with the most cocotiles places a lair here.
	// If two players have equal numbers of cocotiles, no lairs will be placed in the centre. These tests look 
	// at the letter in the center of the board (representing players lairs or the GC or neither), and checks
	// the lair count of a player if they have the most cocotiles. 
	// ---------------------------------------------------------------------------------------------------------
	
	// Test that if one player has the most cocotiles, they will get a lair in the center (if the GC isnt there)
	@Test
	public void testMostCocotilesPlayer() {
		// Setup: Remove GC from center and add cocotiles to players
		testBoard.moveGC(); 
		testPlayerList.get(0).addCocoTile();
		testPlayerList.get(0).addCocoTile();
		testPlayerList.get(1).addCocoTile();
		testBoard.mostCocotiles(testPlayerList);
		// Compare the letter expected at the center and the actual letter at the centre
		char boardCentre = testBoard.getBoardDesign()[8][18];
		assertEquals('B', boardCentre, "Most cocotiles, lair in centre - placement");
		assertEquals(3, testPlayerList.get(0).getLairCount(), "Most cocotiles, lair in centre - count");
	} 
	
	// Test if the player with the most cocotiles changes, the lair in the center changes 
	@Test
	public void testMostCocotilesSwitchPlayer() {
		// Setup: Remove GC from center and add cocotiles to player 1
		testBoard.moveGC();	
		testPlayerList.get(0).addCocoTile();
		testBoard.mostCocotiles(testPlayerList);
		// Test initial center player 
		char boardCentre = testBoard.getBoardDesign()[8][18];
		assertEquals('B', boardCentre, "Most cocotiles, lair in centre"); // Expecting 'B'
		// Setup for a center lair player switch 
		testPlayerList.get(1).addCocoTile();
		testPlayerList.get(1).addCocoTile();
		// Call method under test and test switch 
		testBoard.mostCocotiles(testPlayerList);
		boardCentre = testBoard.getBoardDesign()[8][18];
		assertEquals('R', boardCentre, "Most cocotiles, lair in centre"); // Expecting 'R'
		assertEquals(2, testPlayerList.get(0).getLairCount(), "Most cocotiles, lair in centre - count");
		assertEquals(3, testPlayerList.get(1).getLairCount(), "Most cocotiles, lair in centre - count"); 
		
	}
	
	// If the GC is in the center, the player with the most cocotiles will NOT get a lair in spooky island
	@Test
	public void testMostCocotilesPlayerGC() {
		// Setup -  Add cocotiles to players 
		testPlayerList.get(0).addCocoTile();			
		testPlayerList.get(0).addCocoTile();
		testPlayerList.get(1).addCocoTile();
		testBoard.mostCocotiles(testPlayerList);
		// Compare the letter expected at the center and the actual letter at the centre
		char boardCentre = testBoard.getBoardDesign()[8][18];
		assertEquals('G', boardCentre, "Most cocotiles, but GC is in centre");
		assertEquals(2, testPlayerList.get(0).getLairCount(), "Most cocotiles, lair in centre - count");
	}
	
	// If 2 players have the same number of cocotiles, no cocotiles in the middle
	@Test
	public void testMostCocotilesEqualPlayers() {
		// Setup: Move GC and add cocotiles to players
		testBoard.moveGC(); 
		testPlayerList.get(0).addCocoTile();						
		testPlayerList.get(1).addCocoTile();
		// Call Method Under Test
		testBoard.mostCocotiles(testPlayerList);
		// Compare the letter expected at the center and the actual letter at the centre
		char boardCentre = testBoard.getBoardDesign()[8][18];
		assertEquals(' ', boardCentre, "Equal most cocotiles, neither lair in center");
	}
}
