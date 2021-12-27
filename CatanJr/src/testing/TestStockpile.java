package testing;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import board.Stockpile;
import player.Player;
import resources.Resources;
import setup.ResourceSetup;

class TestStockpile {

	// ----------------------------------------------------------------------------
	// --------- Section 1 Testing : Testing swapStockpile() method ---------------
	// ----------------------------------------------------------------------------
	// 1.1 If valid resources, check marketplace has 1 less of traded out resource
	@Test
	void testSwapStockpileCountsSOut() {
		// Set up of players and stockpile for testing
		ArrayList<Player> testPlayerList=new ArrayList<Player>();
		testPlayerList.add(new Player("testPlayer1", "Blue"));
		testPlayerList.get(0).setupUserPocket();
		ResourceSetup resourceSetup = new ResourceSetup(testPlayerList);
		testPlayerList.get(0).addResource(Resources.Molasses, 1);
		int StockpileOutInitial = resourceSetup.getStockpile().getResourceCount(Resources.Gold);
		// Call Method under test. Wanted resource: Gold ; To swap resource: Molasses
		resourceSetup.getStockpile().swapStockpile(Resources.Gold, Resources.Molasses, testPlayerList.get(0)); 
		// Test expected results. The stockpile should have 1 less gold
		int StockpileOut = resourceSetup.getStockpile().getResourceCount(Resources.Gold);
		// Expect 18 - 1 (for marketplae) - 1 (for trade) = 16 Gold's in stockpile
		assertEquals(17, StockpileOutInitial, "Stockpile trade out success- initial");	// Count before trade
		assertEquals(16, StockpileOut, "Stockpile trade out success- final");			// Count after trade
	}
	
	// 1.2 If valid resources, check marketplace has 2 more of traded in resource
	@Test
	void testSwapStockpileSIn() {
		// Set up of players and stockpile for testing
		ArrayList<Player> testPlayerList=new ArrayList<Player>();
		testPlayerList.add(new Player("testPlayer1", "Blue"));
		testPlayerList.get(0).setupUserPocket();
		ResourceSetup resourceSetup = new ResourceSetup(testPlayerList);
		testPlayerList.get(0).addResource(Resources.Molasses, 1);
		int StockpileInInitial  = resourceSetup.getStockpile().getResourceCount(Resources.Molasses);
		// Call Method under test. Wanted resource: Gold ; To swap resource: Molasses
		resourceSetup.getStockpile().swapStockpile(Resources.Gold, Resources.Molasses, testPlayerList.get(0)); 
		// Test expected results. The stockpile should have 2 extra molasses
		int StockpileIn  = resourceSetup.getStockpile().getResourceCount(Resources.Molasses);
		// Expect 18 - 2 (for marketplace and player setup) + 2 (traded in) = 18 for molasses in stockpile
		assertEquals(16, StockpileInInitial, "Stockpile get in success- initial");
		assertEquals(18, StockpileIn, "Stockpile get in success - final");
	}
	
	// 1.3 If valid resources, check player has 2 less of traded in resource
	@Test
	void testSwapStockpilePOut() {
		// Set up of players and stockpile for testing
		ArrayList<Player> testPlayerList=new ArrayList<Player>();
		testPlayerList.add(new Player("testPlayer1", "Blue"));
		testPlayerList.get(0).setupUserPocket();
		ResourceSetup resourceSetup = new ResourceSetup(testPlayerList);
		testPlayerList.get(0).addResource(Resources.Molasses, 1);
		// Call Method under test. Wanted resource: Gold ; To swap resource: Molasses
		resourceSetup.getStockpile().swapStockpile(Resources.Gold, Resources.Molasses, testPlayerList.get(0)); 
		// Test expected results. The player should have 2 less Molasses (2 to start, so expect 0)
		int playerCountOut = testPlayerList.get(0).checkPocketResourcesType(Resources.Molasses);
		// Expect 18 - 2 (for playersetup) + 2 (traded in) = 18 for molasses in stockpile
		assertEquals(0, playerCountOut, "Player out stockpile trade success");
	}
	
	// 1.4 If valid resources, check player has 1 more of desired resource
	@Test
	void testSwapStockpilePIn() {
		// Set up of players and stockpile for testing
		ArrayList<Player> testPlayerList=new ArrayList<Player>();
		testPlayerList.add(new Player("testPlayer1", "Blue"));
		testPlayerList.get(0).setupUserPocket();
		ResourceSetup resourceSetup = new ResourceSetup(testPlayerList);
		testPlayerList.get(0).addResource(Resources.Molasses, 1);
		// Call Method under test. Wanted resource: Gold ; To swap resource: Molasses
		resourceSetup.getStockpile().swapStockpile(Resources.Gold, Resources.Molasses, testPlayerList.get(0)); 
		// Test expected results. The player should have 2 less Molasses (2 to start, so expect 0)
		int playerCountIn = testPlayerList.get(0).checkPocketResourcesType(Resources.Gold);
		// Expect 18 - 2 (for playersetup) + 2 (traded in) = 18 for molasses in stockpile
		assertEquals(1, playerCountIn, "Player in stockpile trade success");
	}
	
	// 1.5 
	// 1.6
	
	// ----------------------------------------------------------------------------
	// --------- Section 2 Testing : Testing checkStockpile() method --------------
	// ----------------------------------------------------------------------------
	
	// ----------------------------------------------------------------------------
	// --------- Section 3 Testing : Testing restockResource() method -------------
	// ----------------------------------------------------------------------------
	// 3.1 Check that the stockpile updates
	@Test
	void testRestockResourceStockpile() {
		// Set up of players and stockpile for testing
		ArrayList<Player> testPlayerList=new ArrayList<Player>();
		testPlayerList.add(new Player("testPlayer1", "Blue"));
		testPlayerList.get(0).setupUserPocket();
		ResourceSetup resourceSetup = new ResourceSetup(testPlayerList);
		resourceSetup.getStockpile().removeResource(Resources.Gold, 17); // remove all gold's
		int StockpileInitial = resourceSetup.getStockpile().getResourceCount(Resources.Gold); // Should be 0
		// Call Method under test. Want to restock gold
		resourceSetup.getStockpile().restockResource(Resources.Gold); 
		// Test expected results. The stockpile should have 1 less gold
		int StockpileFinal = resourceSetup.getStockpile().getResourceCount(Resources.Gold); // Should be 0
		// Expect 0 initially, and then a fully restocked 17 after test method
		assertEquals(0, StockpileInitial, "Stockpile restock success- initial");	// Count before restock
		assertEquals(17, StockpileFinal, "Stockpile restock success- final");		// Count after restock
	}
	
	// 3.2 Check that the player updates when there is a stockpile restock
	@Test
	void testRestockResourcePlayer() {
		// Set up of players and stockpile for testing
		ArrayList<Player> testPlayerList=new ArrayList<Player>();
		testPlayerList.add(new Player("testPlayer1", "Blue"));
		testPlayerList.get(0).setupUserPocket();
		testPlayerList.get(0).addResource(Resources.Gold, 2);
		ResourceSetup resourceSetup = new ResourceSetup(testPlayerList);
		resourceSetup.getStockpile().removeResource(Resources.Gold, 17); // remove all gold's
		int playerInitial = testPlayerList.get(0).checkPocketResources("G"); // Should be 2
		// Call Method under test. Want to restock gold
		resourceSetup.getStockpile().restockResource(Resources.Gold); 
		// Test expected results. The stockpile should have 1 less gold
		int playerFinal = testPlayerList.get(0).checkPocketResources("G"); // Should be 0
		// Expect 0 initially, and then a fully restocked 17 after test method
		assertEquals(2, playerInitial, "(player) Stockpile restock success- initial");	// Count before restock
		assertEquals(0, playerFinal, "(player) Stockpile restock success- final");		// Count after restock
	}
	
	
	// ----------------------------------------------------------------------------
	// --------- Section 4 Testing : Testing setupPlayers() method ----------------
	// ----------------------------------------------------------------------------
	// 4.1 Test setupPlayers() - the correct amount of resources are removed from the stockpile
	@Test
	void testSetupPlayers() {
		// Set up of players and stockpile for testing
		Stockpile stockpileTest;
		ArrayList<Player> testPlayerList=new ArrayList<Player>();
		stockpileTest = new Stockpile(testPlayerList);
		stockpileTest.setupPlayers(3); 
		// Set up test values 
		int expWood = stockpileTest.getResourceCount(Resources.Wood);
		int expGold = stockpileTest.getResourceCount(Resources.Gold);
		int expMolasses = stockpileTest.getResourceCount(Resources.Molasses);
		int expCutlass = stockpileTest.getResourceCount(Resources.Cutlasses);
		int expGoat = stockpileTest.getResourceCount(Resources.Goats);
		// Expect 18 for all resources not distributed to players initially.
		assertEquals(18, expGold, "Test player setup (Stockpile gold count)");
		assertEquals(15, expWood, "Test player setup (Stockpile wood count)");
		assertEquals(15, expMolasses, "Test player setup (Stockpile molasses count)");
		assertEquals(18, expCutlass, "Test player setup (Stockpile cutlass count)");
		assertEquals(18, expGoat, "Test player setup (Stockpile goat count)");
	}
	
	// ----------------------------------------------------------------------------
	// --------- Section 5 Testing : Testing return Resource() method -------------
	// ----------------------------------------------------------------------------
	// 5.1 Test return Resource() - the count in stockpile should increase accordingly
	@Test
	void testReturnResource() {
		// Set up of players and stockpile for testing
		ArrayList<Player> testPlayerList=new ArrayList<Player>();
		ResourceSetup resourceSetup = new ResourceSetup(testPlayerList);
		// Call method under test (adding 1 gold back to the resources (17+1)
		resourceSetup.getStockpile().returnResource(Resources.Gold, 1);
		// Test expected results. The stockpile should have 18 gold (up from 17)
		int stockpileActual = resourceSetup.getStockpile().getResourceCount(Resources.Gold); // Should be 0
		// Expect 0 initially, and then a fully restocked 17 after test method
		assertEquals(18, stockpileActual, "Return Resource success");	// Count before restock
	}
	
	// ----------------------------------------------------------------------------
	// --------- Section 6 Testing : Testing distributeResource() method ----------
	// ----------------------------------------------------------------------------
	
	

}


// Things to test:
// 1.5. if someone wants to swap with stockpile and there is not enough resources - check that the stockpile is restocked
// 1.6. if someone wants to swap with stockpile and there is not enough resources - check that the player and marketplace are eventually updated
// 2.1 Test that checkStockpile() catches an empty resource stocks, and restocks itselt
// 2.2 Test that checkStockpile() catches an empty resource stocks, and no player or marketplace has that resource after update
// 6.1 Test distributeResource() - if there are enough, check that player resources is correctly updated 
// 6.2 Test distributeResource() - if there are enough, check that stockpile is correctly updated
// 6.3 Test distributeResource() - if there are not enough, check that restock is correctly called 

// Thing I think may be an error: If the stockpile needs to restock, if doesn't look like the stockpile will be restocked and then the player will
// get their desired resource. Instead it looks like the stockpile is just restocked and that is the end of that. check what the desired result is.



//public class Stockpile {
//	// ------------------------------------------------------------------------------------------------
//	// ---------- Variables ---------------------------------------------------------------------------
//	// ------------------------------------------------------------------------------------------------
//	protected HashMap<Resources, Integer> stockpile = new HashMap<Resources, Integer>();
//	protected ArrayList<Player> players;
//	protected Marketplace marketplace;
//
//	// ------------------------------------------------------------------------------------------------
//	// ---------- Constructor -------------------------------------------------------------------------
//	// ------------------------------------------------------------------------------------------------
//	public Stockpile(ArrayList<Player> players) {
//		// Add 18 of each resource to stockpile
//		stockpile.put(Resources.Gold, 18);
//		stockpile.put(Resources.Wood, 18);
//		stockpile.put(Resources.Cutlasses, 18);
//		stockpile.put(Resources.Molasses, 18);
//		stockpile.put(Resources.Goats, 18);
//		this.players = players;
//	}
//
//	// ------------------------------------------------------------------------------------------------
//	// ---------- Method: swapStockpile ---------------------------------------------------------------
//	// 2 toSwapResource need to be removed from pocket, and 1 wantedResource need to be added to pocket.
//	// ------------------------------------------------------------------------------------------------
//	public void swapStockpile(Resources wantedResource, Resources toSwapResource, Player player) {
//		int numberofwantedresource = stockpile.get(wantedResource);				// The wantedResource comes from the stockpile
//		if (numberofwantedresource > 0) {
//			stockpile.put(wantedResource, numberofwantedresource - 1); 			// Remove 1 wantedResource from stockpile
//			player.addResource(wantedResource, 1); 								// Add 1 wantedResource to users pocket
//			player.removeResource(toSwapResource, 2); 							// Remove 2 toSwapResource's from users pocket
//			stockpile.put(toSwapResource, stockpile.get(toSwapResource) + 2); 	// Add 2 toSwapResource's to the stockpile
//		} else {
//			checkStockpile(); 	// Check if there's 1+ of each resource, if not, all of that resource must be returned
//		}
//	}
//	
//	// ------------------------------------------------------------------------------------------------
//	// ---------- Method: checkStockpile --------------------------------------------------------------
//	// This method checks if the resources need to be returned to the stockpile
//	// ------------------------------------------------------------------------------------------------
//	public void checkStockpile() {
//		if (stockpile.get(Resources.Gold) == 0) {				// If the stockpile is out of gold ...
//			restockResource(Resources.Gold); 						// ... take back all gold from all players
//		} else if (stockpile.get(Resources.Molasses) == 0) {	// If the stockpile is out of molasses ...
//			restockResource(Resources.Molasses); 					// ... take back all molasses from every player
//		} else if (stockpile.get(Resources.Wood) == 0) {		// If the stockpile is out of wood ...
//			restockResource(Resources.Wood); 						// ... take back all wood from all players
//		} else if (stockpile.get(Resources.Cutlasses) == 0) {	// If the stockpile is out of cutlasses ...
//			restockResource(Resources.Cutlasses); 					// ... take back all cutlasses from all players
//		} else if (stockpile.get(Resources.Goats) == 0) {		// If the stockpile is out of goats...
//			restockResource(Resources.Goats); 						// ... take back all goats from all players
//		}
//	}
//
//	// ------------------------------------------------------------------------------------------------
//	// ---------- Method: restockResource -------------------------------------------------------------
//	// If the stockpile is out of a resource, all of that resource should be returned to the stockpile.
//	// ------------------------------------------------------------------------------------------------
//	public void restockResource(Resources resource) {
//		// ----- Remove all of the defined resource from users pocket ----------------------------------
//		for (int i = 0; i <= this.players.size() - 1; i++) {
//			int resource_pocket = players.get(i).checkPocketResourcesType(resource);
//			players.get(i).removeResource(resource, resource_pocket);
//		}
//		// ----- Restock stockpile with all of a resource except those that are in the marketplace -----
//		int resource_marketplace = marketplace.resourceCount(resource);
//		stockpile.put(resource, 18 - resource_marketplace); // restock number of requested resource
//	}
//
//	// ------------------------------------------------------------------------------------------------
//	// ---------- Method: setupPlayers ----------------------------------------------------------------
//	// Each player gets 1 wood and 1 molasses added to their pocket from the stockpile
//	// ------------------------------------------------------------------------------------------------
//	public void setupPlayers(int numberPlayers) {
//		// ----- Get the number of each resource from the stockpile -----
//		int numberofwoodresource = stockpile.get(Resources.Wood);
//		int numberofmolassesresource = stockpile.get(Resources.Molasses);
//		// ----- Take the resources out of the stockpile as needed ------
//		stockpile.put(Resources.Wood, numberofwoodresource - numberPlayers);
//		stockpile.put(Resources.Molasses, numberofmolassesresource - numberPlayers);
//		//----- Add the resources to the players pockets ----------------
//		for (int i = 0; i <= players.size() - 1; i++) {
//			players.get(i).addResource(Resources.Wood, 1);
//			players.get(i).addResource(Resources.Molasses, 1);
//		}
//	}
//
//	// ------------------------------------------------------------------------------------------------
//	// ---------- Method: setupMarketplace ------------------------------------------------------------
//	// Used for marketplace setup. Takes one of each resource from the stockpile.
//	// ------------------------------------------------------------------------------------------------
//	public void setupMarketplace(Marketplace marketplace) {
//		this.marketplace = marketplace;
//		stockpile.put(Resources.Wood, stockpile.get(Resources.Wood) - 1);
//		stockpile.put(Resources.Gold, stockpile.get(Resources.Gold) - 1);
//		stockpile.put(Resources.Molasses, stockpile.get(Resources.Molasses) - 1);
//		stockpile.put(Resources.Goats, stockpile.get(Resources.Goats) - 1);
//		stockpile.put(Resources.Cutlasses, stockpile.get(Resources.Cutlasses) - 1);
//	}
//
//	// ------------------------------------------------------------------------------------------------
//	// ---------- Method: return Resource -------------------------------------------------------------
//	// Returns resources to the stockpile. Used for buying things, restocking the marketplace etc.
//	// ------------------------------------------------------------------------------------------------
//	public void returnResource(Resources resource, int numberOfResource) {
//		stockpile.put(resource, stockpile.get(resource) + numberOfResource);
//	}
//
//	// ------------------------------------------------------------------------------------------------
//	// ---------- Method: distributeResource ----------------------------------------------------------
//	// This distributes resources from the stockpile to users
//	// ------------------------------------------------------------------------------------------------
//	public void distributeResource(Resources resource, int numberOfResource) {
//		int numberofResourceAvailable = stockpile.get(resource);
//		// ----- If there are enough resources, take resources from stockpile to deliver to users -----
//		if (numberofResourceAvailable >= numberOfResource) { 
//			stockpile.put(resource, stockpile.get(resource) - numberOfResource);
//		} 
//		// ----- If there aren't enough resources, restock the stockpile with that resources ----------
//		else {
//			restockResource(resource);
//		}
//	}
