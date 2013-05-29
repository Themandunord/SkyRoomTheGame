package ressource;

import items.Chest;

import java.io.FileNotFoundException;

import npc.NPC;
import npc.NPCFollower;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import event.Event;

import player.Player;

import map.Map;


/*
 * Monde UHA Infecté
 */
public class World4 implements WorldRessource{
	
	private Image fog,tram,letter;
	private int xTram=544,yTram=0;
	private NPC npc1,npc2,npc3,npc4,npc5,hallScreen,foodScreen;
	private int cpt;
	private NPCFollower enscmuGirl;
	private int[] enscmuX = {4,4,4,5,5,5,6,6,6,6,6,6,7,7,7,7};
	private int[] enscmuY = {8,9,10,8,9,10,10,9,8,7,6,5,9,8,6,5};
	private Chest chest;
	
	public World4(){
		try {
			fog = new Image("res/all/fog.png");
			tram = new Image("res/event/tram.png");
			letter = new Image("res/event/letter.png");
			npc1 = new NPC();
			npc2 = new NPC();
			npc3 = new NPC();
			npc4 = new NPC();
			npc5 = new NPC();
			hallScreen = new NPC();
			foodScreen = new NPC();
			enscmuGirl = new NPCFollower(576,416);
			chest = new Chest();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initNPC() throws SlickException {
		// TODO Auto-generated method stub
		if(!Map.isInitNPC()){
			npc1.init("spartan", 307, 4, 8, 0, "down");
			npc2.init("blonde", 306, 4, 8, 0, "left");
			npc1.init("prof_uha", 302, 7, 8, 0, "down");
			npc2.init("green", 303, 7, 8, 0, "down");
			npc3.init("joy", 304, 7, 8, 0, "left");
			npc4.init("brun", 305, 7, 8, 0, "left");
			npc1.init("WernerGirl", 310, 7, 9, 0, "left");
			npc2.init("WernerBoy", 313, 7, 9, 0, "left");
			npc1.init("enscmu", 314, 7, 6, 1, "down");	
			npc1.init("vide", 301, 6, 8, 0, "down");
			hallScreen.initScreen("hallScreen", 4, 10);
			foodScreen.initScreen("foodScreen", 6, 8);
			
			chest.init("brun_chest","torch_chest", 4, 10, 40);
			chest.init("brun_chest", "cape", 7, 9, 41);

			
			if(Event.enscmuReady){
				enscmuGirl.init("enscmu", 317, 4, 8, 0, "down");
				enscmuGirl.init("enscmu", 317, 4, 9, 0, "down");
				enscmuGirl.init("enscmu", 317, 4, 10, 0, "down");
				enscmuGirl.init("enscmu", 317, 5, 8, 0, "down");
				enscmuGirl.init("enscmu", 317, 5, 9, 0, "down");
				enscmuGirl.init("enscmu", 317, 5, 10, 0, "down");
				enscmuGirl.init("enscmu", 317, 6, 10, 0, "down");
				enscmuGirl.init("enscmu", 317, 6, 9, 0, "down");
				enscmuGirl.init("enscmu", 317, 6, 8, 0, "down");
				enscmuGirl.init("enscmu", 317, 6, 7, 0, "down");
				enscmuGirl.init("enscmu", 317, 6, 6, 0, "down");
				enscmuGirl.init("enscmu", 317, 6, 5, 0, "down");
				enscmuGirl.init("enscmu", 317, 7, 5, 0, "down");
				enscmuGirl.init("enscmu", 317, 7, 8, 0, "down");
				enscmuGirl.init("enscmu", 317, 7, 9, 0, "down");
				enscmuGirl.init("enscmu", 317, 7, 6, 0, "down");
			}
			if(Event.MJ){
				npc5.init("enscmu", 316, 7, 8, 0, "down");
				enscmuGirl.init("enscmu", 317, 7, 8, 0, "down");
			}			
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
		if(Map.isMap(7, 5)){
			if(yTram>-450)
			yTram-=0.05*delta;
			else yTram=650;
		}
		npc1.update(gc);
		npc2.update(gc);
		npc3.update(gc);
		npc4.update(gc);
		npc5.update(gc);
		if(Event.enscmuReady)
			enscmuGirl.follow(delta, enscmuX, enscmuY);
		
		if(Map.isMap(7,7)){
			Event.MJ = true;
		}
		
		
	}

	@Override
	public void renderRessources(Graphics g, GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		if(Map.isMap(7, 5)){
			tram.draw(xTram, yTram);
		}
		try {
			npc1.render(4, 8, 1, 0, 32, 64, g);
			npc2.render(4, 8, 1, 1, 736, 544, g);
			npc1.render(7, 8, 1, 0, 672, 64, g);
			npc2.render(7, 8, 1, 0, 576, 160, g);
			npc3.render(7, 8, 1, 1, 672, 352, g);
			npc4.render(7, 8, 1, 1, 704, 480, g);
			npc1.render(7, 9, 1, 1, 640, 192, g);
			npc2.render(7, 9, 1, 1, 640, 256, g);
			npc1.render(7, 6, 1, 0, 416, 128, g);
			npc1.render(6, 8, 0, 0, 480, 384, g);
			npc1.render(6, 8, 0, 0, 480, 384, g);
			
			chest.render(4, 10, 608, 64, gc);
			chest.render(7, 9, 704, 384, gc);
			
			if(Event.MJ)
				npc5.render(7, 8, 1, 0, 576, 416, g);
			if(Event.enscmuReady)
				enscmuGirl.render(g, enscmuX, enscmuY);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void renderParticle() {
		// TODO Auto-generated method stub
		
	}
	public void renderUp() throws SlickException{
		if(Map.isMap(5, 10))
			fog.draw(-1180 + Player.getX(), -890 + Player.getY());
		if(Map.isMap(6, 10))
			fog.draw(-1180 + Player.getX(), -890 + Player.getY());
		if(Map.isMap(5, 9))
			fog.draw(-1180 + Player.getX(), -890 + Player.getY());
		if(Map.isMap(5, 8))
			fog.draw(-1180 + Player.getX(), -890 + Player.getY());
		if(Map.isMap(6, 9))
			fog.draw(-1180 + Player.getX(), -890 + Player.getY());
		if(Map.isMap(6, 8))
			fog.draw(-1180 + Player.getX(), -890 + Player.getY());
		if(Map.isMap(6, 6))
			fog.draw(-1180 + Player.getX(), -890 + Player.getY());
		if(Map.isMap(6, 5))
			fog.draw(-1180 + Player.getX(), -890 + Player.getY());
		if(Map.isMap(6, 7))
			fog.draw(-1180 + Player.getX(), -890 + Player.getY());
		if(Map.isMap(7, 5))
			fog.draw(-1180 + Player.getX(), -890 + Player.getY());
	}

	@Override
	public void renderDialog(Graphics g) throws SlickException, FileNotFoundException {
		// TODO Auto-generated method stub
		npc1.renderDialog();
		npc2.renderDialog();
		npc3.renderDialog();
		npc4.renderDialog();
		npc5.renderDialog();
		hallScreen.renderScreen(4, 10, 512, 288);
		foodScreen.renderScreen(6, 8, 640, 160);
		chest.renderSpecialDialog();
		
		if(Event.renderLetter){
			if(cpt<300)
				cpt++;
			if(cpt>240) Event.renderLetter = false;
			letter.draw(0, 0);
		}
	}
}
