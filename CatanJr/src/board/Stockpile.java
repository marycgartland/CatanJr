package board;

//----------------------------------------------------------------------------------------
// This class represents the stockpile. It sets up the stockpile with the resources for 
// the game, and has methods to deal with resources coming in and out of the stockpile,
// as well as providing setup of the players and marketplace with its resources.
//----------------------------------------------------------------------------------------

import java.util.ArrayList;
import java.util.HashMap;

import player.Player;
import resources.Resources;

public class Stockpile {
	// ------------------------------------------------------------------------------------------------
	// ---------- Variables ---------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------
	protected HashMap<Resources, Integer> stockpile = new HashMap<Resources, Integer>();
	protected ArrayList<Player> players;
	protected Marketplace marketplace;

	// ------------------------------------------------------------------------------------------------
	// ---------- Constructor -------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------
	public Stockpile(ArrayList<Player> players) {
		// Add 18 of each resource to stockpile
		stockpile.put(Resources.Gold, 18);
		stockpile.put(Resources.Wood, 18);
		stockpile.put(Resources.Cutlasses, 18);
		stockpile.put(Resources.Molasses, 18);
		stockpile.put(Resources.Goats, 18);
		this.players = players;
	}

	// ------------------------------------------------------------------------------------------------
	// ---------- Method: swapStockpile ---------------------------------------------------------------
	// 2 toSwapResource need to be removed from pocket, and 1 wantedResource need to be added to pocket.
	// ------------------------------------------------------------------------------------------------
	public void swapStockpile(Resources wantedResource, Resources toSwapResource, Player player) {
		int numberofwantedresource = stockpile.get(wantedResource);				// The wantedResource comes from the stockpile
		if (numberofwantedresource > 0) {
			stockpile.put(wantedResource, numberofwantedresource - 1); 			// Remove 1 wantedResource from stockpile
			player.addResource(wantedResource, 1); 								// Add 1 wantedResource to users pocket
			player.removeResource(toSwapResource, 2); 							// Remove 2 toSwapResource's from users pocket
			stockpile.put(toSwapResource, stockpile.get(toSwapResource) + 2); 	// Add 2 toSwapResource's to the stockpile
		} else {
			checkStockpile(); 	// Check if there's 1+ of each resource, if not, all of that resource must be returned
		}
	}
	
	// ------------------------------------------------------------------------------------------------
	// ---------- Method: checkStockpile --------------------------------------------------------------
	// This method checks if the resources need to be returned to the stockpile
	// ------------------------------------------------------------------------------------------------
	public void checkStockpile() {
		if (stockpile.get(Resources.Gold) == 0) {				// If the stockpile is out of gold ...
			restockResource(Resources.Gold); 						// ... take back all gold from all players
		} else if (stockpile.get(Resources.Molasses) == 0) {	// If the stockpile is out of molasses ...
			restockResource(Resources.Molasses); 					// ... take back all molasses from every player
		} else if (stockpile.get(Resources.Wood) == 0) {		// If the stockpile is out of wood ...
			restockResource(Resources.Wood); 						// ... take back all wood from all players
		} else if (stockpile.get(Resources.Cutlasses) == 0) {	// If the stockpile is out of cutlasses ...
			restockResource(Resources.Cutlasses); 					// ... take back all cutlasses from all players
		} else if (stockpile.get(Resources.Goats) == 0) {		// If the stockpile is out of goats...
			restockResource(Resources.Goats); 						// ... take back all goats from all players
		}
	}

	// ------------------------------------------------------------------------------------------------
	// ---------- Method: restockResource -------------------------------------------------------------
	// If the stockpile is out of a resource, all of that resource should be returned to the stockpile.
	// ------------------------------------------------------------------------------------------------
	public void restockResource(Resources resource) {
		// ----- Remove all of the defined resource from users pocket ----------------------------------
		for (int i = 0; i <= this.players.size() - 1; i++) {
			int resource_pocket = players.get(i).checkPocketResourcesType(resource);
			players.get(i).removeResource(resource, resource_pocket);
		}
		// ----- Restock stockpile with all of a resource except those that are in the marketplace -----
		int resource_marketplace = marketplace.resourceCount(resource);
		stockpile.put(resource, 18 - resource_marketplace); // restock number of requested resource
	}

	// ------------------------------------------------------------------------------------------------
	// ---------- Method: setupPlayers ----------------------------------------------------------------
	// Each player gets 1 wood and 1 molasses added to their pocket from the stockpile
	// ------------------------------------------------------------------------------------------------
	public void setupPlayers(int numberPlayers) {
		// ----- Get the number of each resource from the stockpile -----
		int numberofwoodresource = stockpile.get(Resources.Wood);
		int numberofmolassesresource = stockpile.get(Resources.Molasses);
		// ----- Take the resources out of the stockpile as needed ------
		stockpile.put(Resources.Wood, numberofwoodresource - numberPlayers);
		stockpile.put(Resources.Molasses, numberofmolassesresource - numberPlayers);
		//----- Add the resources to the players pockets ----------------
		for (int i = 0; i <= players.size() - 1; i++) {
			players.get(i).addResource(Resources.Wood, 1);
			players.get(i).addResource(Resources.Molasses, 1);
		}
	}

	// ------------------------------------------------------------------------------------------------
	// ---------- Method: setupMarketplace ------------------------------------------------------------
	// Used for marketplace setup. Takes one of each resource from the stockpile.
	// ------------------------------------------------------------------------------------------------
	public void setupMarketplace(Marketplace marketplace) {
		this.marketplace = marketplace;
		stockpile.put(Resources.Wood, stockpile.get(Resources.Wood) - 1);
		stockpile.put(Resources.Gold, stockpile.get(Resources.Gold) - 1);
		stockpile.put(Resources.Molasses, stockpile.get(Resources.Molasses) - 1);
		stockpile.put(Resources.Goats, stockpile.get(Resources.Goats) - 1);
		stockpile.put(Resources.Cutlasses, stockpile.get(Resources.Cutlasses) - 1);
	}

	// ------------------------------------------------------------------------------------------------
	// ---------- Method: return Resource -------------------------------------------------------------
	// Returns resources to the stockpile. Used for buying things, restocking the marketplace etc.
	// ------------------------------------------------------------------------------------------------
	public void returnResource(Resources resource, int numberOfResource) {
		stockpile.put(resource, stockpile.get(resource) + numberOfResource);
	}

	// ------------------------------------------------------------------------------------------------
	// ---------- Method: distributeResource ----------------------------------------------------------
	// This distributes resources from the stockpile to users
	// ------------------------------------------------------------------------------------------------
	public void distributeResource(Resources resource, int numberOfResource) {
		int numberofResourceAvailable = stockpile.get(resource);
		// ----- If there are enough resources, take resources from stockpile to deliver to users -----
		if (numberofResourceAvailable >= numberOfResource) { 
			stockpile.put(resource, stockpile.get(resource) - numberOfResource);
		} 
		// ----- If there aren't enough resources, restock the stockpile with that resources ----------
		else {
			restockResource(resource);
		}
	}
	
	// ------------------------------------------------------------------------------------------------
	// ---------- Test Methods ------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------
	public int getResourceCount(Resources resource) {
		return stockpile.get(resource);
	}
	
	public void removeResource(Resources resource, int count) {
		stockpile.put(resource, stockpile.get(resource) - count);
	}
}