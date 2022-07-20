/*     */ package me.storagecabinet.manager.cabinet;
/*     */ 
/*     */ import java.util.List;
/*     */ import me.storagecabinet.StorageCabinet;
/*     */ import me.storagecabinet.support.list.Factions;
/*     */ import me.storagecabinet.support.list.RedProtect;
/*     */ import me.storagecabinet.support.list.WorldGuard;
/*     */ import me.storagecabinet.utils.XMaterial;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.block.Hopper;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryPickupItemEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CabinetHopper
/*     */ {
/*     */   public boolean pickup_hopper(InventoryPickupItemEvent EV) {
/*  30 */     if (EV == null) {
/*  31 */       return false;
/*     */     }
/*  33 */     Item I = EV.getItem();
/*  34 */     if (I == null) {
/*  35 */       return false;
/*     */     }
/*  37 */     ItemStack STACK = I.getItemStack();
/*     */     
/*  39 */     if (StorageCabinet.CABINET_MANAGER.is_cabinet_item(STACK)) {
/*  40 */       EV.setCancelled(true);
/*  41 */       return true;
/*     */     } 
/*  43 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean explode_hopper(Block B) {
/*  48 */     if (B == null) {
/*  49 */       return false;
/*     */     }
/*  51 */     XMaterial MAT = XMaterial.matchXMaterial(B.getType());
/*     */     
/*  53 */     if (MAT == XMaterial.HOPPER) {
/*  54 */       Hopper H = (Hopper)B.getState();
/*     */       
/*  56 */       if (StorageCabinet.STORAGE.HOPPERS.containsKey(H)) {
/*  57 */         StorageCabinet.STORAGE.HOPPERS.remove(H);
/*     */       }
/*  59 */       if (StorageCabinet.STORAGE.HOPPERS_DOWN.contains(H)) {
/*  60 */         StorageCabinet.STORAGE.HOPPERS_DOWN.remove(H);
/*     */       }
/*     */     } 
/*  63 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean break_hopper(Player P, Block B) {
/*  68 */     if (B == null || P == null) {
/*  69 */       return false;
/*     */     }
/*  71 */     Location L = B.getLocation();
/*  72 */     XMaterial MAT = XMaterial.matchXMaterial(B.getType());
/*     */     
/*  74 */     if (MAT == XMaterial.HOPPER) {
/*  75 */       Hopper H = (Hopper)B.getState();
/*     */       
/*  77 */       if (StorageCabinet.SUPPORT.HAS_F && !Factions.DESTROY.is_allowed(P, L, true)) {
/*  78 */         return false;
/*     */       }
/*  80 */       if (StorageCabinet.SUPPORT.HAS_WG && !WorldGuard.DESTROY.is_allowed(P, L, true)) {
/*  81 */         return false;
/*     */       }
/*  83 */       if (StorageCabinet.SUPPORT.HAS_RP && !RedProtect.DESTROY.is_allowed(P, L, true)) {
/*  84 */         return false;
/*     */       }
/*  86 */       if (StorageCabinet.STORAGE.HOPPERS.containsKey(H)) {
/*  87 */         StorageCabinet.STORAGE.HOPPERS.remove(H);
/*     */       }
/*  89 */       if (StorageCabinet.STORAGE.HOPPERS_DOWN.contains(H)) {
/*  90 */         StorageCabinet.STORAGE.HOPPERS_DOWN.remove(H);
/*     */       }
/*     */     } 
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean place_hopper(Player P, Block B) {
/*  98 */     if (B == null || P == null) {
/*  99 */       return false;
/*     */     }
/* 101 */     Location L = B.getLocation();
/* 102 */     XMaterial MAT = XMaterial.matchXMaterial(B.getType());
/*     */     
/* 104 */     if (MAT == XMaterial.HOPPER) {
/* 105 */       if (!((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("hopper_system")).booleanValue()) {
/* 106 */         return false;
/*     */       }
/* 108 */       if (StorageCabinet.SUPPORT.HAS_F && !Factions.PLACE.is_allowed(P, L, true)) {
/* 109 */         return false;
/*     */       }
/* 111 */       if (StorageCabinet.SUPPORT.HAS_WG && !WorldGuard.PLACE.is_allowed(P, L, true)) {
/* 112 */         return false;
/*     */       }
/* 114 */       if (StorageCabinet.SUPPORT.HAS_RP && !RedProtect.PLACE.is_allowed(P, L, true)) {
/* 115 */         return false;
/*     */       }
/* 117 */       Hopper H = (Hopper)B.getState();
/* 118 */       String S = H.getBlockData().getAsString();
/*     */       
/* 120 */       String[] ST = S.split(",");
/* 121 */       if (ST.length < 2) {
/* 122 */         return false;
/*     */       }
/* 124 */       String[] ST1 = ST[1].replace("]", "").split("=");
/*     */       
/* 126 */       if (ST1.length < 2) {
/* 127 */         return false;
/*     */       }
/* 129 */       String FACEING = ST1[1].toUpperCase();
/* 130 */       BlockFace FACE = BlockFace.valueOf(FACEING);
/* 131 */       Block RB = H.getBlock().getRelative(FACE);
/* 132 */       Block UP = H.getBlock().getRelative(BlockFace.UP);
/*     */       
/* 134 */       if (StorageCabinet.CABINET_MANAGER.is_cabinet_block(RB)) {
/* 135 */         StorageCabinet.STORAGE.HOPPERS.put(H, FACE);
/*     */       }
/* 137 */       if (StorageCabinet.CABINET_MANAGER.is_cabinet_block(UP)) {
/* 138 */         StorageCabinet.STORAGE.HOPPERS_DOWN.add(H);
/*     */       }
/* 140 */       return true;
/*     */     } 
/* 142 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean move_item_from_cabinet_to_hopper(Cabinet C, Hopper H) {
/* 147 */     if (H == null || C == null) {
/* 148 */       return false;
/*     */     }
/* 150 */     Block B = H.getBlock();
/*     */     
/* 152 */     if (!H.isPlaced() || XMaterial.matchXMaterial(B.getType()) != XMaterial.HOPPER) {
/* 153 */       return false;
/*     */     }
/* 155 */     List<Inventory> INV_L = C.IN_L;
/* 156 */     Inventory HI = H.getInventory();
/* 157 */     ItemStack AIR = XMaterial.AIR.parseItem();
/*     */     
/* 159 */     if (INV_L == null || INV_L.isEmpty()) {
/* 160 */       return false;
/*     */     }
/* 162 */     for (Inventory IN : INV_L) {
/* 163 */       ItemStack[] ITEMS = IN.getStorageContents();
/*     */       
/* 165 */       if (ITEMS.length <= 0)
/*     */         continue;  byte b;
/*     */       int i;
/*     */       ItemStack[] arrayOfItemStack1;
/* 169 */       for (i = (arrayOfItemStack1 = ITEMS).length, b = 0; b < i; ) { ItemStack IT = arrayOfItemStack1[b];
/* 170 */         if (XMaterial.isAir(IT)) {
/*     */           continue;
/*     */         }
/* 173 */         if (StorageCabinet.CABINET_MANAGER.is_cabinet_item(IT)) {
/*     */           continue;
/*     */         }
/* 176 */         if (C.FILTER.out_enabled() && !C.FILTER.out_similar(IT)) {
/*     */           continue;
/*     */         }
/* 179 */         if (IT.hasItemMeta()) {
/* 180 */           ItemMeta M = IT.getItemMeta();
/*     */           
/* 182 */           if (M.hasLocalizedName()) {
/*     */             continue;
/*     */           }
/*     */         } 
/* 186 */         int ADD = 1;
/* 187 */         int R_A = IT.getAmount() - ADD;
/* 188 */         int EMPTY_SLOT = HI.firstEmpty();
/* 189 */         ItemStack[] ITEMS1 = HI.getStorageContents(); byte b1;
/*     */         int j;
/*     */         ItemStack[] arrayOfItemStack2;
/* 192 */         for (j = (arrayOfItemStack2 = ITEMS1).length, b1 = 0; b1 < j; ) { ItemStack I = arrayOfItemStack2[b1];
/*     */           
/* 194 */           if (!XMaterial.isAir(I) && I.isSimilar(IT)) {
/* 195 */             int A_A = I.getAmount() + ADD;
/*     */ 
/*     */             
/* 198 */             if (I.getAmount() != I.getMaxStackSize()) {
/*     */ 
/*     */               
/* 201 */               I.setAmount(A_A);
/*     */               
/* 203 */               if (R_A > 0) {
/* 204 */                 IT.setAmount(IT.getAmount() - ADD);
/*     */               } else {
/* 206 */                 IN.setItem(IN.first(IT), AIR);
/*     */               } 
/* 208 */               return true;
/*     */             } 
/*     */           }  b1++; }
/*     */         
/* 212 */         if (IT.getAmount() >= 1 && EMPTY_SLOT >= 0) {
/* 213 */           ItemStack IT2 = IT.clone();
/*     */           
/* 215 */           IT2.setAmount(ADD);
/* 216 */           HI.setItem(EMPTY_SLOT, IT2);
/*     */           
/* 218 */           if (R_A > 0) {
/* 219 */             IT.setAmount(IT.getAmount() - ADD);
/*     */           } else {
/* 221 */             IN.setItem(IN.first(IT), AIR);
/*     */           } 
/* 223 */           return true;
/*     */         }  continue;
/*     */         b++; }
/*     */     
/*     */     } 
/* 228 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean move_item_from_hopper_to_cabinet(Hopper H, Cabinet C) {
/* 233 */     if (H == null || C == null) {
/* 234 */       return false;
/*     */     }
/* 236 */     Block B = H.getBlock();
/*     */     
/* 238 */     if (!H.isPlaced() || XMaterial.matchXMaterial(B.getType()) != XMaterial.HOPPER) {
/* 239 */       return false;
/*     */     }
/* 241 */     List<Inventory> INV_L = C.IN_L;
/* 242 */     Inventory HI = H.getInventory();
/*     */     
/* 244 */     if (INV_L == null || INV_L.isEmpty()) {
/* 245 */       return false;
/*     */     }
/* 247 */     ItemStack AIR = XMaterial.AIR.parseItem();
/* 248 */     ItemStack[] ITEMS = HI.getStorageContents();
/*     */     
/* 250 */     if (ITEMS.length <= 0)
/* 251 */       return false;  byte b; int i;
/*     */     ItemStack[] arrayOfItemStack1;
/* 253 */     for (i = (arrayOfItemStack1 = ITEMS).length, b = 0; b < i; ) { ItemStack IT = arrayOfItemStack1[b];
/* 254 */       if (!XMaterial.isAir(IT))
/*     */       {
/*     */         
/* 257 */         if (!StorageCabinet.CABINET_MANAGER.is_cabinet_item(IT))
/*     */         {
/*     */           
/* 260 */           if (!C.FILTER.in_enabled() || C.FILTER.in_similar(IT)) {
/*     */ 
/*     */             
/* 263 */             int ADD = 1;
/* 264 */             int R_A = IT.getAmount() - ADD;
/*     */             
/* 266 */             for (Inventory IN : INV_L) {
/* 267 */               int EMPTY_SLOT = IN.firstEmpty();
/* 268 */               ItemStack[] ITEMS1 = IN.getStorageContents(); byte b1; int j;
/*     */               ItemStack[] arrayOfItemStack2;
/* 270 */               for (j = (arrayOfItemStack2 = ITEMS1).length, b1 = 0; b1 < j; ) { ItemStack I = arrayOfItemStack2[b1];
/*     */                 
/* 272 */                 if (!XMaterial.isAir(I) && I.isSimilar(IT)) {
/* 273 */                   int A_A = I.getAmount() + ADD;
/*     */ 
/*     */                   
/* 276 */                   if (I.getAmount() != I.getMaxStackSize()) {
/*     */ 
/*     */                     
/* 279 */                     I.setAmount(A_A);
/*     */                     
/* 281 */                     if (R_A > 0) {
/* 282 */                       IT.setAmount(IT.getAmount() - ADD);
/*     */                     } else {
/* 284 */                       HI.setItem(HI.first(IT), AIR);
/*     */                     } 
/* 286 */                     return true;
/*     */                   } 
/*     */                 }  b1++; }
/*     */               
/* 290 */               if (IT.getAmount() >= 1 && EMPTY_SLOT >= 0) {
/* 291 */                 ItemStack IT2 = IT.clone();
/*     */                 
/* 293 */                 IT2.setAmount(ADD);
/* 294 */                 IN.setItem(EMPTY_SLOT, IT2);
/*     */                 
/* 296 */                 if (R_A > 0) {
/* 297 */                   IT.setAmount(IT.getAmount() - ADD);
/*     */                 } else {
/* 299 */                   HI.setItem(HI.first(IT), AIR);
/*     */                 } 
/* 301 */                 return true;
/*     */               } 
/*     */             } 
/*     */           }  }  }  b++; }
/*     */     
/* 306 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\cabinet\CabinetHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */