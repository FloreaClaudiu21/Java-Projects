/*     */ package me.furnace.utils;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.furnace.IMain;
/*     */ import me.furnace.XMaterial;
/*     */ import me.furnace.config.IConfig;
/*     */ import me.furnace.config.IKey;
/*     */ import me.furnace.manager.IBar;
/*     */ import me.furnace.manager.data.IDatabase;
/*     */ import me.furnace.manager.furnace.IFurnaceU;
/*     */ import me.furnace.manager.menu.IMenus;
/*     */ import me.furnace.manager.recipe.IFurnaceRecipe;
/*     */ import me.furnace.manager.recipe.IRecipes;
/*     */ import me.furnace.version.IVersion;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Effect;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.FurnaceInventory;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IUtils
/*     */ {
/*     */   public void print() {
/*  42 */     console("&8=-=-=-=-=-=-=-=-=-=-=");
/*  43 */     console(" &8| &3&lPortableFurnace&r &8|");
/*  44 */     console("&8=-=-=-=-=-=-=-=-=-=-=");
/*  45 */     console("&8&l➢ &dMain: &6" + IMain.DESC.getMain());
/*  46 */     console("&8&l➢ &dVersion: &e" + IMain.DESC.getVersion());
/*  47 */     console("&8&l➢ &dAuthor: &e" + (String)IMain.DESC.getAuthors().get(0));
/*  48 */     console("&8&l➢ &dBukkit Version: &a" + IMain.VE);
/*  49 */     console("&8&l➢ &dDescription: &r" + IMain.DESC.getDescription());
/*  50 */     console("&8|||||||||||||||||||||||||||||||||||||||||||||||");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean load() {
/*  56 */     print();
/*  57 */     IMain.RED = color("&c");
/*  58 */     IMain.PUR = color("&d");
/*  59 */     IMain.RESET = color("&e");
/*  60 */     IMain.TRUE = color("&atrue");
/*  61 */     IMain.FALSE = color("&cfalse");
/*     */     
/*  63 */     String V = IMain.VE.split("_")[1];
/*  64 */     IMain.VEID = Integer.parseInt(V);
/*  65 */     if (IMain.VEID < 17) {
/*  66 */       IMain.VERSION = new IVersion();
/*     */     } else {
/*  68 */       IMain.NEWVERSION = true;
/*  69 */       IMain.VERSION = new IVersion();
/*     */     } 
/*  71 */     if (IMain.ERROR) {
/*  72 */       return false;
/*     */     }
/*  74 */     IMain.MENUS = new IMenus();
/*  75 */     IMain.DATA = new IDatabase();
/*  76 */     IMain.VARS = new IVars();
/*  77 */     IMain.BOSSBAR = new IBar();
/*  78 */     IMain.CONFIG = new IConfig();
/*  79 */     IMain.RECIPES = new IRecipes();
/*  80 */     IMain.COOLDOWN = new ICooldown();
/*  81 */     IMain.EMPTY_ITEM = XMaterial.AIR.parseItem(0);
/*  82 */     IMain.EMPTY_AIR = XMaterial.AIR.parseMaterial();
/*  83 */     IMain.GLASS_ITEM = XMaterial.IRON_BARS.parseItem(1); byte b; int i;
/*     */     XMaterial[] arrayOfXMaterial;
/*  85 */     for (i = (arrayOfXMaterial = XMaterial.values()).length, b = 0; b < i; ) { XMaterial M = arrayOfXMaterial[b];
/*  86 */       if (M.name().endsWith("_GLASS_PANE"))
/*  87 */         IMain.GLASS_MATERIAL.add(M.parseItem(1)); 
/*     */       b++; }
/*     */     
/*  90 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String decolor(String M) {
/*  95 */     if (M == null || M.isEmpty()) {
/*  96 */       return "";
/*     */     }
/*  98 */     return ChatColor.stripColor(M);
/*     */   }
/*     */ 
/*     */   
/*     */   public String color(String M) {
/* 103 */     if (M == null || M.isEmpty()) {
/* 104 */       return "";
/*     */     }
/* 106 */     return ChatColor.translateAlternateColorCodes('&', M);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> colorL(List<String> L) {
/* 111 */     List<String> LI = new ArrayList<>();
/*     */     
/* 113 */     if (L == null || L.isEmpty()) {
/* 114 */       return LI;
/*     */     }
/* 116 */     for (String M : L) {
/* 117 */       LI.add(color(M));
/*     */     }
/* 119 */     return LI;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean console(String M) {
/* 124 */     return console(M, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public String center(String M) {
/* 129 */     return center(M, IMain.MAX_CHAR);
/*     */   }
/*     */ 
/*     */   
/*     */   public String center(String M, int MAX) {
/* 134 */     if (M == null || M.isEmpty()) {
/* 135 */       return "";
/*     */     }
/* 137 */     if (M.length() > MAX) {
/* 138 */       M = M.substring(0, MAX);
/*     */     }
/* 140 */     if (M.length() == MAX) {
/* 141 */       return M;
/*     */     }
/* 143 */     int PREFIX = 1;
/* 144 */     int SUFFIX = 1;
/* 145 */     int L = MAX - M.length();
/* 146 */     StringBuffer SB = new StringBuffer();
/* 147 */     StringBuffer SB1 = new StringBuffer();
/*     */     
/* 149 */     if (L > 2) {
/* 150 */       PREFIX = L / 2;
/* 151 */       SUFFIX = L / 2;
/*     */     }  int I;
/* 153 */     for (I = 0; I < PREFIX; I++) {
/* 154 */       SB = SB.append(" ");
/*     */     }
/* 156 */     for (I = 0; I < SUFFIX; I++) {
/* 157 */       SB1 = SB1.append(" ");
/*     */     }
/* 159 */     return String.valueOf(SB.toString()) + M + SB1.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean console(String M, boolean P) {
/* 164 */     if (M == null || M.isEmpty()) {
/* 165 */       return false;
/*     */     }
/* 167 */     if (P) {
/* 168 */       IMain.CONSOLE.sendMessage(color(String.valueOf(IMain.PR) + M));
/*     */     } else {
/* 170 */       IMain.CONSOLE.sendMessage(color(M));
/*     */     } 
/* 172 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int progress_total(int S_A, int R_A) {
/* 177 */     if (R_A <= 0) {
/* 178 */       return 0;
/*     */     }
/* 180 */     double D = R_A / (S_A + R_A);
/* 181 */     D *= 100.0D;
/* 182 */     return (int)Math.round(D);
/*     */   }
/*     */ 
/*     */   
/*     */   public int progress(int COOKTIME, int TOTAL) {
/* 187 */     if (COOKTIME <= 0) {
/* 188 */       return 0;
/*     */     }
/* 190 */     int T = TOTAL;
/* 191 */     int C = COOKTIME;
/*     */     
/* 193 */     double R = C / T;
/* 194 */     return (int)(R * 100.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean match(ItemStack SOURCE, ItemStack TARGET) {
/* 199 */     if (IFurnaceU.E(SOURCE) || IFurnaceU.E(TARGET)) {
/* 200 */       return false;
/*     */     }
/* 202 */     if (XMaterial.matchXMaterial(SOURCE) == XMaterial.matchXMaterial(TARGET)) {
/* 203 */       return true;
/*     */     }
/* 205 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int d(float var0) {
/* 210 */     int var1 = (int)var0;
/* 211 */     return (var0 < var1) ? (var1 - 1) : var1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int f(float var0) {
/* 216 */     int var1 = (int)var0;
/* 217 */     return (var0 > var1) ? (var1 + 1) : var1;
/*     */   }
/*     */ 
/*     */   
/*     */   private int XP(int A, Float XP) {
/* 222 */     if (A < 1) {
/* 223 */       A = 1;
/*     */     }
/* 225 */     int i = A;
/* 226 */     Float f = XP;
/*     */     
/* 228 */     if (f.floatValue() <= 0.1F) {
/* 229 */       f = Float.valueOf(0.1F);
/*     */     }
/* 231 */     int j = d(i * f.floatValue());
/* 232 */     if (j < f(i * f.floatValue()) && Math.random() < (i * f.floatValue() - j)) {
/* 233 */       j++;
/*     */     }
/* 235 */     i = j;
/* 236 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean file_exists(File F, String... KEYS) {
/* 241 */     if (F == null || !F.exists()) {
/* 242 */       return false;
/*     */     }
/* 244 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(F);
/*     */     
/* 246 */     if (yamlConfiguration.getKeys(false).isEmpty()) {
/* 247 */       F.delete();
/* 248 */       return false;
/*     */     }  byte b; int i; String[] arrayOfString;
/* 250 */     for (i = (arrayOfString = KEYS).length, b = 0; b < i; ) { String K = arrayOfString[b];
/* 251 */       String P = yamlConfiguration.getString(K);
/*     */       
/* 253 */       if (P == null || P.isEmpty()) {
/* 254 */         F.delete();
/* 255 */         return false;
/*     */       }  b++; }
/*     */     
/* 258 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public File save_resource(File O, String RESOURCE_NAME) {
/* 263 */     if (RESOURCE_NAME == null || RESOURCE_NAME.isEmpty() || O == null) {
/* 264 */       return null;
/*     */     }
/* 266 */     RESOURCE_NAME = RESOURCE_NAME.replace('\\', '/');
/* 267 */     InputStream in = IMain.PL.getResource(RESOURCE_NAME);
/*     */     
/* 269 */     if (in == null) {
/* 270 */       throw new IllegalArgumentException("The embedded resource '" + RESOURCE_NAME + "' cannot be found");
/*     */     }
/* 272 */     File outFile = new File(O, RESOURCE_NAME);
/* 273 */     int lastIndex = RESOURCE_NAME.lastIndexOf('/');
/* 274 */     File outDir = new File(O, RESOURCE_NAME.substring(0, (lastIndex >= 0) ? lastIndex : 0));
/*     */     
/* 276 */     if (!outDir.exists()) {
/* 277 */       outDir.mkdirs();
/*     */     }
/*     */     try {
/* 280 */       if (!outFile.exists()) {
/* 281 */         OutputStream out = new FileOutputStream(outFile);
/* 282 */         byte[] buf = new byte[1024];
/*     */         int len;
/* 284 */         while ((len = in.read(buf)) > 0) {
/* 285 */           out.write(buf, 0, len);
/*     */         }
/* 287 */         out.close();
/* 288 */         in.close();
/*     */       } 
/* 290 */     } catch (IOException ex) {
/* 291 */       ex.printStackTrace();
/*     */     } 
/* 293 */     return outFile;
/*     */   }
/*     */ 
/*     */   
/*     */   public String PERM(Player USER, String P) {
/* 298 */     if (IMain.DATA.ADMINS.contains(USER)) {
/* 299 */       return IMain.CONFIG.perm(String.valueOf(P) + "_admin");
/*     */     }
/* 301 */     return IMain.CONFIG.perm(P);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean giveXP(Inventory TOP, Player USER) {
/* 306 */     if (TOP == null || USER == null) {
/* 307 */       return false;
/*     */     }
/* 309 */     FurnaceInventory FI = (FurnaceInventory)TOP;
/*     */     
/* 311 */     ItemStack R = FI.getResult();
/* 312 */     if (IFurnaceU.E(R)) {
/* 313 */       return false;
/*     */     }
/* 315 */     IFurnaceRecipe RECIPE = IMain.RECIPES.recipe_result(R);
/* 316 */     if (RECIPE == null) {
/* 317 */       return false;
/*     */     }
/* 319 */     int XP = XP(R.getAmount(), RECIPE.EXPERIENCE);
/*     */     
/* 321 */     USER.giveExp(XP);
/* 322 */     IMain.VERSION.actionbar_send(USER, IMain.VARS.M((OfflinePlayer)USER, "furnace_extract").replace("%XP%", (new StringBuilder(String.valueOf(XP))).toString()));
/* 323 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean sendEffect(Player U, String PATH) {
/* 329 */     if (U == null || PATH == null || PATH.isEmpty()) {
/* 330 */       return false;
/*     */     }
/* 332 */     PATH = "effect_" + PATH;
/* 333 */     if (!IMain.CONFIG.CONFIG_MAP.containsKey(PATH)) {
/* 334 */       return false;
/*     */     }
/* 336 */     boolean E = ((IKey)IMain.CONFIG.CONFIG_MAP.get(PATH)).B;
/*     */     
/* 338 */     if (E) {
/* 339 */       int I = IMain.CONFIG.i("e_radius");
/*     */       
/* 341 */       if (I < 1 || I > 15) {
/* 342 */         I = 5;
/*     */       }
/* 344 */       Effect P = null;
/* 345 */       Location L = U.getLocation();
/* 346 */       String NAME = ((IKey)IMain.CONFIG.CONFIG_MAP.get(String.valueOf(PATH) + "_name")).S;
/*     */       
/*     */       try {
/* 349 */         P = Effect.valueOf(NAME);
/* 350 */       } catch (Exception exception) {}
/*     */       
/* 352 */       if (P != null) {
/* 353 */         for (int I1 = 0; I1 < I; I1++) {
/* 354 */           U.playEffect(L, P, I1);
/*     */         }
/*     */       }
/*     */     } 
/* 358 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnac\\utils\IUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */