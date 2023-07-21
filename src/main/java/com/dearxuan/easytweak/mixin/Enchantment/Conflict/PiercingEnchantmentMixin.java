package com.dearxuan.easytweak.mixin.Enchantment.Conflict;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.PiercingEnchantment;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;

/**
 * 穿刺
 */
@Mixin(PiercingEnchantment.class)
public class PiercingEnchantmentMixin extends Enchantment {

    public PiercingEnchantmentMixin(Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.CROSSBOW, slotTypes);
    }

    /**
     * 穿透 与 多重射击 不再冲突
     */
    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
