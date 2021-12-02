package board;

import java.util.HashMap;

import main.Interactor;
import resources.Resources;
import gameplay.Dice;

public class Board {
	protected char[][] design;
	protected char[] options = {'1', '2', '3', '4', '5'};
	protected char[] symbolHolder = {'x','x','x','x', 'x'};
	Interactor interactor = new Interactor();

	protected int diceValue;
	
	// Lower case: Ship
	// Upper case: Lair

	// TODO: maybe take out dashes

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

		design = new char[][]{
			{ '-', '-', '-', '-','-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-','-', '-', '-', '-','-','-', '-','-','-','-','-', '-','-','-','-','-', '-','-','-','-', '-','\n' },
				{ '-', '-', '-', '-','-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', 'X', '-','-', '-', '-', '-','X','-', '-','-','-','-','-', '-','-','-','-','-', '-','-','-','-', '-','\n' },
				{ '-', '-', '-', '-','-', '-', '-', '-', '-', '-','-','-','-', '/', '-', '-', '-', '\\', '-', '/', '-', '-', '-','\\', '-', '-','-','-','-', '-', '-','-','-','-','-','-','-', '-',  '\n' }, 
				{ '-', '-', '-', '-','-', '-', '-', '-', '-', '-','-','-','X', '-', '-', '-', '-', '-', 'X', '-', '-', '-', '-','-','X','-', '-','-','-','-', '-', '-','-','-','-','-','-', '-',  '\n' }, 
				{ '-', '-', '-', '-','-', '-', '-', '-', '-', '-','-','-','|', '-', '-', '-', '-', '-', '|', '-', '-', '-', '-','-','|','-', '-','-','-','-', '-', '-','-','-','-','-','-', '-','\n' }, 
				{ '-', '-', '-', '-','-', '-', 'X', '-', '-', '-','-','-','X', '-', '-', '-', '-', '-', 'X', '-', '-', '-', '-','-','X','-', '-', '-', '-', '-','X','-', '-','-', '-','-','-', '-','\n' },
				{ '-', '-', '-', '-','/', '-', '-', '-', '\\', '-','/','-','-', '-', '\\', '-', '/', '-', '-', '-', '\\', '-', '/','-','-','-', '\\', '-', '/', '-','-','-','\\','-','-','-','-', '-','\n' },
				{ '-', '-', '-', '-','X', '-', '-', '-', '-', 'X','-','-','-','-', '-', 'X', '-', '-', '-', '-', '-', 'X', '-', '-','-','-','-', 'X', '-', '-', '-','-','-','X','-','-','-', '-','\n' },
				{ '-', '-', '-', '-','|', '-', '-', '-', '-', '|','-','-','-','-', '-', '|', '-', '-', '-', '-', '-', '|', '-', '-','-','-','-', '|', '-', '-', '-','-','-','|','-','-','-', '-','\n' },
				{ '-', '-', '-', '-','X', '-', '-', '-', '-', 'X','-','-','-','-', '-', 'X', '-', '-', '-', '-', '-', 'X', '-', '-','-','-','-', 'X', '-', '-', '-','-','-','X','-','-','-', '-','\n' },
				{ '-', '-', '-', '-','\\', '-', '-', '-', '/', '-','\\','-','-', '-', '/', '-', '\\', '-', '-', '-', '/', '-', '\\','-','-','-', '/', '-', '\\', '-','-','-','/','-','-','-','-', '-','\n' },
				{ '-', '-', '-', '-','-', '-', 'X', '-', '-', '-','-','-','X', '-', '-', '-', '-', '-', 'X', '-', '-', '-', '-','-','X','-', '-', '-', '-', '-','X','-','-','-','-','-','-', '-','\n' },
				{ '-', '-', '-', '-','-', '-', '-', '-', '-', '-','-','-','|', '-', '-', '-', '-', '-', '|', '-', '-', '-', '-','-','|','-', '-','-','-','-', '-', '-','-','-','-','-','-', '-','\n' },
				{ '-', '-', '-', '-','-', '-', '-', '-', '-', '-','-','-','X', '-', '-', '-', '-', '-', 'X', '-', '-', '-', '-','-','X','-', '-','-','-','-', '-', '-','-','-','-','-','-', '-','\n' }, 
				{ '-', '-', '-', '-','-', '-', '-', '-', '-', '-','-','-','-', '\\', '-', '-', '-', '/', '-', '\\', '-', '-', '-','/', '-', '-','-','-','-', '-', '-','-','-','-','-','-','-', '-','\n' }, 
				{ '-', '-', '-', '-','-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', 'X', '-','-', '-', '-', '-','X','-', '-','-','-','-','-', '-','-','-','-','-', '-','-','-','-', '-','\n' },
				{ '-', '-', '-', '-','-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-','-', '-', '-', '-','-','-', '-','-','-','-','-', '-','-','-','-','-', '-','-','-','-', '-','\n' }
		};
	}
	
	// Method to display board layout
	public void showBoardLayout() {
		System.out.println("Board Layout:");

		for (int i = 0; i <= 17 - 1; i++) {
			for (int j = 0; j <= 38 - 1; j++) {
				System.out.print(design[i][j]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	// place ghost captain on an island
	// this will stop users from obtaining resources from this island
	public void placeGhostCaptain() {
		
	}
	
	// place a users ship on the board
	// search for upper case of colour to search for lairs to connect ships to.
	// TODO: what happens when no ships are available
	public boolean placeShip(String colour) {
		boolean continue_turn = false;
		int count = 0;
		
		
		for (int i = 0; i <= 17 - 1; i++) {
			for (int j = 0; j <= 38 - 1; j++) {
				if (design[i][j] == Character.toUpperCase(colour.charAt(0))) {
					// check row above for available ship place
					for (int p = j - 3; p <= j + 3; p++) {
						if (design[i - 1][p] == '\\' || design[i - 1][p] == '/' || design[i - 1][p] == '|' ) {
							symbolHolder[count] = design[i - 1][p];
							design[i - 1][p] = options[count];
							count = count + 1;
						}
					}
					// check row below for available ship place
					for (int q = j - 3; q <= j + 3; q++) {
						if (design[i + 1][q] == '\\' || design[i + 1][q] == '/' || design[i + 1][q] == '|') {
							symbolHolder[count] = design[i+1][q];
							design[i + 1][q] = options[count];
							count = count + 1;
						}
					}
				}
			}
		}
		
		showBoardLayout();
		System.out.print("Which number option would you like to build your ship at?: ");
		String location_number = interactor.takeInAnswer();
		
		
		// place ship at users choice of location
		for (int i = 0; i <= 17 - 1; i++) {
			for (int j = 0; j <= 38 - 1; j++) {
				if (design[i][j] == location_number.charAt(0)) {
					design[i][j] = colour.charAt(0);
				}
			}
		}

		
		// replace numbers with slash's or lines again
		for (int i = 0; i <= 17 - 1; i++) {
			for (int j = 0; j <= 38 - 1; j++) {

				if (design[i][j] == '1' || design[i][j] == '2' || design[i][j] == '3' || design[i][j] == '4'|| design[i][j] == '5') {
					design[i][j] = symbolHolder[Character.getNumericValue(design[i][j])];
				}
			}
		}
		showBoardLayout();
		return continue_turn = true;
	}
	
	
	
	
	
	// place a users lair on the board
	// TODO: what if there are no options to place a lair?
	public boolean placeLair(String colour) {
		boolean continue_turn = false;
		int count = 0;

		for (int i = 0; i <= 17 - 1; i++) {
			for (int j = 0; j <= 38 - 1; j++) {
				if (design[i][j] == colour.charAt(0)) {
					// check row above for available lair place
					for (int p = j - 3; p <= j + 3; p++) {
						if (design[i - 1][p] == 'X') {
							design[i - 1][p] = options[count];
							count = count + 1;
						}
					}
					// check row below for available lair place
					for (int q = j - 3; q <= j + 3; q++) {
						if (design[i + 1][q] == 'X') {
							design[i + 1][q] = options[count];
							count = count + 1;
						}
					}
				}
			}
		}
		showBoardLayout();
		System.out.print("Which number option would you like to build your lair at?: ");
		String location_number = interactor.takeInAnswer();

		// place lair at users choice of location
		for (int i = 0; i <= 17 - 1; i++) {
			for (int j = 0; j <= 38 - 1; j++) {
				if (design[i][j] == location_number.charAt(0)) {
					design[i][j] = Character.toUpperCase(colour.charAt(0));
				}
			}
		}

		// replace numbers with X's again
		for (int i = 0; i <= 17 - 1; i++) {
			for (int j = 0; j <= 38 - 1; j++) {

				if (design[i][j] == '1' || design[i][j] == '2' || design[i][j] == '3' || design[i][j] == '4'
						|| design[i][j] == '5') {
					design[i][j] = 'X';
				}
			}
		}

		showBoardLayout();
		return continue_turn = true;
	}
	
	// Function to define starting positions of blue players ships and lairs
	public void setupBluePlayerLocations() {
		design[5][30] = 'B';
		design[6][28] = 'b';
		design[13][12] = 'B';
		design[12][12] = 'b';
	}

	// Function to define starting positions of red players ships and lairs
	public void setupRedPlayerLocations() {
		design[3][12] = 'R';
		design[4][12] = 'r';
		design[10][28] = 'R';
		design[11][30] = 'r';
	}

	// Function to define starting positions of white players ships and lairs
	public void setupWhitePlayerLocations() {
		design[5][6] = 'W';
		design[6][8] = 'w';
		design[12][24] = 'w';
		design[13][24] = 'W';
	}

	// Function to define starting positions of orange players ships and lairs
	public void setupOrangePlayerLocations() {
		design[3][24] = 'O';
		design[4][24] = 'o';
		design[10][8] = 'O';
		design[11][6] = 'o';
	}

	// Setup Board design, place users first ship and lairs on board
	// TODO: put this setup in BoardSetup class 
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
	
	
	private int[] rows1  = {3, 5, 7, 5}; 	
	private int[] rows2  = {3, 1, 3, 5, 7, 5};
	private int[] rows3  = {7, 5, 7, 9, 11, 9}; 
	private int[] rows4  = {13, 11, 9, 11};
	private int[] rows5 = {11, 9, 11, 13, 15, 13};
	
	private int[] cols1 = {12, 12, 9, 6};
	private int[] cols2 = {12, 15, 18, 18, 15, 12};
	private int[] cols3  = {18, 21, 24, 24, 21, 18};
	private int[] cols4  = {24, 24, 27, 30};
	private int[] cols5  = {4, 6, 9, 9, 6, 4};
	private int[] cols6  = {9, 12, 15, 15, 12, 9};
	private int[] cols7 = {21, 24, 27, 27, 24, 21};
	private int[] cols8  = {27, 30, 33, 33, 30, 27};
	 		 // i9 has the same rows as i1
	 // i10 has the same rows as i2
	//private int[] i11rows = {11, 9, 11, 13, 15, 13}; // i11 has the same cols as i3
	//private int[] i12rows = {13, 11, 9, 11}; 		 //i12 has the same cols as i4
	
	
	// Set up the islands
	protected int temp_row;
	protected int temp_col;
	
	public void setUpIslands() {
		//GameManager gameManager = new GameManager();
		Island island1 = new Island(rows1,cols1);
		Island island2 = new Island(rows2,cols2);
		Island island3 = new Island(rows2,cols3);
		Island island4 = new Island(rows1,cols4);
		Island island5 = new Island(rows3,cols5);
		Island island6 = new Island(rows3,cols6);
		Island island7 = new Island(rows3,cols7);
		Island island8 = new Island(rows3,cols8);
		Island island9 = new Island(rows4,cols1);
		Island island10 = new Island(rows5,cols2);
		Island island11 = new Island(rows5,cols3);
		Island island12 = new Island(rows4,cols4);
		
		// For testing 
		//for (int i = 0; i <= rows4.length - 1; i++) {
		//	temp_row = rows4[i];
		//	temp_col = cols4[i];
		//	if(design[temp_row][temp_col]=='X') {
		//		System.out.println("OK. getting " + design[temp_row][temp_col] + "for " + temp_row + " and " + temp_col);
		//	}else {
		//		System.out.println("Error... getting " + design[temp_row][temp_col] + "for " + temp_row + " and " + temp_col);
		//		}	
		//}
		
	}
	
	// Check the dice roll values.... Might be better moving to GameManager later and just checking the board?
	//----- Method: checkDiceRoll-----
	public void checkDiceRoll(Dice dice) {
		diceValue = dice.getDiceRollValue();
//		checkArray(Test1, 'X', Line1); // temp
		if(diceValue==1) {				// Roll a 1
			// Check islands 1, 3 and 10
			// Island 1: {Arrays 3, 5, 7}, Assign: Cutlass
//			checkArray(Test1, 'B', Line1);
//			checkArray(Test1, 'R', Line1);
//			checkArray(Test1, 'W', Line1);
//			checkArray(Test1, 'O', Line1);
			// Island 3: {Arrays 1, 3, 5, 7}, Assign: goat
			// Island 10: {Arrays 9, 11, 13, 15}, Assign: wood
//			checkArray(Test1, 'X', Line1);
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
	
	protected int temp;
	
	public void checkArray(int[] testset, char playerColor, char[] lineArray){
		
		for (int i = 0; i <= testset.length - 1; i++) {
			temp = testset[i];
			if(lineArray[temp]==playerColor) {
				System.out.println("Add a resource for " + playerColor);
			}
		}
	}
	
}