package setup;

import java.util.ArrayList;
import java.util.Scanner;
import main.Interactor;
import player.Player;

public class PlayerSetup {
	// -------------------------------
	// ---------- Variables ----------
	// -------------------------------
	protected String[] colours = {"blue", "red", "white", "orange"};	// Array of all available colors
	protected ArrayList<Player> players = new ArrayList<Player>(); 		// Array to hold list of players
	protected int numberofPlayers;										// Number of players
	protected Boolean validPlayerNum = false;							// To determine if a valid number of players is entered							
	Interactor interactor = new Interactor();							// Create an interactor object

	// MG: Are these things done?
	// place a lair and ship on the board for each player, by their color (is this under board setup or player setup?)
	// Assign each player a building cost tile that matches his/her colour 
	// EP: need to add 1 wood and 1 molasses to users pocket
	// Give each player one wood resource tile and one molassess resource tile (Is this under player setup or resource setup?)
	
	// QUESTION (from MG to EP 3/12): Says on the assignment that players should range from 3-4 - is 2 not an option?
	// Note that I left the error checking to be okay with 1-4 for now but that should be an easy switch 
	
	// ---------------------------------
	// ---------- Constructor ----------
	// ---------------------------------
	public PlayerSetup() {
		while(validPlayerNum == false) {							// This loops until a valid number is entered
			interactor.printMessage("number of players");			// Call the interactor to ask the # of players
			String numberofPlayers = interactor.takeInAnswer();		// Take in the # of players 
			validPlayerNumberCheck(numberofPlayers);				// Check if the entered value is valid
		}
		for (int i = 0; i <= this.numberofPlayers - 1; i++) {
			interactor.printMessage("player name");					// Call the interactor to take in player names
			String playerName = interactor.takeInAnswer();			// Take in the players names
			this.players.add(new Player(playerName, colours[i])); 	// Add player to array of players
			players.get(i).setupUserPocket(); 						// Call method to setup users pocket
			System.out.println(players.get(i).toString());			// Print out players name and colour
		}
	}
	
	// --------------------------------------------------
	// ---------- Method: getNumberofPlayers() ----------
	// This returns the number of players in the game
	// --------------------------------------------------
	public int getNumberofPlayers() {
		return this.numberofPlayers;
	}
	
	// ---------------------------------------
	// ---------- Method: Players() ----------
	// This returns players in the game
	// ---------------------------------------
	// EP: this method will return the arraylist containing the players. By using the PlayerName method in the Player class we can determine which player is next
	// Should it be a queue, so every time it's a players go, they're removed from the front of the stack and added to the back, this way we will be able to keep the order
	public ArrayList<Player> Players(){
		return this.players;
	}

	// ----------------------------------------------------
	// ---------- Method: validPlayerNumberCheck ----------
	// Check that the # of players entered is between 2-4
	// ----------------------------------------------------
	public void validPlayerNumberCheck(String enteredValue){
		if (enteredValue.equals("2") || enteredValue.equals("3") || enteredValue.equals("4")) {
			validPlayerNum = true;
			this.numberofPlayers = Integer.parseInt(enteredValue);
		} else {
			interactor.printMessage("invalid players");
		}
	}
}
