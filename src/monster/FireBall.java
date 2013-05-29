package monster;

import game.Game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import player.Player;
import particle.Particle;

/**
 * Classe créant les boules de feu pour les monstres
 * Ex : RedAncient
 * 
 * @author Alex
 *
 */

public class FireBall {

	/** Particule de boule de feu */
	private Particle fire;
	/** Vitesse de la boule */
	private float speed = 0.3f;
	/** Coordonnées du tir de la boule*/
	private float xShoot, yShoot;
	/** Direction de la boule */
	private int dir;
	/** True si c'est lancé */
	private static boolean isShoot=false;
	/** Cercle de collision */
	private Circle circle;
	/** Vecteur pour les tirs en diagonale */
	private Vector2f target,source,distance;
	private float targetX,targetY;
	
	/**
	 * Initialisation des Ã©lÃ©ments
	 * Direction 
	 * 1 = haut
	 * 2 = bas
	 * 3 = droite
	 * 4 = gauche
	 * 
	 * @param xShoot Abscisse
	 * @param yShoot Ordonnée
	 * @param dir Direction
	 */
	public FireBall(String name,float xShoot, float yShoot, int dir){
		this.dir = dir;
		this.xShoot = xShoot;
		this.yShoot = yShoot;
		fire = new Particle();
		fire.init(name);
		fire.setReady(true);
		this.isShoot = true;
		circle = new Circle(xShoot, xShoot, 5);
		this.targetX = Player.getX()+16;
		this.targetY = Player.getY()+16;
		source = new Vector2f(this.xShoot,this.yShoot);
		target = new Vector2f(this.targetX,this.targetY);
		distance = target.copy().sub(source).normalise();
		
	}
	
	/**
	 * Mouvement de la particule
	 * @param delta
	 */
	public void update(int delta)
	{
		if(xShoot>-20 && xShoot<820 && yShoot>-20 && yShoot<620){
			if(dir == 0) this.yShoot -= speed *delta; // Up
			if(dir == 1) this.yShoot += speed *delta; // Down
			if(dir == 2) this.xShoot += speed *delta; // Right
			if(dir == 3) this.xShoot -= speed *delta; // Left
			fire.update(delta);
			circle.setLocation(xShoot, yShoot);
		}
		else isShoot = false;
		
	}
	/**
	 * Méthode d'update des tir en diagonales
	 * 
	 * @param delta
	 */
	public void updateDiag(int delta){
		if(xShoot>-20 && xShoot<820 && yShoot>-20 && yShoot<620){
			xShoot = source.x;
			yShoot = source.y;
			Vector2f deplacement = distance.copy().scale((int)delta*0.2f);
			source.add(deplacement);
			fire.update(delta);
			circle.setLocation(xShoot, yShoot);
		}
		else {
			isShoot = false;
		}

	}
	
	/**
	 * Affichage de la particule
	 */
	public void render(){
		if(isShoot)
			fire.render(xShoot, yShoot);
	}
	
	/**
	 * @return circle Cercle de collision
	 */
	public Circle getCircle(){
		return circle;
	}
	/**
	 * Permet d'arrêter le tir s'il touche le joueur
	 * 
	 * @param isShoot True si on peut tirer
	 */
	public void setShoot(boolean isShoot){
		this.isShoot = isShoot;
	}
}