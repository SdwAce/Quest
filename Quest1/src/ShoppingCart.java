import java.util.ArrayList;
import java.util.List;
public class ShoppingCart{
	protected List<Goods> items;
	protected int total;
	public ShoppingCart() {
		items = new ArrayList<Goods>();
		total = 0;
	}
	//add item to cart
	public void addItem(Hero h,Goods item) {
		if (item.reachLevel(h)) {
		    items.add(item);
		    total += item.cost;
		    printCart();
		}else {
			System.out.println("You didn't reach the requried level for the good you just selected!");
			printCart();
		}
	}
	//remove item from cart
	public void removeItem(int i) {
		total -= items.get(i-1).cost;
		items.remove(i-1);
		System.out.println("Item removed!");
		printCart();
	}
	
	public void printCart(){
		System.out.println();
		System.out.println("-------------");
		if (items.size()==0) {
			System.out.println("The cart is Empty!");
		}else {
		  for (int counter = 0; counter < items.size(); counter++) {
			Goods element = items.get(counter);
			System.out.print("Item "+ (counter+1) + ":");
			System.out.print("| ");
			System.out.print("Name:"+element.name+ " Type:"+element.type+ "; Req level: "+element.required_level+"; Cost:"+element.getCost() + " | ");
			System.out.println();
			System.out.println("-------------");
		}
		  System.out.println("Total cost: " + this.total);
	    }
	}
	
	public boolean valid_input(int choice) {
		return (choice>0 && choice<=items.size());
    }
	
		
		
		
	}
