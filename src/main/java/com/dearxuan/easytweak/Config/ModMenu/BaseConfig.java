package com.dearxuan.easytweak.Config.ModMenu;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BaseConfig {

    public BaseConfig(){}

    private String[] Matchs = null;

    public boolean shouldApply(String mixinClassFullname){
        try{
            if(Matchs == null){
                Matchs = getAllMatches();
            }
            for(String pattern : Matchs){
                if(Support.isMatch(mixinClassFullname, pattern)){
                    return true;
                }
            }
            return false;
        }catch (Exception e){
            ModInfo.LOGGER.error(e);
            return false;
        }

    }

    public String[] getAllMatches() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<ConfigDesc> configDescList = getConfigDesc();
        HashSet<String> matches = new HashSet<>();
        matches.add(ModInfo.PackageName + ".mixin.Debug.*");
        for(ConfigDesc configDesc : configDescList){
            // 仅当配置不是默认值或测试字段才启用 Mixin
            if(!configDesc.DefaultValue.equals(configDesc.Value)){
                for(String mixinClass : configDesc.MixinClassName){
                    // 匹配 com.dearxuan.modid.mixin*.mixinClass
                    matches.add(ModInfo.PackageName + ".mixin*" + mixinClass);
                }
                for(String mixinPackage : configDesc.MixinPackage){
                    // 匹配 com.dearxuan.modid.mixin.mixinPackage.*
                    matches.add(ModInfo.PackageName + ".mixin." + mixinPackage + ".*");
                }
            }
        }
        return matches.toArray(new String[]{});
    }

    public List<ConfigDesc> getConfigDesc() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return getConfigDesc(null);
    }

    private List<ConfigDesc> getConfigDesc(String father) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 创建一个临时类用以获取默认值
        BaseConfig defaultConfig = this.getClass().getDeclaredConstructor().newInstance();
        // 获取自己的Field
        Field[] fields = this.getClass().getFields();
        // 待返回的具体配置内容
        List<ConfigDesc> configDescList = new ArrayList<>();
        for (Field field : fields ) {
            // 排除静态字段和私有字段
            if(!Modifier.isStatic(field.getModifiers()) && !Modifier.isPrivate(field.getModifiers())){
                switch (field.getType().getName()){
                    // 仅处理基本类型的配置
                    case "int", "double", "boolean":
                        configDescList.add(getConfigDesc(father, field, defaultConfig));
                        break;
                    default:
                        if(BaseConfig.class.isAssignableFrom(field.getType())){
                            configDescList.addAll(((BaseConfig)field.get(this)).getConfigDesc(field.getName()));
                        }
                }
            }

        }
        return configDescList;

    }

    private <T> ConfigDesc<T> getConfigDesc(String father, Field field, BaseConfig defaultConfig) throws IllegalAccessException {
        ConfigDesc<T> configDesc = new ConfigDesc<>(father, field, this, defaultConfig);
        return configDesc;
    }
}
