/*    */ package me.storagecabinet.manager.recipes.list;
/*    */ 
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import me.storagecabinet.manager.cabinet.CabinetIDS;
/*    */ import me.storagecabinet.manager.recipes.RecipeAPI;
/*    */ import me.storagecabinet.utils.XMaterial;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.inventory.ShapedRecipe;
/*    */ 
/*    */ public class emerald_cabinet_recipe
/*    */   implements RecipeAPI {
/*    */   public ShapedRecipe RECIPE;
/*    */   
/*    */   public emerald_cabinet_recipe() {
/* 15 */     this.RECIPE = new ShapedRecipe(new NamespacedKey(StorageCabinet.PLUGIN, "emerald_cabinet"), 
/* 16 */         StorageCabinet.CABINET_MANAGER.create_fake_cabinet_item_with_id(CabinetIDS.EMERALD_CABINET.get_id()));
/* 17 */     this.RECIPE.shape(new String[] { "***", "*[*", "*]*" });
/* 18 */     this.RECIPE.setIngredient('*', XMaterial.EMERALD.parseMaterial());
/* 19 */     this.RECIPE.setIngredient('[', XMaterial.GLASS.parseMaterial());
/* 20 */     this.RECIPE.setIngredient(']', XMaterial.EMERALD_BLOCK.parseMaterial());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ShapedRecipe get_recipe() {
/* 26 */     return this.RECIPE;
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\recipes\list\emerald_cabinet_recipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */