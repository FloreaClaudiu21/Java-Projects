/*     */ package me.furnace;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.furnace.config.IConfig;
/*     */ import me.furnace.manager.IBar;
/*     */ import me.furnace.manager.data.IDatabase;
/*     */ import me.furnace.manager.menu.IMenus;
/*     */ import me.furnace.manager.recipe.IRecipes;
/*     */ import me.furnace.manager.recipe.menu.IRecipeMenu;
/*     */ import me.furnace.update.IUpdate;
/*     */ import me.furnace.utils.ICooldown;
/*     */ import me.furnace.utils.IUtils;
/*     */ import me.furnace.utils.IVars;
/*     */ import me.furnace.version.IVersion;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.command.ConsoleCommandSender;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.PluginDescriptionFile;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ import org.bukkit.scheduler.BukkitScheduler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IMain
/*     */   extends JavaPlugin
/*     */ {
/*     */   public static Plugin PL;
/*     */   public static IVars VARS;
/*     */   public static IBar BOSSBAR;
/*     */   public static IUtils UTILS;
/*     */   public static IDatabase DATA;
/*     */   public static IMenus MENUS;
/*  40 */   public static int VEID = 0;
/*     */   public static IConfig CONFIG;
/*     */   public static IVersion VERSION;
/*     */   public static IRecipes RECIPES;
/*  44 */   public static int MAX_CHAR = 50;
/*     */   public static ICooldown COOLDOWN;
/*     */   public static Material EMPTY_AIR;
/*     */   public static boolean ERROR = false;
/*     */   public static boolean NEWVERSION = false;
/*     */   public static boolean PLACEHOLDER = false;
/*     */   public static IRecipeMenu RECIPE_CREATOR;
/*  51 */   public static Server S = Bukkit.getServer(); public static ItemStack EMPTY_ITEM;
/*     */   public static ItemStack GLASS_ITEM;
/*  53 */   public static PluginDescriptionFile DESC = null;
/*  54 */   public static BukkitScheduler SC = S.getScheduler();
/*  55 */   public static PluginManager PM = S.getPluginManager(); public static String TRUE; public static String FALSE;
/*  56 */   public static String EMPTY = "None"; public static String RED;
/*  57 */   public static String PR = "&8| &3&lPortableFurnace&r &8| &r"; public static String RESET; public static String PUR;
/*  58 */   public static ConsoleCommandSender CONSOLE = S.getConsoleSender();
/*  59 */   public static List<ItemStack> GLASS_MATERIAL = new ArrayList<>();
/*  60 */   public static String VE = S.getClass().getPackage().getName().split("\\.")[3];
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  64 */     PL = (Plugin)this;
/*  65 */     UTILS = new IUtils();
/*  66 */     DESC = PL.getDescription();
/*     */     
/*  68 */     UTILS.load();
/*  69 */     if (ERROR) {
/*     */       return;
/*     */     }
/*  72 */     CONFIG.load();
/*  73 */     MENUS.load();
/*  74 */     DATA.load();
/*     */ 
/*     */     
/*  77 */     RECIPES.load();
/*  78 */     BOSSBAR.load();
/*  79 */     COOLDOWN.start();
/*     */     
/*  81 */     IUpdate U = new IUpdate();
/*  82 */     RECIPE_CREATOR = new IRecipeMenu();
/*     */     
/*  84 */     if (U.NEWVERSION) {
/*  85 */       UTILS.console("&dIt seems you are using a older version of portablefurnace plugin, new version available &c" + 
/*  86 */           U.NEWVERSION_S, true);
/*     */     }
/*  88 */     SC.runTaskLaterAsynchronously(PL, new Runnable() {
/*     */           public void run() {
/*  90 */             Plugin PL = IMain.PM.getPlugin("PlaceholderAPI");
/*     */             
/*  92 */             if (PL == null || !PL.isEnabled()) {
/*     */               return;
/*     */             }
/*  95 */             IMain.PLACEHOLDER = true;
/*  96 */             IMain.UTILS.console("&6&lFound PlaceholderAPI support, using the plugin for placeholders.", true);
/*     */           }
/*  98 */         }20L);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDisable() {
/* 104 */     UTILS.print();
/*     */     
/* 106 */     if (ERROR) {
/*     */       return;
/*     */     }
/* 109 */     DATA.save();
/* 110 */     RECIPES.save();
/* 111 */     COOLDOWN.stop();
/* 112 */     SC.cancelTasks((Plugin)this);
/* 113 */     CONFIG.CONFIG_MAP.clear();
/* 114 */     HandlerList.unregisterAll(PL);
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\IMain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */