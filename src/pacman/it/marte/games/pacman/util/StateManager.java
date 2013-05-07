/*    */ package pacman.it.marte.games.pacman.util;
/*    */ 
/*    */ import pacman.it.marte.games.pacman.base.Entity;
/*    */ import java.util.LinkedList;
/*    */ import org.newdawn.slick.GameContainer;
/*    */ import org.newdawn.slick.Graphics;
/*    */ 
/*    */ public class StateManager
/*    */ {
/*    */   private LinkedList<State> states;
/*    */   private State currentState;
/*    */   private State previousState;
/*    */ 
/*    */   public StateManager()
/*    */   {
/* 22 */     this.states = new LinkedList();
/* 23 */     this.currentState = null;
/*    */   }
/*    */ 
/*    */   public void add(State s) {
/* 27 */     this.states.add(s);
/* 28 */     if (this.currentState == null) {
/* 29 */       this.currentState = s;
/* 30 */       this.currentState.enter();
/*    */     }
/*    */   }
/*    */ 
/*    */   public void enter(Object o)
/*    */   {
/* 41 */     for (State s : this.states)
/* 42 */       if (s.equals(o)) {
/* 43 */         this.previousState = this.currentState;
/* 44 */         this.currentState = s;
/* 45 */         this.currentState.enter();
/*    */       }
/*    */   }
/*    */ 
/*    */   public void update(GameContainer game, int delta)
/*    */   {
/* 51 */     this.currentState.update(game, delta);
/*    */   }
/*    */ 
/*    */   public void onCollision(Entity obstacle) {
/* 55 */     this.currentState.onCollision(obstacle);
/*    */   }
/*    */ 
/*    */   public void render(Graphics g) {
/* 59 */     this.currentState.render(g);
/*    */   }
/*    */ 
/*    */   public State currentState() {
/* 63 */     return this.currentState;
/*    */   }
/*    */ 
/*    */   public State getPreviousState() {
/* 67 */     return this.previousState;
/*    */   }
/*    */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.util.StateManager
 * JD-Core Version:    0.6.2
 */