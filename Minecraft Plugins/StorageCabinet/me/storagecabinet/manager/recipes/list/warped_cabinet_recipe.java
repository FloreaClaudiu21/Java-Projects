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
/*    */ public class warped_cabinet_recipe
/*    */   implements RecipeAPI {
/*    */   public ShapedRecipe RECIPE;
/*    */   
/*    */   public warped_cabinet_recipe() {
/* 16 */     Material M = XMaterial.WARPED_PLANKS.parseMaterial();
/*    */     
/* 18 */     if (M != null) {
/* 19 */       this.RECIPE = new ShapedRecipe(new NamespacedKey(StorageCabinet.PLUGIN, "warped_cabinet"), 
/* 20 */           StorageCabinet.CABINET_MANAGER
/* 21 */           .create_fake_cabinet_item_with_id(CabinetIDS.WARPED_CABINET.get_id()));
/* 22 */       this.RECIPE.shape(new String[] { "***", "*[*", "*]*" });
/* 23 */       this.RECIPE.setIngredient('*', XMaterial.WARPED_PLANKS.parseMaterial());
/* 24 */       this.RECIPE.setIngredient('[', XMaterial.GLASS.parseMaterial());
/* 25 */       this.RECIPE.setIngredient(']', XMaterial.WARPED_STEM.parseMaterial());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ShapedRecipe get_recipe() {
/* 32 */     return this.RECIPE;
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\recipes\list\warped_cabinet_recipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */