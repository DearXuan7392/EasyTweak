package com.dearxuan.easytweak.mixin.Enchantment.Conflict;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

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
     */
    @Override
    public boolean canAccept(Enchantment other) {
        if (other instanceof ProtectionEnchantment protectionEnchantment) {
            return this.protectionType != protectionEnchantment.protectionType;
        } else {
            return super.canAccept(other);
        }
    }
}
