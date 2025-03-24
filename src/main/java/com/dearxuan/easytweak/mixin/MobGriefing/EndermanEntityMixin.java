package com.dearxuan.easytweak.mixin.MobGriefing;

import net.minecraft.entity.ai.goal.Goal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.entity.mob.EndermanEntity$PickUpBlockGoal")
public abstract class EndermanEntityMixin extends Goal {

    @Inject(
            method = "canStart",
            at = @At("HEAD"),
            cancellable = true
    )
    public void canStart(CallbackInfoReturnable<Boolean> info){
        info.setReturnValue(false);
    }
}
