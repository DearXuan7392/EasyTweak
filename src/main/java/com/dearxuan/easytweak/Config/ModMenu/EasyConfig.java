package com.dearxuan.easytweak.Config.ModMenu;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface EasyConfig {

    /**
     * 需要加载的mixin类
     */
    String[] mixin() default {};

    /**
     * 需要加载的mixin包
     */
    String[] mixinPackage() default {};

    /**
     * 需要启用的其他配置项
     */
    String[] require() default {};

    String CommentKey() default "";
}