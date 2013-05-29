package items;

import game.Game;
import game.InputControl;
import map.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import player.Player;

public class Arcade {

	private Image arcade,dialbox;
	private int sbgID;
	private Rectangle rec;
	private int x,y,IDx,IDy;
	private String text;
	private Input input;
	
	public Arcade(String name, int sbgID,int x, int y, int IDx, int IDy,String text){
		this.sbgID = sbgID;
		rec = new Rectangle(x+5,y+5,55,75);
		this.x = x;
		this.y = y;
		this.IDx = IDx;
		this.IDy = IDy;
		this.text = text;
		try {
			this.dialbox = new Image("res/all/dialboxEvent.png");
			this.arcade = new Image("res/arcade/arcade-"+name+".png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void renderUpdating(StateBasedGame sbg, GameContainer gc){
		input = gc.getInput();
		if(Map.isMap(IDx, IDy)){
			arcade.draw(x, y);
			if(rec.intersects(Player.getRect())){
				dialbox.draw(195,470);
				Game.uFont.drawString(210,475,""+text);
				if(InputControl.inputPressed(Input.KEY_RALT) || input.isControlPressed(7)){
					sbg.enterState(sbgID);
					sbg.enterState(sbgID, new FadeOutTransition (Color.black),new FadeInTransition(Color.black));
				}
			}
		}
	}
}
