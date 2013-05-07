package monster;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import player.Player;
import event.Event;

/**
 * Classe créant le boss du futur.
 * Celui-ci lance des missile sur le joueur
 * 
 * @author Rémy
 *
 */

public class BossFutur extends Monster {

	/** Liste de tir */
	private List<Shoot> listMissile;
	/** Intervalle pour le lancer de missiles */
	private int compteur = 1;
	
	public BossFutur() {
		super(0.05f, 200);
		listMissile = new ArrayList<Shoot>();
	}

	public void init() throws SlickException{
		super.init();
		super.MonsterAnim("boss_futur", 65, 75);
		xM = 448f;
		yM = 160f;
	}
	
	public void update(GameContainer gc, int delta) throws SlickException{
		super.update(gc, delta);
		if(alive){
			rectMonster.setBounds(xM+5,yM+2,55,70);
			if(rectMonster.intersects(Player.getRect())){
				Event.colision = true;
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
		
		if(alive && !Event.cinematic){
			int random =(int) (Math.random()*32+16);
			if(left && compteur == 0){
				listMissile.add(new Shoot("boss_futur_missile", xM, yM+random-20,2));				// 2 <=> left
			}
			if(right && compteur == 0){
				listMissile.add(new Shoot("boss_futur_missile",xM+20, yM+random-20,3));  // 3 <=> right
			}
			if(down && compteur == 0){
				listMissile.add(new Shoot("boss_futur_missile", xM+random-20, yM+10,1));  // 1 <=> down
			}
			if(up && compteur == 0){
				listMissile.add(new Shoot("boss_futur_missile", xM+random-20, yM+5,0));  // 0 <=> up
			}
			compteur++;	
			
			if(compteur > 60) compteur = 0;
			
			for(Shoot so:listMissile){
				so.updateMissile(delta);
				if(so.getRec().intersects(Player.getRect())){
					Event.colision = true;
					so.setShoot(false);
				}
			}	
		}	
	}
	
	/**
	 * Affichage du monstre et de ses tirs
	 * 
	 * @param g Sortie de l'écran
	 */
	public void renderBot(Graphics g) throws SlickException{
		super.renderBot(g);
		if(alive){
			for(Shoot so:listMissile){
				so.setShoot(true);
				so.render();
			}
		}
	}
	
}
