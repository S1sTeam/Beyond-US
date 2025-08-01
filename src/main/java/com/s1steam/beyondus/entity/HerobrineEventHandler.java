package com.s1steam.beyondus.event;

import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;

public class HerobrineEventHandler {

    private int ticksPlayed = 0;

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent event) {
        Player player = event.player;
        if (!player.level.isClientSide()) {
            ticksPlayed++;

            // Через каждые 20*60*5 = 6000 тик (5 минут) можно повысить "разум" или вызвать эффект
            if (ticksPlayed % 6000 == 0) {
                // Тут можно реализовать логику появления Херобрина или пугалок
                player.sendSystemMessage(net.minecraft.network.chat.Component.literal("Херобрин становится умнее..."));
                // Можно запускать звук, спавнить сущность, и т.д.
            }
        }
    }
}
