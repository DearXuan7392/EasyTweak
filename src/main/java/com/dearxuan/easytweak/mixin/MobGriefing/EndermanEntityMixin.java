package com.dearxuan.easytweak.mixin.MobGriefing;

import net.minecraft.entity.ai.goal.Goal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.entity.mob.EndermanEntity$PickUpBlockGoal")
public abstract class EndermanEntityMixin extends Goal {

    /**
     * 末影人不再搬运方块
     * @param info
     */
    @Inject(
            method = "canStart",
            at = @At("HEAD"),
            cancellable = true
    )
    public void canStart(CallbackInfoReturnable<Boolean> info){
        info.setReturnValue(false);
        info.cancel();
    }
}
