/*     */ package pacman.it.marte.games.pacman.brains;
/*     */ 
/*     */ import pacman.it.marte.games.pacman.actors.Brain;
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
/*     */ public class RunningGhostBrain
/*     */   implements Brain
/*     */ {
/*     */   private static final int THINKINGTIME = 50;
/*     */   private int updateThinkingTime;
/*     */   private int currentStepIndex;
/*     */   private Path path;
/*     */   private Image dot;
/*     */   private Vector2f current;
/*     */   private Map map;
/*     */   private boolean cannotFindPath;
/*     */   private Vector2f corner;
/*     */ 
/*     */   public RunningGhostBrain(Map map, Vector2f start)
/*     */     throws SlickException
/*     */   {
/*  59 */     this.map = map;
/*  60 */     this.current = start;
/*  61 */     init();
/*     */   }
/*     */ 
/*     */   public void init()
/*     */   {
/*  70 */     this.cannotFindPath = false;
/*  71 */     this.path = null;
/*  72 */     this.updateThinkingTime = 0;
/*  73 */     this.currentStepIndex = 0;
/*     */     try
/*     */     {
/*  76 */       this.dot = new Image("data/dot.gif");
/*     */     } catch (SlickException e) {
/*  78 */       Log.error(e);
/*     */     }
/*  80 */     this.corner = this.map.getRandomCorner();
/*  81 */     if (this.corner == null) {
/*  82 */       Log.error("corners null!");
/*     */     }
/*     */ 
/*  85 */     updatePathToPlayer();
/*     */   }
/*     */ 
/*     */   public void update(int delta)
/*     */   {
/*  93 */     if (this.path == null) {
/*  94 */       updatePathToPlayer();
/*  95 */       return;
/*     */     }
/*     */ 
/*  98 */     this.updateThinkingTime += delta;
/*  99 */     if (this.updateThinkingTime > 50) {
/* 100 */       this.updateThinkingTime = 0;
/*     */ 
/* 102 */       if (this.currentStepIndex > this.path.getLength() - 1)
/* 103 */         reThink(this.current, this.map, this.path);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void reThink(Vector2f current, Map map, Path path)
/*     */   {
/* 116 */     this.currentStepIndex = 0;
/* 117 */     path = null;
/* 118 */     updatePathToPlayer();
/*     */   }
/*     */ 
/*     */   private void updatePathToPlayer()
/*     */   {
/*     */     try
/*     */     {
/* 126 */       this.corner = this.map.getRandomCorner();
/* 127 */       this.path = this.map.getUpdatedPath((int)this.current.getX() / this.map.getTileSize(), (int)this.current.getY() / this.map.getTileSize(), (int)this.corner.getX(), (int)this.corner.getY());
/*     */     }
/*     */     catch (NullPointerException e)
/*     */     {
/* 133 */       this.path = null;
/* 134 */       this.cannotFindPath = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void render(BasicGameState game, Graphics g)
/*     */   {
/* 146 */     if (this.path != null)
/* 147 */       for (int i = 0; i < this.path.getLength(); i++) {
/* 148 */         Path.Step a = this.path.getStep(i);
/* 149 */         this.dot.draw(a.getX() * this.map.getTileSize(), a.getY() * this.map.getTileSize());
/*     */       }
/*     */   }
/*     */ 
/*     */   public Path.Step getCurrentStep()
/*     */   {
/* 159 */     if (this.path == null) {
/* 160 */       updatePathToPlayer();
/*     */     }
/* 162 */     return this.path.getStep(this.currentStepIndex);
/*     */   }
/*     */ 
/*     */   public void goToNextStep(Vector2f position)
/*     */   {
/* 171 */     this.currentStepIndex += 1;
/* 172 */     this.current = position;
/*     */   }
/*     */ 
/*     */   public boolean isCannotFindPath() {
/* 176 */     return this.cannotFindPath;
/*     */   }
/*     */ 
/*     */   public void setCurrent(Vector2f current) {
/* 180 */     this.current = current;
/*     */   }
/*     */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.brains.RunningGhostBrain
 * JD-Core Version:    0.6.2
 */