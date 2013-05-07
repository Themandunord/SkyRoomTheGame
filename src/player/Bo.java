package player;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 * Classe créant une épée sur le joueur
 * 
 * @author Rémy
 *
 */

public class Bo {
	/** Animation du bo */
	private Image boU,boD,boL,boR;
	/** Rectangle de collision de l'épée */
	private static Rectangle rec;
	/** Permet d'éviter que le rectangle ne se forme lorsque le joueur ne tape pas */
	public static boolean isHere,isHit;
	
	
	/**
	 * Instancie les Animations et le rectangle
	 */
	public Bo(){
		
		try {
			boU = new Image("res/hero/boU.png");
			boD = new Image("res/hero/boD.png");
			boL = new Image("res/hero/boL.png");
			boR = new Image("res/hero/boR.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		rec = new Rectangle(0, 0, 0, 0);
	}
	
	/**
	 * Affiche l'animation haute de l'épée
	 * @param g
	 */
	public void renderUp(Graphics g){
		boU.draw(Player.getX()+20, Player.getY()-20);
	}
	
	/**
	 * Affiche l'animation basse de l'épée
	 * @param g
	 */
	public void renderDown(Graphics g){
		boD.draw(Player.getX()+1, Player.getY()+5);
		rec.setBounds((int)Player.getX()+3, (int)Player.getY()+25, 7, 26);
		isHere = true;
	}
	/**
	 * Update du renderUp
	 */
	public void UpdateUp(){
		rec.setBounds((int)Player.getX()+24, (int)Player.getY()-13, 7, 25);
		isHere = true;
	}
	
	/**
	 * Affiche l'animation gauche de l'épée
	 * @param g
	 */
	public void renderLeft(Graphics g){
		boL.draw(Player.getX()-14, Player.getY()+21);
		rec.setBounds((int)Player.getX()-14, (int)Player.getY()+25, 20, 5);
		isHere = true;
	}
	
	/**
	 * Affiche l'animation droite de l'épée
	 * @param g
	 */
	public void renderRight(Graphics g){
		boR.draw(Player.getX(), Player.getY()+21);
		rec.setBounds((int)Player.getX()+25, (int)Player.getY()+25, 25, 5);
		isHere = true;
	}
	
	/**
	 * @return rec Rectangle de collision
	 */
	public static Rectangle getRect(){
		return rec;
	}
}
