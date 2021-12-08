package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import player.Player;
import resources.Resources;

class TestPlayer {

	// --------------- Testing addResource() method --------------------------------
	@Test
	void testAddResource() {
		// Create a test player
		Player testPlayer = new Player("testPlayer", "Blue");
		testPlayer.setupUserPocket();
		// Add a resource to the players pocket
		testPlayer.addResource(Resources.Wood, 1);
		// Test
		assertEquals(1, testPlayer.checkPocketResources("W"), "Testing adding resource");
	}

	// --------------- Testing removeResource() method -----------------------------
	@Test
	void TestRemoveResource() {
		// Create a test player
		Player testPlayer = new Player("testPlayer", "Blue");
		testPlayer.setupUserPocket();
		testPlayer.addResource(Resources.Wood, 2);
		// Remove resource
		testPlayer.removeResource(Resources.Wood, 1);
		// Test
		assertEquals(1, testPlayer.checkPocketResources("W"), "Testing removing resource");
	}
	
	// --------------- Testing adding a cocotile to a players pocket ---------------
		@Test
		void TestAddCocoTile() {
			// Create a test player
			Player testPlayer = new Player("testPlayer", "Blue");
			testPlayer.setupUserPocket();
			// Add a cocotile
			testPlayer.addCocoTile();
			// Test
			assertEquals(1, testPlayer.getCocoTileCount(), "Testing adding cocotile");
		}
		
	// --------------- Testing changing the # of lairs a user has ------------------
	@Test
	void TestChangeLairs() {
		// Create a test player
		Player testPlayer = new Player("testPlayer", "Blue");
		testPlayer.setupUserPocket();
		testPlayer.addResource(Resources.Wood, 2);
		// Add a lair
		testPlayer.addLair();
		// Test (set up with 2 lairs, so adding a lair should mean 3 lairs)
		assertEquals(3, testPlayer.getLairCount(), "Testing changing lair count - remove");
		// Remove lair
		testPlayer.removeLair();
		// Test
		assertEquals(2, testPlayer.getLairCount(), "Testing changing lair count - remove");
	}
}

// TODO: test removing more resources than they have, or is this prechecked?