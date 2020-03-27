import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game{
	protected Board board;
	protected boolean isOver;
	protected Market currMarket;
	protected List<Monster> allMonsters;
	protected List<Monster> currMonsters;
	protected List<Hero> chosen_Heros; 
	protected Team team;
	protected String choice;
	
	protected List<Weaponry> allweapons;
	protected List<Armory> allarmors;
	protected List<Potions> allpotions;
	protected List<Spells> allspells;
	
	public Game(Board board,List<Monster> allMonsters,List<Hero> chosen_Heros,List<Weaponry> allweapons,List<Armory> allarmors,List<Potions> allpotions,List<Spells> allspells,int start_row,int start_col) {
		this.board= board;
		this.allMonsters = allMonsters;
		this.chosen_Heros = chosen_Heros;
		this.team = new Team(chosen_Heros,chosen_Heros.size(),start_row,start_col);
		this.allweapons = allweapons;
		this.allarmors = allarmors;
		this.allspells = allspells;
		this.allpotions = allpotions;
        isOver = false;
    }
	//run the game
	public void game_run() {
		board.board[team.curr_row][team.curr_col] = 'P';
		do {
		 board.printBoard();
		 Scanner s1 = new Scanner(System.in);
		 levelup();
		 do {
			makeChoice(); 		
		 }while (valid_input(choice)==false);
		 if (choice.equals("q")) {
			isOver=true;
		 }else if (choice.equals("i")){
			 pritnInfo();
	     }else {
			team.makeMove(board, choice);
			meetSpecial(board);
		 }
		}while(isOver == false);
		System.out.println("Game is over! ByeBye!");
		
	}
	public void levelup() {
		for (Hero hero:chosen_Heros) {
			if (hero.curr_exp >= hero.level*10) {
				System.out.println("Hero "+hero.name + " levels up to " + (hero.level+1) + " !");
				hero.levelup();
			}
		}
		
	}
	
	/*handle special cases, like level up, battle or market*/
	public void meetSpecial(Board board1) {
		Random rand = new Random();
		int curr_row = team.curr_row;
		int curr_col = team.curr_col;
		if (board1.boardcells[curr_row][curr_col].type.equals("market")) {
			currMarket = new Market(allweapons,allarmors,allspells,allpotions);
			Purchase purchase_new = new Purchase(team.heros,currMarket);
			purchase_new.purchase_run();
		}else if (board1.boardcells[curr_row][curr_col].type.equals("common")) {
			int random_int = rand.nextInt(10);
			//start battle
			if (random_int<7) {
				currMonsters = getRandomMonster(allMonsters,team.num);
				Battle battle_new = new Battle(team.heros,currMonsters,team.num);
				battle_new.battle_run();
			}
			
		}
	}
	
	//Randomly pick monsters from all the monsters
	public List<Monster> getRandomMonster(List<Monster> monsters,int total_num){
		List<Integer> list = new ArrayList<Integer>();
		int highest_level = 0;
		for (Hero hero : chosen_Heros) {
			if (hero.level>=highest_level) {
				highest_level = hero.level;
			}
			
		}
		 Random rand = new Random(); 
		 List<Monster> newList = new ArrayList<>(); 
		 while (newList.size()<total_num) { 
			    // take a random index between 0 to size 
	            // of given List 
	            int randomIndex = rand.nextInt(monsters.size()); 
	            if (monsters.get(randomIndex).level<=highest_level && list.contains(randomIndex)==false) {
	               // add element in temporary list 
	               newList.add(monsters.get(randomIndex)); 
	               list.add(randomIndex);
	            }
		 }
	        return newList; 
	 }
	public void makeChoice() {
		Scanner s1 = new Scanner(System.in);
		
		System.out.println("Team please choose your action: ");
		System.out.println("1.w(move up) 2.a(move left) 3.s(move down) 4.d(move right) 5.q(quit) 6.i(info)"); 
		choice  = s1.next();
    }
     
	
	  //check input
      public boolean valid_input(String str) {
    	  if (str.equals("w")==false && str.equals("a")==false && str.equals("s")==false && str.equals("d")==false && str.equals("q")==false && str.equals("i")==false){
    		  System.out.println("Invalid input! Please try again.");
    		  return false;
    	  }else if (board.valid_move(team,str)==false) {
    		  System.out.println("Your intended destination cannot be accessed! Try another option!");
    		  return false;
    	 }
	  
	     return true;
      }
      public void pritnInfo() {
    	  for (Hero hero:chosen_Heros) {
    	      System.out.println("Player Name: " + hero.name + "\n" + "Level: " + hero.level + "\n" + 
    	      "Hp: " + hero.hp + "\n" + "Mana: " + hero.mana + "\n" + "Money:" +  hero.money + "\n" +"Curr_Exp:" + hero.curr_exp);
    	      System.out.println();
    	      System.out.println();
    	  }
      }

	
}