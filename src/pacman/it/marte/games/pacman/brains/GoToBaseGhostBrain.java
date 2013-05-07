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
/*     */ public class GoToBaseGhostBrain
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
/*     */ 
/*     */   public GoToBaseGhostBrain(Map map, Vector2f start)
/*     */     throws SlickException
/*     */   {
/*  56 */     this.map = map;
/*  57 */     this.current = start;
/*  58 */     init();
/*     */   }
/*     */ 
/*     */   public void init()
/*     */   {
/*  67 */     this.path = null;
/*  68 */     this.updateThinkingTime = 0;
/*  69 */     this.currentStepIndex = 0;
/*     */ 
/*  71 */     this.cannotFindPath = false;
/*     */     try {
/*  73 */       this.dot = new Image("data/dot.gif");
/*     */     } catch (SlickException e) {
/*  75 */       Log.error(e);
/*     */     }
/*  77 */     updatePathToBase();
/*     */   }
/*     */ 
/*     */   public void update(int delta)
/*     */   {
/*  85 */     if (this.path == null) {
/*  86 */       updatePathToBase();
/*  87 */       return;
/*     */     }
/*     */ 
/*  90 */     this.updateThinkingTime += delta;
/*  91 */     if (this.updateThinkingTime > 50) {
/*  92 */       this.updateThinkingTime = 0;
/*     */ 
/*  94 */       if (this.currentStepIndex > this.path.getLength() - 1)
/*  95 */         reThink(this.current, this.map, this.path);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void reThink(Vector2f current, Map map, Path path)
/*     */   {
/* 108 */     this.currentStepIndex = 0;
/* 109 */     path = null;
/* 110 */     updatePathToBase();
/*     */   }
/*     */ 
/*     */   private void updatePathToBase()
/*     */   {
/* 117 */     Vector2f base = this.map.getBase();
/*     */     try {
/* 119 */       this.path = this.map.getUpdatedPath((int)this.current.getX() / this.map.getTileSize(), (int)this.current.getY() / this.map.getTileSize(), (int)base.getX(), (int)base.getY());
/*     */     }
/*     */     catch (NullPointerException e)
/*     */     {
/* 123 */       this.cannotFindPath = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void render(BasicGameState game, Graphics g)
/*     */   {
/* 135 */     if (this.path != null)
/* 136 */       for (int i = 0; i < this.path.getLength(); i++) {
/* 137 */         Path.Step a = this.path.getStep(i);
/* 138 */         this.dot.draw(a.getX() * this.map.getTileSize(), a.getY() * this.map.getTileSize());
/*     */       }
/*     */   }
/*     */ 
/*     */   public Path.Step getCurrentStep()
/*     */   {
/* 148 */     if (this.path == null) {
/* 149 */       updatePathToBase();
/*     */     }
/* 151 */     return this.path.getStep(this.currentStepIndex);
/*     */   }
/*     */ 
/*     */   public void goToNextStep(Vector2f position)
/*     */   {
/* 160 */     this.currentStepIndex += 1;
/* 161 */     this.current = position;
/*     */   }
/*     */ 
/*     */   public boolean isCannotFindPath()
/*     */   {
/* 168 */     return this.cannotFindPath;
/*     */   }
/*     */ 
/*     */   public void setCurrent(Vector2f current)
/*     */   {
/* 175 */     this.current = current;
/*     */   }
/*     */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.brains.GoToBaseGhostBrain
 * JD-Core Version:    0.6.2
 */