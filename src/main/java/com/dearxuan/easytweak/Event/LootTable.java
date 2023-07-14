package com.dearxuan.easytweak.Event;

import com.dearxuan.easytweak.Config.ModConfig;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

/**
 * 战利品加载事件
 */
public class LootTable {

    private final Identifier SPAWNER = Blocks.SPAWNER.getLootTableId();
    private final Identifier BUDDING_AMETHYST = Blocks.BUDDING_AMETHYST.getLootTableId();

    public LootTable() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, table, source) -> {
            if (ModConfig.INSTANCE.BETTER_MOB_SPAWNER && SPAWNER.equals(id)) {
                Add_Spawner(resourceManager, lootManager, id, table, source);
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
}
