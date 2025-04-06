package com.dearxuan.easytweak.mixin.BetterSpawner.SpawnerEnchantment;

import net.minecraft.block.spawner.MobSpawnerLogic;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobSpawnerLogic.class)
public abstract class MobSpawnerLogicMixin {

    /**
     * 是否需要更新刷怪笼状态. 仅在第一次 tick 时修改, 判断是否有红石充能
     */
    @Unique
    private boolean NeedUpdateBlockState = true;

    /**
     * 是否接收到红石信号
     */
    @Unique
    private boolean IsReceivingRedstonePower = false;

    /**
     * 是否启用该刷怪笼
     */
    @Unique
    private boolean Enable = false;

    @Inject(
            method = "serverTick",
            at = @At("HEAD"),
            cancellable = true
    )
    private void injectServerTick(ServerWorld world, BlockPos pos, CallbackInfo info){
        if (this.NeedUpdateBlockState){
            this.IsReceivingRedstonePower = world.isReceivingRedstonePower(pos);
            this.NeedUpdateBlockState = false;
        }
    }
}
