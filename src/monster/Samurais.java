package monster;

import event.Event;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import player.Player;

/**
 * Classe créant des Samouraïs
 * 
 * @author Stanislas
 *
 */

public class Samurais  extends Monster{
	
	private Strike strike;
	private static int direction;
	
	public Samurais(){
		super(0.05f,300);
	}
	
	public void init() throws SlickException{
		super.init();
		
		double var = Math.random();
		
		strike = new Strike();
		
		if(var<0.17)
			super.MonsterAnim("masamune1",32,32);
		else if(var>0.17 && var<0.33)
			super.MonsterAnim("masamune2",32,32);
		else if(var>0.33 && var<0.5)
			super.MonsterAnim("sam_red",32,32);
		else if(var>0.5 && var<0.66)
			super.MonsterAnim("samourai1",32,32);
		else if(var>0.66 && var<0.83)
			super.MonsterAnim("tachibana_bleu",32,32);
		else
			super.MonsterAnim("tachibana_rouge",32,32);
	}
	
	public void renderBot(Graphics g) throws SlickException{
		if(alive){
			if(up)
				strike.renderUp(g, xM+8, yM-10);
		}
		super.renderBot(g);
		renderStrike(g);
	}
	
	public void renderStrike(Graphics g){
		if(alive){
			if(up)
				strike.UpdateUp(xM+8, yM-10);
			else if(down)
				strike.renderDown(g, xM, yM+15);
			else if(right)
				strike.renderRight(g, xM+15, yM);
			else if(left)
				strike.renderLeft(g, xM-19, yM);	
		}
	}
	
	public void update(GameContainer gc, int delta) throws SlickException{
		super.update(gc, delta);
		if(strike.getRect().intersects(Player.getRect())){
			Event.colision = true;
		}
		if(!alive){
			strike.getRect().setBounds(-100, -100, 0, 0);
		}
	}

}
