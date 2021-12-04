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
	// -------------------------------
	// ---------- Variables ----------
	// -------------------------------
	protected char[][] design;
	protected char[] options = {'1', '2', '3', '4', '5', '6', '7'};
	protected char[] symbolHolder = {'x','x','x','x', 'x', 'x', 'x'}; // placeholder array
	protected Interactor interactor;
	protected GhostCaptain ghostCaptain;
	protected Island[] islands; // An array for the islands


	protected char[] playerColors = {'B','R', 'W', 'O'}; // Array of player colors 
	protected char playerColor;	// Players color
	protected Player player;	// Selected player
	protected char isGhost; 	// Character to see if the ghost captain is there
	protected int temp_row;		// Selected row value
	protected int temp_col;		// Selected column value
	private String playerName;

	// Lower case: Ship
	// Upper case: Lair

	// TODO: maybe take out dashes
	// Track ship and Lair placements
	// Provide ship/lair placement options to user when building
	// Needs to look after ships and lairs, keeping track of placements 
	// check which user has the most cocotiles: whichever user has the most, they can place their lair on spooky island
	
	
	// -------------------------------
	// ---------- Constructor --------
	// Sets up the layout of the board
	// -------------------------------
	public Board() {
		interactor = new Interactor();
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
	
	//
	public char[][] getBoardDesign() {
		return design;
	}
	
	
	// -----------------------------------------------
	// ---------- Method: showBoardLayout() ----------
	// This method displays the layout of the board
	// -----------------------------------------------
	public void showBoardLayout() {
		interactor.printBoard(this.design);
	}
		
	// function that passes the created ghostcaptin created in boardsetup class and passes it to board class
	public void setGhostCaptain(GhostCaptain ghostCaptain) {
		this.ghostCaptain = ghostCaptain;
	}
	
	// --------------------------------------------------------------------------------------------------------
	// ---------- Method: mostCocotiles() ---------------------------------------------------------------------
	// This method determines which player has the most cocotiles. 
	// If 'G' is no longer at the centre (design[8][18]), the user with the most cocotiles can add a lair here.
	// This needs to be checked every turn by game manager. 
	// --------------------------------------------------------------------------------------------------------
	public void mostCocotiles(ArrayList<Player> players) {
		int max = 0;
		char maxchar = '-';
		if (design[8][18] != 'G') {	// Check that the Ghost captain isn't at the centre of the board
			for (int i = 0; i <= players.size() - 1; i++) {
				// If player has more cocotiles then another need to remove the previous players lair and reduce the number
				if (players.get(i).getCocoTileCount() > max) {
					reduceLairCount(maxchar, players);	
					max = players.get(i).getCocoTileCount();
					maxchar = players.get(i).getColour().charAt(0);
				} else if (players.get(i).getCocoTileCount() == max) {
					reduceLairCount(maxchar, players); 							
					maxchar = '-';
				}
			}
			placeLairMostCocotile(maxchar, players);
		}
		showBoardLayout();
	}
	
	
	// -----------------------------------------------------------------------------------------
	// ---------- Method: reduceLairCount ------------------------------------------------------
	// Finds player and reduces their lair count by 1
	// If a new player has the most cocotiles, remove the previous players lair and reduce the #
	// -----------------------------------------------------------------------------------------
	public void reduceLairCount(char colour, ArrayList<Player> players ) {
		for(int i=0;i<=players.size()-1;i++) {
			if(players.get(i).getColour().charAt(0)==colour) {
				players.get(i).removeLair();
			}
		}
	}
	
	
	// ----------------------------------------------------------------
	// ---------- Method: placeLairMostCocotile -----------------------
	// Places lair on spooky island based on who has the most cocotiles 
	// ----------------------------------------------------------------
	public void placeLairMostCocotile(char player_colour, ArrayList<Player> players) {
		design[8][18] = Character.toUpperCase(player_colour);
		for(int i=0;i<=players.size()-1;i++) {
			if(players.get(i).getColour().charAt(0)== player_colour) {
				players.get(i).addLair();
			}
		}
	}
	
	
	// -----------------------------------------
	// ---------- moveGhostCaptain() -----------
	// Moves the ghost captain to another island
	// -----------------------------------------
	// TODO: cannot move ghost captain to spooky island if a users lairs is there because they have the most cocotiles
	public void moveGhostCaptain() {
		interactor.printMessage("move ghost captain");					// Ask user which island they want to move the ghost captain to.
		showIslandNumberLayout(ghostCaptain.getGhostCaptainLocation());	// Replace centers of island with their island #'s
		String island_number = interactor.takeInAnswer();				// Take in user's response
		ghostCaptain.updateLocationGC(Integer.parseInt(island_number)); // update location of GC
		for (int i = 0; i <= 17 - 1; i++) {
			for (int j = 0; j <= 38 - 1; j++) {
				if (design[i][j] == 'G') {
					design[i][j] = '-';
					possibleLocationGhostCaptain(Integer.parseInt(island_number)); // Place 'G' to specificed location
				}
			}
		}
		interactor.printMessage("GC moved");	// Confirm that the ghost captain has been moved
		showBoardLayout();						// Show new layout of the board
	}
	
	
	// ------------------------------------------------------------
	// ---------- Method: showIslandNumberLayout ------------------
	// Displays board with the corresponding island numbers to user 
	// ------------------------------------------------------------
	public void showIslandNumberLayout(int current_GC_location) {
		for (int i = 0; i <= 17 - 1; i++) {
			for (int j = 0; j <= 38 - 1; j++) {
				// Make sure user isn't trying to place ghost captain on top of someone's existing lair on spooky island or ghost captain
				if (design[i][j]!='B' && design[i][j] != 'R' && design[i][j] != 'W' && design[i][j] != 'O' && design[i][j] != 'G') {
					switch (i) {
					case 4:
						if (j == 9) {
							design[4][9] = '1'; // 1
						} else if (j == 15) {
							design[4][15] = '2'; // 2
						} else if (j == 21) {
							design[4][21] = '3'; // 3
						} else if (j == 27) {
							design[4][27] = '4'; // 4
						}
						break;
					case 8:
						if (j == 6) {
							design[8][6] = '5'; // 5
						} else if (j == 12) {
							design[8][12] = '6'; // 6
						} else if (j == 24) {
							design[8][24] = '7'; // 7
						} else if (j == 30) {
							design[8][30] = '8'; // 8
						} else if (j == 18) {
							design[8][18] = '1'; //13
							design[8][19] = '3'; //13
						} 
						break;
					case 12:
						if (j == 9) {
							design[12][9] = '9'; // 9
						} else if (j == 15) {
							design[12][15] = '1'; //10
							design[12][16] = '0'; // 10
						} else if (j == 21) {
							design[12][21] = '1'; //11
							design[12][22] = '1'; //11
						} else if (j == 27) {
							design[12][27] = '1'; //12
							design[12][28] = '2';//12
						} 
						break;
					}
				}
			}
		}
		System.out.println("Island Number Layout:");
		showBoardLayout();	// Print out board

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
	
	
	// ----------------------------------------------------------------------------
	// ---------- Method: possibleLocationGhostCaptain ----------------------------
	// Outline possible locations for the ghost captain (the centre of each island)
	// ----------------------------------------------------------------------------
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
	
	
	// ------------------------------------------------------------------------
	// ---------- Method: placeShip -------------------------------------------
	// Place a users ship on the board 
	// Search for upper case of colour to search for lairs to connect ships to.
	// ------------------------------------------------------------------------
	// TODO: what happens when no ships are available
	// NOTE FROM MG TO EP: could be being stupid cuz im tired, but the 2 for loops are the same. could you not just have 1 for loop with the 2 if statements in it?
	public boolean placeShip(String colour) {
		boolean continue_turn = false;
		int count = 0;
		for (int i = 0; i <= 17 - 1; i++) {
			for (int j = 0; j <= 38 - 1; j++) {
				if (design[i][j] == Character.toUpperCase(colour.charAt(0))) {
					for (int p = j - 3; p <= j + 3; p++) {	// Check row above for available ship place
						if (design[i - 1][p] == '\\' || design[i - 1][p] == '/' || design[i - 1][p] == '|' ) {
							symbolHolder[count] = design[i - 1][p];
							design[i - 1][p] = options[count];
							count = count + 1;
						}
					}
					for (int q = j - 3; q <= j + 3; q++) {	// Check row below for available ship place
						if (design[i + 1][q] == '\\' || design[i + 1][q] == '/' || design[i + 1][q] == '|') {
							symbolHolder[count] = design[i+1][q];
							design[i + 1][q] = options[count];
							count = count + 1;
						}
					}
				}
			}
		}
		
		// Let user decide where they would like to build their ship
		showBoardLayout();
		System.out.print("Which number option would you like to build your ship at?: "); // TODO: move to interactor class
		String location_number = interactor.takeInAnswer();
		// TODO: need to make sure players can only choose numbers and not letters, cause it will replace letters with lairs

		
		// Place ship at users choice of location
		// NOTE FROM MARY TO EMMA: once again, these 2 double 4 loops are the same - could we put the 2 double for loops, with 2 if statements in them?
		for (int i = 0; i <= 17 - 1; i++) {
			for (int j = 0; j <= 38 - 1; j++) {
				if (design[i][j] == location_number.charAt(0)) {
					design[i][j] = colour.charAt(0);
				}
			}
		}

		// Replace numbers with slash's or lines again
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
	

	// ---------------------------------------
	// ---------- Method: placeLair ----------
	// Place a users lair on the board
	// ---------------------------------------
	// NOTE FROM MAZ TO EMA: Same comment as before with the for loops
	// TODO: what if there are no options to place a lair?
	public boolean placeLair(String colour) {
		boolean continue_turn = false;
		int count = 0;
		for (int i = 0; i <= 17 - 1; i++) {
			for (int j = 0; j <= 38 - 1; j++) {
				if (design[i][j] == colour.charAt(0)) {
					for (int p = j - 3; p <= j + 3; p++) { // Check row above for available lair place
						if (design[i - 1][p] == 'X') {
							design[i - 1][p] = options[count];
							count = count + 1;
						}
					}
					for (int q = j - 3; q <= j + 3; q++) { // Check row below for available lair place
						if (design[i + 1][q] == 'X') {
							design[i + 1][q] = options[count];
							count = count + 1;
						}
					}
				}
			}
		}
		
		// Let user determine where they want to place their lair
		showBoardLayout();
		System.out.print("Which number option would you like to build your lair at?: ");
		String location_number = interactor.takeInAnswer();

		// TODO: need to make sure players can only choose numbers and not letters, cause it will replace letters with lairs
		// place lair at users choice of location
		// NOTE FROM MAZ: Same for loop situation. 
		// Place lair at users choice of location
		for (int i = 0; i <= 17 - 1; i++) {
			for (int j = 0; j <= 38 - 1; j++) {
				if (design[i][j] == location_number.charAt(0)) {
					design[i][j] = Character.toUpperCase(colour.charAt(0));
				}
			}
		}

		// Replace numbers with X's again
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
	

	// method to set the islands created by the boardsetup class
	public void setIslands(Island[] islands) {
		this.islands = islands;
	}
	//-----------------------------------------------
	//---------- Method: getIslands() ---------------
	// This method returns an array of the 12 islands
	//-----------------------------------------------
	public Island[] getIslands() {
		return islands;
	}
	
	
	//----------------------------------------------------------------------------------------------------
	//---------- Method: checkDiceRoll() -----------------------------------------------------------------
	// This method checks islands according to the value of the dice roll, and calls the checkArray method
	//----------------------------------------------------------------------------------------------------
	// TODO: Might be better moving to GameManager later and just checking the board?
	public void checkDiceRoll(int diceValue, Island[] islands, ArrayList<Player> players) {
		if(diceValue==1) {			//---------- Roll a 1 - Islands 1, 3 and 10 -----------					
			checkArray(islands[0], playerColors, players, Resources.Cutlasses, design[4][9]);	// Island 1, Assign: Cutlasses
			checkArray(islands[2], playerColors, players, Resources.Goats, design[4][21]);		// Island 3, Assign: goat
			checkArray(islands[9], playerColors, players, Resources.Wood, design[12][15]);		// Island 10, Assign: wood
		} else if(diceValue==2) {	//---------- Roll a 2 - Islands 2, 4, and 11 ----------
			checkArray(islands[1], playerColors, players, Resources.Wood, design[4][15]);		// Island 2, Assign: wood
			checkArray(islands[3], playerColors, players, Resources.Molasses, design[4][27]);	// Island 4, Assign: molasses
			checkArray(islands[10], playerColors, players, Resources.Goats, design[12][21]);	// Island 11, Assign: goat
		} else if(diceValue==3) {	//---------- Roll a 3 - islands 5 and 7 ---------------
			checkArray(islands[4], playerColors, players, Resources.Wood, design[8][6]);		// Island 5, Assign: wood
			checkArray(islands[6], playerColors, players, Resources.Gold, design[8][24]);		// Island 7, Assign: gold
		} else if(diceValue==4) {	//---------- Roll a 4 - islands 9 and 12 --------------
			checkArray(islands[8], playerColors, players, Resources.Cutlasses, design[12][9]);	// Island 9, Assign: Cutlass
			checkArray(islands[11], playerColors, players, Resources.Molasses, design[12][27]);	// Island 12, Assign: molasses
		} else if(diceValue==5) {	//---------- Roll a 5 - islands 6 and 8 ---------------
			checkArray(islands[5], playerColors, players, Resources.Gold, design[8][12]);		// Island 6, Assign: gold
			checkArray(islands[7], playerColors, players, Resources.Goats, design[8][30]);		// Island 8, Assign: goat
		} else {					//---------- Roll a 6 - ghost captain -----------------
			moveGhostCaptain();
		}
	}
	
	
	//-----------------------------------------------------------------------------------------------
	//---------- Method: checkArray() ---------------------------------------------------------------
	// This method checks what players have a Lair touching an island and assigns resources as needed
	//-----------------------------------------------------------------------------------------------
	// QUESTION FROM MARY TO EMMA: should this be in 'island' class or 'board' class do you think?
	public void checkArray(Island island, char[] playerColors, ArrayList<Player> players, Resources resource, char isGhost){
		if(isGhost != 'G') {
			for (int j = 0; j <= players.size() - 1; j++) {
				playerColor = playerColors[j];
				player = players.get(j);
				for (int i = 0; i <= island.getColumn().length - 1; i++) {
					temp_row = island.getRow()[i];
					temp_col = island.getColumn()[i];
					if(design[temp_row][temp_col]==playerColor) {
						player.addResource(resource, 1);
						playerName = player.getName();
						interactor.printMessage("Island check: success", resource, playerName);
						System.out.println(player.viewPocket());
					}
				}
			}
		} else {	// If the ghost captain is on an island, no resources are distributed from it
			interactor.printMessage("Island check: ghost captain");
		}
	}	
}