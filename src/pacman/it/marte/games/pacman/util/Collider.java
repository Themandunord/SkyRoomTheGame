/*     */ package pacman.it.marte.games.pacman.util;
/*     */ 
/*     */ import pacman.it.marte.games.pacman.base.Body;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedList;
/*     */ import org.newdawn.slick.geom.Shape;
/*     */ 
/*     */ public class Collider
/*     */ {
/*     */   public static void testAndAlert(Body subject, Iterable<Body> obstacles)
/*     */   {
/*  18 */     for (Body obstacle : obstacles)
/*  19 */       if ((!subject.equals(obstacle)) && (intersecting(subject, obstacle))) {
/*  20 */         subject.onCollision(obstacle);
/*  21 */         obstacle.onCollision(subject);
/*     */       }
/*     */   }
/*     */ 
/*     */   public static void testAndAlert(Body subject, Body obstacle)
/*     */   {
/*  28 */     if ((!subject.equals(obstacle)) && (intersecting(subject, obstacle))) {
/*  29 */       subject.onCollision(obstacle);
/*  30 */       obstacle.onCollision(subject);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean isColliding(Body subject, Body obstacle)
/*     */   {
/*  40 */     if ((!subject.equals(obstacle)) && (intersecting(subject, obstacle))) {
/*  41 */       return true;
/*     */     }
/*  43 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean intersecting(Body one, Body two)
/*     */   {
/*  52 */     return (checkBounds(one, two)) && (checkPrimitives(one.shape, two.shape));
/*     */   }
/*     */ 
/*     */   private static boolean checkBounds(Body one, Body two)
/*     */   {
/*  60 */     float x = (float)Math.pow(one.getCenterX() - two.getCenterX(), 2.0D);
/*  61 */     float y = (float)Math.pow(one.getCenterY() - two.getCenterY(), 2.0D);
/*     */ 
/*  63 */     float r1 = one.shape.getBoundingCircleRadius();
/*  64 */     float r2 = two.shape.getBoundingCircleRadius();
/*  65 */     float r = (float)Math.pow(r1 + r2, 2.0D);
/*     */ 
/*  67 */     return x + y <= r;
/*     */   }
/*     */ 
/*     */   private static boolean checkPrimitives(Shape one, Shape two)
/*     */   {
/*  76 */     return one.intersects(two);
/*     */   }
/*     */ 
/*     */   public static Body testAndReturn(Body subject, Iterable<Body> obstacles, Body[] exceptions)
/*     */   {
/*  89 */     for (Body obstacle : obstacles) {
/*  90 */       if ((!matches(obstacle, exceptions)) && (intersecting(subject, obstacle)))
/*     */       {
/*  92 */         return obstacle;
/*     */       }
/*     */     }
/*  95 */     return null;
/*     */   }
/*     */ 
/*     */   public static Body testAndReturn(Body subject, Iterable<Body> obstacles)
/*     */   {
/* 106 */     for (Body obstacle : obstacles) {
/* 107 */       if ((!subject.equals(obstacle)) && (intersecting(subject, obstacle))) {
/* 108 */         return obstacle;
/*     */       }
/*     */     }
/* 111 */     return null;
/*     */   }
/*     */ 
/*     */   public static ArrayList<Body> testAndReturnBodies(Body subject, Iterable<Body> obstacles)
/*     */   {
/* 116 */     ArrayList result = new ArrayList();
/* 117 */     for (Body obstacle : obstacles) {
/* 118 */       if ((!subject.equals(obstacle)) && (intersecting(subject, obstacle))) {
/* 119 */         result.add(obstacle);
/*     */       }
/*     */     }
/* 122 */     return result;
/*     */   }
/*     */ 
/*     */   public static boolean testAndFlag(Body subject, LinkedList<Body> obstacles, Body[] exceptions)
/*     */   {
/* 135 */     for (Body obstacle : obstacles) {
/* 136 */       if ((!matches(obstacle, exceptions)) && (intersecting(subject, obstacle)))
/*     */       {
/* 138 */         return true;
/*     */       }
/*     */     }
/* 141 */     return false;
/*     */   }
/*     */ 
/*     */   private static boolean matches(Body subject, Body[] obstacles) {
/* 145 */     for (Body obstacle : obstacles) {
/* 146 */       if (subject.equals(obstacle)) {
/* 147 */         return true;
/*     */       }
/*     */     }
/* 150 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.util.Collider
 * JD-Core Version:    0.6.2
 */