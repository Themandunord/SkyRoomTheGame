package player;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * Classe créant un tir de blaster par le joueur
 * 
 * @author Rémy
 *
 */

public class Blaster {
	
	/** Image du tir */
	private Image shootImage;
	/** xShoot */
	private float xShoot;
	/** yShoot */
	private float yShoot;
	/** Direction */
	private int dir;
	/** Direction */
	private int direction;
	/** Rectangle de collision */
	private Rectangle rec;
	/** True si on peut tirer */
	private boolean isShoot;
	/** Vitesse du tir */
	private float speed=0.25f;
	/** Munitions */
	private static int ammo = 20;
	
	/**
	 * On instancie un tir
	 * 
	 * @param xShoot
	 * @param yShoot
	 * @param direction
	 * @param dir
	 * @param name
	 */
	public Blaster(float xShoot , float yShoot , int direction, int dir, String name){
		this.xShoot = xShoot;
		this.yShoot = yShoot;
		this.dir = dir;
		try {
			if(dir == 0) shootImage = new Image("res/monster/"+name+String.valueOf(dir)+".png");  // Haut et bas
			if(dir == 1) shootImage = new Image("res/monster/"+name+String.valueOf(dir)+".png");  // Droite et gauche
			if(dir == 0) rec = new Rectangle(xShoot+12,yShoot+5,5,20);
			if(dir == 1) rec = new Rectangle(xShoot+5,yShoot+12,20,5);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.direction = direction;
		isShoot = true;
	}
	
	/**
	 * On affiche le tir
	 */
	public void render(){
		if(isShoot){
			shootImage.draw(xShoot,	yShoot);
		}
	}
	
	/**
	 * Update du blaster
	 * 
	 * @param delta
	 */
	public void updateBlaster(int delta)
	{
		if(xShoot>-35 && xShoot<835 && yShoot>-35 && yShoot<635){
			if(direction == 0) this.yShoot -= speed *delta; // Up
			if(direction == 1) this.yShoot += speed *delta; // Down
			if(direction == 2) this.xShoot += speed *delta; // Right
			if(direction == 3) this.xShoot -= speed *delta; // Left
		
			if(dir == 0 || dir == 1) rec.setBounds(xShoot+24,yShoot+5,10,50);
			if(dir == 2 || dir == 3) rec.setBounds(xShoot+5,yShoot+24,50,10);
		}
		else isShoot = false;
		if(!isShoot){
			rec.setBounds(-500,-500,10,50);
		}
	}

	/**
	 * @return nombre de munitions restantes
	 */
	public static int getAmmo() {
		return ammo;
	}

	/**
	 * Modification du nombre de munitions
	 * 
	 * @param ammo
	 */
	public static void setAmmo(int ammo) {
		Blaster.ammo = ammo;
	}
	
	/**
	 * @return rectangle de collision
	 */
	public Rectangle getRect(){
		return rec;
	}
	
	/**
	 * Autorise le tir
	 * 
	 * @param isShoot
	 */
	public void setShoot(boolean isShoot){
		this.isShoot = isShoot;
	}
	

}
