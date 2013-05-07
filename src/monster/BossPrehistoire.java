package monster;

import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import player.Player;

import event.Event;

/**
 * Classe créant le boss du monde préhistoire.
 * Il a une certain position de départ. Lorsqu'on approche trop prêt de lui,
 * il nous charge
 * 
 * @author Rémy
 *
 */

public class BossPrehistoire extends Monster{

	public BossPrehistoire() {
		super(0.12f, 100);
	}
	
	public void init() throws SlickException{
		super.init();
		this.MonsterAnim("boss_prehistoire",64,64);
		currentMonsterAnim = monster_down;
		xM = 416;
		yM = 96;
		currentMonsterAnim.stop();
		Event.cinematic = true;
	}
	
	public void update(GameContainer gc, int delta) throws SlickException{
		super.update(gc, delta);
		if(Math.sqrt(Math.pow(xM-Player.getX(),2)+Math.pow(yM - Player.getY(),2)) < 200){
			Event.cinematic = false;
			currentMonsterAnim.start();
		}
		if(alive){
			rectMonster.setBounds(xM+5,yM+2,50,54);
			if(rectMonster.intersects(Player.getRect())){
				Event.colision = true;
			}
		}
		if(Map.isSlow(xM+5, yM+25)){
			speed = 0.05f;
		}else speed = 0.12f;
	
	}

}
