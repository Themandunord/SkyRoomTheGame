package npc;

import java.io.FileNotFoundException;

import map.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import player.Player;

import event.Event;
import game.Game;
import game.InputControl;

/**
 * Classe permettant aux NPC de parcourir un chemin spécifique
 * 
 * @author Rémy
 *
 */

public class NPCMover extends NPC {
	/** Animation du NPC */
	protected Animation current_anim,left_anim,up_anim,down_anim,right_anim;
	/** Coordonnées du NPC */
	private float x,y;
	private boolean spartiateFutur;
	private Image dialbox;
	private int cpt,cpt2;
	private Input input;
	
	/** 
	 * Instancie le NPCMover
	 * 
	 * @param x X de départ
	 * @param y Y d'arrivé
	 */
	public NPCMover(float x, float y){
		super();
		this.x=x;
		this.y=y;
		try {
			dialbox = new Image("res/all/dialboxEvent.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialisation des animations
	 */
	public void init(String name , int ID , int IDx, int IDy, int nbrID,String direction) throws SlickException{
		super.init(name, ID, IDx, IDy, nbrID, direction);
		if(Map.isMap(IDx, IDy)){ 
			SpriteSheet spriteNpc = new SpriteSheet("res/npc/npc-spartan.png", 32, 32);
			down_anim = new Animation(spriteNpc, 0, 0, 2, 0, true, 200, true);
			up_anim = new Animation(spriteNpc, 0, 3, 2, 3, true, 200, true);
			right_anim = new Animation(spriteNpc, 0, 2, 2, 2, true, 200, true);
			left_anim = new Animation(spriteNpc, 0, 1, 2, 1, true, 200, true);
			down_anim.stop(); // On commence par annuler les animation
			up_anim.stop();
			right_anim.stop();
			left_anim.stop();
			current_anim = down_anim; // position courante
		}
	}
	
	/**
	 * Permet d'afficher le NPC Sparta et de le faire avancer lors
	 * de l'activation de l'événement
	 * 
	 * @param IDx IDX de la map
	 * @param IDy IDY de la map
	 * @param X X du sprite de départ
	 * @param Y Y du sprite de départ
	 * @param xPos X de départ
	 * @param yPos Y de départ
	 * @param g Sortie à l'écran
	 * @throws FileNotFoundException
	 */
	public void renderSpartanPrehistoire(int IDx , int IDy , int X, int Y,int xPos, int yPos , Graphics g,GameContainer gc) throws FileNotFoundException{
		input = gc.getInput();
		if(Map.isMap(IDx, IDy)){
			if(Event.NPC_event!=1){
				if((InputControl.inputPressed(Input.KEY_J) || input.isControlPressed(5)) && frontOf){
					Event.NPC_event = 1;
				}
				super.render(IDx, IDy, X , Y, xPos, yPos, g);
				super.renderDialog();
			}
			else {
				current_anim = right_anim;
				Player.setMoving(false);
				if(x<850){
					current_anim.start();
					current_anim.draw(x,y);
					x+=4;
				}else{
					Player.setMoving(true);
					Map.setSpartiate(true);
				}
			}			
		}
	}
	
	public void renderSpartanTransition(int IDx , int IDy , int X, int Y,int xPos, int yPos , Graphics g) throws FileNotFoundException{
		if(Map.isMap(IDx, IDy) && current_anim!=null){
			current_anim = up_anim;
			if(y>-50){
				current_anim.start();
				current_anim.draw(x,y);
				y-=5;
			}
		}			
	}
	
	public void renderSpartanFutur(int IDx , int IDy , int X, int Y,int xPos, int yPos , Graphics g) throws FileNotFoundException{
		if(Map.isMap(IDx, IDy) && current_anim!=null){
			current_anim = up_anim;
			if(Event.spartiateFuturEvent){
				if(!spartiateFutur){
					Player.setMoving(false);
					current_anim.start();
					current_anim.draw(x,y);
					if(y>400)
						y-=5;
					else spartiateFutur = true;
				}
				if(spartiateFutur){
					Player.setMoving(false);
					current_anim = down_anim;
					if(cpt<200){
						current_anim.stop();
						current_anim.draw(x,y);
						cpt++;
						dialbox.draw(300,250);
						Game.uFont.drawString(310, 260,"Quoi ?! Cet homme que les dieux t'ont envoyé vient de te\nsauver la vie" +
								" et tu en profites pour te moquer de lui ?\nTu vas voir ce que j'en fait de ta barrière !! SpartaaaAa !!");
					}
					else{
						if(y<650){
							cpt2 = 0;
							current_anim.start();
							current_anim.draw(x, y);
							y+=5;
						}
						else{
							if(cpt2<160){
								cpt2++;
								dialbox.draw(50,250);
								Game.uFont.drawString(60, 260,"... C'était qui ce gars ?");
							}
							else{
								Event.spartiateFuturEvent = false;
								spartiateFutur = false;
								Player.setMoving(true);
								Event.futur_gate = true;
							}
							
						}
					}
				}
			}
		}			
	}
	
}
