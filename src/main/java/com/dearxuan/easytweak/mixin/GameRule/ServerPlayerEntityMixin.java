package com.dearxuan.easytweak.mixin.GameRule;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends LivingEntity {

    protected ServerPlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = "onDeath",
            at = @At("RETURN")
    )
    public void afterPlayerDeath(DamageSource damageSource, CallbackInfo info){
        if(damageSource.getAttacker() instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity)(Object)this;
            String name = player.getName().getString();
            ItemStack itemStack = new ItemStack(Items.PLAYER_HEAD, 1);
            NbtCompound nbtCompound = new NbtCompound();
            nbtCompound.putString("SkullOwner", name);
            itemStack.setNbt(nbtCompound);
            Optional<GlobalPos> _pos = player.getLastDeathPos();
            if(_pos.isPresent()){
                BlockPos pos = _pos.get().getPos();
                ItemScatterer.spawn(player.getWorld(), pos.getX(), pos.getY(), pos.getZ(), itemStack);
            }
        }
    }
}
