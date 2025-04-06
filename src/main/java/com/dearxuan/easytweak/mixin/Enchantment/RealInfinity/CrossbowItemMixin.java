package com.dearxuan.easytweak.mixin.Enchantment.RealInfinity;

import com.dearxuan.easytweak.Config.ModConfig;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CrossbowItem.class)
public abstract class CrossbowItemMixin extends RangedWeaponItem  implements Vanishable {

    public CrossbowItemMixin(Settings settings) {
        super(settings);
    }

    /**
     * 允许没有箭使用无限附魔
     * @param bl
     * @param shooter
     * @param crossbow
     * @return
     */
    @ModifyVariable(
            method = "loadProjectiles",
            at = @At("STORE"),
            ordinal = 0
    )
    private static boolean modifyLoadProjectiles(
            boolean bl,
            @Local(argsOnly = true) LivingEntity shooter,
            @Local(argsOnly = true, ordinal = 0) ItemStack crossbow) {
        return bl || EnchantmentHelper.getLevel(Enchantments.INFINITY, crossbow) > 0;
    }

    @Redirect(
            method = "use",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getProjectileType(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;")
    )
    private ItemStack use_getProjectileType(PlayerEntity instance, ItemStack stack){
        if(ModConfig.INSTANCE.Enchantment.Real_Infinity){
            ItemStack res = instance.getProjectileType(stack);
            if(res.isEmpty() && EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0){
                res = new ItemStack(Items.ARROW);
            }
            return res;
        }else{
            return instance.getProjectileType(stack);
        }
    }
}
