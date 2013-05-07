package spaceInvader;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Enemy {

	private Image enemy;
	private int X,Y;
	private Rectangle rec;
	private boolean alive;
	private List<Bullet> bullets;
	private int cpt;
	private int collision,colision; 
	private long collisionTimer;
	private boolean life = true;
	public Enemy(int X, int Y) throws SlickException{
		enemy = new Image("data/ennemy.png");
		this.X = X;
		this.Y = Y;
		this.alive = true;
		rec = new Rectangle(X,Y,30,30);
		bullets = new ArrayList<Bullet>();
	}
	
	public void render(){
		if(alive)
			enemy.draw(X, Y);
		for(Bullet bl:bullets){
			bl.render();
		}
	}
	
	public void update(int delta) throws SlickException{
		if(alive){
			rec.setBounds(X,Y,30,30);
			if(cpt==240){
				if(Math.random()<0.1)
					bullets.add(new Bullet(X+16,Y+30));
				cpt=0;
			}else cpt++;
		}
		else rec.setBounds(-100,-100,0,0);
		
		for(Bullet bl:bullets){
			bl.updateEnemy(delta);
			if(SpaceI.getRecPlayer().intersects(bl.getRect())){
				if (collision == 1) {
					collisionTimer = System.currentTimeMillis();  // permet de donner 2 secondes au joueur avant de se faire retirer a nouveau une vie
					collision = 0;
					
					SpaceI.setLife(SpaceI.getLife()-1);
					bl.setDestroy(true);
					SpaceI.hitEnemy = true;
				}
				else if (collision == 0) {
					SpaceI.hitEnemy =false;
					if (collisionTimer + 2000 < System.currentTimeMillis()) 
					{
						collision = 1;
					}
					
				}
						
			}
			
		}
	}
	
	public Rectangle getRect(){
		return rec;
	}
	public void setAlive(boolean alive){
		this.alive = alive;
	}
	public void setX(int X){
		this.X = X;
	}
	public void setY(int Y){
		this.Y = Y;
	}
	public int getX(){
		return X;
	}
	public int getY(){
		return Y;
	}
	public boolean getLife(){
		return life;
	}
	public void setLife(boolean life){
		this.life = life;
	}
}
