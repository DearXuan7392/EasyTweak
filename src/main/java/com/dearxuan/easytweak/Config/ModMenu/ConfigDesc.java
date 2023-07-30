package com.dearxuan.easytweak.Config.ModMenu;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ConfigDesc<T> {

    /**
     * 配置名称(仅子配置名,可能重复)
     */
    public final String Name;

    /**
     * 配置名称(父配置名.子配置名)
     */
    public final String Fullname;

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
     * 依赖于其他配置项
     */
    public final String[] require;

    /**
     * 配置项对应的注释 key
     */
    public final String CommentKey;

    private final String[] patterns;

    public ConfigDesc(String father, Field field,BaseConfig config, BaseConfig defaultConfig) throws IllegalAccessException {
        this.Name = field.getName();
        if(father == null || father.isBlank()){
            this.Fullname = this.Name;
        }else{
            this.Fullname = father + "." + this.Name;
        }
        this.Value = (T) field.get(config);
        this.DefaultValue = (T) field.get(defaultConfig);
        EasyConfig easyConfig = field.getAnnotation(EasyConfig.class);
        if(easyConfig == null){
            this.MixinClassName = Support.StringsEmpty;
            this.MixinPackage = Support.StringsEmpty;
            this.CommentKey = StringUtils.EMPTY;
            this.require = Support.StringsEmpty;
        }else{
            this.MixinClassName = easyConfig.mixin();
            this.MixinPackage = easyConfig.mixinPackage();
            this.CommentKey = easyConfig.CommentKey();
            this.require = easyConfig.require();
        }

        List<String> patterns = new ArrayList<>();
        for(String mixinClass : this.MixinClassName){
            // 匹配 com.dearxuan.modid.mixin*.mixinClass
            patterns.add(ModInfo.PackageName + ".mixin*" + mixinClass);
        }
        for(String mixinPackage : this.MixinPackage){
            // 匹配 com.dearxuan.modid.mixin.mixinPackage.*
            patterns.add(ModInfo.PackageName + ".mixin." + mixinPackage + ".*");
        }
        this.patterns = patterns.toArray(new String[0]);
    }

    public boolean canApply(String mixinClass){
        if(this.DefaultValue.equals(this.Value)){
            return false;
        }
        for(String pattern : this.patterns){
            if(Support.isMatch(mixinClass, pattern)){
                return true;
            }
        }
        return false;
    }


}
