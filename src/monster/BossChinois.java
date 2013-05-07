package monster;

import event.Event;

import java.util.ArrayList;
import java.util.List;

import map.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Line;

import player.Player;

/**
 * Classe créant des Space Invaders
 * 
 * @author Rémy
 * 
 */

public class BossChinois extends Monster {

	/** Liste de tir */
	private List<Tornado> listTornado;
	/** Lancement du tir à intervalle régulier */
	private int compteur = 1;

	/**
	 * Constructeur par défaut qui donne une vitesse de 0.06 Instancie la liste,
	 * la ligne et l'ellipse
	 */
	public BossChinois() {
		super(0.06f, 140);
		listTornado= new ArrayList<Tornado>();
	}

	/**
	 * Initialisation des animations
	 */
	public void init() throws SlickException {
		super.init();
		xM =384;
		yM =128;
		super.MonsterAnim("tengu", 32, 32);
	}

	/**
	 * Boucle update qui gère le déplacement et le tir du monstre
	 */
	public void update(GameContainer gc, int delta) throws SlickException {
		super.update(gc, delta);

		if (alive && !Event.cinematic) {
			if (left && (compteur == 0)) {
				listTornado.add(new Tornado(xM,yM));
			}
			if (right && (compteur == 0)) {
				listTornado.add(new Tornado(xM,yM));
			}
			if (down && (compteur == 0)) {
				listTornado.add(new Tornado(xM,yM));
			}
			if (up && (compteur == 0)) {
				listTornado.add(new Tornado(xM,yM));
			}
			compteur++;

			if (compteur > 80)
				compteur = 0;

			for (Tornado so : listTornado) {
				so.updateDiag(delta);
				if (so.getRec().intersects(Player.getRect())) {
					Event.colision = true;
					so.setShoot(false);
				}
			}

		}

	}

	/**
	 * Boucle de rendu
	 * 
	 * @param g
	 *            Sortie de l'écran
	 */
	public void renderBot(Graphics g) throws SlickException {
		super.renderBot(g);
		if (alive) {
			for (Tornado so : listTornado) {
				so.setShoot(true);
				so.render();
			}
		}
	}
}
