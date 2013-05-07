/*     */ package pacman.it.marte.games.pacman.state;
/*     */ 
/*     */ import pacman.it.marte.games.pacman.util.ScoreRecord;
/*     */ import pacman.it.marte.games.pacman.util.ScoreTableLoader;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import org.newdawn.slick.Color;
/*     */ import org.newdawn.slick.GameContainer;
/*     */ import org.newdawn.slick.Graphics;
/*     */ import org.newdawn.slick.SlickException;
/*     */ import org.newdawn.slick.state.BasicGameState;
/*     */ import org.newdawn.slick.state.StateBasedGame;
/*     */ import org.newdawn.slick.state.transition.FadeInTransition;
/*     */ import org.newdawn.slick.state.transition.FadeOutTransition;
/*     */ import org.newdawn.slick.util.Log;
/*     */ 
/*     */ public class ScoreTable extends BasicGameState
/*     */ {
/*     */   public static final int ID = 5;
/*     */   private StateBasedGame game;
/*     */   private ArrayList<ScoreRecord> scores;
/*     */   private int updateTimer;
/*     */   private GameContainer container;
public ScoreTable(int selector6) {
	// TODO Auto-generated constructor stub
}
/*     */ 
/*     */   public int getID()
/*     */   {
/*  37 */     return 13;
/*     */   }
/*     */ 
/*     */   public void init(GameContainer container, StateBasedGame game) throws SlickException
/*     */   {
/*  42 */     this.game = game;
/*  43 */     this.container = container;
/*  44 */     this.updateTimer = 0;
/*     */     try {
/*  46 */       ScoreTableLoader stl = new ScoreTableLoader("scoretable.properties");
/*  47 */       this.scores = new ArrayList(stl.loadScoreTable());
/*     */     }
/*     */    catch (FileNotFoundException e) {
/*  50 */       Log.error(e);
/*     */     } catch (IOException e) {
/*  52 */       Log.error(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
/*     */   {
/*  58 */     g.drawString("Pacman Scoretable", 100.0F, 50.0F);
/*     */ 
/*  61 */     g.setColor(Color.red);
/*  62 */     int counter = 0;
/*     */ 
/*  64 */     Collections.sort(this.scores);
/*     */ 
/*  66 */     for (ScoreRecord score : this.scores) {
/*  67 */       if (counter >= 20)
/*     */         break;
/*  69 */       g.drawString(counter + " - " + score.getName(), 100.0F, 90 + counter * 15);
/*     */ 
/*  71 */       g.drawString(score.getPoints().toString(), 300.0F, 90 + counter * 15);
/*     */ 
/*  73 */       counter++;
/*     */     }
/*     */ 
/*  76 */     g.setColor(Color.white);
/*     */ 
/*  78 */     g.drawString("Press enter to continue", 100.0F, 500.0F);
/*     */   }
/*     */ 
/*     */   public void update(GameContainer container, StateBasedGame game, int delta)
/*     */     throws SlickException
/*     */   {
/*  84 */     this.updateTimer += delta;
/*  85 */     if (this.updateTimer > 5000)
/*     */       try {
/*  87 */         ScoreTableLoader stl = new ScoreTableLoader("scoretable.properties");
/*     */ 
/*  89 */         this.scores = new ArrayList(stl.loadScoreTable());
/*     */       }
/*     */       catch (FileNotFoundException e) {
/*  92 */         Log.error(e);
/*     */       } catch (IOException e) {
/*  94 */         Log.error(e);
/*     */       }
/*     */   }
/*     */ 
/*     */   public void keyReleased(int key, char c)
/*     */   {
/* 100 */     if (key == 28) {
/* 101 */       this.game.enterState(8, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
/*     */     }
/*     */ 
/* 104 */     if (key == 60)
/*     */       try {
/* 106 */         if (!this.container.isFullscreen())
/* 107 */           this.container.setFullscreen(true);
/*     */         else
/* 109 */           this.container.setFullscreen(false);
/*     */       }
/*     */       catch (SlickException e) {
/* 112 */         Log.error(e);
/*     */       }
/*     */   }
/*     */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.state.ScoreTable
 * JD-Core Version:    0.6.2
 */