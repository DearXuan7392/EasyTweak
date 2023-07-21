package com.dearxuan.easytweak.Config.ModMenu;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface EasyConfig {

    String[] mixin() default {};

    String CommentKey() default "";

    String[] mixinPackage() default {};
}