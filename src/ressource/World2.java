package ressource;

import items.Chest;

import java.io.FileNotFoundException;

import map.Map;

import npc.NPC;
import npc.NPCMover;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import particle.Particle;

/*
 * Monde Transition
 */

public class World2 implements WorldRessource{

	/** Particule */
	private Particle stargate,hole;	
	/** NPC */
	private NPC npc,npc2;
	/** Coffre */
	private Chest chest;
	/** Spartiate */
	private NPCMover npcMover;
	
	public World2(){
		stargate = new Particle();
		hole = new Particle();
		npc = new NPC();
		npc2 = new NPC();
		chest = new Chest();
		npcMover = new NPCMover(420, 450);
	}
	@Override
	public void initNPC() throws SlickException {
		if(!Map.isInitNPC()){
			npc.init("panel", 110, 3, 4, 0, "down");
			npc.init("panel", 111, 3, 5, 0, "down");
			npc.init("panel", 112, 3, 6, 0, "down");
			npc.init("panel", 107, 2, 6, 0, "down");
			npc.init("panel", 103, 4, 6, 0, "down");
			npc.init("panel", 109, 3, 7, 0, "down");
			npc.init("panel", 113, 1, 4, 0, "down");
			npc2.init("panel", 108, 2, 6, 0, "down");
			npc2.init("panel", 104, 4, 6, 0, "down");
			npcMover.init("spartan", 005, 3, 3, 0, "up");
			
			chest.init("first_chest","bikini_chest",2,6,5);
			chest.init("first_chest","heart_chest",4,6,3);
			chest.init("first_chest","heart_chest",1,5,7);
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		stargate.init("stargate");
		hole.init("wormhole");
	}

	@Override
	public void updateRessource(GameContainer gc, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		stargate.update(delta);
		hole.update(delta);
		npc.update(gc);
		
	}

	@Override
	public void renderRessources(Graphics g, GameContainer gc) throws SlickException {
			try {
				npc.render(3, 4, 0, 0, 352, 440, g);
				npc.render(3, 5, 0, 0, 352, 440, g);
				npc.render(3, 6, 0, 0, 352, 440, g);
				npc.render(2, 6, 0, 0, 352, 480, g);
				npc.render(4, 6, 0, 0, 576, 480, g);
				npc.render(3, 7, 0, 0, 384, 480, g);
				npc.render(1, 4, 0, 0, 576, 128, g);
				npc2.render(2, 6, 0, 0, 672, 224, g);
				npc2.render(4, 6, 0, 0, 128, 416, g);
				
				npcMover.renderSpartanTransition(3, 3, 1, 3, 420, 450, g);
				
				chest.render(2, 6, 224, 256, gc);
				chest.render(4, 6, 576, 128, gc);
				chest.render(1, 5, 672, 160, gc);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void renderParticle() {
				stargate.renderStargate(3, 3);
				stargate.renderStargate(3, 4);
				stargate.renderStargate(3, 5);
				stargate.renderStargate(3, 6);
				stargate.renderStargate(3, 7);
				stargate.renderStargate(4, 4);
				stargate.renderStargate(4, 5);
				stargate.renderStargate(4, 6);
				stargate.renderStargate(2, 4);
				stargate.renderStargate(2, 5);
				stargate.renderStargate(2, 6);
				stargate.renderStargate(1, 4);
				stargate.renderStargate(1, 5);
		
		
			hole.renderHole(6, 4);
			
		
	}
	@Override
	public void renderDialog(Graphics g) throws SlickException, FileNotFoundException {
		npc2.renderDialog();npc.renderDialog();
		chest.renderSpecialDialog();
	}

}
