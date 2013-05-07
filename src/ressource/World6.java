package ressource;

import java.io.FileNotFoundException;
import java.io.IOException;

import map.Map;
import npc.NPC;
import npc.NPCFollower;
import npc.NPCMover;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import event.Event;
import event.SpaceShip;

import particle.Particle;
import player.Blaster;
import items.Item;

/*
 * Monde Espace
 */
public class World6 implements WorldRessource
{
	private int[] IDX={6,6,6,6,7,7,7,8,8,8,9,9,9,7,8};
	private int[] IDY={4,3,2,1,3,2,1,4,3,2,3,2,1,0,1};
	private Particle star;
	private SpaceShip spaceShip;
	private Item costume,speed;
	private NPCFollower npcFollower;
	private int[] spartaX = {10,9,9,8,8};
	private int[] spartaY = {1,1,0,0,1};
	private NPC npc1,npc2;
	
	
	public World6(){
		star = new Particle();
		npc1 = new NPC();
		npc2 = new NPC();
		try {
			spaceShip = new SpaceShip();
			costume = new Item("costume");
			npcFollower = new NPCFollower(100,200);
			speed = new Item("speed");
			
		} catch (SlickException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initNPC() throws SlickException {
		// TODO Auto-generated method stub
		if(!Map.isInitNPC()){
			npcFollower.init("spartan", 0, 10, 1, 0, "right");
			npcFollower.init("spartan", 0, 9, 1, 0, "right");
			npcFollower.init("spartan", 0, 9, 0, 0, "right");
			npcFollower.init("spartan", 0, 8, 0, 0, "right");
			npcFollower.init("spartan", 0, 8, 1, 0, "right");
			npc1.init("Gpanel", 501, 6, 0, 0, "down");
			npc1.init("Gpanel", 502, 7, 0, 0, "down");
			npc1.init("Gpanel", 503, 8, 0, 0, "down");
			npc2.init("Gpanel", 503, 8, 0, 0, "down");
			npc1.init("Gpanel", 504, 9, 0, 0, "down");
			npc2.init("Gpanel", 504, 9, 0, 0, "down");
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		star.init("star2");
	}

	@Override
	public void updateRessource(GameContainer gc, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		spaceShip.update(delta);
		star.update(delta);
		if(Event.spartanSpace && !Event.spaceShip)
			npcFollower.follow(delta,spartaX,spartaY);
		npc1.update(gc);
		npc2.update(gc);
	}

	@Override
	public void renderRessources(Graphics g, GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		spaceShip.render();
		
		try {
			npc1.render(6, 0, 0, 0, 448, 64, g);
			npc1.render(7, 0, 0, 0, 640, 64, g);
			npc1.render(8, 0, 0, 0, 192, 64, g);
			npc2.render(8, 0, 0, 0, 576, 64, g);
			npc1.render(9, 0, 0, 0, 342, 64, g);
			npc2.render(9, 0, 0, 0, 512, 64, g);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if(Map.isMap(6, 0) && !Event.costume)
			costume.render(576, 64);
		else if(Map.isMap(8, 0)){
			Event.costume = false;
			Event.costume_partiel = true;
		}
		else if(Map.isMap(11, 1)){
			Event.costume_partiel = false;
			Event.costume = false;
		}
		else if(Map.isMap(10, 1)){
			Event.spartanSpace = true;
			Event.spaceKey = true;
		}
		else if(Map.isMap(8, 2)){
			Blaster.setAmmo(99);
		}
		
		if(Event.spartanSpace && !Event.spaceShip)
			npcFollower.render(g, spartaX, spartaY);
		if(Map.isMap(8, 2))
			Event.blasterStorm = false;
		
		if(Map.isMap(8, 4)){
			speed.render(400, 300);
		}
		
		
	}

	@Override
	public void renderParticle() {
			star.renderStar(IDX, IDY);
	}

	@Override
	public void renderDialog(Graphics g) throws SlickException, FileNotFoundException {
		costume.renderItemDialog();
		npc1.renderDialog();
		npc2.renderDialog();
	}

}
