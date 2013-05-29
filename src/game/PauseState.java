package game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.EmptyTransition;

import event.Event;

import particle.Particle;
import player.Player;
import serialize.Serializer;

/**
 * Classe permettant d'afficher le menu de pause durant le jeu
 * 
 * @author Rémy
 *
 */

public class PauseState{

	/** MouseOverArea des boutons */
	private MouseOverArea menu, save, option,quit,retour;
	/** Image du fond */
	private Image bg;
	/** Particules du fonds */
	private Particle particle;
	/** Etat du jeu */
	public static boolean PAUSE;
	/** Sauvegarde du jeu */
    private Serializer serial;
	
    /**
     * Initialise les bouton etc...
     * 
     * @param gc GameContainer
     * @throws SlickException
     */
	public void init(GameContainer gc) throws SlickException{
		menu = new MouseOverArea(gc, new Image("res/pause/menu.png"), 340, 180);
		menu.setNormalColor(new Color(0.8f, 0.8f, 0.8f, 1f));
		save = new MouseOverArea(gc, new Image("res/pause/save.png"), 280, 230);
		save.setNormalColor(new Color(0.8f, 0.8f, 0.8f, 1f));
		option = new MouseOverArea(gc, new Image("res/pause/option.png"), 320, 290);
		option.setNormalColor(new Color(0.8f, 0.8f, 0.8f, 1f));
		quit = new MouseOverArea(gc, new Image("res/pause/quitter.png"), 325, 350);
		quit.setNormalColor(new Color(0.8f, 0.8f, 0.8f, 1f));
		retour = new MouseOverArea(gc, new Image("res/pause/retour.png"), 325, 420);
		retour.setNormalColor(new Color(0.8f, 0.8f, 0.8f, 1f));
		bg = new Image("res/pause/bg.png");
		particle = new Particle();
		particle.init("menu");
		serial = new Serializer();
		
	}
	
	/** 
	 * Gère la souris, et les particules 
	 * 
	 * @param sbg State 
	 * @param delta delta de Slick
	 */
	public void update(StateBasedGame sbg,int delta){
		particle.update(delta);
		if(PAUSE){
		
			Player.setMoving(false);
			Event.cinematic = true;
			Event.notMove = true;
			
			int posX = Mouse.getX();    // attrape la position de la souris
			int posY = Math.abs((Mouse.getY())-600);
			
			
			
			if (this.pos(menu, posX, posY)) {
				if (Mouse.isButtonDown(0)) {
					sbg.enterState(0);
					sbg.enterState(0, new EmptyTransition (),new BlobbyTransition(Color.black));
					PauseState.PAUSE = false;
				}
			}
			if (this.pos(option, posX, posY)) {
				if (Mouse.isButtonDown(0)) {
					sbg.enterState(4);
					sbg.enterState(4, new EmptyTransition (),new BlobbyTransition(Color.black));
				}
			}
			if (this.pos(quit, posX, posY)) {
				if (Mouse.isButtonDown(0)) {
					System.exit(0);
				}
			}
			if (this.pos(retour, posX, posY)) {
				if (Mouse.isButtonDown(0)) {
					PauseState.PAUSE = false;
				}
			}
			if (this.pos(save, posX, posY)) {
				if (Mouse.isButtonDown(0)) {
					serial.serializer();
					PauseState.PAUSE = false;
				}
			}
		}
		else {
			Player.setMoving(true);
			Event.cinematic = false;
			Event.notMove = false;
			
		}
	}
	
	/**
	 * Retourne true si la souris est sur le bouton 
	 *
	 * @param a
	 * @param posX
	 * @param posY
	 * @return boolean
	 */
	private boolean pos(MouseOverArea a, int posX, int posY){
		if ((posX > a.getX() && posX < (a.getX()+a.getWidth()) && (posY < a.getY()+a.getHeight() && posY > a.getY()))) {
			return true;
		}
		return false;
	}
	
	/**
	 * Affiche le menu a l'écran 
	 * @param gc GameContainer
	 * @param g Graphics
	 */
	public void render(GameContainer gc, Graphics g){
		if(PAUSE){
			particle.render(400, 300);
			bg.draw(0,0);
			menu.render(gc, g);
			option.render(gc, g);
			save.render(gc, g);
			retour.render(gc, g);
			quit.render(gc, g);
		}
	}

}
