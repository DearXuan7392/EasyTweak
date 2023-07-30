package com.dearxuan.easytweak.mixin.Enchantment.BetterCrossbow;

import com.dearxuan.easytweak.Config.ModConfig;
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

    @ModifyVariable(
            method = "loadProjectiles",
            at = @At("STORE"),
            ordinal = 0
    )
    private static boolean loadProjectiles_bl(boolean bl, LivingEntity shooter, ItemStack crossbow) {
        return bl || EnchantmentHelper.getLevel(Enchantments.INFINITY, crossbow) > 0;
    }

    @ModifyVariable(
            method = "shoot",
            at = @At("TAIL"),
            ordinal = 0
    )
    private static ProjectileEntity setEnchantmentDamage(
            ProjectileEntity projectileEntity,
            World world,
            LivingEntity shooter,
            Hand hand,
            ItemStack crossbow,
            ItemStack projectile,
            float soundPitch,
            boolean creative,
            float speed,
            float divergence,
            float simulated){
        if(!projectile.isOf(Items.FIREWORK_ROCKET)){
            PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) projectileEntity;
            // 计算附魔伤害
            int k, j;
            if ((j = EnchantmentHelper.getLevel(Enchantments.POWER, crossbow)) > 0) {
                persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + (double)j * 0.5 + 0.5);
            }
            if ((k = EnchantmentHelper.getLevel(Enchantments.PUNCH, crossbow)) > 0) {
                persistentProjectileEntity.setPunch(k);
            }
            if (EnchantmentHelper.getLevel(Enchantments.FLAME, crossbow) > 0) {
                persistentProjectileEntity.setOnFireFor(100);
            }
            if(persistentProjectileEntity.pickupType != PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY && EnchantmentHelper.getLevel(Enchantments.INFINITY, crossbow) > 0){
                persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
            }
        }
        return projectileEntity;
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
