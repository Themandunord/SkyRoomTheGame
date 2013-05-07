package player;

import org.newdawn.slick.geom.Circle;

import particle.Particle;

/**
 * Classe créant une Pistolet à eau 
 * 
 * @author Rémy
 *
 */

public class WaterGun {

	/** Particule d'eau */
	private Particle water;
	/** Vitesse de l'eau */
	private float speed = 0.3f;
	/** Coordonnée de l'eau */
	private float x=200,y=300;
	/** Direction de l'eau */
	private int dir;
	/** True si c'est lancé */
	private boolean isShoot;
	/** Cercle de collision */
	private Circle circle;
	
	/**
	 * Initialisation des éléments
	 * Direction 
	 * 1 = haut
	 * 2 = bas
	 * 3 = droite
	 * 4 = gauche
	 * 
	 * @param x Abscisse
	 * @param y Ordonnée
	 * @param dir Direction
	 */
	public WaterGun(float x, float y, int dir){
		this.dir = dir;
		this.x = x;
		this.y = y;
		water = new Particle();
		water.init("water");
		water.setReady(true);
		this.isShoot = true;
		circle = new Circle(x, x, 5);
		
	}
	
	/**
	 * Mouvement de la particule
	 * @param delta
	 */
	public void update(int delta){
		if(isShoot){
			if(dir==1) y-= speed * delta;
			if(dir==2) y+= speed * delta;
			if(dir==3) x+= speed * delta;
			if(dir==4) x-= speed * delta;
			water.update(delta);
			circle.setLocation(x,y);
		}
		if(x>800 || x<0 || y>600 || y<0){
			isShoot = false;
			water.setReady(false);
		}
			
		
	}
	
	/**
	 * Affichage de la particule
	 */
	public void render(){
		if(isShoot)
			water.render(x, y);
	}
	
	/**
	 * @return circle Cercle de collision
	 */
	public Circle getCircle(){
		return circle;
	}
	
	/**
	 * Modification de isShoot
	 * 
	 * @param isShoot
	 */
	public void setShoot(boolean isShoot){
		this.isShoot = isShoot;
	}
}