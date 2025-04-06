package com.dearxuan.easytweak.mixin.BetterSpawner.SpawnerEnchantment;

import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.SpawnerBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

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
     * 不再掉落经验
     * @param dropExperience
     * @return
     */
    @ModifyVariable(
            method = "onStacksDropped",
            at = @At("HEAD"),
            ordinal = 0
    )
    private boolean modifyDropExperience(boolean dropExperience){
        return false;
    }

//    @Override
//    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
//        if(!state.isOf(newState.getBlock())){
//            BlockEntity blockEntity = world.getBlockEntity(pos);
//            if(blockEntity instanceof MobSpawnerBlockEntity mobSpawnerBlockEntity){
//                MobSpawnerEvent.DropEgg(world, pos, mobSpawnerBlockEntity);
//            }
//        }
//        super.onStateReplaced(state, world, pos, newState, moved);
//    }

//    @Override
//    public void neighborUpdate(
//            BlockState state,
//            World world,
//            BlockPos pos,
//            Block sourceBlock,
//            BlockPos sourcePos,
//            boolean notify) {
//        BlockEntity blockEntity = world.getBlockEntity(pos);
//        if(blockEntity instanceof MobSpawnerInterface mobSpawnerInterface){
//            mobSpawnerInterface.updateState(world, pos);
//        }
//    }
}
