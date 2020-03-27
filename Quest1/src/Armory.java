public class Armory extends Goods{
	protected int reduction;
	public Armory(String name,int cost,int required_level,int reduction){
		super(name,cost,required_level);
		this.reduction = reduction;
		this.type = "Armory";
		
		
	}
	
}