package tetris.au.com.sensis.slicktest;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Tetris extends BasicGameState {

    private Model model;
    private Renderer renderer;

    public Tetris(int ID) {
        
    }
    
    public int getID() {
		return 15;
	}
	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		model = new Model();
        renderer = new Renderer();
		
	}
	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		renderer.render(model, container, g);
		
	}
	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		model.update(delta, container.getInput(),sbg);
		if(Model.restart){
			this.init(container, sbg);
			Model.restart = false;
		}
	}

}
