package resources;

// -----------------------------------------------------------------------------------------------------------
// This class deals with the CocoTiles of the board game. It sets up each cocotile for the game, where the
// different types are enums. It has a method to allow players to buy cocotiles, which can be called on their 
// turn.
// -----------------------------------------------------------------------------------------------------------

import java.util.*;

public class CocoTiles {
	
	//-------------------------------------------------
	// ---------- Variables ---------------------------
	//-------------------------------------------------
	protected ArrayList<CocoTileTypes> CocoTiles = new ArrayList<CocoTileTypes>();	// Array to hold cocotiles
	
	//-------------------------------------------------
	// ---------- Constructor -------------------------
	//-------------------------------------------------
	public CocoTiles() { 
		// Add 20 cocotiles to the Arraylist.
		for (int i = 0; i <= 2; i++) { 	// There are 3 of each resource cocotile
			this.CocoTiles.add(CocoTileTypes.WoodMolasses);
			this.CocoTiles.add(CocoTileTypes.GoatCutlasses);
			this.CocoTiles.add(CocoTileTypes.ShipCastle);
		}
		for (int i = 0; i <= 10; i++) { // 11 Ghost captain CocoTiles
			this.CocoTiles.add(CocoTileTypes.GhostCaptain);
		}
		// Shuffle cocotiles
		Collections.shuffle(CocoTiles);
	}

	//-------------------------------------------------
	// ---------- Method: buyCocoTile -----------------
	//-------------------------------------------------
	public CocoTileTypes buyCocoTile() {
		if (CocoTiles.size() > 0) {
			CocoTileTypes cocoTileBought = CocoTiles.get(0); 	// Give user 1st element in shuffled ArrayList of cocotiles
			CocoTiles.remove(0);								// Remove the cocotile used from the pile of cocotiles
			return cocoTileBought;
		} else {
			return null;										// If there are no cocotiles left, the user cannot buy one
		}
	}
	
	//-------------------------------------------------
	// ---------- Method: get cocotile array ----------
	// Used for testing purposes only.
	//-------------------------------------------------
	public ArrayList<CocoTileTypes> getCocoTiles() {
		return CocoTiles;
	}
}