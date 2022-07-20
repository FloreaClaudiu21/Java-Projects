/*     */ package me.furnace.config.section;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ISectionUtils
/*     */ {
/*     */   public ISectionUtils(ISection S) {
/*  10 */     this.S = S;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private ISection S;
/*     */ 
/*     */ 
/*     */   
/*     */   private List<Object> find_list(int START) {
/*  20 */     List<Object> L = new ArrayList();
/*     */     
/*  22 */     for (int I = START + 1; I < this.S.LINES.size(); ) {
/*  23 */       String O = this.S.LINES.get(Integer.valueOf(I));
/*  24 */       String N = O.replace(" ", "");
/*     */       
/*  26 */       if (!N.isEmpty() && N.startsWith("-")) {
/*  27 */         String[] ARGS = N.split("-", 2);
/*     */         
/*  29 */         if (ARGS.length > 1 && !ARGS[1].isEmpty()) {
/*  30 */           String[] ARGS1 = O.split("-", 2);
/*  31 */           String M = ARGS1[1];
/*     */           
/*  33 */           if (M.startsWith(" ")) {
/*  34 */             int spaces = 0;
/*     */             
/*  36 */             for (int I1 = 0; I1 < M.length(); ) {
/*  37 */               char C = M.charAt(I1);
/*     */               
/*  39 */               if (C == ' ') {
/*  40 */                 spaces++;
/*     */                 I1++;
/*     */               } 
/*     */               break;
/*     */             } 
/*  45 */             M = M.substring(spaces);
/*     */           } 
/*  47 */           if (M.startsWith("\"") || M.startsWith("'")) {
/*  48 */             M = M.substring(1);
/*     */           }
/*  50 */           if (M.endsWith("\"") || M.endsWith("'")) {
/*  51 */             M = M.substring(0, M.length() - 1);
/*     */           }
/*  53 */           L.add(M);
/*     */         } 
/*     */         I++;
/*     */       } 
/*     */       break;
/*     */     } 
/*  59 */     return L;
/*     */   }
/*     */ 
/*     */   
/*     */   public void read_lines() {
/*  64 */     for (Iterator<Integer> iterator = this.S.LINES.keySet().iterator(); iterator.hasNext(); ) { int I = ((Integer)iterator.next()).intValue();
/*  65 */       String LINE = this.S.LINES.get(Integer.valueOf(I));
/*  66 */       String N = LINE.replace(" ", "");
/*     */       
/*  68 */       if (N.startsWith("#")) {
/*  69 */         this.S.COMMENTS.add(LINE);
/*     */       }
/*  71 */       if (!N.startsWith("#") && !N.startsWith("-") && N.contains(":")) {
/*  72 */         String[] ARGS = N.split(":", 2);
/*  73 */         String PATH = ARGS[0];
/*     */         
/*  75 */         if (ARGS.length == 0) {
/*  76 */           List<Object> V = find_list(I);
/*     */           
/*  78 */           if (V != null && !V.isEmpty()) {
/*  79 */             this.S.VALUES.put(PATH, V);
/*     */           }
/*     */         } 
/*  82 */         if (ARGS.length > 1 && ARGS[1].isEmpty()) {
/*  83 */           List<Object> V = find_list(I);
/*     */           
/*  85 */           if (V != null && !V.isEmpty()) {
/*  86 */             this.S.VALUES.put(PATH, V);
/*     */           }
/*     */         } 
/*  89 */         if (ARGS.length > 1 && !ARGS[1].isEmpty()) {
/*  90 */           String V = LINE.split(":", 2)[1];
/*     */           
/*  92 */           if (V.startsWith(" ")) {
/*  93 */             int spaces = 0;
/*     */             
/*  95 */             for (int I1 = 0; I1 < V.length(); ) {
/*  96 */               char C = V.charAt(I1);
/*     */               
/*  98 */               if (C == ' ') {
/*  99 */                 spaces++;
/*     */                 I1++;
/*     */               } 
/*     */               break;
/*     */             } 
/* 104 */             V = V.substring(spaces);
/*     */           } 
/* 106 */           if (V.startsWith("\"") || V.startsWith("'")) {
/* 107 */             V = V.substring(1);
/*     */           }
/* 109 */           if (V.endsWith("\"") || V.endsWith("'")) {
/* 110 */             V = V.substring(0, V.length() - 1);
/*     */           }
/* 112 */           this.S.VALUES.put(PATH, V);
/*     */         } 
/*     */       }  }
/*     */   
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\config\section\ISectionUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */