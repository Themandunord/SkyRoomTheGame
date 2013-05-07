package particle;

import java.io.IOException;

import map.Map;

import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

/**
 * Classe permettant la mise en place de particule dans le jeu
 * Il suffit de définir un nom
 * 
 * @author Rémy
 *
 */

public class Particle {

	/** Particle */
	private ParticleSystem particle;
	private boolean isReady;
	
	/**
	 * Initialisation de la particule
	 * 
	 * @param name Nom de la particule
	 */
	public void init(String name)
	{
		try {
			particle = ParticleIO.loadConfiguredSystem("res/particle/"+name+".xml");
			this.isReady = true;

		} catch (IOException e) {
	
			e.printStackTrace();
		}
	}
	
	/**
	 * Boucle d'update permettant de modifier les valeurs 
	 * de la particule et de lui donner un mouvement
	 * 	
	 * @param delta
	 */
	public void update(int delta){
		if(isReady)
			particle.update(delta);
	}
	
	/**
	 * Permet d'afficher la particule Stargate
	 * 
	 * @param IDx IDX de la map
	 * @param IDy IDY de la map
	 */
	public void renderStargate(int IDx, int IDy){
		if(Map.isMap(IDx, IDy)){
			particle.render(300, 400);
		}
		
	}
	
	/**
	 * Permet d'afficher la particule Hole
	 * 
	 * @param IDx IDX de la map
	 * @param IDy IDY de la map
	 */
	public void renderHole(int IDx, int IDy){
		if(Map.isMap(IDx, IDy)){
			particle.render(335, 340);
		}
	}
	
	/**
	 * Permet d'afficher la particule Star
	 * 
	 * @param IDX IDX de la map
	 * @param IDY IDY de la map
	 */
	public void renderStar(int[] IDX, int[] IDY){
		for(int i = 0; i<IDX.length; i++){
			if(Map.isMap(IDX[i], IDY[i])){
				particle.render(400, 300);
			}
				
		}
	}
	
	/**
	 * Start de l'update
	 * 
	 * @param IDX
	 * @param IDY
	 */
	public void startUpdate(int IDX, int IDY){
		if(Map.isMap(IDX, IDY)){
			if(!isReady)
				isReady = true;
		}
	}
	
	/**
	 * Stop de l'update 
	 * 
	 * @param IDX
	 * @param IDY
	 */
	public void stopUpdate(int IDX, int IDY){
		if(Map.isMap(IDX, IDY)){
			if(isReady)
				isReady = false;
		}
	}
	
	/**
	 * Permet d'afficher une particule
	 * 
	 * @param x IDX de la map
	 * @param y IDY de la map
	 */
	public void render(float x, float y){
		
		particle.render(x, y);
	}
	
	/**
	 * @return true si la particule est prete
	 */
	public boolean getReady(){
		return isReady;
	}
	
	/**
	 * Modification de isReady
	 * 
	 * @param isReady
	 */
	public void setReady( boolean isReady){
		this.isReady = isReady;
	}
}
