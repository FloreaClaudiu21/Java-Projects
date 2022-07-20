/*     */ package me.furnace.manager;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import me.furnace.IMain;
/*     */ import org.bukkit.boss.BarColor;
/*     */ import org.bukkit.boss.BarStyle;
/*     */ import org.bukkit.boss.BossBar;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ public class IBar
/*     */ {
/*     */   private String C;
/*     */   private String S;
/*  16 */   private String MSG = "Default Message";
/*  17 */   public Map<String, BossBar> BARS_DATABASE = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean load() {
/*  29 */     this.C = IMain.CONFIG.s("bar_color").toUpperCase();
/*  30 */     this.S = IMain.CONFIG.s("bar_style").toUpperCase();
/*     */     
/*  32 */     for (Player USER : IMain.S.getOnlinePlayers()) {
/*  33 */       add(USER);
/*     */     }
/*  35 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hide(Player USER) {
/*  41 */     if (USER == null) {
/*  42 */       return false;
/*     */     }
/*  44 */     if (!IMain.CONFIG.b("bar")) {
/*  45 */       return false;
/*     */     }
/*  47 */     BossBar BAR = get(USER);
/*     */     
/*  49 */     if (BAR == null) {
/*  50 */       return false;
/*     */     }
/*  52 */     BAR.hide();
/*  53 */     return true;
/*     */   }
/*     */   
/*     */   public boolean show(Player USER, String M) {
/*     */     BarStyle STYLE;
/*     */     BarColor COLOR;
/*  59 */     if (USER == null) {
/*  60 */       return false;
/*     */     }
/*  62 */     if (!IMain.CONFIG.b("bar")) {
/*  63 */       IMain.VERSION.actionbar_send(USER, M);
/*  64 */       return false;
/*     */     } 
/*  66 */     if (IMain.VE.startsWith("v1_8") || IMain.VE.startsWith("v1_7")) {
/*  67 */       IMain.VERSION.actionbar_send(USER, M);
/*  68 */       return false;
/*     */     } 
/*     */ 
/*     */     
/*  72 */     BossBar BAR = get(USER);
/*     */     
/*  74 */     if (BAR == null) {
/*  75 */       return false;
/*     */     }
/*  77 */     if (this.C == null || this.C.isEmpty()) {
/*  78 */       this.C = BarColor.PINK.name();
/*     */     }
/*  80 */     if (this.S == null || this.S.isEmpty()) {
/*  81 */       this.S = BarStyle.SEGMENTED_20.name();
/*     */     }
/*     */     try {
/*  84 */       STYLE = BarStyle.valueOf(this.S);
/*  85 */       COLOR = BarColor.valueOf(this.C);
/*     */       
/*  87 */       if (COLOR == null) {
/*  88 */         COLOR = BarColor.PINK;
/*     */       }
/*  90 */       if (STYLE == null) {
/*  91 */         STYLE = BarStyle.SEGMENTED_20;
/*     */       }
/*  93 */     } catch (Exception E) {
/*  94 */       COLOR = BarColor.PINK;
/*  95 */       STYLE = BarStyle.SEGMENTED_20;
/*     */     } 
/*  97 */     BAR.setTitle(M);
/*  98 */     BAR.setColor(COLOR);
/*  99 */     BAR.setStyle(STYLE);
/* 100 */     BAR.show();
/* 101 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private BossBar get(Player USER) {
/* 106 */     if (USER == null) {
/* 107 */       return null;
/*     */     }
/* 109 */     String N = USER.getName().toLowerCase();
/*     */     
/* 111 */     if (!this.BARS_DATABASE.containsKey(N)) {
/* 112 */       return null;
/*     */     }
/* 114 */     BossBar BAR = this.BARS_DATABASE.get(N);
/*     */     
/* 116 */     if (!BAR.getPlayers().contains(USER)) {
/* 117 */       BAR.addPlayer(USER);
/*     */     }
/* 119 */     return BAR;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean remove(Player USER) {
/* 125 */     if (USER == null) {
/* 126 */       return false;
/*     */     }
/* 128 */     if (!IMain.CONFIG.b("bar")) {
/* 129 */       return false;
/*     */     }
/* 131 */     String N = USER.getName().toLowerCase();
/*     */     
/* 133 */     if (!this.BARS_DATABASE.containsKey(N)) {
/* 134 */       return false;
/*     */     }
/* 136 */     BossBar BAR = get(USER);
/*     */     
/* 138 */     BAR.hide();
/* 139 */     BAR.removePlayer(USER);
/* 140 */     this.BARS_DATABASE.remove(N);
/* 141 */     return true;
/*     */   }
/*     */   
/*     */   public boolean add(Player USER) {
/*     */     BarStyle STYLE;
/*     */     BarColor COLOR;
/* 147 */     if (USER == null) {
/* 148 */       return false;
/*     */     }
/* 150 */     if (!IMain.CONFIG.b("bar")) {
/* 151 */       return false;
/*     */     }
/* 153 */     if (IMain.VE.startsWith("v1_8") || IMain.VE.startsWith("v1_7")) {
/* 154 */       return false;
/*     */     }
/* 156 */     String N = USER.getName().toLowerCase();
/*     */     
/* 158 */     if (this.BARS_DATABASE.containsKey(N)) {
/* 159 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 163 */     if (this.S == null || this.S.isEmpty()) {
/* 164 */       this.S = BarStyle.SEGMENTED_20.name();
/*     */     }
/* 166 */     if (this.C == null || this.C.isEmpty()) {
/* 167 */       this.C = BarColor.PINK.name();
/*     */     }
/*     */     try {
/* 170 */       STYLE = BarStyle.valueOf(this.S);
/* 171 */       COLOR = BarColor.valueOf(this.C);
/*     */       
/* 173 */       if (COLOR == null) {
/* 174 */         COLOR = BarColor.PINK;
/*     */       }
/* 176 */       if (STYLE == null) {
/* 177 */         STYLE = BarStyle.SEGMENTED_20;
/*     */       }
/* 179 */     } catch (Exception E) {
/* 180 */       COLOR = BarColor.PINK;
/* 181 */       STYLE = BarStyle.SEGMENTED_20;
/*     */     } 
/* 183 */     BossBar BAR = IMain.S.createBossBar(this.MSG, COLOR, STYLE, new org.bukkit.boss.BarFlag[0]);
/*     */     
/* 185 */     BAR.hide();
/* 186 */     BAR.addPlayer(USER);
/* 187 */     this.BARS_DATABASE.put(N, BAR);
/* 188 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\manager\IBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */