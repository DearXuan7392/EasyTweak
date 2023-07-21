package com.dearxuan.easytweak.mixin.BetterSpawner;

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
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(HostileEntity.class)
public abstract class HostileEntityMixin extends PathAwareEntity
        implements Monster {
    protected HostileEntityMixin(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @author DearXuan
     * @reason 刷怪笼生成怪物不再检测亮度
     */
    @Overwrite
    public static boolean canSpawnInDark(EntityType<? extends HostileEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        if(world.getDifficulty() == Difficulty.PEACEFUL){
            return false;
        }else {
            if(spawnReason == SpawnReason.SPAWNER){
                return true;
            }else{
                BlockPos blockPos = pos.down();
                return HostileEntity.isSpawnDark(world, pos, random) && world.getBlockState(blockPos).allowsSpawning(world, blockPos, type);
            }
        }
    }
}
