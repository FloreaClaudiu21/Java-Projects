/*     */ package me.furnace.manager.furnace;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import me.furnace.IMain;
/*     */ import me.furnace.XMaterial;
/*     */ import me.furnace.manager.recipe.IFurnaceRecipe;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class ITick
/*     */ {
/*     */   private IFurnace F;
/*     */   
/*     */   public ITick(IFurnace F) {
/*  18 */     this.F = F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean start() {
/*  28 */     if (this.F.VIEWERS_TASK == null) {
/*  29 */       this.F.VIEWERS_TASK = IMain.SC.runTaskTimer(IMain.PL, new Runnable() {
/*     */             public void run() {
/*  31 */               ITick.this.tick_viewers();
/*     */             }
/*  33 */           },  20L, 20L);
/*     */     }
/*  35 */     if (this.F.FURNACE_TASK == null) {
/*  36 */       this.F.FURNACE_TASK = IMain.SC.runTaskTimerAsynchronously(IMain.PL, new Runnable() {
/*     */             public void run() {
/*  38 */               ITick.this.tick_furnace();
/*     */             }
/*  40 */           },  1L, 1L);
/*     */     }
/*  42 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean stop() {
/*  47 */     if (this.F.VIEWERS_TASK != null) {
/*  48 */       this.F.VIEWERS_TASK.cancel();
/*     */     }
/*  50 */     if (this.F.FURNACE_TASK != null) {
/*  51 */       this.F.FURNACE_TASK.cancel();
/*     */     }
/*  53 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void tick_viewers() {
/*  60 */     if (this.F.VIEWERS.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/*  64 */     List<HumanEntity> L = new ArrayList<>();
/*     */     
/*  66 */     for (HumanEntity VIEWER : this.F.VIEWERS) {
/*  67 */       if (!VIEWER.hasPermission(IMain.UTILS.PERM((Player)VIEWER, "use"))) {
/*  68 */         L.add(VIEWER);
/*     */       }
/*     */     } 
/*  71 */     if (!L.isEmpty()) {
/*  72 */       for (HumanEntity VIEWER : L) {
/*  73 */         VIEWER.closeInventory();
/*  74 */         IMain.UTILS.sendEffect((Player)VIEWER, "noperm");
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void tick_furnace() {
/*     */     try {
/*  85 */       if (this.F.OWNER == null || !this.F.OWNER.isOnline()) {
/*  86 */         if (IMain.CONFIG.b("notify_menu")) {
/*  87 */           IFurnaceU.send_all(this.F, IMain.VARS.M(null, "furnace_preview", this.F));
/*     */         }
/*     */         return;
/*     */       } 
/*  91 */       Player OWNER_P = IMain.S.getPlayer(this.F.OWNER.getName());
/*     */       
/*  93 */       if (!OWNER_P.hasPermission(IMain.CONFIG.perm("use"))) {
/*  94 */         if (IMain.CONFIG.b("notify_menu")) {
/*  95 */           IFurnaceU.send_all(this.F, IMain.VARS.M((OfflinePlayer)OWNER_P, "furnace_preview", this.F));
/*     */         }
/*     */         return;
/*     */       } 
/*  99 */       ItemStack FUEL = this.F.fuel();
/* 100 */       ItemStack RESULT = this.F.result();
/* 101 */       ItemStack SMELTING = this.F.smelting();
/* 102 */       IFurnaceRecipe RECIPE = IMain.RECIPES.recipe_smelting(SMELTING);
/*     */ 
/*     */       
/* 105 */       if (this.F.BURNTIME > 1) {
/* 106 */         if (this.F.COOKTIME > 0) {
/* 107 */           if (IMain.CONFIG.b("notify_menu")) {
/* 108 */             IFurnaceU.send_all(this.F, IMain.VARS.M((OfflinePlayer)OWNER_P, "furnace_smelting", this.F));
/*     */           }
/*     */         }
/* 111 */         else if (IMain.CONFIG.b("notify_menu")) {
/* 112 */           IFurnaceU.send_all(this.F, IMain.VARS.M((OfflinePlayer)OWNER_P, "furnace_burning", this.F));
/*     */         } 
/*     */       } else {
/*     */         
/* 116 */         this.F.BURNTIME = 0;
/* 117 */         if (IMain.CONFIG.b("notify_menu")) {
/* 118 */           IFurnaceU.send_all(this.F, IMain.VARS.M((OfflinePlayer)OWNER_P, "furnace_waiting", this.F));
/*     */         }
/*     */       } 
/*     */       
/* 122 */       if (this.F.BURNTIME <= 0 && (IFurnaceU.E(FUEL) || IFurnaceU.E(SMELTING))) {
/* 123 */         this.F.FUEL_C = IMain.EMPTY;
/* 124 */         this.F.RESULT_C = IMain.EMPTY;
/* 125 */         this.F.SMELTING_C = IMain.EMPTY;
/*     */         
/* 127 */         if (this.F.COOKTIME > 1) {
/* 128 */           this.F.COOKTIME = 0;
/* 129 */           this.F.RESULT_A = 0;
/* 130 */           this.F.SMELTING_A = 0;
/* 131 */           IFurnaceU.send_all_P(this.F, "nofuel");
/* 132 */           IMain.UTILS.sendEffect(OWNER_P, "nofuel");
/* 133 */           if (IMain.CONFIG.b("notify_owner")) {
/* 134 */             OWNER_P.sendMessage(IMain.VARS.M((OfflinePlayer)OWNER_P, "furnace_nofuel_m", this.F));
/*     */           }
/* 136 */           if (IMain.CONFIG.b("notify_menu")) {
/* 137 */             IFurnaceU.send_all(this.F, IMain.VARS.M((OfflinePlayer)OWNER_P, "furnace_nofuel", this.F), true);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 142 */       if (this.F.BURNTIME <= 0 && IFurnaceU.has_fuel(this.F.TILE, FUEL) && IFurnaceU.can_burn(RECIPE, RESULT)) {
/* 143 */         int TIME = ((Integer)IMain.VERSION.FURNACE_TILE_FUELTIME_METHOD.invoke(this.F.TILE, new Object[] {
/* 144 */               IMain.VERSION.FURNACE_TILE_GETITEM_METHOD.invoke(this.F.TILE, new Object[] { Integer.valueOf(1) })
/*     */             })).intValue();
/* 146 */         this.F.BURNTIME = TIME;
/* 147 */         this.F.FUEL_C = IFurnaceU.word(FUEL);
/*     */ 
/*     */         
/* 150 */         ItemStack FC = IMain.EMPTY_ITEM;
/*     */         
/* 152 */         if (FUEL.getAmount() > 1) {
/* 153 */           FUEL.setAmount(FUEL.getAmount() - 1);
/* 154 */           FC = FUEL;
/*     */         }
/* 156 */         else if (IFurnaceU.is_bucket(FUEL)) {
/* 157 */           FC = XMaterial.BUCKET.parseItem(1);
/*     */         } 
/*     */         
/* 160 */         IMain.VERSION.FURNACE_TILE_SETITEM_METHOD.invoke(this.F.TILE, new Object[] { Integer.valueOf(1), 
/* 161 */               IMain.VERSION.CITEMSTACK_NMS_METHOD.invoke(null, new Object[] { FC }) });
/*     */       } 
/*     */       
/* 164 */       if (this.F.BURNTIME > 0) {
/* 165 */         this.F.BURNTIME--;
/* 166 */         if (IFurnaceU.can_burn(RECIPE, RESULT)) {
/* 167 */           this.F.COOKTIME++;
/* 168 */           this.F.RESULT_A = RESULT.getAmount();
/* 169 */           this.F.COOKTIME_TOTAL = RECIPE.COOKTIME;
/* 170 */           this.F.SMELTING_A = SMELTING.getAmount();
/*     */           
/* 172 */           if (this.F.COOKTIME == 1) {
/* 173 */             this.F.SMELTING_C = IFurnaceU.word(SMELTING);
/* 174 */             this.F.RESULT_C = IFurnaceU.word(RECIPE.RESULT);
/*     */           } 
/* 176 */           if (this.F.COOKTIME >= this.F.COOKTIME_TOTAL - 1) {
/* 177 */             this.F.COOKTIME = 0;
/* 178 */             IFurnaceU.send_all_P(this.F, "smelting");
/* 179 */             IMain.UTILS.sendEffect(OWNER_P, "smelting");
/* 180 */             IFurnaceU.burn(RECIPE, this.F.TILE, SMELTING, RESULT, FUEL);
/* 181 */             if (IMain.CONFIG.b("notify_menu")) {
/* 182 */               IFurnaceU.send_all(this.F, IMain.VARS.M((OfflinePlayer)OWNER_P, "furnace_smelting_done", this.F)
/* 183 */                   .replace("%RESULT_A%", (new StringBuilder(String.valueOf(RECIPE.RESULT.getAmount()))).toString()), true);
/*     */             }
/* 185 */             if (IMain.CONFIG.b("notify_owner")) {
/* 186 */               IMain.VERSION.actionbar_send(OWNER_P, 
/* 187 */                   IMain.VARS.M((OfflinePlayer)OWNER_P, "furnace_smelting_done_m", this.F).replace("%RESULT_A%", (
/* 188 */                     new StringBuilder(String.valueOf(RECIPE.RESULT.getAmount()))).toString()));
/*     */             }
/*     */           } 
/*     */         } else {
/* 192 */           this.F.COOKTIME = 0;
/* 193 */           this.F.RESULT_A = 0;
/* 194 */           this.F.SMELTING_A = 0;
/* 195 */           this.F.RESULT_C = IMain.EMPTY;
/* 196 */           this.F.SMELTING_C = IMain.EMPTY;
/*     */         } 
/*     */       } 
/*     */       
/* 200 */       IMain.VERSION.FURNACE_TILE_BURN_FIELD.set(this.F.TILE, Integer.valueOf(this.F.BURNTIME));
/* 201 */       IMain.VERSION.FURNACE_TILE_COOK_FIELD.set(this.F.TILE, Integer.valueOf(this.F.COOKTIME));
/* 202 */       IMain.VERSION.FURNACE_TILE_COOKT_FIELD.set(this.F.TILE, Integer.valueOf(this.F.COOKTIME_TOTAL));
/* 203 */     } catch (Exception E) {
/* 204 */       E.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\manager\furnace\ITick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */