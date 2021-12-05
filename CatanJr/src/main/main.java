package main;

//import java.util.Scanner;
//import java.util.*;
//import gameplay.*;
//import board.*;

//import player.Player;
//import resources.Resources;
//import setup.PlayerSetup;
//import setup.ResourceSetup;
import gameplay.GameManager;

public class main {
	
	public static void main(String[] args) {
		
		GameManager gameManager = new GameManager();
		gameManager.startGame();
		

		// MG: Are these things done?
		// place a lair and ship on the board for each player, by their color (is this
		// under board setup or player setup?)
		// Assign each player a building cost tile that matches his/her colour
		// EP: need to add 1 wood and 1 molasses to users pocket
		// Give each player one wood resource tile and one molassess resource tile (Is
		// this under player setup or resource setup?)

		// QUESTION (from MG to EP 3/12): Says on the assignment that players should
		// range from 3-4 - is 2 not an option?
		// Note that I left the error checking to be okay with 1-4 for now but that
		// should be an easy switch
		
		// Assign each island needs to be associated with a dice number between 1 to 5,
		// and a resource
		// Assign each color to a location on the board
		// Add a lair and ship for each player at their color location on the board (is
		// this board setup or player setup? It's chosen by players...)
		// Place ghost captain on the board
		
		// Check that cocotiles are actually shuffled 
		
		// FROM COCOTILES CLASS:
		// EP: we are going with option 2, so Player class will track this, we need to
		// just compare them after every turn
		// Keep track of which player has the most tiles (there should be a 'used'
		// stockpile of cocotiles for each player)
		// Option 1: Keep track of who has each cocotile here with just adding 1 each
		// time they get a new one
		// Option 2: use a get method in class 'Player' to get and compare that way

		// EP: I think this is relevant for the board/ YourTurn class
		// If a player has the same number of tiles another, they should remove their
		// pirates lair from spooky island
		// If a player has the most cocotiles, they get to place an unused pirates lair
		// on spooky island
		// First player to buy a cocotile has the most at that moment
		
		// user needs to keep track the number of cocotiles they have and thats how we
		// will determine who has the most in the game
		// YourTurn needs to determine the action based on the cocotiletype outputted by
		// this method, is user is given WoodMolasses/GoatCutlasses need to give out 2
		// of each of that resource etc
		// before a user buys a cocotile, their pocket should be checked to make sure
		// they have sufficient funds
		
		// IN PLAYER CLASS
			// TODO: Change these back to 0. they are just set to 20 for testing purposes at the moment
			// TODO: We want methods to show people their resources at any time protected Item []
		
		// More things I have to do: 
			// close scanner in interactor class
			// Finish changing all user interactions to interactor class
			// Abstract classes and interfaces?
			// Limit any code repetition
			// Player class 'toString' method i think is laready don ein player set up -no need to repeat
		
		// move the creation of the dice object from playerTurn to Setup/gameplay
		
		// Error check for players buying cocotiles - entering incorrect options
		
		// Make sure user can't build 2 of the same ships/lairs side by side
		// error checking if user doesn't choose ship/LAIR OPTION	
		
		// Do we want to remove the pocket printing in the GameManager take turn loop? it was set for testing
		
		
		// STOCKPILE NOTES

		// Keep track of the number of each resource
		// Method to make sure there is at least 1 of each resource left, otherwise all
		// of that resource are returned to the stockpile

		// EP: Stockpile needs to be called before the users are given their first
		// pieces and before the marketplace is setup
		// EP: Need method to assign resources to different users, for example when the
		// dice is rolled, the stockpile needs to assign the resources to the players
		//stockpile - setupPlayers prints pocket - do we want to leave this in or take this out?
		
		
		
		// MARKETPLACE
		// Variables
		// Constructor
		// If all 5 places are the same resource, then we need to reset the marketplace
		// - need a check and a reset method
		// method to let the user trade

		// SetupMethod which will be called by the setup class: this will restock the
		// market place with one of each resource (1 x: cutlass, goat, wood, gold and
		// molasses)

		// CheckMethod to check that all resources in the marketplace are not the same,
		// if it is the Setup Method is called and the all resources are returned to
		// stockpile


		// Swap method that can only be called once in a turn which tracks resources in
		// marketplace
		
		// EP: YourTurn Class should keep track of how many times the marketplace is swapped with per users turn
		// Problem with checkMarketPlace class 
		// checks to see if there are 5 of the same element or not
		// this only works if the loop goes to <=3 and the counter==4, but I would say
		// that those values need to be increased by one but it won't run like that
		
		//GHOST CAPTAIN:
		// Note: Don't even know if the Ghost Captain should be under board still or not
    }

}
