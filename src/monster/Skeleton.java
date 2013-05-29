package monster;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import player.Player;
import player.Sword;
import event.Event;
import game.Menu;

public class Skeleton extends Monster{

	static int NB_LIFE =1000;
	public Skeleton(){
		super(0.05f,1000);
	}
	
	public void init() throws SlickException{
		super.init();
		this.MonsterAnim("skeleton", 40, 40);
	}
	
	public void update(GameContainer gc, int delta) throws SlickException{
		super.update(gc, delta);
		
		if(alive){
			rectMonster.setBounds(xM+5,yM+2,38,38);
			if(rectMonster.intersects(Player.getRect())){
				Event.colision = true;
			}
		
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

	public void setAlive(boolean b) {
		// TODO Auto-generated method stub
		this.alive = b;
	}
	public void setLife(int b){
		this.life = b;
	}
	
}
