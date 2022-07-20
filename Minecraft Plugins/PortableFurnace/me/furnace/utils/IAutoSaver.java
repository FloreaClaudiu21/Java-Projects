/*    */ package me.furnace.utils;
/*    */ 
/*    */ import java.io.File;
/*    */ import me.furnace.IMain;
/*    */ import me.furnace.manager.data.IData;
/*    */ 
/*    */ public class IAutoSaver
/*    */   implements Runnable {
/*    */   public IAutoSaver() {
/* 10 */     int TIME = IMain.CONFIG.i("autosaver");
/*    */     
/* 12 */     if (TIME < 1) {
/* 13 */       TIME = 5;
/*    */     }
/* 15 */     IMain.SC.runTaskTimerAsynchronously(IMain.PL, this, 20L, TIME * 1200L);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void run() {
/* 26 */     autosave();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void autosave() {
/* 32 */     int COUNT = 0;
/* 33 */     File DIR = new File(IMain.PL.getDataFolder(), "Data");
/*    */     
/* 35 */     if (!DIR.exists()) {
/* 36 */       DIR.mkdir();
/*    */     }
/* 38 */     for (String OFF : IMain.DATA.DATABASE.keySet()) {
/* 39 */       IData DATA = (IData)IMain.DATA.DATABASE.get(OFF);
/*    */       
/* 41 */       if (DATA.autosave()) {
/* 42 */         COUNT++;
/*    */       }
/*    */     } 
/* 45 */     String C = String.valueOf(COUNT) + " &2user";
/*    */     
/* 47 */     if (COUNT > 1) {
/* 48 */       C = String.valueOf(COUNT) + " &2users";
/*    */     }
/* 50 */     if (COUNT > 0 && IMain.CONFIG.b("autosaver_notify"))
/* 51 */       IMain.UTILS.console("&6" + C + " &2have been saved in the database.", true); 
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnac\\utils\IAutoSaver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */