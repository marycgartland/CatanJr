package testing;

//-----------------------------------------------------------------------------------------------------------
// This JUnit test tests the Marketplace class of the board package. It tests the following methods: 
// checkForResourceMarketplace(), testResourceCount(), and swapMarketplace().
//-----------------------------------------------------------------------------------------------------------

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.*;
import player.Player;
import resources.Resources;
import setup.ResourceSetup;

public class TestMarketPlace {

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
	
	// --------------------------------------------------------------------------------------------
	// ---------- Method under test: checkForResourceMarketplace() --------------------------------
	// Tests include expecting 'true' if the resource is in the marketplace, and 'false' otherwise
	// --------------------------------------------------------------------------------------------
	// Test that if a resource is in the marketplace, it is found
	@Test
	public void testCheckForResourceMarketplace() {
		// Call method under test
		boolean actual = resourceSetup.getMarketplace().checkForResourceMarketplace(Resources.Gold); 
		// Test expected results.
		assertEquals(true, actual, "Marketplace resource search - true");
	} 
	
	// Test that if a resource isn't the marketplace, it is returned as false
	@Test
	public void testFalseCheckForResourceMarketplace() {
		// Remove the gold resource from the marketplace
		resourceSetup.getMarketplace().swapMarketplace(Resources.Gold, Resources.Molasses, testPlayerList.get(0));
		// Call method under test
		boolean actual = resourceSetup.getMarketplace().checkForResourceMarketplace(Resources.Gold); 
		// Test expected results.
		assertEquals(false, actual, "Marketplace resource search - false");
		}
	
	// --------------------------------------------------------------------------------------------
	// ---------- Method under test: testResourceCount() ------------------------------------------
	// Ensure each resource in the marketplace is counted correctly based on setup numbers --------
	// --------------------------------------------------------------------------------------------
	@Test
	public void testResourceCount() {
		// Set up expected and actual (There should be one of each resource in marketplace to begin)
		ArrayList<Integer> actualCount = new ArrayList<Integer>();
		actualCount.add(resourceSetup.getMarketplace().resourceCount(Resources.Wood));
		actualCount.add(resourceSetup.getMarketplace().resourceCount(Resources.Molasses));
		actualCount.add(resourceSetup.getMarketplace().resourceCount(Resources.Gold));
		actualCount.add(resourceSetup.getMarketplace().resourceCount(Resources.Goats));
		actualCount.add(resourceSetup.getMarketplace().resourceCount(Resources.Cutlasses));
		ArrayList<Integer> expectedCount = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1));
		assertEquals(expectedCount, actualCount, "Marketplace count");
	}
	
	
	// --------------------------------------------------------------------------------------------
	// ---------- Method under test: swapMarketplace() --------------------------------------------
	// Ensure that if resources are available the resources are correctly swapped into the players
	// pocket and out of the marketplace. If resources are not available in the marketplace, no
	// trade occurs.
	// --------------------------------------------------------------------------------------------
	
	// Test that a resource is correctly swapped in/out of the pocket with marketplace
	@Test
	public void testSwapMarketPlaceP() {
		// Call method under test. Wanted resource: Gold ; To swap resource: Molasses
		resourceSetup.getMarketplace().swapMarketplace(Resources.Gold, Resources.Molasses, testPlayerList.get(0)); 
		// Test expected results. The marketplace should have 2 molasses now, and 0 gold. 
		int playerCountIn = testPlayerList.get(0).checkPocketResourcesType(Resources.Gold);
		int playerCountOut = testPlayerList.get(0).checkPocketResourcesType(Resources.Molasses);
		assertEquals(0, playerCountOut, "Player trade out w/ marketplace");	// Expecting 0 Molasses in pocket after trade
		assertEquals(1, playerCountIn, "Player trade in w/ marketplace");	// Expecting 1 gold in pocket after trade
	} 

	
	// Test that a resource is correctly swapped in/out of the marketplace
	@Test
	public void testSwapMarketPlaceMP() {
		// Call method under test. Wanted resource: Gold ; To swap resource: Molasses
		resourceSetup.getMarketplace().swapMarketplace(Resources.Gold, Resources.Molasses, testPlayerList.get(0)); 
		// Test expected results. The marketplace should have 2 molasses now, and 0 gold. 
		int marketplaceCountOut = resourceSetup.getMarketplace().resourceCount(Resources.Gold);
		int marketplaceCountIn = resourceSetup.getMarketplace().resourceCount(Resources.Molasses);
		assertEquals(2, marketplaceCountIn, "Marketplace trade in success");	// Expecting 2 molasses in marketplace after trade
		assertEquals(0, marketplaceCountOut, "Marketplace trade out success");	// Expecting 0 gold in marketplace after trade
	} 
	
	// Test that if a resource isn't in the marketplace, no trade occurs 
	@Test
	public void testFalseSwapMarketPlaceMP() {
		// Call method under test. Wanted resource: Gold ; To swap resource: Molasses.
		// First swap should go through, 2nd should not because of lack of marketplace resources
		resourceSetup.getMarketplace().swapMarketplace(Resources.Gold, Resources.Molasses, testPlayerList.get(0)); 
		resourceSetup.getMarketplace().swapMarketplace(Resources.Gold, Resources.Molasses, testPlayerList.get(0)); 
		// Test expected results. The marketplace should have 2 molasses now, and 0 gold. (same as if only 1 swap went through)
		int marketplaceCountOut = resourceSetup.getMarketplace().resourceCount(Resources.Gold);
		int marketplaceCountIn = resourceSetup.getMarketplace().resourceCount(Resources.Molasses);
		assertEquals(2, marketplaceCountIn, "Marketplace trade in success");	// Expecting 2 Molasses in marketplace after trade
		assertEquals(0, marketplaceCountOut, "Marketplace trade out success");	// Expecting 0 gold in marketplace after trade
	} 
}
