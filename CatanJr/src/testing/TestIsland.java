package testing;
// -----------------------------------------------------------------------------------------------------------
// This JUnit test tests the Island class of the board package. It tests the checkArray() method, which deals
// with assigning resources to players on the dice roll if they have a lair placed along an island with that
// dice roll value. This tests each of the 12 islands, and ensured that resources were assigned to players as 
// needed. This was based on initial lair placement of player lairs at setup. Additionally, a test was ran to 
// ensure that if a ghost captain was placed at an island, no resources will be distributed from that island
// on a dice roll.
// -----------------------------------------------------------------------------------------------------------
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.*;
import board.Board;
import board.Island;
import player.Player;
import resources.Resources;
import setup.BoardSetup;

public class TestIsland {

	// ---------------------------------------------------------------------------------
	// ---------- Declare all objects needed -------------------------------------------
	// ---------------------------------------------------------------------------------
	private ArrayList<Player> testPlayerList;
	private Player testPlayer1;
	private Player testPlayer2;
	private Player testPlayer3;
	private Player testPlayer4;
	private BoardSetup testboardSetup;
	private Board testBoard;
	private char[][] designTest;	
	private Island[] testIslands;
	
	// ---------------------------------------------------------------------------------
	// ---------- Setup to do before each test - board and player setup ----------------
	// ---------------------------------------------------------------------------------
	@Before
	public void setUp() throws Exception{
		// Set up each player and their pockets 
		testPlayer1 = new Player("testPlayer1", "Blue");
		testPlayer2 = new Player("testPlayer2", "Red");
		testPlayer3 = new Player("testPlayer3", "White");
		testPlayer4 = new Player("testPlayer4", "Orange");
		testPlayer1.setupUserPocket();
		testPlayer2.setupUserPocket();
		testPlayer3.setupUserPocket();
		testPlayer4.setupUserPocket();
		
		// Add the players to a player array 
		testPlayerList = new ArrayList<Player>();
		testPlayerList.add(testPlayer1);
		testPlayerList.add(testPlayer2);
		testPlayerList.add(testPlayer3);
		testPlayerList.add(testPlayer4);
		
		// Setup the board - place lairs and island setup
		testboardSetup = new BoardSetup(testPlayerList);
		testboardSetup.setupBoard(4);
		
		// Get the board, and therefore the design
		testBoard = testboardSetup.getBoard();
		designTest = testBoard.getBoardDesign();	
		testIslands = testBoard.getIslands();
		
		System.out.println("Setup");
	}
	
	// ---------------------------------------------------------------------------------
	// ---------- Tear down objects set up after each test -----------------------------
	// ---------------------------------------------------------------------------------
	@After
	public void tearDown() {
		testPlayerList.clear();
		testPlayer1 = null;
		testPlayer2 = null;
		testPlayer3 = null;
		testPlayer4 = null;
		testboardSetup = null;
		designTest = null;
		testIslands = null;
		System.out.println("Teardown");
	}
	
	// ---------------------------------------------------------------------------------
	// ---------- Test method: checkArray() --------------------------------------------
	// Check each island and distribute resources to players based on initial lair setup
	// ---------------------------------------------------------------------------------
	
	// ---------- Island 1: red and white should get a cutlass -------------------------
	@Test
	public void testIsland1() {
		// Set up initial values 
		int initialResourceR = testPlayer2.checkPocketResources("C");
		int initialResourceW = testPlayer3.checkPocketResources("C");
		// Call method under test - Island 1, adding cutlasses
		testIslands[0].checkArray(testPlayerList, Resources.Cutlasses, ' ', designTest);
		// Set up final values 
		int finalResourceR = testPlayerList.get(1).checkPocketResources("C");
		int finalResourceW = testPlayerList.get(1).checkPocketResources("C");
		// Test
		assertEquals(1, finalResourceR - initialResourceR, "Testing adding resource - Island 1, Red");
		assertEquals(1, finalResourceW - initialResourceW, "Testing adding resource - Island 1, White");
	} 
	

	// ---------- Island 2: red should get wood ----------------------------------------
	@Test
	public void testIsland2() {
		// Set up initial values 
		int initialResourceR = testPlayer2.checkPocketResources("W");
		// Call method under test - Island 1, adding cutlasses
		testIslands[1].checkArray(testPlayerList, Resources.Wood, ' ', designTest);
		// Set up final values 
		int finalResourceR = testPlayerList.get(1).checkPocketResources("W");
		// Test
		assertEquals(1, finalResourceR - initialResourceR, "Testing adding resource - Island 2, Red");
	} 
	

	// ---------- Island 3: orange should get a goat -----------------------------------
	@Test
	public void testIsland3() {
		// Set up initial values 
		int initialResourceO = testPlayer4.checkPocketResources("GT");	
		// Call method under test - Island 1, adding cutlasses
		testIslands[2].checkArray(testPlayerList, Resources.Goats, ' ', designTest);
		// Set up final values 
		int finalResourceO = testPlayerList.get(3).checkPocketResources("GT");
		// Test
		assertEquals(1, finalResourceO - initialResourceO, "Testing adding resource - Island 3, Orange");
	} 

	// ---------- Island 4: orange and blue should get a molasses ----------------------
	@Test
	public void testIsland4() {
		// Set up initial values 
		int initialResourceB = testPlayer1.checkPocketResources("M");
		int initialResourceO = testPlayer4.checkPocketResources("M");
		// Call method under test - Island 1, adding cutlasses
		testIslands[3].checkArray(testPlayerList, Resources.Molasses, ' ', designTest);
		// Set up final values 
		int finalResourceB = testPlayerList.get(0).checkPocketResources("M");
		int finalResourceO = testPlayerList.get(3).checkPocketResources("M");
		// Test
		assertEquals(1, finalResourceB - initialResourceB, "Testing adding resource - Island 4, Blue");
		assertEquals(1, finalResourceO - initialResourceO, "Testing adding resource - Island 4, Orange");
	}
	
	// ---------- Island 5: orange and white should get a wood -------------------------
	@Test
	public void testIsland5() {
		// Set up initial values 
		int initialResourceW = testPlayer3.checkPocketResources("W");
		int initialResourceO = testPlayer4.checkPocketResources("W");
		// Call method under test - Island 1, adding cutlasses
		testIslands[4].checkArray(testPlayerList, Resources.Wood, ' ', designTest);
		// Set up final values 
		int finalResourceW = testPlayerList.get(2).checkPocketResources("W");
		int finalResourceO = testPlayerList.get(3).checkPocketResources("W");
		// Test
		assertEquals(1, finalResourceW - initialResourceW, "Testing adding resource - Island 5, White");
		assertEquals(1, finalResourceO - initialResourceO, "Testing adding resource - Island 5, Orange");
	}
	 
	// ---------- Island 8: blue and red should get a goat -----------------------------
	@Test
	public void testIsland8() {
		// Set up initial values 
		int initialResourceB = testPlayer1.checkPocketResources("GT");
		int initialResourceR = testPlayer2.checkPocketResources("GT");
		// Call method under test - Island 1, adding cutlasses
		testIslands[7].checkArray(testPlayerList, Resources.Goats, ' ', designTest);
		// Set up final values 
		int finalResourceB = testPlayerList.get(0).checkPocketResources("GT");
		int finalResourceR = testPlayerList.get(1).checkPocketResources("GT");
		// Test
		assertEquals(1, finalResourceB - initialResourceB, "Testing adding resource - Island 8, Blue");
		assertEquals(1, finalResourceR - initialResourceR, "Testing adding resource - Island 8, Red");
	}
	

	// ---------- Island 9: orange and blue should get a cutlass -----------------------
	@Test
	public void testIsland9() {
		// Set up initial values 
		int initialResourceB = testPlayer1.checkPocketResources("C");
		int initialResourceO = testPlayer4.checkPocketResources("C");
		// Call method under test - Island 1, adding cutlasses
		testIslands[8].checkArray(testPlayerList, Resources.Cutlasses, ' ', designTest);
		// Set up final values 
		int finalResourceB = testPlayerList.get(0).checkPocketResources("C");
		int finalResourceO = testPlayerList.get(3).checkPocketResources("C");
		// Test
		assertEquals(1, finalResourceB - initialResourceB, "Testing adding resource - Island 9, Blue");
		assertEquals(1, finalResourceO - initialResourceO, "Testing adding resource - Island 9, Orange");
	}

	// ---------- Island 10: blue should get a wood ------------------------------------
	@Test
	public void testIsland10() {
		// Set up initial values 
		int initialResourceB = testPlayer1.checkPocketResources("W");
		// Call method under test - Island 1, adding cutlasses
		testIslands[9].checkArray(testPlayerList, Resources.Wood, ' ', designTest);
		// Set up final values 
		int finalResourceB = testPlayerList.get(0).checkPocketResources("W");
		// Test
		assertEquals(1, finalResourceB - initialResourceB, "Testing adding resource - Island 10, Blue");
	}
	
	// ---------- Island 11: white should get a goat -----------------------------------
	@Test
	public void testIsland11() {
		// Set up initial values 
		int initialResourceW = testPlayer3.checkPocketResources("GT");
		// Call method under test - Island 1, adding cutlasses
		testIslands[10].checkArray(testPlayerList, Resources.Goats, ' ', designTest);
		// Set up final values 
		int finalResourceW = testPlayerList.get(2).checkPocketResources("GT");
		// Test
		assertEquals(1, finalResourceW - initialResourceW, "Testing adding resource - Island 11, White");
	}
	
	// ---------- Island 12: red and white should get molasses -------------------------
	@Test
	public void testIsland12() {
		// Set up initial values 
		int initialResourceR = testPlayer2.checkPocketResources("M");
		int initialResourceW = testPlayer3.checkPocketResources("M");
		// Call method under test - Island 1, adding cutlasses
		testIslands[11].checkArray(testPlayerList, Resources.Molasses, ' ', designTest);
		// Set up final values 
		int finalResourceR = testPlayerList.get(1).checkPocketResources("M");
		int finalResourceW = testPlayerList.get(2).checkPocketResources("M");
		// Test
		assertEquals(1, finalResourceR - initialResourceR, "Testing adding resource - Island 12, Red");
		assertEquals(1, finalResourceW - initialResourceW, "Testing adding resource - Island 12, White");
	}
	
	// ---------------------------------------------------------------------------------
	// ---------- Test method: checkArray() --------------------------------------------
	// Test that resources aren't assigned if a Ghost Captain is at the island. 
	// Test island 1 again 
	// ---------------------------------------------------------------------------------
	@Test
	public void testIslandGC() {
		// Set up initial values 
		int initialResourceR = testPlayer2.checkPocketResources("C");
		int initialResourceW = testPlayer3.checkPocketResources("C");
		// Move the ghost captain to island 1
		testBoard.moveGhostCaptain("1");
		// Call method under test - Island 1, adding cutlasses
		testIslands[0].checkArray(testPlayerList, Resources.Cutlasses, ' ', designTest);
		// Set up final values 
		int finalResourceR = testPlayerList.get(1).checkPocketResources("C");
		int finalResourceW = testPlayerList.get(1).checkPocketResources("C");
		// Test
		assertEquals(1, finalResourceR - initialResourceR, "Testing adding resource - Island 1, Red");
		assertEquals(1, finalResourceW - initialResourceW, "Testing adding resource - Island 1, White");
	}
}