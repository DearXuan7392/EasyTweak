package com.dearxuan.easytweak.mixin.GameRule;

import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(net.minecraft.block.NetherPortalBlock.class)
public class NetherPortalBlock {

    /**
     * @author DearXuan
     * @reason 禁止下界传送门产生僵尸猪人
     */
    @Overwrite
    public void randomTick(
            BlockState state,
            ServerWorld world,
            BlockPos pos,
            Random random){

    }
}
