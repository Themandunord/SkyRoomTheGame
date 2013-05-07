package game;

import map.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import player.Player;
import event.Event;

/**
 * Classe permettant de gérer le Game Over
 * 
 * @author Rémy
 */

public class GameOver extends BasicGameState{
	/** Image du fond */
	private Image background,backgroundgirl;
	/** Musique lors du GameOver */
	private Music music;
	
	/**
	 * Constructeur pour les states
	 * 
	 * @param ID Numéro de la state
	 */
	public GameOver(int ID){}	
	
	/**
	 * Boucle d'initialisation
	 * 
	 * @param gc GameContainer
	 * @param sbg State
	 * @throws SlickException
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("res/all/GameOver.png");
		backgroundgirl = new Image("res/all/GameOverGirl.png");
		music = new Music("res/music/gameOver.ogg",true);	
	}
	
	/**
	 * Boucle de rendu 
	 * @param gc GameContainer
	 * @param sbg State
	 * @param g Sortie à l'écran
	 * @throws SlickException
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
	
		g.setColor(Color.blue); // Permet de changer la couleur du texte en bleu
		if(Player.getSex().equals("boy"))
			background.draw(0,0);
		else 
			backgroundgirl.draw(0,0);
	}

	/**
	 * Boucle d'update du jeu
	 * 
	 * @param gc GameContainer
	 * @param sbg State
	 * @param delta Permet d'éviter le changement de vitesse selon les FPS
	 * @throws SlickException
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		InputControl.gc = gc;
		if(!music.playing()){ // Regarde si la musique est en cours ou non. Permet de gérer la boucle update
			music.loop();
			music.setVolume(Event.volume);
		}
		Map.setInitNPC(false);
		if(InputControl.inputPressed(Input.KEY_SPACE)){
			music.stop();  // On stop la musique
			Game.setNewGame(false);
			Event.STARTED=true;
			sbg.enterState(0);
			sbg.enterState(0, new FadeOutTransition (),new FadeInTransition());
		}

	}
	
	/**
	 * Retourne l'ID de la state
	 * 
	 * @param ID Numéro de la state
	 */
	@Override
	public int getID() {
		return 3;
	}

}
