/*     */ package me.furnace.config;
/*     */ 
/*     */ import com.google.common.base.Charsets;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Writer;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import me.furnace.IMain;
/*     */ import me.furnace.config.def.IDefaultKey;
/*     */ import me.furnace.config.def.IDefaults;
/*     */ import me.furnace.config.section.ISection;
/*     */ 
/*     */ 
/*     */ public class IConfig
/*     */ {
/*     */   public IDefaults DEF;
/*     */   public Map<String, IKey> CONFIG_MAP;
/*     */   
/*     */   public IConfig() {
/*  23 */     this.DEF = new IDefaults();
/*  24 */     this.CONFIG_MAP = new HashMap<>();
/*     */     
/*  26 */     this.DEF.load();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean reload() {
/*  37 */     int COUNT = 0;
/*  38 */     this.CONFIG_MAP.clear();
/*  39 */     File F = new File(IMain.PL.getDataFolder(), "config.yml");
/*     */     
/*     */     try {
/*  42 */       if (!F.exists()) {
/*  43 */         F.createNewFile();
/*     */       }
/*  45 */       ISection CO = new ISection(F);
/*     */       
/*  47 */       for (String K : this.DEF.DEFAULTS_MAP.keySet()) {
/*  48 */         Object VALUE = null;
/*  49 */         boolean P = CO.has(K);
/*  50 */         IDefaultKey KEY = (IDefaultKey)this.DEF.DEFAULTS_MAP.get(K);
/*     */         
/*  52 */         COUNT++;
/*  53 */         if (!P) {
/*  54 */           VALUE = KEY.VALUE;
/*  55 */           IMain.UTILS.console("&7>> &cConfig key &b&l" + K + 
/*  56 */               " &cdoes not exist, using the default value till you reload the server.");
/*     */         } else {
/*  58 */           Object O = CO.get(K);
/*     */           
/*  60 */           if (O == null) {
/*  61 */             VALUE = KEY.VALUE;
/*  62 */             IMain.UTILS.console("&7>> &cValue of key &b&l" + K + 
/*  63 */                 " &cwas null, using the default value till you reload the server.");
/*     */           } else {
/*  65 */             VALUE = O;
/*     */           } 
/*     */         } 
/*  68 */         this.CONFIG_MAP.put(K, new IKey(VALUE));
/*     */       } 
/*  70 */     } catch (Exception EX) {
/*  71 */       EX.printStackTrace();
/*     */     } 
/*  73 */     IMain.UTILS.console("&2Loaded &6" + COUNT + " &2keys from the config.", true);
/*  74 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean load() {
/*  79 */     int COUNT = 0;
/*  80 */     boolean NEW = false;
/*  81 */     IMain.PL.getDataFolder().mkdirs();
/*  82 */     File F = new File(IMain.PL.getDataFolder(), "config.yml");
/*     */     
/*     */     try {
/*  85 */       if (!F.exists()) {
/*  86 */         NEW = true;
/*  87 */         F.createNewFile();
/*     */       } 
/*  89 */       ISection CO = new ISection(F);
/*  90 */       Writer FW = new OutputStreamWriter(new FileOutputStream(F), Charsets.UTF_8);
/*     */       
/*  92 */       this.DEF.header(FW);
/*  93 */       for (String K : this.DEF.DEFAULTS_MAP.keySet()) {
/*  94 */         Object VALUE = null;
/*  95 */         boolean P = CO.has(K);
/*  96 */         IDefaultKey KEY = (IDefaultKey)this.DEF.DEFAULTS_MAP.get(K);
/*     */         
/*  98 */         COUNT++;
/*  99 */         if (!P) {
/* 100 */           if (!NEW) {
/* 101 */             IMain.UTILS.console("&7>> &cConfig key &b&l" + K + 
/* 102 */                 " &cdoes not exist, setting the default value in the config.");
/*     */           }
/* 104 */           VALUE = KEY.VALUE;
/*     */         } else {
/* 106 */           Object O = CO.get(K);
/*     */           
/* 108 */           if (O == null) {
/* 109 */             VALUE = KEY.VALUE;
/* 110 */             IMain.UTILS.console("&7>> &cValue of key &b&l" + K + 
/* 111 */                 " &cwas null, setting the default value in the config.");
/*     */           } else {
/* 113 */             VALUE = O;
/*     */           } 
/*     */         } 
/* 116 */         this.CONFIG_MAP.put(K, new IKey(VALUE));
/* 117 */         this.DEF.save(FW, K, VALUE, KEY.COMMENTS);
/*     */       } 
/* 119 */       FW.close();
/* 120 */     } catch (Exception EX) {
/* 121 */       EX.printStackTrace();
/*     */     } 
/* 123 */     IMain.UTILS.console("&2Loaded &6" + COUNT + " &2keys from the config.", true);
/* 124 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b(String PATH) {
/* 131 */     if (PATH == null || PATH.isEmpty()) {
/* 132 */       return false;
/*     */     }
/* 134 */     if (!this.CONFIG_MAP.containsKey(PATH)) {
/* 135 */       return false;
/*     */     }
/* 137 */     return ((IKey)this.CONFIG_MAP.get(PATH)).B;
/*     */   }
/*     */ 
/*     */   
/*     */   public String s(String PATH) {
/* 142 */     if (PATH == null || PATH.isEmpty()) {
/* 143 */       return "";
/*     */     }
/* 145 */     if (!this.CONFIG_MAP.containsKey(PATH)) {
/* 146 */       return "";
/*     */     }
/* 148 */     return ((IKey)this.CONFIG_MAP.get(PATH)).S;
/*     */   }
/*     */ 
/*     */   
/*     */   public int i(String PATH) {
/* 153 */     if (PATH == null || PATH.isEmpty()) {
/* 154 */       return 0;
/*     */     }
/* 156 */     if (!this.CONFIG_MAP.containsKey(PATH)) {
/* 157 */       return 0;
/*     */     }
/* 159 */     return ((IKey)this.CONFIG_MAP.get(PATH)).I;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> l(String PATH) {
/* 164 */     if (PATH == null || PATH.isEmpty()) {
/* 165 */       return null;
/*     */     }
/* 167 */     if (!this.CONFIG_MAP.containsKey(PATH)) {
/* 168 */       return null;
/*     */     }
/* 170 */     return ((IKey)this.CONFIG_MAP.get(PATH)).L;
/*     */   }
/*     */ 
/*     */   
/*     */   public String perm(String PATH) {
/* 175 */     if (PATH == null || PATH.isEmpty()) {
/* 176 */       return "notfound";
/*     */     }
/* 178 */     PATH = "perm_" + PATH;
/*     */     
/* 180 */     if (!this.CONFIG_MAP.containsKey(PATH)) {
/* 181 */       return "notfound";
/*     */     }
/* 183 */     return ((IKey)this.CONFIG_MAP.get(PATH)).S;
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\config\IConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */