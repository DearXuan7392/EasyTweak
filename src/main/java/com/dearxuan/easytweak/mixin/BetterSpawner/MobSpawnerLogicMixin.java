package com.dearxuan.easytweak.mixin.BetterSpawner;

import com.dearxuan.easytweak.Config.ModConfig;
import com.dearxuan.easytweak.Interface.MobSpawnerInterface;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(MobSpawnerLogic.class)
public abstract class MobSpawnerLogicMixin implements MobSpawnerInterface {

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

    @Shadow
    private int spawnDelay = 20;

    @Shadow
    protected abstract void updateSpawns(World world, BlockPos pos);

    @Shadow
    protected abstract MobSpawnerEntry getSpawnEntry(@Nullable World world, Random random, BlockPos pos);

    @Shadow
    protected abstract boolean isPlayerInRange(World world, BlockPos pos);

    @Shadow @Final private static Logger LOGGER;
    private boolean IsReceiveRedstonePower = false;

    private int _RandomHeightNextInt;

    private int _RandomHeightPlusValue;

    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    private void OnInit(CallbackInfo info){
        updateNbt();
    }

    @Inject(
            method = "readNbt",
            at = @At("RETURN")
    )
    public void readNbt(@Nullable World world, BlockPos pos, NbtCompound nbt, CallbackInfo info){
        updateNbt();
    }

    private void updateNbt(){
        this.requiredPlayerRange = ModConfig.INSTANCE.BetterSpawner.Player_Range;
        this.maxNearbyEntities = ModConfig.INSTANCE.BetterSpawner.Max_Nearby;
        this.minSpawnDelay = ModConfig.INSTANCE.BetterSpawner.Min_Delay;
        this.maxSpawnDelay = ModConfig.INSTANCE.BetterSpawner.Max_Delay;
        this.spawnRange = ModConfig.INSTANCE.BetterSpawner.Spawner_Range / 2;
        this.spawnCount = ModConfig.INSTANCE.BetterSpawner.Spawner_Count;
        this._RandomHeightNextInt = ModConfig.INSTANCE.BetterSpawner.Spawner_Height;
        if(this._RandomHeightNextInt % 2 == 0 && this._RandomHeightNextInt > 0){
            this._RandomHeightNextInt -= 1;
        }
        this._RandomHeightPlusValue = this._RandomHeightNextInt / 2;
    }

    @Override
    public void updateState(boolean isReceiveRedstonePower){
        this.IsReceiveRedstonePower = isReceiveRedstonePower;
    }

    @ModifyConstant(
            method = "serverTick",
            constant = @Constant(intValue = 1, ordinal = 3)
    )
    private int getRandomHeightPlusValue(int constant){
        return this._RandomHeightPlusValue;
    }

    @ModifyConstant(
            method = "serverTick",
            constant = @Constant(intValue = 3, ordinal = 0)
    )
    private int getRandomHeightNextInt(int constant){
        return this._RandomHeightNextInt;
    }

    /**
     * @author DearXuan
     * @reason 改写生成函数
     */
    @Overwrite
    public void serverTick(ServerWorld world, BlockPos pos) {
        if (!(this.isPlayerInRange(world, pos) || this.IsReceiveRedstonePower)) {
            return;
        }
        if (this.spawnDelay == -1) {
            this.updateSpawns(world, pos);
        }
        if (this.spawnDelay > 0) {
            --this.spawnDelay;
            return;
        }
        boolean bl = false;
        Random random = world.getRandom();
        MobSpawnerEntry mobSpawnerEntry = this.getSpawnEntry(world, random, pos);
        for (int i = 0; i < this.spawnCount; ++i) {
            MobSpawnerEntry.CustomSpawnRules customSpawnRules;
            double f;
            NbtCompound nbtCompound = mobSpawnerEntry.getNbt();
            Optional<EntityType<?>> optional = EntityType.fromNbt(nbtCompound);
            if (optional.isEmpty()) {
                this.updateSpawns(world, pos);
                return;
            }
            NbtList nbtList = nbtCompound.getList("Pos", NbtElement.DOUBLE_TYPE);
            int j = nbtList.size();
            double d = j >= 1 ? nbtList.getDouble(0) : (double)pos.getX() + (random.nextDouble() - random.nextDouble()) * (double)this.spawnRange + 0.5;
            double e = j >= 2 ? nbtList.getDouble(1) : (double)(pos.getY() + random.nextInt(3) - 1);
            double d2 = f = j >= 3 ? nbtList.getDouble(2) : (double)pos.getZ() + (random.nextDouble() - random.nextDouble()) * (double)this.spawnRange + 0.5;
            if (!world.isSpaceEmpty(optional.get().createSimpleBoundingBox(d, e, f))) continue;
            BlockPos blockPos = BlockPos.ofFloored(d, e, f);
            if (!mobSpawnerEntry.getCustomSpawnRules().isPresent()
                    ? !SpawnRestriction.canSpawn(optional.get(), world, SpawnReason.SPAWNER, blockPos, world.getRandom())
                    : !optional.get().getSpawnGroup().isPeaceful() && world.getDifficulty() == Difficulty.PEACEFUL || !(customSpawnRules = mobSpawnerEntry.getCustomSpawnRules().get()).blockLightLimit().contains(world.getLightLevel(LightType.BLOCK, blockPos)) || !customSpawnRules.skyLightLimit().contains(world.getLightLevel(LightType.SKY, blockPos))) continue;
            Entity entity2 = EntityType.loadEntityWithPassengers(nbtCompound, world, entity -> {
                entity.refreshPositionAndAngles(d, e, f, entity.getYaw(), entity.getPitch());
                return entity;
            });
            if (entity2 == null) {
                this.updateSpawns(world, pos);
                return;
            }
            int k = world.getNonSpectatingEntities(entity2.getClass(), new Box(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1).expand(this.spawnRange)).size();
            if (k >= this.maxNearbyEntities) {
                this.updateSpawns(world, pos);
                return;
            }
            entity2.refreshPositionAndAngles(entity2.getX(), entity2.getY(), entity2.getZ(), random.nextFloat() * 360.0f, 0.0f);
            if (entity2 instanceof MobEntity) {
                MobEntity mobEntity = (MobEntity)entity2;
                if (!EasyTweak_MobEntity_canSpawn(mobEntity, world)) continue;
                if (mobSpawnerEntry.getNbt().getSize() == 1 && mobSpawnerEntry.getNbt().contains("id", NbtElement.STRING_TYPE)) {
                    ((MobEntity)entity2).initialize(world, world.getLocalDifficulty(entity2.getBlockPos()), SpawnReason.SPAWNER, null, null);
                }
            }
            if (!world.spawnNewEntityAndPassengers(entity2)) {
                this.updateSpawns(world, pos);
                return;
            }
            world.syncWorldEvent(WorldEvents.SPAWNER_SPAWNS_MOB, pos, 0);
            world.emitGameEvent(entity2, GameEvent.ENTITY_PLACE, blockPos);
            if (entity2 instanceof MobEntity) {
                ((MobEntity)entity2).playSpawnEffects();
            }
            bl = true;
        }
        if (bl) {
            this.updateSpawns(world, pos);
        }
    }

    private boolean EasyTweak_MobEntity_canSpawn(MobEntity instance, WorldView world){
        if(ModConfig.INSTANCE.BetterSpawner.Disable_Fluid_Restriction){
            return world.doesNotIntersectEntities(instance);
        }else{
            return instance.canSpawn(world);
        }
    }
}
