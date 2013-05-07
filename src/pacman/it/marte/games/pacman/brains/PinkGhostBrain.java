/*     */ package pacman.it.marte.games.pacman.brains;
/*     */ 
/*     */ import pacman.it.marte.games.pacman.actors.Brain;
/*     */ import pacman.it.marte.games.pacman.actors.Player;
/*     */ import pacman.it.marte.games.pacman.map.Map;
/*     */ import org.newdawn.slick.Graphics;
/*     */ import org.newdawn.slick.Image;
/*     */ import org.newdawn.slick.SlickException;
/*     */ import org.newdawn.slick.geom.Vector2f;
/*     */ import org.newdawn.slick.state.BasicGameState;
/*     */ import org.newdawn.slick.util.Log;
/*     */ import org.newdawn.slick.util.pathfinding.Path;
/*     */ import org.newdawn.slick.util.pathfinding.Path.Step;
/*     */ 
/*     */ public class PinkGhostBrain
/*     */   implements Brain
/*     */ {
/*     */   private static final int THINKINGTIME = 50;
/*     */   private static final int SHIFTFACTOR = 4;
/*     */   private int updateThinkingTime;
/*     */   private int updatePlayerPositionTime;
/*     */   private int currentStepIndex;
/*     */   private Path path;
/*     */   private Image dot;
/*     */   private Vector2f current;
/*     */   private Map map;
/*     */   private boolean cannotFindPath;
/*     */ 
/*     */   public PinkGhostBrain(Map map, Vector2f start)
/*     */     throws SlickException
/*     */   {
/*  66 */     this.map = map;
/*  67 */     this.current = start;
/*  68 */     init();
/*     */   }
/*     */ 
/*     */   public void init()
/*     */   {
/*  77 */     this.path = null;
/*  78 */     this.updateThinkingTime = 0;
/*  79 */     this.updatePlayerPositionTime = 0;
/*  80 */     this.currentStepIndex = 0;
/*  81 */     this.cannotFindPath = false;
/*     */     try
/*     */     {
/*  84 */       this.dot = new Image("data/pinkdot.gif");
/*     */     } catch (SlickException e) {
/*  86 */       Log.error(e);
/*     */     }
/*  88 */     updatePathToPlayer();
/*     */   }
/*     */ 
/*     */   public void update(int delta)
/*     */   {
/*  96 */     if (this.path == null) {
/*  97 */       updatePathToPlayer();
/*  98 */       return;
/*     */     }
/*     */ 
/* 101 */     this.updateThinkingTime += delta;
/* 102 */     if (this.updateThinkingTime > 50) {
/* 103 */       this.updateThinkingTime = 0;
/*     */ 
/* 105 */       if (this.currentStepIndex > this.path.getLength() - 1) {
/* 106 */         reThink(this.current, this.map, this.path);
/*     */       }
/*     */     }
/*     */ 
/* 110 */     this.updatePlayerPositionTime += delta;
/*     */   }
/*     */ 
/*     */   private void reThink(Vector2f current, Map map, Path path)
/*     */   {
/* 122 */     this.currentStepIndex = 0;
/* 123 */     path = null;
/* 124 */     updatePathToPlayer();
/*     */   }
/*     */ 
/*     */   private void updatePathToPlayer()
/*     */   {
/* 132 */     Player pl = Map.getPlayer();
/* 133 */     Vector2f pos = pl.getPosition();
/*     */ 
/* 135 */     String dir = pl.getLastDir();
/*     */ 
/* 137 */     if (dir.equals("up")) {
/* 138 */       pos.y -= 4 * this.map.getTileSize();
/*     */     }
/* 140 */     if (dir.equals("down")) {
/* 141 */       pos.y += 4 * this.map.getTileSize();
/*     */     }
/* 143 */     if (dir.equals("left")) {
/* 144 */       pos.x -= 4 * this.map.getTileSize();
/*     */     }
/* 146 */     if (dir.equals("right")) {
/* 147 */       pos.x += 4 * this.map.getTileSize();
/*     */     }
/*     */     try
/*     */     {
/* 151 */       if (!this.map.blocked(null, (int)pos.x / this.map.getTileSize(), (int)pos.y / this.map.getTileSize()))
/*     */       {
/* 153 */         this.path = this.map.getUpdatedPath((int)this.current.getX() / this.map.getTileSize(), (int)this.current.getY() / this.map.getTileSize(), (int)pos.getX() / this.map.getTileSize(), (int)pos.getY() / this.map.getTileSize());
/*     */       }
/*     */       else
/*     */       {
/* 159 */         this.path = this.map.getUpdatedPath((int)this.current.getX() / this.map.getTileSize(), (int)this.current.getY() / this.map.getTileSize(), (int)pl.getX() / this.map.getTileSize(), (int)pl.getY() / this.map.getTileSize());
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (NullPointerException e)
/*     */     {
/* 166 */       this.path = this.map.getUpdatedPath((int)this.current.getX() / this.map.getTileSize(), (int)this.current.getY() / this.map.getTileSize(), (int)pl.getX() / this.map.getTileSize(), (int)pl.getY() / this.map.getTileSize());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void render(BasicGameState game, Graphics g)
/*     */   {
/* 182 */     if (this.path != null)
/* 183 */       for (int i = 0; i < this.path.getLength(); i++) {
/* 184 */         Path.Step a = this.path.getStep(i);
/* 185 */         this.dot.draw(a.getX() * this.map.getTileSize(), a.getY() * this.map.getTileSize());
/*     */       }
/*     */   }
/*     */ 
/*     */   public Path.Step getCurrentStep()
/*     */   {
/* 195 */     if (this.path == null) {
/* 196 */       updatePathToPlayer();
/*     */     }
/* 198 */     return this.path.getStep(this.currentStepIndex);
/*     */   }
/*     */ 
/*     */   public void goToNextStep(Vector2f position)
/*     */   {
/* 207 */     this.currentStepIndex += 1;
/* 208 */     this.current = position;
/*     */   }
/*     */ 
/*     */   public boolean isCannotFindPath() {
/* 212 */     return this.cannotFindPath;
/*     */   }
/*     */ 
/*     */   public void setCurrent(Vector2f current)
/*     */   {
/* 219 */     this.current = current;
/*     */   }
/*     */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.brains.PinkGhostBrain
 * JD-Core Version:    0.6.2
 */