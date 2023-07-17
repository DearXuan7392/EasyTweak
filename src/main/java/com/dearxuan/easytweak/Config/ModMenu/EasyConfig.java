package com.dearxuan.easytweak.Config.ModMenu;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface EasyConfig {

    String min() default "0";

    String max() default "256";

    String[] mixin() default {};

    String CommentKey() default "";

    String[] mixinPackage() default {};
}