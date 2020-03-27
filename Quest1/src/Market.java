import java.util.ArrayList;
import java.util.List;
public class Market{
	protected List<Weaponry> weapons;
	protected List<Armory> armors;
	protected List<Potions> potions;
	protected List<Spells> spells;
	protected List<List<? extends Goods>> goods;
	
	public Market(List<Weaponry> weapons,List<Armory> armors,List<Spells> spells,List<Potions> potions) {
		this.weapons = weapons;
		this.armors = armors;
		this.potions = potions;
		this.spells = spells;
		this.goods = new ArrayList<List<? extends Goods>>();
		this.goods.add(this.weapons);
		this.goods.add(this.armors);
		this.goods.add(this.spells);
		this.goods.add(this.potions);
		
	}
	public void printMarket(int choice) {
		System.out.println("-------------");
		List<? extends Goods> item_list = goods.get(choice-1);
		for (int counter = 0; counter < item_list.size(); counter++) {
			Goods element = item_list.get(counter);
			System.out.print("Item "+ (counter+1) + ":");
			System.out.print("| ");
			System.out.print("Name:"+element.name+ "; Type:"+element.type+ "; Req level: "+ element.getReqLevel() + "; Cost:"+element.getCost() + " | ");
			System.out.println();
			System.out.println("-------------");
		}
	}
	
	
	
	
	
}