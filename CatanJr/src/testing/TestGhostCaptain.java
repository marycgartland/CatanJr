package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import board.GhostCaptain;
import gameplay.Dice;
import player.Player;
import setup.BoardSetup;

class TestGhostCaptain {
	@Test
	// --------------- Testing Changing Ghost Captain Location ---------------
	void testGhostCaptain() {
		GhostCaptain testGC; 
		ArrayList<Player> testPlayerList=new ArrayList<Player>();
		BoardSetup boardSetup = new BoardSetup(testPlayerList);			// Set up board			
		//Board testBoard;
		//testBoard = boardSetup.getBoard();
		//setupGhostCaptain();
		//board.setGhostCaptain(GhostCaptain ghostCaptain)
		//protected GhostCaptain ghostCaptain;
		//testGC.updateLocationGC(6);
		
		//BoardSetup(ArrayList<Player> players)
		//testGC.setUpGhostCaptain();
		//assertEquals(10, testGC.getGhostCaptainLocation(), "Testing Ghost Captain");	// Test
		//testGC.updateLocationGC(8);														// Change
		//assertEquals(8, testGC.getGhostCaptainLocation(), "Testing Ghost Captain");		// Test
	}
}



//public class GhostCaptain {
//	
//	protected int location; // location of Ghost captain
//	private static GhostCaptain instance = new GhostCaptain(13); // create instance of ghost captain
//	
//	// -----------------------------------------------------
//	// ---------- Constructor ------------------------------
//	// Ghost captain will be placed on spooky island to start
//	// -----------------------------------------------------
//	private GhostCaptain(int island_number) {
//		this.location = island_number;
//	}
//	
//	// -----------------------------------------------------
//	// ---------- Method: updateLocationGC -----------------
//	// Update the ghost captains location
//	// -----------------------------------------------------
//	public void updateLocationGC(int island_update) {
//		this.location = island_update;
//	}
//
//	// -----------------------------------------------------
//	// ---------- Method: getGhostCaptainLocation ----------
//	// -----------------------------------------------------
//	public int getGhostCaptainLocation() {
//		return this.location;
//	}
//	
//	// ----------------------------------------------------
//	// ---------- Method: getInstance ---------------------
//	// ----------------------------------------------------
//	public static GhostCaptain getInstance() {
//		return instance;
//	}
//}