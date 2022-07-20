/*    */ package me.furnace.update;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import java.net.URL;
/*    */ import java.net.URLConnection;
/*    */ import me.furnace.IMain;
/*    */ 
/*    */ public class IUpdate
/*    */ {
/* 12 */   public String NEWVERSION_S = null;
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean NEWVERSION = false;
/*    */ 
/*    */ 
/*    */   
/*    */   public IUpdate() {
/* 21 */     URL URL = null;
/* 22 */     String ID = "74644";
/*    */     
/*    */     try {
/* 25 */       URL = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + ID);
/* 26 */       URLConnection C = URL.openConnection();
/* 27 */       this.NEWVERSION_S = (new BufferedReader(new InputStreamReader(C.getInputStream()))).readLine();
/* 28 */       if (!IMain.DESC.getVersion().equals(this.NEWVERSION_S)) {
/* 29 */         this.NEWVERSION = true;
/*    */       } else {
/* 31 */         this.NEWVERSION = false;
/*    */       } 
/* 33 */     } catch (IOException iOException) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnac\\update\IUpdate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */