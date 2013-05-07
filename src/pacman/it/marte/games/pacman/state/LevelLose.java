/*     */ package pacman.it.marte.games.pacman.state;
/*     */ 
/*     */ import pacman.it.marte.games.pacman.base.Score;
/*     */ import pacman.it.marte.games.pacman.util.ScoreRecord;
/*     */ import pacman.it.marte.games.pacman.util.ScoreTableLoader;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
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
/*     */ public class LevelLose extends BasicGameState
/*     */ {
/*     */   public static final int ID = 3;
/*     */   private StateBasedGame game;
/*     */   private String playerName;
/*     */   private GameContainer container;
public LevelLose(int selector4) {
	// TODO Auto-generated constructor stub
}
/*     */ 
/*     */   public int getID()
/*     */   {
/*  32 */     return 11;
/*     */   }
/*     */ 
/*     */   public void init(GameContainer container, StateBasedGame game) throws SlickException
/*     */   {
/*  37 */     this.game = game;
/*  38 */     this.container = container;
/*  39 */     this.playerName = new String();
/*     */   }
/*     */ 
/*     */   public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
/*     */   {
/*  44 */     g.drawString("You Lose! !", 100.0F, 100.0F);
/*  45 */     g.drawString("Please enter your name!", 100.0F, 150.0F);
/*     */ 
/*  47 */     g.setColor(Color.red);
/*  48 */     g.drawString(this.playerName, 100.0F, 300.0F);
/*  49 */     g.setColor(Color.white);
/*     */ 
/*  51 */     g.drawString("Press enter to write your name into scoretable!", 100.0F, 450.0F);
/*     */   }
/*     */ 
/*     */   public void update(GameContainer container, StateBasedGame game, int delta)
/*     */     throws SlickException
/*     */   {
/*     */   }
/*     */ 
/*     */   public void keyReleased(int key, char c)
/*     */   {
/*  63 */     if (key == 28) {
/*  64 */       writeScore();
/*  65 */       this.game.enterState(13, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
/*     */     }
/*     */ 
/*  68 */     if ((key == 14) && 
/*  69 */       (this.playerName.length() > 0)) {
/*  70 */       this.playerName = this.playerName.substring(0, this.playerName.length() - 1);
/*     */     }
/*     */ 
/*  73 */     if (this.playerName.length() < 20) {
/*  74 */       if (key == 30) {
/*  75 */         this.playerName += "A";
/*     */       }
/*  77 */       if (key == 48) {
/*  78 */         this.playerName += "B";
/*     */       }
/*  80 */       if (key == 46) {
/*  81 */         this.playerName += "C";
/*     */       }
/*  83 */       if (key == 32) {
/*  84 */         this.playerName += "D";
/*     */       }
/*  86 */       if (key == 18) {
/*  87 */         this.playerName += "E";
/*     */       }
/*  89 */       if (key == 33) {
/*  90 */         this.playerName += "F";
/*     */       }
/*  92 */       if (key == 34) {
/*  93 */         this.playerName += "G";
/*     */       }
/*  95 */       if (key == 35) {
/*  96 */         this.playerName += "H";
/*     */       }
/*  98 */       if (key == 23) {
/*  99 */         this.playerName += "I";
/*     */       }
/* 101 */       if (key == 36) {
/* 102 */         this.playerName += "J";
/*     */       }
/* 104 */       if (key == 37) {
/* 105 */         this.playerName += "K";
/*     */       }
/* 107 */       if (key == 38) {
/* 108 */         this.playerName += "L";
/*     */       }
/* 110 */       if (key == 50) {
/* 111 */         this.playerName += "M";
/*     */       }
/* 113 */       if (key == 49) {
/* 114 */         this.playerName += "N";
/*     */       }
/* 116 */       if (key == 24) {
/* 117 */         this.playerName += "O";
/*     */       }
/* 119 */       if (key == 25) {
/* 120 */         this.playerName += "P";
/*     */       }
/* 122 */       if (key == 16) {
/* 123 */         this.playerName += "Q";
/*     */       }
/* 125 */       if (key == 19) {
/* 126 */         this.playerName += "R";
/*     */       }
/* 128 */       if (key == 31) {
/* 129 */         this.playerName += "S";
/*     */       }
/* 131 */       if (key == 20) {
/* 132 */         this.playerName += "T";
/*     */       }
/* 134 */       if (key == 22) {
/* 135 */         this.playerName += "U";
/*     */       }
/* 137 */       if (key == 47) {
/* 138 */         this.playerName += "V";
/*     */       }
/* 140 */       if (key == 17) {
/* 141 */         this.playerName += "W";
/*     */       }
/* 143 */       if (key == 45) {
/* 144 */         this.playerName += "X";
/*     */       }
/* 146 */       if (key == 21) {
/* 147 */         this.playerName += "Y";
/*     */       }
/* 149 */       if (key == 44) {
/* 150 */         this.playerName += "Z";
/*     */       }
/* 152 */       if (key == 57) {
/* 153 */         this.playerName += " ";
/*     */       }
/*     */     }
/*     */ 
/* 157 */     if (key == 60)
/*     */       try {
/* 159 */         if (!this.container.isFullscreen())
/* 160 */           this.container.setFullscreen(true);
/*     */         else
/* 162 */           this.container.setFullscreen(false);
/*     */       }
/*     */       catch (SlickException e) {
/* 165 */         Log.error(e);
/*     */       }
/*     */   }
/*     */ 
/*     */   private void writeScore()
/*     */   {
/*     */     try
/*     */     {
/* 176 */       ScoreTableLoader stl = new ScoreTableLoader("scoretable.properties");
/* 177 */       ArrayList scores = stl.loadScoreTable();
/* 178 */       ScoreRecord sr = new ScoreRecord(this.playerName, Integer.valueOf(Score.getFinalScore()));
/* 179 */       scores.add(sr);
/* 180 */       stl.saveScoreTable(scores);
/* 181 */       stl = null;
/*     */     } catch (FileNotFoundException e) {
/* 183 */       Log.error(e);
/*     */     } catch (IOException e) {
/* 185 */       Log.error(e);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.state.LevelLose
 * JD-Core Version:    0.6.2
 */