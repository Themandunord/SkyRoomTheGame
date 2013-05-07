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
/*     */ public class RedGhostBrain
/*     */   implements Brain
/*     */ {
/*     */   private static final int THINKINGTIME = 50;
/*     */   private static final int UPDATETIME = 5000;
/*     */   private int updateThinkingTime;
/*     */   private int updatePlayerPositionTime;
/*     */   private int currentStepIndex;
/*     */   private Path path;
/*     */   private Image dot;
/*     */   private Vector2f current;
/*     */   private Map map;
/*     */   private boolean cannotFindPath;
/*     */ 
/*     */   public RedGhostBrain(Map map, Vector2f start)
/*     */     throws SlickException
/*     */   {
/*  65 */     this.map = map;
/*  66 */     this.current = start;
/*  67 */     init();
/*     */   }
/*     */ 
/*     */   public void init()
/*     */   {
/*  76 */     this.path = null;
/*  77 */     this.updateThinkingTime = 0;
/*  78 */     this.updatePlayerPositionTime = 0;
/*  79 */     this.currentStepIndex = 0;
/*  80 */     this.cannotFindPath = false;
/*     */     try
/*     */     {
/*  83 */       this.dot = new Image("data/dot.gif");
/*     */     } catch (SlickException e) {
/*  85 */       Log.error(e);
/*     */     }
/*  87 */     updatePathToPlayer();
/*     */   }
/*     */ 
/*     */   public void update(int delta)
/*     */   {
/*  95 */     if (this.path == null) {
/*  96 */       updatePathToPlayer();
/*  97 */       return;
/*     */     }
/*     */ 
/* 100 */     this.updateThinkingTime += delta;
/* 101 */     if (this.updateThinkingTime > 50) {
/* 102 */       this.updateThinkingTime = 0;
/*     */ 
/* 104 */       if (this.currentStepIndex > this.path.getLength() - 1) {
/* 105 */         reThink(this.current, this.map, this.path);
/*     */       }
/*     */     }
/*     */ 
/* 109 */     this.updatePlayerPositionTime += delta;
/*     */ 
/* 111 */     if (this.updatePlayerPositionTime > 5000) {
/* 112 */       this.updatePlayerPositionTime = 0;
/* 113 */       reThink(this.current, this.map, this.path);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void reThink(Vector2f current, Map map, Path path)
/*     */   {
/* 126 */     this.currentStepIndex = 0;
/* 127 */     path = null;
/* 128 */     updatePathToPlayer();
/*     */   }
/*     */ 
/*     */   private void updatePathToPlayer()
/*     */   {
/* 136 */     Player pl = Map.getPlayer();
/*     */     try {
/* 138 */       this.path = this.map.getUpdatedPath((int)this.current.getX() / this.map.getTileSize(), (int)this.current.getY() / this.map.getTileSize(), (int)pl.getX() / this.map.getTileSize(), (int)pl.getY() / this.map.getTileSize());
/*     */     }
/*     */     catch (NullPointerException e)
/*     */     {
/* 143 */       this.path = null;
/* 144 */       this.cannotFindPath = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void render(BasicGameState game, Graphics g)
/*     */   {
/* 156 */     if (this.path != null)
/* 157 */       for (int i = 0; i < this.path.getLength(); i++) {
/* 158 */         Path.Step a = this.path.getStep(i);
/* 159 */         this.dot.draw(a.getX() * this.map.getTileSize(), a.getY() * this.map.getTileSize());
/*     */       }
/*     */   }
/*     */ 
/*     */   public Path.Step getCurrentStep()
/*     */   {
/* 169 */     if (this.path == null) {
/* 170 */       updatePathToPlayer();
/*     */     }
/* 172 */     return this.path.getStep(this.currentStepIndex);
/*     */   }
/*     */ 
/*     */   public void goToNextStep(Vector2f position)
/*     */   {
/* 181 */     this.currentStepIndex += 1;
/* 182 */     this.current = position;
/*     */   }
/*     */ 
/*     */   public boolean isCannotFindPath() {
/* 186 */     return this.cannotFindPath;
/*     */   }
/*     */ 
/*     */   public void setCurrent(Vector2f current)
/*     */   {
/* 194 */     this.current = current;
/*     */   }
/*     */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.brains.RedGhostBrain
 * JD-Core Version:    0.6.2
 */