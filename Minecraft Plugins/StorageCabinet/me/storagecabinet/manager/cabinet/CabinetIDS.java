/*    */ package me.storagecabinet.manager.cabinet;
/*    */ 
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ 
/*    */ public enum CabinetIDS
/*    */ {
/* 10 */   OAK_CABINET(65), BIRCH_CABINET(66), JUNGLE_CABINET(67), SPRUCE_CABINET(68), ACACIA_CABINET(69), DARKOAK_CABINET(70),
/* 11 */   WARPED_CABINET(71), CRIMSON_CABINET(72), COBBLE_CABINET(73), COAL_CABINET(74), IRON_CABINET(75), GOLD_CABINET(76),
/* 12 */   LAPIS_CABINET(77), REDSTONE_CABINET(78), DIAMOND_CABINET(79), EMERALD_CABINET(80), NETHERITE_CABINET(81);
/*    */   
/*    */   private int ID;
/*    */   
/*    */   CabinetIDS(int I) {
/* 17 */     this.ID = I;
/*    */   }
/*    */ 
/*    */   
/*    */   public int get_id() {
/* 22 */     return this.ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public static CabinetIDS get_type_by_item(ItemStack ITEM) {
/* 27 */     if (!StorageCabinet.CABINET_MANAGER.is_cabinet_item(ITEM)) {
/* 28 */       return null;
/*    */     }
/* 30 */     ItemMeta M = ITEM.getItemMeta();
/* 31 */     int ID = M.getCustomModelData();
/*    */     
/* 33 */     return (CabinetIDS)StorageCabinet.STORAGE.MAP.get(Integer.valueOf(ID));
/*    */   }
/*    */ 
/*    */   
/*    */   public static int get_id_by_item(ItemStack ITEM) {
/* 38 */     if (!StorageCabinet.CABINET_MANAGER.is_cabinet_item(ITEM)) {
/* 39 */       return -1;
/*    */     }
/* 41 */     ItemMeta M = ITEM.getItemMeta();
/*    */     
/* 43 */     return M.getCustomModelData();
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\cabinet\CabinetIDS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */