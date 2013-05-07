package game;

import java.util.TreeMap;

import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import event.Event;

/**
 * Classe gérant le son du jeu 
 * 
 * @author Rémy
 *
 */

public class GameSound {

	/** Musique des différents mondes */
	private static Music menuTheme,monde1Theme,startTheme,MJ,uhaTheme,transition,skyroom,renaissance2,futur,bonus,spartaland,espace,futur1,espace2;
	/** Musique courrante */
	private static Music current_music;
	/** Volume du jeu */
	private static int volume = 10;
	/** Recupère les Inputs au clavier */
	private static Input input;
	/** Permet de lancer une musique pendant un certain temps */
	private static int compteur = 0;
	/** Volume lors des changements */
	private static int vol=100;
	public static int music;
	private static TreeMap<Integer,Music> tree;

	/**
	 * Initialisation des musiques et sons 
	 * 
	 * @throws SlickException
	 */
	public void init () throws SlickException{
	
		tree = new TreeMap<Integer,Music>();
		menuTheme = new Music("res/music/menuTheme.ogg",true);
		monde1Theme = new Music("res/music/monde1Theme.ogg",true);
		startTheme = new Music("res/music/StartTheme.ogg",true);
		MJ = new Music("res/music/MJ.ogg",true);
		uhaTheme = new Music("res/music/uhaTheme.ogg",true);
		transition = new Music("res/music/transition.OGG",true);
		skyroom = new Music("res/music/skyroom.ogg",true);
		renaissance2 = new Music("res/music/renaissance2.OGG",true);
		futur = new Music("res/music/futur.OGG",true);
		bonus = new Music("res/music/bonus.OGG",true);
		spartaland = new Music("res/music/spartaland.OGG",true);
		espace = new Music("res/music/espace.OGG",true);
		futur1 = new Music("res/music/futur1.ogg",true);
		espace2 = new Music("res/music/espace2.ogg",true);
		current_music = menuTheme;
		music = 0;
		
		tree.put(1,startTheme);
		tree.put(2,monde1Theme);
		tree.put(3,uhaTheme);
		tree.put(4,transition);
		tree.put(5,skyroom);
		tree.put(6,renaissance2);
		tree.put(7,futur1);
		tree.put(8,bonus);
		tree.put(9,spartaland);
		tree.put(10, espace);
		tree.put(11, futur);
		tree.put(12, espace2);
	}
	
	/**
	 * Méthode permettant de changer de musique en fonction des maps
	 * 
	 */
	public static void updateMusic(){		
		if(whereMusic(0, 0)){ isPlaying(startTheme); music = 1; }
		else if(whereMusic(0,1)) { isPlaying(monde1Theme); music = 2; }
		else if(whereMusic(7,7)){
			if(!MJ.playing() && compteur==0){
				MJ.stop();
				MJ.play();
			}
			else compteur++;
			if(compteur==320){  isPlaying(uhaTheme); music = 3 ; }
		}
		else if(whereMusic(4,9) || whereMusic(7,8)) { isPlaying(uhaTheme); music = 3; }
		else if(whereMusic(3, 3)){ isPlaying(transition); music = 4;}
		else if(whereMusic(10, 11)){ isPlaying(skyroom); music = 5;}
		else if(whereMusic(11, 5)){ isPlaying(renaissance2); music = 6;}
		else if(whereMusic(3, 8)){ isPlaying(futur1); music = 7;}
		else if(whereMusic(10, 10)){ isPlaying(bonus); music = 8;}
		else if(whereMusic(8, 8)){ isPlaying(spartaland); music = 9;}
		else if(whereMusic(6, 0)){ isPlaying(espace); music = 10;}
		else if(whereMusic(1, 10)){ isPlaying(futur); music = 11;}
		else if(whereMusic(8, 1)){ isPlaying(espace2); music = 12;}
		
		
	}
	
	/**
	 * Change le musique courrante et adapte le volume
	 * 
	 * @param music Musique que l'on veut mettre
	 */
	public static void isPlaying(Music music){
		current_music = music;      // Permet le changement de music, permet de gerer la boucle update
		if(current_music!=null && !current_music.playing()){
			current_music.stop();
			current_music.loop();
			current_music.setVolume(Event.volume);
		}
	}
	
	/**
	 * Permet de savoir si on se trouve sur la bonne map
	 * 
	 * @param IDx IDX de la map
	 * @param IDy IDy de la map
	 * @return true si on est sur la map
	 */
	public static boolean whereMusic(int IDx, int IDy){	
		if(IDx==Map.getIDx() &&	IDy==Map.getIDy()){
			return true;
		}
		return false;
	}
	
	/**
	 * On redéfinie la méthode setVolume() afin 
	 * changer le volume du jeu
	 */
	public static void setVolume() {
		if(volume > 100) {
			volume = 100;
		} else if(volume < 0) {
			volume = 0;
		}
		Launch.container.setMusicVolume(volume / 100.0f);
	}

	/**
	 * Augmente ou baisse le volume 
	 * Touche M => Moins
	 * Touche P => Plus
	 * 
	 * @param gc GameContainer
	 */
	public static void updateVolume(GameContainer gc){
		input = gc.getInput();
		
		if (input.isKeyDown(Input.KEY_M)) {
			vol = (int) (current_music.getVolume() * 100);
			vol --;
			if (vol < 0) vol = 0;
			current_music.setVolume(vol/100.0f);
			Event.volume = vol/100.0f;
		}
		if (input.isKeyDown(Input.KEY_P)) {
			vol = (int) (current_music.getVolume() * 100);
			vol ++;
			if (vol > 100) vol = 100;
			current_music.setVolume(vol/100.0f);
			Event.volume = vol/100.0f;
		}
	}
	
	/**
	 * @return musique courante
	 */
	public static Music getMusic(){
		return current_music;
	}
	/**
	 * Modifie la musique courante
	 * 
	 * @param current_music
	 */
	public void setMusic(Music current_music){
		GameSound.current_music = current_music;
	}
	/**
	 * Modifie le compteur
	 * @param compteur Entier permettant le mise en place d'un son durant une durée définie 
	 */
	public static void setCompteur(int compteur)
	{
		GameSound.compteur = compteur;
	}
	/**
	 * @return le volume du jeu
	 */
	public static int getVolume(){
		return volume;
	}
	public static TreeMap<Integer,Music> getTree(){
		return tree;
	}
}
