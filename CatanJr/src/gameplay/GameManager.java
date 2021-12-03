package gameplay;

import java.util.ArrayList;

import board.Board;
import board.Island;
import board.Marketplace;
import board.Stockpile;
import main.Interactor;
import player.Player;
import resources.CocoTiles;
import setup.PlayerSetup;
import setup.ResourceSetup;

public class GameManager {
	// -------------------------------------------------------------------------------------------------------
	// This class controls/runs the game
	// It controls the turns, it determines the winner
	// How it determines a winner: when a user has placed 7 lairs (including if they have on on spooky island)
	// -------------------------------------------------------------------------------------------------------
	
	// -------------------------------
	// ---------- Variables ----------
	// -------------------------------
	Interactor interactor = new Interactor();	// Create an object 'Interactor' to deal with user interactions
	protected Board board;						// Create a game board
	protected Stockpile stockPile;				// Create a stockpile
	protected CocoTiles cocoTiles;				// Create cocoTiles
	protected Marketplace marketPlace;			// Create a marketPlace
	protected String winner;					// Create a string to hold the winner
	
	// ---------------------------------
	// ---------- Constructor ----------
	// ---------------------------------
	public GameManager() {
		this.board = new Board();
	}
	
	// -----------------------------------------
	// ---------- Method: StartGame() ----------
	// This method sets up the game to begin ---
	// -----------------------------------------
	public void startGame() {
		PlayerSetup playerSetup = new PlayerSetup(); 							// Set up players 
		ResourceSetup resourceSetup = new ResourceSetup(playerSetup.Players()); // Set up resources
		board.setupBoard(playerSetup.Players().size());							//
		board.setupGhostCaptain();												//
		board.showBoardLayout();												//
		board.setUpIslands();													//
		this.stockPile = resourceSetup.getStockpile();							//
		this.cocoTiles = resourceSetup.getCocoTiles();							// Set up cocotiles
		this.marketPlace = resourceSetup.getMarketplace();						// Set up the resouces in the marketplace
		//board.mostCocotiles(playerSetup.Players());//remove: testing
		// while there is no winner declared, rotate players turns
		int player_turn = 0;
		while (!checkWinner(playerSetup.Players())) {
			PlayerTurn playerTurn = new PlayerTurn(playerSetup.Players().get(player_turn), marketPlace, stockPile,
					cocoTiles, board);
			//playerTurn.takeTurn();
			board.mostCocotiles(playerSetup.Players()); // check if player with most cocotiles can place their lair on spooky island
			playerTurn.takeTurn(board.getIslands(),playerSetup.Players()); // TRYING TO TAKE IN THE ISLANDS AND THE ARRAY OF PLAYERS
			//playerTurn.takeTurn();
			player_turn = (player_turn + 1) % (playerSetup.Players().size() + 1); // this will loop through players
		} 
		interactor.printMessage("winner", winner);
	}
	
	// method to check if a player has 7 or more lairs
	// make sure that this count is including the lair on spooky island (it should be correct cause users keep track of their lair count)

	public boolean checkWinner(ArrayList<Player> players) {
		for (int i = 0; i <= players.size() - 1; i++) {
			if(players.get(i).getLairCount()>=7) {
				this.winner = players.get(i).getName();
				return true; // there is a winner
			} 
		}
		return false;
	}
}