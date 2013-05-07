package monster;

import event.Event;
import game.Game;
import game.Menu;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import player.Player;

/**
 * Classe permettant de créer des tir pour les monstres
 * 
 * @author Rémy
 *
 */

public class Shoot {
	/** Coordonnées du tir */
	private float xShoot, yShoot;
	/** Image du tir */
	private Image shootImage;
	/** True si le tir est lancé */
	private static boolean isShoot = false;
	/** Direction dans laquelle le tir a été lancé */
	private int direction;
	/** Cercle de collision */
	private Circle circle;
	/** Vitesse du tir */
	private float speed = 0.26f;
	/** Direction du Sprite du blaster
	 * 1 => droite gauche
	 * 0 => haut bas
	 */
	private int dir;
	/** Rectangle de blaster */
	private Rectangle rec;
	/** Vecteur pour les tirs en diagonale */
	private Vector2f target,source,distance;
	/** Cible des vecteurs */
	private float targetX,targetY;
	/** Coordonnée a un instant t */
	private float _xM, _yM;
	/** Compteur pour les différents effets des tirs */
	private int cpt, cptGrenade,cptExplo, cptFinishExplo;
	/** SpriteSheet de l'explosion */
	private SpriteSheet sprite,exploSprite;
	/** True si le tir est une grenade */
	private boolean isGrenade;
	/** Animation de l'explosion des grenades */
	private Animation explo;
	private boolean isPlayingExplo;
	
	
	/**
	 * Constructeur permettant l'instanciation d'un tir en forme de rond
	 * 
	 * @param xShoot Abscisse d'où démarre le tir
	 * @param yShoot Ordonnée d'où démarre le tir
	 * @param direction Direction dans laquelle le tir va aller
	 * @param name Nom de l'image associée 
	 */
	public Shoot(float xShoot , float yShoot , int direction, String name){
		this.xShoot = xShoot;
		this.yShoot = yShoot;
		try {
			if(name.equals("grenade")){
				sprite = new SpriteSheet("res/monster/grenadeExpl.png",32,32);
				exploSprite = new SpriteSheet("res/monster/explo.png",64,64);
				explo = new Animation(exploSprite,0,0,4,4,true,50,true);
				isGrenade = true;
			}
			shootImage = new Image("res/monster/"+name+".png");
			circle = new Circle(xShoot+5, yShoot+5, 5);	
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.direction = direction;
		this.targetX = Player.getX()+16;
		this.targetY = Player.getY()+16;
		source = new Vector2f(this.xShoot,this.yShoot);
		target = new Vector2f(this.targetX,this.targetY);
		distance = target.copy().sub(source).normalise();
	}
	
	
	/**
	 * Constructeur permettant l'instanciation d'un tir en forme de blaster
	 * 
	 * @param xShoot Abscisse d'où démarre le tir
	 * @param yShoot Ordonnée d'où démarre le tir
	 * @param direction Direction dans laquelle le tir va aller
	 * @param dir Direction du spriteSheet
	 * @param name Nom de l'image associée 
	 */
	public Shoot(float xShoot , float yShoot , int direction, int dir, String name){
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
	}
	
	/**
	 * Constructeur permettant l'instanciation d'un tir dans les 4 directions
	 * 
	 * @param xShoot Abscisse d'où démarre le tir
	 * @param yShoot Ordonnée d'où démarre le tir
	 * @param direction Direction dans laquelle le tir va aller
	 * @param name Nom de l'image associée 
	 */
	public Shoot(String name, float xShoot , float yShoot , int direction){
		this.xShoot = xShoot;
		this.yShoot = yShoot;
		this.direction = direction;
		try {
			if(direction == 0) shootImage = new Image("res/monster/"+name+String.valueOf(direction)+".png"); //haut 
			if(direction == 1) shootImage = new Image("res/monster/"+name+String.valueOf(direction)+".png"); //bas
			if(direction == 2) shootImage = new Image("res/monster/"+name+String.valueOf(direction)+".png"); //gauche
			if(direction == 3) shootImage = new Image("res/monster/"+name+String.valueOf(direction)+".png"); //droite
			if(direction == 0 || direction == 1) rec = new Rectangle(xShoot+24,yShoot+5,10,50);
			if(direction == 2 || direction == 3) rec = new Rectangle(xShoot+5,yShoot+24,50,10);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Initialisation de l'image du tir par défaut
	 * A ne pas faire si on veut une autre image
	 * 
	 * @throws SlickException
	 */
	public void init() throws SlickException{
		shootImage = new Image("res/monster/tir.png");
	}
	
	/**
	 * Affichage du tir seulement si isShoot = true
	 */
	public void render(){
		if(isShoot){
			shootImage.draw(xShoot,	yShoot);	
			if(isGrenade) {
				if(cptGrenade>30 && cptGrenade<40 && cptExplo<3){
				sprite.getSprite(1, 0).draw(xShoot, yShoot);
				cptGrenade++;
				}
				else if(cptGrenade>45 && cptExplo<3){
					cptGrenade =0;
					cptExplo++;
				}
				else cptGrenade++;
				if(cptExplo>=3){
					explo.start();
					explo.setLooping(false);
					if(!isPlayingExplo){
						Menu.explosion.play(1,Event.volume);
						isPlayingExplo = true;
					}
					explo.draw(xShoot-16, yShoot-16);
					cptFinishExplo++;
					
				}
			}
		}
	}
	
	/**
	 * On déplace le tir dans la direction souhaitée
	 * Pour le tir arrondi
	 * 
	 * @param delta Permet une vitesse constante selon les FPS
	 */
	public void update(int delta)
	{
		if(xShoot>-20 && xShoot<820 && yShoot>-20 && yShoot<620){
			if(direction == 0) this.yShoot -= speed *delta; // Up
			if(direction == 1) this.yShoot += speed *delta; // Down
			if(direction == 2) this.xShoot += speed *delta; // Right
			if(direction == 3) this.xShoot -= speed *delta; // Left
			circle.setLocation(xShoot, yShoot);
		}
		else isShoot = false;
		
	}
	
	
	/**
	 * On déplace le tir dans la direction souhaitée
	 * Pour le tir Blaster
	 * 
	 * @param delta Permet une vitesse constante selon les FPS
	 */
	public void updateBlaster(int delta)
	{
		if(xShoot>-35 && xShoot<835 && yShoot>-35 && yShoot<635){
			if(direction == 0) this.yShoot -= speed *delta; // Up
			if(direction == 1) this.yShoot += speed *delta; // Down
			if(direction == 2) this.xShoot += speed *delta; // Right
			if(direction == 3) this.xShoot -= speed *delta; // Left
		
			if(dir == 0 ) rec.setBounds(xShoot+15,yShoot+5,5,20);
			if(dir == 1 ) rec.setBounds(xShoot+5,yShoot+15,20,5);
		}
		else isShoot = false;
	}
	
	/**
	 * On déplace le tir dans la direction souhaitée
	 * Pour le tir dse missiles ( BossFutur )s
	 * 
	 * @param delta Permet une vitesse constante selon les FPS
	 */
	public void updateMissile(int delta)
	{
		if(xShoot>-65 && xShoot<865 && yShoot>-65 && yShoot<665){
			if(direction == 0) this.yShoot -= speed *delta; // Up
			if(direction == 1) this.yShoot += speed *delta; // Down
			if(direction == 3) this.xShoot += speed *delta; // Right
			if(direction == 2) this.xShoot -= speed *delta; // Left
		
			if(direction == 0 || direction == 1) rec.setBounds(xShoot+27,yShoot+5,10,50);
			if(direction == 2 || direction == 3) rec.setBounds(xShoot+5,yShoot+27,50,10);
		}
		else{
			isShoot = false;
		}
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
			circle.setLocation(xShoot, yShoot);
		}
		else {
			isShoot = false;
		}
			
	}
	
	
	/**
	 * Update de la grenade
	 * 
	 * @param delta
	 */
	public void updateGrenade(float xM, float yM, int delta)
	{	//la grenade vole sur 4 cases avant d'atterir
		if(cpt==0){
			_xM = xM;
			_yM = yM;
			cpt = 1;
		}
		
		if((direction == 0) && _yM-yShoot<=128) this.yShoot -= speed *delta; // Up : ne change pas
		if((direction == 1) && yShoot-_yM<=128) this.yShoot += speed *delta; // Down : ne change pas
		/* la grenade suit une parabole d'équation y=0.25x² */
		if((direction == 2) && xShoot-_xM<=128)
		{	
			this.xShoot += speed *delta; 						// 1 case vers Right
			if(xShoot-_xM<64)this.yShoot -= 0.5*speed*delta; 	// 0.25 case vers Up la première moitié de son vol
			else this.yShoot += 0.25*speed*delta;				//0.25 case vers Down : elle redescend à mi-chemin
		}
		if((direction == 3) && _xM-xShoot<=128)
		{
			this.xShoot -= speed *delta; 						// 1 case vers Left
			if(_xM-xShoot<64)this.yShoot -= 0.25*speed*delta; 	//0.25 case vers Up
			else this.yShoot += 0.5*speed*delta; 				// 0.25 case vers Down : elle redescend à mi-chemin
		}
		if(xShoot<-20 || xShoot>820 || yShoot<-20 || yShoot>620){
			isShoot = false;
		}
		
		if(isShoot){
			if(cptFinishExplo < 1){
				circle.setLocation(xShoot+5, yShoot+5);
			}
			else if(cptFinishExplo < 50)
				circle.setRadius(10);
			else circle.setLocation(-100, -100);
			
		}
		else circle.setLocation(-100, -100);
	}
	
	/**
	 * Modification de isShoot
	 * 
	 * @param isShoot True si le tir est lancé l
	 */
	public void setShoot( boolean isShoot){
		Shoot.isShoot = isShoot;
	}
	
	/**
	 * @return isShoot True si le tir est lancé
	 */
	public static boolean isShoot(){
		return isShoot;
	}
	/**
	 * @return xShoot Abscisse du tir
	 */
	public float getX(){
		return xShoot;
	}
	
	/**
	 * @return yShoot Ordonnée du tir
	 */
	public float getY(){
		return yShoot;
	}
	
	/**
	 * Modification de l'abscisse du tir
	 * 
	 * @param xShoot Abscisse du tir
	 */
	public void setX( float xShoot){
		this.xShoot = xShoot;
	}
	
	/**
	 * Modification de l'ordonnée du tir
	 *
	 * @param yShoot Ordonnée du tir
	 */
	public void setY( float yShoot){
		this.yShoot = yShoot;
	}
	
	/**
	 * @return circle Cercle de collision
	 */
	public Circle getCircle(){
		return circle;
	}
	
	/**
	 * @return Rectangle de collision pour les tirs blaster et missiles
	 */
	public Rectangle getRec(){
		return rec;
	}
	
	/**
	 * @return le compteur de fin d'explosion
	 */
	public int getCptFinishExplo(){
		return cptFinishExplo;
	}
		
}
