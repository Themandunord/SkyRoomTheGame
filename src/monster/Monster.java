package monster;

import game.Menu;
import items.Item;
import event.Event;
import map.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;

import player.Bo;
import player.Player;
import player.Sword;

/**
 * Classe mère de tout les monstres
 * L'IA des monstres est codée ici
 * 
 * @author Rémy
 *
 */

public class Monster {
	/** Abscisse du monstre */
	protected float xM;
	/** Ordonnée du monstre */
	protected float yM;
	/** Rectangle de collision du monstre */
	protected Rectangle rectMonster;
	/** Anmation du monstre */
	protected Animation monster_down,monster_up,monster_right,monster_left,
					  currentMonsterAnim;
	/** Compteur permettant l'update durant un intervalle de temps */
	protected int compteurDeplacement = 201 , collision = 1, compteur=200;
	/** Direction du monstre */
	protected boolean left,right,up,down;
	/** SpriteSheet du monstre */
	private SpriteSheet monsterSprite;
	/** True si le monstre est en vie */
	protected boolean alive = true; // Permet de d'afficher le monstre ou non selon son état ( Vivant/ Mort )
	/** Drop du monstre */
	protected Item item;
	/** Vitesse de monstre */
	protected float speed;
	/** Le PathFinder */
	protected AStarPathFinder pathFinder;
	/** Le chemin du monstre */
	protected Path path;
	/** Map permettant de trouver le chemin */
	protected Map map = new Map();
	/** Image de mort du monstre */
	protected SpriteSheet spriteDead;
	/** Animation lors de la mort */
	protected Animation dead;
	/** True si le monstre est mort */
	protected boolean isDead;
	protected int life;
	/** True si il a été touché */
	protected boolean isHit;
	/** Compteur pour la durer de l'animation aie */
	private int cpt;
	protected SpriteSheet aieSprite;
	/** Animation lors de la collision */
	protected Animation aie;
	/** True s'il peut faire l'animation de mort */
	protected boolean canDoHitAnimation;
	/** True si le monstre est mort */
	protected boolean declareDead;
	private boolean isPlaying;
	
	/**
	 * Constructeur par défaut
	 * 
	 * @param speed Vitesse du monstre
	 */
	public  Monster(float speed,int life){
		this.speed = speed;
		this.life = life;
		this.canDoHitAnimation = true;
	}

	/**
	 * Calcule la valeur absolue
	 * 
	 * @param x Entier positif ou négatif
	 * @return La valeur absolu
	 */
	private static float abs(float x)
	{
		return x<0 ? -x : x ;
	}
	
	/**
	 * On donne une position aléatoire au monstre en évitant les collisions
	 * On initialise les animations
	 * 
	 * @throws SlickException
	 */
	public void init() throws SlickException{
	
		// On donne une une position aléatoire au monstre , tant qu'il n'es pas sur un case bloquante
		do{
			xM = (float) (Math.random()*480 +160);
			yM = (float) (Math.random()*280 +160);
		} 
		while(Map.isBlocked(xM, yM)||(Map.isBlocked(xM+31, yM+31)) 
				|| (Map.isBlocked(xM, yM+31))||(Map.isBlocked(xM+31, yM)));
		rectMonster =new Rectangle(xM,yM,28,28);  // Rectangle de collision
		
		if(Math.random()<0.2)
			item = new Item("rubis");
		else if(Math.random()<0.4)
			item = new Item("heart");
		else  
			item = new Item("saphir");
		rectMonster =new Rectangle(xM+5,yM+2,25,27);	
		spriteDead = new SpriteSheet("res/monster/dead.png", 64	, 64);
		dead = new Animation(spriteDead,0,0,4,5,true,50,true);
		dead.stop();
	}
	
	/**
	 * Méthode permettant d'initialiser les animations du monstre
	 * Dans les ressources monster-xxx.png
	 *  
	 * @param monsterName Nom du monstre
	 * @throws SlickException
	 */
	public void MonsterAnim(String monsterName,int width, int height) throws SlickException{
		monsterSprite= new SpriteSheet("res/monster/monster-"+monsterName+".png",width,height);
		
		monster_down = new Animation(monsterSprite, 0, 0, 2, 0, true, 200, true);
		monster_up = new Animation(monsterSprite, 0, 3, 2, 3, true, 200, true);
		monster_right = new Animation(monsterSprite, 0, 2, 2, 2, true, 200, true);
		monster_left = new Animation(monsterSprite, 0, 1, 2, 1, true, 200, true);
		currentMonsterAnim = monster_down;
		monster_up.stop();
		monster_right.stop();
		monster_left.stop();
		
		aieSprite = new SpriteSheet("res/monster/aie.png", 32, 32);
		aie = new Animation(aieSprite, 0, 0, 1, 0, true, 200, true);
		aie.stop();
	}
	
	/**
	 * Méthode qui gère l'animation lors des dégats
	 */
	public void hitMonster(){
		if(isHit){
				if(cpt<40){
					aie.start();
					aie.draw(xM-32, yM-10);
					cpt++;
					if(!isPlaying){
						Menu.hurt.play(1,Event.volume);
						isPlaying = true;
					}
				}else {
					isHit = false;
					cpt = 0;
					isPlaying = false;
				}
			}else{
				aie.stop();
			}
	}
	
	/**
	 * Affiche le monstre avec son animation 
	 * @param g Sortie de l'écran
	 * @throws SlickException
	 */
	public void renderBot(Graphics g) throws SlickException{
		if(alive){ // S'il est en vie
			currentMonsterAnim.draw(xM,yM);
			hitMonster();
		}
		else{
			if(canDoHitAnimation){
				dead.start();
				dead.setLooping(false);
				dead.draw(xM-16, yM-16);
				if(dead.isStopped())
					isDead=true;
			}
			if(isDead)
					item.render(xM, yM);
			
		}
	}
	
	public void renderUp(Graphics g){
		
	}
	
	/**
	 * IA du monstre
	 * Si le personnage est loin du monstre, on utilise la la méthode A*
	 * Sinon on utilise un algorithme moins performant pour éviter tout bug
	 * 
	 * @param gc GameContaienr
	 * @param delta Permet d'avoir une vitesse constante selon les FPS
	 * @throws SlickException
	 */
	public void update(GameContainer gc, int delta) throws SlickException{
		if(alive && !Event.cinematic){ // S'il est en vie ( Evite de prendre de la mémoire pour rien )
			
		rectMonster.setBounds(xM+5,yM+2,25,27);
		
		if(rectMonster.intersects(Player.getRect())){
			Event.colision = true;
		}
		if(rectMonster.intersects(Sword.getRect()) && Sword.isHere){
			if(!Menu.hit.playing()){
				Menu.hit.play(1, Event.volume);
			}
			if(this.life < 0)
				alive = false;
			else{
				isHit =true;
				this.life-=100;
			}
		}
		if(rectMonster.intersects(Bo.getRect()) && Bo.isHere){
			Bo.isHit= true;
			if(!Menu.hit.playing()){
				Menu.hit.play(1, Event.volume);
			}
			if(this.life < 0)
				alive = false;
			else{
				isHit =true;
				this.life-=100;
			}
		}
		
		for(int i = 0; i<Player.getCircle().size() ; i++){
			if(rectMonster.intersects(Player.getCircle().get(i))){
				if(this.life < 0)
					alive = false;
				else this.life-=10;
				isHit = true;
				Player.getCircle().get(i).setLocation(-100, -100);
				Player.getWater().get(i).setShoot(false);
			}
		}
		
		for(int i = 0; i<Player.getBlaster().size() ; i++){
			if(rectMonster.intersects(Player.getBlaster().get(i).getRect())){
				if(this.life < 0)
					alive = false;
				else this.life-=50;
				isHit = true;
				Player.getBlaster().get(i).setShoot(false);
			}
		}
		
		// IA
		if(compteurDeplacement>20){
			Map.setReady(false);
		
			compteurDeplacement=0;
			pathFinder = new AStarPathFinder(map, 475, false);
			path = pathFinder.findPath(null,(int)(xM+16)/32, (int)(yM+17)/32, (int)(Player.getX()+16)/32, (int)(Player.getY()+17)/32);
			Map.setReady(true);
		}
		if(!Map.isReady()){
			xM=path.getX(0);
			yM=path.getY(0);
		}
		if(Map.isReady())
			moveTo(1,delta);
		compteurDeplacement++;
		}
	}

	/**
	 * PathFinding du monstre.
	 * Le monstre bouge en suivant le chemin généré par l'algorithme A*
	 * S'il est trop prêt du joueur, on passe a un algo plus léger afin d'éviter des petits bugs
	 * 
	 * @param i
	 * @param delta
	 */
	public void moveTo(int i, int delta){
			if(/*Math.sqrt(Math.pow(xM-Player.getX(),2)+Math.pow(yM - Player.getY(),2)) > 33 &&*/ path != null && currentMonsterAnim!=null){
				if(path.getX(i)<path.getX(i-1)){
					xM-=speed*delta;
					left=true;
					right=false;
					up=false;
					down=false;
					currentMonsterAnim = monster_left;
					currentMonsterAnim.start();
				}
				else if(path.getX(i)>path.getX(i-1)){
					xM+=speed*delta;
					left=false;
					right=true;
					up=false;
					down=false;
					currentMonsterAnim = monster_right;
					currentMonsterAnim.start();
				}
				if(path.getY(i)>path.getY(i-1)){
					yM+=speed*delta;
					left=false;
					right=false;
					up=false;
					down=true;
					currentMonsterAnim = monster_down;
					currentMonsterAnim.start();
				}
				else if(path.getY(i)<path.getY(i-1)){
					yM-=speed*delta;
					left=false;
					right=false;
					up=true;
					down=false;
					currentMonsterAnim = monster_up;
					currentMonsterAnim.start();
				}
				
			}
			else{
				if(compteur>=15) // Permet d'éviter les mouvements trop rapides
				{
					compteur = 0;
				
					left = false ;
					right = false ;
					up = false;
					down =false ;
				
				
				if((Player.getX() - xM)<0){
					left =true;
				}
				else
					right = true;
				
				if((Player.getY() - yM) >0){
					down = true;
				}
				else
					up = true;
				
				
				
				if(abs(Player.getX() - xM) <= abs(Player.getY() - yM)){
					left = false;
					right = false;
				}
				else{
					up = false;
					down = false;
				}
				}
				
				if(abs(Player.getX() - xM) <= 15)
				{
					left =false ;
					right = false;
				}
				if(abs(Player.getY() - yM) <= 15)
				{
					down = false;
					up = false;
				}
				
				
				if(left == true){
					if (!Map.isBlocked(xM, yM + 32) || !Map.isBlocked(xM, yM)){
						xM -= speed * delta;
						currentMonsterAnim = monster_left;
						currentMonsterAnim.start();
					}else yM+=speed*delta;
				}
				else if(right == true){
					if (!Map.isBlocked(xM +32 , yM +32) || !Map.isBlocked(xM+32, yM)){
						xM += speed * delta;
						currentMonsterAnim = monster_right;
						currentMonsterAnim.start();
					}else yM-=speed*delta;
				}
				else if(down == true){
					if (!Map.isBlocked(xM  , yM + 32 ) || !Map.isBlocked(xM+32, yM+32)){
						yM += speed * delta;
						currentMonsterAnim = monster_down;
						currentMonsterAnim.start();
						
					}else xM-=speed*delta;
				}
				else if(up == true){
					if (!Map.isBlocked(xM, yM) || !Map.isBlocked(xM+32, yM)){
								yM -= speed * delta;
						currentMonsterAnim = monster_up;
						currentMonsterAnim.start();
					}else xM+=speed*delta;
				}else currentMonsterAnim.stop();
				
				
				compteur++;
				
				}
	}
	
	/**
	 * @return xM Abscisse du monstre 
	 */
	public float getX() {
		return xM;
	}

	/**
	 * @return yM Ordonnée du monstre
	 */
	public float getY() {
		return yM;
	}

	/**
	 * @return rectMonster Rectangle de collision du monstre
	 */
	public Rectangle getRect() {
		return rectMonster;
	}
	
	/**
	 * @return True si le monstre est en vie
	 */
	public boolean getAlive(){
		return alive;
	}
	
	/**
	 * @return True si le monstre est mort
	 */
	public boolean getDeclareDead(){
		return declareDead;
	}
	
	/**
	 * Modification de declaredDead
	 * 
	 * @param declareDead
	 */
	public void setDeclareDead( boolean declareDead){
		this.declareDead = declareDead;
	}
	
}

