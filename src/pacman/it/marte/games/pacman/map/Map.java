/*     */ package pacman.it.marte.games.pacman.map;
/*     */ 
/*     */ import pacman.it.marte.games.pacman.actors.Block;
/*     */ import pacman.it.marte.games.pacman.actors.Brain;
/*     */ import pacman.it.marte.games.pacman.actors.EatGem;
/*     */ import pacman.it.marte.games.pacman.actors.Gem;
/*     */ import pacman.it.marte.games.pacman.actors.Ghost;
/*     */ import pacman.it.marte.games.pacman.actors.Player;
/*     */ import pacman.it.marte.games.pacman.base.Body;
/*     */ import pacman.it.marte.games.pacman.base.Entity;
/*     */ import pacman.it.marte.games.pacman.base.Entity.Role;
/*     */ import pacman.it.marte.games.pacman.base.Level;
/*     */ import pacman.it.marte.games.pacman.brains.BlueGhostBrain;
/*     */ import pacman.it.marte.games.pacman.brains.GrayGhostBrain;
/*     */ import pacman.it.marte.games.pacman.brains.GreenGhostBrain;
/*     */ import pacman.it.marte.games.pacman.brains.OrangeGhostBrain;
/*     */ import pacman.it.marte.games.pacman.brains.PinkGhostBrain;
/*     */ import pacman.it.marte.games.pacman.brains.RedGhostBrain;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import java.util.Vector;
/*     */ import org.newdawn.slick.GameContainer;
/*     */ import org.newdawn.slick.Graphics;
/*     */ import org.newdawn.slick.Image;
/*     */ import org.newdawn.slick.SlickException;
/*     */ import org.newdawn.slick.geom.Rectangle;
/*     */ import org.newdawn.slick.geom.Shape;
/*     */ import org.newdawn.slick.geom.Vector2f;
/*     */ import org.newdawn.slick.state.BasicGameState;
/*     */ import org.newdawn.slick.tiled.TiledMap;
/*     */ import org.newdawn.slick.util.Log;
/*     */ import org.newdawn.slick.util.pathfinding.AStarPathFinder;
/*     */ import org.newdawn.slick.util.pathfinding.Mover;
/*     */ import org.newdawn.slick.util.pathfinding.Path;
/*     */ import org.newdawn.slick.util.pathfinding.PathFindingContext;
/*     */ import org.newdawn.slick.util.pathfinding.TileBasedMap;
/*     */ 
/*     */ public class Map
/*     */   implements Entity, TileBasedMap
/*     */ {
/*     */   private TiledMap map;
/*     */   private static Player player;
/*     */   public static final int SIZE = 32;
/*  60 */   private Iterable<Body> blockingEnt = null;
/*     */   private boolean[][] blocked;
/*  65 */   private Iterable<Body> collectableEnt = null;
/*     */ 
/*  68 */   private Iterable<Body> eatGemEnt = null;
/*     */   private Iterable<Body> ghostEnt;
/*     */   private Vector2f base;
/*     */   private Hashtable<String, Vector2f> bluePoint;
/*  78 */   private Vector2f cornerUpLeft = new Vector2f(1000.0F, 1000.0F);
/*  79 */   private Vector2f cornerUpRight = new Vector2f(0.0F, 0.0F);
/*  80 */   private Vector2f cornerDownLeft = new Vector2f(0.0F, 1000.0F);
/*  81 */   private Vector2f cornerDownRight = new Vector2f(0.0F, 0.0F);
/*     */ 
/*     */   public Map(String mapPath)
/*     */     throws SlickException
/*     */   {
/*  91 */     this.map = new TiledMap(mapPath);
/*  92 */     player = loadPlayer();
/*  93 */     this.blocked = getCollisionMatrix(this.map, "blocked", "false");
/*  94 */     this.blockingEnt = loadBlockingEntities(LAYER.terrain, "blocked");
/*  95 */     this.collectableEnt = loadGemEntities(LAYER.bonus, "gem");
/*  96 */     this.eatGemEnt = loadEatGemEntities(LAYER.bonus, "eatGem");
/*  97 */     this.bluePoint = loadBluePoint(LAYER.entity, "position");
/*     */ 
/*  99 */     this.ghostEnt = loadGhostEntities(LAYER.entity, "ghost");
/* 100 */     this.base = loadBaseEntity(LAYER.entity, "base");
/*     */ 
/* 103 */     initCorners();
/*     */   }
/*     */ 
/*     */   public void render(BasicGameState game, Graphics g) {
/* 107 */     this.map.render(0, 0, 0, 0, 32, 32, LAYER.terrain.ordinal(), false);
/*     */   }
/*     */ 
/*     */   public void update(GameContainer game, int delta) {
/*     */   }
/*     */ 
/*     */   private Player loadPlayer() throws SlickException {
/* 114 */     Player pl = null;
/*     */     try
/*     */     {
/* 117 */       pl = new Player(this, Entity.Role.PLAYER, getPlayerStart(LAYER.entity, "player"));
/*     */     }
/*     */     catch (SlickException e) {
/* 120 */       throw new SlickException("cannot find player position " + e.getMessage());
/*     */     }
/*     */ 
/* 123 */     return pl;
/*     */   }
/*     */ 
/*     */   private Iterable<Body> loadBlockingEntities(LAYER layer, String prop)
/*     */   {
/* 136 */     Vector ent = new Vector();
/*     */ 
/* 138 */     for (int xAxis = 0; xAxis < this.map.getWidth(); xAxis++) {
/* 139 */       for (int yAxis = 0; yAxis < this.map.getHeight(); yAxis++) {
/* 140 */         int tileID = this.map.getTileId(xAxis, yAxis, layer.ordinal());
/*     */ 
/* 142 */         String value = this.map.getTileProperty(tileID, prop, "false");
/* 143 */         if ("true".equals(value)) {
/* 144 */           int blockSize = this.map.getTileHeight();
/* 145 */           int xrec = xAxis * blockSize;
/* 146 */           int yrec = yAxis * blockSize;
/* 147 */           Rectangle rect = new Rectangle(xrec, yrec, blockSize, blockSize);
/*     */ 
/* 151 */           Block block = new Block(Entity.Role.BLOCK, rect);
/* 152 */           ent.add(block);
/*     */         }
/*     */       }
/*     */     }
/* 156 */     return ent;
/*     */   }
/*     */ 
/*     */   private Iterable<Body> loadGemEntities(LAYER layer, String prop)
/*     */   {
/* 169 */     Vector ent = new Vector();
/*     */ 
/* 171 */     for (int xAxis = 0; xAxis < this.map.getWidth(); xAxis++) {
/* 172 */       for (int yAxis = 0; yAxis < this.map.getHeight(); yAxis++) {
/* 173 */         int tileID = this.map.getTileId(xAxis, yAxis, layer.ordinal());
/*     */ 
/* 175 */         String value = this.map.getTileProperty(tileID, prop, "false");
/* 176 */         if ("true".equals(value)) {
/* 177 */           int blockSize = this.map.getTileHeight();
/* 178 */           int xrec = xAxis * blockSize;
/* 179 */           int yrec = yAxis * blockSize;
/* 180 */           Rectangle rect = new Rectangle(xrec, yrec, blockSize, blockSize);
/*     */ 
/* 182 */           Image gem = this.map.getTileImage(xAxis, yAxis, layer.ordinal());
/* 183 */           Gem block = new Gem(Entity.Role.GOLD, rect, gem);
/* 184 */           ent.add(block);
/*     */         }
/*     */       }
/*     */     }
/* 188 */     return ent;
/*     */   }
/*     */ 
/*     */   private Iterable<Body> loadEatGemEntities(LAYER layer, String prop)
/*     */   {
/* 201 */     Vector ent = new Vector();
/*     */ 
/* 203 */     for (int xAxis = 0; xAxis < this.map.getWidth(); xAxis++) {
/* 204 */       for (int yAxis = 0; yAxis < this.map.getHeight(); yAxis++) {
/* 205 */         int tileID = this.map.getTileId(xAxis, yAxis, layer.ordinal());
/*     */ 
/* 207 */         String value = this.map.getTileProperty(tileID, prop, "false");
/* 208 */         if ("true".equals(value)) {
/* 209 */           int blockSize = this.map.getTileHeight();
/* 210 */           int xrec = xAxis * blockSize;
/* 211 */           int yrec = yAxis * blockSize;
/* 212 */           Rectangle rect = new Rectangle(xrec, yrec, blockSize, blockSize);
/*     */ 
/* 214 */           Image gem = this.map.getTileImage(xAxis, yAxis, layer.ordinal());
/* 215 */           EatGem block = new EatGem(Entity.Role.EATGEM, rect, gem);
/* 216 */           ent.add(block);
/*     */         }
/*     */       }
/*     */     }
/* 220 */     return ent;
/*     */   }
/*     */ 
/*     */   private Iterable<Body> loadGhostEntities(LAYER layer, String prop)
/*     */   {
/* 233 */     Vector ent = new Vector();
/*     */ 
/* 235 */     for (int xAxis = 0; xAxis < this.map.getWidth(); xAxis++) {
/* 236 */       for (int yAxis = 0; yAxis < this.map.getHeight(); yAxis++) {
/* 237 */         int tileID = this.map.getTileId(xAxis, yAxis, layer.ordinal());
/*     */ 
/* 239 */         String value = this.map.getTileProperty(tileID, prop, "false");
/* 240 */         if ("true".equals(value)) {
/* 241 */           int blockSize = this.map.getTileHeight();
/* 242 */           int xrec = xAxis * blockSize;
/* 243 */           int yrec = yAxis * blockSize;
/* 244 */           Rectangle rect = new Rectangle(xrec, yrec, blockSize, blockSize);
/*     */ 
/* 246 */           Ghost ghost = null;
/*     */           try {
/* 248 */             String color = this.map.getTileProperty(tileID, "red", "false");
/*     */ 
/* 250 */             Brain b = null;
/* 251 */             if (color.equals("true")) {
/* 252 */               Vector2f pos = new Vector2f();
/* 253 */               pos.set(xAxis * 32, yAxis * 32);
/* 254 */               b = new RedGhostBrain(this, pos);
/* 255 */               ghost = new Ghost(this, Entity.Role.GHOST, rect, b, 0);
/*     */             }
/* 257 */             color = this.map.getTileProperty(tileID, "pink", "false");
/* 258 */             if (color.equals("true")) {
/* 259 */               Vector2f pos = new Vector2f();
/* 260 */               pos.set(xAxis * 32, yAxis * 32);
/* 261 */               b = new PinkGhostBrain(this, pos);
/* 262 */               ghost = new Ghost(this, Entity.Role.GHOST, rect, b, 2);
/*     */             }
/* 264 */             color = this.map.getTileProperty(tileID, "blue", "false");
/* 265 */             if (color.equals("true")) {
/* 266 */               Vector2f pos = new Vector2f();
/* 267 */               pos.set(xAxis * 32, yAxis * 32);
/* 268 */               b = new BlueGhostBrain(this, pos);
/* 269 */               ghost = new Ghost(this, Entity.Role.GHOST, rect, b, 3);
/*     */             }
/* 271 */             color = this.map.getTileProperty(tileID, "orange", "false");
/* 272 */             if (color.equals("true")) {
/* 273 */               Vector2f pos = new Vector2f();
/* 274 */               pos.set(xAxis * 32, yAxis * 32);
/* 275 */               b = new OrangeGhostBrain(this, pos);
/* 276 */               ghost = new Ghost(this, Entity.Role.GHOST, rect, b, 1);
/*     */             }
/* 278 */             color = this.map.getTileProperty(tileID, "green", "false");
/* 279 */             if (color.equals("true")) {
/* 280 */               Vector2f pos = new Vector2f();
/* 281 */               pos.set(xAxis * 32, yAxis * 32);
/* 282 */               b = new GreenGhostBrain(this, pos);
/* 283 */               ghost = new Ghost(this, Entity.Role.GHOST, rect, b, 6);
/*     */             }
/* 285 */             color = this.map.getTileProperty(tileID, "gray", "false");
/* 286 */             if (color.equals("true")) {
/* 287 */               Vector2f pos = new Vector2f();
/* 288 */               pos.set(xAxis * 32, yAxis * 32);
/* 289 */               b = new GrayGhostBrain(this, pos);
/* 290 */               ghost = new Ghost(this, Entity.Role.GHOST, rect, b, 7);
/*     */ 
/* 292 */               ((GrayGhostBrain)ghost.getBrain()).setParent(ghost);
/*     */             }
/*     */           }
/*     */           catch (SlickException e)
/*     */           {
/* 297 */             Log.error(e);
/*     */           }
/* 299 */           ent.add(ghost);
/*     */         }
/*     */       }
/*     */     }
/* 303 */     return ent;
/*     */   }
/*     */ 
/*     */   private Hashtable<String, Vector2f> loadBluePoint(LAYER layer, String prop) {
/* 307 */     Hashtable positions = new Hashtable();
/*     */ 
/* 309 */     for (int xAxis = 0; xAxis < this.map.getWidth(); xAxis++) {
/* 310 */       for (int yAxis = 0; yAxis < this.map.getHeight(); yAxis++) {
/* 311 */         int tileID = this.map.getTileId(xAxis, yAxis, layer.ordinal());
/*     */ 
/* 313 */         String value = this.map.getTileProperty(tileID, prop, "false");
/* 314 */         if ("true".equals(value)) {
/* 315 */           int blockSize = this.map.getTileHeight();
/* 316 */           int xrec = xAxis * blockSize;
/* 317 */           int yrec = yAxis * blockSize;
/*     */ 
/* 319 */           String name = this.map.getTileProperty(tileID, "a", "false");
/* 320 */           if (name.equals("true")) {
/* 321 */             positions.put("a", new Vector2f(xrec, yrec));
/*     */           }
/* 323 */           name = this.map.getTileProperty(tileID, "b", "false");
/* 324 */           if (name.equals("true")) {
/* 325 */             positions.put("b", new Vector2f(xrec, yrec));
/*     */           }
/* 327 */           name = this.map.getTileProperty(tileID, "c", "false");
/* 328 */           if (name.equals("true")) {
/* 329 */             positions.put("c", new Vector2f(xrec, yrec));
/*     */           }
/* 331 */           name = this.map.getTileProperty(tileID, "d", "false");
/* 332 */           if (name.equals("true")) {
/* 333 */             positions.put("d", new Vector2f(xrec, yrec));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 339 */     return positions;
/*     */   }
/*     */ 
/*     */   private Vector2f loadBaseEntity(LAYER layer, String prop) {
/* 343 */     Vector2f base = new Vector2f(0.0F, 0.0F);
/*     */ 
/* 345 */     for (int xAxis = 0; xAxis < this.map.getWidth(); xAxis++) {
/* 346 */       for (int yAxis = 0; yAxis < this.map.getHeight(); yAxis++) {
/* 347 */         int tileID = this.map.getTileId(xAxis, yAxis, layer.ordinal());
/* 348 */         String value = this.map.getTileProperty(tileID, prop, "false");
/* 349 */         if ("true".equals(value)) {
/* 350 */           base = new Vector2f(xAxis, yAxis);
/* 351 */           return base;
/*     */         }
/*     */       }
/*     */     }
/* 355 */     return base;
/*     */   }
/*     */ 
/*     */   public void addToLevel(Level l)
/*     */   {
/* 360 */     Vector collectable = (Vector)getCollectableEnt();
/* 361 */     Iterator iterator = collectable.iterator();
/* 362 */     while (iterator.hasNext()) {
/* 363 */       Body bod = (Body)iterator.next();
/* 364 */       bod.addToLevel(l);
/* 365 */       l.add(bod);
/*     */     }
/*     */ 
/* 368 */     Vector eatableGem = (Vector)getEatGemEnt();
/* 369 */     Iterator iterator1 = eatableGem.iterator();
/* 370 */     while (iterator1.hasNext()) {
/* 371 */       Body bod = (Body)iterator1.next();
/* 372 */       bod.addToLevel(l);
/* 373 */       l.add(bod);
/*     */     }
/*     */ 
/* 376 */     l.add(player);
/*     */ 
/* 378 */     Vector ghosts = (Vector)getGhostEnt();
/* 379 */     for (Iterator iterator11 = ghosts.iterator(); iterator11.hasNext(); ) {
/* 380 */       Body bod = (Body)iterator11.next();
/* 381 */       bod.addToLevel(l);
/* 382 */       l.add(bod);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Entity.Role getRole() {
/* 387 */     return Entity.Role.MAP;
/*     */   }
/*     */ 
/*     */   public void onCollision(Entity obstacle)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void removeFromLevel(Level l)
/*     */   {
/*     */   }
/*     */ 
/*     */   public Iterable<Body> getBlockingEntities()
/*     */   {
/* 401 */     return this.blockingEnt;
/*     */   }
/*     */ 
/*     */   public Shape getPlayerStart(LAYER layer, String prop) {
/* 405 */     Shape player = null;
/*     */ 
/* 407 */     for (int xAxis = 0; xAxis < this.map.getWidth(); xAxis++) {
/* 408 */       for (int yAxis = 0; yAxis < this.map.getHeight(); yAxis++) {
/* 409 */         int tileID = this.map.getTileId(xAxis, yAxis, layer.ordinal());
/*     */ 
/* 411 */         String value = this.map.getTileProperty(tileID, prop, "false");
/* 412 */         if ("true".equals(value)) {
/* 413 */           int blockSize = this.map.getTileHeight();
/* 414 */           int xrec = xAxis * blockSize;
/* 415 */           int yrec = yAxis * blockSize;
/* 416 */           Rectangle rect = new Rectangle(xrec, yrec, blockSize, blockSize);
/*     */ 
/* 420 */           player = rect;
/*     */         }
/*     */       }
/*     */     }
/* 424 */     return player;
/*     */   }
/*     */ 
/*     */   public Iterable<Body> getCollectableEnt()
/*     */   {
/* 431 */     return this.collectableEnt;
/*     */   }
/*     */ 
/*     */   public Iterable<Body> getGhostEnt()
/*     */   {
/* 438 */     return this.ghostEnt;
/*     */   }
/*     */ 
/*     */   public static Player getPlayer()
/*     */   {
/* 445 */     return player;
/*     */   }
/*     */ 
/*     */   public boolean isToRemove() {
/* 449 */     return false;
/*     */   }
/*     */ 
/*     */   public Path getUpdatedPath(int sx, int sy, int ex, int ey)
/*     */     throws NullPointerException
/*     */   {
/* 457 */     AStarPathFinder pathfinder = new AStarPathFinder(this, 1000, false);
/* 458 */     Mover dummyMover = new Mover()
/*     */     {
/*     */     };
/* 460 */     Path path = pathfinder.findPath(dummyMover, sx, sy, ex, ey);
/* 461 */     if (path != null) {
/* 462 */       return path;
/*     */     }
/*     */ 
/* 465 */     throw new NullPointerException("cannot find a path");
/*     */   }
/*     */ 
/*     */   public boolean blocked(PathFindingContext contex, int x, int y)
/*     */   {
/* 470 */     if (this.blocked == null)
/* 471 */       this.blocked = getCollisionMatrix(this.map, "blocked", "false");
/*     */     try
/*     */     {
/* 474 */       if (this.blocked[x][y])
/* 475 */         return true;
/*     */     }
/*     */     catch (ArrayIndexOutOfBoundsException e) {
/* 478 */       return true;
/*     */     }
/* 480 */     return false;
/*     */   }
/*     */ 
/*     */   public float getCost(PathFindingContext contex, int x, int y) {
/* 484 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   public int getHeightInTiles() {
/* 488 */     return this.map.getHeight();
/*     */   }
/*     */ 
/*     */   public int getWidthInTiles() {
/* 492 */     return this.map.getWidth();
/*     */   }
/*     */ 
/*     */   public void pathFinderVisited(int x, int y)
/*     */   {
/*     */   }
/*     */ 
/*     */   private boolean[][] getCollisionMatrix(TiledMap map, String key, String value)
/*     */   {
/* 509 */     boolean[][] matrix = new boolean[map.getWidth()][map.getHeight()];
/* 510 */     for (int x = 0; x < map.getWidth(); x++) {
/* 511 */       for (int y = 0; y < map.getHeight(); y++) {
/* 512 */         int tileID = map.getTileId(x, y, 0);
/* 513 */         String temp = map.getTileProperty(tileID, key, value);
/* 514 */         if ("true".equals(temp)) {
/* 515 */           matrix[x][y] = true;
/*     */         }
/*     */       }
/*     */     }
/* 519 */     return matrix;
/*     */   }
/*     */ 
/*     */   public Iterable<Body> getEatGemEnt()
/*     */   {
/* 526 */     return this.eatGemEnt;
/*     */   }
/*     */ 
/*     */   private void initCorners()
/*     */   {
/* 533 */     boolean first = true;
/* 534 */     for (int x = 0; x < this.map.getWidth(); x++)
/* 535 */       for (int y = 0; y < this.map.getHeight(); y++) {
/* 536 */         boolean block = this.blocked[x][y];
/* 537 */         if (!block) {
/* 538 */           if (first) {
/* 539 */             first = false;
/* 540 */             this.cornerUpLeft.set(x, y);
/*     */           }
/* 542 */           if ((x >= this.cornerUpRight.x) && (y > this.cornerUpRight.y)) {
/* 543 */             this.cornerUpRight.set(x, y);
/*     */           }
/* 545 */           if ((x >= this.cornerDownLeft.x) && (y <= this.cornerDownLeft.y)) {
/* 546 */             this.cornerDownLeft.set(x, y);
/*     */           }
/* 548 */           if ((x >= this.cornerDownRight.x) && (y >= this.cornerDownRight.y))
/* 549 */             this.cornerDownRight.set(x, y);
/*     */         }
/*     */       }
/*     */   }
/*     */ 
/*     */   public Vector2f getRandomCorner()
/*     */   {
/* 561 */     Vector2f corner = new Vector2f(0.0F, 0.0F);
/*     */ 
/* 563 */     Random rnd = new Random();
/* 564 */     int value = rnd.nextInt(3);
/* 565 */     switch (value) {
/*     */     case 0:
/* 567 */       corner.set(this.cornerUpLeft);
/* 568 */       break;
/*     */     case 1:
/* 570 */       corner.set(this.cornerUpRight);
/* 571 */       break;
/*     */     case 2:
/* 573 */       corner.set(this.cornerDownLeft);
/* 574 */       break;
/*     */     case 3:
/* 576 */       corner.set(this.cornerDownRight);
/* 577 */       break;
/*     */     default:
/* 579 */       corner.set(this.cornerUpLeft);
/*     */     }
/*     */ 
/* 583 */     return corner;
/*     */   }
/*     */ 
/*     */   public Vector2f getRandomPoint()
/*     */   {
/* 592 */     Vector2f point = new Vector2f();
/* 593 */     Random rnd = new Random();
/* 594 */     boolean found = false;
/* 595 */     while (!found) {
/* 596 */       int rndX = rnd.nextInt(this.map.getWidth() - 1);
/* 597 */       int rndY = rnd.nextInt(this.map.getHeight() - 1);
/*     */ 
/* 599 */       if (!this.blocked[rndX][rndY]) {
/* 600 */         point.x = (rndX * 32);
/* 601 */         point.y = (rndY * 32);
/* 602 */         found = true;
/*     */       }
/*     */     }
/* 605 */     return point;
/*     */   }
/*     */ 
/*     */   public Vector2f getBase()
/*     */   {
/* 612 */     return this.base;
/*     */   }
/*     */ 
/*     */   public Hashtable<String, Vector2f> getBluePoint()
/*     */   {
/* 619 */     return this.bluePoint;
/*     */   }
/*     */ 
/*     */   public int getTileSize()
/*     */   {
/* 628 */     return this.map.getTileHeight();
/*     */   }
/*     */ 
/*     */   public static enum LAYER
/*     */   {
/*  54 */     terrain, entity, bonus;
/*     */   }
/*     */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.map.Map
 * JD-Core Version:    0.6.2
 */