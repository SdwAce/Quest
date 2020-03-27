import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Purchase{
	protected List<Hero> heros;
	protected Market market;
	protected int start_num; //num of starting_members;
	protected Goods choicemade;
	protected Hero action_hero;
	protected boolean act;
	public boolean isOver;
	protected int choice1;
	protected int choice2;
	
	public Purchase(List<Hero> heros,Market market) {
		this.heros = heros;
		this.market = market;
		
        this.start_num = heros.size();
        this.act = true;
        this.isOver=false;
	}
	public void purchase_run() {
		Scanner s1 = new Scanner(System.in);
		System.out.println("Welcome to the market!");
		 //Hero Act
		 for (int counter = 0; counter < heros.size(); counter++)  {
			act = true;
			isOver = false;
		    //Get the hero
		    Hero hero = heros.get(counter);
		    //Print necessary contents
		    System.out.println(hero.name + " 's turn!");
		    //ask the player whether he wants to purchase or not
		    actorNot();
		    if (act) {
		      do {
		        do {
		    	   System.out.print("Please type valid message.");
		    	   System.out.println("Select the type you want to purchase:"+"\n"+ "1. Weapons"+"\n"+ "2.Armors"+"\n"+"3. Spells"+"\n"+"4. Potions");
		    	   choice1 = s1.nextInt(); 
		           }while (valid_input(choice1) == false);
		           //list the items
		        market.printMarket(choice1);
		        do {
		    	  System.out.print("Please type valid message.");
		          System.out.print("Select the item you want: ");
		          choice2 = s1.nextInt();
		        }while (valid_input2(choice1,choice2) == false);
		        choicemade = market.goods.get(choice1-1).get(choice2-1);
		        //add item to shopping cart
		        System.out.println("Your shopping cart: ");
		        hero.cart.addItem(hero,choicemade);
		        
		        again();
		      }while (isOver !=true);
		      //checkout
		      hero.purchase();
		    }
		 }
		 System.out.println();
		 System.out.println("All the players have already made their choice in market!");
		 System.out.println("Thanks for your visit! ByeBye!");
		 System.out.println();
	}
		 
	public void actorNot(){
		System.out.println("Do you want to purchase items? (y/n)");
		Scanner s1 = new Scanner(System.in);
		String a = s1.next();
		if (a.charAt(0)=='n') {
			act = false;
			System.out.println("Then let the next player do the purchase!");
		}else if ((a.charAt(0)!='y') &&(a.charAt(0)!='n')){
			System.out.println("Invalid Message, retype please!");
			actorNot();
		}
	}
	//check valid input for heros
	public boolean valid_input(int choice) {
			return (choice==1 || choice==2 || choice==3 || choice ==4);
	}
	
	public boolean valid_input2(int choice_1,int choice_2) {
		List<? extends Goods> item_list = market.goods.get(choice_1-1);
		return (choice_2>0 && choice_2<=item_list.size());
    }
	/* check whether the players want to select an another item*/
	public void again(){
		System.out.println("Do you want to start an select an another item? (y/n)");
		Scanner s1 = new Scanner(System.in);
		String a = s1.next();
		if (a.charAt(0)=='n') {
			isOver = true;
		}else if ((a.charAt(0)!='y') &&(a.charAt(0)!='n')){
			System.out.println("Invalid Message, retype please!");
			again();
			
		}
	}
	
	
	
	
}