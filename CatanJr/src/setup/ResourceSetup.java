package setup;

import java.util.ArrayList;

import board.Marketplace;
import board.Stockpile;
import player.Player;
import resources.CocoTiles;
import resources.Resources;

public class ResourceSetup {

	// Should this be its own setup?
	// Give each player one wood resource tile and one molassess resource tile, need to assign these to users pockets
	
	public ResourceSetup(ArrayList<Player> players) {
		Stockpile stockPile = new Stockpile(); // construct Stockpile
		CocoTiles cocoTiles = new CocoTiles(); // construct Cocotiles
		Marketplace marketPlace = new Marketplace(); //construct marketplace
		stockPile.SetupMarketplace(); // remove one of each resource from the stockpile as its being added to the marketplace
		marketPlace.SetupMarketplace(); // this adds one of each resource to the marketplace
		stockPile.SetupPlayers(players.size()); // this just removes 1 wood and 1 molasses per player, still need to assign these resources to each user
		System.out.println("\nResources have been setup "); // will take out, just tracking whats happening
		
		// need to add 1 wood and 1 molasses to each players pocket
		for(int i=0; i<=players.size()-1;i++) {
			players.get(i).addResource(Resources.Wood, 1);
			players.get(i).addResource(Resources.Molasses, 1);
			System.out.println(players.get(i).viewPocket()); // will take out, just tracking whats happening


		}
		

	}
}
