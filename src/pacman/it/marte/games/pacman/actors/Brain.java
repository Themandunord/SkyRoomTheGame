package pacman.it.marte.games.pacman.actors;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.Path.Step;

public abstract interface Brain
{
  public abstract void init();

  public abstract void update(int paramInt);

  public abstract Path.Step getCurrentStep();

  public abstract void goToNextStep(Vector2f paramVector2f);

  public abstract void render(BasicGameState paramBasicGameState, Graphics paramGraphics);

  public abstract boolean isCannotFindPath();

  public abstract void setCurrent(Vector2f paramVector2f);
}

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.actors.Brain
 * JD-Core Version:    0.6.2
 */