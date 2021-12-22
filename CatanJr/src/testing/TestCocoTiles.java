package testing;

//----------------------------------------------------------------------------------------
// This JUnit test tests methods form the CocoTiles class (in the resources package. It
// runs 2 tests, including ensuring the cocotiles are actually shuffled, and also that 
// when a player buys a cocotiles, it is properly removed from the cocotiles pile.
//----------------------------------------------------------------------------------------

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import resources.CocoTileTypes;
import resources.CocoTiles;

class TestCocoTiles {
	// ---------- Test cocotile construction - ensure random order and correct amount ----------
	@Test
	void test() {
		// Create cocoTile array to test correct setup and shuffling
		CocoTiles testCocoTiles = new CocoTiles();
		// Create an unshuffled cocotile array to compare to
		ArrayList<CocoTileTypes> NotExpectedCocoTiles = new ArrayList<CocoTileTypes>(
				Arrays.asList(CocoTileTypes.WoodMolasses, CocoTileTypes.WoodMolasses, CocoTileTypes.WoodMolasses,
						CocoTileTypes.GoatCutlasses, CocoTileTypes.GoatCutlasses, CocoTileTypes.GoatCutlasses,
						CocoTileTypes.ShipCastle, CocoTileTypes.ShipCastle, CocoTileTypes.ShipCastle,
						CocoTileTypes.GhostCaptain, CocoTileTypes.GhostCaptain, CocoTileTypes.GhostCaptain,
						CocoTileTypes.GhostCaptain, CocoTileTypes.GhostCaptain, CocoTileTypes.GhostCaptain,
						CocoTileTypes.GhostCaptain, CocoTileTypes.GhostCaptain, CocoTileTypes.GhostCaptain,
						CocoTileTypes.GhostCaptain, CocoTileTypes.GhostCaptain));
		// Test the sizes are the same, but the ordering is not
		assertEquals(NotExpectedCocoTiles.size(), testCocoTiles.getCocoTiles().size(), "Cocotiles array size test");
		assertNotSame(NotExpectedCocoTiles, testCocoTiles.getCocoTiles(), "Cocotiles contest shuffled test");
	}
	
	// ---------- Test cocotile construction - ensure random order and correct amount ----------
	@Test
	void testBuyCocotiles() {
		// Create CocoTile array to test the buyCocoTile method on
		CocoTiles testCocoTiles = new CocoTiles();
		testCocoTiles.buyCocoTile();
		// Create a CocoTile array as the expected array 
		ArrayList<CocoTileTypes> compareCocoTiles = testCocoTiles.getCocoTiles();
		compareCocoTiles.remove(0);
		// Test buyCocoTile() method
		assertEquals(compareCocoTiles, testCocoTiles.getCocoTiles(), "Cocotiles buy method");
	}
}