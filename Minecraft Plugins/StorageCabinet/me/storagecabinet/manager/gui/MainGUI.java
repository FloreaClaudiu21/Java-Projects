/*     */ package me.storagecabinet.manager.gui;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import me.storagecabinet.StorageCabinet;
/*     */ import me.storagecabinet.manager.cabinet.CabinetIDS;
/*     */ import me.storagecabinet.utils.XMaterial;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ public class MainGUI
/*     */   implements Listener {
/*  22 */   private static List<InventoryAction> AC = Arrays.asList(new InventoryAction[] { InventoryAction.PICKUP_ALL, InventoryAction.PICKUP_ONE });
/*     */   
/*     */   public MainGUI() {
/*  25 */     setup_main_gui();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setup_main_gui() {
/*  31 */     int ISIZE = 18;
/*  32 */     Set<Integer> IDS = StorageCabinet.STORAGE.MAP.keySet();
/*     */     
/*  34 */     int S = IDS.size();
/*  35 */     if (S >= 18) {
/*  36 */       ISIZE = 27;
/*  37 */     } else if (S >= 27) {
/*  38 */       ISIZE = 36;
/*  39 */     } else if (S >= 36) {
/*  40 */       ISIZE = 45;
/*  41 */     } else if (S >= 45) {
/*  42 */       ISIZE = 54;
/*     */     } 
/*  44 */     String TITLE = (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.maingui_title");
/*  45 */     if (TITLE.length() > 52) {
/*  46 */       TITLE = TITLE.substring(0, 52);
/*     */     }
/*  48 */     StorageCabinet.GUI_MANAGER.MAIN_INV = StorageCabinet.SERVER.createInventory(null, ISIZE, TITLE);
/*     */     
/*  50 */     Inventory I = StorageCabinet.GUI_MANAGER.MAIN_INV;
/*  51 */     for (int IN = 0; IN < I.getSize(); IN++) {
/*  52 */       I.setItem(IN, CabinetGUI.iron_bars());
/*     */     }
/*  54 */     int P = 0;
/*  55 */     for (Iterator<Integer> iterator = StorageCabinet.STORAGE.MAP.keySet().iterator(); iterator.hasNext(); ) { int ID = ((Integer)iterator.next()).intValue();
/*  56 */       I.setItem(P, StorageCabinet.CABINET_MANAGER.create_fake_cabinet_item_with_id(ID));
/*  57 */       P++; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean open_main_gui(Player P) {
/*  64 */     if (P == null || !P.hasPermission(StorageCabinet.PERM_MANAGER.COMMAND_MENU)) {
/*  65 */       return false;
/*     */     }
/*  67 */     P.openInventory(StorageCabinet.GUI_MANAGER.MAIN_INV);
/*  68 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean give_cabinet_with_id(Player P, ItemStack C) {
/*  73 */     if (P == null || C == null || !C.hasItemMeta()) {
/*  74 */       return false;
/*     */     }
/*  76 */     ItemMeta META = C.getItemMeta();
/*  77 */     String PH = META.getDisplayName();
/*  78 */     String UUID = META.getLocalizedName();
/*  79 */     PlayerInventory PI = P.getInventory();
/*     */     
/*  81 */     if (PH == null || PH.isEmpty())
/*  82 */       PH = CabinetIDS.get_type_by_item(C).name();  byte b;
/*     */     int i;
/*     */     ItemStack[] arrayOfItemStack;
/*  85 */     for (i = (arrayOfItemStack = PI.getStorageContents()).length, b = 0; b < i; ) { ItemStack I = arrayOfItemStack[b];
/*  86 */       if (I == null || I.getType() == XMaterial.AIR.parseMaterial()) {
/*  87 */         PI.addItem(new ItemStack[] { C });
/*  88 */         StorageCabinet.UTILS.send_msg(P, ((String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.receive_cabinet"))
/*  89 */             .replace("{CABINET}", PH).replace("{ID}", UUID));
/*  90 */         return true;
/*     */       } 
/*     */       b++; }
/*     */     
/*  94 */     P.getWorld().dropItem(P.getLocation(), C);
/*  95 */     P.sendMessage(((String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.receive_cabinet_fullinv"))
/*  96 */         .replace("{CABINET}", PH).replace("{ID}", UUID));
/*  97 */     return true;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onMainGUI_interact(InventoryClickEvent EV) {
/* 102 */     Player P = (Player)EV.getWhoClicked();
/* 103 */     InventoryAction ACTION = EV.getAction();
/* 104 */     Inventory CI = EV.getClickedInventory();
/*     */     
/* 106 */     if (CI == null) {
/*     */       return;
/*     */     }
/* 109 */     if (CI.equals(StorageCabinet.GUI_MANAGER.MAIN_INV)) {
/* 110 */       ItemStack IT = EV.getCurrentItem();
/*     */       
/* 112 */       EV.setCancelled(true);
/* 113 */       if (AC.contains(ACTION) && 
/* 114 */         IT != null && XMaterial.matchXMaterial(IT) == XMaterial.STONE_SLAB && IT.hasItemMeta() && 
/* 115 */         IT.getItemMeta().hasCustomModelData()) {
/* 116 */         int ID = IT.getItemMeta().getCustomModelData();
/* 117 */         ItemStack CABINET = StorageCabinet.CABINET_MANAGER.create_cabinet_item_with_id(ID);
/*     */         
/* 119 */         give_cabinet_with_id(P, CABINET);
/*     */         return;
/*     */       } 
/*     */       return;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\gui\MainGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */