/*     */ package me.furnace.manager.recipe;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import me.furnace.IMain;
/*     */ import me.furnace.manager.furnace.IFurnaceU;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.inventory.FurnaceRecipe;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class IFurnaceRecipe {
/*     */   public String ID;
/*     */   public int COOKTIME;
/*     */   public boolean ENABLED;
/*     */   public Float EXPERIENCE;
/*     */   private FurnaceRecipe RECIPE;
/*     */   public ItemStack SMELTING;
/*     */   public ItemStack RESULT;
/*     */   
/*     */   public IFurnaceRecipe(String ID) {
/*  22 */     this.ID = ID;
/*     */   }
/*     */   
/*     */   public IFurnaceRecipe(FurnaceRecipe RECIPE) {
/*  26 */     this.RECIPE = RECIPE;
/*     */   }
/*     */   
/*     */   public IFurnaceRecipe(FurnaceRecipe RECIPE, ItemStack SMELTING) {
/*  30 */     this.RECIPE = RECIPE;
/*  31 */     this.SMELTING = SMELTING;
/*     */   }
/*     */ 
/*     */   
/*     */   public IFurnaceRecipe(String ID, boolean ENABLED, ItemStack SMELTING, ItemStack RESULT, int COOKTIME, Float EXPERIENCE) {
/*  36 */     this.ID = ID;
/*  37 */     this.RESULT = RESULT;
/*  38 */     this.ENABLED = ENABLED;
/*  39 */     this.SMELTING = SMELTING;
/*  40 */     this.COOKTIME = COOKTIME;
/*  41 */     if (this.COOKTIME < 20) {
/*  42 */       this.COOKTIME = 20;
/*     */     }
/*  44 */     this.EXPERIENCE = EXPERIENCE;
/*  45 */     if (this.EXPERIENCE.floatValue() < 0.1F) {
/*  46 */       this.EXPERIENCE = Float.valueOf(0.1F);
/*     */     }
/*  48 */     IMain.RECIPES.NEWRECIPES.add(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean same(ItemStack S, ItemStack R) {
/*  59 */     if (IFurnaceU.E(S) || IFurnaceU.E(R)) {
/*  60 */       return false;
/*     */     }
/*  62 */     List<String> LIST = IFurnaceU.serialize_L(S);
/*  63 */     List<String> LIST2 = IFurnaceU.serialize_L(R);
/*     */     
/*  65 */     if (LIST.isEmpty() || LIST2.isEmpty()) {
/*  66 */       return false;
/*     */     }
/*  68 */     String TYPE = LIST.get(0);
/*  69 */     String TYPE1 = LIST2.get(0);
/*     */     
/*  71 */     if (TYPE.equalsIgnoreCase(TYPE1)) {
/*  72 */       short D = 0, D1 = 0;
/*     */       
/*  74 */       if (LIST.size() > 1) {
/*  75 */         D = Short.valueOf(LIST.get(1)).shortValue();
/*     */       }
/*  77 */       if (LIST2.size() > 1) {
/*  78 */         D1 = Short.valueOf(LIST2.get(1)).shortValue();
/*     */       }
/*  80 */       if (D == -1 || D1 == -1 || D == Short.MAX_VALUE || D1 == Short.MAX_VALUE || D == D1) {
/*  81 */         return true;
/*     */       }
/*     */     } 
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean load_choices(FileConfiguration CO) {
/*  91 */     if (CO == null || this.RECIPE == null) {
/*  92 */       return false;
/*     */     }
/*  94 */     int TIME = 200;
/*  95 */     boolean E = true;
/*  96 */     ItemStack R = this.RECIPE.getResult();
/*  97 */     Float XP = IMain.VERSION.get_experience(this.RECIPE);
/*     */     
/*  99 */     if (this.SMELTING != null && this.SMELTING.getType() != IMain.EMPTY_AIR && R != null && 
/* 100 */       R.getType() != IMain.EMPTY_AIR && !same(this.SMELTING, R)) {
/* 101 */       if (TIME < 20) {
/* 102 */         TIME = 20;
/*     */       }
/* 104 */       if (XP.floatValue() < 0.1F) {
/* 105 */         XP = Float.valueOf(0.1F);
/*     */       }
/* 107 */       String ID = UUID.randomUUID().toString().substring(0, 8);
/*     */       
/* 109 */       this.ID = ID;
/* 110 */       this.RESULT = R;
/* 111 */       this.ENABLED = E;
/* 112 */       this.COOKTIME = TIME;
/* 113 */       this.EXPERIENCE = XP;
/*     */       
/* 115 */       return save(CO, "DEFAULT");
/*     */     } 
/* 117 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean load_recipe(FileConfiguration CO) {
/* 124 */     if (CO == null || this.RECIPE == null) {
/* 125 */       return false;
/*     */     }
/* 127 */     int TIME = 200;
/* 128 */     boolean E = true;
/* 129 */     ItemStack S = this.RECIPE.getInput();
/* 130 */     ItemStack R = this.RECIPE.getResult();
/* 131 */     Float XP = IMain.VERSION.get_experience(this.RECIPE);
/*     */     
/* 133 */     if (S != null && S.getType() != IMain.EMPTY_AIR && R != null && R.getType() != IMain.EMPTY_AIR && !same(S, R)) {
/* 134 */       if (TIME < 20) {
/* 135 */         TIME = 20;
/*     */       }
/* 137 */       if (XP.floatValue() < 0.1F) {
/* 138 */         XP = Float.valueOf(0.1F);
/*     */       }
/* 140 */       String ID = UUID.randomUUID().toString().substring(0, 8);
/*     */       
/* 142 */       this.ID = ID;
/* 143 */       this.SMELTING = S;
/* 144 */       this.RESULT = R;
/* 145 */       this.ENABLED = E;
/* 146 */       this.COOKTIME = TIME;
/* 147 */       this.EXPERIENCE = XP;
/*     */       
/* 149 */       return save(CO, "DEFAULT");
/*     */     } 
/* 151 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean load_config(ConfigurationSection SE) {
/* 158 */     if (SE == null) {
/* 159 */       return false;
/*     */     }
/* 161 */     String P = SE.getName();
/* 162 */     int TIME = SE.getInt(String.valueOf(this.ID) + ".COOKTIME");
/* 163 */     boolean E = SE.getBoolean(String.valueOf(this.ID) + ".ENABLED");
/* 164 */     String S_S = SE.getString(String.valueOf(this.ID) + ".RESULT");
/* 165 */     String R_S = SE.getString(String.valueOf(this.ID) + ".SMELTING");
/* 166 */     Float XP = Float.valueOf((float)SE.getDouble(String.valueOf(this.ID) + ".EXPERIENCE"));
/*     */     
/* 168 */     if (S_S == null || R_S == null || S_S.isEmpty() || R_S.isEmpty()) {
/* 169 */       IMain.UTILS.console("&7>> &cFound illegal smelting or result name in recipe with ID &b&l" + this.ID + 
/* 170 */           " &cand section with name &6&l" + P);
/* 171 */       return false;
/*     */     } 
/*     */     
/* 174 */     ItemStack RESULT = IFurnaceU.deserialize(SE.getConfigurationSection(String.valueOf(this.ID) + ".RESULT"));
/* 175 */     ItemStack SMELTING = IFurnaceU.deserialize(SE.getConfigurationSection(String.valueOf(this.ID) + ".SMELTING"));
/*     */     
/* 177 */     if (IFurnaceU.E(SMELTING)) {
/* 178 */       IMain.UTILS.console("&7>> &cRecipe with ID &b&l" + this.ID + 
/* 179 */           " &ccan't have the smelting item set to AIR, found in section with name &6&l" + P);
/* 180 */       return false;
/*     */     } 
/* 182 */     if (IFurnaceU.E(RESULT)) {
/* 183 */       IMain.UTILS.console("&7>> &cRecipe with ID &b&l" + this.ID + 
/* 184 */           " &ccan't have the result item set to AIR, found in section with name &6&l" + P);
/* 185 */       return false;
/*     */     } 
/* 187 */     if (same(RESULT, SMELTING)) {
/* 188 */       IMain.UTILS.console("&7>> &cRecipe with ID &b&l" + this.ID + 
/* 189 */           " &ccan't have the same result and smelting item, found in section with name &6&l" + P);
/* 190 */       return false;
/*     */     } 
/* 192 */     if (XP.floatValue() < 0.1F) {
/* 193 */       XP = Float.valueOf(0.1F);
/*     */     }
/* 195 */     if (TIME < 20) {
/* 196 */       TIME = 20;
/*     */     }
/* 198 */     this.ENABLED = E;
/* 199 */     this.COOKTIME = TIME;
/* 200 */     this.EXPERIENCE = XP;
/* 201 */     this.RESULT = RESULT;
/* 202 */     this.SMELTING = SMELTING;
/* 203 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean save(FileConfiguration CO, String P) {
/* 210 */     if (CO == null || this.SMELTING == null || this.RESULT == null || P == null) {
/* 211 */       return false;
/*     */     }
/* 213 */     CO.set(String.valueOf(P) + "." + this.ID + ".ENABLED", Boolean.valueOf(this.ENABLED));
/* 214 */     CO.set(String.valueOf(P) + "." + this.ID + ".COOKTIME", Integer.valueOf(this.COOKTIME));
/* 215 */     CO.set(String.valueOf(P) + "." + this.ID + ".EXPERIENCE", this.EXPERIENCE);
/* 216 */     CO.set(String.valueOf(P) + "." + this.ID + ".RESULT", IFurnaceU.serialize(this.RESULT));
/* 217 */     CO.set(String.valueOf(P) + "." + this.ID + ".SMELTING", IFurnaceU.serialize(this.SMELTING));
/* 218 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\manager\recipe\IFurnaceRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */