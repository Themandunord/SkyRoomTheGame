package map;

import monster.AddMonster;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

import event.Event;
import game.InputControl;

/**
 * Classe qui gère les map
 * Chaque changement de map rénitialise des variables permettant
 * de spawn des monstres ou pnj
 * 
 * @author Rémy
 *
 */

public class Map implements TileBasedMap{

	/** IDX de la map */
	private static int IDx;
	/** IDY de la map */
	private static int IDy;
	/** Map affichée */
	private static SkyroomMap map;
	/** Permet de le changement de map sans rechargement des ressources toutes les frames */
	private static int NumX, NumY;
	/** Permet l'initialisation des monstres et des NPC */
	private static boolean isInit,isInitNPC;
	/** Tableau comprenant des 1 s'il y a des collisions */
	private static int[][] mapTab = new int[30][20];
	/** true si on peut lancer une éxecution */
	private static boolean ready = false;
	/** true si on peut passer, relatif au monde futur */
	private static boolean one,two,three,four,five,spartiate,spaceBoss;
	private static GameContainer gc;

	/**
	 * Initialisation de la map
	 * 
	 * @param gc GameContainer
	 * @throws SlickException
	 */
	public void init(GameContainer gc) throws SlickException{
		this.gc = gc;
		map = new SkyroomMap("res/maps/" + IDx + "-" + IDy + ".tmx", "res/maps"); // Charge la Map
		this.one = false;
		this.two = false;
		this.three = false;
		this.four = false;
		this.five = false;
		this.spartiate = false;
	}
	
	/**
	 * Affiche la map qui se trouve derrière le player
	 */
	public void renderDown(){
		map.render(0, 0, 0, 0, map.getWidth(), map.getHeight(), 0, false);    // affiche le calque 0 du la map
		map.render(0, 0, 0, 0, map.getWidth(), map.getHeight(), 1, false);	  // Calque 1 ...
		map.render(0, 0, 0, 0, map.getWidth(), map.getHeight(), 2, false);    // Calque 2...
	}
	/**
	 * Affiche la map qui se trouve devant le personnage
	 */
	public void renderUp(){
		map.render(0, 0, 0, 0, map.getWidth(), map.getHeight(), 3, false);    // Calque 3..
		
	}
	
	public static boolean isMap(int IDX , int IDY){
		if(IDx == IDX && IDy == IDY)
			return true;
		return false;
	}
	
	
	/**
	 * Méthode permettant de changer de map 
	 * @param IDx IDX de la map d'arrivée
	 * @param IDy IDY de la map d'arrivée
	 * @throws SlickException
	 */
	public static void changeMap(int IDx, int IDy) throws SlickException {
		if (NumX != IDx || NumY != IDy) {
			NumX = IDx;
			NumY = IDy;
			map.destroyTile();
			map = new SkyroomMap("res/maps/" + IDx + "-" + IDy + ".tmx","res/maps");
			mapTab();
			isInit=false;  // Permet de recharger les monstres
			AddMonster.isMonster = false;  // Permet de savoir s'il y a un monstre ou non;
			isInitNPC = false;	
			ready = false;
			Event.slowAncient = false;
			Event.save = true;
			InputControl.gc = Map.gc;
			InputControl.update();
			Event.fadeTransition = true;
			System.gc();
		}
	}
	
	/**
	 * Gestion des collisions
	 * Retourne true si c'est bloquant
	 * Avec le logciel TiledMap => "blocked" = "true"
	 * 
	 * @param x Abscisse où l'on veut aller
	 * @param y Ordonnée où l'on veut aller
	 * @return true si c'est bloquant
	 */
	public static boolean isBlocked(float x, float y) {
		
		if(x<798 && y<598 && x>1 && y>1){
			int IDtiles = map.getTileId((int) (x / 32), (int) (y / 32), 4);
			String blocked = map.getTileProperty(IDtiles, "blocked", "false");
			if (!blocked.equals("false")) {
				if (blocked.equals("true"))
					return true;
			}
		}else return false;
		return false;
	}
	
	/**
	 * Crée un tableau de 1 et de 0
	 * 1 si collision 0 sinon
	 */
	public static void mapTab(){
		for(int i=0; i<800;i+=32){
			for(int j=0; j<608; j+=32){
				if(isBlocked(i,j))
					mapTab[i/32][j/32]=1;
				else if(isFall(i, j))
					mapTab[i/32][j/32]=1;
				else if(isBlockedIf(i, j))
					mapTab[i/32][j/32]=1;
				else				
					mapTab[i/32][j/32]=0;
			}
		}
	}
	
	
	/**
	 * Méthode qui cherche une téléportation
	 * Avec le logiciel TiledMap => "teleport" = "true"
	 * 
	 * @param x
	 * @param y
	 * @return true s'il y a une téléportation
	 */
	public static boolean isTeleport(float x, float y) {
		int IDtiles = map.getTileId((int) ((x) / 32), (int) ((y) / 32), 4);
		String teleport = map.getTileProperty(IDtiles, "teleport", "false");
		if (!teleport.equals("false")) {
			if (teleport.equals("true")) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}
	
	/**
	 * Cases de la map où le personnage est ralenti
	 * Avec le logiciel TiledMap => "slow" = "true"
	 * 
	 * @param x
	 * @param y
	 * @return true si c'est ralentie
	 */
	public static boolean isSlow(float x, float y) {
		int IDtiles = map.getTileId((int) ((x) / 32), (int) ((y) / 32), 4);
		String teleport = map.getTileProperty(IDtiles, "slow", "false");
		if (!teleport.equals("false")) {
			if (teleport.equals("true")) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}
	
	/**
	 * Case de la map où le personnage est accéléré
	 * Avec le logiciel TiledMap => "fast" = "true"
	 * @param x
	 * @param y
	 * @return true si c'est rapide
	 */
	public static boolean isFast(float x, float y) {
		int IDtiles = map.getTileId((int) ((x) / 32), (int) ((y) / 32), 4);
		String teleport = map.getTileProperty(IDtiles, "fast", "false");
		if (!teleport.equals("false")) {
			if (teleport.equals("true")) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}
	
	/**
	 * Case de la map où le personnage est dans le vide
	 * Avec le logiciel TiledMap => "fall" = "true"
	 * @param x
	 * @param y
	 * @return true si c'est du vide
	 */
	public static boolean isFall(float x, float y) {
		int IDtiles = map.getTileId((int) ((x) / 32), (int) ((y) / 32), 4);
		String teleport = map.getTileProperty(IDtiles, "fall", "false");
		if (!teleport.equals("false")) {
			if (teleport.equals("true")) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}
	
	/**
	 * Case de la map où le personnage prend de l'eau
	 * Avec le logiciel TiledMap => "waterRennaissance" = "true"
	 * @param x
	 * @param y
	 * @return true s'il prend de l'eau
	 */
	public static boolean isWater(float x, float y) {
		int IDtiles = map.getTileId((int) ((x) / 32), (int) ((y) / 32), 4);
		String teleport = map.getTileProperty(IDtiles, "waterRennaissance", "false");
		if (!teleport.equals("false")) {
			if (teleport.equals("true")) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}
	
	/**
	 * Case de la map où le personnage purifie l'eau
	 * Avec le logiciel TiledMap => "purification" = "true"
	 * @param x
	 * @param y
	 * @return true si c'est purifié
	 */
	public static boolean isPurification(float x, float y) {
		int IDtiles = map.getTileId((int) ((x) / 32), (int) ((y) / 32), 4);
		String teleport = map.getTileProperty(IDtiles, "purification", "false");
		if (!teleport.equals("false")) {
			if (teleport.equals("true")) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}
	
	/**
	 * Case de la map où le personnage purifie l'eau
	 * Avec le logiciel TiledMap => "purification" = "true"
	 * @param x
	 * @param y
	 * @return true si c'est purifié
	 */
	public static boolean isFood(float x, float y) {
		int IDtiles = map.getTileId((int) ((x) / 32), (int) ((y) / 32), 4);
		String teleport = map.getTileProperty(IDtiles, "food", "false");
		if (!teleport.equals("false")) {
			if (teleport.equals("true")) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}
	
	/**
	 * Collision seulement si les états sont false
	 * 
	 * @param x
	 * @param y
	 * @return true si c'est bloquant
	 */
	public static boolean isBlockedIf(float x, float y) {
		if(x>799) x=799;
		if(y>599) y = 599;
		int IDtiles = map.getTileId((int) ((x) / 32), (int) ((y) / 32), 4);
		String blockedif = map.getTileProperty(IDtiles, "blockedif", "false");
		if (!blockedif.equals("false")) {
			if (!one && blockedif.equals("one")) {
				return true;
			}
			else if (!two && blockedif.equals("two")) {
				return true;
			}
			else if (!three && blockedif.equals("three")) {
				return true;
			}
			else if (!four && blockedif.equals("four")) {
				return true;
			}
			else if (!five && blockedif.equals("five")) {
				return true;
			}
			else if (!spartiate && blockedif.equals("spartiate")) {
				return true;
			}
			else if (!spaceBoss && blockedif.equals("spaceBoss")) {
				return true;
			}
			else if (!Event.torch && blockedif.equals("torch")) {
				return true;
			}
			else if (!Event.notRU && blockedif.equals("notRU")) {
				return true;
			}
			else if (!Event.yesRU && blockedif.equals("yesRU")) {
				return true;
			}
			else if (!Event.tram && blockedif.equals("tram")) {
				return true;
			}
			else if (!Event.tram && blockedif.equals("tram")) {
				return true;
			}
			else if (!Event.notSword && blockedif.equals("notSword")) {
				return true;
			}
			else if (!Event.spaceKey && blockedif.equals("spaceKey")) {
				return true;
			}
			else if (!Event.costume && blockedif.equals("costume")) {
				return true;
			}
			else if (!Event.fontaine && blockedif.equals("fontaine")) {
				return true;
			}
			else if (!Event.finish_loveQuete && blockedif.equals("finishQuete")) {
				return true;
			}
			else if (!Event.boss_futur_explosion && blockedif.equals("bossfutur")) {
				return true;
			}
			else if (!Event.boss_chinois && blockedif.equals("bosschinois")) {
				return true;
			}
			else if (!Event.futur_gate && blockedif.equals("futurgate")) {
				return true;
			}
			else if (!Event.bossRennaissance && blockedif.equals("bossRennaissance")) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}
	
	/**
	 * @return IDx IDX de la map courante
	 */
	public static int getIDx() {
		return IDx;
	}

	/**
	 * @return IDy IDY de la map courante
	 */
	public static int getIDy() {
		return IDy;
	}

	/**
	 * Modifie l'IDX de la map
	 * @param IDx IDX de la map
	 */
	public static void setIDx(int IDx) {
		Map.IDx = IDx;
	}

	/**
	 * Modifie l'IDY de la map
	 * @param IDy IDY de la map
	 */
	public static void setIDy(int IDy) {
		Map.IDy = IDy;
	}
	
	/**
	 * @return map Retourne la map courante
	 */
	public static TiledMap getMap(){
		return map;
	}

	/**
	 * @return isInit Etat de l'initialisation des monstres
	 */
	public static boolean isInit() {
		return isInit;
	}

	/**
	 * Modifie l'initialisation des monstres
	 * 
	 * @param isInit True si les monstres sont deja initialisé
	 */
	public static void setInit(boolean isInit) {
		Map.isInit = isInit;
	}
	
	/**
	 * @return isInitNPC Etat d'inialisation des NPC
	 */
	public static boolean isInitNPC() {
		return isInitNPC;
	}

	/**
	 * Modifie l'initialisation des NPC 
	 * 
	 * @param isInitNPC True si les NPC sont déja initialisé
	 */
	public static void setInitNPC(boolean isInitNPC) {
		Map.isInitNPC = isInitNPC;
	}

	/**
	 * Méthode qui récupère le tableau de collision afin d'utiliser le pathFinding correctement
	 * Retourne true si c'est bloqué
	 * 
	 * @param crx PathFindingContext
	 * @param x Abscisse d'arrivée
	 * @param y Ordonnées d'arrivée
	 */
	@Override
	public boolean blocked(PathFindingContext crx, int x, int y) {
		if(mapTab[x][y]==1)
			return true;
		else return false;
	}

	/**
	 * @return 0 le coût de chaque mouvement
	 */
	@Override
	public float getCost(PathFindingContext crx, int x, int y) {
		return 0;
	}

	/**
	 * @return 19 Nombre de cases en hauteur
	 */
	@Override
	public int getHeightInTiles() {
		return 19;
	}

	/**
	 * @return 25 Nombre de cases en largeur
	 */
	@Override
	public int getWidthInTiles() {
		return 25;
	}

	/**
	 * Méthode permettant de savoir si on est deja passé par ce chemin
	 */
	@Override
	public void pathFinderVisited(int x, int y) {}
	
	/**
	 * @return mapTab Retourne le tableau de collision
	 */
	public static int[][] getMapTab(){
		return mapTab;
	}
	
	/**
	 * @return ready Retourne true c'est prêt
	 */
	public static boolean isReady(){
		return ready;
	}
	
	/**
	 * Modifie l'état de ready
	 * 
	 * @param ready True si c'est prêt
	 */
	public static void setReady(boolean ready){
		Map.ready=ready;
	}
	
	/**
	 * Modifie l'état one
	 * 
	 * @param one True si c'est non bloquant
	 */
	public static void setOne(boolean one){
		Map.one = one;
	}
	
	/**
	 * Modifie l'état two
	 * 
	 * @param two True si c'est non bloquant
	 */
	public static void setTwo(boolean two){
		Map.two = two;
	}
	
	/**
	 * Modifie l'état three
	 * 
	 * @param three True si c'est non bloquant
	 */
	public static void setThree(boolean three){
		Map.three = three;
	}
	
	/**
	 * Modifie l'état four
	 * 
	 * @param four True si c'est non bloquant
	 */
	public static void setFour(boolean four){
		Map.four = four;
	}
	
	/**
	 * Modifie l'état five
	 * 
	 * @param five True si c'est non bloquant
	 */
	public static void setFive(boolean five){
		Map.five = five;
	}
	
	/**
	 * @return one Etat de la case bloquante
	 */
	public static boolean isOne(){
		return one;
	}
	
	/**
	 * @return two Etat de la case bloquante
	 */
	public static boolean isTwo(){
		return two;
	}
	
	/**
	 * @return three Etat de la case bloquante
	 */
	public static boolean isThree(){
		return three;
	}
	
	/**
	 * @return four Etat de la case bloquante
	 */
	public static boolean isFour(){
		return four;
	}
	
	/**
	 * @return four Etat de la case bloquante
	 */
	public static boolean isFive(){
		return five;
	}
	
	/**
	 * @return spartiaet L'event si on ne peut pas passé au boss Préhistoire
	 */
	public static boolean isSpartiate() {
		return spartiate;
	}
	
	/**
	 * Modifie l'état spartiate
	 * 
	 * @param spartiate
	 */
	public static void setSpartiate(boolean spartiate) {
		Map.spartiate = spartiate;
	}
	
	/**
	 * @return spaceBoss true si on a tué le boss de l'espace
	 */
	public static boolean isSpaceBoss() {
		return spaceBoss;
	}

	/**
	 * Modifie l'état de spaceBoss
	 * 
	 * @param spaceBoss
	 */
	public static void setSpaceBoss(boolean spaceBoss) {
		Map.spaceBoss = spaceBoss;
	}
	
	
}
