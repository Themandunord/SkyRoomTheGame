/*    */ package pacman.it.marte.games.pacman;
/*    */ 
/*    */ import pacman.it.marte.games.pacman.state.EndOfGame;
/*    */ import pacman.it.marte.games.pacman.state.GamePacman;
/*    */ import pacman.it.marte.games.pacman.state.LevelLose;
/*    */ import pacman.it.marte.games.pacman.state.LevelWin;
/*    */ import pacman.it.marte.games.pacman.state.MenuPacman;
/*    */ import pacman.it.marte.games.pacman.state.PausePacman;
/*    */ import pacman.it.marte.games.pacman.state.ScoreTable;
/*    */ import org.newdawn.slick.AppGameContainer;
/*    */ import org.newdawn.slick.GameContainer;
/*    */ import org.newdawn.slick.SlickException;
/*    */ import org.newdawn.slick.state.StateBasedGame;
/*    */ 
/*    */ public class PacManGame extends StateBasedGame
/*    */ {
/*    */   public PacManGame()
/*    */   {
/* 23 */     super("PacMan game");
/*    */   }
/*    */ 
/*    */   public static void main(String[] arguments)
/*    */   {
/*    */     try
/*    */     {
/* 36 */       AppGameContainer app = new AppGameContainer(new PacManGame());
/* 37 */       app.setDisplayMode(800, 600, false);
/* 38 */       app.setShowFPS(false);
/* 39 */       app.start();
/*    */     } catch (SlickException e) {
/* 41 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public void initStatesList(GameContainer container) throws SlickException
/*    */   {
/* 47 */     addState(new MenuPacman(0));
/* 48 */     addState(new GamePacman(1));
/* 49 */     addState(new PausePacman(2));
/* 50 */     addState(new LevelLose(3));
/* 51 */     addState(new LevelWin(4));
/* 52 */     addState(new ScoreTable(5));
/* 53 */     addState(new EndOfGame(6));
/*    */   }
/*    */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.PacManGame
 * JD-Core Version:    0.6.2
 */