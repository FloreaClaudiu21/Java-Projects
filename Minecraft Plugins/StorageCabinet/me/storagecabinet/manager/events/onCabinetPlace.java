/*    */ package me.storagecabinet.manager.events;
/*    */ 
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockPlaceEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class onCabinetPlace
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void on_cabinet_place(BlockPlaceEvent EV) {
/* 16 */     StorageCabinet.CABINET_MANAGER.MECHANICS.place_cabinet(EV);
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\events\onCabinetPlace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */