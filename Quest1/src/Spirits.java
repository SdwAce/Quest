public class Spirits extends Monster {
	public Spirits(String name,int level,int damage,int defense,int dodgechance) {
		super(name,level,damage,defense,dodgechance);
		this.hp = level*100;
		this.type = "Spirit";
		
	}
	
}