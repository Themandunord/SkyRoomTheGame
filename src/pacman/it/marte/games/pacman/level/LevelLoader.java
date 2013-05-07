/*    */ package pacman.it.marte.games.pacman.level;
/*    */ 
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.IOException;
/*    */ import java.util.Hashtable;
/*    */ import java.util.Iterator;
/*    */ import java.util.Properties;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class LevelLoader
/*    */ {
/*    */   private Properties prop;
/*    */   private Hashtable<String, String> table;
/*    */ 
/*    */   public LevelLoader(String filePath)
/*    */     throws FileNotFoundException, IOException
/*    */   {
/* 31 */     this.prop = new Properties();
/* 32 */     this.prop.load(new FileInputStream(filePath));
/* 33 */     this.table = new Hashtable();
/*    */ 
/* 35 */     for (Iterator i$ = this.prop.keySet().iterator(); i$.hasNext(); ) { Object iterable_element = i$.next();
/* 36 */       this.table.put((String)iterable_element, this.prop.getProperty((String)iterable_element));
/*    */     }
/*    */   }
/*    */ 
/*    */   public Hashtable<String, String> getLevelChain()
/*    */   {
/* 46 */     return this.table;
/*    */   }
/*    */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.level.LevelLoader
 * JD-Core Version:    0.6.2
 */