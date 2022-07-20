/*    */ package me.storagecabinet.manager.events;
/*    */ 
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerDropItemEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class onCabinetDrop
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void on_cabinet_drop(PlayerDropItemEvent EV) {
/* 16 */     StorageCabinet.CABINET_MANAGER.MECHANICS.drop_cabinet(EV);
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\events\onCabinetDrop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */