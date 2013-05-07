package monster;

import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Classe créant des Dinosaures de deux couleurs
 * différentes
 * 
 * @author Rémy
 *
 */
public class Dino extends Monster{
	/**
	 * Constructeur par défaut
	 * On donne une vitesse de 0.05 aux dinosaures
	 */
	public Dino(){
		super(0.06f,10);
	}
	
	/**
	 * On initialise un sprite aléatoirement
	 */
	public void init() throws SlickException{
		super.init();
		// On charge un sprite aléatoirement
		if(Math.random()<0.5){
			super.MonsterAnim("dinoRouge",32,32);
		}
		else super.MonsterAnim("dinoVert",32,32);		
	}
	
	public void update(GameContainer gc, int delta) throws SlickException{
		super.update(gc, delta);
		if(Map.isSlow(xM+5, yM+25)){
			speed = 0.04f;
		}else speed = 0.06f;
	}
	public void renderBot(Graphics g) throws SlickException{
		super.renderBot(g);
	}
}
