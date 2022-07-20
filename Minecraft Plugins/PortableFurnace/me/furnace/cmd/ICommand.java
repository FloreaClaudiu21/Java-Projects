/*    */ package me.furnace.cmd;
/*    */ 
/*    */ import me.furnace.IMain;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class ICommand
/*    */   implements CommandExecutor {
/*    */   public ICommand(IMain PL) {
/* 13 */     PL.getCommand("portablefurnace").setExecutor(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender S, Command C, String L, String[] A) {
/* 24 */     if (S instanceof org.bukkit.command.ConsoleCommandSender) {
/* 25 */       return true;
/*    */     }
/* 27 */     if (!C.getName().equalsIgnoreCase("portablefurnace")) {
/* 28 */       return false;
/*    */     }
/* 30 */     Player P = (Player)S;
/*    */     
/* 32 */     if (A.length == 0) {
/* 33 */       P.closeInventory();
/* 34 */       IMain.DATA.get((OfflinePlayer)P).open_menu(P);
/* 35 */       return true;
/*    */     } 
/* 37 */     if (A.length >= 1) {
/* 38 */       String N = A[0];
/*    */       
/* 40 */       if (N == null || N.isEmpty()) {
/* 41 */         return false;
/*    */       }
/* 43 */       N = N.toLowerCase();
/*    */       
/* 45 */       if (!IMain.DATA.DATABASE.containsKey(N)) {
/* 46 */         IMain.VERSION.title_send(P, IMain.VARS.M((OfflinePlayer)P, "title"), IMain.VARS.M((OfflinePlayer)P, "playernotfound", N));
/* 47 */         return false;
/*    */       } 
/* 49 */       P.closeInventory();
/* 50 */       IMain.DATA.get(N).open_menu(P);
/* 51 */       return true;
/*    */     } 
/* 53 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\cmd\ICommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */