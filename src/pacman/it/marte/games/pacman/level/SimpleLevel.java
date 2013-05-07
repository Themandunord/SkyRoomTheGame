/*     */ package pacman.it.marte.games.pacman.level;
/*     */ 
/*     */ import pacman.it.marte.games.pacman.actors.Ghost;
/*     */ import pacman.it.marte.games.pacman.actors.Ghost.State;
/*     */ import pacman.it.marte.games.pacman.actors.Player;
/*     */ import pacman.it.marte.games.pacman.base.Entity;
/*     */ import pacman.it.marte.games.pacman.base.Entity.Role;
/*     */ import pacman.it.marte.games.pacman.base.Level;
/*     */ import pacman.it.marte.games.pacman.base.Score;
/*     */ import pacman.it.marte.games.pacman.map.Map;
/*     */ import pacman.it.marte.games.pacman.util.Clock;
/*     */ import java.util.Iterator;
/*     */ import java.util.Vector;
/*     */ import org.newdawn.slick.AngelCodeFont;
/*     */ import org.newdawn.slick.GameContainer;
/*     */ import org.newdawn.slick.Graphics;
/*     */ import org.newdawn.slick.Image;
/*     */ import org.newdawn.slick.SlickException;
/*     */ import org.newdawn.slick.state.BasicGameState;
/*     */ import org.newdawn.slick.util.Log;
/*     */ 
/*     */ public class SimpleLevel
/*     */   implements Level
/*     */ {
/*     */   private Vector<Entity> entities;
/*     */   private Map map;
/*  34 */   private boolean levelEnded = false;
/*     */ 
/*  36 */   private boolean levelLose = false;
/*     */   private AngelCodeFont font;
/*     */   private Image box;
/*     */   private static Clock clock;
/*     */ 
/*     */   public SimpleLevel(String mapPath)
/*     */   {
/*     */     try
/*     */     {
/*  54 */       this.box = new Image("data/roundBox.png");
/*     */     } catch (SlickException e2) {
/*  56 */       Log.error(e2.getMessage());
/*     */     }
/*     */     try {
/*  59 */       this.font = new AngelCodeFont("data/demo2.fnt", "data/demo2_00.tga");
/*     */     } catch (SlickException e1) {
/*  61 */       Log.error(e1);
/*     */     }
/*     */ 
/*  64 */     this.entities = new Vector();
/*     */     try
/*     */     {
/*  67 */       this.map = new Map(mapPath);
/*     */     } catch (SlickException e) {
/*  69 */       Log.error("Map not found : " + mapPath);
/*  70 */       e.printStackTrace();
/*     */     }
/*     */ 
/*  73 */     add(this.map);
/*     */ 
/*  75 */     this.map.addToLevel(this);
/*     */ 
/*  77 */     clock = new Clock();
/*     */   }
/*     */ 
/*     */   public void add(Entity e) {
/*  81 */     this.entities.add(e);
/*     */   }
/*     */ 
/*     */   public void clear() {
/*  85 */     for (int i = 0; i < this.entities.size(); i++) {
/*  86 */       ((Entity)this.entities.get(i)).removeFromLevel(this);
/*  87 */       this.entities.remove(i);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void remove(Entity e) {
/*  92 */     this.entities.remove(e);
/*     */   }
/*     */ 
/*     */   public void render(BasicGameState game, Graphics g)
/*     */   {
/* 100 */     for (Entity e : this.entities) {
/* 101 */       e.render(game, g);
/*     */     }
/*     */ 
/* 104 */     this.box.draw(0.0F, 0.0F);
/* 105 */     this.font.drawString(10.0F, 10.0F, "Score: " + Score.getScore());
/* 106 */     this.font.drawString(10.0F, 40.0F, "Time : " + Clock.getTime());
/* 107 */     this.box.draw(0.0F, 550.0F);
/* 108 */     this.font.drawString(10.0F, 560.0F, "Lives : " + Map.getPlayer().getLive());
/*     */   }
/*     */ 
/*     */   public void update(GameContainer game, int delta)
/*     */   {
/* 116 */     Iterator iterator = this.entities.iterator();
/* 117 */     while (iterator.hasNext()) {
/* 118 */       Entity ent = (Entity)iterator.next();
/* 119 */       ent.update(game, delta);
/*     */     }
/*     */ 
/* 122 */     removeEntities();
/*     */ 
/* 124 */     isLevelWinned();
/*     */ 
/* 126 */     isLevelLosed();
/*     */ 
/* 128 */     Score.setScore(Map.getPlayer().getScore());
/*     */ 
/* 130 */     clock.update(delta);
/* 131 */     Score.setTime(Clock.getTimer());
/*     */   }
/*     */ 
/*     */   public boolean isLevelWin()
/*     */   {
/* 139 */     return this.levelEnded;
/*     */   }
/*     */ 
/*     */   public boolean isLevelLose()
/*     */   {
/* 146 */     return this.levelLose;
/*     */   }
/*     */ 
/*     */   private void isLevelWinned()
/*     */   {
/* 153 */     int gemLeft = 0;
/* 154 */     Iterator iterator = this.entities.iterator();
/* 155 */     while (iterator.hasNext()) {
/* 156 */       Entity ent = (Entity)iterator.next();
/* 157 */       if (ent.getRole().equals(Entity.Role.GOLD)) {
/* 158 */         gemLeft++;
/*     */       }
/*     */     }
/* 161 */     if (gemLeft == 0)
/* 162 */       this.levelEnded = true;
/*     */   }
/*     */ 
/*     */   private void isLevelLosed()
/*     */   {
/* 170 */     boolean playerFound = false;
/* 171 */     Iterator iterator = this.entities.iterator();
/* 172 */     while (iterator.hasNext()) {
/* 173 */       Entity ent = (Entity)iterator.next();
/* 174 */       if (ent.getRole().equals(Entity.Role.PLAYER)) {
/* 175 */         playerFound = true;
/*     */       }
/*     */     }
/* 178 */     if (!playerFound)
/* 179 */       this.levelLose = true;
/*     */   }
/*     */ 
/*     */   private void removeEntities()
/*     */   {
/* 189 */     Vector removeable = new Vector();
/* 190 */     Iterator iterator = this.entities.iterator();
/* 191 */     while (iterator.hasNext()) {
/* 192 */       Entity ent = (Entity)iterator.next();
/* 193 */       if (ent.isToRemove()) {
/* 194 */         removeable.add(ent);
/* 195 */         if (ent.getRole().equals(Entity.Role.EATGEM))
/*     */         {
/* 197 */           alertGhostsEatable();
/*     */         }
/*     */       }
/*     */     }
/* 201 */     this.entities.removeAll(removeable);
/*     */   }
/*     */ 
/*     */   public void alertGhostsEatable()
/*     */   {
/* 208 */     Iterator iterator = this.entities.iterator();
/* 209 */     while (iterator.hasNext()) {
/* 210 */       Entity ent = (Entity)iterator.next();
/* 211 */       if (ent.getRole().equals(Entity.Role.GHOST)) {
/* 212 */         Ghost gh = (Ghost)ent;
/* 213 */         if (!gh.getState().equals(Ghost.State.DEATH))
/* 214 */           gh.setEatable();
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.level.SimpleLevel
 * JD-Core Version:    0.6.2
 */