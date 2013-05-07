package event;

import map.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Classe créant un objet/image qui se tourne
 * 
 * @author Rémy
 *
 */

public class RotationImage {

	/** Image a faire tourner */
	private Image img;
	/** True si l'image a été tournée */
	private boolean isRotate;
	
	/**
	 * Instancie une image à faire tourner : event-xxx.png
	 * 
	 * @param name nom de l'image
	 * @throws SlickException
	 */
	public RotationImage(String name) throws SlickException{
		this.img = new Image("res/event/event-"+name+".png");
		this.isRotate = false;
	}
	
	/**
	 * Méthode permettant de faire tourner l'image
	 * 
	 * @param event Evenement relatif a cette image
	 * @param IDx IDx de la map
	 * @param IDy IDy de la map
	 * @param posX x 
	 * @param posY y
	 * @param angle angle de rotation en degré
	 * @param finX x : position finale 
	 * @param finY y : position finale
	 */
	public void render(boolean event, int IDx, int IDy, int posX, int posY, int angle, int finX, int finY){
		if(img!=null){
			if(Map.isMap(IDx, IDy)){
				if(!event){
					img.draw(posX,posY);
				}
				else {
					if(!isRotate){
						img.rotate(angle);
						isRotate = true;
					}
					img.draw(finX,finY);
				}
			}
		}
	}
}
