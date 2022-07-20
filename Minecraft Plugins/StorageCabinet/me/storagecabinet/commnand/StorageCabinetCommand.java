/*     */ package me.storagecabinet.commnand;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import me.storagecabinet.StorageCabinet;
/*     */ import me.storagecabinet.manager.cabinet.Cabinet;
/*     */ import me.storagecabinet.manager.gui.MainGUI;
/*     */ import me.storagecabinet.utils.Utils;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.command.TabCompleter;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StorageCabinetCommand
/*     */   implements CommandExecutor, TabCompleter
/*     */ {
/*     */   public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
/*  28 */     if (args.length == 1) {
/*  29 */       return Arrays.asList(new String[] { "menu", "get", "getid", "delete", "locate", "preview", "reload" });
/*     */     }
/*  31 */     if (args.length == 2) {
/*  32 */       List<String> IDS = new ArrayList<>();
/*     */       
/*  34 */       StorageCabinet.STORAGE.CABINETS.keySet().forEach(K -> paramList.add(K));
/*     */ 
/*     */       
/*  37 */       return IDS;
/*     */     } 
/*  39 */     if (args.length > 2) {
/*  40 */       return Arrays.asList(new String[] { "" });
/*     */     }
/*  42 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
/*  47 */     if (!(sender instanceof Player)) {
/*  48 */       return false;
/*     */     }
/*  50 */     Player P = (Player)sender;
/*  51 */     String W = P.getWorld().getName().toLowerCase();
/*     */     
/*  53 */     if (args.length == 0) {
/*  54 */       StorageCabinet.UTILS.send_msgL(P, "messages.usage");
/*  55 */       return true;
/*     */     } 
/*  57 */     if (args.length == 1) {
/*  58 */       Block B; Cabinet C; Block B1; Cabinet C1; Inventory CI; String arg = args[0];
/*     */       
/*  60 */       if (arg == null || arg.isEmpty()) {
/*  61 */         return false;
/*     */       }
/*  63 */       arg = arg.toLowerCase();
/*     */       String str1;
/*  65 */       switch ((str1 = arg).hashCode()) { case -934641255: if (!str1.equals("reload")) {
/*     */             break;
/*     */           }
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
/*  78 */           if (!P.hasPermission(StorageCabinet.PERM_MANAGER.COMMAND_RELOAD)) {
/*  79 */             StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.noperm_cmd"));
/*  80 */             return false;
/*     */           } 
/*  82 */           StorageCabinet.SETTINGS.reload();
/*  83 */           StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.reload"));
/*  84 */           return true;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case -318184504:
/*     */           if (!str1.equals("preview")) {
/*     */             break;
/*     */           }
/* 106 */           if (!P.hasPermission(StorageCabinet.PERM_MANAGER.COMMAND_PREVIEW)) {
/* 107 */             StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.noperm_cmd"));
/* 108 */             return false;
/*     */           } 
/* 110 */           if (((List)StorageCabinet.SETTINGS.LIST_MAP.get("disabled_worlds")).contains(W)) {
/* 111 */             StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("disabled_world"));
/* 112 */             return false;
/*     */           } 
/* 114 */           B1 = P.getTargetBlock(null, 3);
/*     */           
/* 116 */           if (!StorageCabinet.CABINET_MANAGER.is_cabinet_block(B1)) {
/* 117 */             StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.nocabinet"));
/* 118 */             return false;
/*     */           } 
/* 120 */           C1 = StorageCabinet.CABINET_MANAGER.get_cabinet_by_block(B1);
/* 121 */           CI = C1.IN_L.get(0);
/*     */           
/* 123 */           P.openInventory(CI);
/* 124 */           StorageCabinet.STORAGE.PREVIEW.add(P);
/* 125 */           P.playSound(P.getLocation(), Sound.BLOCK_CHEST_OPEN, 1.0F, 0.8F);
/* 126 */           StorageCabinet.UTILS.send_msg(P, (
/* 127 */               (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.previewcabinet")).replace("{ID}", C1.ID));
/* 128 */           return true;case 3347807: if (!str1.equals("menu"))
/*     */             break;  if (!P.hasPermission(StorageCabinet.PERM_MANAGER.COMMAND_MENU)) { StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.noperm_cmd")); return false; }  if (((List)StorageCabinet.SETTINGS.LIST_MAP.get("disabled_worlds")).contains(W)) { StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("disabled_world")); return false; }  MainGUI.open_main_gui(P); return true;case 98246385: if (!str1.equals("getid"))
/* 130 */             break;  if (!P.hasPermission(StorageCabinet.PERM_MANAGER.COMMAND_GETID)) { StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.noperm_cmd")); return false; }  if (((List)StorageCabinet.SETTINGS.LIST_MAP.get("disabled_worlds")).contains(W)) { StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("disabled_world")); return false; }  B = P.getTargetBlock(null, 3); if (!StorageCabinet.CABINET_MANAGER.is_cabinet_block(B)) { StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.nocabinet")); return false; }  C = StorageCabinet.CABINET_MANAGER.get_cabinet_by_block(B); StorageCabinet.UTILS.send_msg(P, ((String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.idcabinet")).replace("{ID}", C.ID)); return true; }  StorageCabinet.UTILS.send_msgL(P, "messages.usage");
/* 131 */       return true;
/*     */     } 
/*     */     
/* 134 */     if (args.length >= 2) {
/* 135 */       Cabinet C1; Inventory CI; Cabinet C; List<String> DET; Cabinet C2, C3; String MSG, arg = args[0];
/* 136 */       String ID = args[1];
/*     */       
/* 138 */       if (arg == null || arg.isEmpty()) {
/* 139 */         return false;
/*     */       }
/* 141 */       arg = arg.toLowerCase();
/*     */       String str1;
/* 143 */       switch ((str1 = arg).hashCode()) { case -1335458389: if (!str1.equals("delete")) {
/*     */             break;
/*     */           }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 230 */           if (!P.hasPermission(StorageCabinet.PERM_MANAGER.COMMAND_DELETE)) {
/* 231 */             StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.noperm_cmd"));
/* 232 */             return false;
/*     */           } 
/* 234 */           if (((List)StorageCabinet.SETTINGS.LIST_MAP.get("disabled_worlds")).contains(W)) {
/* 235 */             StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("disabled_world"));
/* 236 */             return false;
/*     */           } 
/* 238 */           if (ID == null || ID.isEmpty()) {
/* 239 */             StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.specifyid"));
/* 240 */             return false;
/*     */           } 
/* 242 */           if (!StorageCabinet.STORAGE.CABINETS.containsKey(ID)) {
/* 243 */             StorageCabinet.UTILS.send_msg(P, (
/* 244 */                 (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.idnotfound")).replace("{ID}", ID));
/* 245 */             return false;
/*     */           } 
/* 247 */           C3 = (Cabinet)StorageCabinet.STORAGE.CABINETS.get(ID);
/* 248 */           MSG = ((String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.deletecabinet")).replace("{ID}", C3.ID);
/*     */           
/* 250 */           MSG = StorageCabinet.UTILS.send_msg(P, MSG);
/* 251 */           StorageCabinet.CONSOLE.sendMessage(MSG);
/* 252 */           Utils.send_sound(P.getWorld(), P.getLocation());
/* 253 */           StorageCabinet.CABINET_MANAGER.delete_cabinet_from_database(C3);
/* 254 */           return true;case -1097461934: if (!str1.equals("locate")) break;  if (!P.hasPermission(StorageCabinet.PERM_MANAGER.COMMAND_LOCATE)) { StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.noperm_cmd")); return false; }  if (((List)StorageCabinet.SETTINGS.LIST_MAP.get("disabled_worlds")).contains(W)) { StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("disabled_world")); return false; }  if (ID == null || ID.isEmpty()) { StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.specifyid")); return false; }  if (!StorageCabinet.STORAGE.CABINETS.containsKey(ID)) { StorageCabinet.UTILS.send_msg(P, ((String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.idnotfound")).replace("{ID}", ID)); return false; }  C = (Cabinet)StorageCabinet.STORAGE.CABINETS.get(ID); if (C.LOC == null) { StorageCabinet.UTILS.send_msg(P, ((String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.notplaced")).replace("{ID}", ID)); return false; }  DET = new ArrayList<>(); DET.add("Data: " + C.DATA); DET.add("Type: " + C.TYPE.toString()); DET.add("World: " + C.LOC.getWorld().getName()); DET.add("Block X: " + C.LOC.getBlockX()); DET.add("Block Y: " + C.LOC.getBlockY()); DET.add("Block Z: " + C.LOC.getBlockZ()); StorageCabinet.UTILS.send_msg(P, ((String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.loccabinet")).replace("{ID}", C.ID).replace("{LOC}", DET.toString().replace("[", "").replace("]", ""))); return true;case -318184504: if (!str1.equals("preview"))
/*     */             break;  if (!P.hasPermission(StorageCabinet.PERM_MANAGER.COMMAND_PREVIEW)) { StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.noperm_cmd")); return false; }  if (((List)StorageCabinet.SETTINGS.LIST_MAP.get("disabled_worlds")).contains(W)) { StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("disabled_world")); return false; }  if (ID == null || ID.isEmpty()) { StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.specifyid")); return false; }  if (!StorageCabinet.STORAGE.CABINETS.containsKey(ID)) { StorageCabinet.UTILS.send_msg(P, ((String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.idnotfound")).replace("{ID}", ID)); return false; }  C1 = (Cabinet)StorageCabinet.STORAGE.CABINETS.get(ID); CI = C1.IN_L.get(0); P.openInventory(CI); StorageCabinet.STORAGE.PREVIEW.add(P); P.playSound(P.getLocation(), Sound.BLOCK_CHEST_OPEN, 1.0F, 0.8F); StorageCabinet.UTILS.send_msg(P, ((String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.previewcabinet")).replace("{ID}", C1.ID)); return true;case 102230: if (!str1.equals("get"))
/* 256 */             break;  if (!P.hasPermission(StorageCabinet.PERM_MANAGER.COMMAND_GET)) { StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.noperm_cmd")); return false; }  if (((List)StorageCabinet.SETTINGS.LIST_MAP.get("disabled_worlds")).contains(W)) { StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("disabled_world")); return false; }  if (ID == null || ID.isEmpty()) { StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.specifyid")); return false; }  if (!StorageCabinet.STORAGE.CABINETS.containsKey(ID)) { StorageCabinet.UTILS.send_msg(P, ((String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.idnotfound")).replace("{ID}", ID)); return false; }  C2 = (Cabinet)StorageCabinet.STORAGE.CABINETS.get(ID); MainGUI.give_cabinet_with_id(P, C2.ITEM); return true; }  StorageCabinet.UTILS.send_msgL(P, "messages.usage");
/* 257 */       return true;
/*     */     } 
/*     */     
/* 260 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\commnand\StorageCabinetCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */