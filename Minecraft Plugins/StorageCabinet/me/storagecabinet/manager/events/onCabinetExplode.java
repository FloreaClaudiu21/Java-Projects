/*    */ package me.storagecabinet.manager.events;
/*    */ 
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityExplodeEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class onCabinetExplode
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void on_cabinet_explode(EntityExplodeEvent EV) {
/* 16 */     StorageCabinet.CABINET_MANAGER.MECHANICS.explode_cabinet(EV);
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\events\onCabinetExplode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */