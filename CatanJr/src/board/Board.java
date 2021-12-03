package board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


import main.Interactor;
import player.Player;
import resources.Resources;
import gameplay.Dice;
import setup.PlayerSetup;
import player.Player;

public class Board {
	protected char[][] design;
	protected char[] options = {'1', '2', '3', '4', '5', '6', '7'};
	protected char[] symbolHolder = {'x','x','x','x', 'x', 'x', 'x'}; // placeholder array
	Interactor interactor = new Interactor();
	protected GhostCaptain ghostCaptain;
	protected char[] islandNumbers = {'1', '2', '3', '4', '5', '6', '7','8', '9', '0'};


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
				{ '-', '-', '-', 'X','-', '-', '-', '-', '-', 'X','-','-','-','-', '-', 'X', '-', '-', '-', '-', '-', 'X', '-', '-','-','-','-', 'X', '-', '-', '-','-','-','X','-','-','-', '-','\n' },
				{ '-', '-', '-', '|','-', '-', '-', '-', '-', '|','-','-','-','-', '-', '|', '-', '-', '-', '-', '-', '|', '-', '-','-','-','-', '|', '-', '-', '-','-','-','|','-','-','-', '-','\n' },
				{ '-', '-', '-', 'X','-', '-', '-', '-', '-', 'X','-','-','-','-', '-', 'X', '-', '-', '-', '-', '-', 'X', '-', '-','-','-','-', 'X', '-', '-', '-','-','-','X','-','-','-', '-','\n' },
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
	// when game is setup the ghost captain is setup in centre of the board
	public void setupGhostCaptain() {
		ghostCaptain = new GhostCaptain(13); // setup ghost chaptain in spooky island (island = 13)
		design[8][18] = 'G';
	}
	
	
	// this needs to be checked every turn by game manager
	// if G is no longer at design[8][18], then user with the most cocotiles can add a lair here
	// this determines which player has the most cocotiles and places a lair of theres in the centre if the ghost captain is not there
	public void mostCocotiles(ArrayList<Player> players) {

		int max = 0;
		char maxchar = '-';
		if (design[8][18] != 'G') { // if the ghost captain isnt in the centre island there is potential for a player to place their lair their

			for (int i = 0; i <= players.size() - 1; i++) {
				if (players.get(i).getCocoTileCount() > max) {
					reduceLairCount(maxchar, players); // if player has more cocotiles then another need to remove the
														// previous players lair and reduce the number
					max = players.get(i).getCocoTileCount();
					maxchar = players.get(i).getColour().charAt(0);
				} else if (players.get(i).getCocoTileCount() == max) {
					reduceLairCount(maxchar, players); // if player has more cocotiles then another need to remove the
														// previous players lair and reduce the number
					maxchar = '-';
				}
			}
			placeLairMostCocotile(maxchar, players);
		}
		showBoardLayout();

//		System.out.print("Lair count player 1:    "+ players.get(0).getLairCount()+"\n");
//		System.out.print("Cocotile count player 1:"+ players.get(0).getCocoTileCount()+"\n");
//		System.out.print("Lair count player 2:    "+ players.get(1).getLairCount()+"\n");
//		System.out.print("Cocotile count player 2:"+ players.get(1).getCocoTileCount()+"\n");
//		System.out.print("Lair count player 3:    "+ players.get(2).getLairCount()+"\n");
//		System.out.print("Cocotile count player 3:"+ players.get(2).getCocoTileCount()+"\n");

	}
	
	// find player and reduce their lair count by one
	 // if player has more cocotiles then another need to remove the previous players lair and reduce the number
	public void reduceLairCount(char colour, ArrayList<Player> players ) {
		for(int i=0;i<=players.size()-1;i++) {
			if(players.get(i).getColour().charAt(0)==colour) {
				players.get(i).removeLair();
			}
		}
		
	}
	
	
	// method to place the lair on spooky island based on who has the most cocotiles
	public void placeLairMostCocotile(char player_colour, ArrayList<Player> players) {
		design[8][18] = Character.toUpperCase(player_colour);
		for(int i=0;i<=players.size()-1;i++) {
			if(players.get(i).getColour().charAt(0)== player_colour) {
				players.get(i).addLair();
			}
		}
	}
	
	
	
	// Method to move ghost captain to another island
	// TODO: cannot move ghost captain to spooky island if a users lairs is there because they have the most cocotiles
	public void moveGhostCaptain() {
		// ask user which island they want to move the ghost captain to.
		interactor.printMessage("move ghost captain");
		// replace centers of island with their island numbers
		showIslandNumberLayout(ghostCaptain.getGhostCaptainLocation());
		String island_number = interactor.takeInAnswer();
		ghostCaptain.updateLocationGC(Integer.parseInt(island_number)); // update location of GC
		for (int i = 0; i <= 17 - 1; i++) {
			for (int j = 0; j <= 38 - 1; j++) {
				if (design[i][j] == 'G') {
					design[i][j] = '-';
					possibleLocationGhostCaptain(Integer.parseInt(island_number)); // Place 'G' to specificed location
				}
			}
		}
		interactor.printMessage("GC moved");
		showBoardLayout();
	}
	
	
	// method that shows the user the board with the corresponding island numbers
	public void showIslandNumberLayout(int current_GC_location) {
		design[4][9] = '1'; // 1
		design[4][15] = '2'; // 2
		design[4][21] = '3'; // 3
		design[4][27] = '4'; // 4
		design[8][6] = '5'; // 5
		design[8][12] = '6'; // 6
		design[8][24] = '7'; // 7
		design[8][30] = '8'; // 8
		design[12][9] = '9'; // 9
		// 10
		design[12][15] = '1';
		design[12][16] = '0'; // 10

		// 11
		design[12][21] = '1'; 
		design[12][22] = '1'; 

		// 12
		design[12][27] = '1'; 
		design[12][28] = '2'; 

		// 13
		design[8][18] = '1'; 
		design[8][19] = '3'; 

		System.out.println("Island Number Layout:");

		for (int i = 0; i <= 17 - 1; i++) {
			for (int j = 0; j <= 38 - 1; j++) {
				System.out.print(design[i][j]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");

		// revert back to map without numbers once the numbered map has been shown
		possibleLocationGhostCaptain(current_GC_location); // Place 'G' to specificed location
		// replace numbers with dashs
		for (int i = 0; i <= 17 - 1; i++) {
			for (int j = 0; j <= 38 - 1; j++) {
				if (design[i][j] == '1' || design[i][j] == '2' || design[i][j] == '3' || design[i][j] == '4'
						|| design[i][j] == '5' || design[i][j] == '6' || design[i][j] == '7' || design[i][j] == '8'
						|| design[i][j] == '9' || design[i][j] == '0') {
					design[i][j] = '-';
				}
			}
		}

	}
	
	// method that outlines the possilbe locations of ghost captains ( the centre of each island)
	public void possibleLocationGhostCaptain(int location) {
		switch (location) {
		case 1:
			design[4][9] = 'G';
			break;
		case 2:
			design[4][15] = 'G';
			break;
		case 3:
			design[4][21] = 'G';
			break;
		case 4:
			design[4][27] = 'G';
			break;
		case 5:
			design[8][6] = 'G';
			break;
		case 6:
			design[8][12] = 'G';
			break;
		case 7:
			design[8][24] = 'G';
			break;
		case 8:
			design[8][30] = 'G';
			break;
		case 9:
			design[12][9] = 'G';
			break;
		case 10:
			design[12][15] = 'G';
			break;
		case 11:
			design[12][21] = 'G';
			break;
		case 12:
			design[12][27] = 'G';
			break;
		case 13:
			design[8][18] = 'G';
			break;
		}
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
		design[10][28] = 'r';
		design[11][30] = 'R';
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
		design[10][8] = 'o';
		design[11][6] = 'O';
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
	
	private int[] rows1 = {3, 5, 7, 5}; 	
	private int[] rows2 = {3, 1, 3, 5, 7, 5};
	private int[] rows3 = {7, 5, 7, 9, 11, 9}; 
	private int[] rows4 = {13, 11, 9, 11};
	private int[] rows5 = {11, 9, 11, 13, 15, 13};
	
	private int[] cols1 = {12, 12, 9, 6};
	private int[] cols2 = {12, 15, 18, 18, 15, 12};
	private int[] cols3 = {18, 21, 24, 24, 21, 18};
	private int[] cols4 = {24, 24, 27, 30};
	private int[] cols5 = {3, 6, 9, 9, 6, 3};	// the two 4's were changed to 3's - this hasn't been tested yet
	private int[] cols6 = {9, 12, 15, 15, 12, 9};
	private int[] cols7 = {21, 24, 27, 27, 24, 21};
	private int[] cols8 = {27, 30, 33, 33, 30, 27};
	
	
	// Set up the islands
	protected int temp_row;
	protected int temp_col;
	protected Island[] islands; // An array for the islands
	
	public void setUpIslands() {
		Island island1  = new Island(rows1,cols1);
		Island island2  = new Island(rows2,cols2);
		Island island3  = new Island(rows2,cols3);
		Island island4  = new Island(rows1,cols4);
		Island island5  = new Island(rows3,cols5);
		Island island6  = new Island(rows3,cols6);
		Island island7  = new Island(rows3,cols7);
		Island island8  = new Island(rows3,cols8);
		Island island9  = new Island(rows4,cols1);
		Island island10 = new Island(rows5,cols2);
		Island island11 = new Island(rows5,cols3);
		Island island12 = new Island(rows4,cols4);
		
		// Add the islands to the island array
		islands = new Island[] {island1, island2, island3, island4, island5, island6, island7, island8,island9, island10, island11, island12};
		
		//
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
	
	public Island[] getIslands() {
		return islands;
	}
	
	
	

	
	//-----------------------------------------------------
	//---------- Method: checkDiceRoll() ------------------
	// This method checks islands according to the value  
	// of the dice roll, and calls the checkArray method
	//-----------------------------------------------------
	// TODO: Might be better moving to GameManager later and just checking the board?
	// TODO: add in the ghost captain part 
	public void checkDiceRoll(int diceValue, Island[] islands, ArrayList<Player> players) {
		//---------- Roll a 1 - Islands 1, 3 and 10 ----------
		if(diceValue==1) {						
			checkArray(islands[0], playerColors, players, Resources.Cutlasses);	// Island 1, Assign: Cutlass
			checkArray(islands[2], playerColors, players, Resources.Goats);	// Island 3, Assign: goat
			checkArray(islands[9], playerColors, players, Resources.Wood);	// Island 10, Assign: wood
		}
		
		//---------- Roll a 2 - Islands 2, 4, and 11 ----------
		else if(diceValue==2) {	
			checkArray(islands[1], playerColors, players, Resources.Wood);	// Island 2, Assign: wood
			checkArray(islands[3], playerColors, players, Resources.Molasses);	// Island 4, Assign: molasses
			checkArray(islands[10], playerColors, players, Resources.Goats);	// Island 11, Assign: goat
		}
		
		//---------- Roll a 3 - islands 5 and 7 ---------------
		else if(diceValue==3) {			// Roll a 3 - islands 5 and 7
			checkArray(islands[4], playerColors, players, Resources.Wood);	// Island 5, Assign: wood
			checkArray(islands[6], playerColors, players, Resources.Gold);	// Island 7, Assign: gold
		}
		
		//---------- Roll a 4 - islands 9 and 12 --------------
		else if(diceValue==4) {	
			checkArray(islands[8], playerColors, players, Resources.Cutlasses);	// Island 9, Assign: Cutlass
			checkArray(islands[11], playerColors, players, Resources.Molasses);	// Island 12, Assign: molasses
		}
		
		//---------- Roll a 5 - islands 6 and 8 ---------------
		else if(diceValue==5) {	
			checkArray(islands[5], playerColors, players, Resources.Gold);	// Island 6, Assign: gold
			checkArray(islands[7], playerColors, players, Resources.Goats);	// Island 8, Assign: goat
		}
		else {							// Roll a 6
			// Ghost captain
		}
		
	}
	
	
	//protected int temp;
	protected char[] playerColors = {'B','R', 'W', 'O'};
	protected char playerColor;
	protected Player player;
	
	//-----------------------------------------------------
	//---------- Method: checkArray() ---------------------
	// This method checks what players have a Lair touching 
	// an island and assigns resources as needed
	//-----------------------------------------------------
	//public void checkArray(int[] testset, char playerColor, char[] lineArray){
	public void checkArray(Island island, char[] playerColors, ArrayList<Player> players, Resources resource){
		for (int j = 0; j <= players.size() - 1; j++) {
			playerColor = playerColors[j];
			player = players.get(j);
			for (int i = 0; i <= island.getColumn().length - 1; i++) {
				temp_row = island.getRow()[i];
				temp_col = island.getColumn()[i];
				if(design[temp_row][temp_col]==playerColor) {
					player.addResource(resource, 1);
					System.out.println("Add a " + resource + " for player " + player + " with color " + playerColor);
					System.out.println(player.viewPocket());
				}
			}
		}
	}
	
}