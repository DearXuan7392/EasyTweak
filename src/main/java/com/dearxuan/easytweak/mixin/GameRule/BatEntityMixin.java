package com.dearxuan.easytweak.mixin.GameRule;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BatEntity.class)
public abstract class BatEntityMixin extends AmbientEntity {
    protected BatEntityMixin(EntityType<? extends AmbientEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = "canSpawn",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void disableBatSpawn(
            EntityType<BatEntity> type,
            WorldAccess world,
            SpawnReason spawnReason,
            BlockPos pos,
            Random random,
            CallbackInfoReturnable<Boolean> info){
        info.setReturnValue(false);
    }
}
