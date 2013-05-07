/*    */ package pacman.it.marte.games.pacman.util;
/*    */ 
/*    */ import org.newdawn.slick.Animation;
/*    */ import org.newdawn.slick.SpriteSheet;
/*    */ 
/*    */ public class SheetUtil
/*    */ {
/*    */   public static Animation getAnimationFromSheet(SpriteSheet sheet, int rigaSheet, int startNumFramAnimazione, int numFramAnimazione)
/*    */   {
/* 23 */     Animation animation = new Animation();
/* 24 */     for (int frame = startNumFramAnimazione; frame < numFramAnimazione; frame++) {
/* 25 */       animation.addFrame(sheet.getSprite(frame, rigaSheet), 150);
/*    */     }
/* 27 */     animation.setAutoUpdate(false);
/* 28 */     return animation;
/*    */   }
/*    */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.util.SheetUtil
 * JD-Core Version:    0.6.2
 */