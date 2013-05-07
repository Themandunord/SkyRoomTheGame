/*    */ package pacman.it.marte.games.pacman.base;
/*    */ 
/*    */ import org.newdawn.slick.geom.Shape;
/*    */ import org.newdawn.slick.geom.Vector2f;
/*    */ 
/*    */ public abstract class Body
/*    */   implements Entity
/*    */ {
/*    */   public Shape shape;
/*    */   protected Entity.Role role;
/*    */ 
/*    */   public Body(Entity.Role role, Shape shape)
/*    */   {
/* 27 */     this.role = role;
/* 28 */     this.shape = shape;
/*    */   }
/*    */ 
/*    */   public float getCenterX() {
/* 32 */     return this.shape.getCenterX();
/*    */   }
/*    */ 
/*    */   public float getCenterY() {
/* 36 */     return this.shape.getCenterY();
/*    */   }
/*    */ 
/*    */   public float getX() {
/* 40 */     return this.shape.getX();
/*    */   }
/*    */ 
/*    */   public float getY() {
/* 44 */     return this.shape.getY();
/*    */   }
/*    */ 
/*    */   public float getWidth() {
/* 48 */     return this.shape.getMaxX() - this.shape.getX();
/*    */   }
/*    */ 
/*    */   public float getHeight() {
/* 52 */     return this.shape.getMaxY() - this.shape.getY();
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 57 */     return "Body[" + getCenterX() + "," + getCenterY() + "]";
/*    */   }
/*    */ 
/*    */   public Vector2f getPosition() {
/* 61 */     Vector2f pos = new Vector2f();
/* 62 */     pos.set(this.shape.getX(), this.shape.getY());
/* 63 */     return pos;
/*    */   }
/*    */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.base.Body
 * JD-Core Version:    0.6.2
 */