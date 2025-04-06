package com.dearxuan.easytweak.mixin.Enchantment.Conflict;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.RiptideEnchantment;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 激流
 */
@Mixin(RiptideEnchantment.class)
public class RiptideEnchantmentMixin extends Enchantment {

    public RiptideEnchantmentMixin(Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.TRIDENT, slotTypes);
    }

    @Inject(
            method = "canAccept",
            at = @At("HEAD"),
            cancellable = true
    )
    private void injectCanAccept(Enchantment other, CallbackInfoReturnable<Boolean> cir){
        if (other == Enchantments.LOYALTY || other == Enchantments.CHANNELING){
            cir.setReturnValue(super.canAccept(other));
            cir.cancel();
        }
    }
}
