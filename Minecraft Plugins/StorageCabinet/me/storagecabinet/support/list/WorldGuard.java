/*    */ package me.storagecabinet.support.list;
/*    */ 
/*    */ import com.sk89q.worldedit.bukkit.BukkitAdapter;
/*    */ import com.sk89q.worldedit.extension.platform.Actor;
/*    */ import com.sk89q.worldedit.world.World;
/*    */ import com.sk89q.worldguard.LocalPlayer;
/*    */ import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
/*    */ import com.sk89q.worldguard.internal.permission.RegionPermissionModel;
/*    */ import com.sk89q.worldguard.protection.ApplicableRegionSet;
/*    */ import com.sk89q.worldguard.protection.flags.Flags;
/*    */ import com.sk89q.worldguard.protection.flags.StateFlag;
/*    */ import com.sk89q.worldguard.protection.regions.RegionQuery;
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import me.storagecabinet.support.SupportAPI;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public enum WorldGuard
/*    */   implements SupportAPI {
/* 20 */   PLACE(0), INTERACT(1), DESTROY(2);
/*    */   
/*    */   private int I;
/*    */   
/*    */   WorldGuard(int I) {
/* 25 */     this.I = I;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean is_allowed(Player P, Location L, boolean H) {
/* 32 */     if (P == null || L == null) {
/* 33 */       return false;
/*    */     }
/* 35 */     if (!StorageCabinet.SUPPORT.HAS_WG) {
/* 36 */       return false;
/*    */     }
/* 38 */     if (!H && P.hasPermission(StorageCabinet.PERM_MANAGER.CABINET_BYPASS)) {
/* 39 */       return true;
/*    */     }
/* 41 */     World W = BukkitAdapter.adapt(L.getWorld());
/* 42 */     LocalPlayer LP = WorldGuardPlugin.inst().wrapPlayer(P);
/* 43 */     RegionPermissionModel RPM = new RegionPermissionModel((Actor)LP);
/*    */     
/* 45 */     if (RPM.mayIgnoreRegionProtection(W)) {
/* 46 */       return true;
/*    */     }
/* 48 */     RegionQuery RQ = StorageCabinet.SUPPORT.WG.getPlatform().getRegionContainer().createQuery();
/*    */     
/* 50 */     ApplicableRegionSet RE = RQ.getApplicableRegions(BukkitAdapter.adapt(L));
/* 51 */     if (RE.size() <= 0) {
/* 52 */       return true;
/*    */     }
/* 54 */     switch (this.I) {
/*    */       case 0:
/* 56 */         return RQ.testBuild(BukkitAdapter.adapt(L), LP, new StateFlag[] { Flags.BLOCK_PLACE });
/*    */       case 1:
/* 58 */         return RQ.testState(BukkitAdapter.adapt(L), LP, new StateFlag[] { Flags.CHEST_ACCESS });
/*    */       case 2:
/* 60 */         return RQ.testBuild(BukkitAdapter.adapt(L), LP, new StateFlag[] { Flags.BLOCK_BREAK });
/*    */     } 
/*    */ 
/*    */     
/* 64 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\support\list\WorldGuard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */