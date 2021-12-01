package setup;

import java.util.ArrayList;

import board.Board;
import board.Marketplace;
import board.Stockpile;
import main.Interactor;
import player.Player;
import resources.CocoTiles;
import resources.Resources;

public class ResourceSetup {
	
	protected Board board;
	protected Stockpile stockPile;
	protected CocoTiles cocoTiles;
	protected Marketplace marketPlace;

	Interactor interactor = new Interactor();
	// Should this be its own setup?
	
	public ResourceSetup(ArrayList<Player> players) {
		this.board = new Board();
		this.stockPile = new Stockpile(players); // construct Stockpile
		this.cocoTiles = new CocoTiles(); // construct Cocotiles
		this.marketPlace = new Marketplace(); //construct marketplace
		stockPile.SetupMarketplace(marketPlace); // remove one of each resource from the stockpile as its being added to the marketplace
		marketPlace.SetupMarketplace(stockPile); // this adds one of each resource to the marketplace
		stockPile.SetupPlayers(players.size()); // this removes 1 wood and 1 molasses from the stockpile and adds them to users pocket
		board.setupBoard();
		interactor.printMessage("resources setup");
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public Stockpile getStockpile() {
		return this.stockPile;
	}
	
	public Marketplace getMarketplace() {
		return this.marketPlace;
	}
	
	public CocoTiles getCocoTiles() {
		return this.cocoTiles;
	}
}
