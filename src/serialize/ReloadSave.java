package serialize;

import items.Item;

import java.util.Scanner;

import event.Event;
import game.GameSound;
import game.Hud;
import game.InputControl;
import game.Launch;
import game.Menu;
import game.PauseState;
import map.Map;
import player.Player;

import org.jasypt.util.text.BasicTextEncryptor;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.EmptyTransition;

/*
 * Chargement et sauvegarde dans le XML en fonction des input
 */

public class ReloadSave {

	private boolean isCheat;
	private Cheat cheat;
	private Serializer serializer;
	private Deserializer deserializer;
	
	public ReloadSave(){
		serializer = new Serializer();
		deserializer = new Deserializer();
	}
	
	public void reload(GameContainer gc, StateBasedGame sbg){
		
		
		if(InputControl.inputPressed(Input.KEY_R)){
			Event.makeReload = true;	
			//reloadGame();
		}
		
		
		if(InputControl.inputPressed(Input.KEY_S)){
			serializer.serializer();
		}
		
		if(InputControl.inputPressed(Input.KEY_N)){
			sbg.enterState(0);
		}
		
		if(InputControl.inputPressed(Input.KEY_B)){
			GameSound.getMusic().stop();
			sbg.enterState(3);
			sbg.enterState(3, new EmptyTransition (),new BlobbyTransition(Color.black));
		}
		if(InputControl.inputPressed(Input.KEY_EQUALS)){
			isCheat = true;
			cheat = new Cheat();
		}
		if(InputControl.inputPressed(Input.KEY_I)){
			Event.fin = true;
		}
		if(InputControl.inputPressed(Input.KEY_ESCAPE)){
			PauseState.PAUSE = !PauseState.PAUSE;
		}
		
		if(isCheat){
			
			cheat.update();
		}
				
	}
	
	public void reloadGame(){
		String myEncryptionPassword ="key";
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(myEncryptionPassword);
		
        Map.setInitNPC(false);
        Map.setInit(false);
	
		deserializer.DeSerializere();

		Player.setX(Float.parseFloat(textEncryptor.decrypt(Deserializer.getSaveData().getxHero())));
		Player.setY(Float.parseFloat(textEncryptor.decrypt(Deserializer.getSaveData().getyHero())));
        Map.setInitNPC(false);
        Map.setInit(false);
		Map.setIDx(Integer.valueOf(textEncryptor.decrypt(Deserializer.getSaveData().getIDx())));
		Map.setIDy(Integer.valueOf(textEncryptor.decrypt(Deserializer.getSaveData().getIDy())));
        Map.setInitNPC(false);
        Map.setInit(false);
		Hud.setNbrHeart(Integer.valueOf(textEncryptor.decrypt(Deserializer.getSaveData().getNbrHeart())));;
		GameSound.setCompteur(0);
		GameSound.music = Deserializer.getSaveData().getMusic();
		GameSound.isPlaying(GameSound.getTree().get(GameSound.music));
		try {
			Player.setSex(Deserializer.getSaveData().getSex());
		} catch (SlickException e) {
			e.printStackTrace();
		}
		Menu.setPseudo(Deserializer.getSaveData().getPseudo());
		Event.isDove = Deserializer.getSaveData().isDove();
		Event.SWORD = Deserializer.getSaveData().isSword();
		Event.WaterGun = Deserializer.getSaveData().isWaterGun();
		Event.blaster = Deserializer.getSaveData().isBlaster();
		Event.blasterStorm = Deserializer.getSaveData().isBlasterStorm();
		Event.volume = Deserializer.getSaveData().getVolume();
		Event.stan = Deserializer.getSaveData().isStan();
		Event.loic = Deserializer.getSaveData().isLoic();
		Event.amael =Deserializer.getSaveData().isAmael();
		Event.alex = Deserializer.getSaveData().isAlex();
		Event.boss_chinois =Deserializer.getSaveData().isBoss_chinois();
		Event.special_item_bonus = Deserializer.getSaveData().getSpecial_item_bonus();
		Event.specialItemBonus = Deserializer.getSaveData().isSpecialItemBonus();
		Event.haveSpecialBonus = Deserializer.getSaveData().isHaveSpecialBonus();
		Event.goSkyRoom = Deserializer.getSaveData().isGoSkyRoom();
		Event.fontaine = Deserializer.getSaveData().isFontaine();
		Event.waterQuete =Deserializer.getSaveData().isWaterQuete();
		Event.waterRennaissance = Deserializer.getSaveData().isWaterRennaissance();
		Event.purification = Deserializer.getSaveData().isPurification();
		Event.champiQuete = Deserializer.getSaveData().isChampiQuete();
		Event.champi1 =Deserializer.getSaveData().isChampi1();
		Event.champi2 = Deserializer.getSaveData().isChampi2();
		Event.champi3= Deserializer.getSaveData().isChampi3();
		Event.loveQuete = Deserializer.getSaveData().isLoveQuete();
		Event.loverQuete = Deserializer.getSaveData().isLoverQuete();
		Event.finish_loveQuete =Deserializer.getSaveData().isFinish_loveQuete();
		Event.spartaland_cine =Deserializer.getSaveData().isSpartaland_cine();
		Event.bonus_cine =Deserializer.getSaveData().isBonus_cine();
		Event.credit =Deserializer.getSaveData(). isCredit();
		Event.remy =Deserializer.getSaveData().isRemy();
		Event.EVENT =Deserializer.getSaveData().getEVENT();
		Event.NPC_event =Deserializer.getSaveData().getNPC_event();
		Event.futur_gate =Deserializer.getSaveData().isFutur_gate();
		Event.spartiateFuturEvent = Deserializer.getSaveData().isSpartiateFuturEvent();
		Event.bo = Deserializer.getSaveData().isBo();
		Event.cinematic =Deserializer.getSaveData().isCinematic();
		Event.girl_cine = Deserializer.getSaveData().isGirl_cine();
		Event.costume = Deserializer.getSaveData().isCostume();
		Event.bikini = Deserializer.getSaveData().isBikini();
		Event.costume_partiel =Deserializer.getSaveData().isCostume_partiel();
		Event.spartanSpace =Deserializer.getSaveData().isSpartanSpace();
		Event.spaceShip = Deserializer.getSaveData().isSpaceShip();
		Event.spaceKey = Deserializer.getSaveData().isSpaceKey();
		Event.torch = Deserializer.getSaveData().isTorch();
		Event.halo = Deserializer.getSaveData().getHalo();
		Event.boss_futur_explosion = Deserializer.getSaveData().isBoss_futur_explosion();
		Event.water = Deserializer.getSaveData().isWater();
		Event.maxHeart = Deserializer.getSaveData().getMaxHeart();
		Event.MJ =Deserializer.getSaveData().isMJ();
		Event.renderLetter = Deserializer.getSaveData().isRenderLetter();
		Event.enscmuReady = Deserializer.getSaveData().isEnscmuReady();
		Event.yesRU = Deserializer.getSaveData().isYesRU();
		Event.notRU = Deserializer.getSaveData().isNotRU();
		Event.tram = Deserializer.getSaveData().isTram();
		Event.wernerGirlOrBoy = Deserializer.getSaveData().isWernerGirlOrBoy();
		Event.wood = Deserializer.getSaveData().isWood();
		Event.notSword = Deserializer.getSaveData().isNotSword();
		Item.setAPPLE(Deserializer.getSaveData().getNb_saphir());
		Item.setLEMON(Deserializer.getSaveData().getNb_rubis());
		
	}
}
