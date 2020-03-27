public class Spells extends Goods implements Damage{
	protected int mana_cost;
	protected int damage;
	public Spells(String name,int cost,int required_level,int damage,int mana_cost){
		super(name,cost,required_level);
		this.damage = damage;
		this.mana_cost = mana_cost;
		this.type = "Spell";
	}
	public int finaldmg(Hero hero,Monster monster) {
		int base_dmg = this.damage;
		int dexterity = hero.dexterity;
		//damage fomula for spells
		int dmgcal = (int)((base_dmg + (dexterity/10000)*base_dmg-monster.defense)*0.20);
		if (dmgcal <0) {
			dmgcal = 0;
		}
		return dmgcal;
	}
	
	//launch the spell attack
	public void launch(Hero hero,Monster monster) {
		if ((hero.mana - this.mana_cost)<0){
			System.out.println("Your mana is not enough! You will launch normal attack!");
			hero.normal_attack(hero, monster);
		}else {
		   //decrease mana
		   hero.mana -= this.mana_cost;
		   int dmg = finaldmg(hero,monster);
		   hero.attack(monster, dmg);
		}
		
	}
	
}