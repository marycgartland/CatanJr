package resources;

// Import 
import java.util.*;

import main.Interactor;

public class CocoTiles {
	// Variables
	// protected Player highestPlayer;// EP: not needed if Player is tracking this
	
	protected ArrayList<CocoTileTypes> CocoTiles = new ArrayList<CocoTileTypes>();
	Interactor interactor = new Interactor();
	
	// Method: Constructor 
	public CocoTiles() { // 20 cocotiles in total
		// add cocotiles to arraylist then randomise
		for (int i = 0; i <= 2; i++) { // 3 of resource CocoTiles
			this.CocoTiles.add(CocoTileTypes.WoodMolasses);
			this.CocoTiles.add(CocoTileTypes.GoatCutlasses);
			this.CocoTiles.add(CocoTileTypes.ShipCastle);
		}
		for (int i = 0; i <= 10; i++) { // 10 Ghost captain CocoTiles
			this.CocoTiles.add(CocoTileTypes.GhostCaptain);
		}
		Collections.shuffle(CocoTiles); // this shuffles the cocotiles // need to check that these are actually shuffled
	}
	
	// if a user buys a cocotile, give them the first element in the shuffled arraylist and then remove it 
	// user needs to keep track the number of cocotiles they have and thats how we will determine who has the most in the game
	// YourTurn needs to determine the action based on the cocotiletype outputted by this method, is user is given WoodMolasses/GoatCutlasses need to give out 2 of each of that resource etc
	// before a user buys a cocotile, their pocket should be checked to make sure they have sufficient funds
	public CocoTileTypes buyCocoTile() {
		if(CocoTiles.size()>0) {
		CocoTileTypes cocoTileBought = CocoTiles.get(0);
		CocoTiles.remove(0);
		return cocoTileBought;
		}
		else {
			interactor.printMessage("no cocotiles");
			return null;
		}
	}
		
	// EP: we are going with option 2, so Player class will track this, we need to just compare them after every turn
	// Keep track of which player has the most tiles (there should be a 'used' stockpile of cocotiles for each player)
		// Option 1: Keep track of who has each cocotile here with just adding 1 each time they get a new one
		// Option 2: use a get method in class 'Player' to get and compare that way

	
	// EP: I think this is relevant for the board/ YourTurn class
	// If a player has the same number of tiles another, they should remove their pirates lair from spooky island
	// If a player has the most cocotiles, they get to place an unused pirates lair on spooky island
	// First player to buy a cocotile has the most at that moment
	
}
