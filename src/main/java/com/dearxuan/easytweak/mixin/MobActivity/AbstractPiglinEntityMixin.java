package com.dearxuan.easytweak.mixin.MobActivity;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractPiglinEntity.class)
public abstract class AbstractPiglinEntityMixin extends HostileEntity {
    protected AbstractPiglinEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @author DearXuan
     * @reason 移除猪灵僵尸化代码
     */
    @Inject(
            method = "shouldZombify",
            at = @At("HEAD"),
            cancellable = true
    )
    private void injectShouldZombify(CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(false);
        cir.cancel();
    }
}
