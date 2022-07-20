/*     */ package me.furnace.manager.menu.menu;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import me.furnace.IMain;
/*     */ import me.furnace.XMaterial;
/*     */ import me.furnace.manager.furnace.IFurnace;
/*     */ import me.furnace.manager.furnace.IFurnaceU;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.SkullMeta;
/*     */ 
/*     */ public class IMenu {
/*     */   private String MENU;
/*     */   public int MENU_SIZE;
/*  23 */   public int UPDATE_INTERVAL = 1;
/*  24 */   public String MENU_NAME = null;
/*     */   
/*     */   public int MENU_S_SLOT;
/*     */   public int MENU_R_SLOT;
/*     */   private FileConfiguration MENU_CONFIG;
/*  29 */   public List<Integer> EMPTY_SLOTS = new ArrayList<>(); private List<String> MENU_F_NAME; private List<String> MENU_F_KEYS; private Map<String, List<String>> MENU_F_LORES;
/*  30 */   public List<IContent> MENU_CONTENT = new ArrayList<>();
/*  31 */   public Map<Integer, IContent> CONTENT_BY_SLOT = new HashMap<>();
/*     */   
/*     */   public IMenu(String NAME) {
/*  34 */     this.MENU = NAME;
/*  35 */     this.MENU_F_KEYS = new ArrayList<>();
/*  36 */     this.MENU_F_LORES = new HashMap<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update_furnace(IFurnace F, ItemStack IT) {
/*  47 */     if (F == null || IT == null) {
/*     */       return;
/*     */     }
/*  50 */     List<String> LORE = null;
/*  51 */     String NAME1 = F.NAMES.get(1);
/*  52 */     String NAME2 = F.NAMES.get(0);
/*  53 */     String NAME = "&cName not found";
/*  54 */     String OWNER = F.HEAD_META.getOwner();
/*  55 */     SkullMeta META = (SkullMeta)IT.getItemMeta();
/*     */     
/*  57 */     if (F.BURNTIME > 0) {
/*  58 */       if (!OWNER.equals(NAME1))
/*  59 */         META.setOwner(NAME1); 
/*  60 */       F.HEAD_META.setOwner(NAME1);
/*     */     } else {
/*  62 */       if (!OWNER.equals(NAME2))
/*  63 */         META.setOwner(NAME2); 
/*  64 */       F.HEAD_META.setOwner(NAME2);
/*     */     } 
/*  66 */     if (!this.MENU_F_KEYS.isEmpty()) {
/*  67 */       if (F.I1 >= this.MENU_F_KEYS.size()) {
/*  68 */         F.I1 = 0;
/*     */       }
/*  70 */       String K = this.MENU_F_KEYS.get(F.I1);
/*     */       
/*  72 */       LORE = this.MENU_F_LORES.get(K);
/*  73 */       F.I1++;
/*     */     } 
/*  75 */     if (!this.MENU_F_NAME.isEmpty()) {
/*  76 */       if (F.I >= this.MENU_F_NAME.size()) {
/*  77 */         F.I = 0;
/*     */       }
/*  79 */       NAME = IMain.VARS.M1(null, this.MENU_F_NAME.get(F.I), F, null, null);
/*  80 */       F.I++;
/*     */     } 
/*  82 */     List<String> LORES = IMain.VARS.L(null, LORE, F);
/*     */     
/*  84 */     META.setLore(LORES);
/*  85 */     F.HEAD_META.setLore(LORES);
/*  86 */     META.setDisplayName(IMain.UTILS.center(NAME));
/*  87 */     F.HEAD_META.setDisplayName(IMain.UTILS.center(NAME));
/*  88 */     F.HEAD.setItemMeta((ItemMeta)F.HEAD_META);
/*  89 */     IT.setItemMeta((ItemMeta)META);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int load() {
/*  95 */     int COUNT = 0;
/*  96 */     File M = new File(IMain.PL.getDataFolder() + File.separator + "Menus");
/*  97 */     if (!M.exists()) {
/*  98 */       M.mkdirs();
/*     */     }
/* 100 */     File F = new File(M, String.valueOf(this.MENU) + ".yml");
/*     */     
/* 102 */     if (!IMain.UTILS.file_exists(F, new String[] { "menu_name", "menu_contents" })) {
/* 103 */       IMain.UTILS.save_resource(M, String.valueOf(this.MENU) + ".yml");
/*     */     }
/* 105 */     this.MENU_CONFIG = (FileConfiguration)YamlConfiguration.loadConfiguration(F);
/*     */     
/* 107 */     this.MENU_SIZE = this.MENU_CONFIG.getInt("menu_size");
/* 108 */     if (this.MENU_SIZE <= 0 || this.MENU_SIZE > 54) {
/* 109 */       this.MENU_SIZE = 54;
/*     */     }
/* 111 */     this.MENU_NAME = this.MENU_CONFIG.getString("menu_name");
/* 112 */     if (this.MENU_NAME == null || this.MENU_NAME.isEmpty()) {
/* 113 */       this.MENU_NAME = "&0&lName not found";
/*     */     }
/* 115 */     this.UPDATE_INTERVAL = this.MENU_CONFIG.getInt("menu_interval");
/* 116 */     if (this.UPDATE_INTERVAL <= 0) {
/* 117 */       this.UPDATE_INTERVAL = 20;
/*     */     }
/* 119 */     if (this.MENU_NAME.length() > 32) {
/* 120 */       this.MENU_NAME = this.MENU_NAME.substring(0, 32);
/*     */     }
/* 122 */     this.MENU_NAME = IMain.UTILS.color(this.MENU_NAME);
/* 123 */     this.MENU_R_SLOT = this.MENU_CONFIG.getInt("menu_result_slot");
/* 124 */     this.MENU_S_SLOT = this.MENU_CONFIG.getInt("menu_smelting_slot");
/* 125 */     this.MENU_F_NAME = IMain.UTILS.colorL(this.MENU_CONFIG.getStringList("menu_furnace_names"));
/* 126 */     ConfigurationSection SE1 = this.MENU_CONFIG.getConfigurationSection("menu_furnace_lores");
/* 127 */     if (SE1 != null) {
/* 128 */       for (String KEY : SE1.getKeys(false)) {
/* 129 */         if (KEY != null && !KEY.isEmpty()) {
/* 130 */           List<String> L = SE1.getStringList(KEY);
/*     */           
/* 132 */           if (L != null && !L.isEmpty()) {
/* 133 */             if (!this.MENU_F_KEYS.contains(KEY)) {
/* 134 */               this.MENU_F_KEYS.add(KEY);
/*     */             }
/* 136 */             this.MENU_F_LORES.put(KEY, IMain.UTILS.colorL(L));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/* 141 */     ConfigurationSection SE = this.MENU_CONFIG.getConfigurationSection("menu_contents");
/*     */     
/* 143 */     if (SE != null) {
/* 144 */       for (String KEY : SE.getKeys(false)) {
/* 145 */         if (KEY != null && !KEY.isEmpty()) {
/* 146 */           ConfigurationSection CONTENT_S = SE.getConfigurationSection(KEY);
/* 147 */           IContent CONTENT = new IContent(CONTENT_S);
/*     */           
/* 149 */           if (CONTENT.load()) {
/* 150 */             COUNT++;
/* 151 */             this.MENU_CONTENT.add(CONTENT);
/* 152 */             this.CONTENT_BY_SLOT.put(Integer.valueOf(CONTENT.ITEM_SLOT), CONTENT);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/* 157 */     for (int I = 0; I < this.MENU_SIZE; I++) {
/* 158 */       if (!this.CONTENT_BY_SLOT.containsKey(Integer.valueOf(I))) {
/* 159 */         this.EMPTY_SLOTS.add(Integer.valueOf(I));
/*     */       }
/*     */     } 
/* 162 */     return COUNT;
/*     */   }
/*     */   
/*     */   public class IContent {
/*     */     public int ITEM_SLOT;
/*     */     public String ITEM_CLICK;
/*     */     public ItemStack ITEM;
/*     */     public ItemStack DEFAULT;
/*     */     private ConfigurationSection SE;
/*     */     private List<Integer> AMOUNT;
/*     */     private List<Integer> D_AMOUNT;
/*     */     private List<XMaterial> TYPES;
/*     */     private List<XMaterial> D_TYPES;
/*     */     private List<String> NAMES;
/*     */     private List<String> OWNERS;
/*     */     private List<String> D_NAMES;
/* 178 */     private int N1 = 0, L1 = 0, T1 = 0, A1 = 0;
/* 179 */     private int N = 0; private int L = 0; private int O = 0; private int T = 0; private int A = 0;
/* 180 */     private List<String> K_L = new ArrayList<>();
/* 181 */     private List<String> K_L1 = new ArrayList<>();
/* 182 */     private Map<String, List<String>> LORES_MAP = new HashMap<>();
/* 183 */     private Map<String, List<String>> DLORES_MAP = new HashMap<>();
/*     */     
/*     */     public IContent(ConfigurationSection SE) {
/* 186 */       this.SE = SE;
/* 187 */       this.TYPES = new ArrayList<>();
/* 188 */       this.D_TYPES = new ArrayList<>();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private ItemStack item_bysection(ConfigurationSection SE) {
/* 194 */       if (SE == null) {
/* 195 */         return IMain.EMPTY_ITEM;
/*     */       }
/* 197 */       return IFurnaceU.deserialize(SE);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private void set_meta(ItemStack IT, ItemMeta M, SkullMeta M1, String D, List<String> L, XMaterial TYPE, int A) {
/* 203 */       if (M1 != null) {
/* 204 */         M1.setLore(L);
/* 205 */         M1.setDisplayName(D);
/* 206 */         IT.setItemMeta((ItemMeta)M1);
/*     */       } else {
/* 208 */         M.setLore(L);
/* 209 */         M.setDisplayName(D);
/* 210 */         IT.setItemMeta(M);
/*     */       } 
/* 212 */       IT.setAmount(A);
/* 213 */       IT = TYPE.setType(IT);
/* 214 */       IT.setDurability((short)TYPE.getData());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean update(IMenuVars V) {
/* 221 */       int AM = 1, AM1 = 1;
/* 222 */       SkullMeta META1 = null;
/* 223 */       ItemMeta META2 = null;
/* 224 */       XMaterial TYPE = null, TYPE1 = null;
/* 225 */       List<String> LORES = null, LORES1 = null;
/* 226 */       String NAME = null, OWNER = null, NAME1 = null;
/*     */       
/* 228 */       ItemMeta META = this.ITEM.getItemMeta();
/* 229 */       if (this.TYPES != null && !this.TYPES.isEmpty()) {
/* 230 */         if (this.T >= this.TYPES.size()) {
/* 231 */           this.T = 0;
/*     */         }
/* 233 */         TYPE = this.TYPES.get(this.T);
/* 234 */         this.T++;
/*     */       } 
/* 236 */       if (META instanceof SkullMeta) {
/* 237 */         META1 = (SkullMeta)META;
/*     */         
/* 239 */         if (this.OWNERS != null && !this.OWNERS.isEmpty()) {
/* 240 */           if (this.O >= this.OWNERS.size()) {
/* 241 */             this.O = 0;
/*     */           }
/* 243 */           OWNER = this.OWNERS.get(this.O);
/* 244 */           OWNER = V.M(OWNER);
/* 245 */           if (!OWNER.isEmpty()) {
/* 246 */             META1.setOwner(OWNER);
/*     */           }
/* 248 */           this.O++;
/*     */         } 
/*     */       } 
/* 251 */       if (this.NAMES != null && !this.NAMES.isEmpty()) {
/* 252 */         if (this.N >= this.NAMES.size()) {
/* 253 */           this.N = 0;
/*     */         }
/* 255 */         NAME = this.NAMES.get(this.N);
/* 256 */         NAME = V.M(NAME);
/* 257 */         this.N++;
/*     */       } 
/* 259 */       if (this.AMOUNT != null && !this.AMOUNT.isEmpty()) {
/* 260 */         if (this.A >= this.AMOUNT.size()) {
/* 261 */           this.A = 0;
/*     */         }
/* 263 */         AM = ((Integer)this.AMOUNT.get(this.A)).intValue();
/* 264 */         this.A++;
/*     */       } 
/* 266 */       if (this.LORES_MAP != null && !this.LORES_MAP.isEmpty() && !this.K_L.isEmpty()) {
/* 267 */         if (this.L >= this.K_L.size()) {
/* 268 */           this.L = 0;
/*     */         }
/* 270 */         String K = this.K_L.get(this.L);
/*     */         
/* 272 */         LORES = this.LORES_MAP.get(K);
/* 273 */         LORES = V.L(LORES);
/* 274 */         this.L++;
/*     */       } 
/*     */       
/* 277 */       if (this.DEFAULT != null) {
/* 278 */         META2 = this.DEFAULT.getItemMeta();
/* 279 */         if (this.D_TYPES != null && !this.D_TYPES.isEmpty()) {
/* 280 */           if (this.T1 >= this.D_TYPES.size()) {
/* 281 */             this.T1 = 0;
/*     */           }
/* 283 */           TYPE1 = this.D_TYPES.get(this.T1);
/* 284 */           this.T1++;
/*     */         } 
/* 286 */         if (this.D_NAMES != null && !this.D_NAMES.isEmpty()) {
/* 287 */           if (this.N1 >= this.D_NAMES.size()) {
/* 288 */             this.N1 = 0;
/*     */           }
/* 290 */           NAME1 = this.D_NAMES.get(this.N1);
/* 291 */           NAME1 = V.M(NAME1);
/* 292 */           this.N1++;
/*     */         } 
/* 294 */         if (this.D_AMOUNT != null && !this.D_AMOUNT.isEmpty()) {
/* 295 */           if (this.A1 >= this.D_AMOUNT.size()) {
/* 296 */             this.A1 = 0;
/*     */           }
/* 298 */           AM1 = ((Integer)this.D_AMOUNT.get(this.A1)).intValue();
/* 299 */           this.A1++;
/*     */         } 
/* 301 */         if (this.DLORES_MAP != null && !this.DLORES_MAP.isEmpty() && !this.K_L1.isEmpty()) {
/* 302 */           if (this.L1 >= this.K_L1.size()) {
/* 303 */             this.L1 = 0;
/*     */           }
/* 305 */           String K = this.K_L1.get(this.L1);
/*     */           
/* 307 */           LORES1 = this.DLORES_MAP.get(K);
/* 308 */           LORES1 = V.L(LORES1);
/* 309 */           this.L1++;
/*     */         } 
/* 311 */         set_meta(this.DEFAULT, META2, null, NAME1, LORES1, TYPE1, AM1);
/*     */       } 
/* 313 */       set_meta(this.ITEM, META, META1, NAME, LORES, TYPE, AM);
/* 314 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean load() {
/* 319 */       if (this.SE == null) {
/* 320 */         return false;
/*     */       }
/* 322 */       Set<String> KEYS = this.SE.getKeys(false);
/*     */       
/* 324 */       if (KEYS.isEmpty()) {
/* 325 */         return false;
/*     */       }
/* 327 */       List<String> TY = this.SE.getStringList("types");
/* 328 */       List<String> TY1 = this.SE.getStringList("d_types");
/*     */       
/* 330 */       if (TY == null || TY.isEmpty()) {
/* 331 */         return false;
/*     */       }
/* 333 */       for (String TYPE : TY) {
/*     */         try {
/* 335 */           this.TYPES.add(XMaterial.valueOf(TYPE.toUpperCase()));
/* 336 */         } catch (Exception exception) {}
/*     */       } 
/*     */       
/* 339 */       if (this.TYPES.isEmpty()) {
/* 340 */         return false;
/*     */       }
/* 342 */       XMaterial MAT1 = null;
/* 343 */       XMaterial MAT = this.TYPES.get(0);
/*     */       
/* 345 */       if (IFurnaceU.E(MAT)) {
/* 346 */         return false;
/*     */       }
/* 348 */       if (TY1 != null && !TY1.isEmpty()) {
/* 349 */         for (String TYPE : TY1) {
/*     */           try {
/* 351 */             this.D_TYPES.add(XMaterial.valueOf(TYPE.toUpperCase()));
/* 352 */           } catch (Exception exception) {}
/*     */         } 
/*     */       }
/*     */       
/* 356 */       this.ITEM = MAT.parseItem(1);
/* 357 */       if (!this.D_TYPES.isEmpty()) {
/* 358 */         MAT1 = this.D_TYPES.get(0);
/*     */         
/* 360 */         if (!IFurnaceU.E(MAT1)) {
/* 361 */           this.DEFAULT = MAT1.parseItem(1);
/*     */         }
/*     */       } 
/* 364 */       this.ITEM_SLOT = this.SE.getInt("slot");
/* 365 */       this.ITEM_CLICK = this.SE.getString("click");
/* 366 */       this.OWNERS = this.SE.getStringList("owners");
/* 367 */       this.AMOUNT = this.SE.getIntegerList("amount");
/* 368 */       this.D_AMOUNT = this.SE.getIntegerList("d_amount");
/* 369 */       this.NAMES = IMain.UTILS.colorL(this.SE.getStringList("names"));
/* 370 */       this.D_NAMES = IMain.UTILS.colorL(this.SE.getStringList("d_names"));
/* 371 */       ConfigurationSection LORES_S = this.SE.getConfigurationSection("lores");
/* 372 */       ConfigurationSection LORES_D = this.SE.getConfigurationSection("d_lores");
/*     */       
/* 374 */       if (LORES_S != null) {
/* 375 */         Set<String> K = LORES_S.getKeys(false);
/*     */         
/* 377 */         if (!K.isEmpty()) {
/* 378 */           for (String K1 : K) {
/* 379 */             if (K1 != null && !K1.isEmpty()) {
/* 380 */               List<String> L = LORES_S.getStringList(K1);
/*     */               
/* 382 */               if (L != null && !L.isEmpty()) {
/* 383 */                 if (!this.K_L.contains(K1)) {
/* 384 */                   this.K_L.add(K1);
/*     */                 }
/* 386 */                 this.LORES_MAP.put(K1, IMain.UTILS.colorL(L));
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/* 392 */       if (LORES_D != null) {
/* 393 */         Set<String> K = LORES_D.getKeys(false);
/*     */         
/* 395 */         if (!K.isEmpty()) {
/* 396 */           for (String K1 : K) {
/* 397 */             if (K1 != null && !K1.isEmpty()) {
/* 398 */               List<String> L = LORES_D.getStringList(K1);
/*     */               
/* 400 */               if (L != null && !L.isEmpty()) {
/* 401 */                 if (!this.K_L1.contains(K1)) {
/* 402 */                   this.K_L1.add(K1);
/*     */                 }
/* 404 */                 this.DLORES_MAP.put(K1, IMain.UTILS.colorL(L));
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/* 410 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\manager\menu\menu\IMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */