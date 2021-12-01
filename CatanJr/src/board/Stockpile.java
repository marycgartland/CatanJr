package board;

import java.util.ArrayList;
import java.util.HashMap;

import player.Player;
import resources.Resources;

public class Stockpile {

	// Variables
	// Constructor
	// Method to let user swap 2 resources for 1 in the stockpile
	// Keep track of the number of each resource
	// Method to make sure there is at least 1 of each resource left, otherwise all
	// of that resource are returned to the stockpile

	// EP: Stockpile needs to be called before the users are given their first pieces and before the marketplace is setup
	// EP: Need method to assign resources to different users, for example when the dice is rolled, the stockpile needs to assign the resources to the players

	protected HashMap<Resources, Integer> stockpile = new HashMap<Resources, Integer>();
	protected ArrayList<Player> players;
	protected Marketplace marketplace;

	// Constructor
	public Stockpile(ArrayList<Player> players) {
		// add 18 of each resource to stockpile
		stockpile.put(Resources.Gold, 18);
		stockpile.put(Resources.Wood, 18);
		stockpile.put(Resources.Cutlasses, 18);
		stockpile.put(Resources.Molasses, 18);
		stockpile.put(Resources.Goats, 18);
		this.players = players;
	}

	// if this method is called 2 of the toSwapResource needs to be removed from the
	// users pocket and 1 of the wantedResource needs to be added to their pocket
	public void SwapStockpile(Resources wantedResource, Resources toSwapResource, Player player) {
		int numberofwantedresource = stockpile.get(wantedResource);
		if (numberofwantedresource > 0) {
			stockpile.put(wantedResource, numberofwantedresource - 1); // need to add 1 of this resource to users pocket
			player.addResource(wantedResource, 1); // add one wanted resource to users pocket
			player.removeResource(toSwapResource, 2); // remove 2 from users pocket
			stockpile.put(toSwapResource, stockpile.get(toSwapResource) + 2); // need to remove 2 of this resource from users pocket
		} else {
			CheckStockpile(); // need to call method to check if there is more than 0 of each resource, if not all of that resource must be returned
		}
	}

	// method to check if resources need to be returned to stockpile
	public void CheckStockpile() {
		if (stockpile.get(Resources.Gold) == 0) {
			restockResource(Resources.Gold); // take back all gold from every player
		} else if (stockpile.get(Resources.Molasses) == 0) {
			restockResource(Resources.Molasses); // take back all molasses from every player
		} else if (stockpile.get(Resources.Wood) == 0) {
			restockResource(Resources.Wood); // take back all wood from every player
		} else if (stockpile.get(Resources.Cutlasses) == 0) {
			restockResource(Resources.Cutlasses); // take back all cutlasses from every player
		} else if (stockpile.get(Resources.Goats) == 0) {
			restockResource(Resources.Goats); // take back all goats from every player
		}
	}
	
	
	// if a resource is zero that resource is returned to the stockpile,
	public void restockResource(Resources resource) {
		// removes all of the defined resource from users pocket
		for (int i = 0; i <= this.players.size() - 1; i++) {
			int resource_pocket = players.get(i).checkPocketResourcesType(resource);
			players.get(i).removeResource(resource, resource_pocket);
		}
		
		// resource isn't restocked from marketplace: so need to check if resource is in marketplace and minus that number from number to restock by
		int resource_marketplace = marketplace.CheckForResourceMarketplaceStockpileRestock(resource);
		stockpile.put(resource, 18 - resource_marketplace); // restock number of requested resource
	}
	
	// Give each player one wood resource tile and one molassess resource tile
	// This function removes 1 wood and 1 molasses per player from the stockpile,
	// these then need to be added to the users pockets
	public void SetupPlayers(int numberPlayers) {
		int numberofwoodresource = stockpile.get(Resources.Wood);
		int numberofmolassesresource = stockpile.get(Resources.Molasses);

		stockpile.put(Resources.Wood, numberofwoodresource - numberPlayers);
		stockpile.put(Resources.Molasses, numberofmolassesresource - numberPlayers);

		// need to add 1 wood and 1 molasses to each players pocket
		for (int i = 0; i <= players.size() - 1; i++) {
			players.get(i).addResource(Resources.Wood, 1);
			players.get(i).addResource(Resources.Molasses, 1);
			System.out.println(players.get(i).viewPocket()); // will take out, just tracking whats happening
		}

	}
	
	// this is used by marketplace when marketplace is setup, it will remove one of each resource from the stockpile
	public void SetupMarketplace(Marketplace marketplace) {
		this.marketplace = marketplace;
		stockpile.put(Resources.Wood, stockpile.get(Resources.Wood)-1);
		stockpile.put(Resources.Gold, stockpile.get(Resources.Gold)-1);
		stockpile.put(Resources.Molasses, stockpile.get(Resources.Molasses)-1);
		stockpile.put(Resources.Goats, stockpile.get(Resources.Goats)-1);
		stockpile.put(Resources.Cutlasses, stockpile.get(Resources.Cutlasses)-1);
	}
	
	// method called to return resources to stockpile e.g. buying things, restocking marketplace
	public void ReturnResource(Resources resource, int numberOfResource) {
		stockpile.put(resource, stockpile.get(resource)+ numberOfResource);
	}
	
	// distribute resources from stockpile to users
	public void DistributeResource(Resources resource, int numberOfResource) {
		int numberofResourceAvailable = stockpile.get(resource);
		if (numberofResourceAvailable >= numberOfResource) { // if there are enough resources to be distributed
			stockpile.put(resource, stockpile.get(resource) - numberOfResource);
		} else { // restock stockpile with that resource if there arent enough to be given out (return all of that resource to stockpile, from users)
			restockResource(resource);
		}
	}

}
