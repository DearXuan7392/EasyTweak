package com.dearxuan.easytweak.mixin.Enchantment.Conflict;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.InfinityEnchantment;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 无限
 */
@Mixin(InfinityEnchantment.class)
public class InfinityEnchantmentMixin extends Enchantment{

    public InfinityEnchantmentMixin(Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.BOW, slotTypes);

    }

    /**
     * 允许与经验修补同时使用
     * @param other
     * @param cir
     */
    @Inject(
            method = "canAccept",
            at = @At("HEAD"),
            cancellable = true
    )
    private void injectCanAccept(Enchantment other, CallbackInfoReturnable<Boolean> cir){
        if (other instanceof MendingEnchantment){
            cir.setReturnValue(super.canAccept(other));
            cir.cancel();
        }
    }
}
