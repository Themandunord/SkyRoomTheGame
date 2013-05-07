package event;

import map.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;

import player.Player;

/**
 * Classe permettant de créer des interrupteur relatifs aux tuyaux 
 * de la classe Pipe
 * Changement d'image lors de l'activation de l'interrupteur
 * 
 * @author Rémy
 */

public class Switch {
	/**  Coordonnées de l'interrupteur */
	private int x,y;
	/** Activé ou non */
	private boolean actived = false;
	/** Image correspondant aux différents états de l'interrupteur */
	private Image active, noActive;
	/** Cercle permettant la collision avec le personnage */
	private Circle circle;
	/**  Numéro relatif au tuyau */
	private int ID;
	
	/**
	 * On initialise l'interrupteur
	 * @param x Abscisse
	 * @param y Ordonnées
	 * @param ID Numéro correspondant aux tuyaux
	 * @throws SlickException
	 */
	public Switch(int x, int y,int ID) throws SlickException{
		this.x = x;
		this.y = y;
		this.ID = ID;
		this.actived = false;
		noActive = new Image("res/event/noActiveSwitch.png");
		active = new Image("res/event/activeSwitch.png");
		circle = new Circle(x+16, y+16, 10);
	}
	
	/**
	 * Classe permettant le rendu des intérrupteur
	 */
	public void render(int IDX, int IDY){
		
		if(Map.isMap(IDX, IDY)){
			if(!actived){
				noActive.draw(x, y);
			}
			else active.draw(x, y);
		
			circle.setLocation(x, y);
			
			if(Player.getRect().intersects(this.circle)){
				actived=true;
				if(ID==1)
					Map.setOne(true);
				if(ID==2)
					Map.setTwo(true);
				if(ID==3)
					Map.setThree(true);
				if(ID==4)
					Map.setFour(true);
				if(ID==5)
					Map.setFive(true);
			}
		}
	}
}
