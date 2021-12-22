package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import board.Board;
import board.Marketplace;
import board.Stockpile;
import gameplay.Dice;
import player.Player;
import resources.Resources;
import setup.BoardSetup;
import setup.PlayerSetup;
import setup.ResourceSetup;

class TestMarketplace {

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	// Test that a resource is correctly swapped in/out of the marketplace (MUT: swapMarketplace)
	@Test
	void testMostCocotilesPlayer() {
		// SetUp players for testing
		ArrayList<Player> testPlayerList=new ArrayList<Player>();  // Set up test players 
		testPlayerList.add(new Player("testPlayer1", "Blue"));
		testPlayerList.add(new Player("testPlayer2", "Red"));
		testPlayerList.add(new Player("testPlayer3", "White"));
		testPlayerList.get(0).setupUserPocket();
		testPlayerList.get(1).setupUserPocket();
		testPlayerList.get(2).setupUserPocket();
		ResourceSetup resourceSetup = new ResourceSetup(testPlayerList);
		resourceSetup.getStockpile(); 							// Set up the games stockpile
		//this.cocoTiles = resourceSetup.getCocoTiles(); 							// Set up cocotiles
		///this.marketPlace = resourceSetup.getMarketplace(); 						// Set up the resources in the marketplace
		//swapMarketplace(Resources.Molasses, Resources.Gold, testPlayerList.get(1));
		//swapMarketplace(Resources wantedResource, Resources toSwapResource, Player player)
		// compare the letter expected at the center and the actual letter at the centre
		//char boardCentre = testBoard.getBoardDesign()[8][18];
		//assertEquals('B', boardCentre, "Most cocotiles, lair in centre - placement");	// Expecting 'B'
		//assertEquals(3, testPlayerList.get(0).getLairCount(), "Most cocotiles, lair in centre - count"); // Expecting 3 lairs
	}

}


// Checks that I want to do:
	// 1. Make sure that the marketplace resources are taken from the stockpile 
	// 2. Test that if a resource is in the marketplace, it is found (MUT: checkForResourceMarketplace)
	// 3. Test that if a resource isn't in the marketplace, it is not found (MUT: checkForResourceMarketplace)
	// 4. Test that the counter correctly counts the number of resources in the marketplace (MUT: checkForResourceMarketplaceStockpileRestock)


/*
 * // -------------------------------------------------------------------------
 * // ---------- Variables ----------------------------------------------------
 * // -------------------------------------------------------------------------
 * protected Resources[] marketPlace; // Array containing marketplace resources
 * protected Stockpile stockpile; private static Marketplace instance = new
 * Marketplace();
 * 
 * // -------------------------------------------------------------------------
 * // ---------- Constructor --------------------------------------------------
 * // -------------------------------------------------------------------------
 * private Marketplace() { }
 * 
 * // -------------------------------------------------------------------------
 * // ---------- Method: setupMarketplace -------------------------------------
 * // Removes 5 resources from stockpile to add to marketplace //
 * -------------------------------------------------------------------------
 * public void setupMarketplace(Stockpile stockpile) { this.marketPlace = new
 * Resources[] { Resources.Wood, Resources.Cutlasses, Resources.Goats,
 * Resources.Gold, Resources.Molasses }; this.stockpile = stockpile; }
 * 
 * // -------------------------------------------------------------------------
 * // ---------- Method: checkForResourceMarketplace --------------------------
 * // Check if specified resource is in the marketplace //
 * -------------------------------------------------------------------------
 * public boolean checkForResourceMarketplace(Resources resource) { boolean
 * resource_found = false; for (int i = 0; i <= 4; i++) { if (marketPlace[i] ==
 * resource) { resource_found = true; } } return resource_found; }
 * 
 * // -------------------------------------------------------------------------
 * // ---------- Method: checkForResourceMarketplaceStockpileRestock ----------
 * // Check for resource in marketplace when stockpile needs to be restocked. //
 * This returns the number of the resource in the marketplace //
 * -------------------------------------------------------------------------
 * public int checkForResourceMarketplaceStockpileRestock(Resources resource) {
 * int counter = 0; for (int i = 0; i <= 4; i++) { if (marketPlace[i] ==
 * resource) { counter = counter + 1; } } return counter; }
 * 
 * // -------------------------------------------------------------------------
 * // ---------- Method: swapMarketplace --------------------------------------
 * // Method to swap one resource for another //
 * -------------------------------------------------------------------------
 * public String swapMarketplace(Resources wantedResource, Resources
 * toSwapResource, Player player) { String message = " "; for (int i = 0; i <=
 * 4; i++) { if (marketPlace[i] == wantedResource) { marketPlace[i] =
 * toSwapResource; // Update the marketplace resources
 * player.addResource(wantedResource, 1); // Add one of wanted resource to
 * players pocket player.removeResource(toSwapResource, 1); // Remove one of
 * swapped resource to players pocket checkMarketplace(); // Check that all
 * marketplace resources are not the same type message=
 * "successful marketplace trade"; } } return message; }
 * 
 * // -------------------------------------------------------------------------
 * // ---------- Method: checkMarketplace -------------------------------------
 * // Check marketplace to see if there are 5 of the same element or not. //
 * -------------------------------------------------------------------------
 * public void checkMarketplace() { int counter = 0; for (int i = 0; i <= 3;
 * i++) { if (marketPlace[i] == marketPlace[i + 1]) { counter = counter + 1; } }
 * // If all array elements are equal, send these back to stockpile, and reset
 * marketplace if (counter == 4) { stockpile.returnResource(marketPlace[1], 5);
 * // Return resources from marketplace to stockpile
 * setupMarketplace(stockpile); // Setup marketplace with the 5 different
 * elements } }
 * 
 * // -------------------------------------------------------------------------
 * // ---------- Method: viewMarketplace --------------------------------------
 * // Return string containing marketplace contents //
 * -------------------------------------------------------------------------
 * public String viewMarketplace() { String toPrint =
 * String.valueOf(marketPlace[0]) + ", " + String.valueOf(marketPlace[1]) + ", "
 * + String.valueOf(marketPlace[2]) + ", " + String.valueOf(marketPlace[3]) +
 * ", " + String.valueOf(marketPlace[4]); return toPrint; }
 * 
 * // ------------------------------------------------------------------------
 * // ---------- Method: getInstance -----------------------------------------
 * // ------------------------------------------------------------------------
 * public static Marketplace getInstance() { return instance; }
 */