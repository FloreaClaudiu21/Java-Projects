/*     */ package me.storagecabinet.manager.recipes;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import me.storagecabinet.StorageCabinet;
/*     */ import me.storagecabinet.manager.ManagerAPI;
/*     */ import me.storagecabinet.manager.recipes.list.acacia_cabinet_recipe;
/*     */ import me.storagecabinet.manager.recipes.list.birch_cabinet_recipe;
/*     */ import me.storagecabinet.manager.recipes.list.coal_cabinet_recipe;
/*     */ import me.storagecabinet.manager.recipes.list.cobble_cabinet_recipe;
/*     */ import me.storagecabinet.manager.recipes.list.crimson_cabinet_recipe;
/*     */ import me.storagecabinet.manager.recipes.list.darkoak_cabinet_recipe;
/*     */ import me.storagecabinet.manager.recipes.list.diamond_cabinet_recipe;
/*     */ import me.storagecabinet.manager.recipes.list.emerald_cabinet_recipe;
/*     */ import me.storagecabinet.manager.recipes.list.gold_cabinet_recipe;
/*     */ import me.storagecabinet.manager.recipes.list.iron_cabinet_recipe;
/*     */ import me.storagecabinet.manager.recipes.list.jungle_cabinet_recipe;
/*     */ import me.storagecabinet.manager.recipes.list.lapis_cabinet_recipe;
/*     */ import me.storagecabinet.manager.recipes.list.netherrite_cabinet_recipe;
/*     */ import me.storagecabinet.manager.recipes.list.oak_cabinet_recipe;
/*     */ import me.storagecabinet.manager.recipes.list.redstone_cabinet_recipe;
/*     */ import me.storagecabinet.manager.recipes.list.spruce_cabinet_recipe;
/*     */ import me.storagecabinet.manager.recipes.list.warped_cabinet_recipe;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.CraftItemEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.Recipe;
/*     */ import org.bukkit.inventory.ShapedRecipe;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ public class RecipesManager
/*     */   implements ManagerAPI, Listener
/*     */ {
/*     */   public List<RecipeAPI> LIST;
/*     */   public Collection<NamespacedKey> COL;
/*     */   
/*     */   public RecipesManager() {
/*  42 */     this.LIST = new ArrayList<>();
/*  43 */     this.COL = new ArrayList<>();
/*     */     
/*  45 */     setup_map();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setup_map() {
/*  51 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("recipes.oak_cabinet.enabled")).booleanValue()) {
/*  52 */       this.LIST.add(new oak_cabinet_recipe());
/*     */     }
/*  54 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("recipes.spruce_cabinet.enabled")).booleanValue()) {
/*  55 */       this.LIST.add(new spruce_cabinet_recipe());
/*     */     }
/*  57 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("recipes.birch_cabinet.enabled")).booleanValue()) {
/*  58 */       this.LIST.add(new birch_cabinet_recipe());
/*     */     }
/*  60 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("recipes.jungle_cabinet.enabled")).booleanValue()) {
/*  61 */       this.LIST.add(new jungle_cabinet_recipe());
/*     */     }
/*  63 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("recipes.acacia_cabinet.enabled")).booleanValue()) {
/*  64 */       this.LIST.add(new acacia_cabinet_recipe());
/*     */     }
/*  66 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("recipes.darkoak_cabinet.enabled")).booleanValue()) {
/*  67 */       this.LIST.add(new darkoak_cabinet_recipe());
/*     */     }
/*  69 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("recipes.crimson_cabinet.enabled")).booleanValue()) {
/*  70 */       this.LIST.add(new crimson_cabinet_recipe());
/*     */     }
/*  72 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("recipes.warped_cabinet.enabled")).booleanValue()) {
/*  73 */       this.LIST.add(new warped_cabinet_recipe());
/*     */     }
/*  75 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("recipes.cobble_cabinet.enabled")).booleanValue()) {
/*  76 */       this.LIST.add(new cobble_cabinet_recipe());
/*     */     }
/*  78 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("recipes.coal_cabinet.enabled")).booleanValue()) {
/*  79 */       this.LIST.add(new coal_cabinet_recipe());
/*     */     }
/*  81 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("recipes.iron_cabinet.enabled")).booleanValue()) {
/*  82 */       this.LIST.add(new iron_cabinet_recipe());
/*     */     }
/*  84 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("recipes.gold_cabinet.enabled")).booleanValue()) {
/*  85 */       this.LIST.add(new gold_cabinet_recipe());
/*     */     }
/*  87 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("recipes.lapis_cabinet.enabled")).booleanValue()) {
/*  88 */       this.LIST.add(new lapis_cabinet_recipe());
/*     */     }
/*  90 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("recipes.redstone_cabinet.enabled")).booleanValue()) {
/*  91 */       this.LIST.add(new redstone_cabinet_recipe());
/*     */     }
/*  93 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("recipes.diamond_cabinet.enabled")).booleanValue()) {
/*  94 */       this.LIST.add(new diamond_cabinet_recipe());
/*     */     }
/*  96 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("recipes.emerald_cabinet.enabled")).booleanValue()) {
/*  97 */       this.LIST.add(new emerald_cabinet_recipe());
/*     */     }
/*  99 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("recipes.netherite_cabinet.enabled")).booleanValue()) {
/* 100 */       this.LIST.add(new netherrite_cabinet_recipe());
/*     */     }
/* 102 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean register() {
/* 107 */     this.LIST.forEach(R -> {
/*     */           ShapedRecipe SR = R.get_recipe();
/*     */           
/*     */           if (SR != null) {
/*     */             this.COL.add(SR.getKey());
/*     */             StorageCabinet.SERVER.addRecipe((Recipe)SR);
/*     */           } 
/*     */         });
/* 115 */     StorageCabinet.PLUGIN_MANAGER.registerEvents(this, StorageCabinet.PLUGIN);
/* 116 */     for (Player P : StorageCabinet.SERVER.getOnlinePlayers()) {
/* 117 */       P.discoverRecipes(this.COL);
/*     */     }
/* 119 */     StorageCabinet.UTILS.reload_player_list();
/* 120 */     StorageCabinet.UTILS.debug("Cabinet recipes have been registered", false);
/* 121 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean unregister() {
/* 126 */     this.LIST.forEach(R -> {
/*     */           ShapedRecipe SR = R.get_recipe();
/*     */           
/*     */           if (SR != null) {
/*     */             StorageCabinet.SERVER.removeRecipe(SR.getKey());
/*     */           }
/*     */         });
/* 133 */     for (Player P : StorageCabinet.SERVER.getOnlinePlayers()) {
/* 134 */       P.undiscoverRecipes(this.COL);
/*     */     }
/* 136 */     this.COL.clear();
/* 137 */     this.LIST.clear();
/* 138 */     StorageCabinet.UTILS.debug("Cabinet recipes have been deleted", false);
/* 139 */     return true;
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void on_cabinet_craft(CraftItemEvent EV) {
/* 144 */     Recipe RECIPE = EV.getRecipe();
/*     */     
/* 146 */     if (!(EV.getWhoClicked() instanceof Player)) {
/*     */       return;
/*     */     }
/* 149 */     if (!(RECIPE instanceof ShapedRecipe)) {
/*     */       return;
/*     */     }
/* 152 */     ShapedRecipe R1 = (ShapedRecipe)RECIPE;
/*     */     
/* 154 */     if (!this.COL.contains(R1.getKey())) {
/*     */       return;
/*     */     }
/* 157 */     ItemStack RESULT = R1.getResult();
/*     */     
/* 159 */     if (!RESULT.hasItemMeta() || !RESULT.getItemMeta().hasCustomModelData()) {
/*     */       return;
/*     */     }
/* 162 */     ItemMeta META = RESULT.getItemMeta();
/* 163 */     Player P = (Player)EV.getWhoClicked();
/*     */     
/* 165 */     if (!P.hasPermission(StorageCabinet.PERM_MANAGER.CABINET_CRAFT)) {
/* 166 */       EV.setCancelled(true);
/* 167 */       StorageCabinet.UTILS.send_msg(P, (String)StorageCabinet.SETTINGS.STRING_MAP.get("messages.noperm_craft"));
/*     */       return;
/*     */     } 
/* 170 */     if (StorageCabinet.CABINET_MANAGER.is_cabinet_item(EV.getCurrentItem())) {
/*     */       return;
/*     */     }
/* 173 */     ItemStack RC = StorageCabinet.CABINET_MANAGER.create_cabinet_item_with_id(META.getCustomModelData());
/* 174 */     EV.setCurrentItem(RC);
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\recipes\RecipesManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */