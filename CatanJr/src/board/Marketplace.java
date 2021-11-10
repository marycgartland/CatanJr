package board;

public class Marketplace {

	protected Resources[] marketPlace = new Resources[5]; // marketplace will be made up of an array of resources (chose an array cause its a set length, arraylists can change in length)

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

	public void SetupMarketplace() {
		this.marketPlace = [Resources.Wood, Resources.Cutlasses, Resources.Goats, Resources.Gold, Resources.Molasses];
		// this takes resources from the stockpile so need to update the stockpile
	}

	// Method to check and see if a specified resource is in the marketplace if the
	// user wants to trade
	public boolean CheckForResourceMarketplace(Resources resource) {
		for (int i = 0; i <= 4; i++) {
			if (marketPlace[i] == resource) {
				System.out.println("The Marketplace has this resource");
				// ask the user if they want to swap with the marketplace, if yes then call
				// SwapMethod
				return true;
			} else {
				System.out.println("The Marketplace does not have resource");
				return false;
			}
		}
	}

	// Method to swap a resource for another
	public void SwapMarketplace(Resources wantedResource, Resources toSwapResource) {
		for (int i = 0; i <= 4; i++) {
			if (marketPlace[i] == wantedResource) {
				marketPlace[i] = toSwapResource; // this updates the marketplace resources, need to update users pocket to give them this variable
				CheckMarketplace(); // now that user has swapped with the marketplace, need to check and make sure all marketplace resources are not the same type

			}
		}
	}

	// checks to see if there are 5 of the same element or not
	public void CheckMarketplace() {
		int counter = 0;
		for (int i = 0; i <= 3; i++) {
			if (marketPlace[i] == marketPlace[i + 1]) {
				counter = counter + 1;
			}
		}
		if (counter == 4) { // all array elements are equal
			// need to return all resources to stockpile
			SetupMarketplace(); // setup marketplace again with the 5 different elements
		} else {
			// all elements are not equal
		}
	}

}
