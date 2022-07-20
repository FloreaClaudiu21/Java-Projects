/*     */ package me.furnace.event;
/*     */ 
/*     */ import me.furnace.IMain;
/*     */ import me.furnace.manager.data.IData;
/*     */ import me.furnace.manager.furnace.IFurnaceU;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.ClickType;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*     */ import org.bukkit.event.inventory.InventoryDragEvent;
/*     */ import org.bukkit.event.inventory.InventoryType;
/*     */ import org.bukkit.event.player.PlayerCommandPreprocessEvent;
/*     */ import org.bukkit.event.player.PlayerJoinEvent;
/*     */ import org.bukkit.event.player.PlayerKickEvent;
/*     */ import org.bukkit.event.player.PlayerQuitEvent;
/*     */ import org.bukkit.event.server.ServerCommandEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class IEvent
/*     */   implements Listener {
/*     */   public IEvent(Plugin P) {
/*  26 */     IMain.PM.registerEvents(this, P);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void JOIN(final PlayerJoinEvent E) {
/*  37 */     IMain.SC.runTaskLater(IMain.PL, new Runnable() {
/*     */           public void run() {
/*  39 */             IMain.BOSSBAR.add(E.getPlayer());
/*  40 */             IMain.VERSION.actionbar_send(E.getPlayer(), "YRDY");
/*     */           }
/*  42 */         }10L);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void LEAVE(PlayerQuitEvent E) {
/*  47 */     Player P = E.getPlayer();
/*     */     
/*  49 */     P.closeInventory();
/*  50 */     IMain.BOSSBAR.hide(P);
/*  51 */     IMain.DATA.ADMINS.remove(P);
/*  52 */     IMain.DATA.VIEWERS.remove(P);
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void QUIT(PlayerKickEvent E) {
/*  58 */     Player P = E.getPlayer();
/*     */     
/*  60 */     P.closeInventory();
/*  61 */     IMain.BOSSBAR.hide(P);
/*  62 */     IMain.DATA.ADMINS.remove(P);
/*  63 */     IMain.DATA.VIEWERS.remove(P);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void FURNACE_CLOSE(InventoryCloseEvent E) {
/*  71 */     Inventory I = E.getInventory();
/*     */     
/*  73 */     if (I == null) {
/*     */       return;
/*     */     }
/*  76 */     if (!(I instanceof org.bukkit.inventory.FurnaceInventory)) {
/*     */       return;
/*     */     }
/*  79 */     final Player P = (Player)E.getPlayer();
/*     */     
/*  81 */     if (IMain.DATA.VIEWERS.containsKey(P)) {
/*  82 */       final IData D = (IData)IMain.DATA.VIEWERS.get(P);
/*     */       
/*  84 */       IMain.BOSSBAR.hide(P);
/*  85 */       IMain.DATA.VIEWERS.remove(P);
/*  86 */       IMain.SC.runTaskLater(IMain.PL, new Runnable() {
/*     */             public void run() {
/*  88 */               if (P.isOnline() && !P.isDead()) {
/*  89 */                 D.open_menu(P);
/*     */               }
/*     */             }
/*  92 */           },  5L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void FURNACE_DRAG(InventoryDragEvent E) {
/*  99 */     Inventory TOP = E.getView().getTopInventory();
/*     */     
/* 101 */     if (TOP == null || !(TOP instanceof org.bukkit.inventory.FurnaceInventory)) {
/*     */       return;
/*     */     }
/* 104 */     Player P = (Player)E.getWhoClicked();
/*     */     
/* 106 */     if (IMain.DATA.VIEWERS.containsKey(P)) {
/* 107 */       E.setCancelled(true);
/* 108 */       E.setResult(Event.Result.DENY);
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void FURNACE_CLICK(InventoryClickEvent E) {
/* 116 */     Inventory TOP = E.getView().getTopInventory();
/* 117 */     Inventory BOTTOM = E.getView().getBottomInventory();
/*     */     
/* 119 */     if (TOP == null || !(TOP instanceof org.bukkit.inventory.FurnaceInventory)) {
/*     */       return;
/*     */     }
/* 122 */     if (BOTTOM.getType() == InventoryType.PLAYER) {
/* 123 */       Player P = (Player)E.getWhoClicked();
/*     */       
/* 125 */       if (IMain.DATA.VIEWERS.containsKey(P)) {
/* 126 */         IData D = (IData)IMain.DATA.VIEWERS.get(P);
/* 127 */         Inventory I = E.getClickedInventory();
/*     */         
/* 129 */         if (I == null) {
/*     */           return;
/*     */         }
/* 132 */         if (D.OWNER.isOnline() && D.OWNER.getPlayer().hasPermission(IMain.CONFIG.perm("use"))) {
/* 133 */           if (I.equals(TOP)) {
/* 134 */             if (E.getSlot() != 2) {
/*     */               return;
/*     */             }
/* 137 */             if (E.getClick() != ClickType.LEFT) {
/* 138 */               E.setCancelled(true);
/* 139 */               E.setResult(Event.Result.DENY);
/*     */               return;
/*     */             } 
/* 142 */             if (!IFurnaceU.E(E.getCursor())) {
/* 143 */               E.setCancelled(true);
/* 144 */               E.setResult(Event.Result.DENY);
/*     */               return;
/*     */             } 
/* 147 */             IMain.UTILS.giveXP(TOP, P);
/*     */             return;
/*     */           } 
/*     */         } else {
/* 151 */           if (!P.hasPermission(IMain.CONFIG.perm("use_admin"))) {
/* 152 */             E.setCancelled(true);
/* 153 */             E.setResult(Event.Result.DENY);
/*     */             return;
/*     */           } 
/* 156 */           if (I.equals(TOP)) {
/* 157 */             if (E.getSlot() != 2) {
/*     */               return;
/*     */             }
/* 160 */             if (E.getClick() != ClickType.LEFT) {
/* 161 */               E.setCancelled(true);
/* 162 */               E.setResult(Event.Result.DENY);
/*     */               return;
/*     */             } 
/* 165 */             if (!IFurnaceU.E(E.getCursor())) {
/* 166 */               E.setCancelled(true);
/* 167 */               E.setResult(Event.Result.DENY);
/*     */               return;
/*     */             } 
/* 170 */             IMain.UTILS.giveXP(TOP, P);
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void CMD(PlayerCommandPreprocessEvent E) {
/* 183 */     String C = E.getMessage();
/*     */     
/* 185 */     if (C.toLowerCase().startsWith("/bukkit:")) {
/* 186 */       E.setCancelled(true);
/*     */       return;
/*     */     } 
/* 189 */     if (C.toLowerCase().startsWith("/stop") || C.toLowerCase().startsWith("/rl") || 
/* 190 */       C.toLowerCase().startsWith("/reload")) {
/* 191 */       IMain.DATA.ADMINS.clear();
/* 192 */       IMain.DATA.VIEWERS.clear();
/* 193 */       for (Player P1 : IMain.S.getOnlinePlayers()) {
/* 194 */         P1.closeInventory();
/* 195 */         IMain.BOSSBAR.remove(P1);
/*     */       } 
/* 197 */       IMain.RECIPE_CREATOR.give_items();
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void CMD(ServerCommandEvent E) {
/* 204 */     String C = E.getCommand();
/*     */     
/* 206 */     if (C.toLowerCase().startsWith("bukkit:")) {
/* 207 */       E.setCommand("/");
/*     */       return;
/*     */     } 
/* 210 */     if (C.toLowerCase().startsWith("stop") || C.toLowerCase().startsWith("rl") || 
/* 211 */       C.toLowerCase().startsWith("reload")) {
/* 212 */       IMain.DATA.ADMINS.clear();
/* 213 */       IMain.DATA.VIEWERS.clear();
/* 214 */       for (Player P1 : IMain.S.getOnlinePlayers()) {
/* 215 */         P1.closeInventory();
/* 216 */         IMain.BOSSBAR.remove(P1);
/*     */       } 
/* 218 */       IMain.RECIPE_CREATOR.give_items();
/*     */       return;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\event\IEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */