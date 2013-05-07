package player;

import java.util.ArrayList;
import java.util.List;

import event.Event;
import game.GameSound;
import game.Hud;
import game.InputControl;
import game.Launch;
import game.Menu;
import map.Map;
import monster.Monster;
import monster.Shoot;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

/**
 * Classe Principale du Player
 * 
 * @author Rémy
 *
 */

public class Player {

	/** Permet le changement d'animation sans soucis */
	private static int Num = -1;
	/** ID du Skin du Hero */
	private static int IDHero;
	/** SpriteSheet du Hero */
	private static SpriteSheet hero;
	/** Animation du hero */
	private static Animation Anim_down, Anim_up,Anim_right,Anim_left,current_Anim;
	/** Abscisse du Hero */
	private static float xHero = 393f;
	/** Ordonnée du Hero */
	private static float yHero = 380f;
	/** Recupere les entrées au clavier */	
	private Input input;
	/** Vitesse du hero */
	private static float speed = 0.15f;
	/** Rectangle de collision du Hero */
	private static Rectangle rectHero;
	/** True si le Hero peut bouger */
	private static boolean moving = true;
	/** IDX où le Hero se transforme en vaisseau */
	private int[] IDX={6,6,6,6,7,7,7,8,8,8,9,9};
	/** IDY où le Hero se transforme en vaisseau */
	private int[] IDY={4,3,2,1,3,2,1,4,3,2,3,2};
	/** Epée du Hero */
	private Sword sword;
	/** Direction à laquelle le Hero se trouve */
	private static int direction;
	/** True si le Hero a une arme */
	private boolean isSword,isSwordXbox;
	/** Liste de Fusil à eau */
	private static List<WaterGun> water;
	/** 1 Si il n'y a pas encore eu de collision */
	protected int collision = 1;
	/** Permet de faire une timer */
	protected long collisionTimer;
	/** SpriteSheet de l'Animation aie */
	protected SpriteSheet aieSprite;
	/** Animation lors de la collision */
	protected Animation aie;
	/** True si il a été touché */
	private boolean isHit;
	/** Compteur pour la durer de l'animation aie */
	private int cpt;
	/** Fille ou garçon pour l'image */
	private static String sex = "boy";
	/** Permet de savoir si le joueur doit-etre affiché ou non */
	private static boolean isVisible, isFall;
	/** Compteur lorsque le joueur tombe dans le vide */
	private int cptFall;
	/** Liste de tir de blaster */
	private static List<Blaster> listBlaster;
	/** Compteur pour éviter de spammer les tirs */
	private int cptBlaster;
	private Bo bo;
	private boolean boXbox;
	private boolean isBo;
	
	/**
	 * Initialise les animation et le rectangle 
	 * 
	 * @throws SlickException
	 */
	public void init() throws SlickException{
		isVisible = true;
		ChangeAnim(IDHero);
		rectHero = new Rectangle(xHero+8, yHero, 16, 28);
		sword = new Sword();
		bo = new Bo();
		water = new ArrayList<WaterGun>();
		listBlaster = new ArrayList<Blaster>();
		aieSprite = new SpriteSheet("res/hero/aie.png", 32, 32);
		aie = new Animation(aieSprite, 0, 0, 1, 0, true, 200, true);
		aie.stop();
	}
	
	/**
	 * Affiche les différents éléments du Hero
	 * Y compris les armes
	 * 
	 * @param g Sortie à l'écran
	 * @throws SlickException
	 */
	public void render(Graphics g) throws SlickException{
		if(Event.SWORD){
			if(direction==1 && ( isSword || isSwordXbox) && !sword.getAnim(0).isStopped()) sword.renderUp(g);
		}
		if(Event.bo){
			if(direction==1 && (isBo|| boXbox)) bo.renderUp(g);
		}
		if(isVisible){
			current_Anim.draw(xHero, yHero);
		}	
		
		renderSword(g);
		hitPlayer();
	}
	
	/**
	 * Méthode qui gère l'animation lors des dégats
	 */
	public void hitPlayer(){
		if(isHit){
				if(cpt<80){
					aie.start();
					aie.draw(xHero+10, yHero-10);
					cpt++;
				}else {
					isHit = false;
					cpt = 0;
				}
			}else{
				aie.stop();
			}
	}

	/**
	 * Permet l'affichage du l'épée du personnage
	 * 
	 * @param g
	 */
	public void renderSword(Graphics g){
		if(Event.SWORD){
				if(direction==1 && ( isSword || isSwordXbox)) sword.UpdateUp();
				else if(direction==2 && ( isSword || isSwordXbox) && !sword.getAnim(1).isStopped()) sword.renderDown(g);
				else if(direction==3 && ( isSword || isSwordXbox) && !sword.getAnim(3).isStopped()) sword.renderRight(g);
				else if(direction==4 && ( isSword || isSwordXbox) && !sword.getAnim(2).isStopped()) sword.renderLeft(g);
				else Sword.isHere = false;
			}
		sword.update();
		if(Event.bo){
			if(direction==1 && (isBo || boXbox)) bo.UpdateUp();
			else if(direction==2 && (isBo|| boXbox)) bo.renderDown(g);
			else if(direction==3 && (isBo|| boXbox)) bo.renderRight(g);
			else if(direction==4 && (isBo|| boXbox)) bo.renderLeft(g);
			else Bo.isHere = false;
			
			if(!Bo.isHere){
				bo.getRect().setBounds(-100,-100,0,0);
			}
		}
		if(Event.WaterGun){
			for(WaterGun wg:water){
				wg.render();
			}
		}
		if(Event.blaster){
			for(Blaster bl:listBlaster){
				bl.render();
			}
		}
	}
	
	/**
	 * Permet de changer l'animation du Hero
	 * Il faut nommer le fichier "hero-x.png" où x est un chiffre
	 * 
	 * @param ID Numéro du fichier
	 * @throws SlickException
	 */
	public static void ChangeAnim(int ID) throws SlickException  {
		if (Num != ID) {
			Num = ID;
			hero = new SpriteSheet("res/hero/"+sex+"-" + ID + ".png", 32, 32);
			Anim_down = new Animation(hero, 0, 0, 2, 0, true, 200, true);
			Anim_up = new Animation(hero, 0, 3, 2, 3, true, 200, true);
			Anim_right = new Animation(hero, 0, 2, 2, 2, true, 200, true);
			Anim_left = new Animation(hero, 0, 1, 2, 1, true, 200, true);

			Anim_down.stop(); // On commence par annuler les animation
			Anim_up.stop();
			Anim_right.stop();
			Anim_left.stop();
			current_Anim = Anim_up; // position courante	
		}
	}
	
	/**
	 * Méthode qui gère la collision
	 */
	public void colision(){
		if(Hud.getBikiniLife()==0){
			Event.bikini = false;
		}
		
		if(Event.colision) {
				if (collision == 1) {
					collisionTimer = System.currentTimeMillis();  // permet de donner 2 secondes au joueur avant de se faire retirer a nouveau une vie
					collision = 0;
					if(Event.bikini){
						if(Hud.getBikiniLife()>0)
							Hud.setBikiniLige(Hud.getBikiniLife()-1);
						else {
							Event.bikini = false;
							Hud.setNbrHeart(Hud.getNbrHeart() - 1);  // On retire un coeur
						}
					}
					else {
						Hud.setNbrHeart(Hud.getNbrHeart() - 1);  // On retire un coeur
					}
					
					if(sex.equals("boy"))
							Menu.ouch.play(1,Event.volume);
					else {
							Menu.ouch_girl.play(1,Event.volume);
					}
					isHit = true;
				}
				else if (collision == 0) {
					if (collisionTimer + 2000 < System.currentTimeMillis()) 
					{
						collision = 1;
						Event.colision = false;
					}
				}
			}
	}

	
	/**
	 * Permet de bouger le personnage 
	 * 
	 * @param gc GameContainer
	 * @param delta Permet de garder une vitesse constante selon les FPS
	 * @throws SlickException
	 */
	public void update(GameContainer gc, int delta) throws SlickException{
		int IDx = Map.getIDx();
		int IDy = Map.getIDy();
		boolean change = false;
		input = gc.getInput();
		
		colision();
		changePlayerMap(IDx, IDy);
		launchWaterGun(delta);
		launchBlaster(delta);
		inputControl(delta);
		
		if(isFall){
			isHit = true;
			moving = false;
			Event.colision = true;
			cptFall++;
			current_Anim.stop();
			if(cptFall>40){
				cptFall = 0;
				isFall = false;
				moving = true;
			}
		}
		
		
		
			
		if(Map.isMap(11, 1)){
			Dove.doveIsOnPlayer = true;
		}
		
		for(int i=0 ; i<IDX.length;++i){
			if(Map.isMap(IDX[i], IDY[i])){
				change = true ; break;
			}
		}
		if(change){
			ChangeAnim(2);
			Dove.doveIsOnPlayer = false;
			rectHero.setBounds(xHero+10, yHero+5, 18, 15);
		}
		else {
			Event.spaceShip = false;
			if(Event.costume) ChangeAnim(3);
			else if(Event.costume_partiel){
				if(sex.equals("boy"))
					ChangeAnim(4);
				else ChangeAnim(4);
			}
			else if(Event.wood){
				if(sex.equals("boy"))
					ChangeAnim(5);
				else ChangeAnim(5);
			}
			else if(sex.equals("boy")){
				ChangeAnim(0);
			}
				
			else if(sex.equals("girl")){
				ChangeAnim(1);
			}
			rectHero.setBounds(xHero+8, yHero+16, 16, 14);
		}
		
		// Full Ecran
		if(input.isKeyDown(Input.KEY_F)){
		Launch.container.setFullscreen(true);
		}	
	}
	
	/**
	 * Gestion des actions clavier et des différents évenement lié au jouerur et la map
	 * @param delta
	 */
	public void inputControl(int delta){
		
		if(moving & !isFall){
				if(Map.isSlow(xHero+5, yHero+25)){
					speed = 0.10f; 
				}
				else if(Map.isFast(xHero+5, yHero+25)){
					speed = 0.50f;
				}
				else if(Event.spaceShip)
					speed = 0.20f;
				else if (Event.slowAncient)
					speed = 0.10f;
				else speed = 0.15f;
					
				if(Map.isFall(xHero+10, yHero+30)){
					xHero+=32; isFall = true;			
				}
				if(Map.isFall(xHero+25, yHero+30)){
					xHero-=38; isFall = true;
				}
				if(Map.isFall(xHero+16, yHero+25)){
					yHero+=32; isFall = true;
				}
				if(Map.isFall(xHero+16, yHero+31)){
					yHero-=32; isFall = true;
				}
				
				
				if(!Event.waterRennaissance && Map.isWater(xHero+5, yHero+25)){
					Event.waterRennaissance = true;
				}
				if(Event.waterRennaissance && !Event.purification && Map.isPurification(xHero+5, yHero+25)){
					Event.purification = true;
				}
				if(InputControl.inputDown(Input.KEY_LCONTROL) && !Bo.isHit){
					isBo = true;
				}else isBo = false;
				if(InputControl.inputDown(Input.KEY_LCONTROL)){
					isSword = true;	
				}else isSword = false;
				
				
				if(Event.bo && input.isControlPressed(4)){
					boXbox = !boXbox;
				}
				
				if(Event.SWORD && input.isControlPressed(4)){
					isSwordXbox = !isSwordXbox;
				}
				
				
				
				if(InputControl.inputPressed(Input.KEY_LCONTROL)){
							Bo.isHit = false;
				}	
				if(InputControl.inputDown(Input.KEY_UP) || input.isControllerUp(0))
				{
					if (!(Map.isBlocked(xHero+16,yHero+7))){
						if(!Map.isBlockedIf(xHero+16, yHero+8)){
							yHero-=speed*delta;
							Anim_up.start();
							current_Anim = Anim_up;
							direction=1;
						}						
					}
				}
				else if(InputControl.inputDown(Input.KEY_DOWN) || input.isControllerDown(0)){
					if (!(Map.isBlocked(xHero+8, yHero + 33))  &&  !Map.isBlocked(xHero+25, yHero +33)) {
						if(!(Map.isBlockedIf(xHero+16, yHero+32))){
							yHero+=speed*delta;
							Anim_down.start();
							current_Anim = Anim_down;
							direction=2;
						}
					}
				}
				else if(InputControl.inputDown(Input.KEY_RIGHT) || input.isControllerRight(0)){
					if ((!(Map.isBlocked(xHero + 32 , yHero+17))) && (!Map.isBlocked(xHero + 32, yHero +30))){
						if(!(Map.isBlockedIf(xHero+32, yHero+32))){
							xHero+=speed*delta;
							Anim_right.start();
							current_Anim = Anim_right;
							direction=3;
						}
					}
				}
				else if(InputControl.inputDown(Input.KEY_LEFT) || input.isControllerLeft(0)){
					if ((!(Map.isBlocked(xHero, yHero+17))) && (!Map.isBlocked(xHero, yHero +30))){
						if(!(Map.isBlockedIf(xHero, yHero+32))){
							xHero-=speed*delta;
							Anim_left.start();
							current_Anim = Anim_left;
							direction=4;
						}
					}
				}
				else {
					current_Anim.stop();
				}
				
			}
	}
	
	/**
	 * Lancement du pistolet à eau
	 * 
	 * @param delta
	 */
	public void launchWaterGun(int delta){
		if(Event.WaterGun){
				if(InputControl.inputPressed(Input.KEY_LCONTROL) || input.isControlPressed(4)){
					if(direction==1) water.add(new WaterGun(xHero+16, yHero,1));
					if(direction==2) water.add(new WaterGun(xHero+16, yHero+25,2));
					if(direction==3) water.add(new WaterGun(xHero+28, yHero+18,3));
					if(direction==4) water.add(new WaterGun(xHero-2, yHero+18,4));
					Menu.water.play(1,Event.volume);
				}
				for(WaterGun wg:water){
					wg.update(delta);
				}
			}  
	}
	
	/**
	 * Méthode permettant de tirer les blasers
	 * 
	 * @param delta
	 */
	public void launchBlaster(int delta){
		if(Event.blaster){
			cptBlaster++;
				if((InputControl.inputPressed(Input.KEY_LCONTROL) || input.isControlPressed(4)) && Blaster.getAmmo()>0){
					if(Event.blasterStorm && cptBlaster>20){
						if(direction==1) listBlaster.add(new Blaster(xHero+10, yHero+8,0,0,"blasterShot"));
						if(direction==2) listBlaster.add(new Blaster(xHero-8, yHero+10,1,0,"blasterShot"));
						if(direction==3) listBlaster.add(new Blaster(xHero+10, yHero+10,2,1,"blasterShot"));
						if(direction==4) listBlaster.add(new Blaster(xHero, yHero+10,3,1,"blasterShot"));
						cptBlaster = 0;
						Blaster.setAmmo(Blaster.getAmmo()-1	);
						Menu.laser.play(1,Event.volume);
					}
					else if(!Event.blasterStorm &&cptBlaster>9){
						if(direction==1) listBlaster.add(new Blaster(xHero+10, yHero+8,0,0,"blasterShot"));
						if(direction==2) listBlaster.add(new Blaster(xHero+10, yHero+10,1,0,"blasterShot"));
						if(direction==3) listBlaster.add(new Blaster(xHero+10, yHero+10,2,1,"blasterShot"));
						if(direction==4) listBlaster.add(new Blaster(xHero, yHero+10,3,1,"blasterShot"));
						cptBlaster = 0;
						Menu.laser.play(1,Event.volume);
					}
				}
				for(Blaster bl:listBlaster){
					bl.updateBlaster(delta);
				}
			}  
	}
	
	/**
	 * Changement de map selon la position du joueur
	 * 
	 * @param IDx
	 * @param IDy
	 * @throws SlickException
	 */
	public void changePlayerMap(int IDx , int IDy) throws SlickException{
		/////Changement de Map Selon POS
			if (xHero < 10) {
				Map.changeMap(IDx - 1, IDy);
				Map.setIDx(IDx - 1);// gauche
				xHero = xHero + 745;
				water.clear();
				//System.gc();
			} else if (xHero > 760) {
				Map.changeMap(IDx + 1, IDy);
				Map.setIDx(IDx+1); // droite
				xHero = xHero - 745;
				water.clear();
				//System.gc();
			} else if (yHero < 10) {
				Map.changeMap(IDx, IDy + 1);
				Map.setIDy(IDy + 1); // haut
				yHero = yHero + 550;
				water.clear();
				//System.gc();
			} else if (yHero > 560) {
				Map.changeMap(IDx, IDy - 1);
				Map.setIDy(IDy - 1); // bas
				yHero = yHero - 550;
				water.clear();
				//System.gc();
			}else Map.changeMap(IDx,IDy);
	}
	
	/**
	 * @return xHero
	 */
	public static float getX(){
		return xHero;
	}
	
	/**
	 * @return yHero
	 */
	public static float getY(){
		return yHero;
	}
	
	/**
	 * @return rectHero Rectangle de collision du Hero
	 */
	public static Rectangle getRect(){
		return rectHero;
	}
	
	/**
	 * Modification des X
	 * @param xHero
	 */
	public static void setX(float xHero){
		Player.xHero=xHero;
	}
	
	/**
	 * Modification des Y
	 * @param yHero
	 */
	public static void setY(float yHero){
		Player.yHero=yHero;
	}
	
	/**
	 * Modification de moving
	 * @param moving True si le joueur peut bouger
	 */
	public static void setMoving( boolean moving)
	{
		Player.moving = moving;
	}
	
	/**
	 * @return list Liste des cercles de collisions du fusil à eau
	 */
	public static List<Circle> getCircle(){
		List<Circle> list = new ArrayList<Circle>();
		for(WaterGun wg:water)
			list.add(wg.getCircle());	
		return list;
	}
	
	/**
	 * @return Liste de tir du pistolet à eau
	 */
	public static List<WaterGun> getWater(){
		return water;
	}
	
	/**
	 * @return Liste de tir du blaster
	 */
	public static List<Blaster> getBlaster(){
		return listBlaster;
	}
	
	/**
	 * Modifie le sexe du personnage
	 * 
	 * @param sex "boy" ou "girl"
	 * @throws SlickException
	 */
	public static void setSex(String sex) throws SlickException
	{
		Player.sex = sex;
		if(Player.sex.equals("girl"))
			ChangeAnim(1);
		else ChangeAnim(0);
	}
	
	/**
	 * @return Sexe du personnage
	 */
	public static String getSex(){
		return Player.sex;
	}
	
	/**
	 * Modification de visiblePlayer
	 * @param isVisible
	 */
	public static void setVisiblePlayer(boolean isVisible){
		Player.isVisible = isVisible;
	}
	
	/**
	 * @return direction dans laquelle le joueur regarde
	 */
	public static int getDirection(){
		return direction;
	}
	/**
	 * Modification de la vitesse 
	 * @param speed
	 */
	public static void setSpeed(float speed){
		Player.speed = speed;
	}
}
	
	

