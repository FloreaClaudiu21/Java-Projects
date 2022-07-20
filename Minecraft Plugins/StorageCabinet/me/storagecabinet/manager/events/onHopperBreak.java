/*    */ package me.storagecabinet.manager.events;
/*    */ 
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockBreakEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class onHopperBreak
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void on_hopper_place(BlockBreakEvent EV) {
/* 16 */     StorageCabinet.CABINET_MANAGER.HOPPER_MECHANICS.break_hopper(EV.getPlayer(), EV.getBlock());
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\events\onHopperBreak.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */