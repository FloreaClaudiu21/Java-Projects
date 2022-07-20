/*     */ package me.furnace.manager.furnace;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import me.furnace.IMain;
/*     */ import me.furnace.XMaterial;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.SkullMeta;
/*     */ import org.bukkit.scheduler.BukkitTask;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IFurnace
/*     */ {
/*     */   public String ID;
/*     */   public Object TILE;
/*     */   public ITick TICKER;
/*     */   public ItemStack HEAD;
/*     */   public SkullMeta HEAD_META;
/*     */   public OfflinePlayer OWNER;
/*     */   private FileConfiguration CO;
/*  30 */   public List<String> NAMES = Arrays.asList(new String[] { "Davenator911", "MCadir1" }); private ConfigurationSection SE; public List<HumanEntity> VIEWERS; public String SMELTING_C; public String FUEL_C; public String RESULT_C; public BukkitTask VIEWERS_TASK;
/*     */   public BukkitTask FURNACE_TASK;
/*  32 */   public int BURNTIME = 0, COOKTIME = 0, COOKTIME_TOTAL = 200, SMELTING_A = 0, RESULT_A = 0, I1 = 0, I = 0;
/*     */ 
/*     */   
/*     */   public IFurnace(String ID, OfflinePlayer OWNER) {
/*  36 */     this.ID = ID;
/*  37 */     this.OWNER = OWNER;
/*  38 */     this.FUEL_C = IMain.EMPTY;
/*  39 */     this.RESULT_C = IMain.EMPTY;
/*  40 */     this.SMELTING_C = IMain.EMPTY;
/*  41 */     this.TICKER = new ITick(this);
/*  42 */     this.TILE = IMain.VERSION.create_tile();
/*  43 */     this.HEAD = XMaterial.PLAYER_HEAD.parseItem(1);
/*  44 */     this.HEAD_META = (SkullMeta)this.HEAD.getItemMeta();
/*  45 */     this.HEAD_META.setOwner(this.NAMES.get(0));
/*  46 */     this.HEAD_META.setLore(Arrays.asList(new String[] { "" }));
/*  47 */     this.HEAD_META.setDisplayName(IMain.VARS.EMPTY);
/*  48 */     this.HEAD.setItemMeta((ItemMeta)this.HEAD_META);
/*     */     try {
/*  50 */       this.VIEWERS = (List<HumanEntity>)IMain.VERSION.FURNACE_TILE_VIEWERS_METHOD.invoke(this.TILE, new Object[0]);
/*  51 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack smelting() throws Exception {
/*  63 */     Object ITEM = IMain.VERSION.FURNACE_TILE_GETITEM_METHOD.invoke(this.TILE, new Object[] { Integer.valueOf(0) });
/*  64 */     return (ItemStack)IMain.VERSION.CITEMSTACK_BUKKIT_METHOD.invoke(null, new Object[] { ITEM });
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack fuel() throws Exception {
/*  69 */     Object ITEM = IMain.VERSION.FURNACE_TILE_GETITEM_METHOD.invoke(this.TILE, new Object[] { Integer.valueOf(1) });
/*  70 */     return (ItemStack)IMain.VERSION.CITEMSTACK_BUKKIT_METHOD.invoke(null, new Object[] { ITEM });
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack result() throws Exception {
/*  75 */     Object ITEM = IMain.VERSION.FURNACE_TILE_GETITEM_METHOD.invoke(this.TILE, new Object[] { Integer.valueOf(2) });
/*  76 */     return (ItemStack)IMain.VERSION.CITEMSTACK_BUKKIT_METHOD.invoke(null, new Object[] { ITEM });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean open_container(Player VIEW) {
/*  83 */     if (VIEW == null) {
/*  84 */       return false;
/*     */     }
/*  86 */     if (!VIEW.hasPermission(IMain.CONFIG.perm("open"))) {
/*  87 */       IMain.VERSION.actionbar_send(VIEW, IMain.VARS.M((OfflinePlayer)VIEW, "noperm"));
/*  88 */       return false;
/*     */     } 
/*     */     try {
/*  91 */       Object EP = IMain.VERSION.PLAYER_HANDLE_METHOD.invoke(VIEW, new Object[0]);
/*  92 */       IMain.VERSION.OPENCONTAINER_METHOD.invoke(EP, new Object[] { this.TILE });
/*  93 */       Object CT = IMain.VERSION.ACTIVECONTAINER_FIELD.get(EP);
/*  94 */       IMain.VERSION.REACH_FIELD.set(CT, Boolean.valueOf(false));
/*  95 */     } catch (Exception e) {
/*  96 */       e.printStackTrace();
/*     */     } 
/*  98 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean keys() {
/* 105 */     if (this.SE == null) {
/* 106 */       return false;
/*     */     }
/* 108 */     Set<String> KEYS = this.SE.getKeys(false); byte b; int i;
/*     */     PATHS[] arrayOfPATHS;
/* 110 */     for (i = (arrayOfPATHS = PATHS.values()).length, b = 0; b < i; ) { PATHS KEY = arrayOfPATHS[b];
/* 111 */       if (!KEYS.contains(KEY.K)) {
/* 112 */         IMain.UTILS.console("&7>> &cUser key &b&l" + KEY.K + 
/* 113 */             " &cdoes not exist in the furnace config with ID &6#" + this.ID);
/* 114 */         return false;
/*     */       } 
/* 116 */       String V = this.SE.getString(KEY.K);
/*     */       
/* 118 */       if (V == null || V.isEmpty()) {
/* 119 */         IMain.UTILS.console("&7>> &cUser key &b&l" + KEY.K + 
/* 120 */             " &cdoes not exist in the furnace config with ID &6#" + this.ID);
/* 121 */         return false;
/*     */       }  b++; }
/*     */     
/* 124 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void s(String PATH, Object VALUE) {
/* 129 */     if (this.CO == null || PATH == null || VALUE == null || PATH.isEmpty()) {
/*     */       return;
/*     */     }
/* 132 */     this.CO.set(String.valueOf(this.ID) + "." + PATH, VALUE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean load(ConfigurationSection SE) {
/* 141 */     if (SE == null) {
/* 142 */       return false;
/*     */     }
/* 144 */     this.SE = SE;
/* 145 */     if (!keys()) {
/* 146 */       return false;
/*     */     }
/*     */     try {
/* 149 */       this.OWNER = IMain.S.getOfflinePlayer(this.SE.getString("OWNER"));
/* 150 */       this.BURNTIME = this.SE.getInt("BURNTIME");
/* 151 */       this.COOKTIME = this.SE.getInt("COOKTIME");
/* 152 */       this.COOKTIME_TOTAL = this.SE.getInt("COOKTIMETOTAL");
/* 153 */       this.SMELTING_C = this.SE.getString("SMELTING_C");
/* 154 */       this.FUEL_C = this.SE.getString("FUEL_C");
/* 155 */       this.RESULT_C = this.SE.getString("RESULT_C");
/*     */       
/* 157 */       Object FUEL = IFurnaceU.deserialize(this.SE.getConfigurationSection("FUEL"));
/* 158 */       Object RESULT = IFurnaceU.deserialize(this.SE.getConfigurationSection("RESULT"));
/* 159 */       Object SMELTING = IFurnaceU.deserialize(this.SE.getConfigurationSection("SMELTING"));
/*     */       
/* 161 */       IMain.VERSION.FURNACE_TILE_SETITEM_METHOD.invoke(this.TILE, new Object[] { Integer.valueOf(1), 
/* 162 */             IMain.VERSION.CITEMSTACK_NMS_METHOD.invoke(null, new Object[] { FUEL }) });
/* 163 */       IMain.VERSION.FURNACE_TILE_SETITEM_METHOD.invoke(this.TILE, new Object[] { Integer.valueOf(0), 
/* 164 */             IMain.VERSION.CITEMSTACK_NMS_METHOD.invoke(null, new Object[] { SMELTING }) });
/* 165 */       IMain.VERSION.FURNACE_TILE_SETITEM_METHOD.invoke(this.TILE, new Object[] { Integer.valueOf(2), 
/* 166 */             IMain.VERSION.CITEMSTACK_NMS_METHOD.invoke(null, new Object[] { RESULT }) });
/* 167 */     } catch (Exception exception) {}
/*     */     
/* 169 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean save(FileConfiguration CO) {
/* 174 */     if (CO == null) {
/* 175 */       return false;
/*     */     }
/* 177 */     this.CO = CO;
/*     */     
/*     */     try {
/* 180 */       s("OWNER", this.OWNER.getName());
/* 181 */       s("SMELTING_C", this.SMELTING_C);
/* 182 */       s("FUEL_C", this.FUEL_C);
/* 183 */       s("RESULT_C", this.RESULT_C);
/* 184 */       s("BURNTIME", Integer.valueOf(this.BURNTIME));
/* 185 */       s("COOKTIME", Integer.valueOf(this.COOKTIME));
/* 186 */       s("COOKTIMETOTAL", Integer.valueOf(this.COOKTIME_TOTAL));
/* 187 */       s("FUEL", IFurnaceU.serialize(fuel()));
/* 188 */       s("RESULT", IFurnaceU.serialize(result()));
/* 189 */       s("SMELTING", IFurnaceU.serialize(smelting()));
/* 190 */     } catch (Exception exception) {}
/*     */     
/* 192 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private enum PATHS
/*     */   {
/* 199 */     OWNER("OWNER"), SMELTING_C("SMELTING_C"), FUEL_C("FUEL_C"), RESULT_C("RESULT_C"), BURNTIME("BURNTIME"),
/* 200 */     COOKTIME("COOKTIME"), SMELTING("SMELTING"), FUEL("FUEL"), RESULT("RESULT");
/*     */     public String K;
/*     */     
/*     */     PATHS(String VALUE) {
/* 204 */       this.K = VALUE;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\manager\furnace\IFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */