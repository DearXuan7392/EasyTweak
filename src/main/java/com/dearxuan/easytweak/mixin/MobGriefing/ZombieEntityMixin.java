package com.dearxuan.easytweak.mixin.MobGriefing;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ZombieEntity.class)
public abstract class ZombieEntityMixin extends HostileEntity {
    protected ZombieEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * 僵尸不再破门
     * @param info
     */
    @Inject(
            method = "canBreakDoors",
            at = @At("RETURN"),
            cancellable = true
    )
    private void canBreakDoor(CallbackInfoReturnable<Boolean> info){
        info.setReturnValue(false);
        info.cancel();
    }
}
