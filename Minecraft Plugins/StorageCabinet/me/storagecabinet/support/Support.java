/*    */ package me.storagecabinet.support;
/*    */ 
/*    */ import br.net.fabiozumbi12.RedProtect.Bukkit.API.RedProtectAPI;
/*    */ import br.net.fabiozumbi12.RedProtect.Bukkit.RedProtect;
/*    */ import com.sk89q.worldguard.WorldGuard;
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ public class Support
/*    */ {
/*    */   public WorldGuard WG;
/*    */   public RedProtectAPI RP;
/*    */   public boolean HAS_WG;
/*    */   
/*    */   public Support(StorageCabinet C) {
/* 17 */     load_support();
/*    */   }
/*    */   public boolean HAS_RP; public boolean HAS_F;
/*    */   public boolean HAS_ESS;
/*    */   
/*    */   private boolean load_support() {
/* 23 */     StorageCabinet.SCHEDULER.runTaskLater(StorageCabinet.PLUGIN, () -> {
/*    */           Plugin PL = StorageCabinet.PLUGIN_MANAGER.getPlugin("WorldGuard");
/*    */           
/*    */           Plugin RP = StorageCabinet.PLUGIN_MANAGER.getPlugin("RedProtect");
/*    */           Plugin F = StorageCabinet.PLUGIN_MANAGER.getPlugin("Factions");
/*    */           Plugin E = StorageCabinet.PLUGIN_MANAGER.getPlugin("Essentials");
/*    */           if (E != null && E.isEnabled()) {
/*    */             this.HAS_ESS = true;
/*    */             StorageCabinet.UTILS.debug("Found support for Essentials, using the plugin..", false);
/*    */           } 
/*    */           if (PL != null && PL.isEnabled()) {
/*    */             this.HAS_WG = true;
/*    */             this.WG = WorldGuard.getInstance();
/*    */             StorageCabinet.UTILS.debug("Found support for WorldGuard, using the plugin..", false);
/*    */           } 
/*    */           if (RP != null && RP.isEnabled()) {
/*    */             this.HAS_RP = true;
/*    */             this.RP = RedProtect.get().getAPI();
/*    */             StorageCabinet.UTILS.debug("Found support for RedProtect, using the plugin..", false);
/*    */           } 
/*    */           if (F != null && F.isEnabled()) {
/*    */             this.HAS_F = true;
/*    */             StorageCabinet.UTILS.debug("Found support for SaberFactions, using the plugin..", false);
/*    */           } 
/* 47 */         }20L);
/* 48 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\support\Support.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */