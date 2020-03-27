import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Battle{
	protected static List<Hero> init_heros; // all heros participate in the battle
	protected static List<Hero> curr_heros; // heros not faint
	protected static List<Monster> init_monsters;  // all monsters participate in the battle
	protected static List<Monster> curr_monsters; // monsters not faint
	protected int start_num; //num of starting_members;
	protected int round;
	protected Goods choicemade;
	protected Hero action_hero;
	protected Monster action_monster;
	protected String hero_action;
	protected String winner;
	public Battle(List<Hero> heros,List<Monster> monsters,int num) {
		init_heros = heros;
		curr_heros = makeCopyHero(init_heros);
		init_monsters = monsters;
        curr_monsters = makeCopyMonster(init_monsters);
        this.start_num = num;
        this.round = 1;
        this.hero_action = "";
	}
	
	//make copy of hero arraylist
	private List<Hero> makeCopyHero(List<Hero> old){
	    List<Hero> copy = new ArrayList<Hero>(old.size());
	    for(Hero h : old){
	        copy.add(h);
	    }
	    return copy;
	}
	//make copy of mosnter arraylist
	private List<Monster> makeCopyMonster(List<Monster> old){
	    List<Monster> copy = new ArrayList<Monster>(old.size());
	    for(Monster h : old){
	        copy.add(h);
	    }
	    return copy;
	}
	
	public void battle_run() {
		Scanner s1 = new Scanner(System.in);
		String choice;
		System.out.println("Battle encountered! Ready to fight!");
		do {
		 System.out.println();
		 System.out.println("      Round " + round);
		 System.out.println();
		 //Hero's regain some hp
		 recover(curr_heros);
		 //Print statistics
		 printStatistics();
		 System.out.println();
		 //Hero Act
		 System.out.println("Heros take action first!");
		 for (int counter = 0; counter < curr_heros.size(); counter++)  {
		    //Get the hero
		    Hero hero = curr_heros.get(counter);
		    //Print necessary contents
		    System.out.println(hero.name + " 's turn!");
		    do {
		    System.out.println("Select your action(type a/b/c/d): ");
		    System.out.println("a. Weapon_Att"+"\n"+ "b. Spell"+"\n"+"c. Potions" + "\n" + "d. Normal_Attack");
		    choice = s1.next();
		    }while (valid_input(choice) == false);
		    //choose the monster being attacked
		    Monster monster_attacked = heroLogic(counter);
		    hero_act(choice,hero,monster_attacked);
		    //remove faint
		    remove(curr_monsters);
		 }
		 System.out.println();
		 // Monster Act
		 System.out.println("Monster's turn!");
		 for (int counter = 0; counter < curr_monsters.size(); counter++)  {
			    //Get the hero
			    Monster monster = curr_monsters.get(counter);
			    //Print necessary contents
			    System.out.println(monster.name + " 's turn!");
			    //choose the monster being attacked
			    Hero hero_attacked = monsterLogic(counter);
			    monster.monster_attack(hero_attacked);
			    //remove faint
			    remove(curr_heros);
		}
		 round+=1;
		}while(isOver() == false);
	}
	
	//hp recover for each battle
	protected void recover(List<Hero> Heros) {
		for (Hero hero : Heros) {
			hero.hp+= (int)(hero.start_hp*0.05);
			if (hero.hp>=hero.start_hp) {
				hero.hp = hero.start_hp;
			}
			hero.mana+= (int)(hero.start_mana*0.05);
			if (hero.mana>=hero.start_mana) {
				hero.mana = hero.start_mana;
			}
		}
	}
	
	
	//check valid input for heros
	public boolean valid_input(String choice) {
		return (choice.equals("a") || choice.equals("b") || choice.equals("c") || choice.equals("d"));
	}
	public boolean valid_input2(int i,List<? extends Goods> items,Hero hero) {
		if (i == 2 && hero.mana ==0) {
			System.out.println("Your mana is not ennough to launch spell attack!");
			return false;
		}
		return (i>0 && i<=items.size());
	}
	
	//print list of options
	public void printlist(List<? extends Goods> list) {
		Spells spell;
		if (list.get(0).type.equals("FireSpell") || list.get(0).type.equals("IceSpell") || list.get(0).type.equals("LightningSpell")) {
			for (int i =0;i<list.size();i++) {
				spell = (Spells) (list.get(i));
				System.out.println("Item"+(i+1) + " Name: " + spell.getName() + "; Type: " + spell.getType() + 
						"; Mana Cost: " + spell.mana_cost );
			}
			
		}else {
		  for (int i =0;i<list.size();i++) {
			System.out.println("Item"+(i+1) + " Name: " + list.get(i).getName() + "; Type: " + list.get(i).getType());
		  }
		}
	}

	
	//help hero choose which monster to attack
	public Monster heroLogic(int counter) {
		Monster monster_attacked;
		int difference = curr_heros.size()-curr_monsters.size();
		if (difference ==0) {
			monster_attacked = curr_monsters.get(counter);
		}else if (difference == 1 || difference == -1) {
			if (counter ==0) {
				monster_attacked = curr_monsters.get(0);
			}else {
				monster_attacked = curr_monsters.get(curr_monsters.size()-1);
			}
		}else {
			monster_attacked = curr_monsters.get(0);
		}
		return monster_attacked;
	}
	
	//help monster choose which hero to attack
	public Hero monsterLogic(int counter) {
		Hero hero_attacked;
		int difference = curr_monsters.size()-curr_heros.size();
		if (difference ==0) {
			hero_attacked = curr_heros.get(counter);
		}else if (difference == 1 || difference == -1) {
			if (counter ==0) {
				hero_attacked = curr_heros.get(0);
			}else {
				hero_attacked = curr_heros.get(curr_heros.size()-1);
			}
		}else {
			hero_attacked = curr_heros.get(0);
		}
		return hero_attacked;
	}
	
	//hero make choice on his action and the pacific good he is using
	public void makechoice(String choice,Hero hero) {
		int option;
		List<? extends Goods> goods;
		if (choice.equals("d")){
			hero_action="Normal_Attack";
			return;
		}else if (choice.equals("a")&& hero.weaponry_list.size()>0){
	       goods = hero.weaponry_list;
	       hero_action = "Weapon";
		}else if (choice.equals("b")&& hero.spell_list.size()>0) {
		   goods = hero.spell_list;
		   hero_action = "Spell";
		}else if (choice.equals("c")&& hero.potion_list.size()>0){
		   goods = hero.potion_list;
		   hero_action = "Potion";
		}else {
			System.out.println("You can only make normal attack since you don't have this type of goods.");
			hero_action="Normal_Attack";
			return;
		}
		printlist(goods);
		do {
		System.out.print("Please type an valid input. Select the item you want to use (1/2/3...):");
		Scanner s1 = new Scanner(System.in);
		option = s1.nextInt();
		} while(valid_input2(option,goods,hero)==false);
		choicemade = hero.choose(option,goods);
	}

	//hero's real action after making choice
	public void hero_act(String choice,Hero hero,Monster monster) {
		makechoice(choice,hero);
		if (hero_action.equals("Weapon")) {
			Weaponry curr_choice = (Weaponry) choicemade;
			curr_choice.launch(hero,monster);
		} else if (hero_action.equals("Spell")) {
			Spells curr_choice = (Spells) choicemade;
			curr_choice.launch(hero,monster);
		}else if (hero_action.equals("Potion")) {
			Potions curr_choice = (Potions) choicemade;
			curr_choice.implement(hero);
			hero.potion_list.remove(curr_choice);
			
		}else if (hero_action.equals("Normal_Attack")) {
			hero.normal_attack(hero, monster);
		}
	}
	
	//remove a fighter when a hero or monster faints
	public void remove(List<? extends Fighter> fighters) {
    	 for (int counter = 0; counter < fighters.size(); counter++)  {
    		 if (fighters.get(counter).isAlive() == false) {
    			 System.out.println(fighters.get(counter).getName() + " is faint and can no longer fight!" );
    			 fighters.remove(counter);
    			 
    		 }
    	 }
    }
	
	//print statistic of heros and monsters
	public void printStatistics() {
		System.out.println("Heros");
		System.out.println("-------------");
		for (int counter = 0; counter < curr_heros.size(); counter++) {
			Hero element = curr_heros.get(counter);
			System.out.print("Hero "+ (counter+1) + ":");
			System.out.print("| ");
			System.out.print("Name:"+element.name+  " Hp:"+ element.hp + " Mana:"+element.mana + " | ");
			System.out.println();
			System.out.println("-------------");
		}
		System.out.println("Monsters");
		System.out.println("-------------");
		for (int counter = 0; counter < curr_monsters.size(); counter++) {
			Monster element = curr_monsters.get(counter);
			System.out.print("Monster "+ (counter+1) + ":");
			System.out.print("| ");
			System.out.print("Name:"+element.name+ " Type: " + element.type + " Level: " + element.level + " Hp:"+ element.hp + " Damage:" + element.damage + " Defense:"+ element.defense + " | ");
			System.out.println();
			System.out.println("-------------");
		}
	}
	
	
	
	//check whether the battle is over
	public static boolean isOver() {
		if (curr_heros.isEmpty()==true) {
			System.out.println("Monster wins!");
			for (Hero hero : init_heros) {
				if (hero.money>0) {
				hero.money-= (int)(hero.money*0.5);
				}else {
					hero.money = 0;
				}
				hero.hp+=(int)(hero.start_hp*0.5);
				
			}
			for (Monster monster : init_monsters) {
				monster.hp = monster.start_hp;
			}
			
	        return true;
		}else if (curr_monsters.isEmpty()==true) {
			System.out.println("Hero wins!");
			for (Hero hero : init_heros) {
				hero.money+= 150;
				if (hero.hp<=0) {
					hero.hp+= (int)(hero.start_hp*0.5);
				}
			}
			for (Hero hero : curr_heros) {
				hero.curr_exp+=2;
				hero.hp+=(int)(hero.start_hp*0.2);
				if (hero.hp>=hero.start_hp) {
					hero.hp = hero.start_hp;
					
				}
			}
			for (Monster monster : init_monsters) {
				monster.hp = monster.start_hp;
			}
			return true;
		}
		return false;
	}
}