package event;

import java.io.IOException;

import map.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import particle.Particle;
import player.Dove;
import player.Player;

public class SpaceShip {

	/** Image du vaisseau */
	private Image spaceShip;
	/** Position du vaisseau */
	private float x,y;
	/** Permet de savoir si le joueur est monté */
	private boolean takeOf;
	/** particule des réacteurs */
	private Particle draft;
	/** Rectangle permettant de faire monté le personnage */
	private Rectangle rec;
	
	/**
	 * On instancie le vaisseau
	 * 
	 * @throws SlickException
	 * @throws IOException
	 */
	public SpaceShip() throws SlickException, IOException{
		spaceShip = new Image("res/event/spaceShip.png");
		draft = new Particle();
		draft.init("fusee");
		this.x = 330;
		this.y = 250;
		rec = new Rectangle(x, y, 140, 125);
		this.takeOf = false;
	}
	
	/**
	 * Méthode qui détecte la collision avec le joueur et le fait monté dans le cas échéant.
	 * 
	 * @param delta delta de Slick
	 */
	public void update(int delta){
		if(Map.isMap(8, 1)){
			if (rec.intersects(Player.getRect())) {
				takeOf = true;
				Player.setVisiblePlayer(false);
				Dove.doveIsOnPlayer = false;
				Event.spartanSpace = false;
				draft.setReady(true);
				draft.update(delta);
			}
			if (takeOf) {
				y -= 0.1 * delta;
				draft.update(delta);
			}
			if(takeOf && y<-150){
				Map.setIDx(8);
				Map.setIDy(2);
				Player.setX(380);
				Player.setY(500);
				Player.setVisiblePlayer(true);
				Dove.doveIsOnPlayer = true;
			}
		}
	}
	
	/**
	 * Affiche le vaisseau et les particules
	 */
	public void render(){
		if(Map.isMap(8, 1)){
			if (takeOf){
			draft.render(x + 70, y + 125);
			}
			spaceShip.draw(x, y);
		}
	}
}
