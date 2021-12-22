package setup;

//----------------------------------------------------------------------------------------
// This class sets up players. This calls all necessary methods to set up each player 
// to begin the game, and ensure that a valid number of players (3 or 4) is entered.
//----------------------------------------------------------------------------------------

import java.util.ArrayList;
import main.Interactor;
import player.Player;

public class PlayerSetup {
	// --------------------------------------------------------
	// ---------- Variables -----------------------------------
	// --------------------------------------------------------
	protected String[] colours = { "blue", "red", "white", "orange" };	// Array of all available colors
	protected ArrayList<Player> players = new ArrayList<Player>(); 		// Array to hold list of players
	protected int numberofPlayers; 										// Number of players
	protected Boolean validPlayerNum = false; 							// To determine if a valid number of players is entered

	// ----- Create an interactor object ----------------------
	Interactor interactor = new Interactor();

	// --------------------------------------------------------
	// ---------- Constructor ---------------------------------
	// --------------------------------------------------------
	public PlayerSetup() {
		// Set up number of players in the game
		while (validPlayerNum == false) { 							// This loops until a valid number is entered
			interactor.printMessage("number of players"); 			// Call the interactor to ask the # of players
			String numberofPlayers = interactor.takeInAnswer(); 	// Take in the # of players
			validPlayerNumberCheck(numberofPlayers); 				// Check if the entered value is valid
		}
		// Set up names of players in the game
		for (int i = 0; i <= this.numberofPlayers - 1; i++) {
			interactor.printMessage("player name"); 							// Call the interactor to take in player names
			String playerName = interactor.takeInAnswer(); 						// Take in the players names
			this.players.add(new Player(playerName, colours[i]));				// Add player to array of players
			players.get(i).setupUserPocket(); 									// Call method to setup users pocket	
			interactor.printMessage("player info", players.get(i).toString());	// Print out players name and colour
		}
	}

	// --------------------------------------------------------
	// ---------- Method: getNumberofPlayers() ----------------
	// This returns the number of players in the game
	// --------------------------------------------------------
	public int getNumberofPlayers() {
		return this.numberofPlayers;
	}

	// --------------------------------------------------------
	// ---------- Method: Players() ---------------------------
	// Returns the ArrayList containing the players in the game
	// --------------------------------------------------------
	public ArrayList<Player> Players() {
		return this.players;
	}

	// --------------------------------------------------------
	// ---------- Method: validPlayerNumberCheck --------------
	// Check that the # of players entered is either 3 or 4
	// --------------------------------------------------------
	public void validPlayerNumberCheck(String enteredValue) {
		if (enteredValue.equals("3") || enteredValue.equals("4")) {
			validPlayerNum = true;
			this.numberofPlayers = Integer.parseInt(enteredValue);
		} else {
			interactor.printMessage("invalid players");
		}
	}
}
