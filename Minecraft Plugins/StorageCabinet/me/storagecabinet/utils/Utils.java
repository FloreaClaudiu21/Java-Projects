/*     */ package me.storagecabinet.utils;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.storagecabinet.StorageCabinet;
/*     */ import me.storagecabinet.manager.cabinet.Cabinet;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Utils
/*     */ {
/*     */   public String color(String MSG) {
/*  23 */     return ChatColor.translateAlternateColorCodes('&', MSG);
/*     */   }
/*     */ 
/*     */   
/*     */   public String decolor(String MSG) {
/*  28 */     return ChatColor.stripColor(MSG);
/*     */   }
/*     */ 
/*     */   
/*     */   public void console(String MSG) {
/*  33 */     StorageCabinet.CONSOLE.sendMessage(color(MSG));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void debug(String MSG, boolean C) {
/*  39 */     if (C && !((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("debug")).booleanValue()) {
/*     */       return;
/*     */     }
/*  42 */     StorageCabinet.CONSOLE.sendMessage("[StorageCabinet] " + MSG);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void send_sound(World W, Location L) {
/*  48 */     Sound S = null;
/*     */     try {
/*  50 */       S = Sound.valueOf("BLOCK_AMETHYST_BLOCK_BREAK");
/*  51 */     } catch (Exception e) {
/*     */       return;
/*     */     } 
/*  54 */     if (S != null) {
/*  55 */       W.playSound(L, S, 1.0F, 0.8F);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String vars(String MSG, Cabinet CABINET) {
/*  62 */     if (MSG == null || CABINET == null) {
/*  63 */       return "";
/*     */     }
/*  65 */     String NM = color(MSG);
/*     */     
/*  67 */     NM = NM.replace("{ID}", CABINET.ID).replace("{TYPE}", CABINET.TYPE.name())
/*  68 */       .replace("{SLOTS}", (new StringBuilder(String.valueOf(CABINET.max_slots()))).toString()).replace("{FREE_SLOTS}", (new StringBuilder(String.valueOf(CABINET.free_slots()))).toString())
/*  69 */       .replace("{FREE_SPACE}", (new StringBuilder(String.valueOf(CABINET.free_space()))).toString());
/*  70 */     return NM;
/*     */   }
/*     */ 
/*     */   
/*     */   public String send_msg(Player P, String MSG) {
/*  75 */     if (P == null || MSG == null) {
/*  76 */       return "";
/*     */     }
/*  78 */     String PREFIX = (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.prefix");
/*     */     
/*  80 */     MSG = color(MSG).replace("{PREFIX}", PREFIX);
/*  81 */     P.sendMessage(MSG);
/*  82 */     return MSG;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean send_msgL(Player P, String PATH) {
/*  87 */     if (P == null || PATH == null || PATH.isEmpty()) {
/*  88 */       return false;
/*     */     }
/*  90 */     List<String> LIST = (List<String>)StorageCabinet.SETTINGS.LIST_MAP.get(PATH);
/*  91 */     if (!LIST.isEmpty()) {
/*  92 */       LIST.forEach(M -> paramPlayer.sendMessage(color(M)));
/*     */     }
/*     */ 
/*     */     
/*  96 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> vars_L(List<String> L, Cabinet CABINET) {
/* 101 */     List<String> NL = new ArrayList<>();
/* 102 */     if (L == null || CABINET == null || L.isEmpty()) {
/* 103 */       return NL;
/*     */     }
/* 105 */     L.forEach(S -> paramList.add(vars(S, paramCabinet)));
/*     */ 
/*     */     
/* 108 */     return NL;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reload_player_list() {
/*     */     Class<?> CLASS;
/* 114 */     String V = StorageCabinet.VER;
/*     */     
/*     */     try {
/* 117 */       CLASS = Class.forName("net.minecraft.server." + V + ".MinecraftServer");
/* 118 */     } catch (ClassNotFoundException EX) {
/*     */       try {
/* 120 */         CLASS = Class.forName("net.minecraft.server.MinecraftServer");
/* 121 */       } catch (ClassNotFoundException EX1) {
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     try {
/* 126 */       Method M = CLASS.getMethod("getServer", new Class[0]);
/* 127 */       Object O = M.invoke(null, new Object[0]);
/* 128 */       Method M1 = M.getReturnType().getMethod("getPlayerList", new Class[0]);
/* 129 */       Object O1 = M1.invoke(O, new Object[0]);
/* 130 */       Method M2 = M1.getReturnType().getMethod("reload", new Class[0]);
/*     */       
/* 132 */       M2.invoke(O1, new Object[0]);
/* 133 */       StorageCabinet.UTILS.debug("PlayerList have been reloaded", false);
/*     */       return;
/* 135 */     } catch (Exception EX2) {
/* 136 */       StorageCabinet.UTILS.debug(
/* 137 */           "Exception ocurred when trying to reload the playerlist/Exception:" + EX2.getMessage(), false);
/*     */       return;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabine\\utils\Utils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */