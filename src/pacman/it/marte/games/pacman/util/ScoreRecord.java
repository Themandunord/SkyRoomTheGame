/*    */ package pacman.it.marte.games.pacman.util;
/*    */ 
/*    */ public class ScoreRecord
/*    */   implements Comparable<ScoreRecord>
/*    */ {
/*    */   private String name;
/*    */   private Integer points;
/*    */ 
/*    */   public ScoreRecord(String name, Integer points)
/*    */   {
/* 25 */     this.name = name;
/* 26 */     this.points = points;
/*    */   }
/*    */ 
/*    */   public int compareTo(ScoreRecord o) {
/* 30 */     ScoreRecord comp = o;
/* 31 */     if (comp.getPoints() == getPoints()) {
/* 32 */       return 0;
/*    */     }
/* 34 */     if (comp.getPoints().intValue() < getPoints().intValue()) {
/* 35 */       return -1;
/*    */     }
/* 37 */     return 1;
/*    */   }
/*    */ 
/*    */   public Integer getPoints()
/*    */   {
/* 45 */     return this.points;
/*    */   }
/*    */ 
/*    */   public void setPoints(Integer points)
/*    */   {
/* 53 */     this.points = points;
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 60 */     return this.name;
/*    */   }
/*    */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.util.ScoreRecord
 * JD-Core Version:    0.6.2
 */