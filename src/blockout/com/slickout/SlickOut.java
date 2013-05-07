package blockout.com.slickout;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class SlickOut extends StateBasedGame {
	public final static int MAINMENUSTATE = 31;
	public final static int GAMEPLAYSTATE = 30;
	public final static int EXITSTATE = 2;
	public final static int LEVELSELECTORSTATE = 32;

	public SlickOut() {
		super("SlickOut");
	}

	@Override
	
	public void initStatesList(GameContainer gc) throws SlickException{
//		PlayerInfo.createNewCurrentPlayerInfo();
		addState(new MainMenuGameState(MAINMENUSTATE));
		GamePlayState state = new GamePlayState(GAMEPLAYSTATE);
		state.setLevelFile("data/level1.lvl");
		addState(state);
		addState(new LevelSelector(LEVELSELECTORSTATE));
	}


}
