/*     */ package pacman.it.marte.games.pacman.state;
/*     */ 
/*     */ import org.newdawn.slick.AngelCodeFont;
/*     */ import org.newdawn.slick.Color;
/*     */ import org.newdawn.slick.GameContainer;
/*     */ import org.newdawn.slick.Graphics;
/*     */ import org.newdawn.slick.Image;
/*     */ import org.newdawn.slick.SlickException;
/*     */ import org.newdawn.slick.state.BasicGameState;
/*     */ import org.newdawn.slick.state.GameState;
/*     */ import org.newdawn.slick.state.StateBasedGame;
/*     */ import org.newdawn.slick.state.transition.FadeInTransition;
/*     */ import org.newdawn.slick.state.transition.FadeOutTransition;
/*     */ import org.newdawn.slick.util.Log;
/*     */ 
/*     */ public class MenuPacman extends BasicGameState
/*     */ {
/*     */   public static final int ID = 0;
/*     */   private StateBasedGame game;
/*     */   private AngelCodeFont font;
/*  26 */   private String[] options = { "Start", "Scoretable", "Quit" };
/*     */   private Image logo;
/*     */   private int selected;
/*     */   private FadeOutTransition fot;
/*     */   private FadeInTransition fit;
/*     */   private GameContainer container;
public MenuPacman(int selector1) {
	// TODO Auto-generated constructor stub
}
/*     */ 
/*     */   public int getID()
/*     */   {
/*  41 */     return 8;
/*     */   }
/*     */ 
/*     */   public void init(GameContainer container, StateBasedGame game) throws SlickException
/*     */   {
/*  46 */     this.container = container;
/*  47 */     this.game = game;
/*  48 */     this.fot = new FadeOutTransition(Color.black);
/*  49 */     this.fit = new FadeInTransition(Color.black);
/*     */     try {
/*  51 */       this.font = new AngelCodeFont("data/demo2.fnt", "data/demo2_00.tga");
/*  52 */       this.logo = new Image("data/logo.gif");
/*     */     } catch (SlickException e1) {
/*  54 */       Log.error("font non found " + e1.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void render(GameContainer container, StateBasedGame game, Graphics g)
/*     */     throws SlickException
/*     */   {
/*  61 */     g.drawString("Arrow - movement, ENTER - select options, F2 toggle fullscreen", 20.0F, 580.0F);
/*     */ 
/*  65 */     g.drawString("http://code.google.com/p/jpacman/", 520.0F, 580.0F);
/*     */ 
/*  67 */     g.setFont(this.font);
/*  68 */     g.drawImage(this.logo, 320.0F, 50.0F);
/*     */ 
/*  70 */     for (int i = 0; i < this.options.length; i++) {
/*  71 */       g.drawString(this.options[i], 400 - this.font.getWidth(this.options[i]) / 2, 200 + i * 50);
/*     */ 
/*  73 */       if (this.selected == i)
/*  74 */         g.drawRect(200.0F, 190 + i * 50, 400.0F, 50.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void update(GameContainer container, StateBasedGame game, int delta)
/*     */     throws SlickException
/*     */   {
/*     */   }
/*     */ 
/*     */   public void keyReleased(int key, char c)
/*     */   {
/*  85 */     if (key == 208) {
/*  86 */       this.selected += 1;
/*  87 */       if (this.selected >= this.options.length) {
/*  88 */         this.selected = 0;
/*     */       }
/*     */     }
/*  91 */     if (key == 200) {
/*  92 */       this.selected -= 1;
/*  93 */       if (this.selected < 0) {
/*  94 */         this.selected = (this.options.length - 1);
/*     */       }
/*     */     }
/*  97 */     if (key == 28) {
/*  98 */       if (this.selected == 0) {
/*     */         try {
/* 100 */           this.game.getState(9).init(this.container, this.game);
/*     */         } catch (SlickException e) {
/* 102 */           Log.error(e);
/*     */         }
/* 104 */         this.game.enterState(9, this.fot, this.fit);
/*     */       }
/* 106 */       if (this.selected == 1) {
/* 107 */         this.game.enterState(13, this.fot, this.fit);
/*     */       }
/* 109 */       if (this.selected == 2) {
/* 110 */         this.game.getContainer().exit();
/*     */       }
/*     */     }
/* 113 */     if (key == 60)
/*     */       try {
/* 115 */         if (!this.container.isFullscreen())
/* 116 */           this.container.setFullscreen(true);
/*     */         else
/* 118 */           this.container.setFullscreen(false);
/*     */       }
/*     */       catch (SlickException e) {
/* 121 */         Log.error(e);
/*     */       }
/*     */   }
/*     */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.state.Menu
 * JD-Core Version:    0.6.2
 */