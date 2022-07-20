/*     */ package me.storagecabinet.settings;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.io.File;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import me.storagecabinet.StorageCabinet;
/*     */ import me.storagecabinet.manager.ManagerAPI;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Settings
/*     */   implements ManagerAPI
/*     */ {
/*     */   private StorageCabinet SC;
/*     */   public Map<String, Integer> INT_MAP;
/*     */   public Map<String, Boolean> BOOL_MAP;
/*     */   public Map<String, String> STRING_MAP;
/*     */   public Map<String, List<String>> LIST_MAP;
/*     */   private static ImmutableMap<String, Object> IM_KEYS;
/*     */   
/*     */   public Settings(StorageCabinet SC) {
/*  28 */     this.SC = SC;
/*  29 */     this.INT_MAP = new HashMap<>();
/*  30 */     this.BOOL_MAP = new HashMap<>();
/*  31 */     this.STRING_MAP = new HashMap<>();
/*  32 */     this.LIST_MAP = new HashMap<>();
/*     */     
/*  34 */     setup_def_map();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean reload() {
/*  40 */     this.INT_MAP.clear();
/*  41 */     this.BOOL_MAP.clear();
/*  42 */     this.LIST_MAP.clear();
/*  43 */     this.STRING_MAP.clear();
/*     */ 
/*     */     
/*  46 */     for (Player P1 : StorageCabinet.SERVER.getOnlinePlayers()) {
/*  47 */       P1.closeInventory();
/*     */     }
/*  49 */     register();
/*  50 */     StorageCabinet.PERM_MANAGER.register();
/*  51 */     StorageCabinet.RECIPES_MANAGER.unregister();
/*  52 */     StorageCabinet.RECIPES_MANAGER.setup_map();
/*  53 */     StorageCabinet.RECIPES_MANAGER.register();
/*  54 */     StorageCabinet.SCHEDULER_MANAGER.unregister();
/*  55 */     StorageCabinet.SCHEDULER_MANAGER.register();
/*  56 */     StorageCabinet.GUI_MANAGER.MAINGUI.setup_main_gui();
/*  57 */     StorageCabinet.UTILS.debug("Plugin have been reloaded successufully", false);
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean register() {
/*  64 */     StorageCabinet.UTILS.debug("Loading the plugin settings...", false);
/*     */     
/*  66 */     File F1 = new File(this.SC.getDataFolder(), "storage");
/*  67 */     if (!F1.exists()) {
/*  68 */       F1.mkdirs();
/*     */     }
/*  70 */     File F = new File(this.SC.getDataFolder(), "settings.yml");
/*     */     
/*  72 */     if (F.exists()) {
/*  73 */       int POS = 0;
/*  74 */       Map<Integer, String> MK = new HashMap<>();
/*  75 */       YamlConfiguration yamlConfiguration1 = YamlConfiguration.loadConfiguration(F);
/*     */       
/*  77 */       for (String KEY : IM_KEYS.keySet()) {
/*  78 */         POS++;
/*  79 */         String K = yamlConfiguration1.getString(KEY);
/*     */         
/*  81 */         if (K == null || K.isEmpty()) {
/*  82 */           MK.put(Integer.valueOf(POS), KEY);
/*  83 */           Object object = IM_KEYS.get(KEY);
/*     */           
/*  85 */           if (object instanceof String) {
/*  86 */             this.STRING_MAP.put(KEY, StorageCabinet.UTILS.color(String.valueOf(object))); continue;
/*  87 */           }  if (object instanceof Boolean) {
/*  88 */             this.BOOL_MAP.put(KEY, Boolean.valueOf(String.valueOf(object))); continue;
/*  89 */           }  if (object instanceof List) {
/*  90 */             this.LIST_MAP.put(KEY, (List<String>)object); continue;
/*  91 */           }  if (object instanceof Integer) {
/*  92 */             this.INT_MAP.put(KEY, (Integer)object);
/*     */           }
/*     */           continue;
/*     */         } 
/*  96 */         Object O = yamlConfiguration1.get(KEY);
/*     */         
/*  98 */         if (O instanceof String) {
/*  99 */           this.STRING_MAP.put(KEY, StorageCabinet.UTILS.color(String.valueOf(O))); continue;
/* 100 */         }  if (O instanceof Boolean) {
/* 101 */           this.BOOL_MAP.put(KEY, Boolean.valueOf(String.valueOf(O))); continue;
/* 102 */         }  if (O instanceof List) {
/* 103 */           this.LIST_MAP.put(KEY, (List<String>)O); continue;
/* 104 */         }  if (O instanceof Integer) {
/* 105 */           this.INT_MAP.put(KEY, (Integer)O);
/*     */         }
/*     */       } 
/*     */       
/* 109 */       if (!MK.isEmpty()) {
/* 110 */         MK.forEach((P, K) -> StorageCabinet.UTILS.debug("Key " + K + " was missing from the config, using the default value instead.", false));
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 116 */       return true;
/*     */     } 
/* 118 */     this.SC.saveResource("settings.yml", false);
/* 119 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(F);
/*     */     
/* 121 */     for (String KEY : IM_KEYS.keySet()) {
/* 122 */       Object O = yamlConfiguration.get(KEY);
/*     */       
/* 124 */       if (O instanceof String) {
/* 125 */         this.STRING_MAP.put(KEY, StorageCabinet.UTILS.color(String.valueOf(O))); continue;
/* 126 */       }  if (O instanceof Boolean) {
/* 127 */         this.BOOL_MAP.put(KEY, Boolean.valueOf(String.valueOf(O))); continue;
/* 128 */       }  if (O instanceof List) {
/* 129 */         this.LIST_MAP.put(KEY, (List<String>)O); continue;
/* 130 */       }  if (O instanceof Integer) {
/* 131 */         this.INT_MAP.put(KEY, (Integer)O);
/*     */       }
/*     */     } 
/*     */     
/* 135 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean unregister() {
/* 141 */     this.INT_MAP.clear();
/* 142 */     this.BOOL_MAP.clear();
/* 143 */     this.LIST_MAP.clear();
/* 144 */     this.STRING_MAP.clear();
/* 145 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setup_def_map() {
/* 150 */     IM_KEYS = ImmutableMap.builder().put("debug", Boolean.valueOf(true)).put("hopper_system", Boolean.valueOf(true))
/* 151 */       .put("backup.enabled", Boolean.valueOf(true)).put("backup.time", Integer.valueOf(30)).put("particles.drop", Boolean.valueOf(true))
/* 152 */       .put("particles.break", Boolean.valueOf(true)).put("particles.place", Boolean.valueOf(true)).put("particles.pickup", Boolean.valueOf(true))
/* 153 */       .put("particles.despawn", Boolean.valueOf(true)).put("particles.explode", Boolean.valueOf(true)).put("particles.itemtocabinet", Boolean.valueOf(true))
/* 154 */       .put("disabled_worlds", Arrays.asList(new String[] { "world_the_end"
/* 155 */           })).put("permissions.command_menu", "storagecabinet.cmd.menu")
/* 156 */       .put("permissions.command_get", "storagecabinet.cmd.get")
/* 157 */       .put("permissions.command_delete", "storagecabinet.cmd.delete")
/* 158 */       .put("permissions.command_getid", "storagecabinet.cmd.getid")
/* 159 */       .put("permissions.command_preview", "storagecabinet.cmd.preview")
/* 160 */       .put("permissions.command_locate", "storagecabinet.cmd.locate")
/* 161 */       .put("permissions.command_reload", "storagecabinet.cmd.reload")
/* 162 */       .put("permissions.cabinet_use", "storagecabinet.use")
/* 163 */       .put("permissions.cabinet_bypass", "storagecabinet.bypass")
/* 164 */       .put("permissions.cabinet_break", "storagecabinet.break")
/* 165 */       .put("permissions.cabinet_place", "storagecabinet.place")
/* 166 */       .put("permissions.cabinet_craft", "storagecabinet.craft")
/* 167 */       .put("permissions.cabinet_use_admin", "storagecabinet.use.admin")
/* 168 */       .put("recipes.oak_cabinet.enabled", Boolean.valueOf(true))
/* 169 */       .put("recipes.oak_cabinet.name", "&6&n&kii&r&6&n &e&n&lOAK CABINET&6&n &k&nii&r")
/* 170 */       .put("recipes.oak_cabinet.lores", 
/* 171 */         Arrays.asList(new String[] { "&a", "&7&o>> &3Free Slots: &f{FREE_SLOTS}", 
/* 172 */             "&7&o>> &3Available Slots: &f{SLOTS}", "&7&o>> &3Capacity Left: &f{FREE_SPACE}%", 
/* 173 */             "&7&o>> &3ID: &f{ID}", "&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
/* 174 */           })).put("recipes.darkoak_cabinet.enabled", Boolean.valueOf(true))
/* 175 */       .put("recipes.darkoak_cabinet.name", "&6&n&kii&r&6&n &e&n&lDARKOAK CABINET&6&n &k&nii&r")
/* 176 */       .put("recipes.darkoak_cabinet.lores", 
/* 177 */         Arrays.asList(new String[] { "&a", "&7&o>> &3Free Slots: &f{FREE_SLOTS}", 
/* 178 */             "&7&o>> &3Available Slots: &f{SLOTS}", "&7&o>> &3Capacity Left: &f{FREE_SPACE}%", 
/* 179 */             "&7&o>> &3ID: &f{ID}", "&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
/* 180 */           })).put("recipes.spruce_cabinet.enabled", Boolean.valueOf(true))
/* 181 */       .put("recipes.spruce_cabinet.name", "&6&n&kii&r&6&n &e&n&lSPRUCE CABINET&6&n &k&nii&r")
/* 182 */       .put("recipes.spruce_cabinet.lores", 
/* 183 */         Arrays.asList(new String[] { "&a", "&7&o>> &3Free Slots: &f{FREE_SLOTS}", 
/* 184 */             "&7&o>> &3Available Slots: &f{SLOTS}", "&7&o>> &3Capacity Left: &f{FREE_SPACE}%", 
/* 185 */             "&7&o>> &3ID: &f{ID}", "&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
/* 186 */           })).put("recipes.birch_cabinet.enabled", Boolean.valueOf(true))
/* 187 */       .put("recipes.birch_cabinet.name", "&6&n&kii&r&6&n &e&n&lBIRCH CABINET&6&n &k&nii&r")
/* 188 */       .put("recipes.birch_cabinet.lores", 
/* 189 */         Arrays.asList(new String[] { "&a", "&7&o>> &3Free Slots: &f{FREE_SLOTS}", 
/* 190 */             "&7&o>> &3Available Slots: &f{SLOTS}", "&7&o>> &3Capacity Left: &f{FREE_SPACE}%", 
/* 191 */             "&7&o>> &3ID: &f{ID}", "&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
/* 192 */           })).put("recipes.jungle_cabinet.enabled", Boolean.valueOf(true))
/* 193 */       .put("recipes.jungle_cabinet.name", "&6&n&kii&r&6&n &e&n&lJUNGLE CABINET&6&n &k&nii&r")
/* 194 */       .put("recipes.jungle_cabinet.lores", 
/* 195 */         Arrays.asList(new String[] { "&a", "&7&o>> &3Free Slots: &f{FREE_SLOTS}", 
/* 196 */             "&7&o>> &3Available Slots: &f{SLOTS}", "&7&o>> &3Capacity Left: &f{FREE_SPACE}%", 
/* 197 */             "&7&o>> &3ID: &f{ID}", "&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
/* 198 */           })).put("recipes.acacia_cabinet.enabled", Boolean.valueOf(true))
/* 199 */       .put("recipes.acacia_cabinet.name", "&6&n&kii&r&6&n &e&n&lACACIA CABINET&6&n &k&nii&r")
/* 200 */       .put("recipes.acacia_cabinet.lores", 
/* 201 */         Arrays.asList(new String[] { "&a", "&7&o>> &3Free Slots: &f{FREE_SLOTS}", 
/* 202 */             "&7&o>> &3Available Slots: &f{SLOTS}", "&7&o>> &3Capacity Left: &f{FREE_SPACE}%", 
/* 203 */             "&7&o>> &3ID: &f{ID}", "&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
/* 204 */           })).put("recipes.crimson_cabinet.enabled", Boolean.valueOf(true))
/* 205 */       .put("recipes.crimson_cabinet.name", "&d&n&kii&r&d&n &5&n&lCRIMSON CABINET&d&n &k&nii&r")
/* 206 */       .put("recipes.crimson_cabinet.lores", 
/* 207 */         Arrays.asList(new String[] { "&a", "&7&o>> &3Free Slots: &f{FREE_SLOTS}", 
/* 208 */             "&7&o>> &3Available Slots: &f{SLOTS}", "&7&o>> &3Capacity Left: &f{FREE_SPACE}%", 
/* 209 */             "&7&o>> &3ID: &f{ID}", "&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
/* 210 */           })).put("recipes.warped_cabinet.enabled", Boolean.valueOf(true))
/* 211 */       .put("recipes.warped_cabinet.name", "&b&n&kii&r&b&n &3&n&lWARPED CABINET&b&n &k&nii&r")
/* 212 */       .put("recipes.warped_cabinet.lores", 
/* 213 */         Arrays.asList(new String[] { "&a", "&7&o>> &3Free Slots: &f{FREE_SLOTS}", 
/* 214 */             "&7&o>> &3Available Slots: &f{SLOTS}", "&7&o>> &3Capacity Left: &f{FREE_SPACE}%", 
/* 215 */             "&7&o>> &3ID: &f{ID}", "&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
/* 216 */           })).put("recipes.cobble_cabinet.enabled", Boolean.valueOf(true))
/* 217 */       .put("recipes.cobble_cabinet.name", "&7&n&kii&r&7&n &8&n&lCOBBLE CABINET&7&n &k&nii&r")
/* 218 */       .put("recipes.cobble_cabinet.lores", 
/* 219 */         Arrays.asList(new String[] { "&a", "&7&o>> &3Free Slots: &f{FREE_SLOTS}", 
/* 220 */             "&7&o>> &3Available Slots: &f{SLOTS}", "&7&o>> &3Capacity Left: &f{FREE_SPACE}%", 
/* 221 */             "&7&o>> &3ID: &f{ID}", "&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
/* 222 */           })).put("recipes.coal_cabinet.enabled", Boolean.valueOf(true))
/* 223 */       .put("recipes.coal_cabinet.name", "&8&n&kii&r&8&n &0&n&lCOAL CABINET&8&n &k&nii&r")
/* 224 */       .put("recipes.coal_cabinet.lores", 
/* 225 */         Arrays.asList(new String[] { "&a", "&7&o>> &3Free Slots: &f{FREE_SLOTS}", 
/* 226 */             "&7&o>> &3Available Slots: &f{SLOTS}", "&7&o>> &3Capacity Left: &f{FREE_SPACE}%", 
/* 227 */             "&7&o>> &3ID: &f{ID}", "&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
/* 228 */           })).put("recipes.iron_cabinet.enabled", Boolean.valueOf(true))
/* 229 */       .put("recipes.iron_cabinet.name", "&7&n&kii&r&7&n &f&n&lIRON CABINET&7&n &k&nii&r")
/* 230 */       .put("recipes.iron_cabinet.lores", 
/* 231 */         Arrays.asList(new String[] { "&a", "&7&o>> &3Free Slots: &f{FREE_SLOTS}", 
/* 232 */             "&7&o>> &3Available Slots: &f{SLOTS}", "&7&o>> &3Capacity Left: &f{FREE_SPACE}%", 
/* 233 */             "&7&o>> &3ID: &f{ID}", "&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
/* 234 */           })).put("recipes.gold_cabinet.enabled", Boolean.valueOf(true))
/* 235 */       .put("recipes.gold_cabinet.name", "&e&n&kii&r&e&n &6&n&lGOLD CABINET&e&n &k&nii&r")
/* 236 */       .put("recipes.gold_cabinet.lores", 
/* 237 */         Arrays.asList(new String[] { "&a", "&7&o>> &3Free Slots: &f{FREE_SLOTS}", 
/* 238 */             "&7&o>> &3Available Slots: &f{SLOTS}", "&7&o>> &3Capacity Left: &f{FREE_SPACE}%", 
/* 239 */             "&7&o>> &3ID: &f{ID}", "&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
/* 240 */           })).put("recipes.lapis_cabinet.enabled", Boolean.valueOf(true))
/* 241 */       .put("recipes.lapis_cabinet.name", "&3&n&kii&r&3&n &b&n&lLAPIS CABINET&3&n &k&nii&r")
/* 242 */       .put("recipes.lapis_cabinet.lores", 
/* 243 */         Arrays.asList(new String[] { "&a", "&7&o>> &3Free Slots: &f{FREE_SLOTS}", 
/* 244 */             "&7&o>> &3Available Slots: &f{SLOTS}", "&7&o>> &3Capacity Left: &f{FREE_SPACE}%", 
/* 245 */             "&7&o>> &3ID: &f{ID}", "&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
/* 246 */           })).put("recipes.redstone_cabinet.enabled", Boolean.valueOf(true))
/* 247 */       .put("recipes.redstone_cabinet.name", "&c&n&kii&r&c&n &4&n&lREDSTONE CABINET&c&n &k&nii&r")
/* 248 */       .put("recipes.redstone_cabinet.lores", 
/* 249 */         Arrays.asList(new String[] { "&a", "&7&o>> &3Free Slots: &f{FREE_SLOTS}", 
/* 250 */             "&7&o>> &3Available Slots: &f{SLOTS}", "&7&o>> &3Capacity Left: &f{FREE_SPACE}%", 
/* 251 */             "&7&o>> &3ID: &f{ID}", "&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
/* 252 */           })).put("recipes.diamond_cabinet.enabled", Boolean.valueOf(true))
/* 253 */       .put("recipes.diamond_cabinet.name", "&b&n&kii&r&b&n &b&n&lDIAMOND CABINET&b&n &k&nii&r")
/* 254 */       .put("recipes.diamond_cabinet.lores", 
/* 255 */         Arrays.asList(new String[] { "&a", "&7&o>> &3Free Slots: &f{FREE_SLOTS}", 
/* 256 */             "&7&o>> &3Available Slots: &f{SLOTS}", "&7&o>> &3Capacity Left: &f{FREE_SPACE}%", 
/* 257 */             "&7&o>> &3ID: &f{ID}", "&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
/* 258 */           })).put("recipes.emerald_cabinet.enabled", Boolean.valueOf(true))
/* 259 */       .put("recipes.emerald_cabinet.name", "&2&n&kii&r&2&n &2&n&lEMERALD CABINET&2&n &k&nii&r")
/* 260 */       .put("recipes.emerald_cabinet.lores", 
/* 261 */         Arrays.asList(new String[] { "&a", "&7&o>> &3Free Slots: &f{FREE_SLOTS}", 
/* 262 */             "&7&o>> &3Available Slots: &f{SLOTS}", "&7&o>> &3Capacity Left: &f{FREE_SPACE}%", 
/* 263 */             "&7&o>> &3ID: &f{ID}", "&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
/* 264 */           })).put("recipes.netherite_cabinet.enabled", Boolean.valueOf(true))
/* 265 */       .put("recipes.netherite_cabinet.name", "&5&n&kii&r&5&n &5&n&lNETHERITE CABINET&5&n &k&nii&r")
/* 266 */       .put("recipes.netherite_cabinet.lores", 
/* 267 */         Arrays.asList(new String[] { "&a", "&7&o>> &3Free Slots: &f{FREE_SLOTS}", 
/* 268 */             "&7&o>> &3Available Slots: &f{SLOTS}", "&7&o>> &3Capacity Left: &f{FREE_SPACE}%", 
/* 269 */             "&7&o>> &3ID: &f{ID}", "&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
/* 270 */           })).put("messages.prefix", "&7>>[&clStorage&elCabinet&7]<<")
/* 271 */       .put("messages.usage", Arrays.asList(new String[] { "   &7----->>[&c&lStorage&e&lCabinet&7]<<-----", 
/* 272 */             "&7&o>> &3/cabinet menu - &8Open the main menu", 
/* 273 */             "&7&o>> &3/cabinet get <ID> - &8Receive a cabinet with the specified ID", 
/* 274 */             "&7&o>> &3/cabinet getid <ID> - &8Get the id of the cabinet you are looking at or by specifying the ID", 
/* 275 */             "&7&o>> &3/cabinet locate <ID> - &8Get the cabinet location by specifying the ID", 
/* 276 */             "&7&o>> &3/cabinet delete <ID> - &8Delete the cabinet from the database and location", 
/* 277 */             "&7&o>> &3/cabinet preview <ID> - &8Open the inventory of the cabinet you are looking at or by specifying the ID", 
/* 278 */             "&7&o>> &3/cabinet reload - &8Reload the plugin", "&7-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
/* 279 */           })).put("messages.reload", "{PREFIX} &aPlugin have been reloaded successfully.")
/* 280 */       .put("messages.noperm_place", "{PREFIX} &cYou don't have permission to place that cabinet.")
/* 281 */       .put("messages.already_placed", 
/* 282 */         "{PREFIX} &cThat cabinet have been placed already in another location, use /cabinet locate to find the location.")
/* 283 */       .put("messages.noperm_break", "{PREFIX} &cYou don't have permission to break that cabinet.")
/* 284 */       .put("messages.noperm_use", "{PREFIX} &cYou don't have permission to interact with that cabinet.")
/* 285 */       .put("messages.noperm_cmd", "{PREFIX} &cYou don't have permission to use this command.")
/* 286 */       .put("messages.noperm_craft", "{PREFIX} &cYou don't have permission to craft storage cabinets.")
/* 287 */       .put("messages.receive_cabinet", "{PREFIX} &aYou received &e1 &f{CABINET}&r &awith the ID: &d{ID}")
/* 288 */       .put("messages.receive_cabinet_fullinv", 
/* 289 */         "{PREFIX} &cYour inventory was full, &e1 &f{CABINET}&r &cwith the ID: &6{ID} &chave been dropped on the ground.")
/* 290 */       .put("messages.disabled_world", "{PREFIX} &cThe plugin have been disabled in this world.")
/* 291 */       .put("messages.cabinetnotfound", 
/* 292 */         "{PREFIX} &cThe cabinet with the ID: &d{ID} &chave not been found in the database, removing the item...")
/* 293 */       .put("messages.specifyid", "{PREFIX} &cInvalid ID, you must specify the ID of the cabinet.")
/* 294 */       .put("messages.nocabinet", "{PREFIX} &cYou must be looking at the cabinet.")
/* 295 */       .put("messages.idcabinet", "{PREFIX} &dThe cabinet you are looking at has the ID: &e{ID}")
/* 296 */       .put("messages.idnotfound", "{PREFIX} &cNo cabinet with the ID: {ID} have been found.")
/* 297 */       .put("messages.notplaced", "{PREFIX} &cThe cabinet with the ID: {ID} have not been placed yet.")
/* 298 */       .put("messages.protinteract", "{PREFIX} &cI'm sorry but you can't access that cabinet here.")
/* 299 */       .put("messages.protbreak", "{PREFIX} &cI'm sorry but you can't break that cabinet here.")
/* 300 */       .put("messages.protplace", "{PREFIX} &cI'm sorry but you can't place that cabinet here.")
/* 301 */       .put("messages.denypack", "&cYou must accept the texture pack in order to join the server.")
/* 302 */       .put("messages.loccabinet", 
/* 303 */         "{PREFIX} &6The cabinet with the ID: &e{ID} &6have been found at the location: &d{LOC}")
/* 304 */       .put("messages.cabinetclosing", "{PREFIX} &cPlease wait for the cabinet to close.")
/* 305 */       .put("messages.previewcabinet", 
/* 306 */         "{PREFIX} &aPreview of the cabinet with the ID: &e{ID} &ahave been open.")
/* 307 */       .put("messages.deletecabinet", 
/* 308 */         "{PREFIX} &cThe cabinet with the ID: &8{ID} &chave been deleted from the database and his location.")
/* 309 */       .put("messages.maingui_title", "&0&n&l&o>> &0&n&lStorage Cabinets | Choose a cabinet")
/* 310 */       .put("messages.cabinetgui_title", "&0&n&l&o>> &0&n&l{TYPE} - Page {PAGE}/{MAX_PAGE}")
/* 311 */       .put("messages.cabinetgui_filter", "&0&n&l&o>> &0&n&l{TYPE} - Hopper Settings")
/* 312 */       .put("messages.cabinetgui_leftarrow", "&d&l{PREV_PAGE} <= Prev Page")
/* 313 */       .put("messages.cabinetgui_rightarrow", "&d&lNext Page => {NEXT_PAGE}")
/* 314 */       .put("messages.cabinetgui_arrow", "&d&l<= Main Page")
/* 315 */       .put("messages.cabinetgui_alreadyone", "{PREFIX} &cI'm sorry but you can't have more than 1.")
/* 316 */       .put("messages.cabinetgui_alreadythere", 
/* 317 */         "{PREFIX} &cIt seems like the item &7{ITEM} &chave been already placed in this list.")
/* 318 */       .put("messages.cabinetgui_hopper", "      &3&lCabinet Settings | Filter")
/* 319 */       .put("messages.cabinetgui_hopper_lores", Arrays.asList(new String[] { "  &7You can filter the items that gets in or", 
/* 320 */             "&7out of the cabinet by specifying them inside", "&7of a menu.", 
/* 321 */             "&7&o>> &3Right-Click to open the filter menu", "&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
/* 322 */           })).put("messages.cabinetgui_in", "                 &2&lIN")
/* 323 */       .put("messages.cabinetgui_in_lores", Arrays.asList(new String[] { "  &7Here you can specify the items you", 
/* 324 */             "&7wish to enter the cabinet, you must ", "&7put a copy of the item in the available", 
/* 325 */             "&7slots below.", "  &c&lNOTE: &fOnly the items you specify", 
/* 326 */             "&fgonna enter the cabinet, the others are", "&fgonna be rejected and remain in the hopper.", 
/* 327 */             "&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
/* 328 */           })).put("messages.cabinetgui_out", "                 &c&lOUT")
/* 329 */       .put("messages.cabinetgui_out_lores", Arrays.asList(new String[] { "  &7Here you can specify the items you", 
/* 330 */             "&7wish to leave the cabinet, you must ", "&7put a copy of the item in the available", 
/* 331 */             "&7slots below.", "  &c&lNOTE: &fOnly the items you specify", 
/* 332 */             "&fgonna leave the cabinet, the others are", "&fgonna be rejected and remain in the cabinet.", 
/* 333 */             "&8-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
/* 334 */           })).build();
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\settings\Settings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */