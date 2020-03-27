public class Weaponry extends Goods implements Damage{
	protected int required_hands;
	protected int damage;
	public Weaponry(String name,int cost,int required_level,int damage,int required_hands){
		super(name,cost,required_level);
		this.damage = damage;
		this.required_hands = required_hands;
		this.type= "Weapon";
		
	}
	public int finaldmg(Hero hero, Monster monster) {
		int base_dmg = this.damage-monster.defense;
		int strength = hero.strength;
		int dmgcal = (int)((strength + base_dmg)*0.08);
		if (dmgcal<=0) {
			dmgcal =0;
		}
		return dmgcal;
	}
	@Override
	//launch the weapon attack
	public void launch(Hero hero,Monster monster) {
		int dmg = finaldmg(hero,monster);
		hero.attack(monster, dmg);
		
	}
	
}