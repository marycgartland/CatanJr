package main;

import java.util.Scanner;
import java.util.*;
import gameplay.*;
import board.*;

import player.Player;
import resources.Resources;
import setup.PlayerSetup;
import setup.ResourceSetup;

public class main {
	
	public static void main(String[] args) {
		
		GameManager gameManager = new GameManager();
		gameManager.StartGame();
		
		// QUESTION FROM MARY TO EMMA: how do we select a player from the Players array for testing purposes?
		// ANSWER: the Players() function in PlayerSetup() class will return an arraylist of players so then i think you 
		// can use Players.get(0) to get the first player
		
		// General Things to fix:
		// Maybe not necesssary, but would be nice - now when you put in a wrong option, instead of letting you try again you go back to the start.


    }

}
