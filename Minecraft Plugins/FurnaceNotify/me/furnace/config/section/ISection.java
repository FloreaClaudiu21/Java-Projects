/*    */ package me.furnace.config.section;
/*    */ 
/*    */ import com.google.common.base.Charsets;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.util.ArrayList;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ISection
/*    */ {
/* 19 */   public List<String> COMMENTS = new ArrayList<>(); public ISection(File F) throws Exception {
/* 20 */     FileInputStream S = new FileInputStream(F);
/* 21 */     this.LINES = new LinkedHashMap<>();
/* 22 */     this.VALUES = new LinkedHashMap<>();
/* 23 */     BufferedReader READER = new BufferedReader(new InputStreamReader(S, Charsets.UTF_8));
/*    */     try {
/* 25 */       int I = 0;
/*    */       String line;
/* 27 */       while ((line = READER.readLine()) != null) {
/* 28 */         if (line.length() > 200) {
/* 29 */           line = line.substring(0, 200);
/*    */         }
/* 31 */         this.LINES.put(Integer.valueOf(I), line);
/* 32 */         I++;
/*    */       } 
/*    */     } finally {
/* 35 */       READER.close();
/*    */     } 
/* 37 */     (new ISectionUtils(this)).read_lines();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public LinkedHashMap<String, Object> VALUES;
/*    */   
/*    */   public LinkedHashMap<Integer, String> LINES;
/*    */ 
/*    */   
/*    */   public boolean has(String PATH) {
/* 48 */     if (PATH == null || PATH.isEmpty()) {
/* 49 */       return false;
/*    */     }
/* 51 */     return this.VALUES.containsKey(PATH);
/*    */   }
/*    */ 
/*    */   
/*    */   public LinkedHashMap<String, Object> values() {
/* 56 */     return this.VALUES;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object get(String PATH) {
/* 61 */     if (PATH == null || PATH.isEmpty() || !this.VALUES.containsKey(PATH)) {
/* 62 */       return null;
/*    */     }
/* 64 */     Object V = this.VALUES.get(PATH);
/*    */     
/* 66 */     if (V == null) {
/* 67 */       return null;
/*    */     }
/* 69 */     if (V instanceof String) {
/* 70 */       String M = (String)V;
/*    */       
/* 72 */       if (M == null || M.isEmpty()) {
/* 73 */         return Boolean.valueOf(false);
/*    */       }
/*    */     } 
/* 76 */     return V;
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\config\section\ISection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */