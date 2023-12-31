package com.dearxuan.easytweak.mixin.StackableItem;

import com.dearxuan.easytweak.Config.ModConfig;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Items.class)
public abstract class PotionMixin {

    @ModifyArg(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/PotionItem;<init>(Lnet/minecraft/item/Item$Settings;)V",
                    ordinal = 0),
            index = 0
    )
    private static Item.Settings GetPotionSetting(Item.Settings settings){
        return settings.maxCount(ModConfig.INSTANCE.StackableItem.Potions);
    }

    @ModifyArg(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/SplashPotionItem;<init>(Lnet/minecraft/item/Item$Settings;)V",
                    ordinal = 0),
            index = 0
    )
    private static Item.Settings GetSplashPotionSetting(Item.Settings settings){
        return settings.maxCount(ModConfig.INSTANCE.StackableItem.Potions);
    }

    @ModifyArg(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/LingeringPotionItem;<init>(Lnet/minecraft/item/Item$Settings;)V",
                    ordinal = 0),
            index = 0
    )
    private static Item.Settings GetLingeringPotionSetting(Item.Settings settings){
        return settings.maxCount(ModConfig.INSTANCE.StackableItem.Potions);
    }
}
