package monster;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Classe permettant de mettre des montres sur les maps
 * Ici , on utilise l'introspection
 * Ex : AddMonstere(5, Dino.class, 5, 2);
 * 
 * @author Rémy
 *
 */

public class AddMonster {

	/** Liste de Monstre => Donc tour ce qui hérite de Monster */
	private List<Monster> monsters = new ArrayList<Monster>(); 
	/** Permet de savoir s'il y a des monstres sur la map */
	public static boolean isMonster = true;
	private int IDx, IDy;
	private int nbrMonster;
	
	/**
	 * Méthode permettant d'initialiser les monstres sur la map
	 * 
	 * @param nbrMonster Nombre de monstres sur la map
	 * @param type Type du monstre sur la map ( Utilisation des .class )
	 * @param IDx IDX de la map
	 * @param IDy IDY de la map
	 * @throws SlickException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public void AddMonstere(int nbrMonster, Class<? extends Monster> type, int IDx , int IDy) throws SlickException, InstantiationException, IllegalAccessException{
		if(Map.isMap(IDx, IDy)){
			this.nbrMonster = nbrMonster;
			this.IDx = IDx;
			this.IDy = IDy;
			if(  type.isInterface() || Modifier.isAbstract(type.getModifiers()) ) {
				System.err.println("Impossible d'ajouter la classe " + type.getName());  // Exception permettant d'éviter une erreur si on charge un mauvais monstre
			} else {
				monsters.clear();  // Vide la liste
				isMonster = true;
				for(int i=0;i<nbrMonster;i++) {
					monsters.add(type.newInstance());  // Crée une nouvelle instance de la classe appelée
				}
			}
    
			for(Monster mo:monsters){
				mo.init();
				Map.setInit(true); // permet de dire que le monstre est initialiser ==> Gere la boucle update
			}
		}
		
		}
	
	/**
	 * Boucle d'update des monstres
	 * 
	 * @param gc GameContainer
	 * @param delta Permet de gérer la vitesse
	 * @throws SlickException
	 */
	public void update(GameContainer gc, int delta) throws SlickException{
		if(Map.isMap(this.IDx, this.IDy)){
			for(Monster mo:monsters){
				mo.update(gc, delta);
			}
		}
	}

	/**
	 * Boucle de rendu des monstres  l'écran 
	 * 
	 * @param g Sortie à l'écran
	 * @throws SlickException
	 */
	public void render(Graphics g) throws SlickException{
		if(Map.isMap(this.IDx, this.IDy)){
			for(Monster mo:monsters){
				mo.renderBot(g);
			}
		}
	}
	
	/**
	 * Permet d'afficher des éléments au dessus du perso
	 * 
	 * @param g Graphics
	 */
	public void renderUp(Graphics g){
		if(Map.isMap(this.IDx, this.IDy)){
			for(Monster mo:monsters){
				mo.renderUp(g);
			}
		}
	}

	/**
	 * @return monsters Liste des monstres sur la map
	 */
	public List<Monster> getMonsterList() {
		return monsters;
	}

	/**
	 * Modifie la liste de monstres
	 * 
	 * @param monsters Liste de monstres
	 */
	public void setMonsterList(List<Monster> monsters) {
		this.monsters = monsters;
	}
	
	public int getNb(){
		return this.nbrMonster;
	}
}
