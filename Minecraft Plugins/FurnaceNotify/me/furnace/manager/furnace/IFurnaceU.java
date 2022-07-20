/*     */ package me.furnace.manager.furnace;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import me.furnace.IMain;
/*     */ import me.furnace.XMaterial;
/*     */ import me.furnace.manager.recipe.IFurnaceRecipe;
/*     */ import org.apache.commons.lang.WordUtils;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IFurnaceU
/*     */ {
/*     */   public static void send_all_P(IFurnace F, String PA) {
/*  26 */     if (F == null || PA == null || PA.isEmpty()) {
/*     */       return;
/*     */     }
/*  29 */     for (HumanEntity H : F.VIEWERS) {
/*  30 */       Player P = (Player)H;
/*     */       
/*  32 */       IMain.UTILS.sendEffect(P, PA);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void send_all(IFurnace F, String M) {
/*  39 */     send_all(F, M, false, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void send_all(IFurnace F, String M, boolean ACTION) {
/*  45 */     send_all(F, M, ACTION, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void send_all(IFurnace F, String M, boolean A, String TITLE) {
/*  51 */     if (F == null || M == null || M.isEmpty()) {
/*     */       return;
/*     */     }
/*  54 */     for (HumanEntity H : F.VIEWERS) {
/*  55 */       Player P = (Player)H;
/*     */       
/*  57 */       if (A) {
/*  58 */         IMain.VERSION.actionbar_send(P, M); continue;
/*  59 */       }  if (TITLE != null) {
/*  60 */         IMain.VERSION.title_send(P, TITLE, M); continue;
/*     */       } 
/*  62 */       IMain.BOSSBAR.show(P, M);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean E(XMaterial M) {
/*  70 */     if (M == null) {
/*  71 */       return true;
/*     */     }
/*  73 */     if (M == XMaterial.AIR) {
/*  74 */       return true;
/*     */     }
/*  76 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean E(ItemStack ITEM) {
/*  81 */     if (ITEM == null) {
/*  82 */       return true;
/*     */     }
/*  84 */     if (ITEM.getAmount() <= 0) {
/*  85 */       return true;
/*     */     }
/*  87 */     Map<String, Object> MAP = serialize(ITEM);
/*     */     
/*  89 */     if (!MAP.containsKey("type")) {
/*  90 */       return true;
/*     */     }
/*  92 */     String T = String.valueOf(MAP.get("type")).toLowerCase();
/*     */     
/*  94 */     if (T.equalsIgnoreCase("air")) {
/*  95 */       return true;
/*     */     }
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean is_bucket(ItemStack ITEM) {
/* 102 */     if (E(ITEM)) {
/* 103 */       return false;
/*     */     }
/* 105 */     XMaterial M = XMaterial.matchXMaterial(ITEM);
/*     */     
/* 107 */     if (M == XMaterial.LAVA_BUCKET) {
/* 108 */       return true;
/*     */     }
/* 110 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean furnace_empty(IFurnace F) {
/* 115 */     if (F == null) {
/* 116 */       return false;
/*     */     }
/*     */     try {
/* 119 */       if (!E(F.fuel()) || !E(F.smelting()) || !E(F.result())) {
/* 120 */         return false;
/*     */       }
/* 122 */     } catch (Exception exception) {}
/*     */     
/* 124 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String item_type(ItemStack ITEM) {
/* 131 */     return (String)serialize(ITEM).get("type");
/*     */   }
/*     */ 
/*     */   
/*     */   public static Map<String, Object> serialize(ItemStack ITEM) {
/* 136 */     Map<String, Object> NMAP = new HashMap<>();
/*     */     
/* 138 */     if (ITEM == null) {
/* 139 */       return NMAP;
/*     */     }
/* 141 */     Map<String, Object> MAP = ITEM.serialize();
/*     */     
/* 143 */     for (String K : MAP.keySet()) {
/* 144 */       K = K.toLowerCase();
/*     */       
/* 146 */       if (!K.equalsIgnoreCase("meta")) {
/* 147 */         NMAP.put(K, MAP.get(K));
/*     */       }
/*     */     } 
/* 150 */     return NMAP;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack deserialize(ConfigurationSection SE) {
/* 155 */     if (SE == null || SE.getKeys(false).isEmpty()) {
/* 156 */       return IMain.EMPTY_ITEM;
/*     */     }
/* 158 */     Map<String, Object> MAP = new HashMap<>();
/*     */     
/* 160 */     for (String K : SE.getKeys(false)) {
/* 161 */       Object O = SE.get(K);
/*     */       
/* 163 */       if (O != null) {
/* 164 */         MAP.put(K.toLowerCase(), O);
/*     */       }
/*     */     } 
/* 167 */     if (!MAP.keySet().contains("type")) {
/* 168 */       return IMain.EMPTY_ITEM;
/*     */     }
/*     */     try {
/* 171 */       return ItemStack.deserialize(MAP);
/* 172 */     } catch (Exception EX) {
/* 173 */       return IMain.EMPTY_ITEM;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> serialize_L(ItemStack ITEM) {
/* 179 */     List<String> L = new ArrayList<>();
/* 180 */     Map<String, Object> MAP = ITEM.serialize();
/*     */     
/* 182 */     if (MAP.containsKey("type")) {
/* 183 */       L.add((String)MAP.get("type"));
/*     */     }
/* 185 */     if (MAP.containsKey("damage")) {
/* 186 */       L.add((String)MAP.get("damage"));
/*     */     }
/* 188 */     return L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String word(ItemStack ITEM) {
/* 195 */     if (E(ITEM)) {
/* 196 */       return IMain.EMPTY;
/*     */     }
/* 198 */     String NAME = null;
/*     */     
/*     */     try {
/* 201 */       Object NMS = IMain.VERSION.CITEMSTACK_NMS_METHOD.invoke(null, new Object[] { ITEM });
/* 202 */       Object TAG = IMain.VERSION.create_nbt();
/* 203 */       Object TAG1 = IMain.VERSION.ITEMSTACK_SAVE_METHOD.invoke(NMS, new Object[] { TAG });
/*     */       
/* 205 */       NAME = (String)IMain.VERSION.NBT_STRING_METHOD.invoke(TAG1, new Object[] { "id" });
/* 206 */       if (NAME == null || NAME.isEmpty()) {
/* 207 */         return IMain.EMPTY;
/*     */       }
/* 209 */       NAME = NAME.toLowerCase().replace("minecraft:", "").replace("_", " ");
/*     */       
/* 211 */       return WordUtils.capitalize(NAME);
/* 212 */     } catch (Exception E) {
/* 213 */       E.printStackTrace();
/*     */       
/* 215 */       return IMain.EMPTY;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean has_fuel(Object TILE, ItemStack FUEL) throws Exception {
/* 222 */     if (TILE == null || FUEL == null || FUEL.getAmount() <= 0) {
/* 223 */       return false;
/*     */     }
/* 225 */     if (((Boolean)IMain.VERSION.FURNACE_TILE_HASFUEL_METHOD.invoke(null, new Object[] {
/* 226 */           IMain.VERSION.CITEMSTACK_NMS_METHOD.invoke(null, new Object[] { FUEL }) })).booleanValue()) {
/* 227 */       return true;
/*     */     }
/* 229 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean burn(IFurnaceRecipe RECIPE, Object TILE, ItemStack SMELTING, ItemStack RESULT, ItemStack FUEL) throws Exception {
/* 235 */     if (RECIPE == null || TILE == null || E(SMELTING)) {
/* 236 */       return false;
/*     */     }
/*     */     
/* 239 */     if (E(RESULT)) {
/* 240 */       IMain.VERSION.FURNACE_TILE_SETITEM_METHOD.invoke(TILE, new Object[] { Integer.valueOf(2), 
/* 241 */             IMain.VERSION.CITEMSTACK_NMS_METHOD.invoke(null, new Object[] { RECIPE.RESULT }) });
/* 242 */     } else if (IFurnaceRecipe.same(RESULT, RECIPE.RESULT)) {
/* 243 */       RESULT.setAmount(RESULT.getAmount() + 1);
/* 244 */       IMain.VERSION.FURNACE_TILE_SETITEM_METHOD.invoke(TILE, new Object[] { Integer.valueOf(2), 
/* 245 */             IMain.VERSION.CITEMSTACK_NMS_METHOD.invoke(null, new Object[] { RESULT }) });
/*     */     } 
/*     */     
/* 248 */     XMaterial MAT = XMaterial.matchXMaterial(SMELTING);
/* 249 */     if (MAT == XMaterial.SPONGE && 
/* 250 */       !E(FUEL) && XMaterial.matchXMaterial(FUEL) == XMaterial.BUCKET) {
/* 251 */       IMain.VERSION.FURNACE_TILE_SETITEM_METHOD.invoke(TILE, new Object[] { Integer.valueOf(1), 
/* 252 */             IMain.VERSION.CITEMSTACK_NMS_METHOD.invoke(null, new Object[] { XMaterial.WATER_BUCKET.parseItem(1) }) });
/*     */     }
/*     */ 
/*     */     
/* 256 */     ItemStack SM = IMain.EMPTY_ITEM;
/*     */     
/* 258 */     if (SMELTING.getAmount() > 1) {
/* 259 */       SMELTING.setAmount(SMELTING.getAmount() - 1);
/* 260 */       SM = SMELTING;
/*     */     } 
/* 262 */     IMain.VERSION.FURNACE_TILE_SETITEM_METHOD.invoke(TILE, new Object[] { Integer.valueOf(0), IMain.VERSION.CITEMSTACK_NMS_METHOD.invoke(null, new Object[] { SM }) });
/* 263 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean can_burn(IFurnaceRecipe RECIPE, ItemStack RESULT) throws Exception {
/* 268 */     if (RECIPE == null) {
/* 269 */       return false;
/*     */     }
/* 271 */     if (!RECIPE.ENABLED) {
/* 272 */       return false;
/*     */     }
/* 274 */     if (E(RESULT)) {
/* 275 */       return true;
/*     */     }
/* 277 */     if (!IFurnaceRecipe.same(RESULT, RECIPE.RESULT)) {
/* 278 */       return false;
/*     */     }
/* 280 */     if (RESULT.getAmount() < 64 && RESULT.getAmount() < RESULT.getMaxStackSize()) {
/* 281 */       return true;
/*     */     }
/* 283 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\manager\furnace\IFurnaceU.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */