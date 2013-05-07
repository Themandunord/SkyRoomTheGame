package npc;

import event.Event;

import java.io.FileNotFoundException;

import map.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;

import player.Player;

/**
 * Classe permettant de créer un NPC capable de nous suivre
 * 
 * @author Rémy
 *
 */

public class NPCFollower extends NPC {

	/** Animation du NPC */
	protected Animation current_anim,left_anim,up_anim,down_anim,right_anim;
	/** Coordonnées des NPC */
	private float x=100,y=200;
	/** PathFinder */
	private AStarPathFinder pathFinder;
	/** Chemin que le NPC parcourt */
	private Path path;
	/** Map permettant dans trouver le chemin */
	protected Map map2 = new Map();
 
	
	public NPCFollower(int x, int y){
		this.x = x;
		this.y = y;
	}
	/**
	 * On ajoute l'animation du NPC
	 */
	public void init(String name , int ID , int IDx, int IDy, int nbrID,String direction) throws SlickException{
		super.init(name, ID, IDx, IDy, nbrID, direction);
		if(Map.isMap(IDx, IDy)){
		down_anim = new Animation(spriteNpc, 0, 0, 2, 0, true, 200, true);
		up_anim = new Animation(spriteNpc, 0, 3, 2, 3, true, 200, true);
		right_anim = new Animation(spriteNpc, 0, 2, 2, 2, true, 200, true);
		left_anim = new Animation(spriteNpc, 0, 1, 2, 1, true, 200, true);
		down_anim.stop(); // On commence par annuler les animation
		up_anim.stop();
		right_anim.stop();
		left_anim.stop();
		current_anim = right_anim; // Animation courante
		Event.EVENT = 1;
		}
	}
	
	/**
	 * Méthode de rendu si l'événement a eu lieu
	 * @param g Sortie a l'écran
	 * @param IDx Tableau des abscisses
	 * @param IDy Tableau des ordonnées
	 */
	public void render(Graphics g, int IDx[], int IDy[]){
		if(Event.EVENT==1 && !Event.spaceShip){
			for(int i=0; i<IDx.length; i++){
				if(Map.isMap(IDx[i], IDy[i]))
					current_anim.draw(x, y);
			}
			
		}
	}
	
	/**
	 * On affiche le dialogue et on active les événements
	 */
	public void render(int IDX, int IDY, int X , int Y , int x , int y,Graphics g) throws FileNotFoundException{
		if(Event.EVENT==0){
			super.render(IDX, IDY, X, Y, x, y, g);
			super.renderDialog();
		}
		if(finish)
			Event.EVENT=1;
	}
	
	/**
	 * On active l'update selon les événements
	 */
	public void update(GameContainer gc){
		if(Event.EVENT==0)
			super.update(gc);
		
	}
	
	
	/**
	 * Update permettant au NPC de suivre le joueur
	 * 
	 * @param delta Permet de garder une vitesse constante selon les FPS
	 * @param IDx Tableau des abscisses
	 * @param IDy Tableau des ordonnées
	 */
	public void follow(int delta,int IDx[], int IDy[]){
		if(Event.EVENT==1 & !Event.spaceShip && current_anim!=null){
			for(int i=0; i<IDx.length; i++){
				if(Map.isMap(IDx[i], IDy[i])){
					
					pathFinder = new AStarPathFinder(map2, 475, false);
					path = pathFinder.findPath(null,(int)(x+16)/32, (int)(y+20)/32, (int)(Player.getX()+16)/32, (int)(Player.getY()+16)/32);
					
					if(Math.sqrt(Math.pow(x-Player.getX(),2)+Math.pow(y - Player.getY(),2)) > 50 && path != null ){
						
						if(path.getX(1)<path.getX(0)){
							x-=0.10*delta;
							current_anim = left_anim;
							current_anim.start();
						}
						else if(path.getX(1)>path.getX(0)){
							x+=0.10*delta;
							
							current_anim = right_anim;
							current_anim.start();
						}
						if(path.getY(1)>path.getY(0)){
							y+=0.10*delta;
							current_anim = down_anim;
							current_anim.start();
						}
						else if(path.getY(1)<path.getY(0)){
							y-=0.10*delta;
							
							current_anim = up_anim;
							current_anim.start();
						}
						
					}
					else current_anim.stop();
					
					if(!Map.isInitNPC()){
						Map.mapTab();
						x = Player.getX()+20;
						y = Player.getY()-20;
					}
				}
			}
		}
	}
}
