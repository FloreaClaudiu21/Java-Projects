/*     */ package me.storagecabinet;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import me.storagecabinet.commnand.StorageCabinetCommand;
/*     */ import me.storagecabinet.manager.cabinet.CabinetManager;
/*     */ import me.storagecabinet.manager.events.EventManager;
/*     */ import me.storagecabinet.manager.gui.GUIManager;
/*     */ import me.storagecabinet.manager.permission.PermissionManager;
/*     */ import me.storagecabinet.manager.recipes.RecipesManager;
/*     */ import me.storagecabinet.manager.scheduler.SchedulerManager;
/*     */ import me.storagecabinet.manager.storage.StorageManager;
/*     */ import me.storagecabinet.settings.Settings;
/*     */ import me.storagecabinet.support.Support;
/*     */ import me.storagecabinet.utils.DefaultSettingsReader;
/*     */ import me.storagecabinet.utils.Utils;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.ConsoleCommandSender;
/*     */ import org.bukkit.command.PluginCommand;
/*     */ import org.bukkit.command.TabCompleter;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.PluginDescriptionFile;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ import org.bukkit.scheduler.BukkitScheduler;
/*     */ 
/*     */ public class StorageCabinet extends JavaPlugin {
/*     */   public static Utils UTILS;
/*     */   public static Plugin PLUGIN;
/*     */   public static String VERSION;
/*     */   public static Support SUPPORT;
/*     */   public static Settings SETTINGS;
/*     */   public static StorageManager STORAGE;
/*     */   public static GUIManager GUI_MANAGER;
/*     */   public static EventManager EVENT_MANAGER;
/*     */   public static DefaultSettingsReader READER;
/*     */   public static PermissionManager PERM_MANAGER;
/*     */   public static CabinetManager CABINET_MANAGER;
/*     */   public static RecipesManager RECIPES_MANAGER;
/*  42 */   public static Server SERVER = Bukkit.getServer();
/*     */   public static SchedulerManager SCHEDULER_MANAGER;
/*  44 */   public static BukkitScheduler SCHEDULER = SERVER.getScheduler();
/*  45 */   public static PluginManager PLUGIN_MANAGER = SERVER.getPluginManager();
/*  46 */   public static ConsoleCommandSender CONSOLE = SERVER.getConsoleSender();
/*  47 */   public static String VER = SERVER.getClass().getPackage().getName().split("\\.")[3];
/*  48 */   public static String RESOURCE_PACK = "https://www.dropbox.com/s/uyk2rd3ctvo4833/StorageCabinet.zip?dl=1";
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*     */     try {
/*  53 */       PLUGIN = (Plugin)this;
/*  54 */       UTILS = new Utils();
/*  55 */       SUPPORT = new Support(this);
/*  56 */       SETTINGS = new Settings(this);
/*  57 */       STORAGE = new StorageManager();
/*  58 */       GUI_MANAGER = new GUIManager((Plugin)this);
/*  59 */       EVENT_MANAGER = new EventManager((Plugin)this);
/*  60 */       PERM_MANAGER = new PermissionManager();
/*  61 */       CABINET_MANAGER = new CabinetManager();
/*  62 */       READER = new DefaultSettingsReader(this);
/*  63 */       SCHEDULER_MANAGER = new SchedulerManager((Plugin)this);
/*  64 */       PluginDescriptionFile DESC = PLUGIN.getDescription();
/*     */       
/*  66 */       VERSION = DESC.getVersion();
/*  67 */       SETTINGS.register();
/*  68 */       STORAGE.register();
/*  69 */       CABINET_MANAGER.register();
/*  70 */       RECIPES_MANAGER = new RecipesManager();
/*  71 */       RECIPES_MANAGER.register();
/*     */       
/*  73 */       StorageCabinetCommand C = new StorageCabinetCommand();
/*  74 */       PluginCommand CMD = getCommand("storagecabinet");
/*  75 */       UTILS.debug("Plugin command have been registered", false);
/*     */       
/*  77 */       CMD.setExecutor((CommandExecutor)C);
/*  78 */       CMD.setTabCompleter((TabCompleter)C);
/*     */       
/*  80 */       GUI_MANAGER.register();
/*  81 */       PERM_MANAGER.register();
/*  82 */       EVENT_MANAGER.register();
/*  83 */       SCHEDULER_MANAGER.register();
/*     */ 
/*     */       
/*  86 */       this.HEADER.forEach(S -> UTILS.console(S.replace("█", "=")));
/*     */ 
/*     */       
/*  89 */       UTILS.console("                   >> VERSION: " + VERSION + " <<");
/*  90 */       UTILS.console(" ");
/*     */       
/*     */       return;
/*  93 */     } catch (Exception EX) {
/*  94 */       this.HEADER.forEach(S -> UTILS.console(S.replace("█", "=")));
/*     */ 
/*     */       
/*  97 */       UTILS.console(" ");
/*  98 */       UTILS.console("&cException: " + EX.getMessage());
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDisable() {
/* 105 */     SCHEDULER_MANAGER.tick_task1();
/*     */     
/* 107 */     STORAGE.unregister();
/* 108 */     SETTINGS.unregister();
/* 109 */     RECIPES_MANAGER.unregister();
/* 110 */     CABINET_MANAGER.unregister();
/*     */     
/* 112 */     GUI_MANAGER.unregister();
/* 113 */     PERM_MANAGER.unregister();
/* 114 */     EVENT_MANAGER.unregister();
/* 115 */     SCHEDULER_MANAGER.unregister();
/* 116 */     PLUGIN = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 121 */   private final List<String> HEADER = Arrays.asList(new String[] { " ", "████████████████████████████████████████████████████████", 
/* 122 */         " ", " ███████  ██████  █████   ██   ███  ██  ██████  ██████", 
/* 123 */         " ██       ██  ██  ██  ██      ██ ██ ██  ██        ██  ", 
/* 124 */         " ██       ██████  █████   ██  ██ ██ ██  ██████    ██  ", 
/* 125 */         " ██       ██  ██  ██  ██  ██  ██ ██ ██  ██        ██  ", 
/* 126 */         " ███████  ██  ██  █████   ██  ██  ███   ██████    ██  ", " ", 
/* 127 */         "████████████████████████████████████████████████████████" });
/*     */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\StorageCabinet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */