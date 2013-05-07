/*    */ package pacman.it.marte.games.pacman.base;
/*    */ 
/*    */ public class Score
/*    */ {
/* 11 */   private static int score = 0;
/*    */ 
/* 13 */   private static long time = 0L;
/*    */ 
/*    */   public static int getScore()
/*    */   {
/* 19 */     return score * 10;
/*    */   }
/*    */ 
/*    */   public static int getFinalScore()
/*    */   {
/* 24 */     int temp = score * 10 + getTempBonus();
/* 25 */     return temp;
/*    */   }
/*    */ 
/*    */   public static void setScore(int score)
/*    */   {
/* 33 */     score = score;
/*    */   }
/*    */ 
/*    */   public static long getTime()
/*    */   {
/* 40 */     return time;
/*    */   }
/*    */ 
/*    */   public static void setTime(long time)
/*    */   {
/* 48 */     time = time;
/*    */   }
/*    */ 
/*    */   private static int getTempBonus() {
/* 52 */     int temporalBonus = 0;
/*    */ 
/* 54 */     if (time == 0L) {
/* 55 */       temporalBonus = 0;
/*    */     }
/* 57 */     if ((time != 0L) && (time <= 30L)) {
/* 58 */       temporalBonus = 10000;
/*    */     }
/* 60 */     if ((time > 30L) && (time <= 60L)) {
/* 61 */       temporalBonus = 1000;
/*    */     }
/* 63 */     if ((time > 60L) && (time <= 90L)) {
/* 64 */       temporalBonus = 100;
/*    */     }
/* 66 */     if (time >= 90L) {
/* 67 */       temporalBonus = 10;
/*    */     }
/* 69 */     return temporalBonus;
/*    */   }
/*    */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.base.Score
 * JD-Core Version:    0.6.2
 */