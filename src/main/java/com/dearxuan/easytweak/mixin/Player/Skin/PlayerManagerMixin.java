package com.dearxuan.easytweak.mixin.Player.Skin;

import com.dearxuan.easytweak.utils.skin.SkinRestorer;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {

    @Shadow
    public abstract List<ServerPlayerEntity> getPlayerList();

    @Shadow @Final
    private MinecraftServer server;

    @Inject(method = "onPlayerConnect", at = @At("HEAD"))
    private void onPlayerConnect(
            ClientConnection connection,
            ServerPlayerEntity player,
            CallbackInfo info){
        if (player.getClass() != ServerPlayerEntity.class){
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {

            });
        }
    }
}
