/*     */ package me.furnace.manager.data;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import me.furnace.IMain;
/*     */ import me.furnace.manager.furnace.IFurnace;
/*     */ import me.furnace.manager.menu.menu.IMenu;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.ClickType;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.scheduler.BukkitTask;
/*     */ 
/*     */ public class IData implements Listener {
/*     */   protected String N;
/*     */   private IDataT TICKER;
/*     */   public Inventory MENU;
/*  30 */   protected int PAGE = 1;
/*  31 */   protected int START = 0;
/*     */   
/*     */   private IDataM MECHANICS;
/*     */   protected IMenu MENU_DATA;
/*     */   public OfflinePlayer OWNER;
/*  36 */   public List<String> FURNACES_IDS = new ArrayList<>(); public BukkitTask MENU_TASK; public BukkitTask VIEWERS_TASK; public BukkitTask CONTENT_TASK;
/*  37 */   public Map<String, IFurnace> FURNACES = new HashMap<>();
/*  38 */   protected List<ClickType> TYPES = Arrays.asList(new ClickType[] { ClickType.LEFT, ClickType.RIGHT });
/*     */   
/*     */   public IData(OfflinePlayer OFF) {
/*  41 */     if (OFF == null) {
/*     */       return;
/*     */     }
/*  44 */     this.OWNER = OFF;
/*  45 */     this.TICKER = new IDataT(this);
/*  46 */     this.MECHANICS = new IDataM(this);
/*  47 */     this.MENU_DATA = IMain.MENUS.MAIN_MENU;
/*  48 */     this.N = this.OWNER.getName().toLowerCase();
/*  49 */     this.MENU = IMain.S.createInventory(null, this.MENU_DATA.MENU_SIZE, 
/*  50 */         this.MENU_DATA.MENU_NAME.replace("%TARGET%", this.OWNER.getName()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start() {
/*  61 */     this.TICKER.start();
/*  62 */     this.MECHANICS.start();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() {
/*  68 */     this.TICKER.stop();
/*  69 */     this.MECHANICS.stop();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean open_menu(Player USER) {
/*  77 */     if (USER == null) {
/*  78 */       return false;
/*     */     }
/*  80 */     if (USER.getName().equals(this.OWNER.getName())) {
/*  81 */       IMain.DATA.ADMINS.remove(USER);
/*     */     } else {
/*  83 */       IMain.DATA.ADMINS.add(USER);
/*     */     } 
/*  85 */     if (!USER.hasPermission(IMain.UTILS.PERM(USER, "menu"))) {
/*  86 */       IMain.UTILS.sendEffect(USER, "noperm");
/*  87 */       IMain.VERSION.title_send(USER, IMain.VARS.M((OfflinePlayer)USER, "title"), IMain.VARS.M((OfflinePlayer)USER, "noperm"));
/*  88 */       return false;
/*     */     } 
/*  90 */     tick_menu();
/*  91 */     USER.openInventory(this.MENU);
/*  92 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean tick_menu() {
/*  97 */     int S = 0;
/*  98 */     int FINAL = this.START + this.MENU_DATA.EMPTY_SLOTS.size();
/*     */     
/* 100 */     for (Iterator<Integer> iterator = this.MENU_DATA.EMPTY_SLOTS.iterator(); iterator.hasNext(); ) { int I = ((Integer)iterator.next()).intValue();
/* 101 */       this.MENU.setItem(I, IMain.EMPTY_ITEM); }
/*     */     
/* 103 */     if (FINAL > this.FURNACES.size()) {
/* 104 */       FINAL = this.FURNACES.size();
/*     */     }
/* 106 */     for (int I1 = this.START; I1 < FINAL; I1++) {
/* 107 */       String ID = this.FURNACES_IDS.get(I1);
/* 108 */       IFurnace F = this.FURNACES.get(ID);
/*     */       
/* 110 */       this.MENU.setItem(((Integer)this.MENU_DATA.EMPTY_SLOTS.get(S)).intValue(), F.HEAD);
/* 111 */       S++;
/*     */     } 
/* 113 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IFurnace add(String ID) {
/* 120 */     if (ID == null || ID.isEmpty()) {
/* 121 */       return null;
/*     */     }
/* 123 */     IFurnace M = new IFurnace(ID, this.OWNER);
/*     */     
/* 125 */     M.TICKER.start();
/* 126 */     this.FURNACES.put(ID, M);
/* 127 */     this.FURNACES_IDS.add(ID);
/* 128 */     return M;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean remove(String ID) {
/* 133 */     if (ID == null || ID.isEmpty()) {
/* 134 */       return false;
/*     */     }
/* 136 */     if (!this.FURNACES.containsKey(ID)) {
/* 137 */       return false;
/*     */     }
/* 139 */     IFurnace M = this.FURNACES.get(ID);
/* 140 */     List<HumanEntity> L = new ArrayList<>();
/*     */     
/* 142 */     M.TICKER.stop();
/* 143 */     for (HumanEntity VIEWER : M.VIEWERS) {
/* 144 */       L.add(VIEWER);
/*     */     }
/* 146 */     for (HumanEntity VIEWER : L) {
/* 147 */       Player P = (Player)VIEWER;
/*     */       
/* 149 */       VIEWER.closeInventory();
/* 150 */       IMain.UTILS.sendEffect(P, "delete");
/* 151 */       IMain.VERSION.actionbar_send(P, IMain.VARS.M((OfflinePlayer)P, "furnace_delete", M));
/*     */     } 
/* 153 */     this.FURNACES.remove(ID);
/* 154 */     this.FURNACES_IDS.remove(ID);
/* 155 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean autosave() {
/* 162 */     File FILE = new File(IMain.PL.getDataFolder(), "Data" + File.separator + this.N + ".dat");
/*     */     
/* 164 */     if (FILE.exists()) {
/* 165 */       FILE.delete();
/*     */     }
/* 167 */     if (this.FURNACES.isEmpty()) {
/* 168 */       return false;
/*     */     }
/* 170 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(FILE);
/*     */     
/* 172 */     for (String ID : this.FURNACES.keySet()) {
/* 173 */       IFurnace M = this.FURNACES.get(ID);
/*     */       
/* 175 */       M.save((FileConfiguration)yamlConfiguration);
/*     */     } 
/*     */     try {
/* 178 */       yamlConfiguration.save(FILE);
/* 179 */     } catch (IOException iOException) {}
/*     */     
/* 181 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean save() {
/* 186 */     File FILE = new File(IMain.PL.getDataFolder(), "Data" + File.separator + this.N + ".dat");
/*     */     
/* 188 */     if (FILE.exists()) {
/* 189 */       FILE.delete();
/*     */     }
/* 191 */     if (this.FURNACES.isEmpty()) {
/* 192 */       return false;
/*     */     }
/* 194 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(FILE);
/*     */     
/* 196 */     for (String ID : this.FURNACES.keySet()) {
/* 197 */       IFurnace M = this.FURNACES.get(ID);
/*     */       
/* 199 */       M.save((FileConfiguration)yamlConfiguration);
/* 200 */       M.TICKER.stop();
/*     */     } 
/*     */     try {
/* 203 */       yamlConfiguration.save(FILE);
/* 204 */     } catch (IOException iOException) {}
/*     */     
/* 206 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean load() {
/* 211 */     File FILE = new File(IMain.PL.getDataFolder(), "Data" + File.separator + this.N + ".dat");
/*     */     
/* 213 */     if (!FILE.exists()) {
/* 214 */       return false;
/*     */     }
/* 216 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(FILE);
/* 217 */     Set<String> IDS = yamlConfiguration.getKeys(false);
/*     */     
/* 219 */     if (IDS.size() <= 0) {
/* 220 */       FILE.delete();
/* 221 */       return false;
/*     */     } 
/* 223 */     for (String ID : IDS) {
/* 224 */       IFurnace M = new IFurnace(ID, this.OWNER);
/* 225 */       ConfigurationSection SE = yamlConfiguration.getConfigurationSection(ID);
/*     */       
/* 227 */       if (M.load(SE)) {
/* 228 */         this.FURNACES.put(ID, M);
/* 229 */         this.FURNACES_IDS.add(ID);
/* 230 */         M.TICKER.start();
/*     */       } 
/*     */     } 
/* 233 */     FILE.delete();
/* 234 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\manager\data\IData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */