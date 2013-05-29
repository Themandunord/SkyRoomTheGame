package monster;

import items.Item;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import event.Event;

import player.Player;

/**
 * Classe créant un BlackAncient
 * Monstre qui charge un éclair et le lance à une position définie
 * 
 * @author Rémy
 *
 */

public class BlackAncient extends Monster {

	/** SpriteSheet de la lumière et de l'éclair */
	private SpriteSheet spriteLight,spriteThunder;
	/** Animation de la lumière et de l'éclair */
	private Animation animLight,animThunder;
	/** Image du nuage */
	private Image cloud;
	/** True si l'éclair et affiché */
	private boolean isThunder;
	/** Compteur pour l'effet aléatoire */
	private int cptTarget=1,cpt=1;
	/** Cible de l'éclair */
	private float targetX, targetY;
	private Rectangle rec;
	private boolean increaseNB;
	/**
	 * Instanciation du Black Ancient avec une vitesse et une vie par défaut
	 */
	public BlackAncient() {
		super(0.05f,250);
	}
	
	public void init() throws SlickException{
		super.init();
		this.MonsterAnim("blackAncient", 32, 32);
		cloud = new Image("res/monster/blackCloud.png");
		spriteLight = new SpriteSheet("res/monster/light.png", 64, 64);
		spriteThunder = new SpriteSheet("res/monster/blackCloudThunder.png", 64, 64);
		animLight = new Animation(spriteLight,0,0,4,3,true,100,true);
		animThunder = new Animation(spriteThunder,0,0,4,0,true,100,true);
		rec = new Rectangle(0,0,0,0);
		if(Event.specialItemBonus){
			item = new Item("specialBonus");
			Event.specialItemBonus = false;
		}
	}
	
	public void renderBot(Graphics g) throws SlickException{
		super.renderBot(g);
		if(alive){
			if(!isThunder) rec.setBounds(0, 0, -100, -100);
			if(cptTarget==20){
				targetX = Player.getX()-16;
				isThunder = true;
				targetY = Player.getY();
			}
			if(cptTarget<160) 
				cptTarget++;
			else cptTarget=0;
			
			if(cptTarget<40){
				isThunder = false;
				animLight.start();
				animLight.draw(xM-16,yM-16);
			}else {
				isThunder = true;
				animLight.stop();
			}
		}
		rec.setBounds(0,0,-100,-100);

		
	}
	
	/**
	 * Méthode créant l'éclair en fonction du certaine position
	 * 
	 * @param x x
	 * @param y y
	 */
	public void thunder(float x, float y){
		
		if(isThunder){
			animThunder.start();
			animThunder.draw(x,y-32);
			cloud.draw(x, y-70);
			rec.setBounds(targetX+20, targetY+10, 25, 10);
		}
		else rec.setBounds(0, 0, -500, -500);
	}
	
	public void update(GameContainer gc, int delta) throws SlickException{
		super.update(gc, delta);
		if(rec.intersects(Player.getRect()))
			Event.colision = true;
		if(!alive && !increaseNB){
			increaseNB = true;
			Event.special_item_bonus++;
		}
	}
	
	public void renderUp(Graphics g){
		if(alive)
			thunder(targetX,targetY);
	}

}
