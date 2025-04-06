package com.dearxuan.easytweak.mixin.GameRule;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.village.VillagerDataContainer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ZombieVillagerEntity.class)
public abstract class ZombieVillagerEntityMixin extends ZombieEntity implements VillagerDataContainer {
    public ZombieVillagerEntityMixin(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * 加速僵尸村民转换
     * @param original
     * @return
     */
    @ModifyConstant(
            method = "interactMob",
            constant = @Constant(intValue = 2401)
    )
    private int GetNewConstant_2401(int original){
        return 121;
    }

    @ModifyConstant(
            method = "interactMob",
            constant = @Constant(intValue = 3600)
    )
    private int GetNewConstant_3600(int original){
        return 80;
    }
}
