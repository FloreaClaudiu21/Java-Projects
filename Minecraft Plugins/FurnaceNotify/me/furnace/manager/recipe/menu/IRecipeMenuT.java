/*    */ package me.furnace.manager.recipe.menu;
/*    */ 
/*    */ import me.furnace.IMain;
/*    */ import me.furnace.manager.menu.menu.IMenu;
/*    */ import me.furnace.manager.menu.menu.IMenuVars;
/*    */ 
/*    */ public class IRecipeMenuT
/*    */ {
/*    */   private IRecipeMenu D;
/*    */   
/*    */   public IRecipeMenuT(IRecipeMenu D) {
/* 12 */     this.D = D;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void stop() {
/* 22 */     if (this.D.MENU_TASK != null) {
/* 23 */       this.D.MENU_TASK.cancel();
/*    */     }
/* 25 */     if (this.D.CONTENT_TASK != null) {
/* 26 */       this.D.CONTENT_TASK.cancel();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void start() {
/* 33 */     if (this.D.MENU_DATA == null) {
/*    */       return;
/*    */     }
/* 36 */     if (this.D.MENU_TASK == null) {
/* 37 */       this.D.MENU_TASK = IMain.SC.runTaskTimerAsynchronously(IMain.PL, new Runnable() {
/*    */             public void run() {
/* 39 */               IRecipeMenuT.this.tick_menu();
/*    */             }
/* 41 */           },  20L, 20L);
/*    */     }
/* 43 */     if (this.D.CONTENT_TASK == null) {
/* 44 */       this.D.CONTENT_TASK = IMain.SC.runTaskTimerAsynchronously(IMain.PL, new Runnable() {
/*    */             public void run() {
/* 46 */               IRecipeMenuT.this.tick_content();
/*    */             }
/* 48 */           },  20L, this.D.MENU_DATA.UPDATE_INTERVAL * 1L);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void tick_content() {
/* 57 */     IMenuVars VARS = new IMenuVars(this.D.COOKTIME, this.D.EXPERIENCE, this.D.A_TIME, this.D.R_TIME, this.D.A_XP, this.D.R_XP);
/*    */     
/* 59 */     for (IMenu.IContent CONTENT : this.D.MENU_DATA.MENU_CONTENT) {
/* 60 */       CONTENT.update(VARS);
/* 61 */       this.D.MENU.setItem(CONTENT.ITEM_SLOT, CONTENT.ITEM);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void tick_menu() {
/* 68 */     if (this.D.MENU.getViewers().isEmpty() && this.D.LAST_USER != null) {
/* 69 */       this.D.give_items();
/* 70 */       this.D.INUSE = false;
/* 71 */       this.D.LAST_USER = null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\manager\recipe\menu\IRecipeMenuT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */