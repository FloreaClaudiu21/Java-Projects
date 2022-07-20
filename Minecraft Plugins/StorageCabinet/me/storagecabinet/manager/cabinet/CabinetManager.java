/*     */ package me.storagecabinet.manager.cabinet;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import me.storagecabinet.StorageCabinet;
/*     */ import me.storagecabinet.manager.ManagerAPI;
/*     */ import me.storagecabinet.utils.XMaterial;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.ArmorStand;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CabinetManager
/*     */   implements ManagerAPI
/*     */ {
/*  20 */   public static List<XMaterial> P_T = Arrays.asList(new XMaterial[] { XMaterial.GLASS });
/*     */ 
/*     */   
/*  23 */   public CabinetHopper HOPPER_MECHANICS = new CabinetHopper();
/*  24 */   public CabinetMechanics MECHANICS = new CabinetMechanics(this);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean register() {
/*  30 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean unregister() {
/*  35 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Cabinet get_cabinet_by_item(ItemStack CABINET) {
/*  40 */     if (!is_cabinet_item(CABINET)) {
/*  41 */       return null;
/*     */     }
/*  43 */     ItemMeta META = CABINET.getItemMeta();
/*  44 */     String ID = META.getLocalizedName();
/*     */     
/*  46 */     return (Cabinet)StorageCabinet.STORAGE.CABINETS.get(ID);
/*     */   }
/*     */ 
/*     */   
/*     */   public Cabinet get_cabinet_by_block(Block CABINET) {
/*  51 */     if (!is_cabinet_block(CABINET)) {
/*  52 */       return null;
/*     */     }
/*  54 */     return (Cabinet)StorageCabinet.STORAGE.LOCATIONS.get(CABINET.getLocation());
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack create_cabinet_item_with_cabinet_and_id(String ID, Cabinet C) {
/*  59 */     if (ID == null || C == null) {
/*  60 */       return null;
/*     */     }
/*  62 */     ItemStack IT = XMaterial.STONE_SLAB.parseItem(1);
/*     */     
/*  64 */     ItemMeta META = IT.getItemMeta();
/*     */     
/*  66 */     META.setLocalizedName(ID);
/*  67 */     META.setCustomModelData(Integer.valueOf(C.DATA));
/*  68 */     META.setDisplayName((String)StorageCabinet.SETTINGS.STRING_MAP.get(String.valueOf(path_by_id(C.DATA)) + "name"));
/*  69 */     META.setLore(StorageCabinet.UTILS
/*  70 */         .vars_L((List)StorageCabinet.SETTINGS.LIST_MAP.get(String.valueOf(path_by_id(C.DATA)) + "lores"), C));
/*  71 */     IT.setItemMeta(META);
/*  72 */     return IT;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack create_cabinet_item_with_id(int ID) {
/*  77 */     if (!StorageCabinet.STORAGE.MAP.containsKey(Integer.valueOf(ID))) {
/*  78 */       return null;
/*     */     }
/*  80 */     ItemStack IT = XMaterial.STONE_SLAB.parseItem(1);
/*  81 */     String ID1 = UUID.randomUUID().toString().substring(0, 18);
/*     */     
/*  83 */     ItemMeta META = IT.getItemMeta();
/*  84 */     Cabinet CA = new Cabinet(ID1, IT, ID, (CabinetIDS)StorageCabinet.STORAGE.MAP.get(Integer.valueOf(ID)), true);
/*     */     
/*  86 */     META.setLocalizedName(ID1);
/*  87 */     META.setCustomModelData(Integer.valueOf(ID));
/*  88 */     META.setDisplayName((String)StorageCabinet.SETTINGS.STRING_MAP.get(String.valueOf(path_by_id(ID)) + "name"));
/*  89 */     META.setLore(
/*  90 */         StorageCabinet.UTILS.vars_L((List)StorageCabinet.SETTINGS.LIST_MAP.get(String.valueOf(path_by_id(ID)) + "lores"), CA));
/*  91 */     IT.setItemMeta(META);
/*  92 */     StorageCabinet.STORAGE.CABINETS.put(ID1, CA);
/*  93 */     return IT;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack create_fake_cabinet_item_with_id(int ID) {
/*  98 */     if (!StorageCabinet.STORAGE.MAP.containsKey(Integer.valueOf(ID))) {
/*  99 */       return null;
/*     */     }
/* 101 */     ItemStack IT = XMaterial.STONE_SLAB.parseItem(1);
/* 102 */     String ID1 = UUID.randomUUID().toString().substring(0, 18);
/*     */     
/* 104 */     ItemMeta META = IT.getItemMeta();
/* 105 */     Cabinet CA = new Cabinet(ID1, IT, ID, (CabinetIDS)StorageCabinet.STORAGE.MAP.get(Integer.valueOf(ID)), false);
/*     */     
/* 107 */     META.setLocalizedName(ID1);
/* 108 */     META.setCustomModelData(Integer.valueOf(ID));
/* 109 */     META.setDisplayName((String)StorageCabinet.SETTINGS.STRING_MAP.get(String.valueOf(path_by_id(ID)) + "name"));
/* 110 */     META.setLore(
/* 111 */         StorageCabinet.UTILS.vars_L((List)StorageCabinet.SETTINGS.LIST_MAP.get(String.valueOf(path_by_id(ID)) + "lores"), CA));
/* 112 */     IT.setItemMeta(META);
/* 113 */     return IT;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean is_cabinet_block(Block CABINET) {
/* 118 */     if (CABINET == null || !P_T.contains(XMaterial.matchXMaterial(CABINET.getType())) || 
/* 119 */       !CABINET.hasMetadata("C_B")) {
/* 120 */       return false;
/*     */     }
/* 122 */     Location L = CABINET.getLocation();
/*     */     
/* 124 */     if (!StorageCabinet.STORAGE.LOCATIONS.containsKey(L)) {
/* 125 */       return false;
/*     */     }
/* 127 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean is_stand_loaded(ArmorStand STAND) {
/* 132 */     if (STAND == null) {
/* 133 */       return true;
/*     */     }
/* 135 */     ItemStack CABINET = STAND.getEquipment().getHelmet();
/*     */     
/* 137 */     if (CABINET == null || XMaterial.matchXMaterial(CABINET) != XMaterial.STONE_SLAB || !CABINET.hasItemMeta() || 
/* 138 */       !CABINET.getItemMeta().hasCustomModelData() || !CABINET.getItemMeta().hasLocalizedName()) {
/* 139 */       return true;
/*     */     }
/* 141 */     Block B = STAND.getLocation().getBlock();
/* 142 */     ItemMeta M = CABINET.getItemMeta();
/* 143 */     String UUID = M.getLocalizedName();
/*     */     
/* 145 */     if (B == null || XMaterial.matchXMaterial(B.getType()) != XMaterial.GLASS) {
/* 146 */       return true;
/*     */     }
/* 148 */     if (!StorageCabinet.STORAGE.CABINETS.containsKey(UUID)) {
/* 149 */       return true;
/*     */     }
/* 151 */     Location LO = B.getLocation();
/* 152 */     Cabinet C = (Cabinet)StorageCabinet.STORAGE.CABINETS.get(UUID);
/*     */     
/* 154 */     if (C.LOC == null || C.STAND != null) {
/* 155 */       return true;
/*     */     }
/* 157 */     if (!C.LOC.equals(LO)) {
/* 158 */       return true;
/*     */     }
/* 160 */     C.STAND = STAND;
/* 161 */     StorageCabinet.UTILS.debug("Found the armor stand of the cabinet with the ID: " + UUID, true);
/* 162 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean is_cabinet_stand(ArmorStand STAND) {
/* 167 */     if (STAND == null) {
/* 168 */       return false;
/*     */     }
/* 170 */     ItemStack CABINET = STAND.getEquipment().getHelmet();
/*     */     
/* 172 */     if (CABINET == null || XMaterial.matchXMaterial(CABINET) != XMaterial.STONE_SLAB || !CABINET.hasItemMeta() || 
/* 173 */       !CABINET.getItemMeta().hasCustomModelData() || !CABINET.getItemMeta().hasLocalizedName()) {
/* 174 */       return false;
/*     */     }
/* 176 */     Block B = STAND.getLocation().getBlock();
/* 177 */     String ID = CABINET.getItemMeta().getLocalizedName();
/*     */     
/* 179 */     if (B == null || XMaterial.matchXMaterial(B.getType()) != XMaterial.GLASS) {
/* 180 */       return true;
/*     */     }
/* 182 */     for (Location L : StorageCabinet.STORAGE.LOCATIONS.keySet()) {
/* 183 */       Cabinet C = (Cabinet)StorageCabinet.STORAGE.LOCATIONS.get(L);
/*     */       
/* 185 */       if (C.ID.equals(ID)) {
/* 186 */         return false;
/*     */       }
/*     */     } 
/* 189 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean delete_cabinet_from_database(Cabinet C) {
/* 194 */     if (C == null) {
/* 195 */       return false;
/*     */     }
/*     */     
/* 198 */     StorageCabinet.STORAGE.CABINETS.remove(C.ID);
/*     */     
/* 200 */     if (C.LOC != null && StorageCabinet.STORAGE.LOCATIONS.containsKey(C.LOC)) {
/* 201 */       Block B = C.LOC.getBlock();
/*     */       
/* 203 */       B.setType(XMaterial.AIR.parseMaterial());
/* 204 */       B.getState().update(true);
/* 205 */       if (C.STAND != null) {
/* 206 */         C.STAND.remove();
/* 207 */         C.STAND = null;
/*     */       } 
/*     */     } 
/* 210 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean is_cabinet_item(ItemStack CABINET) {
/* 215 */     if (CABINET == null || XMaterial.matchXMaterial(CABINET) != XMaterial.STONE_SLAB || !CABINET.hasItemMeta() || 
/* 216 */       !CABINET.getItemMeta().hasCustomModelData() || !CABINET.getItemMeta().hasLocalizedName()) {
/* 217 */       return false;
/*     */     }
/* 219 */     ItemMeta M = CABINET.getItemMeta();
/* 220 */     int ID = M.getCustomModelData();
/* 221 */     String ID1 = M.getLocalizedName();
/*     */     
/* 223 */     if (!StorageCabinet.STORAGE.MAP.containsKey(Integer.valueOf(ID))) {
/* 224 */       return false;
/*     */     }
/* 226 */     if (!StorageCabinet.STORAGE.CABINETS.containsKey(ID1)) {
/* 227 */       return false;
/*     */     }
/* 229 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean is_cabinet_item_without_data(ItemStack CABINET) {
/* 234 */     if (CABINET == null) {
/* 235 */       return false;
/*     */     }
/* 237 */     if (!CABINET.hasItemMeta()) {
/* 238 */       return false;
/*     */     }
/* 240 */     ItemMeta META = CABINET.getItemMeta();
/*     */     
/* 242 */     if (XMaterial.matchXMaterial(CABINET) == XMaterial.STONE_SLAB && META.hasCustomModelData() && 
/* 243 */       META.hasLocalizedName()) {
/* 244 */       String ID = META.getLocalizedName();
/*     */       
/* 246 */       if (!StorageCabinet.STORAGE.CABINETS.containsKey(ID)) {
/* 247 */         return true;
/*     */       }
/*     */     } 
/* 250 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String path_by_id(int ID) {
/* 255 */     switch (ID) {
/*     */       case 69:
/* 257 */         return "recipes.acacia_cabinet.";
/*     */       case 66:
/* 259 */         return "recipes.birch_cabinet.";
/*     */       case 74:
/* 261 */         return "recipes.coal_cabinet.";
/*     */       
/*     */       case 73:
/* 264 */         return "recipes.cobble_cabinet.";
/*     */       
/*     */       case 72:
/* 267 */         return "recipes.crimson_cabinet.";
/*     */       
/*     */       case 70:
/* 270 */         return "recipes.darkoak_cabinet.";
/*     */       
/*     */       case 79:
/* 273 */         return "recipes.diamond_cabinet.";
/*     */       
/*     */       case 80:
/* 276 */         return "recipes.emerald_cabinet.";
/*     */       
/*     */       case 76:
/* 279 */         return "recipes.gold_cabinet.";
/*     */       
/*     */       case 75:
/* 282 */         return "recipes.iron_cabinet.";
/*     */       
/*     */       case 67:
/* 285 */         return "recipes.jungle_cabinet.";
/*     */       
/*     */       case 77:
/* 288 */         return "recipes.lapis_cabinet.";
/*     */       
/*     */       case 81:
/* 291 */         return "recipes.netherite_cabinet.";
/*     */       
/*     */       case 65:
/* 294 */         return "recipes.oak_cabinet.";
/*     */       
/*     */       case 78:
/* 297 */         return "recipes.redstone_cabinet.";
/*     */       
/*     */       case 68:
/* 300 */         return "recipes.spruce_cabinet.";
/*     */       
/*     */       case 71:
/* 303 */         return "recipes.warped_cabinet.";
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 308 */     return "";
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\cabinet\CabinetManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */