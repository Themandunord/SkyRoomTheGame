package monster;

import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import player.Player;

/**
 * Classe créant le BOSS Mickael Jackson
 * 
 * @author Rémy
 *
 */

public class MJ extends Monster {

	/** Lumière autour du perssonnage */
	private Image light;
	/** Permet de faire revenir MJ à la vie */
	private boolean restartMJ;
	/** Délai entre 2 morts consécutives */
	private int cptDead;
	
	/**
	 * Constructeur par défaut 
	 * on lui donne une vitesse de 0.07
	 */
	public MJ(){
		super(0.07f,1);
	}
	
	/**
	 * Initialisation du monstre et de la lumière
	 */
	public void init() throws SlickException{
		super.init();
		super.MonsterAnim("MJ",32,32);
		light = new Image("res/all/light2.png");
	}
	
	/**
	 * On affiche le monstre et on affiche la lumière
	 * 
	 * @param g Sortie de l'écran
	 */
	public void renderBot(Graphics g) throws SlickException{
		super.renderBot(g);
		light.draw(-795 + Player.getX(), -955 + Player.getY());

	}
	
	public void update(GameContainer gc, int delta) throws SlickException{
		super.update(gc, delta);
		
		if(!alive){
			cptDead++;
			if(cptDead>80){
				restartMJ = true;
				cptDead = 0;
			}
		}
		
		if(restartMJ){
			do{
				xM = (float) (Math.random()*480 +160);
				yM = (float) (Math.random()*280 +160);
			} 
			while(Map.isBlocked(xM, yM)||(Map.isBlocked(xM+31, yM+31)) 
					|| (Map.isBlocked(xM, yM+31))||(Map.isBlocked(xM+31, yM)));
			restartMJ =false;
			alive = true;
		}
		
	}
}
