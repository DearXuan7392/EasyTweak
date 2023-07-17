package com.dearxuan.easytweak.Config.ModMenu;

import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.nio.file.Files;

import static com.dearxuan.easytweak.Config.ModMenu.ModInfo.ConfigClass;
import static com.dearxuan.easytweak.Config.ModMenu.ModInfo.LOGGER;

public class ModSaver {

    /**
     * 初始化Mod信息
     */
    public static void InitModConfig(Class config) {
        try {
            ConfigClass = config;

            // 尝试读取配置, 若失败, 则使用默认配置
            if (!ReadFromYaml()) {
                ModInfo.setInstance(ConfigClass.getDeclaredConstructor().newInstance());
            }
            // 覆盖原配置文件, 防止mod升级后新增的配置无法写入
            WriteToYaml();
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * 保存配置文件
     */
    public static boolean Save() {
        return WriteToYaml();
    }

    private static boolean WriteToYaml() {
        try {
            Yaml yaml = new Yaml();
            String yamlString = yaml.dumpAsMap(ModInfo.getInstance());
            // 如果配置文件存在且内容相同, 则不写入
            if(Files.exists(ModInfo.ConfigurationFilePath)){
                String originalString = new String(Files.readAllBytes(ModInfo.ConfigurationFilePath));
                if(originalString.equals(yamlString)){
                    return true;
                }
            }
            FileWriter writer = new FileWriter(ModInfo.ConfigurationFilePath.toFile());
            writer.write(yamlString);
            writer.close();
            return true;
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
    }

    private static boolean ReadFromYaml() {
        try {
            String yamlString = new String(Files.readAllBytes(ModInfo.ConfigurationFilePath));
            Yaml yaml = new Yaml();
            ModInfo.setInstance(yaml.loadAs(yamlString, ConfigClass));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
