package player;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 * Classe créant une épée sur le joueur
 * 
 * @author Rémy
 *
 */

public class Sword {

	/** SPriteSheet de l'épée */
	private SpriteSheet swordUpSprite,swordDownSprite,swordRightSprite,swordLeftSprite;
	/** Animation de l'épée */
	private Animation swordUp,swordDown,swordLeft,swordRight;
	/** Rectangle de collision de l'épée */
	private static Rectangle rec;
	/** Permet d'éviter que le rectangle ne se forme lorsque le joueur ne tape pas */
	public static boolean isHere;
	
	
	/**
	 * Instancie les Animations et le rectangle
	 */
	public Sword(){
		try {
			swordUpSprite = new SpriteSheet("res/hero/swordUp.png", 32, 32);
			swordDownSprite = new SpriteSheet("res/hero/swordDown.png", 32, 32);
			swordLeftSprite = new SpriteSheet("res/hero/swordLeft.png", 32, 32);
			swordRightSprite = new SpriteSheet("res/hero/swordRight.png", 32, 32);
		} catch (SlickException e) {e.printStackTrace();}
		swordUp = new Animation(swordUpSprite,0,0,2,0,true,300,true);
		swordDown = new Animation(swordDownSprite,0,0,2,0,true,300,true);
		swordLeft = new Animation(swordLeftSprite,0,0,2,0,true,300,true);
		swordRight = new Animation(swordRightSprite,0,0,2,0,true,300,true);
		rec = new Rectangle(0, 0, 0, 0);
	}
	
	/**
	 * Affiche l'animation haute de l'épée
	 * @param g
	 */
	public void renderUp(Graphics g){
		swordUp.start();
		swordUp.setLooping(false);
		swordUp.draw(Player.getX()+10, Player.getY()-16);
	}
	
	/**
	 * Affiche l'animation basse de l'épée
	 * @param g
	 */
	public void renderDown(Graphics g){
		swordDown.start();
		swordDown.setLooping(false);
		swordDown.draw(Player.getX()-7, Player.getY()+20);
		rec.setBounds((int)Player.getX()+10, (int)Player.getY()+25, 5, 20);
		isHere = true;
	}
	/**
	 * Update du renderUp
	 */
	public void UpdateUp(){
		rec.setBounds((int)Player.getX()+24, (int)Player.getY()-11, 5, 25);
		isHere = true;
	}
	
	/**
	 * Affiche l'animation gauche de l'épée
	 * @param g
	 */
	public void renderLeft(Graphics g){
		swordLeft.start();
		swordLeft.setLooping(false);
		swordLeft.draw(Player.getX()-14, Player.getY()+8);
		rec.setBounds((int)Player.getX()-14, (int)Player.getY()+23, 20, 5);
		isHere = true;
	}
	
	/**
	 * Affiche l'animation droite de l'épée
	 * @param g
	 */
	public void renderRight(Graphics g){
		swordRight.start();
		swordRight.setLooping(false);
		swordRight.draw(Player.getX()+15, Player.getY()+10);
		rec.setBounds((int)Player.getX()+20, (int)Player.getY()+20, 20, 5);
		isHere = true;
	}
	
	public void update(){
		if(!isHere){
			swordDown.restart();
			swordLeft.restart();
			swordRight.restart();
			swordUp.restart();
		}
	}
	
	/**
	 * @return rec Rectangle de collision
	 */
	public static Rectangle getRect(){
		return rec;
	}
	/**
	 * Retourne l'animation de l'épée
	 * 
	 * @param i
	 * @return
	 */
	public Animation getAnim(int i){
		if(i==0) return swordUp;
		else if(i==1) return swordDown;
		else if(i==2) return swordLeft;
		return swordRight;
	}
}
