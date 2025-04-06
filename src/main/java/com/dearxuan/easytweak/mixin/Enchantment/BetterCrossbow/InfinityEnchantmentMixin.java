package com.dearxuan.easytweak.mixin.Enchantment.BetterCrossbow;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.InfinityEnchantment;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

    @Override
    public boolean isAcceptableItem(ItemStack stack){
        Item item = stack.getItem();
        return item instanceof BowItem || item instanceof CrossbowItem;
    }
}
