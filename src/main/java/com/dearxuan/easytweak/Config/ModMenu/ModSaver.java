package com.dearxuan.easytweak.Config.ModMenu;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.introspector.PropertyUtils;
import org.yaml.snakeyaml.representer.Representer;

import java.io.FileWriter;
import java.nio.file.Files;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
                ModInfo.setInstance((BaseConfig) ConfigClass.getDeclaredConstructor().newInstance());
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
            DumperOptions options = new DumperOptions();
            EasyRepresenter easyRepresenter = new EasyRepresenter(options);
            Yaml yaml = new Yaml(easyRepresenter);
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
            ModInfo.setInstance((BaseConfig) yaml.loadAs(yamlString, ConfigClass));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static class EasyRepresenter extends Representer {

        public EasyRepresenter(DumperOptions options) {
            super(options);
            PropertyUtils propertyUtils = new PropertyUtils(){
                @Override
                protected Set<Property> createPropertySet(Class<? extends Object> type, BeanAccess bAccess){
                    return getPropertiesMap(type, BeanAccess.FIELD)
                            .values()
                            .stream()
                            .sequential()
                            .filter(property -> property.isReadable() && (isAllowReadOnlyProperties() || property.isWritable()))
                            .collect(Collectors.toCollection(LinkedHashSet::new));
                }
            };
            setPropertyUtils(propertyUtils);
        }
    }
}
