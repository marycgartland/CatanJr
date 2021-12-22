package board;

//----------------------------------------------------------------------------------------
// This is a Singleton class to represent the board for the game. It sets up the board 
// design, and provides methods to view and change the board. It also involves methods that 
// deal with interactions with the board such as dealing with the movement of the ghost
// captain, the placement of lairs and ships, and resource distribution.
//----------------------------------------------------------------------------------------

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import main.Interactor;
import player.Player;
import resources.Resources;

// -------------------------------------------------------------------------------------------------------
// ---------- A Singleton class for the Board ------------------------------------------------------------
// -------------------------------------------------------------------------------------------------------
public class Board {
	// -------------------------------------------------------------------------------------------------------
	// ---------- Variables ----------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------
	protected char[][] design;													// Matrix board design 
	protected String[] options_string = { "1", "2", "3", "4", "5", "6", "7" }; 	// Option #'s to present to user
	protected char[] symbolHolder = { 'x', 'x', 'x', 'x', 'x', 'x', 'x' }; 		// Placeholder array
	protected Interactor interactor;											// Interactor
	protected GhostCaptain ghostCaptain;										// Ghost captain
	protected Island[] islands; 												// Array to hold islands
	protected String[] island_numbers = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13" };
	private static Board instance = new Board();
	

	// -------------------------------------------------------------------------------------------------------
	// ---------- Constructor --------------------------------------------------------------------------------
	// Sets up the layout of the board
	// -------------------------------------------------------------------------------------------------------
	private Board() {
		interactor = new Interactor();
		design = new char[][] {
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
						' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
						'\n' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ',
						' ', 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
						'\n' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '/', ' ', ' ', ' ', '\\', ' ', '/',
						' ', ' ', ' ', '\\', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
						'\n' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ',
						' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
						'\n' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ',
						' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
						'\n' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ',
						' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
						'\n' },
				{ ' ', ' ', ' ', ' ', '/', ' ', ' ', ' ', '\\', ' ', '/', ' ', ' ', ' ', '\\', ' ', '/', ' ', ' ', ' ',
						'\\', ' ', '/', ' ', ' ', ' ', '\\', ' ', '/', ' ', ' ', ' ', '\\', ' ', ' ', ' ', ' ', ' ',
						'\n' },
				{ ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ',
						' ', 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ',
						'\n' },
				{ ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ',
						' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ',
						'\n' },
				{ ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ',
						' ', 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ',
						'\n' },
				{ ' ', ' ', ' ', ' ', '\\', ' ', ' ', ' ', '/', ' ', '\\', ' ', ' ', ' ', '/', ' ', '\\', ' ', ' ', ' ',
						'/', ' ', '\\', ' ', ' ', ' ', '/', ' ', '\\', ' ', ' ', ' ', '/', ' ', ' ', ' ', ' ', ' ',
						'\n' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ',
						' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
						'\n' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ',
						' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
						'\n' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ',
						' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
						'\n' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '\\', ' ', ' ', ' ', '/', ' ', '\\',
						' ', ' ', ' ', '/', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
						'\n' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ',
						' ', 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
						'\n' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
						' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',
						'\n' } };
	}

	// -------------------------------------------------------------------------------------------------------
	// ---------- Method: getBoardDesign() -------------------------------------------------------------------
	// This method returns the board design array
	// -------------------------------------------------------------------------------------------------------
	public char[][] getBoardDesign() {
		return design;
	}

	// -------------------------------------------------------------------------------------------------------
	// ---------- Method: showBoardLayout() ------------------------------------------------------------------
	// This method displays the layout of the board
	// -------------------------------------------------------------------------------------------------------
	public void showBoardLayout() {
		interactor.printBoard(this.design);
	}

	// -------------------------------------------------------------------------------------------------------
	// ---------- Method: setGhostCaptain() ------------------------------------------------------------------
	// This method assigns the created ghostCaptain to its variable
	// -------------------------------------------------------------------------------------------------------
	public void setGhostCaptain(GhostCaptain ghostCaptain) {
		this.ghostCaptain = ghostCaptain;
	}

	// -------------------------------------------------------------------------------------------------------
	// ---------- Method: mostCocotiles() --------------------------------------------------------------------
	// Determines which player has the most cocotiles. If 'G' is no longer at the centre (design[8][18]), the 
	// user with the most cocotiles can add a lair there.
	// -------------------------------------------------------------------------------------------------------
	public void mostCocotiles(ArrayList<Player> players) {
		int max = 0;
		char maxchar = ' ';
		if (design[8][18] != 'G') { 									// Check that the  GC isn't at centre of board
			for (int i = 0; i <= players.size() - 1; i++) {
				// ---- If player has >= cocotiles to another - remove previous players lair and reduce lair count ----
				if (players.get(i).getCocoTileCount() > max) {			// If player has most cocotiles - lair to center
					reduceLairCount(maxchar, players);
					max = players.get(i).getCocoTileCount();
					maxchar = players.get(i).getColour().charAt(0);
				} else if (players.get(i).getCocoTileCount() == max) {	// If no player has max, no lair at center
					reduceLairCount(maxchar, players);
					maxchar = ' ';
				}
			}
			placeLairMostCocotile(maxchar, players);					// Place lair for player with most cocotiles
		}
		showBoardLayout();												// Display new board layout for users
	}

	// -------------------------------------------------------------------------------------------------------
	// ---------- Method: reduceLairCount --------------------------------------------------------------------
	// Finds player and reduces their lair count by 1. If a new player has the most cocotiles, remove the 
	// previous players lair and reduce the #
	// -------------------------------------------------------------------------------------------------------
	public void reduceLairCount(char colour, ArrayList<Player> players) {
		for (int i = 0; i <= players.size() - 1; i++) {
			if (players.get(i).getColour().charAt(0) == colour) {
				players.get(i).removeLair();
			}
		}
	}

	// -------------------------------------------------------------------------------------------------------
	// ---------- Method: placeLairMostCocotile --------------------------------------------------------------
	// Places lair on spooky island based on who has the most cocotiles
	// -------------------------------------------------------------------------------------------------------
	public void placeLairMostCocotile(char player_colour, ArrayList<Player> players) {
		design[8][18] = Character.toUpperCase(player_colour);
		for (int i = 0; i <= players.size() - 1; i++) {
			if (players.get(i).getColour().charAt(0) == player_colour) {
				players.get(i).addLair();
			}
		}
	}

	// -------------------------------------------------------------------------------------------------------
	// ---------- Method: moveGhostCaptain() -----------------------------------------------------------------
	// Moves the ghost captain to another island
	// -------------------------------------------------------------------------------------------------------
	public String moveGhostCaptain(String number) {
		String message = null;
		boolean integer_given = false;
		while (!integer_given) {
			List<String> comparison_list = Arrays.asList(island_numbers);
			if (comparison_list.contains(number)) {									// If input is a valid island # ...
				ghostCaptain.updateLocationGC(Integer.parseInt(number)); 			// Update GC location
				for (int i = 0; i <= 17 - 1; i++) {
					for (int j = 0; j <= 38 - 1; j++) {
						if (design[i][j] == 'G') {
							design[i][j] = ' ';
							possibleLocationGhostCaptain(Integer.parseInt(number)); // Place 'G' at specificed location
						}
					}
				}
				message="GC moved";													// Confirm GC has been moved
				showBoardLayout();													// Display new board layout
				integer_given = true; 												// End loop	
			} 
			else {																// If island is invalid ...
				message = "invalid island";
				integer_given = false;
				break;
			}
		}
		return message;
	}

	// -------------------------------------------------------------------------------------------------------
	// ---------- Method: showIslandNumberLayout -------------------------------------------------------------
	// Displays board with the corresponding island numbers to user
	// -------------------------------------------------------------------------------------------------------
	public void showIslandNumberLayout(int current_GC_location) {
		for (int i = 0; i <= 17 - 1; i++) {
			for (int j = 0; j <= 38 - 1; j++) {
				// --- Make sure user isn't trying to place GC on top of an existing lair or GC on spooky island ---
				if (design[i][j] != 'B' && design[i][j] != 'R' && design[i][j] != 'W' && design[i][j] != 'O'
						&& design[i][j] != 'G') {
					switch (i) {
					// --- Print out island numbers within each island ---
					case 4:
						if (j == 9) {
							design[4][9] = '1'; 	// 1
						} else if (j == 15) {
							design[4][15] = '2'; 	// 2
						} else if (j == 21) {
							design[4][21] = '3'; 	// 3
						} else if (j == 27) {
							design[4][27] = '4'; 	// 4
						}
						break;
					case 8:
						if (j == 6) {
							design[8][6] = '5'; 	// 5
						} else if (j == 12) {
							design[8][12] = '6'; 	// 6
						} else if (j == 24) {
							design[8][24] = '7'; 	// 7
						} else if (j == 30) {
							design[8][30] = '8'; 	// 8
						} else if (j == 18) {
							design[8][18] = '1'; 	// 13
							design[8][19] = '3'; 	// 13
						}
						break;
					case 12:
						if (j == 9) {
							design[12][9] = '9'; 	// 9
						} else if (j == 15) {
							design[12][15] = '1'; 	// 10
							design[12][16] = '0'; 	// 10
						} else if (j == 21) {
							design[12][21] = '1'; 	// 11
							design[12][22] = '1'; 	// 11
						} else if (j == 27) {
							design[12][27] = '1';	// 12
							design[12][28] = '2';	// 12
						}
						break;
					}
				}
			}
		}
		// Print out board with number layout
		interactor.printMessage("Number layout");
		showBoardLayout(); // Print out board

		// revert back to map without numbers once the numbered map has been shown
		possibleLocationGhostCaptain(current_GC_location); // Place 'G' to specificed location
		// replace numbers with spaces
		for (int i = 0; i <= 17 - 1; i++) {
			for (int j = 0; j <= 38 - 1; j++) {
				if (design[i][j] == '1' || design[i][j] == '2' || design[i][j] == '3' || design[i][j] == '4'
						|| design[i][j] == '5' || design[i][j] == '6' || design[i][j] == '7' || design[i][j] == '8'
						|| design[i][j] == '9' || design[i][j] == '0') {
					design[i][j] = ' ';

				}
			}
		}
	}

	// -------------------------------------------------------------------------------------------------------
	// ---------- Method: possibleLocationGhostCaptain -------------------------------------------------------
	// Outline possible locations for the ghost captain (the centre of each island)
	// -------------------------------------------------------------------------------------------------------
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
	
	// -------------------------------------------------------------------------------------------------------
	// ---------- Method: placeLairShip ----------------------------------------------------------------------
	// ---- Place a users lair or ship on the board ----------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------
	public boolean placeLairShip(Player player, String shiplair_choice) {
		boolean continue_turn = false;
		boolean integer_given = false;
		int count = 0;
		char ship_lair_option = ' ';
		for (int i = 0; i <= 17 - 1; i++) {
			for (int j = 0; j <= 38 - 1; j++) {
				// ----- To build a ship, must search for available lair positions ----
				if (shiplair_choice.equals("S")) {
					ship_lair_option = Character.toUpperCase(player.getColour().charAt(0));
				}
				// ----- To build a lair, must search for available ship positions ----
				else if (shiplair_choice.equals("L")) {
					ship_lair_option = player.getColour().charAt(0);
				}
				if (design[i][j] == ship_lair_option) {
					for (int p = j - 3; p <= j + 3; p++) {
						// ------ Place a ship -----------------------------------
						if (shiplair_choice.equals("S")) {
							// ----- Check row above for available ship place ---------------
							if (design[i - 1][p] == '\\' || design[i - 1][p] == '/' || design[i - 1][p] == '|') {
								symbolHolder[count] = design[i - 1][p];
								design[i - 1][p] = options_string[count].charAt(0);
								count = count + 1;
							}
							// ----- Check row below for available ship place ---------------
							else if (design[i + 1][p] == '\\' || design[i + 1][p] == '/' || design[i + 1][p] == '|') {
								symbolHolder[count] = design[i + 1][p];
								design[i + 1][p] = options_string[count].charAt(0);
								count = count + 1;
							}
						}
						// ------ Place a lair -----------------------------------
						else if (shiplair_choice.equals("L")) {
							// ----- Check row above for available lair place ---------------
							if (design[i - 1][p] == 'X') {
								design[i - 1][p] = options_string[count].charAt(0);
								count = count + 1;
							}
							// ----- Check row below for available lair place ---------------
							else if (design[i + 1][p] == 'X') {
								design[i + 1][p] = options_string[count].charAt(0);
								count = count + 1;
							}
						}
					}
				}
			}
		}
		// ----- If options available, let user decide where to build their lair/ship --------
		if (count > 0) {
			while (!integer_given) {
				// ----- Interact with user to determine lair/ship placement -----------------
				showBoardLayout();
				interactor.printMessage("build ship/lair option", shiplair_choice);
				String location_number = interactor.takeInAnswer();
				// ----- If valid input, place ship, take out cost ----------------------
				if (Character.isDigit(location_number.charAt(0))) {
					if (Integer.parseInt(location_number) > 0 && Integer.parseInt(location_number) <= count) {
						for (int i = 0; i <= 17 - 1; i++) {
							for (int j = 0; j <= 38 - 1; j++) {
								if (shiplair_choice.equals("S")) {
									if (design[i][j] == location_number.charAt(0)) {
										// ----- Place ship -------
										design[i][j] = player.getColour().charAt(0);
										interactor.printMessage("ship built");
										// ----- Take out cost -----
										player.removeResource(Resources.Wood, 1);
										player.removeResource(Resources.Goats, 1);
									} else if (design[i][j] == '1' || design[i][j] == '2' || design[i][j] == '3'
											|| design[i][j] == '4' || design[i][j] == '5') { // replace numbers with
																								// slashs
										design[i][j] = symbolHolder[Character.getNumericValue(design[i][j]) - 1];
									}
								} else if (shiplair_choice.equals("L")) {
									if (design[i][j] == location_number.charAt(0)) {
										// ----- Place lair -----------------------------------------
										design[i][j] = Character.toUpperCase(player.getColour().charAt(0));
										// ----- Take out cost (wood, custlass, molasses,goat) -------
										player.removeResource(Resources.Wood, 1);
										player.removeResource(Resources.Goats, 1);
										player.removeResource(Resources.Cutlasses, 1);
										player.removeResource(Resources.Molasses, 1);
										player.addLair(); // Increment the players lair count
										interactor.printMessage("lair built");
									} else if (design[i][j] == '1' || design[i][j] == '2' || design[i][j] == '3'
											|| design[i][j] == '4' || design[i][j] == '5') { // replace numbers with X's
																								// again
										design[i][j] = 'X';
									}
								}
							}
						}
						showBoardLayout();
						integer_given = true;
					} else { // If user input is invalid, cannot place lair/ship
						interactor.printMessage("invalid option");
						integer_given = false;
					}
				} else { // If user input is invalid, cannot place lair/ship
					interactor.printMessage("invalid option");
					integer_given = false;
				}
			}
		} else { // If no space for a lair/ship, cannot place it
			interactor.printMessage("no ships/lairs", shiplair_choice);
		}
		return continue_turn = true;
	}

	// -------------------------------------------------------------------------------------------------------
	//  ---------- Method: setIslands()-----------------------------------------------------------------------
	// Set the islands created by the boardsetup class
	// -------------------------------------------------------------------------------------------------------
	public void setIslands(Island[] islands) {
		this.islands = islands;
	}

	// -------------------------------------------------------------------------------------------------------
	// ---------- Method: getIslands() -----------------------------------------------------------------------
	// Returns an array of the 12 islands
	// -------------------------------------------------------------------------------------------------------
	public Island[] getIslands() {
		return islands;
	}

	// -------------------------------------------------------------------------------------------------------
	// ---------- Method: islandResourceDistribution() -------------------------------------------------------
	// Checks islands according to the value of the dice roll, and calls the checkArray method
	// -------------------------------------------------------------------------------------------------------
	public void islandResourceDistribution(int diceValue, Island[] islands, ArrayList<Player> players) {
		if (diceValue == 1) { // ---------- Roll a 1 - Islands 1, 3 and 10 -----------
			islands[0].checkArray(players, Resources.Cutlasses, design[4][9], design);	// Island 1, Assign: Cutlasses
			islands[2].checkArray(players, Resources.Goats, design[4][21], design);		// Island 3, Assign: goat
			islands[9].checkArray(players, Resources.Wood, design[12][15], design); 	// Island 10, Assign: wood
		} else if (diceValue == 2) { // ---------- Roll a 2 - Islands 2, 4, and 11 ----------
			islands[1].checkArray(players, Resources.Wood, design[4][15], design); 		// Island 2, Assign: wood
			islands[3].checkArray(players, Resources.Molasses, design[4][27], design); 	// Island 4, Assign: molasses
			islands[10].checkArray(players, Resources.Goats, design[12][21], design); 	// Island 11, Assign: goat
		} else if (diceValue == 3) { // ---------- Roll a 3 - islands 5 and 7 ---------------
			islands[4].checkArray(players, Resources.Wood, design[8][6], design); 		// Island 5, Assign: wood
			islands[6].checkArray(players, Resources.Gold, design[8][24], design); 		// Island 7, Assign: gold
		} else if (diceValue == 4) { // ---------- Roll a 4 - islands 9 and 12 --------------
			islands[8].checkArray(players, Resources.Cutlasses, design[12][9], design); // Island 9, Assign: Cutlass
			islands[11].checkArray(players, Resources.Molasses, design[12][27], design);// Island 12, Assign: molasses
		} else if (diceValue == 5) { // ---------- Roll a 5 - islands 6 and 8 ---------------
			islands[5].checkArray(players, Resources.Gold, design[8][12], design); 		// Island 6, Assign: gold
			islands[7].checkArray(players, Resources.Goats, design[8][30], design); 	// Island 8, Assign: goat
		} 
//		else { // ---------- Roll a 6 - ghost captain -----------------
//			moveGhostCaptain(); 
//		}
	}
	
	
	// -------------------------------------------------------------------------------------------------------
	// ---------- Method: getInstance() ----------------------------------------------------------------------
	// Gets instance of the Board created
	// -------------------------------------------------------------------------------------------------------
	public static Board getInstance(){
	      return instance;
	   }
	
	// -------------------------------------------------------------------------------------------------------
	// ---------- Method for test purposes: Moving gc w/o needing inputs -------------------------------------
	// -------------------------------------------------------------------------------------------------------
	public void moveGC() {
		design[8][18] = ' ';
	}
	
	public GhostCaptain getGC() {
		return ghostCaptain;
	}
}