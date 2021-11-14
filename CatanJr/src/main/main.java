package main;

import java.util.Scanner;
import java.util.*;
import gameplay.*;

import player.Player;
import resources.Resources;
import setup.PlayerSetup;
import setup.ResourceSetup;

public class main {
	
	public static void main(String[] args) {
		
		// ---------- TESTING: REMOVE ALL THIS LATER ----------//
		// Create a Person and make sure it is taking in what move the player wants to take 
		Player player1 = new Player("Emma", "blue");
		player1.setupUserPocket();
		player1.addResource(Resources.Wood, 1);
		player1.addResource(Resources.Molasses, 1);
		player1.addResource(Resources.Goats, 1);
		player1.addResource(Resources.Cutlasses, 1);
		//player1.yourTurn();

		// QUESTION FROM MARY TO EMMA: how do we select a player from the Players array for testing purposes?
		PlayerSetup playerSetup = new PlayerSetup(); //setup players
		ResourceSetup resourceSetup = new ResourceSetup(playerSetup.Players()); //setup resources
		playerSetup.Players();
		PlayerTurn turn1 = new PlayerTurn(player1);
		//PlayerTurn turn1 = new PlayerTurn(Players.get(1));
		turn1.takeTurn();
    }

}
