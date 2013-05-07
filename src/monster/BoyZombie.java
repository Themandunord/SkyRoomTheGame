package monster;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import player.Player;

/**
 * Classe créant le zombie garÃ§on
 * 
 * @author Alexandre
 *
 */
public class BoyZombie extends Monster{
	private SpriteSheet spritePoison;
	private Animation zombieExplosion;
	private boolean isExplode,explodeFinish;
	
	
	/**
	 * Constructeur par défaut
	 * On donne une vitesse de 0.06 aux zombies
	 */
	public BoyZombie(){
		super(0.09f,200);
		this.canDoHitAnimation = false;
	}
	public void init() throws SlickException
	{
		super.init();
		super.MonsterAnim("zombie2",32,32);
		spritePoison = new SpriteSheet("res/monster/PoisonZ.png",64,64); //instanciation de la SpriteSheet
		zombieExplosion = new Animation(spritePoison,0,0,4,3,true,100,true);//instanciation de l'animation d'explosion du zombie
		zombieExplosion.stop();
	}
	public void update(GameContainer gc, int delta) throws SlickException{
		super.update(gc, delta);
		if(rectMonster.intersects(Player.getRect())) // si le zombie touche le joueur, le zombie meurt en explosant
		{	
			isExplode =true;
		}
		if(!alive && !isExplode){
			this.explode();
		}
		
		if(isExplode){
			this.explode();
		}
		
	}
	
	/**
	 * Méthode gérant l'explosion du Zombie
	 */
	public void explode()
	{
		this.alive = false; //le zombie explose, donc meurt ! 
		this.isExplode = true;
		isDead = true;
		dead.stop();  // Evite que l'animation de mort se dÃ©clenche.

		zombieExplosion.start(); //l'animation d'explosion commence
		zombieExplosion.setLooping(false);
	}
	
	public void renderBot(Graphics g) throws SlickException{
		super.renderBot(g);
		if(isExplode && !explodeFinish){
			zombieExplosion.draw(xM-16, yM-16);
			if(zombieExplosion.isStopped()){
				isExplode = false;
				explodeFinish = true;
			}
		}
	}
	

}
