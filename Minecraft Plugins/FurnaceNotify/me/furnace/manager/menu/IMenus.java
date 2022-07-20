/*    */ package me.furnace.manager.menu;
/*    */ 
/*    */ import me.furnace.IMain;
/*    */ import me.furnace.manager.menu.menu.IMenu;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IMenus
/*    */ {
/*    */   public IMenu MAIN_MENU;
/*    */   public IMenu RECIPE_MENU;
/*    */   
/*    */   public void reload() {
/* 14 */     load();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void load() {
/* 20 */     IMenu A = new IMenu("menu");
/* 21 */     IMenu B = new IMenu("recipe");
/*    */     
/* 23 */     this.MAIN_MENU = A;
/* 24 */     this.RECIPE_MENU = B;
/* 25 */     IMain.UTILS.console("&2Loaded &6" + A.load() + " &2items from the menu.yml file.", true);
/* 26 */     IMain.UTILS.console("&2Loaded &6" + B.load() + " &2items from the recipe.yml file.", true);
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\manager\menu\IMenus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */