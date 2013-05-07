/*     */ package pacman.it.marte.games.pacman.state;
/*     */ 
/*     */ import pacman.it.marte.games.pacman.base.Score;
/*     */ import pacman.it.marte.games.pacman.util.Clock;
/*     */ import org.newdawn.slick.AngelCodeFont;
/*     */ import org.newdawn.slick.Color;
/*     */ import org.newdawn.slick.GameContainer;
/*     */ import org.newdawn.slick.Graphics;
/*     */ import org.newdawn.slick.Image;
/*     */ import org.newdawn.slick.SlickException;
/*     */ import org.newdawn.slick.state.BasicGameState;
/*     */ import org.newdawn.slick.state.StateBasedGame;
/*     */ import org.newdawn.slick.state.transition.FadeInTransition;
/*     */ import org.newdawn.slick.state.transition.FadeOutTransition;
/*     */ import org.newdawn.slick.util.Log;
/*     */ 
/*     */ public class PausePacman extends BasicGameState
/*     */ {
/*     */   public static final int ID = 2;
/*     */   private StateBasedGame game;
/*     */   private AngelCodeFont font;
/*  29 */   private String[] options = { "Continue", "Quit" };
/*     */   private Image logo;
/*     */   private int selected;
/*     */   private FadeOutTransition fot;
/*     */   private FadeInTransition fit;
/*     */   private GameContainer container;
public PausePacman(int selector3) {
	// TODO Auto-generated constructor stub
}
/*     */ 
/*     */   public int getID()
/*     */   {
/*  44 */     return 10;
/*     */   }
/*     */ 
/*     */   public void init(GameContainer container, StateBasedGame game) throws SlickException
/*     */   {
/*  49 */     this.game = game;
/*  50 */     this.container = container;
/*  51 */     this.fot = new FadeOutTransition(Color.black);
/*  52 */     this.fit = new FadeInTransition(Color.black);
/*     */     try {
/*  54 */       this.font = new AngelCodeFont("data/demo2.fnt", "data/demo2_00.tga");
/*  55 */       this.logo = new Image("data/logo.gif");
/*     */     } catch (SlickException e1) {
/*  57 */       Log.error("font non found " + e1.getMessage());
/*  58 */       e1.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void render(GameContainer container, StateBasedGame game, Graphics g)
/*     */     throws SlickException
/*     */   {
/*  65 */     g.setFont(this.font);
/*  66 */     g.drawImage(this.logo, 320.0F, 20.0F);
/*     */ 
/*  68 */     g.drawString("Score :" + Score.getScore(), 200.0F, 80.0F);
/*     */ 
/*  70 */     g.drawString("Time : " + Clock.getTime(), 200.0F, 120.0F);
/*     */ 
/*  72 */     for (int i = 0; i < this.options.length; i++) {
/*  73 */       g.drawString(this.options[i], 400 - this.font.getWidth(this.options[i]) / 2, 200 + i * 50);
/*     */ 
/*  75 */       if (this.selected == i)
/*  76 */         g.drawRect(200.0F, 190 + i * 50, 400.0F, 50.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
/*     */   {
/*     */   }
/*     */ 
/*     */   public void keyReleased(int key, char c)
/*     */   {
/*  86 */     if (key == 208) {
/*  87 */       this.selected += 1;
/*  88 */       if (this.selected >= this.options.length) {
/*  89 */         this.selected = 0;
/*     */       }
/*     */     }
/*  92 */     if (key == 200) {
/*  93 */       this.selected -= 1;
/*  94 */       if (this.selected < 0) {
/*  95 */         this.selected = (this.options.length - 1);
/*     */       }
/*     */     }
/*  98 */     if (key == 28) {
/*  99 */       if (this.selected == 0) {
/* 100 */         this.game.enterState(9, this.fot, this.fit);
/*     */       }
/* 102 */       if (this.selected == 1) {
/* 103 */         this.game.getContainer().exit();
/*     */       }
/*     */     }
/* 106 */     if (key == 60)
/*     */       try {
/* 108 */         if (!this.container.isFullscreen())
/* 109 */           this.container.setFullscreen(true);
/*     */         else
/* 111 */           this.container.setFullscreen(false);
/*     */       }
/*     */       catch (SlickException e) {
/* 114 */         Log.error(e);
/*     */       }
/*     */   }
/*     */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.state.Pause
 * JD-Core Version:    0.6.2
 */