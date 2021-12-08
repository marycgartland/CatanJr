package player;

// Import any packages needed
import java.util.*;
import resources.Resources;

public class Player {
	// ----------------------------------------------------------
	// ---------- Variables -------------------------------------
	// ----------------------------------------------------------
	protected String name;				// Name of player
	protected String colour;			// Players assigned color
	protected HashMap<Resources, Integer> pocket = new HashMap<Resources, Integer>();	// Stores a players resources
	protected int numberCocoTiles = 0;	// Number of cocotiles a player has
	int diceValue;						// Dice roll of a player
	protected int numberLairs = 2; 		// Keeps track of # of lairs of a player. 2 lairs/player on board to begin. Need 7 to win


	// ----------------------------------------------------------
	// ---------- Constructor Method ----------------------------
	// ----------------------------------------------------------
	public Player(String name, String colour) {
		this.name = name;
		this.colour = colour;
	}

	// ----------------------------------------------------------
	// ---------- Pocket methods --------------------------------
	// ----------------------------------------------------------

	// Method to add a certain number of resources to the pocket
	public void addResource(Resources newResource, int numberNewResource) {
		pocket.put(newResource, pocket.get(newResource) + numberNewResource);
	}

	// Method remove a certain number of resources from the pocket
	public void removeResource(Resources removedResource, int numberRemovedResource) {
		pocket.put(removedResource, pocket.get(removedResource) - numberRemovedResource);
	}

	// Method to view a count of all resources in the pocket
	public String viewPocket() {
		return "\n" + this.name + "'s Pocket: \n" + "Cocotiles: " + getCocoTileCount() + "\nCutlasses: "
				+ checkPocketResources("C") + "\nGoats: " + checkPocketResources("GT") + "\nWood: " 
				+ checkPocketResources("W") + "\nGold: " + checkPocketResources("G") + "\nMolasses: " 
				+ checkPocketResources("M") + "\n";
	}

	// Method to set up the users pocket at the start of the game
	public void setupUserPocket() {
		pocket.put(Resources.Gold, 0);
		pocket.put(Resources.Wood, 0);
		pocket.put(Resources.Cutlasses, 0);
		pocket.put(Resources.Molasses, 0);
		pocket.put(Resources.Goats, 0);
	}

	// Method to add a cocotile to a players cocotile count when they buy one
	public void addCocoTile() {
		this.numberCocoTiles = this.numberCocoTiles + 1;
	}

	// Method to increase number of lairs user has bought
	public void addLair() {
		this.numberLairs = this.numberLairs + 1;
	}

	// Method to decrease number of lairs a user has on the board
	public void removeLair() {
		this.numberLairs = this.numberLairs - 1;
	}
	
	// Method to check how much of a user has in their pocket based on the resource letter
	public int checkPocketResources(String resourceLetter) {
		if (resourceLetter.equals("W")) {
			return pocket.get(Resources.Wood);
		} else if (resourceLetter.equals("M")) {
			return pocket.get(Resources.Molasses);
		} else if (resourceLetter.equals("G")) {
			return pocket.get(Resources.Gold);
		} else if (resourceLetter.equals("GT")) {
			return pocket.get(Resources.Goats);
		} else {
			return pocket.get(Resources.Cutlasses);
		}
	}

	// Method to check how many of specified resource type is in users pocket
	public int checkPocketResourcesType(Resources resource) {
		return pocket.get(resource);
	}

	// ----------------------------------------------------------
	// ---------- Methods to get player features ----------------
	// ----------------------------------------------------------
	public String PlayerName() {
		return this.name;
	}

	public String getColour() {
		return this.colour;
	}
	
	public String toString() {
		return "Player Name: " + this.name + "\nPlayer Colour: " + this.colour;
	}

	// ----------------------------------------------------------
	// ---------- Get methods to count other resources ----------
	// ----------------------------------------------------------
	public int getCocoTileCount() {
		return this.numberCocoTiles;
	}

	public int getLairCount() {
		return numberLairs;
	}
}