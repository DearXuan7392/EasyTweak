package com.dearxuan.easytweak.mixin.BetterSpawner.DisableLimit.LightLimit;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HostileEntity.class)
public abstract class HostileEntityMixin extends PathAwareEntity
        implements Monster {
    @Shadow
    public static boolean isSpawnDark(ServerWorldAccess world, BlockPos pos, Random random) {
        return false;
    }

    protected HostileEntityMixin(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * 允许怪物在黑暗中生成
     */
    @Redirect(
            method = "canSpawnInDark",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/HostileEntity;isSpawnDark(Lnet/minecraft/world/ServerWorldAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/random/Random;)Z")
    )
    private static boolean RedirectIsSpawnDark(
            ServerWorldAccess world,
            BlockPos pos,
            Random random,
            @Local(argsOnly = true) SpawnReason spawnReason){
        if (spawnReason == SpawnReason.SPAWNER){
            return true;
        } else {
            return isSpawnDark(world, pos, random);
        }
    }
}
