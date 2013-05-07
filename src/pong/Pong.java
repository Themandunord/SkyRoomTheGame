package pong;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Pong extends BasicGameState {

	static int width = 800;
	static int height = 600;
	static boolean fullscreen = false;
	static String title = "Mongo";
	static int fpsLimit = 40;
	Circle ball;
	Rectangle paddlePlayer;
	Rectangle paddleCPU;
	Vector2f ballVelocity;
	int scorePlayer;
	int scoreCPU;
	boolean gameOver;
	boolean restart;
	int cpt;
	
	public Pong(int game) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame arg1)
			throws SlickException {
		gc.getInput().enableKeyRepeat();
		paddlePlayer = new RoundedRectangle(5, height / 2, 10, 80, 3);
		paddleCPU = new RoundedRectangle(width - 15, height / 2, 10, 80, 3);
		ball = new Circle(width / 2, height / 2, 6);
		ballVelocity = new Vector2f(-10, 10);
		scorePlayer = 0;
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.fill(paddlePlayer);
		g.fill(paddleCPU);
		g.fill(ball);
		g.drawString("Score : "+scorePlayer, 700, 50);
		if(gameOver){
			g.drawString("Game Over !",	360	, 300);
		}
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		if (gc.getInput().isKeyDown(Input.KEY_UP)) {
			if (paddlePlayer.getMinY() > 0)
				paddlePlayer.setY(paddlePlayer.getY() - 10.0f);
		} else if (gc.getInput().isKeyDown(Input.KEY_DOWN)) {
			if (paddlePlayer.getMaxY() < height)
				paddlePlayer.setY(paddlePlayer.getY() + 10.0f);
		}

		ball.setLocation(ball.getX() + ballVelocity.getX(), ball.getY()
				+ ballVelocity.getY());

		if (ball.getMinX() <= 0) {
			ballVelocity.x = -ballVelocity.getX();
			scoreCPU++;
			gameOver = true;
		}
		if (ball.getMaxX() >= width) {
			ballVelocity.x = -ballVelocity.getX();
			scorePlayer++;
		}

		if (ball.getMinY() <= 0)
			ballVelocity.y = -ballVelocity.getY();
		if (ball.getMaxY() >= height)
			ballVelocity.y = -ballVelocity.getY();

		if (ball.intersects(paddlePlayer) || ball.intersects(paddleCPU)) {
			ballVelocity.x = -ballVelocity.getX();
		}
		if (ball.intersects(paddleCPU)) {
			scorePlayer++;
		}

		float ypos = ball.getCenterY() - paddleCPU.getHeight() / 2;
		paddleCPU.setY(ypos);
		
		if(gameOver){
			if(cpt < 200 ) cpt++;
			else{
				cpt = 0;
				restart = true;
				gameOver = false;
				sbg.enterState(2);
				sbg.enterState(2, new FadeOutTransition (Color.black),new FadeInTransition(Color.black));
			}
		}
		if(restart){
			this.init(gc, sbg);
			restart = false;
		}

	}

	@Override
	public int getID() {
		return 22;
	}

}
