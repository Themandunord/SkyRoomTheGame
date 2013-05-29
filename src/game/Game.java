package game;

import java.io.FileNotFoundException;

import map.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import event.Credit;
import event.Event;

import player.Dove;
import player.Player;
import ressource.Resource;
import ressource.World4;
import serialize.ReloadSave;
import serialize.Serializer;
import util.HelpScreen;
import util.Timer;

/** 
 * Classe qui gère l'ensemble du jeu ( GamePlay )
 *   
 * @author Rémy
 */

public class Game extends BasicGameState {
	/** On lance la map */
	private Map map ;
	/** On ajoute le héro */
	private Player hero;
	/** On lance le son du jeu */
	private GameSound music;
	/** On affiche l'HUD */
	private Hud hud;
	/** On lance le rechargement du jeu */
	private ReloadSave reload;
	/** On charge les ressources du jeu */
	private Resource ressource;
	/** Permet de savoir si on a déja lancer le jeu une fois */
	private static boolean newGame = true;
	/** On crée une lumière autour du hero dans le monde UHA */
	private World4 fog;
	/** On passe la police du jeu complet */
	public static UnicodeFont uFont;
	private Dove dove;
	private PauseState pause;
	private Serializer save;
	private Credit credit;
	private boolean isSave,isReload;
	private int cptSave,cptReload,cptInput;
	private Timer timer;
	private HelpScreen help1,help2,help3;
	
	private Color color;
	
	/**
	 * Constructeur relatif aux States
	 * 
	 * @param ID
	 */
	public Game(int ID){}

	@SuppressWarnings("unchecked")
	/**
	 * Initialisation des éléments
	 * Ce lance une seul fois au cours du jeu
	 * 
	 * @param gc GameContainer
	 * @param sbg State
	 * @throws SlickException
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		map = new Map();
		hero = new Player();
		music = new GameSound();
		reload = new ReloadSave();
		ressource = new Resource();
		fog = new World4();
		hud = new Hud(Event.maxHeart);
		dove = new Dove();
		pause = new PauseState();
		save = new Serializer();
		credit = new Credit();
		help1 = new HelpScreen("1");
		help2 = new HelpScreen("2");
		help3 = new HelpScreen("3");
		hero.init();
		map.init(gc);
		music.init();
		hud.init(gc, sbg);
		ressource.initNPC();
		ressource.init();
		pause.init(gc);
		
		uFont = new UnicodeFont("res/all/comic.ttf", 13 , false, false);
		uFont.addAsciiGlyphs();
		uFont.addGlyphs(400, 600);
		uFont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));  
		uFont.loadGlyphs();	
		
		dove.init();
		
		color = new Color(Color.black);
		
		cptInput = 0;
	}

	/**
	 * Boucle d'update du jeu
	 * Tout ce qui "bouge" dans le jeu ce fait ici
	 * 
	 * @param gc GameContainer
	 * @param sbg State
	 * @param delta Permet d'éviter le changement de vitesse selon les FPS
	 * @throws SlickException
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
	
		InputControl.gc = gc;
		if(cptInput<40){
			InputControl.update();
			cptInput++;
		}
		
		
		hero.update(gc, delta);
		GameSound.updateMusic();
		GameSound.updateVolume(gc);
		ressource.initMonster();
		ressource.updateRessource(gc, delta);
		ressource.initNPC();
		reload.reload(gc,sbg);
		pause.update(sbg, delta);
		
		/* Sauvegarde pour chaque monde */
		if(Event.save){
			if(Map.isMap(0, 1)){
				Event.save = false;
				save.serializer();
				isSave = true;
			}
			else if(Map.isMap(3, 3)){
				Event.save = false;
				save.serializer();
				isSave = true;
			}
			else if(Map.isMap(3, 8)){
				Event.save = false;
				save.serializer();
				isSave = true;
			}
			else if(Map.isMap(4,9)){
				Event.save = false;
				save.serializer();
				isSave = true;
			}
			else if(Map.isMap(6, 0)){
				Event.save = false;
				save.serializer();
				isSave = true;
			}
			else if(Map.isMap(8, 5)){
				Event.save = false;
				save.serializer();
				isSave = true;
			}
			else if(Map.isMap(11, 1)){
				Event.save = false;
				save.serializer();
				isSave = true;
			}
			else if(Map.isMap(8, 8)){
				Event.save = false;
				save.serializer();
				isSave = true;
			}
			else if(Map.isMap(10, 10)){
				Event.save = false;
				save.serializer();
				isSave = true;
			}
			else if(Map.isMap(10, 11)){
				Event.save = false;
				save.serializer();
				isSave = true;
			}
		}
		if(Event.fin){
			Event.initialize();
			Map.setIDx(0);
			Map.setIDy(0);
			Dove.doveIsOnPlayer = false;
			ressource.initMonster();
			ressource.initNPC();
			this.init(gc, sbg);
			Map.setInit(false);
			Map.setInitNPC(false);
			ressource.initMonster();
			ressource.initNPC();
			Event.fin = false;
			Event.girl_cine = false;
			timer = new Timer();
			System.gc();
		}
		if(Event.makeReload){
			isReload = true;
			Event.initialize();
			ressource.initMonster();
			ressource.initNPC();
			this.init(gc, sbg);
			Map.setInit(false);
			Map.setInitNPC(false);
			ressource.initMonster();
			ressource.initNPC();
			Event.fin = false;
			reload.reloadGame();
			Event.makeReload = false;
			Event.girl_cine = false;
			timer = new Timer(Event.time);
			System.gc();
		}
		
		if(Event.fadeTransition){
			color.a = 1;
			Event.fadeTransition = false;
		}
		if(color !=null && color.a !=0)
			color.a -= delta * (1.0f / 400);
		else{
			color.a = 0;
			Event.fadeTransition = false;
		}	
		
		
			
	}

	/**
	 * Boucle de rendu
	 * Tout ce qu'on affiche à l'écran se trouve ici
	 * 
	 * @param gc GameContainer
	 * @param sbg State
	 * @param g Sortie de l'écran
	 * @throws SlickException
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		ressource.renderDown();
		map.renderDown();
		ressource.renderRessources(g,gc,sbg);
		hero.render(g);
		dove.render();
		map.renderUp();
		try {
			ressource.renderParticle();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		fog.renderUp();
		hud.render(sbg,g);
		
		if(timer!=null){
			if(!PauseState.PAUSE){
				String time = timer.updateTime();
				Event.time = timer.getMinute();
				Game.uFont.drawString(10, 30, ""+timer.updateTime());
			}
		}
		
		
	    try {
			ressource.renderDialog(g,sbg);
			dove.renderDialogDove();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    
	    pause.render(gc,g);
	    
	    if(Menu.pseudo.equals("Laura") || Menu.pseudo.equals("Kevin")){
			if(!Menu.pseudo_save.equals(""))
				Menu.pseudo = Menu.pseudo_save;
			else if(Player.getSex().equals("boy"))
				Menu.pseudo = "Kevin";
			else if(Player.getSex().equals("girl"))
				Menu.pseudo = "Laura";
	    }
	    credit.update(6, 240, sbg);
	    
	    help1.render(0, 2);
	   	help2.render(2, 3);
	   	if(Event.WaterGun)
	   		help3.render(2, 10);
		
	    Color old = g.getColor();
		g.setColor(color);
			
		g.fillRect(0, 0, 800, 600);
		g.setColor(old);
		
		if(isSave){
			if(cptSave<80) cptSave++;
			else {
				cptSave = 0;
				isSave = false;
			}
			Game.uFont.drawString(10, 560, "Sauvegarde en cours...");
		}
		
		if(isReload){
			Event.cinematic = true;
			Player.setMoving(false);
			if(cptReload<80) cptReload++;
			else {
				Event.fadeTransition = true;
				cptReload = 0;
				isReload = false;
				Event.cinematic = false;
				Player.setMoving(true);
			}
			
			g.setColor(Color.black);
			g.fillRect(0, 0, 800, 600);
			g.setColor(Color.white);
			Game.uFont.drawString(270, 280, "Chargement de votre partie en cours...");
			
		}
		if(Event.makeReload){
			g.setColor(Color.black);
			g.fillRect(0, 0, 800, 600);
			g.setColor(Color.white);
			Game.uFont.drawString(270, 280, "Chargement de votre partie en cours...");
		}	
		
	}

	/**
	 * On récupère l'ID de la state
	 */
	public int getID() {
		return 2;
	}
	
	/**
	 * Retourne l'état du jeu
	 * 
	 * @return newGame Permet de savoir si je leu à deja été lancé
	 */
	public static boolean getNewGame(){
		return newGame;
	}
	/**
	 * Permet de modifier l'état du jeu
	 * 
	 * @param newGame idem
	 */
	public static void setNewGame( boolean newGame ){
		Game.newGame = newGame;
	}

}
