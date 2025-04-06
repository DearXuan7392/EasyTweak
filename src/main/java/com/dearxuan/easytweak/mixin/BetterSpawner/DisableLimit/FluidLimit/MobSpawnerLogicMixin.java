package com.dearxuan.easytweak.mixin.BetterSpawner.DisableLimit.FluidLimit;

import net.minecraft.block.spawner.MobSpawnerLogic;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MobSpawnerLogic.class)
public abstract class MobSpawnerLogicMixin {

    /**
     * 允许怪物在流体中生成
     * @param instance
     * @param world
     * @return
     */
    @Redirect(
            method = "serverTick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/MobEntity;canSpawn(Lnet/minecraft/world/WorldView;)Z")
    )
    private boolean RedirectMobEntityCanSpawn(MobEntity instance, WorldView world){
        return world.doesNotIntersectEntities(instance);
    }
}
