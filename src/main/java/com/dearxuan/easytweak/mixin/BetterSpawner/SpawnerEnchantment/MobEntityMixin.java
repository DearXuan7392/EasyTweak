package com.dearxuan.easytweak.mixin.BetterSpawner.SpawnerEnchantment;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Targeter;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity implements Targeter {
    protected MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * 随机掉落生物蛋
     * @param damageSource
     * @param causedByPlayer
     * @param info
     */
    @Inject(
            method = "dropLoot",
            at = @At("HEAD")
    )
    private void dropLoot(DamageSource damageSource, boolean causedByPlayer, CallbackInfo info){
        // 仅在玩家击杀时触发
        if(damageSource.getAttacker() instanceof PlayerEntity player){
            // 获取抢夺附魔等级
            int level = EnchantmentHelper.getLevel(Enchantments.LOOTING, player.getMainHandStack());
            // 随机生成 0 ~ 99 的整数
            int random = new Random().nextInt(100);
            if(level == 0){
                level = 1;
            }else{
                level *= 3;
            }
            // 按照一定的概率掉落生物蛋
            // 附魔等级: 0, 1, 2, 3. 掉落概率: 1%, 3%, 6%, 9%
            if(random < level){
                MobEntity mobEntity = (MobEntity) (Object) this;
                ServerWorld world = (ServerWorld) mobEntity.getEntityWorld();
                ItemStack egg = new ItemStack(Registries.ITEM.get(new Identifier(EntityType.getId(mobEntity.getType()).toString() + "_spawn_egg")));
                world.spawnEntity(new ItemEntity(world, mobEntity.prevX, mobEntity.prevY, mobEntity.prevZ, egg));
            }
        }
    }
}
