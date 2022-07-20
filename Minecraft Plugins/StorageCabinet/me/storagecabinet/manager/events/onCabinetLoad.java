/*    */ package me.storagecabinet.manager.events;
/*    */ 
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import org.bukkit.entity.ArmorStand;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.world.ChunkLoadEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class onCabinetLoad
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void on_stand_load(ChunkLoadEvent EV) {
/* 18 */     Entity[] EL = EV.getChunk().getEntities(); byte b; int i;
/*    */     Entity[] arrayOfEntity1;
/* 20 */     for (i = (arrayOfEntity1 = EL).length, b = 0; b < i; ) { Entity E = arrayOfEntity1[b];
/* 21 */       if (E instanceof ArmorStand) {
/* 22 */         ArmorStand S = (ArmorStand)E;
/*    */         
/* 24 */         StorageCabinet.CABINET_MANAGER.is_stand_loaded(S);
/*    */       } 
/*    */       b++; }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\events\onCabinetLoad.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */