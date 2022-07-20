/*      */ package me.storagecabinet.utils;
/*      */ 
/*      */ import com.google.common.cache.Cache;
/*      */ import com.google.common.cache.CacheBuilder;
/*      */ import com.google.common.cache.CacheLoader;
/*      */ import com.google.common.cache.LoadingCache;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Objects;
/*      */ import java.util.Optional;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.TimeUnit;
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
/*   77 */   ACACIA_BOAT(new String[] { "BOAT_ACACIA" }), ACACIA_BUTTON(new String[] { "WOOD_BUTTON" }), ACACIA_DOOR(new String[] { "ACACIA_DOOR", "ACACIA_DOOR_ITEM" }),
/*   78 */   ACACIA_FENCE(new String[0]), ACACIA_FENCE_GATE(new String[0]), ACACIA_LEAVES(new String[] { "LEAVES_2" }), ACACIA_LOG(new String[] { "LOG_2" }), ACACIA_PLANKS(4, new String[] { "WOOD" }),
/*   79 */   ACACIA_PRESSURE_PLATE(new String[] { "WOOD_PLATE" }), ACACIA_SAPLING(4, new String[] { "SAPLING" }), ACACIA_SIGN(new String[] { "SIGN_POST", "SIGN" }),
/*   80 */   ACACIA_SLAB(4, new String[] { "WOOD_DOUBLE_STEP", "WOOD_STEP", "WOODEN_SLAB" }), ACACIA_STAIRS(new String[0]), ACACIA_TRAPDOOR(new String[] { "TRAP_DOOR" }),
/*   81 */   ACACIA_WALL_SIGN(new String[] { "WALL_SIGN" }), ACACIA_WOOD(new String[] { "LOG_2" }), ACTIVATOR_RAIL(new String[0]),
/*   82 */   AIR(new String[0]),
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   88 */   ALLIUM(2, new String[] { "RED_ROSE" }), AMETHYST_BLOCK(new String[0]), AMETHYST_CLUSTER(new String[0]), AMETHYST_SHARD(new String[0]), ANCIENT_DEBRIS(new String[0]), ANDESITE(5, new String[] { "STONE" }),
/*   89 */   ANDESITE_SLAB(new String[0]), ANDESITE_STAIRS(new String[0]), ANDESITE_WALL(new String[0]), ANVIL(new String[0]), APPLE(new String[0]), ARMOR_STAND(new String[0]), ARROW(new String[0]),
/*   90 */   ATTACHED_MELON_STEM(7, new String[] { "MELON_STEM" }), ATTACHED_PUMPKIN_STEM(7, new String[] { "PUMPKIN_STEM" }), AXOLOTL_BUCKET(new String[0]), AXOLOTL_SPAWN_EGG(new String[0]),
/*   91 */   AZALEA(new String[0]), AZALEA_LEAVES(new String[0]), AZURE_BLUET(3, new String[] { "RED_ROSE" }), BAKED_POTATO(new String[0]), BAMBOO(new String[0]), BAMBOO_SAPLING(new String[0]), BARREL(new String[0]), BARRIER(new String[0]), BASALT(new String[0]),
/*   92 */   BAT_SPAWN_EGG(65, new String[] { "MONSTER_EGG" }), BEACON(new String[0]), BEDROCK(new String[0]), BEEF(new String[] { "RAW_BEEF" }), BEEHIVE(new String[0]),
/*   93 */   BEETROOT(new String[] {
/*      */ 
/*      */       
/*   96 */       "BEETROOT_BLOCK" }), BEETROOTS(new String[] { "BEETROOT" }), BEETROOT_SEEDS(new String[0]), BEETROOT_SOUP(new String[0]), BEE_NEST(new String[0]), BEE_SPAWN_EGG(new String[0]), BELL(new String[0]),
/*   97 */   BIG_DRIPLEAF(new String[0]), BIG_DRIPLEAF_STEM(new String[0]), BIRCH_BOAT(new String[] { "BOAT_BIRCH" }), BIRCH_BUTTON(new String[] { "WOOD_BUTTON" }),
/*   98 */   BIRCH_DOOR(new String[] { "BIRCH_DOOR", "BIRCH_DOOR_ITEM" }), BIRCH_FENCE(new String[0]), BIRCH_FENCE_GATE(new String[0]), BIRCH_LEAVES(2, new String[] { "LEAVES" }),
/*   99 */   BIRCH_LOG(2, new String[] { "LOG" }), BIRCH_PLANKS(2, new String[] { "WOOD" }), BIRCH_PRESSURE_PLATE(new String[] { "WOOD_PLATE" }), BIRCH_SAPLING(2, new String[] { "SAPLING" }),
/*  100 */   BIRCH_SIGN(new String[] { "SIGN_POST", "SIGN" }), BIRCH_SLAB(2, new String[] { "WOOD_DOUBLE_STEP", "WOOD_STEP", "WOODEN_SLAB" }),
/*  101 */   BIRCH_STAIRS(new String[] { "BIRCH_WOOD_STAIRS" }), BIRCH_TRAPDOOR(new String[] { "TRAP_DOOR" }), BIRCH_WALL_SIGN(new String[] { "WALL_SIGN" }), BIRCH_WOOD(2, new String[] { "LOG" }),
/*  102 */   BLACKSTONE(new String[0]), BLACKSTONE_SLAB(new String[0]), BLACKSTONE_STAIRS(new String[0]), BLACKSTONE_WALL(new String[0]), BLACK_BANNER(new String[] { "STANDING_BANNER", "BANNER" }),
/*  103 */   BLACK_BED(15, new String[] { "BED_BLOCK", "BED" }), BLACK_CANDLE(new String[0]), BLACK_CANDLE_CAKE(new String[0]), BLACK_CARPET(15, new String[] { "CARPET" }),
/*  104 */   BLACK_CONCRETE(15, new String[] { "CONCRETE" }), BLACK_CONCRETE_POWDER(15, new String[] { "CONCRETE_POWDER" }), BLACK_DYE(new String[] { "INK_SACK", "INK_SAC" }),
/*  105 */   BLACK_GLAZED_TERRACOTTA(new String[0]), BLACK_SHULKER_BOX(new String[0]), BLACK_STAINED_GLASS(15, new String[] { "STAINED_GLASS" }),
/*  106 */   BLACK_STAINED_GLASS_PANE(15, new String[] { "STAINED_GLASS_PANE" }), BLACK_TERRACOTTA(15, new String[] { "STAINED_CLAY" }),
/*  107 */   BLACK_WALL_BANNER(new String[] { "WALL_BANNER" }), BLACK_WOOL(15, new String[] { "WOOL" }), BLAST_FURNACE(new String[0]), BLAZE_POWDER(new String[0]), BLAZE_ROD(new String[0]),
/*  108 */   BLAZE_SPAWN_EGG(61, new String[] { "MONSTER_EGG" }), BLUE_BANNER(4, new String[] { "STANDING_BANNER", "BANNER" }), BLUE_BED(11, new String[] { "BED_BLOCK", "BED" }),
/*  109 */   BLUE_CANDLE(new String[0]), BLUE_CANDLE_CAKE(new String[0]), BLUE_CARPET(11, new String[] { "CARPET" }), BLUE_CONCRETE(11, new String[] { "CONCRETE" }),
/*  110 */   BLUE_CONCRETE_POWDER(11, new String[] { "CONCRETE_POWDER" }), BLUE_DYE(4, new String[] { "INK_SACK", "LAPIS_LAZULI" }), BLUE_GLAZED_TERRACOTTA(new String[0]),
/*  111 */   BLUE_ICE(new String[0]), BLUE_ORCHID(1, new String[] { "RED_ROSE" }), BLUE_SHULKER_BOX(new String[0]), BLUE_STAINED_GLASS(11, new String[] { "STAINED_GLASS" }),
/*  112 */   BLUE_STAINED_GLASS_PANE(11, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }), BLUE_TERRACOTTA(11, new String[] { "STAINED_CLAY" }),
/*  113 */   BLUE_WALL_BANNER(4, new String[] { "WALL_BANNER" }), BLUE_WOOL(11, new String[] { "WOOL" }), BONE(new String[0]), BONE_BLOCK(new String[0]), BONE_MEAL(15, new String[] { "INK_SACK" }), BOOK(new String[0]),
/*  114 */   BOOKSHELF(new String[0]), BOW(new String[0]), BOWL(new String[0]), BRAIN_CORAL(new String[0]), BRAIN_CORAL_BLOCK(new String[0]), BRAIN_CORAL_FAN(new String[0]), BRAIN_CORAL_WALL_FAN(new String[0]), BREAD(new String[0]),
/*  115 */   BREWING_STAND(new String[] { "BREWING_STAND", "BREWING_STAND_ITEM" }), BRICK(new String[] { "CLAY_BRICK" }), BRICKS(new String[] { "BRICKS", "BRICK" }),
/*  116 */   BRICK_SLAB(4, new String[] { "STEP" }), BRICK_STAIRS(new String[0]), BRICK_WALL(new String[0]), BROWN_BANNER(3, new String[] { "STANDING_BANNER", "BANNER" }),
/*  117 */   BROWN_BED(12, new String[] { "BED_BLOCK", "BED" }), BROWN_CANDLE(new String[0]), BROWN_CANDLE_CAKE(new String[0]), BROWN_CARPET(12, new String[] { "CARPET" }),
/*  118 */   BROWN_CONCRETE(12, new String[] { "CONCRETE" }), BROWN_CONCRETE_POWDER(12, new String[] { "CONCRETE_POWDER" }),
/*  119 */   BROWN_DYE(3, new String[] { "INK_SACK", "DYE", "COCOA_BEANS" }), BROWN_GLAZED_TERRACOTTA(new String[0]), BROWN_MUSHROOM(new String[0]),
/*  120 */   BROWN_MUSHROOM_BLOCK(new String[] { "BROWN_MUSHROOM", "HUGE_MUSHROOM_1" }), BROWN_SHULKER_BOX(new String[0]),
/*  121 */   BROWN_STAINED_GLASS(12, new String[] { "STAINED_GLASS" }), BROWN_STAINED_GLASS_PANE(12, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/*  122 */   BROWN_TERRACOTTA(12, new String[] { "STAINED_CLAY" }), BROWN_WALL_BANNER(3, new String[] { "WALL_BANNER" }), BROWN_WOOL(12, new String[] { "WOOL" }), BUBBLE_COLUMN(new String[0]),
/*  123 */   BUBBLE_CORAL(new String[0]), BUBBLE_CORAL_BLOCK(new String[0]), BUBBLE_CORAL_FAN(new String[0]), BUBBLE_CORAL_WALL_FAN(new String[0]), BUCKET(new String[0]), BUDDING_AMETHYST(new String[0]), BUNDLE(new String[0]), CACTUS(new String[0]),
/*  124 */   CAKE(new String[] { "CAKE_BLOCK" }), CALCITE(new String[0]), CAMPFIRE(new String[0]), CANDLE(new String[0]), CANDLE_CAKE(new String[0]), CARROT(new String[] { "CARROT_ITEM" }), CARROTS(new String[] { "CARROT" }),
/*  125 */   CARROT_ON_A_STICK(new String[] { "CARROT_STICK" }), CARTOGRAPHY_TABLE(new String[0]), CARVED_PUMPKIN(new String[0]), CAT_SPAWN_EGG(new String[0]),
/*  126 */   CAULDRON(new String[] { "CAULDRON", "CAULDRON_ITEM" }),
/*  127 */   CAVE_AIR(new String[] {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  132 */       "AIR" }), CAVE_SPIDER_SPAWN_EGG(59, new String[] { "MONSTER_EGG" }), CAVE_VINES(new String[0]), CAVE_VINES_PLANT(new String[0]), CHAIN(new String[0]), CHAINMAIL_BOOTS(new String[0]),
/*  133 */   CHAINMAIL_CHESTPLATE(new String[0]), CHAINMAIL_HELMET(new String[0]), CHAINMAIL_LEGGINGS(new String[0]), CHAIN_COMMAND_BLOCK(new String[] { "COMMAND", "COMMAND_CHAIN" }),
/*  134 */   CHARCOAL(1, new String[] { "COAL" }), CHEST(new String[] { "LOCKED_CHEST" }), CHEST_MINECART(new String[] { "STORAGE_MINECART" }), CHICKEN(new String[] { "RAW_CHICKEN" }),
/*  135 */   CHICKEN_SPAWN_EGG(93, new String[] { "MONSTER_EGG" }), CHIPPED_ANVIL(1, new String[] { "ANVIL" }), CHISELED_DEEPSLATE(new String[0]),
/*  136 */   CHISELED_NETHER_BRICKS(1, new String[] { "NETHER_BRICKS" }), CHISELED_POLISHED_BLACKSTONE(new String[] { "POLISHED_BLACKSTONE" }),
/*  137 */   CHISELED_QUARTZ_BLOCK(1, new String[] { "QUARTZ_BLOCK" }), CHISELED_RED_SANDSTONE(1, new String[] { "RED_SANDSTONE" }),
/*  138 */   CHISELED_SANDSTONE(1, new String[] { "SANDSTONE" }), CHISELED_STONE_BRICKS(3, new String[] { "SMOOTH_BRICK" }), CHORUS_FLOWER(new String[0]), CHORUS_FRUIT(new String[0]),
/*  139 */   CHORUS_PLANT(new String[0]), CLAY(new String[] { "HARD_CLAY" }), CLAY_BALL(new String[0]), CLOCK(new String[] { "WATCH" }), COAL(new String[0]), COAL_BLOCK(new String[0]), COAL_ORE(new String[0]), COARSE_DIRT(1, new String[] { "DIRT" }),
/*  140 */   COBBLED_DEEPSLATE(new String[0]), COBBLED_DEEPSLATE_SLAB(new String[0]), COBBLED_DEEPSLATE_STAIRS(new String[0]), COBBLED_DEEPSLATE_WALL(new String[0]), COBBLESTONE(new String[0]),
/*  141 */   COBBLESTONE_SLAB(3, new String[] { "STEP" }), COBBLESTONE_STAIRS(new String[0]), COBBLESTONE_WALL(new String[] { "COBBLE_WALL" }), COBWEB(new String[] { "WEB" }), COCOA(new String[0]),
/*  142 */   COCOA_BEANS(3, new String[] { "INK_SACK" }), COD(new String[] { "RAW_FISH" }), COD_BUCKET(new String[0]), COD_SPAWN_EGG(new String[0]), COMMAND_BLOCK(new String[] { "COMMAND" }),
/*  143 */   COMMAND_BLOCK_MINECART(new String[] { "COMMAND_MINECART" }),
/*  144 */   COMPARATOR(new String[] {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  151 */       "REDSTONE_COMPARATOR_OFF", "REDSTONE_COMPARATOR_ON", "REDSTONE_COMPARATOR" }), COMPASS(new String[0]), COMPOSTER(new String[0]), CONDUIT(new String[0]),
/*  152 */   COOKED_BEEF(new String[0]), COOKED_CHICKEN(new String[0]), COOKED_COD(new String[] { "COOKED_FISH" }), COOKED_MUTTON(new String[0]), COOKED_PORKCHOP(new String[] { "PORK", "GRILLED_PORK" }),
/*  153 */   COOKED_RABBIT(new String[0]), COOKED_SALMON(1, new String[] { "COOKED_FISH" }), COOKIE(new String[0]), COPPER_BLOCK(new String[0]), COPPER_INGOT(new String[0]), COPPER_ORE(new String[0]), CORNFLOWER(new String[0]),
/*  154 */   COW_SPAWN_EGG(92, new String[] { "MONSTER_EGG" }), CRACKED_DEEPSLATE_BRICKS(new String[0]), CRACKED_DEEPSLATE_TILES(new String[0]),
/*  155 */   CRACKED_NETHER_BRICKS(2, new String[] { "NETHER_BRICKS" }), CRACKED_POLISHED_BLACKSTONE_BRICKS(new String[] { "POLISHED_BLACKSTONE_BRICKS" }),
/*  156 */   CRACKED_STONE_BRICKS(2, new String[] { "SMOOTH_BRICK" }), CRAFTING_TABLE(new String[] { "WORKBENCH" }), CREEPER_BANNER_PATTERN(new String[0]),
/*  157 */   CREEPER_HEAD(4, new String[] { "SKULL", "SKULL_ITEM" }), CREEPER_SPAWN_EGG(50, new String[] { "MONSTER_EGG" }),
/*  158 */   CREEPER_WALL_HEAD(4, new String[] { "SKULL", "SKULL_ITEM" }), CRIMSON_BUTTON(new String[0]), CRIMSON_DOOR(new String[0]), CRIMSON_FENCE(new String[0]), CRIMSON_FENCE_GATE(new String[0]),
/*  159 */   CRIMSON_FUNGUS(new String[0]), CRIMSON_HYPHAE(new String[0]), CRIMSON_NYLIUM(new String[0]), CRIMSON_PLANKS(new String[0]), CRIMSON_PRESSURE_PLATE(new String[0]), CRIMSON_ROOTS(new String[0]),
/*  160 */   CRIMSON_SIGN(new String[] { "SIGN_POST" }), CRIMSON_SLAB(new String[0]), CRIMSON_STAIRS(new String[0]), CRIMSON_STEM(new String[0]), CRIMSON_TRAPDOOR(new String[0]),
/*  161 */   CRIMSON_WALL_SIGN(new String[] { "WALL_SIGN" }), CROSSBOW(new String[0]), CRYING_OBSIDIAN(new String[0]), CUT_COPPER(new String[0]), CUT_COPPER_SLAB(new String[0]), CUT_COPPER_STAIRS(new String[0]),
/*  162 */   CUT_RED_SANDSTONE(new String[0]), CUT_RED_SANDSTONE_SLAB(new String[] { "STONE_SLAB2" }), CUT_SANDSTONE(new String[0]), CUT_SANDSTONE_SLAB(new String[] { "STEP" }),
/*  163 */   CYAN_BANNER(6, new String[] { "STANDING_BANNER", "BANNER" }), CYAN_BED(9, new String[] { "BED_BLOCK", "BED" }), CYAN_CANDLE(new String[0]), CYAN_CANDLE_CAKE(new String[0]),
/*  164 */   CYAN_CARPET(9, new String[] { "CARPET" }), CYAN_CONCRETE(9, new String[] { "CONCRETE" }), CYAN_CONCRETE_POWDER(9, new String[] { "CONCRETE_POWDER" }),
/*  165 */   CYAN_DYE(6, new String[] { "INK_SACK" }), CYAN_GLAZED_TERRACOTTA(new String[0]), CYAN_SHULKER_BOX(new String[0]), CYAN_STAINED_GLASS(9, new String[] { "STAINED_GLASS" }),
/*  166 */   CYAN_STAINED_GLASS_PANE(9, new String[] { "STAINED_GLASS_PANE" }), CYAN_TERRACOTTA(9, new String[] { "STAINED_CLAY" }),
/*  167 */   CYAN_WALL_BANNER(6, new String[] { "WALL_BANNER" }), CYAN_WOOL(9, new String[] { "WOOL" }), DAMAGED_ANVIL(2, new String[] { "ANVIL" }), DANDELION(new String[] { "YELLOW_FLOWER" }),
/*  168 */   DARK_OAK_BOAT(new String[] { "BOAT_DARK_OAK" }), DARK_OAK_BUTTON(new String[] { "WOOD_BUTTON" }),
/*  169 */   DARK_OAK_DOOR(new String[] { "DARK_OAK_DOOR", "DARK_OAK_DOOR_ITEM" }), DARK_OAK_FENCE(new String[0]), DARK_OAK_FENCE_GATE(new String[0]),
/*  170 */   DARK_OAK_LEAVES(1, new String[] { "LEAVES_2" }), DARK_OAK_LOG(1, new String[] { "LOG_2" }), DARK_OAK_PLANKS(5, new String[] { "WOOD" }),
/*  171 */   DARK_OAK_PRESSURE_PLATE(new String[] { "WOOD_PLATE" }), DARK_OAK_SAPLING(5, new String[] { "SAPLING" }), DARK_OAK_SIGN(new String[] { "SIGN_POST", "SIGN" }),
/*  172 */   DARK_OAK_SLAB(5, new String[] { "WOOD_DOUBLE_STEP", "WOOD_STEP", "WOODEN_SLAB" }), DARK_OAK_STAIRS(new String[0]), DARK_OAK_TRAPDOOR(new String[] { "TRAP_DOOR" }),
/*  173 */   DARK_OAK_WALL_SIGN(new String[] { "WALL_SIGN" }), DARK_OAK_WOOD(1, new String[] { "LOG_2" }), DARK_PRISMARINE(1, new String[] { "PRISMARINE" }), DARK_PRISMARINE_SLAB(new String[0]),
/*  174 */   DARK_PRISMARINE_STAIRS(new String[0]), DAYLIGHT_DETECTOR(new String[] { "DAYLIGHT_DETECTOR_INVERTED" }), DEAD_BRAIN_CORAL(new String[0]), DEAD_BRAIN_CORAL_BLOCK(new String[0]),
/*  175 */   DEAD_BRAIN_CORAL_FAN(new String[0]), DEAD_BRAIN_CORAL_WALL_FAN(new String[0]), DEAD_BUBBLE_CORAL(new String[0]), DEAD_BUBBLE_CORAL_BLOCK(new String[0]), DEAD_BUBBLE_CORAL_FAN(new String[0]),
/*  176 */   DEAD_BUBBLE_CORAL_WALL_FAN(new String[0]), DEAD_BUSH(new String[] { "LONG_GRASS" }), DEAD_FIRE_CORAL(new String[0]), DEAD_FIRE_CORAL_BLOCK(new String[0]), DEAD_FIRE_CORAL_FAN(new String[0]),
/*  177 */   DEAD_FIRE_CORAL_WALL_FAN(new String[0]), DEAD_HORN_CORAL(new String[0]), DEAD_HORN_CORAL_BLOCK(new String[0]), DEAD_HORN_CORAL_FAN(new String[0]), DEAD_HORN_CORAL_WALL_FAN(new String[0]),
/*  178 */   DEAD_TUBE_CORAL(new String[0]), DEAD_TUBE_CORAL_BLOCK(new String[0]), DEAD_TUBE_CORAL_FAN(new String[0]), DEAD_TUBE_CORAL_WALL_FAN(new String[0]), DEBUG_STICK(new String[0]), DEEPSLATE(new String[0]),
/*  179 */   DEEPSLATE_BRICKS(new String[0]), DEEPSLATE_BRICK_SLAB(new String[0]), DEEPSLATE_BRICK_STAIRS(new String[0]), DEEPSLATE_BRICK_WALL(new String[0]), DEEPSLATE_COAL_ORE(new String[0]),
/*  180 */   DEEPSLATE_COPPER_ORE(new String[0]), DEEPSLATE_DIAMOND_ORE(new String[0]), DEEPSLATE_EMERALD_ORE(new String[0]), DEEPSLATE_GOLD_ORE(new String[0]), DEEPSLATE_IRON_ORE(new String[0]),
/*  181 */   DEEPSLATE_LAPIS_ORE(new String[0]), DEEPSLATE_REDSTONE_ORE(new String[0]), DEEPSLATE_TILES(new String[0]), DEEPSLATE_TILE_SLAB(new String[0]), DEEPSLATE_TILE_STAIRS(new String[0]),
/*  182 */   DEEPSLATE_TILE_WALL(new String[0]), DETECTOR_RAIL(new String[0]), DIAMOND(new String[0]), DIAMOND_AXE(new String[0]), DIAMOND_BLOCK(new String[0]), DIAMOND_BOOTS(new String[0]), DIAMOND_CHESTPLATE(new String[0]),
/*  183 */   DIAMOND_HELMET(new String[0]), DIAMOND_HOE(new String[0]), DIAMOND_HORSE_ARMOR(new String[] { "DIAMOND_BARDING" }), DIAMOND_LEGGINGS(new String[0]), DIAMOND_ORE(new String[0]), DIAMOND_PICKAXE(new String[0]),
/*  184 */   DIAMOND_SHOVEL(new String[] { "DIAMOND_SPADE" }), DIAMOND_SWORD(new String[0]), DIORITE(3, new String[] { "STONE" }), DIORITE_SLAB(new String[0]), DIORITE_STAIRS(new String[0]), DIORITE_WALL(new String[0]),
/*  185 */   DIRT(new String[0]),
/*  186 */   DIRT_PATH(new String[] {
/*      */ 
/*      */       
/*  189 */       "GRASS_PATH" }), DISPENSER(new String[0]), DOLPHIN_SPAWN_EGG(new String[0]), DONKEY_SPAWN_EGG(32, new String[] { "MONSTER_EGG" }),
/*  190 */   DRAGON_BREATH(new String[] { "DRAGONS_BREATH" }), DRAGON_EGG(new String[0]), DRAGON_HEAD(new String[] { "SKULL", "SKULL_ITEM" }),
/*  191 */   DRAGON_WALL_HEAD(5, new String[] { "SKULL", "SKULL_ITEM" }), DRIED_KELP(new String[0]), DRIED_KELP_BLOCK(new String[0]), DRIPSTONE_BLOCK(new String[0]), DROPPER(new String[0]),
/*  192 */   DROWNED_SPAWN_EGG(new String[0]), EGG(new String[0]), ELDER_GUARDIAN_SPAWN_EGG(4, new String[] { "MONSTER_EGG" }), ELYTRA(new String[0]), EMERALD(new String[0]), EMERALD_BLOCK(new String[0]), EMERALD_ORE(new String[0]),
/*  193 */   ENCHANTED_BOOK(new String[0]), ENCHANTED_GOLDEN_APPLE(1, new String[] { "GOLDEN_APPLE" }), ENCHANTING_TABLE(new String[] { "ENCHANTMENT_TABLE" }),
/*  194 */   ENDERMAN_SPAWN_EGG(58, new String[] { "MONSTER_EGG" }), ENDERMITE_SPAWN_EGG(67, new String[] { "MONSTER_EGG" }), ENDER_CHEST(new String[0]),
/*  195 */   ENDER_EYE(new String[] { "EYE_OF_ENDER" }), ENDER_PEARL(new String[0]), END_CRYSTAL(new String[0]), END_GATEWAY(new String[0]), END_PORTAL(new String[] { "ENDER_PORTAL" }),
/*  196 */   END_PORTAL_FRAME(new String[] { "ENDER_PORTAL_FRAME" }), END_ROD(new String[0]), END_STONE(new String[] { "ENDER_STONE" }), END_STONE_BRICKS(new String[] { "END_BRICKS" }),
/*  197 */   END_STONE_BRICK_SLAB(6, new String[] { "STEP" }), END_STONE_BRICK_STAIRS(new String[] { "SMOOTH_STAIRS" }), END_STONE_BRICK_WALL(new String[0]),
/*  198 */   EVOKER_SPAWN_EGG(34, new String[] { "MONSTER_EGG" }), EXPERIENCE_BOTTLE(new String[] { "EXP_BOTTLE" }), EXPOSED_COPPER(new String[0]), EXPOSED_CUT_COPPER(new String[0]),
/*  199 */   EXPOSED_CUT_COPPER_SLAB(new String[0]), EXPOSED_CUT_COPPER_STAIRS(new String[0]), FARMLAND(new String[] { "SOIL" }), FEATHER(new String[0]), FERMENTED_SPIDER_EYE(new String[0]),
/*  200 */   FERN(2, new String[] { "LONG_GRASS" }),
/*  201 */   FILLED_MAP(new String[] {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  206 */       "MAP" }), FIRE(new String[0]), FIREWORK_ROCKET(new String[] { "FIREWORK" }), FIREWORK_STAR(new String[] { "FIREWORK_CHARGE" }), FIRE_CHARGE(new String[] { "FIREBALL" }),
/*  207 */   FIRE_CORAL(new String[0]), FIRE_CORAL_BLOCK(new String[0]), FIRE_CORAL_FAN(new String[0]), FIRE_CORAL_WALL_FAN(new String[0]), FISHING_ROD(new String[0]), FLETCHING_TABLE(new String[0]), FLINT(new String[0]),
/*  208 */   FLINT_AND_STEEL(new String[0]), FLOWERING_AZALEA(new String[0]), FLOWERING_AZALEA_LEAVES(new String[0]), FLOWER_BANNER_PATTERN(new String[0]),
/*  209 */   FLOWER_POT(new String[] { "FLOWER_POT", "FLOWER_POT_ITEM" }), FOX_SPAWN_EGG(new String[0]),
/*  210 */   FROSTED_ICE(new String[0]),
/*      */ 
/*      */   
/*  213 */   FURNACE(new String[] { "BURNING_FURNACE" }), FURNACE_MINECART(new String[] { "POWERED_MINECART" }), GHAST_SPAWN_EGG(56, new String[] { "MONSTER_EGG" }),
/*  214 */   GHAST_TEAR(new String[0]), GILDED_BLACKSTONE(new String[0]), GLASS(new String[0]), GLASS_BOTTLE(new String[0]), GLASS_PANE(new String[] { "THIN_GLASS" }),
/*  215 */   GLISTERING_MELON_SLICE(new String[] { "SPECKLED_MELON" }), GLOBE_BANNER_PATTERN(new String[0]), GLOWSTONE(new String[0]), GLOWSTONE_DUST(new String[0]), GLOW_BERRIES(new String[0]),
/*  216 */   GLOW_INK_SAC(new String[0]), GLOW_ITEM_FRAME(new String[0]), GLOW_LICHEN(new String[0]), GLOW_SQUID_SPAWN_EGG(new String[0]), GOAT_SPAWN_EGG(new String[0]), GOLDEN_APPLE(new String[0]),
/*  217 */   GOLDEN_AXE(new String[] { "GOLD_AXE" }), GOLDEN_BOOTS(new String[] { "GOLD_BOOTS" }), GOLDEN_CARROT(new String[0]), GOLDEN_CHESTPLATE(new String[] { "GOLD_CHESTPLATE" }),
/*  218 */   GOLDEN_HELMET(new String[] { "GOLD_HELMET" }), GOLDEN_HOE(new String[] { "GOLD_HOE" }), GOLDEN_HORSE_ARMOR(new String[] { "GOLD_BARDING" }),
/*  219 */   GOLDEN_LEGGINGS(new String[] { "GOLD_LEGGINGS" }), GOLDEN_PICKAXE(new String[] { "GOLD_PICKAXE" }), GOLDEN_SHOVEL(new String[] { "GOLD_SPADE" }),
/*  220 */   GOLDEN_SWORD(new String[] { "GOLD_SWORD" }), GOLD_BLOCK(new String[0]), GOLD_INGOT(new String[0]), GOLD_NUGGET(new String[0]), GOLD_ORE(new String[0]), GRANITE(1, new String[] { "STONE" }), GRANITE_SLAB(new String[0]),
/*  221 */   GRANITE_STAIRS(new String[0]), GRANITE_WALL(new String[0]), GRASS(1, new String[] { "LONG_GRASS" }), GRASS_BLOCK(new String[] { "GRASS" }), GRAVEL(new String[0]),
/*  222 */   GRAY_BANNER(8, new String[] { "STANDING_BANNER", "BANNER" }), GRAY_BED(7, new String[] { "BED_BLOCK", "BED" }), GRAY_CANDLE(new String[0]), GRAY_CANDLE_CAKE(new String[0]),
/*  223 */   GRAY_CARPET(7, new String[] { "CARPET" }), GRAY_CONCRETE(7, new String[] { "CONCRETE" }), GRAY_CONCRETE_POWDER(7, new String[] { "CONCRETE_POWDER" }),
/*  224 */   GRAY_DYE(8, new String[] { "INK_SACK" }), GRAY_GLAZED_TERRACOTTA(new String[0]), GRAY_SHULKER_BOX(new String[0]), GRAY_STAINED_GLASS(7, new String[] { "STAINED_GLASS" }),
/*  225 */   GRAY_STAINED_GLASS_PANE(7, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }), GRAY_TERRACOTTA(7, new String[] { "STAINED_CLAY" }),
/*  226 */   GRAY_WALL_BANNER(8, new String[] { "WALL_BANNER" }), GRAY_WOOL(7, new String[] { "WOOL" }), GREEN_BANNER(2, new String[] { "STANDING_BANNER", "BANNER" }),
/*  227 */   GREEN_BED(13, new String[] { "BED_BLOCK", "BED" }), GREEN_CANDLE(new String[0]), GREEN_CANDLE_CAKE(new String[0]), GREEN_CARPET(13, new String[] { "CARPET" }),
/*  228 */   GREEN_CONCRETE(13, new String[] { "CONCRETE" }), GREEN_CONCRETE_POWDER(13, new String[] { "CONCRETE_POWDER" }),
/*  229 */   GREEN_DYE(2, new String[] { "INK_SACK", "CACTUS_GREEN" }), GREEN_GLAZED_TERRACOTTA(new String[0]), GREEN_SHULKER_BOX(new String[0]),
/*  230 */   GREEN_STAINED_GLASS(13, new String[] { "STAINED_GLASS" }), GREEN_STAINED_GLASS_PANE(13, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/*  231 */   GREEN_TERRACOTTA(13, new String[] { "STAINED_CLAY" }), GREEN_WALL_BANNER(2, new String[] { "WALL_BANNER" }), GREEN_WOOL(13, new String[] { "WOOL" }), GRINDSTONE(new String[0]),
/*  232 */   GUARDIAN_SPAWN_EGG(68, new String[] { "MONSTER_EGG" }), GUNPOWDER(new String[] { "SULPHUR" }), HANGING_ROOTS(new String[0]), HAY_BLOCK(new String[0]), HEART_OF_THE_SEA(new String[0]),
/*  233 */   HEAVY_WEIGHTED_PRESSURE_PLATE(new String[] { "IRON_PLATE" }), HOGLIN_SPAWN_EGG(new String[] { "MONSTER_EGG" }), HONEYCOMB(new String[0]), HONEYCOMB_BLOCK(new String[0]),
/*  234 */   HONEY_BLOCK(new String[0]), HONEY_BOTTLE(new String[0]), HOPPER(new String[0]), HOPPER_MINECART(new String[0]), HORN_CORAL(new String[0]), HORN_CORAL_BLOCK(new String[0]), HORN_CORAL_FAN(new String[0]),
/*  235 */   HORN_CORAL_WALL_FAN(new String[0]), HORSE_SPAWN_EGG(100, new String[] { "MONSTER_EGG" }), HUSK_SPAWN_EGG(23, new String[] { "MONSTER_EGG" }), ICE(new String[0]),
/*  236 */   INFESTED_CHISELED_STONE_BRICKS(5, new String[] { "MONSTER_EGGS" }), INFESTED_COBBLESTONE(1, new String[] { "MONSTER_EGGS" }),
/*  237 */   INFESTED_CRACKED_STONE_BRICKS(4, new String[] { "MONSTER_EGGS" }), INFESTED_DEEPSLATE(new String[0]),
/*  238 */   INFESTED_MOSSY_STONE_BRICKS(3, new String[] { "MONSTER_EGGS" }), INFESTED_STONE(new String[] { "MONSTER_EGGS" }),
/*  239 */   INFESTED_STONE_BRICKS(2, new String[] { "MONSTER_EGGS" }),
/*  240 */   INK_SAC(new String[] {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  245 */       "INK_SACK" }), IRON_AXE(new String[0]), IRON_BARS(new String[] { "IRON_FENCE" }), IRON_BLOCK(new String[0]), IRON_BOOTS(new String[0]), IRON_CHESTPLATE(new String[0]),
/*  246 */   IRON_DOOR(new String[] { "IRON_DOOR_BLOCK" }), IRON_HELMET(new String[0]), IRON_HOE(new String[0]), IRON_HORSE_ARMOR(new String[] { "IRON_BARDING" }), IRON_INGOT(new String[0]), IRON_LEGGINGS(new String[0]),
/*  247 */   IRON_NUGGET(new String[0]), IRON_ORE(new String[0]), IRON_PICKAXE(new String[0]), IRON_SHOVEL(new String[] { "IRON_SPADE" }), IRON_SWORD(new String[0]), IRON_TRAPDOOR(new String[0]), ITEM_FRAME(new String[0]),
/*  248 */   JACK_O_LANTERN(new String[0]), JIGSAW(new String[0]), JUKEBOX(new String[0]), JUNGLE_BOAT(new String[] { "BOAT_JUNGLE" }), JUNGLE_BUTTON(new String[] { "WOOD_BUTTON" }),
/*  249 */   JUNGLE_DOOR(new String[] { "JUNGLE_DOOR", "JUNGLE_DOOR_ITEM" }), JUNGLE_FENCE(new String[0]), JUNGLE_FENCE_GATE(new String[0]), JUNGLE_LEAVES(3, new String[] { "LEAVES" }),
/*  250 */   JUNGLE_LOG(3, new String[] { "LOG" }), JUNGLE_PLANKS(3, new String[] { "WOOD" }), JUNGLE_PRESSURE_PLATE(new String[] { "WOOD_PLATE" }), JUNGLE_SAPLING(3, new String[] { "SAPLING" }),
/*  251 */   JUNGLE_SIGN(new String[] { "SIGN_POST", "SIGN" }), JUNGLE_SLAB(3, new String[] { "WOOD_DOUBLE_STEP", "WOOD_STEP", "WOODEN_SLAB" }),
/*  252 */   JUNGLE_STAIRS(new String[] { "JUNGLE_WOOD_STAIRS" }), JUNGLE_TRAPDOOR(new String[] { "TRAP_DOOR" }), JUNGLE_WALL_SIGN(new String[] { "WALL_SIGN" }),
/*  253 */   JUNGLE_WOOD(3, new String[] { "LOG" }), KELP(new String[0]), KELP_PLANT(new String[0]), KNOWLEDGE_BOOK(new String[] { "BOOK" }), LADDER(new String[0]), LANTERN(new String[0]), LAPIS_BLOCK(new String[0]),
/*  254 */   LAPIS_LAZULI(4, new String[] { "INK_SACK" }), LAPIS_ORE(new String[0]), LARGE_AMETHYST_BUD(new String[0]), LARGE_FERN(3, new String[] { "DOUBLE_PLANT" }), LAVA(new String[] { "STATIONARY_LAVA" }),
/*  255 */   LAVA_BUCKET(new String[0]), LAVA_CAULDRON(new String[0]), LEAD(new String[] { "LEASH" }), LEATHER(new String[0]), LEATHER_BOOTS(new String[0]), LEATHER_CHESTPLATE(new String[0]), LEATHER_HELMET(new String[0]),
/*  256 */   LEATHER_HORSE_ARMOR(new String[] { "IRON_HORSE_ARMOR" }), LEATHER_LEGGINGS(new String[0]), LECTERN(new String[0]), LEVER(new String[0]), LIGHT(new String[0]), LIGHTNING_ROD(new String[0]),
/*  257 */   LIGHT_BLUE_BANNER(12, new String[] { "STANDING_BANNER", "BANNER" }), LIGHT_BLUE_BED(3, new String[] { "BED_BLOCK", "BED" }), LIGHT_BLUE_CANDLE(new String[0]),
/*  258 */   LIGHT_BLUE_CANDLE_CAKE(new String[0]), LIGHT_BLUE_CARPET(3, new String[] { "CARPET" }), LIGHT_BLUE_CONCRETE(3, new String[] { "CONCRETE" }),
/*  259 */   LIGHT_BLUE_CONCRETE_POWDER(3, new String[] { "CONCRETE_POWDER" }), LIGHT_BLUE_DYE(12, new String[] { "INK_SACK" }), LIGHT_BLUE_GLAZED_TERRACOTTA(new String[0]),
/*  260 */   LIGHT_BLUE_SHULKER_BOX(new String[0]), LIGHT_BLUE_STAINED_GLASS(3, new String[] { "STAINED_GLASS" }),
/*  261 */   LIGHT_BLUE_STAINED_GLASS_PANE(3, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }), LIGHT_BLUE_TERRACOTTA(3, new String[] { "STAINED_CLAY" }),
/*  262 */   LIGHT_BLUE_WALL_BANNER(12, new String[] { "WALL_BANNER", "STANDING_BANNER", "BANNER" }), LIGHT_BLUE_WOOL(3, new String[] { "WOOL" }),
/*  263 */   LIGHT_GRAY_BANNER(7, new String[] { "STANDING_BANNER", "BANNER" }), LIGHT_GRAY_BED(8, new String[] { "BED_BLOCK", "BED" }), LIGHT_GRAY_CANDLE(new String[0]),
/*  264 */   LIGHT_GRAY_CANDLE_CAKE(new String[0]), LIGHT_GRAY_CARPET(8, new String[] { "CARPET" }), LIGHT_GRAY_CONCRETE(8, new String[] { "CONCRETE" }),
/*  265 */   LIGHT_GRAY_CONCRETE_POWDER(8, new String[] { "CONCRETE_POWDER" }), LIGHT_GRAY_DYE(7, new String[] { "INK_SACK" }),
/*  266 */   LIGHT_GRAY_GLAZED_TERRACOTTA(new String[] {
/*      */ 
/*      */ 
/*      */       
/*  270 */       "STAINED_CLAY", "LIGHT_GRAY_TERRACOTTA", "SILVER_GLAZED_TERRACOTTA" }),
/*  271 */   LIGHT_GRAY_SHULKER_BOX(new String[] { "SILVER_SHULKER_BOX" }), LIGHT_GRAY_STAINED_GLASS(8, new String[] { "STAINED_GLASS" }),
/*  272 */   LIGHT_GRAY_STAINED_GLASS_PANE(8, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }), LIGHT_GRAY_TERRACOTTA(8, new String[] { "STAINED_CLAY" }),
/*  273 */   LIGHT_GRAY_WALL_BANNER(7, new String[] { "WALL_BANNER" }), LIGHT_GRAY_WOOL(8, new String[] { "WOOL" }), LIGHT_WEIGHTED_PRESSURE_PLATE(new String[] { "GOLD_PLATE" }),
/*  274 */   LILAC(1, new String[] { "DOUBLE_PLANT" }), LILY_OF_THE_VALLEY(new String[0]), LILY_PAD(new String[] { "WATER_LILY" }), LIME_BANNER(10, new String[] { "STANDING_BANNER", "BANNER" }),
/*  275 */   LIME_BED(5, new String[] { "BED_BLOCK", "BED" }), LIME_CANDLE(new String[0]), LIME_CANDLE_CAKE(new String[0]), LIME_CARPET(5, new String[] { "CARPET" }),
/*  276 */   LIME_CONCRETE(5, new String[] { "CONCRETE" }), LIME_CONCRETE_POWDER(5, new String[] { "CONCRETE_POWDER" }), LIME_DYE(10, new String[] { "INK_SACK" }),
/*  277 */   LIME_GLAZED_TERRACOTTA(new String[0]), LIME_SHULKER_BOX(new String[0]), LIME_STAINED_GLASS(5, new String[] { "STAINED_GLASS" }),
/*  278 */   LIME_STAINED_GLASS_PANE(5, new String[] { "STAINED_GLASS_PANE" }), LIME_TERRACOTTA(5, new String[] { "STAINED_CLAY" }),
/*  279 */   LIME_WALL_BANNER(10, new String[] { "WALL_BANNER" }), LIME_WOOL(5, new String[] { "WOOL" }), LINGERING_POTION(new String[0]), LLAMA_SPAWN_EGG(103, new String[] { "MONSTER_EGG" }),
/*  280 */   LODESTONE(new String[0]), LOOM(new String[0]), MAGENTA_BANNER(13, new String[] { "STANDING_BANNER", "BANNER" }), MAGENTA_BED(2, new String[] { "BED_BLOCK", "BED" }),
/*  281 */   MAGENTA_CANDLE(new String[0]), MAGENTA_CANDLE_CAKE(new String[0]), MAGENTA_CARPET(2, new String[] { "CARPET" }), MAGENTA_CONCRETE(2, new String[] { "CONCRETE" }),
/*  282 */   MAGENTA_CONCRETE_POWDER(2, new String[] { "CONCRETE_POWDER" }), MAGENTA_DYE(13, new String[] { "INK_SACK" }), MAGENTA_GLAZED_TERRACOTTA(new String[0]),
/*  283 */   MAGENTA_SHULKER_BOX(new String[0]), MAGENTA_STAINED_GLASS(2, new String[] { "STAINED_GLASS" }),
/*  284 */   MAGENTA_STAINED_GLASS_PANE(2, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }), MAGENTA_TERRACOTTA(2, new String[] { "STAINED_CLAY" }),
/*  285 */   MAGENTA_WALL_BANNER(13, new String[] { "WALL_BANNER" }), MAGENTA_WOOL(2, new String[] { "WOOL" }), MAGMA_BLOCK(new String[] { "MAGMA" }), MAGMA_CREAM(new String[0]),
/*  286 */   MAGMA_CUBE_SPAWN_EGG(62, new String[] { "MONSTER_EGG" }),
/*  287 */   MAP(new String[] {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  293 */       "EMPTY_MAP" }), MEDIUM_AMETHYST_BUD(new String[0]), MELON(new String[] { "MELON_BLOCK" }), MELON_SEEDS(new String[0]), MELON_SLICE(new String[] { "MELON" }), MELON_STEM(new String[0]),
/*  294 */   MILK_BUCKET(new String[0]), MINECART(new String[0]), MOJANG_BANNER_PATTERN(new String[0]), MOOSHROOM_SPAWN_EGG(96, new String[] { "MONSTER_EGG" }), MOSSY_COBBLESTONE(new String[0]),
/*  295 */   MOSSY_COBBLESTONE_SLAB(3, new String[] { "STEP" }), MOSSY_COBBLESTONE_STAIRS(new String[0]),
/*  296 */   MOSSY_COBBLESTONE_WALL(1, new String[] { "COBBLE_WALL", "COBBLESTONE_WALL" }), MOSSY_STONE_BRICKS(1, new String[] { "SMOOTH_BRICK" }),
/*  297 */   MOSSY_STONE_BRICK_SLAB(5, new String[] { "STEP" }), MOSSY_STONE_BRICK_STAIRS(new String[] { "SMOOTH_STAIRS" }), MOSSY_STONE_BRICK_WALL(new String[0]), MOSS_BLOCK(new String[0]),
/*  298 */   MOSS_CARPET(new String[0]), MOVING_PISTON(new String[] { "PISTON_MOVING_PIECE" }), MULE_SPAWN_EGG(32, new String[] { "MONSTER_EGG" }),
/*  299 */   MUSHROOM_STEM(new String[] { "BROWN_MUSHROOM" }), MUSHROOM_STEW(new String[] { "MUSHROOM_SOUP" }), MUSIC_DISC_11(new String[] { "GOLD_RECORD" }),
/*  300 */   MUSIC_DISC_13(new String[] { "GREEN_RECORD" }), MUSIC_DISC_BLOCKS(new String[] { "RECORD_3" }), MUSIC_DISC_CAT(new String[] { "RECORD_4" }),
/*  301 */   MUSIC_DISC_CHIRP(new String[] { "RECORD_5" }), MUSIC_DISC_FAR(new String[] { "RECORD_6" }), MUSIC_DISC_MALL(new String[] { "RECORD_7" }),
/*  302 */   MUSIC_DISC_MELLOHI(new String[] { "RECORD_8" }), MUSIC_DISC_PIGSTEP(new String[0]), MUSIC_DISC_STAL(new String[] { "RECORD_9" }), MUSIC_DISC_STRAD(new String[] { "RECORD_10" }),
/*  303 */   MUSIC_DISC_WAIT(new String[] { "RECORD_11" }), MUSIC_DISC_WARD(new String[] { "RECORD_12" }), MUTTON(new String[0]), MYCELIUM(new String[] { "MYCEL" }), NAME_TAG(new String[0]), NAUTILUS_SHELL(new String[0]),
/*  304 */   NETHERITE_AXE(new String[0]), NETHERITE_BLOCK(new String[0]), NETHERITE_BOOTS(new String[0]), NETHERITE_CHESTPLATE(new String[0]), NETHERITE_HELMET(new String[0]), NETHERITE_HOE(new String[0]),
/*  305 */   NETHERITE_INGOT(new String[0]), NETHERITE_LEGGINGS(new String[0]), NETHERITE_PICKAXE(new String[0]), NETHERITE_SCRAP(new String[0]), NETHERITE_SHOVEL(new String[0]), NETHERITE_SWORD(new String[0]),
/*  306 */   NETHERRACK(new String[0]), NETHER_BRICK(new String[] { "NETHER_BRICK_ITEM" }), NETHER_BRICKS(new String[] { "NETHER_BRICK" }), NETHER_BRICK_FENCE(new String[] { "NETHER_FENCE" }),
/*  307 */   NETHER_BRICK_SLAB(6, new String[] { "STEP" }), NETHER_BRICK_STAIRS(new String[0]), NETHER_BRICK_WALL(new String[0]), NETHER_GOLD_ORE(new String[0]), NETHER_PORTAL(new String[] { "PORTAL" }),
/*  308 */   NETHER_QUARTZ_ORE(new String[] { "QUARTZ_ORE" }), NETHER_SPROUTS(new String[0]), NETHER_STAR(new String[0]),
/*  309 */   NETHER_WART(new String[] {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  314 */       "NETHER_WARTS", "NETHER_STALK" }), NETHER_WART_BLOCK(new String[0]), NOTE_BLOCK(new String[0]), OAK_BOAT(new String[] { "BOAT" }),
/*  315 */   OAK_BUTTON(new String[] { "WOOD_BUTTON" }), OAK_DOOR(new String[] { "WOODEN_DOOR", "WOOD_DOOR" }), OAK_FENCE(new String[] { "FENCE" }), OAK_FENCE_GATE(new String[] { "FENCE_GATE" }),
/*  316 */   OAK_LEAVES(new String[] { "LEAVES" }), OAK_LOG(new String[] { "LOG" }), OAK_PLANKS(new String[] { "WOOD" }), OAK_PRESSURE_PLATE(new String[] { "WOOD_PLATE" }), OAK_SAPLING(new String[] { "SAPLING" }),
/*  317 */   OAK_SIGN(new String[] { "SIGN_POST", "SIGN" }), OAK_SLAB(new String[] { "WOOD_DOUBLE_STEP", "WOOD_STEP", "WOODEN_SLAB" }), OAK_STAIRS(new String[] { "WOOD_STAIRS" }),
/*  318 */   OAK_TRAPDOOR(new String[] { "TRAP_DOOR" }), OAK_WALL_SIGN(new String[] { "WALL_SIGN" }), OAK_WOOD(new String[] { "LOG" }), OBSERVER(new String[0]), OBSIDIAN(new String[0]),
/*  319 */   OCELOT_SPAWN_EGG(98, new String[] { "MONSTER_EGG" }), ORANGE_BANNER(14, new String[] { "STANDING_BANNER", "BANNER" }),
/*  320 */   ORANGE_BED(1, new String[] { "BED_BLOCK", "BED" }), ORANGE_CANDLE(new String[0]), ORANGE_CANDLE_CAKE(new String[0]), ORANGE_CARPET(1, new String[] { "CARPET" }),
/*  321 */   ORANGE_CONCRETE(1, new String[] { "CONCRETE" }), ORANGE_CONCRETE_POWDER(1, new String[] { "CONCRETE_POWDER" }), ORANGE_DYE(14, new String[] { "INK_SACK" }),
/*  322 */   ORANGE_GLAZED_TERRACOTTA(new String[0]), ORANGE_SHULKER_BOX(new String[0]), ORANGE_STAINED_GLASS(1, new String[] { "STAINED_GLASS" }),
/*  323 */   ORANGE_STAINED_GLASS_PANE(1, new String[] { "STAINED_GLASS_PANE" }), ORANGE_TERRACOTTA(1, new String[] { "STAINED_CLAY" }),
/*  324 */   ORANGE_TULIP(5, new String[] { "RED_ROSE" }), ORANGE_WALL_BANNER(14, new String[] { "WALL_BANNER" }), ORANGE_WOOL(1, new String[] { "WOOL" }),
/*  325 */   OXEYE_DAISY(8, new String[] { "RED_ROSE" }), OXIDIZED_COPPER(new String[0]), OXIDIZED_CUT_COPPER(new String[0]), OXIDIZED_CUT_COPPER_SLAB(new String[0]),
/*  326 */   OXIDIZED_CUT_COPPER_STAIRS(new String[0]), PACKED_ICE(new String[0]), PAINTING(new String[0]), PANDA_SPAWN_EGG(new String[0]), PAPER(new String[0]), PARROT_SPAWN_EGG(105, new String[] { "MONSTER_EGG" }),
/*  327 */   PEONY(5, new String[] { "DOUBLE_PLANT" }), PETRIFIED_OAK_SLAB(new String[] { "WOOD_STEP" }), PHANTOM_MEMBRANE(new String[0]), PHANTOM_SPAWN_EGG(new String[0]),
/*  328 */   PIGLIN_BANNER_PATTERN(new String[0]), PIGLIN_BRUTE_SPAWN_EGG(new String[0]), PIGLIN_SPAWN_EGG(57, new String[] { "MONSTER_EGG" }),
/*  329 */   PIG_SPAWN_EGG(90, new String[] { "MONSTER_EGG" }), PILLAGER_SPAWN_EGG(new String[0]), PINK_BANNER(9, new String[] { "STANDING_BANNER", "BANNER" }),
/*  330 */   PINK_BED(6, new String[] { "BED_BLOCK", "BED" }), PINK_CANDLE(new String[0]), PINK_CANDLE_CAKE(new String[0]), PINK_CARPET(6, new String[] { "CARPET" }),
/*  331 */   PINK_CONCRETE(6, new String[] { "CONCRETE" }), PINK_CONCRETE_POWDER(6, new String[] { "CONCRETE_POWDER" }), PINK_DYE(9, new String[] { "INK_SACK" }),
/*  332 */   PINK_GLAZED_TERRACOTTA(new String[0]), PINK_SHULKER_BOX(new String[0]), PINK_STAINED_GLASS(6, new String[] { "STAINED_GLASS" }),
/*  333 */   PINK_STAINED_GLASS_PANE(6, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }), PINK_TERRACOTTA(6, new String[] { "STAINED_CLAY" }),
/*  334 */   PINK_TULIP(7, new String[] { "RED_ROSE" }), PINK_WALL_BANNER(9, new String[] { "WALL_BANNER" }), PINK_WOOL(6, new String[] { "WOOL" }), PISTON(new String[] { "PISTON_BASE" }),
/*  335 */   PISTON_HEAD(new String[] { "PISTON_EXTENSION" }), PLAYER_HEAD(3, new String[] { "SKULL", "SKULL_ITEM" }), PLAYER_WALL_HEAD(3, new String[] { "SKULL", "SKULL_ITEM" }),
/*  336 */   PODZOL(2, new String[] { "DIRT" }), POINTED_DRIPSTONE(new String[0]), POISONOUS_POTATO(new String[0]), POLAR_BEAR_SPAWN_EGG(102, new String[] { "MONSTER_EGG" }),
/*  337 */   POLISHED_ANDESITE(6, new String[] { "STONE" }), POLISHED_ANDESITE_SLAB(new String[0]), POLISHED_ANDESITE_STAIRS(new String[0]), POLISHED_BASALT(new String[0]),
/*  338 */   POLISHED_BLACKSTONE(new String[0]), POLISHED_BLACKSTONE_BRICKS(new String[0]), POLISHED_BLACKSTONE_BRICK_SLAB(new String[0]), POLISHED_BLACKSTONE_BRICK_STAIRS(new String[0]),
/*  339 */   POLISHED_BLACKSTONE_BRICK_WALL(new String[0]), POLISHED_BLACKSTONE_BUTTON(new String[0]), POLISHED_BLACKSTONE_PRESSURE_PLATE(new String[0]),
/*  340 */   POLISHED_BLACKSTONE_SLAB(new String[0]), POLISHED_BLACKSTONE_STAIRS(new String[0]), POLISHED_BLACKSTONE_WALL(new String[0]), POLISHED_DEEPSLATE(new String[0]),
/*  341 */   POLISHED_DEEPSLATE_SLAB(new String[0]), POLISHED_DEEPSLATE_STAIRS(new String[0]), POLISHED_DEEPSLATE_WALL(new String[0]), POLISHED_DIORITE(4, new String[] { "STONE" }),
/*  342 */   POLISHED_DIORITE_SLAB(new String[0]), POLISHED_DIORITE_STAIRS(new String[0]), POLISHED_GRANITE(2, new String[] { "STONE" }), POLISHED_GRANITE_SLAB(new String[0]),
/*  343 */   POLISHED_GRANITE_STAIRS(new String[0]), POPPED_CHORUS_FRUIT(new String[] { "CHORUS_FRUIT_POPPED" }), POPPY(new String[] { "RED_ROSE" }), PORKCHOP(new String[] { "PORK" }),
/*  344 */   POTATO(new String[] { "POTATO_ITEM" }), POTATOES(new String[] { "POTATO" }), POTION(new String[0]), POTTED_ACACIA_SAPLING(4, new String[] { "FLOWER_POT" }),
/*  345 */   POTTED_ALLIUM(2, new String[] { "RED_ROSE", "FLOWER_POT" }), POTTED_AZALEA_BUSH(new String[0]), POTTED_AZURE_BLUET(3, new String[] { "RED_ROSE", "FLOWER_POT" }),
/*  346 */   POTTED_BAMBOO(new String[0]), POTTED_BIRCH_SAPLING(2, new String[] { "FLOWER_POT" }), POTTED_BLUE_ORCHID(1, new String[] { "RED_ROSE", "FLOWER_POT" }),
/*  347 */   POTTED_BROWN_MUSHROOM(new String[] { "FLOWER_POT" }), POTTED_CACTUS(new String[] { "FLOWER_POT" }), POTTED_CORNFLOWER(new String[0]), POTTED_CRIMSON_FUNGUS(new String[0]),
/*  348 */   POTTED_CRIMSON_ROOTS(new String[0]), POTTED_DANDELION(new String[] { "YELLOW_FLOWER", "FLOWER_POT" }), POTTED_DARK_OAK_SAPLING(5, new String[] { "FLOWER_POT" }),
/*  349 */   POTTED_DEAD_BUSH(new String[] { "FLOWER_POT" }), POTTED_FERN(2, new String[] { "LONG_GRASS", "FLOWER_POT" }), POTTED_FLOWERING_AZALEA_BUSH(new String[0]),
/*  350 */   POTTED_JUNGLE_SAPLING(3, new String[] { "FLOWER_POT" }), POTTED_LILY_OF_THE_VALLEY(new String[0]), POTTED_OAK_SAPLING(new String[] { "FLOWER_POT" }),
/*  351 */   POTTED_ORANGE_TULIP(5, new String[] { "RED_ROSE", "FLOWER_POT" }), POTTED_OXEYE_DAISY(8, new String[] { "RED_ROSE", "FLOWER_POT" }),
/*  352 */   POTTED_PINK_TULIP(7, new String[] { "RED_ROSE", "FLOWER_POT" }), POTTED_POPPY(new String[] { "RED_ROSE", "FLOWER_POT" }),
/*  353 */   POTTED_RED_MUSHROOM(new String[] { "FLOWER_POT" }), POTTED_RED_TULIP(4, new String[] { "RED_ROSE", "FLOWER_POT" }),
/*  354 */   POTTED_SPRUCE_SAPLING(1, new String[] { "FLOWER_POT" }), POTTED_WARPED_FUNGUS(new String[0]), POTTED_WARPED_ROOTS(new String[0]),
/*  355 */   POTTED_WHITE_TULIP(6, new String[] { "RED_ROSE", "FLOWER_POT" }), POTTED_WITHER_ROSE(new String[0]), POWDER_SNOW(new String[0]), POWDER_SNOW_BUCKET(new String[0]),
/*  356 */   POWDER_SNOW_CAULDRON(new String[0]), POWERED_RAIL(new String[0]), PRISMARINE(new String[0]), PRISMARINE_BRICKS(2, new String[] { "PRISMARINE" }),
/*  357 */   PRISMARINE_BRICK_SLAB(4, new String[] { "STEP" }), PRISMARINE_BRICK_STAIRS(new String[0]), PRISMARINE_CRYSTALS(new String[0]), PRISMARINE_SHARD(new String[0]), PRISMARINE_SLAB(new String[0]),
/*  358 */   PRISMARINE_STAIRS(new String[0]), PRISMARINE_WALL(new String[0]), PUFFERFISH(3, new String[] { "RAW_FISH" }), PUFFERFISH_BUCKET(new String[0]), PUFFERFISH_SPAWN_EGG(new String[0]), PUMPKIN(new String[0]),
/*  359 */   PUMPKIN_PIE(new String[0]), PUMPKIN_SEEDS(new String[0]), PUMPKIN_STEM(new String[0]), PURPLE_BANNER(5, new String[] { "STANDING_BANNER", "BANNER" }),
/*  360 */   PURPLE_BED(10, new String[] { "BED_BLOCK", "BED" }), PURPLE_CANDLE(new String[0]), PURPLE_CANDLE_CAKE(new String[0]), PURPLE_CARPET(10, new String[] { "CARPET" }),
/*  361 */   PURPLE_CONCRETE(10, new String[] { "CONCRETE" }), PURPLE_CONCRETE_POWDER(10, new String[] { "CONCRETE_POWDER" }), PURPLE_DYE(5, new String[] { "INK_SACK" }),
/*  362 */   PURPLE_GLAZED_TERRACOTTA(new String[0]), PURPLE_SHULKER_BOX(new String[0]), PURPLE_STAINED_GLASS(10, new String[] { "STAINED_GLASS" }),
/*  363 */   PURPLE_STAINED_GLASS_PANE(10, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }), PURPLE_TERRACOTTA(10, new String[] { "STAINED_CLAY" }),
/*  364 */   PURPLE_WALL_BANNER(5, new String[] { "WALL_BANNER" }), PURPLE_WOOL(10, new String[] { "WOOL" }), PURPUR_BLOCK(new String[0]), PURPUR_PILLAR(new String[0]),
/*  365 */   PURPUR_SLAB(new String[] { "PURPUR_DOUBLE_SLAB" }), PURPUR_STAIRS(new String[0]), QUARTZ(new String[0]), QUARTZ_BLOCK(new String[0]), QUARTZ_BRICKS(new String[0]),
/*  366 */   QUARTZ_PILLAR(2, new String[] { "QUARTZ_BLOCK" }), QUARTZ_SLAB(7, new String[] { "STEP" }), QUARTZ_STAIRS(new String[0]), RABBIT(new String[0]), RABBIT_FOOT(new String[0]), RABBIT_HIDE(new String[0]),
/*  367 */   RABBIT_SPAWN_EGG(101, new String[] { "MONSTER_EGG" }), RABBIT_STEW(new String[0]), RAIL(new String[] { "RAILS" }), RAVAGER_SPAWN_EGG(new String[0]), RAW_COPPER(new String[0]), RAW_COPPER_BLOCK(new String[0]),
/*  368 */   RAW_GOLD(new String[0]), RAW_GOLD_BLOCK(new String[0]), RAW_IRON(new String[0]), RAW_IRON_BLOCK(new String[0]), REDSTONE(new String[0]), REDSTONE_BLOCK(new String[0]),
/*  369 */   REDSTONE_LAMP(new String[] {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  375 */       "REDSTONE_LAMP_ON", "REDSTONE_LAMP_OFF" }), REDSTONE_ORE(new String[] { "GLOWING_REDSTONE_ORE" }),
/*  376 */   REDSTONE_TORCH(new String[] {
/*      */ 
/*      */ 
/*      */       
/*  380 */       "REDSTONE_TORCH_OFF", "REDSTONE_TORCH_ON" }), REDSTONE_WALL_TORCH(new String[0]), REDSTONE_WIRE(new String[0]),
/*  381 */   RED_BANNER(1, new String[] { "STANDING_BANNER", "BANNER" }),
/*  382 */   RED_BED(
/*      */ 
/*      */     
/*  385 */     14, new String[] { "BED_BLOCK", "BED" }), RED_CANDLE(new String[0]), RED_CANDLE_CAKE(new String[0]), RED_CARPET(14, new String[] { "CARPET" }),
/*  386 */   RED_CONCRETE(14, new String[] { "CONCRETE" }), RED_CONCRETE_POWDER(14, new String[] { "CONCRETE_POWDER" }), RED_DYE(1, new String[] { "INK_SACK", "ROSE_RED" }),
/*  387 */   RED_GLAZED_TERRACOTTA(new String[0]), RED_MUSHROOM(new String[0]), RED_MUSHROOM_BLOCK(new String[] { "RED_MUSHROOM", "HUGE_MUSHROOM_2" }),
/*  388 */   RED_NETHER_BRICKS(new String[] { "RED_NETHER_BRICK" }), RED_NETHER_BRICK_SLAB(4, new String[] { "STEP" }), RED_NETHER_BRICK_STAIRS(new String[0]),
/*  389 */   RED_NETHER_BRICK_WALL(new String[0]), RED_SAND(1, new String[] { "SAND" }), RED_SANDSTONE(new String[0]), RED_SANDSTONE_SLAB(new String[] { "DOUBLE_STONE_SLAB2", "STONE_SLAB2" }),
/*  390 */   RED_SANDSTONE_STAIRS(new String[0]), RED_SANDSTONE_WALL(new String[0]), RED_SHULKER_BOX(new String[0]), RED_STAINED_GLASS(14, new String[] { "STAINED_GLASS" }),
/*  391 */   RED_STAINED_GLASS_PANE(14, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }), RED_TERRACOTTA(14, new String[] { "STAINED_CLAY" }),
/*  392 */   RED_TULIP(4, new String[] { "RED_ROSE" }), RED_WALL_BANNER(1, new String[] { "WALL_BANNER" }), RED_WOOL(14, new String[] { "WOOL" }),
/*  393 */   REPEATER(new String[] { "DIODE_BLOCK_ON", "DIODE_BLOCK_OFF", "DIODE" }), REPEATING_COMMAND_BLOCK(new String[] { "COMMAND", "COMMAND_REPEATING" }),
/*  394 */   RESPAWN_ANCHOR(new String[0]), ROOTED_DIRT(new String[0]), ROSE_BUSH(4, new String[] { "DOUBLE_PLANT" }), ROTTEN_FLESH(new String[0]), SADDLE(new String[0]), SALMON(1, new String[] { "RAW_FISH" }),
/*  395 */   SALMON_BUCKET(new String[0]), SALMON_SPAWN_EGG(new String[0]), SAND(new String[0]), SANDSTONE(new String[0]), SANDSTONE_SLAB(1, new String[] { "DOUBLE_STEP", "STEP", "STONE_SLAB" }),
/*  396 */   SANDSTONE_STAIRS(new String[0]), SANDSTONE_WALL(new String[0]), SCAFFOLDING(new String[0]), SCULK_SENSOR(new String[0]), SCUTE(new String[0]), SEAGRASS(new String[0]), SEA_LANTERN(new String[0]), SEA_PICKLE(new String[0]), SHEARS(new String[0]),
/*  397 */   SHEEP_SPAWN_EGG(91, new String[] { "MONSTER_EGG" }), SHIELD(new String[0]), SHROOMLIGHT(new String[0]), SHULKER_BOX(new String[] { "PURPLE_SHULKER_BOX" }), SHULKER_SHELL(new String[0]),
/*  398 */   SHULKER_SPAWN_EGG(69, new String[] { "MONSTER_EGG" }), SILVERFISH_SPAWN_EGG(60, new String[] { "MONSTER_EGG" }),
/*  399 */   SKELETON_HORSE_SPAWN_EGG(28, new String[] { "MONSTER_EGG" }), SKELETON_SKULL(new String[] { "SKULL", "SKULL_ITEM" }),
/*  400 */   SKELETON_SPAWN_EGG(51, new String[] { "MONSTER_EGG" }), SKELETON_WALL_SKULL(new String[] { "SKULL", "SKULL_ITEM" }), SKULL_BANNER_PATTERN(new String[0]), SLIME_BALL(new String[0]),
/*  401 */   SLIME_BLOCK(new String[0]), SLIME_SPAWN_EGG(55, new String[] { "MONSTER_EGG" }), SMALL_AMETHYST_BUD(new String[0]), SMALL_DRIPLEAF(new String[0]), SMITHING_TABLE(new String[0]), SMOKER(new String[0]),
/*  402 */   SMOOTH_BASALT(new String[0]), SMOOTH_QUARTZ(new String[0]), SMOOTH_QUARTZ_SLAB(7, new String[] { "STEP" }), SMOOTH_QUARTZ_STAIRS(new String[0]),
/*  403 */   SMOOTH_RED_SANDSTONE(2, new String[] { "RED_SANDSTONE" }), SMOOTH_RED_SANDSTONE_SLAB(new String[] { "STONE_SLAB2" }), SMOOTH_RED_SANDSTONE_STAIRS(new String[0]),
/*  404 */   SMOOTH_SANDSTONE(2, new String[] { "SANDSTONE" }), SMOOTH_SANDSTONE_SLAB(new String[] { "STEP" }), SMOOTH_SANDSTONE_STAIRS(new String[0]), SMOOTH_STONE(new String[] { "STEP" }),
/*  405 */   SMOOTH_STONE_SLAB(new String[] { "STEP" }), SNOW(new String[0]), SNOWBALL(new String[] { "SNOW_BALL" }), SNOW_BLOCK(new String[0]), SOUL_CAMPFIRE(new String[0]), SOUL_FIRE(new String[0]), SOUL_LANTERN(new String[0]),
/*  406 */   SOUL_SAND(new String[0]), SOUL_SOIL(new String[0]), SOUL_TORCH(new String[0]), SOUL_WALL_TORCH(new String[0]), SPAWNER(new String[] { "MOB_SPAWNER" }), SPECTRAL_ARROW(new String[0]), SPIDER_EYE(new String[0]),
/*  407 */   SPIDER_SPAWN_EGG(52, new String[] { "MONSTER_EGG" }), SPLASH_POTION(new String[0]), SPONGE(new String[0]), SPORE_BLOSSOM(new String[0]), SPRUCE_BOAT(new String[] { "BOAT_SPRUCE" }),
/*  408 */   SPRUCE_BUTTON(new String[] { "WOOD_BUTTON" }), SPRUCE_DOOR(new String[] { "SPRUCE_DOOR", "SPRUCE_DOOR_ITEM" }), SPRUCE_FENCE(new String[0]), SPRUCE_FENCE_GATE(new String[0]),
/*  409 */   SPRUCE_LEAVES(1, new String[] { "LEAVES" }), SPRUCE_LOG(1, new String[] { "LOG" }), SPRUCE_PLANKS(1, new String[] { "WOOD" }), SPRUCE_PRESSURE_PLATE(new String[] { "WOOD_PLATE" }),
/*  410 */   SPRUCE_SAPLING(1, new String[] { "SAPLING" }), SPRUCE_SIGN(new String[] { "SIGN_POST", "SIGN" }),
/*  411 */   SPRUCE_SLAB(1, new String[] { "WOOD_DOUBLE_STEP", "WOOD_STEP", "WOODEN_SLAB" }), SPRUCE_STAIRS(new String[] { "SPRUCE_WOOD_STAIRS" }),
/*  412 */   SPRUCE_TRAPDOOR(new String[] { "TRAP_DOOR" }), SPRUCE_WALL_SIGN(new String[] { "WALL_SIGN" }), SPRUCE_WOOD(1, new String[] { "LOG" }), SPYGLASS(new String[0]),
/*  413 */   SQUID_SPAWN_EGG(94, new String[] { "MONSTER_EGG" }), STICK(new String[0]), STICKY_PISTON(new String[] { "PISTON_BASE", "PISTON_STICKY_BASE" }), STONE(new String[0]), STONECUTTER(new String[0]),
/*  414 */   STONE_AXE(new String[0]), STONE_BRICKS(new String[] { "SMOOTH_BRICK" }), STONE_BRICK_SLAB(4, new String[] { "DOUBLE_STEP", "STEP", "STONE_SLAB" }),
/*  415 */   STONE_BRICK_STAIRS(new String[] { "SMOOTH_STAIRS" }), STONE_BRICK_WALL(new String[0]), STONE_BUTTON(new String[0]), STONE_HOE(new String[0]), STONE_PICKAXE(new String[0]),
/*  416 */   STONE_PRESSURE_PLATE(new String[] { "STONE_PLATE" }), STONE_SHOVEL(new String[] { "STONE_SPADE" }), STONE_SLAB(new String[] { "DOUBLE_STEP", "STEP" }), STONE_STAIRS(new String[0]),
/*  417 */   STONE_SWORD(new String[0]), STRAY_SPAWN_EGG(6, new String[] { "MONSTER_EGG" }), STRIDER_SPAWN_EGG(new String[0]), STRING(new String[0]), STRIPPED_ACACIA_LOG(new String[] { "LOG_2" }),
/*  418 */   STRIPPED_ACACIA_WOOD(new String[] { "LOG_2" }), STRIPPED_BIRCH_LOG(2, new String[] { "LOG" }), STRIPPED_BIRCH_WOOD(2, new String[] { "LOG" }), STRIPPED_CRIMSON_HYPHAE(new String[0]),
/*  419 */   STRIPPED_CRIMSON_STEM(new String[0]), STRIPPED_DARK_OAK_LOG(new String[] { "LOG" }), STRIPPED_DARK_OAK_WOOD(new String[] { "LOG" }), STRIPPED_JUNGLE_LOG(3, new String[] { "LOG" }),
/*  420 */   STRIPPED_JUNGLE_WOOD(3, new String[] { "LOG" }), STRIPPED_OAK_LOG(new String[] { "LOG" }), STRIPPED_OAK_WOOD(new String[] { "LOG" }), STRIPPED_SPRUCE_LOG(1, new String[] { "LOG" }),
/*  421 */   STRIPPED_SPRUCE_WOOD(1, new String[] { "LOG" }), STRIPPED_WARPED_HYPHAE(new String[0]), STRIPPED_WARPED_STEM(new String[0]), STRUCTURE_BLOCK(new String[0]),
/*  422 */   STRUCTURE_VOID(
/*      */ 
/*      */ 
/*      */     
/*  426 */     10, new String[] { "BARRIER" }), SUGAR(new String[0]),
/*  427 */   SUGAR_CANE(new String[] {
/*      */ 
/*      */       
/*  430 */       "SUGAR_CANE_BLOCK" }), SUNFLOWER(new String[] { "DOUBLE_PLANT" }), SUSPICIOUS_STEW(new String[0]), SWEET_BERRIES(new String[0]), SWEET_BERRY_BUSH(new String[0]),
/*  431 */   TALL_GRASS(2, new String[] { "DOUBLE_PLANT" }), TALL_SEAGRASS(new String[0]), TARGET(new String[0]), TERRACOTTA(new String[] { "STAINED_CLAY" }), TINTED_GLASS(new String[0]), TIPPED_ARROW(new String[0]), TNT(new String[0]),
/*  432 */   TNT_MINECART(new String[] { "EXPLOSIVE_MINECART" }), TORCH(new String[0]), TOTEM_OF_UNDYING(new String[] { "TOTEM" }), TRADER_LLAMA_SPAWN_EGG(new String[0]), TRAPPED_CHEST(new String[0]),
/*  433 */   TRIDENT(new String[0]), TRIPWIRE(new String[0]), TRIPWIRE_HOOK(new String[0]), TROPICAL_FISH(2, new String[] { "RAW_FISH" }), TROPICAL_FISH_BUCKET(new String[] { "BUCKET", "WATER_BUCKET" }),
/*  434 */   TROPICAL_FISH_SPAWN_EGG(new String[] { "MONSTER_EGG" }), TUBE_CORAL(new String[0]), TUBE_CORAL_BLOCK(new String[0]), TUBE_CORAL_FAN(new String[0]), TUBE_CORAL_WALL_FAN(new String[0]), TUFF(new String[0]),
/*  435 */   TURTLE_EGG(new String[0]), TURTLE_HELMET(new String[0]), TURTLE_SPAWN_EGG(new String[0]), TWISTING_VINES(new String[0]), TWISTING_VINES_PLANT(new String[0]), VEX_SPAWN_EGG(35, new String[] { "MONSTER_EGG" }),
/*  436 */   VILLAGER_SPAWN_EGG(120, new String[] { "MONSTER_EGG" }), VINDICATOR_SPAWN_EGG(36, new String[] { "MONSTER_EGG" }), VINE(new String[0]),
/*  437 */   VOID_AIR(new String[] {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  442 */       "AIR" }), WALL_TORCH(new String[] { "TORCH" }), WANDERING_TRADER_SPAWN_EGG(new String[0]), WARPED_BUTTON(new String[0]), WARPED_DOOR(new String[0]), WARPED_FENCE(new String[0]),
/*  443 */   WARPED_FENCE_GATE(new String[0]), WARPED_FUNGUS(new String[0]), WARPED_FUNGUS_ON_A_STICK(new String[0]), WARPED_HYPHAE(new String[0]), WARPED_NYLIUM(new String[0]), WARPED_PLANKS(new String[0]),
/*  444 */   WARPED_PRESSURE_PLATE(new String[0]), WARPED_ROOTS(new String[0]), WARPED_SIGN(new String[] { "SIGN_POST" }), WARPED_SLAB(new String[0]), WARPED_STAIRS(new String[0]), WARPED_STEM(new String[0]),
/*  445 */   WARPED_TRAPDOOR(new String[0]), WARPED_WALL_SIGN(new String[] { "WALL_SIGN" }), WARPED_WART_BLOCK(new String[0]),
/*  446 */   WATER(new String[] {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  452 */       "STATIONARY_WATER" }), WATER_BUCKET(new String[0]), WATER_CAULDRON(new String[0]), WAXED_COPPER_BLOCK(new String[0]), WAXED_CUT_COPPER(new String[0]),
/*  453 */   WAXED_CUT_COPPER_SLAB(new String[0]), WAXED_CUT_COPPER_STAIRS(new String[0]), WAXED_EXPOSED_COPPER(new String[0]), WAXED_EXPOSED_CUT_COPPER(new String[0]),
/*  454 */   WAXED_EXPOSED_CUT_COPPER_SLAB(new String[0]), WAXED_EXPOSED_CUT_COPPER_STAIRS(new String[0]), WAXED_OXIDIZED_COPPER(new String[0]), WAXED_OXIDIZED_CUT_COPPER(new String[0]),
/*  455 */   WAXED_OXIDIZED_CUT_COPPER_SLAB(new String[0]), WAXED_OXIDIZED_CUT_COPPER_STAIRS(new String[0]), WAXED_WEATHERED_COPPER(new String[0]),
/*  456 */   WAXED_WEATHERED_CUT_COPPER(new String[0]), WAXED_WEATHERED_CUT_COPPER_SLAB(new String[0]), WAXED_WEATHERED_CUT_COPPER_STAIRS(new String[0]), WEATHERED_COPPER(new String[0]),
/*  457 */   WEATHERED_CUT_COPPER(new String[0]), WEATHERED_CUT_COPPER_SLAB(new String[0]), WEATHERED_CUT_COPPER_STAIRS(new String[0]), WEEPING_VINES(new String[0]), WEEPING_VINES_PLANT(new String[0]),
/*  458 */   WET_SPONGE(1, new String[] { "SPONGE" }),
/*  459 */   WHEAT(new String[] {
/*      */ 
/*      */       
/*  462 */       "CROPS" }), WHEAT_SEEDS(new String[] { "SEEDS" }), WHITE_BANNER(15, new String[] { "STANDING_BANNER", "BANNER" }), WHITE_BED(new String[] { "BED_BLOCK", "BED" }),
/*  463 */   WHITE_CANDLE(new String[0]), WHITE_CANDLE_CAKE(new String[0]), WHITE_CARPET(new String[] { "CARPET" }), WHITE_CONCRETE(new String[] { "CONCRETE" }),
/*  464 */   WHITE_CONCRETE_POWDER(new String[] { "CONCRETE_POWDER" }), WHITE_DYE(15, new String[] { "INK_SACK", "BONE_MEAL" }),
/*  465 */   WHITE_GLAZED_TERRACOTTA(new String[] { "STAINED_CLAY" }), WHITE_SHULKER_BOX(new String[0]), WHITE_STAINED_GLASS(new String[] { "STAINED_GLASS" }),
/*  466 */   WHITE_STAINED_GLASS_PANE(new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }), WHITE_TERRACOTTA(new String[] { "STAINED_CLAY", "TERRACOTTA" }),
/*  467 */   WHITE_TULIP(6, new String[] { "RED_ROSE" }), WHITE_WALL_BANNER(15, new String[] { "WALL_BANNER" }), WHITE_WOOL(new String[] { "WOOL" }),
/*  468 */   WITCH_SPAWN_EGG(66, new String[] { "MONSTER_EGG" }), WITHER_ROSE(new String[0]), WITHER_SKELETON_SKULL(1, new String[] { "SKULL", "SKULL_ITEM" }),
/*  469 */   WITHER_SKELETON_SPAWN_EGG(5, new String[] { "MONSTER_EGG" }), WITHER_SKELETON_WALL_SKULL(1, new String[] { "SKULL", "SKULL_ITEM" }),
/*  470 */   WOLF_SPAWN_EGG(95, new String[] { "MONSTER_EGG" }), WOODEN_AXE(new String[] { "WOOD_AXE" }), WOODEN_HOE(new String[] { "WOOD_HOE" }), WOODEN_PICKAXE(new String[] { "WOOD_PICKAXE" }),
/*  471 */   WOODEN_SHOVEL(new String[] { "WOOD_SPADE" }), WOODEN_SWORD(new String[] { "WOOD_SWORD" }), WRITABLE_BOOK(new String[] { "BOOK_AND_QUILL" }), WRITTEN_BOOK(new String[0]),
/*  472 */   YELLOW_BANNER(11, new String[] { "STANDING_BANNER", "BANNER" }), YELLOW_BED(4, new String[] { "BED_BLOCK", "BED" }), YELLOW_CANDLE(new String[0]),
/*  473 */   YELLOW_CANDLE_CAKE(new String[0]), YELLOW_CARPET(4, new String[] { "CARPET" }), YELLOW_CONCRETE(4, new String[] { "CONCRETE" }),
/*  474 */   YELLOW_CONCRETE_POWDER(4, new String[] { "CONCRETE_POWDER" }), YELLOW_DYE(11, new String[] { "INK_SACK", "DANDELION_YELLOW" }),
/*  475 */   YELLOW_GLAZED_TERRACOTTA(4, new String[] { "STAINED_CLAY", "YELLOW_TERRACOTTA" }), YELLOW_SHULKER_BOX(new String[0]),
/*  476 */   YELLOW_STAINED_GLASS(4, new String[] { "STAINED_GLASS" }), YELLOW_STAINED_GLASS_PANE(4, new String[] { "THIN_GLASS", "STAINED_GLASS_PANE" }),
/*  477 */   YELLOW_TERRACOTTA(4, new String[] { "STAINED_CLAY" }), YELLOW_WALL_BANNER(11, new String[] { "WALL_BANNER" }), YELLOW_WOOL(4, new String[] { "WOOL" }),
/*  478 */   ZOGLIN_SPAWN_EGG(new String[0]), ZOMBIE_HEAD(2, new String[] { "SKULL", "SKULL_ITEM" }), ZOMBIE_HORSE_SPAWN_EGG(29, new String[] { "MONSTER_EGG" }),
/*  479 */   ZOMBIE_SPAWN_EGG(54, new String[] { "MONSTER_EGG" }), ZOMBIE_VILLAGER_SPAWN_EGG(27, new String[] { "MONSTER_EGG" }),
/*  480 */   ZOMBIE_WALL_HEAD(2, new String[] { "SKULL", "SKULL_ITEM" }),
/*  481 */   ZOMBIFIED_PIGLIN_SPAWN_EGG(57, new String[] { "MONSTER_EGG", "ZOMBIE_PIGMAN_SPAWN_EGG" });
/*      */   public static final XMaterial[] VALUES;
/*      */   private static final Map<String, XMaterial> NAMES;
/*      */   private static final Cache<String, XMaterial> NAME_CACHE;
/*      */   private static final LoadingCache<String, Pattern> CACHED_REGEX;
/*      */   private static final byte MAX_DATA_VALUE = 120;
/*      */   
/*      */   static {
/*  489 */     VALUES = values();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  497 */     NAMES = new HashMap<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  506 */     NAME_CACHE = CacheBuilder.newBuilder()
/*  507 */       .expireAfterAccess(1L, TimeUnit.HOURS).build();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  514 */     CACHED_REGEX = CacheBuilder.newBuilder()
/*  515 */       .expireAfterAccess(3L, TimeUnit.HOURS).build(new CacheLoader<String, Pattern>()
/*      */         {
/*      */           public Pattern load(@Nonnull String str) {
/*      */             try {
/*  519 */               return Pattern.compile(str);
/*  520 */             } catch (PatternSyntaxException ex) {
/*  521 */               ex.printStackTrace();
/*  522 */               return null;
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
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
/*      */     
/*  565 */     for (i = (arrayOfXMaterial = VALUES).length, b = 0; b < i; ) { XMaterial material = arrayOfXMaterial[b];
/*  566 */       NAMES.put(material.name(), material);
/*      */       
/*      */       b++; }
/*      */     
/*  570 */     if (Data.ISFLAT) {
/*      */       
/*  572 */       DUPLICATED = null;
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  578 */       DUPLICATED = new HashSet<>(4);
/*  579 */       DUPLICATED.add(GRASS.name());
/*  580 */       DUPLICATED.add(MELON.name());
/*  581 */       DUPLICATED.add(BRICK.name());
/*  582 */       DUPLICATED.add(NETHER_BRICK.name());
/*      */     } 
/*      */   }
/*      */ 
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
/*  611 */     this.data = (byte)data;
/*  612 */     this.legacy = legacy;
/*      */     
/*  614 */     Material mat = null;
/*  615 */     if ((!Data.ISFLAT && isDuplicated()) || (mat = Material.getMaterial(name())) == null)
/*  616 */       for (int i = legacy.length - 1; i >= 0; i--) {
/*  617 */         mat = Material.getMaterial(legacy[i]);
/*  618 */         if (mat != null) {
/*      */           break;
/*      */         }
/*      */       }  
/*  622 */     this.material = mat;
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
/*      */   @Deprecated
/*      */   public static boolean isNewVersion() {
/*  644 */     return Data.ISFLAT;
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
/*      */   @Deprecated
/*      */   public static boolean isOneEight() {
/*  663 */     return !supports(9);
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
/*  677 */     return Optional.ofNullable(NAMES.get(name));
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
/*  688 */     return Data.VERSION;
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
/*  700 */     String holder = String.valueOf(name) + data;
/*  701 */     XMaterial cache = (XMaterial)NAME_CACHE.getIfPresent(holder);
/*  702 */     if (cache != null)
/*  703 */       return cache;  byte b; int i;
/*      */     XMaterial[] arrayOfXMaterial;
/*  705 */     for (i = (arrayOfXMaterial = VALUES).length, b = 0; b < i; ) { XMaterial material = arrayOfXMaterial[b];
/*      */       
/*  707 */       if ((data == -1 || data == material.data) && material.anyMatchLegacy(name)) {
/*  708 */         NAME_CACHE.put(holder, material);
/*  709 */         return material;
/*      */       } 
/*      */       b++; }
/*      */     
/*  713 */     return null;
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
/*      */   public static Optional<XMaterial> matchXMaterial(@Nonnull String name) {
/*  727 */     Validate.notEmpty(name, "Cannot match a material with null or empty material name");
/*  728 */     Optional<XMaterial> oldMatch = matchXMaterialWithData(name);
/*  729 */     return oldMatch.isPresent() ? oldMatch : matchDefinedXMaterial(format(name), (byte)-1);
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
/*      */   @Nonnull
/*      */   private static Optional<XMaterial> matchXMaterialWithData(@Nonnull String name) {
/*  754 */     int index = name.indexOf(':');
/*  755 */     if (index != -1) {
/*  756 */       String mat = format(name.substring(0, index));
/*      */       
/*      */       try {
/*  759 */         byte data = (byte)Integer.parseInt(StringUtils.deleteWhitespace(name.substring(index + 1)));
/*  760 */         return (data >= 0 && data < 120) ? matchDefinedXMaterial(mat, data) : 
/*  761 */           matchDefinedXMaterial(mat, (byte)-1);
/*  762 */       } catch (NumberFormatException ignored) {
/*  763 */         return matchDefinedXMaterial(mat, (byte)-1);
/*      */       } 
/*      */     } 
/*      */     
/*  767 */     return Optional.empty();
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
/*  780 */     Objects.requireNonNull(material, "Cannot match null material");
/*  781 */     return matchDefinedXMaterial(material.name(), (byte)-1).<Throwable>orElseThrow(() -> new IllegalArgumentException("Unsupported material with no data value: " + paramMaterial.name()));
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
/*      */   @Nonnull
/*      */   public static XMaterial matchXMaterial(@Nonnull ItemStack item) {
/*  799 */     Objects.requireNonNull(item, "Cannot match null ItemStack");
/*  800 */     String material = item.getType().name();
/*  801 */     byte data = (byte)((Data.ISFLAT || item.getType().getMaxDurability() > 0) ? 0 : item.getDurability());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  807 */     return matchDefinedXMaterial(material, data).<Throwable>orElseThrow(() -> new IllegalArgumentException("Unsupported material from item: " + paramString + " (" + paramByte + ')'));
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
/*      */   @Nonnull
/*      */   protected static Optional<XMaterial> matchDefinedXMaterial(@Nonnull String name, byte data) {
/*  831 */     Boolean duplicated = null;
/*  832 */     boolean isAMap = name.equalsIgnoreCase("MAP");
/*      */ 
/*      */     
/*  835 */     if (Data.ISFLAT || (!isAMap && data <= 0 && !(duplicated = Boolean.valueOf(isDuplicated(name))).booleanValue())) {
/*  836 */       Optional<XMaterial> xMaterial = getIfPresent(name);
/*  837 */       if (xMaterial.isPresent()) {
/*  838 */         return xMaterial;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  843 */     XMaterial oldXMaterial = requestOldXMaterial(name, data);
/*  844 */     if (oldXMaterial == null)
/*      */     {
/*  846 */       return (data >= 0 && isAMap) ? Optional.<XMaterial>of(FILLED_MAP) : Optional.<XMaterial>empty();
/*      */     }
/*      */     
/*  849 */     if (!Data.ISFLAT && oldXMaterial.isPlural() && ((duplicated == null) ? isDuplicated(name) : duplicated.booleanValue()))
/*  850 */       return getIfPresent(name); 
/*  851 */     return Optional.of(oldXMaterial);
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
/*      */   private static boolean isDuplicated(@Nonnull String name) {
/*  871 */     return DUPLICATED.contains(name);
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
/*      */   @Nonnull
/*      */   @Deprecated
/*      */   public static Optional<XMaterial> matchXMaterial(int id, byte data) {
/*  895 */     if (id < 0 || id > 2267 || data < 0)
/*  896 */       return Optional.empty();  byte b; int i; XMaterial[] arrayOfXMaterial;
/*  897 */     for (i = (arrayOfXMaterial = VALUES).length, b = 0; b < i; ) { XMaterial materials = arrayOfXMaterial[b];
/*  898 */       if (materials.data == data && materials.getId() == id)
/*  899 */         return Optional.of(materials);  b++; }
/*      */     
/*  901 */     return Optional.empty();
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
/*      */     //   #918	-> 0
/*      */     //   #919	-> 5
/*      */     //   #920	-> 9
/*      */     //   #921	-> 11
/*      */     //   #923	-> 14
/*      */     //   #924	-> 20
/*      */     //   #926	-> 28
/*      */     //   #927	-> 66
/*      */     //   #929	-> 72
/*      */     //   #931	-> 75
/*      */     //   #932	-> 128
/*      */     //   #933	-> 133
/*      */     //   #934	-> 141
/*      */     //   #937	-> 144
/*      */     //   #938	-> 149
/*      */     //   #940	-> 160
/*      */     //   #923	-> 172
/*      */     //   #945	-> 181
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
/*      */ 
/*      */   
/*      */   public static boolean supports(int version) {
/*  959 */     return (Data.VERSION >= version);
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
/*      */   @Nonnull
/*      */   public static String getMajorVersion(@Nonnull String version) {
/*  977 */     Validate.notEmpty(version, "Cannot get major Minecraft version from null or empty string");
/*      */ 
/*      */     
/*  980 */     int index = version.lastIndexOf("MC:");
/*  981 */     if (index != -1) {
/*  982 */       version = version.substring(index + 4, version.length() - 1);
/*  983 */     } else if (version.endsWith("SNAPSHOT")) {
/*      */       
/*  985 */       index = version.indexOf('-');
/*  986 */       version = version.substring(0, index);
/*      */     } 
/*      */ 
/*      */     
/*  990 */     int lastDot = version.lastIndexOf('.');
/*  991 */     if (version.indexOf('.') != lastDot) {
/*  992 */       version = version.substring(0, lastDot);
/*      */     }
/*  994 */     return version;
/*      */   }
/*      */   
/*      */   public String[] getLegacy() {
/*  998 */     return this.legacy;
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
/* 1019 */     return !(this != CARROTS && this != POTATOES);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isOneOf(@Nullable Collection<String> materials) {
/* 1068 */     if (materials == null || materials.isEmpty())
/* 1069 */       return false; 
/* 1070 */     String name = name();
/*      */     
/* 1072 */     for (String comp : materials) {
/* 1073 */       String checker = comp.toUpperCase(Locale.ENGLISH);
/* 1074 */       if (checker.startsWith("CONTAINS:")) {
/* 1075 */         comp = format(checker.substring(9));
/* 1076 */         if (name.contains(comp))
/* 1077 */           return true; 
/*      */         continue;
/*      */       } 
/* 1080 */       if (checker.startsWith("REGEX:")) {
/* 1081 */         comp = comp.substring(6);
/* 1082 */         Pattern pattern = (Pattern)CACHED_REGEX.getUnchecked(comp);
/* 1083 */         if (pattern != null && pattern.matcher(name).matches()) {
/* 1084 */           return true;
/*      */         }
/*      */         
/*      */         continue;
/*      */       } 
/* 1089 */       Optional<XMaterial> xMat = matchXMaterial(comp);
/* 1090 */       if (xMat.isPresent() && xMat.get() == this)
/* 1091 */         return true; 
/*      */     } 
/* 1093 */     return false;
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
/* 1110 */     Objects.requireNonNull(item, "Cannot set material for null ItemStack");
/* 1111 */     Material material = parseMaterial();
/* 1112 */     Objects.requireNonNull(material, () -> "Unsupported material: " + name());
/*      */     
/* 1114 */     item.setType(material);
/* 1115 */     if (!Data.ISFLAT && material.getMaxDurability() <= 0)
/* 1116 */       item.setDurability(this.data); 
/* 1117 */     return item;
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
/*      */   private boolean anyMatchLegacy(@Nonnull String name) {
/* 1131 */     for (int i = this.legacy.length - 1; i >= 0; i--) {
/* 1132 */       if (name.equals(this.legacy[i]))
/* 1133 */         return true; 
/*      */     } 
/* 1135 */     return false;
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
/*      */   @Nonnull
/*      */   public String toString() {
/* 1156 */     return WordUtils.capitalize(name().replace('_', ' ').toLowerCase(Locale.ENGLISH));
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
/*      */   public int getId() {
/* 1174 */     Material material = parseMaterial();
/* 1175 */     if (material == null)
/* 1176 */       return -1; 
/*      */     try {
/* 1178 */       return material.getId();
/* 1179 */     } catch (IllegalArgumentException ignored) {
/* 1180 */       return -1;
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
/*      */ 
/*      */   
/*      */   public byte getData() {
/* 1196 */     return this.data;
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
/*      */   @Nullable
/*      */   public ItemStack parseItem() {
/* 1210 */     Material material = parseMaterial();
/* 1211 */     if (material == null)
/* 1212 */       return null; 
/* 1213 */     return Data.ISFLAT ? new ItemStack(material) : new ItemStack(material, 1, this.data);
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack parseItem(int A) {
/* 1218 */     Material material = parseMaterial();
/* 1219 */     if (material == null)
/* 1220 */       return null; 
/* 1221 */     return Data.ISFLAT ? new ItemStack(material, A) : new ItemStack(material, A, this.data);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isAir(ItemStack IT) {
/* 1226 */     if (IT == null || matchXMaterial(IT) == AIR) {
/* 1227 */       return true;
/*      */     }
/* 1229 */     return false;
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
/* 1240 */     return this.material;
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
/*      */   public boolean isSimilar(@Nonnull ItemStack item) {
/* 1254 */     Objects.requireNonNull(item, "Cannot compare with null ItemStack");
/* 1255 */     if (item.getType() != parseMaterial())
/* 1256 */       return false; 
/* 1257 */     return !(!Data.ISFLAT && item.getDurability() != this.data && item.getType().getMaxDurability() <= 0);
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
/* 1271 */     return (this.material != null);
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
/*      */   private boolean isDuplicated() {
/*      */     String str;
/* 1286 */     switch ((str = name()).hashCode()) { case -1929109465: if (!str.equals("POTATO")) {
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
/*      */         
/* 1310 */         return true;case -1722057187: if (!str.equals("DARK_OAK_DOOR")) break;  return true;case -519277571: if (!str.equals("BIRCH_DOOR")) break;  return true;case -333218805: if (!str.equals("SPRUCE_DOOR")) break;  return true;case -328086150: if (!str.equals("NETHER_BRICK")) break;  return true;case 76092: if (!str.equals("MAP")) break;  return true;case 63467553: if (!str.equals("BRICK")) break;  return true;case 68077974: if (!str.equals("GRASS")) break;  return true;case 73242259: if (!str.equals("MELON")) break;  return true;case 478520881: if (!str.equals("ACACIA_DOOR")) break;  return true;case 868145122: if (!str.equals("CAULDRON")) break;  return true;case 1379814896: if (!str.equals("JUNGLE_DOOR")) break;  return true;case 1401892433: if (!str.equals("FLOWER_POT")) break;  return true;case 1545025079: if (!str.equals("BREWING_STAND")) break;  return true;case 1980706179: if (!str.equals("CARROT")) break;  return true; }
/*      */     
/* 1312 */     return false;
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
/*      */   private static final class Data
/*      */   {
/* 1329 */     private static final int VERSION = Integer.parseInt(XMaterial.getMajorVersion(Bukkit.getVersion()).substring(2));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1335 */     private static final boolean ISFLAT = XMaterial.supports(13);
/*      */   }
/*      */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabine\\utils\XMaterial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */