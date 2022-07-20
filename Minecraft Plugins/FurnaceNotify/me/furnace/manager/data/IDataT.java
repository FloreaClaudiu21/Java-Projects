/*     */ package me.furnace.manager.data;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.furnace.IMain;
/*     */ import me.furnace.manager.furnace.IFurnace;
/*     */ import me.furnace.manager.menu.menu.IMenu;
/*     */ import me.furnace.manager.menu.menu.IMenuVars;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ public class IDataT
/*     */ {
/*     */   private IData D;
/*     */   
/*     */   public IDataT(IData D) {
/*  19 */     this.D = D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() {
/*  29 */     if (this.D.MENU_TASK != null) {
/*  30 */       this.D.MENU_TASK.cancel();
/*     */     }
/*  32 */     if (this.D.VIEWERS_TASK != null) {
/*  33 */       this.D.VIEWERS_TASK.cancel();
/*     */     }
/*  35 */     if (this.D.CONTENT_TASK != null) {
/*  36 */       this.D.CONTENT_TASK.cancel();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void start() {
/*  43 */     if (this.D.MENU_DATA == null) {
/*     */       return;
/*     */     }
/*  46 */     if (this.D.VIEWERS_TASK == null) {
/*  47 */       this.D.VIEWERS_TASK = IMain.SC.runTaskTimer(IMain.PL, new Runnable() {
/*     */             public void run() {
/*  49 */               IDataT.this.tick_player();
/*     */             }
/*  51 */           },  20L, 20L);
/*     */     }
/*  53 */     if (this.D.MENU_TASK == null) {
/*  54 */       this.D.VIEWERS_TASK = IMain.SC.runTaskTimerAsynchronously(IMain.PL, new Runnable() {
/*     */             public void run() {
/*  56 */               IDataT.this.tick_menu();
/*     */             }
/*  58 */           },  10L, 10L);
/*     */     }
/*  60 */     if (this.D.CONTENT_TASK == null) {
/*  61 */       this.D.CONTENT_TASK = IMain.SC.runTaskTimerAsynchronously(IMain.PL, new Runnable() {
/*     */             public void run() {
/*  63 */               IDataT.this.tick_content();
/*     */             }
/*  65 */           },  20L, this.D.MENU_DATA.UPDATE_INTERVAL * 1L);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean tick_menu() {
/*  74 */     int S = 0;
/*  75 */     int FINAL = this.D.START + this.D.MENU_DATA.EMPTY_SLOTS.size();
/*     */     
/*  77 */     if (FINAL > this.D.FURNACES.size()) {
/*  78 */       FINAL = this.D.FURNACES.size();
/*     */     }
/*  80 */     for (int I1 = this.D.START; I1 < FINAL; I1++) {
/*  81 */       String ID = this.D.FURNACES_IDS.get(I1);
/*  82 */       IFurnace F = this.D.FURNACES.get(ID);
/*     */       
/*  84 */       ItemStack IT = this.D.MENU.getItem(((Integer)this.D.MENU_DATA.EMPTY_SLOTS.get(S)).intValue());
/*  85 */       this.D.MENU_DATA.update_furnace(F, IT);
/*  86 */       S++;
/*     */     } 
/*  88 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void tick_player() {
/*  93 */     if (this.D.MENU.getViewers().isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/*  97 */     List<HumanEntity> L = new ArrayList<>();
/*     */     
/*  99 */     for (HumanEntity VIEWER : this.D.MENU.getViewers()) {
/* 100 */       Player P = (Player)VIEWER;
/*     */       
/* 102 */       if (!VIEWER.hasPermission(IMain.UTILS.PERM(P, "menu"))) {
/* 103 */         L.add(VIEWER);
/*     */       }
/*     */     } 
/* 106 */     if (!L.isEmpty()) {
/* 107 */       for (HumanEntity VIEWER : L) {
/* 108 */         VIEWER.closeInventory();
/* 109 */         IMain.UTILS.sendEffect((Player)VIEWER, "noperm");
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void tick_content() {
/* 117 */     int FINAL = this.D.START + this.D.MENU_DATA.EMPTY_SLOTS.size();
/* 118 */     IMenuVars VARS = new IMenuVars(this.D.OWNER, this.D.PAGE + 1, this.D.PAGE - 1, this.D.PAGE);
/*     */     
/* 120 */     for (IMenu.IContent CONTENT : this.D.MENU_DATA.MENU_CONTENT) {
/* 121 */       if (CONTENT.ITEM_CLICK != null && !CONTENT.ITEM_CLICK.isEmpty()) {
/* 122 */         if (CONTENT.ITEM_CLICK.equalsIgnoreCase("next_page")) {
/* 123 */           if (this.D.FURNACES.size() > FINAL) {
/* 124 */             CONTENT.update(VARS);
/* 125 */             this.D.MENU.setItem(CONTENT.ITEM_SLOT, CONTENT.ITEM); continue;
/*     */           } 
/* 127 */           CONTENT.update(VARS);
/* 128 */           this.D.MENU.setItem(CONTENT.ITEM_SLOT, CONTENT.DEFAULT); continue;
/*     */         } 
/* 130 */         if (CONTENT.ITEM_CLICK.equalsIgnoreCase("prev_page")) {
/* 131 */           if (this.D.PAGE > 1) {
/* 132 */             CONTENT.update(VARS);
/* 133 */             this.D.MENU.setItem(CONTENT.ITEM_SLOT, CONTENT.ITEM); continue;
/*     */           } 
/* 135 */           CONTENT.update(VARS);
/* 136 */           this.D.MENU.setItem(CONTENT.ITEM_SLOT, CONTENT.DEFAULT);
/*     */           continue;
/*     */         } 
/* 139 */         CONTENT.update(VARS);
/* 140 */         this.D.MENU.setItem(CONTENT.ITEM_SLOT, CONTENT.ITEM);
/*     */         continue;
/*     */       } 
/* 143 */       CONTENT.update(VARS);
/* 144 */       this.D.MENU.setItem(CONTENT.ITEM_SLOT, CONTENT.ITEM);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\manager\data\IDataT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */