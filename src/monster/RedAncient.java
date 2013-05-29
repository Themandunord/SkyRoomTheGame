package monster;

import event.Event;
import particle.Particle;
import org.newdawn.slick.geom.Circle;

import items.Item;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import player.Player;

/**
 * Classe créant des anges rouges qui tirent des boules 
 * de feu sur le personnage
 * 
 * @author Alex,Rémy
 *
 */

public class RedAncient extends Monster{
	
	/** Liste de tir */
	private List<FireBall> listShoot;
	/** Intervalle pour le lancer de boules */
	private int compteur = 1;
	private boolean increaseNB;

	/** 
	 * Constructeur par dÃ©faut
	 * On lui donne une vitesse de 0.05 et on 
	 * instancie la liste de tir
	 */
	public RedAncient(){
		super(0.05f,250);
		listShoot = new ArrayList<FireBall>();
	}
	
	/**
	 * Initialisation de l'animation 
	 */
	public void init() throws SlickException{
		super.init();
		super.MonsterAnim("redAncient",32,32);
		if(Event.specialItemBonus){
			item = new Item("specialBonus");
			Event.specialItemBonus = false;
		}
	}
	
	/**
	 * Boucle d'update permettant le lancement des boules de feu
	 * 
	 * @param gc GameContainer
	 * @param delta Permet de garder une vitesse constante selon les FPS
	 * @throws SlickException
	 */
	public void update(GameContainer gc, int delta) throws SlickException{
		super.update(gc, delta);
		
		if(alive && !Event.cinematic){
			if(left && compteur == 0){
				listShoot.add(new FireBall("bouleDeFeu",xM+16, yM+16,3));
			}
			if(right && compteur == 0){
				listShoot.add(new FireBall("bouleDeFeu",xM+16, yM+16,2));
			}
			if(down && compteur == 0){
				listShoot.add(new FireBall("bouleDeFeu",xM+16, yM+16,1));
			}
			if(up && compteur == 0){
				listShoot.add(new FireBall("bouleDeFeu",xM+16, yM+16,0));
			}
			compteur++;
		}
		if(compteur > 80) compteur =0;
		
		
		for(FireBall so:listShoot){
			so.update(delta);
			if(so.getCircle().intersects(Player.getRect())){
				Event.colision = true;
				so.setShoot(false);
			}
		}
		
		if(!alive && !increaseNB){
			increaseNB = true;
			Event.special_item_bonus++;
		}
	}

	/**
	 * Affichage du monstre et de ses tirs
	 * 
	 * @param g Sortie de l'Ã©cran
	 */
	public void renderBot(Graphics g) throws SlickException{
		super.renderBot(g);
		if(alive){
			for(FireBall so:listShoot){
				so.setShoot(true);
				so.render();
			}
		}
	}
}
