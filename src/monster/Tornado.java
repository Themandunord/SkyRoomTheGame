package monster;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import player.Player;

public class Tornado {
	private float xShoot,yShoot;
	/** Vecteur pour les tirs en diagonale */
	private Vector2f target,source,distance;
	/** Cible des vecteurs */
	private float targetX,targetY;
	private Rectangle rec;
	private boolean isShoot;
	private Animation tornado;
	
	
	public Tornado(float xShoot , float yShoot){
		this.xShoot = xShoot;
		this.yShoot = yShoot;
		this.targetX = Player.getX()+16;
		this.targetY = Player.getY()+16;
		source = new Vector2f(this.xShoot,this.yShoot);
		target = new Vector2f(this.targetX,this.targetY);
		distance = target.copy().sub(source).normalise();
		try {
			tornado = new Animation(new SpriteSheet("res/monster/tornado.png",32,32),0,0,7,0,true,100,true);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.rec = new Rectangle(0,0,0,0);
	}
	
	/**
	 * Méthode d'update des tir en diagonales
	 * 
	 * @param delta
	 */
	public void updateDiag(int delta){
		if(xShoot>-50 && xShoot<850 && yShoot>-50 && yShoot<650){
			xShoot = source.x;
			yShoot = source.y;
			Vector2f deplacement = distance.copy().scale((int)delta*0.2f);
			source.add(deplacement);
			rec.setBounds(xShoot+5,yShoot+5,20,25);		
		}
		else {
			isShoot = false;
			rec.setBounds(-100,-100,0,0);
		}
			
	}
	
	public void render(){
		if(isShoot){
			tornado.start();
			tornado.draw(xShoot, yShoot);
		}
	}

	public void setShoot(boolean isShoot) {
		this.isShoot = isShoot;
	}
	
	public Rectangle getRec(){
		return this.rec;
	}
}
