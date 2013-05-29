package monster;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import player.Player;
import event.Event;

/**
 * Classe créant le boss du monde espace.
 * Il tire des missiles par rafales de 3,
 * et tir dans les 4 directions en même temps
 * 
 * @author Rémy
 *
 */

public class BossSpace extends Monster{
	/** Liste de tir */
	private List<Shoot> list;
	/** Intervalle pour le lancer de missiles */
	private int compteur = 1;
	
	
	public BossSpace() {
		super(0.05f, 500);
		list = new ArrayList<Shoot>();
	}

	/**
	 * Initialisation de l'animation 
	 */
	public void init() throws SlickException{
		super.init();
		// On charge un sprite aléatoirement
		super.MonsterAnim("boss_space",64,64);
	}
	
	/**
	 * Boucle d'update permettant le lancement des lasers
	 * 
	 * @param gc GameContainer
	 * @param delta Permet de garder une vitesse constante selon les FPS
	 * @throws SlickException
	 */
	public void update(GameContainer gc, int delta) throws SlickException{
		super.update(gc, delta);
		
		if(alive){
			rectMonster.setBounds(xM+10,yM+15,50,45);
			if(rectMonster.intersects(Player.getRect())){
				Event.colision = true;
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
		
		if(alive && !Event.cinematic){
			int random =(int) (Math.random()*32+16);
			if(compteur == 0 || compteur==10 || compteur==20){
				list.add(new Shoot(xM+10, yM+random,3,"invaders"));				// 3 <=> left
			}
			if(compteur == 0 || compteur==10 || compteur==20){
				list.add(new Shoot(xM+55, yM+random,2,"invaders"));  // 2 <=> right
			}
			if(compteur == 0 || compteur==10 || compteur==20){
				list.add(new Shoot(xM+random, yM+55,1,"invaders"));  // 1 <=> down
			}
			if(compteur == 0 || compteur==10 || compteur==20){
				list.add(new Shoot(xM+random, yM+10,0,"invaders"));  // 0 <=> up
			}
			compteur++;	
		}
		
		if(compteur > 60) compteur = 0;
		
		for(Shoot so:list){
			so.update(delta);
			if(so.getCircle().intersects(Player.getRect())){
				Event.colision = true;
				so.setShoot(false);
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
			for(Shoot so:list){
				so.setShoot(true);
				so.render();
			}
		}
	}
}
