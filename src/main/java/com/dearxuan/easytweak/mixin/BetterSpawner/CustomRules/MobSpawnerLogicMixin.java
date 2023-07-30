package com.dearxuan.easytweak.mixin.BetterSpawner.CustomRules;

import com.dearxuan.easytweak.Config.ModConfig;
import com.dearxuan.easytweak.Interface.MobSpawnerInterface;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.MobSpawnerLogic;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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

    private int _RandomHeightNextInt;

    private int _RandomHeightPlusValue;

    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    private void OnInit(CallbackInfo info){
        updateNbt();
    }

    @Inject(
            method = "readNbt",
            at = @At("RETURN")
    )
    public void readNbt(@Nullable World world, BlockPos pos, NbtCompound nbt, CallbackInfo info){
        updateNbt();
    }

    private void updateNbt(){
        this.requiredPlayerRange = ModConfig.INSTANCE.BetterSpawner.Player_Range;
        this.maxNearbyEntities = ModConfig.INSTANCE.BetterSpawner.Max_Nearby;
        this.minSpawnDelay = ModConfig.INSTANCE.BetterSpawner.Min_Delay;
        this.maxSpawnDelay = ModConfig.INSTANCE.BetterSpawner.Max_Delay;
        this.spawnRange = ModConfig.INSTANCE.BetterSpawner.Spawner_Range / 2;
        this.spawnCount = ModConfig.INSTANCE.BetterSpawner.Spawner_Count;
        this._RandomHeightNextInt = ModConfig.INSTANCE.BetterSpawner.Spawner_Height;
        if(this._RandomHeightNextInt % 2 == 0 && this._RandomHeightNextInt > 0){
            this._RandomHeightNextInt -= 1;
        }
        this._RandomHeightPlusValue = this._RandomHeightNextInt / 2;
    }

    @ModifyConstant(
            method = "serverTick",
            constant = @Constant(intValue = 1, ordinal = 3)
    )
    private int getRandomHeightPlusValue(int constant){
        return this._RandomHeightPlusValue;
    }

    @ModifyConstant(
            method = "serverTick",
            constant = @Constant(intValue = 3, ordinal = 0)
    )
    private int getRandomHeightNextInt(int constant){
        return this._RandomHeightNextInt;
    }
}
