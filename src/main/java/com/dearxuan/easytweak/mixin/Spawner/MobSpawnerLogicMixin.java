package com.dearxuan.easytweak.mixin.Spawner;

import com.dearxuan.easytweak.Config.ModConfig;
import com.dearxuan.easytweak.Interface.MobSpawnerInterface;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobSpawnerLogic.class)
public abstract class MobSpawnerLogicMixin implements MobSpawnerInterface {

    @Shadow
    private int requiredPlayerRange;

    @Shadow
    private int maxNearbyEntities;

    @Shadow
    private int minSpawnDelay;

    @Shadow
    private int maxSpawnDelay;

    @Shadow
    private int spawnCount = 4;

    @Shadow
    private int spawnRange;

    @Shadow
    private int spawnDelay = 20;

    @Shadow
    protected abstract void updateSpawns(World world, BlockPos pos);

    @Shadow
    protected abstract MobSpawnerEntry getSpawnEntry(@Nullable World world, Random random, BlockPos pos);

    @Shadow
    protected abstract boolean isPlayerInRange(World world, BlockPos pos);

    private boolean IsReceiveRedstonePower = false;

    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    private void OnInit(CallbackInfo info){
        this.requiredPlayerRange = ModConfig.INSTANCE.SPAWNER_PLAYER_RANGE;
        this.maxNearbyEntities = ModConfig.INSTANCE.SPAWNER_MAX_NEARBY;
        this.minSpawnDelay = ModConfig.INSTANCE.SPAWNER_MIN_DELAY;
        this.maxSpawnDelay = ModConfig.INSTANCE.SPAWNER_MAX_DELAY;
        this.spawnRange = ModConfig.INSTANCE.SPAWNER_RANGE / 2;
        this.spawnCount = ModConfig.INSTANCE.SPAWNER_COUNT;
    }

    @Override
    public void updateState(boolean isReceiveRedstonePower){
        this.IsReceiveRedstonePower = isReceiveRedstonePower;
    }

    @Inject(
            method = "isPlayerInRange",
            at = @At("HEAD"),
            cancellable = true
    )
    private void isPlayerInRangeOrPowered(World world, BlockPos pos, CallbackInfoReturnable<Boolean> info){
        if(this.IsReceiveRedstonePower){
            info.setReturnValue(true);
        }
    }
}
