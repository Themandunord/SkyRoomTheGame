/*    */ package pacman.it.marte.games.pacman.state;
/*    */ 
/*    */ import pacman.it.marte.games.pacman.level.LevelLoader;
/*    */ import pacman.it.marte.games.pacman.level.SimpleLevel;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.IOException;
/*    */ import java.util.Hashtable;
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
/*    */ public class GamePacman extends BasicGameState
/*    */ {
/*    */   private SimpleLevel currentLevel;
/* 25 */   private Hashtable<String, String> levelChain = new Hashtable();
/*    */   private StateBasedGame game;
/* 29 */   private Integer levelNumber = Integer.valueOf(1);
/*    */   private GameContainer container;
/*    */   public static final int ID = 1;
public GamePacman(int selector2) {
	// TODO Auto-generated constructor stub
}
/*    */ 
/*    */   public int getID()
/*    */   {
/* 37 */     return 9;
/*    */   }
/*    */ 
/*    */   public void init(GameContainer container, StateBasedGame game) throws SlickException
/*    */   {
/* 42 */     this.game = game;
/* 43 */     this.container = container;
/*    */     try
/*    */     {
/* 46 */       LevelLoader ll = new LevelLoader("data/maps/levels.properties");
/* 47 */       this.levelChain = ll.getLevelChain();
/*    */ 
/* 51 */       if (this.levelNumber.intValue() > this.levelChain.size() + 900) {
/* 52 */         this.levelNumber = Integer.valueOf(1);
/*    */       }
/* 54 */       if (this.levelNumber.intValue() <= this.levelChain.size()) {
/* 55 */         this.currentLevel = new SimpleLevel((String)this.levelChain.get(this.levelNumber.toString()));
/*    */       }
/*    */       else
/*    */       {
/* 59 */         this.levelNumber = Integer.valueOf(this.levelNumber.intValue() + 1000);
/* 60 */         game.enterState(14, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
/*    */ 
/* 63 */         return;
/*    */       }
/*    */     } catch (FileNotFoundException e) {
/* 66 */       e.printStackTrace();
/*    */     } catch (IOException e) {
/* 68 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
/*    */   {
/* 74 */     this.currentLevel.render(this, g);
/*    */   }
/*    */ 
/*    */   public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
/*    */   {
/* 79 */     this.currentLevel.update(container, delta);
/*    */ 
/* 81 */     if (this.currentLevel.isLevelLose()) {
/* 82 */       game.enterState(11);
/* 83 */       init(container, game);
/* 84 */       return;
/*    */     }
/* 86 */     if (this.currentLevel.isLevelWin()) {
/* 87 */       Integer localInteger1 = this.levelNumber; Integer localInteger2 = this.levelNumber = Integer.valueOf(this.levelNumber.intValue() + 1);
/* 88 */       game.enterState(12);
/* 89 */       init(container, game);
/* 90 */       return;
/*    */     }
/*    */   }
/*    */ 
/*    */   public void keyReleased(int key, char c) {
/* 95 */     if (key == 1) {
/* 96 */       this.game.enterState(10, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
/*    */     }
/*    */ 
/* 99 */     if (key == 60)
/*    */       try {
/* 101 */         if (!this.container.isFullscreen())
/* 102 */           this.container.setFullscreen(true);
/*    */         else
/* 104 */           this.container.setFullscreen(false);
/*    */       }
/*    */       catch (SlickException e) {
/* 107 */         Log.error(e);
/*    */       }
/*    */   }
/*    */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.state.Game
 * JD-Core Version:    0.6.2
 */