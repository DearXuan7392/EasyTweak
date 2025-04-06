package com.dearxuan.easytweak.mixin.Enchantment.Conflict;

import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 锋利, 亡灵杀手, 节肢杀手
 */
@Mixin(DamageEnchantment.class)
public class DamageEnchantmentMixin extends Enchantment{

    @Shadow @Final public int typeIndex;

    protected DamageEnchantmentMixin(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(weight, target, slotTypes);
    }

    /**
     * 锋利, 亡灵杀手, 节肢杀手 不再冲突
     * @param other
     * @param cir
     */
    @Inject(
            method = "canAccept",
            at = @At("HEAD"),
            cancellable = true
    )
    private void injectCanAccept(Enchantment other, CallbackInfoReturnable<Boolean> cir){
        if (other instanceof DamageEnchantment damageEnchantment){
            cir.setReturnValue(damageEnchantment.typeIndex != typeIndex);
        } else {
            cir.setReturnValue(true);
        }
        cir.cancel();
    }
}
