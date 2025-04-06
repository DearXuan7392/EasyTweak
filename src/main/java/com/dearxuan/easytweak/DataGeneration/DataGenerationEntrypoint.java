//package com.dearxuan.easytweak.DataGeneration;
//
//import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
//import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
//import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
//import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
//import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
//import net.minecraft.block.Blocks;
//import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
//import net.minecraft.item.Items;
//import net.minecraft.recipe.book.RecipeCategory;
//
//import java.util.function.Consumer;
//
//public class DataGenerationEntrypoint implements DataGeneratorEntrypoint {
//
//    @Override
//    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
//        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
//        pack.addProvider(LootTable::new);
//        pack.addProvider(Recipe::new);
//    }
//
//    private static class LootTable extends FabricBlockLootTableProvider {
//
//        protected LootTable(FabricDataOutput dataOutput) {
//            super(dataOutput);
//        }
//
//        @Override
//        public void generate() {
//            // addDrop(Blocks.SPAWNER); // 刷怪笼掉落已添加
//            addDrop(Blocks.BUDDING_AMETHYST);
//        }
//    }
//
//    private static class Recipe extends FabricRecipeProvider {
//        public Recipe(FabricDataOutput output) {
//            super(output);
//        }
//
//        @Override
//        public void generate(Consumer<RecipeJsonProvider> exporter) {
//            // 附魔金苹果
//            ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD,
//                            Items.ENCHANTED_GOLDEN_APPLE)
//                    .pattern("III")
//                    .pattern("IAI")
//                    .pattern("III")
//                    .input('I', Items.GOLD_BLOCK)
//                    .input('A', Items.APPLE)
//                    .criterion(
//                            FabricRecipeProvider.hasItem(Items.GOLD_BLOCK),
//                            FabricRecipeProvider.conditionsFromItem(Items.GOLD_BLOCK))
//                    .criterion(
//                            FabricRecipeProvider.hasItem(Items.APPLE),
//                            FabricRecipeProvider.conditionsFromItem(Items.APPLE))
//                    .showNotification(true)
//                    .offerTo(exporter);
//
//            // 紫水晶母岩
//            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS,
//                            Items.BUDDING_AMETHYST)
//                    .pattern("##")
//                    .pattern("##")
//                    .input('#', Items.AMETHYST_BLOCK)
//                    .criterion(
//                            FabricRecipeProvider.hasItem(Items.AMETHYST_BLOCK),
//                            FabricRecipeProvider.conditionsFromItem(Items.AMETHYST_BLOCK)
//                    )
//                    .showNotification(true)
//                    .offerTo(exporter);
//        }
//    }
//}