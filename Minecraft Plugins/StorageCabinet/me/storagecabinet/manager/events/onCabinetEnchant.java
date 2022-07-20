/*    */ package me.storagecabinet.manager.events;
/*    */ 
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class onCabinetEnchant
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void on_cabinet_enchant(PrepareItemEnchantEvent EV) {
/* 15 */     StorageCabinet.CABINET_MANAGER.MECHANICS.enchant_cabinet(EV);
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\events\onCabinetEnchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */