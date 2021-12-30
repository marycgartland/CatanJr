package board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.Interactor;
import player.Player;
import resources.Resources;

// ----------------------------------------------------------------------------------------
// This is a class to construct the islands on the board. The goal of this class is to set 
// the lair locations connected to each island, in terms of their position on the matrix.
// Each island will have 2 arrays, one for columns and one for rows. The nth position in
// each array will correspond to one another. 
//----------------------------------------------------------------------------------------

public class Island {
	// --------------------------------
	// ----------- Variables ----------
	// --------------------------------
	private int[] columns;									// Array of column values
	private int[] rows;										// Array of row values
	protected Interactor interactor;						// Interactor to work with user
	protected char[] playerColors = {'B','R', 'W', 'O'};	// Array of color letters as appears on the board
	protected char playerColor;								// Players color
	protected Player player;								// Selected player
	protected char isGhost; 								// Character to see if the ghost captain is there
	protected int temp_row;									// Selected row value
	protected int temp_col;									// Selected column value
	
	// ----------------------------------
	// ----------- Constructor ----------
	// ----------------------------------
	public Island(int[] row_vals, int[] col_vals) {
		this.columns = col_vals;
		this.rows = row_vals;
		interactor = new Interactor();
	}
	
	// ----------------------------------
	// ----------- Get methods ----------
	// ----------------------------------
	public int[] getColumn() {	// Get column value
		return columns;
	}
	
	public int[] getRow() {		// Get row value
		return rows;
	}
	
	//-----------------------------------------------------------------------------------------------
	//---------- Method: checkArray() ---------------------------------------------------------------
	// This method checks what players have a Lair touching an island and assigns resources as needed
	//-----------------------------------------------------------------------------------------------
	public void checkArray(ArrayList<Player> players, Resources resource, char isGhost, char[][] design){
		if(isGhost != 'G') {
			for (int j = 0; j <= players.size() - 1; j++) {
				playerColor = playerColors[j];
				player = players.get(j);
				for (int i = 0; i <= columns.length - 1; i++) {
					temp_row = rows[i];
					temp_col = columns[i];
					if(design[temp_row][temp_col]==playerColor) {
						player.addResource(resource, 1);
						interactor.printMessage("Island check: success", resource.toString(), player);
						}
					}
				}
			} else {	// If the ghost captain is on an island, no resources are distributed from it
				interactor.printMessage("Island check: ghost captain");
			}
		}
}