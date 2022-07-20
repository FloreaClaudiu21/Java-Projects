/*     */ package me.storagecabinet.utils;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.URL;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.ScheduledExecutorService;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.logging.Level;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.net.ssl.HttpsURLConnection;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
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
/*     */ 
/*     */ 
/*     */ public class Metrics
/*     */ {
/*     */   private final Plugin plugin;
/*     */   private final MetricsBase metricsBase;
/*     */   
/*     */   public Metrics(JavaPlugin plugin, int serviceId) {
/*  50 */     this.plugin = (Plugin)plugin;
/*  51 */     File bStatsFolder = new File(plugin.getDataFolder(), "metrics");
/*  52 */     File configFile = new File(bStatsFolder, "config.yml");
/*  53 */     YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
/*  54 */     if (!config.isSet("serverUuid")) {
/*  55 */       config.addDefault("enabled", Boolean.valueOf(true));
/*  56 */       config.addDefault("serverUuid", UUID.randomUUID().toString());
/*  57 */       config.addDefault("logFailedRequests", Boolean.valueOf(false));
/*  58 */       config.addDefault("logSentData", Boolean.valueOf(false));
/*  59 */       config.addDefault("logResponseStatusText", Boolean.valueOf(false));
/*     */       
/*  61 */       config.options().copyDefaults(true);
/*     */       try {
/*  63 */         config.save(configFile);
/*  64 */       } catch (IOException iOException) {}
/*     */     } 
/*     */ 
/*     */     
/*  68 */     boolean enabled = config.getBoolean("enabled", true);
/*  69 */     String serverUUID = config.getString("serverUuid");
/*  70 */     boolean logErrors = config.getBoolean("logFailedRequests", false);
/*  71 */     boolean logSentData = config.getBoolean("logSentData", false);
/*  72 */     boolean logResponseStatusText = config.getBoolean("logResponseStatusText", false);
/*  73 */     this.metricsBase = new MetricsBase("bukkit", serverUUID, serviceId, enabled, this::appendPlatformData, 
/*  74 */         this::appendServiceData, submitDataTask -> { 
/*  75 */         }plugin::isEnabled, (message, error) -> this.plugin.getLogger().log(Level.WARNING, message, error), message -> this.plugin.getLogger().log(Level.INFO, message), 
/*  76 */         logErrors, logSentData, 
/*  77 */         logResponseStatusText);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCustomChart(CustomChart chart) {
/*  87 */     this.metricsBase.addCustomChart(chart);
/*     */   }
/*     */   
/*     */   private void appendPlatformData(JsonObjectBuilder builder) {
/*  91 */     builder.appendField("playerAmount", getPlayerAmount());
/*  92 */     builder.appendField("onlineMode", Bukkit.getOnlineMode() ? 1 : 0);
/*  93 */     builder.appendField("bukkitVersion", Bukkit.getVersion());
/*  94 */     builder.appendField("bukkitName", Bukkit.getName());
/*  95 */     builder.appendField("javaVersion", System.getProperty("java.version"));
/*  96 */     builder.appendField("osName", System.getProperty("os.name"));
/*  97 */     builder.appendField("osArch", System.getProperty("os.arch"));
/*  98 */     builder.appendField("osVersion", System.getProperty("os.version"));
/*  99 */     builder.appendField("coreCount", Runtime.getRuntime().availableProcessors());
/*     */   }
/*     */   
/*     */   private void appendServiceData(JsonObjectBuilder builder) {
/* 103 */     builder.appendField("pluginVersion", this.plugin.getDescription().getVersion());
/*     */   }
/*     */   
/*     */   private int getPlayerAmount() {
/*     */     try {
/* 108 */       Method onlinePlayersMethod = Class.forName("org.bukkit.Server").getMethod("getOnlinePlayers", new Class[0]);
/* 109 */       return onlinePlayersMethod.getReturnType().equals(Collection.class) ? (
/* 110 */         (Collection)onlinePlayersMethod.invoke(Bukkit.getServer(), new Object[0])).size() : (
/* 111 */         (Player[])onlinePlayersMethod.invoke(Bukkit.getServer(), new Object[0])).length;
/* 112 */     } catch (Exception e) {
/* 113 */       return Bukkit.getOnlinePlayers().size();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class MetricsBase { public static final String METRICS_VERSION = "2.2.1";
/*     */     private static final ScheduledExecutorService scheduler;
/*     */     private static final String REPORT_URL = "https://bStats.org/api/v2/data/%s";
/*     */     
/*     */     static {
/* 122 */       scheduler = Executors.newScheduledThreadPool(1, task -> new Thread(task, "bStats-Metrics"));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private final String platform;
/*     */     
/*     */     private final String serverUuid;
/*     */     
/*     */     private final int serviceId;
/*     */     
/*     */     private final Consumer<Metrics.JsonObjectBuilder> appendPlatformDataConsumer;
/*     */     
/*     */     private final Consumer<Metrics.JsonObjectBuilder> appendServiceDataConsumer;
/*     */     
/*     */     private final Consumer<Runnable> submitTaskConsumer;
/*     */     
/*     */     private final Supplier<Boolean> checkServiceEnabledSupplier;
/*     */     
/*     */     private final BiConsumer<String, Throwable> errorLogger;
/*     */     
/*     */     private final Consumer<String> infoLogger;
/*     */     
/*     */     private final boolean logErrors;
/*     */     
/*     */     private final boolean logSentData;
/*     */     
/*     */     private final boolean logResponseStatusText;
/*     */     
/* 151 */     private final Set<Metrics.CustomChart> customCharts = new HashSet<>();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final boolean enabled;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public MetricsBase(String platform, String serverUuid, int serviceId, boolean enabled, Consumer<Metrics.JsonObjectBuilder> appendPlatformDataConsumer, Consumer<Metrics.JsonObjectBuilder> appendServiceDataConsumer, Consumer<Runnable> submitTaskConsumer, Supplier<Boolean> checkServiceEnabledSupplier, BiConsumer<String, Throwable> errorLogger, Consumer<String> infoLogger, boolean logErrors, boolean logSentData, boolean logResponseStatusText) {
/* 189 */       this.platform = platform;
/* 190 */       this.serverUuid = serverUuid;
/* 191 */       this.serviceId = serviceId;
/* 192 */       this.enabled = enabled;
/* 193 */       this.appendPlatformDataConsumer = appendPlatformDataConsumer;
/* 194 */       this.appendServiceDataConsumer = appendServiceDataConsumer;
/* 195 */       this.submitTaskConsumer = submitTaskConsumer;
/* 196 */       this.checkServiceEnabledSupplier = checkServiceEnabledSupplier;
/* 197 */       this.errorLogger = errorLogger;
/* 198 */       this.infoLogger = infoLogger;
/* 199 */       this.logErrors = logErrors;
/* 200 */       this.logSentData = logSentData;
/* 201 */       this.logResponseStatusText = logResponseStatusText;
/* 202 */       checkRelocation();
/* 203 */       if (enabled) {
/* 204 */         startSubmitting();
/*     */       }
/*     */     }
/*     */     
/*     */     public void addCustomChart(Metrics.CustomChart chart) {
/* 209 */       this.customCharts.add(chart);
/*     */     }
/*     */     
/*     */     private void startSubmitting() {
/* 213 */       Runnable submitTask = () -> {
/*     */           if (!this.enabled || !((Boolean)this.checkServiceEnabledSupplier.get()).booleanValue()) {
/*     */             scheduler.shutdown();
/*     */             return;
/*     */           } 
/*     */           if (this.submitTaskConsumer != null) {
/*     */             this.submitTaskConsumer.accept(this::submitData);
/*     */           } else {
/*     */             submitData();
/*     */           } 
/*     */         };
/* 224 */       long initialDelay = (long)(60000.0D * (3.0D + Math.random() * 3.0D));
/* 225 */       long secondDelay = (long)(60000.0D * Math.random() * 30.0D);
/* 226 */       scheduler.schedule(submitTask, initialDelay, TimeUnit.MILLISECONDS);
/* 227 */       scheduler.scheduleAtFixedRate(submitTask, initialDelay + secondDelay, 1800000L, 
/* 228 */           TimeUnit.MILLISECONDS);
/*     */     }
/*     */     
/*     */     private void submitData() {
/* 232 */       Metrics.JsonObjectBuilder baseJsonBuilder = new Metrics.JsonObjectBuilder();
/* 233 */       this.appendPlatformDataConsumer.accept(baseJsonBuilder);
/* 234 */       Metrics.JsonObjectBuilder serviceJsonBuilder = new Metrics.JsonObjectBuilder();
/* 235 */       this.appendServiceDataConsumer.accept(serviceJsonBuilder);
/*     */       
/* 237 */       serviceJsonBuilder.appendField("id", this.serviceId);
/* 238 */       serviceJsonBuilder.appendField("customCharts", "");
/* 239 */       baseJsonBuilder.appendField("service", serviceJsonBuilder.build());
/* 240 */       baseJsonBuilder.appendField("serverUUID", this.serverUuid);
/* 241 */       baseJsonBuilder.appendField("metricsVersion", "2.2.1");
/* 242 */       Metrics.JsonObjectBuilder.JsonObject data = baseJsonBuilder.build();
/* 243 */       scheduler.execute(() -> {
/*     */             try {
/*     */               sendData(param1JsonObject);
/* 246 */             } catch (Exception e) {
/*     */               if (this.logErrors)
/*     */                 this.errorLogger.accept("Could not submit bStats metrics data", e); 
/*     */             } 
/*     */           });
/*     */     }
/*     */     
/*     */     private void sendData(Metrics.JsonObjectBuilder.JsonObject data) throws Exception {
/*     */       StringBuilder builder;
/* 255 */       if (this.logSentData) {
/* 256 */         this.infoLogger.accept("Sent bStats metrics data: " + data.toString());
/*     */       }
/* 258 */       String url = String.format("https://bStats.org/api/v2/data/%s", new Object[] { this.platform });
/* 259 */       HttpsURLConnection connection = (HttpsURLConnection)(new URL(url)).openConnection();
/* 260 */       byte[] compressedData = compress(data.toString());
/* 261 */       connection.setRequestMethod("POST");
/* 262 */       connection.addRequestProperty("Accept", "application/json");
/* 263 */       connection.addRequestProperty("Connection", "close");
/* 264 */       connection.addRequestProperty("Content-Encoding", "gzip");
/* 265 */       connection.addRequestProperty("Content-Length", String.valueOf(compressedData.length));
/* 266 */       connection.setRequestProperty("Content-Type", "application/json");
/* 267 */       connection.setRequestProperty("User-Agent", "Metrics-Service/1");
/* 268 */       connection.setDoOutput(true);
/* 269 */       Exception exception1 = null, exception2 = null; try { DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream()); 
/* 270 */         try { outputStream.write(compressedData); }
/* 271 */         finally { if (outputStream != null) outputStream.close();  }  } finally { exception2 = null; if (exception1 == null) { exception1 = exception2; } else if (exception1 != exception2) { exception1.addSuppressed(exception2); }
/*     */          }
/* 273 */        Exception exception3 = null; try { BufferedReader bufferedReader = new BufferedReader(
/* 274 */             new InputStreamReader(connection.getInputStream())); 
/*     */         try { String line;
/* 276 */           while ((line = bufferedReader.readLine()) != null)
/* 277 */             builder.append(line);  }
/*     */         finally
/* 279 */         { if (bufferedReader != null) bufferedReader.close();  }  } finally { exception3 = null; if (exception2 == null) { exception2 = exception3; } else if (exception2 != exception3) { exception2.addSuppressed(exception3); }
/*     */          }
/*     */     
/*     */     }
/*     */ 
/*     */     
/*     */     private void checkRelocation() {
/* 286 */       if (System.getProperty("bstats.relocatecheck") == null || 
/* 287 */         !System.getProperty("bstats.relocatecheck").equals("false")) {
/* 288 */         String defaultPackage = new String(
/* 289 */             new byte[] { 111, 114, 103, 46, 98, 115, 116, 97, 116, 115 });
/* 290 */         String examplePackage = new String(
/* 291 */             new byte[] { 121, 111, 117, 114, 46, 112, 97, 99, 107, 97, 103, 101 });
/* 292 */         if (MetricsBase.class.getPackage().getName().startsWith(defaultPackage) || 
/* 293 */           MetricsBase.class.getPackage().getName().startsWith(examplePackage)) {
/* 294 */           throw new IllegalStateException("bStats Metrics class has not been relocated correctly!");
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*     */     private static byte[] compress(String str) throws IOException {
/* 300 */       if (str == null) {
/* 301 */         return null;
/*     */       }
/* 303 */       ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
/* 304 */       Exception exception1 = null, exception2 = null;
/*     */     } }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class AdvancedBarChart
/*     */     extends CustomChart
/*     */   {
/*     */     private final Callable<Map<String, int[]>> callable;
/*     */     
/*     */     public AdvancedBarChart(String chartId, Callable<Map<String, int[]>> callable) {
/* 315 */       super(chartId);
/* 316 */       this.callable = callable;
/*     */     }
/*     */ 
/*     */     
/*     */     protected Metrics.JsonObjectBuilder.JsonObject getChartData() throws Exception {
/* 321 */       Metrics.JsonObjectBuilder valuesBuilder = new Metrics.JsonObjectBuilder();
/* 322 */       Map<String, int[]> map = this.callable.call();
/* 323 */       if (map == null || map.isEmpty())
/*     */       {
/* 325 */         return null;
/*     */       }
/* 327 */       boolean allSkipped = true;
/* 328 */       for (Map.Entry<String, int[]> entry : map.entrySet()) {
/* 329 */         if (((int[])entry.getValue()).length == 0) {
/*     */           continue;
/*     */         }
/*     */         
/* 333 */         allSkipped = false;
/* 334 */         valuesBuilder.appendField(entry.getKey(), entry.getValue());
/*     */       } 
/* 336 */       if (allSkipped)
/*     */       {
/* 338 */         return null;
/*     */       }
/* 340 */       return (new Metrics.JsonObjectBuilder()).appendField("values", valuesBuilder.build()).build();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class SimpleBarChart
/*     */     extends CustomChart
/*     */   {
/*     */     private final Callable<Map<String, Integer>> callable;
/*     */ 
/*     */ 
/*     */     
/*     */     public SimpleBarChart(String chartId, Callable<Map<String, Integer>> callable) {
/* 355 */       super(chartId);
/* 356 */       this.callable = callable;
/*     */     }
/*     */ 
/*     */     
/*     */     protected Metrics.JsonObjectBuilder.JsonObject getChartData() throws Exception {
/* 361 */       Metrics.JsonObjectBuilder valuesBuilder = new Metrics.JsonObjectBuilder();
/* 362 */       Map<String, Integer> map = this.callable.call();
/* 363 */       if (map == null || map.isEmpty())
/*     */       {
/* 365 */         return null;
/*     */       }
/* 367 */       for (Map.Entry<String, Integer> entry : map.entrySet()) {
/* 368 */         valuesBuilder.appendField(entry.getKey(), new int[] { ((Integer)entry.getValue()).intValue() });
/*     */       } 
/* 370 */       return (new Metrics.JsonObjectBuilder()).appendField("values", valuesBuilder.build()).build();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class MultiLineChart
/*     */     extends CustomChart
/*     */   {
/*     */     private final Callable<Map<String, Integer>> callable;
/*     */ 
/*     */ 
/*     */     
/*     */     public MultiLineChart(String chartId, Callable<Map<String, Integer>> callable) {
/* 385 */       super(chartId);
/* 386 */       this.callable = callable;
/*     */     }
/*     */ 
/*     */     
/*     */     protected Metrics.JsonObjectBuilder.JsonObject getChartData() throws Exception {
/* 391 */       Metrics.JsonObjectBuilder valuesBuilder = new Metrics.JsonObjectBuilder();
/* 392 */       Map<String, Integer> map = this.callable.call();
/* 393 */       if (map == null || map.isEmpty())
/*     */       {
/* 395 */         return null;
/*     */       }
/* 397 */       boolean allSkipped = true;
/* 398 */       for (Map.Entry<String, Integer> entry : map.entrySet()) {
/* 399 */         if (((Integer)entry.getValue()).intValue() == 0) {
/*     */           continue;
/*     */         }
/*     */         
/* 403 */         allSkipped = false;
/* 404 */         valuesBuilder.appendField(entry.getKey(), ((Integer)entry.getValue()).intValue());
/*     */       } 
/* 406 */       if (allSkipped)
/*     */       {
/* 408 */         return null;
/*     */       }
/* 410 */       return (new Metrics.JsonObjectBuilder()).appendField("values", valuesBuilder.build()).build();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class AdvancedPie
/*     */     extends CustomChart
/*     */   {
/*     */     private final Callable<Map<String, Integer>> callable;
/*     */ 
/*     */ 
/*     */     
/*     */     public AdvancedPie(String chartId, Callable<Map<String, Integer>> callable) {
/* 425 */       super(chartId);
/* 426 */       this.callable = callable;
/*     */     }
/*     */ 
/*     */     
/*     */     protected Metrics.JsonObjectBuilder.JsonObject getChartData() throws Exception {
/* 431 */       Metrics.JsonObjectBuilder valuesBuilder = new Metrics.JsonObjectBuilder();
/* 432 */       Map<String, Integer> map = this.callable.call();
/* 433 */       if (map == null || map.isEmpty())
/*     */       {
/* 435 */         return null;
/*     */       }
/* 437 */       boolean allSkipped = true;
/* 438 */       for (Map.Entry<String, Integer> entry : map.entrySet()) {
/* 439 */         if (((Integer)entry.getValue()).intValue() == 0) {
/*     */           continue;
/*     */         }
/*     */         
/* 443 */         allSkipped = false;
/* 444 */         valuesBuilder.appendField(entry.getKey(), ((Integer)entry.getValue()).intValue());
/*     */       } 
/* 446 */       if (allSkipped)
/*     */       {
/* 448 */         return null;
/*     */       }
/* 450 */       return (new Metrics.JsonObjectBuilder()).appendField("values", valuesBuilder.build()).build();
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class CustomChart
/*     */   {
/*     */     private final String chartId;
/*     */     
/*     */     protected CustomChart(String chartId) {
/* 459 */       if (chartId == null) {
/* 460 */         throw new IllegalArgumentException("chartId must not be null");
/*     */       }
/* 462 */       this.chartId = chartId;
/*     */     }
/*     */ 
/*     */     
/*     */     public Metrics.JsonObjectBuilder.JsonObject getRequestJsonObject(BiConsumer<String, Throwable> errorLogger, boolean logErrors) {
/* 467 */       Metrics.JsonObjectBuilder builder = new Metrics.JsonObjectBuilder();
/* 468 */       builder.appendField("chartId", this.chartId);
/*     */       try {
/* 470 */         Metrics.JsonObjectBuilder.JsonObject data = getChartData();
/* 471 */         if (data == null)
/*     */         {
/* 473 */           return null;
/*     */         }
/* 475 */         builder.appendField("data", data);
/* 476 */       } catch (Throwable t) {
/* 477 */         if (logErrors) {
/* 478 */           errorLogger.accept("Failed to get data for custom chart with id " + this.chartId, t);
/*     */         }
/* 480 */         return null;
/*     */       } 
/* 482 */       return builder.build();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected abstract Metrics.JsonObjectBuilder.JsonObject getChartData() throws Exception;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class SingleLineChart
/*     */     extends CustomChart
/*     */   {
/*     */     private final Callable<Integer> callable;
/*     */ 
/*     */     
/*     */     public SingleLineChart(String chartId, Callable<Integer> callable) {
/* 499 */       super(chartId);
/* 500 */       this.callable = callable;
/*     */     }
/*     */ 
/*     */     
/*     */     protected Metrics.JsonObjectBuilder.JsonObject getChartData() throws Exception {
/* 505 */       int value = ((Integer)this.callable.call()).intValue();
/* 506 */       if (value == 0)
/*     */       {
/* 508 */         return null;
/*     */       }
/* 510 */       return (new Metrics.JsonObjectBuilder()).appendField("value", value).build();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class SimplePie
/*     */     extends CustomChart
/*     */   {
/*     */     private final Callable<String> callable;
/*     */ 
/*     */ 
/*     */     
/*     */     public SimplePie(String chartId, Callable<String> callable) {
/* 525 */       super(chartId);
/* 526 */       this.callable = callable;
/*     */     }
/*     */ 
/*     */     
/*     */     protected Metrics.JsonObjectBuilder.JsonObject getChartData() throws Exception {
/* 531 */       String value = this.callable.call();
/* 532 */       if (value == null || value.isEmpty())
/*     */       {
/* 534 */         return null;
/*     */       }
/* 536 */       return (new Metrics.JsonObjectBuilder()).appendField("value", value).build();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class DrilldownPie
/*     */     extends CustomChart
/*     */   {
/*     */     private final Callable<Map<String, Map<String, Integer>>> callable;
/*     */ 
/*     */ 
/*     */     
/*     */     public DrilldownPie(String chartId, Callable<Map<String, Map<String, Integer>>> callable) {
/* 551 */       super(chartId);
/* 552 */       this.callable = callable;
/*     */     }
/*     */ 
/*     */     
/*     */     public Metrics.JsonObjectBuilder.JsonObject getChartData() throws Exception {
/* 557 */       Metrics.JsonObjectBuilder valuesBuilder = new Metrics.JsonObjectBuilder();
/* 558 */       Map<String, Map<String, Integer>> map = this.callable.call();
/* 559 */       if (map == null || map.isEmpty())
/*     */       {
/* 561 */         return null;
/*     */       }
/* 563 */       boolean reallyAllSkipped = true;
/* 564 */       for (Map.Entry<String, Map<String, Integer>> entryValues : map.entrySet()) {
/* 565 */         Metrics.JsonObjectBuilder valueBuilder = new Metrics.JsonObjectBuilder();
/* 566 */         boolean allSkipped = true;
/* 567 */         for (Map.Entry<String, Integer> valueEntry : (Iterable<Map.Entry<String, Integer>>)((Map)map.get(entryValues.getKey())).entrySet()) {
/* 568 */           valueBuilder.appendField(valueEntry.getKey(), ((Integer)valueEntry.getValue()).intValue());
/* 569 */           allSkipped = false;
/*     */         } 
/* 571 */         if (!allSkipped) {
/* 572 */           reallyAllSkipped = false;
/* 573 */           valuesBuilder.appendField(entryValues.getKey(), valueBuilder.build());
/*     */         } 
/*     */       } 
/* 576 */       if (reallyAllSkipped)
/*     */       {
/* 578 */         return null;
/*     */       }
/* 580 */       return (new Metrics.JsonObjectBuilder()).appendField("values", valuesBuilder.build()).build();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class JsonObjectBuilder
/*     */   {
/* 593 */     private StringBuilder builder = new StringBuilder();
/*     */     
/*     */     private boolean hasAtLeastOneField = false;
/*     */     
/*     */     public JsonObjectBuilder() {
/* 598 */       this.builder.append("{");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public JsonObjectBuilder appendNull(String key) {
/* 608 */       appendFieldUnescaped(key, "null");
/* 609 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public JsonObjectBuilder appendField(String key, String value) {
/* 620 */       if (value == null) {
/* 621 */         throw new IllegalArgumentException("JSON value must not be null");
/*     */       }
/* 623 */       appendFieldUnescaped(key, "\"" + escape(value) + "\"");
/* 624 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public JsonObjectBuilder appendField(String key, int value) {
/* 635 */       appendFieldUnescaped(key, String.valueOf(value));
/* 636 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public JsonObjectBuilder appendField(String key, JsonObject object) {
/* 647 */       if (object == null) {
/* 648 */         throw new IllegalArgumentException("JSON object must not be null");
/*     */       }
/* 650 */       appendFieldUnescaped(key, object.toString());
/* 651 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public JsonObjectBuilder appendField(String key, String[] values) {
/* 662 */       if (values == null) {
/* 663 */         throw new IllegalArgumentException("JSON values must not be null");
/*     */       }
/* 665 */       String escapedValues = Arrays.<String>stream(values).map(value -> "\"" + escape(value) + "\"")
/* 666 */         .collect(Collectors.joining(","));
/* 667 */       appendFieldUnescaped(key, "[" + escapedValues + "]");
/* 668 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public JsonObjectBuilder appendField(String key, int[] values) {
/* 679 */       if (values == null) {
/* 680 */         throw new IllegalArgumentException("JSON values must not be null");
/*     */       }
/* 682 */       String escapedValues = Arrays.stream(values).<CharSequence>mapToObj(String::valueOf).collect(Collectors.joining(","));
/* 683 */       appendFieldUnescaped(key, "[" + escapedValues + "]");
/* 684 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public JsonObjectBuilder appendField(String key, JsonObject[] values) {
/* 695 */       if (values == null) {
/* 696 */         throw new IllegalArgumentException("JSON values must not be null");
/*     */       }
/* 698 */       String escapedValues = Arrays.<JsonObject>stream(values).map(JsonObject::toString).collect(Collectors.joining(","));
/* 699 */       appendFieldUnescaped(key, "[" + escapedValues + "]");
/* 700 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void appendFieldUnescaped(String key, String escapedValue) {
/* 710 */       if (this.builder == null) {
/* 711 */         throw new IllegalStateException("JSON has already been built");
/*     */       }
/* 713 */       if (key == null) {
/* 714 */         throw new IllegalArgumentException("JSON key must not be null");
/*     */       }
/* 716 */       if (this.hasAtLeastOneField) {
/* 717 */         this.builder.append(",");
/*     */       }
/* 719 */       this.builder.append("\"").append(escape(key)).append("\":").append(escapedValue);
/* 720 */       this.hasAtLeastOneField = true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public JsonObject build() {
/* 729 */       if (this.builder == null) {
/* 730 */         throw new IllegalStateException("JSON has already been built");
/*     */       }
/* 732 */       JsonObject object = new JsonObject(this.builder.append("}").toString(), null);
/* 733 */       this.builder = null;
/* 734 */       return object;
/*     */     }
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
/*     */ 
/*     */     
/*     */     private static String escape(String value) {
/* 749 */       StringBuilder builder = new StringBuilder();
/* 750 */       for (int i = 0; i < value.length(); i++) {
/* 751 */         char c = value.charAt(i);
/* 752 */         if (c == '"') {
/* 753 */           builder.append("\\\"");
/* 754 */         } else if (c == '\\') {
/* 755 */           builder.append("\\\\");
/* 756 */         } else if (c <= '\017') {
/* 757 */           builder.append("\\u000").append(Integer.toHexString(c));
/* 758 */         } else if (c <= '\037') {
/* 759 */           builder.append("\\u00").append(Integer.toHexString(c));
/*     */         } else {
/* 761 */           builder.append(c);
/*     */         } 
/*     */       } 
/* 764 */       return builder.toString();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static class JsonObject
/*     */     {
/*     */       private final String value;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       private JsonObject(String value) {
/* 780 */         this.value = value;
/*     */       }
/*     */ 
/*     */       
/*     */       public String toString() {
/* 785 */         return this.value;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\flore\Desktop\StorageCabinet.jar!\me\storagecabine\\utils\Metrics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */