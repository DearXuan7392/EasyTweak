package com.dearxuan.easytweak.EntryPoint;

import com.dearxuan.easytweak.Config.ModConfig;
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

    private final HashSet<String> mixinPackageSet = new HashSet<>();

    @Override
    public void onLoad(String mixinPackage) {
        try{
            ModInfo.Init("Easy Tweak", "easytweak", ModConfig.class);
            for(Field field : ModInfo.ConfigClass.getFields()){
                if(field.getType() == boolean.class){
                    EasyConfig easyConfig = field.getAnnotation(EasyConfig.class);
                    if(easyConfig != null && easyConfig.mixin().length != 0 && (boolean)ModSaver.DefaultValue.get(field.getName()) != field.getBoolean(ModConfig.INSTANCE)){
                        // 添加需要加载的mixin类名
                        mixinPackageSet.addAll(Arrays.asList(easyConfig.mixin()));

                    }
                }
            }
        }catch (Exception e){
            LOGGER.error(e);
        }

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        String[] mixinNames = mixinClassName.split("\\.");
        if(mixinPackageSet.contains(mixinNames[mixinNames.length - 1])){
            if(Main.DEBUG){
                LOGGER.info("Mixin is loaded : " + mixinClassName);
            }
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
