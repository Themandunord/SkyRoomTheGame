/*    */ package pacman.it.marte.games.pacman.util;
/*    */ 
/*    */ import pacman.it.marte.games.pacman.base.Body;
/*    */ import pacman.it.marte.games.pacman.base.Entity;
/*    */ import pacman.it.marte.games.pacman.base.Entity.Role;
/*    */ import pacman.it.marte.games.pacman.base.Level;
/*    */ import org.newdawn.slick.GameContainer;
/*    */ import org.newdawn.slick.Graphics;
/*    */ import org.newdawn.slick.geom.Line;
/*    */ import org.newdawn.slick.geom.Shape;
/*    */ import org.newdawn.slick.geom.Vector2f;
/*    */ import org.newdawn.slick.state.BasicGameState;
/*    */ 
/*    */ public class Ray extends Body
/*    */ {
/*    */   private Body one;
/*    */   private Body two;
/*    */ 
/*    */   public Ray(Body one, Body two)
/*    */   {
/* 19 */     super(Entity.Role.RAY, new Line(one.getCenterX(), one.getCenterY(), two.getCenterX(), two.getCenterY()));
/*    */ 
/* 21 */     this.one = one;
/* 22 */     this.two = two;
/*    */   }
/*    */ 
/*    */   public Ray(Body one, float dx, float dy) {
/* 26 */     super(Entity.Role.RAY, new Line(one.getCenterX(), one.getCenterY(), dx, dy, false));
/*    */   }
/*    */ 
/*    */   public Vector2f getDirection()
/*    */   {
/* 31 */     Vector2f d = new Vector2f();
/* 32 */     d.x = ((Line)this.shape).getDX();
/* 33 */     d.y = ((Line)this.shape).getDY();
/* 34 */     d.normalise();
/* 35 */     return d;
/*    */   }
/*    */ 
/*    */   public void render(Graphics g) {
/* 39 */     g.draw(this.shape);
/*    */   }
/*    */ 
/*    */   public void translateX(float x) {
/* 43 */     this.shape.setCenterX(getCenterX() + x);
/*    */   }
/*    */ 
/*    */   public void translateY(float y) {
/* 47 */     this.shape.setCenterY(getCenterY() + y);
/*    */   }
/*    */ 
/*    */   public void addToLevel(Level l) {
/*    */   }
/*    */ 
/*    */   public Entity.Role getRole() {
/* 54 */     return Entity.Role.RAY;
/*    */   }
/*    */ 
/*    */   public boolean isToRemove() {
/* 58 */     return false;
/*    */   }
/*    */ 
/*    */   public void onCollision(Entity obstacle) {
/*    */   }
/*    */ 
/*    */   public void removeFromLevel(Level l) {
/*    */   }
/*    */ 
/*    */   public void render(BasicGameState game, Graphics g) {
/* 68 */     g.draw(this.shape);
/*    */   }
/*    */ 
/*    */   public void update(GameContainer game, int delta)
/*    */   {
/*    */   }
/*    */ 
/*    */   public Body getOne()
/*    */   {
/* 78 */     return this.one;
/*    */   }
/*    */ 
/*    */   public Body getTwo()
/*    */   {
/* 85 */     return this.two;
/*    */   }
/*    */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.util.Ray
 * JD-Core Version:    0.6.2
 */