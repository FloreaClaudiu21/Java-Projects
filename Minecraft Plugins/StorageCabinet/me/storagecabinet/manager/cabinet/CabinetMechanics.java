/*     */ package me.storagecabinet.manager.cabinet;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import me.storagecabinet.StorageCabinet;
/*     */ import me.storagecabinet.support.list.Factions;
/*     */ import me.storagecabinet.support.list.RedProtect;
/*     */ import me.storagecabinet.support.list.WorldGuard;
/*     */ import me.storagecabinet.utils.Utils;
/*     */ import me.storagecabinet.utils.XMaterial;
/*     */ import org.bukkit.Effect;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Particle;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.block.Hopper;
/*     */ import org.bukkit.entity.ArmorStand;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.block.BlockDispenseEvent;
/*     */ import org.bukkit.event.block.BlockPlaceEvent;
/*     */ import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
/*     */ import org.bukkit.event.entity.EntityExplodeEvent;
/*     */ import org.bukkit.event.entity.ExplosionPrimeEvent;
/*     */ import org.bukkit.event.entity.ItemDespawnEvent;
/*     */ import org.bukkit.event.inventory.ClickType;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.inventory.InventoryType;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerPickupItemEvent;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.metadata.FixedMetadataValue;
/*     */ import org.bukkit.metadata.MetadataValue;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CabinetMechanics
/*     */ {
/*     */   private CabinetManager MANAGER;
/*  55 */   private List<InventoryAction> ACTIONS = Arrays.asList(new InventoryAction[] { InventoryAction.PLACE_ALL, InventoryAction.PLACE_ONE, 
/*  56 */         InventoryAction.PICKUP_ONE, InventoryAction.PICKUP_ALL });
/*     */   
/*     */   public CabinetMechanics(CabinetManager M) {
/*  59 */     this.MANAGER = M;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArmorStand spawn_stand(Location L, ItemStack CABINET, Vector V) {
/*  65 */     if (L == null || CABINET == null) {
/*  66 */       return null;
/*     */     }
/*  68 */     ArmorStand S = (ArmorStand)L.getWorld().spawnEntity(L.clone().add(0.5D, 0.0D, 0.5D).setDirection(V), 
/*  69 */         EntityType.ARMOR_STAND);
/*     */     
/*  71 */     S.setSmall(true);
/*  72 */     S.setArms(false);
/*  73 */     S.setVisible(false);
/*  74 */     S.setGravity(false);
/*  75 */     S.setHelmet(CABINET);
/*  76 */     S.setBasePlate(false);
/*  77 */     S.setCollidable(false);
/*  78 */     S.setInvulnerable(true);
/*  79 */     S.setCustomName("CUSTOM");
/*  80 */     S.setRemoveWhenFarAway(false);
/*     */     
/*  82 */     return S;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean add_item_to_cabinet(Player P, ItemStack IT, Cabinet C, boolean OFF) {
/*  87 */     if (P == null || C == null) {
/*  88 */       return false;
/*     */     }
/*  90 */     int AMOUNT = IT.getAmount();
/*  91 */     List<Inventory> INV_L = C.IN_L;
/*  92 */     PlayerInventory PI = P.getInventory();
/*  93 */     ItemStack AIR = XMaterial.AIR.parseItem();
/*     */     
/*  95 */     if (INV_L == null || INV_L.isEmpty()) {
/*  96 */       return false;
/*     */     }
/*  98 */     for (Inventory IN : INV_L) {
/*  99 */       int EMPTY_SLOT = IN.firstEmpty();
/* 100 */       ItemStack[] ITEMS = IN.getStorageContents(); byte b; int i;
/*     */       ItemStack[] arrayOfItemStack1;
/* 102 */       for (i = (arrayOfItemStack1 = ITEMS).length, b = 0; b < i; ) { ItemStack I = arrayOfItemStack1[b];
/*     */         
/* 104 */         if (I != null && I.isSimilar(IT)) {
/* 105 */           int A_A = I.getAmount() + AMOUNT;
/* 106 */           int R_A = A_A - I.getMaxStackSize();
/*     */           
/* 108 */           if (I.getAmount() != I.getMaxStackSize())
/*     */           {
/*     */             
/* 111 */             if (A_A > IT.getMaxStackSize()) {
/* 112 */               I.setAmount(IT.getMaxStackSize());
/* 113 */               IT.setAmount(R_A);
/*     */             } else {
/*     */               
/* 116 */               I.setAmount(A_A);
/*     */ 
/*     */               
/* 119 */               if (R_A > 0) {
/* 120 */                 IT.setAmount(R_A);
/*     */               }
/* 122 */               else if (!OFF) {
/* 123 */                 PI.setItemInMainHand(AIR);
/*     */               } else {
/* 125 */                 PI.setItemInOffHand(AIR);
/*     */               } 
/*     */               
/* 128 */               return true;
/*     */             }  } 
/*     */         } 
/*     */         b++; }
/*     */       
/* 133 */       if (IT.getAmount() >= 1 && EMPTY_SLOT >= 0) {
/* 134 */         IN.setItem(EMPTY_SLOT, IT);
/* 135 */         if (!OFF) {
/* 136 */           PI.setItemInMainHand(AIR);
/*     */         } else {
/* 138 */           PI.setItemInOffHand(AIR);
/*     */         } 
/* 140 */         return true;
/*     */       } 
/*     */     } 
/* 143 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean interact_cabinet(PlayerInteractEvent EV) {
/* 148 */     if (EV == null || EV.getHand() == EquipmentSlot.OFF_HAND) {
/* 149 */       return false;
/*     */     }
/* 151 */     Action A = EV.getAction();
/* 152 */     Player P = EV.getPlayer();
/* 153 */     PlayerInventory PI = P.getInventory();
/* 154 */     Block CABINET = EV.getClickedBlock();
/*     */     
/* 156 */     if (!this.MANAGER.is_cabinet_block(CABINET)) {
/* 157 */       return false;
/*     */     }
/* 159 */     String W = P.getWorld().getName().toLowerCase();
/*     */     
/* 161 */     if (((List)StorageCabinet.SETTINGS.LIST_MAP.get("disabled_worlds")).contains(W)) {
/* 162 */       StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.disabled_world"));
/* 163 */       return false;
/*     */     } 
/* 165 */     Location L = CABINET.getLocation();
/* 166 */     ItemStack IT = PI.getItemInMainHand();
/* 167 */     ItemStack IT1 = PI.getItemInOffHand();
/* 168 */     Cabinet CABINET_M = this.MANAGER.get_cabinet_by_block(CABINET);
/*     */ 
/*     */     
/* 171 */     if (XMaterial.isAir(IT) && XMaterial.isAir(IT1) && A == Action.RIGHT_CLICK_BLOCK) {
/* 172 */       if (!P.hasPermission(StorageCabinet.PERM_MANAGER.CABINET_USE)) {
/* 173 */         EV.setCancelled(true);
/* 174 */         StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.noperm_use"));
/* 175 */         return false;
/*     */       } 
/* 177 */       if (StorageCabinet.SUPPORT.HAS_WG && !WorldGuard.INTERACT.is_allowed(P, L, false)) {
/* 178 */         EV.setCancelled(true);
/* 179 */         StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.protinteract"));
/* 180 */         return false;
/*     */       } 
/* 182 */       if (StorageCabinet.SUPPORT.HAS_RP && !RedProtect.INTERACT.is_allowed(P, L, false)) {
/* 183 */         EV.setCancelled(true);
/* 184 */         StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.protinteract"));
/* 185 */         return false;
/*     */       } 
/* 187 */       Inventory CI = CABINET_M.IN_L.get(0);
/*     */       
/* 189 */       if (CABINET_M.isClosing) {
/* 190 */         StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.cabinetclosing"));
/* 191 */         return false;
/*     */       } 
/* 193 */       P.openInventory(CI);
/* 194 */       CABINET_M.ANIM.open_anim();
/* 195 */       P.playSound(P.getLocation(), Sound.BLOCK_CHEST_OPEN, 1.0F, 0.8F);
/* 196 */       return true;
/*     */     } 
/*     */     
/* 199 */     if (A == Action.LEFT_CLICK_BLOCK && P.isSneaking()) {
/* 200 */       if (!P.hasPermission(StorageCabinet.PERM_MANAGER.CABINET_USE)) {
/* 201 */         EV.setCancelled(true);
/* 202 */         StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.noperm_use"));
/* 203 */         return false;
/*     */       } 
/* 205 */       if (StorageCabinet.SUPPORT.HAS_WG && !WorldGuard.INTERACT.is_allowed(P, L, false)) {
/* 206 */         EV.setCancelled(true);
/* 207 */         StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.protinteract"));
/* 208 */         return false;
/*     */       } 
/* 210 */       if (StorageCabinet.SUPPORT.HAS_RP && !RedProtect.INTERACT.is_allowed(P, L, false)) {
/* 211 */         EV.setCancelled(true);
/* 212 */         StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.protinteract"));
/* 213 */         return false;
/*     */       } 
/* 215 */       boolean OFF = false;
/*     */       
/* 217 */       if (XMaterial.isAir(IT)) {
/* 218 */         OFF = true;
/* 219 */         IT = PI.getItemInOffHand();
/*     */       } else {
/* 221 */         IT = PI.getItemInMainHand();
/*     */       } 
/* 223 */       if (StorageCabinet.CABINET_MANAGER.is_cabinet_item(IT)) {
/* 224 */         return false;
/*     */       }
/* 226 */       if (XMaterial.isAir(IT)) {
/* 227 */         return false;
/*     */       }
/* 229 */       EV.setCancelled(true);
/* 230 */       add_item_to_cabinet(P, IT, CABINET_M, OFF);
/* 231 */       P.playSound(P.getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 0.8F);
/*     */       
/* 233 */       if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("particles.itemtocabinet")).booleanValue()) {
/* 234 */         for (int I = -2; I < 2; I++) {
/* 235 */           P.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, CABINET.getLocation().clone().add(0.5D, 0.0D, 0.5D), 
/* 236 */               14, 0.4D, 0.6D, 0.2D);
/*     */         }
/*     */       }
/* 239 */       return true;
/*     */     } 
/* 241 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean pickup_cabinet(PlayerPickupItemEvent EV) {
/* 246 */     if (EV == null) {
/* 247 */       return false;
/*     */     }
/* 249 */     Item ITEM = EV.getItem();
/* 250 */     Player P = EV.getPlayer();
/* 251 */     ItemStack CABINET = ITEM.getItemStack();
/*     */     
/* 253 */     if (this.MANAGER.is_cabinet_item_without_data(CABINET)) {
/* 254 */       ItemMeta META = CABINET.getItemMeta();
/* 255 */       String UUID = META.getLocalizedName();
/*     */       
/* 257 */       ITEM.remove();
/* 258 */       EV.setCancelled(true);
/* 259 */       StorageCabinet.UTILS.send_msg(P, (
/* 260 */           (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.cabinetnotfound")).replace("{ID}", UUID));
/* 261 */       return false;
/*     */     } 
/* 263 */     if (!this.MANAGER.is_cabinet_item(CABINET)) {
/* 264 */       return false;
/*     */     }
/* 266 */     StorageCabinet.STORAGE.DROPPED.remove(ITEM);
/* 267 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("particles.pickup")).booleanValue()) {
/* 268 */       P.getWorld().playEffect(P.getLocation().getBlock().getLocation().clone().add(0.3D, 0.1D, 0.2D), 
/* 269 */           Effect.ENDER_SIGNAL, 8);
/*     */     }
/* 271 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean dispense_cabinet(BlockDispenseEvent EV) {
/* 276 */     if (EV == null) {
/* 277 */       return false;
/*     */     }
/* 279 */     ItemStack CABINET = EV.getItem();
/*     */     
/* 281 */     if (this.MANAGER.is_cabinet_item_without_data(CABINET)) {
/* 282 */       EV.setCancelled(true);
/* 283 */       return false;
/*     */     } 
/* 285 */     if (!this.MANAGER.is_cabinet_item(CABINET)) {
/* 286 */       return false;
/*     */     }
/* 288 */     if (CABINET.getAmount() > 1) {
/* 289 */       EV.setItem(XMaterial.AIR.parseItem());
/* 290 */       return false;
/*     */     } 
/* 292 */     EV.setCancelled(true);
/* 293 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean drop_cabinet(PlayerDropItemEvent EV) {
/* 298 */     if (EV == null) {
/* 299 */       return false;
/*     */     }
/* 301 */     Player P = EV.getPlayer();
/* 302 */     Item ITEM = EV.getItemDrop();
/* 303 */     PlayerInventory PI = P.getInventory();
/* 304 */     ItemStack CABINET = ITEM.getItemStack();
/*     */     
/* 306 */     if (this.MANAGER.is_cabinet_item_without_data(CABINET)) {
/* 307 */       ItemMeta itemMeta = CABINET.getItemMeta();
/* 308 */       String UUID = itemMeta.getLocalizedName();
/*     */       
/* 310 */       ITEM.remove();
/* 311 */       StorageCabinet.UTILS.send_msg(P, (
/* 312 */           (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.cabinetnotfound")).replace("{ID}", UUID));
/* 313 */       return false;
/*     */     } 
/* 315 */     if (!this.MANAGER.is_cabinet_item(CABINET)) {
/* 316 */       return false;
/*     */     }
/* 318 */     ItemStack HAND = PI.getItemInMainHand();
/* 319 */     Cabinet CAB = StorageCabinet.CABINET_MANAGER.get_cabinet_by_item(CABINET);
/*     */     
/* 321 */     if (HAND.getAmount() > 1) {
/* 322 */       ITEM.remove();
/* 323 */       PI.setItemInMainHand(XMaterial.AIR.parseItem());
/* 324 */       P.updateInventory();
/* 325 */       return false;
/*     */     } 
/* 327 */     ItemMeta META = CABINET.getItemMeta();
/*     */     
/* 329 */     ITEM.setCustomName(META.getDisplayName());
/* 330 */     ITEM.setCustomNameVisible(true);
/* 331 */     StorageCabinet.STORAGE.DROPPED.put(ITEM, CAB.ID);
/* 332 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("particles.drop")).booleanValue()) {
/* 333 */       P.getWorld().playEffect(ITEM.getLocation().getBlock().getLocation().clone().add(0.3D, 0.1D, 0.2D), 
/* 334 */           Effect.RECORD_PLAY, 8);
/*     */     }
/* 336 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean enchant_cabinet(PrepareItemEnchantEvent EV) {
/* 341 */     if (EV == null) {
/* 342 */       return false;
/*     */     }
/* 344 */     ItemStack CABINET = EV.getItem();
/*     */     
/* 346 */     if (!this.MANAGER.is_cabinet_item(CABINET)) {
/* 347 */       return false;
/*     */     }
/* 349 */     EV.setCancelled(true);
/* 350 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean rename_cabinet(InventoryClickEvent EV) {
/* 355 */     if (EV == null) {
/* 356 */       return false;
/*     */     }
/* 358 */     InventoryAction AC = EV.getAction();
/* 359 */     ItemStack CABINET = EV.getCurrentItem();
/* 360 */     Inventory TOP = EV.getView().getTopInventory();
/* 361 */     Inventory BOTTOM = EV.getView().getBottomInventory();
/*     */     
/* 363 */     if (TOP == null || BOTTOM == null) {
/* 364 */       return false;
/*     */     }
/* 366 */     if (!this.MANAGER.is_cabinet_item(CABINET)) {
/* 367 */       return false;
/*     */     }
/* 369 */     if (TOP instanceof org.bukkit.inventory.AnvilInventory && BOTTOM instanceof PlayerInventory) {
/* 370 */       EV.setCancelled(true);
/* 371 */       return true;
/*     */     } 
/* 373 */     if (EV.getClick() == ClickType.MIDDLE || EV.getAction() == InventoryAction.CLONE_STACK) {
/* 374 */       EV.setCancelled(true);
/* 375 */       return true;
/*     */     } 
/* 377 */     if (TOP.getType() == InventoryType.WORKBENCH && BOTTOM.getType() == InventoryType.PLAYER) {
/* 378 */       if (this.ACTIONS.contains(AC) && EV.getClick() != ClickType.CREATIVE) {
/* 379 */         return false;
/*     */       }
/* 381 */       EV.setCancelled(true);
/* 382 */       return true;
/*     */     } 
/* 384 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean despawn_cabinet(ItemDespawnEvent EV) {
/* 389 */     if (EV == null || EV.isCancelled()) {
/* 390 */       return false;
/*     */     }
/* 392 */     ItemStack CABINET = EV.getEntity().getItemStack();
/*     */     
/* 394 */     if (!this.MANAGER.is_cabinet_item(CABINET)) {
/* 395 */       return false;
/*     */     }
/* 397 */     Location L = EV.getLocation();
/* 398 */     Cabinet CABINET_M = this.MANAGER.get_cabinet_by_item(CABINET);
/*     */ 
/*     */     
/* 401 */     EV.getEntity().remove();
/*     */     
/* 403 */     StorageCabinet.STORAGE.CABINETS.remove(CABINET_M.ID);
/*     */     
/* 405 */     if (CABINET_M.LOC != null) {
/* 406 */       Block B = CABINET_M.LOC.getBlock();
/*     */       
/* 408 */       if (StorageCabinet.CABINET_MANAGER.is_cabinet_block(B)) {
/* 409 */         B.setType(XMaterial.AIR.parseMaterial());
/* 410 */         StorageCabinet.STORAGE.LOCATIONS.remove(CABINET_M.LOC);
/*     */       } 
/* 412 */       if (CABINET_M.STAND != null) {
/* 413 */         CABINET_M.STAND.remove();
/* 414 */         CABINET_M.STAND = null;
/*     */       } 
/* 416 */       CABINET_M.LOC = null;
/*     */     } 
/*     */     
/* 419 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("particles.despawn")).booleanValue()) {
/* 420 */       for (int I = 0; I <= 2; I++) {
/* 421 */         L.getWorld().playEffect(L, Effect.SMOKE, I);
/*     */       }
/*     */     }
/* 424 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean explode_cabinet_item(ExplosionPrimeEvent EV) {
/* 429 */     if (EV == null || EV.isCancelled()) {
/* 430 */       return false;
/*     */     }
/* 432 */     float radius = EV.getRadius();
/* 433 */     Location L = EV.getEntity().getLocation();
/*     */     
/* 435 */     Collection<Entity> EN = L.getWorld().getNearbyEntities(L, radius, radius, radius);
/*     */     
/* 437 */     for (Entity E : EN) {
/* 438 */       if (!(E instanceof Item)) {
/*     */         continue;
/*     */       }
/* 441 */       ItemStack CABINET = ((Item)E).getItemStack();
/*     */       
/* 443 */       if (!this.MANAGER.is_cabinet_item(CABINET)) {
/*     */         continue;
/*     */       }
/* 446 */       Cabinet CABINET_M = this.MANAGER.get_cabinet_by_item(CABINET);
/*     */ 
/*     */       
/* 449 */       E.remove();
/*     */       
/* 451 */       StorageCabinet.STORAGE.CABINETS.remove(CABINET_M.ID);
/*     */       
/* 453 */       if (CABINET_M.LOC != null) {
/* 454 */         Block B = CABINET_M.LOC.getBlock();
/* 455 */         if (StorageCabinet.CABINET_MANAGER.is_cabinet_block(B)) {
/* 456 */           B.setType(XMaterial.AIR.parseMaterial());
/* 457 */           StorageCabinet.STORAGE.LOCATIONS.remove(CABINET_M.LOC);
/*     */         } 
/* 459 */         if (CABINET_M.STAND != null) {
/* 460 */           CABINET_M.STAND.remove();
/* 461 */           CABINET_M.STAND = null;
/*     */         } 
/* 463 */         CABINET_M.LOC = null;
/*     */       } 
/*     */       
/* 466 */       if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("particles.explode")).booleanValue()) {
/* 467 */         for (int I = 0; I <= 2; I++) {
/* 468 */           E.getWorld().playEffect(E.getLocation(), Effect.SMOKE, I);
/*     */         }
/*     */       }
/*     */     } 
/*     */     
/* 473 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean explode_cabinet(EntityExplodeEvent EV) {
/* 478 */     if (EV == null || EV.isCancelled()) {
/* 479 */       return false;
/*     */     }
/* 481 */     List<Block> LB = EV.blockList();
/*     */ 
/*     */     
/* 484 */     for (Block CABINET : LB) {
/* 485 */       if (!this.MANAGER.is_cabinet_block(CABINET)) {
/*     */         continue;
/*     */       }
/* 488 */       World W = CABINET.getWorld();
/* 489 */       Location L = CABINET.getLocation();
/* 490 */       Cabinet CABINET_M = this.MANAGER.get_cabinet_by_block(CABINET);
/*     */       
/* 492 */       ItemStack IT = CABINET_M.ITEM;
/*     */ 
/*     */       
/* 495 */       CABINET_M.STAND.remove();
/* 496 */       CABINET_M.STAND = null;
/*     */       
/* 498 */       StorageCabinet.STORAGE.LOCATIONS.remove(CABINET_M.LOC);
/*     */       
/* 500 */       ItemMeta M = IT.getItemMeta();
/* 501 */       M.setLore(StorageCabinet.UTILS.vars_L(
/* 502 */             (List)StorageCabinet.SETTINGS.LIST_MAP
/* 503 */             .get(String.valueOf(StorageCabinet.CABINET_MANAGER.path_by_id(CabinetIDS.get_id_by_item(IT))) + "lores"), 
/* 504 */             CABINET_M));
/* 505 */       IT.setItemMeta(M);
/*     */       
/* 507 */       Item ITEM = W.dropItem(CABINET_M.LOC.clone().add(0.5D, 0.5D, 0.5D), IT);
/* 508 */       ITEM.setCustomName(M.getDisplayName());
/* 509 */       ITEM.setCustomNameVisible(true);
/*     */       
/* 511 */       CABINET_M.LOC = null;
/*     */       
/* 513 */       CABINET.setType(XMaterial.AIR.parseMaterial());
/*     */       
/* 515 */       for (Player P1 : StorageCabinet.SERVER.getOnlinePlayers()) {
/* 516 */         if (CABINET_M.IN_L.contains(P1.getOpenInventory().getTopInventory())) {
/* 517 */           P1.closeInventory();
/* 518 */           P1.playSound(P1.getLocation(), Sound.BLOCK_CHEST_CLOSE, 1.0F, 0.8F);
/*     */         } 
/*     */       } 
/*     */       
/* 522 */       Utils.send_sound(W, L);
/*     */       
/* 524 */       if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("particles.explode")).booleanValue()) {
/* 525 */         for (int I = 0; I <= 5; I++) {
/* 526 */           W.playEffect(L, Effect.BREWING_STAND_BREW, I);
/* 527 */           W.playEffect(L.clone().add(0.5D, 0.2D, 0.5D), Effect.SMOKE, I);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 532 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean break_cabinet(BlockBreakEvent EV) {
/* 537 */     if (EV == null || EV.isCancelled()) {
/* 538 */       return false;
/*     */     }
/* 540 */     Player P = EV.getPlayer();
/* 541 */     Block CABINET = EV.getBlock();
/* 542 */     Location L = CABINET.getLocation();
/*     */     
/* 544 */     if (!this.MANAGER.is_cabinet_block(CABINET)) {
/* 545 */       return false;
/*     */     }
/* 547 */     String W = P.getWorld().getName().toLowerCase();
/*     */     
/* 549 */     if (((List)StorageCabinet.SETTINGS.LIST_MAP.get("disabled_worlds")).contains(W)) {
/* 550 */       StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.disabled_world"));
/* 551 */       return false;
/*     */     } 
/* 553 */     if (!P.hasPermission(StorageCabinet.PERM_MANAGER.CABINET_BREAK)) {
/* 554 */       EV.setCancelled(true);
/* 555 */       StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.noperm_break"));
/* 556 */       return false;
/*     */     } 
/* 558 */     if (StorageCabinet.SUPPORT.HAS_F && !Factions.DESTROY.is_allowed(P, L, false)) {
/* 559 */       EV.setCancelled(true);
/* 560 */       StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.protbreak"));
/* 561 */       return false;
/*     */     } 
/* 563 */     if (StorageCabinet.SUPPORT.HAS_WG && !WorldGuard.DESTROY.is_allowed(P, L, false)) {
/* 564 */       EV.setCancelled(true);
/* 565 */       StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.protbreak"));
/* 566 */       return false;
/*     */     } 
/* 568 */     if (StorageCabinet.SUPPORT.HAS_RP && !RedProtect.DESTROY.is_allowed(P, L, false)) {
/* 569 */       EV.setCancelled(true);
/* 570 */       StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.protbreak"));
/* 571 */       return false;
/*     */     } 
/* 573 */     Cabinet CABINET_M = this.MANAGER.get_cabinet_by_block(CABINET);
/*     */     
/* 575 */     ItemStack IT = CABINET_M.ITEM;
/*     */     
/* 577 */     EV.setDropItems(false);
/*     */     
/* 579 */     if (CABINET_M.STAND != null) {
/* 580 */       CABINET_M.STAND.remove();
/* 581 */       CABINET_M.STAND = null;
/*     */     } 
/*     */     
/* 584 */     StorageCabinet.STORAGE.LOCATIONS.remove(CABINET_M.LOC);
/*     */     
/* 586 */     ItemMeta M = IT.getItemMeta();
/* 587 */     M.setLore(StorageCabinet.UTILS.vars_L(
/* 588 */           (List)StorageCabinet.SETTINGS.LIST_MAP
/* 589 */           .get(String.valueOf(StorageCabinet.CABINET_MANAGER.path_by_id(CabinetIDS.get_id_by_item(IT))) + "lores"), 
/* 590 */           CABINET_M));
/* 591 */     IT.setItemMeta(M);
/*     */     
/* 593 */     Item ITEM = CABINET_M.LOC.getWorld().dropItem(CABINET_M.LOC.clone().add(0.5D, 0.5D, 0.5D), IT);
/* 594 */     ITEM.setCustomName(M.getDisplayName());
/* 595 */     ITEM.setCustomNameVisible(true);
/*     */     
/* 597 */     CABINET_M.LOC = null;
/*     */     
/* 599 */     CABINET.setType(XMaterial.AIR.parseMaterial());
/*     */     
/* 601 */     for (Player P1 : StorageCabinet.SERVER.getOnlinePlayers()) {
/* 602 */       if (CABINET_M.IN_L.contains(P1.getOpenInventory().getTopInventory())) {
/* 603 */         P1.closeInventory();
/* 604 */         P.playSound(P1.getLocation(), Sound.BLOCK_CHEST_CLOSE, 1.0F, 0.8F);
/*     */       } 
/*     */     } 
/*     */     
/* 608 */     Utils.send_sound(P.getWorld(), L);
/*     */     
/* 610 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("particles.break")).booleanValue()) {
/* 611 */       for (int I = 1; I <= 7; I++) {
/* 612 */         P.getWorld().playEffect(L, Effect.SMOKE, I);
/* 613 */         P.getWorld().playEffect(L.clone().add(0.5D, 0.25D, 0.5D), Effect.SMOKE, I);
/* 614 */         P.getWorld().playEffect(L.clone().subtract(0.2D, 0.15D, 0.35D), Effect.SMOKE, I);
/*     */       } 
/*     */     }
/* 617 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean place_cabinet(BlockPlaceEvent EV) {
/* 622 */     if (EV == null || EV.isCancelled() || !EV.canBuild()) {
/* 623 */       return false;
/*     */     }
/* 625 */     boolean OFF = false;
/* 626 */     Block B = EV.getBlock();
/* 627 */     Player P = EV.getPlayer();
/* 628 */     PlayerInventory IN = P.getInventory();
/*     */     
/* 630 */     ItemStack CABINET = IN.getItemInMainHand();
/* 631 */     switch (EV.getHand()) {
/*     */       case OFF_HAND:
/* 633 */         OFF = true;
/* 634 */         CABINET = IN.getItemInOffHand();
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 640 */     if (this.MANAGER.is_cabinet_item_without_data(CABINET)) {
/* 641 */       ItemMeta itemMeta = CABINET.getItemMeta();
/* 642 */       String UUID = itemMeta.getLocalizedName();
/*     */       
/* 644 */       IN.remove(CABINET);
/* 645 */       EV.setCancelled(true);
/* 646 */       StorageCabinet.UTILS.send_msg(P, (
/* 647 */           (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.cabinetnotfound")).replace("{ID}", UUID));
/* 648 */       return false;
/*     */     } 
/* 650 */     if (!this.MANAGER.is_cabinet_item(CABINET)) {
/* 651 */       return false;
/*     */     }
/* 653 */     Cabinet CABINET_M = this.MANAGER.get_cabinet_by_item(CABINET);
/*     */ 
/*     */     
/* 656 */     for (Location LOC : StorageCabinet.STORAGE.LOCATIONS.keySet()) {
/* 657 */       Cabinet C = (Cabinet)StorageCabinet.STORAGE.LOCATIONS.get(LOC);
/*     */       
/* 659 */       if (C.ID.equals(CABINET_M.ID)) {
/* 660 */         IN.remove(CABINET);
/* 661 */         EV.setCancelled(true);
/* 662 */         StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.already_placed"));
/* 663 */         return false;
/*     */       } 
/*     */     } 
/* 666 */     String W = P.getWorld().getName().toLowerCase();
/*     */     
/* 668 */     if (((List)StorageCabinet.SETTINGS.LIST_MAP.get("disabled_worlds")).contains(W)) {
/* 669 */       StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.disabled_world"));
/* 670 */       return false;
/*     */     } 
/* 672 */     if (!P.hasPermission(StorageCabinet.PERM_MANAGER.CABINET_PLACE)) {
/* 673 */       EV.setCancelled(true);
/* 674 */       StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.noperm_place"));
/* 675 */       return false;
/*     */     } 
/* 677 */     Location L = B.getLocation();
/*     */     
/* 679 */     if (StorageCabinet.SUPPORT.HAS_WG && !WorldGuard.PLACE.is_allowed(P, L, false)) {
/* 680 */       EV.setCancelled(true);
/* 681 */       StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.protplace"));
/* 682 */       return false;
/*     */     } 
/* 684 */     if (StorageCabinet.SUPPORT.HAS_RP && !RedProtect.PLACE.is_allowed(P, L, false)) {
/* 685 */       EV.setCancelled(true);
/* 686 */       StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.protplace"));
/* 687 */       return false;
/*     */     } 
/* 689 */     if (StorageCabinet.SUPPORT.HAS_F && !Factions.PLACE.is_allowed(P, L, false)) {
/* 690 */       EV.setCancelled(true);
/* 691 */       StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.protplace"));
/* 692 */       return false;
/*     */     } 
/* 694 */     ItemMeta META = CABINET.getItemMeta();
/* 695 */     Vector V = P.getFacing().getOppositeFace().getDirection();
/*     */ 
/*     */     
/* 698 */     if (CABINET.getAmount() > 1) {
/* 699 */       IN.remove(CABINET);
/* 700 */       EV.setCancelled(true);
/* 701 */       return false;
/*     */     } 
/*     */     
/* 704 */     B.setType(XMaterial.GLASS.parseMaterial());
/* 705 */     B.getState().setMetadata("C_B", (MetadataValue)new FixedMetadataValue(StorageCabinet.PLUGIN, META.getLocalizedName()));
/*     */     
/* 707 */     ArmorStand S = spawn_stand(L, CABINET, V);
/*     */     
/* 709 */     CABINET_M.LOC = L;
/* 710 */     CABINET_M.STAND = S;
/* 711 */     StorageCabinet.STORAGE.LOCATIONS.put(L, CABINET_M);
/*     */     
/* 713 */     Block BR = B.getRelative(BlockFace.DOWN);
/*     */     
/* 715 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("hopper_system")).booleanValue() && 
/* 716 */       BR != null && XMaterial.matchXMaterial(BR.getType()) == XMaterial.HOPPER) {
/* 717 */       Hopper H = (Hopper)BR.getState();
/*     */       
/* 719 */       if (!StorageCabinet.STORAGE.HOPPERS_DOWN.contains(H)) {
/* 720 */         StorageCabinet.STORAGE.HOPPERS_DOWN.add(H);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 725 */     if (!OFF) {
/* 726 */       if (P.getGameMode() == GameMode.SURVIVAL) {
/* 727 */         CABINET.setAmount(CABINET.getAmount() - 1);
/*     */       } else {
/* 729 */         IN.remove(CABINET);
/*     */       } 
/*     */     } else {
/* 732 */       IN.setItemInOffHand(null);
/*     */     } 
/*     */     
/* 735 */     P.playSound(L, Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 0.8F);
/* 736 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("particles.place")).booleanValue())
/*     */     {
/* 738 */       for (int I = 0; I <= 4; I++) {
/* 739 */         P.getWorld().playEffect(L, Effect.ENDER_SIGNAL, I);
/* 740 */         P.getWorld().playEffect(L.clone().add(0.5D, 0.25D, 0.5D), Effect.MOBSPAWNER_FLAMES, I);
/*     */       } 
/*     */     }
/* 743 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\cabinet\CabinetMechanics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */