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
/*     */ public class OrangeGhostBrain
/*     */   implements Brain
/*     */ {
/*     */   private static final int THINKINGTIME = 50;
/*     */   private static final int FOLLOWTIME = 20000;
/*     */   private static final int LINEOFSIGHT = 6;
/*     */   private int updateThinkingTime;
/*     */   private int currentStepIndex;
/*     */   private Path path;
/*     */   private Image dot;
/*     */   private Vector2f current;
/*     */   private Map map;
/*     */   private int followTimer;
/*     */   private boolean cannotFindPath;
/*     */ 
/*     */   public OrangeGhostBrain(Map map, Vector2f start)
/*     */     throws SlickException
/*     */   {
/*  64 */     this.map = map;
/*  65 */     this.current = start;
/*  66 */     init();
/*     */   }
/*     */ 
/*     */   public void init()
/*     */   {
/*  76 */     this.path = null;
/*  77 */     this.updateThinkingTime = 0;
/*  78 */     this.followTimer = 0;
/*  79 */     this.currentStepIndex = 0;
/*  80 */     this.cannotFindPath = false;
/*     */     try
/*     */     {
/*  83 */       this.dot = new Image("data/orangedot.gif");
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
/* 109 */     this.followTimer += delta;
/* 110 */     if (this.followTimer > 20000) {
/* 111 */       updatePathToPlayer();
/* 112 */       this.followTimer = 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void reThink(Vector2f current, Map map, Path path)
/*     */   {
/* 124 */     this.currentStepIndex = 0;
/* 125 */     path = null;
/* 126 */     updatePathToPlayer();
/*     */   }
/*     */ 
/*     */   private void updatePathToPlayer()
/*     */   {
/* 134 */     Vector2f target = this.map.getRandomPoint();
/*     */ 
/* 136 */     if (Map.getPlayer().getPosition().distance(this.current) < 6 * this.map.getTileSize())
/*     */     {
/* 138 */       target = Map.getPlayer().getPosition();
/*     */     }
/*     */     try {
/* 141 */       this.path = this.map.getUpdatedPath((int)this.current.getX() / this.map.getTileSize(), (int)this.current.getY() / this.map.getTileSize(), (int)target.getX() / this.map.getTileSize(), (int)target.getY() / this.map.getTileSize());
/*     */     }
/*     */     catch (NullPointerException e)
/*     */     {
/* 147 */       this.path = null;
/* 148 */       this.cannotFindPath = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void render(BasicGameState game, Graphics g)
/*     */   {
/* 161 */     if (this.path != null)
/* 162 */       for (int i = 0; i < this.path.getLength(); i++) {
/* 163 */         Path.Step a = this.path.getStep(i);
/* 164 */         this.dot.draw(a.getX() * this.map.getTileSize(), a.getY() * this.map.getTileSize());
/*     */       }
/*     */   }
/*     */ 
/*     */   public Path.Step getCurrentStep()
/*     */   {
/* 174 */     if (this.path == null) {
/* 175 */       updatePathToPlayer();
/*     */     }
/* 177 */     return this.path.getStep(this.currentStepIndex);
/*     */   }
/*     */ 
/*     */   public void goToNextStep(Vector2f position)
/*     */   {
/* 186 */     this.currentStepIndex += 1;
/* 187 */     this.current = position;
/*     */   }
/*     */ 
/*     */   public boolean isCannotFindPath() {
/* 191 */     return this.cannotFindPath;
/*     */   }
/*     */ 
/*     */   public void setCurrent(Vector2f current)
/*     */   {
/* 198 */     this.current = current;
/*     */   }
/*     */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.brains.OrangeGhostBrain
 * JD-Core Version:    0.6.2
 */