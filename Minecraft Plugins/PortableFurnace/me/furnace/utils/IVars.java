/*     */ package me.furnace.utils;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.clip.placeholderapi.PlaceholderAPI;
/*     */ import me.furnace.IMain;
/*     */ import me.furnace.config.IKey;
/*     */ import me.furnace.manager.data.IData;
/*     */ import me.furnace.manager.furnace.IFurnace;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IVars
/*     */ {
/*  19 */   public String EMPTY = IMain.UTILS.color("&a");
/*  20 */   private String NOT_FOUND = IMain.UTILS.color("&cMessage not found");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int d(int TIME) {
/*  30 */     if (TIME < 20 && TIME > 0) {
/*  31 */       return 1;
/*     */     }
/*  33 */     return TIME / 20;
/*     */   }
/*     */ 
/*     */   
/*     */   private int count(int AMOUNT, int COOKTIME) {
/*  38 */     if (AMOUNT <= 0) {
/*  39 */       return 0;
/*     */     }
/*  41 */     return AMOUNT * COOKTIME / 20;
/*     */   }
/*     */ 
/*     */   
/*     */   private String convert(int TIME) {
/*  46 */     if (TIME < 60) {
/*  47 */       if (TIME == 1) {
/*  48 */         return String.valueOf(IMain.RED) + TIME + IMain.RESET + " second";
/*     */       }
/*  50 */       return String.valueOf(IMain.RED) + TIME + IMain.RESET + " seconds";
/*     */     } 
/*     */     
/*  53 */     String MI = String.valueOf(IMain.RESET) + " minute";
/*  54 */     int M = TIME / 60;
/*  55 */     int s = 60 * M;
/*  56 */     int secondsLeft = TIME - s;
/*     */     
/*  58 */     if (M < 60) {
/*  59 */       if (M > 1) {
/*  60 */         MI = String.valueOf(IMain.RESET) + " minutes";
/*     */       }
/*  62 */       if (secondsLeft > 0) {
/*  63 */         if (secondsLeft == 1) {
/*  64 */           return String.valueOf(IMain.PUR) + M + MI + " " + IMain.RED + secondsLeft + IMain.RESET + " second";
/*     */         }
/*  66 */         return String.valueOf(IMain.PUR) + M + MI + " " + IMain.RED + secondsLeft + IMain.RESET + " seconds";
/*     */       } 
/*     */       
/*  69 */       return String.valueOf(IMain.PUR) + M + MI;
/*     */     } 
/*  71 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> L(OfflinePlayer USER, List<String> L, IFurnace F) {
/*  76 */     List<String> NL = new ArrayList<>();
/*     */     
/*  78 */     if (L == null || L.isEmpty()) {
/*  79 */       if (F != null) {
/*  80 */         NL.add(IMain.UTILS.color("&0&o" + F.ID));
/*     */       }
/*  82 */       return NL;
/*     */     } 
/*  84 */     if (F != null) {
/*  85 */       NL.add(IMain.UTILS.color("&0&o" + F.ID));
/*     */     }
/*  87 */     for (String M : L) {
/*  88 */       M = M1(USER, M, F, null, null);
/*     */       
/*  90 */       if (F != null && M.length() > 60) {
/*  91 */         M = M.substring(0, 60);
/*     */       }
/*  93 */       NL.add(M);
/*     */     } 
/*  95 */     return NL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String M(OfflinePlayer USER, String PATH) {
/* 102 */     return M(USER, PATH, null, null, null, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public String M(OfflinePlayer USER, String PATH, IFurnace F) {
/* 107 */     return M(USER, PATH, F, null, null, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public String M(OfflinePlayer USER, String PATH, String TARGET) {
/* 112 */     return M(USER, PATH, null, null, TARGET, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public String M(OfflinePlayer USER, String PATH, String OWNER, boolean A) {
/* 117 */     return M(USER, PATH, null, null, null, OWNER);
/*     */   }
/*     */ 
/*     */   
/*     */   public String M(OfflinePlayer USER, String PATH, IFurnace F, ItemStack S, String TARGET, String OWNER) {
/* 122 */     if (PATH == null || PATH.isEmpty()) {
/* 123 */       return this.NOT_FOUND;
/*     */     }
/* 125 */     PATH = "m_" + PATH;
/* 126 */     if (!IMain.CONFIG.CONFIG_MAP.containsKey(PATH)) {
/* 127 */       return this.NOT_FOUND;
/*     */     }
/* 129 */     String MSG = ((IKey)IMain.CONFIG.CONFIG_MAP.get(PATH)).S;
/*     */     
/* 131 */     return M1(USER, MSG, F, TARGET, OWNER);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String M1(OfflinePlayer USER, String MSG, IFurnace F, String TARGET, String OWNER) {
/* 138 */     if (MSG == null || MSG.isEmpty()) {
/* 139 */       return this.NOT_FOUND;
/*     */     }
/* 141 */     Player P = null;
/* 142 */     int max = IMain.CONFIG.i("max_furnaces");
/*     */     
/* 144 */     if (TARGET == null || TARGET.isEmpty()) {
/* 145 */       MSG = MSG.replace("%TARGET%", "");
/*     */     } else {
/* 147 */       MSG = MSG.replace("%TARGET%", TARGET);
/*     */     } 
/* 149 */     MSG = MSG.replace("%MAX%", (new StringBuilder(String.valueOf(max))).toString());
/* 150 */     if (USER == null) {
/* 151 */       MSG = MSG.replace("%USER%", "");
/* 152 */       MSG = MSG.replace("%ADMIN%", "");
/* 153 */       MSG = MSG.replace("%COOLDOWN%", "");
/* 154 */       MSG = MSG.replace("%FURNACES%", "0");
/*     */     } else {
/* 156 */       if (USER.isOnline()) {
/* 157 */         P = IMain.S.getPlayer(USER.getName());
/*     */         
/* 159 */         MSG = MSG.replace("%NAME%", P.getDisplayName());
/* 160 */         MSG = MSG.replace("%ADMIN%", P.getDisplayName());
/* 161 */         if (P != null) {
/* 162 */           MSG = MSG.replace("%COOLDOWN%", IMain.COOLDOWN.get(P));
/*     */         }
/*     */       } else {
/* 165 */         MSG = MSG.replace("%NAME%", USER.getName());
/* 166 */         MSG = MSG.replace("%ADMIN%", USER.getName());
/* 167 */         MSG = MSG.replace("%COOLDOWN%", "0");
/*     */       } 
/* 169 */       MSG = MSG.replace("%USER%", USER.getName());
/* 170 */       MSG = MSG.replace("%FURNACES%", (new StringBuilder(String.valueOf((IMain.DATA.get(USER)).FURNACES_IDS.size()))).toString());
/*     */     } 
/* 172 */     if (F == null) {
/* 173 */       MSG = MSG.replace("%ID%", "");
/* 174 */       if (OWNER == null) {
/* 175 */         MSG = MSG.replace("%OWNER%", "");
/*     */       } else {
/* 177 */         MSG = MSG.replace("%OWNER%", OWNER);
/*     */       } 
/* 179 */       MSG = MSG.replace("%FURNACES_OWNER%", "0");
/* 180 */       MSG = MSG.replace("%SMELTING%", "");
/* 181 */       MSG = MSG.replace("%SMELTING_A%", "0");
/* 182 */       MSG = MSG.replace("%FUEL%", "");
/* 183 */       MSG = MSG.replace("%RESULT%", "");
/*     */       
/* 185 */       MSG = MSG.replace("%COOKTIME%", "");
/* 186 */       MSG = MSG.replace("%COOKTIMETOTAL%", "");
/* 187 */       MSG = MSG.replace("%BURNTIME%", "");
/* 188 */       MSG = MSG.replace("%BURNING%", "");
/* 189 */       MSG = MSG.replace("%PROGRESS%", "0");
/* 190 */       MSG = MSG.replace("%PROGRESSTOTAL%", "");
/*     */     } else {
/* 192 */       IData D = IMain.DATA.get(F.OWNER);
/*     */       
/* 194 */       MSG = MSG.replace("%ID%", F.ID);
/* 195 */       if (F.OWNER.isOnline()) {
/* 196 */         Player O = IMain.S.getPlayer(F.OWNER.getName());
/* 197 */         MSG = MSG.replace("%OWNER%", O.getDisplayName());
/*     */       } else {
/* 199 */         MSG = MSG.replace("%OWNER%", F.OWNER.getName());
/*     */       } 
/* 201 */       MSG = MSG.replace("%FURNACES_OWNER%", (new StringBuilder(String.valueOf(D.FURNACES_IDS.size()))).toString());
/* 202 */       MSG = MSG.replace("%SMELTING%", F.SMELTING_C);
/* 203 */       MSG = MSG.replace("%SMELTING_A%", (new StringBuilder(String.valueOf(F.SMELTING_A))).toString());
/* 204 */       MSG = MSG.replace("%FUEL%", F.FUEL_C);
/* 205 */       MSG = MSG.replace("%RESULT%", F.RESULT_C);
/* 206 */       if (F.BURNTIME > 0) {
/* 207 */         MSG = MSG.replace("%BURNING%", IMain.TRUE);
/*     */       } else {
/* 209 */         MSG = MSG.replace("%BURNING%", IMain.FALSE);
/*     */       } 
/* 211 */       MSG = MSG.replace("%BURNTIME%", convert(d(F.BURNTIME)));
/* 212 */       MSG = MSG.replace("%COOKTIME%", convert(d(F.COOKTIME_TOTAL - F.COOKTIME)));
/* 213 */       MSG = MSG.replace("%COOKTIMETOTAL%", convert(count(F.SMELTING_A, F.COOKTIME_TOTAL)));
/* 214 */       MSG = MSG.replace("%PROGRESS%", (new StringBuilder(String.valueOf(IMain.UTILS.progress(F.COOKTIME, F.COOKTIME_TOTAL)))).toString());
/* 215 */       MSG = MSG.replace("%PROGRESSTOTAL%", (new StringBuilder(String.valueOf(IMain.UTILS.progress_total(F.SMELTING_A, F.RESULT_A)))).toString());
/*     */     } 
/* 217 */     if (IMain.PLACEHOLDER && P != null) {
/* 218 */       MSG = PlaceholderAPI.setPlaceholders(P, MSG);
/*     */     }
/* 220 */     return MSG;
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnac\\utils\IVars.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */