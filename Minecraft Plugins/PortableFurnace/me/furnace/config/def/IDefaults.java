/*     */ package me.furnace.config.def;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.math.NumberUtils;
/*     */ import org.bukkit.Effect;
/*     */ 
/*     */ public class IDefaults
/*     */ {
/*  12 */   public LinkedHashMap<String, IDefaultKey> DEFAULTS_MAP = new LinkedHashMap<>();
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
/*     */   private void a(IDefaultKey KEY) {
/*  24 */     if (KEY == null || KEY.PATH == null || KEY.PATH.isEmpty()) {
/*     */       return;
/*     */     }
/*  27 */     this.DEFAULTS_MAP.put(KEY.PATH, KEY);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private IDefaultKey n(String PATH, Object VALUE, String... C) {
/*  33 */     return new IDefaultKey(PATH, VALUE, C);
/*     */   }
/*     */ 
/*     */   
/*     */   public void header(Writer FW) throws IOException {
/*  38 */     if (FW == null) {
/*     */       return;
/*     */     }
/*  41 */     FW.write("################################################\n");
/*  42 */     FW.write("################################################\n");
/*  43 */     FW.write("######## PORTABLE FURNACE CONFIGURATION ########\n");
/*  44 */     FW.write("################################################\n");
/*  45 */     FW.write("################################################\n");
/*  46 */     FW.write("\n");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean asList(Writer W, String PATH, Object VALUE) throws IOException {
/*  52 */     if (!(VALUE instanceof List)) {
/*  53 */       return false;
/*     */     }
/*  55 */     List<?> L = (List)VALUE;
/*     */     
/*  57 */     W.write(String.valueOf(PATH) + ":" + "\n");
/*  58 */     for (Object M : L) {
/*  59 */       if (M instanceof String) {
/*  60 */         String S = (String)M;
/*     */         
/*  62 */         if (!S.isEmpty())
/*  63 */           W.write(" - \"" + S + "\"\n"); 
/*     */         continue;
/*     */       } 
/*  66 */       W.write(" - " + M + "\n");
/*     */     } 
/*     */     
/*  69 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void save_normal(Writer FW, String PATH, Object VALUE) throws IOException {
/*  74 */     if (FW == null || VALUE == null || PATH == null) {
/*     */       return;
/*     */     }
/*  77 */     if (VALUE instanceof List) {
/*  78 */       asList(FW, PATH, VALUE);
/*     */       return;
/*     */     } 
/*  81 */     if (!(VALUE instanceof String)) {
/*  82 */       FW.write(String.valueOf(PATH) + ": " + VALUE + "\n");
/*     */       return;
/*     */     } 
/*  85 */     String O = (String)VALUE;
/*     */     
/*  87 */     if (O.equalsIgnoreCase("true") || O.equalsIgnoreCase("false") || NumberUtils.isNumber(O)) {
/*  88 */       FW.write(String.valueOf(PATH) + ": " + VALUE + "\n");
/*     */       return;
/*     */     } 
/*  91 */     FW.write(String.valueOf(PATH) + ": \"" + VALUE + "\"\n");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean save(Writer FW, String PATH, Object VALUE, String... COMMENTS) throws IOException {
/*  97 */     if (FW == null || PATH == null || PATH.isEmpty()) {
/*  98 */       return false;
/*     */     }
/* 100 */     if (COMMENTS != null && COMMENTS.length > 0) {
/* 101 */       byte b; int i; String[] arrayOfString; for (i = (arrayOfString = COMMENTS).length, b = 0; b < i; ) { String C = arrayOfString[b];
/* 102 */         FW.write(String.valueOf(C) + "\n"); b++; }
/*     */     
/*     */     } 
/* 105 */     save_normal(FW, PATH, VALUE);
/* 106 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean load() {
/* 111 */     String P = "&8|&3&l✦ PortableFurnace ✦&r&8| ";
/*     */     
/* 113 */     a(n("max_furnaces", Integer.valueOf(50), new String[] { "#Maximum number of furnaces you can create" }));
/* 114 */     a(n("cooldown", Integer.valueOf(20), new String[] { "#Cooldown time (in seconds)" }));
/* 115 */     a(n("autosaver", Integer.valueOf(5), new String[] { "#Auto data saver time (in minutes)" }));
/* 116 */     a(n("autosaver_notify", Boolean.valueOf(true), new String[] { "#Show the message in console when the plugin is done saving the data" }));
/* 117 */     a(n("notify_menu", Boolean.valueOf(true), new String[] { "#Enable or disable the use of messages that are send when furnace menu is open" }));
/* 118 */     a(n("notify_owner", Boolean.valueOf(true), new String[] {
/* 119 */             "#Enable or disable the use of messages that are send to the owner when furnace has no fuel or item is done smelting." }));
/* 120 */     a(n("bar", Boolean.valueOf(true), new String[] { "#Enable the use of bossbar (only +1.9 support)" }));
/* 121 */     a(n("bar", Boolean.valueOf(true), new String[] { "#Enable the use of bossbar (only +1.9 support)" }));
/* 122 */     a(n("bar_color", "PINK", new String[] {
/* 123 */             "#Bar color, more colors can be found here https://hub.spigotmc.org/javadocs/spigot/org/bukkit/boss/BarColor.html" }));
/* 124 */     a(n("bar_style", "SEGMENTED_20", new String[] {
/* 125 */             "#Bar style, more style can be found here https://hub.spigotmc.org/javadocs/spigot/org/bukkit/boss/BarStyle.html" }));
/* 126 */     a(n("e_radius", Integer.valueOf(5), new String[] { "#Effect radius, by default was set to 5" }));
/* 127 */     a(n("effect_smelting", Boolean.valueOf(true), new String[] { "#Enable or disable the use of this effect" }));
/* 128 */     a(n("effect_smelting_name", Effect.MOBSPAWNER_FLAMES.name(), new String[] {
/* 129 */             "#Effect name, more effect can be found here https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Effect.html" }));
/* 130 */     a(n("effect_create_recipe", Boolean.valueOf(true), new String[0]));
/* 131 */     a(n("effect_create_recipe_name", Effect.RECORD_PLAY.name(), new String[0]));
/* 132 */     a(n("effect_nofuel", Boolean.valueOf(true), new String[0]));
/* 133 */     a(n("effect_nofuel_name", Effect.SMOKE.name(), new String[0]));
/* 134 */     a(n("effect_noperm", Boolean.valueOf(true), new String[0]));
/* 135 */     a(n("effect_noperm_name", Effect.RECORD_PLAY.name(), new String[0]));
/* 136 */     a(n("effect_create", Boolean.valueOf(true), new String[0]));
/* 137 */     a(n("effect_create_name", Effect.CLICK1.name(), new String[0]));
/* 138 */     a(n("effect_delete", Boolean.valueOf(true), new String[0]));
/* 139 */     a(n("effect_delete_name", Effect.ENDER_SIGNAL.name(), new String[0]));
/* 140 */     a(n("perm_open", "portablefurnace.open", new String[] { "#The permissions of the plugin, edit them as you wish" }));
/* 141 */     a(n("perm_use", "portablefurnace.use", new String[0]));
/* 142 */     a(n("perm_use_admin", "portablefurnace.use.admin", new String[0]));
/* 143 */     a(n("perm_menu", "portablefurnace.menu", new String[0]));
/* 144 */     a(n("perm_menu_admin", "portablefurnace.menu.admin", new String[0]));
/* 145 */     a(n("perm_create", "portablefurnace.create", new String[0]));
/* 146 */     a(n("perm_recipe_create", "portablefurnace.recipe.create", new String[0]));
/* 147 */     a(n("perm_recipe_save", "portablefurnace.recipe.save", new String[0]));
/* 148 */     a(n("perm_furnace", "portablefurnace.furnace.", new String[0]));
/* 149 */     a(n("perm_reload", "portablefurnace.reload", new String[0]));
/* 150 */     a(n("perm_create_admin", "portablefurnace.create.admin", new String[0]));
/* 151 */     a(n("perm_delete", "portablefurnace.delete", new String[0]));
/* 152 */     a(n("perm_delete_admin", "portablefurnace.delete.admin", new String[0]));
/*     */     
/* 154 */     a(n("m_noperm", "&cYou must have permission.", new String[] {
/* 155 */             "#The messages of the plugin, edit them as you wish, more placeholers can be found here", 
/* 156 */             "#if you have PlaceholderAPI installed https://www.spigotmc.org/wiki/placeholderapi-placeholders/" }));
/* 157 */     a(n("m_title", "&8♦ &3&nPortableFurnace&r &8♦", new String[0]));
/* 158 */     a(n("m_cooldown", String.valueOf(P) + "&cYou must wait &e%COOLDOWN% &cbefore you can use this again.", new String[0]));
/* 159 */     a(n("m_recipe_inuse", String.valueOf(P) + "&cThere's another player already creating a new recipe.", new String[0]));
/* 160 */     a(n("m_recipe_noresult", String.valueOf(P) + "&cThere's no result item found in the recipe.", new String[0]));
/* 161 */     a(n("m_recipe_nosmelting", String.valueOf(P) + "&cThere's no smelting item found in the recipe.", new String[0]));
/* 162 */     a(n("m_recipe_sameitems", String.valueOf(P) + "&cThe result and smelting item can't be the same.", new String[0]));
/* 163 */     a(n("m_recipe_exists", String.valueOf(P) + "&cThere's already a recipe using this smelting item.", new String[0]));
/* 164 */     a(n("m_recipe_close", "&c✘ Recipe menu have been closed, you didn't save the recipe. ✘ &r", new String[0]));
/* 165 */     a(n("m_recipe_save", String.valueOf(P) + "&2New recipe with ID &5#%R_ID% &2have been created and saved.", new String[0]));
/* 166 */     a(n("m_gui_close", "&c✘ Menu have been closed, furnaces have been saved. ✘ &r", new String[0]));
/* 167 */     a(n("m_playernotfound", "&cPlayer &d%TARGET% &chas not been found", new String[0]));
/* 168 */     a(n("m_owneroffline", String.valueOf(P) + "&cOwner %OWNER% must be online if you wish to create a new furnace.", new String[0]));
/* 169 */     a(n("m_reload", String.valueOf(P) + "&aPlugin have been reloaded.", new String[0]));
/* 170 */     a(n("m_furnace_nofuel", String.valueOf(P) + "&cFurnace with ID &5#%ID% &cleft with no fuel while it was smelting.", new String[0]));
/* 171 */     a(n("m_furnace_nofuel_m", String.valueOf(P) + "&cYour furnace with ID &5#%ID% &cleft with no fuel while it was smelting.", new String[0]));
/* 172 */     a(n("m_furnace_smelting", 
/* 173 */           "&bFurnace with ID &5#%ID% &bis smelting &e1 &d%SMELTING% &busing &5%FUEL%, &c%PROGRESS%% &bdone, &6%BURNTIME% &bleft of fuel.", new String[0]));
/* 174 */     a(n("m_furnace_burning", "&bFurnace with ID &5#%ID% &bis currently burning, &c%BURNTIME% &bleft.", new String[0]));
/* 175 */     a(n("m_furnace_waiting", "&2Furnace with ID &5#%ID% &2is currently waiting for ingredients..", new String[0]));
/* 176 */     a(n("m_furnace_preview", 
/* 177 */           "&6You're currently viewing &c%OWNER%'s &6furnace with ID &5#%ID%&6, while he is offline or does not have permission.", new String[0]));
/* 178 */     a(n("m_furnace_smelting_done", 
/* 179 */           "&a&lDONE! &b%RESULT_A% &d%RESULT% &bhave been added in the furnace inventory.", new String[0]));
/* 180 */     a(n("m_furnace_smelting_done_m", String.valueOf(P) + 
/* 181 */           "&6Your furnace with ID &5#%ID% &6have finished smelting &c1 &d%SMELTING%, &b%RESULT_A% &e%RESULT% &6have been added in the furnace inventory.", new String[0]));
/* 182 */     a(n("m_furnace_extract", "&a&l+ %XP% XP", new String[0]));
/*     */     
/* 184 */     a(n("m_furnace_notempty", String.valueOf(P) + "&cFurnace with ID &5#%ID% &cmust be empty before you can delete it.", new String[0]));
/* 185 */     a(n("m_furnace_max", String.valueOf(P) + "&cPlayer &r%TARGET% &ccan't have more than &d%MAX% &cfurnaces created.", new String[0]));
/* 186 */     a(n("m_furnace_noperm", 
/* 187 */           String.valueOf(P) + "&cPlayer &r%TARGET% &cmust have permission to create more than %SIZE% furnaces. (%PERM%)", new String[0]));
/* 188 */     a(n("m_furnace_create", String.valueOf(P) + "&aFurnace with ID &3#%ID% &ahave been created", new String[0]));
/* 189 */     a(n("m_furnace_create_admin", String.valueOf(P) + "&aNew furnace with ID &3#%ID% &ahave been created by &r%ADMIN%", new String[0]));
/* 190 */     a(n("m_furnace_delete", String.valueOf(P) + "&cFurnace with ID &5#%ID% &chave been deleted", new String[0]));
/* 191 */     a(n("m_furnace_delete_admin", String.valueOf(P) + "&cYour furnace with ID &5#%ID% &chave been deleted by &r%ADMIN%", new String[0]));
/* 192 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\config\def\IDefaults.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */