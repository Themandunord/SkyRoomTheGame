package monster;

import event.Event;

import java.util.ArrayList;
import java.util.List;

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

public class SpaceInvader extends Monster {

	/** Liste de tir */
	private List<Shoot> listShoot;
	/** Lancement du tir à intervalle régulier */
	private int compteur = 1;
	/** Ligne droite */
	private Line line;

	/**
	 * Constructeur par défaut qui donne une vitesse de 0.06 Instancie la liste,
	 * la ligne et l'ellipse
	 */
	public SpaceInvader() {
		super(0.06f, 140);
		listShoot = new ArrayList<Shoot>();
		line = new Line(xM, yM, Player.getX(), Player.getY());
	}

	/**
	 * Initialisation des animations
	 */
	public void init() throws SlickException {
		super.init();
		// On charge un sprite aléatoirement
		if (Math.random() < 0.5)
			super.MonsterAnim("invader1", 32, 32);
		else
			super.MonsterAnim("invader2", 32, 32);
	}

	/**
	 * Boucle update qui gère le déplacement et le tir du monstre
	 */
	public void update(GameContainer gc, int delta) throws SlickException {
		super.update(gc, delta);

		if (alive && !Event.cinematic) {
			if (left && (compteur == 0 || compteur == 20)) {
				listShoot.add(new Shoot(xM + 16, yM + 16, 3, "invaders"));
			}
			if (right && (compteur == 0 || compteur == 20)) {
				listShoot.add(new Shoot(xM + 16, yM + 16, 2, "invaders"));
			}
			if (down && (compteur == 0 || compteur == 20)) {
				listShoot.add(new Shoot(xM + 16, yM + 16, 1, "invaders"));
			}
			if (up && (compteur == 0 || compteur == 20)) {
				listShoot.add(new Shoot(xM + 16, yM + 16, 0, "invaders"));
			}
			compteur++;

			if (compteur > 80)
				compteur = 0;

			for (Shoot so : listShoot) {
				so.updateDiag(delta);
				if (so.getCircle().intersects(Player.getRect())) {
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
			for (Shoot so : listShoot) {
				so.setShoot(true);
				so.render();
			}
		}
	}
}
