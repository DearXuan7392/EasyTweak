package com.dearxuan.easytweak.mixin.BetterSpawner.DisableLimit.LightLimit;

import net.minecraft.block.spawner.MobSpawnerLogic;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MobSpawnerLogic.class)
public abstract class MobSpawnerLogicMixin {

    /**
     * 待修改
     * @param instance
     * @param world
     * @param spawnReason
     * @return
     */
    @Redirect(
            method = "serverTick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/MobEntity;canSpawn(Lnet/minecraft/world/WorldAccess;Lnet/minecraft/entity/SpawnReason;)Z")
    )
    private boolean redirectMobEntityCanSpawn(MobEntity instance, WorldAccess world, SpawnReason spawnReason){
        return true;
    }
}
