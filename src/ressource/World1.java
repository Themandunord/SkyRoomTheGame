package ressource;

import game.Game;
import items.Chest;

import java.io.FileNotFoundException;
import java.util.List;

import map.Map;
import npc.NPC;
import npc.NPCFollower;
import npc.NPCMover;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import player.Player;

import event.Cinematic;

/**
 * Classe d'initialisation des ressources du 
 * Monde Départ et Monde Préhistoire
 * 
 * @author Rémy
 *
 */

public class World1 implements WorldRessource{
	
	/** NPC */
	private NPC npc;
	/** NPCFollower */
	private NPCFollower npcFollow;
	/** NPCMover */
	private NPCMover npcMover;
	/** Tableau pour l'affichage du NPCFollower */
	private int[] IDx = {0,0,0,0,1,1,1,2,2,2,3};
	/** Tableau pour l'affichage du NPCFollower */
	private int[] IDy = {0,1,2,3,3,2,1,3,2,1,2};
	/** Coffre */
	private Chest chest;
	/** Image de la dialbox de la colombe */
	private Image dialbox,dove_image;
	
	public World1(){
		npc = new NPC();
		npcMover = new NPCMover(230,250);
		chest = new Chest();
	}

	@Override
	public void initNPC() throws SlickException {
		// TODO Auto-generated method stub
		if(!Map.isInitNPC()){
			/*
			 * Ressources NPC init
			 */
			npc.init("prof", 002, 0, 0, 0,"down");
			npc.init("yoshi", 004, 0, 3, 0,"right");
			npcMover.init("spartan", 005, 2, 2, 0, "right");
			/*
			 * Chest
			 */
			chest.init("carton_chest","rubis_chest",1,3,1);		

		}
	}

	@Override
	public void init() {
		try {
			dialbox = new Image("res/all/dialboxEvent.png");
			dove_image = new Image("res/hero/dove.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateRessource(GameContainer gc, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		/*
		 * Ressources Téléportation
		 */
		//teleportation.teleport(0, 0, 0, 2, 100, 100);
		npc.update(gc);
		if(Map.isMap(2, 2))
				npcMover.update(gc);
	}

	@Override
	public void renderRessources(Graphics g, GameContainer gc) throws SlickException {
		try {
			/*
			 * Ressources NPC render
			 */
			npc.render(0, 0, 1, 0, 192, 128, g);
			npc.render(0, 3, 0, 0, 160, 325, g);
			if(npcMover!=null)
				npcMover.renderSpartanPrehistoire(2, 2, 1, 2, 230, 250, g,gc);
			
			/*
			 * Ressources Chest render
			 */
			chest.render(2, 3, 350, 400, gc);
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
		chest.renderSpecialDialog();
		renderDoveDialog(2, 2, "Tu devrais aller lui parler.");
		renderDoveDialog(4, 10, "Tu devrais ouvrir le coffre");
		renderDoveDialog(6, 9, "Tu devrais aller à la cantine pour commencer.");
		renderDoveDialog(5, 8, "Tu devrais aller à la cantine pour commencer.");
		renderDoveDialog(6, 7, "Tu ne peux pas rentrer ici pour le moment.\nRetournes à la cantine.");
		renderDoveDialog(7, 5, "Tu n'as pas sauvé tout les èlèves !\nRetournes-y !");
		renderDoveDialog(8, 0, "Il faut trouver la clef du vaisseau pour t'y rendre !");
		renderDoveDialog(6, 0, "Tu devrais dabord prendre le costume là-bas !");
		
		
	}
	
	/**
	 * Méthode créant un dialoque de la colombe lorsque les cases sont blockedif
	 * 
	 * @param IDx
	 * @param IDy
	 * @param text texte du dialogue
	 */
	public void renderDoveDialog(int IDx, int IDy, String text){
		if(Map.isMap(IDx, IDy)){
			if(Map.isBlockedIf(Player.getX()+32, Player.getY()+16) 
					|| Map.isBlockedIf(Player.getX()+16, Player.getY()-5)){
				dove_image.draw(200, 380);
				dialbox.draw(200, 470);
				Game.uFont.drawString(210,477,text);
			}
		}
	}

}
