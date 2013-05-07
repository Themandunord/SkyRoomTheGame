package spaceInvader;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class SpaceI extends BasicGameState{

	private Image ship;
	private Input input;
	private int xShip = 395;
	private List<Bullet> bullets;
	private List<Enemy> enemies;
	private int cpt,cptX,cptBullet;
	private static Rectangle rec,rectPlayer;
	private boolean gameOver;
	private static int life=3;
	public static int nbrEnemy;
	public static boolean hitEnemy;
	private boolean finish;
	private int collision,colision;
	private long collisionTimer;
	private int score;
	private int cptGameOver, cptWin;
	private boolean restart;
	
	public SpaceI(int game) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		ship = new Image("data/ship.png");
		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		rec = new Rectangle(0,450,800,10);
		rectPlayer = new Rectangle(xShip,500,30,30);
		for(int i = 0; i<11 ; i++){
			for(int j = 0; j<5 ; j++){
				enemies.add(new Enemy(64+i*40,96 + j*32));
			}
			
		}
		this.cptX = 0;
		this.cpt =0;
		this.cptBullet = 0;
		this.finish = false;
		this.gameOver = false;
		this.score = 0;
		this.nbrEnemy = 0;
		this.xShip = 395;	
		this.life = 3;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		for(Bullet bl:bullets){
			bl.render();
		}
		for(Enemy en:enemies){
			en.render();
		}
		ship.draw(xShip, 500);
		
		if(gameOver){
			g.drawString("GameOver", 380, 300);
		}
		g.drawString("Life : "+life, 700, 50);
		g.drawString("Score : "+score, 700, 70);
		
		if(score>54){
			finish = true;
			g.drawString("You Win", 380, 300);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		input = gc.getInput();
		if(!finish){
		rectPlayer.setBounds(xShip,500,30,30);
		if(input.isKeyDown(Input.KEY_RIGHT)){
			if(xShip<750)
				xShip+=0.15f*delta;
		}
		else if(input.isKeyDown(Input.KEY_LEFT)){
			if(xShip>50)
				xShip-=0.15f*delta;
		}
		
		
		if(input.isKeyPressed(Input.KEY_SPACE)){
			if(cptBullet>40){
				bullets.add(new Bullet(xShip+16));
				cptBullet=0;
			}
			
		}
		cptBullet++;
		
		for(Bullet bl:bullets){
			bl.update(delta);
		}
		for(Enemy en:enemies){
			en.update(delta);
		}
		
		if(!gameOver){
		if(cpt==80){
			if(cptX<6){
				for(Enemy en:enemies){
					en.setX(en.getX() + 32);
				}
			}
			
			
			if(cptX==6 || cptX==12){
				for(Enemy en:enemies){
					en.setY(en.getY() + 32);
				}
				if(cptX==12){
					cptX = 0;
				}
			}
			
			if(cptX>6){
				for(Enemy en:enemies){
					en.setX(en.getX() - 32);
				}
			}
			
			cptX++;
			cpt=0;
		}
		else cpt++;
	
		
		for(Bullet bl:bullets){
			for(Enemy en:enemies){
				if(bl.getRect().intersects(en.getRect())){
					en.setAlive(false);
					bl.setDestroy(true);
					en.setLife(false);
					SpaceI.hitEnemy = true;
				}
			}
		}
		for(Enemy en:enemies){
			if(rec.intersects(en.getRect())){
				this.gameOver = true;
			}
		}
		}
		
		if(life<=0){
			gameOver = true;
			life = 0;
		}
		}
		
		if(hitEnemy){
			if (collision == 1) {
				collisionTimer = System.currentTimeMillis();  // permet de donner 2 secondes au joueur avant de se faire retirer a nouveau une vie
				collision = 0;
				nbrEnemy++;
			}
			else if (collision == 0) {
				if (collisionTimer + 200 < System.currentTimeMillis()) 
				{
					collision = 1;
				}
			}
		}
		score = 0;
		for(int i =0; i<enemies.size();i++){
			if(!enemies.get(i).getLife())
				score++;
		}
		
		if(gameOver){
			if(cptGameOver<200) cptGameOver++;
			else{
				sbg.enterState(2);
				sbg.enterState(2, new FadeOutTransition (Color.black),new FadeInTransition(Color.black));
				cptGameOver = 0;
				restart = true;
			}
		}
		if(finish){
			if(cptWin<200) cptWin++;
			else{
				sbg.enterState(2);
				sbg.enterState(2, new FadeOutTransition (Color.black),new FadeInTransition(Color.black));
				cptWin = 0;
				restart = true;
			}
		}
		if(restart){
			this.init(gc, sbg);
			restart = false;
		}
		
		
	}

	@Override
	public int getID() {
		return 21;
	}
	
	public static Rectangle getRec(){
		return rec;
	}
	public static Rectangle getRecPlayer(){
		return rectPlayer;
	}
	public static void setLife(int life){
		SpaceI.life = life;
	}

	public static int getLife() {
		return life;
	}
	
}