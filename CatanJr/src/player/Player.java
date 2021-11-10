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
	protected int numberCocoTiles=0;	
	int diceValue;
	
	// Variable - this array/set, "pocket", stores the resources a player has
	//protected Set<Resource> pocket = new HashSet<Resource>();
	
	
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

	// In this method, we can remove a certain number of resources to the pocket
	public void removeResource(Resources removedResource, int numberRemovedResource) {
		pocket.put(removedResource, pocket.get(removedResource) - numberRemovedResource);
	}
	
	// Pocket (2) - view pocket
	// Note: So far, this displays a count of each resource just that a person has
	public String viewPocket() {
		return this.name+"'s Pocket: \n"+
				"Cocotiles: " + getCocoTileCount() 
				+ "\nCutlasses: " + getCutlassesCount()
				+ "\nGoats: " + getGoatsCount()
				+ "\nWood: " + getWoodCount()
				+ "\nGold: " + getGoldCount()
				+ "\nMolasses: " + getMolassesCount() + "\n";
	}
	
	// method to setup the users pocket at the start of the game, they will have no resources
	public void setupUserPocket() {
		pocket.put(Resources.Gold, 0);
		pocket.put(Resources.Wood, 0);
		pocket.put(Resources.Cutlasses, 0);
		pocket.put(Resources.Molasses, 0);
		pocket.put(Resources.Goats, 0);
	}
	
	// Method that can be called when user purchases a cocotile, this will increase there count by 1
	public void addCocoTile() {
		this.numberCocoTiles = this.numberCocoTiles +1;
	}

	
	
	// Add a way to show where ships and Lairs are on board
	
	//----------------------------------------------------------
	//---------- "Your-turn" method ----------------------------
	//----------------------------------------------------------
	
	


	// We want methods to show people their resources at any time	protected Item [] itemArray;
	
	
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

}
