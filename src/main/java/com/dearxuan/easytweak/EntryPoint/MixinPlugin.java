package com.dearxuan.easytweak.EntryPoint;

import com.dearxuan.easytweak.Config.ModConfig;
import com.dearxuan.easytweak.Config.ModMenu.ConfigDesc;
import com.dearxuan.easytweak.Config.ModMenu.EasyConfig;
import com.dearxuan.easytweak.Config.ModMenu.ModInfo;
import com.dearxuan.easytweak.Config.ModMenu.ModSaver;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.lang.reflect.Field;
import java.util.*;

import static com.dearxuan.easytweak.Config.ModMenu.ModInfo.LOGGER;

public class MixinPlugin implements IMixinConfigPlugin {

    @Override
    public void onLoad(String mixinPackage) {
        ModInfo.Init("Easy Tweak", "easytweak", ModConfig.class);
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if(ModConfig.INSTANCE.shouldApply(mixinClassName)){
            LOGGER.debug("Mixin Apply: " + mixinClassName);
            return true;
        }
        return false;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
