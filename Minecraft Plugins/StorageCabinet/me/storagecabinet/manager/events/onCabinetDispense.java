/*    */ package me.storagecabinet.manager.events;
/*    */ 
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockDispenseEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class onCabinetDispense
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void on_cabinet_dispense(BlockDispenseEvent EV) {
/* 17 */     StorageCabinet.CABINET_MANAGER.MECHANICS.dispense_cabinet(EV);
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\events\onCabinetDispense.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */