package ressource;

import java.io.FileNotFoundException;

import map.Map;
import npc.NPC;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/*
 * Monde Des Spartiates
 */
public class World8 implements WorldRessource
{
	private NPC npc1,npc2;

	public World8(){
		npc1 = new NPC();
		npc2 = new NPC();
	}
	
	@Override
	public void initNPC() throws SlickException {
		if(!Map.isInitNPC()){
			npc1.init("spartan", 701, 8, 9, 0, "down");
			npc2.init("leonidas", 702, 8, 9, 0, "down");
		}
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRessource(GameContainer gc, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		npc1.update(gc);
		npc2.update(gc);
		
	}

	@Override
	public void renderRessources(Graphics g, GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		try {
			npc1.render(8, 9, 1, 0, 320, 256, g);
			npc2.render(8, 9, 0, 0, 352, 256, g);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void renderParticle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renderDialog(Graphics g) throws SlickException, FileNotFoundException {
		// TODO Auto-generated method stub
		npc1.renderDialog();
		npc2.renderDialog();
	}
	
}
