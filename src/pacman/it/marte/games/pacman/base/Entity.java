/*    */ package pacman.it.marte.games.pacman.base;
/*    */ 
/*    */ public abstract interface Entity extends Animatable
/*    */ {
/*    */   public abstract Role getRole();
/*    */ 
/*    */   public abstract void addToLevel(Level paramLevel);
/*    */ 
/*    */   public abstract void removeFromLevel(Level paramLevel);
/*    */ 
/*    */   public abstract void onCollision(Entity paramEntity);
/*    */ 
/*    */   public abstract boolean isToRemove();
/*    */ 
/*    */   public static enum Role
/*    */   {
/* 15 */     PLAYER, GHOST, GOLD, BLOCK, DUMMY, MAP, EATGEM, RAY;
/*    */   }
/*    */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.base.Entity
 * JD-Core Version:    0.6.2
 */