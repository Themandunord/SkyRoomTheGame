package monster;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import player.Player;

/**
 * Classe créant le zombie fille
 * 
 * @author Alexandre
 *
 */
public class GirlZombie extends Monster{
	
	/**
	 * Constructeur par défaut
	 * On donne une vitesse de 0.03 aux zombies
	 */
	public GirlZombie(){
		super(0.01f,200);
	}
	public void init() throws SlickException
	{
		super.init();
		super.MonsterAnim("zombie1",32,32);
	}
	public void update(GameContainer gc, int delta) throws SlickException{
		super.update(gc, delta);
		
		if(Math.sqrt(Math.pow(xM-Player.getX(),2)+Math.pow(yM - Player.getY(),2)) < 162){
			speed = 0.1f;
		}
		else speed = 0.02f;
	}

}
