package com.dearxuan.easytweak.Event;

import com.dearxuan.easytweak.Config.ModConfig;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
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

    private final Identifier SPAWNER = Blocks.SPAWNER.getLootTableId();
    private final Identifier BUDDING_AMETHYST = Blocks.BUDDING_AMETHYST.getLootTableId();
    private final Identifier BLAZE = EntityType.BLAZE.getLootTableId();

    public LootTable() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, table, source) -> {
            if (ModConfig.INSTANCE.BetterSpawner.Enable && SPAWNER.equals(id)) {
                Add_Spawner(resourceManager, lootManager, id, table, source);
            }
            if(ModConfig.INSTANCE.LootTable.Always_Drop_Blaze_Rod && BLAZE.equals(id)){
                Add_Blaze(resourceManager, lootManager, id, table, source);
            }
            if(ModConfig.INSTANCE.LootTable.Mineable_Budding_Amethyst && BUDDING_AMETHYST.equals(id)){
                Add_BuddingAmethyst(resourceManager, lootManager, id, table, source);
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
        LootPool.Builder builder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .with(ItemEntry.builder(Items.BUDDING_AMETHYST));

        table.pool(builder);
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
        LootPool.Builder builder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .with(ItemEntry.builder(Items.BLAZE_ROD))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)).build())
                .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)).build());

        table.pool(builder);
    }
}
