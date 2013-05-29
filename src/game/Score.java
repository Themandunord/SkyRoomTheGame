package game;

import items.Item;

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

import event.Event;

public class Score extends BasicGameState{

	private Image bg,espace,score,rubis,saphirs,timing,point;
	private UnicodeFont uFont;
	
	public Score(int ID){
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {	
		this.bg = new Image("res/menu/backgroundLoad.jpg");
		this.espace = new Image("res/score/espace.png");
		this.score = new Image("res/score/score.png");
		this.rubis = new Image("res/score/rubis.png");
		this.saphirs= new Image("res/score/saphirs.png");
		this.timing = new Image("res/score/timing.png");
		this.point = new Image("res/score/points.png");
		uFont = new UnicodeFont("res/score/police.ttf", 35  , false, false);
		uFont.addAsciiGlyphs();
		uFont.addGlyphs(400, 600);
		java.awt.Color color = new java.awt.Color(136, 213, 242);
		uFont.getEffects().add(new ColorEffect(color));  
		uFont.loadGlyphs();	
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {	
		
		bg.draw(0, 0);
		espace.draw(60, 500);
		rubis.draw(230,120);
		saphirs.draw(200,200);
		score.draw(230,0);
		timing.draw(175, 300);
		point.draw(380, 400);
		int Nb_rubis = Item.getNB_Rubis();
		int Nb_saphirs = Item.getNB_Saphir();
		int time = Event.time*60;
		int score = (int) (3000*Math.exp(-0.00075*(time - 15*60))+50*Nb_rubis+20*Nb_saphirs);
		uFont.drawString(400, 130, ""+Nb_rubis);
		uFont.drawString(400, 210, ""+Nb_saphirs);
		uFont.drawString(350, 310, ""+time/60);
		uFont.drawString(250, 420, ""+score);
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
