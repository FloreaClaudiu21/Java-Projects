/*    */ package me.storagecabinet.support.list;
/*    */ 
/*    */ import com.massivecraft.factions.Board;
/*    */ import com.massivecraft.factions.FLocation;
/*    */ import com.massivecraft.factions.FPlayer;
/*    */ import com.massivecraft.factions.FPlayers;
/*    */ import com.massivecraft.factions.Faction;
/*    */ import com.massivecraft.factions.zcore.fperms.Access;
/*    */ import com.massivecraft.factions.zcore.fperms.PermissableAction;
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import me.storagecabinet.support.SupportAPI;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public enum Factions
/*    */   implements SupportAPI
/*    */ {
/* 19 */   PLACE(0), INTERACT(1), DESTROY(2);
/*    */   
/*    */   private int I;
/*    */   
/*    */   Factions(int I) {
/* 24 */     this.I = I;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean is_allowed(Player P, Location L, boolean H) {
/*    */     Access A, A1, A2;
/* 30 */     if (P == null || L == null) {
/* 31 */       return false;
/*    */     }
/* 33 */     if (!StorageCabinet.SUPPORT.HAS_F) {
/* 34 */       return false;
/*    */     }
/* 36 */     if (!H && P.hasPermission(StorageCabinet.PERM_MANAGER.CABINET_BYPASS)) {
/* 37 */       return true;
/*    */     }
/* 39 */     FPlayer M = FPlayers.getInstance().getByPlayer(P);
/* 40 */     Faction F = Board.getInstance().getFactionAt(new FLocation(L));
/*    */     
/* 42 */     if (F == null || M == null || F.getFPlayerLeader() == null) {
/* 43 */       return true;
/*    */     }
/* 45 */     if (F.getFPlayerLeader().getName().equals(M.getName())) {
/* 46 */       return true;
/*    */     }
/* 48 */     switch (this.I) {
/*    */       case 0:
/* 50 */         A = F.getAccess(M, PermissableAction.BUILD);
/*    */         
/* 52 */         if (A == Access.ALLOW) {
/* 53 */           return true;
/*    */         }
/*    */         break;
/*    */       case 1:
/* 57 */         A1 = F.getAccess(M, PermissableAction.CONTAINER);
/*    */         
/* 59 */         if (A1 == Access.ALLOW) {
/* 60 */           return true;
/*    */         }
/*    */         break;
/*    */       case 2:
/* 64 */         A2 = F.getAccess(M, PermissableAction.DESTROY);
/*    */         
/* 66 */         if (A2 == Access.ALLOW) {
/* 67 */           return true;
/*    */         }
/*    */         break;
/*    */     } 
/*    */ 
/*    */     
/* 73 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\support\list\Factions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */