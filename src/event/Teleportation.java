package event;

import player.Player;
import map.Map;

/**
 * Classe permettant la téléportation du personnage
 * Sur la Map , il suffit de remplir la map avec un "teleport" = "true"
 * avec le logiciel tileMap
 * 
 * @author Rémy
 */

public class Teleportation {
	
	/**
	 * Méthode permettant la téléportation du personnage
	 * @param IDxD IDx de départ
	 * @param IDyD IDy de départ
	 * @param IDxA IDx d'arrivée
	 * @param IDyA IDy d'arrivée
	 * @param x Abscisse d'arrivée
	 * @param y Ordonnée d'arrivée
	 */
	public void teleport(int IDxD , int IDyD , int IDxA, int IDyA, float x, float y){
		if(Map.isMap(IDxD, IDyD)){
			if(Map.isTeleport(Player.getX()+16,Player.getY()+16)){
				Map.setIDx(IDxA);
				Map.setIDy(IDyA);
				Map.setInitNPC(false);
				Map.setInit(false);
				Player.setX(x);
				Player.setY(y);
			}
		}
	}
}
