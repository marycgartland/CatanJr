package setup;

//----------------------------------------------------------------------------------------
// This class sets up the resources, including the stockpile, cocotiles, and marketplace.
//----------------------------------------------------------------------------------------

import java.util.ArrayList;
import board.Marketplace;
import board.Stockpile;
import player.Player;
import resources.CocoTiles;

public class ResourceSetup {
	// ---------------------------------------
	// ---------- Variables ------------------
	// ---------------------------------------
	protected Stockpile stockPile;	
	protected CocoTiles cocoTiles;
	protected Marketplace marketPlace;
	
	// ---------------------------------------
	// ---------- Constructor ----------------
	// ---------------------------------------
	public ResourceSetup(ArrayList<Player> players) {
		this.stockPile = new Stockpile(players);	// Construct the stockpile
		this.cocoTiles = new CocoTiles(); 			// Construct the Cocotiles
		this.marketPlace = Marketplace.getInstance(); // Get instance of the marketplace
		stockPile.setupMarketplace(marketPlace); 	// Take one of each resource from the stockpile and add it to the marketplace
		marketPlace.setupMarketplace(stockPile); 	// Add one of each resource to the marketplace
		stockPile.setupPlayers(players.size()); 	// Add 1 wood and 1 molasses from the stockpile to each players pocket
	}
	
	// ---------------------------------------
	// ---------- Get methods ----------------
	// ---------------------------------------
	public Stockpile getStockpile() {
		return this.stockPile;
	}
	
	public Marketplace getMarketplace() {
		return this.marketPlace;
	}

	public CocoTiles getCocoTiles() {
		return this.cocoTiles;
	}
}
