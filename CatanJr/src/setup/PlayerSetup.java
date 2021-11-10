package setup;

import java.util.ArrayList;
import java.util.Scanner;

import player.Player;

public class PlayerSetup {
	
	protected String[] colours = {"blue", "red", "white", "orange"}; // array of available colours
	protected ArrayList<Player> players = new ArrayList<Player>(); //  array list of players
	protected int numberofPlayers;
	
	// place a lair and ship on the board for each player, by their color (is this under board setup or player setup?)
	// Assign each player a building cost tile that matches his.her colour 
	
	// EP: need to add 1 wood and 1 molasses to users pocket
	// Give each player one wood resource tile and one molassess resource tile (Is this under player setup or resource setup?)
	
	// need to add error checks: user can only enter 1/2/3/4 for number of players
	public PlayerSetup() {
		System.out.println("Enter the number of players(1-4): ");
		Scanner scan = new Scanner(System.in);
		String numberofPlayers = scan.next();
		this.numberofPlayers = Integer.parseInt(numberofPlayers);
		for (int i = 0; i <= this.numberofPlayers - 1; i++) {
			System.out.println("\nEnter a players name: "); // take in players name
			Scanner scan1 = new Scanner(System.in);
			String playerName = scan1.next();
			
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
	
}
