/*    */ package pacman.it.marte.games.pacman.actors;
/*    */ 
/*    */ import pacman.it.marte.games.pacman.base.Body;
/*    */ import pacman.it.marte.games.pacman.base.Entity;
/*    */ import pacman.it.marte.games.pacman.base.Entity.Role;
/*    */ import pacman.it.marte.games.pacman.base.Level;
/*    */ import pacman.it.marte.games.pacman.map.Map;
/*    */ import pacman.it.marte.games.pacman.util.Collider;
/*    */ import org.newdawn.slick.GameContainer;
/*    */ import org.newdawn.slick.Graphics;
/*    */ import org.newdawn.slick.Image;
/*    */ import org.newdawn.slick.geom.Shape;
/*    */ import org.newdawn.slick.state.BasicGameState;
/*    */ 
/*    */ public class Gem extends Body
/*    */ {
/*    */   private Image image;
/* 25 */   private boolean toRemove = false;
/*    */ 
/*    */   public Gem(Entity.Role role, Shape shape, Image image) {
/* 28 */     super(role, shape);
/* 29 */     this.image = image;
/*    */   }
/*    */ 
/*    */   public void addToLevel(Level l) {
/* 33 */     l.add(this);
/*    */   }
/*    */ 
/*    */   public Entity.Role getRole() {
/* 37 */     return Entity.Role.GOLD;
/*    */   }
/*    */ 
/*    */   public void onCollision(Entity obstacle) {
/* 41 */     if (obstacle.getRole().equals(Entity.Role.PLAYER))
/* 42 */       this.toRemove = true;
/*    */   }
/*    */ 
/*    */   public void removeFromLevel(Level l)
/*    */   {
/* 47 */     l.remove(this);
/*    */   }
/*    */ 
/*    */   public void render(BasicGameState game, Graphics g) {
/* 51 */     g.drawImage(this.image, getX(), getY());
/*    */   }
/*    */ 
/*    */   public void update(GameContainer game, int delta) {
/* 55 */     if (!this.toRemove) {
/* 56 */       Player player = Map.getPlayer();
/* 57 */       Collider.testAndAlert(player, this);
/*    */     }
/*    */   }
/*    */ 
/*    */   public boolean isToRemove()
/*    */   {
/* 66 */     return this.toRemove;
/*    */   }
/*    */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.actors.Gem
 * JD-Core Version:    0.6.2
 */