package ressource;

import items.Item;

import java.io.FileNotFoundException;

import map.Map;
import npc.NPC;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import player.Dove;

import event.Event;

/*
 * Monde Renaissance
 */

public class World5 implements WorldRessource
{

	private NPC npc;
	private Item champi1,champi2,champi3;
	private Image souche1,souche2;
	private Animation purification,fontaine;
	private int cptWaterRennaissance,cptPurification;
	
	public World5(){
		npc = new NPC();
		try {
			champi1 = new Item("champi");
			champi2 = new Item("champi");
			champi3 = new Item("champi");
			souche1 = new Image("res/event/buche.png");
			souche2 = souche1;
			purification = new Animation(new SpriteSheet("res/event/purification.png",64,64),0,0,4,4,true,100,true);
			fontaine = new Animation(new SpriteSheet("res/all/fontaine.png",64,64),0,0,4,5,true,100,true);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initNPC() throws SlickException {
		if(!Map.isInitNPC()){
			npc.init("allonge", 401, 10, 6, 0, "down");
			npc.init("amoureuxGirl", 405, 8, 7, 0, "down");
			npc.init("amoureuxBoy", 406, 10, 7, 0, "down");
			npc.init("spartan", 408, 10, 5, 0, "left");
		}
		
	}

	@Override
	public void init() {
			
	}

	@Override
	public void updateRessource(GameContainer gc, int delta)
			throws SlickException {
		npc.update(gc);
		champi1.itemUpdate();
		champi2.itemUpdate();
		champi3.itemUpdate();
	}

	@Override
	public void renderRessources(Graphics g, GameContainer gc) throws SlickException {
		try {
			if(!Event.champiQuete)
				npc.render(10, 6, 0, 0, 704, 128, g);
			else npc.render(10, 6, 1, 0, 704, 128, g);
			npc.render(8, 7, 1, 0, 192, 384, g);
			npc.render(10, 7, 1, 0, 224, 64,g);
			if(!Event.finish_loveQuete)
				npc.render(10, 5, 1, 1, 736, 288, g);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(Map.isMap(10, 7) && !Event.loveQuete){
			souche1.draw(384, 352);
			souche2.draw(416, 352);
		}
		
		if(Map.isMap(9, 7) && Event.champiQuete)
			champi1.render(96, 96);
		else if(Map.isMap(10, 7) && Event.champiQuete)
			champi2.render(160, 480);
		else if(Map.isMap(10, 6) && Event.champiQuete)
			champi3.render(96, 64);
		
		if(champi1.getTake()) Event.champi1 = true;
		if(champi2.getTake()) Event.champi2 = true;
		if(champi3.getTake()) Event.champi3 = true;
		
		if(Map.isMap(10, 6)){
			purification.start();
			purification.draw(432,128);
		}
		if(Map.isMap(10, 7)){
			fontaine.start();
			fontaine.draw(304, 96);
		}
	}

	@Override
	public void renderParticle() {	
	}

	@Override
	public void renderDialog(Graphics g) throws SlickException, FileNotFoundException {
		npc.renderDialog();
		if(Event.waterRennaissance){
			if(cptWaterRennaissance<200){
				Dove.renderDoveDialog("Tu as récupéré de l'eau du lac !\n" +
						"Il faudrait aller la purifié sur la pierre dorée !");
				cptWaterRennaissance++;
			}		
		}
		if(Event.purification){
			if(cptPurification<200){
				Dove.renderDoveDialog("Tu as purifié l'eau !\n" +
						"Tu peux allé donner ça à l'homme à terre.");
				cptPurification++;
			}
				
		}
			
	}

}
