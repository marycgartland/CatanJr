package testing;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.*;

import board.Stockpile;
import player.Player;
import resources.Resources;
import setup.ResourceSetup;

public class TestStockpile {

	private ArrayList<Player> testPlayerList;
	private ResourceSetup resourceSetup;
	
	@Before
	public void setUp() throws Exception{
		testPlayerList=new ArrayList<Player>();
		testPlayerList.add(new Player("testPlayer1", "Blue"));
		testPlayerList.get(0).setupUserPocket();
		resourceSetup = new ResourceSetup(testPlayerList);
		System.out.println("Setup");
	}
	
	@After
	public void tearDown() {
		testPlayerList.clear();
		System.out.println("Teardown");
	}
	
	// ----------------------------------------------------------------------------
	// --------- Section 1 Testing : Testing swapStockpile() method ---------------
	// ----------------------------------------------------------------------------
	// 1.1 If valid resources, check marketplace has 1 less of traded out resource
	@Test
	public void testSwapStockpileCountsSOut() {
		System.out.println("Testing2");
		// Set up of players and stockpile for testing
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
	public void testSwapStockpileSIn() {
		// Set up of players and stockpile for testing
		testPlayerList.get(0).addResource(Resources.Molasses, 1);
		int StockpileInInitial  = resourceSetup.getStockpile().getResourceCount(Resources.Molasses);
		// Call Method under test. Wanted resource: Gold ; To swap resource: Molasses
		resourceSetup.getStockpile().swapStockpile(Resources.Gold, Resources.Molasses, testPlayerList.get(0)); 
		// Test expected results. The stockpile should have 2 extra molasses
		int StockpileIn  = resourceSetup.getStockpile().getResourceCount(Resources.Molasses);
		assertEquals(2, StockpileIn - StockpileInInitial, "Stockpile get in success- initial");
	}
	
	// 1.3 If valid resources, check player has 2 less of traded in resource
	@Test
	public void testSwapStockpilePout() {
		// Set up of players and stockpile for testing
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
	public void testSwapStockpilePin() {
		// Set up of players and stockpile for testing
		testPlayerList.get(0).addResource(Resources.Molasses, 1);
		// Call Method under test. Wanted resource: Gold ; To swap resource: Molasses
		resourceSetup.getStockpile().swapStockpile(Resources.Gold, Resources.Molasses, testPlayerList.get(0)); 
		// Test expected results. The player should have 2 less Molasses (2 to start, so expect 0)
		int playerCountIn = testPlayerList.get(0).checkPocketResourcesType(Resources.Gold);
		// Expect 18 - 2 (for playersetup) + 2 (traded in) = 18 for molasses in stockpile
		assertEquals(1, playerCountIn, "Player in stockpile trade success");
	}
	
	// 1.5. If there is not enough resources - check that the stockpile is restocked
	@Test
	public void testSwapStockpileInvalid() {
		// Set up of players and stockpile for testing
		testPlayerList.get(0).addResource(Resources.Molasses, 1);
		resourceSetup.getStockpile().removeResource(Resources.Gold, 17); // remove all gold's
		int StockpileOutInitial = resourceSetup.getStockpile().getResourceCount(Resources.Gold);
		// Call Method under test. Wanted resource: Gold ; To swap resource: Molasses
		resourceSetup.getStockpile().swapStockpile(Resources.Gold, Resources.Molasses, testPlayerList.get(0)); 
		// Test expected results. The stockpile should be restocked with gold 
		int StockpileOut = resourceSetup.getStockpile().getResourceCount(Resources.Gold);
		assertEquals(0, StockpileOutInitial, "Stockpile trade out success- initial");	// Count before trade/restock
		assertEquals(17, StockpileOut, "Stockpile trade out success- final");			// Count after trade/restock
	}
	
	// 1.6 
	
	// ----------------------------------------------------------------------------
	// --------- Section 2 Testing : Testing checkStockpile() method --------------
	// ----------------------------------------------------------------------------
		
	// ----------------------------------------------------------------------------
	// --------- Section 3 Testing : Testing restockResource() method -------------
	// ----------------------------------------------------------------------------
	// 3.1 Check that the stockpile updates
	@Test
	public void testRestockResourceStockpile() {
		// Set up of players and stockpile for testing
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
	public void testRestockResourcePlayer() {
		testPlayerList.get(0).addResource(Resources.Gold, 2);
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
	public void testSetupPlayers() {
		// Set up of players and stockpile for testing
		Stockpile stockpileTest;
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
	public void testReturnResource() {
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
	
	
	// Things to test:
	// 1.5. if someone wants to swap with stockpile and there is not enough resources - check that the stockpile is restocked
	// 1.6. if someone wants to swap with stockpile and there is not enough resources - check that the player and marketplace are eventually updated
	// 2.1 Test that checkStockpile() catches an empty resource stocks, and restocks itselt
	// 2.2 Test that checkStockpile() catches an empty resource stocks, and no player or marketplace has that resource after update
	// 6.1 Test distributeResource() - if there are enough, check that player resources is correctly updated 
	// 6.2 Test distributeResource() - if there are enough, check that stockpile is correctly updated
	// 6.3 Test distributeResource() - if there are not enough, check that restock is correctly called 
}
