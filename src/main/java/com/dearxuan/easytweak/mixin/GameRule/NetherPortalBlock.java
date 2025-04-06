package com.dearxuan.easytweak.mixin.GameRule;

import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.block.NetherPortalBlock.class)
public class NetherPortalBlock {

    /**
     * 移除地狱门的随机刻, 不再刷猪人
     * @param state
     * @param world
     * @param pos
     * @param random
     * @param info
     */
    @Inject(
            method = "randomTick",
            at = @At("HEAD"),
            cancellable = true
    )
    public void randomTick(
            BlockState state,
            ServerWorld world,
            BlockPos pos,
            Random random,
            CallbackInfo info){
        info.cancel();
    }
}
