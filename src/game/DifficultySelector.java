package game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class DifficultySelector extends BasicGameState {

	private MouseOverArea easy,medium,hard,god,back;
	private Image bg;
	public static boolean EASY,MEDIUM,HARD,GOD;
	
	public DifficultySelector(int ID) {
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		// TODO Auto-generated method stub
		bg = new Image("res/dificulty/bg.png");
		easy = new MouseOverArea(gc, new Image("res/dificulty/easy.png"), 300, 160);
		easy.setNormalColor(new Color(0.6f, 0.6f, 0.6f, 1f)); 					
		easy.setMouseOverColor(new Color(1f, 1f, 1f, 1f)); 
		medium = new MouseOverArea(gc, new Image("res/dificulty/medium.png"), 300, 230);
		medium.setNormalColor(new Color(0.6f, 0.6f, 0.6f, 1f)); 					
		medium.setMouseOverColor(new Color(1f, 1f, 1f, 1f));
		hard = new MouseOverArea(gc, new Image("res/dificulty/hard.png"),300, 300);
		hard.setNormalColor(new Color(0.6f, 0.6f, 0.6f, 1f)); 					
		hard.setMouseOverColor(new Color(1f, 1f, 1f, 1f)); 
		god = new MouseOverArea(gc, new Image("res/dificulty/god.png"), 300, 380);
		god.setNormalColor(new Color(0.6f, 0.6f, 0.6f, 1f)); 					
		god.setMouseOverColor(new Color(1f, 1f, 1f, 1f)); 
		back = new MouseOverArea(gc, new Image("res/option/retour.png"), 580, 510);
		back.setNormalColor(new Color(0.6f, 0.6f, 0.6f, 1f)); 					
		back.setMouseOverColor(new Color(1f, 1f, 1f, 1f)); 
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		bg.draw();
		easy.render(gc, g);
		medium.render(gc, g);
		hard.render(gc, g);
		god.render(gc, g);
		back.render(gc, g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
		int posX = Mouse.getX();    // attrape la position de la souris
		int posY = Math.abs((Mouse.getY())-600);
		
		if(pos(easy, posX, posY)){
			if (Mouse.isButtonDown(0)) {
				EASY=true;
				MEDIUM = false;
				HARD=false;
				GOD=false;
				sbg.enterState(2);
				sbg.enterState(2, new FadeOutTransition (),new FadeInTransition(Color.black));
			}
		}
		if(pos(medium, posX, posY)){
			if (Mouse.isButtonDown(0)) {
				MEDIUM = true;
				EASY=false;
				HARD=false;
				GOD=false;
				sbg.enterState(2);
				sbg.enterState(2, new FadeOutTransition (),new FadeInTransition(Color.black));
			}
		}
		if(pos(hard, posX, posY)){
			if (Mouse.isButtonDown(0)) {
				HARD=true;
				EASY=false;
				MEDIUM = false;
				GOD=false;
				sbg.enterState(2);
				sbg.enterState(2, new FadeOutTransition (),new FadeInTransition(Color.black));
			}
		}
		if(pos(god, posX, posY)){
			if (Mouse.isButtonDown(0)) {
				GOD = true;
				EASY=false;
				MEDIUM = false;
				HARD=false;
				sbg.enterState(2);
				sbg.enterState(2, new FadeOutTransition (),new FadeInTransition(Color.black));
			}
			
		}
		if ((posX >back.getX() && posX < (back.getX()+back.getWidth())) && (posY < (back.getY()+back.getHeight()) && posY > back.getY())) {
			if (Mouse.isButtonDown(0)) {
					sbg.enterState(6);
					sbg.enterState(6, new FadeOutTransition (),new FadeInTransition(Color.black));
			}
		}
	}

	@Override
	public int getID() {
		return 10;
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

}
