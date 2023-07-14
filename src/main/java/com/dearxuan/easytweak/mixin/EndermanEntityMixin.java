package com.dearxuan.easytweak.mixin;

import net.minecraft.entity.ai.goal.Goal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(targets = "net.minecraft.entity.mob.EndermanEntity$PickUpBlockGoal")
public abstract class EndermanEntityMixin extends Goal {

    /**
     * @author DearXuan
     * @reason 禁止末影人搬运方块
     */
    @Overwrite
    public boolean canStart(){
        return false;
    }
}
