/*    */ package me.storagecabinet.manager.events;
/*    */ 
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockExplodeEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class onHopperExplode
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void on_hopper_explode(BlockExplodeEvent EV) {
/* 18 */     for (Block B : EV.blockList())
/* 19 */       StorageCabinet.CABINET_MANAGER.HOPPER_MECHANICS.explode_hopper(B); 
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\events\onHopperExplode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */