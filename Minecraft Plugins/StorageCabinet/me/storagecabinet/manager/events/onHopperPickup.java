/*    */ package me.storagecabinet.manager.events;
/*    */ 
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.inventory.InventoryPickupItemEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class onHopperPickup
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void on_hopper_place(InventoryPickupItemEvent EV) {
/* 17 */     StorageCabinet.CABINET_MANAGER.HOPPER_MECHANICS.pickup_hopper(EV);
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\events\onHopperPickup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */