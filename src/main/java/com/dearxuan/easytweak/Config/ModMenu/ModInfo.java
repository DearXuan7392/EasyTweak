package com.dearxuan.easytweak.Config.ModMenu;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class ModInfo {

    public static boolean DEBUG = true;

    public static String ModName;

    public static String ModId;

    public static Class ConfigClass;

    public static String PackageName;

    public static Logger LOGGER;

    public static Path ConfigurationFilePath;

    public static void Init(String ModName, String ModId, Class ConfigClass, boolean DEBUG) {
        ModInfo.DEBUG = DEBUG;
        ModInfo.ModName = ModName;
        ModInfo.ModId = ModId;
        ModInfo.PackageName = "com.dearxuan." + ModId;
        ConfigurationFilePath = FabricLoader.getInstance().getConfigDir().toAbsolutePath().resolve(ModId + ".yml");
        ModInfo.LOGGER = new Logger(ModName);
        ModSaver.InitModConfig(ConfigClass);
    }

    public static BaseConfig getInstance() throws NoSuchFieldException, IllegalAccessException {
        return (BaseConfig) ConfigClass.getField("INSTANCE").get(null);
    }

    public static void setInstance(BaseConfig obj) throws NoSuchFieldException, IllegalAccessException {
        ConfigClass.getField("INSTANCE").set(null, obj);
    }


}
