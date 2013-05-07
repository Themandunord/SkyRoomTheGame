package pacman.it.marte.games.pacman.base;

public abstract interface Level extends Animatable
{
  public abstract void add(Entity paramEntity);

  public abstract void remove(Entity paramEntity);

  public abstract void clear();
}

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.base.Level
 * JD-Core Version:    0.6.2
 */