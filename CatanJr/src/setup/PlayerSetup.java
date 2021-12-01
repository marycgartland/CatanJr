package setup;

import java.util.ArrayList;
import java.util.Scanner;

import main.Interactor;
import player.Player;

public class PlayerSetup {
	
	protected String[] colours = {"blue", "red", "white", "orange"}; // array of available colours
	protected ArrayList<Player> players = new ArrayList<Player>(); //  array list of players
	protected int numberofPlayers;
	Interactor interactor = new Interactor();

	
	// place a lair and ship on the board for each player, by their color (is this under board setup or player setup?)
	// Assign each player a building cost tile that matches his.her colour 
	
	// EP: need to add 1 wood and 1 molasses to users pocket
	// Give each player one wood resource tile and one molassess resource tile (Is this under player setup or resource setup?)
	
	// need to add error checks: user can only enter 1/2/3/4 for number of players
	public PlayerSetup() {
		interactor.printMessage("number of players");
		String numberofPlayers = interactor.takeInAnswer();

		this.numberofPlayers = Integer.parseInt(numberofPlayers);
		for (int i = 0; i <= this.numberofPlayers - 1; i++) {
			interactor.printMessage("player name");
			String playerName = interactor.takeInAnswer();
			this.players.add(new Player(playerName, colours[i])); // add player to array of players
			
			players.get(i).setupUserPocket(); // method to setup users pocket
			System.out.println(players.get(i).toString());
		
		}
	}
	
	public int getNumberofPlayers() {
		return this.numberofPlayers;
	}
	
	//EP: this method will return the arraylist containing the players. By using the PlayerName method in the Player class we can determine which player is next
	// should it be a queue, so everytime its a players go, they are removed from the front of the stack and added to the back, this way we will be able to keep the order
	public ArrayList<Player> Players(){
		return this.players;
	}
	
	//
	
	//-----------------------------------------------------------
	//---------- Method: validPlayerNumberCheck -----------------
	//-----------------------------------------------------------
	// EP: this method needs to be called somewhere
	public boolean validPlayerNumberCheck(String enteredValue){
		// If entered resource is  avalide resource return true, otherwise return false
		if (enteredValue.equals(1) || enteredValue.equals(2) || enteredValue.equals(3) || enteredValue.equals(4)) {
			return true;
		} else {
			interactor.printMessage("invalid players");
			return false;
		}
	}
}
