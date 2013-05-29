package event;

import map.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import player.Player;

/**
 * Classe créant une cinématique à l'aide de plusieurs images
 * 
 * @author Rémy
 *
 */

public class Cinematic {

	/** Image de la cinématique */
	private Image img;
	/** Image entre deux image */
	private Image empty;
	/** Compteur permettant un délai entre deux images */
	private int cpt;
	/** Tableau contenant les différents nom des images */
	private String[] name;
	/** Numéro de la cinématique en cours */
	private int current_nbr;
	/** Boolean permettant de savoir si l'image a été chargé. */
	private boolean isLoad;
	
	
	/**
	 * Constructeur permettant d'instancier la cinématique
	 * 
	 * @param name tableau comportant le nom des différentes images : cine-xxx.png
	 */
	public Cinematic(String[] name){
		this.name = name;
		this.current_nbr = 0;
		this.cpt = 0;
		try {
			empty = new Image("res/cinematic/vide.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Affiche la cinématique en jeu
	 * 
	 * @param IDX IDx de la map
	 * @param IDY IDy de la map
	 * @param nbr Nombre d'images
	 * @param duration Durée entre deux images
	 */
	public void render(int IDX, int IDY, int nbr, int duration, int ID){
		if(Map.isMap(IDX, IDY)){
			if(current_nbr<=nbr){
				Event.cinematic = true;
				Player.setMoving(false);
				if(cpt<current_nbr*duration){
					cpt++;
					if(!isLoad){
						try {
							if(img!=null)
								img.destroy();
							img = new Image("res/cinematic/cine-"+name[current_nbr-1]+".png");
						} catch (SlickException e) {
							e.printStackTrace();
						}
						isLoad = true;
					}
					if(img!=null)
						img.draw(0, 0);
				}
				else{
					current_nbr++;
					if(empty!=null)
						empty.draw(0, 0);
					isLoad = false;
				}
			}else{
				Event.cinematic = false;
				if(img!=null){
					try {
						img.destroy();
					} catch (SlickException e1) {
						e1.printStackTrace();
					}
				}
				switch(ID){
				case 1 : Event.cine_dark = true; break;
				case 2 : Event.cine_prehistoire = true; break;
				case 3 : Event.cine_stormtrooper = true; break;
				case 4 : Event.cine_bonus = true; break;
				case 5 : Event.cine_futur = true; break;
				case 6 : Event.cine_chinois = true; break;
				case 7 : Event.cine_renaissance = true; break;
				case 8 : Event.cine_skyroom = true; break;
				case 9 : Event.cine_spartaland = true; break;
				}
				Player.setMoving(true);
				img = null;
			}
		}
	}
	
}
