package pacman.it.marte.games.pacman.util;

import pacman.it.marte.games.pacman.base.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract interface State
{
  public abstract boolean equals(Object paramObject);

  public abstract void enter();

  public abstract void update(GameContainer paramGameContainer, int paramInt);

  public abstract void render(Graphics paramGraphics);

  public abstract void onCollision(Entity paramEntity);
}

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.util.State
 * JD-Core Version:    0.6.2
 */