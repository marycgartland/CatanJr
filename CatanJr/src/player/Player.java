package player;

// Import any packages needed
import java.util.*;
import gameplay.*;

public class Player {
	
	//----------------------------------------------------------
	//---------- Variables -------------------------------------
	//----------------------------------------------------------
	protected String name;
	protected String colour;
	
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
	
	
	//----------------------------------------------------------
	//---------- Pocket methods --------------------------------
	//----------------------------------------------------------
	
	// Pocket (1) - add method
		// In this method, we can add resources to the pocket
	//public void addResource(Resource newResouce){
	//	pocket.add(newResource);
	//}
	
	// Pocket (2) - view pocket
	// Note: So far, this displays a count of each resource just that a person has
	//public String viewPocket() {
	//	cocoTileCount = Collections.frequency(pocket, CocoTiles); 
	//	return "Cocotiles: " + getCocoTileCount() 
	//			+ "\nCutlasses: " + getCutlassesCount()
	//			+ "\nGoats: " + getGoatsCount()
	//			+ "\nWood: " + getWoodCount()
	//			+ "\nGold: " + getGoldCount()
	//			+ "\Molasses: " + getMolassesCount()
	//}
	
	// Pocket (3) - remove items from pocket{
	//}
	
	// Add a way to show where ships and Lairs are on board
	
	//----------------------------------------------------------
	//---------- "Your-turn" method ----------------------------
	//----------------------------------------------------------
	
	// Notes: This method gives the user a choice of what actions they want to take on their turn
	// We will need to make something in the main class or something that loops through the players turns
	public void yourTurn() {
		System.out.println("It is your turn, " + name + ". Please roll the dice.");
		
		// Call roll-dice method... will need to fix this because we can't keep creating a dice with the same name each time
		Dice dice1 = new Dice();
		diceValue = dice1.rollDice();
		
		System.out.println("You have a rolled a " + diceValue + "\n");
		
		System.out.print("Would you like to Buy [B], Build [Bd], Trade [T], or End turn [E]?");
		// Take in the option the user wants. Will need to add in error checking later
		Scanner scan = new Scanner(System.in);
		String option = scan.next();
		System.out.print(option);
		
		// Call the 'Buy' Method if the user enters 'B'
		if(option == "B") {	
		}
		
		// Call the 'Build' method if the user enters 'Bd'
		else if(option == "Bd") {
		}
		
		// Call the 'Trade' method if the user enters 'T'
		else if(option == "T") {		
		}
		
		// If the user wishes to end turn, leave this method
		else {
			return;
		}
		
		// To do: make sure that this loops, so the player can do multiple things in one turn
	}

	// Buy method: This method lets you buy cocotiles
	// Build method: This method lets you build lairs/ships on the board
	// Trade method
	// We want methods to show people their resources at any time	protected Item [] itemArray;
	
	
	//----------------------------------------------------------
	//---------- Get methods to count items in pocket ----------
	//----------------------------------------------------------
	
	// public int getCocoTileCount(){
	//		return Collections.frequency(pocket, CocoTiles); 
	//}
	
	// public int getCutlassesCount(){
	//		return Collections.frequency(pocket, cutlasses); 
	//}
	
	// public int getGoatsCount(){
		//		return Collections.frequency(pocket, goats); 
	//}
	
	// public int getWoodCount(){
	//		return Collections.frequency(pocket, wood); 
	//}
	
	// public int getGoldCount(){
		//		return Collections.frequency(pocket, gold); 
		//}
	
	// public int getMolassesCount(){
	//		return Collections.frequency(pocket, molasses); 
	//}

}
