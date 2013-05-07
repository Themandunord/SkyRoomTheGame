package ressource;

import items.Chest;
import items.Item;

import java.io.FileNotFoundException;

import npc.NPC;
import npc.NPCMover;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import player.Player;

import event.Event;
import event.Pipe;
import event.RotationImage;
import event.Switch;
import event.Teleportation;

import map.Map;

/*
 * Monde Futur
 */
public class World3 implements WorldRessource{
	
	/** Tuyaux */
	private Pipe pipe1,pipe2,pipe3,pipe4,pipe5,pipe6,pipe7,pipe8,pipe9,pipe10,pipe11,pipe12;
	/** Interrupteur */
	private Switch switch1,switch2,switch3,switch4,switch5;
	/** Téleportation */
	private Teleportation teleportation;
	/** Pistolet a eau */
	private Item waterGun;
	/** Rotation de l'image du portail */
	private RotationImage gate1,gate2;
	/** NPC */
	private NPC npc1,npc2,npc3;
	/** Image de la porte et du verre d'eau */
	private Image door,futurWater;
	/** SpriteSheet de l'explosion */
	private SpriteSheet sprite_explose_door;
	/** Animation de l'explosion */
	private Animation anim_explose_door, anim_explose_door_bis,anim_explose_door_bis_bis;
	/** coffre */
	private Chest chest;
	/** Spartiate */
	private NPCMover npcMover;
	/** Compteur */
	private int cpt;
	/** Futur Gate */
	private Image futur_gate_close, futur_gate_open;
	
	public World3(){
		teleportation = new Teleportation();
		npc1 = new NPC();
		npc2 = new NPC();
		npc3 = new NPC();
		chest = new Chest();
		npcMover = new NPCMover(500, 620);
		try {
			pipe1 = new Pipe("horizontal",128,64,0);
			pipe2 = new Pipe("horizontal",160,64,0);
			pipe3 = new Pipe("vertical",192,32,1);
			pipe4 = new Pipe("vertical",480,160,2);
			pipe5 = new Pipe("horizontal",576,128,2);
			pipe6 = new Pipe("horizontal",512,64,3);
			pipe7 = new Pipe("horizontal",542,64,3);
			pipe8 = new Pipe("horizontal",704,192,3);
			pipe9 = new Pipe("horizontal",736,192,3);
			pipe10 = new Pipe("vertical",640,224,4);
			pipe11 = new Pipe("vertical",640,256,4);
			pipe12 = new Pipe("horizontal",32,320,5);
			switch1 = new Switch(32, 192,1);
			switch2 = new Switch(352, 160,2);
			switch3 = new Switch(512, 96,3);
			switch4 = new Switch(672, 256,4);
			switch5 = new Switch(512, 480,5);
			waterGun = new Item("waterGun");
			gate1 = new RotationImage("gateG");
			gate2 = new RotationImage("gateD");
			
			door = new Image("res/event/porte.png");
			futurWater = new Image("res/items/item-verre.png");
			sprite_explose_door = new SpriteSheet("res/event/explosion.png",64,64);
			anim_explose_door = new Animation(sprite_explose_door,0,0,3,3,true,100,true);
			anim_explose_door_bis = anim_explose_door;
			anim_explose_door_bis_bis = anim_explose_door;
			futur_gate_open = new Image("res/event/futur_gate_open.png");
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void initNPC() throws SlickException {
		if(!Map.isInitNPC()){
			npc1.init("future1", 201, 3, 8, 0, "down");
			npc2.init("blueMan", 211, 3, 8, 0, "down");
			npc1.init("future2", 202, 3, 9, 0, "left");
			npc2.init("doc", 212, 3, 9, 0, "down");
			npc3.init("marty", 213, 3, 9, 0, "up");
			npc1.init("future3", 203, 3, 10, 0, "up");
			npc2.init("bandanaBoy", 214, 3, 10, 0, "down");
			npc1.init("future4", 204, 2, 10, 0, "right");
			npc2.init("chauveFutur", 215, 2, 10, 0, "down");
			npc1.init("future5", 205, 2, 9, 0, "right");
			npc2.init("future1", 206, 2, 9, 0, "left");
			npc3.init("tron", 216, 2, 9, 0, "down");
			npc1.init("halo", 208, 2, 11, 0, "down");
			npc1.init("vide", 207, 1, 9, 0, "down");
			chest.init("blue_chest", "heart_chest", 2, 9, 20);
			npcMover.init("spartan", 0, 2, 11, 0, "up");
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
		
		teleportation.teleport(3, 2, 3, 3, 420, 550);
		teleportation.teleport(2, 8, 4, 9, 384, 544);
		teleportation.teleport(4, 10, 5, 10, 390, 300);
		teleportation.teleport(5, 10, 4, 10, 736, 192);
		teleportation.teleport(5, 8, 4, 8, 736, 320);
		teleportation.teleport(4, 8, 5, 8, 160, 288);
		teleportation.teleport(6, 7, 7, 6, 64, 288);
		teleportation.teleport(6, 8, 7, 8, 50, 300);
		teleportation.teleport(7, 8, 6, 8, 650, 300);
		teleportation.teleport(7, 5, 8, 5, 32, 288);
		teleportation.teleport(8, 5, 7, 5, 650, 300);
		teleportation.teleport(12, 7, 6, 0, 32, 320);
		teleportation.teleport(6, 4, 11, 1, 352, 320);
		teleportation.teleport(11, 4, 8, 8, 400, 300);
		teleportation.teleport(11, 6, 11, 7, 160, 32);
		teleportation.teleport(11, 7, 11, 6, 128, 512);
		teleportation.teleport(8, 9, 10, 10, 384, 192);
		teleportation.teleport(10, 11, 10, 10, 650, 300);
		//teleportation.teleport(10, 10,10, 11, 650, 300);
		
		npc1.update(gc);
		npc2.update(gc);
		npc3.update(gc);
	}

	@Override
	public void renderRessources(Graphics g, GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		pipe1.render(); pipe2.render(); pipe3.render();
		pipe4.render(); pipe5.render(); pipe6.render();
		pipe7.render(); pipe8.render(); pipe9.render();
		pipe10.render(); pipe11.render(); pipe12.render();
		switch1.render(3,10); switch2.render(3,10);
		switch3.render(3,10); switch4.render(3,10);
		switch5.render(2,9);
		
		if(!Event.futur_gate){
			gate1.render(Event.futur_gate, 2, 10, 160, 128, -90, 160-32,128-32);
			gate2.render(Event.futur_gate, 2, 10, 256, 128, -90,256+32,128+32);
		}
		else{
			if(Map.isMap(2, 10))
				futur_gate_open.draw(160, 128);
		}
			
		
		
		if(Map.isMap(2, 8) && !Event.boss_futur_explosion)
			door.draw(145, 64);
		
		if(Map.isMap(2, 8) && Event.boss_futur_explosion){
			anim_explose_door.start();
			anim_explose_door_bis.start();
			anim_explose_door_bis_bis.start();
			anim_explose_door.setLooping(false);
			anim_explose_door_bis.setLooping(false);
			anim_explose_door_bis_bis.setLooping(false);
			
			if(!anim_explose_door.isStopped()){
				anim_explose_door_bis.draw(200, 85);
				anim_explose_door.draw(150, 70);
				anim_explose_door_bis_bis.draw(175, 60);
			}			
		}

		try {
			npc1.render(3, 8, 1, 0, 224, 288, g);
			npc2.render(3, 8, 1, 0, 608, 192, g);
			npc1.render(3, 9, 1, 1, 640, 288, g);
			npc2.render(3, 9, 0, 0, 192, 288, g);
			npc3.render(3, 9, 0, 0, 192, 384, g);
			npc1.render(3, 10, 1, 3, 224, 320, g);
			npc2.render(3, 10, 1, 0, 608, 384, g);
			npc1.render(2, 10, 1, 2, 224, 416, g);
			npc2.render(2, 10, 1, 0, 448, 160, g);
			npc1.render(2, 9, 1, 2, 388, 480, g);
			npc2.render(2, 9, 1, 1, 480, 480, g);
			npc3.render(2, 9, 0, 0, 512, 224, g);
			npc1.render(2, 11, 1, 1, 64, 480, g);
			npc1.render(1, 9, 0, 0, 704, 160, g);
			
			chest.render(2, 9, 64, 64, gc);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
				
		if(Map.isMap(3, 10)){
			waterGun.render(750, 220);
		}
		
		if(Event.water){
			if(cpt<80){
				cpt++;
				futurWater.draw(Player.getX(), Player.getY()-32);	
			}
		}
	}

	@Override
	public void renderParticle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renderDialog(Graphics g) throws SlickException, FileNotFoundException {
		npcMover.renderSpartanFutur(2, 11, 0, 1, 500, 620, g);
		npc1.renderDialog();
		npc2.renderDialog();
		npc3.renderDialog();
		chest.renderSpecialDialog();
		
		
	}
}
