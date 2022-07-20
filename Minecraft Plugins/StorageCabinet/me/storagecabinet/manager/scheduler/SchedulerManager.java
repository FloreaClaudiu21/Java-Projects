/*     */ package me.storagecabinet.manager.scheduler;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import me.storagecabinet.StorageCabinet;
/*     */ import me.storagecabinet.manager.ManagerAPI;
/*     */ import me.storagecabinet.manager.cabinet.Cabinet;
/*     */ import me.storagecabinet.utils.XMaterial;
/*     */ import org.bukkit.Effect;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.block.Hopper;
/*     */ import org.bukkit.entity.ArmorStand;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.scheduler.BukkitTask;
/*     */ 
/*     */ public class SchedulerManager implements ManagerAPI {
/*     */   private Plugin PL;
/*     */   
/*     */   public SchedulerManager(Plugin PL) {
/*  27 */     this.PL = PL;
/*     */   }
/*     */   private BukkitTask TASK; private BukkitTask TASK1; private BukkitTask TASK2; private BukkitTask TASK3;
/*     */   private BukkitTask TASK4;
/*     */   
/*     */   public boolean register() {
/*  33 */     int TIME = ((Integer)StorageCabinet.SETTINGS.INT_MAP.get("backup.time")).intValue();
/*     */     
/*  35 */     this.TASK = StorageCabinet.SCHEDULER.runTaskTimerAsynchronously(this.PL, () -> tick_task(), 
/*     */         
/*  37 */         20L, 10L);
/*  38 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("backup.enabled")).booleanValue()) {
/*  39 */       this.TASK1 = StorageCabinet.SCHEDULER.runTaskTimerAsynchronously(this.PL, () -> {
/*     */             StorageCabinet.UTILS.debug("Backup started, might cause some lag...", true);
/*     */             tick_task1();
/*  42 */           }20L, TIME * 1200L);
/*     */     }
/*  44 */     this.TASK2 = StorageCabinet.SCHEDULER.runTaskTimer(this.PL, () -> tick_task2(), 
/*     */         
/*  46 */         40L, 4800L);
/*  47 */     this.TASK3 = StorageCabinet.SCHEDULER.runTaskTimerAsynchronously(this.PL, () -> tick_task3(), 
/*     */         
/*  49 */         20L, 20L);
/*  50 */     if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("hopper_system")).booleanValue()) {
/*  51 */       this.TASK4 = StorageCabinet.SCHEDULER.runTaskTimerAsynchronously(this.PL, () -> tick_task4(), 
/*     */           
/*  53 */           20L, 10L);
/*     */     }
/*  55 */     StorageCabinet.UTILS.debug("Schedulers have been started", false);
/*  56 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean unregister() {
/*  61 */     this.TASK.cancel();
/*  62 */     if (this.TASK1 != null) {
/*  63 */       this.TASK1.cancel();
/*     */     }
/*  65 */     this.TASK2.cancel();
/*  66 */     this.TASK3.cancel();
/*  67 */     if (this.TASK4 != null) {
/*  68 */       this.TASK4.cancel();
/*     */     }
/*  70 */     StorageCabinet.UTILS.debug("Schedulers have been stopped", false);
/*  71 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void tick_task() {
/*  76 */     StorageCabinet.STORAGE.LOCATIONS.forEach((L, C) -> {
/*     */           List<Inventory> IN_L = C.IN_L;
/*     */           for (Inventory I : IN_L) {
/*     */             if (I.getViewers().isEmpty()) {
/*     */               continue;
/*     */             }
/*     */             C.FOUND = true;
/*     */             C.IS_OPEN = true;
/*     */             break;
/*     */           } 
/*     */           if (C.FOUND) {
/*     */             C.IS_OPEN = true;
/*     */           } else {
/*     */             C.ANIM.close_anim();
/*     */           } 
/*     */           C.FOUND = false;
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick_task1() {
/* 101 */     if (!((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("backup.enabled")).booleanValue()) {
/*     */       return;
/*     */     }
/*     */     
/* 105 */     String T = " cabinets ";
/* 106 */     int I = StorageCabinet.STORAGE.backup_cabinets();
/* 107 */     int L = I - StorageCabinet.STORAGE.CABINETS.size();
/*     */     
/* 109 */     if (I < 2) {
/* 110 */       T = " cabinet ";
/*     */     }
/* 112 */     StorageCabinet.UTILS.debug("Backup done!", true);
/* 113 */     StorageCabinet.UTILS.debug(String.valueOf(I) + T + "have been saved in the backup folder", true);
/* 114 */     if (L > 0) {
/* 115 */       if (L > 1) {
/* 116 */         T = " cabinets ";
/*     */       } else {
/* 118 */         T = " cabinet ";
/*     */       } 
/* 120 */       StorageCabinet.UTILS
/* 121 */         .debug(String.valueOf(L) + T + "could not be saved cause of some errors that occured during the save...", true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void tick_task2() {
/* 128 */     StorageCabinet.UTILS.debug("Looking for ghost armor stands in the worlds, might cause a bit of lag...", true);
/* 129 */     StorageCabinet.SERVER.getWorlds().forEach(W -> {
/*     */           Collection<Entity> LE = W.getEntities();
/*     */           for (Entity E : LE) {
/*     */             if (E instanceof ArmorStand) {
/*     */               ArmorStand S1 = (ArmorStand)E;
/*     */               if (StorageCabinet.CABINET_MANAGER.is_cabinet_stand(S1)) {
/*     */                 Block B = S1.getLocation().getBlock();
/*     */                 S1.remove();
/*     */                 if (XMaterial.matchXMaterial(B.getType()) == XMaterial.GLASS) {
/*     */                   B.setType(XMaterial.AIR.parseMaterial());
/*     */                 }
/*     */                 StorageCabinet.UTILS.debug("Found a ghost armor stand at the location: " + B.getLocation().toString() + "/Block and armor stand have been removed!", true);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void tick_task3() {
/* 155 */     List<Entity> R = new ArrayList<>();
/*     */     
/* 157 */     StorageCabinet.STORAGE.DROPPED.forEach((E, ID) -> {
/*     */           if (E.isDead()) {
/*     */             Location L = E.getLocation();
/*     */ 
/*     */ 
/*     */             
/*     */             paramList.add(E);
/*     */ 
/*     */ 
/*     */             
/*     */             if (StorageCabinet.STORAGE.CABINETS.containsKey(ID)) {
/*     */               Cabinet CABINET_M = (Cabinet)StorageCabinet.STORAGE.CABINETS.get(ID);
/*     */ 
/*     */ 
/*     */               
/*     */               if (CABINET_M.LOC != null) {
/*     */                 StorageCabinet.SCHEDULER.runTaskLater(StorageCabinet.PLUGIN, (), 1L);
/*     */               }
/*     */             } 
/*     */ 
/*     */ 
/*     */             
/*     */             StorageCabinet.STORAGE.CABINETS.remove(ID);
/*     */ 
/*     */ 
/*     */             
/*     */             if (((Boolean)StorageCabinet.SETTINGS.BOOL_MAP.get("particles.drop")).booleanValue()) {
/*     */               for (int I = 0; I <= 2; I++) {
/*     */                 L.getWorld().playEffect(L, Effect.SMOKE, I);
/*     */               }
/*     */             }
/*     */           } 
/*     */         });
/*     */ 
/*     */     
/* 192 */     R.forEach(E -> StorageCabinet.STORAGE.DROPPED.remove(E));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void tick_task4() {
/* 200 */     StorageCabinet.STORAGE.HOPPERS_DOWN.forEach(H -> {
/*     */           Block B = H.getBlock();
/*     */           
/*     */           Block BR = B.getRelative(BlockFace.UP);
/*     */           
/*     */           if (BR.getChunk().isLoaded() && StorageCabinet.CABINET_MANAGER.is_cabinet_block(BR)) {
/*     */             Cabinet C = StorageCabinet.CABINET_MANAGER.get_cabinet_by_block(BR);
/*     */             StorageCabinet.CABINET_MANAGER.HOPPER_MECHANICS.move_item_from_cabinet_to_hopper(C, H);
/*     */           } 
/*     */         });
/* 210 */     StorageCabinet.STORAGE.HOPPERS.forEach((H, FACE) -> {
/*     */           Block RB = H.getBlock().getRelative(FACE);
/*     */           if (RB.getChunk().isLoaded() && StorageCabinet.CABINET_MANAGER.is_cabinet_block(RB)) {
/*     */             Cabinet C = StorageCabinet.CABINET_MANAGER.get_cabinet_by_block(RB);
/*     */             StorageCabinet.CABINET_MANAGER.HOPPER_MECHANICS.move_item_from_hopper_to_cabinet(H, C);
/*     */             return;
/*     */           } 
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\scheduler\SchedulerManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */