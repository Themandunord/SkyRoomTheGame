package snake;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class Board extends BasicGameState
{

	private final int WIDTH = 800;
	private final int HEIGHT = 600;
	private final int DOT_SIZE = 10;
	private final int ALL_DOTS = 900;
	private final int RAND_POS = 59;
	private int x[] = new int[ALL_DOTS];
	private int y[] = new int[ALL_DOTS];
	
	private Input input;
	
	private int dots;
	private int apple_x;
    private int apple_y;
    
	private boolean inGame = false; //avant de jouer, on ne joue pas !
	private boolean instructions =true;
		
	private Image apple; //apple
	private Image dot1;
	private Image dot2;
	private Image banana;
	private Image watermelon;
	private Image instruct;
	
	private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private int cpt;
    private Image gameOver;
    private boolean isEaten=false,banane,pomme,pasteque;
    
    public int score=0;
    
    private int compteurDeFin;
    
	
	

	public Board(int ID)
	{
		
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException 
	{
		dots = 3;
        for (int z = 0; z < dots; z++) 
        {
            x[z] = 200 - z*10;
            y[z] = 200;
        }
        
        locateApple();
        apple = new Image("resSnake/apple.png");  			//pomme
        banana = new Image("resSnake/banana.png");			//banane
        watermelon = new Image("resSnake/watermelon.png");	//pastèque
        dot1 = new Image("resSnake/tir.png"); 				//head
        dot2 = new Image("resSnake/snakeBody.png"); 			//body
        gameOver = new Image("resSnake/gameoverSnake2.png");	//GameOver
        instruct = new Image("resSnake/snakeInstructions.png"); 	//instructions
        
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		
		if(!isEaten && !instructions){
			if(Math.random()<0.4){
				pomme = true;
				pasteque =false;
				banane = false;
			}
			else if(Math.random()<0.7)
			{
				
				pomme = false;
				pasteque =false;
				banane = true;
			}
			else 
			{ 
				
				pomme = false;
				pasteque =true;
				banane = false;
			}
			isEaten =true;
		}
		if(pomme)
			apple.draw(apple_x, apple_y);
		if(banane)
			banana.draw(apple_x, apple_y);
		if(pasteque)
			watermelon.draw(apple_x, apple_y);
			
			
		dot1.draw( x[0], y[0]);
		
			for (int z = 1; z < dots; z++) 	
			{
				dot2.draw(x[z], y[z]); //body
			}
			
			
			if(instructions)
			{
				instruct.draw(0, 0);
			}
			
		if(!inGame && !instructions)
		{
			gameOver.draw(0,0);
			if (score<20)
				g.drawString("Ton score est de "+score+". C'est pas top...",70, 500);
			else if (score<50)
				g.drawString("Ton score est de "+score+". C'est pas mal...",70, 500);
			else
				g.drawString("Ton score est de "+score+" ! Champion !",70, 500);			
		}
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		
		if(compteurDeFin>200){
			this.init(gc, sbg);
			instructions = true;
			compteurDeFin = 0;
		}
		
		input = gc.getInput();
		if(!inGame && instructions)
			start(input);
		if(inGame)
		{
			move(input);
			checkCollision();
			checkApple();
			
		}
		if(!inGame && !instructions){
			if(compteurDeFin<200) compteurDeFin++;
			else{
				sbg.enterState(2);
				sbg.enterState(2, new FadeOutTransition (Color.black),new FadeInTransition(Color.black));
				compteurDeFin++;
			}
		}
	}

	private void start(Input input) 
	{
		if(input.isKeyPressed(Input.KEY_SPACE))
		{
			instructions = false;
			inGame = true;
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 17;
	}
	
	public void locateApple() {
        
		int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));
        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
    }
	public void move(Input input) {
		
        
		if (input.isKeyPressed(Input.KEY_LEFT) && (!right)) {
           
            up = false;
            down = false;
            left = true;
        }

		else if (input.isKeyPressed(Input.KEY_RIGHT) && (!left)) {
           
            up = false;
            down = false;
            right = true;
        }

		else if (input.isKeyPressed(Input.KEY_UP) && (!down)) {
           
            up = true;
            right = false;
            left = false;
        }

		else if (input.isKeyPressed(Input.KEY_DOWN) && (!up)) {
        
            
            down = true;
            right = false;
            left = false;
        }
       
        if(right && cpt==3){
        	
        	cpt=0;
        	for (int z = dots; z > 0; z--) 
    		{
        		x[z] = x[(z - 1)];
                y[z] = y[(z - 1)];
            }
        	x[0] += DOT_SIZE;
        }
        else if(left && cpt==3){
        	
        	cpt=0;
        	for (int z = dots; z > 0; z--) 
    		{
                x[z] = x[(z - 1)];
                y[z] = y[(z - 1)];
            }
        	x[0] -= DOT_SIZE;
        }
        else if(down && cpt==3){
        	
        	cpt=0;
        	for (int z = dots; z > 0; z--) 
    		{
                x[z] = x[(z - 1)];
                y[z] = y[(z - 1)];
            }
        	y[0] += DOT_SIZE;
        }
        else if(up && cpt==3){
        	
        	cpt=0;
        	for (int z = dots; z > 0; z--) 
    		{
                x[z] = x[(z - 1)];
                y[z] = y[(z - 1)];
            }
        	y[0] -= DOT_SIZE;
        	
        }
        else cpt++;
    }
	
	public void checkCollision() 
	{

        for (int z = dots; z > 3; z--) 
        {

            if ((z > 3) && (x[0] == x[z]) && (y[0] == y[z])) 
            {
                inGame = false;
            }
        }

      if (y[0] > HEIGHT) {
          inGame = false;
      }

      if (y[0] < 0) {
          inGame = false;
      }

      if (x[0] > WIDTH) {
          inGame = false;
      }

      if (x[0] < 0) {
          inGame = false;
      }
          	  
  }
	
	public void checkApple() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            dots++;
            locateApple();
            isEaten=false;
            score++;
            
        }
    }
	
}
