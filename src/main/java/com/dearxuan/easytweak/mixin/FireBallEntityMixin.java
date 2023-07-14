package com.dearxuan.easytweak.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FireballEntity.class)
public abstract class FireBallEntityMixin extends AbstractFireballEntity {

    @Shadow
    private int explosionPower;

    public FireBallEntityMixin(EntityType<? extends AbstractFireballEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @author DearXuan
     * @reason 禁止恶魂的火球破坏方块
     */
    @Overwrite
    public void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.getWorld().createExplosion(
                    this,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    (float)this.explosionPower,
                    false,
                    World.ExplosionSourceType.NONE);
            this.discard();
        }
    }
}
