/*    */ package pacman.it.marte.games.pacman.state;
/*    */ 
/*    */ import pacman.it.marte.games.pacman.base.Score;
/*    */ import org.newdawn.slick.Color;
/*    */ import org.newdawn.slick.GameContainer;
/*    */ import org.newdawn.slick.Graphics;
/*    */ import org.newdawn.slick.SlickException;
/*    */ import org.newdawn.slick.state.BasicGameState;
/*    */ import org.newdawn.slick.state.StateBasedGame;
/*    */ import org.newdawn.slick.state.transition.FadeInTransition;
/*    */ import org.newdawn.slick.state.transition.FadeOutTransition;
/*    */ import org.newdawn.slick.util.Log;
/*    */ 
/*    */ public class LevelWin extends BasicGameState
/*    */ {
/*    */   public static final int ID = 4;
/*    */   private StateBasedGame game;
/*    */   private GameContainer container;
public LevelWin(int selector5) {
	// TODO Auto-generated constructor stub
}
/*    */ 
/*    */   public int getID()
/*    */   {
/* 24 */     return 12;
/*    */   }
/*    */ 
/*    */   public void init(GameContainer container, StateBasedGame game) throws SlickException
/*    */   {
/* 29 */     this.game = game;
/* 30 */     this.container = container;
/*    */   }
/*    */ 
/*    */   public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
/*    */   {
/* 35 */     g.drawString("Score :" + Score.getFinalScore(), 20.0F, 20.0F);
/* 36 */     g.drawString("Level WIN! Press ENTER to continue", 100.0F, 100.0F);
/*    */   }
/*    */ 
/*    */   public void update(GameContainer container, StateBasedGame game, int delta)
/*    */     throws SlickException
/*    */   {
/*    */   }
/*    */ 
/*    */   public void keyReleased(int key, char c)
/*    */   {
/* 47 */     if (key == 28) {
/* 48 */       this.game.enterState(9, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
/*    */     }
/*    */ 
/* 51 */     if (key == 60)
/*    */       try {
/* 53 */         if (!this.container.isFullscreen())
/* 54 */           this.container.setFullscreen(true);
/*    */         else
/* 56 */           this.container.setFullscreen(false);
/*    */       }
/*    */       catch (SlickException e) {
/* 59 */         Log.error(e);
/*    */       }
/*    */   }
/*    */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.state.LevelWin
 * JD-Core Version:    0.6.2
 */