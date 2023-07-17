package com.dearxuan.easytweak.Config.ModMenu;

import java.lang.reflect.Field;

public class ConfigDesc<T> {

    /**
     * 配置名称
     */
    public final String Name;

    /**
     * 配置值
     */
    public final T Value;

    /**
     * 默认值
     */
    public final T DefaultValue;

    /**
     * 对应的 Mixin 类名称
     */
    public final String[] MixinClassName;

    /**
     * 对应的 Mixin 包名, 覆盖此文件夹下全部类
     */
    public final String[] MixinPackage;

    /**
     * 配置项对应的注释 key
     */
    public final String CommentKey;

    private final static String[] EMPTY = new String[]{};

    public ConfigDesc(String father, Field field,BaseConfig config, BaseConfig defaultConfig) throws IllegalAccessException {
        if(father == null || father.isBlank()){
            this.Name = field.getName();
        }else{
            this.Name = father + "." + field.getName();
        }
        this.Value = (T) field.get(config);
        this.DefaultValue = (T) field.get(defaultConfig);
        EasyConfig easyConfig = field.getAnnotation(EasyConfig.class);
        if(easyConfig == null){
            this.MixinClassName = EMPTY;
            this.MixinPackage = EMPTY;
            this.CommentKey = "";
        }else{
            this.MixinClassName = easyConfig.mixin();
            this.MixinPackage = easyConfig.mixinPackage();
            this.CommentKey = easyConfig.CommentKey();
        }

    }
}
