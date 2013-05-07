package monster;

import event.Event;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import player.Player;

/**
 * Classe créant des robots qui tire des missiles 
 * sur le personnage
 * 
 * @author Rémy
 *
 */

public class Robot extends Monster{
	
	/** Liste de tir */
	private List<Shoot> listShoot;
	/** Intervalle pour le lancer de missiles */
	private int compteur = 1;

	/** 
	 * Constructeur par défaut
	 * On lui donne une vitesse de 0.05 et on 
	 * instancie la liste de tir
	 */
	public Robot(){
		super(0.05f,30);
		listShoot = new ArrayList<Shoot>();
	}
	
	/**
	 * Initialisation de l'animation 
	 */
	public void init() throws SlickException{
		super.init();
		// On charge un sprite aléatoirement
		super.MonsterAnim("robot",32,32);
	}
	
	/**
	 * Boucle d'update permettant le lancement des missiles
	 * 
	 * @param gc GameContainer
	 * @param delta Permet de garder une vitesse constante selon les FPS
	 * @throws SlickException
	 */
	public void update(GameContainer gc, int delta) throws SlickException{
		super.update(gc, delta);
		
		if(alive && !Event.cinematic){
			if(left && compteur == 0){
				listShoot.add(new Shoot(xM+16, yM+16,3,"tir"));
			}
			if(right && compteur == 0){
				listShoot.add(new Shoot(xM+16, yM+16,2,"tir"));
			}
			if(down && compteur == 0){
				listShoot.add(new Shoot(xM+16, yM+16,1,"tir"));
			}
			if(up && compteur == 0){
				listShoot.add(new Shoot(xM+16, yM+16,0,"tir"));
			}
			compteur++;
		}
		if(compteur > 60) compteur =0;
		
		
		for(Shoot so:listShoot){
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
			for(Shoot so:listShoot){
				so.setShoot(true);
				so.render();
			}
		}
	}
}
