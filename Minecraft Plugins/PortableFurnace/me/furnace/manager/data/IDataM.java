/*     */ package me.furnace.manager.data;
/*     */ 
/*     */ import me.furnace.IMain;
/*     */ import me.furnace.XMaterial;
/*     */ import me.furnace.manager.furnace.IFurnace;
/*     */ import me.furnace.manager.furnace.IFurnaceU;
/*     */ import me.furnace.manager.menu.menu.IMenu;
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
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ public class IDataM implements Listener {
/*     */   private IData D;
/*     */   private InventoryClickEvent EV;
/*     */   
/*     */   public IDataM(IData D) {
/*  28 */     this.D = D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private enum PAGE_TYPE
/*     */   {
/*  38 */     NEXT, PREV;
/*     */   }
/*     */ 
/*     */   
/*     */   public void start() {
/*  43 */     IMain.PM.registerEvents(this, IMain.PL);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() {
/*  49 */     HandlerList.unregisterAll(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void on_change_page(PAGE_TYPE TYPE) {
/*  57 */     if (TYPE == PAGE_TYPE.NEXT) {
/*  58 */       int S = this.D.START + this.D.MENU_DATA.EMPTY_SLOTS.size();
/*     */       
/*  60 */       if (S < this.D.FURNACES_IDS.size()) {
/*  61 */         this.D.PAGE++;
/*  62 */         this.D.START = S;
/*     */       } 
/*     */     } else {
/*  65 */       if (this.D.PAGE <= 1) {
/*     */         return;
/*     */       }
/*  68 */       this.D.PAGE--;
/*  69 */       this.D.START -= this.D.MENU_DATA.EMPTY_SLOTS.size();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void on_opencreator(Player USER) {
/*  76 */     if (USER == null) {
/*     */       return;
/*     */     }
/*  79 */     if (!USER.hasPermission(IMain.CONFIG.perm("recipe_create"))) {
/*  80 */       USER.closeInventory();
/*  81 */       IMain.UTILS.sendEffect(USER, "noperm");
/*  82 */       IMain.VERSION.title_send(USER, IMain.VARS.M((OfflinePlayer)USER, "title"), IMain.VARS.M((OfflinePlayer)USER, "noperm"));
/*     */       return;
/*     */     } 
/*  85 */     if (IMain.RECIPE_CREATOR.INUSE) {
/*  86 */       IMain.VERSION.actionbar_send(USER, IMain.VARS.M((OfflinePlayer)USER, "recipe_inuse"));
/*     */       return;
/*     */     } 
/*  89 */     USER.closeInventory();
/*  90 */     IMain.DATA.VIEWERS.put(USER, this.D);
/*  91 */     IMain.RECIPE_CREATOR.LAST_USER = USER;
/*  92 */     USER.openInventory(IMain.RECIPE_CREATOR.MENU);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean on_openfurnace(Player VIEW, String ID) {
/*  98 */     if (VIEW == null || ID == null) {
/*  99 */       return false;
/*     */     }
/* 101 */     if (!this.D.FURNACES.containsKey(ID)) {
/* 102 */       return false;
/*     */     }
/* 104 */     if (!VIEW.hasPermission(IMain.UTILS.PERM(VIEW, "use"))) {
/* 105 */       VIEW.closeInventory();
/* 106 */       IMain.UTILS.sendEffect(VIEW, "noperm");
/* 107 */       IMain.VERSION.title_send(VIEW, IMain.VARS.M((OfflinePlayer)VIEW, "title"), IMain.VARS.M((OfflinePlayer)VIEW, "noperm"));
/* 108 */       return false;
/*     */     } 
/* 110 */     IFurnace M = this.D.FURNACES.get(ID);
/*     */     
/* 112 */     M.open_container(VIEW);
/* 113 */     IMain.DATA.VIEWERS.put(VIEW, this.D);
/* 114 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void on_delete(Player VIEWER, String ID) {
/* 121 */     if (VIEWER == null || this.EV == null || ID == null) {
/*     */       return;
/*     */     }
/* 124 */     if (!VIEWER.hasPermission(IMain.UTILS.PERM(VIEWER, "delete"))) {
/* 125 */       VIEWER.closeInventory();
/* 126 */       IMain.UTILS.sendEffect(VIEWER, "noperm");
/* 127 */       IMain.VERSION.title_send(VIEWER, IMain.VARS.M((OfflinePlayer)VIEWER, "title"), IMain.VARS.M((OfflinePlayer)VIEWER, "noperm"));
/*     */       return;
/*     */     } 
/* 130 */     if (IMain.COOLDOWN.COOLDOWN_MAP.containsKey(VIEWER)) {
/* 131 */       this.EV.setCancelled(true);
/* 132 */       this.EV.setResult(Event.Result.DENY);
/* 133 */       IMain.VERSION.actionbar_send(VIEWER, IMain.VARS.M((OfflinePlayer)VIEWER, "cooldown"));
/*     */       return;
/*     */     } 
/* 136 */     if (!this.D.FURNACES.containsKey(ID)) {
/*     */       return;
/*     */     }
/* 139 */     IMain.COOLDOWN.add(VIEWER);
/* 140 */     IFurnace F = this.D.FURNACES.get(ID);
/*     */     
/* 142 */     if (F == null) {
/*     */       return;
/*     */     }
/* 145 */     if (!IFurnaceU.furnace_empty(F)) {
/* 146 */       IMain.VERSION.actionbar_send(VIEWER, IMain.VARS.M((OfflinePlayer)VIEWER, "furnace_notempty", F));
/*     */       return;
/*     */     } 
/* 149 */     if (this.D.remove(ID)) {
/* 150 */       this.D.tick_menu();
/* 151 */       IMain.UTILS.sendEffect(VIEWER, "delete");
/* 152 */       IMain.VERSION.actionbar_send(VIEWER, IMain.VARS.M((OfflinePlayer)VIEWER, "furnace_delete", F));
/* 153 */       if (this.D.OWNER.isOnline() && !this.D.OWNER.getName().equalsIgnoreCase(VIEWER.getName())) {
/* 154 */         Player O = IMain.S.getPlayer(this.D.OWNER.getName());
/*     */         
/* 156 */         IMain.UTILS.sendEffect(O, "delete");
/* 157 */         O.sendMessage(IMain.VARS.M((OfflinePlayer)VIEWER, "furnace_delete_admin", F));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void on_create(Player VIEWER) {
/* 165 */     if (!VIEWER.hasPermission(IMain.UTILS.PERM(VIEWER, "create"))) {
/* 166 */       VIEWER.closeInventory();
/* 167 */       this.EV.setCancelled(true);
/* 168 */       this.EV.setResult(Event.Result.DENY);
/* 169 */       IMain.UTILS.sendEffect(VIEWER, "noperm");
/* 170 */       IMain.VERSION.title_send(VIEWER, IMain.VARS.M((OfflinePlayer)VIEWER, "title"), IMain.VARS.M((OfflinePlayer)VIEWER, "noperm"));
/*     */       return;
/*     */     } 
/* 173 */     if (IMain.COOLDOWN.COOLDOWN_MAP.containsKey(VIEWER)) {
/* 174 */       this.EV.setCancelled(true);
/* 175 */       this.EV.setResult(Event.Result.DENY);
/* 176 */       IMain.VERSION.actionbar_send(VIEWER, IMain.VARS.M((OfflinePlayer)VIEWER, "cooldown"));
/*     */       return;
/*     */     } 
/* 179 */     IMain.COOLDOWN.add(VIEWER);
/* 180 */     if (!this.D.OWNER.isOnline()) {
/* 181 */       this.EV.setCancelled(true);
/* 182 */       this.EV.setResult(Event.Result.DENY);
/* 183 */       IMain.UTILS.sendEffect(VIEWER, "noperm");
/* 184 */       IMain.VERSION.actionbar_send(VIEWER, IMain.VARS.M((OfflinePlayer)VIEWER, "owneroffline", this.D.OWNER.getName(), true));
/*     */       return;
/*     */     } 
/* 187 */     String N = this.D.OWNER.getName();
/* 188 */     Player O = IMain.S.getPlayer(N);
/* 189 */     int max = IMain.CONFIG.i("max_furnaces");
/*     */     
/* 191 */     if (this.D.FURNACES_IDS.size() >= max) {
/* 192 */       this.EV.setCancelled(true);
/* 193 */       this.EV.setResult(Event.Result.DENY);
/* 194 */       IMain.VERSION.actionbar_send(VIEWER, IMain.VARS.M((OfflinePlayer)VIEWER, "furnace_max", O.getDisplayName()));
/*     */       return;
/*     */     } 
/* 197 */     int NEXT = this.D.FURNACES_IDS.size() + 1;
/* 198 */     String NP = String.valueOf(IMain.CONFIG.perm("furnace")) + NEXT;
/*     */     
/* 200 */     if (!O.hasPermission(NP)) {
/* 201 */       this.EV.setCancelled(true);
/* 202 */       this.EV.setResult(Event.Result.DENY);
/* 203 */       IMain.UTILS.sendEffect(VIEWER, "noperm");
/* 204 */       IMain.VERSION.actionbar_send(VIEWER, IMain.VARS.M((OfflinePlayer)VIEWER, "furnace_noperm", O.getDisplayName())
/* 205 */           .replace("%PERM%", NP).replace("%SIZE%", (new StringBuilder(String.valueOf(this.D.FURNACES_IDS.size()))).toString()));
/*     */       return;
/*     */     } 
/* 208 */     this.EV.setCancelled(true);
/* 209 */     this.EV.setResult(Event.Result.DENY);
/* 210 */     IMain.UTILS.sendEffect(VIEWER, "create");
/* 211 */     IFurnace F = IMain.DATA.create(this.D.N);
/* 212 */     IMain.VERSION.actionbar_send(VIEWER, IMain.VARS.M((OfflinePlayer)VIEWER, "furnace_create", F));
/* 213 */     if (!O.getName().equalsIgnoreCase(VIEWER.getName())) {
/* 214 */       IMain.UTILS.sendEffect(O, "create");
/* 215 */       O.sendMessage(IMain.VARS.M((OfflinePlayer)VIEWER, "furnace_create_admin", F));
/*     */     } 
/* 217 */     this.D.tick_menu();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void on_reload(Player VIEWER) {
/* 223 */     if (!VIEWER.hasPermission(IMain.CONFIG.perm("reload"))) {
/* 224 */       VIEWER.closeInventory();
/* 225 */       this.EV.setCancelled(true);
/* 226 */       this.EV.setResult(Event.Result.DENY);
/* 227 */       IMain.UTILS.sendEffect(VIEWER, "noperm");
/* 228 */       IMain.VERSION.title_send(VIEWER, IMain.VARS.M((OfflinePlayer)VIEWER, "title"), IMain.VARS.M((OfflinePlayer)VIEWER, "noperm"));
/*     */       return;
/*     */     } 
/* 231 */     if (IMain.COOLDOWN.COOLDOWN_MAP.containsKey(VIEWER)) {
/* 232 */       this.EV.setCancelled(true);
/* 233 */       this.EV.setResult(Event.Result.DENY);
/* 234 */       IMain.VERSION.actionbar_send(VIEWER, IMain.VARS.M((OfflinePlayer)VIEWER, "cooldown"));
/*     */       return;
/*     */     } 
/* 237 */     IMain.DATA.ADMINS.clear();
/* 238 */     IMain.DATA.VIEWERS.clear();
/* 239 */     IMain.COOLDOWN.add(VIEWER);
/* 240 */     this.EV.setCancelled(true);
/* 241 */     this.EV.setResult(Event.Result.DENY);
/* 242 */     for (Player P1 : IMain.S.getOnlinePlayers()) {
/* 243 */       P1.closeInventory();
/* 244 */       IMain.BOSSBAR.hide(P1);
/* 245 */       IMain.VERSION.actionbar_send(P1, IMain.VARS.M((OfflinePlayer)P1, "reload"));
/*     */     } 
/* 247 */     IMain.CONFIG.reload();
/* 248 */     IMain.MENUS.reload();
/* 249 */     IMain.BOSSBAR.load();
/* 250 */     IMain.RECIPES.reload();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   private void MENU_CLOSE(InventoryCloseEvent E) {
/* 258 */     Inventory I = E.getInventory();
/*     */     
/* 260 */     if (I.equals(this.D.MENU)) {
/* 261 */       Player P = (Player)E.getPlayer();
/*     */       
/* 263 */       IMain.DATA.ADMINS.remove(P);
/* 264 */       IMain.VERSION.actionbar_send(P, IMain.VARS.M((OfflinePlayer)P, "gui_close"));
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   private void MENU_DRAG(InventoryDragEvent E) {
/* 271 */     Inventory TOP = E.getView().getTopInventory();
/* 272 */     Inventory B = E.getView().getBottomInventory();
/*     */     
/* 274 */     if (TOP == null || B == null) {
/*     */       return;
/*     */     }
/* 277 */     if (TOP.equals(this.D.MENU) && B.getType() == InventoryType.PLAYER) {
/* 278 */       E.setCancelled(true);
/* 279 */       E.setResult(Event.Result.DENY);
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   private void MENU_CLICK(InventoryClickEvent E) {
/* 286 */     Inventory I = E.getClickedInventory();
/*     */     
/* 288 */     if (I == null) {
/*     */       return;
/*     */     }
/* 291 */     this.EV = E;
/* 292 */     if (E.getView().getTopInventory().equals(this.D.MENU)) {
/* 293 */       if (!this.D.TYPES.contains(E.getClick())) {
/* 294 */         E.setCancelled(true);
/* 295 */         E.setResult(Event.Result.DENY);
/*     */         return;
/*     */       } 
/* 298 */       if (I.getType() == InventoryType.PLAYER) {
/* 299 */         E.setCancelled(true);
/* 300 */         E.setResult(Event.Result.DENY);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 305 */     if (I.equals(this.D.MENU)) {
/* 306 */       int SLOT = E.getSlot();
/* 307 */       ItemStack IT = E.getCurrentItem();
/* 308 */       Player VIEWER = (Player)E.getWhoClicked();
/*     */       
/* 310 */       if (IT == null) {
/*     */         return;
/*     */       }
/* 313 */       if (this.D.MENU_DATA.CONTENT_BY_SLOT.containsKey(Integer.valueOf(SLOT))) {
/* 314 */         IMenu.IContent C = (IMenu.IContent)this.D.MENU_DATA.CONTENT_BY_SLOT.get(Integer.valueOf(SLOT));
/*     */         
/* 316 */         if (C.ITEM_CLICK != null && !C.ITEM_CLICK.isEmpty()) {
/* 317 */           if (C.ITEM_CLICK.equalsIgnoreCase("reload")) {
/* 318 */             on_reload(VIEWER);
/* 319 */           } else if (C.ITEM_CLICK.equalsIgnoreCase("next_page")) {
/* 320 */             on_change_page(PAGE_TYPE.NEXT);
/* 321 */           } else if (C.ITEM_CLICK.equalsIgnoreCase("prev_page")) {
/* 322 */             on_change_page(PAGE_TYPE.PREV);
/* 323 */           } else if (C.ITEM_CLICK.equalsIgnoreCase("menu_close")) {
/* 324 */             VIEWER.closeInventory();
/* 325 */           } else if (C.ITEM_CLICK.equalsIgnoreCase("menu_recipe")) {
/* 326 */             on_opencreator(VIEWER);
/* 327 */           } else if (C.ITEM_CLICK.equalsIgnoreCase("furnace_create")) {
/* 328 */             on_create(VIEWER);
/*     */           } 
/*     */         }
/*     */       } else {
/* 332 */         ClickType T = E.getClick();
/* 333 */         XMaterial MAT = XMaterial.matchXMaterial(IT);
/*     */         
/* 335 */         if (IT.hasItemMeta() && MAT == XMaterial.PLAYER_HEAD) {
/* 336 */           ItemMeta ME = IT.getItemMeta();
/*     */           
/* 338 */           if (ME.hasLore()) {
/* 339 */             String ID = IMain.UTILS.decolor(ME.getLore().get(0));
/*     */             
/* 341 */             if (T == ClickType.RIGHT) {
/* 342 */               on_delete(VIEWER, ID);
/*     */             }
/* 344 */             if (T == ClickType.LEFT) {
/* 345 */               on_openfurnace(VIEWER, ID);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/* 350 */       E.setCancelled(true);
/* 351 */       E.setResult(Event.Result.DENY);
/*     */       return;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\manager\data\IDataM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */