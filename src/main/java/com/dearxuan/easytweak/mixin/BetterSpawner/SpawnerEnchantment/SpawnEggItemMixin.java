package com.dearxuan.easytweak.mixin.BetterSpawner.SpawnerEnchantment;

import com.dearxuan.easytweak.Event.MobSpawnerEvent;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.block.entity.Spawner;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(SpawnEggItem.class)
public abstract class SpawnEggItemMixin extends Item {

    public SpawnEggItemMixin(Settings settings) {
        super(settings);
    }

    /**
     * 对刷怪笼使用怪物蛋, 丢下原本的怪物蛋
     */
    @Inject(
            method = "useOnBlock",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/Spawner;setEntityType(Lnet/minecraft/entity/EntityType;Lnet/minecraft/util/math/random/Random;)V")
    )
    private void injectUseOnBlock(
            ItemUsageContext context,
            CallbackInfoReturnable<ActionResult> cir,
            @Local(ordinal = 0) World world,
            @Local(ordinal = 0) BlockPos blockPos){
        MobSpawnerEvent.DropEgg(world, blockPos);
    }
}
