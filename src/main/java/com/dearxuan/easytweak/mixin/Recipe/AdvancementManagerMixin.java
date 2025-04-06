//package com.dearxuan.easytweak.mixin.Recipe;
//
//import com.dearxuan.easytweak.Config.ModMenu.ModInfo;
//import com.dearxuan.easytweak.Event.Recipe;
//import net.minecraft.advancement.Advancement;
//import net.minecraft.advancement.AdvancementManager;
//import net.minecraft.util.Identifier;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import java.util.Map;
//
//@Mixin(AdvancementManager.class)
//public abstract class AdvancementManagerMixin {
//
//    @Inject(
//            method = "load",
//            at = @At("HEAD")
//    )
//    private void putNewAdvancements(
//            Map<Identifier, Advancement.Builder> advancements,
//            CallbackInfo info){
//        advancements.putAll(Recipe.getAdvancementsMap());
//    }
//}
