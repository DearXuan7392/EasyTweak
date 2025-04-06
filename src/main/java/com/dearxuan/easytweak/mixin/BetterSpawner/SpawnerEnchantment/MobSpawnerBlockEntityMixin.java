//package com.dearxuan.easytweak.mixin.BetterSpawner.SpawnerEnchantment;
//
//import com.dearxuan.easytweak.Interface.MobSpawnerInterface;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.Blocks;
//import net.minecraft.block.entity.BlockEntity;
//import net.minecraft.block.entity.BlockEntityType;
//import net.minecraft.block.entity.MobSpawnerBlockEntity;
//import net.minecraft.block.spawner.MobSpawnerLogic;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.Unique;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(MobSpawnerBlockEntity.class)
//public abstract class MobSpawnerBlockEntityMixin extends BlockEntity implements MobSpawnerInterface {
//
//    @Unique
//    private boolean Enable = true;
//
//    @Unique
//    private boolean needUpdate = true;
//
//    @Shadow
//    @Final
//    private MobSpawnerLogic logic;
//
//    public MobSpawnerBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
//        super(type, pos, state);
//    }
//
//    @Inject(
//            method = "serverTick",
//            at = @At("HEAD"),
//            cancellable = true
//    )
//    private static void onServerTick(
//            World world,
//            BlockPos pos,
//            BlockState state,
//            MobSpawnerBlockEntity blockEntity,
//            CallbackInfo info){
//        MobSpawnerBlockEntityMixin mobSpawner = (MobSpawnerBlockEntityMixin) world.getBlockEntity(pos);
//        mobSpawner.checkStateIfNeed(world, pos);
//        if (!mobSpawner.Enable) {
//            info.cancel();
//        }
//    }
//
//    @Override
//    public void updateState(World world, BlockPos pos){
//        // 是否收到红石信号
//        ((MobSpawnerInterface)this.logic).updateState(world.isReceivingRedstonePower(pos));
//        Block block = world.getBlockState(pos.up()).getBlock();
//        // 上方是否为火把或灵魂火把
//        this.Enable = !(block == Blocks.TORCH || block == Blocks.SOUL_TORCH);
//    }
//
//    @Override
//    public void checkStateIfNeed(World world, BlockPos pos){
//        if(needUpdate){
//            this.updateState(world, pos);
//            needUpdate = false;
//        }
//    }
//}
