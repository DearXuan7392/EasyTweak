package com.dearxuan.easytweak.mixin.Enchantment.Conflict;

import net.minecraft.enchantment.*;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 多重射击
 */
@Mixin(MultishotEnchantment.class)
public class MultishotEnchantmentMixin extends Enchantment {

    public MultishotEnchantmentMixin(Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.CROSSBOW, slotTypes);
    }

    /**
     * 多重射击 与 穿透 不再冲突
     * @param other
     * @param cir
     */
    @Inject(
            method = "canAccept",
            at = @At("HEAD"),
            cancellable = true
    )
    private void injectCanAccept(Enchantment other, CallbackInfoReturnable<Boolean> cir){
        if (other == Enchantments.PIERCING){
            cir.setReturnValue(super.canAccept(other));
            cir.cancel();
        }
    }
}
