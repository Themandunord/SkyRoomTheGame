/*    */ package pacman.it.marte.games.pacman.actors;
/*    */ 
/*    */ import pacman.it.marte.games.pacman.base.Body;
/*    */ import pacman.it.marte.games.pacman.base.Entity;
/*    */ import pacman.it.marte.games.pacman.base.Entity.Role;
/*    */ import pacman.it.marte.games.pacman.base.Level;
/*    */ import org.newdawn.slick.GameContainer;
/*    */ import org.newdawn.slick.Graphics;
/*    */ import org.newdawn.slick.geom.Shape;
/*    */ import org.newdawn.slick.state.BasicGameState;
/*    */ 
/*    */ public class DummyBody extends Body
/*    */ {
/*    */   public DummyBody(Entity.Role role, Shape shape)
/*    */   {
/* 21 */     super(role, shape);
/*    */   }
/*    */ 
/*    */   public void addToLevel(Level l) {
/*    */   }
/*    */ 
/*    */   public Entity.Role getRole() {
/* 28 */     return Entity.Role.DUMMY;
/*    */   }
/*    */ 
/*    */   public void onCollision(Entity obstacle) {
/*    */   }
/*    */ 
/*    */   public void removeFromLevel(Level l) {
/*    */   }
/*    */ 
/*    */   public void render(BasicGameState game, Graphics g) {
/*    */   }
/*    */ 
/*    */   public void update(GameContainer game, int delta) {
/*    */   }
/*    */ 
/*    */   public boolean isToRemove() {
/* 44 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.actors.DummyBody
 * JD-Core Version:    0.6.2
 */