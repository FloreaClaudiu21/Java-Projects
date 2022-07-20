/*    */ package me.storagecabinet.manager.recipes.list;
/*    */ 
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import me.storagecabinet.manager.recipes.RecipeAPI;
/*    */ import me.storagecabinet.utils.XMaterial;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.inventory.ShapedRecipe;
/*    */ 
/*    */ public class oak_cabinet_recipe
/*    */   implements RecipeAPI {
/*    */   public ShapedRecipe RECIPE;
/*    */   
/*    */   public oak_cabinet_recipe() {
/* 14 */     this.RECIPE = new ShapedRecipe(new NamespacedKey(StorageCabinet.PLUGIN, "oak_cabinet"), 
/* 15 */         StorageCabinet.CABINET_MANAGER.create_fake_cabinet_item_with_id(65));
/* 16 */     this.RECIPE.shape(new String[] { "***", "*[*", "*]*" });
/* 17 */     this.RECIPE.setIngredient('*', XMaterial.OAK_PLANKS.parseMaterial());
/* 18 */     this.RECIPE.setIngredient('[', XMaterial.GLASS.parseMaterial());
/* 19 */     this.RECIPE.setIngredient(']', XMaterial.OAK_LOG.parseMaterial());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ShapedRecipe get_recipe() {
/* 25 */     return this.RECIPE;
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\recipes\list\oak_cabinet_recipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */