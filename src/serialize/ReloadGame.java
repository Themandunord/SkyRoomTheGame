package serialize;

import org.newdawn.slick.Image;

import items.Item;
import game.GameSound;
import game.Hud;
import map.Map;
import player.Player;


/*
 * Permet de relancer le jeu avec les valeurs par défaut
 * NOUVEAU JEU
 */
public class ReloadGame {
	
	public ReloadGame(){
		
		Player.setX(393f);
		Player.setY(380f);
		Map.setIDx(0);
		Map.setIDy(0);
		Hud.setNbrHeart(6);
		Item.setLEMON(0);
		Item.setAPPLE(0);
		GameSound.setCompteur(0);
	}
}
