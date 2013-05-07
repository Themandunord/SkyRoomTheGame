package ressource;

import java.io.FileNotFoundException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface WorldRessource {

	public void initNPC() throws SlickException;
	public void init();
	public void updateRessource(GameContainer gc, int delta) throws SlickException;
	public void renderRessources(Graphics g, GameContainer gc) throws SlickException;
	public void renderParticle();
	public void renderDialog(Graphics g) throws SlickException,FileNotFoundException;
}
