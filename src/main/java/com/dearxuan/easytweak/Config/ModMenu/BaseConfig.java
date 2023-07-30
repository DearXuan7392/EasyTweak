package com.dearxuan.easytweak.Config.ModMenu;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BaseConfig {

    public BaseConfig(){}

    private transient HashMap<String, ConfigDesc> ConfigDescHashMap = null;

    /**
     * 是否应该注入该Mixin类
     */
    public boolean shouldApply(String mixinClassFullname){
        for(ConfigDesc configDesc : getAllConfigDesc().values()){
            if(configDesc.canApply(mixinClassFullname)){
                return true;
            }
        }
        return false;
    }

    public HashMap<String, ConfigDesc> getAllConfigDesc() {
        if(this.ConfigDescHashMap != null){
            return this.ConfigDescHashMap;
        }
        this.ConfigDescHashMap = new HashMap<>();
        try{
            List<ConfigDesc> configDescList = getConfigDesc();
            for (ConfigDesc configDesc : configDescList){
                this.ConfigDescHashMap.put(configDesc.Fullname, configDesc);
            }
        }catch (Exception e){
            ModInfo.LOGGER.error(e);
        }
        return this.ConfigDescHashMap;
    }

    private List<ConfigDesc> getConfigDesc() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
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
                if(BaseConfig.class.isAssignableFrom(field.getType())){
                    configDescList.addAll(((BaseConfig)field.get(this)).getConfigDesc(field.getName()));
                }else {
                    configDescList.add(getConfigDesc(father, field, defaultConfig));
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
