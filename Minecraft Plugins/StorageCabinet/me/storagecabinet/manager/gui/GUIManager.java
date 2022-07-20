/*    */ package me.storagecabinet.manager.gui;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import me.storagecabinet.manager.ManagerAPI;
/*    */ import me.storagecabinet.manager.cabinet.Cabinet;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class GUIManager
/*    */   implements ManagerAPI
/*    */ {
/*    */   private Plugin PL;
/*    */   public MainGUI MAINGUI;
/*    */   public Inventory MAIN_INV;
/*    */   public Map<Inventory, Cabinet> CABINET_GUIS;
/*    */   
/*    */   public GUIManager(Plugin PL) {
/* 20 */     this.PL = PL;
/* 21 */     this.CABINET_GUIS = new HashMap<>();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean register() {
/* 27 */     this.MAINGUI = new MainGUI();
/*    */     
/* 29 */     StorageCabinet.PLUGIN_MANAGER.registerEvents(this.MAINGUI, this.PL);
/* 30 */     StorageCabinet.PLUGIN_MANAGER.registerEvents(new CabinetGUI(), this.PL);
/* 31 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean unregister() {
/* 36 */     this.PL = null;
/* 37 */     this.MAIN_INV = null;
/* 38 */     this.CABINET_GUIS.clear();
/* 39 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\gui\GUIManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */