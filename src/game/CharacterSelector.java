package game;

import org.lwjgl.input.Mouse;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import particle.Particle;
import player.Player;

/**
 * Classe de la State CharacterSelector.
 * Crée la vue de la sélection de personnage.
 * 
 * @author Rémy
 *
 */

public class CharacterSelector extends BasicGameState{
	
	/** Images nécessaires à la vue */
	private Image background,cadreGirl,cadreBoy;
	/** SpriteSheet pour les animations */
	private SpriteSheet spriteBoy,spriteGirl;
	/** Animation de la fille et du garçon */
	private Animation girl, boy;
	/** MouseOverArea des cadres */
	private MouseOverArea cBoy,cGirl,back;
	/** Particules de fond */
	private Particle particle;
	/** Position de la conlombe */
	private float x=400,y;
	/** SpriteSheet de la colombe */
	private SpriteSheet spriteDove;
	/** Animation de la colombe */
	private Animation animDove;
	/** Fréquence du sinus */
	private int freq;
	
	/**
	 * Constructeur pour les states
	 * 
	 * @param ID
	 */
	public CharacterSelector(int ID){
		
	}
	
	/**
	 * Boucle d'initialisation
	 * 
	 * @param gc GameContainer
	 * @param sbg State
	 * @throws SlickException
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

		background = new Image("res/all/bg_choix.png");
		cadreGirl = new Image("res/all/cadre.png");
		cadreBoy = new Image("res/all/cadre.png");
		spriteBoy = new SpriteSheet("res/hero/boy-0.png",32,32);
		spriteGirl = new SpriteSheet("res/hero/girl-1.png",32,32);
		girl = new Animation(spriteGirl,0,0,2,0,true,200,true);
		boy = new Animation(spriteBoy,0,0,2,0,true,200,true);
		cBoy = new MouseOverArea(gc, cadreBoy, 250, 300);
		cBoy.setNormalColor(new Color(0.6f, 0.6f, 0.6f, 1f)); 					// état normal
		cBoy.setMouseOverColor(new Color(1f, 1f, 1f, 1f)); 		
		cGirl= new MouseOverArea(gc, cadreGirl, 450, 300);
		cGirl.setNormalColor(new Color(0.6f, 0.6f, 0.6f, 1f)); 					// état normal
		cGirl.setMouseOverColor(new Color(1f, 1f, 1f, 1f)); 
		back = new MouseOverArea(gc, new Image("res/option/retour.png"), 580, 500);
		back.setNormalColor(new Color(0.6f, 0.6f, 0.6f, 1f)); 					
		back.setMouseOverColor(new Color(1f, 1f, 1f, 1f)); 
		
		spriteDove = new SpriteSheet("res/hero/compagnon.png",32,48);
		animDove = new Animation(spriteDove,3,2,5,2,true,200,true);
		
		particle = new Particle();
		particle.init("menu");
		freq = 130;
	}

	/**
	 * Boucle de rendu 
	 * @param gc GameContainer
	 * @param sbg State
	 * @param g Sortie à l'écran
	 * @throws SlickException
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		background.draw(0, 0);
		particle.render(400, 300);
		cBoy.render(gc, g);
		cGirl.render(gc,g);
		back.render(gc, g);
		girl.start();
		boy.start();
		boy.draw(270, 370);
		girl.draw(470, 370);
		animDove.start();
		animDove.draw(x-500, y+300);
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
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		int posX = Mouse.getX();   
		int posY = Math.abs((Mouse.getY())-600);
		
		if ((posX > cBoy.getX() && posX < (cBoy.getX()+cBoy.getWidth()) && (posY < cBoy.getY()+cBoy.getHeight() && posY > cBoy.getY()))) {
			if (Mouse.isButtonDown(0)) {
				Player.setSex("boy");
				if(Menu.pseudo.equals("Laura")) Menu.pseudo = "Kevin";
				sbg.enterState(10);
				sbg.enterState(10, new FadeOutTransition (),new FadeInTransition(Color.black)); 
			}
		}
		if ((posX > cGirl.getX() && posX < (cGirl.getX()+cGirl.getWidth()) && (posY < cGirl.getY()+cGirl.getHeight() && posY > cGirl.getY()))) {
			if (Mouse.isButtonDown(0)) {
				Player.setSex("girl");
				sbg.enterState(10);
				sbg.enterState(10, new FadeOutTransition (),new FadeInTransition(Color.black));
			}
		}
		if ((posX >back.getX() && posX < (back.getX()+back.getWidth())) && (posY < (back.getY()+back.getHeight()) && posY > back.getY())) {
			if (Mouse.isButtonDown(0)) {
					sbg.enterState(0);
					sbg.enterState(0, new FadeOutTransition (),new FadeInTransition(Color.black));
			}
		}
		
		particle.update(delta);
		
		if(x<860+500){
			x+=2f;
			y = (float)(Math.sin(x/freq)*120);
		}
		else {
			x=380;
			freq = (int) (Math.random()*50+100);
		}
	}

	/**
	 * Retourne l'ID de la state
	 * 
	 * @param ID Numéro de la state
	 */
	@Override
	public int getID() {
		return 6;
	}

}
