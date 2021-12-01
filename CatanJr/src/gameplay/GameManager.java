package gameplay;

import java.util.ArrayList;

import board.Board;
import board.Marketplace;
import board.Stockpile;
import player.Player;
import resources.CocoTiles;
import setup.PlayerSetup;
import setup.ResourceSetup;

public class GameManager {
	// The example had this. 
	// Control turns 
	// Determine winner: one user has placed 7 lairs, also check if the layer on spooky island is also theres
	
	protected Board board;
	protected Stockpile stockPile;
	protected CocoTiles cocoTiles;
	protected Marketplace marketPlace;

	
	
	public GameManager() {
		
	}
	
	public void StartGame() {
		PlayerSetup playerSetup = new PlayerSetup(); //setup players
		ResourceSetup resourceSetup = new ResourceSetup(playerSetup.Players()); //setup resources	
		
		this.board = resourceSetup.getBoard();
		this.stockPile = resourceSetup.getStockpile();
		this.cocoTiles = resourceSetup.getCocoTiles();
		this.marketPlace = resourceSetup.getMarketplace();
		// while there is no winner declared, rotate players turns
		int player_turn=0;
		//System.out.print(playerSetup.Players().get(player_turn).getName());
		//PlayerTurn playerTurn = new PlayerTurn(playerSetup.Players().get(player_turn), marketPlace, stockPile, cocoTiles, board);
		//playerTurn.takeTurn();

		while(!checkWinner(playerSetup.Players())) {
			PlayerTurn playerTurn = new PlayerTurn(playerSetup.Players().get(player_turn), marketPlace, stockPile, cocoTiles, board);
			playerTurn.takeTurn();
			player_turn=(player_turn+1)% (playerSetup.Players().size() + 1); // this will loop through players 
		}
	}
	
	// method to check if a player has 7 or more lairs
	public boolean checkWinner(ArrayList<Player> players) {
		for (int i = 0; i <= players.size() - 1; i++) {
			if(players.get(i).getLairCount()>=7) {
				return true; // there is a winner
			} else {
				return false; // there is no winner yet
			}
		}
		return false;
	}
	
	
}
