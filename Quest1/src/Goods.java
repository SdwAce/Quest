public class Goods{
	protected String name;
	protected int cost;
	protected int required_level;
	protected String type;
	
	public Goods (String name,int cost,int required_level) {
    	this.name = name;
    	this.cost = cost;
		this.required_level = required_level;
		
    }
	public String getName() {
		return this.name;
	}
	public int getCost() {
		return this.cost;
	}
	public int getReqLevel() {
		return this.required_level;
	}
	public String getType() {
		return this.type;
		
	}
	public int hashCode() {
		return name.hashCode() + cost;
	}
	//determine wheather a hero's level reach the min required level 
	public boolean reachLevel(Hero h) {
		if (h.level<this.getReqLevel()) {
		    return false;
		}else {
		   return true;
		}
    }
	
	
}