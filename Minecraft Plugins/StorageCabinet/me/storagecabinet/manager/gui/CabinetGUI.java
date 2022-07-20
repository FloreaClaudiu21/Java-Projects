/*     */ package me.storagecabinet.manager.gui;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import me.storagecabinet.StorageCabinet;
/*     */ import me.storagecabinet.manager.cabinet.Cabinet;
/*     */ import me.storagecabinet.utils.XMaterial;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*     */ import org.bukkit.event.inventory.InventoryType;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ public class CabinetGUI
/*     */   implements Listener {
/*  24 */   private static List<InventoryAction> DL = Arrays.asList(new InventoryAction[] { InventoryAction.HOTBAR_SWAP, 
/*  25 */         InventoryAction.HOTBAR_MOVE_AND_READD, InventoryAction.SWAP_WITH_CURSOR });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static ItemStack custom(XMaterial MAT) {
/*  33 */     if (MAT == null) {
/*  34 */       return null;
/*     */     }
/*  36 */     ItemStack IT = MAT.parseItem(1);
/*  37 */     ItemMeta M = IT.getItemMeta();
/*     */     
/*  39 */     M.setLocalizedName("GUI");
/*  40 */     IT.setItemMeta(M);
/*  41 */     return IT;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack iron_bars() {
/*  46 */     ItemStack IT = XMaterial.IRON_BARS.parseItem(1);
/*  47 */     ItemMeta M = IT.getItemMeta();
/*     */     
/*  49 */     M.setLocalizedName("GUI");
/*  50 */     M.addEnchant(Enchantment.ARROW_FIRE, 1, false);
/*  51 */     M.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
/*  52 */     IT.setItemMeta(M);
/*  53 */     return IT;
/*     */   }
/*     */ 
/*     */   
/*     */   private static ItemStack hopper(Cabinet C) {
/*  58 */     ItemStack IT = XMaterial.HOPPER.parseItem(1);
/*  59 */     ItemMeta M = IT.getItemMeta();
/*  60 */     List<String> L = (List<String>)StorageCabinet.SETTINGS.LIST_MAP.get("messages.cabinetgui_hopper_lores");
/*  61 */     L = StorageCabinet.UTILS.vars_L(L, C);
/*     */     
/*  63 */     M.setLocalizedName("GUI");
/*  64 */     M.setDisplayName((
/*  65 */         (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.cabinetgui_hopper")).replace("{ID}", (new StringBuilder(String.valueOf(C.ID))).toString()));
/*  66 */     M.setLore(L);
/*  67 */     M.addEnchant(Enchantment.ARROW_FIRE, 1, false);
/*  68 */     M.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
/*  69 */     IT.setItemMeta(M);
/*  70 */     return IT;
/*     */   }
/*     */ 
/*     */   
/*     */   private static ItemStack arrow(int ID, int PN) {
/*  75 */     ItemStack IT = XMaterial.STONE_SLAB.parseItem(1);
/*  76 */     ItemMeta M = IT.getItemMeta();
/*     */     
/*  78 */     if (ID == 1000) {
/*  79 */       M.setDisplayName(((String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.cabinetgui_leftarrow"))
/*  80 */           .replace("{PREV_PAGE}", (new StringBuilder(String.valueOf(PN - 1))).toString()));
/*  81 */       M.setLocalizedName((new StringBuilder(String.valueOf(PN - 1))).toString());
/*     */     } else {
/*  83 */       M.setDisplayName(((String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.cabinetgui_rightarrow"))
/*  84 */           .replace("{NEXT_PAGE}", (new StringBuilder(String.valueOf(PN + 1))).toString()));
/*  85 */       M.setLocalizedName((new StringBuilder(String.valueOf(PN + 1))).toString());
/*     */     } 
/*  87 */     M.setCustomModelData(Integer.valueOf(ID));
/*  88 */     IT.setItemMeta(M);
/*  89 */     return IT;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Inventory create_cabinet_filter_gui(Cabinet CABINET, int SIZE) {
/*  94 */     if (CABINET == null || SIZE <= 0) {
/*  95 */       return null;
/*     */     }
/*  97 */     String TITLE = (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.cabinetgui_filter");
/*  98 */     if (TITLE.length() > 52) {
/*  99 */       TITLE = TITLE.substring(0, 52);
/*     */     }
/* 101 */     TITLE = TITLE.replace("{TYPE}", CABINET.TYPE.name().replace("_", " ")).replace("{ID}", CABINET.ID)
/* 102 */       .replace("{MAX_PAGE}", (new StringBuilder(String.valueOf(CABINET.PAGES))).toString());
/* 103 */     Inventory I = StorageCabinet.SERVER.createInventory(null, SIZE, TITLE);
/*     */     
/*     */     int I1;
/*     */     
/* 107 */     for (I1 = 0; I1 < 9; I1++) {
/* 108 */       I.setItem(I1, iron_bars());
/*     */     }
/* 110 */     for (I1 = 27; I1 < 36; I1++) {
/* 111 */       I.setItem(I1, iron_bars());
/*     */     }
/*     */     
/* 114 */     ItemStack AR = custom(XMaterial.ARROW);
/*     */     
/* 116 */     ItemMeta META2 = AR.getItemMeta();
/* 117 */     META2.setDisplayName((String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.cabinetgui_arrow"));
/* 118 */     AR.setItemMeta(META2);
/* 119 */     I.setItem(0, AR);
/*     */     
/* 121 */     ItemStack R = custom(XMaterial.RED_STAINED_GLASS_PANE);
/* 122 */     ItemStack G = custom(XMaterial.GREEN_STAINED_GLASS_PANE);
/*     */     
/* 124 */     ItemMeta META = G.getItemMeta();
/* 125 */     ItemMeta META1 = R.getItemMeta();
/*     */     
/* 127 */     META.setDisplayName((String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.cabinetgui_in"));
/* 128 */     META.setLore(StorageCabinet.UTILS.vars_L((List)StorageCabinet.SETTINGS.LIST_MAP.get("messages.cabinetgui_in_lores"), 
/* 129 */           CABINET));
/* 130 */     G.setItemMeta(META);
/* 131 */     I.setItem(4, G);
/*     */     
/* 133 */     META1.setDisplayName((String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.cabinetgui_out"));
/* 134 */     META1.setLore(StorageCabinet.UTILS.vars_L((List)StorageCabinet.SETTINGS.LIST_MAP.get("messages.cabinetgui_out_lores"), 
/* 135 */           CABINET));
/* 136 */     R.setItemMeta(META1);
/* 137 */     I.setItem(4, G);
/* 138 */     I.setItem(31, R);
/*     */     
/* 140 */     CABINET.FILTER_INV = I;
/* 141 */     return I;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Inventory create_cabinet_gui(Cabinet CABINET, int SIZE, int PN) {
/* 146 */     if (CABINET == null || CABINET.TYPE == null || SIZE <= 0 || SIZE > 54) {
/* 147 */       return null;
/*     */     }
/* 149 */     String TITLE = (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.cabinetgui_title");
/* 150 */     if (TITLE.length() > 52) {
/* 151 */       TITLE = TITLE.substring(0, 52);
/*     */     }
/* 153 */     TITLE = TITLE.replace("{TYPE}", CABINET.TYPE.name().replace("_", " ")).replace("{PAGE}", (new StringBuilder(String.valueOf(PN))).toString())
/* 154 */       .replace("{MAX_PAGE}", (new StringBuilder(String.valueOf(CABINET.PAGES))).toString());
/* 155 */     Inventory I = StorageCabinet.SERVER.createInventory(null, SIZE, TITLE);
/*     */     
/* 157 */     for (int I1 = SIZE - 3; I1 < SIZE; I1++) {
/* 158 */       I.setItem(I1, iron_bars());
/*     */     }
/* 160 */     I.setItem(I.getSize() - 3, hopper(CABINET));
/* 161 */     if (PN > 1) {
/* 162 */       I.setItem(I.getSize() - 2, arrow(1000, PN));
/*     */     }
/* 164 */     if (PN < CABINET.PAGES) {
/* 165 */       I.setItem(I.getSize() - 1, arrow(1001, PN));
/*     */     }
/* 167 */     StorageCabinet.GUI_MANAGER.CABINET_GUIS.put(I, CABINET);
/* 168 */     return I;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onCabinetGUI_close(InventoryCloseEvent EV) {
/* 173 */     Inventory I = EV.getInventory();
/* 174 */     Player P = (Player)EV.getPlayer();
/*     */     
/* 176 */     if (StorageCabinet.GUI_MANAGER.CABINET_GUIS.containsKey(I)) {
/* 177 */       StorageCabinet.SCHEDULER.runTaskLater(StorageCabinet.PLUGIN, () -> {
/*     */             InventoryType T = paramPlayer.getOpenInventory().getType();
/*     */             
/*     */             if (T == InventoryType.CRAFTING || T == InventoryType.CREATIVE) {
/*     */               StorageCabinet.STORAGE.PREVIEW.remove(paramPlayer);
/*     */               paramPlayer.playSound(paramPlayer.getLocation(), Sound.BLOCK_CHEST_CLOSE, 1.0F, 0.8F);
/*     */             } 
/* 184 */           }10L);
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onCabinetGUI_click(InventoryClickEvent EV) {
/* 192 */     InventoryAction A = EV.getAction();
/* 193 */     ItemStack IT = EV.getCurrentItem();
/* 194 */     Inventory TOP = EV.getView().getTopInventory();
/* 195 */     Inventory BOT = EV.getView().getBottomInventory();
/*     */     
/* 197 */     if (StorageCabinet.GUI_MANAGER.CABINET_GUIS.containsKey(TOP) && BOT.getType() == InventoryType.PLAYER) {
/* 198 */       int SLOT = EV.getSlot();
/* 199 */       Player P = (Player)EV.getWhoClicked();
/* 200 */       Cabinet C = StorageCabinet.GUI_MANAGER.CABINET_GUIS.get(TOP);
/*     */       
/* 202 */       if (DL.contains(A)) {
/* 203 */         EV.setCancelled(true);
/*     */         
/*     */         return;
/*     */       } 
/* 207 */       if (StorageCabinet.STORAGE.PREVIEW.contains(P) && 
/* 208 */         !P.hasPermission(StorageCabinet.PERM_MANAGER.CABINET_USE_ADMIN) && 
/* 209 */         SLOT != TOP.getSize() - 1 && SLOT != TOP.getSize() - 2 && SLOT != TOP.getSize() - 3) {
/* 210 */         EV.setCancelled(true);
/*     */         
/*     */         return;
/*     */       } 
/* 214 */       if (IT == null) {
/*     */         return;
/*     */       }
/* 217 */       if (!IT.hasItemMeta()) {
/*     */         return;
/*     */       }
/* 220 */       ItemMeta META = IT.getItemMeta();
/*     */       
/* 222 */       if (!META.hasLocalizedName()) {
/*     */         return;
/*     */       }
/* 225 */       XMaterial MAT = XMaterial.matchXMaterial(IT);
/*     */ 
/*     */       
/* 228 */       if (MAT == XMaterial.IRON_BARS) {
/* 229 */         EV.setCancelled(true);
/*     */         
/*     */         return;
/*     */       } 
/* 233 */       if (StorageCabinet.CABINET_MANAGER.is_cabinet_item(IT)) {
/* 234 */         EV.setCancelled(true);
/*     */         
/*     */         return;
/*     */       } 
/* 238 */       if (SLOT == TOP.getSize() - 1 && MAT == XMaterial.STONE_SLAB && META.hasCustomModelData()) {
/* 239 */         int NP = Integer.valueOf(META.getLocalizedName()).intValue();
/*     */ 
/*     */         
/* 242 */         EV.setCancelled(true);
/* 243 */         Inventory NI = C.IN_L.get(NP - 1);
/* 244 */         if (NI != null) {
/* 245 */           P.openInventory(NI);
/*     */         }
/* 247 */         P.playSound(P.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 0.8F);
/*     */         
/*     */         return;
/*     */       } 
/* 251 */       if (SLOT == TOP.getSize() - 2 && MAT == XMaterial.STONE_SLAB && META.hasCustomModelData()) {
/* 252 */         int PP = Integer.valueOf(META.getLocalizedName()).intValue();
/*     */ 
/*     */         
/* 255 */         EV.setCancelled(true);
/* 256 */         Inventory NI = C.IN_L.get(PP - 1);
/* 257 */         if (NI != null) {
/* 258 */           P.openInventory(NI);
/*     */         }
/* 260 */         P.playSound(P.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 0.8F);
/*     */         
/*     */         return;
/*     */       } 
/* 264 */       if (SLOT == TOP.getSize() - 3 && MAT == XMaterial.HOPPER) {
/* 265 */         EV.setCancelled(true);
/* 266 */         P.openInventory(C.FILTER_INV);
/* 267 */         P.playSound(P.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 0.8F);
/*     */         return;
/*     */       } 
/*     */       return;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\gui\CabinetGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */