package board;

import main.Interactor;
import player.Player;
import resources.Resources;

public class Marketplace {

	protected Resources[] marketPlace; // marketplace will be made up of an array of resources (chose an array cause its a set length, arraylists can change in length)
	Interactor interactor = new Interactor();
	protected Stockpile stockpile;

	// Variables
	// Constructor
	// If all 5 places are the same resource, then we need to reset the marketplace
	// - need a check and a reset method
	// method to let the user trade

	// SetupMethod which will be called by the setup class: this will restock the
	// market place with one of each resource (1 x: cutlass, goat, wood, gold and
	// molasses)

	// CheckMethod to check that all resources in the marketplace are not the same,
	// if it is the Setup Method is called and the all resources are returned to
	// stockpile


	// Swap method that can only be called once in a turn which tracks resources in
	// marketplace
	
	// EP: YourTurn Class should keep track of how many times the marketplace is swapped with per users turn


	public Marketplace() {
	}

	// this removes the 5 resources from the stockpile (the actual removal from stockpile is handled in ResourceSetup class)
	public void SetupMarketplace(Stockpile stockpile) {
		this.marketPlace = new Resources[] {Resources.Wood, Resources.Cutlasses, Resources.Goats, Resources.Gold, Resources.Molasses};
		this.stockpile = stockpile;
	}

	// Method to check and see if a specified resource is in the marketplace if the
	// user wants to trade
	public boolean CheckForResourceMarketplace(Resources resource) {
		for (int i = 0; i <= 4; i++) {
			if (marketPlace[i] == resource) {
				return true;
			} else {
				return false;
			}
		}
		return false; // not sure if this will override previous boolean values, need to check this
	}

	// method to check how many of a particular resource is in the marketplace when the stockpile needs to be restocked by that resource as there is 0 in the stockpile
	// this method will return the number of that resource in the marketplace so the stockpile restocks by 18-(no of resource in marketplace)
	public int CheckForResourceMarketplaceStockpileRestock(Resources resource) {
		int counter = 0;
		for (int i = 0; i <= 4; i++) {
			if(marketPlace[i]==resource) {
				counter=counter+1;
			}
		}
		return counter;
	}
	
	// Method to swap a resource for another
	public void SwapMarketplace(Resources wantedResource, Resources toSwapResource, Player player) {
		for (int i = 0; i <= 4; i++) {
			if (marketPlace[i] == wantedResource) {
				marketPlace[i] = toSwapResource; // this updates the marketplace resources, need to update users pocket to give them this variable
				interactor.printMessage("Successful marketplace trade");
				player.addResource(wantedResource, 1); // add one of wanted resource to players pocket
				player.removeResource(toSwapResource, 1); // remove one of resource to players pocket
				CheckMarketplace(); // now that user has swapped with the marketplace, need to check and make sure all marketplace resources are not the same type
			}
		}
	}

	// checks to see if there are 5 of the same element or not
	public void CheckMarketplace() {
		int counter = 0;
		for (int i = 0; i <= 4; i++) {
			if (marketPlace[i] == marketPlace[i + 1]) {
				counter = counter + 1;
			}
		}
		if (counter == 5) { // all array elements are equal, need to send 5 of this matching resource back to stockpile
			stockpile.ReturnResource(marketPlace[1], 5); // need to return the resource back to the stockpile before market is setup again
			SetupMarketplace(stockpile); // setup marketplace again with the 5 different elements
		} else {
			// all elements are not equal
		}
	}

}
