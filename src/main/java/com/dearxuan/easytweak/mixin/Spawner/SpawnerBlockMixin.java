package com.dearxuan.easytweak.mixin.Spawner;

import com.dearxuan.easytweak.Event.MobSpawnerEvent;
import com.dearxuan.easytweak.Interface.MobSpawnerInterface;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.SpawnerBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.function.BooleanSupplier;

@Mixin(SpawnerBlock.class)
public abstract class SpawnerBlockMixin extends BlockWithEntity {

    /**
     * 刷怪笼是否被封印
     */
    private static BooleanSupplier ENABLE;

    /**
     * 刷怪笼是否收到红石信号
     */
    private static BooleanSupplier REDSTONEPOWER;

    protected SpawnerBlockMixin(Settings settings) {
        super(settings);
    }

    /**
     * @author DearXuan
     * @reason 不再掉落经验
     */
    @Overwrite
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience) {
        super.onStacksDropped(state, world, pos, tool, false);

    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if(!state.isOf(newState.getBlock())){
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if(blockEntity instanceof MobSpawnerBlockEntity mobSpawnerBlockEntity){
                MobSpawnerEvent.DropEgg(world, pos, mobSpawnerBlockEntity);
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    /**
     * @author DearXuan
     * @reason 创建实体时, 更新实体状态
     */
    @Overwrite
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MobSpawnerBlockEntity(pos, state);
    }

    @Override
    public void neighborUpdate(
            BlockState state,
            World world,
            BlockPos pos,
            Block sourceBlock,
            BlockPos sourcePos,
            boolean notify) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if(blockEntity instanceof MobSpawnerInterface mobSpawnerInterface){
            mobSpawnerInterface.updateState(world, pos);
        }
    }
}
