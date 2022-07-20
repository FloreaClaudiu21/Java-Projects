/*    */ package me.furnace.utils;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import me.furnace.IMain;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.scheduler.BukkitTask;
/*    */ 
/*    */ public class ICooldown
/*    */   implements Runnable
/*    */ {
/*    */   public BukkitTask COOLDOWN_TASK;
/* 13 */   public Map<Player, Integer> COOLDOWN_MAP = new HashMap<>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean start() {
/* 25 */     if (this.COOLDOWN_TASK == null) {
/* 26 */       this.COOLDOWN_TASK = IMain.SC.runTaskTimerAsynchronously(IMain.PL, this, 20L, 20L);
/*    */     }
/* 28 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean stop() {
/* 33 */     if (this.COOLDOWN_TASK != null) {
/* 34 */       this.COOLDOWN_TASK.cancel();
/*    */     }
/* 36 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean add(Player P) {
/* 43 */     if (P == null) {
/* 44 */       return false;
/*    */     }
/* 46 */     this.COOLDOWN_MAP.put(P, Integer.valueOf(IMain.CONFIG.i("cooldown")));
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean remove(Player P) {
/* 52 */     this.COOLDOWN_MAP.remove(P);
/* 53 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String get(Player P) {
/* 58 */     if (P == null) {
/* 59 */       return "0 seconds";
/*    */     }
/* 61 */     if (this.COOLDOWN_MAP == null) {
/* 62 */       return "0 seconds";
/*    */     }
/* 64 */     if (!this.COOLDOWN_MAP.containsKey(P)) {
/* 65 */       return "0 seconds";
/*    */     }
/* 67 */     int I = ((Integer)this.COOLDOWN_MAP.get(P)).intValue();
/*    */     
/* 69 */     if (I > 1) {
/* 70 */       return String.valueOf(I) + " seconds";
/*    */     }
/* 72 */     return "1 second";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void run() {
/* 79 */     if (this.COOLDOWN_MAP.isEmpty()) {
/*    */       return;
/*    */     }
/* 82 */     for (Player P : IMain.S.getOnlinePlayers()) {
/* 83 */       if (this.COOLDOWN_MAP.containsKey(P)) {
/* 84 */         int I = ((Integer)this.COOLDOWN_MAP.get(P)).intValue();
/*    */         
/* 86 */         if (I > 1) {
/* 87 */           I--;
/* 88 */           this.COOLDOWN_MAP.put(P, Integer.valueOf(I)); continue;
/*    */         } 
/* 90 */         this.COOLDOWN_MAP.remove(P);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnac\\utils\ICooldown.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */