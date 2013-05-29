/**
 * @licence
 * @copyright
 * @author LESPAGNOL <r.lespagnol@iariss.fr>, NAME2 <email2>
 *  
 */

package game;

import java.awt.SplashScreen;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JOptionPane;

import org.lwjgl.Sys;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import blockout.com.slickout.GamePlayState;
import blockout.com.slickout.LevelSelector;
import blockout.com.slickout.MainMenuGameState;

import pacman.it.marte.games.pacman.state.EndOfGame;
import pacman.it.marte.games.pacman.state.GamePacman;
import pacman.it.marte.games.pacman.state.LevelLose;
import pacman.it.marte.games.pacman.state.LevelWin;
import pacman.it.marte.games.pacman.state.MenuPacman;
import pacman.it.marte.games.pacman.state.PausePacman;
import pacman.it.marte.games.pacman.state.ScoreTable;
import pacman.it.marte.games.pacman.state.GamePacman;
import pacman.it.marte.games.pacman.state.MenuPacman;
import pacman.it.marte.games.pacman.state.PausePacman;
import pong.Pong;
import snake.Board;
import spaceInvader.SpaceI;
import tetris.au.com.sensis.slicktest.Tetris;

/**
 * Classe Principale du jeu 
 * Toutes les states y sont répertoriés
 * 
 * @author Rémy
 *
 */

public class Launch extends StateBasedGame{
	/** Container du jeu */
	public static  AppGameContainer container;
	/** ID du menu */
	public static final int menu = 0;
	/** ID de l'intro */
	public static final int intro = 1;
	/** ID de jeu */
	public static final int game = 2;
	/** ID du gameOver */
	public static final int gameOver = 3;
	/** ID de l'option */
	public static final int option = 4;
	/** ID du generique */
	public static final int generic = 5;
	/** ID de character Selector */
	public static final int selector = 6;
	/** Image pour les fenêtres du jeu */
	private static String [] refs ={"res/all/icon11.png","res/all/icon12.png","res/all/icon13.png"}; 
	
	public static final int movement = 7;
	
	public static final int keyboard = 8;
	public static final int joystick = 9;
	public static final int difficultySelector = 10;
	
	/*public static final int selector1 =8;
	public static final int selector2 =9;
	public static final int selector3 =10;
	public static final int selector4 =11;
	public static final int selector5 =12;
	public static final int selector6 =13;
	public static final int selector7 =14;*/
	
	public static final int tetris = 15;
	
	public static final int pause = 16;
	
	public static final int snake = 17;
	public static final int score = 20;
	public static final int spaceInvader = 21;
	public static final int pong = 22;
	/**
	 * Constructeur principale qui définie le nom de la fénêtre
	 */
	public Launch(){
		super("Beta-SkyRoom");
	}
	
	/**
	 * Initialisation des différentes States
	 * 
	 * @param container GameContainer
	 */
	public void initStatesList(GameContainer container) throws SlickException{
		if(container instanceof AppGameContainer){
			container = (AppGameContainer) container;
		}
		this.addState(new Menu(menu));   // Ajoute les States
		this.addState(new Game(game));
		this.addState(new GameOver(gameOver));
		this.addState(new Intro(intro));
		this.addState(new Option(option));
		this.addState(new Generic(generic));
		this.addState(new CharacterSelector(selector));
		this.addState(new KeyboardControl(keyboard));
		this.addState(new JoystickControl(joystick));
		this.addState(new DifficultySelector(difficultySelector));
		this.addState(new Movement(100));
		/*addState(new MenuPacman(selector1));
		addState(new GamePacman(selector2));
		addState(new PausePacman(selector3));
		addState(new pacman.it.marte.games.pacman.state.LevelLose(selector4));
		addState(new pacman.it.marte.games.pacman.state.LevelWin(selector5));
		addState(new pacman.it.marte.games.pacman.state.ScoreTable(selector6));
		addState(new pacman.it.marte.games.pacman.state.EndOfGame(selector7));*/
		addState(new Tetris(tetris));
		addState(new Board(snake));
		addState(new Score(score));
		addState(new SpaceI(spaceInvader));
		addState(new Pong(pong));
		addState(new MainMenuGameState(31));
		GamePlayState state = new GamePlayState(30);
		state.setLevelFile("data/level1.lvl");
		addState(state);
	    addState(new LevelSelector(32));
	
		//this.enterState(31);
	}
	
	/**
	 * Méthode qui lance la fénêtre et le jeu
	 * 
	 * @param args 
	 * @throws SlickException
	 * @throws IOException 
	 * @throws IllegalStateException 
	 * @throws NullPointerException 
	 */
	public static void main(String[] args) throws SlickException, NullPointerException, IllegalStateException, IOException{
		//try { Thread.sleep(500); } catch (Exception e) {}
		/*if(SplashScreen.getSplashScreen()!=null)
			SplashScreen.getSplashScreen().close();*/
		container = new AppGameContainer(new Launch());
		int option = JOptionPane.showConfirmDialog(null, "Plein écran ?");
	    if (option == 0)
	      container.setDisplayMode(800, 600, true);
	    else if (option == 1)
	      container.setDisplayMode(800, 600, false);
	    else {
	      System.exit(0);
	    }
		//container.setDisplayMode(800, 600, false); // True si plein ecran
		container.setTargetFrameRate(40); // Défini le n	ombre de FPS
		container.setIcons(refs);
		container.setShowFPS(false);
		/*Sys.alert("SkyRoom", "L'équipe de développement de SkyRoom vous souhaite un bon jeu :)" +
				"\n\t\t-Lespagnol Rémy" +
				"\n\t\t-Guicharrousse Alexandre" +
				"\n\t\t-Duchet Loïc" +
				"\n\t\t-Botton Amaël" +	
				"\n\t\t-Merten Stanislas");*/
		container.start();   // Lance l'app
	}
}
