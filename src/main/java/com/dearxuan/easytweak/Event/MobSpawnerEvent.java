package com.dearxuan.easytweak.Event;

import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.MobSpawnerLogic;
import net.minecraft.world.World;

public class MobSpawnerEvent {

    /**
     * 刷怪笼丢出刷怪蛋
     */
    public static void DropEgg(World world, BlockPos pos, MobSpawnerBlockEntity mobSpawnerBlockEntity){
        MobSpawnerLogic logic = mobSpawnerBlockEntity.getLogic();

        NbtCompound nbt = new NbtCompound();
        nbt = logic.writeNbt(nbt);
        NbtElement spawnData = nbt.get(MobSpawnerLogic.SPAWN_DATA_KEY);
        if(spawnData != null){
            String entity_string = spawnData.asString();
            if(entity_string.contains("\"")){
                entity_string = entity_string.substring(entity_string.indexOf("\"") + 1);
                entity_string = entity_string.substring(0, entity_string.indexOf("\""));
                if(!entity_string.contains("area_effect_cloud")){
                    Item egg = Registries.ITEM.get(new Identifier(entity_string + "_spawn_egg"));
                    ItemStack itemStack = new ItemStack(egg);
                    ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), itemStack);
                }
            }

        }
    }
}
