package event;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Classe créant les crédits du jeu
 * 
 * @author Rémy
 * 
 */
public class Credit {

	private Image text, fond;
	private int cpt, current_text;
	private boolean isLoad;

	public Credit() throws SlickException {
		this.cpt = 0;
		this.current_text = 0;
		this.fond = new Image("res/credit/bg.png");
	}

	public void update(int max, int duration, StateBasedGame sbg)
			throws SlickException {
		if (Event.credit) {
			fond.draw(0, 260);
			if (current_text < max) {
				if (cpt < duration) {
					cpt++;
					Event.cinematic = true;
					if (!isLoad) {
						text = new Image("res/credit/credit-" + current_text
								+ ".png");
						isLoad = true;
					}
					if(text!=null)
						text.draw(130, 310);
				} else {
					isLoad = false;
					current_text++;
					cpt = 0;
				}
			} else {
				Event.remy = false;
				Event.credit = false;
				sbg.enterState(20);
				sbg.enterState(20, new FadeOutTransition(),
						new FadeInTransition());
			}
		}
	}
}
