package npc;

import event.Event;
import game.Game;
import game.Hud;
import game.InputControl;
import game.Menu;

import java.io.FileNotFoundException;

import map.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import player.Player;

/**
 * Classe mère des NPC
 * No Player Character
 * 
 * @author Rémy
 *
 */

public class NPC {

	/** Image de la dialbox */
	protected Image dialbox;
	/** SpriteSheet du NPC*/
	protected SpriteSheet spriteNpc;
	/** Rectangle de collision */
	protected Rectangle rec;
	/** Numéro du dialogue */
	protected int ID, IDdepart;
	/** Récupère le dialog dans le fichier XML */
	protected NPCDialog npc;
	/** Dialogue du NPC et son nom */
	protected String dialog,name;
	/** Nombre de dialogue qu'a le NPC */
	protected int nbrID;
	/** True si le personnage est a coté*/
	protected boolean frontOf;
	/** True si il n'y a plus de dialogue */
	protected boolean finish;
	/** Direction dans laquelle le NPC regarde */
	private String direction;
	/** ID de la map */
	private int IDX=-1, IDY=-1;
	/** True si le chargement des ressources a été éffectué */
	private boolean isReady;
	/** Image pour les images à l'écran ( ex lettre ) */
	private Image screen;
	/** True si on est en train de parler au NPC */
	private boolean talk;
	/** Compteur pour l'affichage de l'affiche */ 
	private int compteur;
	/** Input du clavier */
	private Input input;
	/** Compteur pour afficher la lettre après avoir parler à l'enscmu girl */
	private int cptEnscmu;
	/** True si on peut lancer la cinematique du spartiate */
	private boolean cineSparta;
	private Image next;
	
	
	/**
	 * Constructeur par défaut qui instancie les animations et images
	 */
	public NPC(){
		try {
			dialbox = new Image("res/all/dialboxEvent.png");
			next = new Image("res/all/next.png");
			npc = new NPCDialog();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialisation des Sprite et des rectangles de collisions
	 * 
	 * @param name Nom du NPC
	 * @param ID Numéro du dialogue du NPC
	 * @param IDx IDX de la map
	 * @param IDy IDY de la map
	 * @param nbrID Nombre de dialogue du NPC
	 * @param direction Direction dans laquelle le NPC regarde
	 * @throws SlickException
	 */
	public void init(String name , int ID , int IDx, int IDy, int nbrID, String direction) throws SlickException{
		if(Map.isMap(IDx, IDy)){ 
			isReady = false;
			spriteNpc = new SpriteSheet("res/npc/npc-"+name+".png",32, 32);  // Chargement en fonction de son nom
			this.name =  name;
			this.ID = ID;
			this.nbrID = nbrID;
			this.direction = direction;
			IDdepart = ID;
			if(direction.equals("right")) rec = new Rectangle(-100,-100,30,10);
			if(direction.equals("left")) rec = new Rectangle(-100,-100,15,10);
			if(direction.equals("up")) rec = new Rectangle(-100,-100,10,20);
			if(direction.equals("down")) rec = new Rectangle(-100,-100,15,30);
			Map.setInitNPC(true);// Permet de savoir s'il a été initialiser ou non
			isReady = true;
		}	 
	}
	
	/**
	 * Affichage du NPC selon la map
	 * 
	 * @param IDx IDX de la map
	 * @param IDy IDY de la map
	 * @param X X du Sprite
	 * @param Y Y du Sprite
	 * @param x Abscisse sur la map
	 * @param y Ordonnée sur la map
	 * @param g Sortie de l'écran
	 * @throws FileNotFoundException
	 */
	public void render(int IDx, int IDy, int X , int Y , int x , int y,Graphics g) throws FileNotFoundException{
		if(Map.isMap(IDx, IDy) && isReady && spriteNpc!=null){
			this.IDX = IDx;
			this.IDY = IDy;
			if(ID==302 || ID ==303 || ID == 304 || ID == 305 || (ID == 317 && Event.enscmuReady)
					|| ID == 307 || ID == 306 || ID == 314 || ID == 310 || ID == 313 || ID == 311 
					|| ( Event.renderLetter && ID == 317) || ID == 309){
				if(Event.MJ){
					x = -100;
					y = -100;
				}
				if(ID == 307 && Event.notRU){
					x = -100;
					y = -100;
				}
			}
			spriteNpc.getSprite(X, Y).draw(x,y);
			
			if(direction.equals("right")) rec.setLocation(x+10,y+14);
			if(direction.equals("left")) rec.setLocation(x-10,y+14);
			if(direction.equals("up")) rec.setLocation(x+10,y-10);
			if(direction.equals("down")) rec.setLocation(x+10,y+20);
			
			if(EventNPCDialog.isDialogEvent() && name.equals(EventNPCDialog.getName())){
				EventNPCDialog.excla.start();
				EventNPCDialog.excla.draw(x, y-32);
			}
			
		}
	}
	
	/**
	 * Initialisation des images pour les affiches
	 * 
	 * @param name Nom de l'image	
	 * @param IDx IDx de la map
	 * @param IDy IDy de la map
	 * @throws SlickException
	 */
	public void initScreen(String name, int IDx, int IDy) throws SlickException{
		if(Map.isMap(IDx,IDy)){
			screen = new Image("res/event/"+name+".png");
			rec = new Rectangle(-100,-100,10,20);
			Map.setInitNPC(true);
		}
	}
	
	/**
	 * Render à l'écran pour l'affiche 
	 * 
	 * @param IDx IDx de la map
	 * @param IDy IDy de la map
	 * @param x x
	 * @param y y
	 * @throws SlickException
	 */
	public void renderScreen(int IDx, int IDy,int x, int y) throws SlickException{
		if(Map.isMap(IDx,IDy) && rec!=null){
			rec.setLocation(x+10,y+25);
			if(Player.getRect().intersects(this.rec)){
				screen.draw(0, 0);
			}
			
			
		}
	}
	
	/**
	 * Affiche le dialogue du NPC récupéré dans le fichier XML
	 * 
	 * @throws FileNotFoundException
	 */
	public void renderDialog() throws FileNotFoundException{
		if((ID == 208 || ID == 209) && Event.halo == 3){
			this.ID = 209;
			if(compteur<220){
				npc.NPCDialoge(ID);
				String name = Menu.getPseudo();
				dialog = npc.getDialog();
				dialog = dialog.replaceAll("name", name);
				dialbox.draw(195,470);
				Game.uFont.drawString(210,475,""+dialog);
				
				compteur++;
			}
			if(compteur==160)
				Event.spartiateFuturEvent = true;
		}
		
		
		if(Map.isMap(IDX, IDY)){ 
			String pseudo = Menu.getPseudo();
			if(Player.getRect().intersects(this.rec)){
				npc.NPCDialoge(ID);   // Permet de récupérer son dialoque dans le xml
				dialog = npc.getDialog();
				if(dialog!=null)
					dialog = dialog.replaceAll("name",pseudo);
				dialbox.draw(200,475);
				talk = true;
				Game.uFont.drawString(210,479,""+dialog); // Affiche le dialogue à l'écran
				if(nbrID!=0 && ID == IDdepart)
					next.draw(550, 560);
				//if(!finish){
					//Player.setMoving(false); // A voir 
					frontOf = true;
				//}
			}else frontOf = false;
		}
	}
	
	/**
	 * Permet de changer de dialogue
	 * 
	 * @param gc GameContainer
	 */
	public void update(GameContainer gc){
		if(!Map.isInitNPC()){
			ID = IDdepart;
			finish=false;
			talk = false;
		}
		
		if(frontOf){
			if(InputControl.inputPressed(Input.KEY_LALT))
			{
				if(ID < (IDdepart+nbrID))
					ID = ID + 1;
				if(ID == (IDdepart+nbrID)){
					finish = true;
				}		
			}
		}
		else finish = false;
		
		
		
		
		if(ID==005 && finish) // Spartiate dans la prehistoire
			Event.NPC_event=1;
		
		if(ID == 316 &&  !Event.enscmuReady){  // Enscmu Girl
			if(cptEnscmu<320) cptEnscmu++;
			if(!Event.renderLetter && cptEnscmu>318){
				this.ID = 317;
				Event.enscmuReady = true;
				Event.tram = true;
			}
			if(!Event.renderLetter && cptEnscmu>318)
				Event.renderLetter = true;
			
		}
		if(ID == 303 & Event.SWORD){  // Debloque l'accés a werner
			this.ID = 308;
			Event.notRU = true;
		}
		
		if(ID == 308 && Event.notRU && Event.SWORD && Event.wernerGirlOrBoy){  // Debloque l'accés a l'encsmu
			Event.yesRU = true;
			this.ID = 311;
		}
		if(ID == 306 && Event.notRU) // Blonde de la BU qui change de dialogue
			this.ID = 309;
		if(ID == 420 && Event.finish_loveQuete)//guarde du monde rennaissance
			this.ID = 421;
		if(ID == 401){
			if(Event.loveQuete)
				ID = 403;
			else if(Event.water){
				ID = 402;
				Event.champiQuete = true;
			}
			else if(Event.waterRennaissance && Event.purification){
				ID = 402;
				Event.champiQuete = true;
			}
			
		}
		if(ID == 405 && Event.loverQuete){
			ID = 407;
			Event.finish_loveQuete = true;
		}
		
		
		
		if(talk){
			switch (ID){
			
			case 304 :
				Hud.setNbrHeart(Event.maxHeart);
			break;
			case 307 :
				Event.WaterGun = false;
				Event.SWORD = true;
			break;
			case 303 :
				Event.notSword = true;
			break;
			case 308 :
				Event.notRU = true;
			break;
			case 310 :
				Event.wernerGirlOrBoy = true;
			break;
			case 313 :
				Event.wernerGirlOrBoy = true;
			break;
			case 207 :
				Event.water = true;
			break;
			case 401 :
				Event.waterQuete = true;
			break;
			case 402 :
				if(Event.champi1 && Event.champi2 && Event.champi3){
					Event.loveQuete = true;
					Event.fontaine = true;
					ID = 403;
				}
			break;
			case 406 :
				Event.loverQuete = true;
			break;
			case 702:
				Event.spartaland_cine = true;
			break;
			case 601:
				Event.bo = true;
				Event.blasterStorm = false;
				Event.blaster = false;
				Event.SWORD = false;
				Event.WaterGun = false;
			break;
			case 1000:
				Event.remy = true;
			break;
			case 1001:
				Event.alex = true;
			break;
			case 1002:
				Event.loic = true;
			break;
			case 1003:
				Event.amael = true;
			break;
			case 1004:
				Event.stan = true;
			break;
			}
			
		}
	}
	
	
	/** 
	 * @return name Nom du NPC
	 */
	public String getName(){
		return name;
	}
	
	public void destroySprite() throws SlickException{
		if(this.spriteNpc!=null)
			this.spriteNpc.destroy();
	}
}
