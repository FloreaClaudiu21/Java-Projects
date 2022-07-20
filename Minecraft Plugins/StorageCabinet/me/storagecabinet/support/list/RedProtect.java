/*    */ package me.storagecabinet.support.list;
/*    */ 
/*    */ import br.net.fabiozumbi12.RedProtect.Bukkit.Region;
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import me.storagecabinet.support.SupportAPI;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public enum RedProtect
/*    */   implements SupportAPI
/*    */ {
/* 12 */   PLACE(0), INTERACT(1), DESTROY(2);
/*    */   
/*    */   private int I;
/*    */   
/*    */   RedProtect(int I) {
/* 17 */     this.I = I;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean is_allowed(Player P, Location L, boolean H) {
/* 23 */     if (P == null || L == null) {
/* 24 */       return false;
/*    */     }
/* 26 */     if (!StorageCabinet.SUPPORT.HAS_RP) {
/* 27 */       return false;
/*    */     }
/* 29 */     if (!H && P.hasPermission(StorageCabinet.PERM_MANAGER.CABINET_BYPASS)) {
/* 30 */       return true;
/*    */     }
/* 32 */     Region R = StorageCabinet.SUPPORT.RP.getRegion(L);
/*    */     
/* 34 */     if (R == null) {
/* 35 */       return true;
/*    */     }
/* 37 */     switch (this.I) {
/*    */       case 0:
/* 39 */         return R.canBuild(P);
/*    */       case 1:
/* 41 */         return R.canChest(P);
/*    */       case 2:
/* 43 */         return R.canBuild(P);
/*    */     } 
/*    */ 
/*    */     
/* 47 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\support\list\RedProtect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */