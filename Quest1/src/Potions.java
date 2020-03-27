public class Potions extends Goods{
	protected int attribute_increase;
	public Potions(String name,int cost,int required_level,int attribute_increase){
		super(name,cost,required_level);
		this.attribute_increase = attribute_increase;
		this.type = "Potion";
	}
	public void implement(Hero hero) {
		if (this.name.equals("Healing_Potion")) {
			hero.hp += 100;
			if (hero.hp > hero.start_hp) {
				hero.hp = hero.start_hp;
			}
		}else if (this.name.equals("Strength_Potion")) {
			hero.strength+=75;
		}else if (this.name.equals("Magic_Potion")) {
			hero.mana +=100;
			if (hero.hp > hero.start_mana) {
				hero.hp = hero.start_mana;
			}
		}
	}
	
}