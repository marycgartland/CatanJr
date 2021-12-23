
                                                                                           Catan Jr: Instruction Manual 

                                                                                       Software Engineering COMP41670 - 2021

                                                                                      Authors: Emma Pender and Mary Gartland

This document contains:
	1. How to Play Instruction Manual
	2. Action HandBook (AH)

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

How to Play:

	1. Open the project and press the run button
	2. Choose a number of players between 3 and 4
	3. Enter the name of each player as prompted, each player will be assigned a colour, a wood and molasses, and their starting lairs and ships will be placed on the board 
	automatically. Lairs are represented by the capital letter of the players assigned colour, and ships are represented by the lower case of the players assigned colour,
	for example the blue players lairs will be identifyed as'B' and their ships will be 'b'
	4. After the players are setup, the game will begin with dice roll for the first player
	5. Based on the value of the dice roll, resources are distributed to players that have lairs connected to the islands that contain the value of the dice roll
	6. The player is then presented with 4 options:

	"Would you like to Buy [B], Build [Bd], Trade [T], View pocket[P] or End turn [E]?"

 		Option 1: Buy [B]:    
            		- This gives the player the option to buy a cocotile if they have sufficient funds
            		- Based on the type of cocotile that the player receieves, they will either gain resources, move the Ghost Captain (see AH.1) or build a ship (AH.2) or build a lair (AH.3)
                
 		Option 2: Build [Bd]:
        		- This gives the player the option to build a ship (AH.2) or a lair (AH.3) if they have sufficient funds
                
 		Option 3: Trade [T]:
        		- This gives the player the option to trade with the marketplace (AH.4) or the stockpile (AH.5)
        
 		Option 4: View pocket [P]:
        		- This gives the player the option to view the resources they have in their pocket

 		Option 5: End turn [E]:
        		- This allows the player to end their turn and the next players turn begins.

	7. Once a player ends their turn it is the next players turn and the cycle is repeated. 
	8. The game ends when a player has successfully placed 7 on their lairs on the board


------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

												Action HandBook (AH)

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Contents:
	1. How to move the Ghost Captain
	2. How to build a ship
	3. How to build a lair
	4. How to trade with the marketplace
	5. How to trade with the stockpile
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
1. How to move the Ghost Captain:
When the player is presented with the opportunity to move the ghost captain from either rolling a 6 on the dice or receiving the ghost captain cocotile, the following map is displayed and the player is asked which island the ghost captain should be moved to:

> Board Layout:
>                                       
>               X     X                
>             /   \ /   \              
>            R     X     X             
>         1  r  2  |  3  |  4          
>      W     X     X     X     B       
>    /   w /   \ /   \ /   \ b   \     
>   X     X     X     X     X     X    
>   |  5  |  6  |  G  |  7  |  8  |    
>   X     X     X     X     X     X    
>    \   / \   / \   / \   / r   /     
>      X     X     X     X     R       
>         9  b  10 |  11 w  12         
>            B     X     W             
>             \   / \   /              
>               X     X                
>                                      
>
> Which island would you like to move the ghost captain to?:

 The player must choose a island number between 1 and 13 to move the Ghost Captain to.

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

2. How to build a ship

When the player is presented with the opportunity to build a ship the following map is displayed which shows the numbered ship placement options:
 
> * Building ship *
>
> Board Layout:
>                                      
>               X     X                
>             /   \ /   \              
>            R     X     X             
>            r     |     |             
>      W     X     X     X     B       
>    /   w /   \ /   \ /   \ b   1     
>   X     X     X     X     X     X    
>   |     |     |  G  |     |     |    
>   X     X     X     X     X     X    
>    \   / \   / \   / \   / r   /     
>      X     X     X     X     R       
>            b     |     w             
>            B     X     W             
>             2   / \   /              
>               X     X                
>                                      
>
> Which number option would you like to build your ship at?: 

In order to place a ship, the player must enter a number that corresponds to the placement of ship on this map.
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

3. How to build a lair:

When the player is presented with the opportunity to build a lair the following map is displayed which shows the numbered lair placement options:

> * Building lair *
>
> Board Layout:
>                                      
>               X     X                
>             /   \ /   \              
>            R     X     X             
>            r     |     |             
>      W     X     X     X     B       
>    /   w /   \ /   \ /   \ b   \     
>   X     X     X     X     1     X    
>   |     |     |  G  |     |     |    
>   X     X     X     X     X     X    
>    \   / \   / \   / \   / r   /     
>      X     2     X     X     R       
>            b     |     w             
>            B     X     W             
>             \   / \   /              
>               X     X                
>                                      
>
> Which number option would you like to build your lair at?: 

In order to place a lair, the player must enter a number that corresponds to the placement of lair on this map.

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

4. How to trade with the marketplace

When the player chooses to trade with the marketplace, they are presented with the following sample dialog, which outlines the resources currently in the marketplace, from this the user must choose the resource they want to obtain from the marketplace:

> You have chosen to trade with the marketplace.
>
> Marketplace Contents: Wood, Cutlasses, Goats, Gold, Molasses
>
> What item would you like to obtain (Wood [W], Molasses [M], Gold [G], Goat[GT] or Cutlass [C])? 

The user must enter one of the 5 letters to distinguish the resource they want to obtain, next they are asked what resource they would like to exchange for this resource:

> What item would to trade in (Wood [W], Molasses [M], Gold [G], Goat[GT] or Cutlass [C])? 

The user must enter one of the 5 letters to distinguish the resource they want to swap in order to obtain their desired resource from the marketplace

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

5. How to trade with the stockpile
When the player chooses to trade with the stockpile they are asked what resources they wish to obtain:

> What item would you like to obtain (Wood [W], Molasses [M], Gold [G], Goat[GT] or Cutlass [C])? 

The user must enter one of the 5 letters to outline which resource they want. Next the player is asked what resources they will swap for this desired resource:

> What item would to trade in (Wood [W], Molasses [M], Gold [G], Goat[GT] or Cutlass [C])? 

The user must enter one of the 5 letters to outline which resource they will exchanage the desired resource for.



