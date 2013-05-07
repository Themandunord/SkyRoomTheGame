package monster;

import event.Cinematic;
import event.Event;
import game.Menu;

import items.Item;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import player.Player;

/**
 * Classe créant des stormtroopers qui tirent des lasers 
 * par rafales de 3, et des grenades sur le personnage
 * 
 * @author Alexandre Polaire Fluo
 *
 */
//la grenade n'est pas encore faite
public class Stormtrooper extends Monster{
	
	/** Liste de tir */
	private List<Shoot> listBlaster,listGrenade;
	/** Intervalle pour le lancer de missiles */
	private int compteur = 1;

	/** 
	 * Constructeur par défaut
	 * On lui donne une vitesse de 0.05 et on 
	 * instancie la liste de tir
	 */
	public Stormtrooper(){
		super(0.05f,90);
		listBlaster = new ArrayList<Shoot>();
		listGrenade = new ArrayList<Shoot>();
	}
	
	/**
	 * Initialisation de l'animation 
	 */
	public void init() throws SlickException{
		super.init();
		// On charge un sprite aléatoirement
		super.MonsterAnim("stormtrooper",32,32);
		if(Math.random()<0.2)
			item = new Item("rubis");
		else if(Math.random()<0.3)
			item = new Item("heart");
		else if(Math.random()<0.6)
			item = new Item("blaster");
		else  
			item = new Item("saphir");
	}
	
	/**
	 * Boucle d'update permettant le lancement des lasers et des grenades 
	 * en fonction de la position du joueur
	 * 
	 * @param gc GameContainer
	 * @param delta Permet de garder une vitesse constante selon les FPS
	 * @throws SlickException
	 */
	public void update(GameContainer gc, int delta) throws SlickException{
		super.update(gc, delta);
		
		if(alive && !Event.cinematic){
			if(Math.sqrt(Math.pow(xM-Player.getX(),2)+Math.pow(yM - Player.getY(),2)) > 130){
				if(left && (compteur == 0 || compteur==10 || compteur==40)){
					listBlaster.add(new Shoot(xM, yM+10,3,1,"blasterShot"));	
					Menu.laser.play(1,Event.volume);// 3 <=> left
				}
				if(right && (compteur == 0 || compteur==10 || compteur==40)){
					listBlaster.add(new Shoot(xM+10, yM+10,2,1,"blasterShot"));
					Menu.laser.play(1,Event.volume);// 2 <=> right
				}
				if(down && (compteur == 0 || compteur==10 || compteur==40)){
					listBlaster.add(new Shoot(xM, yM+10,1,0,"blasterShot"));
					Menu.laser.play(1,Event.volume);// 1 <=> down
				}
				if(up && (compteur == 0 || compteur==10 || compteur==40)){
					listBlaster.add(new Shoot(xM+10, yM+10,0,0,"blasterShot"));
					Menu.laser.play(1,Event.volume);// 0 <=> up
				}
				compteur++;
			}
			else{
				if(left && compteur == 0){
					listGrenade.add(new Shoot(xM, yM+10,3,"grenade"));				// 3 <=> left
				}
				if(right && compteur == 0){
					listGrenade.add(new Shoot(xM+10, yM+10,2,"grenade"));  // 2 <=> right
				}
				if(down && compteur == 0){
					listGrenade.add(new Shoot(xM, yM+10,1,"grenade"));  // 1 <=> down
				}
				if(up && compteur == 0){
					listGrenade.add(new Shoot(xM+10, yM+10,0,"grenade"));  // 0 <=> up
				}
				compteur++;	
			}
		}
		
		if(compteur > 80) compteur =0;
		
		for(Shoot so:listGrenade){
			so.updateGrenade(xM+16, yM+16, delta);
			if(so.getCircle().intersects(Player.getRect())){
				Event.colision = true;
			}
		}
		
		for(Shoot so:listBlaster){
			so.updateBlaster(delta);
			if(so.getRec().intersects(Player.getRect())){
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
			for(Shoot so:listGrenade){
				if(so.getCptFinishExplo()<50)
					so.setShoot(true);
				else so.setShoot(false);
				so.render();
			}
			for(Shoot so:listBlaster){
				so.setShoot(true);
				so.render();
			}
		}
	}
}
