package com.dearxuan.easytweak.mixin.GameRule;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FarmlandBlock.class)
public abstract class FarmlandBlockMixin extends Block {

    public FarmlandBlockMixin(Settings settings) {
        super(settings);
    }

    /**
     * 不再毁坏农田
     * @param world
     * @param state
     * @param pos
     * @param entity
     * @param fallDistance
     * @param ci
     */
    @Inject(
            method = "onLandedUpon",
            at = @At("HEAD"),
            cancellable = true
    )
    private void injectOnLandUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance, CallbackInfo ci){
        ci.cancel();
    }
}
