/*     */ package pacman.it.marte.games.pacman.brains;
/*     */ 
/*     */ import pacman.it.marte.games.pacman.actors.Brain;
/*     */ import pacman.it.marte.games.pacman.actors.Player;
/*     */ import pacman.it.marte.games.pacman.map.Map;
/*     */ import java.util.Random;
/*     */ import org.newdawn.slick.Graphics;
/*     */ import org.newdawn.slick.Image;
/*     */ import org.newdawn.slick.SlickException;
/*     */ import org.newdawn.slick.geom.Circle;
/*     */ import org.newdawn.slick.geom.Vector2f;
/*     */ import org.newdawn.slick.state.BasicGameState;
/*     */ import org.newdawn.slick.util.Log;
/*     */ import org.newdawn.slick.util.pathfinding.Path;
/*     */ import org.newdawn.slick.util.pathfinding.Path.Step;
/*     */ 
/*     */ public class GreenGhostBrain
/*     */   implements Brain
/*     */ {
/*     */   private static final int THINKINGTIME = 30;
/*     */   private static final int FOLLOWTIME = 20000;
/*     */   private static final int CIRCLERADIUS = 2;
/*     */   private int updateThinkingTime;
/*     */   private int currentStepIndex;
/*     */   private Path path;
/*     */   private Image dot;
/*     */   private Vector2f current;
/*     */   private Map map;
/*     */   private int followTimer;
/*     */   private boolean cannotFindPath;
/*     */ 
/*     */   public GreenGhostBrain(Map map, Vector2f start)
/*     */     throws SlickException
/*     */   {
/*  69 */     this.map = map;
/*  70 */     this.current = start;
/*  71 */     init();
/*     */   }
/*     */ 
/*     */   public void init()
/*     */   {
/*  79 */     this.path = null;
/*  80 */     this.updateThinkingTime = 0;
/*  81 */     this.followTimer = 0;
/*  82 */     this.currentStepIndex = 0;
/*  83 */     this.cannotFindPath = false;
/*     */     try
/*     */     {
/*  86 */       this.dot = new Image("data/greendot.gif");
/*     */     } catch (SlickException e) {
/*  88 */       Log.error(e);
/*     */     }
/*  90 */     updatePathToPlayer();
/*     */   }
/*     */ 
/*     */   public void update(int delta)
/*     */   {
/*  98 */     if (this.path == null) {
/*  99 */       updatePathToPlayer();
/* 100 */       return;
/*     */     }
/*     */ 
/* 103 */     this.updateThinkingTime += delta;
/* 104 */     if (this.updateThinkingTime > 30) {
/* 105 */       this.updateThinkingTime = 0;
/*     */ 
/* 107 */       if (this.currentStepIndex > this.path.getLength() - 1) {
/* 108 */         reThink(this.current, this.map, this.path);
/*     */       }
/*     */     }
/*     */ 
/* 112 */     this.followTimer += delta;
/* 113 */     if (this.followTimer > 20000) {
/* 114 */       updatePathToPlayer();
/* 115 */       this.followTimer = 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void reThink(Vector2f current, Map map, Path path)
/*     */   {
/* 127 */     this.currentStepIndex = 0;
/* 128 */     path = null;
/* 129 */     updatePathToPlayer();
/*     */   }
/*     */ 
/*     */   private void updatePathToPlayer()
/*     */   {
/* 137 */     Circle circle = new Circle(Map.getPlayer().getCenterX(), Map.getPlayer().getCenterY(), this.map.getTileSize() * 2);
/*     */ 
/* 140 */     Random rnd = new Random();
/* 141 */     int point = rnd.nextInt(circle.getPointCount());
/*     */ 
/* 143 */     float[] points = circle.getPoint(point);
/* 144 */     Vector2f target = new Vector2f(points[0], points[1]);
/*     */ 
/* 146 */     if (Map.getPlayer().getPosition().distance(this.current) < 2 * this.map.getTileSize())
/*     */     {
/* 148 */       target = Map.getPlayer().getPosition();
/*     */     }
/*     */     try {
/* 151 */       this.path = this.map.getUpdatedPath((int)this.current.getX() / this.map.getTileSize(), (int)this.current.getY() / this.map.getTileSize(), (int)target.getX() / this.map.getTileSize(), (int)target.getY() / this.map.getTileSize());
/*     */     }
/*     */     catch (NullPointerException e)
/*     */     {
/* 157 */       this.path = null;
/* 158 */       this.cannotFindPath = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void render(BasicGameState game, Graphics g)
/*     */   {
/* 171 */     if (this.path != null)
/* 172 */       for (int i = 0; i < this.path.getLength(); i++) {
/* 173 */         Path.Step a = this.path.getStep(i);
/* 174 */         this.dot.draw(a.getX() * this.map.getTileSize(), a.getY() * this.map.getTileSize());
/*     */       }
/*     */   }
/*     */ 
/*     */   public Path.Step getCurrentStep()
/*     */   {
/* 184 */     if (this.path == null) {
/* 185 */       updatePathToPlayer();
/*     */     }
/* 187 */     return this.path.getStep(this.currentStepIndex);
/*     */   }
/*     */ 
/*     */   public void goToNextStep(Vector2f position)
/*     */   {
/* 196 */     this.currentStepIndex += 1;
/* 197 */     this.current = position;
/*     */   }
/*     */ 
/*     */   public boolean isCannotFindPath() {
/* 201 */     return this.cannotFindPath;
/*     */   }
/*     */ 
/*     */   public void setCurrent(Vector2f current)
/*     */   {
/* 208 */     this.current = current;
/*     */   }
/*     */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.brains.GreenGhostBrain
 * JD-Core Version:    0.6.2
 */