package game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import event.Event;

/**
 * Classe correspondant au menu Option
 * 
 * @author Rémy
 *
 */

public class Option extends BasicGameState {
	/** Volume en cours */
	private int vol=100;
	/** Boutons */
	private MouseOverArea plus,moins,joystick,keyboard;
	public static MouseOverArea back;
	/** Image de fond du menu option*/
	public static  Image background;
	
	/**
	 * Constructeur relatifs aux States
	 * 
	 * @param ID Numéro du state
	 */
	public Option(int ID) {}

	/**
	 * Initialisation des boutons
	 * 
	 * @param gc GameContainer
	 * @param sbg State
	 * @throws SlickException
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		plus = new MouseOverArea(gc, new Image("res/option/plus.png"), 650, 70);
		plus.setNormalColor(new Color(0.6f, 0.6f, 0.6f, 1f)); 					
		plus.setMouseOverColor(new Color(1f, 1f, 1f, 1f)); 
		moins = new MouseOverArea(gc, new Image("res/option/moins.png"), 300, 70);
		moins.setNormalColor(new Color(0.6f, 0.6f, 0.6f, 1f)); 			
		moins.setMouseOverColor(new Color(1f, 1f, 1f, 1f)); 
		back = new MouseOverArea(gc, new Image("res/option/retour.png"), 580, 500);
		back.setNormalColor(new Color(0.6f, 0.6f, 0.6f, 1f)); 					
		back.setMouseOverColor(new Color(1f, 1f, 1f, 1f)); 
		joystick = new MouseOverArea(gc, new Image("res/option/manette.png"), 435, 350);
		joystick.setNormalColor(new Color(0.6f, 0.6f, 0.6f, 1f)); 					
		joystick.setMouseOverColor(new Color(1f, 1f, 1f, 1f)); 
		keyboard = new MouseOverArea(gc, new Image("res/option/clavier.png"),135, 350);
		keyboard.setNormalColor(new Color(0.6f, 0.6f, 0.6f, 1f)); 					
		keyboard.setMouseOverColor(new Color(1f, 1f, 1f, 1f)); 
		background = new Image("res/option/bg_option.png");
	}

	/**
	 * Affiche les boutons et la progress bar
	 * 
	 * @param gc GameContainer
	 * @param sbg State
	 * @param g Sortie de l'écran
	 * @throws SlickException
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.draw(0,0);
		g.fillRoundRect(352, 85, Event.volume * 300, 35,20);
		g.setColor(Color.black);
		g.drawRoundRect(352, 85, Event.volume*300, 35,20);
		g.setColor(Color.white);
		plus.render(gc,g);
		moins.render(gc, g);
		back.render(gc, g);
		keyboard.render(gc, g);
		joystick.render(gc,g);
	}

	/**
	 * Augmente ou diminue le volume
	 * 
	 * @param gc GameContainer
	 * @param sbg State
	 * @param delta Permet d'éviter le changement de vitesse selon les FPS
	 * @throws SlickException
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		int posX = Mouse.getX();    // attrape la position de la souris
		int posY = Math.abs((Mouse.getY())-600);
		if (pos(plus, posX, posY)) {
			if (Mouse.isButtonDown(0)) {
				vol = (int) (Event.volume*101);
				vol++;
				if (vol > 100) vol = 100;
				Event.volume = vol/100.0f;
				Menu.getMusic().setVolume(Event.volume);
			}
		}
		if (pos(moins, posX, posY)) {
			if (Mouse.isButtonDown(0)) {
				vol = (int) (Event.volume*100);
				vol--;
				if (vol < 0) vol = 0;
				Event.volume = vol/100.0f;	
				Menu.getMusic().setVolume(Event.volume);
			}
		}
		if (pos(back, posX, posY)) {
			if (Mouse.isButtonDown(0)) {
				if(!PauseState.PAUSE){
					sbg.enterState(0);
					sbg.enterState(0, new FadeOutTransition (),new FadeInTransition(Color.black));
				}
				else {
					sbg.enterState(2);
					sbg.enterState(2, new FadeOutTransition (),new FadeInTransition(Color.black));
					PauseState.PAUSE = false;
				}
			}
		}
		
		if(pos(keyboard,posX,posY)){
			if (Mouse.isButtonDown(0)) {
				sbg.enterState(8);
				sbg.enterState(8, new FadeOutTransition (),new FadeInTransition(Color.black));
			}
		}
		if(pos(joystick,posX,posY)){
			if (Mouse.isButtonDown(0)) {
				sbg.enterState(9);
				sbg.enterState(9, new FadeOutTransition (),new FadeInTransition(Color.black));
			}
		}
	}
	
	/**
	 * Retourne true si la souris est sur le bouton 
	 *
	 * @param a
	 * @param posX
	 * @param posY
	 * @return boolean
	 */
	private boolean pos(MouseOverArea a, int posX, int posY){
		if ((posX > a.getX() && posX < (a.getX()+a.getWidth()) && (posY < a.getY()+a.getHeight() && posY > a.getY()))) {
			return true;
		}
		return false;
	}

	/**
	 * @return ID de la state
	 */
	@Override
	public int getID() {
		return 4;
	}

}
