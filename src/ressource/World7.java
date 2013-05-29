package ressource;

import items.Chest;

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
	private Chest chest1,chest2,chest3,chest4;

	public World7(){
		npc = new NPC();
		chest1 = new Chest();
		chest2 = new Chest();
		chest3 = new Chest();
		chest4 = new Chest();
	}
	@Override
	public void initNPC() throws SlickException {
		if(!Map.isInitNPC()){
			npc.init("zorro", 601, 11, 1, 0, "right");
			chest1.init("brun_chest", "heart_chest", 10, 2, 1);
			chest2.init("brun_chest", "rubis_chest", 10, 2, 2);
			chest3.init("brun_chest", "saphir_chest", 10, 2, 3);
			chest4.init("brun_chest", "saphir_chest", 10, 2, 4);
			chest1.init("brun_chest", "heart_chest", 12, 2, 5);
			chest1.init("brun_chest", "rubis_chest", 11, 3, 6);
			chest2.init("brun_chest", "rubis_chest", 11, 3, 7);
			
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
			chest1.render(10, 2, 192, 96, gc);
			chest2.render(10, 2, 224, 320, gc);
			chest3.render(10, 2, 416, 320, gc);
			chest4.render(10, 2, 384, 96, gc);
			chest1.render(12, 2, 640, 96, gc);
			chest1.render(11, 3, 320, 224, gc);
			chest2.render(11, 3, 480, 224, gc);
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
		chest1.renderSpecialDialog();
		chest2.renderSpecialDialog();
		chest3.renderSpecialDialog();
		chest4.renderSpecialDialog();
	}

}
