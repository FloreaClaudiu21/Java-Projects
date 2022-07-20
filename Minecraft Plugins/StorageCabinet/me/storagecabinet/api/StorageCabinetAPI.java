/*     */ package me.storagecabinet.api;
/*     */ 
/*     */ import me.storagecabinet.StorageCabinet;
/*     */ import me.storagecabinet.manager.cabinet.Cabinet;
/*     */ import me.storagecabinet.manager.cabinet.CabinetIDS;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StorageCabinetAPI
/*     */ {
/*     */   public boolean is_cabinet_block(Block B) {
/*  21 */     Validate.notNull(B, "The block can't be null or air");
/*     */     
/*  23 */     if (StorageCabinet.CABINET_MANAGER.is_cabinet_block(B)) {
/*  24 */       return true;
/*     */     }
/*  26 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean is_cabinet_item(ItemStack ITEM) {
/*  31 */     Validate.notNull(ITEM, "The item can't be set to null");
/*     */     
/*  33 */     if (StorageCabinet.CABINET_MANAGER.is_cabinet_item(ITEM)) {
/*  34 */       return true;
/*     */     }
/*  36 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void give_cabinet(Player P, CabinetIDS TYPE) {
/*  41 */     Validate.notNull(P, "The player can't be set to null");
/*  42 */     Validate.notNull(P, "The cabinet type can't be set to null");
/*     */     
/*  44 */     PlayerInventory PI = P.getInventory();
/*  45 */     int E = PI.firstEmpty();
/*     */     
/*  47 */     ItemStack CABINET = StorageCabinet.CABINET_MANAGER.create_cabinet_item_with_id(TYPE.get_id());
/*  48 */     if (E == -1) {
/*  49 */       P.getWorld().dropItem(P.getLocation().add(0.0D, 0.5D, 0.0D), CABINET);
/*     */     } else {
/*  51 */       PI.addItem(new ItemStack[] { CABINET });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean delete_cabinet_by_id(String ID) {
/*  58 */     Validate.notEmpty(ID, "The ID can't be null or empty");
/*     */     
/*  60 */     if (!StorageCabinet.STORAGE.CABINETS.containsKey(ID)) {
/*  61 */       return false;
/*     */     }
/*  63 */     Cabinet C = (Cabinet)StorageCabinet.STORAGE.CABINETS.get(ID);
/*  64 */     StorageCabinet.CABINET_MANAGER.delete_cabinet_from_database(C);
/*  65 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean delete_cabinet_by_item(ItemStack ITEM) {
/*  70 */     Validate.notNull(ITEM, "The item can't be set to null");
/*     */     
/*  72 */     if (!is_cabinet_item(ITEM)) {
/*  73 */       return false;
/*     */     }
/*  75 */     String ID = ITEM.getItemMeta().getLocalizedName();
/*  76 */     Cabinet C = (Cabinet)StorageCabinet.STORAGE.CABINETS.get(ID);
/*     */     
/*  78 */     StorageCabinet.CABINET_MANAGER.delete_cabinet_from_database(C);
/*  79 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Cabinet get_cabinet_manager_by_item(ItemStack ITEM) {
/*  84 */     Validate.notNull(ITEM, "The item can't be set to null");
/*     */     
/*  86 */     if (is_cabinet_item(ITEM)) {
/*  87 */       Cabinet C = StorageCabinet.CABINET_MANAGER.get_cabinet_by_item(ITEM);
/*  88 */       return C;
/*     */     } 
/*  90 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Cabinet get_cabinet_manager_by_block(Block B) {
/*  95 */     Validate.notNull(B, "The block can't be null or air");
/*     */     
/*  97 */     if (is_cabinet_block(B)) {
/*  98 */       Cabinet C = StorageCabinet.CABINET_MANAGER.get_cabinet_by_block(B);
/*  99 */       return C;
/*     */     } 
/* 101 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\api\StorageCabinetAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */