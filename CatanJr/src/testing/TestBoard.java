package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import board.*;
import gameplay.*;
import main.*;
import player.*;
import resources.*;
import setup.*;


class TestBoard {
	


	// TODO: Before and after
	// ---------- 
	//@Before
	//public void setUp() throws Exception{
	//	// SetUp players for testing
	//	ArrayList<Player> testPlayerList=new ArrayList<Player>();  // Set up test players 
	//	//Player player1 = new Player("testPlayer1", "Blue");
	//	testPlayerList.add(new Player("testPlayer1", "Blue"));
	//	testPlayerList.add(new Player("testPlayer2", "Red"));
	//	testPlayerList.add(new Player("testPlayer3", "White"));
	//	BoardSetup testboardSetup = new BoardSetup(testPlayerList);	// Set up a test board
	//	Board board = new Board();
	//	board = testboardSetup.getBoard();
	//}
	
	//@Before
	//public void setUp() throws Exception{
		
	//}
	
	
	
	// --------------- Testing mostCocotiles method ------------------------------------------------
	// This method covers the the methods reduceLairCount() and placeLairMostCocotile(). These tests
	// cover testing how the lair placement at spooky island changes when the player with  the most
	// cocotiles changes. We have tested 4 cases here. This includes when one player has the most
	// but the GC is in the center so their lair can't be placed; when one player has the most tiles
	// and can place a lair; when the player with the most cocotiles switches from one to another;
	// and when two players are equal for the most cocotiles.
	
	// Test that if one player has the most cocotiles, they will get a lair in the center (if the GC isnt there)
	@Test
	void testMostCocotilesPlayer() {
		// SetUp players for testing
		ArrayList<Player> testPlayerList=new ArrayList<Player>();  // Set up test players 
		testPlayerList.add(new Player("testPlayer1", "Blue"));
		testPlayerList.add(new Player("testPlayer2", "Red"));
		testPlayerList.add(new Player("testPlayer3", "White"));
		BoardSetup testboardSetup = new BoardSetup(testPlayerList);	// Set up a test board
		Board testBoard = new Board();
		testBoard = testboardSetup.getBoard();
		testBoard.moveGC(); 										// Remove GC from centre
		testPlayerList.get(0).addCocoTile();						// Add cocotiles to players
		testPlayerList.get(0).addCocoTile();
		testPlayerList.get(1).addCocoTile();
		testBoard.mostCocotiles(testPlayerList);
		// compare the letter expected at the center and the actual letter at the centre
		char boardCentre = testBoard.getBoardDesign()[8][18];
		assertEquals('B', boardCentre, "Most cocotiles, lair in centre - placement");	// Expecting 'B'
		assertEquals(3, testPlayerList.get(0).getLairCount(), "Most cocotiles, lair in centre - count"); // Expecting 3 lairs
	}
	
	// Test that if one player has the most cocotiles, their lair won't be placed in the center if the GC is there
		@Test
		void testMostCocotilesPlayerGC() {
			// Setup
			ArrayList<Player> testPlayerList=new ArrayList<Player>();  	// Set up test players 
			testPlayerList.add(new Player("testPlayer1", "Blue"));
			testPlayerList.add(new Player("testPlayer2", "Red"));
			testPlayerList.add(new Player("testPlayer3", "White"));
			BoardSetup testboardSetup = new BoardSetup(testPlayerList);	// Set up a test board
			Board testBoard = new Board();
			testBoard = testboardSetup.getBoard();
			testPlayerList.get(0).addCocoTile();						// Add cocotiles to players 
			testPlayerList.get(0).addCocoTile();
			testPlayerList.get(1).addCocoTile();
			testBoard.mostCocotiles(testPlayerList);
			// Compare the letter expected at the center and the actual letter at the centre
			char boardCentre = testBoard.getBoardDesign()[8][18];
			assertEquals('G', boardCentre, "Most cocotiles, but GC is in centre");
			assertEquals(2, testPlayerList.get(0).getLairCount(), "Most cocotiles, lair in centre - count"); // Expecting 2 lairs
		}
		
	// Test if the player with the most cocotiles changes, the lair in the center changes 
		@Test
		void testMostCocotilesSwitchPlayer() {
			// SetUp players for testing
			ArrayList<Player> testPlayerList=new ArrayList<Player>();  		// Set up test players 
			testPlayerList.add(new Player("testPlayer1", "Blue"));
			testPlayerList.add(new Player("testPlayer2", "Red"));
			testPlayerList.add(new Player("testPlayer3", "White"));
			BoardSetup testboardSetup = new BoardSetup(testPlayerList);		// Set up a test board
			Board testBoard = new Board();
			testBoard = testboardSetup.getBoard();
			testBoard.moveGC(); 											// Remove GC from centre
			testPlayerList.get(0).addCocoTile();							// Add cocotiles to players
			testBoard.mostCocotiles(testPlayerList);
			char boardCentre = testBoard.getBoardDesign()[8][18];
			assertEquals('B', boardCentre, "Most cocotiles, lair in centre"); // Expecting 'B'
			testPlayerList.get(1).addCocoTile();
			testPlayerList.get(1).addCocoTile();
			testBoard.mostCocotiles(testPlayerList);
			// compare the letter expected at the center and the actual letter at the centre
			boardCentre = testBoard.getBoardDesign()[8][18];
			assertEquals('R', boardCentre, "Most cocotiles, lair in centre"); // Expecting 'R'
			assertEquals(2, testPlayerList.get(0).getLairCount(), "Most cocotiles, lair in centre - count"); // Expecting 2 lairs
			assertEquals(3, testPlayerList.get(1).getLairCount(), "Most cocotiles, lair in centre - count"); // Expecting 3 lairs
		}
		
	// If 2 players have the same number of cocotiles, no cocotiles in the middle
		@Test
		void testMostCocotilesEqualPlayers() {
			// Setup
			ArrayList<Player> testPlayerList=new ArrayList<Player>();  	// Set up test players 
			testPlayerList.add(new Player("testPlayer1", "Blue"));
			testPlayerList.add(new Player("testPlayer2", "Red"));
			testPlayerList.add(new Player("testPlayer3", "White"));
			BoardSetup testboardSetup = new BoardSetup(testPlayerList);	// Set up a test board
			Board testBoard = new Board();
			testBoard = testboardSetup.getBoard();
			testBoard.moveGC(); 
			testPlayerList.get(0).addCocoTile();						// Add cocotiles to players 
			testPlayerList.get(1).addCocoTile();
			testBoard.mostCocotiles(testPlayerList);
			// Compare the letter expected at the center and the actual letter at the centre
			char boardCentre = testBoard.getBoardDesign()[8][18];
			assertEquals(' ', boardCentre, "Equal most cocotiles, neither lair in center");
		}
		
		//--------------- Testing islandResourceDistribution method -----------------------------------
		// I will test the general 1-5 dice roll, which is resource distribution
		// Expected: Red molasses and blue cutlass
		//@Test
		//void testIslandResourceDistribution() {
		//	System.out.println("test");
		//	ArrayList<Player> testPlayerList=new ArrayList<Player>();  // Set up test players 
		//	testPlayerList.add(new Player("testPlayer1", "Blue"));
		//	testPlayerList.add(new Player("testPlayer2", "Red"));
		//	testPlayerList.add(new Player("testPlayer3", "White"));
		//	BoardSetup testboardSetup = new BoardSetup(testPlayerList);	// Set up a test board
		//	Board testBoard = new Board();
		//	testBoard = testboardSetup.getBoard();
		//	testBoard.islandResourceDistribution(4, testBoard.getIslands(), testPlayerList);
		//	System.out.println("test2");
		//	System.out.println("this: " + testPlayerList.get(0).checkPocketResourcesLetter("C"));
		//	System.out.println(testPlayerList.get(0).PlayerName());
		//	//assertEquals("testPlayer1", testPlayerList.get(0).PlayerName(), "Resource assignment - player 1, roll 4");
		//	// Compare assigned resources with desired assigned resources for players touching islands
		///	//assertEquals(1, testPlayerList.get(0).checkPocketResourcesLetter("C"), "Resource assignment - player 1, roll 4");
		//	//assertEquals(1, testPlayerList.get(1).checkPocketResourcesLetter("M"), "Resource assignment - player 2, roll 4");
		//}

}


// Note: We did not test methods requiring user interaction when using Junit tests.


