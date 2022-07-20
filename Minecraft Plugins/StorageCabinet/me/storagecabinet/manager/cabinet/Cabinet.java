/*     */ package me.storagecabinet.manager.cabinet;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.time.LocalDateTime;
/*     */ import java.time.format.DateTimeFormatter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import me.storagecabinet.StorageCabinet;
/*     */ import me.storagecabinet.manager.gui.CabinetGUI;
/*     */ import me.storagecabinet.utils.XMaterial;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.block.Hopper;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.ArmorStand;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*     */ import org.bukkit.event.inventory.InventoryDragEvent;
/*     */ import org.bukkit.event.inventory.InventoryType;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.metadata.FixedMetadataValue;
/*     */ import org.bukkit.metadata.MetadataValue;
/*     */ 
/*     */ public class Cabinet implements Listener {
/*     */   public String ID;
/*     */   public Location LOC;
/*  44 */   public int DATA = 0;
/*  45 */   public int PAGES = 1;
/*     */   public ItemStack ITEM;
/*     */   public Filter FILTER;
/*     */   public CabinetIDS TYPE;
/*     */   public CabinetAnim ANIM;
/*     */   private File CABINET_FILE;
/*     */   public CabinetHopper HOPPER;
/*     */   public Inventory FILTER_INV;
/*     */   public List<Inventory> IN_L;
/*     */   public boolean FOUND = false;
/*     */   public List<Integer> ANIM_IDS;
/*  56 */   public ArmorStand STAND = null;
/*     */   public boolean IS_OPEN = false;
/*     */   public boolean isClosing = false;
/*  59 */   private int FREE_S = 0; private int CAPACITY = 0;
/*  60 */   private static List<InventoryAction> DL = Arrays.asList(new InventoryAction[] { InventoryAction.HOTBAR_SWAP, 
/*  61 */         InventoryAction.HOTBAR_MOVE_AND_READD, InventoryAction.SWAP_WITH_CURSOR, InventoryAction.CLONE_STACK, 
/*  62 */         InventoryAction.DROP_ALL_SLOT });
/*     */   
/*     */   public Cabinet(File CABINET_FILE) {
/*  65 */     this.CABINET_FILE = CABINET_FILE;
/*  66 */     this.ANIM = new CabinetAnim(this);
/*  67 */     this.IN_L = new ArrayList<>();
/*  68 */     this.ANIM_IDS = new ArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   public Cabinet(String ID, ItemStack ITEM, int DATA, CabinetIDS T, boolean IN) {
/*  73 */     this.ID = ID;
/*  74 */     this.TYPE = T;
/*  75 */     this.ITEM = ITEM;
/*  76 */     this.DATA = DATA;
/*  77 */     this.ANIM = new CabinetAnim(this);
/*  78 */     this.IN_L = new ArrayList<>();
/*  79 */     this.ANIM_IDS = new ArrayList<>();
/*     */     
/*  81 */     load_types(IN);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int max_slots() {
/*  87 */     return this.CAPACITY - this.PAGES * 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public int free_slots() {
/*  92 */     this.FREE_S = 0;
/*  93 */     if (this.IN_L.isEmpty()) {
/*  94 */       return max_slots();
/*     */     }
/*  96 */     this.IN_L.forEach(I -> {
/*     */           ItemStack[] ITS = I.getStorageContents(); ItemStack[] arrayOfItemStack1;
/*     */           int i = (arrayOfItemStack1 = ITS).length;
/*     */           for (byte b = 0; b < i; b++) {
/*     */             ItemStack IT = arrayOfItemStack1[b];
/*     */             if (IT == null || IT.getType() == Material.AIR)
/*     */               this.FREE_S++; 
/*     */           } 
/*     */         });
/* 105 */     return this.FREE_S;
/*     */   }
/*     */ 
/*     */   
/*     */   public double free_space() {
/* 110 */     int percent = free_slots() * 100 / max_slots();
/* 111 */     double b = Math.round(percent * 10.0D) / 10.0D;
/* 112 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean load() {
/*     */     try {
/* 119 */       if (this.CABINET_FILE == null || !this.CABINET_FILE.exists()) {
/* 120 */         return false;
/*     */       }
/*     */       
/* 123 */       YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(this.CABINET_FILE);
/*     */ 
/*     */       
/* 126 */       if (!has_paths((FileConfiguration)yamlConfiguration)) {
/* 127 */         return false;
/*     */       }
/*     */       
/* 130 */       this.ID = yamlConfiguration.getString("ID");
/*     */       
/* 132 */       this.DATA = yamlConfiguration.getInt("DATA");
/*     */       
/* 134 */       this.LOC = load_loc(yamlConfiguration.getString("LOCATION"));
/*     */       
/* 136 */       String T = yamlConfiguration.getString("TYPE");
/* 137 */       CabinetIDS TYPE = CabinetIDS.valueOf(T);
/* 138 */       this.TYPE = TYPE;
/*     */       
/* 140 */       load_types(true);
/*     */       
/* 142 */       ConfigurationSection CS1 = yamlConfiguration.getConfigurationSection("FILTERINV.ITEMS");
/*     */       
/* 144 */       if (CS1 != null && !CS1.getKeys(false).isEmpty()) {
/* 145 */         this.FILTER.SLOTS.forEach((I1, TYP) -> {
/*     */               ConfigurationSection C_I1 = paramConfigurationSection.getConfigurationSection((String)I1);
/*     */ 
/*     */               
/*     */               if (C_I1 != null) {
/*     */                 Set<String> KEYS = C_I1.getKeys(false);
/*     */                 
/*     */                 if (!KEYS.isEmpty()) {
/*     */                   Map<String, Object> MAP = new HashMap<>();
/*     */                   
/*     */                   KEYS.forEach(());
/*     */                   
/*     */                   ItemStack ITEM = ItemStack.deserialize(MAP);
/*     */                   
/*     */                   if (ITEM != null) {
/*     */                     this.FILTER_INV.setItem(I1.intValue(), ITEM);
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             });
/*     */       }
/*     */       
/* 167 */       for (int I = 1; I <= this.PAGES; I++) {
/* 168 */         ConfigurationSection CS = yamlConfiguration.getConfigurationSection("INVENTORY." + I + ".ITEMS");
/*     */         
/* 170 */         if (CS != null && !CS.getKeys(false).isEmpty()) {
/* 171 */           Inventory INV = this.IN_L.get(I - 1);
/*     */           
/* 173 */           for (int I1 = 0; I1 < INV.getSize() - 3; I1++) {
/* 174 */             ConfigurationSection C_I = yamlConfiguration.getConfigurationSection("INVENTORY." + I + ".ITEMS." + I1);
/*     */             
/* 176 */             if (C_I != null) {
/* 177 */               Set<String> KEYS = C_I.getKeys(false);
/*     */               
/* 179 */               if (!KEYS.isEmpty()) {
/* 180 */                 Map<String, Object> MAP = new HashMap<>();
/*     */                 
/* 182 */                 KEYS.forEach(K -> paramMap.put(K, paramConfigurationSection.get(K)));
/*     */ 
/*     */                 
/* 185 */                 ItemStack ITEM = ItemStack.deserialize(MAP);
/*     */                 
/* 187 */                 if (ITEM != null) {
/* 188 */                   INV.setItem(I1, ITEM);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 196 */       this.ITEM = StorageCabinet.CABINET_MANAGER.create_cabinet_item_with_cabinet_and_id(this.ID, this);
/*     */       
/* 198 */       StorageCabinet.STORAGE.CABINETS.put(this.ID, this);
/*     */       
/* 200 */       if (this.LOC != null) {
/* 201 */         Block B = this.LOC.getBlock();
/*     */         
/* 203 */         if (CabinetManager.P_T.contains(XMaterial.matchXMaterial(B.getType()))) {
/* 204 */           StorageCabinet.STORAGE.LOCATIONS.put(this.LOC, this);
/* 205 */           B.getState().setMetadata("C_B", (MetadataValue)new FixedMetadataValue(StorageCabinet.PLUGIN, this.ID));
/*     */         } 
/*     */         
/* 208 */         Block BR = this.LOC.getBlock().getRelative(BlockFace.DOWN);
/*     */         
/* 210 */         if (BR != null && XMaterial.matchXMaterial(BR.getType()) == XMaterial.HOPPER) {
/* 211 */           Hopper H = (Hopper)BR.getState();
/*     */           
/* 213 */           if (!StorageCabinet.STORAGE.HOPPERS_DOWN.contains(H)) {
/* 214 */             StorageCabinet.STORAGE.HOPPERS_DOWN.add(H);
/*     */           }
/*     */         } 
/*     */         
/* 218 */         List<Entity> ENL = this.LOC.getWorld().getEntities();
/*     */         
/* 220 */         for (Entity E : ENL) {
/* 221 */           if (E instanceof ArmorStand) {
/* 222 */             ArmorStand S = (ArmorStand)E;
/* 223 */             Location L = S.getLocation().getBlock().getLocation();
/*     */             
/* 225 */             if (L.equals(this.LOC)) {
/* 226 */               this.STAND = S;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 232 */       this.CABINET_FILE.delete();
/* 233 */       return true;
/* 234 */     } catch (Exception EX) {
/* 235 */       StorageCabinet.UTILS.debug("Exception ocurred when trying to load the cabinet with the ID: " + 
/* 236 */           this.CABINET_FILE.getName() + "/Exception:" + EX.getMessage(), false);
/* 237 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean save(boolean BACKUP) {
/* 243 */     if (this.IS_OPEN && !this.isClosing && this.STAND != null) {
/* 244 */       ItemStack H = this.STAND.getEquipment().getHelmet();
/* 245 */       if (H != null && H.hasItemMeta()) {
/* 246 */         ItemMeta M = H.getItemMeta();
/*     */         
/* 248 */         M.setCustomModelData(this.ANIM_IDS.get(0));
/* 249 */         H.setItemMeta(M);
/* 250 */         this.STAND.getEquipment().setHelmet(H);
/*     */       } 
/*     */     } 
/*     */     
/*     */     try {
/* 255 */       File F = null;
/*     */       
/* 257 */       if (BACKUP) {
/* 258 */         DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd/HH_mm_ss");
/* 259 */         LocalDateTime TIME = LocalDateTime.now();
/* 260 */         String TN = DTF.format(TIME);
/*     */         
/* 262 */         File DIR = new File(StorageCabinet.PLUGIN.getDataFolder(), "backup" + File.separator + TN);
/*     */         
/* 264 */         DIR.mkdirs();
/* 265 */         F = new File(StorageCabinet.PLUGIN.getDataFolder(), 
/* 266 */             "backup" + File.separator + TN + File.separator + this.ID + ".dat");
/*     */       } else {
/* 268 */         File DIR = new File(StorageCabinet.PLUGIN.getDataFolder(), "storage");
/*     */         
/* 270 */         DIR.mkdirs();
/* 271 */         F = new File(StorageCabinet.PLUGIN.getDataFolder(), "storage" + File.separator + this.ID + ".dat");
/*     */       } 
/* 273 */       if (!F.exists()) {
/* 274 */         F.createNewFile();
/*     */       }
/*     */       
/* 277 */       YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(F);
/*     */ 
/*     */       
/* 280 */       yamlConfiguration.set("ID", this.ID);
/* 281 */       yamlConfiguration.set("DATA", Integer.valueOf(this.DATA));
/* 282 */       yamlConfiguration.set("TYPE", this.TYPE.name());
/* 283 */       yamlConfiguration.set("LOCATION", save_loc(this.LOC));
/*     */       
/* 285 */       for (int I = 1; I <= this.PAGES; I++) {
/* 286 */         Inventory INV = this.IN_L.get(I - 1);
/*     */         
/* 288 */         yamlConfiguration.set("INVENTORY." + I + ".ITEMS", save_inv(INV));
/*     */       } 
/*     */       
/* 291 */       yamlConfiguration.set("FILTERINV.ITEMS", save_filter_inv());
/*     */       
/* 293 */       yamlConfiguration.save(F);
/* 294 */       return true;
/* 295 */     } catch (Exception EX) {
/* 296 */       StorageCabinet.UTILS.debug("Exception ocurred when trying to save the cabinet with the ID: " + this.ID + 
/* 297 */           "/Exception:" + EX.getMessage(), false);
/* 298 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Map<Integer, Map<String, Object>> save_filter_inv() {
/* 304 */     Inventory I = this.FILTER_INV;
/* 305 */     Map<Integer, Map<String, Object>> MAP = new HashMap<>();
/*     */     
/* 307 */     if (I == null) {
/* 308 */       return MAP;
/*     */     }
/* 310 */     if ((I.getStorageContents()).length <= 0) {
/* 311 */       return MAP;
/*     */     }
/*     */     
/* 314 */     if (!this.FILTER.in_list().isEmpty()) {
/* 315 */       this.FILTER.in_list().forEach((SLOT, IT) -> paramMap.put(SLOT, this.FILTER_INV.getItem(SLOT.intValue()).serialize()));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 320 */     if (!this.FILTER.out_list().isEmpty()) {
/* 321 */       this.FILTER.out_list().forEach((SLOT, IT) -> paramMap.put(SLOT, this.FILTER_INV.getItem(SLOT.intValue()).serialize()));
/*     */     }
/*     */ 
/*     */     
/* 325 */     return MAP;
/*     */   }
/*     */ 
/*     */   
/*     */   private Map<Integer, Map<String, Object>> save_inv(Inventory I) {
/* 330 */     Map<Integer, Map<String, Object>> MAP = new HashMap<>();
/*     */     
/* 332 */     if (I == null) {
/* 333 */       return MAP;
/*     */     }
/* 335 */     if ((I.getStorageContents()).length <= 0) {
/* 336 */       return MAP;
/*     */     }
/* 338 */     int SLOT = 0; byte b; int i;
/*     */     ItemStack[] arrayOfItemStack;
/* 340 */     for (i = (arrayOfItemStack = I.getStorageContents()).length, b = 0; b < i; ) { ItemStack IT = arrayOfItemStack[b];
/* 341 */       if (SLOT >= I.getSize() - 3) {
/*     */         break;
/*     */       }
/* 344 */       if (IT != null) {
/* 345 */         MAP.put(Integer.valueOf(SLOT), IT.serialize());
/*     */       }
/* 347 */       SLOT++; b++; }
/*     */     
/* 349 */     return MAP;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String save_loc(Location LOC) {
/* 354 */     if (LOC == null) {
/* 355 */       return "NONE";
/*     */     }
/* 357 */     return "{W:" + LOC.getWorld().getName() + "/X:" + LOC.getBlockX() + "/Y:" + LOC.getBlockY() + "/Z:" + 
/* 358 */       LOC.getBlockZ() + "}";
/*     */   }
/*     */   
/*     */   public static Location load_loc(String L) {
/*     */     int X, Y, Z;
/* 363 */     if (L == null || L.isEmpty() || L.equalsIgnoreCase("NONE")) {
/* 364 */       return null;
/*     */     }
/* 366 */     L = L.substring(1, L.length() - 1);
/*     */     
/* 368 */     if (!L.contains("/") || !L.contains(":")) {
/* 369 */       return null;
/*     */     }
/*     */     
/* 372 */     String[] L1 = L.split("/");
/* 373 */     String[] w1 = L1[0].split(":");
/* 374 */     String[] x1 = L1[1].split(":");
/* 375 */     String[] y1 = L1[2].split(":");
/* 376 */     String[] z1 = L1[3].split(":");
/*     */     
/* 378 */     if (w1.length != 2) {
/* 379 */       return null;
/*     */     }
/* 381 */     World W = StorageCabinet.SERVER.getWorld(w1[1]);
/* 382 */     if (W == null) {
/* 383 */       return null;
/*     */     }
/*     */     
/*     */     try {
/* 387 */       X = Integer.valueOf(x1[1]).intValue();
/* 388 */       Y = Integer.valueOf(y1[1]).intValue();
/* 389 */       Z = Integer.valueOf(z1[1]).intValue();
/*     */     }
/* 391 */     catch (NumberFormatException EX) {
/* 392 */       return null;
/*     */     } 
/* 394 */     Location LOC = new Location(W, X, Y, Z);
/*     */     
/* 396 */     return LOC;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean has_paths(FileConfiguration C) {
/* 401 */     if (C == null) {
/* 402 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 408 */     String P1 = C.getString("ID");
/* 409 */     String P7 = C.getString("DATA");
/* 410 */     String P3 = C.getString("TYPE");
/* 411 */     String P5 = C.getString("LOCATION");
/* 412 */     ConfigurationSection P6 = C.getConfigurationSection("INVENTORY");
/* 413 */     ConfigurationSection P8 = C.getConfigurationSection("FILTERINV");
/*     */     
/* 415 */     if (P1 == null || P1.isEmpty() || P1.length() != 18 || P3 == null || P3.isEmpty() || P5 == null || P5.isEmpty() || 
/* 416 */       P6 == null || P6.getKeys(false).isEmpty() || P7 == null || P7.isEmpty() || P8 == null || 
/* 417 */       P8.getKeys(false).isEmpty()) {
/* 418 */       return false;
/*     */     }
/* 420 */     return true;
/*     */   }
/*     */   
/*     */   private final void load_types(boolean IN) {
/*     */     int I;
/* 425 */     switch (this.TYPE) {
/*     */       case OAK_CABINET:
/* 427 */         this.PAGES = 1;
/* 428 */         this.CAPACITY = 27;
/* 429 */         this.ANIM_IDS.add(Integer.valueOf(this.TYPE.get_id()));
/* 430 */         for (I = 100; I <= 105; I++) {
/* 431 */           this.ANIM_IDS.add(Integer.valueOf(I));
/*     */         }
/* 433 */         if (IN)
/* 434 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 27, 1)); 
/*     */         break;
/*     */       case null:
/* 437 */         this.PAGES = 1;
/* 438 */         this.CAPACITY = 36;
/* 439 */         this.ANIM_IDS.add(Integer.valueOf(this.TYPE.get_id()));
/* 440 */         for (I = 118; I <= 123; I++) {
/* 441 */           this.ANIM_IDS.add(Integer.valueOf(I));
/*     */         }
/* 443 */         if (IN)
/* 444 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 36, 1)); 
/*     */         break;
/*     */       case BIRCH_CABINET:
/* 447 */         this.PAGES = 1;
/* 448 */         this.CAPACITY = 36;
/* 449 */         this.ANIM_IDS.add(Integer.valueOf(this.TYPE.get_id()));
/* 450 */         for (I = 106; I <= 111; I++) {
/* 451 */           this.ANIM_IDS.add(Integer.valueOf(I));
/*     */         }
/* 453 */         if (IN)
/* 454 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 36, 1)); 
/*     */         break;
/*     */       case DARKOAK_CABINET:
/* 457 */         this.PAGES = 1;
/* 458 */         this.CAPACITY = 36;
/* 459 */         this.ANIM_IDS.add(Integer.valueOf(this.TYPE.get_id()));
/* 460 */         for (I = 124; I <= 129; I++) {
/* 461 */           this.ANIM_IDS.add(Integer.valueOf(I));
/*     */         }
/* 463 */         if (IN)
/* 464 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 36, 1)); 
/*     */         break;
/*     */       case JUNGLE_CABINET:
/* 467 */         this.PAGES = 1;
/* 468 */         this.CAPACITY = 36;
/* 469 */         this.ANIM_IDS.add(Integer.valueOf(this.TYPE.get_id()));
/* 470 */         for (I = 206; I <= 211; I++) {
/* 471 */           this.ANIM_IDS.add(Integer.valueOf(I));
/*     */         }
/* 473 */         if (IN)
/* 474 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 36, 1)); 
/*     */         break;
/*     */       case SPRUCE_CABINET:
/* 477 */         this.PAGES = 1;
/* 478 */         this.CAPACITY = 36;
/* 479 */         this.ANIM_IDS.add(Integer.valueOf(this.TYPE.get_id()));
/* 480 */         for (I = 112; I <= 117; I++) {
/* 481 */           this.ANIM_IDS.add(Integer.valueOf(I));
/*     */         }
/* 483 */         if (IN)
/* 484 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 36, 1)); 
/*     */         break;
/*     */       case CRIMSON_CABINET:
/* 487 */         this.PAGES = 1;
/* 488 */         this.CAPACITY = 45;
/* 489 */         this.ANIM_IDS.add(Integer.valueOf(this.TYPE.get_id()));
/* 490 */         for (I = 136; I <= 141; I++) {
/* 491 */           this.ANIM_IDS.add(Integer.valueOf(I));
/*     */         }
/* 493 */         if (IN)
/* 494 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 45, 1)); 
/*     */         break;
/*     */       case WARPED_CABINET:
/* 497 */         this.PAGES = 1;
/* 498 */         this.CAPACITY = 45;
/* 499 */         this.ANIM_IDS.add(Integer.valueOf(this.TYPE.get_id()));
/* 500 */         for (I = 130; I <= 135; I++) {
/* 501 */           this.ANIM_IDS.add(Integer.valueOf(I));
/*     */         }
/* 503 */         if (IN)
/* 504 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 45, 1)); 
/*     */         break;
/*     */       case COBBLE_CABINET:
/* 507 */         this.PAGES = 1;
/* 508 */         this.CAPACITY = 54;
/* 509 */         this.ANIM_IDS.add(Integer.valueOf(this.TYPE.get_id()));
/* 510 */         for (I = 142; I <= 147; I++) {
/* 511 */           this.ANIM_IDS.add(Integer.valueOf(I));
/*     */         }
/* 513 */         if (IN)
/* 514 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 54, 1)); 
/*     */         break;
/*     */       case COAL_CABINET:
/* 517 */         this.PAGES = 2;
/* 518 */         this.CAPACITY = 81;
/* 519 */         this.ANIM_IDS.add(Integer.valueOf(this.TYPE.get_id()));
/* 520 */         for (I = 148; I <= 153; I++) {
/* 521 */           this.ANIM_IDS.add(Integer.valueOf(I));
/*     */         }
/* 523 */         if (IN) {
/* 524 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 54, 1));
/* 525 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 27, 2));
/*     */         } 
/*     */         break;
/*     */       case IRON_CABINET:
/* 529 */         this.PAGES = 2;
/* 530 */         this.CAPACITY = 99;
/* 531 */         this.ANIM_IDS.add(Integer.valueOf(this.TYPE.get_id()));
/* 532 */         for (I = 154; I <= 159; I++) {
/* 533 */           this.ANIM_IDS.add(Integer.valueOf(I));
/*     */         }
/* 535 */         if (IN) {
/* 536 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 54, 1));
/* 537 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 45, 2));
/*     */         } 
/*     */         break;
/*     */       case GOLD_CABINET:
/* 541 */         this.PAGES = 2;
/* 542 */         this.CAPACITY = 108;
/* 543 */         this.ANIM_IDS.add(Integer.valueOf(this.TYPE.get_id()));
/* 544 */         for (I = 160; I <= 165; I++) {
/* 545 */           this.ANIM_IDS.add(Integer.valueOf(I));
/*     */         }
/* 547 */         if (IN) {
/* 548 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 54, 1));
/* 549 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 54, 2));
/*     */         } 
/*     */         break;
/*     */       case LAPIS_CABINET:
/* 553 */         this.PAGES = 3;
/* 554 */         this.CAPACITY = 135;
/* 555 */         this.ANIM_IDS.add(Integer.valueOf(this.TYPE.get_id()));
/* 556 */         for (I = 166; I <= 181; I++) {
/* 557 */           this.ANIM_IDS.add(Integer.valueOf(I));
/*     */         }
/* 559 */         if (IN) {
/* 560 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 54, 1));
/* 561 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 54, 2));
/* 562 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 27, 3));
/*     */         } 
/*     */         break;
/*     */       case REDSTONE_CABINET:
/* 566 */         this.PAGES = 3;
/* 567 */         this.CAPACITY = 153;
/* 568 */         this.ANIM_IDS.add(Integer.valueOf(this.TYPE.get_id()));
/* 569 */         for (I = 182; I <= 187; I++) {
/* 570 */           this.ANIM_IDS.add(Integer.valueOf(I));
/*     */         }
/* 572 */         if (IN) {
/* 573 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 54, 1));
/* 574 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 54, 2));
/* 575 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 45, 3));
/*     */         } 
/*     */         break;
/*     */       case DIAMOND_CABINET:
/* 579 */         this.PAGES = 3;
/* 580 */         this.CAPACITY = 162;
/* 581 */         this.ANIM_IDS.add(Integer.valueOf(this.TYPE.get_id()));
/* 582 */         for (I = 188; I <= 193; I++) {
/* 583 */           this.ANIM_IDS.add(Integer.valueOf(I));
/*     */         }
/* 585 */         if (IN) {
/* 586 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 54, 1));
/* 587 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 54, 2));
/* 588 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 54, 3));
/*     */         } 
/*     */         break;
/*     */       case EMERALD_CABINET:
/* 592 */         this.PAGES = 4;
/* 593 */         this.CAPACITY = 198;
/* 594 */         this.ANIM_IDS.add(Integer.valueOf(this.TYPE.get_id()));
/* 595 */         for (I = 194; I <= 199; I++) {
/* 596 */           this.ANIM_IDS.add(Integer.valueOf(I));
/*     */         }
/* 598 */         if (IN) {
/* 599 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 54, 1));
/* 600 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 54, 2));
/* 601 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 54, 3));
/*     */           
/* 603 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 36, 4));
/*     */         } 
/*     */         break;
/*     */       case NETHERITE_CABINET:
/* 607 */         this.PAGES = 4;
/* 608 */         this.CAPACITY = 216;
/* 609 */         this.ANIM_IDS.add(Integer.valueOf(this.TYPE.get_id()));
/* 610 */         for (I = 200; I <= 205; I++) {
/* 611 */           this.ANIM_IDS.add(Integer.valueOf(I));
/*     */         }
/* 613 */         if (IN) {
/* 614 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 54, 1));
/* 615 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 54, 2));
/* 616 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 54, 3));
/* 617 */           this.IN_L.add(CabinetGUI.create_cabinet_gui(this, 54, 4));
/*     */         } 
/*     */         break;
/*     */       default:
/* 621 */         this.CAPACITY = 9;
/*     */         break;
/*     */     } 
/*     */     
/* 625 */     this.FILTER = new Filter();
/* 626 */     CabinetGUI.create_cabinet_filter_gui(this, 54);
/* 627 */     StorageCabinet.PLUGIN_MANAGER.registerEvents(this, StorageCabinet.PLUGIN);
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onfilter_inventory_close(InventoryCloseEvent EV) {
/* 633 */     Inventory I = EV.getInventory();
/* 634 */     Player P = (Player)EV.getPlayer();
/*     */     
/* 636 */     if (I.equals(this.FILTER_INV)) {
/* 637 */       StorageCabinet.SCHEDULER.runTaskLater(StorageCabinet.PLUGIN, () -> {
/*     */             InventoryType T = paramPlayer.getOpenInventory().getType();
/*     */             
/*     */             if (T == InventoryType.CRAFTING || T == InventoryType.CREATIVE) {
/*     */               StorageCabinet.STORAGE.PREVIEW.remove(paramPlayer);
/*     */               paramPlayer.playSound(paramPlayer.getLocation(), Sound.BLOCK_CHEST_CLOSE, 1.0F, 0.8F);
/*     */             } 
/* 644 */           }10L);
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void on_drag_filter_inventory(InventoryDragEvent EV) {
/* 652 */     Inventory I = EV.getInventory();
/*     */     
/* 654 */     if (I != null && I.equals(this.FILTER_INV)) {
/* 655 */       EV.setCancelled(true);
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void on_click_filter_inventory(InventoryClickEvent EV) {
/* 663 */     int SLOT = EV.getSlot();
/* 664 */     InventoryAction A = EV.getAction();
/* 665 */     ItemStack IT = EV.getCurrentItem();
/* 666 */     Inventory CI = EV.getClickedInventory();
/* 667 */     Inventory TOP = EV.getView().getTopInventory();
/* 668 */     Inventory BOT = EV.getView().getBottomInventory();
/*     */     
/* 670 */     if (TOP.equals(this.FILTER_INV) && BOT.getType() == InventoryType.PLAYER) {
/* 671 */       Player P = (Player)EV.getWhoClicked();
/*     */       
/* 673 */       if (DL.contains(A)) {
/* 674 */         EV.setCancelled(true);
/*     */         
/*     */         return;
/*     */       } 
/* 678 */       if (StorageCabinet.STORAGE.PREVIEW.contains(P) && 
/* 679 */         !P.hasPermission(StorageCabinet.PERM_MANAGER.CABINET_USE_ADMIN) && 
/* 680 */         SLOT != 0) {
/* 681 */         EV.setCancelled(true);
/*     */         
/*     */         return;
/*     */       } 
/* 685 */       if (CI != null && CI.equals(this.FILTER_INV) && this.FILTER.SLOTS.containsKey(Integer.valueOf(SLOT))) {
/* 686 */         ItemStack CURSOR = EV.getCursor();
/* 687 */         XMaterial xMaterial = XMaterial.matchXMaterial(CURSOR);
/*     */         
/* 689 */         if (IT != null && !XMaterial.isAir(CURSOR)) {
/* 690 */           EV.setCancelled(true);
/* 691 */           if (XMaterial.matchXMaterial(IT) == xMaterial) {
/* 692 */             StorageCabinet.UTILS.send_msg(P, ((String)StorageCabinet.SETTINGS.STRING_MAP
/* 693 */                 .get("messages.cabinetgui_alreadyone")).replace("{ITEM}", xMaterial.name()));
/*     */           }
/*     */           return;
/*     */         } 
/* 697 */         if (IT == null && !XMaterial.isAir(CURSOR)) {
/* 698 */           String T = this.FILTER.SLOTS.get(Integer.valueOf(SLOT));
/* 699 */           List<XMaterial> LIST = new ArrayList<>();
/*     */ 
/*     */           
/* 702 */           if (T.equals("I")) {
/* 703 */             for (int I = 9; I < 27; I++) {
/* 704 */               ItemStack ITEM = this.FILTER_INV.getItem(I);
/*     */               
/* 706 */               if (ITEM != null && !XMaterial.isAir(ITEM)) {
/* 707 */                 LIST.add(XMaterial.matchXMaterial(ITEM));
/*     */               }
/*     */             } 
/*     */           } else {
/* 711 */             for (int I = 36; I < 54; I++) {
/* 712 */               ItemStack ITEM = this.FILTER_INV.getItem(I);
/*     */               
/* 714 */               if (ITEM != null && !XMaterial.isAir(ITEM)) {
/* 715 */                 LIST.add(XMaterial.matchXMaterial(ITEM));
/*     */               }
/*     */             } 
/*     */           } 
/*     */           
/* 720 */           if (LIST.contains(xMaterial)) {
/* 721 */             EV.setCancelled(true);
/* 722 */             StorageCabinet.UTILS.send_msg(P, ((String)StorageCabinet.SETTINGS.STRING_MAP
/* 723 */                 .get("messages.cabinetgui_alreadythere")).replace("{ITEM}", xMaterial.name()));
/*     */             return;
/*     */           } 
/* 726 */           int AM = CURSOR.getAmount();
/* 727 */           ItemStack CC = CURSOR.clone();
/*     */           
/* 729 */           CC.setAmount(1);
/* 730 */           EV.setCancelled(true);
/* 731 */           CURSOR.setAmount(AM - 1);
/* 732 */           this.FILTER_INV.setItem(SLOT, CC);
/*     */           return;
/*     */         } 
/*     */         return;
/*     */       } 
/* 737 */       if (IT == null) {
/*     */         return;
/*     */       }
/* 740 */       if (!IT.hasItemMeta()) {
/*     */         return;
/*     */       }
/* 743 */       ItemMeta META = IT.getItemMeta();
/*     */       
/* 745 */       if (!META.hasLocalizedName()) {
/*     */         return;
/*     */       }
/* 748 */       XMaterial MAT = XMaterial.matchXMaterial(IT);
/*     */ 
/*     */       
/* 751 */       if (MAT == XMaterial.IRON_BARS || MAT == XMaterial.GREEN_STAINED_GLASS_PANE || 
/* 752 */         MAT == XMaterial.RED_STAINED_GLASS_PANE) {
/* 753 */         EV.setCancelled(true);
/*     */         
/*     */         return;
/*     */       } 
/* 757 */       if (StorageCabinet.CABINET_MANAGER.is_cabinet_item(IT)) {
/* 758 */         EV.setCancelled(true);
/*     */         
/*     */         return;
/*     */       } 
/* 762 */       if (SLOT == 0) {
/* 763 */         EV.setCancelled(true);
/* 764 */         P.openInventory(this.IN_L.get(0));
/* 765 */         P.playSound(P.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 0.8F);
/*     */         return;
/*     */       } 
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public class Filter
/*     */   {
/* 774 */     public Map<Integer, String> SLOTS = new HashMap<>();
/*     */     public Filter() {
/*     */       int I;
/* 777 */       for (I = 9; I < 27; I++) {
/* 778 */         this.SLOTS.put(Integer.valueOf(I), "I");
/*     */       }
/* 780 */       for (I = 36; I < 54; I++) {
/* 781 */         this.SLOTS.put(Integer.valueOf(I), "O");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private Map<Integer, XMaterial> in_list() {
/* 788 */       Map<Integer, XMaterial> ITL = new HashMap<>();
/*     */       
/* 790 */       if (Cabinet.this.FILTER_INV == null) {
/* 791 */         return ITL;
/*     */       }
/* 793 */       for (int I = 9; I < 27; I++) {
/* 794 */         ItemStack ITEM = Cabinet.this.FILTER_INV.getItem(I);
/*     */         
/* 796 */         if (ITEM != null && !XMaterial.isAir(ITEM)) {
/* 797 */           ITL.put(Integer.valueOf(I), XMaterial.matchXMaterial(ITEM));
/*     */         }
/*     */       } 
/* 800 */       return ITL;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean in_similar(ItemStack IT) {
/* 805 */       if (XMaterial.isAir(IT)) {
/* 806 */         return false;
/*     */       }
/* 808 */       if (in_list().containsValue(XMaterial.matchXMaterial(IT))) {
/* 809 */         return true;
/*     */       }
/* 811 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean in_enabled() {
/* 816 */       if (Cabinet.this.FILTER_INV == null) {
/* 817 */         return false;
/*     */       }
/* 819 */       Map<Integer, XMaterial> IT = in_list();
/*     */       
/* 821 */       if (IT.isEmpty()) {
/* 822 */         return false;
/*     */       }
/* 824 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     private Map<Integer, XMaterial> out_list() {
/* 829 */       Map<Integer, XMaterial> ITL = new HashMap<>();
/*     */       
/* 831 */       for (int I = 36; I < 54; I++) {
/* 832 */         ItemStack ITEM = Cabinet.this.FILTER_INV.getItem(I);
/*     */         
/* 834 */         if (ITEM != null && !XMaterial.isAir(ITEM)) {
/* 835 */           ITL.put(Integer.valueOf(I), XMaterial.matchXMaterial(ITEM));
/*     */         }
/*     */       } 
/* 838 */       return ITL;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean out_similar(ItemStack IT) {
/* 843 */       if (XMaterial.isAir(IT)) {
/* 844 */         return false;
/*     */       }
/* 846 */       if (out_list().containsValue(XMaterial.matchXMaterial(IT))) {
/* 847 */         return true;
/*     */       }
/* 849 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean out_enabled() {
/* 854 */       if (Cabinet.this.FILTER_INV == null) {
/* 855 */         return false;
/*     */       }
/* 857 */       Map<Integer, XMaterial> ITL = out_list();
/*     */       
/* 859 */       if (ITL.isEmpty()) {
/* 860 */         return false;
/*     */       }
/* 862 */       return true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 868 */     return "CABINET{ID=" + this.ID + "/LOC=" + this.LOC + "/TYPE=" + this.TYPE + "/CAPACITY=" + this.CAPACITY + "}";
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\cabinet\Cabinet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */