/*    */ package me.storagecabinet.manager.events;
/*    */ 
/*    */ import java.util.List;
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockPistonExtendEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class onCabinetPush
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void on_cabinet_push(BlockPistonExtendEvent EV) {
/* 20 */     List<Block> BLS = EV.getBlocks();
/*    */     
/* 22 */     BLS.forEach(B -> {
/*    */           if (StorageCabinet.CABINET_MANAGER.is_cabinet_block(B)) {
/*    */             paramBlockPistonExtendEvent.setCancelled(true);
/*    */             return;
/*    */           } 
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\events\onCabinetPush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */