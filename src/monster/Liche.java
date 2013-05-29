package monster;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

import player.Player;
import player.Sword;
import event.Event;
import game.Menu;
	
/**
 * Classe créant des Liches
 * 
 * 
 * @author Amaël
 *
 */
public class Liche extends Monster{

	private List<Skeleton> skeletons;
	private List<FireBall> fireballs;
	private Animation anim;
	private boolean readyAnim = true;
	private int cpt,cptDead;
	private int nbSkl;	//Le nombre de squelette que peut avoir une liche
	private boolean skeleton = true;
	
	/**
	 * Constructeur par défaut
	 * On donne une vitesse de 0.05 aux Liches
	 */
	public Liche(){
		super(0f,30000);
		nbSkl=3;
		skeletons = new ArrayList<Skeleton>();
		fireballs = new ArrayList<FireBall>();
		skeletons.add(new Skeleton());
		skeletons.add(new Skeleton());
		skeletons.add(new Skeleton());
		try {
			anim = new Animation(new SpriteSheet("res/monster/liche-anim.png",64,64),0,0,7,0,true,100,true);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * On initialise un sprite aléatoirement
	 */
	public void init() throws SlickException{
		super.init();
		this.MonsterAnim("liche",64,64);
		this.xM = 650;
		this.yM = 280;
		for(Skeleton sk:skeletons){
			sk.init();
			sk.MonsterAnim("skeleton", 40, 40);
		}
		
	}
	
	public void update(GameContainer gc, int delta) throws SlickException{
		super.update(gc, delta);
		actions(gc,delta);
		if(alive){
			rectMonster.setBounds(xM+5,yM+2,60,62);
			if(rectMonster.intersects(Player.getRect())){
				Event.colision = true;
			}
		
			if(!skeleton){
				if(rectMonster.intersects(Sword.getRect()) && Sword.isHere){
					if(!Menu.hit.playing()){
						Menu.hit.play(1, Event.volume);
					}
					if(this.life < 0)
						alive = false;
					else{
						isHit =true;
						this.life-=100;
					}
				}
			}
		}
	}
	
	/**
	 * Les actions de la liche sont lancer une boule de feu et invoquer un squelette.
	 */
	
	public void actions(GameContainer gc , int delta) {
		//Il faut un timer pour cadancer les actions.
		
		if(alive) {					//Rester dans la boucle tant que la liche est en vie.
									// <- utiliser le timer
			throwFireball(delta);
			
			nbSkl = 0;
			for(Skeleton sk:skeletons){
				if(sk.getAlive())
					nbSkl++;
			}
			
			if(nbSkl == 0){
				skeleton = false;
				if(cptDead<300) cptDead++;
				else{
					skeleton = true;
					readyAnim = true;
					cptDead = 0;
					for(Skeleton sk:skeletons){
						sk.setAlive(true);
						sk.setLife(Skeleton.NB_LIFE);
						
					}
				}
					
			}
			
			if(skeleton){
				for(Skeleton sk:skeletons){
					try {
						sk.update(gc, delta);
					} catch (SlickException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		
	}

	/**
	 * Action lancer une boule de feu.
	 */
	
	private void throwFireball(int delta) {
		
		if(alive && !Event.cinematic && skeleton){
			if(left && compteur == 0){
				fireballs.add(new FireBall("licheAttack",xM+16, yM+16,3));
			}
			if(right && compteur == 0){
				fireballs.add(new FireBall("licheAttack",xM+16, yM+16,2));
			}
			if(down && compteur == 0){
				fireballs.add(new FireBall("licheAttack",xM+16, yM+16,1));
			}
			if(up && compteur == 0){
				fireballs.add(new FireBall("licheAttack",xM+16, yM+16,0));
			}
			compteur++;
		}
		if(compteur > 80) compteur = 0;	
		
		for(FireBall so:fireballs){
			so.updateDiag(delta);
			if(so.getCircle().intersects(Player.getRect())){
				Event.colision = true;
				so.setShoot(false);
			}
		}

	}
	
	public void renderBot(Graphics g) throws SlickException{
		if(!readyAnim)
			super.renderBot(g);
		if(alive){
			if(readyAnim){
				anim.start();
				if(cpt<120){
					anim.draw(xM, yM);
					cpt++;
				}
				else{
					cpt = 0;
					readyAnim = false;
					anim.stop();
				}
			}
			
			for (FireBall so : fireballs) {
				so.setShoot(true);
				so.render();
			}
			
			if(skeleton){
				for(Skeleton sk:skeletons){
					sk.renderBot(g);
				}
			}
		}
	}
}