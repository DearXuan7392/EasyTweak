package com.dearxuan.easytweak.Event;

import com.dearxuan.easytweak.Config.ModConfig;
import com.dearxuan.easytweak.Config.ModMenu.ModInfo;
import com.dearxuan.easytweak.EntryPoint.Main;
import com.google.gson.JsonElement;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Recipe {

    private static Map<Identifier, JsonElement> RecipeMap = null;

    private static Map<Identifier, Advancement.Builder> AdvancementsMap = null;

    private enum JsonType {
        Advancements,
        Recipes
    }

    public Recipe() {

    }

    public static Map<Identifier, JsonElement> getRecipeMap() {
        if (RecipeMap == null) {
            generate();
        }
        return RecipeMap;
    }

    public static Map<Identifier, Advancement.Builder> getAdvancementsMap() {
        if (AdvancementsMap == null) {
            generate();
        }
        return AdvancementsMap;
    }

    public static void generate() {
        RecipeMap = new HashMap<>();
        AdvancementsMap = new HashMap<>();
        if (ModConfig.INSTANCE.Recipes.Enchanted_Golden_Apple) {
            AddNewRecipeWithAdvancement("enchanted_golden_apple", Items.GOLD_BLOCK, Items.APPLE);
        }
        if (ModConfig.INSTANCE.Recipes.Budding_Amethyst) {
            AddNewRecipeWithAdvancement("budding_amethyst", Items.AMETHYST_BLOCK);
        }
    }

    private static void AddNewRecipeWithAdvancement(String recipeJsonFile, Item ... needToUnlock){
        Identifier identifier = new Identifier(ModInfo.ModId, recipeJsonFile);
        putRecipe(identifier);
        putAdvancements(identifier, needToUnlock);
    }

    private static void putRecipe(Identifier identifier) {
        String fullpath = "assets/easytweak/json/recipes/" + identifier.getPath() + ".json";
        try {
            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(fullpath);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            inputStream.close();
            String json = new String(bytes);
            JsonElement jsonElement = JsonHelper.deserialize(json);
            RecipeMap.put(identifier, jsonElement);
            ModInfo.LOGGER.debug("Adding Recipe: " + identifier.getPath());
        } catch (Exception e) {
            ModInfo.LOGGER.error(e);
        }
    }

    private static void putAdvancements(Identifier identifier, Item ... needToUnlock) {
        try {
            Advancement.Builder builder = Advancement.Builder
                    .create()
                    .rewards(AdvancementRewards.Builder.recipe(identifier))
                    .criterion(
                            ModInfo.ModId + "_get_" + identifier.getPath(),
                            InventoryChangedCriterion.Conditions.items(needToUnlock))
                    .criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
            AdvancementsMap.put(identifier, builder);
            ModInfo.LOGGER.debug("Adding Advancement: " + identifier.getPath());
        } catch (Exception e) {
            ModInfo.LOGGER.error(e);
        }
    }
}
