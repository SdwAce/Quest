import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Hero extends Fighter implements DodgeAttack{
	protected int start_mana; //start_mana(full mana)
	protected int mana;
	protected int strength;
	protected int agility;
	protected int dexterity;
	protected int money;
	protected int starting_exp;
	protected int curr_exp;
	protected ShoppingCart cart;
	protected Armory curr_armory;
	protected List<Armory> armory_list;
	protected List<Weaponry> weaponry_list;
	protected List<Spells> spell_list;
	protected List<Potions> potion_list;
	
	
	public Hero(String name,int level,int mana,int strength,int agility,int dexterity,int starting_money,int starting_exp){
		super(name,level);
		this.hp = level*100;
		this.start_mana = mana;
		this.mana = mana;
		this.strength = strength;
		this.agility = agility;
		this.dexterity = dexterity;
		this.money = starting_money;
		this.starting_exp = starting_exp;
		this.curr_exp = starting_exp;
		dodge_chance();
		this.cart = new ShoppingCart();
		this.weaponry_list = new ArrayList<Weaponry>();
		this.armory_list = new ArrayList<Armory>();
		this.spell_list = new ArrayList<Spells>();
		this.potion_list = new ArrayList<Potions>();
		this.curr_armory = null;
	}
	protected void levelup() {
		this.level+=1;
		this.start_mana = (int)(this.start_mana + this.start_mana*0.1);
		this.mana = this.start_mana;
		this.start_hp = (this.level)*100;
		this.hp=(this.level)*100;
	}
	
	@Override
	public void dodge_chance() {
		this.dodge_chance = (this.agility)*0.02*0.01;
	}
	protected Goods choose(int i,List<? extends Goods> goods) {
		//if the choice is out of range, then choose the first good availiable
		if (i>goods.size() || i<=0 ) {
			return goods.get(0);
		}else {
			return goods.get(i-1);
		}
	}
	protected void purchase() {
		System.out.println("Checkout!");
		int choice;
		Scanner s1 = new Scanner(System.in);
		while(this.money<cart.total) {
			this.cart.printCart();
			System.out.println("The money you have is " + this.money + " and is still not enough!");
			do {
			System.out.println("Please enter the item number that you want to remove: "); 
			choice = s1.nextInt(); 
			}while (this.cart.valid_input(choice) == false);
			this.cart.removeItem(choice);
		};
		System.out.println("You have sucessfully purchased these items!");
		this.money -= this.cart.total;
		System.out.println("You have " + this.money + " left" +"!");
		//add items to hero's storage
		for (int counter = 0; counter < cart.items.size(); counter++) {
			if (cart.items.get(counter).type.equals("Weapon")) {
				weaponry_list.add((Weaponry) cart.items.get(counter));
			}else if (cart.items.get(counter).type.equals("Armory")) {
				armory_list.add((Armory) cart.items.get(counter));
			}else if (cart.items.get(counter).type.equals("FireSpell")) {
				FireSpells firespell =  (FireSpells) cart.items.get(counter);
				spell_list.add(firespell);
			}else if (cart.items.get(counter).type.equals("LightningSpell")) {
				LightningSpells lightningspell =  (LightningSpells) cart.items.get(counter);
				spell_list.add(lightningspell);
			}else if (cart.items.get(counter).type.equals("IceSpell")) {
				IceSpells icespell =  (IceSpells) cart.items.get(counter);
				spell_list.add(icespell);
			}else if (cart.items.get(counter).type.equals("Potion")) {
				potion_list.add((Potions) cart.items.get(counter));
			}
		}
		this.cart.items.clear();
		this.cart.total = 0;
		//automatically change to the best armor;
		changeArmor();
		
	}
	protected void changeArmor() {
		if(curr_armory!=null && armory_list.size()>0) {
		  for (int counter = 0; counter < armory_list.size(); counter++) {
			if (armory_list.get(counter).reduction > curr_armory.reduction) {
				curr_armory = armory_list.get(counter);
			}
		   }
		}else {
			curr_armory = null;
		}
	}
	//normal attack damage
	public void normal_attack(Hero hero,Monster monster) {
		int dmg = (int)((hero.strength-monster.defense)*0.08);
		if (dmg<=0) {
			dmg=0;
		}
		hero.attack(monster, dmg);
		
	}
	
	
}