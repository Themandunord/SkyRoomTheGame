	package player;

import event.Event;
import game.Game;
import game.Hud;
import game.Menu;

import map.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Classe créant la colombe de la Skyroom !
 * 
 * @author Rémy
 *
 */

public class Dove {

	/** SpriteSheet de l'animation */
	private SpriteSheet spriteCompagnon;
	/** Animation de la colombe */
	private Animation compagnon_right, compagnon_left, compagnon_up, compagnon_down,current_compagnon;
	/** Position de la colombe */
	private int x,y;
	/** True si la colombe suit le joueur */
	public static boolean doveIsOnPlayer;
	/** True si c'est initialisé */
	private boolean isInit;
	/** Durée du dialogue */
	private static int cpt_dialog;
	/** Image de la dialbox */
	private static Image dialbox;
	/** Image de la colombe affichée au dessus de la dialbox */
	private static Image dove_image;
	/** dialogue de son arrivée */
	private static String dialog_colombe_spartiate;
	private boolean isSay;
	
	public Dove(){
		this.cpt_dialog = 0;
	}
	
	/**
	 * Initialisation de la colombe 
	 * 
	 * @throws SlickException
	 */
	public void init() throws SlickException{
		dialbox = new Image("res/all/dialboxEvent.png");
		dove_image = new Image("res/hero/dove.png");
		spriteCompagnon = new SpriteSheet("res/hero/compagnon.png",32,48);
		compagnon_up = new Animation(spriteCompagnon,3,3,5,3,true,200,true);
		compagnon_down = new Animation(spriteCompagnon,3,0,5,0,true,200,true);
		compagnon_left = new Animation(spriteCompagnon,3,1,5,1,true,200,true);
		compagnon_right = new Animation(spriteCompagnon,3,2,5,2,true,200,true);
		compagnon_down.stop();
		compagnon_left.stop();
		compagnon_up.stop();
		compagnon_right.stop();
		current_compagnon = compagnon_up;
		this.x = 432;
		this.y = -32;
	}
	
	/**
	 * Affichage de la colombe
	 */
	public void render(){
		if(Map.isMap(2, 2) && !isInit){
			Event.isDove = true;
			isInit = true;
		}
		
		if(Event.isDove && Map.isMap(2,2)){
			current_compagnon.start();
			current_compagnon.draw(x, y);
			if(x>=Player.getX()+5){
				x-=3; 
				current_compagnon = compagnon_left;
			}
			else if(y<=Player.getY()+5){
				y+=3;
				current_compagnon = compagnon_down;
			}
			else if(x<Player.getX()){
				x+=3; 
				current_compagnon = compagnon_right;
			}
			else if(y>Player.getY()){
				y-=3;
				current_compagnon = compagnon_up;
			}
			if(Math.sqrt(Math.pow(x-Player.getX(),2)+Math.pow(y - Player.getY(),2)) < 25){
				Event.isDove = false;
				doveIsOnPlayer = true;
				Event.doveOnPlayer = true;
			}
		}
		
		if(Event.doveOnPlayer){
			current_compagnon.start();
			current_compagnon.draw(Player.getX()-15, Player.getY()-20);
			
			if(Player.getDirection()==1) current_compagnon = compagnon_up; //up
			if(Player.getDirection()==2) current_compagnon = compagnon_down; //down
			if(Player.getDirection()==3) current_compagnon = compagnon_right; //right
			if(Player.getDirection()==4) current_compagnon = compagnon_left; // left
		}
	}
	/**
	 * Dialogue lors de son arrivée
	 */
	public void renderDialogDove(){
		if(doveIsOnPlayer && !isSay && !Event.dialog_dove){
			if(cpt_dialog<200){
				dove_image.draw(200, 380);
				dialbox.draw(200, 470);
				dialog_colombe_spartiate = "Bonjour name, je suis la colombe de la SkyRoom.\n" +
				"Je suis ici pour te guider dans cette aventure.\n" +
				"Tu vois l'homme là-bas ? Tu devrais aller lui parler.";
				dialog_colombe_spartiate = dialog_colombe_spartiate.replaceAll("name", Menu.getPseudo());
				Game.uFont.drawString(210,477,dialog_colombe_spartiate);
				cpt_dialog++;
				
			}
			else {
				isSay = true;
				Event.dialog_dove = true;
			}
		}
	}
	
	/**
	 * Méthode permettant d'afficher un dialogue de la colombe dans n'importe quelle classe 
	 * 
	 * @param text Texte du dialog
	 */
	public static void renderDoveDialog(String text){
			dove_image.draw(200, 380);
			dialbox.draw(200, 470);
			Game.uFont.drawString(210,477,text);
	}
}
