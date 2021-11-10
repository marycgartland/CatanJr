package board;

import java.util.ArrayList;
import java.util.HashMap;

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
	// EP: make a method to setup marketplace?????????????????

	protected HashMap<Resources, Integer> stockpile = new HashMap<Resources, Integer>();

	// Constructor
	public Stockpile() {
		// add 18 of each resource to stockpile
		stockpile.put(Resources.Gold, 18);
		stockpile.put(Resources.Wood, 18);
		stockpile.put(Resources.Cutlasses, 18);
		stockpile.put(Resources.Molasses, 18);
		stockpile.put(Resources.Goats, 18);
	}

	// if this method is called 2 of the toSwapResource needs to be removed from the
	// users pocket and 1 of the wantedResource needs to be added to their pocket
	public void SwapStockpile(Resources wantedResource, Resources toSwapResource) {
		int numberofwantedresource = stockpile.get(wantedResource);

		if (numberofwantedresource > 0) {
			stockpile.put(wantedResource, numberofwantedresource - 1); // need to add 1 of this resource to users pocket
			stockpile.put(toSwapResource, stockpile.get(toSwapResource) + 2); // need to remove 2 of this resource from users pocket
		}
		else {
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
		stockpile.put(resource, 18); // restock number of requested resource, need to remove this resource from everyones pocket aswell, but not the marketplace
	}

}
