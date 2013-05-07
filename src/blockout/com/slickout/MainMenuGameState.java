package blockout.com.slickout;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenuGameState extends BasicGameState {
	private Image background;
	private Image selector;
	private int selection;
	private int optionSelected;
	private int topScore;
	private int stateId=31;
	public static Sound bip;
	public static Sound bop;
	public static Sound bump;
	public static Sound drop;

	public MainMenuGameState(int id) {
		super();
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		background = new Image("data/mainmenu.jpg");
		selector = new Image("data/selector.png");
		try {
			bip = new Sound("data/bip.wav");
			bop = new Sound("data/bop.wav");
			bump = new Sound("data/bump.wav");
			drop = new Sound("data/drop.wav");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		background.draw();
		if (selection == SlickOut.GAMEPLAYSTATE) {
			selector.draw(158, 310);
			selector.draw(694, 310);
			GameInfo.createNewGameInfo();
		} else if (selection == SlickOut.EXITSTATE) {
			selector.draw(158, 474);
			selector.draw(694, 474);
		}
		g.drawString("TOPSCORE: " + topScore, 10, 10);
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		super.mouseClicked(button, x, y, clickCount);
		optionSelected = selection;
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		super.mouseMoved(oldx, oldy, newx, newy);
		if (newx > 228 && newx < 702) {
			// start game
			if (newy > 308 && newy < 389) {
				selection = SlickOut.GAMEPLAYSTATE;
				// exit game
			} else if (newy > 475 && newy < 544) {
				selection = SlickOut.EXITSTATE;
			} else {
				selection = -1;
			}
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if (optionSelected == SlickOut.GAMEPLAYSTATE) {
			game.enterState(SlickOut.GAMEPLAYSTATE);
		} else if (optionSelected == SlickOut.EXITSTATE) {
			game.enterState(2, new FadeOutTransition(),new FadeInTransition(Color.black));
		}
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		selection = -1;
		optionSelected = selection;
		if (GameInfo.getCurrentGameInfo() != null) {
			topScore = (topScore > GameInfo.getCurrentGameInfo().getPlayerInfo().getScore() ? topScore : GameInfo.getCurrentGameInfo().getPlayerInfo().getScore());
		}
	}

	@Override
	public int getID() {
		return stateId;
	}

}
