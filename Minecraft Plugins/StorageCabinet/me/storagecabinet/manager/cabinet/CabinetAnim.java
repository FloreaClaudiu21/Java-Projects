/*    */ package me.storagecabinet.manager.cabinet;
/*    */ 
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ import org.bukkit.scheduler.BukkitTask;
/*    */ 
/*    */ public class CabinetAnim
/*    */ {
/*    */   private Cabinet C;
/*    */   private BukkitTask TASK;
/*    */   private int CUR_A_I;
/*    */   private int CUR_A_I1;
/*    */   
/*    */   public CabinetAnim(Cabinet C) {
/* 16 */     this.C = C;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean open_anim() {
/* 21 */     if (this.C.STAND == null || this.C.IS_OPEN) {
/* 22 */       return false;
/*    */     }
/* 24 */     this.CUR_A_I = 0;
/*    */     
/* 26 */     if (this.TASK != null) {
/* 27 */       this.TASK.cancel();
/*    */     }
/* 29 */     this.C.IS_OPEN = true;
/* 30 */     this.TASK = StorageCabinet.SCHEDULER.runTaskTimer(StorageCabinet.PLUGIN, () -> {
/*    */           if (this.CUR_A_I >= this.C.ANIM_IDS.size() || this.C.STAND == null) {
/*    */             this.TASK.cancel();
/*    */             
/*    */             this.TASK = null;
/*    */             
/*    */             return;
/*    */           } 
/*    */           int ID = ((Integer)this.C.ANIM_IDS.get(this.CUR_A_I)).intValue();
/*    */           ItemStack H = this.C.STAND.getEquipment().getHelmet();
/*    */           if (H != null && H.hasItemMeta()) {
/*    */             ItemMeta M = H.getItemMeta();
/*    */             M.setCustomModelData(Integer.valueOf(ID));
/*    */             H.setItemMeta(M);
/*    */             this.C.STAND.getEquipment().setHelmet(H);
/*    */           } 
/*    */           this.CUR_A_I++;
/* 47 */         }0L, 1L);
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean close_anim() {
/* 53 */     if (this.C.STAND == null || !this.C.IS_OPEN || this.C.isClosing) {
/* 54 */       return false;
/*    */     }
/* 56 */     this.CUR_A_I1 = this.C.ANIM_IDS.size();
/*    */     
/* 58 */     if (this.TASK != null) {
/* 59 */       this.TASK.cancel();
/*    */     }
/* 61 */     this.C.isClosing = true;
/* 62 */     this.TASK = StorageCabinet.SCHEDULER.runTaskTimer(StorageCabinet.PLUGIN, () -> {
/*    */           if (this.CUR_A_I1 <= 0 || this.C.STAND == null) {
/*    */             this.TASK.cancel();
/*    */             
/*    */             this.TASK = null;
/*    */             
/*    */             this.C.IS_OPEN = false;
/*    */             this.C.isClosing = false;
/*    */             return;
/*    */           } 
/*    */           int ID = ((Integer)this.C.ANIM_IDS.get(this.CUR_A_I1 - 1)).intValue();
/*    */           ItemStack H = this.C.STAND.getEquipment().getHelmet();
/*    */           if (H != null && H.hasItemMeta()) {
/*    */             ItemMeta M = H.getItemMeta();
/*    */             M.setCustomModelData(Integer.valueOf(ID));
/*    */             H.setItemMeta(M);
/*    */             this.C.STAND.getEquipment().setHelmet(H);
/*    */           } 
/*    */           this.CUR_A_I1--;
/* 81 */         }0L, 1L);
/* 82 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\cabinet\CabinetAnim.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */