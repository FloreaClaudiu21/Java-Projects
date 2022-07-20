/*    */ package me.storagecabinet.utils;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileWriter;
/*    */ import java.util.Scanner;
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultSettingsReader
/*    */ {
/*    */   private static final String NEW_LINE = "\n";
/*    */   
/*    */   public DefaultSettingsReader(StorageCabinet PL) {}
/*    */   
/*    */   public void set_missing_key(File F, int POS, String KEY) {
/* 19 */     if (F == null || KEY == null || KEY.isEmpty() || POS < 0) {
/*    */       return;
/*    */     }
/*    */     try {
/* 23 */       int POS1 = 0;
/*    */       
/* 25 */       FileInputStream IS = new FileInputStream(F);
/* 26 */       Scanner S = new Scanner(IS);
/* 27 */       FileWriter FW = new FileWriter(F);
/* 28 */       StringBuilder SB = new StringBuilder();
/*    */       
/* 30 */       while (S.hasNextLine()) {
/* 31 */         POS1++;
/* 32 */         String LINE = S.nextLine();
/* 33 */         System.out.println(LINE);
/* 34 */         SB.append(String.valueOf(LINE) + "\n");
/* 35 */         if (POS == POS1) {
/*    */           break;
/*    */         }
/*    */       } 
/* 39 */       FW.write(String.valueOf(SB.toString()) + KEY);
/* 40 */       S.close();
/* 41 */       IS.close();
/* 42 */       FW.close();
/* 43 */     } catch (Exception EX) {
/* 44 */       EX.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabine\\utils\DefaultSettingsReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */