package setup;

import java.util.ArrayList;
import board.Board;
import board.GhostCaptain;
import board.Island;
import player.Player;

public class BoardSetup {

	// -----------------------------------------------------------------
	// ---------- Variables --------------------------------------------
	// -----------------------------------------------------------------
	protected Board board;
	protected char[][] design;	// The layout of the board
	protected GhostCaptain ghostCaptain;
	protected Island[] islands;	// An array for the islands

	// Variables for dealing with islands and resource distribution
	private int[] rows1 = { 3, 5, 7, 5 }; 
	private int[] rows2 = { 3, 1, 3, 5, 7, 5 };
	private int[] rows3 = { 7, 5, 7, 9, 11, 9 };
	private int[] rows4 = { 13, 11, 9, 11 };
	private int[] rows5 = { 11, 9, 11, 13, 15, 13 };
	private int[] cols1 = { 12, 12, 9, 6 };
	private int[] cols2 = { 12, 15, 18, 18, 15, 12 };
	private int[] cols3 = { 18, 21, 24, 24, 21, 18 };
	private int[] cols4 = { 24, 24, 27, 30 };
	private int[] cols5 = { 3, 6, 9, 9, 6, 3 };
	private int[] cols6 = { 9, 12, 15, 15, 12, 9 };
	private int[] cols7 = { 21, 24, 27, 27, 24, 21 };
	private int[] cols8 = { 27, 30, 33, 33, 30, 27 };

	// -----------------------------------------------------------------
	// ---------- Constructor ------------------------------------------
	// -----------------------------------------------------------------
	public BoardSetup(ArrayList<Player> players) {
		this.board = Board.getInstance(); // get instance of board
		this.design = board.getBoardDesign();
		setupBoard(players.size());	// Place ships and lairs based on player #
		setUpIslands();				// Set up the 12 islands
		setUpGhostCaptain();		// Set up the ghost captain
	}

	// -----------------------------------------------------------------
	// ---------- Method: setupBoard -----------------------------------
	// Setup Board design, place users first ship and lairs on board
	// -----------------------------------------------------------------
	public void setupBoard(int numberplayers) {
		if (numberplayers == 3) { 			// 3 players = blue, red, white
			setupBluePlayerLocations(); 		// Place blue players ships and lairs
			setupRedPlayerLocations(); 			// Place red players ships and lairs
			setupWhitePlayerLocations(); 		// Place white players ships and lairs
		} else { 							// 4 players = blue, red, white, orange
			setupBluePlayerLocations(); 		// Place blue players ships and lairs
			setupRedPlayerLocations(); 			// Place red players ships and lairs
			setupWhitePlayerLocations(); 		// Place white players ships and lairs
			setupOrangePlayerLocations(); 		// Place orange players ships and lairs
		}
	}

	// -----------------------------------------------------------------
	// ---------- Methods to set up player locations -------------------
	// These define the starting positions of a players ships and lairs
	// -----------------------------------------------------------------
	
	public void setupBluePlayerLocations() {	// Blue player setup
		design[5][30] = 'B';
		design[6][28] = 'b';
		design[13][12] = 'B';
		design[12][12] = 'b';
	}

	public void setupRedPlayerLocations() {		// Red player setup
		design[3][12] = 'R';
		design[4][12] = 'r';
		design[10][28] = 'r';
		design[11][30] = 'R';
	}

	public void setupWhitePlayerLocations() {	// White player setup
		design[5][6] = 'W';
		design[6][8] = 'w';
		design[12][24] = 'w';
		design[13][24] = 'W';
	}

	public void setupOrangePlayerLocations() {	// Orange player setup
		design[3][24] = 'O';
		design[4][24] = 'o';
		design[10][8] = 'o';
		design[11][6] = 'O';
	}

	// -----------------------------------------------------------------
	// ---------- Method: setUpIslands() -------------------------------
	// Sets up islands with the possible lair locations surrounding them
	// -----------------------------------------------------------------
	public void setUpIslands() {
		// Create an array for the 12 islands, created by their row and column locations for lair possibilities
		islands = new Island[] {new Island(rows1, cols1), new Island(rows2, cols2), new Island(rows2, cols3), 
				new Island(rows1, cols4), new Island(rows3, cols5), new Island(rows3, cols6), 
				new Island(rows3, cols7), new Island(rows3, cols8), new Island(rows4, cols1), 
				new Island(rows5, cols2), new Island(rows5, cols3), new Island(rows4, cols4)};
		
		// Set island locations on board
		board.setIslands(islands); 
	}

	// ----------------------------------------------------------------
	// ---------- Method: setUpGhostCaptain() -------------------------
	// This method sets up the ghost captain in the center of the board
	// This stops the users obtaining resources from islands it's on
	// -----------------------------------------------------------------
	public void setUpGhostCaptain() {
		GhostCaptain ghostCaptain = GhostCaptain.getInstance(); 	// Get instance of ghost captain
		design[8][18] = 'G'; 										// 'G' represents location of ghost captain on board
		board.setGhostCaptain(ghostCaptain); 						// Set ghost captain on board
	}
	
	// -----------------------------------------------------------------
	// ---------- Method: getBoard() -----------------------------------
	// Returns the board created
	// -----------------------------------------------------------------
	public Board getBoard() {
		return board;
	}
}