package com.dearxuan.easytweak.Interface;


import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface MobSpawnerInterface {

    default void updateState(World world, BlockPos pos){

    }

    default void updateState(boolean isReceiveRedstonePower){

    }

    default void checkStateIfNeed(World world, BlockPos pos){

    }
}
