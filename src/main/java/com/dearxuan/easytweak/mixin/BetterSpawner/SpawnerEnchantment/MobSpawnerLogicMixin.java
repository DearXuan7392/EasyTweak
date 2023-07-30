package com.dearxuan.easytweak.mixin.BetterSpawner.SpawnerEnchantment;

import com.dearxuan.easytweak.Interface.MobSpawnerInterface;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.MobSpawnerLogic;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MobSpawnerLogic.class)
public abstract class MobSpawnerLogicMixin implements MobSpawnerInterface {
    @Shadow
    protected abstract boolean isPlayerInRange(World world, BlockPos pos);

    private boolean IsReceiveRedstonePower = false;

    @Override
    public void updateState(boolean isReceiveRedstonePower){
        this.IsReceiveRedstonePower = isReceiveRedstonePower;
    }

    @Redirect(
            method = "serverTick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/MobSpawnerLogic;isPlayerInRange(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)Z")
    )
    private boolean isPlayerInRangeOrPowered(
            MobSpawnerLogic instance,
            World world,
            BlockPos pos){
        return this.IsReceiveRedstonePower || this.isPlayerInRange(world, pos);
    }
}
