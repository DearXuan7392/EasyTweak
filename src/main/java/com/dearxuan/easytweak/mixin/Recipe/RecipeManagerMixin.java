package com.dearxuan.easytweak.mixin.Recipe;

import com.dearxuan.easytweak.Event.Recipe;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin extends JsonDataLoader {
    public RecipeManagerMixin(Gson gson, String dataType) {
        super(gson, dataType);
    }

    @Inject(
            method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V",
            at = @At("HEAD")
    )
    private void putNewRecipes(
            Map<Identifier, JsonElement> map,
            ResourceManager resourceManager,
            Profiler profiler,
                CallbackInfo info){
        map.putAll(Recipe.getRecipeMap());
    }
}
