/*    */ package me.storagecabinet.manager.events;
/*    */ 
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import me.storagecabinet.manager.ManagerAPI;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class EventManager
/*    */   implements ManagerAPI {
/*    */   private Plugin PL;
/*    */   
/*    */   public EventManager(Plugin PL) {
/* 12 */     this.PL = PL;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 18 */     StorageCabinet.PLUGIN_MANAGER.registerEvents(new onResourcePack(), this.PL);
/* 19 */     StorageCabinet.PLUGIN_MANAGER.registerEvents(new onHopperPickup(), this.PL);
/* 20 */     StorageCabinet.PLUGIN_MANAGER.registerEvents(new onHopperBreak(), this.PL);
/* 21 */     StorageCabinet.PLUGIN_MANAGER.registerEvents(new onHopperPlace(), this.PL);
/* 22 */     StorageCabinet.PLUGIN_MANAGER.registerEvents(new onHopperExplode(), this.PL);
/*    */     
/* 24 */     StorageCabinet.PLUGIN_MANAGER.registerEvents(new onCabinetUse(), this.PL);
/* 25 */     StorageCabinet.PLUGIN_MANAGER.registerEvents(new onCabinetDrop(), this.PL);
/* 26 */     StorageCabinet.PLUGIN_MANAGER.registerEvents(new onCabinetPickup(), this.PL);
/* 27 */     StorageCabinet.PLUGIN_MANAGER.registerEvents(new onCabinetBreak(), this.PL);
/* 28 */     StorageCabinet.PLUGIN_MANAGER.registerEvents(new onCabinetPush(), this.PL);
/* 29 */     StorageCabinet.PLUGIN_MANAGER.registerEvents(new onCabinetLoad(), this.PL);
/* 30 */     StorageCabinet.PLUGIN_MANAGER.registerEvents(new onCabinetPlace(), this.PL);
/* 31 */     StorageCabinet.PLUGIN_MANAGER.registerEvents(new onCabinetRename(), this.PL);
/* 32 */     StorageCabinet.PLUGIN_MANAGER.registerEvents(new onCabinetDespawn(), this.PL);
/* 33 */     StorageCabinet.PLUGIN_MANAGER.registerEvents(new onCabinetDispense(), this.PL);
/* 34 */     StorageCabinet.PLUGIN_MANAGER.registerEvents(new onCabinetEnchant(), this.PL);
/* 35 */     StorageCabinet.PLUGIN_MANAGER.registerEvents(new onCabinetExplode(), this.PL);
/* 36 */     StorageCabinet.PLUGIN_MANAGER.registerEvents(new onCabinetItemExplode(), this.PL);
/* 37 */     StorageCabinet.UTILS.debug("Plugin events have been registered", false);
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean unregister() {
/* 43 */     this.PL = null;
/* 44 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\events\EventManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */