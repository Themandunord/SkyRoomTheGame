package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import particle.Particle;

/**
 * Classe permettant l'affichage de l'introduction du jeu
 * 
 * @author Rémy
 */

public class Intro extends BasicGameState {
	/** Texte à l'écran */
	private String intro;
	/** Abscisse et coordonnée du texte au départ */
	private float x = 50f,y=610f;
	/** Image de fond de l'introdcution */
	private Image background, intro_texte;
	/** Police de caractère de l'introduction */
	private UnicodeFont uFont;
	private Particle particle;
	
	/**
	 * Constructeur relatif aux States
	 * 
	 * @param intro Textes de l'introduction
	 */
	public Intro(int intro){}

	/**
	 * Initialisation des éléments
	 * 
	 * @param gc GameContainer
	 * @param sbg State
	 * @throws SlickException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		intro = "En cette année de grâce 20XX, les élèves sont de moins en moins performants \n" +
				"et ils sont de plus en plus nombreux à dormir pendant les cours, name \n" +
				"n’échappe pas à cette règle fatidique. Les professeurs, exaspérés par cet \n" +
				"état de fait, ont tout essayé pour résoudre ce problème avant de se rendre \n" +
				"compte que la méthode ancestral consistant à un bon haussement de voix était \n" +
				"de loin la plus efficace. C’est après l’une de ces sempiternelles élévations \n" +
				"de tonalité que nous vous retrouvons, name, dans l’amphi si bien connue \n" +
				"des élèves de l’école.";
		background = new Image("res/menu/backgroundLoad.jpg");
		intro_texte = new Image("res/intro/intro.png");
		uFont = new UnicodeFont("res/all/comic.ttf", 15 , false, false);
		uFont.addAsciiGlyphs();
		uFont.addGlyphs(400, 600);
		uFont.getEffects().add(new ColorEffect(java.awt.Color.BLUE));  
		uFont.loadGlyphs();
		
		particle = new Particle();
		particle.init("menu");
	}

	/**
	 * Affiche Le fond et le texte
	 * 
	 * @param gc GameContainer
	 * @param sbg State
	 * @param g Sortie de l'écran
	 * @throws SlickException
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		String pseudo = Menu.getPseudo();
		String introduction = intro.replaceAll("name", Menu.getPseudo());
		background.draw(0,0);
		particle.render(400, 300);
		intro_texte.draw(x, y);
		//uFont.drawString(x, y, introduction);
	}

	/**
	 * Permet de modifier les coordonnée du texte
	 * 
	 * @param gc GameContainer
	 * @param sbg State
	 * @param delta Permet d'éviter le changement de vitesse selon les FPS
	 * @throws SlickException
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		InputControl.gc = gc;
		if(InputControl.inputDown(Input.KEY_SPACE)){
			y -= 15;
		}
		y -= 1;
		if(y<-500){
			sbg.enterState(2);
			sbg.enterState(2, new FadeOutTransition (),new FadeInTransition(Color.black));
			y = 610f;
		}
		particle.update(delta);
	}
	
	/**
	 * Retourne l'ID de la state
	 */
	public int getID(){
		return 1;
	}

}
