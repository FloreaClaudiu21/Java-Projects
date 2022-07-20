/*    */ package me.storagecabinet.manager.events;
/*    */ 
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockBreakEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class onCabinetBreak
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void on_cabinet_break(BlockBreakEvent EV) {
/* 15 */     StorageCabinet.CABINET_MANAGER.MECHANICS.break_cabinet(EV);
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\events\onCabinetBreak.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */