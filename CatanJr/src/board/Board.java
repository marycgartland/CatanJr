package board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.Interactor;
import player.Player;
import resources.Resources;

public class Board {
	// -------------------------------
	// ---------- Variables ----------
	// -------------------------------
	protected char[][] design;
	protected String[] options_string = {"1", "2", "3", "4", "5", "6", "7"};
	protected char[] symbolHolder = {'x','x','x','x', 'x', 'x', 'x'}; // placeholder array
	protected Interactor interactor;
	protected GhostCaptain ghostCaptain;
	protected Island[] islands; // An array for the islands
	protected String[] island_numbers = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13" };	
	
	// -------------------------------
	// ---------- Constructor --------
	// Sets up the layout of the board
	// -------------------------------
	public Board() {
		interactor = new Interactor();
		design = new char[][]{
			{ ' ', ' ', ' ', ' ',' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',' ', ' ', ' ', ' ',' ',' ', ' ',' ',' ',' ',' ', ' ',' ',' ',' ',' ', ' ',' ',' ',' ', ' ','\n' },
				{ ' ', ' ', ' ', ' ',' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ',' ', ' ', ' ', ' ','X',' ', ' ',' ',' ',' ',' ', ' ',' ',' ',' ',' ', ' ',' ',' ',' ', ' ','\n' },
				{ ' ', ' ', ' ', ' ',' ', ' ', ' ', ' ', ' ', ' ',' ',' ',' ', '/', ' ', ' ', ' ', '\\', ' ', '/', ' ', ' ', ' ','\\', ' ', ' ',' ',' ',' ', ' ', ' ',' ',' ',' ',' ',' ',' ', ' ',  '\n' }, 
				{ ' ', ' ', ' ', ' ',' ', ' ', ' ', ' ', ' ', ' ',' ',' ','X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ',' ','X',' ', ' ',' ',' ',' ', ' ', ' ',' ',' ',' ',' ',' ', ' ',  '\n' }, 
				{ ' ', ' ', ' ', ' ',' ', ' ', ' ', ' ', ' ', ' ',' ',' ','|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ',' ','|',' ', ' ',' ',' ',' ', ' ', ' ',' ',' ',' ',' ',' ', ' ','\n' }, 
				{ ' ', ' ', ' ', ' ',' ', ' ', 'X', ' ', ' ', ' ',' ',' ','X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ',' ','X',' ', ' ', ' ', ' ', ' ','X',' ', ' ',' ', ' ',' ',' ', ' ','\n' },
				{ ' ', ' ', ' ', ' ','/', ' ', ' ', ' ', '\\', ' ','/',' ',' ', ' ', '\\', ' ', '/', ' ', ' ', ' ', '\\', ' ', '/',' ',' ',' ', '\\', ' ', '/', ' ',' ',' ','\\',' ',' ',' ',' ', ' ','\n' },
				{ ' ', ' ', ' ', 'X',' ', ' ', ' ', ' ', ' ', 'X',' ',' ',' ',' ', ' ', 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ',' ',' ',' ', 'X', ' ', ' ', ' ',' ',' ','X',' ',' ',' ', ' ','\n' },
				{ ' ', ' ', ' ', '|',' ', ' ', ' ', ' ', ' ', '|',' ',' ',' ',' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ',' ',' ',' ', '|', ' ', ' ', ' ',' ',' ','|',' ',' ',' ', ' ','\n' },
				{ ' ', ' ', ' ', 'X',' ', ' ', ' ', ' ', ' ', 'X',' ',' ',' ',' ', ' ', 'X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ',' ',' ',' ', 'X', ' ', ' ', ' ',' ',' ','X',' ',' ',' ', ' ','\n' },
				{ ' ', ' ', ' ', ' ','\\', ' ', ' ', ' ', '/', ' ','\\',' ',' ', ' ', '/', ' ', '\\', ' ', ' ', ' ', '/', ' ', '\\',' ',' ',' ', '/', ' ', '\\', ' ',' ',' ','/',' ',' ',' ',' ', ' ','\n' },
				{ ' ', ' ', ' ', ' ',' ', ' ', 'X', ' ', ' ', ' ',' ',' ','X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ',' ','X',' ', ' ', ' ', ' ', ' ','X',' ',' ',' ',' ',' ',' ', ' ','\n' },
				{ ' ', ' ', ' ', ' ',' ', ' ', ' ', ' ', ' ', ' ',' ',' ','|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ',' ','|',' ', ' ',' ',' ',' ', ' ', ' ',' ',' ',' ',' ',' ', ' ','\n' },
				{ ' ', ' ', ' ', ' ',' ', ' ', ' ', ' ', ' ', ' ',' ',' ','X', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', ' ', ' ',' ','X',' ', ' ',' ',' ',' ', ' ', ' ',' ',' ',' ',' ',' ', ' ','\n' }, 
				{ ' ', ' ', ' ', ' ',' ', ' ', ' ', ' ', ' ', ' ',' ',' ',' ', '\\', ' ', ' ', ' ', '/', ' ', '\\', ' ', ' ', ' ','/', ' ', ' ',' ',' ',' ', ' ', ' ',' ',' ',' ',' ',' ',' ', ' ','\n' }, 
				{ ' ', ' ', ' ', ' ',' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ',' ', ' ', ' ', ' ','X',' ', ' ',' ',' ',' ',' ', ' ',' ',' ',' ',' ', ' ',' ',' ',' ', ' ','\n' },
				{ ' ', ' ', ' ', ' ',' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',' ', ' ', ' ', ' ',' ',' ', ' ',' ',' ',' ',' ', ' ',' ',' ',' ',' ', ' ',' ',' ',' ', ' ','\n' }
		};		
	}
	
	// -----------------------------------------------
	// ---------- Method: getBoardDesign() ----------
	// This method returns the board design array
	// -----------------------------------------------
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
		
	// ------------------------------------------------------------
	// ---------- Method: setGhostCaptain() -----------------------
	// This method assgins the created ghostCaptain to its variable 
	// ------------------------------------------------------------
	public void setGhostCaptain(GhostCaptain ghostCaptain) {
		this.ghostCaptain = ghostCaptain;
	}
	
	// --------------------------------------------------------------------------------------------------------
	// ---------- Method: mostCocotiles() ---------------------------------------------------------------------
	// This method determines which player has the most cocotiles. 
	// If 'G' is no longer at the centre (design[8][18]), the user with the most cocotiles can add a lair here.
	// --------------------------------------------------------------------------------------------------------
	public void mostCocotiles(ArrayList<Player> players) {
		int max = 0;
		char maxchar = ' ';

		if (design[8][18] != 'G') {	// Check that the Ghost captain isn't at the centre of the board
			for (int i = 0; i <= players.size() - 1; i++) {
				// If player has more cocotiles then another need to remove the previous players lair and reduce the number
				if (players.get(i).getCocoTileCount() > max) {
					reduceLairCount(maxchar, players);	
					max = players.get(i).getCocoTileCount();
					maxchar = players.get(i).getColour().charAt(0);
				} else if (players.get(i).getCocoTileCount() == max) {
					reduceLairCount(maxchar, players); 
					maxchar = ' ';
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
	// TODO: cannot move ghost captain to spooky island if a users lairs is there
	// because they have the most cocotiles
	public void moveGhostCaptain() {
		boolean integer_given = false;
		while (!integer_given) {

			interactor.printMessage("move ghost captain"); // Ask user which island they want to move the ghost captain
			showIslandNumberLayout(ghostCaptain.getGhostCaptainLocation()); // Replace centers of island with their// island #'s
			String number = interactor.takeInAnswer(); // Take in user's response

			List<String> comparison_list = Arrays.asList(island_numbers);

			if (comparison_list.contains(number)) { // determine if number is a valid island number
				ghostCaptain.updateLocationGC(Integer.parseInt(number)); // update location of GC
				for (int i = 0; i <= 17 - 1; i++) {
					for (int j = 0; j <= 38 - 1; j++) {
						if (design[i][j] == 'G') {
							design[i][j] = ' ';
							possibleLocationGhostCaptain(Integer.parseInt(number)); // Place 'G' to specificed location
						}
					}
				}
				interactor.printMessage("GC moved"); // Confirm that the ghost captain has been moved
				showBoardLayout();
				integer_given = true; // end loop
			} else {
				interactor.printMessage("invalid island");
				integer_given = false;

			}
		}

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
	public boolean placeShip(Player player) {
		boolean continue_turn = false;
		boolean integer_given = false;
		int count = 0;
		for (int i = 0; i <= 17 - 1; i++) {
			for (int j = 0; j <= 38 - 1; j++) {
				if (design[i][j] == Character.toUpperCase(player.getColour().charAt(0))) {
					for (int p = j - 3; p <= j + 3; p++) { // Check row above for available ship place
						if (design[i - 1][p] == '\\' || design[i - 1][p] == '/' || design[i - 1][p] == '|') {
							symbolHolder[count] = design[i - 1][p];
							design[i - 1][p] = options_string[count].charAt(0);
							count = count + 1;
						} else if (design[i + 1][p] == '\\' || design[i + 1][p] == '/' || design[i + 1][p] == '|') {
							symbolHolder[count] = design[i + 1][p];
							design[i + 1][p] = options_string[count].charAt(0);
							count = count + 1;
						} else {

						}
					}
				}
			}
		}
		// if options are available, let user decide where they would like to build their ship
		if (count > 0) {

			while (!integer_given) {
				// Let user decide where they would like to build their ship
				showBoardLayout();
				interactor.printMessage("build ship option"); // Gives user option of where to place ship
				String location_number = interactor.takeInAnswer();

				List<String> comparison_list = Arrays.asList(options_string);
				if (comparison_list.contains(location_number)) { // make sure player has provided a valid integer
					// Place ship at users choice of location & replace
					for (int i = 0; i <= 17 - 1; i++) {
						for (int j = 0; j <= 38 - 1; j++) {
							if (design[i][j] == location_number.charAt(0)) {
								design[i][j] = player.getColour().charAt(0);
								// Successfully placed a ship so take cost out of the players pocket
								interactor.printMessage("ship built");
								player.removeResource(Resources.Wood, 1);
								player.removeResource(Resources.Goats, 1);
							} else if (design[i][j] == '1' || design[i][j] == '2' || design[i][j] == '3'
									|| design[i][j] == '4' || design[i][j] == '5') { // replace numbers with slashs
								design[i][j] = symbolHolder[Character.getNumericValue(design[i][j]) - 1];
							}
						}
					}
					showBoardLayout();
					integer_given = true;
				} else {
					interactor.printMessage("invalid option");
					integer_given = false;
				}
			}
		} else {
			interactor.printMessage("no ships"); // no available space to place a ship
		}
		return continue_turn = true;
	}

	// ---------------------------------------
	// ---------- Method: placeLair ----------
	// Place a users lair on the board
	// ---------------------------------------
	public boolean placeLair(Player player) {
		boolean continue_turn = false;
		boolean integer_given = false;
		int count = 0;
		for (int i = 0; i <= 17 - 1; i++) {
			for (int j = 0; j <= 38 - 1; j++) {
				if (design[i][j] == player.getColour().charAt(0)) {
					for (int p = j - 3; p <= j + 3; p++) { // Check row above for available lair place
						if (design[i - 1][p] == 'X') {
							design[i - 1][p] = options_string[count].charAt(0);
							count = count + 1;
						} else if (design[i + 1][p] == 'X') {
							design[i + 1][p] = options_string[count].charAt(0);
							count = count + 1;
						}
					}
				}
			}
		}

		if (count > 0) {
			// Let user determine where they want to place their lair

			while (!integer_given) {
				showBoardLayout();
				interactor.printMessage("build ship option");
				String location_number = interactor.takeInAnswer();

				List<String> comparison_list = Arrays.asList(options_string);
				if (comparison_list.contains(location_number)) { // make sure player has provided a valid integer
					// place lair at users choice of location
					for (int i = 0; i <= 17 - 1; i++) {
						for (int j = 0; j <= 38 - 1; j++) {
							if (design[i][j] == location_number.charAt(0)) {
								design[i][j] = Character.toUpperCase(player.getColour().charAt(0));
								// successfully placed lair
								// Take a goat, a wood, a cutlasses, and a molasses out of the players pocket
								player.removeResource(Resources.Wood, 1);
								player.removeResource(Resources.Goats, 1);
								player.removeResource(Resources.Cutlasses, 1);
								player.removeResource(Resources.Molasses, 1);
								interactor.printMessage("liar built");
							} else if (design[i][j] == '1' || design[i][j] == '2' || design[i][j] == '3'
									|| design[i][j] == '4' || design[i][j] == '5') { // replace numbers with X's again
								design[i][j] = 'X';
							}
						}
					}
					showBoardLayout();
					integer_given = true;
				} else {
					interactor.printMessage("invalid option");
					integer_given = false;
				}
			}
		} else {
			interactor.printMessage("no lairs");
		}
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

	
	
	// TODO: might try move this into 'dice'
	//----------------------------------------------------------------------------------------------------
	//---------- Method: islandResourceDistribution() -----------------------------------------------------------------
	// This method checks islands according to the value of the dice roll, and calls the checkArray method
	//----------------------------------------------------------------------------------------------------	
	public void islandResourceDistribution(int diceValue, Island[] islands, ArrayList<Player> players) {
		if(diceValue==1) {			//---------- Roll a 1 - Islands 1, 3 and 10 -----------					
			islands[0].checkArray(players, Resources.Cutlasses, design[4][9], design);	// Island 1, Assign: Cutlasses
			islands[2].checkArray(players, Resources.Goats, design[4][21], design);		// Island 3, Assign: goat
			islands[9].checkArray(players, Resources.Wood, design[12][15], design);		// Island 10, Assign: wood
		} else if(diceValue==2) {	//---------- Roll a 2 - Islands 2, 4, and 11 ----------
			islands[1].checkArray(players, Resources.Wood, design[4][15], design);		// Island 2, Assign: wood
			islands[3].checkArray(players, Resources.Molasses, design[4][27], design);	// Island 4, Assign: molasses
			islands[10].checkArray(players, Resources.Goats, design[12][21], design);	// Island 11, Assign: goat
		} else if(diceValue==3) {	//---------- Roll a 3 - islands 5 and 7 ---------------
			islands[4].checkArray(players, Resources.Wood, design[8][6], design);		// Island 5, Assign: wood
			islands[6].checkArray(players, Resources.Gold, design[8][24], design);		// Island 7, Assign: gold
		} else if(diceValue==4) {	//---------- Roll a 4 - islands 9 and 12 --------------
			islands[8].checkArray(players, Resources.Cutlasses, design[12][9], design);	// Island 9, Assign: Cutlass
			islands[11].checkArray(players, Resources.Molasses, design[12][27], design);	// Island 12, Assign: molasses
		} else if(diceValue==5) {	//---------- Roll a 5 - islands 6 and 8 ---------------
			islands[5].checkArray(players, Resources.Gold, design[8][12], design);		// Island 6, Assign: gold
			islands[7].checkArray(players, Resources.Goats, design[8][30], design);		// Island 8, Assign: goat
		} else {					//---------- Roll a 6 - ghost captain -----------------
			moveGhostCaptain();
		}
	}	
}