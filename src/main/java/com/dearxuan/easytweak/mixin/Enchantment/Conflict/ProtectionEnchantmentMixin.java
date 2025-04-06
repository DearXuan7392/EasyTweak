package com.dearxuan.easytweak.mixin.Enchantment.Conflict;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 保护
 */
@Mixin(ProtectionEnchantment.class)
public class ProtectionEnchantmentMixin extends Enchantment {

    @Mutable
    @Shadow @Final public ProtectionEnchantment.Type protectionType;

    public ProtectionEnchantmentMixin(Rarity weight, ProtectionEnchantment.Type protectionType, EquipmentSlot... slotTypes) {
        super(weight, protectionType == ProtectionEnchantment.Type.FALL ? EnchantmentTarget.ARMOR_FEET : EnchantmentTarget.ARMOR, slotTypes);
        this.protectionType = protectionType;
    }

    /**
     * 保护类附魔不再冲突
     * @param other
     * @param cir
     */
    @Inject(
            method = "canAccept",
            at = @At("HEAD"),
            cancellable = true
    )
    private void injectCanAccept(Enchantment other, CallbackInfoReturnable cir){
        if (other instanceof ProtectionEnchantment protectionEnchantment){
            cir.setReturnValue(this.protectionType != protectionEnchantment.protectionType);
            cir.cancel();
        }
    }
}
