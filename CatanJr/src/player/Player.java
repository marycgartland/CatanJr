package player;

// Import any packages needed
import java.util.*;
import gameplay.*;
import resources.Resources;

public class Player {
	
	//----------------------------------------------------------
	//---------- Variables -------------------------------------
	//----------------------------------------------------------
	protected String name;
	protected String colour;
	protected HashMap<Resources, Integer> pocket = new HashMap<Resources, Integer>();
	protected int numberCocoTiles = 0;
	int diceValue;
	protected int numberLairs = 2; // this will keep track of how many lairs player has, which will determine the
									// winner if lairs=7
	// set to 2 lairs when its setup beacuse each player can place 2 lairs on the board at the start

	// Variable - this array/set, "pocket", stores the resources a player has
	
	//----------------------------------------------------------
	//---------- Constructor Method ----------------------------
	//----------------------------------------------------------
	
	public Player(String name, String colour){
		this.name = name;
		this.colour = colour;
	}
	
	public String toString() {
		return "Player Name: " + this.name + "\nPlayer Colour: " + this.colour;
	}
	
	public String PlayerName() {
		return this.name;
	}
	
	//----------------------------------------------------------
	//---------- Pocket methods --------------------------------
	//----------------------------------------------------------
	
		// In this method, we can add a certain number of resources to the pocket
	public void addResource(Resources newResource, int numberNewResource) {
		pocket.put(newResource, pocket.get(newResource) + numberNewResource);
	}

	// In this method, we can remove a certain number of resources from the pocket
	public void removeResource(Resources removedResource, int numberRemovedResource) {
		pocket.put(removedResource, pocket.get(removedResource) - numberRemovedResource);
	}
	
	// Pocket (2) - view pocket
	// Note: So far, this displays a count of each resource just that a person has
	public String viewPocket() {
		return "\n" + this.name+"'s Pocket: \n"+
				"Cocotiles: " + getCocoTileCount() 
				+ "\nCutlasses: " + getCutlassesCount()
				+ "\nGoats: " + getGoatsCount()
				+ "\nWood: " + getWoodCount()
				+ "\nGold: " + getGoldCount()
				+ "\nMolasses: " + getMolassesCount() + "\n";
	}
	
	// method to setup the users pocket at the start of the game, they will have no resources
	// need to all be zero, im just testing
	public void setupUserPocket() {
		pocket.put(Resources.Gold, 20);
		pocket.put(Resources.Wood, 20);
		pocket.put(Resources.Cutlasses, 20);
		pocket.put(Resources.Molasses, 20);
		pocket.put(Resources.Goats, 20);
	}
	
	// Method that can be called when user purchases a cocotile, this will increase there count by 1
	public void addCocoTile() {
		this.numberCocoTiles = this.numberCocoTiles + 1;
	}
	
	// Method to increase number of lairs user has bought
	public void addLair() {
		this.numberLairs = this.numberLairs + 1;
	}
	
	// Method to increase number of lairs user has bought
	public void removeLair() {
		this.numberLairs = this.numberLairs - 1;
	}
	
	
	// Add a way to show where ships and Lairs are on board

	// We want methods to show people their resources at any time	protected Item [] itemArray;
	
	//----------------------------------------------------------
	//---------- Get Method for player features ----------------
	//----------------------------------------------------------
	public String getName(){
		return this.name; 
	}
	
	public String getColour() {
		return this.colour;
	}
	
	//----------------------------------------------------------
	//---------- Get methods to count items in pocket ----------
	//----------------------------------------------------------
	
	 public int getCocoTileCount(){
			return this.numberCocoTiles; 
	}
	
	 public int getCutlassesCount(){
			return pocket.get(Resources.Cutlasses); 
	}
	
	 public int getGoatsCount(){
				return pocket.get(Resources.Goats); 
	}
	
	 public int getWoodCount(){
			return pocket.get(Resources.Wood); 
	}
	
	 public int getGoldCount(){
			return pocket.get(Resources.Gold); 
		}
	
	 public int getMolassesCount(){
		return pocket.get(Resources.Molasses); 
	}
	 
	 public int getLairCount() {
		 return numberLairs;
	 }

	 
	 // another quick method to check how much of a user has in their pocket based on the letter of resource
		public int checkPocketResourcesLetter(String resourceLetter) {
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
		
		// check how many of specificed resource type is in users pocket
		public int checkPocketResourcesType(Resources resource) {
			return pocket.get(resource);
		}
}
