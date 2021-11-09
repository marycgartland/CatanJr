package resources;

// Import 
import java.util.*;
import player.Player;

public class CocoTiles {
	// Idea: we could enter all the cocotiles into an array, generate a random number between 1 and the number of tiles left, and select that element in the array to hand out as the next one
	// Variables
	Player highestPlayer;
	
	// Method: Constructor 
	
	// Method: Determine type of cocotiles
		// Types available: 
		//		Move the ghost captain (11)
		// 		Ship/Lair (3)
		//		Goats and Cutlasses - 2 of each (3)
		//		Molasses and Wood - 2 of each (3)
	
	// Keep track of the number of tiles left
	
	// Keep track of which player has the most tiles (there should be a 'used' stockpile of cocotiles for each player)
		// Option 1: Keep track of who has each cocotile here with just adding 1 each time they get a new one
		// Option 2: use a get method in class 'Player' to get and compare that way
	public void playerTileCount(){
		
	}
	
	// If a player has the same number of tiles another, they should remove their pirates lair from spooky island
	// If a player has the most cocotiles, they get to place an unused pirates lair on spooky island
	// First player to buy a cocotile has the most at that moment
	
}
