package setup;

import java.util.ArrayList;

import board.Board;
import board.GhostCaptain;
import board.Island;
import player.Player;

public class BoardSetup {
	
	protected Board board;
	protected char[][] design;
	protected GhostCaptain ghostCaptain;
	protected Island[] islands; // An array for the islands
	
	// Variables for dealing with islands and resource distribution 
		private int[] rows1 = {3, 5, 7, 5}; 				// Set up row and column arrays for possible lair locations
		private int[] rows2 = {3, 1, 3, 5, 7, 5};
		private int[] rows3 = {7, 5, 7, 9, 11, 9}; 
		private int[] rows4 = {13, 11, 9, 11};
		private int[] rows5 = {11, 9, 11, 13, 15, 13};
		private int[] cols1 = {12, 12, 9, 6};
		private int[] cols2 = {12, 15, 18, 18, 15, 12};
		private int[] cols3 = {18, 21, 24, 24, 21, 18};
		private int[] cols4 = {24, 24, 27, 30};
		private int[] cols5 = {3, 6, 9, 9, 6, 3};
		private int[] cols6 = {9, 12, 15, 15, 12, 9};
		private int[] cols7 = {21, 24, 27, 27, 24, 21};
		private int[] cols8 = {27, 30, 33, 33, 30, 27};



	
	// Assign each island needs to be associated with a dice number between 1 to 5, and a resource
	// Assign each color to a location on the board
	// Add a lair and ship for each player at their color location on the board (is this board setup or player setup? It's chosen by players...)
	// Place ghost captain on the board 
	
	
	public BoardSetup(ArrayList<Player> players) {
		this.board = new Board();
		this.design = board.getBoardDesign();
		setupBoard(players.size());
		setUpIslands();
		setUpGhostCaptain();
	}
	
	// -------------------------------------------------------------
		// ---------- Method: setupBoard -------------------------------
		// Setup Board design, place users first ship and lairs on board
		// -------------------------------------------------------------
		public void setupBoard(int numberplayers) {
			if (numberplayers == 1) {
				setupBluePlayerLocations(); 	// place blue players ships and lairs
			} else if (numberplayers == 2) {
				setupBluePlayerLocations(); 	// place blue players ships and lairs
				setupRedPlayerLocations(); 		// place red players ships and lairs
			} else if (numberplayers == 3) { 	// blue, red, white
				setupBluePlayerLocations(); 	// place blue players ships and lairs
				setupRedPlayerLocations(); 		// place red players ships and lairs
				setupWhitePlayerLocations(); 	// place white players ships and lairs
			} else { // 4 players
				setupBluePlayerLocations(); 	// place blue players ships and lairs
				setupRedPlayerLocations();		// place red players ships and lairs
				setupWhitePlayerLocations();	// place white players ships and lairs
				setupOrangePlayerLocations();	// place orange players ships and lairs
			}
		}
		
		// ----------------------------------------------------------
		// ---------- Method: setupBluePlayerLocations --------------
		// Define starting positions of blue player's ships and lairs
		// ----------------------------------------------------------
		public void setupBluePlayerLocations() {
			design[5][30] = 'B';
			design[6][28] = 'b';
			design[13][12] = 'B';
			design[12][12] = 'b';
		}

		// ---------------------------------------------------------
		// ---------- Method: setupRedPlayerLocations --------------
		// Define starting positions of Red player's ships and lairs
		// ---------------------------------------------------------
		public void setupRedPlayerLocations() {
			design[3][12] = 'R';
			design[4][12] = 'r';
			design[10][28] = 'r';
			design[11][30] = 'R';
		}

		// -----------------------------------------------------------
		// ---------- Method: setupWhitePlayerLocations --------------
		// Define starting positions of White player's ships and lairs
		// -----------------------------------------------------------
		public void setupWhitePlayerLocations() {
			design[5][6] = 'W';
			design[6][8] = 'w';
			design[12][24] = 'w';
			design[13][24] = 'W';
		}

		// ------------------------------------------------------------
		// ---------- Method: setupOrangePlayerLocations --------------
		// Define starting positions of Orange player's ships and lairs
		// ------------------------------------------------------------
		public void setupOrangePlayerLocations() {
			design[3][24] = 'O';
			design[4][24] = 'o';
			design[10][8] = 'o';
			design[11][6] = 'O';
		}
		
		//--------------------------------------------------------------------------------
		//---------- Method: setUpIslands() ----------------------------------------------
		// This method sets up the islands with the possible lair locations surrounding it 
		//--------------------------------------------------------------------------------
		// TODO: Move to board setup?
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
			board.setIslands(islands); // set island locations on board
		}
		
		// method to return the board created
		public Board getBoard() {
			return board;
		}
		
		// ----------------------------------------------------------------
		// ---------- Method: setUpGhostCaptain() -------------------------
		// This method sets up the ghost captain in the center of the board
		// This stops the users obtaining resources from islands its on
		//-----------------------------------------------------------------
		public void setUpGhostCaptain() {
			ghostCaptain = new GhostCaptain(13); 	// Setup ghost captain on spooky island (island = 13)
			design[8][18] = 'G';					// 'G' represents location of ghost captain on board
			board.setGhostCaptain(ghostCaptain);	// set ghost captain on board
		}

		
}
