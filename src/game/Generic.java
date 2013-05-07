package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import particle.Particle;

/**
 * Classe créant le générique du jeu 
 * 
 * @author Rémy
 *
 */

public class Generic extends BasicGameState {

	private Image generic,bg;
	private int height, width,x=400,y=200;
	private Particle artifice;
	
	/**
	 * Constructeur relatif aux States
	 * 
	 * @param ID
	 */
	public Generic(int generic) {}

	/**
	 * Initialisation des éléments
	 * Ce lance une seul fois au cours du jeu
	 * 
	 * @param gc GameContainer
	 * @param sbg State
	 * @throws SlickException
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		generic = new Image("res/menu/generic.png");
		bg = new Image("res/menu/backgroundLoad.jpg");
		artifice = new Particle();
		artifice.init("ligne");
	}

	/**
	 * Boucle de rendu
	 * Permet l'affichage du logo
	 * 
	 * @param gc GameContainer
	 * @param sbg State
	 * @param g Sortie de l'écran
	 * @throws SlickException
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		bg.draw(0,0);
		
		generic.draw(x,y,width,height);
		artifice.render(400,y+height);
	}

	/**
	 * Boucle d'update
	 * Permet de faire grandir le logo
	 * 
	 * @param gc GameContainer
	 * @param sbg State
	 * @param delta Permet d'éviter le changement de vitesse selon les FPS
	 * @throws SlickException
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if(width<=800)
			width+=4;
		if(height<197)
			height+=1;
		if(x>0)
			x-=2;
		
		if(width>795)
			sbg.enterState(0, new FadeOutTransition (Color.black),new FadeInTransition(Color.black));
		
		artifice.update(delta);
	}

	/**
	 * On récupère l'ID de la state
	 */
	@Override
	public int getID() {
		return 5;
	}

}
