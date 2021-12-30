package testing;

// -----------------------------------------------------------------------------------------------------------
// This JUnit test tests the Player class of the player method. It runs tests on the following methods of the 
// class: addResource(), removeResource(), addCocoTile(), and changeLairs().
// -----------------------------------------------------------------------------------------------------------

import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;

import player.Player;
import resources.Resources;

public class TestPlayer {
	private Player testPlayer;
	
	@Before
	public void setUp() throws Exception{
		testPlayer = new Player("testPlayer", "Blue");
		testPlayer.setupUserPocket();
	}
	
	@After
	public void tearDown() {
		testPlayer = null;
	}
	
	// --------------- Testing addResource() method --------------------------------
	@Test
	public void testAddResource() {
		// Setup
		int initialResource = testPlayer.checkPocketResources("W");
		testPlayer.addResource(Resources.Wood, 1);
		int finalResource = testPlayer.checkPocketResources("W");
		// Test
		assertEquals(1, finalResource - initialResource, "Testing adding resource");
	}
	
	// --------------- Testing removeResource() method -----------------------------
	@Test
	public void testRemoveResource() {
		// Setup
		testPlayer.addResource(Resources.Wood, 2);
		int initialResource = testPlayer.checkPocketResources("W");
		// Remove resource
		testPlayer.removeResource(Resources.Wood, 1);
		int finalResource = testPlayer.checkPocketResources("W");
		// Test
		assertEquals(1, initialResource - finalResource, "Testing removing resource");
	}
	
	// --------------- Testing adding a cocotile to a players pocket ---------------
	@Test
	public void testAddCocoTile() {
		// Add a cocotile
		testPlayer.addCocoTile();
		// Test
		assertEquals(1, testPlayer.getCocoTileCount(), "Testing adding cocotile");
	}
	
	// --------------- Testing changing the # of lairs a user has ------------------
	@Test
	public void testChangeLairs() {
		// Setup
		testPlayer.addResource(Resources.Wood, 2);
		// Add a lair
		testPlayer.addLair();
		int initialLair = testPlayer.getLairCount();
		// Remove lair
		testPlayer.removeLair();
		int finalLair = testPlayer.getLairCount();
		// Test
		assertEquals(1, initialLair - finalLair, "Testing changing lair count - remove");
	}

}