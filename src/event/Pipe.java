package event;

import map.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Classe permettant la cration des tuyaux du monde Futur 
 * 
 * @author Rémy
 */

public class Pipe {
	/** Image du tuyau */
	private Image pipe;
	/** Coordonnées du tuyau */
	private int x, y;
	/** Position du tuyau */
	private String pos;
	/** Numéro relatif à l'intérrupteur */
	private int ID;
	
	/**
	 * Initialisation des tuyaux
	 * @param pos Direction du tuyau
	 * @param x Coordonnées des abscisses
	 * @param y Coordonnées des ordonnées
	 * @param ID Numéro correspondant à l'intérupteur
	 * @throws SlickException
	 */
	public Pipe(String pos,int x, int y, int ID) throws SlickException{
		this.x=x;
		this.y=y;
		this.pos=pos;
		this.ID=ID;
		if(pos.equals("vertical"))
			this.pipe = new Image("res/event/pipeVertical.png");
		if(pos.equals("horizontal"))
			this.pipe = new Image("res/event/pipeHorizontal.png");
	}
	/**
	 * On affiche les tuyaux selon leur positions
	 */
	public void render(){
		if(pipe!=null){
			if(Map.isMap(3,10)){
				if(!Map.isOne() && ID==1)
					pipe.draw(x,y);
				if(!Map.isTwo() && ID==2)
					pipe.draw(x, y);
				if(!Map.isThree() && ID==3)
					pipe.draw(x,y);
				if(!Map.isFour() && ID==4)
					pipe.draw(x,y);
				
			}
			if(Map.isMap(2,9)){
				if(!Map.isFive() && ID==5)
					pipe.draw(x,y);
			}
		}
	}
	/**
	 * On redéfinie la méthode toString
	 */
	public String toString(){
		return pos+" "+String.valueOf(x)+" "+String.valueOf(y);
	}
}
