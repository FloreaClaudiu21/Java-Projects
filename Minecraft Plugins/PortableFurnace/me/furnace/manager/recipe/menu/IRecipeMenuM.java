/*     */ package me.furnace.manager.recipe.menu;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import me.furnace.IMain;
/*     */ import me.furnace.manager.data.IData;
/*     */ import me.furnace.manager.furnace.IFurnaceU;
/*     */ import me.furnace.manager.menu.menu.IMenu;
/*     */ import me.furnace.manager.recipe.IFurnaceRecipe;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.ClickType;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*     */ import org.bukkit.event.inventory.InventoryDragEvent;
/*     */ import org.bukkit.event.inventory.InventoryType;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ 
/*     */ public class IRecipeMenuM
/*     */   implements Listener {
/*     */   private IRecipeMenu D;
/*     */   
/*     */   public IRecipeMenuM(IRecipeMenu D) {
/*  29 */     this.D = D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start() {
/*  39 */     IMain.PM.registerEvents(this, IMain.PL);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() {
/*  45 */     HandlerList.unregisterAll(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void on_addtime() {
/*  53 */     if (this.D.A_TIME == 1) {
/*  54 */       this.D.A_TIME = 2;
/*  55 */     } else if (this.D.A_TIME == 2) {
/*  56 */       this.D.A_TIME = 5;
/*  57 */     } else if (this.D.A_TIME == 5) {
/*  58 */       this.D.A_TIME = 7;
/*  59 */     } else if (this.D.A_TIME == 7) {
/*  60 */       this.D.A_TIME = 10;
/*     */     } else {
/*  62 */       this.D.A_TIME = 1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void on_addxp() {
/*  69 */     if (this.D.A_XP == 0.1D) {
/*  70 */       this.D.A_XP = 0.2D;
/*  71 */     } else if (this.D.A_XP == 0.2D) {
/*  72 */       this.D.A_XP = 0.5D;
/*  73 */     } else if (this.D.A_XP == 0.5D) {
/*  74 */       this.D.A_XP = 0.7D;
/*  75 */     } else if (this.D.A_XP == 0.7D) {
/*  76 */       this.D.A_XP = 1.0D;
/*     */     } else {
/*  78 */       this.D.A_XP = 0.1D;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void on_removetime() {
/*  85 */     if (this.D.R_TIME == 1) {
/*  86 */       this.D.R_TIME = 2;
/*  87 */     } else if (this.D.R_TIME == 2) {
/*  88 */       this.D.R_TIME = 5;
/*  89 */     } else if (this.D.R_TIME == 5) {
/*  90 */       this.D.R_TIME = 7;
/*  91 */     } else if (this.D.R_TIME == 7) {
/*  92 */       this.D.R_TIME = 10;
/*     */     } else {
/*  94 */       this.D.R_TIME = 1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void on_removexp() {
/* 101 */     if (this.D.R_XP == 0.1D) {
/* 102 */       this.D.R_XP1 = 2;
/* 103 */       this.D.R_XP = 0.2D;
/* 104 */     } else if (this.D.R_XP == 0.2D) {
/* 105 */       this.D.R_XP1 = 5;
/* 106 */       this.D.R_XP = 0.5D;
/* 107 */     } else if (this.D.R_XP == 0.5D) {
/* 108 */       this.D.R_XP1 = 7;
/* 109 */       this.D.R_XP = 0.7D;
/* 110 */     } else if (this.D.R_XP == 0.7D) {
/* 111 */       this.D.R_XP1 = 10;
/* 112 */       this.D.R_XP = 1.0D;
/*     */     } else {
/* 114 */       this.D.R_XP1 = 1;
/* 115 */       this.D.R_XP = 0.1D;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void on_set_time(ClickType T) {
/* 124 */     if (T == ClickType.LEFT) {
/* 125 */       int A = this.D.COOKTIME + this.D.A_TIME;
/*     */       
/* 127 */       if (A < 500) {
/* 128 */         this.D.COOKTIME = A;
/*     */       }
/* 130 */     } else if (T == ClickType.RIGHT) {
/* 131 */       int A = this.D.COOKTIME - this.D.R_TIME;
/*     */       
/* 133 */       if (A > 20) {
/* 134 */         this.D.COOKTIME = A;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void on_set_xp(ClickType T) {
/* 142 */     if (T == ClickType.LEFT) {
/* 143 */       double A = this.D.round(this.D.EXPERIENCE + this.D.A_XP);
/*     */       
/* 145 */       if (A < 10.0D) {
/* 146 */         this.D.EXPERIENCE = A;
/*     */       }
/* 148 */     } else if (T == ClickType.RIGHT) {
/* 149 */       double A = this.D.round(this.D.EXPERIENCE - this.D.R_XP);
/*     */       
/* 151 */       if (A > 0.1D) {
/* 152 */         this.D.EXPERIENCE = A;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void on_save(final Player USER, InventoryClickEvent E) {
/* 162 */     if (USER == null || E == null) {
/*     */       return;
/*     */     }
/* 165 */     if (!USER.hasPermission(IMain.CONFIG.perm("recipe_save"))) {
/* 166 */       E.setCancelled(true);
/* 167 */       E.setResult(Event.Result.DENY);
/* 168 */       IMain.UTILS.sendEffect(USER, "noperm");
/* 169 */       IMain.VERSION.actionbar_send(USER, IMain.VARS.M((OfflinePlayer)USER, "noperm"));
/*     */       return;
/*     */     } 
/* 172 */     if (IMain.COOLDOWN.COOLDOWN_MAP.containsKey(USER)) {
/* 173 */       E.setCancelled(true);
/* 174 */       E.setResult(Event.Result.DENY);
/* 175 */       IMain.VERSION.actionbar_send(USER, IMain.VARS.M((OfflinePlayer)USER, "cooldown"));
/*     */       return;
/*     */     } 
/* 178 */     IMain.COOLDOWN.add(USER);
/* 179 */     final ItemStack R = this.D.MENU.getItem(this.D.MENU_DATA.MENU_R_SLOT);
/* 180 */     final ItemStack SM = this.D.MENU.getItem(this.D.MENU_DATA.MENU_S_SLOT);
/*     */     
/* 182 */     if (IFurnaceU.E(R)) {
/* 183 */       E.setCancelled(true);
/* 184 */       E.setResult(Event.Result.DENY);
/* 185 */       IMain.VERSION.actionbar_send(USER, IMain.VARS.M((OfflinePlayer)USER, "recipe_noresult"));
/*     */       return;
/*     */     } 
/* 188 */     if (IFurnaceU.E(SM)) {
/* 189 */       E.setCancelled(true);
/* 190 */       E.setResult(Event.Result.DENY);
/* 191 */       IMain.VERSION.actionbar_send(USER, IMain.VARS.M((OfflinePlayer)USER, "recipe_nosmelting"));
/*     */       return;
/*     */     } 
/* 194 */     if (IFurnaceRecipe.same(SM, R)) {
/* 195 */       E.setCancelled(true);
/* 196 */       E.setResult(Event.Result.DENY);
/* 197 */       IMain.VERSION.actionbar_send(USER, IMain.VARS.M((OfflinePlayer)USER, "recipe_sameitems"));
/*     */       return;
/*     */     } 
/* 200 */     if (IMain.RECIPES.recipe_smelting(SM) != null) {
/* 201 */       E.setCancelled(true);
/* 202 */       E.setResult(Event.Result.DENY);
/* 203 */       IMain.VERSION.actionbar_send(USER, IMain.VARS.M((OfflinePlayer)USER, "recipe_exists"));
/*     */       return;
/*     */     } 
/* 206 */     IMain.SC.scheduleSyncDelayedTask(IMain.PL, () -> paramPlayer.closeInventory(), 12L);
/* 207 */     IMain.SC.scheduleAsyncDelayedTask(IMain.PL, (Runnable)new BukkitRunnable() {
/*     */           public void run() {
/* 209 */             String ID = UUID.randomUUID().toString().substring(0, 8);
/* 210 */             String MSG = IMain.VARS.M((OfflinePlayer)USER, "recipe_save").replace("%R_ID%", ID);
/*     */ 
/*     */             
/* 213 */             IRecipeMenuM.this.D.SAVE = true;
/* 214 */             IMain.UTILS.console(MSG, false);
/* 215 */             IMain.RECIPES.reload();
/* 216 */             IMain.VERSION.actionbar_send(USER, MSG);
/* 217 */             IMain.UTILS.sendEffect(USER, "create_recipe");
/*     */           }
/* 219 */         }10L);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   private void CREATOR_DRAG(InventoryDragEvent E) {
/* 226 */     Inventory TOP = E.getView().getTopInventory();
/* 227 */     Inventory BOTTOM = E.getView().getBottomInventory();
/*     */     
/* 229 */     if (TOP.equals(this.D.MENU) && BOTTOM.getType() == InventoryType.PLAYER) {
/* 230 */       E.setCancelled(true);
/* 231 */       E.setResult(Event.Result.DENY);
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   private void CREATOR_CLOSE(InventoryCloseEvent E) {
/* 239 */     Inventory IN = E.getInventory();
/* 240 */     final Player P = (Player)E.getPlayer();
/*     */     
/* 242 */     if (IN.equals(this.D.MENU)) {
/* 243 */       this.D.R_XP1 = 1;
/* 244 */       this.D.A_XP1 = 1;
/* 245 */       this.D.A_TIME = 1;
/* 246 */       this.D.R_TIME = 1;
/* 247 */       this.D.A_XP = 0.1D;
/* 248 */       this.D.R_XP = 0.1D;
/* 249 */       this.D.COOKTIME = 200;
/* 250 */       this.D.EXPERIENCE = 0.5D;
/*     */       
/* 252 */       if (!this.D.SAVE) {
/* 253 */         IMain.VERSION.actionbar_send(P, IMain.VARS.M((OfflinePlayer)P, "recipe_close"));
/*     */       } else {
/* 255 */         this.D.SAVE = false;
/*     */       } 
/* 257 */       if (IMain.DATA.VIEWERS.containsKey(P)) {
/* 258 */         final IData D = (IData)IMain.DATA.VIEWERS.get(P);
/*     */         
/* 260 */         IMain.DATA.VIEWERS.remove(P);
/* 261 */         IMain.SC.runTaskLater(IMain.PL, new Runnable() {
/*     */               public void run() {
/* 263 */                 if (P.isOnline() && !P.isDead()) {
/* 264 */                   D.open_menu(P);
/*     */                 }
/*     */               }
/* 267 */             },  5L);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   private void CREATOR_CLICK(InventoryClickEvent E) {
/* 275 */     Inventory I = E.getClickedInventory();
/*     */     
/* 277 */     if (I == null) {
/*     */       return;
/*     */     }
/* 280 */     if (I.equals(this.D.MENU)) {
/* 281 */       int SLOT = E.getSlot();
/* 282 */       ClickType T = E.getClick();
/* 283 */       ItemStack IT = E.getCurrentItem();
/* 284 */       Player VIEWER = (Player)E.getWhoClicked();
/*     */       
/* 286 */       if (SLOT == this.D.MENU_DATA.MENU_R_SLOT || SLOT == this.D.MENU_DATA.MENU_S_SLOT) {
/*     */         return;
/*     */       }
/* 289 */       if (IT == null) {
/* 290 */         if (!IFurnaceU.E(E.getCursor())) {
/* 291 */           E.setCancelled(true);
/* 292 */           E.setResult(Event.Result.DENY);
/*     */           return;
/*     */         } 
/*     */         return;
/*     */       } 
/* 297 */       if (!IFurnaceU.E(E.getCursor())) {
/* 298 */         E.setCancelled(true);
/* 299 */         E.setResult(Event.Result.DENY);
/*     */         return;
/*     */       } 
/* 302 */       if (this.D.MENU_DATA.CONTENT_BY_SLOT.containsKey(Integer.valueOf(SLOT))) {
/* 303 */         IMenu.IContent C = (IMenu.IContent)this.D.MENU_DATA.CONTENT_BY_SLOT.get(Integer.valueOf(SLOT));
/*     */         
/* 305 */         if (C.ITEM_CLICK != null && !C.ITEM_CLICK.isEmpty()) {
/* 306 */           if (C.ITEM_CLICK.equalsIgnoreCase("remove_time")) {
/* 307 */             on_removetime();
/* 308 */           } else if (C.ITEM_CLICK.equalsIgnoreCase("remove_xp")) {
/* 309 */             on_removexp();
/* 310 */           } else if (C.ITEM_CLICK.equalsIgnoreCase("add_time")) {
/* 311 */             on_addtime();
/* 312 */           } else if (C.ITEM_CLICK.equalsIgnoreCase("add_xp")) {
/* 313 */             on_addxp();
/* 314 */           } else if (C.ITEM_CLICK.equalsIgnoreCase("set_xp")) {
/* 315 */             on_set_xp(T);
/* 316 */           } else if (C.ITEM_CLICK.equalsIgnoreCase("set_time")) {
/* 317 */             on_set_time(T);
/* 318 */           } else if (C.ITEM_CLICK.equalsIgnoreCase("recipe_save")) {
/* 319 */             on_save(VIEWER, E);
/*     */           } 
/*     */         }
/*     */       } 
/* 323 */       E.setCancelled(true);
/* 324 */       E.setResult(Event.Result.DENY);
/*     */       return;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\manager\recipe\menu\IRecipeMenuM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */