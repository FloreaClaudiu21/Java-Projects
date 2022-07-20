/*     */ package me.furnace.manager.recipe;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import me.furnace.IMain;
/*     */ import me.furnace.manager.furnace.IFurnaceU;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.inventory.FurnaceRecipe;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.Recipe;
/*     */ import org.bukkit.inventory.RecipeChoice;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IRecipes
/*     */ {
/*  25 */   public List<IFurnaceRecipe> RECIPES = new ArrayList<>();
/*  26 */   public List<IFurnaceRecipe> NEWRECIPES = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IFurnaceRecipe recipe_result(ItemStack RESULT) {
/*  38 */     if (IFurnaceU.E(RESULT)) {
/*  39 */       return null;
/*     */     }
/*  41 */     for (IFurnaceRecipe RECIPE : this.RECIPES) {
/*  42 */       ItemStack R = RECIPE.RESULT;
/*     */       
/*  44 */       if (IFurnaceRecipe.same(R, RESULT)) {
/*  45 */         return RECIPE;
/*     */       }
/*     */     } 
/*  48 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFurnaceRecipe recipe_smelting(ItemStack SMELTING) {
/*  53 */     if (IFurnaceU.E(SMELTING)) {
/*  54 */       return null;
/*     */     }
/*  56 */     for (IFurnaceRecipe RECIPE : this.RECIPES) {
/*  57 */       ItemStack S = RECIPE.SMELTING;
/*     */       
/*  59 */       if (IFurnaceRecipe.same(S, SMELTING)) {
/*  60 */         return RECIPE;
/*     */       }
/*     */     } 
/*  63 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void save() {
/*  70 */     if (this.NEWRECIPES.isEmpty()) {
/*  71 */       this.RECIPES.clear();
/*  72 */       this.NEWRECIPES.clear();
/*     */       return;
/*     */     } 
/*  75 */     File F = new File(IMain.PL.getDataFolder(), "recipes.yml");
/*  76 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(F);
/*     */     
/*  78 */     for (IFurnaceRecipe RECIPE : this.NEWRECIPES) {
/*  79 */       RECIPE.save((FileConfiguration)yamlConfiguration, "CREATED");
/*     */     }
/*     */     try {
/*  82 */       yamlConfiguration.save(F);
/*  83 */     } catch (Throwable throwable) {}
/*     */     
/*  85 */     this.RECIPES.clear();
/*  86 */     this.NEWRECIPES.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void reload() {
/*  92 */     save();
/*  93 */     load();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int load_section(int COUNT, ConfigurationSection SECTION, boolean D, FileConfiguration CO) {
/* 101 */     if (SECTION == null) {
/* 102 */       if (D) {
/* 103 */         COUNT = load_default_recipes(COUNT, CO);
/*     */       }
/* 105 */       return COUNT;
/*     */     } 
/* 107 */     Set<String> KE = SECTION.getKeys(false);
/*     */     
/* 109 */     if (KE.isEmpty()) {
/* 110 */       if (D) {
/* 111 */         COUNT = load_default_recipes(COUNT, CO);
/*     */       }
/* 113 */       return COUNT;
/*     */     } 
/* 115 */     for (String ID : KE) {
/* 116 */       IFurnaceRecipe RE = new IFurnaceRecipe(ID);
/*     */       
/* 118 */       if (RE.load_config(SECTION)) {
/* 119 */         COUNT++;
/* 120 */         this.RECIPES.add(RE);
/*     */       } 
/*     */     } 
/* 123 */     return COUNT;
/*     */   }
/*     */ 
/*     */   
/*     */   private int load_default_recipes(int COUNT, FileConfiguration CO) {
/* 128 */     if (CO == null) {
/* 129 */       return COUNT;
/*     */     }
/* 131 */     Iterator<Recipe> IT = IMain.S.recipeIterator();
/*     */     
/* 133 */     while (IT.hasNext()) {
/* 134 */       Recipe R = IT.next();
/*     */       
/* 136 */       if (R instanceof FurnaceRecipe) {
/*     */         
/* 138 */         FurnaceRecipe RECIPE = (FurnaceRecipe)R;
/*     */         
/* 140 */         if (IMain.VERSION.LEGACY_RECIPE) {
/* 141 */           RecipeChoice C = RECIPE.getInputChoice();
/*     */           
/* 143 */           if (C instanceof RecipeChoice.MaterialChoice) {
/* 144 */             RecipeChoice.MaterialChoice MC = (RecipeChoice.MaterialChoice)C;
/*     */             
/* 146 */             for (Material M : MC.getChoices()) {
/* 147 */               ItemStack S = new ItemStack(M, 1, (short)0);
/* 148 */               IFurnaceRecipe iFurnaceRecipe = new IFurnaceRecipe(RECIPE, S);
/*     */               
/* 150 */               if (iFurnaceRecipe.load_choices(CO)) {
/* 151 */                 COUNT++;
/* 152 */                 this.RECIPES.add(iFurnaceRecipe);
/*     */               } 
/*     */             } 
/*     */           }  continue;
/*     */         } 
/* 157 */         IFurnaceRecipe FR = new IFurnaceRecipe(RECIPE);
/*     */         
/* 159 */         if (FR.load_recipe(CO)) {
/* 160 */           COUNT++;
/* 161 */           this.RECIPES.add(FR);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 166 */     return COUNT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void load() {
/* 173 */     int COUNT = 0;
/* 174 */     boolean NEW = false;
/* 175 */     File F = new File(IMain.PL.getDataFolder(), "recipes.yml");
/*     */     
/* 177 */     if (!F.exists()) {
/* 178 */       NEW = true;
/*     */     }
/* 180 */     YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(F);
/*     */     
/* 182 */     if (NEW) {
/* 183 */       String[] SB = IMain.VE.split("_");
/*     */       
/* 185 */       yamlConfiguration.set("VERSION", String.valueOf(SB[0]) + "_" + SB[1]);
/* 186 */       COUNT = load_default_recipes(COUNT, (FileConfiguration)yamlConfiguration);
/*     */     } else {
/*     */       
/* 189 */       String V = yamlConfiguration.getString("VERSION");
/* 190 */       ConfigurationSection D_S = yamlConfiguration.getConfigurationSection("DEFAULT");
/* 191 */       ConfigurationSection C_S = yamlConfiguration.getConfigurationSection("CREATED");
/*     */       
/* 193 */       if (V == null || V.isEmpty() || !IMain.VE.startsWith(V)) {
/* 194 */         String[] SB = IMain.VE.split("_");
/*     */         
/* 196 */         yamlConfiguration.set("DEFAULT", null);
/* 197 */         yamlConfiguration.set("VERSION", String.valueOf(SB[0]) + "_" + SB[1]);
/*     */ 
/*     */         
/* 200 */         COUNT = load_section(COUNT, C_S, false, null);
/*     */         
/* 202 */         COUNT = load_default_recipes(COUNT, (FileConfiguration)yamlConfiguration);
/* 203 */         IMain.UTILS.console("&cFound a new server version, loading the default recipes for this version.", 
/* 204 */             true);
/*     */       } else {
/*     */         
/* 207 */         COUNT = load_section(COUNT, C_S, false, null);
/*     */         
/* 209 */         COUNT = load_section(COUNT, D_S, true, (FileConfiguration)yamlConfiguration);
/*     */       } 
/* 211 */       if (COUNT <= 0) {
/* 212 */         yamlConfiguration.set("DEFAULT", null);
/* 213 */         COUNT = load_default_recipes(COUNT, (FileConfiguration)yamlConfiguration);
/*     */       } 
/*     */     } 
/*     */     try {
/* 217 */       yamlConfiguration.save(F);
/* 218 */     } catch (IOException iOException) {}
/*     */     
/* 220 */     String C = String.valueOf(COUNT) + " &2recipe";
/*     */     
/* 222 */     if (COUNT > 1) {
/* 223 */       C = String.valueOf(COUNT) + " &2recipes";
/*     */     }
/* 225 */     IMain.UTILS.console("&2Loaded &6" + C + " from the config.", true);
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\manager\recipe\IRecipes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */