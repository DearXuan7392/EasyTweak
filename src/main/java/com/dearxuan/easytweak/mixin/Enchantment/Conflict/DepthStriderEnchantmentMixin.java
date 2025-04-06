package com.dearxuan.easytweak.mixin.Enchantment.Conflict;

import net.minecraft.enchantment.DepthStriderEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 深海探索者
 */
@Mixin(DepthStriderEnchantment.class)
public class DepthStriderEnchantmentMixin extends Enchantment{

    public DepthStriderEnchantmentMixin(Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.ARMOR_FEET, slotTypes);
    }

    /**
     * 深海探索者 与 冰霜行者 不再冲突
     * @param other
     * @param cir
     */
    @Inject(
            method = "canAccept",
            at = @At("HEAD"),
            cancellable = true
    )
    private void injectCanAccept(Enchantment other, CallbackInfoReturnable<Boolean> cir){
        if (other == Enchantments.FROST_WALKER){
            cir.setReturnValue(super.canAccept(other));
            cir.cancel();
        }
    }
}