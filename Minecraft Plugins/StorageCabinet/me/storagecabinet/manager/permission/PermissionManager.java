/*    */ package me.storagecabinet.manager.permission;
/*    */ 
/*    */ import me.storagecabinet.StorageCabinet;
/*    */ import me.storagecabinet.manager.ManagerAPI;
/*    */ 
/*    */ public class PermissionManager
/*    */   implements ManagerAPI
/*    */ {
/*    */   public String COMMAND_MENU;
/*    */   public String COMMAND_GETID;
/*    */   public String COMMAND_DELETE;
/*    */   public String COMMAND_GET;
/*    */   public String COMMAND_PREVIEW;
/*    */   public String COMMAND_LOCATE;
/*    */   
/*    */   public boolean register() {
/* 17 */     this.COMMAND_MENU = StorageCabinet.UTILS
/* 18 */       .decolor((String)StorageCabinet.SETTINGS.STRING_MAP.get("permissions.command_menu"));
/* 19 */     this.COMMAND_GET = StorageCabinet.UTILS
/* 20 */       .decolor((String)StorageCabinet.SETTINGS.STRING_MAP.get("permissions.command_get"));
/* 21 */     this.COMMAND_DELETE = StorageCabinet.UTILS
/* 22 */       .decolor((String)StorageCabinet.SETTINGS.STRING_MAP.get("permissions.command_delete"));
/* 23 */     this.COMMAND_GETID = StorageCabinet.UTILS
/* 24 */       .decolor((String)StorageCabinet.SETTINGS.STRING_MAP.get("permissions.command_getid"));
/* 25 */     this.COMMAND_PREVIEW = StorageCabinet.UTILS
/* 26 */       .decolor((String)StorageCabinet.SETTINGS.STRING_MAP.get("permissions.command_preview"));
/* 27 */     this.COMMAND_LOCATE = StorageCabinet.UTILS
/* 28 */       .decolor((String)StorageCabinet.SETTINGS.STRING_MAP.get("permissions.command_locate"));
/* 29 */     this.COMMAND_RELOAD = StorageCabinet.UTILS
/* 30 */       .decolor((String)StorageCabinet.SETTINGS.STRING_MAP.get("permissions.command_reload"));
/* 31 */     this.CABINET_CRAFT = StorageCabinet.UTILS
/* 32 */       .decolor((String)StorageCabinet.SETTINGS.STRING_MAP.get("permissions.cabinet_craft"));
/* 33 */     this.CABINET_BREAK = StorageCabinet.UTILS
/* 34 */       .decolor((String)StorageCabinet.SETTINGS.STRING_MAP.get("permissions.cabinet_break"));
/* 35 */     this.CABINET_BYPASS = StorageCabinet.UTILS
/* 36 */       .decolor((String)StorageCabinet.SETTINGS.STRING_MAP.get("permissions.cabinet_bypass"));
/* 37 */     this.CABINET_PLACE = StorageCabinet.UTILS
/* 38 */       .decolor((String)StorageCabinet.SETTINGS.STRING_MAP.get("permissions.cabinet_place"));
/* 39 */     this.CABINET_USE = StorageCabinet.UTILS
/* 40 */       .decolor((String)StorageCabinet.SETTINGS.STRING_MAP.get("permissions.cabinet_use"));
/* 41 */     this.CABINET_USE_ADMIN = StorageCabinet.UTILS
/* 42 */       .decolor((String)StorageCabinet.SETTINGS.STRING_MAP.get("permissions.cabinet_use_admin"));
/* 43 */     return false;
/*    */   }
/*    */   public String CABINET_CRAFT; public String CABINET_PLACE; public String CABINET_BREAK; public String CABINET_BYPASS; public String CABINET_USE; public String CABINET_USE_ADMIN; public String COMMAND_RELOAD;
/*    */   
/*    */   public boolean unregister() {
/* 48 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabinet\manager\permission\PermissionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */