/*    */ package me.storagecabinet.manager.events;
/*    */ 
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class onCabinetUse
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void on_cabinet_use(PlayerInteractEvent EV) {
/* 16 */     StorageCabinet.CABINET_MANAGER.MECHANICS.interact_cabinet(EV);
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\events\onCabinetUse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */