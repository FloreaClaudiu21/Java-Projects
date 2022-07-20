/*    */ package me.storagecabinet.manager.events;
/*    */ 
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockPlaceEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class onHopperPlace
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void on_hopper_place(BlockPlaceEvent EV) {
/* 18 */     Player P = EV.getPlayer();
/*    */     
/* 20 */     StorageCabinet.CABINET_MANAGER.HOPPER_MECHANICS.place_hopper(P, EV.getBlock());
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\events\onHopperPlace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */