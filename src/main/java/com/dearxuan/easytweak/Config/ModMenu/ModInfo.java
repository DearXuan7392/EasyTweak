package com.dearxuan.easytweak.Config.ModMenu;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class ModInfo {

    public final static boolean DEBUG = false;

    public static String ModName;

    public static String ModId;

    public static Class ConfigClass;

    public static String PackageName;

    public static Logger LOGGER;

    public static Path ConfigurationFilePath;

    public static void Init(String ModName, String ModId, Class ConfigClass) {
        ModInfo.ModName = ModName;
        ModInfo.ModId = ModId;
        ModInfo.PackageName = "com.dearxuan." + ModId;
        ConfigurationFilePath = FabricLoader.getInstance().getConfigDir().toAbsolutePath().resolve(ModId + ".yml");
        ModInfo.LOGGER = new Logger(ModName);
        ModSaver.InitModConfig(ConfigClass);
    }

    public static Object getInstance() throws NoSuchFieldException, IllegalAccessException {
        return ConfigClass.getField("INSTANCE").get(null);
    }

    public static void setInstance(Object obj) throws NoSuchFieldException, IllegalAccessException {
        ConfigClass.getField("INSTANCE").set(null, obj);
    }


}
