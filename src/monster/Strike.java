package monster;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 * Classe permettant de créer des coups d'épées pour les monstres
 * 
 * @author Stanislas
 *
 */

public class Strike {

	/** Coordonnées du coup */
	private float xStrike, yStrike;
	/** Image du coup */
	private SpriteSheet strikeUpSprite, strikeDownSprite, strikeLeftSprite, strikeRightSprite;
	/** Animation de l'épée */
	private Animation strikeU,strikeD,strikeL,strikeR;
	/** Rectangle de collision de l'épée */
	private Rectangle rec;
	
	
	public Strike(){
		try{
			strikeUpSprite = new SpriteSheet("res/monster/strikeImageU.png",32,32);
			strikeDownSprite = new SpriteSheet("res/monster/strikeImageD.png",32,32);
			strikeLeftSprite = new SpriteSheet("res/monster/strikeImageL.png",32,32);
			strikeRightSprite = new SpriteSheet("res/monster/strikeImageR.png",32,32);
		} catch (SlickException e) {e.printStackTrace();}
		
		strikeU = new Animation(strikeUpSprite,0,0,4,0,true,100,true);
		strikeD = new Animation(strikeDownSprite,0,0,4,0,true,100,true);
		strikeL = new Animation(strikeLeftSprite,0,0,0,4,true,100,true);
		strikeR = new Animation(strikeRightSprite,0,0,0,4,true,100,true);
		
		rec = new Rectangle(0,0,0,0);
	}
	
	public void renderUp(Graphics g, float x, float y){
		strikeU.start();
		strikeU.draw(x,y);
	}
	
	public void UpdateUp(float x, float y){
		rec.setBounds((int) x+15,(int) y+5, 5, 15);
	}
	
	public void renderDown(Graphics g, float x, float y){
		strikeD.start();
		strikeD.draw(x,y);
		rec.setBounds((int) x+15,(int) y+5,5,15);
	}
	
	public void renderLeft(Graphics g, float x, float y){
		strikeL.start();
		strikeL.draw(x, y);
		rec.setBounds((int) x+15,(int) y+15, 10, 5);
		
	}
	
	public void renderRight(Graphics g, float x, float y){
		strikeR.start();
		strikeR.draw(x, y);
		rec.setBounds((int) x+5,(int) y+15, 10, 5);
		
	}
	
	
	
	public Rectangle getRect(){
		return rec;
	}
	
	
	
}
