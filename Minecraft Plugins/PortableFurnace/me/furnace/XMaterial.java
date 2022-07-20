/*      */ package me.furnace;
/*      */ 
/*      */ import com.google.common.cache.Cache;
/*      */ import com.google.common.cache.CacheBuilder;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Objects;
/*      */ import java.util.Optional;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.TimeUnit;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ import java.util.regex.PatternSyntaxException;
/*      */ import javax.annotation.Nonnull;
/*      */ import javax.annotation.Nullable;
/*      */ import org.apache.commons.lang.StringUtils;
/*      */ import org.apache.commons.lang.Validate;
/*      */ import org.apache.commons.lang.WordUtils;
/*      */ import org.bukkit.Bukkit;
/*      */ import org.bukkit.Material;
/*      */ import org.bukkit.inventory.ItemStack;
/*      */ import org.bukkit.inventory.meta.ItemMeta;
/*      */ import org.bukkit.inventory.meta.SpawnEggMeta;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public enum XMaterial
/*      */ {
/*   70 */   ACACIA_BOAT(new String[] { "BOAT_ACACIA" }),
/*   71 */   ACACIA_BUTTON(new String[] { "WOOD_BUTTON" }),
/*   72 */   ACACIA_DOOR(new String[] { "ACACIA_DOOR", "ACACIA_DOOR_ITEM" }),
/*   73 */   ACACIA_FENCE(new String[0]),
/*   74 */   ACACIA_FENCE_GATE(new String[0]),
/*   75 */   ACACIA_LEAVES(new String[] { "LEAVES_2" }),
/*   76 */   ACACIA_LOG(new String[] { "LOG_2" }),
/*   77 */   ACACIA_PLANKS(4, new String[] { "WOOD" }),
/*   78 */   ACACIA_PRESSURE_PLATE(new String[] { "WOOD_PLATE" }),
/*   79 */   ACACIA_SAPLING(4, new String[] { "SAPLING" }),
/*   80 */   ACACIA_SIGN(new String[] { "SIGN_POST", "SIGN" }),
/*   81 */   ACACIA_SLAB(4, new String[] { "WOOD_DOUBLE_STEP", "WOOD_STEP", "WOODEN_SLAB" }),
/*   82 */   ACACIA_STAIRS(new String[0]),
/*   83 */   ACACIA_TRAPDOOR(new String[] { "TRAP_DOOR" }),
/*   84 */   ACACIA_WALL_SIGN(new String[] { "WALL_SIGN" }),
/*   85 */   ACACIA_WOOD(new String[] { "LOG_2" }),
/*   86 */   ACTIVATOR_RAIL(new String[0]),
/*   87 */   AIR(new String[0]),
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   95 */   ALLIUM(2, new String[] { "RED_ROSE" }),
/*   96 */   AMETHYST_BLOCK(new String[0]),
/*   97 */   AMETHYST_CLUSTER(new String[0]),
/*   98 */   AMETHYST_SHARD(new String[0]),
/*   99 */   ANCIENT_DEBRIS(new String[0]),
/*  100 */   ANDESITE(5, new String[] { "STONE" }),
/*  101 */   ANDESITE_SLAB(new String[0]),
/*  102 */   ANDESITE_STAIRS(new String[0]),
/*  103 */   ANDESITE_WALL(new String[0]),
/*  104 */   ANVIL(new String[0]),
/*  105 */   APPLE(new String[0]),
/*  106 */   ARMOR_STAND(new String[0]),
/*  107 */   ARROW(new String[0]),
/*  108 */   ATTACHED_MELON_STEM(7, new String[] { "MELON_STEM" }),
/*  109 */   ATTACHED_PUMPKIN_STEM(7, new String[] { "PUMPKIN_STEM" }),
/*  110 */   AXOLOTL_BUCKET(new String[0]),
/*  111 */   AXOLOTL_SPAWN_EGG(new String[0]),
/*  112 */   AZALEA(new String[0]),
/*  113 */   AZALEA_LEAVES(new String[0]),
/*  114 */   AZURE_BLUET(3, new String[] { "RED_ROSE" }),
/*  115 */   BAKED_POTATO(new String[0]),
/*  116 */   BAMBOO(new String[0]),
/*  117 */   BAMBOO_SAPLING(new String[0]),
/*  118 */   BARREL(new String[0]),
/*  119 */   BARRIER(new String[0]),
/*  120 */   BASALT(new String[0]),
/*  121 */   BAT_SPAWN_EGG(65, new String[] { "MONSTER_EGG" }),
/*  122 */   BEACON(new String[0]),
/*  123 */   BEDROCK(new String[0]),
/*  124 */   BEEF(new String[] { "RAW_BEEF" }),
/*  125 */   BEEHIVE(new String[0]),
/*  126 */   BEETROOT(new String[] {
/*      */ 
/*      */       
/*  129 */       "BEETROOT_BLOCK" }),
/*  130 */   BEETROOTS(new String[] { "BEETROOT" }),
/*  131 */   BEETROOT_SEEDS(new String[0]),
/*  132 */   BEETROOT_SOUP(new String[0]),
/*  133 */   BEE_NEST(new String[0]),
/*  134 */   BEE_SPAWN_EGG(new String[0]),
/*  135 */   BELL(new String[0]),
/*  136 */   BIG_DRIPLEAF(new String[0]),
/*  137 */   BIG_DRIPLEAF_STEM(new String[0]),
/*  138 */   BIRCH_BOAT(new String[] { "BOAT_BIRCH" }),
/*  139 */   BIRCH_BUTTON(new String[] { "WOOD_BUTTON" }),
/*  140 */   BIRCH_DOOR(new String[] { "BIRCH_DOOR", "BIRCH_DOOR_ITEM" }),
/*  141 */   BIRCH_FENCE(new String[0]),
/*  142 */   BIRCH_FENCE_GATE(new String[0]),
/*  143 */   BIRCH_LEAVES(2, new String[] { "LEAVES" }),
/*  144 */   BIRCH_LOG(2, new String[] { "LOG" }),
/*  145 */   BIRCH_PLANKS(2, new String[] { "WOOD" }),
/*  146 */   BIRCH_PRESSURE_PLATE(new String[] { "WOOD_PLATE" }),
/*  147 */   BIRCH_SAPLING(2, new String[] { "SAPLING" }),
/*  148 */   BIRCH_SIGN(new String[] { "SIGN_POST", "SIGN" }),
/*  149 */   BIRCH_SLAB(2, new String[] { "WOOD_DOUBLE_STEP", "WOOD_STEP", "WOODEN_SLAB" }),
/*  150 */   BIRCH_STAIRS(new String[] { "BIRCH_WOOD_STAIRS" }),
/*  151 */   BIRCH_TRAPDOOR(new String[] { "TRAP_DOOR" }),
/*  152 */   BIRCH_WALL_SIGN(new String[] { "WALL_SIGN" }),
/*  153 */   BIRCH_WOOD(2, new String[] { "LOG" }),
/*  154 */   BLACKSTONE(new String[0]),
/*  155 */   BLACKSTONE_SLAB(new String[0]),
/*  156 */   BLACKSTONE_STAIRS(new String[0]),
/*  157 */   BLACKSTONE_WALL(new String[0]),
/*  158 */   BLACK_BANNER(new String[] { "STANDING_BANNER", "BANNER" }),
/*  159 */   BLACK_BED(
/*      */ 
/*      */     
/*  162 */     supports(12) ? 15 : 0, new String[] { "BED_BLOCK", "BED" }),
/*  163 */   BLACK_CANDLE(new String[0]),
/*  164 */   BLACK_CANDLE_CAKE(new String[0]),
/*  165 */   BLACK_CARPET(15, new String[] { "CARPET" }),
/*  166 */   BLACK_CONCRETE(15, new String[] { "CONCRETE" }),
/*  167 */   BLACK_CONCRETE_POWDER(15, new String[] { "CONCRETE_POWDER" }),
/*  168 */   BLACK_DYE(new String[0]),
/*  169 */   BLACK_GLAZED_TERRACOTTA(new String[0]),
/*  170 */   BLACK_SHULKER_BOX(new String[0]),
/*  171 */   BLACK_STAINED_GLASS(15, new String[] { "STAINED_GLASS" }),
/*  172 */   BLACK_STAINED_GLASS_PANE(15, new String[] { "STAINED_GLASS_PANE" }),
/*  173 */   BLACK_TERRACOTTA(15, new String[] { "STAINED_CLAY" }),
/*  174 */   BLACK_WALL_BANNER(new String[] { "WALL_BANNER" }),
/*  175 */   BLACK_WOOL(15, new String[] { "WOOL" }),
/*  176 */   BLAST_FURNACE(new String[0]),
/*  177 */   BLAZE_POWDER(new String[0]),
/*  178 */   BLAZE_ROD(new String[0]),
/*  179 */   BLAZE_SPAWN_EGG(61, new String[] { "MONSTER_EGG" }),
/*  180 */   BLUE_BANNER(4, new String[] { "STANDING_BANNER", "BANNER" }),
/*  181 */   BLUE_BED(supports(12) ? 11 : 0, new String[] { "BED_BLOCK", "BED" }),
/*  182 */   BLUE_CANDLE(new String[0]),
/*  183 */   BLUE_CANDLE_CAKE(new String[0]),
/*  184 */   BLUE_CARPET(11, new String[] { "CARPET" }),
/*  185 */   BLUE_CONCRETE(11, new String[] { "CONCRETE" }),
/*  186 */   BLUE_CONCRETE_POWDER(11, new String[] { "CONCRETE_POWDER" }),
/*  187 */   BLUE_DYE(4, new String[] { "INK_SACK", "LAPIS_LAZULI" }),
/*  188 */   BLUE_GLAZED_TERRACOTTA(new String[0]),
/*  189 */   BLUE_ICE(new String[0]),
/*  190 */   BLUE_ORCHID(1, new String[] { "RED_ROSE" }),
/*  191 */   BLUE_SHULKER_BOX(new String[0]),
/*  192 */   BLUE_STAINED_GLASS(11, new String[] { "STAINED_GLASS" }),
/*  193 */   BLUE_STAINED_GLASS_PANE(11, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/*  194 */   BLUE_TERRACOTTA(11, new String[] { "STAINED_CLAY" }),
/*  195 */   BLUE_WALL_BANNER(4, new String[] { "WALL_BANNER" }),
/*  196 */   BLUE_WOOL(11, new String[] { "WOOL" }),
/*  197 */   BONE(new String[0]),
/*  198 */   BONE_BLOCK(new String[0]),
/*  199 */   BONE_MEAL(15, new String[] { "INK_SACK" }),
/*  200 */   BOOK(new String[0]),
/*  201 */   BOOKSHELF(new String[0]),
/*  202 */   BOW(new String[0]),
/*  203 */   BOWL(new String[0]),
/*  204 */   BRAIN_CORAL(new String[0]),
/*  205 */   BRAIN_CORAL_BLOCK(new String[0]),
/*  206 */   BRAIN_CORAL_FAN(new String[0]),
/*  207 */   BRAIN_CORAL_WALL_FAN(new String[0]),
/*  208 */   BREAD(new String[0]),
/*  209 */   BREWING_STAND(new String[] { "BREWING_STAND", "BREWING_STAND_ITEM" }),
/*  210 */   BRICK(new String[] { "CLAY_BRICK" }),
/*  211 */   BRICKS(new String[] { "BRICKS", "BRICK" }),
/*  212 */   BRICK_SLAB(4, new String[] { "STEP" }),
/*  213 */   BRICK_STAIRS(new String[0]),
/*  214 */   BRICK_WALL(new String[0]),
/*  215 */   BROWN_BANNER(3, new String[] { "STANDING_BANNER", "BANNER" }),
/*  216 */   BROWN_BED(supports(12) ? 12 : 0, new String[] { "BED_BLOCK", "BED" }),
/*  217 */   BROWN_CANDLE(new String[0]),
/*  218 */   BROWN_CANDLE_CAKE(new String[0]),
/*  219 */   BROWN_CARPET(12, new String[] { "CARPET" }),
/*  220 */   BROWN_CONCRETE(12, new String[] { "CONCRETE" }),
/*  221 */   BROWN_CONCRETE_POWDER(12, new String[] { "CONCRETE_POWDER" }),
/*  222 */   BROWN_DYE(3, new String[] { "INK_SACK", "DYE", "COCOA_BEANS" }),
/*  223 */   BROWN_GLAZED_TERRACOTTA(new String[0]),
/*  224 */   BROWN_MUSHROOM(new String[0]),
/*  225 */   BROWN_MUSHROOM_BLOCK(new String[] { "BROWN_MUSHROOM", "HUGE_MUSHROOM_1" }),
/*  226 */   BROWN_SHULKER_BOX(new String[0]),
/*  227 */   BROWN_STAINED_GLASS(12, new String[] { "STAINED_GLASS" }),
/*  228 */   BROWN_STAINED_GLASS_PANE(12, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/*  229 */   BROWN_TERRACOTTA(12, new String[] { "STAINED_CLAY" }),
/*  230 */   BROWN_WALL_BANNER(3, new String[] { "WALL_BANNER" }),
/*  231 */   BROWN_WOOL(12, new String[] { "WOOL" }),
/*  232 */   BUBBLE_COLUMN(new String[0]),
/*  233 */   BUBBLE_CORAL(new String[0]),
/*  234 */   BUBBLE_CORAL_BLOCK(new String[0]),
/*  235 */   BUBBLE_CORAL_FAN(new String[0]),
/*  236 */   BUBBLE_CORAL_WALL_FAN(new String[0]),
/*  237 */   BUCKET(new String[0]),
/*  238 */   BUDDING_AMETHYST(new String[0]),
/*  239 */   BUNDLE(new String[0]),
/*  240 */   CACTUS(new String[0]),
/*  241 */   CAKE(new String[] { "CAKE_BLOCK" }),
/*  242 */   CALCITE(new String[0]),
/*  243 */   CAMPFIRE(new String[0]),
/*  244 */   CANDLE(new String[0]),
/*  245 */   CANDLE_CAKE(new String[0]),
/*  246 */   CARROT(new String[] { "CARROT_ITEM" }),
/*  247 */   CARROTS(new String[] { "CARROT" }),
/*  248 */   CARROT_ON_A_STICK(new String[] { "CARROT_STICK" }),
/*  249 */   CARTOGRAPHY_TABLE(new String[0]),
/*  250 */   CARVED_PUMPKIN(new String[0]),
/*  251 */   CAT_SPAWN_EGG(new String[0]),
/*  252 */   CAULDRON(new String[] { "CAULDRON", "CAULDRON_ITEM" }),
/*  253 */   CAVE_AIR(new String[] {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  258 */       "AIR" }),
/*  259 */   CAVE_SPIDER_SPAWN_EGG(59, new String[] { "MONSTER_EGG" }),
/*  260 */   CAVE_VINES(new String[0]),
/*  261 */   CAVE_VINES_PLANT(new String[0]),
/*  262 */   CHAIN(new String[0]),
/*  263 */   CHAINMAIL_BOOTS(new String[0]),
/*  264 */   CHAINMAIL_CHESTPLATE(new String[0]),
/*  265 */   CHAINMAIL_HELMET(new String[0]),
/*  266 */   CHAINMAIL_LEGGINGS(new String[0]),
/*  267 */   CHAIN_COMMAND_BLOCK(new String[] { "COMMAND", "COMMAND_CHAIN" }),
/*  268 */   CHARCOAL(1, new String[] { "COAL" }),
/*  269 */   CHEST(new String[] { "LOCKED_CHEST" }),
/*  270 */   CHEST_MINECART(new String[] { "STORAGE_MINECART" }),
/*  271 */   CHICKEN(new String[] { "RAW_CHICKEN" }),
/*  272 */   CHICKEN_SPAWN_EGG(93, new String[] { "MONSTER_EGG" }),
/*  273 */   CHIPPED_ANVIL(1, new String[] { "ANVIL" }),
/*  274 */   CHISELED_DEEPSLATE(new String[0]),
/*  275 */   CHISELED_NETHER_BRICKS(1, new String[] { "NETHER_BRICKS" }),
/*  276 */   CHISELED_POLISHED_BLACKSTONE(new String[] { "POLISHED_BLACKSTONE" }),
/*  277 */   CHISELED_QUARTZ_BLOCK(1, new String[] { "QUARTZ_BLOCK" }),
/*  278 */   CHISELED_RED_SANDSTONE(1, new String[] { "RED_SANDSTONE" }),
/*  279 */   CHISELED_SANDSTONE(1, new String[] { "SANDSTONE" }),
/*  280 */   CHISELED_STONE_BRICKS(3, new String[] { "SMOOTH_BRICK" }),
/*  281 */   CHORUS_FLOWER(new String[0]),
/*  282 */   CHORUS_FRUIT(new String[0]),
/*  283 */   CHORUS_PLANT(new String[0]),
/*  284 */   CLAY(new String[0]),
/*  285 */   CLAY_BALL(new String[0]),
/*  286 */   CLOCK(new String[] { "WATCH" }),
/*  287 */   COAL(new String[0]),
/*  288 */   COAL_BLOCK(new String[0]),
/*  289 */   COAL_ORE(new String[0]),
/*  290 */   COARSE_DIRT(1, new String[] { "DIRT" }),
/*  291 */   COBBLED_DEEPSLATE(new String[0]),
/*  292 */   COBBLED_DEEPSLATE_SLAB(new String[0]),
/*  293 */   COBBLED_DEEPSLATE_STAIRS(new String[0]),
/*  294 */   COBBLED_DEEPSLATE_WALL(new String[0]),
/*  295 */   COBBLESTONE(new String[0]),
/*  296 */   COBBLESTONE_SLAB(3, new String[] { "STEP" }),
/*  297 */   COBBLESTONE_STAIRS(new String[0]),
/*  298 */   COBBLESTONE_WALL(new String[] { "COBBLE_WALL" }),
/*  299 */   COBWEB(new String[] { "WEB" }),
/*  300 */   COCOA(new String[0]),
/*  301 */   COCOA_BEANS(3, new String[] { "INK_SACK" }),
/*  302 */   COD(new String[] { "RAW_FISH" }),
/*  303 */   COD_BUCKET(new String[0]),
/*  304 */   COD_SPAWN_EGG(new String[0]),
/*  305 */   COMMAND_BLOCK(new String[] { "COMMAND" }),
/*  306 */   COMMAND_BLOCK_MINECART(new String[] { "COMMAND_MINECART" }),
/*  307 */   COMPARATOR(new String[] {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  314 */       "REDSTONE_COMPARATOR_OFF", "REDSTONE_COMPARATOR_ON", "REDSTONE_COMPARATOR" }),
/*  315 */   COMPASS(new String[0]),
/*  316 */   COMPOSTER(new String[0]),
/*  317 */   CONDUIT(new String[0]),
/*  318 */   COOKED_BEEF(new String[0]),
/*  319 */   COOKED_CHICKEN(new String[0]),
/*  320 */   COOKED_COD(new String[] { "COOKED_FISH" }),
/*  321 */   COOKED_MUTTON(new String[0]),
/*  322 */   COOKED_PORKCHOP(new String[] { "GRILLED_PORK" }),
/*  323 */   COOKED_RABBIT(new String[0]),
/*  324 */   COOKED_SALMON(1, new String[] { "COOKED_FISH" }),
/*  325 */   COOKIE(new String[0]),
/*  326 */   COPPER_BLOCK(new String[0]),
/*  327 */   COPPER_INGOT(new String[0]),
/*  328 */   COPPER_ORE(new String[0]),
/*  329 */   CORNFLOWER(new String[0]),
/*  330 */   COW_SPAWN_EGG(92, new String[] { "MONSTER_EGG" }),
/*  331 */   CRACKED_DEEPSLATE_BRICKS(new String[0]),
/*  332 */   CRACKED_DEEPSLATE_TILES(new String[0]),
/*  333 */   CRACKED_NETHER_BRICKS(2, new String[] { "NETHER_BRICKS" }),
/*  334 */   CRACKED_POLISHED_BLACKSTONE_BRICKS(new String[] { "POLISHED_BLACKSTONE_BRICKS" }),
/*  335 */   CRACKED_STONE_BRICKS(2, new String[] { "SMOOTH_BRICK" }),
/*  336 */   CRAFTING_TABLE(new String[] { "WORKBENCH" }),
/*  337 */   CREEPER_BANNER_PATTERN(new String[0]),
/*  338 */   CREEPER_HEAD(4, new String[] { "SKULL", "SKULL_ITEM" }),
/*  339 */   CREEPER_SPAWN_EGG(50, new String[] { "MONSTER_EGG" }),
/*  340 */   CREEPER_WALL_HEAD(4, new String[] { "SKULL", "SKULL_ITEM" }),
/*  341 */   CRIMSON_BUTTON(new String[0]),
/*  342 */   CRIMSON_DOOR(new String[0]),
/*  343 */   CRIMSON_FENCE(new String[0]),
/*  344 */   CRIMSON_FENCE_GATE(new String[0]),
/*  345 */   CRIMSON_FUNGUS(new String[0]),
/*  346 */   CRIMSON_HYPHAE(new String[0]),
/*  347 */   CRIMSON_NYLIUM(new String[0]),
/*  348 */   CRIMSON_PLANKS(new String[0]),
/*  349 */   CRIMSON_PRESSURE_PLATE(new String[0]),
/*  350 */   CRIMSON_ROOTS(new String[0]),
/*  351 */   CRIMSON_SIGN(new String[] { "SIGN_POST" }),
/*  352 */   CRIMSON_SLAB(new String[0]),
/*  353 */   CRIMSON_STAIRS(new String[0]),
/*  354 */   CRIMSON_STEM(new String[0]),
/*  355 */   CRIMSON_TRAPDOOR(new String[0]),
/*  356 */   CRIMSON_WALL_SIGN(new String[] { "WALL_SIGN" }),
/*  357 */   CROSSBOW(new String[0]),
/*  358 */   CRYING_OBSIDIAN(new String[0]),
/*  359 */   CUT_COPPER(new String[0]),
/*  360 */   CUT_COPPER_SLAB(new String[0]),
/*  361 */   CUT_COPPER_STAIRS(new String[0]),
/*  362 */   CUT_RED_SANDSTONE(new String[0]),
/*  363 */   CUT_RED_SANDSTONE_SLAB(new String[] { "STONE_SLAB2" }),
/*  364 */   CUT_SANDSTONE(new String[0]),
/*  365 */   CUT_SANDSTONE_SLAB(new String[] { "STEP" }),
/*  366 */   CYAN_BANNER(6, new String[] { "STANDING_BANNER", "BANNER" }),
/*  367 */   CYAN_BED(supports(12) ? 9 : 0, new String[] { "BED_BLOCK", "BED" }),
/*  368 */   CYAN_CANDLE(new String[0]),
/*  369 */   CYAN_CANDLE_CAKE(new String[0]),
/*  370 */   CYAN_CARPET(9, new String[] { "CARPET" }),
/*  371 */   CYAN_CONCRETE(9, new String[] { "CONCRETE" }),
/*  372 */   CYAN_CONCRETE_POWDER(9, new String[] { "CONCRETE_POWDER" }),
/*  373 */   CYAN_DYE(6, new String[] { "INK_SACK" }),
/*  374 */   CYAN_GLAZED_TERRACOTTA(new String[0]),
/*  375 */   CYAN_SHULKER_BOX(new String[0]),
/*  376 */   CYAN_STAINED_GLASS(9, new String[] { "STAINED_GLASS" }),
/*  377 */   CYAN_STAINED_GLASS_PANE(9, new String[] { "STAINED_GLASS_PANE" }),
/*  378 */   CYAN_TERRACOTTA(9, new String[] { "STAINED_CLAY" }),
/*  379 */   CYAN_WALL_BANNER(6, new String[] { "WALL_BANNER" }),
/*  380 */   CYAN_WOOL(9, new String[] { "WOOL" }),
/*  381 */   DAMAGED_ANVIL(2, new String[] { "ANVIL" }),
/*  382 */   DANDELION(new String[] { "YELLOW_FLOWER" }),
/*  383 */   DARK_OAK_BOAT(new String[] { "BOAT_DARK_OAK" }),
/*  384 */   DARK_OAK_BUTTON(new String[] { "WOOD_BUTTON" }),
/*  385 */   DARK_OAK_DOOR(new String[] { "DARK_OAK_DOOR", "DARK_OAK_DOOR_ITEM" }),
/*  386 */   DARK_OAK_FENCE(new String[0]),
/*  387 */   DARK_OAK_FENCE_GATE(new String[0]),
/*  388 */   DARK_OAK_LEAVES(1, new String[] { "LEAVES_2" }),
/*  389 */   DARK_OAK_LOG(1, new String[] { "LOG_2" }),
/*  390 */   DARK_OAK_PLANKS(5, new String[] { "WOOD" }),
/*  391 */   DARK_OAK_PRESSURE_PLATE(new String[] { "WOOD_PLATE" }),
/*  392 */   DARK_OAK_SAPLING(5, new String[] { "SAPLING" }),
/*  393 */   DARK_OAK_SIGN(new String[] { "SIGN_POST", "SIGN" }),
/*  394 */   DARK_OAK_SLAB(5, new String[] { "WOOD_DOUBLE_STEP", "WOOD_STEP", "WOODEN_SLAB" }),
/*  395 */   DARK_OAK_STAIRS(new String[0]),
/*  396 */   DARK_OAK_TRAPDOOR(new String[] { "TRAP_DOOR" }),
/*  397 */   DARK_OAK_WALL_SIGN(new String[] { "WALL_SIGN" }),
/*  398 */   DARK_OAK_WOOD(1, new String[] { "LOG_2" }),
/*  399 */   DARK_PRISMARINE(1, new String[] { "PRISMARINE" }),
/*  400 */   DARK_PRISMARINE_SLAB(new String[0]),
/*  401 */   DARK_PRISMARINE_STAIRS(new String[0]),
/*  402 */   DAYLIGHT_DETECTOR(new String[] { "DAYLIGHT_DETECTOR_INVERTED" }),
/*  403 */   DEAD_BRAIN_CORAL(new String[0]),
/*  404 */   DEAD_BRAIN_CORAL_BLOCK(new String[0]),
/*  405 */   DEAD_BRAIN_CORAL_FAN(new String[0]),
/*  406 */   DEAD_BRAIN_CORAL_WALL_FAN(new String[0]),
/*  407 */   DEAD_BUBBLE_CORAL(new String[0]),
/*  408 */   DEAD_BUBBLE_CORAL_BLOCK(new String[0]),
/*  409 */   DEAD_BUBBLE_CORAL_FAN(new String[0]),
/*  410 */   DEAD_BUBBLE_CORAL_WALL_FAN(new String[0]),
/*  411 */   DEAD_BUSH(new String[] { "LONG_GRASS" }),
/*  412 */   DEAD_FIRE_CORAL(new String[0]),
/*  413 */   DEAD_FIRE_CORAL_BLOCK(new String[0]),
/*  414 */   DEAD_FIRE_CORAL_FAN(new String[0]),
/*  415 */   DEAD_FIRE_CORAL_WALL_FAN(new String[0]),
/*  416 */   DEAD_HORN_CORAL(new String[0]),
/*  417 */   DEAD_HORN_CORAL_BLOCK(new String[0]),
/*  418 */   DEAD_HORN_CORAL_FAN(new String[0]),
/*  419 */   DEAD_HORN_CORAL_WALL_FAN(new String[0]),
/*  420 */   DEAD_TUBE_CORAL(new String[0]),
/*  421 */   DEAD_TUBE_CORAL_BLOCK(new String[0]),
/*  422 */   DEAD_TUBE_CORAL_FAN(new String[0]),
/*  423 */   DEAD_TUBE_CORAL_WALL_FAN(new String[0]),
/*  424 */   DEBUG_STICK(new String[0]),
/*  425 */   DEEPSLATE(new String[0]),
/*  426 */   DEEPSLATE_BRICKS(new String[0]),
/*  427 */   DEEPSLATE_BRICK_SLAB(new String[0]),
/*  428 */   DEEPSLATE_BRICK_STAIRS(new String[0]),
/*  429 */   DEEPSLATE_BRICK_WALL(new String[0]),
/*  430 */   DEEPSLATE_COAL_ORE(new String[0]),
/*  431 */   DEEPSLATE_COPPER_ORE(new String[0]),
/*  432 */   DEEPSLATE_DIAMOND_ORE(new String[0]),
/*  433 */   DEEPSLATE_EMERALD_ORE(new String[0]),
/*  434 */   DEEPSLATE_GOLD_ORE(new String[0]),
/*  435 */   DEEPSLATE_IRON_ORE(new String[0]),
/*  436 */   DEEPSLATE_LAPIS_ORE(new String[0]),
/*  437 */   DEEPSLATE_REDSTONE_ORE(new String[0]),
/*  438 */   DEEPSLATE_TILES(new String[0]),
/*  439 */   DEEPSLATE_TILE_SLAB(new String[0]),
/*  440 */   DEEPSLATE_TILE_STAIRS(new String[0]),
/*  441 */   DEEPSLATE_TILE_WALL(new String[0]),
/*  442 */   DETECTOR_RAIL(new String[0]),
/*  443 */   DIAMOND(new String[0]),
/*  444 */   DIAMOND_AXE(new String[0]),
/*  445 */   DIAMOND_BLOCK(new String[0]),
/*  446 */   DIAMOND_BOOTS(new String[0]),
/*  447 */   DIAMOND_CHESTPLATE(new String[0]),
/*  448 */   DIAMOND_HELMET(new String[0]),
/*  449 */   DIAMOND_HOE(new String[0]),
/*  450 */   DIAMOND_HORSE_ARMOR(new String[] { "DIAMOND_BARDING" }),
/*  451 */   DIAMOND_LEGGINGS(new String[0]),
/*  452 */   DIAMOND_ORE(new String[0]),
/*  453 */   DIAMOND_PICKAXE(new String[0]),
/*  454 */   DIAMOND_SHOVEL(new String[] { "DIAMOND_SPADE" }),
/*  455 */   DIAMOND_SWORD(new String[0]),
/*  456 */   DIORITE(3, new String[] { "STONE" }),
/*  457 */   DIORITE_SLAB(new String[0]),
/*  458 */   DIORITE_STAIRS(new String[0]),
/*  459 */   DIORITE_WALL(new String[0]),
/*  460 */   DIRT(new String[0]),
/*  461 */   DIRT_PATH(new String[] {
/*      */ 
/*      */       
/*  464 */       "GRASS_PATH" }),
/*  465 */   DISPENSER(new String[0]),
/*  466 */   DOLPHIN_SPAWN_EGG(new String[0]),
/*  467 */   DONKEY_SPAWN_EGG(32, new String[] { "MONSTER_EGG" }),
/*  468 */   DRAGON_BREATH(new String[] { "DRAGONS_BREATH" }),
/*  469 */   DRAGON_EGG(new String[0]),
/*  470 */   DRAGON_HEAD(5, new String[] { "SKULL", "SKULL_ITEM" }),
/*  471 */   DRAGON_WALL_HEAD(5, new String[] { "SKULL", "SKULL_ITEM" }),
/*  472 */   DRIED_KELP(new String[0]),
/*  473 */   DRIED_KELP_BLOCK(new String[0]),
/*  474 */   DRIPSTONE_BLOCK(new String[0]),
/*  475 */   DROPPER(new String[0]),
/*  476 */   DROWNED_SPAWN_EGG(new String[0]),
/*  477 */   EGG(new String[0]),
/*  478 */   ELDER_GUARDIAN_SPAWN_EGG(4, new String[] { "MONSTER_EGG" }),
/*  479 */   ELYTRA(new String[0]),
/*  480 */   EMERALD(new String[0]),
/*  481 */   EMERALD_BLOCK(new String[0]),
/*  482 */   EMERALD_ORE(new String[0]),
/*  483 */   ENCHANTED_BOOK(new String[0]),
/*  484 */   ENCHANTED_GOLDEN_APPLE(1, new String[] { "GOLDEN_APPLE" }),
/*  485 */   ENCHANTING_TABLE(new String[] { "ENCHANTMENT_TABLE" }),
/*  486 */   ENDERMAN_SPAWN_EGG(58, new String[] { "MONSTER_EGG" }),
/*  487 */   ENDERMITE_SPAWN_EGG(67, new String[] { "MONSTER_EGG" }),
/*  488 */   ENDER_CHEST(new String[0]),
/*  489 */   ENDER_EYE(new String[] { "EYE_OF_ENDER" }),
/*  490 */   ENDER_PEARL(new String[0]),
/*  491 */   END_CRYSTAL(new String[0]),
/*  492 */   END_GATEWAY(new String[0]),
/*  493 */   END_PORTAL(new String[] { "ENDER_PORTAL" }),
/*  494 */   END_PORTAL_FRAME(new String[] { "ENDER_PORTAL_FRAME" }),
/*  495 */   END_ROD(new String[0]),
/*  496 */   END_STONE(new String[] { "ENDER_STONE" }),
/*  497 */   END_STONE_BRICKS(new String[] { "END_BRICKS" }),
/*  498 */   END_STONE_BRICK_SLAB(6, new String[] { "STEP" }),
/*  499 */   END_STONE_BRICK_STAIRS(new String[] { "SMOOTH_STAIRS" }),
/*  500 */   END_STONE_BRICK_WALL(new String[0]),
/*  501 */   EVOKER_SPAWN_EGG(34, new String[] { "MONSTER_EGG" }),
/*  502 */   EXPERIENCE_BOTTLE(new String[] { "EXP_BOTTLE" }),
/*  503 */   EXPOSED_COPPER(new String[0]),
/*  504 */   EXPOSED_CUT_COPPER(new String[0]),
/*  505 */   EXPOSED_CUT_COPPER_SLAB(new String[0]),
/*  506 */   EXPOSED_CUT_COPPER_STAIRS(new String[0]),
/*  507 */   FARMLAND(new String[] { "SOIL" }),
/*  508 */   FEATHER(new String[0]),
/*  509 */   FERMENTED_SPIDER_EYE(new String[0]),
/*  510 */   FERN(2, new String[] { "LONG_GRASS" }),
/*  511 */   FILLED_MAP(new String[] {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  517 */       "MAP" }),
/*  518 */   FIRE(new String[0]),
/*  519 */   FIREWORK_ROCKET(new String[] { "FIREWORK" }),
/*  520 */   FIREWORK_STAR(new String[] { "FIREWORK_CHARGE" }),
/*  521 */   FIRE_CHARGE(new String[] { "FIREBALL" }),
/*  522 */   FIRE_CORAL(new String[0]),
/*  523 */   FIRE_CORAL_BLOCK(new String[0]),
/*  524 */   FIRE_CORAL_FAN(new String[0]),
/*  525 */   FIRE_CORAL_WALL_FAN(new String[0]),
/*  526 */   FISHING_ROD(new String[0]),
/*  527 */   FLETCHING_TABLE(new String[0]),
/*  528 */   FLINT(new String[0]),
/*  529 */   FLINT_AND_STEEL(new String[0]),
/*  530 */   FLOWERING_AZALEA(new String[0]),
/*  531 */   FLOWERING_AZALEA_LEAVES(new String[0]),
/*  532 */   FLOWER_BANNER_PATTERN(new String[0]),
/*  533 */   FLOWER_POT(new String[] { "FLOWER_POT", "FLOWER_POT_ITEM" }),
/*  534 */   FOX_SPAWN_EGG(new String[0]),
/*  535 */   FROSTED_ICE(new String[0]),
/*      */ 
/*      */ 
/*      */   
/*  539 */   FURNACE(new String[] { "BURNING_FURNACE" }),
/*  540 */   FURNACE_MINECART(new String[] { "POWERED_MINECART" }),
/*  541 */   GHAST_SPAWN_EGG(56, new String[] { "MONSTER_EGG" }),
/*  542 */   GHAST_TEAR(new String[0]),
/*  543 */   GILDED_BLACKSTONE(new String[0]),
/*  544 */   GLASS(new String[0]),
/*  545 */   GLASS_BOTTLE(new String[0]),
/*  546 */   GLASS_PANE(new String[] { "THIN_GLASS" }),
/*  547 */   GLISTERING_MELON_SLICE(new String[] { "SPECKLED_MELON" }),
/*  548 */   GLOBE_BANNER_PATTERN(new String[0]),
/*  549 */   GLOWSTONE(new String[0]),
/*  550 */   GLOWSTONE_DUST(new String[0]),
/*  551 */   GLOW_BERRIES(new String[0]),
/*  552 */   GLOW_INK_SAC(new String[0]),
/*  553 */   GLOW_ITEM_FRAME(new String[0]),
/*  554 */   GLOW_LICHEN(new String[0]),
/*  555 */   GLOW_SQUID_SPAWN_EGG(new String[0]),
/*  556 */   GOAT_SPAWN_EGG(new String[0]),
/*  557 */   GOLDEN_APPLE(new String[0]),
/*  558 */   GOLDEN_AXE(new String[] { "GOLD_AXE" }),
/*  559 */   GOLDEN_BOOTS(new String[] { "GOLD_BOOTS" }),
/*  560 */   GOLDEN_CARROT(new String[0]),
/*  561 */   GOLDEN_CHESTPLATE(new String[] { "GOLD_CHESTPLATE" }),
/*  562 */   GOLDEN_HELMET(new String[] { "GOLD_HELMET" }),
/*  563 */   GOLDEN_HOE(new String[] { "GOLD_HOE" }),
/*  564 */   GOLDEN_HORSE_ARMOR(new String[] { "GOLD_BARDING" }),
/*  565 */   GOLDEN_LEGGINGS(new String[] { "GOLD_LEGGINGS" }),
/*  566 */   GOLDEN_PICKAXE(new String[] { "GOLD_PICKAXE" }),
/*  567 */   GOLDEN_SHOVEL(new String[] { "GOLD_SPADE" }),
/*  568 */   GOLDEN_SWORD(new String[] { "GOLD_SWORD" }),
/*  569 */   GOLD_BLOCK(new String[0]),
/*  570 */   GOLD_INGOT(new String[0]),
/*  571 */   GOLD_NUGGET(new String[0]),
/*  572 */   GOLD_ORE(new String[0]),
/*  573 */   GRANITE(1, new String[] { "STONE" }),
/*  574 */   GRANITE_SLAB(new String[0]),
/*  575 */   GRANITE_STAIRS(new String[0]),
/*  576 */   GRANITE_WALL(new String[0]),
/*  577 */   GRASS(1, new String[] { "LONG_GRASS" }),
/*  578 */   GRASS_BLOCK(new String[] { "GRASS" }),
/*  579 */   GRAVEL(new String[0]),
/*  580 */   GRAY_BANNER(8, new String[] { "STANDING_BANNER", "BANNER" }),
/*  581 */   GRAY_BED(supports(12) ? 7 : 0, new String[] { "BED_BLOCK", "BED" }),
/*  582 */   GRAY_CANDLE(new String[0]),
/*  583 */   GRAY_CANDLE_CAKE(new String[0]),
/*  584 */   GRAY_CARPET(7, new String[] { "CARPET" }),
/*  585 */   GRAY_CONCRETE(7, new String[] { "CONCRETE" }),
/*  586 */   GRAY_CONCRETE_POWDER(7, new String[] { "CONCRETE_POWDER" }),
/*  587 */   GRAY_DYE(8, new String[] { "INK_SACK" }),
/*  588 */   GRAY_GLAZED_TERRACOTTA(new String[0]),
/*  589 */   GRAY_SHULKER_BOX(new String[0]),
/*  590 */   GRAY_STAINED_GLASS(7, new String[] { "STAINED_GLASS" }),
/*  591 */   GRAY_STAINED_GLASS_PANE(7, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/*  592 */   GRAY_TERRACOTTA(7, new String[] { "STAINED_CLAY" }),
/*  593 */   GRAY_WALL_BANNER(8, new String[] { "WALL_BANNER" }),
/*  594 */   GRAY_WOOL(7, new String[] { "WOOL" }),
/*  595 */   GREEN_BANNER(2, new String[] { "STANDING_BANNER", "BANNER" }),
/*  596 */   GREEN_BED(supports(12) ? 13 : 0, new String[] { "BED_BLOCK", "BED" }),
/*  597 */   GREEN_CANDLE(new String[0]),
/*  598 */   GREEN_CANDLE_CAKE(new String[0]),
/*  599 */   GREEN_CARPET(13, new String[] { "CARPET" }),
/*  600 */   GREEN_CONCRETE(13, new String[] { "CONCRETE" }),
/*  601 */   GREEN_CONCRETE_POWDER(13, new String[] { "CONCRETE_POWDER" }),
/*  602 */   GREEN_DYE(2, new String[] { "INK_SACK", "CACTUS_GREEN" }),
/*  603 */   GREEN_GLAZED_TERRACOTTA(new String[0]),
/*  604 */   GREEN_SHULKER_BOX(new String[0]),
/*  605 */   GREEN_STAINED_GLASS(13, new String[] { "STAINED_GLASS" }),
/*  606 */   GREEN_STAINED_GLASS_PANE(13, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/*  607 */   GREEN_TERRACOTTA(13, new String[] { "STAINED_CLAY" }),
/*  608 */   GREEN_WALL_BANNER(2, new String[] { "WALL_BANNER" }),
/*  609 */   GREEN_WOOL(13, new String[] { "WOOL" }),
/*  610 */   GRINDSTONE(new String[0]),
/*  611 */   GUARDIAN_SPAWN_EGG(68, new String[] { "MONSTER_EGG" }),
/*  612 */   GUNPOWDER(new String[] { "SULPHUR" }),
/*  613 */   HANGING_ROOTS(new String[0]),
/*  614 */   HAY_BLOCK(new String[0]),
/*  615 */   HEART_OF_THE_SEA(new String[0]),
/*  616 */   HEAVY_WEIGHTED_PRESSURE_PLATE(new String[] { "IRON_PLATE" }),
/*  617 */   HOGLIN_SPAWN_EGG(new String[] { "MONSTER_EGG" }),
/*  618 */   HONEYCOMB(new String[0]),
/*  619 */   HONEYCOMB_BLOCK(new String[0]),
/*  620 */   HONEY_BLOCK(new String[0]),
/*  621 */   HONEY_BOTTLE(new String[0]),
/*  622 */   HOPPER(new String[0]),
/*  623 */   HOPPER_MINECART(new String[0]),
/*  624 */   HORN_CORAL(new String[0]),
/*  625 */   HORN_CORAL_BLOCK(new String[0]),
/*  626 */   HORN_CORAL_FAN(new String[0]),
/*  627 */   HORN_CORAL_WALL_FAN(new String[0]),
/*  628 */   HORSE_SPAWN_EGG(100, new String[] { "MONSTER_EGG" }),
/*  629 */   HUSK_SPAWN_EGG(23, new String[] { "MONSTER_EGG" }),
/*  630 */   ICE(new String[0]),
/*  631 */   INFESTED_CHISELED_STONE_BRICKS(5, new String[] { "MONSTER_EGGS" }),
/*  632 */   INFESTED_COBBLESTONE(1, new String[] { "MONSTER_EGGS" }),
/*  633 */   INFESTED_CRACKED_STONE_BRICKS(4, new String[] { "MONSTER_EGGS" }),
/*  634 */   INFESTED_DEEPSLATE(new String[0]),
/*  635 */   INFESTED_MOSSY_STONE_BRICKS(3, new String[] { "MONSTER_EGGS" }),
/*  636 */   INFESTED_STONE(new String[] { "MONSTER_EGGS" }),
/*  637 */   INFESTED_STONE_BRICKS(2, new String[] { "MONSTER_EGGS" }),
/*  638 */   INK_SAC(new String[] {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  643 */       "INK_SACK" }),
/*  644 */   IRON_AXE(new String[0]),
/*  645 */   IRON_BARS(new String[] { "IRON_FENCE" }),
/*  646 */   IRON_BLOCK(new String[0]),
/*  647 */   IRON_BOOTS(new String[0]),
/*  648 */   IRON_CHESTPLATE(new String[0]),
/*  649 */   IRON_DOOR(new String[] { "IRON_DOOR_BLOCK" }),
/*  650 */   IRON_HELMET(new String[0]),
/*  651 */   IRON_HOE(new String[0]),
/*  652 */   IRON_HORSE_ARMOR(new String[] { "IRON_BARDING" }),
/*  653 */   IRON_INGOT(new String[0]),
/*  654 */   IRON_LEGGINGS(new String[0]),
/*  655 */   IRON_NUGGET(new String[0]),
/*  656 */   IRON_ORE(new String[0]),
/*  657 */   IRON_PICKAXE(new String[0]),
/*  658 */   IRON_SHOVEL(new String[] { "IRON_SPADE" }),
/*  659 */   IRON_SWORD(new String[0]),
/*  660 */   IRON_TRAPDOOR(new String[0]),
/*  661 */   ITEM_FRAME(new String[0]),
/*  662 */   JACK_O_LANTERN(new String[0]),
/*  663 */   JIGSAW(new String[0]),
/*  664 */   JUKEBOX(new String[0]),
/*  665 */   JUNGLE_BOAT(new String[] { "BOAT_JUNGLE" }),
/*  666 */   JUNGLE_BUTTON(new String[] { "WOOD_BUTTON" }),
/*  667 */   JUNGLE_DOOR(new String[] { "JUNGLE_DOOR", "JUNGLE_DOOR_ITEM" }),
/*  668 */   JUNGLE_FENCE(new String[0]),
/*  669 */   JUNGLE_FENCE_GATE(new String[0]),
/*  670 */   JUNGLE_LEAVES(3, new String[] { "LEAVES" }),
/*  671 */   JUNGLE_LOG(3, new String[] { "LOG" }),
/*  672 */   JUNGLE_PLANKS(3, new String[] { "WOOD" }),
/*  673 */   JUNGLE_PRESSURE_PLATE(new String[] { "WOOD_PLATE" }),
/*  674 */   JUNGLE_SAPLING(3, new String[] { "SAPLING" }),
/*  675 */   JUNGLE_SIGN(new String[] { "SIGN_POST", "SIGN" }),
/*  676 */   JUNGLE_SLAB(3, new String[] { "WOOD_DOUBLE_STEP", "WOOD_STEP", "WOODEN_SLAB" }),
/*  677 */   JUNGLE_STAIRS(new String[] { "JUNGLE_WOOD_STAIRS" }),
/*  678 */   JUNGLE_TRAPDOOR(new String[] { "TRAP_DOOR" }),
/*  679 */   JUNGLE_WALL_SIGN(new String[] { "WALL_SIGN" }),
/*  680 */   JUNGLE_WOOD(3, new String[] { "LOG" }),
/*  681 */   KELP(new String[0]),
/*  682 */   KELP_PLANT(new String[0]),
/*  683 */   KNOWLEDGE_BOOK(new String[] { "BOOK" }),
/*  684 */   LADDER(new String[0]),
/*  685 */   LANTERN(new String[0]),
/*  686 */   LAPIS_BLOCK(new String[0]),
/*  687 */   LAPIS_LAZULI(4, new String[] { "INK_SACK" }),
/*  688 */   LAPIS_ORE(new String[0]),
/*  689 */   LARGE_AMETHYST_BUD(new String[0]),
/*  690 */   LARGE_FERN(3, new String[] { "DOUBLE_PLANT" }),
/*  691 */   LAVA(new String[] { "STATIONARY_LAVA" }),
/*  692 */   LAVA_BUCKET(new String[0]),
/*  693 */   LAVA_CAULDRON(new String[0]),
/*  694 */   LEAD(new String[] { "LEASH" }),
/*  695 */   LEATHER(new String[0]),
/*  696 */   LEATHER_BOOTS(new String[0]),
/*  697 */   LEATHER_CHESTPLATE(new String[0]),
/*  698 */   LEATHER_HELMET(new String[0]),
/*  699 */   LEATHER_HORSE_ARMOR(new String[] { "IRON_HORSE_ARMOR" }),
/*  700 */   LEATHER_LEGGINGS(new String[0]),
/*  701 */   LECTERN(new String[0]),
/*  702 */   LEVER(new String[0]),
/*  703 */   LIGHT(new String[0]),
/*  704 */   LIGHTNING_ROD(new String[0]),
/*  705 */   LIGHT_BLUE_BANNER(12, new String[] { "STANDING_BANNER", "BANNER" }),
/*  706 */   LIGHT_BLUE_BED(supports(12) ? 3 : 0, new String[] { "BED_BLOCK", "BED" }),
/*  707 */   LIGHT_BLUE_CANDLE(new String[0]),
/*  708 */   LIGHT_BLUE_CANDLE_CAKE(new String[0]),
/*  709 */   LIGHT_BLUE_CARPET(3, new String[] { "CARPET" }),
/*  710 */   LIGHT_BLUE_CONCRETE(3, new String[] { "CONCRETE" }),
/*  711 */   LIGHT_BLUE_CONCRETE_POWDER(3, new String[] { "CONCRETE_POWDER" }),
/*  712 */   LIGHT_BLUE_DYE(12, new String[] { "INK_SACK" }),
/*  713 */   LIGHT_BLUE_GLAZED_TERRACOTTA(new String[0]),
/*  714 */   LIGHT_BLUE_SHULKER_BOX(new String[0]),
/*  715 */   LIGHT_BLUE_STAINED_GLASS(3, new String[] { "STAINED_GLASS" }),
/*  716 */   LIGHT_BLUE_STAINED_GLASS_PANE(3, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/*  717 */   LIGHT_BLUE_TERRACOTTA(3, new String[] { "STAINED_CLAY" }),
/*  718 */   LIGHT_BLUE_WALL_BANNER(12, new String[] { "WALL_BANNER", "STANDING_BANNER", "BANNER" }),
/*  719 */   LIGHT_BLUE_WOOL(3, new String[] { "WOOL" }),
/*  720 */   LIGHT_GRAY_BANNER(7, new String[] { "STANDING_BANNER", "BANNER" }),
/*  721 */   LIGHT_GRAY_BED(supports(12) ? 8 : 0, new String[] { "BED_BLOCK", "BED" }),
/*  722 */   LIGHT_GRAY_CANDLE(new String[0]),
/*  723 */   LIGHT_GRAY_CANDLE_CAKE(new String[0]),
/*  724 */   LIGHT_GRAY_CARPET(8, new String[] { "CARPET" }),
/*  725 */   LIGHT_GRAY_CONCRETE(8, new String[] { "CONCRETE" }),
/*  726 */   LIGHT_GRAY_CONCRETE_POWDER(8, new String[] { "CONCRETE_POWDER" }),
/*  727 */   LIGHT_GRAY_DYE(7, new String[] { "INK_SACK" }),
/*  728 */   LIGHT_GRAY_GLAZED_TERRACOTTA(
/*      */ 
/*      */ 
/*      */     
/*  732 */     8, new String[] { "STAINED_CLAY", "SILVER_GLAZED_TERRACOTTA" }),
/*  733 */   LIGHT_GRAY_SHULKER_BOX(new String[] { "SILVER_SHULKER_BOX" }),
/*  734 */   LIGHT_GRAY_STAINED_GLASS(8, new String[] { "STAINED_GLASS" }),
/*  735 */   LIGHT_GRAY_STAINED_GLASS_PANE(8, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/*  736 */   LIGHT_GRAY_TERRACOTTA(8, new String[] { "STAINED_CLAY" }),
/*  737 */   LIGHT_GRAY_WALL_BANNER(7, new String[] { "WALL_BANNER" }),
/*  738 */   LIGHT_GRAY_WOOL(8, new String[] { "WOOL" }),
/*  739 */   LIGHT_WEIGHTED_PRESSURE_PLATE(new String[] { "GOLD_PLATE" }),
/*  740 */   LILAC(1, new String[] { "DOUBLE_PLANT" }),
/*  741 */   LILY_OF_THE_VALLEY(new String[0]),
/*  742 */   LILY_PAD(new String[] { "WATER_LILY" }),
/*  743 */   LIME_BANNER(10, new String[] { "STANDING_BANNER", "BANNER" }),
/*  744 */   LIME_BED(supports(12) ? 5 : 0, new String[] { "BED_BLOCK", "BED" }),
/*  745 */   LIME_CANDLE(new String[0]),
/*  746 */   LIME_CANDLE_CAKE(new String[0]),
/*  747 */   LIME_CARPET(5, new String[] { "CARPET" }),
/*  748 */   LIME_CONCRETE(5, new String[] { "CONCRETE" }),
/*  749 */   LIME_CONCRETE_POWDER(5, new String[] { "CONCRETE_POWDER" }),
/*  750 */   LIME_DYE(10, new String[] { "INK_SACK" }),
/*  751 */   LIME_GLAZED_TERRACOTTA(new String[0]),
/*  752 */   LIME_SHULKER_BOX(new String[0]),
/*  753 */   LIME_STAINED_GLASS(5, new String[] { "STAINED_GLASS" }),
/*  754 */   LIME_STAINED_GLASS_PANE(5, new String[] { "STAINED_GLASS_PANE" }),
/*  755 */   LIME_TERRACOTTA(5, new String[] { "STAINED_CLAY" }),
/*  756 */   LIME_WALL_BANNER(10, new String[] { "WALL_BANNER" }),
/*  757 */   LIME_WOOL(5, new String[] { "WOOL" }),
/*  758 */   LINGERING_POTION(new String[0]),
/*  759 */   LLAMA_SPAWN_EGG(103, new String[] { "MONSTER_EGG" }),
/*  760 */   LODESTONE(new String[0]),
/*  761 */   LOOM(new String[0]),
/*  762 */   MAGENTA_BANNER(13, new String[] { "STANDING_BANNER", "BANNER" }),
/*  763 */   MAGENTA_BED(supports(12) ? 2 : 0, new String[] { "BED_BLOCK", "BED" }),
/*  764 */   MAGENTA_CANDLE(new String[0]),
/*  765 */   MAGENTA_CANDLE_CAKE(new String[0]),
/*  766 */   MAGENTA_CARPET(2, new String[] { "CARPET" }),
/*  767 */   MAGENTA_CONCRETE(2, new String[] { "CONCRETE" }),
/*  768 */   MAGENTA_CONCRETE_POWDER(2, new String[] { "CONCRETE_POWDER" }),
/*  769 */   MAGENTA_DYE(13, new String[] { "INK_SACK" }),
/*  770 */   MAGENTA_GLAZED_TERRACOTTA(new String[0]),
/*  771 */   MAGENTA_SHULKER_BOX(new String[0]),
/*  772 */   MAGENTA_STAINED_GLASS(2, new String[] { "STAINED_GLASS" }),
/*  773 */   MAGENTA_STAINED_GLASS_PANE(2, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/*  774 */   MAGENTA_TERRACOTTA(2, new String[] { "STAINED_CLAY" }),
/*  775 */   MAGENTA_WALL_BANNER(13, new String[] { "WALL_BANNER" }),
/*  776 */   MAGENTA_WOOL(2, new String[] { "WOOL" }),
/*  777 */   MAGMA_BLOCK(new String[] { "MAGMA" }),
/*  778 */   MAGMA_CREAM(new String[0]),
/*  779 */   MAGMA_CUBE_SPAWN_EGG(62, new String[] { "MONSTER_EGG" }),
/*  780 */   MAP(new String[] {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  787 */       "EMPTY_MAP" }),
/*  788 */   MEDIUM_AMETHYST_BUD(new String[0]),
/*  789 */   MELON(new String[] { "MELON_BLOCK" }),
/*  790 */   MELON_SEEDS(new String[0]),
/*  791 */   MELON_SLICE(new String[] { "MELON" }),
/*  792 */   MELON_STEM(new String[0]),
/*  793 */   MILK_BUCKET(new String[0]),
/*  794 */   MINECART(new String[0]),
/*  795 */   MOJANG_BANNER_PATTERN(new String[0]),
/*  796 */   MOOSHROOM_SPAWN_EGG(96, new String[] { "MONSTER_EGG" }),
/*  797 */   MOSSY_COBBLESTONE(new String[0]),
/*  798 */   MOSSY_COBBLESTONE_SLAB(3, new String[] { "STEP" }),
/*  799 */   MOSSY_COBBLESTONE_STAIRS(new String[0]),
/*  800 */   MOSSY_COBBLESTONE_WALL(1, new String[] { "COBBLE_WALL", "COBBLESTONE_WALL" }),
/*  801 */   MOSSY_STONE_BRICKS(1, new String[] { "SMOOTH_BRICK" }),
/*  802 */   MOSSY_STONE_BRICK_SLAB(5, new String[] { "STEP" }),
/*  803 */   MOSSY_STONE_BRICK_STAIRS(new String[] { "SMOOTH_STAIRS" }),
/*  804 */   MOSSY_STONE_BRICK_WALL(new String[0]),
/*  805 */   MOSS_BLOCK(new String[0]),
/*  806 */   MOSS_CARPET(new String[0]),
/*  807 */   MOVING_PISTON(new String[] { "PISTON_MOVING_PIECE" }),
/*  808 */   MULE_SPAWN_EGG(32, new String[] { "MONSTER_EGG" }),
/*  809 */   MUSHROOM_STEM(new String[] { "BROWN_MUSHROOM" }),
/*  810 */   MUSHROOM_STEW(new String[] { "MUSHROOM_SOUP" }),
/*  811 */   MUSIC_DISC_11(new String[] { "GOLD_RECORD" }),
/*  812 */   MUSIC_DISC_13(new String[] { "GREEN_RECORD" }),
/*  813 */   MUSIC_DISC_BLOCKS(new String[] { "RECORD_3" }),
/*  814 */   MUSIC_DISC_CAT(new String[] { "RECORD_4" }),
/*  815 */   MUSIC_DISC_CHIRP(new String[] { "RECORD_5" }),
/*  816 */   MUSIC_DISC_FAR(new String[] { "RECORD_6" }),
/*  817 */   MUSIC_DISC_MALL(new String[] { "RECORD_7" }),
/*  818 */   MUSIC_DISC_MELLOHI(new String[] { "RECORD_8" }),
/*  819 */   MUSIC_DISC_OTHERSIDE(new String[0]),
/*  820 */   MUSIC_DISC_PIGSTEP(new String[0]),
/*  821 */   MUSIC_DISC_STAL(new String[] { "RECORD_9" }),
/*  822 */   MUSIC_DISC_STRAD(new String[] { "RECORD_10" }),
/*  823 */   MUSIC_DISC_WAIT(new String[] { "RECORD_11" }),
/*  824 */   MUSIC_DISC_WARD(new String[] { "RECORD_12" }),
/*  825 */   MUTTON(new String[0]),
/*  826 */   MYCELIUM(new String[] { "MYCEL" }),
/*  827 */   NAME_TAG(new String[0]),
/*  828 */   NAUTILUS_SHELL(new String[0]),
/*  829 */   NETHERITE_AXE(new String[0]),
/*  830 */   NETHERITE_BLOCK(new String[0]),
/*  831 */   NETHERITE_BOOTS(new String[0]),
/*  832 */   NETHERITE_CHESTPLATE(new String[0]),
/*  833 */   NETHERITE_HELMET(new String[0]),
/*  834 */   NETHERITE_HOE(new String[0]),
/*  835 */   NETHERITE_INGOT(new String[0]),
/*  836 */   NETHERITE_LEGGINGS(new String[0]),
/*  837 */   NETHERITE_PICKAXE(new String[0]),
/*  838 */   NETHERITE_SCRAP(new String[0]),
/*  839 */   NETHERITE_SHOVEL(new String[0]),
/*  840 */   NETHERITE_SWORD(new String[0]),
/*  841 */   NETHERRACK(new String[0]),
/*  842 */   NETHER_BRICK(new String[] { "NETHER_BRICK_ITEM" }),
/*  843 */   NETHER_BRICKS(new String[] { "NETHER_BRICK" }),
/*  844 */   NETHER_BRICK_FENCE(new String[] { "NETHER_FENCE" }),
/*  845 */   NETHER_BRICK_SLAB(6, new String[] { "STEP" }),
/*  846 */   NETHER_BRICK_STAIRS(new String[0]),
/*  847 */   NETHER_BRICK_WALL(new String[0]),
/*  848 */   NETHER_GOLD_ORE(new String[0]),
/*  849 */   NETHER_PORTAL(new String[] { "PORTAL" }),
/*  850 */   NETHER_QUARTZ_ORE(new String[] { "QUARTZ_ORE" }),
/*  851 */   NETHER_SPROUTS(new String[0]),
/*  852 */   NETHER_STAR(new String[0]),
/*  853 */   NETHER_WART(new String[] {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  858 */       "NETHER_WARTS", "NETHER_STALK" }),
/*  859 */   NETHER_WART_BLOCK(new String[0]),
/*  860 */   NOTE_BLOCK(new String[0]),
/*  861 */   OAK_BOAT(new String[] { "BOAT" }),
/*  862 */   OAK_BUTTON(new String[] { "WOOD_BUTTON" }),
/*  863 */   OAK_DOOR(new String[] { "WOODEN_DOOR", "WOOD_DOOR" }),
/*  864 */   OAK_FENCE(new String[] { "FENCE" }),
/*  865 */   OAK_FENCE_GATE(new String[] { "FENCE_GATE" }),
/*  866 */   OAK_LEAVES(new String[] { "LEAVES" }),
/*  867 */   OAK_LOG(new String[] { "LOG" }),
/*  868 */   OAK_PLANKS(new String[] { "WOOD" }),
/*  869 */   OAK_PRESSURE_PLATE(new String[] { "WOOD_PLATE" }),
/*  870 */   OAK_SAPLING(new String[] { "SAPLING" }),
/*  871 */   OAK_SIGN(new String[] { "SIGN_POST", "SIGN" }),
/*  872 */   OAK_SLAB(new String[] { "WOOD_DOUBLE_STEP", "WOOD_STEP", "WOODEN_SLAB" }),
/*  873 */   OAK_STAIRS(new String[] { "WOOD_STAIRS" }),
/*  874 */   OAK_TRAPDOOR(new String[] { "TRAP_DOOR" }),
/*  875 */   OAK_WALL_SIGN(new String[] { "WALL_SIGN" }),
/*  876 */   OAK_WOOD(new String[] { "LOG" }),
/*  877 */   OBSERVER(new String[0]),
/*  878 */   OBSIDIAN(new String[0]),
/*  879 */   OCELOT_SPAWN_EGG(98, new String[] { "MONSTER_EGG" }),
/*  880 */   ORANGE_BANNER(14, new String[] { "STANDING_BANNER", "BANNER" }),
/*  881 */   ORANGE_BED(supports(12) ? 1 : 0, new String[] { "BED_BLOCK", "BED" }),
/*  882 */   ORANGE_CANDLE(new String[0]),
/*  883 */   ORANGE_CANDLE_CAKE(new String[0]),
/*  884 */   ORANGE_CARPET(1, new String[] { "CARPET" }),
/*  885 */   ORANGE_CONCRETE(1, new String[] { "CONCRETE" }),
/*  886 */   ORANGE_CONCRETE_POWDER(1, new String[] { "CONCRETE_POWDER" }),
/*  887 */   ORANGE_DYE(14, new String[] { "INK_SACK" }),
/*  888 */   ORANGE_GLAZED_TERRACOTTA(new String[0]),
/*  889 */   ORANGE_SHULKER_BOX(new String[0]),
/*  890 */   ORANGE_STAINED_GLASS(1, new String[] { "STAINED_GLASS" }),
/*  891 */   ORANGE_STAINED_GLASS_PANE(1, new String[] { "STAINED_GLASS_PANE" }),
/*  892 */   ORANGE_TERRACOTTA(1, new String[] { "STAINED_CLAY" }),
/*  893 */   ORANGE_TULIP(5, new String[] { "RED_ROSE" }),
/*  894 */   ORANGE_WALL_BANNER(14, new String[] { "WALL_BANNER" }),
/*  895 */   ORANGE_WOOL(1, new String[] { "WOOL" }),
/*  896 */   OXEYE_DAISY(8, new String[] { "RED_ROSE" }),
/*  897 */   OXIDIZED_COPPER(new String[0]),
/*  898 */   OXIDIZED_CUT_COPPER(new String[0]),
/*  899 */   OXIDIZED_CUT_COPPER_SLAB(new String[0]),
/*  900 */   OXIDIZED_CUT_COPPER_STAIRS(new String[0]),
/*  901 */   PACKED_ICE(new String[0]),
/*  902 */   PAINTING(new String[0]),
/*  903 */   PANDA_SPAWN_EGG(new String[0]),
/*  904 */   PAPER(new String[0]),
/*  905 */   PARROT_SPAWN_EGG(105, new String[] { "MONSTER_EGG" }),
/*  906 */   PEONY(5, new String[] { "DOUBLE_PLANT" }),
/*  907 */   PETRIFIED_OAK_SLAB(new String[] { "WOOD_STEP" }),
/*  908 */   PHANTOM_MEMBRANE(new String[0]),
/*  909 */   PHANTOM_SPAWN_EGG(new String[0]),
/*  910 */   PIGLIN_BANNER_PATTERN(new String[0]),
/*  911 */   PIGLIN_BRUTE_SPAWN_EGG(new String[0]),
/*  912 */   PIGLIN_SPAWN_EGG(57, new String[] { "MONSTER_EGG" }),
/*  913 */   PIG_SPAWN_EGG(90, new String[] { "MONSTER_EGG" }),
/*  914 */   PILLAGER_SPAWN_EGG(new String[0]),
/*  915 */   PINK_BANNER(9, new String[] { "STANDING_BANNER", "BANNER" }),
/*  916 */   PINK_BED(supports(12) ? 6 : 0, new String[] { "BED_BLOCK", "BED" }),
/*  917 */   PINK_CANDLE(new String[0]),
/*  918 */   PINK_CANDLE_CAKE(new String[0]),
/*  919 */   PINK_CARPET(6, new String[] { "CARPET" }),
/*  920 */   PINK_CONCRETE(6, new String[] { "CONCRETE" }),
/*  921 */   PINK_CONCRETE_POWDER(6, new String[] { "CONCRETE_POWDER" }),
/*  922 */   PINK_DYE(9, new String[] { "INK_SACK" }),
/*  923 */   PINK_GLAZED_TERRACOTTA(new String[0]),
/*  924 */   PINK_SHULKER_BOX(new String[0]),
/*  925 */   PINK_STAINED_GLASS(6, new String[] { "STAINED_GLASS" }),
/*  926 */   PINK_STAINED_GLASS_PANE(6, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/*  927 */   PINK_TERRACOTTA(6, new String[] { "STAINED_CLAY" }),
/*  928 */   PINK_TULIP(7, new String[] { "RED_ROSE" }),
/*  929 */   PINK_WALL_BANNER(9, new String[] { "WALL_BANNER" }),
/*  930 */   PINK_WOOL(6, new String[] { "WOOL" }),
/*  931 */   PISTON(new String[] { "PISTON_BASE" }),
/*  932 */   PISTON_HEAD(new String[] { "PISTON_EXTENSION" }),
/*  933 */   PLAYER_HEAD(3, new String[] { "SKULL", "SKULL_ITEM" }),
/*  934 */   PLAYER_WALL_HEAD(3, new String[] { "SKULL", "SKULL_ITEM" }),
/*  935 */   PODZOL(2, new String[] { "DIRT" }),
/*  936 */   POINTED_DRIPSTONE(new String[0]),
/*  937 */   POISONOUS_POTATO(new String[0]),
/*  938 */   POLAR_BEAR_SPAWN_EGG(102, new String[] { "MONSTER_EGG" }),
/*  939 */   POLISHED_ANDESITE(6, new String[] { "STONE" }),
/*  940 */   POLISHED_ANDESITE_SLAB(new String[0]),
/*  941 */   POLISHED_ANDESITE_STAIRS(new String[0]),
/*  942 */   POLISHED_BASALT(new String[0]),
/*  943 */   POLISHED_BLACKSTONE(new String[0]),
/*  944 */   POLISHED_BLACKSTONE_BRICKS(new String[0]),
/*  945 */   POLISHED_BLACKSTONE_BRICK_SLAB(new String[0]),
/*  946 */   POLISHED_BLACKSTONE_BRICK_STAIRS(new String[0]),
/*  947 */   POLISHED_BLACKSTONE_BRICK_WALL(new String[0]),
/*  948 */   POLISHED_BLACKSTONE_BUTTON(new String[0]),
/*  949 */   POLISHED_BLACKSTONE_PRESSURE_PLATE(new String[0]),
/*  950 */   POLISHED_BLACKSTONE_SLAB(new String[0]),
/*  951 */   POLISHED_BLACKSTONE_STAIRS(new String[0]),
/*  952 */   POLISHED_BLACKSTONE_WALL(new String[0]),
/*  953 */   POLISHED_DEEPSLATE(new String[0]),
/*  954 */   POLISHED_DEEPSLATE_SLAB(new String[0]),
/*  955 */   POLISHED_DEEPSLATE_STAIRS(new String[0]),
/*  956 */   POLISHED_DEEPSLATE_WALL(new String[0]),
/*  957 */   POLISHED_DIORITE(4, new String[] { "STONE" }),
/*  958 */   POLISHED_DIORITE_SLAB(new String[0]),
/*  959 */   POLISHED_DIORITE_STAIRS(new String[0]),
/*  960 */   POLISHED_GRANITE(2, new String[] { "STONE" }),
/*  961 */   POLISHED_GRANITE_SLAB(new String[0]),
/*  962 */   POLISHED_GRANITE_STAIRS(new String[0]),
/*  963 */   POPPED_CHORUS_FRUIT(new String[] { "CHORUS_FRUIT_POPPED" }),
/*  964 */   POPPY(new String[] { "RED_ROSE" }),
/*  965 */   PORKCHOP(new String[] { "PORK" }),
/*  966 */   POTATO(new String[] { "POTATO_ITEM" }),
/*  967 */   POTATOES(new String[] { "POTATO" }),
/*  968 */   POTION(new String[0]),
/*  969 */   POTTED_ACACIA_SAPLING(4, new String[] { "FLOWER_POT" }),
/*  970 */   POTTED_ALLIUM(2, new String[] { "RED_ROSE", "FLOWER_POT" }),
/*  971 */   POTTED_AZALEA_BUSH(new String[0]),
/*  972 */   POTTED_AZURE_BLUET(3, new String[] { "RED_ROSE", "FLOWER_POT" }),
/*  973 */   POTTED_BAMBOO(new String[0]),
/*  974 */   POTTED_BIRCH_SAPLING(2, new String[] { "FLOWER_POT" }),
/*  975 */   POTTED_BLUE_ORCHID(1, new String[] { "RED_ROSE", "FLOWER_POT" }),
/*  976 */   POTTED_BROWN_MUSHROOM(new String[] { "FLOWER_POT" }),
/*  977 */   POTTED_CACTUS(new String[] { "FLOWER_POT" }),
/*  978 */   POTTED_CORNFLOWER(new String[0]),
/*  979 */   POTTED_CRIMSON_FUNGUS(new String[0]),
/*  980 */   POTTED_CRIMSON_ROOTS(new String[0]),
/*  981 */   POTTED_DANDELION(new String[] { "YELLOW_FLOWER", "FLOWER_POT" }),
/*  982 */   POTTED_DARK_OAK_SAPLING(5, new String[] { "FLOWER_POT" }),
/*  983 */   POTTED_DEAD_BUSH(new String[] { "FLOWER_POT" }),
/*  984 */   POTTED_FERN(2, new String[] { "LONG_GRASS", "FLOWER_POT" }),
/*  985 */   POTTED_FLOWERING_AZALEA_BUSH(new String[0]),
/*  986 */   POTTED_JUNGLE_SAPLING(3, new String[] { "FLOWER_POT" }),
/*  987 */   POTTED_LILY_OF_THE_VALLEY(new String[0]),
/*  988 */   POTTED_OAK_SAPLING(new String[] { "FLOWER_POT" }),
/*  989 */   POTTED_ORANGE_TULIP(5, new String[] { "RED_ROSE", "FLOWER_POT" }),
/*  990 */   POTTED_OXEYE_DAISY(8, new String[] { "RED_ROSE", "FLOWER_POT" }),
/*  991 */   POTTED_PINK_TULIP(7, new String[] { "RED_ROSE", "FLOWER_POT" }),
/*  992 */   POTTED_POPPY(new String[] { "RED_ROSE", "FLOWER_POT" }),
/*  993 */   POTTED_RED_MUSHROOM(new String[] { "FLOWER_POT" }),
/*  994 */   POTTED_RED_TULIP(4, new String[] { "RED_ROSE", "FLOWER_POT" }),
/*  995 */   POTTED_SPRUCE_SAPLING(1, new String[] { "FLOWER_POT" }),
/*  996 */   POTTED_WARPED_FUNGUS(new String[0]),
/*  997 */   POTTED_WARPED_ROOTS(new String[0]),
/*  998 */   POTTED_WHITE_TULIP(6, new String[] { "RED_ROSE", "FLOWER_POT" }),
/*  999 */   POTTED_WITHER_ROSE(new String[0]),
/* 1000 */   POWDER_SNOW(new String[0]),
/* 1001 */   POWDER_SNOW_BUCKET(new String[0]),
/* 1002 */   POWDER_SNOW_CAULDRON(new String[0]),
/* 1003 */   POWERED_RAIL(new String[0]),
/* 1004 */   PRISMARINE(new String[0]),
/* 1005 */   PRISMARINE_BRICKS(2, new String[] { "PRISMARINE" }),
/* 1006 */   PRISMARINE_BRICK_SLAB(4, new String[] { "STEP" }),
/* 1007 */   PRISMARINE_BRICK_STAIRS(new String[0]),
/* 1008 */   PRISMARINE_CRYSTALS(new String[0]),
/* 1009 */   PRISMARINE_SHARD(new String[0]),
/* 1010 */   PRISMARINE_SLAB(new String[0]),
/* 1011 */   PRISMARINE_STAIRS(new String[0]),
/* 1012 */   PRISMARINE_WALL(new String[0]),
/* 1013 */   PUFFERFISH(3, new String[] { "RAW_FISH" }),
/* 1014 */   PUFFERFISH_BUCKET(new String[0]),
/* 1015 */   PUFFERFISH_SPAWN_EGG(new String[0]),
/* 1016 */   PUMPKIN(new String[0]),
/* 1017 */   PUMPKIN_PIE(new String[0]),
/* 1018 */   PUMPKIN_SEEDS(new String[0]),
/* 1019 */   PUMPKIN_STEM(new String[0]),
/* 1020 */   PURPLE_BANNER(5, new String[] { "STANDING_BANNER", "BANNER" }),
/* 1021 */   PURPLE_BED(supports(12) ? 10 : 0, new String[] { "BED_BLOCK", "BED" }),
/* 1022 */   PURPLE_CANDLE(new String[0]),
/* 1023 */   PURPLE_CANDLE_CAKE(new String[0]),
/* 1024 */   PURPLE_CARPET(10, new String[] { "CARPET" }),
/* 1025 */   PURPLE_CONCRETE(10, new String[] { "CONCRETE" }),
/* 1026 */   PURPLE_CONCRETE_POWDER(10, new String[] { "CONCRETE_POWDER" }),
/* 1027 */   PURPLE_DYE(5, new String[] { "INK_SACK" }),
/* 1028 */   PURPLE_GLAZED_TERRACOTTA(new String[0]),
/* 1029 */   PURPLE_SHULKER_BOX(new String[0]),
/* 1030 */   PURPLE_STAINED_GLASS(10, new String[] { "STAINED_GLASS" }),
/* 1031 */   PURPLE_STAINED_GLASS_PANE(10, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/* 1032 */   PURPLE_TERRACOTTA(10, new String[] { "STAINED_CLAY" }),
/* 1033 */   PURPLE_WALL_BANNER(5, new String[] { "WALL_BANNER" }),
/* 1034 */   PURPLE_WOOL(10, new String[] { "WOOL" }),
/* 1035 */   PURPUR_BLOCK(new String[0]),
/* 1036 */   PURPUR_PILLAR(new String[0]),
/* 1037 */   PURPUR_SLAB(new String[] { "PURPUR_DOUBLE_SLAB" }),
/* 1038 */   PURPUR_STAIRS(new String[0]),
/* 1039 */   QUARTZ(new String[0]),
/* 1040 */   QUARTZ_BLOCK(new String[0]),
/* 1041 */   QUARTZ_BRICKS(new String[0]),
/* 1042 */   QUARTZ_PILLAR(2, new String[] { "QUARTZ_BLOCK" }),
/* 1043 */   QUARTZ_SLAB(7, new String[] { "STEP" }),
/* 1044 */   QUARTZ_STAIRS(new String[0]),
/* 1045 */   RABBIT(new String[0]),
/* 1046 */   RABBIT_FOOT(new String[0]),
/* 1047 */   RABBIT_HIDE(new String[0]),
/* 1048 */   RABBIT_SPAWN_EGG(101, new String[] { "MONSTER_EGG" }),
/* 1049 */   RABBIT_STEW(new String[0]),
/* 1050 */   RAIL(new String[] { "RAILS" }),
/* 1051 */   RAVAGER_SPAWN_EGG(new String[0]),
/* 1052 */   RAW_COPPER(new String[0]),
/* 1053 */   RAW_COPPER_BLOCK(new String[0]),
/* 1054 */   RAW_GOLD(new String[0]),
/* 1055 */   RAW_GOLD_BLOCK(new String[0]),
/* 1056 */   RAW_IRON(new String[0]),
/* 1057 */   RAW_IRON_BLOCK(new String[0]),
/* 1058 */   REDSTONE(new String[0]),
/* 1059 */   REDSTONE_BLOCK(new String[0]),
/* 1060 */   REDSTONE_LAMP(new String[] {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1066 */       "REDSTONE_LAMP_ON", "REDSTONE_LAMP_OFF" }),
/* 1067 */   REDSTONE_ORE(new String[] { "GLOWING_REDSTONE_ORE" }),
/* 1068 */   REDSTONE_TORCH(new String[] {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1073 */       "REDSTONE_TORCH_OFF", "REDSTONE_TORCH_ON" }),
/* 1074 */   REDSTONE_WALL_TORCH(new String[0]),
/* 1075 */   REDSTONE_WIRE(new String[0]),
/* 1076 */   RED_BANNER(1, new String[] { "STANDING_BANNER", "BANNER" }),
/* 1077 */   RED_BED(
/*      */ 
/*      */     
/* 1080 */     supports(12) ? 14 : 0, new String[] { "BED_BLOCK", "BED" }),
/* 1081 */   RED_CANDLE(new String[0]),
/* 1082 */   RED_CANDLE_CAKE(new String[0]),
/* 1083 */   RED_CARPET(14, new String[] { "CARPET" }),
/* 1084 */   RED_CONCRETE(14, new String[] { "CONCRETE" }),
/* 1085 */   RED_CONCRETE_POWDER(14, new String[] { "CONCRETE_POWDER" }),
/* 1086 */   RED_DYE(1, new String[] { "INK_SACK", "ROSE_RED" }),
/* 1087 */   RED_GLAZED_TERRACOTTA(new String[0]),
/* 1088 */   RED_MUSHROOM(new String[0]),
/* 1089 */   RED_MUSHROOM_BLOCK(new String[] { "RED_MUSHROOM", "HUGE_MUSHROOM_2" }),
/* 1090 */   RED_NETHER_BRICKS(new String[] { "RED_NETHER_BRICK" }),
/* 1091 */   RED_NETHER_BRICK_SLAB(4, new String[] { "STEP" }),
/* 1092 */   RED_NETHER_BRICK_STAIRS(new String[0]),
/* 1093 */   RED_NETHER_BRICK_WALL(new String[0]),
/* 1094 */   RED_SAND(1, new String[] { "SAND" }),
/* 1095 */   RED_SANDSTONE(new String[0]),
/* 1096 */   RED_SANDSTONE_SLAB(new String[] { "DOUBLE_STONE_SLAB2", "STONE_SLAB2" }),
/* 1097 */   RED_SANDSTONE_STAIRS(new String[0]),
/* 1098 */   RED_SANDSTONE_WALL(new String[0]),
/* 1099 */   RED_SHULKER_BOX(new String[0]),
/* 1100 */   RED_STAINED_GLASS(14, new String[] { "STAINED_GLASS" }),
/* 1101 */   RED_STAINED_GLASS_PANE(14, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/* 1102 */   RED_TERRACOTTA(14, new String[] { "STAINED_CLAY" }),
/* 1103 */   RED_TULIP(4, new String[] { "RED_ROSE" }),
/* 1104 */   RED_WALL_BANNER(1, new String[] { "WALL_BANNER" }),
/* 1105 */   RED_WOOL(14, new String[] { "WOOL" }),
/* 1106 */   REPEATER(new String[] { "DIODE_BLOCK_ON", "DIODE_BLOCK_OFF", "DIODE" }),
/* 1107 */   REPEATING_COMMAND_BLOCK(new String[] { "COMMAND", "COMMAND_REPEATING" }),
/* 1108 */   RESPAWN_ANCHOR(new String[0]),
/* 1109 */   ROOTED_DIRT(new String[0]),
/* 1110 */   ROSE_BUSH(4, new String[] { "DOUBLE_PLANT" }),
/* 1111 */   ROTTEN_FLESH(new String[0]),
/* 1112 */   SADDLE(new String[0]),
/* 1113 */   SALMON(1, new String[] { "RAW_FISH" }),
/* 1114 */   SALMON_BUCKET(new String[0]),
/* 1115 */   SALMON_SPAWN_EGG(new String[0]),
/* 1116 */   SAND(new String[0]),
/* 1117 */   SANDSTONE(new String[0]),
/* 1118 */   SANDSTONE_SLAB(1, new String[] { "DOUBLE_STEP", "STEP", "STONE_SLAB" }),
/* 1119 */   SANDSTONE_STAIRS(new String[0]),
/* 1120 */   SANDSTONE_WALL(new String[0]),
/* 1121 */   SCAFFOLDING(new String[0]),
/* 1122 */   SCULK_SENSOR(new String[0]),
/* 1123 */   SCUTE(new String[0]),
/* 1124 */   SEAGRASS(new String[0]),
/* 1125 */   SEA_LANTERN(new String[0]),
/* 1126 */   SEA_PICKLE(new String[0]),
/* 1127 */   SHEARS(new String[0]),
/* 1128 */   SHEEP_SPAWN_EGG(91, new String[] { "MONSTER_EGG" }),
/* 1129 */   SHIELD(new String[0]),
/* 1130 */   SHROOMLIGHT(new String[0]),
/* 1131 */   SHULKER_BOX(new String[] { "PURPLE_SHULKER_BOX" }),
/* 1132 */   SHULKER_SHELL(new String[0]),
/* 1133 */   SHULKER_SPAWN_EGG(69, new String[] { "MONSTER_EGG" }),
/* 1134 */   SILVERFISH_SPAWN_EGG(60, new String[] { "MONSTER_EGG" }),
/* 1135 */   SKELETON_HORSE_SPAWN_EGG(28, new String[] { "MONSTER_EGG" }),
/* 1136 */   SKELETON_SKULL(new String[] { "SKULL", "SKULL_ITEM" }),
/* 1137 */   SKELETON_SPAWN_EGG(51, new String[] { "MONSTER_EGG" }),
/* 1138 */   SKELETON_WALL_SKULL(new String[] { "SKULL", "SKULL_ITEM" }),
/* 1139 */   SKULL_BANNER_PATTERN(new String[0]),
/* 1140 */   SLIME_BALL(new String[0]),
/* 1141 */   SLIME_BLOCK(new String[0]),
/* 1142 */   SLIME_SPAWN_EGG(55, new String[] { "MONSTER_EGG" }),
/* 1143 */   SMALL_AMETHYST_BUD(new String[0]),
/* 1144 */   SMALL_DRIPLEAF(new String[0]),
/* 1145 */   SMITHING_TABLE(new String[0]),
/* 1146 */   SMOKER(new String[0]),
/* 1147 */   SMOOTH_BASALT(new String[0]),
/* 1148 */   SMOOTH_QUARTZ(new String[0]),
/* 1149 */   SMOOTH_QUARTZ_SLAB(7, new String[] { "STEP" }),
/* 1150 */   SMOOTH_QUARTZ_STAIRS(new String[0]),
/* 1151 */   SMOOTH_RED_SANDSTONE(2, new String[] { "RED_SANDSTONE" }),
/* 1152 */   SMOOTH_RED_SANDSTONE_SLAB(new String[] { "STONE_SLAB2" }),
/* 1153 */   SMOOTH_RED_SANDSTONE_STAIRS(new String[0]),
/* 1154 */   SMOOTH_SANDSTONE(2, new String[] { "SANDSTONE" }),
/* 1155 */   SMOOTH_SANDSTONE_SLAB(new String[] { "STEP" }),
/* 1156 */   SMOOTH_SANDSTONE_STAIRS(new String[0]),
/* 1157 */   SMOOTH_STONE(new String[] { "STEP" }),
/* 1158 */   SMOOTH_STONE_SLAB(new String[] { "STEP" }),
/* 1159 */   SNOW(new String[0]),
/* 1160 */   SNOWBALL(new String[] { "SNOW_BALL" }),
/* 1161 */   SNOW_BLOCK(new String[0]),
/* 1162 */   SOUL_CAMPFIRE(new String[0]),
/* 1163 */   SOUL_FIRE(new String[0]),
/* 1164 */   SOUL_LANTERN(new String[0]),
/* 1165 */   SOUL_SAND(new String[0]),
/* 1166 */   SOUL_SOIL(new String[0]),
/* 1167 */   SOUL_TORCH(new String[0]),
/* 1168 */   SOUL_WALL_TORCH(new String[0]),
/* 1169 */   SPAWNER(new String[] { "MOB_SPAWNER" }),
/* 1170 */   SPECTRAL_ARROW(new String[0]),
/* 1171 */   SPIDER_EYE(new String[0]),
/* 1172 */   SPIDER_SPAWN_EGG(52, new String[] { "MONSTER_EGG" }),
/* 1173 */   SPLASH_POTION(new String[0]),
/* 1174 */   SPONGE(new String[0]),
/* 1175 */   SPORE_BLOSSOM(new String[0]),
/* 1176 */   SPRUCE_BOAT(new String[] { "BOAT_SPRUCE" }),
/* 1177 */   SPRUCE_BUTTON(new String[] { "WOOD_BUTTON" }),
/* 1178 */   SPRUCE_DOOR(new String[] { "SPRUCE_DOOR", "SPRUCE_DOOR_ITEM" }),
/* 1179 */   SPRUCE_FENCE(new String[0]),
/* 1180 */   SPRUCE_FENCE_GATE(new String[0]),
/* 1181 */   SPRUCE_LEAVES(1, new String[] { "LEAVES" }),
/* 1182 */   SPRUCE_LOG(1, new String[] { "LOG" }),
/* 1183 */   SPRUCE_PLANKS(1, new String[] { "WOOD" }),
/* 1184 */   SPRUCE_PRESSURE_PLATE(new String[] { "WOOD_PLATE" }),
/* 1185 */   SPRUCE_SAPLING(1, new String[] { "SAPLING" }),
/* 1186 */   SPRUCE_SIGN(new String[] { "SIGN_POST", "SIGN" }),
/* 1187 */   SPRUCE_SLAB(1, new String[] { "WOOD_DOUBLE_STEP", "WOOD_STEP", "WOODEN_SLAB" }),
/* 1188 */   SPRUCE_STAIRS(new String[] { "SPRUCE_WOOD_STAIRS" }),
/* 1189 */   SPRUCE_TRAPDOOR(new String[] { "TRAP_DOOR" }),
/* 1190 */   SPRUCE_WALL_SIGN(new String[] { "WALL_SIGN" }),
/* 1191 */   SPRUCE_WOOD(1, new String[] { "LOG" }),
/* 1192 */   SPYGLASS(new String[0]),
/* 1193 */   SQUID_SPAWN_EGG(94, new String[] { "MONSTER_EGG" }),
/* 1194 */   STICK(new String[0]),
/* 1195 */   STICKY_PISTON(new String[] { "PISTON_BASE", "PISTON_STICKY_BASE" }),
/* 1196 */   STONE(new String[0]),
/* 1197 */   STONECUTTER(new String[0]),
/* 1198 */   STONE_AXE(new String[0]),
/* 1199 */   STONE_BRICKS(new String[] { "SMOOTH_BRICK" }),
/* 1200 */   STONE_BRICK_SLAB(4, new String[] { "DOUBLE_STEP", "STEP", "STONE_SLAB" }),
/* 1201 */   STONE_BRICK_STAIRS(new String[] { "SMOOTH_STAIRS" }),
/* 1202 */   STONE_BRICK_WALL(new String[0]),
/* 1203 */   STONE_BUTTON(new String[0]),
/* 1204 */   STONE_HOE(new String[0]),
/* 1205 */   STONE_PICKAXE(new String[0]),
/* 1206 */   STONE_PRESSURE_PLATE(new String[] { "STONE_PLATE" }),
/* 1207 */   STONE_SHOVEL(new String[] { "STONE_SPADE" }),
/* 1208 */   STONE_SLAB(new String[] { "DOUBLE_STEP", "STEP" }),
/* 1209 */   STONE_STAIRS(new String[0]),
/* 1210 */   STONE_SWORD(new String[0]),
/* 1211 */   STRAY_SPAWN_EGG(6, new String[] { "MONSTER_EGG" }),
/* 1212 */   STRIDER_SPAWN_EGG(new String[0]),
/* 1213 */   STRING(new String[0]),
/* 1214 */   STRIPPED_ACACIA_LOG(new String[] { "LOG_2" }),
/* 1215 */   STRIPPED_ACACIA_WOOD(new String[] { "LOG_2" }),
/* 1216 */   STRIPPED_BIRCH_LOG(2, new String[] { "LOG" }),
/* 1217 */   STRIPPED_BIRCH_WOOD(2, new String[] { "LOG" }),
/* 1218 */   STRIPPED_CRIMSON_HYPHAE(new String[0]),
/* 1219 */   STRIPPED_CRIMSON_STEM(new String[0]),
/* 1220 */   STRIPPED_DARK_OAK_LOG(new String[] { "LOG" }),
/* 1221 */   STRIPPED_DARK_OAK_WOOD(new String[] { "LOG" }),
/* 1222 */   STRIPPED_JUNGLE_LOG(3, new String[] { "LOG" }),
/* 1223 */   STRIPPED_JUNGLE_WOOD(3, new String[] { "LOG" }),
/* 1224 */   STRIPPED_OAK_LOG(new String[] { "LOG" }),
/* 1225 */   STRIPPED_OAK_WOOD(new String[] { "LOG" }),
/* 1226 */   STRIPPED_SPRUCE_LOG(1, new String[] { "LOG" }),
/* 1227 */   STRIPPED_SPRUCE_WOOD(1, new String[] { "LOG" }),
/* 1228 */   STRIPPED_WARPED_HYPHAE(new String[0]),
/* 1229 */   STRIPPED_WARPED_STEM(new String[0]),
/* 1230 */   STRUCTURE_BLOCK(new String[0]),
/* 1231 */   STRUCTURE_VOID(
/*      */ 
/*      */ 
/*      */     
/* 1235 */     10, new String[] { "BARRIER" }),
/* 1236 */   SUGAR(new String[0]),
/* 1237 */   SUGAR_CANE(new String[] {
/*      */ 
/*      */       
/* 1240 */       "SUGAR_CANE_BLOCK" }),
/* 1241 */   SUNFLOWER(new String[] { "DOUBLE_PLANT" }),
/* 1242 */   SUSPICIOUS_STEW(new String[0]),
/* 1243 */   SWEET_BERRIES(new String[0]),
/* 1244 */   SWEET_BERRY_BUSH(new String[0]),
/* 1245 */   TALL_GRASS(2, new String[] { "DOUBLE_PLANT" }),
/* 1246 */   TALL_SEAGRASS(new String[0]),
/* 1247 */   TARGET(new String[0]),
/* 1248 */   TERRACOTTA(new String[] { "HARD_CLAY" }),
/* 1249 */   TINTED_GLASS(new String[0]),
/* 1250 */   TIPPED_ARROW(new String[0]),
/* 1251 */   TNT(new String[0]),
/* 1252 */   TNT_MINECART(new String[] { "EXPLOSIVE_MINECART" }),
/* 1253 */   TORCH(new String[0]),
/* 1254 */   TOTEM_OF_UNDYING(new String[] { "TOTEM" }),
/* 1255 */   TRADER_LLAMA_SPAWN_EGG(new String[0]),
/* 1256 */   TRAPPED_CHEST(new String[0]),
/* 1257 */   TRIDENT(new String[0]),
/* 1258 */   TRIPWIRE(new String[0]),
/* 1259 */   TRIPWIRE_HOOK(new String[0]),
/* 1260 */   TROPICAL_FISH(2, new String[] { "RAW_FISH" }),
/* 1261 */   TROPICAL_FISH_BUCKET(new String[] { "BUCKET", "WATER_BUCKET" }),
/* 1262 */   TROPICAL_FISH_SPAWN_EGG(new String[] { "MONSTER_EGG" }),
/* 1263 */   TUBE_CORAL(new String[0]),
/* 1264 */   TUBE_CORAL_BLOCK(new String[0]),
/* 1265 */   TUBE_CORAL_FAN(new String[0]),
/* 1266 */   TUBE_CORAL_WALL_FAN(new String[0]),
/* 1267 */   TUFF(new String[0]),
/* 1268 */   TURTLE_EGG(new String[0]),
/* 1269 */   TURTLE_HELMET(new String[0]),
/* 1270 */   TURTLE_SPAWN_EGG(new String[0]),
/* 1271 */   TWISTING_VINES(new String[0]),
/* 1272 */   TWISTING_VINES_PLANT(new String[0]),
/* 1273 */   VEX_SPAWN_EGG(35, new String[] { "MONSTER_EGG" }),
/* 1274 */   VILLAGER_SPAWN_EGG(120, new String[] { "MONSTER_EGG" }),
/* 1275 */   VINDICATOR_SPAWN_EGG(36, new String[] { "MONSTER_EGG" }),
/* 1276 */   VINE(new String[0]),
/* 1277 */   VOID_AIR(new String[] {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1282 */       "AIR" }),
/* 1283 */   WALL_TORCH(new String[] { "TORCH" }),
/* 1284 */   WANDERING_TRADER_SPAWN_EGG(new String[0]),
/* 1285 */   WARPED_BUTTON(new String[0]),
/* 1286 */   WARPED_DOOR(new String[0]),
/* 1287 */   WARPED_FENCE(new String[0]),
/* 1288 */   WARPED_FENCE_GATE(new String[0]),
/* 1289 */   WARPED_FUNGUS(new String[0]),
/* 1290 */   WARPED_FUNGUS_ON_A_STICK(new String[0]),
/* 1291 */   WARPED_HYPHAE(new String[0]),
/* 1292 */   WARPED_NYLIUM(new String[0]),
/* 1293 */   WARPED_PLANKS(new String[0]),
/* 1294 */   WARPED_PRESSURE_PLATE(new String[0]),
/* 1295 */   WARPED_ROOTS(new String[0]),
/* 1296 */   WARPED_SIGN(new String[] { "SIGN_POST" }),
/* 1297 */   WARPED_SLAB(new String[0]),
/* 1298 */   WARPED_STAIRS(new String[0]),
/* 1299 */   WARPED_STEM(new String[0]),
/* 1300 */   WARPED_TRAPDOOR(new String[0]),
/* 1301 */   WARPED_WALL_SIGN(new String[] { "WALL_SIGN" }),
/* 1302 */   WARPED_WART_BLOCK(new String[0]),
/* 1303 */   WATER(new String[] {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1309 */       "STATIONARY_WATER" }),
/* 1310 */   WATER_BUCKET(new String[0]),
/* 1311 */   WATER_CAULDRON(new String[0]),
/* 1312 */   WAXED_COPPER_BLOCK(new String[0]),
/* 1313 */   WAXED_CUT_COPPER(new String[0]),
/* 1314 */   WAXED_CUT_COPPER_SLAB(new String[0]),
/* 1315 */   WAXED_CUT_COPPER_STAIRS(new String[0]),
/* 1316 */   WAXED_EXPOSED_COPPER(new String[0]),
/* 1317 */   WAXED_EXPOSED_CUT_COPPER(new String[0]),
/* 1318 */   WAXED_EXPOSED_CUT_COPPER_SLAB(new String[0]),
/* 1319 */   WAXED_EXPOSED_CUT_COPPER_STAIRS(new String[0]),
/* 1320 */   WAXED_OXIDIZED_COPPER(new String[0]),
/* 1321 */   WAXED_OXIDIZED_CUT_COPPER(new String[0]),
/* 1322 */   WAXED_OXIDIZED_CUT_COPPER_SLAB(new String[0]),
/* 1323 */   WAXED_OXIDIZED_CUT_COPPER_STAIRS(new String[0]),
/* 1324 */   WAXED_WEATHERED_COPPER(new String[0]),
/* 1325 */   WAXED_WEATHERED_CUT_COPPER(new String[0]),
/* 1326 */   WAXED_WEATHERED_CUT_COPPER_SLAB(new String[0]),
/* 1327 */   WAXED_WEATHERED_CUT_COPPER_STAIRS(new String[0]),
/* 1328 */   WEATHERED_COPPER(new String[0]),
/* 1329 */   WEATHERED_CUT_COPPER(new String[0]),
/* 1330 */   WEATHERED_CUT_COPPER_SLAB(new String[0]),
/* 1331 */   WEATHERED_CUT_COPPER_STAIRS(new String[0]),
/* 1332 */   WEEPING_VINES(new String[0]),
/* 1333 */   WEEPING_VINES_PLANT(new String[0]),
/* 1334 */   WET_SPONGE(1, new String[] { "SPONGE" }),
/* 1335 */   WHEAT(new String[] {
/*      */ 
/*      */       
/* 1338 */       "CROPS" }),
/* 1339 */   WHEAT_SEEDS(new String[] { "SEEDS" }),
/* 1340 */   WHITE_BANNER(15, new String[] { "STANDING_BANNER", "BANNER" }),
/* 1341 */   WHITE_BED(new String[] { "BED_BLOCK", "BED" }),
/* 1342 */   WHITE_CANDLE(new String[0]),
/* 1343 */   WHITE_CANDLE_CAKE(new String[0]),
/* 1344 */   WHITE_CARPET(new String[] { "CARPET" }),
/* 1345 */   WHITE_CONCRETE(new String[] { "CONCRETE" }),
/* 1346 */   WHITE_CONCRETE_POWDER(new String[] { "CONCRETE_POWDER" }),
/* 1347 */   WHITE_DYE(15, new String[] { "INK_SACK", "BONE_MEAL" }),
/* 1348 */   WHITE_GLAZED_TERRACOTTA(new String[] { "STAINED_CLAY" }),
/* 1349 */   WHITE_SHULKER_BOX(new String[0]),
/* 1350 */   WHITE_STAINED_GLASS(new String[] { "STAINED_GLASS" }),
/* 1351 */   WHITE_STAINED_GLASS_PANE(new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/* 1352 */   WHITE_TERRACOTTA(new String[] { "STAINED_CLAY" }),
/* 1353 */   WHITE_TULIP(6, new String[] { "RED_ROSE" }),
/* 1354 */   WHITE_WALL_BANNER(15, new String[] { "WALL_BANNER" }),
/* 1355 */   WHITE_WOOL(new String[] { "WOOL" }),
/* 1356 */   WITCH_SPAWN_EGG(66, new String[] { "MONSTER_EGG" }),
/* 1357 */   WITHER_ROSE(new String[0]),
/* 1358 */   WITHER_SKELETON_SKULL(1, new String[] { "SKULL", "SKULL_ITEM" }),
/* 1359 */   WITHER_SKELETON_SPAWN_EGG(5, new String[] { "MONSTER_EGG" }),
/* 1360 */   WITHER_SKELETON_WALL_SKULL(1, new String[] { "SKULL", "SKULL_ITEM" }),
/* 1361 */   WOLF_SPAWN_EGG(95, new String[] { "MONSTER_EGG" }),
/* 1362 */   WOODEN_AXE(new String[] { "WOOD_AXE" }),
/* 1363 */   WOODEN_HOE(new String[] { "WOOD_HOE" }),
/* 1364 */   WOODEN_PICKAXE(new String[] { "WOOD_PICKAXE" }),
/* 1365 */   WOODEN_SHOVEL(new String[] { "WOOD_SPADE" }),
/* 1366 */   WOODEN_SWORD(new String[] { "WOOD_SWORD" }),
/* 1367 */   WRITABLE_BOOK(new String[] { "BOOK_AND_QUILL" }),
/* 1368 */   WRITTEN_BOOK(new String[0]),
/* 1369 */   YELLOW_BANNER(11, new String[] { "STANDING_BANNER", "BANNER" }),
/* 1370 */   YELLOW_BED(supports(12) ? 4 : 0, new String[] { "BED_BLOCK", "BED" }),
/* 1371 */   YELLOW_CANDLE(new String[0]),
/* 1372 */   YELLOW_CANDLE_CAKE(new String[0]),
/* 1373 */   YELLOW_CARPET(4, new String[] { "CARPET" }),
/* 1374 */   YELLOW_CONCRETE(4, new String[] { "CONCRETE" }),
/* 1375 */   YELLOW_CONCRETE_POWDER(4, new String[] { "CONCRETE_POWDER" }),
/* 1376 */   YELLOW_DYE(11, new String[] { "INK_SACK", "DANDELION_YELLOW" }),
/* 1377 */   YELLOW_GLAZED_TERRACOTTA(4, new String[] { "STAINED_CLAY", "YELLOW_TERRACOTTA" }),
/* 1378 */   YELLOW_SHULKER_BOX(new String[0]),
/* 1379 */   YELLOW_STAINED_GLASS(4, new String[] { "STAINED_GLASS" }),
/* 1380 */   YELLOW_STAINED_GLASS_PANE(4, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/* 1381 */   YELLOW_TERRACOTTA(4, new String[] { "STAINED_CLAY" }),
/* 1382 */   YELLOW_WALL_BANNER(11, new String[] { "WALL_BANNER" }),
/* 1383 */   YELLOW_WOOL(4, new String[] { "WOOL" }),
/* 1384 */   ZOGLIN_SPAWN_EGG(new String[0]),
/* 1385 */   ZOMBIE_HEAD(2, new String[] { "SKULL", "SKULL_ITEM" }),
/* 1386 */   ZOMBIE_HORSE_SPAWN_EGG(29, new String[] { "MONSTER_EGG" }),
/* 1387 */   ZOMBIE_SPAWN_EGG(54, new String[] { "MONSTER_EGG" }),
/* 1388 */   ZOMBIE_VILLAGER_SPAWN_EGG(27, new String[] { "MONSTER_EGG" }),
/* 1389 */   ZOMBIE_WALL_HEAD(2, new String[] { "SKULL", "SKULL_ITEM" }),
/* 1390 */   ZOMBIFIED_PIGLIN_SPAWN_EGG(57, new String[] { "MONSTER_EGG", "ZOMBIE_PIGMAN_SPAWN_EGG" });
/*      */   
/*      */   public static final XMaterial[] VALUES;
/*      */   private static final Map<String, XMaterial> NAMES;
/*      */   private static final Cache<String, XMaterial> NAME_CACHE;
/*      */   private static final Cache<String, Pattern> CACHED_REGEX;
/*      */   private static final byte MAX_DATA_VALUE = 120;
/*      */   
/*      */   static {
/* 1399 */     VALUES = values();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1406 */     NAMES = new HashMap<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1414 */     NAME_CACHE = CacheBuilder.newBuilder()
/* 1415 */       .expireAfterAccess(1L, TimeUnit.HOURS)
/* 1416 */       .build();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1422 */     CACHED_REGEX = CacheBuilder.newBuilder()
/* 1423 */       .expireAfterAccess(3L, TimeUnit.HOURS)
/* 1424 */       .build();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     byte b;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     int i;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     XMaterial[] arrayOfXMaterial;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1460 */     for (i = (arrayOfXMaterial = VALUES).length, b = 0; b < i; ) { XMaterial material = arrayOfXMaterial[b]; NAMES.put(material.name(), material); b++; }
/*      */ 
/*      */ 
/*      */     
/* 1464 */     if (Data.ISFLAT) {
/*      */       
/* 1466 */       DUPLICATED = null;
/*      */     }
/*      */     else {
/*      */       
/* 1470 */       DUPLICATED = new HashSet<>(4);
/* 1471 */       DUPLICATED.add(GRASS.name());
/* 1472 */       DUPLICATED.add(MELON.name());
/* 1473 */       DUPLICATED.add(BRICK.name());
/* 1474 */       DUPLICATED.add(NETHER_BRICK.name());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static final byte UNKNOWN_DATA_VALUE = -1;
/*      */ 
/*      */   
/*      */   private static final short MAX_ID = 2267;
/*      */ 
/*      */   
/*      */   private static final Set<String> DUPLICATED;
/*      */ 
/*      */   
/*      */   private final byte data;
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   private final String[] legacy;
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   private final Material material;
/*      */ 
/*      */ 
/*      */   
/*      */   XMaterial(int data, String... legacy) {
/* 1502 */     this.data = (byte)data;
/* 1503 */     this.legacy = legacy;
/*      */     
/* 1505 */     Material mat = null;
/* 1506 */     if ((!Data.ISFLAT && isDuplicated()) || (mat = Material.getMaterial(name())) == null)
/* 1507 */       for (int i = legacy.length - 1; i >= 0; i--) {
/* 1508 */         mat = Material.getMaterial(legacy[i]);
/* 1509 */         if (mat != null)
/*      */           break; 
/*      */       }  
/* 1512 */     this.material = mat;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static boolean isNewVersion() {
/* 1533 */     return Data.ISFLAT;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static boolean isOneEight() {
/* 1553 */     return !supports(9);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   private static Optional<XMaterial> getIfPresent(@Nonnull String name) {
/* 1567 */     return Optional.ofNullable(NAMES.get(name));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getVersion() {
/* 1578 */     return Data.VERSION;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   private static XMaterial requestOldXMaterial(@Nonnull String name, byte data) {
/* 1590 */     String holder = String.valueOf(name) + data;
/* 1591 */     XMaterial cache = (XMaterial)NAME_CACHE.getIfPresent(holder);
/* 1592 */     if (cache != null) return cache;  byte b; int i;
/*      */     XMaterial[] arrayOfXMaterial;
/* 1594 */     for (i = (arrayOfXMaterial = VALUES).length, b = 0; b < i; ) { XMaterial material = arrayOfXMaterial[b];
/*      */       
/* 1596 */       if ((data == -1 || data == material.data) && material.anyMatchLegacy(name)) {
/* 1597 */         NAME_CACHE.put(holder, material);
/* 1598 */         return material;
/*      */       } 
/*      */       b++; }
/*      */     
/* 1602 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   public static Optional<XMaterial> matchXMaterial(@Nonnull String name) {
/* 1615 */     Validate.notEmpty(name, "Cannot match a material with null or empty material name");
/* 1616 */     Optional<XMaterial> oldMatch = matchXMaterialWithData(name);
/* 1617 */     return oldMatch.isPresent() ? oldMatch : matchDefinedXMaterial(format(name), (byte)-1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   private static Optional<XMaterial> matchXMaterialWithData(@Nonnull String name) {
/* 1639 */     int index = name.indexOf(':');
/* 1640 */     if (index != -1) {
/* 1641 */       String mat = format(name.substring(0, index));
/*      */       
/*      */       try {
/* 1644 */         byte data = (byte)Integer.parseInt(StringUtils.deleteWhitespace(name.substring(index + 1)));
/* 1645 */         return (data >= 0 && data < 120) ? matchDefinedXMaterial(mat, data) : matchDefinedXMaterial(mat, (byte)-1);
/* 1646 */       } catch (NumberFormatException ignored) {
/* 1647 */         return matchDefinedXMaterial(mat, (byte)-1);
/*      */       } 
/*      */     } 
/*      */     
/* 1651 */     return Optional.empty();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   public static XMaterial matchXMaterial(@Nonnull Material material) {
/* 1664 */     Objects.requireNonNull(material, "Cannot match null material");
/* 1665 */     return matchDefinedXMaterial(material.name(), (byte)-1)
/* 1666 */       .<Throwable>orElseThrow(() -> new IllegalArgumentException("Unsupported material with no data value: " + paramMaterial.name()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   public static XMaterial matchXMaterial(@Nonnull ItemStack item) {
/* 1683 */     Objects.requireNonNull(item, "Cannot match null ItemStack");
/* 1684 */     String material = item.getType().name();
/* 1685 */     byte data = (byte)((Data.ISFLAT || item.getType().getMaxDurability() > 0) ? 0 : item.getDurability());
/*      */     
/* 1687 */     if (!Data.ISFLAT && item.hasItemMeta() && material.equals("MONSTER_EGG")) {
/* 1688 */       ItemMeta meta = item.getItemMeta();
/* 1689 */       if (meta instanceof SpawnEggMeta) {
/* 1690 */         SpawnEggMeta egg = (SpawnEggMeta)meta;
/* 1691 */         material = String.valueOf(egg.getSpawnedType().name()) + "_SPAWN_EGG";
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1699 */     Optional<XMaterial> result = matchDefinedXMaterial(material, data);
/* 1700 */     if (result.isPresent()) return result.get(); 
/* 1701 */     throw new IllegalArgumentException("Unsupported material from item: " + material + " (" + data + ')');
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   protected static Optional<XMaterial> matchDefinedXMaterial(@Nonnull String name, byte data) {
/* 1720 */     Boolean duplicated = null;
/* 1721 */     boolean isAMap = name.equalsIgnoreCase("MAP");
/*      */ 
/*      */     
/* 1724 */     if (Data.ISFLAT || (!isAMap && data <= 0 && !(duplicated = Boolean.valueOf(isDuplicated(name))).booleanValue())) {
/* 1725 */       Optional<XMaterial> xMaterial = getIfPresent(name);
/* 1726 */       if (xMaterial.isPresent()) return xMaterial;
/*      */     
/*      */     } 
/*      */     
/* 1730 */     XMaterial oldXMaterial = requestOldXMaterial(name, data);
/* 1731 */     if (oldXMaterial == null)
/*      */     {
/* 1733 */       return (data >= 0 && isAMap) ? Optional.<XMaterial>of(FILLED_MAP) : Optional.<XMaterial>empty();
/*      */     }
/*      */     
/* 1736 */     if (!Data.ISFLAT && oldXMaterial.isPlural() && ((duplicated == null) ? isDuplicated(name) : duplicated.booleanValue())) return getIfPresent(name); 
/* 1737 */     return Optional.of(oldXMaterial);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isDuplicated(@Nonnull String name) {
/* 1754 */     return DUPLICATED.contains(name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   @Deprecated
/*      */   public static Optional<XMaterial> matchXMaterial(int id, byte data) {
/* 1774 */     if (id < 0 || id > 2267 || data < 0) return Optional.empty();  byte b; int i; XMaterial[] arrayOfXMaterial;
/* 1775 */     for (i = (arrayOfXMaterial = VALUES).length, b = 0; b < i; ) { XMaterial materials = arrayOfXMaterial[b];
/* 1776 */       if (materials.data == data && materials.getId() == id) return Optional.of(materials);  b++; }
/*      */     
/* 1778 */     return Optional.empty();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   protected static String format(@Nonnull String name) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual length : ()I
/*      */     //   4: istore_1
/*      */     //   5: iload_1
/*      */     //   6: newarray char
/*      */     //   8: astore_2
/*      */     //   9: iconst_0
/*      */     //   10: istore_3
/*      */     //   11: iconst_0
/*      */     //   12: istore #4
/*      */     //   14: iconst_0
/*      */     //   15: istore #5
/*      */     //   17: goto -> 175
/*      */     //   20: aload_0
/*      */     //   21: iload #5
/*      */     //   23: invokevirtual charAt : (I)C
/*      */     //   26: istore #6
/*      */     //   28: iload #4
/*      */     //   30: ifne -> 72
/*      */     //   33: iload_3
/*      */     //   34: ifeq -> 72
/*      */     //   37: iload #6
/*      */     //   39: bipush #45
/*      */     //   41: if_icmpeq -> 58
/*      */     //   44: iload #6
/*      */     //   46: bipush #32
/*      */     //   48: if_icmpeq -> 58
/*      */     //   51: iload #6
/*      */     //   53: bipush #95
/*      */     //   55: if_icmpne -> 72
/*      */     //   58: aload_2
/*      */     //   59: iload_3
/*      */     //   60: caload
/*      */     //   61: bipush #95
/*      */     //   63: if_icmpeq -> 72
/*      */     //   66: iconst_1
/*      */     //   67: istore #4
/*      */     //   69: goto -> 172
/*      */     //   72: iconst_0
/*      */     //   73: istore #7
/*      */     //   75: iload #6
/*      */     //   77: bipush #65
/*      */     //   79: if_icmplt -> 89
/*      */     //   82: iload #6
/*      */     //   84: bipush #90
/*      */     //   86: if_icmple -> 128
/*      */     //   89: iload #6
/*      */     //   91: bipush #97
/*      */     //   93: if_icmplt -> 103
/*      */     //   96: iload #6
/*      */     //   98: bipush #122
/*      */     //   100: if_icmple -> 128
/*      */     //   103: iload #6
/*      */     //   105: bipush #48
/*      */     //   107: if_icmplt -> 121
/*      */     //   110: iload #6
/*      */     //   112: bipush #57
/*      */     //   114: if_icmpgt -> 121
/*      */     //   117: iconst_1
/*      */     //   118: goto -> 122
/*      */     //   121: iconst_0
/*      */     //   122: dup
/*      */     //   123: istore #7
/*      */     //   125: ifeq -> 172
/*      */     //   128: iload #4
/*      */     //   130: ifeq -> 144
/*      */     //   133: aload_2
/*      */     //   134: iload_3
/*      */     //   135: iinc #3, 1
/*      */     //   138: bipush #95
/*      */     //   140: castore
/*      */     //   141: iconst_0
/*      */     //   142: istore #4
/*      */     //   144: iload #7
/*      */     //   146: ifeq -> 160
/*      */     //   149: aload_2
/*      */     //   150: iload_3
/*      */     //   151: iinc #3, 1
/*      */     //   154: iload #6
/*      */     //   156: castore
/*      */     //   157: goto -> 172
/*      */     //   160: aload_2
/*      */     //   161: iload_3
/*      */     //   162: iinc #3, 1
/*      */     //   165: iload #6
/*      */     //   167: bipush #95
/*      */     //   169: iand
/*      */     //   170: i2c
/*      */     //   171: castore
/*      */     //   172: iinc #5, 1
/*      */     //   175: iload #5
/*      */     //   177: iload_1
/*      */     //   178: if_icmplt -> 20
/*      */     //   181: new java/lang/String
/*      */     //   184: dup
/*      */     //   185: aload_2
/*      */     //   186: iconst_0
/*      */     //   187: iload_3
/*      */     //   188: invokespecial <init> : ([CII)V
/*      */     //   191: areturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #1794	-> 0
/*      */     //   #1795	-> 5
/*      */     //   #1796	-> 9
/*      */     //   #1797	-> 11
/*      */     //   #1799	-> 14
/*      */     //   #1800	-> 20
/*      */     //   #1802	-> 28
/*      */     //   #1803	-> 66
/*      */     //   #1805	-> 72
/*      */     //   #1807	-> 75
/*      */     //   #1808	-> 128
/*      */     //   #1809	-> 133
/*      */     //   #1810	-> 141
/*      */     //   #1813	-> 144
/*      */     //   #1814	-> 160
/*      */     //   #1799	-> 172
/*      */     //   #1819	-> 181
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	192	0	name	Ljava/lang/String;
/*      */     //   5	187	1	len	I
/*      */     //   9	183	2	chs	[C
/*      */     //   11	181	3	count	I
/*      */     //   14	178	4	appendUnderline	Z
/*      */     //   17	164	5	i	I
/*      */     //   28	144	6	ch	C
/*      */     //   75	97	7	number	Z
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean supports(int version) {
/* 1831 */     return (Data.VERSION >= version);
/*      */   }
/*      */   
/*      */   public String[] getLegacy() {
/* 1835 */     return this.legacy;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isPlural() {
/* 1856 */     return !(this != CARROTS && this != POTATOES);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isOneOf(@Nullable Collection<String> materials) {
/* 1898 */     if (materials == null || materials.isEmpty()) return false; 
/* 1899 */     String name = name();
/*      */     
/* 1901 */     for (String comp : materials) {
/* 1902 */       String checker = comp.toUpperCase(Locale.ENGLISH);
/* 1903 */       if (checker.startsWith("CONTAINS:")) {
/* 1904 */         comp = format(checker.substring(9));
/* 1905 */         if (name.contains(comp)) return true; 
/*      */         continue;
/*      */       } 
/* 1908 */       if (checker.startsWith("REGEX:")) {
/* 1909 */         comp = comp.substring(6);
/* 1910 */         Pattern pattern = (Pattern)CACHED_REGEX.getIfPresent(comp);
/* 1911 */         if (pattern == null) {
/*      */           try {
/* 1913 */             pattern = Pattern.compile(comp);
/* 1914 */             CACHED_REGEX.put(comp, pattern);
/* 1915 */           } catch (PatternSyntaxException ex) {
/* 1916 */             ex.printStackTrace();
/*      */           } 
/*      */         }
/* 1919 */         if (pattern != null && pattern.matcher(name).matches()) return true;
/*      */ 
/*      */         
/*      */         continue;
/*      */       } 
/* 1924 */       Optional<XMaterial> xMat = matchXMaterial(comp);
/* 1925 */       if (xMat.isPresent() && xMat.get() == this) return true; 
/*      */     } 
/* 1927 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   public ItemStack setType(@Nonnull ItemStack item) {
/* 1944 */     Objects.requireNonNull(item, "Cannot set material for null ItemStack");
/* 1945 */     Material material = parseMaterial();
/* 1946 */     Objects.requireNonNull(material, () -> "Unsupported material: " + name());
/*      */     
/* 1948 */     item.setType(material);
/* 1949 */     if (!Data.ISFLAT && material.getMaxDurability() <= 0) item.setDurability(this.data); 
/* 1950 */     return item;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean anyMatchLegacy(@Nonnull String name) {
/* 1963 */     for (int i = this.legacy.length - 1; i >= 0; i--) {
/* 1964 */       if (name.equals(this.legacy[i])) return true; 
/*      */     } 
/* 1966 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nonnull
/*      */   public String toString() {
/* 1986 */     return WordUtils.capitalize(name().replace('_', ' ').toLowerCase(Locale.ENGLISH));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getId() {
/* 2003 */     Material material = parseMaterial();
/* 2004 */     if (material == null) return -1; 
/*      */     try {
/* 2006 */       return material.getId();
/* 2007 */     } catch (IllegalArgumentException ignored) {
/* 2008 */       return -1;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte getData() {
/* 2022 */     return this.data;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public ItemStack parseItem(int i) {
/* 2037 */     Material material = parseMaterial();
/* 2038 */     if (material == null) return null; 
/* 2039 */     return Data.ISFLAT ? new ItemStack(material) : new ItemStack(material, i, this.data);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public Material parseMaterial() {
/* 2050 */     return this.material;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSimilar(@Nonnull ItemStack item) {
/* 2063 */     Objects.requireNonNull(item, "Cannot compare with null ItemStack");
/* 2064 */     if (item.getType() != parseMaterial()) return false; 
/* 2065 */     return !(!Data.ISFLAT && item.getDurability() != this.data && item.getType().getMaxDurability() <= 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSupported() {
/* 2079 */     return (this.material != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isDuplicated() {
/*      */     String str;
/* 2093 */     switch ((str = name()).hashCode()) { case -1929109465: if (!str.equals("POTATO")) {
/*      */           break;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2116 */         return true;case -1722057187: if (!str.equals("DARK_OAK_DOOR")) break;  return true;case -519277571: if (!str.equals("BIRCH_DOOR")) break;  return true;case -333218805: if (!str.equals("SPRUCE_DOOR")) break;  return true;case -328086150: if (!str.equals("NETHER_BRICK")) break;  return true;case 76092: if (!str.equals("MAP")) break;  return true;case 63467553: if (!str.equals("BRICK")) break;  return true;case 68077974: if (!str.equals("GRASS")) break;  return true;case 73242259: if (!str.equals("MELON")) break;  return true;case 478520881: if (!str.equals("ACACIA_DOOR")) break;  return true;case 868145122: if (!str.equals("CAULDRON")) break;  return true;case 1379814896: if (!str.equals("JUNGLE_DOOR")) break;  return true;case 1401892433: if (!str.equals("FLOWER_POT")) break;  return true;case 1545025079: if (!str.equals("BREWING_STAND")) break;  return true;case 1980706179: if (!str.equals("CARROT")) break;  return true; }
/*      */     
/* 2118 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final class Data
/*      */   {
/*      */     private static final int VERSION;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     static {
/* 2137 */       String version = Bukkit.getVersion();
/* 2138 */       Matcher matcher = Pattern.compile("MC: \\d\\.(\\d+)").matcher(version);
/*      */       
/* 2140 */       if (matcher.find()) { VERSION = Integer.parseInt(matcher.group(1)); }
/* 2141 */       else { throw new IllegalArgumentException("Failed to parse server version from: " + version); }
/*      */     
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2149 */     private static final boolean ISFLAT = XMaterial.supports(13);
/*      */   }
/*      */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\XMaterial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */