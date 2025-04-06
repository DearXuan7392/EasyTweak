package com.dearxuan.easytweak.mixin.BetterSpawner.CustomRules;

import com.dearxuan.easytweak.Config.ModConfig;
import net.minecraft.block.spawner.MobSpawnerLogic;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobSpawnerLogic.class)
public abstract class MobSpawnerLogicMixin {

    @Shadow
    private int requiredPlayerRange;

    @Shadow
    private int maxNearbyEntities;

    @Shadow
    private int minSpawnDelay;

    @Shadow
    private int maxSpawnDelay;

    @Shadow
    private int spawnCount = 4;

    @Shadow
    private int spawnRange;

    // 修改生成怪物高度, 源代码为 (double)(pos.getY() + random.nextInt(3) - 1)
    @Unique
    private int randomHeightNextInt;

    @Unique
    private int randomHeightMinusValue;

    /**
     * 在类构造时修改刷怪位置参数
     * @param info 回调信息
     */
    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    private void OnInit(CallbackInfo info) {
        updateNbt();
    }

    /**
     * 更新刷怪参数
     */
    @Unique
    private void updateNbt() {
        this.requiredPlayerRange = ModConfig.INSTANCE.BetterSpawner.Player_Range;
        this.maxNearbyEntities = ModConfig.INSTANCE.BetterSpawner.Max_Nearby;
        this.minSpawnDelay = ModConfig.INSTANCE.BetterSpawner.Min_Delay;
        this.maxSpawnDelay = ModConfig.INSTANCE.BetterSpawner.Max_Delay;
        this.spawnRange = ModConfig.INSTANCE.BetterSpawner.Spawner_Range / 2;
        this.spawnCount = ModConfig.INSTANCE.BetterSpawner.Spawner_Count;
        this.randomHeightNextInt = ModConfig.INSTANCE.BetterSpawner.Spawner_Height;
        if (this.randomHeightNextInt % 2 == 0 && this.randomHeightNextInt > 0) {
            this.randomHeightNextInt -= 1;
        }
        this.randomHeightMinusValue = this.randomHeightNextInt / 2;
    }

    /**
     * 在加载 NBT 时也需要更新参数, 以避免老刷怪笼仍然是老的参数
     * @param world
     * @param pos
     * @param nbt
     * @param info
     */
    @Inject(
            method = "readNbt",
            at = @At("RETURN")
    )
    public void readNbt(@Nullable World world,
                        BlockPos pos,
                        NbtCompound nbt,
                        CallbackInfo info) {
        updateNbt();
    }

    /**
     * 修改生成怪物高度
     */
    // 源代码: (double)(pos.getY() + random.nextInt(3) - 1)
    // 修改为: (double)(pos.getY() + random.nextInt(randomHeightNextInt) - randomHeightMinusValue)
    @ModifyConstant(
            method = "serverTick",
            constant = @Constant(intValue = 1, ordinal = 3)
    )
    private int getRandomHeightPlusValue(int constant) {
        return this.randomHeightMinusValue;
    }

    /**
     * 修改生成怪物高度
     */
    @ModifyConstant(
            method = "serverTick",
            constant = @Constant(intValue = 3, ordinal = 0)
    )
    private int getRandomHeightNextInt(int constant) {
        return this.randomHeightNextInt;
    }
}
