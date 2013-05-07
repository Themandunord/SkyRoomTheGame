package pacman.it.marte.games.pacman.base;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;

public abstract interface Animatable
{
  public abstract void render(BasicGameState paramBasicGameState, Graphics paramGraphics);

  public abstract void update(GameContainer paramGameContainer, int paramInt);
}

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.base.Animatable
 * JD-Core Version:    0.6.2
 */