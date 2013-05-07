package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

/**
 * Classe permettant de gérer les inputs clavier
 * 
 * @author Rémy
 *
 */

public class InputControl {
	
	/** Input du clavier */
	public static Input input;
	/** GameContainer */
	public static GameContainer gc;
	
	
	public InputControl(){
		input = gc.getInput();
	}
	/**
	 * Touche Présée
	 * @param key ID de la touche
	 * @return True si l'a touche est préssée
	 */
	public static boolean inputPressed(int key){
		input = gc.getInput();
		if(input.isKeyPressed(key)){
			input.clearControlPressedRecord();
			return true;
		}
		return false;
	}
	
	/**
	 * Touche Enfoncée
	 * @param key ID de la touche
	 * @return True si l'a touche est enfoncée
	 */
	public static boolean inputDown(int key){
		input = gc.getInput();
		if(input.isKeyDown(key)){
			return true;
		}
		return false;
	}
	
	public static void update(){
		input = gc.getInput();
		input.clearKeyPressedRecord();
	}
}
