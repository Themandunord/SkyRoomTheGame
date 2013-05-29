package items;

import event.Event;
import game.InputControl;
import game.Menu;

import java.util.TreeMap;

import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import player.Player;

/**
 * Permet de créer des coffres dans le jeu qui contiennent des items
 * 
 * @author Rémy
 *
 */

public class Chest {

	/** SpriteSheet du coffre */
	private SpriteSheet sprite;
	/** Nom du coffre */
	private String name;
	/** Permet de savoir s'il est ouvert et si l'item a été prit */
	private boolean isOpen,isTake;
	/** Rectangle de collision */
	private Rectangle rec;
	/** Compteur pour l'affichage de l'item */
	private int cpt,cptItem;
	/** Item dans le coffre */
	private Item it;
	/** TreeMap permettant de savoir si le coffre a été ouvert */
	private TreeMap<Integer,Boolean> tree;
	/** ID du coffre */
	private int ID;
	private Input input;
	
	
	/**
	 * On instancie le coffre 
	 */
	public Chest(){
		rec = new Rectangle(0, 0, 0, 0);	
		tree = new TreeMap<Integer,Boolean>();
		for(int i = 0; i<100; i++){
			tree.put(i, false);
		}
	}
	
	/**
	 * Initialisation du coffre sur la map
	 * 
	 * @param name Nom du coffre
	 * @param item Item dans le coffre
	 * @param IDx IDx de la map
	 * @param IDy IDy de la map
	 * @param ID ID du coffre
	 */
	public void init(String name, String item,int IDx, int IDy, int ID){
		if(Map.isMap(IDx, IDy)){
			try 
			{
				this.name = name;
				sprite = new SpriteSheet("res/chest/"+name+".png",32,32);
				it = new Item(item);
				isOpen = false;
				isTake = false;
				cpt = 0; cptItem = 0;
				this.ID = ID;
				Map.setInitNPC(true);
			} catch (SlickException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	/** 
	 * Affiche le coffre à l'écran et gère la collision avec le joueur
	 *
	 * @param IDx IDx de la map
	 * @param IDy IDy de la map
	 * @param x x
	 * @param y y
	 * @param gc GameContainer
	 * @throws SlickException
	 */
	public void render(int IDx, int IDy, int x, int y, GameContainer gc) throws SlickException{
		input = gc.getInput();
		if(Map.isMap(IDx, IDy) && sprite!=null){
			if(!isOpen && !tree.get(ID)){
				sprite.getSprite(0, 0).draw(x, y);
				rec.setBounds(x, y, 32, 50);
			}
			else{
				if(cpt<40 && !tree.get(ID)){
					sprite.getSprite(1, 0).draw(x,y);
					cpt++;
				}
				else{
					sprite.getSprite(1, 0).draw(x,y);
					if(!tree.get(ID) && cptItem<40){
						it.renderOnly(Player.getX(), Player.getY()-20);
						it.itemUpdate();
						it.setTaked(true);
						cptItem++;
					}
					else if(it.getTake() && !tree.get(ID)){
						if(it.getName().equals("bikini") || it.getName().equals("cape") || it.getName().equals("torch_chest"))
							Menu.up.play(1,Event.volume);
						else Menu.coin.play(1,Event.volume);
						isTake=true;
						tree.put(ID, true);
					}
					
				}
			}
			
			if(Player.getRect().intersects(this.rec)){
				if(InputControl.inputPressed(Input.KEY_LCONTROL) || input.isControlPressed(6))
					isOpen =true;
				
			}
				
			
		}
	}
	
	/**
	 * Affiche le dialogue de l'item à l'écran
	 */
	public void renderSpecialDialog(){
		if(isTake){
			it.renderItemDialog();
		}
	}
}
