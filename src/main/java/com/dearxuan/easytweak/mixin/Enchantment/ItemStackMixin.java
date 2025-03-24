package com.dearxuan.easytweak.mixin.Enchantment;

import com.dearxuan.easytweak.Config.ModMenu.ModInfo;
import net.fabricmc.fabric.api.item.v1.FabricItemStack;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements FabricItemStack {

    @Shadow
    public abstract boolean isDamageable();

    @Shadow public abstract int getDamage();

    @Shadow public abstract void setDamage(int damage);

    @Shadow public abstract int getMaxDamage();

    private int nextAdditionExperience = 1;

    @Inject(
            method = "damage(ILnet/minecraft/util/math/random/Random;Lnet/minecraft/server/network/ServerPlayerEntity;)Z",
            at = @At(value = "RETURN"),
            cancellable = true
    )
    private void Better_Mending(
            int amount,
            Random random,
            ServerPlayerEntity player,
            CallbackInfoReturnable<Boolean> info){
        if(!this.isDamageable()){
            return;
        }
        int damage = this.getDamage();
        if(damage > 0 && player != null && player.totalExperience > 0 && EnchantmentHelper.getLevel(Enchantments.MENDING, (ItemStack) (Object) this) > 0){
            // 修复所需的经验值
            int needExp = damage / 2;
            // 如果损耗值是奇数, 则两次中有一次需要将经验值加一, 以匹配原版规则
            if(damage % 2 == 1){
                needExp += nextAdditionExperience;
                nextAdditionExperience = 1 - nextAdditionExperience;
            }
            if(needExp < player.totalExperience){
                // 玩家拥有足够的经验
                this.setDamage(0);
                player.addExperience(-needExp);
            }else{
                // 玩家经验恰好足够或不足
                this.setDamage(damage - player.totalExperience * 2);
                player.setExperiencePoints(0);
            }
            info.setReturnValue(this.getDamage() >= this.getMaxDamage());
        }
    }
}
