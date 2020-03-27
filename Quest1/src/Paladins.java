public class Paladins extends Hero{
	public Paladins(String name,int level,int mana,int strength,int agility,int dexterity,int starting_money,int starting_exp){
		super(name,level,mana,strength,agility,dexterity,starting_money,starting_exp);
		this.type = "Paladin";
		this.hp = level*100;
		this.start_hp = this.hp;
		
	}
	@Override
	public void levelup() {
		super.levelup();
		this.strength = (int)(1.10*this.strength);
		this.agility = (int)(1.05*this.agility);
		this.dexterity = (int)(1.10*this.dexterity);
    }
	
}