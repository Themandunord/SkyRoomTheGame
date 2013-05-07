package serialize;

import event.Event;
import game.Hud;

import java.util.Scanner;

import org.newdawn.slick.SlickException;

import map.Map;

public class Cheat {

	private Scanner sc;
	private String str;
	private boolean ready;
	
	public Cheat(){
		str = "";
		sc = new Scanner(System.in);
		str = sc.nextLine();
		ready = true;
	}
	
	/**
	 * 
	 */
	public void update(){
		if(ready){
		if(str.equals("heart")){ 
			str = sc.nextLine();
			Hud.setNbrHeart(Integer.valueOf(str)); 
		}
		else if(str.equals("map")){
			String X = sc.nextLine();
			String Y = sc.nextLine();
			Map.setInitNPC(false);
			Map.setIDx(Integer.valueOf(X));
			Map.setIDy(Integer.valueOf(Y));
			Map.setInitNPC(false);
			
		}
		else if(str.equals("epee")){
			Event.SWORD = true;
		}
		else if(str.equals("eau")){
			Event.WaterGun = true;
		}
		else if(str.equals("futur_gate")){
			Event.futur_gate = true;
		}
		else System.out.println("Ce Cheat Code n'existe pas");
		}
		ready = false;
	}
}
