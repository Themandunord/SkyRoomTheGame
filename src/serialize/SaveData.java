package serialize;

import java.text.Normalizer;

import items.Item;

import org.jasypt.util.text.BasicTextEncryptor;
import org.newdawn.slick.Music;

import event.Event;

import game.DifficultySelector;
import game.GameSound;
import game.Hud;
import game.Menu;
import map.Map;
import player.Player;

/*
 * Tout ce qui doit être sauvegarder ce retrouve ici
 */

public class SaveData {

	private String xHero;
	private String yHero;
	private String IDx, IDy;
	private String nrbHeart;
	private int music;
	private String sex;
	private String pseudo;
	private boolean dove;
	private boolean sword;
	private boolean waterGun;
	private boolean blaster;
	private boolean blasterStorm;
	private int EVENT;
	private int NPC_event;
	private boolean futur_gate, spartiateFuturEvent, bo, cinematic, girl_cine,
			costume, bikini, costume_partiel, spartanSpace, spaceShip,
			spaceKey, torch, boss_futur_explosion, water, MJ, renderLetter,
			enscmuReady, yesRU, notRU, tram, wernerGirlOrBoy, wood, notSword,
			fontaine, waterQuete, waterRennaissance, purification, champiQuete,
			champi1, champi2, champi3, loveQuete, loverQuete, finish_loveQuete,
			spartaland_cine, bonus_cine, credit, remy, stan, alex, loic, amael,
			boss_chinois, specialItemBonus, haveSpecialBonus, goSkyRoom,
			bossRennaissance;
	private int special_item_bonus;
	private int halo, maxHeart;
	private float volume;
	private int nb_rubis, nb_saphir;
	private int time;
	private boolean cine_dark;
	private boolean cine_prehistoire;
	private boolean cine_stormtrooper;
	private boolean cine_bonus;
	private boolean cine_futur;
	private boolean cine_chinois;
	private boolean cine_renaissance;
	private boolean cine_skyroom;
	private boolean cine_spartaland;
	private boolean dialog_dove;
	private boolean doveOnPlayer;
	private boolean EASY,MEDIUM,HARD,GOD;

	public boolean isEASY() {
		return EASY;
	}

	public boolean isMEDIUM() {
		return MEDIUM;
	}

	public boolean isHARD() {
		return HARD;
	}

	public boolean isGOD() {
		return GOD;
	}

	public boolean isBossRennaissance() {
		return bossRennaissance;
	}

	public boolean isCine_dark() {
		return cine_dark;
	}

	public boolean isCine_prehistoire() {
		return cine_prehistoire;
	}

	public boolean isCine_stormtrooper() {
		return cine_stormtrooper;
	}

	public boolean isCine_bonus() {
		return cine_bonus;
	}

	public boolean isCine_futur() {
		return cine_futur;
	}

	public boolean isCine_chinois() {
		return cine_chinois;
	}

	public boolean isCine_renaissance() {
		return cine_renaissance;
	}

	public boolean isCine_skyroom() {
		return cine_skyroom;
	}

	public boolean isCine_spartaland() {
		return cine_spartaland;
	}

	public int getTime() {
		return time;
	}

	public SaveData() {
		String myEncryptionPassword = "key";
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword(myEncryptionPassword);
		this.xHero = /* textEncryptor.encrypt( */String.valueOf(Player.getX())/* ) */;
		this.yHero = /* textEncryptor.encrypt( */String.valueOf(Player.getY())/* ) */;
		this.IDx = /* textEncryptor.encrypt( */String.valueOf(Map.getIDx())/* ) */;
		this.IDy = /* textEncryptor.encrypt( */String.valueOf(Map.getIDy())/* ) */;
		this.nrbHeart = /* textEncryptor.encrypt( */String.valueOf(Hud
				.getNbrHeart())/* ) */;
		this.volume = Event.volume;
		this.stan = Event.stan;
		this.loic = Event.loic;
		this.amael = Event.amael;
		this.alex = Event.alex;
		this.boss_chinois = Event.boss_chinois;
		this.special_item_bonus = Event.special_item_bonus;
		this.specialItemBonus = Event.specialItemBonus;
		this.haveSpecialBonus = Event.haveSpecialBonus;
		this.goSkyRoom = Event.goSkyRoom;
		this.fontaine = Event.fontaine;
		this.waterQuete = Event.waterQuete;
		this.waterRennaissance = Event.waterRennaissance;
		this.purification = Event.purification;
		this.champiQuete = Event.champiQuete;
		this.champi1 = Event.champi1;
		this.champi2 = Event.champi2;
		this.champi3 = Event.champi3;
		this.loveQuete = Event.loveQuete;
		this.loverQuete = Event.loverQuete;
		this.finish_loveQuete = Event.finish_loveQuete;
		this.spartaland_cine = Event.spartaland_cine;
		this.bonus_cine = Event.bonus_cine;
		this.credit = Event.credit;
		this.remy = Event.remy;
		this.EVENT = Event.EVENT;
		this.NPC_event = Event.NPC_event;
		this.futur_gate = Event.futur_gate;
		this.spartiateFuturEvent = Event.spartiateFuturEvent;
		this.bo = Event.bo;
		this.cinematic = Event.cinematic;
		this.girl_cine = Event.girl_cine;
		this.costume = Event.costume;
		this.bikini = Event.bikini;
		this.costume_partiel = Event.costume_partiel;
		this.spartanSpace = Event.spartanSpace;
		this.spaceShip = Event.spaceShip;
		this.spaceKey = Event.spaceKey;
		this.torch = Event.torch;
		this.halo = Event.halo;
		this.boss_futur_explosion = Event.boss_futur_explosion;
		this.water = Event.water;
		this.maxHeart = Event.maxHeart;
		this.MJ = Event.MJ;
		this.renderLetter = Event.renderLetter;
		this.enscmuReady = Event.enscmuReady;
		this.yesRU = Event.yesRU;
		this.notRU = Event.notRU;
		this.tram = Event.tram;
		this.wernerGirlOrBoy = Event.wernerGirlOrBoy;
		this.wood = Event.wood;
		this.notSword = Event.notSword;
		this.music = GameSound.music;
		this.sex = Player.getSex();
		this.pseudo = Menu.getPseudo();
		this.pseudo = Normalizer.normalize(this.pseudo, Normalizer.Form.NFD);
		this.pseudo = this.pseudo.replaceAll("[^\\p{ASCII}]", "");
		this.pseudo
				.replaceAll(
						"\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\-\\{\\}\\|\\;\\\\\\'////\\,\\.\\?\\<\\>\\[]",
						"");
		this.dove = Event.isDove;
		this.sword = Event.SWORD;
		this.waterGun = Event.WaterGun;
		this.blaster = Event.blaster;
		this.blasterStorm = Event.blasterStorm;
		this.nb_rubis = Item.getNB_Rubis();
		this.nb_saphir = Item.getNB_Saphir();
		this.time = Event.time;
		this.bossRennaissance = Event.bossRennaissance;
		this.cine_dark = Event.cine_dark;
		this.cine_prehistoire = Event.cine_prehistoire;
		this.cine_stormtrooper = Event.cine_stormtrooper;
		this.cine_bonus = Event.cine_bonus;
		this.cine_futur = Event.cine_futur;
		this.cine_chinois = Event.cine_chinois;
		this.cine_renaissance = Event.cine_renaissance;
		this.cine_skyroom = Event.cine_skyroom;
		this.cine_spartaland = Event.cine_spartaland;
		this.dialog_dove = Event.dialog_dove;
		this.doveOnPlayer = Event.doveOnPlayer;
		this.EASY = DifficultySelector.EASY;
		this.MEDIUM = DifficultySelector.MEDIUM;
		this.HARD = DifficultySelector.HARD;
		this.GOD = DifficultySelector.GOD;
	}

	public boolean isDoveOnPlayer() {
		return doveOnPlayer;
	}

	public boolean isDialog_dove() {
		return dialog_dove;
	}

	public int getNb_rubis() {
		return nb_rubis;
	}

	public boolean getbossRennaissance() {
		return bossRennaissance;
	}

	public int getNb_saphir() {
		return nb_saphir;
	}

	public String getxHero() {
		return xHero;
	}

	public String getyHero() {
		return yHero;
	}

	public String getIDx() {
		return IDx;
	}

	public String getIDy() {
		return IDy;
	}

	public String getNbrHeart() {
		return nrbHeart;
	}

	public int getMusic() {
		return music;
	}

	public String getSex() {
		return sex;
	}

	public String getPseudo() {
		return pseudo;
	}

	public boolean isDove() {
		return dove;
	}

	public boolean isSword() {
		return sword;
	}

	public boolean isWaterGun() {
		return waterGun;
	}

	public boolean isBlaster() {
		return blaster;
	}

	public boolean isBlasterStorm() {
		return blasterStorm;
	}

	public String getNrbHeart() {
		return nrbHeart;
	}

	public int getEVENT() {
		return EVENT;
	}

	public int getNPC_event() {
		return NPC_event;
	}

	public boolean isFutur_gate() {
		return futur_gate;
	}

	public boolean isSpartiateFuturEvent() {
		return spartiateFuturEvent;
	}

	public boolean isBo() {
		return bo;
	}

	public boolean isCinematic() {
		return cinematic;
	}

	public boolean isGirl_cine() {
		return girl_cine;
	}

	public boolean isCostume() {
		return costume;
	}

	public boolean isBikini() {
		return bikini;
	}

	public boolean isCostume_partiel() {
		return costume_partiel;
	}

	public boolean isSpartanSpace() {
		return spartanSpace;
	}

	public boolean isSpaceShip() {
		return spaceShip;
	}

	public boolean isSpaceKey() {
		return spaceKey;
	}

	public boolean isTorch() {
		return torch;
	}

	public boolean isBoss_futur_explosion() {
		return boss_futur_explosion;
	}

	public boolean isWater() {
		return water;
	}

	public boolean isMJ() {
		return MJ;
	}

	public boolean isRenderLetter() {
		return renderLetter;
	}

	public boolean isEnscmuReady() {
		return enscmuReady;
	}

	public boolean isYesRU() {
		return yesRU;
	}

	public boolean isNotRU() {
		return notRU;
	}

	public boolean isTram() {
		return tram;
	}

	public boolean isWernerGirlOrBoy() {
		return wernerGirlOrBoy;
	}

	public boolean isWood() {
		return wood;
	}

	public boolean isNotSword() {
		return notSword;
	}

	public boolean isFontaine() {
		return fontaine;
	}

	public boolean isWaterQuete() {
		return waterQuete;
	}

	public boolean isWaterRennaissance() {
		return waterRennaissance;
	}

	public boolean isPurification() {
		return purification;
	}

	public boolean isChampiQuete() {
		return champiQuete;
	}

	public boolean isChampi1() {
		return champi1;
	}

	public boolean isChampi2() {
		return champi2;
	}

	public boolean isChampi3() {
		return champi3;
	}

	public boolean isLoveQuete() {
		return loveQuete;
	}

	public boolean isLoverQuete() {
		return loverQuete;
	}

	public boolean isFinish_loveQuete() {
		return finish_loveQuete;
	}

	public boolean isSpartaland_cine() {
		return spartaland_cine;
	}

	public boolean isBonus_cine() {
		return bonus_cine;
	}

	public boolean isCredit() {
		return credit;
	}

	public boolean isRemy() {
		return remy;
	}

	public boolean isStan() {
		return stan;
	}

	public boolean isAlex() {
		return alex;
	}

	public boolean isLoic() {
		return loic;
	}

	public boolean isAmael() {
		return amael;
	}

	public boolean isBoss_chinois() {
		return boss_chinois;
	}

	public boolean isSpecialItemBonus() {
		return specialItemBonus;
	}

	public boolean isHaveSpecialBonus() {
		return haveSpecialBonus;
	}

	public boolean isGoSkyRoom() {
		return goSkyRoom;
	}

	public int getSpecial_item_bonus() {
		return special_item_bonus;
	}

	public int getHalo() {
		return halo;
	}

	public int getMaxHeart() {
		return maxHeart;
	}

	public float getVolume() {
		return volume;
	}
}
