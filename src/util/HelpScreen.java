package util;

import map.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import event.Event;
import game.Game;
import game.InputControl;

public class HelpScreen {
	private Image img;
	private boolean isVisible,isLoad,isShow;
	
	public HelpScreen(String name){
		try {
			this.img = new Image("res/all/helpscreen"+name+".png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void render(int IDX, int IDY){
		if(Map.isMap(IDX, IDY) && !isShow){
			if(!isLoad){
				isVisible = true;
				isLoad = true;
			}
			if(isVisible){
				Event.helpScreen = true;
			}
			
			if(InputControl.inputPressed(Input.KEY_ENTER)){
				isVisible = false;
				Event.helpScreen = false;
				isShow = false;
			}
			if(isVisible){
				img.draw(150, 175);
			}
			
		}else{
			isLoad = false;
		}
	}
}
