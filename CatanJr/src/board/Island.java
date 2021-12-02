package board;

//This is a class to construct the islands on the board
//The goal of this class is to set the lair locations connected to each island, in terms of their position on the matrix
//Each island will have 2 arrays, one for columns and one for rows
//The nth position in each array will correspond to one another 

public class Island {
	private int[] columns;
	private int[] rows;
	
	// Island Constructor
	public Island(int[] row_vals, int[] col_vals) {
		this.columns = col_vals;
		this.rows = row_vals;
	}
	
	// Get methods
	public int[] getColumn() {
		return columns;
	}
	
	public int[] getRow() {
		return rows;
	}

}
