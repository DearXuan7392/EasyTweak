package com.dearxuan.easytweak.mixin.Enchantment.Conflict;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.InfinityEnchantment;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;

/**
 * 无限
 */
@Mixin(InfinityEnchantment.class)
public class InfinityEnchantmentMixin extends Enchantment{

    public InfinityEnchantmentMixin(Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.BOW, slotTypes);

    }

    /**
     * 无限 与 经验修补 不再冲突
     */
    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
