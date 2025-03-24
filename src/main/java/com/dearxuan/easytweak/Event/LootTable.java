package com.dearxuan.easytweak.Event;

import com.dearxuan.easytweak.Config.ModConfig;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

/**
 * 战利品加载事件
 */
public class LootTable {

    private final Identifier Spawner = Blocks.SPAWNER.getLootTableId();
    private final Identifier BuddingAmethyst = Blocks.BUDDING_AMETHYST.getLootTableId();
    private final Identifier Blaze = EntityType.BLAZE.getLootTableId();
    private final Identifier WitherSkeleton = EntityType.WITHER_SKELETON.getLootTableId();

    public LootTable() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, table, source) -> {
            if (ModConfig.INSTANCE.BetterSpawner.Enable && Spawner.equals(id)) {
                Add_Spawner(resourceManager, lootManager, id, table, source);
            }
            if(ModConfig.INSTANCE.LootTable.Always_Drop_Blaze_Rod && Blaze.equals(id)){
                Add_Blaze(resourceManager, lootManager, id, table, source);
            }
            if(ModConfig.INSTANCE.LootTable.Mineable_Budding_Amethyst && BuddingAmethyst.equals(id)){
                Add_BuddingAmethyst(resourceManager, lootManager, id, table, source);
            }
            if(ModConfig.INSTANCE.LootTable.Wither_Skeleton_Drop_Skull && WitherSkeleton.equals(id)){
                Add_Wither_Skeleton(resourceManager, lootManager, id, table, source);
            }
        });
    }

    /**
     * 允许刷怪笼被稿子采集
     */
    private void Add_Spawner(
            ResourceManager resourceManager,
            LootManager lootManager,
            Identifier id,
            net.minecraft.loot.LootTable.Builder table,
            LootTableSource source
    ) {
        LootPool.Builder builder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .with(ItemEntry.builder(Items.SPAWNER));

        table.pool(builder);
    }

    /**
     * 允许紫水晶母岩被挖掘
     */
    private void Add_BuddingAmethyst(
            ResourceManager resourceManager,
            LootManager lootManager,
            Identifier id,
            net.minecraft.loot.LootTable.Builder table,
            LootTableSource source){
        table.pool(getBuilder(Items.BUDDING_AMETHYST));
    }

    /**
     * 烈焰人死亡时总是掉落烈焰棒
     */
    private void Add_Blaze(
            ResourceManager resourceManager,
            LootManager lootManager,
            Identifier id,
            net.minecraft.loot.LootTable.Builder table,
            LootTableSource source){
        LootPool.Builder builder = getBuilder(Items.BLAZE_ROD)
                .apply(setCount(0, 1))
                .apply(setLootingEnchantment(0, 1));
        table.pool(builder);
    }

    /**
     * 凋零骷髅在自然死亡时仍会掉落凋零头颅
     */
    private void Add_Wither_Skeleton(
            ResourceManager resourceManager,
            LootManager lootManager,
            Identifier id,
            net.minecraft.loot.LootTable.Builder table,
            LootTableSource source){
        LootPool.Builder builder_Coal = getBuilder(Items.COAL)
                .apply(setCount(-1, 1))
                .apply(setLootingEnchantment(0, 1));
        LootPool.Builder builder_Bone = getBuilder(Items.BONE)
                .apply(setCount(0, 2))
                .apply(setLootingEnchantment(0, 1));
        LootPool.Builder builder_Skull = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .with(ItemEntry.builder(Items.WITHER_SKELETON_SKULL))
                .conditionally(RandomChanceWithLootingLootCondition.builder(0.025F, 0.01F));
        table.pool(builder_Coal).pool(builder_Bone).pool(builder_Skull);
    }

    private LootPool.Builder getBuilder(Item item){
        return LootPool
                .builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .with(ItemEntry.builder(item));
    }

    private LootPool.Builder getBuilder(Item item, Number chance){
        return LootPool
                .builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .with(ItemEntry.builder(item).conditionally(RandomChanceLootCondition.builder(chance.floatValue())));
    }

    private ConditionalLootFunction.Builder<?> setCount(Number min, Number max){
        return SetCountLootFunction.builder(UniformLootNumberProvider.create(min.floatValue(), max.floatValue()));
    }

    private LootingEnchantLootFunction.Builder setLootingEnchantment(Number min, Number max){
        return LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(min.floatValue(), max.floatValue()));
    }
}
