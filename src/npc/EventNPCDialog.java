package npc;

import java.io.FileNotFoundException;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import event.Event;

import game.Game;
import game.Menu;
import map.Map;

/**
 * Classe permettant de créer des dialogue événementiels
 * 
 * @author Rémy
 *
 */

public class EventNPCDialog {
	
	/** Instanciation du NPCDialog utile pour récupérer le dialogue à afficher */
	private static NPCDialog npc = new NPCDialog();
	/** Le dialogue à afficher */
	private static String dialog;
	/** La dialbox */
	private static Image dialbox;
	/** Durée d'affichage du message */
	private static int compteur;
	/** True si il y a un événement */
	private static boolean isDialogEvent;
	/** Nom du pnj associé à cette événement */
	private static String NPCName;
	protected SpriteSheet exclamation;
	public static Animation excla;
	
	
	/**
	 * Constructeur par défaut permettant d'instancier l'image 
	 * de la dialbox
	 */
	public EventNPCDialog(){
		try {
			dialbox = new Image("res/all/dialboxEvent.png");
			exclamation = new SpriteSheet("res/npc/excla.png",32,32);
			excla = new Animation(exclamation,0,0,2,0,true,200,true);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Rendu du message selon la position du joueur
	 * et durant un certain temps
	 * 
	 * @param g Sortie de l'écran
	 * @throws FileNotFoundException
	 */
	public void renderDialog(Graphics g) throws FileNotFoundException{
	
		if(Map.isMap(0, 0)){ 
			renderDial(200, 470, 001, g);
			EventNPCDialog.NPCName = "prof";
		}
		else if(Map.isMap(0, 3)){
			renderDial(200,470, 003, g);
			EventNPCDialog.NPCName = "yoshi";
		}
		else if(Map.isMap(2, 11) && Event.halo == 3){
			renderDial(200,470, 209, g);
			EventNPCDialog.NPCName = "halo";
		}
		else if(Map.isMap(2, 11)){
			renderDial(200,470, 208, g);
			EventNPCDialog.NPCName = "halo";
		}
		else compteur = 0;
	}
	
	/** 
	 * Affiche le dialogue désiré
	 * 
	 * @param x Position sur l'écran
	 * @param y Position sur l'écran
	 * @param ID Numéro du dialogue
	 * @param g Sortie sur l'écran
	 * @throws FileNotFoundException
	 */
	public static void renderDial(int x, int y , int ID,Graphics g) throws FileNotFoundException{
		
		if(compteur<240){
			isDialogEvent =true;
			Event.cinematic = true;
			npc.NPCDialoge(ID);
			String name = Menu.getPseudo();
			dialog = npc.getDialog();
			dialog = dialog.replaceAll("name", name);
			dialbox.draw(x,y);
			Game.uFont.drawString(x+15,y+5,""+dialog);
			compteur++;
		}
		else {
			isDialogEvent=false;
			Event.cinematic = false;
		}
	}
	
	/**
	 * @return isDialogEvent True si un événement a lieu
	 */
	public static boolean isDialogEvent(){
		return isDialogEvent;
	}
	
	/**
	 * @return NPCName Nom du pnj relatif à l'événement
	 */
	public static String getName(){
		return NPCName;
	}
}
