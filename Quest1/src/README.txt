Name:Diwen Sun
Username: jack123
BU ID: U37462503

(1) How to compile: javac Run.java
    How to execute: java Run.java

(2) 
How to use the program:
A game board, a team of player, an arraylist of hardcoded monsters, an arraylist of hard-coded Weapons,an arraylist of hard-coded Armors,an arraylist of hard-coded potions, an arraylist of hard-coded spells, an int representing start_row and an int representing start_col are served as input for creating a new game.

Market is marked as 'M' in the board.
Player position is marked as 'P' in the board.
Common position don't have markers.

When the game ask "Type the amount of players you want to play (1 to 3): ", simply type in 1 or 2 or 3
When the game ask " Please choose a hero to play(1/2/3...12): ", simply choose a number from 1 to 12 and type in. Duplicate heros cannot be chosen again, or you will be asekd to choose again.
After the game prints out the board and ask "Team please choose your action":, simply type w/a/s/d/q/i.



Market:
All markets sell the same product. The number of each product sold is unlimited in a market.
When a team arrived a market, each player will be asked "Do you want to purchase items?". Simply type y/n. If you type 'n', it will move to the next player's turn.
If you pressed 'y' you are asked to "select the type you want to purchase", just type 1/2/3/4...Then you will see a list of items belongs to the chosen type.
When you are asked to "Select the item you want", just type 1/2/3/4...
If the item  is successful selected then it will add to your shopping cart.
If the item purchase has a required level higher than your current level, then the system prints (Didn't reach the requried level!).You cannot buy it.

After you don't want to select any more goods you are leaded to checkout. If your money is not enough when checkout, you have the right to remove items your purchase.
After your money is enough for all the items in your cart, you will be automatically checkout!


Battle:
The chance that a team meets the monsters in a common cell is 80%. 
When battle is launched, a specific number (determined by number of players) of monsters is chosen from all available monsters. All the chosen mosnters are less than or equal to the level of the most experienced member in the team.
For each round, heros will act first. After all the heros finish their actions, the monsters act.
Both the heros and monsters choose their attacking target automatically following some pre-defined rules.
During every round of a fight the heroes regain 5% of their max hp and 5% of their max mana.


When you are asked ("Select your action(type a/b/c/d): "a. Weapon_Att" "b. Spell" "c. Potions" "d. Normal_Attack"); just type a/b/c/d. Choose a let you see the weapons you have, choose b let you see the spells you can use and choose c let you see the potions you have. Choose d will launch normal attack.

If you choose a/b/c but you didn't have any goods purchased for the chosen action(type), then you will automatically implement normal attack.
If you choose a spell that your mana is not enough to launch, then you will be forced to implement normal attack.
If you have items for the chosen type above, then you will see all the items you have for this type and will be asked ("Select the item you want to use (1/2/3...)". Simply type 1/2/3...

I changed the damage fomulas for a little bit to make the damge more reasonable.

After a battle, the heros that are still alive will gain 150 coins and 2 exp, also a 20% refill of their hp. The faint heros will get 50% recover of their hp.


Others:
Before the player purchase any items, he don't have any equipments.
A player don't have a weapon,spell or potion to choose for equip. However, they are allowed to choose which weapon,spell or potion they want to use during each round of the battle.
After you purchased an armor, your armor will be automatically changed to the armor you have that has the highest defense. You cannot change your armor in other ways.
A hero's hp and mana will be refilled when he levels up.
The effects of potions are eternal, but unlike weapons and spells, they can be used out. Once a potion is used you can no longer see it in your potion list. 
In addition, a hero's hp or mana can never exceed the max value no matter how they use potions. For ex, a hero has 50 hp and he takes the potion that adds 100 hp to him. However,
his max hp at his current level is 100. After taking the potion, his hp will be 100, not 150.
Most of the other rules and regulations for this game not mentioned above follows that in the pdf.

(3)
Run.class is the entry of program. It also helps initialize all the necessary figures that help us run the game.
Game.class is the logic of the game.
Fighters.class is the superclass of both heros and mosters. It helps creating fighters in the battle.
Heros.class,monster.class and all their subclasses extends from Fighter.class.
Armory,Spells,Weaponry,Potions.class and all their subclasses are for the armory,spell, weaponry and potion objects. They all extends Goods.class, since they can be purchased in the market.
Battle.class stores the logic for battles, helping us create and run the battle.
Market.class help us create a market.
Purchass class take market as input and simulate an purchase process in the market.
ShoppingCart.class is a cart that sotres the goods that a hero choose. It helps a hero checkout in the market.
Team.class help creates a team of players that participates the game.
Board.class help us create the gamebord.
Cell.class represents the cell of the gameboard and stores the type of each board cell.
Damage is an interface that both Weaponry.class and and Spells.class implement. (Weapons and spells can launch damge to monsters).
DodgeAttack is an interface that the Fighter class implement. (All the fighters in the battle can dodge attack).










