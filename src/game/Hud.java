package game;

import items.Item;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import player.Blaster;

import event.Event;

/**
 * Classe permettant l'affichage de l'HUD 
 * du jeu
 * 
 * @author Rémy
 *
 */

public class Hud {
	/** Nombre de coeurs */
	private static int nbrHeart,bikiniLife;
	/** Image des éléments à droper et des coeurs, et du stuff*/
	private Image heart,rubis,saphir,bikini,blaster,sword,waterGun,hud,bo,empty_heart;

	/**
	 * Constructeur permettant de définir le nombre de coeurs 
	 * 
	 * @param nbrHeart Nombre de coeurs
	 */
	public Hud(int nbrHeart){
		Hud.nbrHeart = nbrHeart;
	}
	
	/**
	 * Initialisation des images
	 *  
	 * @param gc GameContainer
	 * @param sbg State
	 * @throws SlickException
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		heart = new Image("res/hud/heart.png");
		empty_heart = new Image("res/hud/empty_heart.png");
		rubis = new Image("res/items/item-rubis.png");
		saphir = new Image("res/items/item-saphir.png");
		bikini = new Image("res/items/item-bikini.png");
		blaster = new Image("res/items/item-blaster.png");
		sword = new Image("res/items/item-epee.png");
		waterGun = new Image("res/items/item-waterGun.png");
		hud = new Image("res/all/hud.png");
		bo = new Image("res/hud/bo.png");
	}
	
	/**
	 * Affiche les éléments sur l'écran du jeu7
	 * 
	 * @param sbg State
	 * @param g Sortie de l'écran
	 */
	public void render(StateBasedGame sbg, Graphics g){
		
		hud.draw(745, 0);
		
		if(nbrHeart>Event.maxHeart){
			nbrHeart = Event.maxHeart;
		}
		
		g.setColor(Color.white);
		for (int i = 0; i < Event.maxHeart; i++) {  // affiche le nombre de coeurs à l'ecran
			empty_heart.draw(10 + 16 * i, 10);
		}
		for (int i = 0; i < nbrHeart; i++) {  // affiche le nombre de coeurs à l'ecran
			heart.draw(10 + 16 * i, 10);
		}
		
		
		
		if(Event.bikini){
			bikini.draw(50, 28);
		}
		
		if(nbrHeart<=0){ // Passage au GameOver
			nbrHeart =1;
			sbg.enterState(3);
			sbg.enterState(3, new FadeOutTransition (),new BlobbyTransition(Color.black));
		}
		rubis.draw(745,5);
		saphir.draw(745,35);
		if(Event.blaster){
			blaster.draw(745, 70);
			g.drawString(""+Blaster.getAmmo(), 780, 75);
		}
		else if(Event.SWORD){
			sword.draw(750, 68);
		}
		else if(Event.WaterGun){
			waterGun.draw(750, 70);
		}
		else if(Event.bo){
			bo.draw(750,68);
		}
		
		g.drawString(""+Item.getNB_Rubis(), 775, 10);
		g.drawString(""+Item.getNB_Saphir(), 775, 40);
		

	}

	/**
	 * @return Le nombres de coeurs restant au joueur
	 */
	public static int getNbrHeart() {
		return nbrHeart;
	}

	/**
	 * Modifie le nombre de coeurs
	 * @param nbrHeart Nombre de coeurs
	 */
	public static void setNbrHeart(int nbrHeart) {
		Hud.nbrHeart = nbrHeart;
	}
	
	/**
	 * On récupère le nombre de vie du bikini
	 * @return nombre de vie du bikini
	 */
	public static int getBikiniLife() {
		return bikiniLife;
	}

	/**
	 * On modifie la vie du bikini
	 * @param bikiniLife
	 */
	public static void setBikiniLige(int bikiniLife) {
		Hud.bikiniLife = bikiniLife;
		
	}

}
