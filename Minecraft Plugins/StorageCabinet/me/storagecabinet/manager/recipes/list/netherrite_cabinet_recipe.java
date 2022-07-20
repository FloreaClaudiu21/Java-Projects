/*    */ package me.storagecabinet.manager.recipes.list;
/*    */ 
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import me.storagecabinet.manager.cabinet.CabinetIDS;
/*    */ import me.storagecabinet.manager.recipes.RecipeAPI;
/*    */ import me.storagecabinet.utils.XMaterial;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.inventory.ShapedRecipe;
/*    */ 
/*    */ public class netherrite_cabinet_recipe
/*    */   implements RecipeAPI {
/*    */   public ShapedRecipe RECIPE;
/*    */   
/*    */   public netherrite_cabinet_recipe() {
/* 16 */     Material M = XMaterial.NETHERITE_SCRAP.parseMaterial();
/*    */     
/* 18 */     if (M != null) {
/* 19 */       this.RECIPE = new ShapedRecipe(new NamespacedKey(StorageCabinet.PLUGIN, "netherrite_cabinet"), 
/* 20 */           StorageCabinet.CABINET_MANAGER
/* 21 */           .create_fake_cabinet_item_with_id(CabinetIDS.NETHERITE_CABINET.get_id()));
/* 22 */       this.RECIPE.shape(new String[] { "***", "*[*", "*]*" });
/* 23 */       this.RECIPE.setIngredient('*', XMaterial.NETHERITE_SCRAP.parseMaterial());
/* 24 */       this.RECIPE.setIngredient('[', XMaterial.GLASS.parseMaterial());
/* 25 */       this.RECIPE.setIngredient(']', XMaterial.NETHERITE_BLOCK.parseMaterial());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ShapedRecipe get_recipe() {
/* 32 */     return this.RECIPE;
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\recipes\list\netherrite_cabinet_recipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */