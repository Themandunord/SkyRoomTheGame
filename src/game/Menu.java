package game;

import java.awt.SplashScreen;
import java.io.IOException;

import map.Map;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import particle.Particle;
import player.Player;

import event.Event;

import serialize.ReloadGame;
import serialize.ReloadSave;

/**
 * Classe permettant de gérer le menu
 * 
 * @author Rémy
 *
 */

public class Menu extends BasicGameState {

	/** Image des différents éléments affichés */
	private Image background,bg,loadBar,backgroundLoad,lumies,sky1,sky2,sky3,sky4,textfield;
	/** Bouton du menu */
	private MouseOverArea play,exit,option,load,pseudoImage;
	/** Barre de Chargement */
	private DeferredResource nextResource;
	/** True si c'est lancé */
	private static boolean started ;
	/** True si le chargement est prêt */
	private boolean start;
	/** Champs de saisie de texte pour le pseudo */
	private TextField field;
	/** Pseudo par défaut */
	public static String pseudo = "Kevin",pseudo_save="";
	/** Musique du menu */
	private static Music music;
	/** Son lors des sélections */
	private Sound son;
	/** Coordonnée du l'aureole */
	private float yLumies = 0f;
	/** Coordonnées des différents nuages */
	private int x1=-150,x2=-120,x3=-130,x4=-140,y1=150,y2=300,y3=500,y4=400;
	/** True si on a deja vu le générique */
	private boolean isGeneric;
	/** Compteur pour éviter de voir le le menu avant le générique */
	private int cpt;
	/** Particle du fonds */
	private Particle particle;
	/** Permet que le son soit bien chargé */
	public static Sound ouch,ouch_girl,laser,robot,up,hurt,explosion,coin,water,hit;
	
	
	/**
	 * Constructeurs relatifs au State
	 * 
	 * @param ID Numéro relatif au state
	 */
	public Menu(int ID) {}

	/**
	 * On initialise les bouton, les images,
	 * la barre de chargement...
	 * 
	 * @param gc GameContainer 
	 * @param sbg State
	 * @throws SlickException 
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		LoadingList.setDeferredLoading(true);     // Initialise la barre de chargement
		
		ouch = new Sound("res/music/ouch.wav");
		ouch_girl = new Sound("res/music/ouch_girl.wav");
		coin = new Sound("res/music/sound/coin.wav");
		explosion = new Sound("res/music/sound/explosion.wav");
		hurt = new Sound("res/music/sound/hurt.wav");
		robot = new Sound("res/music/sound/robot.wav");
		up = new Sound("res/music/sound/up.wav");
		laser = new Sound("res/music/sound/laser.wav");
		water = new Sound("res/music/sound/water.wav");
		hit = new Sound("res/music/sound/hit.wav");
		
		particle = new Particle();
		particle.init("menu");
		background =new Image("res/menu/background.png");
		loadBar = new Image("res/menu/load.png");
		backgroundLoad = new  Image("res/menu/bg.png");
		bg = new  Image("res/menu/backgroundLoad.jpg");
		lumies = new Image("res/menu/lumies.png");
		sky1 = new Image("res/menu/sky1.png");
		sky2 = new Image("res/menu/sky2.png");
		sky3 = new Image("res/menu/sky1.png");
		sky4 = new Image("res/menu/sky2.png");
		textfield = new Image("res/menu/textfield.png");
		son = new Sound("res/menu/selec.wav");
		
		play = new MouseOverArea(gc, new Image("res/menu/jouer.png"), 300, 285);
		play.setNormalColor(new Color(0.8f, 0.8f, 0.8f, 1f)); 					// état normal
		play.setMouseOverColor(new Color(1f, 1f, 1f, 1f)); 				// état survolé
		exit = new MouseOverArea(gc, new Image("res/menu/quitter.png"), 540,480 );
		exit.setNormalColor(new Color(0.8f, 0.8f, 0.8f, 1f)); 					// état normal
		exit.setMouseOverColor(new Color(1f, 1f, 1f, 1f)); // état survolé
		load = new MouseOverArea(gc, new Image("res/menu/charger.png"), 250, 370);
		load.setNormalColor(new Color(0.8f, 0.8f, 0.8f, 1f)); 					// état normal
		load.setMouseOverColor(new Color(1f, 1f, 1f, 1f));
		option = new MouseOverArea(gc, new Image("res/menu/option.png"), 80, 480);
		option.setNormalColor(new Color(0.8f, 0.8f, 0.8f, 1f)); 					// état normal
		option.setMouseOverColor(new Color(1f, 1f, 1f, 1f));
		pseudoImage = new MouseOverArea(gc, new Image("res/menu/pseudo.png"), 230, 215);
		pseudoImage.setNormalColor(new Color(0.8f, 0.8f, 0.8f, 1f)); 					// état normal
		pseudoImage.setMouseOverColor(new Color(1f, 1f, 1f, 1f));
		play.setMouseDownSound(son);
		exit.setMouseDownSound(son);
		load.setMouseDownSound(son);
		option.setMouseDownSound(son);
		
		gc.setMouseCursor("res/all/cursor.png", 0, 0); // Modifie le curseur de la souris
		
		field = new TextField(gc, gc.getDefaultFont(), 440, 230, 150, 30); // Crée un champ de texte
		setMusic(new Music("res/music/menuTheme.ogg",true));
	}
	
	/**
	 * On affiche la barre de chargement
	 * Puis on affiche les boutons du menu
	 * 
	 * @param gc GameContainer
	 * @param sbg State
	 * @param g Sortie de l'écran
	 * @throws SlickException
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		int posX = 0; 
		int posY = 0;
		
		g.setColor(Color.white);
		
		int total = LoadingList.get().getTotalResources(); // Retourne un integer, permet de savoir la taille total de la barre
		int loaded = LoadingList.get().getTotalResources() // Permet de savoir la taille dynamique de la barre de chargment
				- LoadingList.get().getRemainingResources();

		/////Barre de Chargement
		if (nextResource != null && start==false) {
			backgroundLoad.draw(0,0); // Background
			g.setColor(Color.black);
			Game.uFont.drawString(20, 550,"Loading: " + nextResource.getDescription(),Color.black); // Permet de voir ce qui est chargé !
		}

		
		@SuppressWarnings("unused")
		float loadingBar = loaded / (float) total;
		if (!started) {
			//g.fillRect(250, 250, loaded * 20, 20); // On créé un rectangle qui se remplie
			//g.drawRect(250, 250, total * 20, 20);// il faudra le placer à la fin pour que cela soit centré
			loadBar.draw(163,0,800*loadingBar,600);  // On fait agrandir la barre de chargement.
			Game.uFont.drawString(380, 350, ""+Math.round(loadingBar*100)+"%");
		}
		
		if (started || Event.STARTED){
			if(!isGeneric){
				if(cpt<25) cpt++;
				//sbg.enterState(5, new EmptyTransition(),new FadeInTransition(Color.black));
				isGeneric = true; 
			}
		}
		
		// Si tout les éléments ont été chargés, on lance le rendu à l'écran
		if (isGeneric && cpt>0) {
			posX = Mouse.getX();    // attrape la position de la souris
			posY = Math.abs((Mouse.getY())-600);
			g.setColor(Color.black);
			bg.draw(0,0);
			particle.render(400, 300);
			sky1.draw(x1, y1);
			sky2.draw(x2, y2);
			sky3.draw(x3, y3);
			sky4.draw(x4, y4);
			lumies.draw(0, yLumies);
			background.draw(0, 0);
			//g.drawString("Position Souris : X =" + posX + " , Y=" + posY, 500,10);
			g.setColor(Color.white);
			play.render(gc, g);
			exit.render(gc, g);
			load.render(gc, g);
			pseudoImage.render(gc,g);
			load.render(gc, g);
			option.render(gc, g);
			field.render(gc,g);
			textfield.draw(439,229);
			
			
			start = true;     // permet de savoir si le jeu a bien été lancé une fois
			Event.STARTED=false;
		}
	
		// On entre dans le jeu avec une transition
		if ((posX > play.getX() && posX < (play.getX()+180)) && (posY < (play.getY()+play.getHeight()) && posY > play.getY())) {
			if (Mouse.isButtonDown(0)) {
				@SuppressWarnings("unused")
				ReloadGame reload = new ReloadGame(); // On relance la partie avec les paramètres par défauts
				//getMusic().stop(); // on stop la musique pour lancer le jeu
				if(Game.getNewGame()){
					pseudo_save="";
					sbg.enterState(6);
					sbg.enterState(6, new FadeOutTransition (Color.black),new FadeInTransition(Color.black));
					yLumies = 0;
					Map.setInitNPC(false);
					Map.mapTab();
					Event.fin = true;
					field.deactivate();
				}
				else
				{
					Event.fin = true;
					sbg.enterState(6);
					sbg.enterState(6, new FadeOutTransition (Color.black),new FadeInTransition(Color.black));
					yLumies = 0;
					Map.setInitNPC(false);
					Map.mapTab();
					field.deactivate();
					pseudo_save="";
				}
				
			}
		}

		if ((posX > exit.getX() && posX < (exit.getX()+exit.getWidth()) && (posY < exit.getY()+exit.getHeight() && posY > exit.getY()))) {
			if (Mouse.isButtonDown(0)) {
				System.exit(0);   // On quitte le jeu
			}
		}
		
		pseudo = field.getText(); // on donne la valeur du champs de texte à la variable pseudo
		if(pseudo.equals("")){
			if(!pseudo_save.equals(""))
				pseudo = pseudo_save;
			else if(Player.getSex().equals("boy"))
				pseudo = "Kevin";
			else if(Player.getSex().equals("girl"))
				pseudo = "Laura";
		}
		if(!pseudo_save.equals(""))
			pseudo = pseudo_save;
		
		
		////CHARGEMENT
		
		if((posX>load.getX() && posX<load.getX()+load.getWidth()) && (posY<load.getY()+load.getHeight() && posY>load.getY())){
			if(Mouse.isButtonDown(0)){
				/*Map.setInit(false);
				Map.setInitNPC(false);
				ReloadSave reload = new ReloadSave();
				Map.setInit(false);
				Map.setInitNPC(false);
				//Event.makeReload = true;
				//Event.fin = true;
				reload.reloadGame(); // Chargement de la sauvegarde*/
				Event.makeReload = true;
				sbg.enterState(2);
				sbg.enterState(2, new FadeOutTransition (Color.black),new FadeInTransition(Color.black));
				
			}
		}
		if((posX>option.getX() && posX<option.getX()+option.getWidth()) && (posY<option.getY()+option.getHeight() && posY>option.getY())){
			if(Mouse.isButtonDown(0)){
				sbg.enterState(4);
				sbg.enterState(4, new FadeOutTransition(),new FadeInTransition(Color.black));
			}
		}
	}

	/**
	 * On augmente la taille de la barre de chargement
	 * On modifie la position des nuages ...
	 * 
	 * @param gc GameContainer
	 * @param sbg State
	 * @param delta Permet d'éviter le changement de vitesse selon les FPS
	 * @throws SlickException
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		
		if (nextResource != null) {
			try {
				nextResource.load();
				try {
					Thread.sleep(50);
				} catch (Exception e) {
				}
			} catch (IOException e) {
				throw new SlickException("Failed to load: "
						+ nextResource.getDescription(), e);
			}

			nextResource = null;
		}

		if (LoadingList.get().getRemainingResources() > 0) {
			nextResource = LoadingList.get().getNext();
		} else {
			if (!started) {
				started = true;
				start=true;
				
			}
		}
		if(started || Event.STARTED){
			if(yLumies>-600)
				yLumies -=10;

			if(!getMusic().playing()){ // Lancement de la music
					getMusic().loop();
					getMusic().setVolume(Event.volume);
			}
			particle.update(delta);
		}
		
		if(x1<800)x1+=0.09*delta;
		else {x1=-150;y1= (int) (Math.random()*600-50);}
		if(x2<800)x2+=0.12*delta;
		else {x2=-120;y2= (int) (Math.random()*600-50);}
		if(x3<800)x3+=0.11*delta;
		else {x3=-130;y3= (int) (Math.random()*600-50);}
		if(x4<800)x4+=0.15*delta;
		else {x4=-140;y4= (int) (Math.random()*600-50);}	
		
	}

	/**
	 * @return Numéro relatif aux states
	 */
	public int getID() {
		return 0;
	}
	
	/**
	 * @return pseudo Pseudo du joueur
	 */
	public static String getPseudo(){
		return pseudo;
	}
	public static void setPseudo( String pseudo_save){
		Menu.pseudo_save = pseudo_save;
	}
	
	/**
	 * Modifie le lancement du jeu
	 * @param started Lancement du jeu
	 */
	public static void setStarted( boolean started){
		Menu.started = started;
	}
	
	/**
	 * @return music Retourne la musique en cours
	 */
	public static Music getMusic() {
		return music;
	}
	
	/**
	 * Modifie la musique en cours
	 * @param music Musique en cours
	 */
	public static void setMusic(Music music) {
		Menu.music = music;
	}
}
