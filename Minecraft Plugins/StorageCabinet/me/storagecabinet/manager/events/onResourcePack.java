/*    */ package me.storagecabinet.manager.events;
/*    */ 
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerJoinEvent;
/*    */ import org.bukkit.event.player.PlayerKickEvent;
/*    */ import org.bukkit.event.player.PlayerQuitEvent;
/*    */ import org.bukkit.event.player.PlayerResourcePackStatusEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class onResourcePack
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void on_player_leave(PlayerQuitEvent EV) {
/* 21 */     Player P = EV.getPlayer();
/*    */     
/* 23 */     if (P.isInvulnerable() && StorageCabinet.STORAGE.PACK.contains(P)) {
/* 24 */       P.setInvulnerable(false);
/* 25 */       StorageCabinet.STORAGE.PACK.remove(P);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void on_player_kick(PlayerKickEvent EV) {
/* 32 */     Player P = EV.getPlayer();
/*    */     
/* 34 */     if (P.isInvulnerable() && StorageCabinet.STORAGE.PACK.contains(P)) {
/* 35 */       P.setInvulnerable(false);
/* 36 */       StorageCabinet.STORAGE.PACK.remove(P);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void on_player_join(PlayerJoinEvent EV) {
/* 43 */     StorageCabinet.SCHEDULER.runTaskLater(StorageCabinet.PLUGIN, () -> {
/*    */           Player P = paramPlayerJoinEvent.getPlayer();
/*    */           
/*    */           P.setInvulnerable(true);
/*    */           
/*    */           StorageCabinet.STORAGE.PACK.add(P);
/*    */           P.setResourcePack(StorageCabinet.RESOURCE_PACK);
/* 50 */         }20L);
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void status(PlayerResourcePackStatusEvent EV) {
/* 56 */     PlayerResourcePackStatusEvent.Status S = EV.getStatus();
/* 57 */     Player P = EV.getPlayer();
/*    */     
/* 59 */     switch (S) {
/*    */ 
/*    */       
/*    */       case DECLINED:
/* 63 */         P.setInvulnerable(false);
/* 64 */         StorageCabinet.STORAGE.PACK.remove(P);
/* 65 */         P.kickPlayer((String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.denypack"));
/*    */         break;
/*    */       case FAILED_DOWNLOAD:
/* 68 */         P.setInvulnerable(false);
/* 69 */         StorageCabinet.STORAGE.PACK.remove(P);
/*    */         break;
/*    */       case SUCCESSFULLY_LOADED:
/* 72 */         P.setInvulnerable(false);
/* 73 */         StorageCabinet.STORAGE.PACK.remove(P);
/*    */         break;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\events\onResourcePack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */