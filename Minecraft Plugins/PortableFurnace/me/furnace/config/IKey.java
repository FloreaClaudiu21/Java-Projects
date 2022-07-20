/*    */ package me.furnace.config;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.furnace.IMain;
/*    */ import org.apache.commons.lang.math.NumberUtils;
/*    */ 
/*    */ 
/*    */ public class IKey
/*    */ {
/* 11 */   public int I = 0;
/*    */   private Object VALUE;
/* 13 */   public String S = "";
/*    */   
/*    */   public List<String> L;
/*    */   public boolean B = false;
/*    */   
/*    */   public IKey(Object VALUE) {
/* 19 */     if (VALUE == null) {
/*    */       return;
/*    */     }
/* 22 */     this.VALUE = VALUE;
/* 23 */     if (this.VALUE instanceof List) {
/* 24 */       this.L = (List<String>)VALUE;
/* 25 */       this.L = IMain.UTILS.colorL(this.L);
/*    */       return;
/*    */     } 
/* 28 */     this.L = new ArrayList<>();
/*    */     
/* 30 */     this.S = String.valueOf(this.VALUE);
/* 31 */     this.B = Boolean.valueOf(this.S).booleanValue();
/* 32 */     if (NumberUtils.isNumber(this.S)) {
/* 33 */       this.I = Integer.valueOf(this.S).intValue();
/*    */       return;
/*    */     } 
/* 36 */     this.S = IMain.UTILS.color(this.S);
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\PortableFurnace.jar!\me\furnace\config\IKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */