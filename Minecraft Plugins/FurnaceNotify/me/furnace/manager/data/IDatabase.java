/*     */ package me.furnace.manager.data;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import me.furnace.IMain;
/*     */ import me.furnace.manager.furnace.IFurnace;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class IDatabase
/*     */ {
/*  16 */   public List<Player> ADMINS = new ArrayList<>();
/*  17 */   public Map<Player, IData> VIEWERS = new HashMap<>();
/*  18 */   public Map<String, IData> DATABASE = new HashMap<>();
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
/*     */   
/*     */   public IData get(String PLAYER) {
/*  31 */     return get(IMain.S.getOfflinePlayer(PLAYER));
/*     */   }
/*     */ 
/*     */   
/*     */   public IData get(OfflinePlayer PLAYER) {
/*  36 */     String NAME = PLAYER.getName().toLowerCase();
/*     */     
/*  38 */     if (!this.DATABASE.containsKey(NAME)) {
/*  39 */       IData DATA = new IData(PLAYER);
/*     */       
/*  41 */       DATA.load();
/*  42 */       DATA.start();
/*  43 */       this.DATABASE.put(NAME, DATA);
/*  44 */       return DATA;
/*     */     } 
/*  46 */     return this.DATABASE.get(NAME);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IFurnace create(String PLAYER) {
/*  53 */     PLAYER = PLAYER.toLowerCase();
/*  54 */     IData DATA = this.DATABASE.get(PLAYER);
/*  55 */     String ID = UUID.randomUUID().toString().substring(0, 8);
/*     */     
/*  57 */     return DATA.add(ID);
/*     */   }
/*     */ 
/*     */   
/*     */   public IData delete(String PLAYER, String ID) {
/*  62 */     PLAYER = PLAYER.toLowerCase();
/*  63 */     IData DATA = this.DATABASE.get(PLAYER);
/*     */     
/*  65 */     if (ID == null || ID.isEmpty()) {
/*  66 */       return DATA;
/*     */     }
/*  68 */     DATA.remove(ID);
/*  69 */     return DATA;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean save() {
/*     */     String C;
/*  76 */     int COUNT = 0;
/*     */     
/*  78 */     File DIR = new File(IMain.PL.getDataFolder(), "Data");
/*     */     
/*  80 */     if (!DIR.exists()) {
/*  81 */       DIR.mkdir();
/*     */     }
/*  83 */     for (String OFF : this.DATABASE.keySet()) {
/*  84 */       IData DATA = this.DATABASE.get(OFF);
/*     */       
/*  86 */       if (DATA.save()) {
/*  87 */         COUNT++;
/*     */       }
/*  89 */       DATA.stop();
/*     */     } 
/*  91 */     this.ADMINS.clear();
/*  92 */     this.VIEWERS.clear();
/*  93 */     this.DATABASE.clear();
/*  94 */     if (COUNT > 1) {
/*  95 */       C = String.valueOf(COUNT) + " &2users";
/*     */     } else {
/*  97 */       C = String.valueOf(COUNT) + " &2user";
/*     */     } 
/*  99 */     IMain.UTILS.console("&6" + C + " &2have been saved in the database.", true);
/* 100 */     return true;
/*     */   }
/*     */   
/*     */   public boolean load() {
/*     */     String C;
/* 105 */     int COUNT = 0;
/*     */     
/* 107 */     File DIR = new File(IMain.PL.getDataFolder(), "Data");
/*     */     
/* 109 */     if (!DIR.exists())
/* 110 */       DIR.mkdir();  byte b; int i;
/*     */     OfflinePlayer[] arrayOfOfflinePlayer;
/* 112 */     for (i = (arrayOfOfflinePlayer = IMain.S.getOfflinePlayers()).length, b = 0; b < i; ) { OfflinePlayer OFF = arrayOfOfflinePlayer[b];
/* 113 */       IData DATA = new IData(OFF);
/*     */       
/* 115 */       if (DATA.load()) {
/* 116 */         COUNT++;
/*     */       }
/* 118 */       DATA.start();
/* 119 */       this.DATABASE.put(OFF.getName().toLowerCase(), DATA); b++; }
/*     */     
/* 121 */     if (COUNT > 1) {
/* 122 */       C = String.valueOf(COUNT) + " &2users";
/*     */     } else {
/* 124 */       C = String.valueOf(COUNT) + " &2user";
/*     */     } 
/* 126 */     IMain.UTILS.console("&6" + C + " &2have been loaded from the database.", true);
/* 127 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\manager\data\IDatabase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */