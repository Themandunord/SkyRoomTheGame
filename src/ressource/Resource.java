package ressource;

/*
 * Permet le chargement des monstres, NPC .... 
 * Afin d'éviter la surchage de la classe Principale Game.java
 */

import game.Game;
import items.Arcade;

import java.io.FileNotFoundException;
import java.util.Iterator;

import npc.EventNPCDialog;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import player.Player;

import event.Cinematic;
import event.Event;

import map.Map;
import monster.AddMonster;
import monster.BlackAncient;
import monster.BossChinois;
import monster.BossFutur;
import monster.BossPrehistoire;
import monster.BossSpace;
import monster.BoyZombie;
import monster.Dino;
import monster.GirlZombie;
import monster.MJ;
import monster.RedAncient;
import monster.Robot;
import monster.Samurais;
import monster.SpaceInvader;
import monster.Stormtrooper;
import monster.WhiteAncient;

/**
 * Classe lancant l'ensemble des ressources
 * et leur initialisation
 * 
 * @author Rémy
 *
 */

public class Resource {

	/** Monde Depart + Monde Prehistoire */
	private World1 world1;
	/** Monde futur */
	private World2 world2;
	/** Monde UHA */
	private World3 world3;
	/** Monde Espace */
	private World4 world4;
	/** Monde Espace */
	private World6 world6;
	/** Skyroom */
	private World9 world9;
	/** Monde rennaissance */
	private World5 world5;
	/** Spartaland */
	private World8 world8;
	/** Chinois */
	private World7 world7;
	/** Ajout de monstre sur la map */
	private AddMonster monster,monster2;
	/** Evénement dialogue */
	private EventNPCDialog npcDial;
	/** Tableau des noms des images de la cinématique dark vador */
	private String[] name_dark = {"dark1","dark2","dark3","dark4","dark5","dark6","dark7","dark8"};
	/** Tableau des noms des images de la cinématique dark vador pour la fille*/
	private String[] name_dark_girl = {"DarkFille1","DarkFille2","DarkFille3","DarkFille4","DarkFille5","DarkFille6","DarkFille7","DarkFille8"};
	/** Cinematique de dark vador */
	private Cinematic cine_dark;
	/** Cinematique de la préhistoire */
	private Cinematic cine_prehistoire;
	/** Tableau des noms des images de la cinématique de la prehistoire */
	private String[] name_prehistoire = {"intro1","intro2","intro3"};
	/** Tableau des noms des images de la cinématique du mec nue */
	private String[] name_stromtrooper = {"poil1","poil2","poil3","poil4","poil5"};
	/** Cinematique du mec nu */
	private Cinematic cine_stromtrooper;
	/** Tableau des noms des images de la cinématique du futur */
	private String[] name_futur = {"futur1","futur2","futur3","futur4","futur5","futur6","futur7"};
	/** Tableau des noms des images de la cinématique du futur pour la fille */
	private String[] name_futur_girl = {"futur1","futur2","futur3","futur4","futur5","futur6","futur7fille"};
	/** Tableau des noms des images de la cinématique du monde bonus */
	private String[] name_bonus = {"easy1","easy2","easy3","easy4","easy5","easy6b","easy7b","easy8b","easy9b","easy10b","easy11","easy12","easy13","easy14"};
	/** Tableau des noms des images de la cinématique du monde bonus pour la fille */
	private String[] name_bonus_girl = {"easy1","easy2","easy3","easy4","easy5","easy6g","easy7g","easy8g","easy9g","easy10g","easy11","easy12","easy13","easy14"};
	/** Cinematique du monde bonus */
	private Cinematic cine_bonus;
	/** Cinematique du monde futur */
	private Cinematic cine_futur;
	/** Cinematique de spartaland */
	private Cinematic cine_spartaland;
	/** Tableau des noms des images de la cinématique spartaland */
	private String[] name_spartaland = {"sparta1","sparta2","sparta3","sparta4","sparta5","sparta6","sparta7","sparta8"};
	/** Tableau des noms des images de la cinématique spartaland fille */
	private String[] name_spartaland_girl = {"spartaG1","spartaG2","spartaG3","spartaG4","spartaG5","spartaG6","spartaG7","spartaG8"};
	/** Teleportation */
	private boolean isTeleportFutur,isTeleportSpace,isTeleportChinois,isTeleportSkyRoom;
	/** Compteur pour la cinématique de spartaland */
	private int cpt_spartaland;
	/** Arcade Snake */
	private Arcade snake,tetris,spaceInvader;
	private int cptGoSkyRoom,cptDialogSkyRoom,cptSkyRoom;
	private Image dialbox;
	
	
	/**
	 * Instanciation des différents objets
	 */
	public Resource(){
		world1 = new World1();
		world2 = new World2();
		world3 = new World3();
		world4 = new World4();
		world6 = new World6();
		world9 = new World9();
		world5 = new World5();
		world8 = new World8();
		world7 = new World7();
		monster = new AddMonster();
		monster2 = new AddMonster();
		npcDial = new EventNPCDialog();
		cine_dark = new Cinematic(name_dark);
		cine_prehistoire = new Cinematic(name_prehistoire);
		cine_stromtrooper = new Cinematic(name_stromtrooper);
		cine_bonus = new Cinematic(name_bonus);
		cine_futur = new Cinematic(name_futur);
		cine_spartaland = new Cinematic(name_spartaland);
		this.cpt_spartaland = 0;
		this.snake = new Arcade("snake", 17, 192, 160, 2, 3, "Jouer à Snake ? Appuie sur la touche G.");
		this.tetris = new Arcade("tetris", 15, 600, 450, 10, 5, "Jouer à Tetris ? Appuie sur la touche G.");
		this.spaceInvader = new Arcade("spaceInvader", 21, 600, 450, 7, 0, "Jouer à Space Invaders ? Appuie sur la touche G.");
		try {
			dialbox = new Image("res/all/dialboxEvent.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialisation des monstres
	 * 
	 * @throws SlickException
	 */
	public void initMonster() throws SlickException{
		if(!Event.girl_cine && Player.getSex().equals("girl")){
			cine_dark = new Cinematic(name_dark_girl);
			cine_bonus = new Cinematic(name_bonus_girl);
			cine_futur = new Cinematic(name_futur_girl);
			cine_spartaland = new Cinematic(name_spartaland_girl);
			Event.girl_cine = true;
		}
		try {
			if(!Map.isInit()){
		    /*
		     *Ressources Monster 
		     */
			monster.AddMonstere(1, Dino.class, 0, 1);
		    monster.AddMonstere(2, Dino.class, 0, 2);
		    monster.AddMonstere(2, Dino.class, 1, 3);
		    monster.AddMonstere(3, Dino.class, 1, 2);
		    monster.AddMonstere(4, Dino.class, 1, 1);
		    monster.AddMonstere(3, Dino.class, 2, 1);
		    if(Event.halo !=3)
		    	monster.AddMonstere(3-Event.halo, Robot.class, 2, 11);
		    monster.AddMonstere(2, Robot.class, 1, 10);
		    monster.AddMonstere(2, Robot.class, 1, 9);
		    monster.AddMonstere(2, Robot.class, 1, 8);
		    monster.AddMonstere(2, GirlZombie.class, 6, 10);
		    monster.AddMonstere(2, GirlZombie.class, 6, 9);
		    monster2.AddMonstere(2, BoyZombie.class, 6, 9);
		    monster.AddMonstere(2, BoyZombie.class, 6, 8);
		    monster.AddMonstere(1, MJ.class, 7, 7);
		    monster.AddMonstere(2, Stormtrooper.class, 8, 0);
		    monster.AddMonstere(2, Stormtrooper.class, 9, 0);
		    monster.AddMonstere(2, Stormtrooper.class, 9, 1);
		    monster.AddMonstere(2, SpaceInvader.class, 8, 2);
		    monster.AddMonstere(3, SpaceInvader.class, 9, 2);
		    monster.AddMonstere(4, SpaceInvader.class, 9, 3);
		    monster.AddMonstere(2, SpaceInvader.class, 8, 3);
		    monster.AddMonstere(3, SpaceInvader.class, 7, 3);
		    monster.AddMonstere(3, SpaceInvader.class, 7, 2);	
		    
		    monster.AddMonstere(3, RedAncient.class,10, 9);
		    monster.AddMonstere(1, BlackAncient.class,10, 8);
		    monster.AddMonstere(1, WhiteAncient.class,9, 8);
		    
		    monster.AddMonstere(4, Samurais.class, 11, 2);
		    monster.AddMonstere(6, Samurais.class, 11, 3);
		    monster.AddMonstere(5, Samurais.class, 10, 2);
		    monster.AddMonstere(5, Samurais.class, 12, 2);
		    
		    /* BOSS */
		    monster.AddMonstere(1, BossPrehistoire.class, 3, 2);
		    monster.AddMonstere(1, BossFutur.class, 2, 8);
		    monster.AddMonstere(1, BossSpace.class, 6, 3);
		    monster.AddMonstere(1, BossChinois.class, 11, 4);
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Initialisation des NPC
	 * 
	 * @throws SlickException
	 */
	public void initNPC() throws SlickException{
		world1.initNPC();
		world2.initNPC();
		world3.initNPC();
		world4.initNPC();
		world6.initNPC();
		world9.initNPC();
		world5.initNPC();
		world8.initNPC();
		world7.initNPC();
	}
	
	/**
	 * Initialisation des éléments 
	 */
	public void init(){
		world1.init();
		world2.init();
		world3.init();
		world4.init();
		world6.init();
		world5.init();
		world8.init();
		world7.init();
	}
	
	/**
	 * Boucle d'update des ressources
	 * 
	 * @param gc 
	 * @param delta
	 * @throws SlickException
	 */
	public void updateRessource(GameContainer gc, int delta) throws SlickException{
		
		if(Map.isMap(2, 8) && !isTeleportFutur){
			isTeleportFutur = true;
			Player.setX(160);
			Player.setY(320);
		}
		if(Map.isMap(10,1) && !isTeleportSpace){
			isTeleportSpace = true;
			Player.setX(256);
			Player.setY(256);
		}
		if(Map.isMap(11,4) && !isTeleportChinois){
			isTeleportChinois = true;
			Player.setX(384);
			Player.setY(384);
		}
		if(Event.goSkyRoom && !isTeleportSkyRoom){
			if(cptGoSkyRoom<200) cptGoSkyRoom++;
			else{
			isTeleportSkyRoom = true;
				Map.setIDx(10);
				Map.setIDy(11);
				Player.setX(384);
				Player.setY(416);
			}
		}
		
		world3.updateRessource(gc, delta);
		world2.updateRessource(gc, delta);
		world1.updateRessource(gc, delta);
		world4.updateRessource(gc, delta);
		world6.updateRessource(gc, delta);
		world9.updateRessource(gc, delta);
		world5.updateRessource(gc, delta);
		world8.updateRessource(gc, delta);
		world7.updateRessource(gc, delta);
		if(AddMonster.isMonster){
			monster.update(gc, delta);
			monster2.update(gc, delta);
		}
		
		
		/* Test de vie sur les monstres */
		if(Map.isMap(2, 8)){
			for(int i = 0; i<monster.getMonsterList().size(); i++){
				if(!monster.getMonsterList().get(i).getAlive()){
					Event.boss_futur_explosion = true;
				}
			}
		}
		
		/* BlockedIf Space Boss */
		if(Map.isMap(6, 3)){
			for(int i = 0; i<monster.getMonsterList().size(); i++){
				if(!monster.getMonsterList().get(i).getAlive()){
					Map.setSpaceBoss(true);
				}
			}
		}
		
		if(Map.isMap(2, 11)){
			for(int i = 0; i<monster.getMonsterList().size(); i++){
				if(!monster.getMonsterList().get(i).getAlive()){
					if(!monster.getMonsterList().get(i).getDeclareDead()){
						Event.halo++;
						monster.getMonsterList().get(i).setDeclareDead(true);
					}
						
				}
			}
		}
		if(Map.isMap(11, 4)){
			for(int i = 0; i<monster.getMonsterList().size(); i++){
				if(!monster.getMonsterList().get(i).getAlive()){
					Event.boss_chinois = true;					
				}
			}
		}
		
		if(Event.special_item_bonus>10){
			Event.specialItemBonus = true;
		}
	}
	
	/**
	 * Boucle de rendu des éléments 
	 * 
	 * @param g
	 * @throws SlickException
	 */
	public void renderRessources(Graphics g, GameContainer gc,StateBasedGame sbg) throws SlickException{
		world3.renderRessources(g,gc);
		world2.renderRessources(g,gc);
		world1.renderRessources(g,gc);
		world4.renderRessources(g,gc);
		world6.renderRessources(g,gc);
		world9.renderRessources(g, gc);
		world5.renderRessources(g, gc);
		world8.renderRessources(g, gc);
		world7.renderRessources(g, gc);
		if(AddMonster.isMonster){
			monster.render(g);
			monster2.render(g);
		}
		snake.renderUpdating(sbg,gc);
		tetris.renderUpdating(sbg,gc);
		spaceInvader.renderUpdating(sbg,gc);
		
			
	}
	
	/**
	 * Boucle de rendu des Particules de décors
	 * 
	 * @throws FileNotFoundException
	 */
	public void renderParticle() throws FileNotFoundException{
		
		world1.renderParticle();
		world2.renderParticle();
		world3.renderParticle();
		world4.renderParticle();
		world5.renderParticle();
	}
	
	/**
	 * Boucle de rendu des dialogue NPC
	 * @param g
	 * @throws FileNotFoundException
	 * @throws SlickException
	 */
	public void renderDialog(Graphics g, StateBasedGame sbg) throws FileNotFoundException, SlickException{
		npcDial.renderDialog(g);
		world1.renderDialog(g);
		world2.renderDialog(g);
		world3.renderDialog(g);
		world4.renderDialog(g);
		world6.renderDialog(g);
		world9.renderDialog(g);
		world5.renderDialog(g);
		world8.renderDialog(g);
		world7.renderDialog(g);
		cine_dark.render(10, 1, 8, 80);
		cine_prehistoire.render(0, 1, 3, 30);
		cine_stromtrooper.render(8, 0, 5, 70);
		cine_bonus.render(10, 10, 14, 40);
		cine_futur.render(2, 8, 7, 40);
		if(Event.spartaland_cine){
			if(cpt_spartaland>240){
				Event.cinematic = true;
				cine_spartaland.render(8, 9, 8, 70);
				Player.setX(500);
				if(!Event.cinematic){
					Map.setIDx(10);
					Map.setIDy(10);
					Event.spartaland_cine = false;
					Event.bonus_cine = true;
				}
			}else cpt_spartaland++;
		}
		if(Event.bonus_cine && !Event.cinematic){
			Player.setX(384);
			Player.setY(192);
			Event.bonus_cine = false;
		}
		
		if(AddMonster.isMonster){
			monster.renderUp(g);
			monster2.renderUp(g);
		}
		
		if(Event.haveSpecialBonus){
			if(cptDialogSkyRoom<200){
				cptDialogSkyRoom++;
				dialbox.draw(200,470);
				Game.uFont.drawString(210,477,"Tu as trouvé la clef de la SkyRoom !\n Rejoins-nous !");
				Event.goSkyRoom = true;
			}		
		}
		if(Event.remy && Event.stan && Event.loic && Event.alex && Event.amael){
			if(cptSkyRoom<160) cptSkyRoom++;
			else
				Event.credit = true;
		}
		
			
	}
	
	/**
	 * Boucle de rendu des particules liés au joueur
	 */
	public void renderDown(){
		world6.renderParticle();
	}
}
