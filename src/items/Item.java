package items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import event.Event;
import game.Game;
import game.Hud;
import game.Menu;

import player.Blaster;
import player.Player;

/**
 * Classe permettant de créer les items dropable 
 * sur les monstres ou autres
 * 
 * @author Rémy
 *
 */

public class Item {
	/** Image de l'item & de la dialbox*/
	private Image item,dialbox;
	/** Cercle de collision */
	private Circle circle;
	/** True si l'item a été pris */
	private boolean taked=false;
	/** Nombres d'item acquérit par le player */
	private static int NB_RUBIS,NB_SAPHIR;
	/** Nom de l'item */
	private String name;
	/** False s'il ne faut plus chercher l'intersection */
	private Boolean drawCircle = true;
	/** Compteur */
	private int cpt;
	/** Etat des objets */
	private boolean isBikini,isEvent,isHeart,isCostume,isTorch,isSpeed;
	/** Image de la colombe pour l'affichage des textes */
	private Image dove_image;
	private boolean isPlaying;

	/**
	 * Constructeur permmettant de crée un item avec son nom
	 * Dans les ressources , l'item doit etre nommé item-xxx.png
	 * 
	 * @param name Nom de l'item
	 * @throws SlickException
	 */
	public Item(String name) throws SlickException{
		this.name = name;
		item = new Image("res/items/item-"+name+".png");
		dialbox = new Image("res/all/dialboxEvent.png");
		circle = new Circle(0, 0, 15);
		dove_image = new Image("res/hero/dove.png");
	}
	
	/**
	 * Affiche l'item et regarde l'intersection avec le player
	 * 
	 * @param xM Coordonnée des abscisses
	 * @param yM Coordonnée des ordonnées
	 */
	public void render(float xM, float yM){
		
		circle.setLocation(xM, yM);
		if(!taked)
			item.draw(xM,yM);


		
		if(drawCircle){
			if((this.circle).intersects(Player.getRect())){
				itemUpdate();
				drawCircle = false;
				taked=true;
			}
		}
		
		if(taked){
			if(!isPlaying){
				Menu.coin.play(1,Event.volume);
				isPlaying = true;
			}
		}
	}
	
	/**
	 * Update des items.
	 * Permet de modifié des états en fonction de l'item dropé.
	 */
	public void itemUpdate(){
		if(!taked){
			if(name.equals("rubis_chest"))
				NB_RUBIS += 1; 
			else if(name.equals("saphir_chest"))
				NB_SAPHIR += 1;
			if(name.equals("rubis"))
				NB_RUBIS += 1; 
			else if(name.equals("saphir"))
				NB_SAPHIR += 1;
			else if(name.equals("waterGun"))
				Event.WaterGun = true;
			else if(name.equals("heart_chest")){
				Hud.setNbrHeart(Hud.getNbrHeart()+1);
				isEvent = true;
				isHeart = true;
			}
			else if(name.equals("heart")){
				Hud.setNbrHeart(Hud.getNbrHeart()+1);
			}
			else if(name.equals("costume")){
				Event.costume = true;
				Event.wood = false;
				Event.SWORD = false;
				Event.WaterGun = false;
				Event.blaster = true;
				Event.blasterStorm = true;
				Blaster.setAmmo(20);
				isEvent = true;
				isCostume = true;
			}
			else if(name.equals("torch_chest")){
				Event.torch = true;
				isEvent = true;
				isTorch = true;
			}
				
			else if(name.equals("bikini_chest")){
				Event.bikini = true;
				Hud.setBikiniLige(2);
				isEvent = true;
				isBikini = true;
			}
			else if(name.equals("blaster")){
				Blaster.setAmmo(Blaster.getAmmo()+10);	
			}
			else if(name.equals("cape")){
				Event.wood = true;
				Event.maxHeart = 6;
				Hud.setNbrHeart(Hud.getNbrHeart()+2);
			}
			else if(name.equals("speed")){
				Event.spaceShip = true;
				isEvent = true;
				isSpeed = true;
			}
			else if(name.equals("specialBonus")){
				Event.haveSpecialBonus = true;
			}
		}
	}
	
	/**
	 * Affiche l'objet simplement 
	 * 
	 * @param xM
	 * @param yM
	 */
	public void renderOnly(float xM, float yM){
			item.draw(xM,yM);
	}
	
	/**
	 * Affiche le dialog de l'item à l'écran 
	 */
	public void renderItemDialog(){
		if(isEvent){
			if(cpt<200){
				cpt++;
				dove_image.draw(200, 380);
				dialbox.draw(200,470);
				if(isBikini){
					Game.uFont.drawString(210,477,"Félicitations, tu as trouvé le Bikini en Cotte de mailles !\n" +
							"Tu vas mieux résister aux coups maintenant !");
				}
				else if(isHeart){
					Game.uFont.drawString(210,477,"Bravo, tu as trouvé un coeur !");
				}
				else if(isCostume){
					Game.uFont.drawString(210,477,"Félicitations, tu as trouvé une Combinaison de Stormtrooper !\n" +
							"Elle te sera utile pour passer inaperçu. En plus, il y a un Blaster !\n" +
							"Attention, ses munitions sont limitées.\n" +
							"(appuie sur la touche CTRL-G pour tirer)");
				}
				else if(isTorch){
					Game.uFont.drawString(210,477,"Tu as récupéré une torche !\nTu peux maintenant traverser l'uha sans problèmes !");
				}
				else if(isSpeed){
					Game.uFont.drawString(210,477,"Tu as récupéré un boost de vitesse !");
				}
				
			}else{
				isEvent = false;
				isBikini = false;
				isHeart = false;
				isCostume = false;
				isTorch = false;
				isSpeed = false;
				cpt = 0;
			}
		}
	}
	
	
	/**
	 * @return NB_RUBIS Nombre de Pomme
	 */
	public static int getNB_Rubis(){
		return NB_RUBIS;
	}
	/**
	 * @return NB_SAPHIR Nombre de citron
	 */
	public static int getNB_Saphir(){
		return NB_SAPHIR;
	}
	/**
	 * Modifie le nombre de citron
	 * 
	 * @param NB_SAPHIR Nombre de citron
	 */
	public static void setLEMON( int NB_SAPHIR){
		Item.NB_SAPHIR = NB_SAPHIR;
	}
	/**
	 * Modifie le nombre de pomme
	 * 
	 * @param NB_RUBIS Nombre de pomme
	 */
	public static void setAPPLE( int NB_RUBIS){
		Item.NB_RUBIS = NB_RUBIS;
	}
	
	/**
	 * Permet de savoir si l'objet a été prit
	 * 
	 * @return True si l'objet est prit
	 */
	public boolean getTake(){
		return taked;
	}
	/** 
	 * Permet de modifier le boolean taked
	 * 
	 * @param taked
	 */
	public void setTaked(boolean taked){
		this.taked = taked;
	}
	
	public String getName(){
		return name;
	}
	
}
