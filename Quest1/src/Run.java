import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Run{
	protected static List<Hero> chosen_hero;
	protected static int numofPlayer;
	protected static int start_row;
	protected static int start_col;
	protected static List<Integer> list;
	
	public static void main(String[] args) {
		run();
   	}
	
	
	 private static void run() {
		 int input;
		 list = new ArrayList<Integer>();
		 Scanner s1 = new Scanner(System.in);
		 Random rand = new Random(); 
    	 System.out.println("Welcome to the Quest game!");
    	 
    	 //choose number of players
    	 do{
    		 System.out.print("Type the amount of players you want to play (1 to 3): "); 
    		 numofPlayer = s1.nextInt();
	 	 }while (valid_input1(numofPlayer)==false);
    	 System.out.println("We have " + numofPlayer + " Players!");
    	 System.out.println();
    	 
    	 //create board
 		 Board game_board = new Board(8,8);
 		 //randomly choose start position in the board
 		 do {
 			start_row = rand.nextInt(8); 
 			start_col = rand.nextInt(8);
 		 }while (game_board.boardcells[start_row][start_col].type.equals("non_accessible") || game_board.boardcells[start_row][start_col].type.equals("market"));
 		 //add all monsters
 		 List<Monster> monsters = addMonsters();
 		 List<Hero> ava_Heros = addHeros();
 		 //select heros
 		 selectHeros(ava_Heros,numofPlayer);
 		 
 		 
 		List<Weaponry> ava_Weapons = addWeapons();
 		List<Armory> ava_Armors = addArmors();
 		List<Spells> ava_Spells = addSpells();
 		List<Potions> ava_Potions = addPotions();
 		 
 		 Game newgame = new Game(game_board,monsters,chosen_hero,ava_Weapons,ava_Armors,ava_Potions,ava_Spells,start_row,start_col);
 		 newgame.game_run();
 		
     } 
	 //add monsters
	 private static List<Monster> addMonsters(){
		 List<Monster> monsters = new ArrayList<Monster>();
		 monsters.add(new Dragons("Desghidorrah", 3,300,400,35));
		 monsters.add(new Dragons("Chrysophylax", 2,200,500,20));
		 monsters.add(new Dragons("BunsenBurner", 4,400,500,45));
		 monsters.add(new Dragons("Natsunomeryu", 1,100,200,10));
		 monsters.add(new Dragons("TheScaleless", 7,700,600,75));
		 monsters.add(new Dragons("Phaarthurnax", 6,600,700,60));
		 monsters.add(new Dragons("D-Maleficent", 9,950,950,85));
		 monsters.add(new Dragons("TheWeatherbe", 8,800,900,80));
		 monsters.add(new ExoSkeletons("Cyrrollalee", 7,700,800,75));
		 monsters.add(new ExoSkeletons("Brandobaris", 3,300,450,30));
		 monsters.add(new ExoSkeletons("BigBad-Wolf", 1,150,250,15));
		 monsters.add(new ExoSkeletons("WickedWitch", 2,250,350,25));
		 monsters.add(new ExoSkeletons("Aasterinian", 4,400,500,45));
		 monsters.add(new ExoSkeletons("Chronepsish", 6,650,750,60));
		 monsters.add(new ExoSkeletons("St-Shargaas", 5,550,650,55));
		 monsters.add(new ExoSkeletons("St-Yeenoghu", 9,950,850,90));
		 monsters.add(new Spirits("Andrealphus", 2,600,500,40));
		 monsters.add(new Spirits("Aim-Haborym", 1,450,550,35));
		 monsters.add(new Spirits("Andromalius", 3,550,450,25));
		 monsters.add(new Spirits("Chiang-shih", 4,700,600,40));
		 monsters.add(new Spirits("FallenAngel", 5,800,700,50));
		 monsters.add(new Spirits("Ereshkigall", 6,950,650,35));
		 monsters.add(new Spirits("Jormunngand ", 8,600,900,20));
		 return monsters;
			 
     }
	 //add heros
	 private static List<Hero> addHeros(){
		 List<Hero> allHeros = new ArrayList<Hero>();
		 allHeros.add(new Warriors("Gaerdal_Ironhand", 1,100,700,500,600,1354,7));
		 allHeros.add(new Warriors("Sehanine_Monnbow", 1,600,700,800,500,2500,8));
		 allHeros.add(new Warriors("Muamman_Duathall", 1,300,900,500,750,2546,6));
		 allHeros.add(new Warriors("Flandal_Steelskin", 1,200,750,650,700,2500,7));
		 allHeros.add(new Sorcerers("Garl_Glittergold ", 1,700,550,600,500,2500,7));
		 allHeros.add(new Sorcerers("Rillifane_Rallathil",1,1300,750,450,500,2500,9));
		 allHeros.add(new Sorcerers("Segojan_Earthcaller", 1,900,800,500,650,2500,5));
		 allHeros.add(new Sorcerers("Skoraeus_Stonebones", 1,800,850,600,450,2500,6));
		 allHeros.add(new Paladins("Solonor_Thelandira ", 1,300,750,650,700,2500,7));
		 allHeros.add(new Paladins("Sehanine_Moonbow", 1,300,750,700,700,2500,7));
		 allHeros.add(new Paladins("Skoraeus_Stonebones", 1,250,650,600,350,2500,4));
		 allHeros.add(new Paladins("Garl_Glittergold", 1,100,600,500,400,2500,5));
		 return allHeros;
			 
     }
	 //add weapons
	 private static List<Weaponry> addWeapons(){
		 List<Weaponry> allWeapons = new ArrayList<Weaponry>();
		 allWeapons.add(new Weaponry("Sword", 500,1,800,1));
		 allWeapons.add(new Weaponry("Bow", 300,2,500,2));
		 allWeapons.add(new Weaponry("Scythe",1000,6,1100,2));
		 allWeapons.add(new Weaponry("Axe", 550,5,850,1));
		 allWeapons.add(new Weaponry("Shield", 400,1,100,1));
		 allWeapons.add(new Weaponry("TSwords", 1400,8,1600,2));
		 allWeapons.add(new Weaponry("Dagger", 200,1,250,1));
		 
	
		 return allWeapons;
		 
	}
	 private static List<Armory> addArmors(){
		 List<Armory> allArmors = new ArrayList<Armory>();
		 allArmors.add(new Armory("Platinum_Shield",150,1,200));
		 allArmors.add(new Armory("Breastplate",350,3,600));
		 allArmors.add(new Armory("Full_Body_Armor",1000,8,1100));
		 allArmors.add(new Armory("Wizard_Shield",1200,10,1500));
		 allArmors.add(new Armory("Speed_Boots",550,4,600));
		 
	
		 return allArmors;
		 
	}
	 private static List<Potions> addPotions(){
		 List<Potions> allPotions = new ArrayList<Potions>();
		 allPotions.add(new Potions("Healing_Potion",250,1,100));
		 allPotions.add(new Potions("Strength_Potion",200,1,75));
		 allPotions.add(new Potions("Magic_Potion",350,2,100));
		 
         return allPotions;
     }
	 private static List<Spells> addSpells(){
		 List<Spells> allSpells = new ArrayList<Spells>();
		 allSpells.add(new FireSpells("Flame_Tornado",700,4,850,300));
		 allSpells.add(new FireSpells("Breath_of_Fire",350,1,450,100));
		 allSpells.add(new FireSpells("Heat_Wave",450,2,650,100));
		 allSpells.add(new FireSpells("Lava_Commet ",800,7,1000,550));
		 allSpells.add(new IceSpells("Snow_Canon",500,2,650,250));
		 allSpells.add(new IceSpells("Ice Blade",250,1,450,100));
		 allSpells.add(new IceSpells("Frost_Blizzard",750,5,850,350));
		 allSpells.add(new IceSpells("Arctic_storm",700,6,800,300));
		 allSpells.add(new LightningSpells("LightningDagger",400,1,550,150));
		 allSpells.add(new LightningSpells("Electric_Arrows",550,5,650,400));
		 allSpells.add(new LightningSpells("Thunder_Blast",750,4,950,400));
		 allSpells.add(new LightningSpells("Spark_Needles",500,2,600,200));
		 return allSpells;
     }
	 
	 //show the hero info and allow the player to choose
	 private static void selectHeros(List<Hero> Heros,int numofPlayers) {
		 chosen_hero = new ArrayList<Hero>(numofPlayers);
		 int counter = 1;
		 int input = -100;
		 for (Hero element:Heros) {
			 System.out.println(counter + ". Name: " + element.name + "; Type: " + element.type + "; Mana: " + element.mana + "; start_moeny: "
					 + element.money + "; start_exp: " + element.starting_exp);
			 counter+=1;
		 }
		 Scanner s1 = new Scanner(System.in);
		 System.out.println();
		 for(int i=0;i<numofPlayers;i++) {
			do{
		 		 System.out.print("Player "+ (i+1) + " please choose a hero(1/2/3...12): "); 
		 	 	 input = s1.nextInt();
		 	   }while (valid_input2(input)==false);
		    chosen_hero.add(Heros.get(input-1));
		    list.add(input);
		    System.out.println("Player "+ (i+1) + " has chosen hero " + Heros.get(input-1).getName());
		 }
		 System.out.println();
	 }
	 private static boolean valid_input1(int num) {
		 boolean valid = (num==1 || num ==2 || num==3);
		 if (valid == false) {
			 System.out.println("Invalid input!");
		 }
		 return valid;
	 }
	 private static boolean valid_input2(int num) {
		 if (list.contains(num)) {
			 System.out.println("This hero has already been taken by an another player! Try another one!");
			 return false;
		 }
		 boolean valid = (num<= 12 && num >=1);
		 if (valid == false) {
			 System.out.println("Invalid input!");
		 }
		 return valid;
	 }
		 
}