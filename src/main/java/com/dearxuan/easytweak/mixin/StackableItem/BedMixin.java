package com.dearxuan.easytweak.mixin.StackableItem;

import com.dearxuan.easytweak.Config.ModConfig;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Items.class)
public class BedMixin {

    @ModifyArg(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/BedItem;<init>(Lnet/minecraft/block/Block;Lnet/minecraft/item/Item$Settings;)V",
                    ordinal = -1),
            index = 1
    )
    private static Item.Settings GetBedItem(Item.Settings settings){
        settings.maxCount(ModConfig.INSTANCE.StackableItem.Bed);
        return settings;
    }
}
