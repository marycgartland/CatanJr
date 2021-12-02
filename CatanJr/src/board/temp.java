package board;

import gameplay.Dice;

public class Board {
	protected char[] Line1;
	protected char[] Line2;
	protected char[] Line3;
	protected char[] Line4;
	protected char[] Line5;
	protected char[] Line6;
	protected char[] Line7;
	protected char[] Line8;
	protected char[] Line9;
	protected char[] Line10;
	protected char[] Line11;
	protected char[] Line12;
	protected char[] Line13;
	protected char[] Line14;
	protected char[] Line15;
	
	protected int diceValue;
	
	// Lower case: Ship
	// Upper case: Lair


	// Variables
	// Constructor
	// Track user locations
	// Track ship and Lair placements
	// Check the location of the ghost captain
	// Track the number of lairs on the board: 7 lairs = winner
	// Provide ship/lair placement options to user when building
	// Needs to look after ships and lairs, keeping track of numbers and placements 
	// assign resources to users based on their positions and dice value rolled
	// check which user has the most cocotiles: whichever user has the most, they can place their lair on spooky island
	
	// Construct board layout
	public Board() {
		Line1 = new char[] { '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', 'X', '-','-', '-', '-', '-','X','\n' };
		Line2 = new char[] { '-', '-', '-', '-', '-', '-','-','-','-', '/', '-', '-', '-', '\\', '-', '/', '-', '-', '-','\\','\n' };
		Line3 = new char[] { '-', '-', '-', '-', '-', '-','-','-','X', '-', '-', '-', '-', '-', 'X', '-', '-', '-', '-','-','X','\n' };
		Line4 = new char[] { '-', '-', '-', '-', '-', '-','-','-','|', '-', '-', '-', '-', '-', '|', '-', '-', '-', '-','-','|','\n' };
		Line5 = new char[] { '-', '-', 'X', '-', '-', '-','-','-','X', '-', '-', '-', '-', '-', 'X', '-', '-', '-', '-','-','X','-', '-', '-', '-', '-','X','\n' };
		Line6 = new char[] { '/', '-', '-', '-', '\\', '-','/','-','-', '-', '\\', '-', '/', '-', '-', '-', '\\', '-', '/','-','-','-', '\\', '-', '/', '-','-','-','\\','\n' };
		Line7 = new char[] { 'X', '-', '-', '-', '-', 'X','-','-','-','-', '-', 'X', '-', '-', '-', '-', '-', 'X', '-', '-','-','-','-', 'X', '-', '-', '-','-','-','X','\n' };
		Line8 = new char[] { '|', '-', '-', '-', '-', '|','-','-','-','-', '-', '|', '-', '-', '-', '-', '-', '|', '-', '-','-','-','-', '|', '-', '-', '-','-','-','|','\n' };
		Line9 = new char[] { 'X', '-', '-', '-', '-', 'X','-','-','-','-', '-', 'X', '-', '-', '-', '-', '-', 'X', '-', '-','-','-','-', 'X', '-', '-', '-','-','-','X','\n' };
		Line10 = new char[] { '\\', '-', '-', '-', '/', '-','\\','-','-', '-', '/', '-', '\\', '-', '-', '-', '/', '-', '\\','-','-','-', '/', '-', '\\', '-','-','-','/','\n' };
		Line11 = new char[] { '-', '-', 'X', '-', '-', '-','-','-','X', '-', '-', '-', '-', '-', 'X', '-', '-', '-', '-','-','X','-', '-', '-', '-', '-','X','\n' };
		Line12 = new char[] { '-', '-', '-', '-', '-', '-','-','-','|', '-', '-', '-', '-', '-', '|', '-', '-', '-', '-','-','|','\n' };
		Line13 = new char[] { '-', '-', '-', '-', '-', '-','-','-','X', '-', '-', '-', '-', '-', 'X', '-', '-', '-', '-','-','X','\n' };
		Line14 = new char[] { '-', '-', '-', '-', '-', '-','-','-','-', '\\', '-', '-', '-', '/', '-', '\\', '-', '-', '-','/','\n' };
		Line15 = new char[] { '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', 'X', '-','-', '-', '-', '-','X','\n' };
		
	}
	
	public void showBoardLayout() {
		System.out.println("Board Layout:");
		for (int i = 0; i <= Line1.length - 1; i++) {
			System.out.print(Line1[i]);
		}
		for (int i = 0; i <= Line2.length - 1; i++) {
			System.out.print(Line2[i]);
		}
		for (int i = 0; i <= Line3.length - 1; i++) {
			System.out.print(Line3[i]);
		}
		for (int i = 0; i <= Line4.length - 1; i++) {
			System.out.print(Line4[i]);
		}
		for (int i = 0; i <= Line5.length - 1; i++) {
			System.out.print(Line5[i]);
		}
		for (int i = 0; i <= Line6.length - 1; i++) {
			System.out.print(Line6[i]);
		}
		for (int i = 0; i <= Line7.length - 1; i++) {
			System.out.print(Line7[i]);
		}
		for (int i = 0; i <= Line8.length - 1; i++) {
			System.out.print(Line8[i]);
		}
		for (int i = 0; i <= Line9.length - 1; i++) {
			System.out.print(Line9[i]);
		}
		for (int i = 0; i <= Line10.length - 1; i++) {
			System.out.print(Line10[i]);
		}
		for (int i = 0; i <= Line11.length - 1; i++) {
			System.out.print(Line11[i]);
		}
		for (int i = 0; i <= Line12.length - 1; i++) {
			System.out.print(Line12[i]);
		}
		for (int i = 0; i <= Line13.length - 1; i++) {
			System.out.print(Line13[i]);
		}
		for (int i = 0; i <= Line14.length - 1; i++) {
			System.out.print(Line14[i]);
		}
		for (int i = 0; i <= Line15.length - 1; i++) {
			System.out.print(Line15[i]);
		}
		System.out.println("\n");
	}
	
	// place ghost captain on an island
	// this will stop users from obtaining resources from this island
	public void placeGhostCaptain() {
		
	}
	
	// place a users ship on the board
	public void placeShip() {
		
	}
	
	// place a users lair on the board
	public void placeLair() {
		
	}
	
	// Function to define starting positions of blue players ships and lairs
	public void setupBluePlayerLocations() {
		Line5[26] = 'B';
		Line6[24] = 'b';
		Line13[8] = 'B';
		Line12[8] = 'b';
	}

	// Function to define starting positions of red players ships and lairs
	public void setupRedPlayerLocations() {
		Line3[8] = 'R';
		Line4[8] = 'r';
		Line10[24] = 'r';
		Line11[26] = 'R';
	}

	// Function to define starting positions of white players ships and lairs
	public void setupWhitePlayerLocations() {
		Line5[2] = 'W';
		Line6[4] = 'w';
		Line12[20] = 'w';
		Line13[20] = 'W';
	}

	// Function to define starting positions of orange players ships and lairs
	public void setupOrangePlayerLocations() {
		Line3[20] = 'O';
		Line4[20] = 'o';
		Line10[4] = 'O';
		Line11[2] = 'o';
	}

	// Setup Board design, place users first ship and lairs on board
	public void setupBoard(int numberplayers) {
		if (numberplayers == 1) {
			setupBluePlayerLocations(); // place blue players ships and lairs
		} else if (numberplayers == 2) {
			setupBluePlayerLocations(); // place blue players ships and lairs
			setupRedPlayerLocations(); // place red players ships and lairs
		} else if (numberplayers == 3) { // blue, red, white
			setupBluePlayerLocations(); // place blue players ships and lairs
			setupRedPlayerLocations(); // place red players ships and lairs
			setupWhitePlayerLocations(); // place white players ships and lairs
		} else { // 4 players
			setupBluePlayerLocations(); // place blue players ships and lairs
			setupRedPlayerLocations(); // place red players ships and lairs
			setupWhitePlayerLocations(); // place white players ships and lairs
			setupOrangePlayerLocations(); // place orange players ships and lairs
		}
	}
	
	// Check the dice roll values.... Might be better moving to GameManager later and just checking the board?
	//----- Method: checkDiceRoll-----
	public void checkDiceRoll(Dice dice) {
		diceValue = dice.getDiceRollValue();
		if(diceValue==1) {				// Roll a 1
			// Check islands 1, 3 and 10
			// Island 1: {Arrays 3, 5, 7}, Assign: Cutlass
			// Island 3: {Arrays 1, 3, 5, 7}, Assign: goat
			// Island 10: {Arrays 9, 11, 13, 15}, Assign: wood
		}
		else if(diceValue==2) {			// Roll a 2
			// Check islands 2, 4, and 11
			// Island 2: {Arrays 1, 3, 5, 7}, Assign: wood
			// Island 4: {Arrays 3, 5, 7}, Assign: molasses
			// Island 11: {Arrays 9, 11, 13, 15}, Assign: goat
		}
		else if(diceValue==3) {			// Roll a 3
			// Check islands 5 and 7
			// Island 5: {Arrays 5, 7, 9 , 11}, Assign: wood
			// Island 7: {Arrays 5, 7, 9, 11}, Assign: gold
		}
		else if(diceValue==4) {			// Roll a 4
			// Check island 9 and 12
			// Island 9:  {Arrays 9, 11, 13}, Assign: Cutlass
			// Island 12: {Arrays 9, 11, 13}, Assign: molasses
		}
		else if(diceValue==5) {			// Roll a 5
			// Check islands 6 and 8
			// Island 6: {Arrays 5, 7, 9, 11}, Assign: gold
			// Island 8: {Arrays 5, 7, 9, 11}, Assign: goat

		}
		else {							// Roll a 6
			// Ghost captain
		}
		
	}
	
	// Make a separate function to check if the given position in the arrays has Each letter
	// If the letter is there
	
	// Check the letter in a location
	// if that letter exists, assign 1 of that resource to the player of that colour
	// Method: check Array
	public void checkArray(){
		
	}
	
	
}