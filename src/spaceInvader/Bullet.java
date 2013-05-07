package spaceInvader;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Bullet {

	private Image bullet;
	private int x,y = 500;
	private Rectangle rec;
	private boolean destroy;
	
	public Bullet(int x) throws SlickException{
		bullet = new Image("data/bullet.png");
		this.x = x;
		rec = new Rectangle(x,y,5,5);
		this.destroy = false;
	}
	
	public Bullet(int x,int y) throws SlickException{
		bullet = new Image("data/bullet.png");
		this.x = x;
		this.y = y;
		rec = new Rectangle(x,y,5,5);
		this.destroy = false;
	}
	
	public void update(int delta){
		if(!destroy){
			y-=0.15*delta;
			rec.setBounds(x,y,5,5);
		}
		else rec.setBounds(-100,-100,0,0);
	}
	
	public void updateEnemy(int delta){
		if(!destroy){
			y+=0.15*delta;
			rec.setBounds(x,y,5,5);
		}
		else rec.setBounds(-100,-100,0,0);
	}
	
	public void render(){
		if(!destroy)
			bullet.draw(x, y);
	}
	
	public Rectangle getRect(){
		return rec;
	}
	public void setDestroy(boolean destroy){
		this.destroy = destroy;
	}
}
