package board;

import main.Interactor;
import player.Player;
import resources.Resources;

public class Marketplace {
	// -------------------------------------------------------------------------
	// ---------- Variables ----------------------------------------------------
	// -------------------------------------------------------------------------
	protected Resources[] marketPlace; // Array containing marketplace resources
	protected Stockpile stockpile;
	
	// ----- Create an interactor object ---------------------------------------
	Interactor interactor = new Interactor();

	// -------------------------------------------------------------------------
	// ---------- Constructor --------------------------------------------------
	// -------------------------------------------------------------------------
	public Marketplace() {
	}

	// -------------------------------------------------------------------------
	// ---------- Method: setupMarketplace -------------------------------------
	// Removes 5 resources from stockpile to add to marketplace
	// -------------------------------------------------------------------------
	public void setupMarketplace(Stockpile stockpile) {
		this.marketPlace = new Resources[] { Resources.Wood, Resources.Cutlasses, Resources.Goats, Resources.Gold, Resources.Molasses };
		this.stockpile = stockpile;
	}

	// -------------------------------------------------------------------------
	// ---------- Method: checkForResourceMarketplace --------------------------
	// Check if specified resource is in the marketplace
	// -------------------------------------------------------------------------
	public boolean checkForResourceMarketplace(Resources resource) {
		boolean resource_found = false;
		for (int i = 0; i <= 4; i++) {
			if (marketPlace[i] == resource) {
				resource_found = true;
			}
		}
		return resource_found;
	}

	// -------------------------------------------------------------------------
	// ---------- Method: checkForResourceMarketplaceStockpileRestock ----------
	// Check for resource in marketplace when stockpile needs to be restocked.
	// This returns the number of the resource in the marketplace
	// -------------------------------------------------------------------------
	public int checkForResourceMarketplaceStockpileRestock(Resources resource) {
		int counter = 0;
		for (int i = 0; i <= 4; i++) {
			if (marketPlace[i] == resource) {
				counter = counter + 1;
			}
		}
		return counter;
	}
	
	// -------------------------------------------------------------------------
	// ---------- Method: swapMarketplace --------------------------------------
	// Method to swap one resource for another
	// -------------------------------------------------------------------------
	public void swapMarketplace(Resources wantedResource, Resources toSwapResource, Player player) {
		for (int i = 0; i <= 4; i++) {
			if (marketPlace[i] == wantedResource) {
				marketPlace[i] = toSwapResource; 			// Update the marketplace resources
				interactor.printMessage("Successful marketplace trade");
				player.addResource(wantedResource, 1); 		// Add one of wanted resource to players pocket
				player.removeResource(toSwapResource, 1); 	// Remove one of swapped resource to players pocket
				checkMarketplace(); 						// Check that all marketplace resources are not the same type
			}
		}
	}

	// -------------------------------------------------------------------------
	// ---------- Method: checkMarketplace -------------------------------------
	// Check marketplace to see if there are 5 of the same element or not. 
	// -------------------------------------------------------------------------
	public void checkMarketplace() {
		int counter = 0;
		for (int i = 0; i <= 3; i++) {
			if (marketPlace[i] == marketPlace[i + 1]) {
				counter = counter + 1;
			}
		}
		// If all array elements are equal, send these back to stockpile, and reset marketplace
		if (counter == 4) {
			stockpile.returnResource(marketPlace[1], 5); 	// Return resources from marketplace to stockpile
			setupMarketplace(stockpile); 					// Setup marketplace with the 5 different elements
		} 
	}

	// -------------------------------------------------------------------------
	// ---------- Method: viewMarketplace --------------------------------------
	// Output marketplace contents 
	// -------------------------------------------------------------------------
	public void viewMarketplace() {
		String toPrint = String.valueOf(marketPlace[0]) + ", " + String.valueOf(marketPlace[1]) + ", "
						+ String.valueOf(marketPlace[2]) + ", " + String.valueOf(marketPlace[3]) + ", " 
						+ String.valueOf(marketPlace[4]);
		interactor.printMessage("View Marketplace", toPrint);
	}
}
