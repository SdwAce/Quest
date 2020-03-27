import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Fighter {
	protected String name;
	protected int hp; // current hp
	protected int level;
	protected int damage;
	protected double dodge_chance; 
	protected String type; 
	protected int start_hp;//start hp(max hp)

    public Fighter (String name,int level) {
    	this.name = name;
		this.level = level;
		this.hp = level*100;
		this.start_hp = level*100;
    }
    
    public int getHp() {
         return this.hp;
    }
    public String getName() {
    	return this.name;
    }
    public void decreaseHp(int dmg) {
		this.hp = getHp() - dmg;
	}

    public void attack(Fighter f,double dmg){
        double rand = Math.random();
        int damage = (rand > f.dodge_chance ? (int)dmg : 0);
        if( damage > 0){
           System.out.println(this.name + " deals " + damage + " damage to " + f.name + ".");
           f.hp -= damage;
		   if(f.hp <= 0){
              f.hp = 0;
           }
        }else{
           System.out.println(this.name + "'s attack missed.");
        }
        System.out.println(f.name + " has " + f.hp +  " hp left.");
     }
    


    public static void healthUpdate(Fighter hero, Fighter monster){
 		    System.out.println();
			System.out.println("====CURRENT HEALTH====");       
			System.out.println( hero.name + "'s health: " + hero.hp + "hp");
            System.out.println( monster.name + "'s health: " + monster.hp + "hp");
			System.out.println();
    }
    public boolean isAlive(){
        if(this.hp > 0)
           return true;
        else
           return false;
     }
    protected int get_dmg(){
		return this.damage;
	}
    
}