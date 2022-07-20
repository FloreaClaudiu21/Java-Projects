/*     */ package me.furnace.version;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import me.furnace.IMain;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class IVersion {
/*     */   private Object MAIN_WORLD;
/*     */   private Object RECIPES;
/*     */   public boolean LEGACY_RECIPE = false;
/*     */   private boolean LEGACY_ACTION = false;
/*     */   private boolean LEGACY_FURNACE = false;
/*     */   private Constructor<?> CHATPACKET;
/*     */   private Constructor<?> CHATCOMPONENTTEXT;
/*     */   private Constructor<?> BLOCKPOS;
/*     */   private Constructor<?> FURNACETILE;
/*     */   private Constructor<?> ACTIONBARPACKET;
/*     */   private Constructor<?> TIMESPACKET;
/*     */   private Constructor<?> TITLEPACKET;
/*     */   private Constructor<?> SUBTITLEPACKET;
/*     */   public Field TILE_SET_WORLD_FIELD;
/*     */   public Field REACH_FIELD;
/*     */   public Field ACTIVECONTAINER_FIELD;
/*     */   public Field FURNACE_TILE_BURN_FIELD;
/*     */   public Field FURNACE_TILE_COOK_FIELD;
/*     */   public Field FURNACE_TILE_COOKT_FIELD;
/*     */   public Field PLAYERCONNECTION_FIELD;
/*     */   public Method NBT_STRING_METHOD;
/*     */   public Method CRAFTWORLD_HANDLE_METHOD;
/*     */   public Method OPENCONTAINER_METHOD;
/*     */   public Method CITEMSTACK_NMS_METHOD;
/*     */   public Method CITEMSTACK_BUKKIT_METHOD;
/*     */   public Method ITEMSTACK_SAVE_METHOD;
/*     */   public Method FURNACE_TILE_GETITEM_METHOD;
/*     */   public Method FURNACE_TILE_SETITEM_METHOD;
/*     */   public Method FURNACE_TILE_HASFUEL_METHOD;
/*     */   public Method FURNACE_TILE_VIEWERS_METHOD;
/*     */   
/*     */   public IVersion() {
/*  40 */     if (IMain.VEID > 13) {
/*  41 */       this.LEGACY_FURNACE = true;
/*     */     }
/*  43 */     if (IMain.VEID > 12) {
/*  44 */       this.LEGACY_RECIPE = true;
/*     */     }
/*  46 */     if (IMain.NEWVERSION) {
/*  47 */       this.LEGACY_ACTION = true;
/*     */       
/*  49 */       this.CHATTYPETEXT_CLASS = C1(true, "network.chat.ChatComponentText");
/*  50 */       this.CHATBASECOMPONENT_CLASS = C1(true, "network.chat.IChatBaseComponent");
/*  51 */       this.CHATMESSAGETYPE_CLASS = C1(true, "network.chat.ChatMessageType");
/*  52 */       this.NBT_CLASS = C1(true, "nbt.NBTTagCompound");
/*  53 */       this.NMSITEMSTACK_CLASS = C1(true, "world.item.ItemStack");
/*  54 */       this.CONTAINER_CLASS = C1(true, "world.inventory.Container");
/*  55 */       this.BLOCKPOSITION_CLASS = C1(true, "core.BlockPosition");
/*  56 */       this.IBLOCKDATA_CLASS = C1(true, "world.level.block.state.IBlockData");
/*  57 */       this.CRAFTBLOCK_DATA_CLASS = C1(false, "block.data.CraftBlockData");
/*  58 */       this.ITILEINV_CLASS = C1(true, "world.ITileInventory");
/*  59 */       this.TILE_CLASS = C1(true, "world.level.block.entity.TileEntity");
/*  60 */       this.CRAFTPLAYER_CLASS = C1(false, "entity.CraftPlayer");
/*  61 */       this.CRAFTWORLD_CLASS = C1(false, "CraftWorld");
/*  62 */       this.CRAFTITEMSTACK_CLASS = C1(false, "inventory.CraftItemStack");
/*  63 */       this.FURNACE_TILE_CLASS = C1(true, "world.level.block.entity.TileEntityFurnace");
/*  64 */       this.FURNACE_TILE_F_CLASS = C1(true, "world.level.block.entity.TileEntityFurnaceFurnace");
/*  65 */       this.ENTITY_CLASS = C1(true, "world.entity.Entity");
/*  66 */       this.NMSITEMSTACK_CLASS = C1(true, "world.item.ItemStack");
/*  67 */       this.ENTITYPLAYER_CLASS = C1(true, "server.level.EntityPlayer");
/*  68 */       this.ENTITYHUMAN_CLASS = C1(true, "world.entity.player.EntityHuman");
/*  69 */       this.PLAYERCONNECTION_CLASS = C1(true, "server.network.PlayerConnection");
/*  70 */       this.PLAYOUTACTIONBAR_PACKET_CLASS = C1(true, "network.protocol.game.ClientboundSetActionBarTextPacket");
/*  71 */       this.PLAYOUTTITLETIMES_PACKET_CLASS = C1(true, "network.protocol.game.ClientboundSetTitlesAnimationPacket");
/*  72 */       this.PLAYOUTTITLE_PACKET_CLASS = C1(true, "network.protocol.game.ClientboundSetTitleTextPacket");
/*  73 */       this.PLAYOUTSUBTITLE_PACKET_CLASS = C1(true, "network.protocol.game.ClientboundSetSubtitleTextPacket");
/*     */       
/*  75 */       this.TILE_SET_WORLD_FIELD = F1(this.TILE_CLASS, "n");
/*  76 */       this.REACH_FIELD = F1(this.CONTAINER_CLASS, "checkReachable");
/*  77 */       this.FURNACE_TILE_BURN_FIELD = F1(this.FURNACE_TILE_CLASS, "t");
/*  78 */       this.FURNACE_TILE_COOK_FIELD = F1(this.FURNACE_TILE_CLASS, "v");
/*  79 */       this.FURNACE_TILE_COOKT_FIELD = F1(this.FURNACE_TILE_CLASS, "w");
/*  80 */       this.ACTIVECONTAINER_FIELD = F1(this.ENTITYHUMAN_CLASS, "bW");
/*  81 */       this.PLAYERCONNECTION_FIELD = F1(this.ENTITYPLAYER_CLASS, "b");
/*     */       
/*  83 */       this.TIMESPACKET = CO(this.PLAYOUTTITLETIMES_PACKET_CLASS, new Class[] { int.class, int.class, int.class });
/*  84 */       this.TITLEPACKET = CO(this.PLAYOUTTITLE_PACKET_CLASS, new Class[] { this.CHATBASECOMPONENT_CLASS });
/*  85 */       this.SUBTITLEPACKET = CO(this.PLAYOUTSUBTITLE_PACKET_CLASS, new Class[] { this.CHATBASECOMPONENT_CLASS });
/*  86 */       this.ACTIONBARPACKET = CO(this.PLAYOUTACTIONBAR_PACKET_CLASS, new Class[] { this.CHATBASECOMPONENT_CLASS });
/*     */       try {
/*  88 */         this.CHATCOMPONENTTEXT = this.CHATTYPETEXT_CLASS.getConstructor(new Class[] { String.class });
/*  89 */       } catch (NoSuchMethodException|SecurityException e) {
/*  90 */         e.printStackTrace();
/*     */       } 
/*  92 */       this.BLOCKPOS = CO(this.BLOCKPOSITION_CLASS, new Class[] { int.class, int.class, int.class });
/*  93 */       this.FURNACETILE = CO(this.FURNACE_TILE_F_CLASS, new Class[] { this.BLOCKPOSITION_CLASS, this.IBLOCKDATA_CLASS });
/*     */       
/*  95 */       this.NBT_STRING_METHOD = M1(this.NBT_CLASS, "l", new Class[] { String.class });
/*  96 */       this.SEND_PACKET_METHOD = M1(this.PLAYERCONNECTION_CLASS, "a", new Class[] { Packet.class });
/*  97 */       this.CRAFTWORLD_HANDLE_METHOD = M(this.CRAFTWORLD_CLASS, "getHandle");
/*  98 */       this.OPENCONTAINER_METHOD = M1(this.ENTITYPLAYER_CLASS, "a", new Class[] { this.ITILEINV_CLASS });
/*  99 */       this.PLAYER_HANDLE_METHOD = M(this.CRAFTPLAYER_CLASS, "getHandle");
/* 100 */       this.BLOCKDATA_HANDLE_METHOD = M(this.CRAFTBLOCK_DATA_CLASS, "getState");
/* 101 */       this.CITEMSTACK_NMS_METHOD = M(this.CRAFTITEMSTACK_CLASS, "asNMSCopy");
/* 102 */       this.CITEMSTACK_BUKKIT_METHOD = M(this.CRAFTITEMSTACK_CLASS, "asBukkitCopy");
/* 103 */       this.ITEMSTACK_SAVE_METHOD = M1(this.NMSITEMSTACK_CLASS, "b", new Class[] { this.NBT_CLASS });
/* 104 */       this.FURNACE_TILE_GETITEM_METHOD = M1(this.FURNACE_TILE_CLASS, "a", new Class[] { int.class });
/* 105 */       this.FURNACE_TILE_SETITEM_METHOD = M1(this.FURNACE_TILE_CLASS, "a", new Class[] { int.class, this.NMSITEMSTACK_CLASS });
/* 106 */       this.FURNACE_TILE_FUELTIME_METHOD = M1(this.FURNACE_TILE_CLASS, "a", new Class[] { this.NMSITEMSTACK_CLASS });
/* 107 */       this.FURNACE_TILE_HASFUEL_METHOD = M1(this.FURNACE_TILE_CLASS, "b", new Class[] { this.NMSITEMSTACK_CLASS });
/* 108 */       this.FURNACE_TILE_VIEWERS_METHOD = M(this.FURNACE_TILE_CLASS, "getViewers");
/*     */       try {
/* 110 */         this.MAIN_WORLD = this.CRAFTWORLD_HANDLE_METHOD.invoke(IMain.S.getWorlds().get(0), new Object[0]);
/* 111 */         if (!this.LEGACY_RECIPE) {
/* 112 */           Method M = M(this.RECIPES_CLASS, "getInstance");
/* 113 */           this.RECIPES = M.invoke(null, new Object[0]);
/*     */         } 
/* 115 */       } catch (Exception E) {
/* 116 */         IMain.ERROR = true;
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/* 121 */     this.CHATTYPETEXT_CLASS = C(true, "ChatComponentText");
/* 122 */     this.PLAYOUTACTIONBAR_PACKET_CLASS = C(true, "PacketPlayOutChat");
/* 123 */     this.CHATBASECOMPONENT_CLASS = C(true, "IChatBaseComponent");
/* 124 */     if (IMain.VEID > 11) {
/* 125 */       this.LEGACY_ACTION = true;
/* 126 */       this.CHATMESSAGETYPE_CLASS = C(true, "ChatMessageType");
/* 127 */       if (IMain.VEID == 16) {
/* 128 */         this.CHATPACKET = CO(this.PLAYOUTACTIONBAR_PACKET_CLASS, new Class[] { this.CHATBASECOMPONENT_CLASS, 
/* 129 */               this.CHATMESSAGETYPE_CLASS, UUID.class });
/*     */       } else {
/* 131 */         this.CHATPACKET = CO(this.PLAYOUTACTIONBAR_PACKET_CLASS, new Class[] { this.CHATBASECOMPONENT_CLASS, 
/* 132 */               this.CHATMESSAGETYPE_CLASS });
/*     */       } 
/*     */     } else {
/* 135 */       this.CHATPACKET = CO(this.PLAYOUTACTIONBAR_PACKET_CLASS, new Class[] { this.CHATBASECOMPONENT_CLASS, byte.class });
/*     */     } 
/* 137 */     this.NBT_CLASS = C(true, "NBTTagCompound");
/* 138 */     this.CONTAINER_CLASS = C(true, "Container");
/* 139 */     this.TILE_CLASS = C(true, "TileEntity");
/* 140 */     this.CRAFTPLAYER_CLASS = C(false, "entity.CraftPlayer");
/* 141 */     this.CRAFTWORLD_CLASS = C(false, "CraftWorld");
/* 142 */     this.CRAFTITEMSTACK_CLASS = C(false, "inventory.CraftItemStack");
/* 143 */     this.FURNACE_TILE_CLASS = C(true, "TileEntityFurnace");
/* 144 */     if (this.LEGACY_FURNACE) {
/* 145 */       this.FURNACE_TILE_F_CLASS = C(true, "TileEntityFurnaceFurnace");
/*     */     }
/* 147 */     this.ENTITY_CLASS = C(true, "Entity");
/* 148 */     this.NMSITEMSTACK_CLASS = C(true, "ItemStack");
/* 149 */     this.ENTITYPLAYER_CLASS = C(true, "EntityPlayer");
/* 150 */     this.ENTITYHUMAN_CLASS = C(true, "EntityHuman");
/* 151 */     this.PLAYOUTTITLE_PACKET_CLASS = C(true, "PacketPlayOutTitle");
/* 152 */     this.PLAYERCONNECTION_CLASS = C(true, "PlayerConnection");
/* 153 */     this.CHATCOMPONENTTEXT = CO(this.CHATTYPETEXT_CLASS, new Class[] { String.class });
/*     */     
/* 155 */     this.TILE_SET_WORLD_FIELD = F(this.TILE_CLASS, "world");
/* 156 */     this.REACH_FIELD = F(this.CONTAINER_CLASS, "checkReachable");
/* 157 */     this.FURNACE_TILE_BURN_FIELD = F(this.FURNACE_TILE_CLASS, "burnTime");
/* 158 */     this.FURNACE_TILE_COOK_FIELD = F(this.FURNACE_TILE_CLASS, "cookTime");
/* 159 */     this.FURNACE_TILE_COOKT_FIELD = F(this.FURNACE_TILE_CLASS, "cookTimeTotal");
/* 160 */     this.ACTIVECONTAINER_FIELD = F(this.ENTITYHUMAN_CLASS, "activeContainer");
/* 161 */     this.PLAYERCONNECTION_FIELD = F(this.ENTITYPLAYER_CLASS, "playerConnection");
/*     */     
/* 163 */     if (!this.LEGACY_RECIPE) {
/* 164 */       this.RECIPES_CLASS = C(true, "RecipesFurnace");
/* 165 */       this.EXPERIENCE_METHOD = M(this.RECIPES_CLASS, "b");
/*     */     } 
/* 167 */     this.NBT_STRING_METHOD = M(this.NBT_CLASS, "getString");
/* 168 */     this.SEND_PACKET_METHOD = M(this.PLAYERCONNECTION_CLASS, "sendPacket");
/* 169 */     this.CRAFTWORLD_HANDLE_METHOD = M(this.CRAFTWORLD_CLASS, "getHandle");
/* 170 */     this.OPENCONTAINER_METHOD = M(this.ENTITYPLAYER_CLASS, "openContainer");
/* 171 */     this.PLAYER_HANDLE_METHOD = M(this.CRAFTPLAYER_CLASS, "getHandle");
/* 172 */     this.CITEMSTACK_NMS_METHOD = M(this.CRAFTITEMSTACK_CLASS, "asNMSCopy");
/* 173 */     this.CITEMSTACK_BUKKIT_METHOD = M(this.CRAFTITEMSTACK_CLASS, "asBukkitCopy");
/* 174 */     this.ITEMSTACK_SAVE_METHOD = M(this.NMSITEMSTACK_CLASS, "save");
/* 175 */     this.FURNACE_TILE_GETITEM_METHOD = M(this.FURNACE_TILE_CLASS, "getItem");
/* 176 */     this.FURNACE_TILE_SETITEM_METHOD = M(this.FURNACE_TILE_CLASS, "setItem");
/* 177 */     this.FURNACE_TILE_FUELTIME_METHOD = M(this.FURNACE_TILE_CLASS, "fuelTime");
/* 178 */     this.FURNACE_TILE_HASFUEL_METHOD = M(this.FURNACE_TILE_CLASS, "isFuel");
/* 179 */     this.FURNACE_TILE_VIEWERS_METHOD = M(this.FURNACE_TILE_CLASS, "getViewers");
/*     */     
/*     */     try {
/* 182 */       this.MAIN_WORLD = this.CRAFTWORLD_HANDLE_METHOD.invoke(IMain.S.getWorlds().get(0), new Object[0]);
/* 183 */       if (!this.LEGACY_RECIPE) {
/* 184 */         Method M = M(this.RECIPES_CLASS, "getInstance");
/* 185 */         this.RECIPES = M.invoke(null, new Object[0]);
/*     */       } 
/* 187 */     } catch (Exception E) {
/* 188 */       IMain.ERROR = true;
/*     */     } 
/*     */   }
/*     */   public Method FURNACE_TILE_FUELTIME_METHOD; public Method SEND_PACKET_METHOD; public Method PLAYER_HANDLE_METHOD; public Method BLOCKDATA_HANDLE_METHOD; public Method EXPERIENCE_METHOD; public Class<?> NBT_CLASS; public Class<?> NSMITEM_CLASS; public Class<?> RECIPES_CLASS; public Class<?> CONTAINER_CLASS;
/*     */   public Class<?> TILE_CLASS;
/*     */   public Class<?> NMSITEMSTACK_CLASS;
/*     */   public Class<?> CRAFTWORLD_CLASS;
/*     */   public Class<?> CRAFTITEMSTACK_CLASS;
/*     */   public Class<?> FURNACE_TILE_CLASS;
/*     */   public Class<?> FURNACE_TILE_F_CLASS;
/*     */   
/*     */   private Constructor<?> CO(Class<?> C, Class... O) {
/* 200 */     Validate.notNull(C, "Class can't be set to null");
/* 201 */     Validate.notNull(O, "Object can't be set to null");
/*     */     
/* 203 */     Constructor<?> CO = null;
/*     */     try {
/* 205 */       CO = C.getConstructor(O);
/* 206 */     } catch (Exception E) {
/* 207 */       IMain.ERROR = true;
/* 208 */       IMain.UTILS.console("&7>> &cConstructor &b(" + O.toString() + ") " + "&cdoes not exist in the class &6&l" + 
/* 209 */           C.getName());
/*     */     } 
/* 211 */     return CO;
/*     */   }
/*     */   public Class<?> CHATTYPETEXT_CLASS; public Class<?> CHATMESSAGETYPE_CLASS; public Class<?> CHATBASECOMPONENT_CLASS; public Class<?> CRAFTPLAYER_CLASS; public Class<?> ENTITY_CLASS; public Class<?> ENTITYPLAYER_CLASS; public Class<?> ENTITYHUMAN_CLASS; public Class<?> PLAYERCONNECTION_CLASS; public Class<?> PLAYOUTTITLE_PACKET_CLASS; public Class<?> PLAYOUTSUBTITLE_PACKET_CLASS; public Class<?> PLAYOUTTITLETIMES_PACKET_CLASS; public Class<?> PLAYOUTACTIONBAR_PACKET_CLASS; public Class<?> BLOCKPOSITION_CLASS; public Class<?> IBLOCKDATA_CLASS; public Class<?> CRAFTBLOCK_DATA_CLASS; public Class<?> ITILEINV_CLASS;
/*     */   
/*     */   private Class<?> C1(boolean NET, String NAME) {
/* 216 */     Validate.notNull(NAME, "Name can't be set to null");
/*     */     try {
/* 218 */       if (NET) {
/* 219 */         return Class.forName("net.minecraft." + NAME);
/*     */       }
/* 221 */       return Class.forName("org.bukkit.craftbukkit." + IMain.VE + "." + NAME);
/*     */     }
/* 223 */     catch (Exception E) {
/* 224 */       IMain.ERROR = true;
/* 225 */       String N = "net.minecraft.";
/*     */       
/* 227 */       if (!NET) {
/* 228 */         N = "org.bukkit.craftbukkit." + IMain.VE + ".";
/*     */       }
/* 230 */       IMain.UTILS.console("&7>> &cClass &6&l" + N + NAME + " &cdoes not exist");
/*     */       
/* 232 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private Class<?> C(boolean NET, String NAME) {
/* 237 */     Validate.notNull(NAME, "Name can't be set to null");
/*     */     try {
/* 239 */       if (NET) {
/* 240 */         return Class.forName("net.minecraft.server." + IMain.VE + "." + NAME);
/*     */       }
/* 242 */       return Class.forName("org.bukkit.craftbukkit." + IMain.VE + "." + NAME);
/*     */     }
/* 244 */     catch (Exception E) {
/* 245 */       IMain.ERROR = true;
/* 246 */       String N = "net.minecraft.server." + IMain.VE + ".";
/*     */       
/* 248 */       if (!NET) {
/* 249 */         N = "org.bukkit.craftbukkit." + IMain.VE + ".";
/*     */       }
/* 251 */       IMain.UTILS.console("&7>> &cClass &6&l" + N + NAME + " &cdoes not exist");
/*     */       
/* 253 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private Method M(Class<?> C, String NAME) {
/* 258 */     Validate.notNull(C, "Class can't be set to null");
/* 259 */     Validate.notNull(NAME, "Name can't be set to null");
/*     */     
/* 261 */     Method M = null; byte b; int i; Method[] arrayOfMethod;
/* 262 */     for (i = (arrayOfMethod = C.getDeclaredMethods()).length, b = 0; b < i; ) { Method M1 = arrayOfMethod[b];
/* 263 */       if (M1.getName().startsWith(NAME)) {
/* 264 */         M = M1;
/* 265 */         M.setAccessible(true);
/*     */       }  b++; }
/*     */     
/* 268 */     if (M == null) {
/* 269 */       IMain.ERROR = true;
/* 270 */       IMain.UTILS.console("&7>> &cMethod &b" + NAME + " &cdoes not exist in the class &6&l" + C.getName());
/*     */     } 
/* 272 */     return M;
/*     */   }
/*     */ 
/*     */   
/*     */   private Method M1(Class<?> C, String NAME, Class... CL) {
/* 277 */     Validate.notNull(C, "Class can't be set to null");
/* 278 */     Validate.notNull(NAME, "Name can't be set to null");
/*     */     
/* 280 */     Method M = null;
/*     */     try {
/* 282 */       M = C.getDeclaredMethod(NAME, CL);
/* 283 */       M.setAccessible(true);
/* 284 */     } catch (Exception e) {
/* 285 */       IMain.ERROR = true;
/* 286 */       IMain.UTILS.console("&7>> &cMethod &b" + NAME + " &cdoes not exist in the class &6&l" + C.getName());
/*     */     } 
/* 288 */     return M;
/*     */   }
/*     */ 
/*     */   
/*     */   private Field F(Class<?> C, String NAME) {
/* 293 */     Validate.notNull(C, "Class can't be set to null");
/* 294 */     Validate.notNull(NAME, "Name can't be set to null");
/*     */     
/* 296 */     Field F = null;
/*     */     try {
/* 298 */       F = C.getDeclaredField(NAME);
/* 299 */     } catch (Exception E) {
/* 300 */       IMain.ERROR = true;
/* 301 */       IMain.UTILS.console("&7>> &cField &b" + NAME + " &cdoes not exist in the class &6&l" + C.getName());
/* 302 */       return F;
/*     */     } 
/* 304 */     F.setAccessible(true);
/*     */     try {
/* 306 */       Field modifiersField = Field.class.getDeclaredField("modifiers");
/* 307 */       modifiersField.setAccessible(true);
/* 308 */       modifiersField.setInt(F, F.getModifiers() & 0xFFFFFFEF & 0xFFFFFFFB);
/* 309 */     } catch (Exception exception) {}
/*     */     
/* 311 */     return F;
/*     */   }
/*     */ 
/*     */   
/*     */   private Field F1(Class<?> C, String NAME) {
/* 316 */     Validate.notNull(C, "Class can't be set to null");
/* 317 */     Validate.notNull(NAME, "Name can't be set to null");
/*     */     
/* 319 */     Field F = null;
/*     */     try {
/* 321 */       F = C.getDeclaredField(NAME);
/* 322 */       F.setAccessible(true);
/* 323 */     } catch (Exception E) {
/* 324 */       IMain.ERROR = true;
/* 325 */       IMain.UTILS.console("&7>> &cField &b" + NAME + " &cdoes not exist in the class &6&l" + C.getName());
/*     */     } 
/* 327 */     return F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object create_tile() {
/*     */     try {
/* 336 */       Object TILE = null;
/*     */       
/* 338 */       if (IMain.NEWVERSION) {
/* 339 */         Object BLOCK_POS = this.BLOCKPOS.newInstance(new Object[] { Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0) });
/* 340 */         Object STATE = this.BLOCKDATA_HANDLE_METHOD.invoke(Material.FURNACE.createBlockData(), new Object[0]);
/* 341 */         TILE = this.FURNACETILE.newInstance(new Object[] { BLOCK_POS, STATE });
/* 342 */         this.TILE_SET_WORLD_FIELD.set(TILE, this.MAIN_WORLD);
/* 343 */         return TILE;
/*     */       } 
/* 345 */       if (this.LEGACY_FURNACE) {
/* 346 */         TILE = this.FURNACE_TILE_F_CLASS.newInstance();
/*     */       } else {
/* 348 */         TILE = this.FURNACE_TILE_CLASS.newInstance();
/*     */       } 
/* 350 */       this.TILE_SET_WORLD_FIELD.set(TILE, this.MAIN_WORLD);
/* 351 */       return TILE;
/* 352 */     } catch (Exception exception) {
/*     */       
/* 354 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Object create_nbt() {
/* 360 */     Object NBT = null;
/*     */     
/*     */     try {
/* 363 */       NBT = this.NBT_CLASS.newInstance();
/* 364 */     } catch (Exception exception) {}
/*     */     
/* 366 */     return NBT;
/*     */   }
/*     */ 
/*     */   
/*     */   public Float get_experience(FurnaceRecipe RECIPE) {
/* 371 */     Float F = Float.valueOf(0.1F);
/*     */     
/*     */     try {
/* 374 */       if (this.LEGACY_RECIPE) {
/* 375 */         F = Float.valueOf(RECIPE.getExperience());
/*     */       } else {
/* 377 */         F = (Float)this.EXPERIENCE_METHOD.invoke(this.RECIPES, new Object[] {
/* 378 */               this.CITEMSTACK_NMS_METHOD.invoke(null, new Object[] { RECIPE.getResult() }) });
/*     */       } 
/* 380 */     } catch (Exception exception) {}
/*     */     
/* 382 */     return F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendPacket(Player USER, Object packet) {
/*     */     try {
/* 390 */       Object nmsPlayer = this.PLAYER_HANDLE_METHOD.invoke(USER, new Object[0]);
/* 391 */       Object connection = this.PLAYERCONNECTION_FIELD.get(nmsPlayer);
/* 392 */       this.SEND_PACKET_METHOD.invoke(connection, new Object[] { packet });
/* 393 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean actionbar_send(Player USER, String MSG) {
/* 401 */     if (MSG == null || MSG.isEmpty()) {
/* 402 */       return false;
/*     */     }
/*     */     try {
/* 405 */       Object IN = null;
/*     */       
/* 407 */       if (IMain.NEWVERSION) {
/* 408 */         IN = this.ACTIONBARPACKET.newInstance(new Object[] { this.CHATCOMPONENTTEXT.newInstance(new Object[] { MSG }) });
/*     */       }
/* 410 */       else if (this.LEGACY_ACTION) {
/* 411 */         if (IMain.VEID == 16) {
/* 412 */           IN = this.CHATPACKET.newInstance(new Object[] { this.CHATCOMPONENTTEXT.newInstance(new Object[] { MSG
/* 413 */                   }), this.CHATMESSAGETYPE_CLASS.getEnumConstants()[2], USER.getUniqueId() });
/*     */         } else {
/* 415 */           IN = this.CHATPACKET.newInstance(new Object[] { this.CHATCOMPONENTTEXT.newInstance(new Object[] { MSG
/* 416 */                   }), this.CHATMESSAGETYPE_CLASS.getEnumConstants()[2] });
/*     */         } 
/*     */       } else {
/* 419 */         IN = this.CHATPACKET.newInstance(new Object[] { this.CHATCOMPONENTTEXT.newInstance(new Object[] { MSG }), Byte.valueOf((byte)2) });
/*     */       } 
/*     */       
/* 422 */       sendPacket(USER, IN);
/* 423 */     } catch (Exception e) {
/* 424 */       e.printStackTrace();
/*     */     } 
/* 426 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void set(Object IN, String P, Object V) {
/* 433 */     if (IN == null || P == null) {
/*     */       return;
/*     */     }
/*     */     try {
/* 437 */       Field F = this.PLAYOUTTITLE_PACKET_CLASS.getDeclaredField(P);
/*     */       
/* 439 */       F.setAccessible(true);
/* 440 */       F.set(IN, V);
/* 441 */       F.setAccessible(false);
/* 442 */     } catch (Exception e) {
/* 443 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean title_send(Player USER, String TITLE, String SUBTITLE) {
/* 450 */     return title_send(USER, TITLE, SUBTITLE, 30, 60, 30);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean title_send(Player USER, String TITLE, String SUBTITLE, int FADEIN, int STAY, int FADEOUT) {
/* 456 */     if (USER == null || TITLE == null || SUBTITLE == null) {
/* 457 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 462 */       if (IMain.NEWVERSION) {
/* 463 */         Object object1 = this.TIMESPACKET.newInstance(new Object[] { Integer.valueOf(FADEIN), Integer.valueOf(STAY), Integer.valueOf(FADEOUT) });
/* 464 */         sendPacket(USER, object1);
/* 465 */         Object object2 = this.TITLEPACKET.newInstance(new Object[] { this.CHATCOMPONENTTEXT.newInstance(new Object[] { TITLE }) });
/* 466 */         sendPacket(USER, object2);
/* 467 */         Object object3 = this.SUBTITLEPACKET.newInstance(new Object[] { this.CHATCOMPONENTTEXT.newInstance(new Object[] { SUBTITLE }) });
/* 468 */         sendPacket(USER, object3);
/* 469 */         return true;
/*     */       } 
/* 471 */       Object TIMES = this.PLAYOUTTITLE_PACKET_CLASS.newInstance();
/*     */       
/* 473 */       set(TIMES, "a", this.PLAYOUTTITLE_PACKET_CLASS.getDeclaredClasses()[0].getField("TIMES").get(null));
/* 474 */       set(TIMES, "b", null);
/* 475 */       set(TIMES, "c", Integer.valueOf(FADEIN));
/* 476 */       set(TIMES, "d", Integer.valueOf(STAY));
/* 477 */       set(TIMES, "e", Integer.valueOf(FADEOUT));
/* 478 */       sendPacket(USER, TIMES);
/* 479 */       Object T = this.PLAYOUTTITLE_PACKET_CLASS.newInstance();
/*     */       
/* 481 */       set(T, "a", this.PLAYOUTTITLE_PACKET_CLASS.getDeclaredClasses()[0].getField("TITLE").get(null));
/* 482 */       set(T, "b", this.CHATCOMPONENTTEXT.newInstance(new Object[] { TITLE }));
/* 483 */       sendPacket(USER, T);
/* 484 */       Object S = this.PLAYOUTTITLE_PACKET_CLASS.newInstance();
/*     */       
/* 486 */       set(S, "a", this.PLAYOUTTITLE_PACKET_CLASS.getDeclaredClasses()[0].getField("SUBTITLE").get(null));
/* 487 */       set(S, "b", this.CHATCOMPONENTTEXT.newInstance(new Object[] { SUBTITLE }));
/* 488 */       sendPacket(USER, S);
/* 489 */     } catch (Exception ex) {
/* 490 */       ex.printStackTrace();
/* 491 */       return false;
/*     */     } 
/* 493 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\version\IVersion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */