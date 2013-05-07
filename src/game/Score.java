package game;

import items.Item;

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

public class Score extends BasicGameState{

	private Image bg;
	private UnicodeFont uFont;
	
	public Score(int ID){
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {	
		this.bg = new Image("res/menu/backgroundLoad.jpg");
		uFont = new UnicodeFont("res/all/comic.ttf", 25 , false, false);
		uFont.addAsciiGlyphs();
		uFont.addGlyphs(400, 600);
		uFont.getEffects().add(new ColorEffect(java.awt.Color.BLACK));  
		uFont.loadGlyphs();	
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {	
		
		bg.draw(0, 0);
		
		int Nb_rubis = Item.getNB_Rubis();
		int Nb_saphirs = Item.getNB_Saphir();
		int score = Nb_rubis*20 + Nb_saphirs*50;
		uFont.drawString(210, 200, "Votre score est de "+score+" points");
		uFont.drawString(215, 300, "Merci d'avoir jouer à SkyRoom");
		uFont.drawString(195, 520, "Pressez la touche ESPACE pour continuer...");
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {		
		if(InputControl.inputPressed(Input.KEY_SPACE)){
			sbg.enterState(0);
			sbg.enterState(0, new FadeOutTransition(),new FadeInTransition());
		}
	}

	@Override
	public int getID() {
		return 20;
	}

}
