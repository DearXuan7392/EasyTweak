package com.dearxuan.easytweak.mixin.Enchantment.RealInfinity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BowItem.class)
public abstract class BowItemMixin extends RangedWeaponItem implements Vanishable{

    public BowItemMixin(Settings settings) {
        super(settings);
    }

    @Redirect(
            method = "onStoppedUsing",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z")
    )
    private boolean isOfArrow(ItemStack instance, Item item){
        return true;
    }

    @Redirect(
            method = "use",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getProjectileType(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;")
    )
    private ItemStack use_getProjectileType(PlayerEntity instance, ItemStack stack){
        ItemStack res = instance.getProjectileType(stack);
        if(res.isEmpty() && EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0){
            res = new ItemStack(Items.ARROW);
        }
        return res;
    }
}
