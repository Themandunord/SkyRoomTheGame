package ressource;

import java.io.FileNotFoundException;

import map.Map;
import npc.NPC;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/*
 * Monde Médiéval Chinois
 */
public class World7 implements WorldRessource
{
	private NPC npc;

	public World7(){
		npc = new NPC();
	}
	@Override
	public void initNPC() throws SlickException {
		if(!Map.isInitNPC()){
			npc.init("zorro", 601, 11, 1, 0, "right");
		}
	}

	@Override
	public void init() {
	}

	@Override
	public void updateRessource(GameContainer gc, int delta)
			throws SlickException {
		npc.update(gc);
	}

	@Override
	public void renderRessources(Graphics g, GameContainer gc) throws SlickException {
		try {
			npc.render(11, 1, 1, 2, 320, 160, g);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void renderParticle() {		
	}

	@Override
	public void renderDialog(Graphics g) throws SlickException, FileNotFoundException {
		npc.renderDialog();
	}

}
