package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import player.Player;
import resources.Resources;
import setup.ResourceSetup;

class TestMarketplace {
	
	// Test that if a resource is in the marketplace, it is found (MUT: checkForResourceMarketplace)
	void testCheckForResourceMarketplace() {
		// Set up players and marketplace for testing
		ArrayList<Player> testPlayerList=new ArrayList<Player>();
		ResourceSetup resourceSetup = new ResourceSetup(testPlayerList);
		// Call method under test
		boolean actual = resourceSetup.getMarketplace().checkForResourceMarketplace(Resources.Gold); 
		// Test expected results. The marketplace should have 2 molasses now, and 0 gold. 
		assertEquals(true, actual, "Marketplace resource search - true");
	}
	
	// Test that if a resource isn't in the marketplace, it is not found (MUT: checkForResourceMarketplace)
	void testFalseCheckForResourceMarketplace() {
		// Set up players and marketplace for testing
		ArrayList<Player> testPlayerList=new ArrayList<Player>();
		ResourceSetup resourceSetup = new ResourceSetup(testPlayerList);
		// Call method under test
		resourceSetup.getMarketplace().swapMarketplace(Resources.Gold, Resources.Wood, testPlayerList.get(0));
		boolean actual = resourceSetup.getMarketplace().checkForResourceMarketplace(Resources.Gold); 
		// Test expected results. The marketplace should have 2 molasses now, and 0 gold. 
		assertEquals(false, actual, "Marketplace resource search - false");
	}
	
	// Test that the counter correctly counts the number of resources in the marketplace (MUT: resourceCount)
	void testResourceCount() {
		// Set up marketplace for testing
		ArrayList<Player> testPlayerList=new ArrayList<Player>();
		ResourceSetup resourceSetup = new ResourceSetup(testPlayerList);
		// Set up expected and actual (There should be one of each resource in marketplace to begin)
		int[] expected = {1, 1, 1, 1, 1};
		int[] actual = {resourceSetup.getMarketplace().resourceCount(Resources.Gold), 
				resourceSetup.getMarketplace().resourceCount(Resources.Molasses),
				resourceSetup.getMarketplace().resourceCount(Resources.Wood),
				resourceSetup.getMarketplace().resourceCount(Resources.Goats),
				resourceSetup.getMarketplace().resourceCount(Resources.Molasses)};
		// Call the test and compare
		assertEquals(expected, actual, "Marketplace count");
	}
	
	// Test that a resource is correctly swapped in/out of the marketplace (MUT: swapMarketplace)
	@Test
	void testSwapMarketPlaceMP() {
		// Set up players and marketplace for testing
		ArrayList<Player> testPlayerList=new ArrayList<Player>();
		testPlayerList.add(new Player("testPlayer1", "Blue"));
		testPlayerList.get(0).setupUserPocket();
		ResourceSetup resourceSetup = new ResourceSetup(testPlayerList);
		// Call method under test. Wanted resource: Gold ; To swap resource: Molasses
		resourceSetup.getMarketplace().swapMarketplace(Resources.Gold, Resources.Molasses, testPlayerList.get(0)); 
		// Test expected results. The marketplace should have 2 molasses now, and 0 gold. 
		int marketplaceCountOut = resourceSetup.getMarketplace().resourceCount(Resources.Gold);
		int marketplaceCountIn = resourceSetup.getMarketplace().resourceCount(Resources.Molasses);
		assertEquals(2, marketplaceCountIn, "Marketplace trade in success");	// Expecting 2 Molasses in marketplace after trade
		assertEquals(0, marketplaceCountOut, "Marketplace trade out success");	// Expecting 0 gold in marketplace after trade
	}
	
	// Test that a resource is correctly swapped in/out of the pocket with marketplace (MUT: swapMarketplace)
	@Test
	void testSwapMarketPlaceP() {
		// Set up players and marketplace for testing
		ArrayList<Player> testPlayerList=new ArrayList<Player>();
		testPlayerList.add(new Player("testPlayer1", "Blue"));
		testPlayerList.get(0).setupUserPocket();
		ResourceSetup resourceSetup = new ResourceSetup(testPlayerList);
		// Call method under test. Wanted resource: Gold ; To swap resource: Molasses
		resourceSetup.getMarketplace().swapMarketplace(Resources.Gold, Resources.Molasses, testPlayerList.get(0)); 
		// Test expected results. The marketplace should have 2 molasses now, and 0 gold. 
		int playerCountIn = testPlayerList.get(0).checkPocketResourcesType(Resources.Gold);
		int playerCountOut = testPlayerList.get(0).checkPocketResourcesType(Resources.Molasses);
		assertEquals(0, playerCountOut, "Player trade out w/ marketplace");	// Expecting 0 Molasses in pocket after trade
		assertEquals(1, playerCountIn, "Player trade in w/ marketplace");	// Expecting 2 gold in pocket after trade
	}
}