/*    */ package pacman.it.marte.games.pacman.util;
/*    */ 
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.Hashtable;
/*    */ import java.util.Iterator;
/*    */ import java.util.Properties;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class ScoreTableLoader
/*    */ {
/*    */   private Properties prop;
/*    */   private Hashtable<String, String> table;
/*    */   private String filePath;
/*    */ 
/*    */   public ScoreTableLoader(String filePath)
/*    */     throws FileNotFoundException, IOException
/*    */   {
/* 36 */     this.prop = new Properties();
/* 37 */     this.prop.load(new FileInputStream(filePath));
/* 38 */     this.filePath = filePath;
/* 39 */     this.table = new Hashtable();
/*    */ 
/* 41 */     for (Iterator i$ = this.prop.keySet().iterator(); i$.hasNext(); ) { Object iterable_element = i$.next();
/* 42 */       this.table.put((String)iterable_element, this.prop.getProperty((String)iterable_element));
/*    */     }
/*    */   }
/*    */ 
/*    */   public ArrayList<ScoreRecord> loadScoreTable()
/*    */   {
/* 51 */     ArrayList srs = new ArrayList();
/*    */ 
/* 53 */     for (String key : this.table.keySet()) {
/* 54 */       String value = (String)this.table.get(key);
/* 55 */       ScoreRecord sr = new ScoreRecord(key, new Integer(value));
/* 56 */       srs.add(sr);
/*    */     }
/*    */ 
/* 59 */     Collections.sort(srs);
/*    */ 
/* 61 */     return srs;
/*    */   }
/*    */ 
/*    */   public boolean saveScoreTable(ArrayList<ScoreRecord> scores)
/*    */   {
/* 72 */     if (scores == null)
/* 73 */       return false;
/* 74 */     boolean result = true;
/*    */ 
/* 76 */     Hashtable towrite = new Hashtable();
/*    */ 
/* 78 */     for (ScoreRecord scoreRecord : scores) {
/* 79 */       towrite.put(scoreRecord.getName(), scoreRecord.getPoints().toString());
/*    */     }
/*    */ 
/* 83 */     this.prop.putAll(towrite);
/*    */     try {
/* 85 */       this.prop.store(new FileOutputStream(this.filePath), null);
/*    */     } catch (FileNotFoundException e) {
/* 87 */       return false;
/*    */     } catch (IOException e) {
/* 89 */       return false;
/*    */     }
/* 91 */     return result;
/*    */   }
/*    */ }

/* Location:           C:\Users\Rémy\Downloads\jpacman030.jar
 * Qualified Name:     it.marte.games.pacman.util.ScoreTableLoader
 * JD-Core Version:    0.6.2
 */