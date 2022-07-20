/*    */ package me.furnace.manager.menu.menu;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.furnace.IMain;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ 
/*    */ public class IMenuVars
/*    */ {
/*    */   private boolean B = false;
/*    */   private OfflinePlayer OFFP;
/*    */   private String ADD_XP;
/*    */   private String R_XP;
/*    */   private String ADD_T;
/*    */   
/*    */   public IMenuVars(OfflinePlayer OFFP, int NEXT, int PREV, int CUR) {
/* 17 */     this.OFFP = OFFP;
/* 18 */     this.CUR_PAGE = (new StringBuilder(String.valueOf(CUR))).toString();
/* 19 */     this.NEXT_PAGE = (new StringBuilder(String.valueOf(NEXT))).toString();
/* 20 */     this.LAST_PAGE = (new StringBuilder(String.valueOf(PREV))).toString();
/*    */   }
/*    */   private String R_T; private String T; private String XP; private String CUR_PAGE; private String NEXT_PAGE; private String LAST_PAGE;
/*    */   
/*    */   public IMenuVars(int T, double XP, int A_TIME, int R_TIME, double A_XP, double R_XP) {
/* 25 */     this.B = true;
/* 26 */     this.T = (new StringBuilder(String.valueOf(T))).toString();
/* 27 */     this.XP = (new StringBuilder(String.valueOf(XP))).toString();
/* 28 */     this.R_XP = (new StringBuilder(String.valueOf(R_XP))).toString();
/* 29 */     this.R_T = (new StringBuilder(String.valueOf(R_TIME))).toString();
/* 30 */     this.ADD_XP = (new StringBuilder(String.valueOf(A_XP))).toString();
/* 31 */     this.ADD_T = (new StringBuilder(String.valueOf(A_TIME))).toString();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<String> L(List<String> L) {
/* 37 */     List<String> NL = new ArrayList<>();
/* 38 */     if (L == null || L.isEmpty()) {
/* 39 */       return NL;
/*    */     }
/* 41 */     for (String M : L) {
/* 42 */       NL.add(M(M));
/*    */     }
/* 44 */     return NL;
/*    */   }
/*    */ 
/*    */   
/*    */   public String M(String M) {
/* 49 */     if (M == null || M.isEmpty()) {
/* 50 */       return "";
/*    */     }
/* 52 */     if (!this.B) {
/* 53 */       M = M.replace("%CUR_PAGE%", this.CUR_PAGE);
/* 54 */       M = M.replace("%LAST_PAGE%", this.LAST_PAGE);
/* 55 */       M = M.replace("%NEXT_PAGE%", this.NEXT_PAGE);
/*    */     } else {
/* 57 */       M = M.replace("%ADD_TIME%", this.ADD_T);
/* 58 */       M = M.replace("%ADD_XP%", this.ADD_XP);
/* 59 */       M = M.replace("%R_XP%", this.R_XP);
/* 60 */       M = M.replace("%R_TIME%", this.R_T);
/* 61 */       M = M.replace("%XP%", this.XP);
/* 62 */       M = M.replace("%TIME%", this.T);
/*    */     } 
/* 64 */     M = IMain.VARS.M1(this.OFFP, M, null, null, null);
/* 65 */     return M;
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\manager\menu\menu\IMenuVars.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */