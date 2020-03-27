public class Monster extends Fighter implements DodgeAttack{
	protected int damage;
	protected int defense;
	protected int dodgechance;
	
	public Monster(String name,int level,int damage,int defense,int dodgechance){
		super(name,level);
		this.hp = level*100;
		this.damage = damage;
		this.defense = defense;
		this.dodgechance = dodgechance; 
		dodge_chance();
		this.type = "Monster";
		
	}
	@Override
	public void dodge_chance() {
		this.dodge_chance = (this.dodgechance*0.01)/2.0;
	}
	public int dmg_calc(Hero hero) {
		int dmg;
		if (hero.curr_armory != null) {
		    dmg = this.damage- hero.curr_armory.reduction;
		}else {
			dmg = this.damage-0;
		}
		if (dmg<0) {
			dmg = 0;
		}
		return (int)(dmg*0.25);
	}
	public void monster_attack(Hero hero) {
		//Monster damage fomula
		int dmg = (int)(dmg_calc(hero)*0.8);
		this.attack(hero, dmg);
	}
	
}