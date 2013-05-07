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
/*    */ public class Block extends Body
/*    */ {
/*    */   public Block(Entity.Role role, Shape shape)
/*    */   {
/* 22 */     super(role, shape);
/*    */   }
/*    */ 
/*    */   public void addToLevel(Level l) {
/* 26 */     l.add(this);
/*    */   }
/*    */ 
/*    */   public Entity.Role getRole() {
/* 30 */     return Entity.Role.BLOCK;
/*    */   }
/*    */ 
/*    */   public void onCollision(Entity obstacle) {
/*    */   }
/*    */ 
/*    */   public void removeFromLevel(Level l) {
/* 37 */     l.remove(this);
/*    */   }
/*    */ 
/*    */   public void render(BasicGameState game, Graphics g)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void update(GameContainer game, int delta) {
/*    */   }
/*    */ 
/*    */   public boolean isToRemove() {
/* 48 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.actors.Block
 * JD-Core Version:    0.6.2
 */