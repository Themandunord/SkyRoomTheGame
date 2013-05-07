/*     */ package pacman.it.marte.games.pacman.brains;
/*     */ 
/*     */ import pacman.it.marte.games.pacman.actors.Brain;
/*     */ import pacman.it.marte.games.pacman.actors.Player;
/*     */ import pacman.it.marte.games.pacman.map.Map;
/*     */ import java.util.Hashtable;
/*     */ import org.newdawn.slick.Graphics;
/*     */ import org.newdawn.slick.Image;
/*     */ import org.newdawn.slick.SlickException;
/*     */ import org.newdawn.slick.geom.Vector2f;
/*     */ import org.newdawn.slick.state.BasicGameState;
/*     */ import org.newdawn.slick.util.Log;
/*     */ import org.newdawn.slick.util.pathfinding.Path;
/*     */ import org.newdawn.slick.util.pathfinding.Path.Step;
/*     */ 
/*     */ public class BlueGhostBrain
/*     */   implements Brain
/*     */ {
/*     */   private static final int THINKINTIME = 50;
/*     */   private static final float DISTANCE = 1.0F;
/*     */   private static final int DISTANCE_PLAYER = 5;
/*     */   private int updateThinkingTime;
/*     */   private int updatePlayerPositionTime;
/*     */   private int currentStepIndex;
/*     */   private Path path;
/*     */   private Image dot;
/*     */   private Vector2f current;
/*     */   private Map map;
/*     */   private boolean cannotFindPath;
/*  61 */   private Vector2f a = null;
/*  62 */   private Vector2f b = null;
/*  63 */   private Vector2f c = null;
/*  64 */   private Vector2f d = null;
/*     */ 
/*     */   public BlueGhostBrain(Map map, Vector2f start)
/*     */     throws SlickException
/*     */   {
/*  74 */     this.map = map;
/*  75 */     this.current = start;
/*  76 */     init();
/*     */   }
/*     */ 
/*     */   public void init()
/*     */   {
/*  84 */     Hashtable points = this.map.getBluePoint();
/*  85 */     if (points != null) {
/*  86 */       this.a = ((Vector2f)points.get("a"));
/*  87 */       this.b = ((Vector2f)points.get("b"));
/*  88 */       this.c = ((Vector2f)points.get("c"));
/*  89 */       this.d = ((Vector2f)points.get("d"));
/*     */     }
/*     */ 
/*  92 */     this.path = null;
/*  93 */     this.updateThinkingTime = 0;
/*  94 */     this.updatePlayerPositionTime = 0;
/*  95 */     this.currentStepIndex = 0;
/*  96 */     this.cannotFindPath = false;
/*     */     try
/*     */     {
/*  99 */       this.dot = new Image("data/bluedot.gif");
/*     */     } catch (SlickException e) {
/* 101 */       Log.error(e);
/*     */     }
/* 103 */     updatePathToPlayer();
/*     */   }
/*     */ 
/*     */   public void update(int delta)
/*     */   {
/* 111 */     if (this.path == null) {
/* 112 */       updatePathToPlayer();
/* 113 */       return;
/*     */     }
/*     */ 
/* 116 */     this.updateThinkingTime += delta;
/* 117 */     if (this.updateThinkingTime > 50) {
/* 118 */       this.updateThinkingTime = 0;
/*     */ 
/* 120 */       if (this.currentStepIndex > this.path.getLength() - 1) {
/* 121 */         reThink(this.current, this.map, this.path);
/*     */       }
/*     */     }
/*     */ 
/* 125 */     this.updatePlayerPositionTime += delta;
/*     */   }
/*     */ 
/*     */   private void reThink(Vector2f current, Map map, Path path)
/*     */   {
/* 136 */     this.currentStepIndex = 0;
/* 137 */     path = null;
/* 138 */     updatePathToPlayer();
/*     */   }
/*     */ 
/*     */   private void updatePathToPlayer()
/*     */   {
/* 146 */     Vector2f target = null;
/*     */ 
/* 148 */     if (this.current.distance(this.a) < 1.0F) {
/* 149 */       target = this.b;
/*     */     }
/* 151 */     if (this.current.distance(this.b) < 1.0F) {
/* 152 */       target = this.c;
/*     */     }
/* 154 */     if (this.current.distance(this.c) < 1.0F) {
/* 155 */       target = this.d;
/*     */     }
/* 157 */     if (this.current.distance(this.d) < 1.0F) {
/* 158 */       target = this.a;
/*     */     }
/* 160 */     if (target == null) {
/* 161 */       target = this.a;
/*     */     }
/* 163 */     if (Map.getPlayer().getPosition().distance(this.current) < 5 * this.map.getTileSize())
/*     */     {
/* 165 */       target = Map.getPlayer().getPosition();
/*     */     }
/*     */     try {
/* 168 */       this.path = this.map.getUpdatedPath((int)this.current.getX() / this.map.getTileSize(), (int)this.current.getY() / this.map.getTileSize(), (int)target.getX() / this.map.getTileSize(), (int)target.getY() / this.map.getTileSize());
/*     */     }
/*     */     catch (NullPointerException e)
/*     */     {
/* 174 */       this.path = null;
/* 175 */       this.cannotFindPath = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void render(BasicGameState game, Graphics g)
/*     */   {
/* 188 */     if (this.path != null)
/* 189 */       for (int i = 0; i < this.path.getLength(); i++) {
/* 190 */         Path.Step a = this.path.getStep(i);
/* 191 */         this.dot.draw(a.getX() * this.map.getTileSize(), a.getY() * this.map.getTileSize());
/*     */       }
/*     */   }
/*     */ 
/*     */   public Path.Step getCurrentStep()
/*     */   {
/* 201 */     if (this.path == null) {
/* 202 */       updatePathToPlayer();
/*     */     }
/* 204 */     return this.path.getStep(this.currentStepIndex);
/*     */   }
/*     */ 
/*     */   public void goToNextStep(Vector2f position)
/*     */   {
/* 213 */     this.currentStepIndex += 1;
/* 214 */     this.current = position;
/*     */   }
/*     */ 
/*     */   public boolean isCannotFindPath()
/*     */   {
/* 221 */     return this.cannotFindPath;
/*     */   }
/*     */ 
/*     */   public void setCurrent(Vector2f current)
/*     */   {
/* 228 */     this.current = current;
/*     */   }
/*     */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.brains.BlueGhostBrain
 * JD-Core Version:    0.6.2
 */