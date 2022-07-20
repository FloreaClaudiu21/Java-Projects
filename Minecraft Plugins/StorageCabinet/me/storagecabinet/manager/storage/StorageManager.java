/*     */ package me.storagecabinet.manager.storage;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import me.storagecabinet.StorageCabinet;
/*     */ import me.storagecabinet.manager.ManagerAPI;
/*     */ import me.storagecabinet.manager.cabinet.Cabinet;
/*     */ import me.storagecabinet.manager.cabinet.CabinetIDS;
/*     */ import me.storagecabinet.utils.XMaterial;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.block.Hopper;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class StorageManager
/*     */   implements ManagerAPI
/*     */ {
/*  30 */   private int I = 0;
/*  31 */   public final List<Player> PACK = new ArrayList<>();
/*  32 */   public final List<Player> PREVIEW = new ArrayList<>();
/*  33 */   public final List<Hopper> HOPPERS_DOWN = new ArrayList<>();
/*  34 */   public final Map<Item, String> DROPPED = new HashMap<>();
/*  35 */   public final Map<String, Cabinet> CABINETS = new HashMap<>();
/*  36 */   public final Map<Hopper, BlockFace> HOPPERS = new HashMap<>();
/*  37 */   public final Map<Integer, CabinetIDS> MAP = new HashMap<>();
/*  38 */   public final Map<Location, Cabinet> LOCATIONS = new HashMap<>();
/*     */   
/*     */   public StorageManager() {
/*  41 */     setup_map();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean register() {
/*  48 */     File[] LF = null;
/*  49 */     File F = new File(StorageCabinet.PLUGIN.getDataFolder(), "storage");
/*     */     
/*  51 */     if (F != null) {
/*  52 */       LF = F.listFiles();
/*     */     }
/*     */ 
/*     */     
/*  56 */     String T = " cabinets ";
/*  57 */     int I = load_cabinets(LF);
/*  58 */     int L = I - LF.length;
/*     */     
/*  60 */     if (I < 2) {
/*  61 */       T = " cabinet ";
/*     */     }
/*  63 */     StorageCabinet.UTILS.debug(String.valueOf(I) + T + "have been loaded from the storage folder", false);
/*  64 */     if (L > 0) {
/*  65 */       if (L > 1) {
/*  66 */         T = " cabinets ";
/*     */       } else {
/*  68 */         T = " cabinet ";
/*     */       } 
/*  70 */       StorageCabinet.UTILS
/*  71 */         .debug(String.valueOf(L) + T + "could not be loaded cause of some errors that occured during the load...", false);
/*     */     } 
/*     */     
/*  74 */     load_dropped();
/*     */     
/*  76 */     load_hoppers();
/*  77 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean unregister() {
/*  83 */     for (Player P : StorageCabinet.SERVER.getOnlinePlayers()) {
/*  84 */       P.closeInventory();
/*     */     }
/*  86 */     for (Player P : this.PACK) {
/*  87 */       if (P.isInvulnerable()) {
/*  88 */         P.setInvulnerable(false);
/*     */       }
/*     */     } 
/*     */     
/*  92 */     String T = " cabinets ";
/*  93 */     int I = save_cabinets(false);
/*  94 */     int L = I - this.CABINETS.size();
/*     */     
/*  96 */     if (I < 2) {
/*  97 */       T = " cabinet ";
/*     */     }
/*  99 */     StorageCabinet.UTILS.debug(String.valueOf(I) + T + "have been saved in the storage folder", false);
/* 100 */     if (L > 0) {
/* 101 */       if (L > 1) {
/* 102 */         T = " cabinets ";
/*     */       } else {
/* 104 */         T = " cabinet ";
/*     */       } 
/* 106 */       StorageCabinet.UTILS
/* 107 */         .debug(String.valueOf(L) + T + "could not be saved cause of some errors that occured during the save...", false);
/*     */     } 
/*     */     
/* 110 */     save_dropped();
/*     */     
/* 112 */     save_hoppers();
/*     */     
/* 114 */     this.MAP.clear();
/* 115 */     this.PACK.clear();
/* 116 */     this.HOPPERS.clear();
/* 117 */     this.DROPPED.clear();
/* 118 */     this.CABINETS.clear();
/* 119 */     this.LOCATIONS.clear();
/* 120 */     this.HOPPERS_DOWN.clear();
/* 121 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int backup_cabinets() {
/* 126 */     return save_cabinets(true);
/*     */   }
/*     */ 
/*     */   
/*     */   private final int save_cabinets(boolean BC) {
/* 131 */     this.I = 0;
/*     */     
/* 133 */     if (this.CABINETS.isEmpty()) {
/* 134 */       return 0;
/*     */     }
/* 136 */     this.CABINETS.forEach((ID, C) -> {
/*     */           if (C.save(paramBoolean)) {
/*     */             this.I++;
/*     */           }
/*     */         });
/* 141 */     return this.I;
/*     */   }
/*     */ 
/*     */   
/*     */   private final int load_cabinets(File[] LF) {
/* 146 */     this.I = 0;
/*     */     
/* 148 */     if (LF == null || LF.length <= 0)
/* 149 */       return this.I;  byte b;
/*     */     int i;
/*     */     File[] arrayOfFile;
/* 152 */     for (i = (arrayOfFile = LF).length, b = 0; b < i; ) { File CF = arrayOfFile[b];
/* 153 */       Cabinet C = new Cabinet(CF);
/*     */       
/* 155 */       if (C.load())
/* 156 */         this.I++; 
/*     */       b++; }
/*     */     
/* 159 */     return this.I;
/*     */   }
/*     */ 
/*     */   
/*     */   public void save_hoppers() {
/* 164 */     if (this.HOPPERS.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     try {
/* 168 */       File F = new File(StorageCabinet.PLUGIN.getDataFolder(), "storage" + File.separator + "general.dat");
/* 169 */       if (!F.exists()) {
/* 170 */         F.createNewFile();
/*     */       }
/* 172 */       YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(F);
/*     */       
/* 174 */       StorageCabinet.STORAGE.HOPPERS.forEach((H, FACE) -> paramFileConfiguration.set("HOPPERS." + Cabinet.save_loc(H.getLocation()), FACE.toString()));
/*     */ 
/*     */ 
/*     */       
/* 178 */       yamlConfiguration.save(F);
/*     */       return;
/* 180 */     } catch (Exception EX) {
/* 181 */       StorageCabinet.UTILS
/* 182 */         .debug("Exception ocurred when trying to save the hoppers/Exception:" + EX.getMessage(), false);
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void load_hoppers() {
/*     */     try {
/* 190 */       File F = new File(StorageCabinet.PLUGIN.getDataFolder(), "storage" + File.separator + "general.dat");
/* 191 */       if (!F.exists()) {
/* 192 */         F.createNewFile();
/*     */       }
/* 194 */       YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(F);
/* 195 */       ConfigurationSection S = yamlConfiguration.getConfigurationSection("HOPPERS");
/*     */       
/* 197 */       if (S == null) {
/*     */         return;
/*     */       }
/* 200 */       Set<String> KEYS = S.getKeys(false);
/*     */       
/* 202 */       if (KEYS == null || KEYS.isEmpty()) {
/*     */         return;
/*     */       }
/* 205 */       KEYS.forEach(K -> {
/*     */             String FACE = paramConfigurationSection.getString(K);
/*     */             
/*     */             if (FACE == null || FACE.isEmpty()) {
/*     */               return;
/*     */             }
/*     */             
/*     */             Location LOC = Cabinet.load_loc(K);
/*     */             
/*     */             BlockFace BF = BlockFace.valueOf(FACE);
/*     */             
/*     */             if (BF == null || LOC == null || LOC.getBlock() == null) {
/*     */               return;
/*     */             }
/*     */             
/*     */             Block B = LOC.getBlock();
/*     */             XMaterial M = XMaterial.matchXMaterial(B.getType());
/*     */             if (M == XMaterial.HOPPER) {
/*     */               Hopper H = (Hopper)B.getState();
/*     */               this.HOPPERS.put(H, BF);
/*     */             } 
/*     */           });
/* 227 */       yamlConfiguration.set("HOPPERS", null);
/*     */       
/* 229 */       yamlConfiguration.save(F);
/*     */       return;
/* 231 */     } catch (Exception EX) {
/* 232 */       StorageCabinet.UTILS
/* 233 */         .debug("Exception ocurred when trying to load the hoppers/Exception:" + EX.getMessage(), false);
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void save_dropped() {
/* 240 */     if (this.DROPPED.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     try {
/* 244 */       File F = new File(StorageCabinet.PLUGIN.getDataFolder(), "storage" + File.separator + "general.dat");
/* 245 */       if (!F.exists()) {
/* 246 */         F.createNewFile();
/*     */       }
/* 248 */       YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(F);
/*     */       
/* 250 */       this.DROPPED.forEach((E, ID) -> {
/*     */             String U = E.getUniqueId().toString();
/*     */             
/*     */             paramFileConfiguration.set("DROPPED_CABINETS." + U + ".W", E.getWorld().getName());
/*     */             
/*     */             paramFileConfiguration.set("DROPPED_CABINETS." + U + ".ID", ID);
/*     */           });
/* 257 */       yamlConfiguration.save(F);
/*     */       return;
/* 259 */     } catch (Exception EX) {
/* 260 */       StorageCabinet.UTILS.debug(
/* 261 */           "Exception ocurred when trying to save the dropped cabinets/Exception:" + EX.getMessage(), false);
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void load_dropped() {
/*     */     try {
/* 269 */       File F = new File(StorageCabinet.PLUGIN.getDataFolder(), "storage" + File.separator + "general.dat");
/* 270 */       if (!F.exists()) {
/* 271 */         F.createNewFile();
/*     */       }
/* 273 */       YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(F);
/* 274 */       ConfigurationSection S = yamlConfiguration.getConfigurationSection("DROPPED_CABINETS");
/*     */       
/* 276 */       if (S == null) {
/*     */         return;
/*     */       }
/* 279 */       Set<String> KEYS = S.getKeys(false);
/*     */       
/* 281 */       if (KEYS == null || KEYS.isEmpty()) {
/*     */         return;
/*     */       }
/* 284 */       KEYS.forEach(K -> {
/*     */             String W_V = paramConfigurationSection.getString(String.valueOf(K) + ".W");
/*     */             
/*     */             String ID_V = paramConfigurationSection.getString(String.valueOf(K) + ".ID");
/*     */             
/*     */             if (W_V != null && !W_V.isEmpty() && ID_V != null && !ID_V.isEmpty()) {
/*     */               World W = StorageCabinet.SERVER.getWorld(W_V);
/*     */               
/*     */               if (W != null) {
/*     */                 boolean FOUND = false;
/*     */                 
/*     */                 Collection<Item> ITC = W.getEntitiesByClass(Item.class);
/*     */                 for (Item I : ITC) {
/*     */                   if (I.getUniqueId().equals(UUID.fromString(K))) {
/*     */                     FOUND = true;
/*     */                     this.DROPPED.put(I, ID_V);
/*     */                     break;
/*     */                   } 
/*     */                 } 
/*     */                 if (!FOUND) {
/*     */                   this.CABINETS.remove(ID_V);
/*     */                 }
/*     */                 return;
/*     */               } 
/*     */               this.CABINETS.remove(ID_V);
/*     */               return;
/*     */             } 
/*     */           });
/* 312 */       yamlConfiguration.set("DROPPED_CABINETS", null);
/*     */       
/* 314 */       yamlConfiguration.save(F);
/*     */       return;
/* 316 */     } catch (Exception EX) {
/* 317 */       StorageCabinet.UTILS.debug(
/* 318 */           "Exception ocurred when trying to load the dropped cabinets/Exception:" + EX.getMessage(), false);
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setup_map() {
/* 325 */     this.MAP.put(Integer.valueOf(65), CabinetIDS.OAK_CABINET);
/* 326 */     this.MAP.put(Integer.valueOf(66), CabinetIDS.BIRCH_CABINET);
/* 327 */     this.MAP.put(Integer.valueOf(67), CabinetIDS.JUNGLE_CABINET);
/* 328 */     this.MAP.put(Integer.valueOf(68), CabinetIDS.SPRUCE_CABINET);
/* 329 */     this.MAP.put(Integer.valueOf(69), CabinetIDS.ACACIA_CABINET);
/* 330 */     this.MAP.put(Integer.valueOf(70), CabinetIDS.DARKOAK_CABINET);
/* 331 */     this.MAP.put(Integer.valueOf(71), CabinetIDS.WARPED_CABINET);
/* 332 */     this.MAP.put(Integer.valueOf(72), CabinetIDS.CRIMSON_CABINET);
/* 333 */     this.MAP.put(Integer.valueOf(73), CabinetIDS.COBBLE_CABINET);
/* 334 */     this.MAP.put(Integer.valueOf(74), CabinetIDS.COAL_CABINET);
/* 335 */     this.MAP.put(Integer.valueOf(75), CabinetIDS.IRON_CABINET);
/* 336 */     this.MAP.put(Integer.valueOf(76), CabinetIDS.GOLD_CABINET);
/* 337 */     this.MAP.put(Integer.valueOf(77), CabinetIDS.LAPIS_CABINET);
/* 338 */     this.MAP.put(Integer.valueOf(78), CabinetIDS.REDSTONE_CABINET);
/* 339 */     this.MAP.put(Integer.valueOf(79), CabinetIDS.DIAMOND_CABINET);
/* 340 */     this.MAP.put(Integer.valueOf(80), CabinetIDS.EMERALD_CABINET);
/* 341 */     this.MAP.put(Integer.valueOf(81), CabinetIDS.NETHERITE_CABINET);
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\storage\StorageManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */