/*    */ package me.furnace.manager.recipe.menu;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import me.furnace.IMain;
/*    */ import me.furnace.manager.furnace.IFurnaceU;
/*    */ import me.furnace.manager.menu.menu.IMenu;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.scheduler.BukkitTask;
/*    */ 
/*    */ public class IRecipeMenu
/*    */ {
/*    */   public boolean INUSE;
/*    */   protected boolean SAVE;
/*    */   public Inventory MENU;
/*    */   public Player LAST_USER;
/*    */   protected IMenu MENU_DATA;
/*    */   private IRecipeMenuT TICKER;
/* 21 */   protected int COOKTIME = 200;
/*    */   private IRecipeMenuM MECHANICS;
/* 23 */   protected double EXPERIENCE = 0.5D;
/* 24 */   protected int A_XP1 = 1, R_XP1 = 1;
/* 25 */   protected int A_TIME = 1; protected int R_TIME = 1;
/* 26 */   protected double A_XP = 0.1D; protected double R_XP = 0.1D;
/*    */   public BukkitTask MENU_TASK;
/*    */   public BukkitTask CONTENT_TASK;
/* 29 */   private List<Integer> EMPTY_SLOTS = Arrays.asList(new Integer[] { Integer.valueOf(16), Integer.valueOf(34) });
/*    */   
/*    */   public IRecipeMenu() {
/* 32 */     this.TICKER = new IRecipeMenuT(this);
/* 33 */     this.MECHANICS = new IRecipeMenuM(this);
/* 34 */     this.MENU_DATA = IMain.MENUS.RECIPE_MENU;
/* 35 */     this.MENU = IMain.S.createInventory(null, this.MENU_DATA.MENU_SIZE, this.MENU_DATA.MENU_NAME);
/* 36 */     this.TICKER.start();
/* 37 */     this.MECHANICS.start();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double round(double d) {
/* 48 */     d *= 100.0D;
/* 49 */     d = Math.round(d);
/* 50 */     d /= 100.0D;
/* 51 */     return d;
/*    */   }
/*    */ 
/*    */   
/*    */   public void give_items() {
/* 56 */     if (this.LAST_USER != null && this.LAST_USER.isOnline()) {
/* 57 */       ItemStack S = this.MENU.getItem(this.MENU_DATA.MENU_S_SLOT);
/* 58 */       ItemStack R = this.MENU.getItem(this.MENU_DATA.MENU_R_SLOT);
/*    */       
/* 60 */       if (!IFurnaceU.E(R)) {
/* 61 */         this.LAST_USER.getInventory().addItem(new ItemStack[] { R });
/*    */       }
/* 63 */       if (!IFurnaceU.E(S)) {
/* 64 */         this.LAST_USER.getInventory().addItem(new ItemStack[] { S });
/*    */       }
/*    */     } 
/* 67 */     this.MENU.setItem(this.MENU_DATA.MENU_R_SLOT, IMain.EMPTY_ITEM);
/* 68 */     this.MENU.setItem(this.MENU_DATA.MENU_S_SLOT, IMain.EMPTY_ITEM);
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\manager\recipe\menu\IRecipeMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */