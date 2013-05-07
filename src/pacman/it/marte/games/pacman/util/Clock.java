/*    */ package pacman.it.marte.games.pacman.util;
/*    */ 
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class Clock
/*    */ {
/*    */   private static long timer;
/*    */   private static long publicTimer;
/*    */   private static SimpleDateFormat sdf;
/*    */ 
/*    */   public Clock()
/*    */   {
/* 21 */     timer = 0L;
/* 22 */     publicTimer = 0L;
/* 23 */     sdf = new SimpleDateFormat("mm:ss");
/*    */   }
/*    */ 
/*    */   public void update(int delta) {
/* 27 */     timer += delta;
/* 28 */     if (timer >= 1000L) {
/* 29 */       publicTimer += 1L;
/* 30 */       timer = 0L;
/*    */     }
/*    */   }
/*    */ 
/*    */   public static String getTime()
/*    */   {
/* 38 */     return sdf.format(new Date(publicTimer * 1000L));
/*    */   }
/*    */ 
/*    */   public static long getTimer()
/*    */   {
/* 45 */     return publicTimer;
/*    */   }
/*    */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.util.Clock
 * JD-Core Version:    0.6.2
 */