package ressource;

import event.Event;
import game.Hud;

import java.io.FileNotFoundException;

import map.Map;
import npc.NPC;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import player.Player;

/*
 * Monde Bonus + Skyroom
 */
public class World9 implements WorldRessource
{
	private NPC npc1,npc2,npc3,npc4,npc5,npc6,npc7,npc8,npc9,npc10,npc11,npc12,npc13;
	private Rectangle lifeFontaine;
	private Animation fontaine_anim;

	public World9(){
		npc1 = new NPC();
		npc2 = new NPC();
		npc3 = new NPC();
		npc4 = new NPC();
		npc5 = new NPC();
		npc6 = new NPC();
		npc7 = new NPC();
		npc8 = new NPC();
		npc9 = new NPC();
		npc10 = new NPC();
		npc11 = new NPC();
		npc12 = new NPC();
		npc13 = new NPC();
		lifeFontaine = new Rectangle(340, 210, 95, 95);
		try {
			fontaine_anim = new Animation(new SpriteSheet("res/all/fontaine.png",64,64),0,0,4,5,true,100,true);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initNPC() throws SlickException {
		if(!Map.isInitNPC()){
			npc1.init("vide", 802, 10, 10, 1, "down");
			npc2.init("vide", 804, 10, 10, 0, "down");
			npc3.init("vide", 805, 10, 10, 0, "down");
			npc4.init("vide", 806, 10, 10, 0, "down");
			npc5.init("vide", 807, 10, 10, 0, "down");
			npc6.init("vide", 808, 10, 10, 0, "down");
			npc7.init("vide", 809, 10, 10, 0, "down");
			npc8.init("vide", 810, 10, 10, 0, "down");
			npc9.init("vide", 811, 10, 10, 0, "down");
			npc10.init("vide", 812, 10, 10, 0, "down");
			npc11.init("vide", 813, 10, 10, 0, "down");
			npc12.init("vide", 814, 10, 10, 0, "down");
			npc13.init("vide", 815, 10, 10, 0, "down");			
			
			npc1.init("remy", 1000, 10, 11, 0, "down");
			npc2.init("alex", 1001, 10, 11, 0, "down");
			npc3.init("loic", 1002, 10, 11, 0, "down");
			npc4.init("amael", 1003, 10, 11, 0, "down");
			npc5.init("stan", 1004, 10, 11, 0, "down");
		}
	}

	@Override
	public void init() {
	
	}

	@Override
	public void updateRessource(GameContainer gc, int delta)
			throws SlickException {
		npc1.update(gc);
		npc2.update(gc);
		npc3.update(gc);
		npc4.update(gc);
		npc5.update(gc);
		npc6.update(gc);
		npc7.update(gc);
		npc8.update(gc);
		npc9.update(gc);
		npc10.update(gc);
		npc11.update(gc);
		npc12.update(gc);
		npc13.update(gc);
		
		
		if(Map.isMap(9, 8) && lifeFontaine.intersects(Player.getRect()))
			Hud.setNbrHeart(Event.maxHeart);
	}

	@Override
	public void renderRessources(Graphics g, GameContainer gc) throws SlickException {
		try {
			npc1.render(10, 10, 0, 0, 384, 160, g);
			npc2.render(10, 10, 0, 0, 112, 160, g);
			npc3.render(10, 10, 0, 0, 720, 160, g);
			npc4.render(10, 10, 0, 0, 720, 256, g);
			npc5.render(10, 10, 0, 0, 720, 352, g);
			npc6.render(10, 10, 0, 0, 624, 256, g);
			npc7.render(10, 10, 0, 0, 624, 352, g);
			npc8.render(10, 10, 0, 0, 208, 160, g);
			npc9.render(10, 10, 0, 0, 112, 256, g);
			npc10.render(10, 10, 0, 0, 112, 352, g);
			npc11.render(10, 10, 0, 0, 624, 160, g);
			npc12.render(10, 10, 0, 0, 208, 256, g);
			npc13.render(10, 10, 0, 0, 208, 352, g);
			
			npc1.render(10, 11, 0, 0, 384, 192, g);
			npc2.render(10, 11, 0, 0, 320, 192, g);
			npc3.render(10, 11, 0, 0, 448, 192, g);
			npc4.render(10, 11, 0, 0, 512, 192, g);
			npc5.render(10, 11, 0, 0, 256, 192, g);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void renderParticle() {
		
		
	}

	@Override
	public void renderDialog(Graphics g) throws SlickException, FileNotFoundException {
		npc1.renderDialog();
		npc2.renderDialog();
		npc3.renderDialog();
		npc4.renderDialog();
		npc5.renderDialog();
		npc6.renderDialog();
		npc7.renderDialog();
		npc8.renderDialog();
		npc9.renderDialog();
		npc10.renderDialog();
		npc11.renderDialog();
		npc12.renderDialog();
		npc13.renderDialog();
		if(Map.isMap(9, 8)){
			fontaine_anim.start();
			fontaine_anim.draw(350,220);
		}
	}

}
