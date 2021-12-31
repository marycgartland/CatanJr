package testing;

//-----------------------------------------------------------------------------------------------------------
// This JUnit test tests the BoardSetup class in the setup package. It tests the setupBoard() method to 
// ensure that if 3 players are in the game, only the blue, red, and white players initial lairs and ships
// are setup. If 4 players are in the game, then the orange player's ships and lairs should be setup as well.
// These tests covered setupBluePlayerLocations, setupRedPlayerLocations, setupWhitePlayerLocations, and
// setupOrangePlayerLocations methods in the process. The method setUpGhostCaptain() was also tested to 
// ensure that the ghost captain was properly placed in the center of the board for the start of the game.
// Finally, the setUpIslands() method was tested to ensure that 12 islands were setup as expected.
//-----------------------------------------------------------------------------------------------------------

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.*;
import org.junit.*;
import board.Board;
import board.Island;
import player.Player;
import setup.BoardSetup;

public class TestBoardSetup {

	private ArrayList<Player> testPlayerList4;
	private ArrayList<Player> testPlayerList3;
	private Player testPlayer1;
	private Player testPlayer2;
	private Player testPlayer3;
	private Player testPlayer4;
	private BoardSetup testboardSetup3;
	private BoardSetup testboardSetup4;
	private Board testBoard3;
	private Board testBoard4;
	private char[][] designTest3;
	private char[][] designTest4;
	
	@Before
	public void setUp() throws Exception{		
		// 4-player  
		testPlayerList4 = new ArrayList<Player>();
		testPlayerList4.add(testPlayer1);
		testPlayerList4.add(testPlayer2);
		testPlayerList4.add(testPlayer3);
		testPlayerList4.add(testPlayer4);
		testboardSetup4 = new BoardSetup(testPlayerList4);
		testBoard4 = testboardSetup4.getBoard();
		designTest4 = testBoard4.getBoardDesign();
		
		// 3-player
		testPlayerList3 = new ArrayList<Player>();
		testPlayerList3.add(testPlayer1);
		testPlayerList3.add(testPlayer2);
		testPlayerList3.add(testPlayer3);
		testboardSetup3 = new BoardSetup(testPlayerList3);
		testBoard3 = testboardSetup3.getBoard();
		designTest3 = testBoard3.getBoardDesign();
	}
	
	@After
	public void tearDown() {
		testPlayerList3.clear();
		testPlayerList4.clear();
		testboardSetup4 = null;
		testboardSetup3 = null;
		testBoard4 = null;
		testBoard3 = null;
		designTest3 = null;
		designTest4 = null;
	}
	
	// ---------------------------------------------------------
	// ---------- Testing Method: setupBoard() -----------------
	// ---------------------------------------------------------
	
	// Confirm that if 3 players are playing- Blue lairs/ships are setup
	@Test
	public void testSetupBoardBlue3() {
		// Test expected Lair locations 
		assertEquals('B', designTest3[5][30], "setupBoard test - 3 players, blue, L1");
		assertEquals('B', designTest3[13][12], "setupBoard test - 3 players, blue, L2");
		// Test expected ship locations 
		assertEquals('b', designTest3[6][28], "setupBoard test - 3 players, blue, S1");
		assertEquals('b', designTest3[12][12], "setupBoard test - 3 players, blue, S2");
	} 
	
	// Confirm that if 3 players are playing- Red lairs/ships are setup
	@Test
	public void testSetupBoardRed3() {
		// Test expected Lair locations 
		assertEquals('R', designTest3[3][12], "setupBoard test - 3 players, red, L1");
		assertEquals('R', designTest3[11][30], "setupBoard test - 3 players, red, L2");
		// Test expected ship locations 
		assertEquals('r', designTest3[4][12], "setupBoard test - 3 players, red, S1");
		assertEquals('r', designTest3[10][28], "setupBoard test - 3 players, red, S2");
	} 
	
	// Confirm that if 3 players are playing- White lairs/ships are setup
	@Test
	public void testSetupBoardWhite3() {
		// Test expected Lair locations 
		assertEquals('W', designTest3[5][6], "setupBoard test - 3 players, white, L1");
		assertEquals('W', designTest3[13][24], "setupBoard test - 3 players, white, L2");
		// Test expected ship locations 
		assertEquals('w', designTest3[6][8], "setupBoard test - 3 players, white, S1");
		assertEquals('w', designTest3[12][24], "setupBoard test - 3 players, white, S2");
	} 
	
	
	// Confirm that if 3 players are playing- Orange lairs/ships are not setup
	@Test
	public void testSetupBoardOrange3() {
		// Test expected Lair locations 
		assertEquals('X', designTest3[3][24], "setupBoard test - 3 players, orange, L1");
		assertEquals('X', designTest3[11][6], "setupBoard test - 3 players, orange, L2");
		// Test expected ship locations 
		assertEquals('|', designTest3[4][24], "setupBoard test - 3 players, orange, S1");
		assertEquals('/', designTest3[10][8], "setupBoard test - 3 players, orange, S2");
	} 
	
	// Confirm that if 4 players are playing- Blue lairs/ships are setup
	@Test
	public void testSetupBoardBlue4() {
		// Test expected Lair locations 
		assertEquals('B', designTest4[5][30], "setupBoard test - 4 players, blue, L1");
		assertEquals('B', designTest4[13][12], "setupBoard test - 4 players, blue, L2");
		// Test expected ship locations 
		assertEquals('b', designTest4[6][28], "setupBoard test - 4 players, blue, S1");
		assertEquals('b', designTest4[12][12], "setupBoard test - 4 players, blue, S2");
	} 
	
	// Confirm that if 4 players are playing- Red lairs/ships are setup
	@Test
	public void testSetupBoardRed4() {
		// Test expected Lair locations 
		assertEquals('R', designTest4[3][12], "setupBoard test - 4 players, red, L1");
		assertEquals('R', designTest4[11][30], "setupBoard test - 4 players, red, L2");
		// Test expected ship locations 
		assertEquals('r', designTest4[4][12], "setupBoard test - 4 players, red, S1");
		assertEquals('r', designTest4[10][28], "setupBoard test - 4 players, red, S2");
	} 
	
	// Confirm that if 4 players are playing- White lairs/ships are setup
	@Test
	public void testSetupBoardWhite4() {
		// Test expected Lair locations 
		assertEquals('W', designTest4[5][6], "setupBoard test - 4 players, white, L1");
		assertEquals('W', designTest4[13][24], "setupBoard test - 4 players, white, L2");
		// Test expected ship locations 
		assertEquals('w', designTest4[6][8], "setupBoard test - 4 players, white, S1");
		assertEquals('w', designTest4[12][24], "setupBoard test - 4 players, white, S2");
	} 
	
	// Confirm that if 4 players are playing- Orange lairs/ships are setup
	@Test
	public void testSetupBoardOrange4() {
		// Test expected Lair locations 
		assertEquals('O', designTest4[3][24], "setupBoard test - 4 players, orange, L1");
		assertEquals('O', designTest4[11][6], "setupBoard test - 4 players, orange, L2");
		// Test expected ship locations 
		assertEquals('o', designTest4[4][24], "setupBoard test - 4 players, orange, S1");
		assertEquals('o', designTest4[10][8], "setupBoard test - 4 players, orange, S2");
	}

	// ---------------------------------------------------------
	// ---------- Testing Method: setUpGhostCaptain() ----------
	// ---------------------------------------------------------
	@Test
	public void testSetupGhostCaptain() {
		// Ensure the GC is in the location it should be to begin
		assertEquals('G', designTest4[8][18], "setupGhostCaptain test");
	}
	
	// ---------------------------------------------------------
	// ---------- Testing Method: setUpIslands() ---------------
	// ---------------------------------------------------------
	@Test
	public void testSetupIslands() {
		// Ensure the correct number of islands are setup
		Island[] testIslands;
		testIslands = testBoard4.getIslands();
		assertEquals(12, Array.getLength(testIslands), "setupIslands test");
	}
}